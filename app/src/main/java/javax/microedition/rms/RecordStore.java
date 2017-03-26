package javax.microedition.rms;

import java.io.IOException;
import java.util.Vector;

public class RecordStore {

	public static final int AUTHMODE_PRIVATE = 0;
	public static final int AUTHMODE_ANY = 1;
	private static final int AUTHMODE_ANY_RO = 2;
	private static final byte DB_INIT[] = {
			109, 105, 100, 112, 45, 114, 109, 115, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 1, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0
	};
	private static final int SIGNATURE_LENGTH = 8;
	private static final int DB_RECORD_HEADER_LENGTH = 16;
	private static final int DB_BLOCK_SIZE = 16;
	private static final int DB_COMPACTBUFFER_SIZE = 64;
	private static final Object dbCacheLock = new Object();
	private static final int RS_SIGNATURE = 0;
	private static final int RS_NUM_LIVE = 8;
	private static final int RS_AUTHMODE = 12;
	private static final int RS_VERSION = 16;
	private static final int RS_NEXT_ID = 20;
	private static final int RS_REC_START = 24;
	private static final int RS_FREE_START = 28;
	private static final int RS_LAST_MODIFIED = 32;
	private static final int RS_DATA_START = 40;
	private static final int RS_DATA_END = 44;
	private static final int bytesPer100millis = getSlowingFactor() * 200;
	private static final int latency = (5 - getSlowingFactor()) * 10;
	private static Vector<RecordStore> dbCache = new Vector<RecordStore>(3);
	private static RecordStoreFile rootRSF = new RecordStoreFile();
	private static int CACHE_SIZE = 8;
	private static byte dbState[] = new byte[DB_INIT.length];
	private static int accessCount = 0;
	private static long lastTime = 0L;
	Object rsLock;
	private String recordStoreName;
	private String uniqueIdPath;
	private int opencount;
	private RecordStoreFile dbraf;
	private Vector<RecordListener> recordListener;
	private RecordHeaderCache recHeadCache;
	private int dbNextRecordID;
	private int dbVersion;
	private int dbAuthMode;
	private int dbNumLiveRecords;
	private long dbLastModified;
	private int dbFirstRecordOffset;
	private int dbFirstFreeBlockOffset;
	private int dbDataStart;
	private int dbDataEnd;

	private RecordStore(String uidPath, String recordStoreName, boolean create)
			throws RecordStoreException, RecordStoreNotFoundException {
		dbNextRecordID = 1;
		dbDataStart = 48;
		dbDataEnd = 48;
		this.recordStoreName = recordStoreName;
		uniqueIdPath = uidPath;
		recHeadCache = new RecordHeaderCache(CACHE_SIZE);
		rsLock = new Object();
		recordListener = new Vector<RecordListener>(3);
		boolean exists = rootRSF.exists(uidPath);
		if (!create && !exists)
			throw new RecordStoreNotFoundException("cannot find record store file");
		if (create && !exists) {
			int space = rootRSF.spaceAvailable();
			if (space - DB_INIT.length < 0)
				throw new RecordStoreFullException();
		}
		try {
			dbraf = rootRSF.newRecordStoreFile(uidPath);
			if (create && !exists) {
				dbraf.seek(0);
				putLong(System.currentTimeMillis(), DB_INIT, 32);
				putInt(48, DB_INIT, 40);
				putInt(48, DB_INIT, 44);
				dbraf.write(DB_INIT);
			} else {
				byte buf[] = new byte[DB_INIT.length];
				dbraf.seek(0);
				dbraf.read(buf);
				for (int i = 0; i < 8; i++)
					if (buf[i] != DB_INIT[i])
						throw new RecordStoreException("invalid record store contents");

				dbNumLiveRecords = getInt(buf, 8);
				dbVersion = getInt(buf, 16);
				dbAuthMode = getInt(buf, 12);
				dbNextRecordID = getInt(buf, 20);
				dbFirstRecordOffset = getInt(buf, 24);
				dbFirstFreeBlockOffset = getInt(buf, 28);
				dbLastModified = getLong(buf, 32);
				dbDataStart = getInt(buf, 40);
				dbDataEnd = getInt(buf, 44);
			}
		} catch (IOException ioe) {
			try {
				if (dbraf != null)
					dbraf.close();
			} catch (IOException ioe2) {
				ioe2.printStackTrace();
			}
			dbraf = null;
			throw new RecordStoreException("error opening record store file");
		}
	}

	private static int getSlowingFactor() {
		return 1;
	}

	public static RecordStore openRecordStore(String recordStoreName, boolean createIfNecessary)
			throws RecordStoreException, RecordStoreFullException, RecordStoreNotFoundException {
		String uidPath = rootRSF.getUniqueIdPath(recordStoreName);
		int n;
		if (recordStoreName.length() > 32 || recordStoreName.length() == 0)
			throw new IllegalArgumentException();
		n = 0;
		RecordStore db;
		do {
			if (n >= dbCache.size())
				break;
			db = dbCache.elementAt(n);
			if (db.uniqueIdPath.equals(uidPath)) {
				db.opencount++;
				return db;
			}
			n++;
		} while (true);
		db = new RecordStore(uidPath, recordStoreName, createIfNecessary);
		db.opencount = 1;
		dbCache.addElement(db);
		return db;
	}

	static int getInt(byte data[], int offset) {
		int r = data[offset++];
		r = r << 8 | data[offset++] & 0xff;
		r = r << 8 | data[offset++] & 0xff;
		r = r << 8 | data[offset++] & 0xff;
		return r;
	}

	static long getLong(byte data[], int offset) {
		long r = data[offset++];
		r = r << 8 | (long) data[offset++] & 255L;
		r = r << 8 | (long) data[offset++] & 255L;
		r = r << 8 | (long) data[offset++] & 255L;
		r = r << 8 | (long) data[offset++] & 255L;
		r = r << 8 | (long) data[offset++] & 255L;
		r = r << 8 | (long) data[offset++] & 255L;
		r = r << 8 | (long) data[offset++] & 255L;
		return r;
	}

	static int putInt(int i, byte data[], int offset) {
		data[offset++] = (byte) (i >> 24 & 0xff);
		data[offset++] = (byte) (i >> 16 & 0xff);
		data[offset++] = (byte) (i >> 8 & 0xff);
		data[offset] = (byte) (i & 0xff);
		return 4;
	}

	static int putLong(long l, byte data[], int offset) {
		data[offset++] = (byte) (int) (l >> 56 & 255L);
		data[offset++] = (byte) (int) (l >> 48 & 255L);
		data[offset++] = (byte) (int) (l >> 40 & 255L);
		data[offset++] = (byte) (int) (l >> 32 & 255L);
		data[offset++] = (byte) (int) (l >> 24 & 255L);
		data[offset++] = (byte) (int) (l >> 16 & 255L);
		data[offset++] = (byte) (int) (l >> 8 & 255L);
		data[offset] = (byte) (int) (l & 255L);
		return 8;
	}

	static synchronized int getBytesQuota(int request) {
		if (bytesPer100millis == 0)
			return request;
		int granted;
		if (request > bytesPer100millis)
			granted = bytesPer100millis;
		else
			granted = request;
		try {
			Thread.sleep((100 * granted) / bytesPer100millis);
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
		return granted;
	}

	static synchronized void addLatency() {
		if (latency == 50)
			return;
		long startTime = System.currentTimeMillis() / 1000L;
		if (startTime != lastTime) {
			accessCount = 0;
			lastTime = System.currentTimeMillis() / 1000L;
		} else if (++accessCount >= 5) {
			accessCount = 0;
			try {
				Thread.sleep(latency);
			} catch (InterruptedException ie) {
				ie.printStackTrace();
			}
		}
	}

	public void closeRecordStore()
			throws RecordStoreNotOpenException, RecordStoreException {
		checkOpen();
		RecordStore db = null;
		int n = 0;
		do {
			if (n >= dbCache.size())
				break;
			db = dbCache.elementAt(n);
			if (db == this) {
				db.opencount--;
				break;
			}
			n++;
		} while (true);
		if (db.opencount > 0)
			throw new RecordStoreException("db is open");
		dbCache.removeElement(db);
		try {
			if (!recordListener.isEmpty())
				recordListener.removeAllElements();
			if (dbFirstFreeBlockOffset != 0) {
				compactRecords();
				dbraf.truncate(dbDataEnd);
			}
			dbraf.close();
		} catch (IOException ioe) {
			throw new RecordStoreException("error closing .db file");
		}
		dbraf = null;
		recHeadCache = null;
	}

	public String getName()
			throws RecordStoreNotOpenException {
		checkOpen();
		return recordStoreName;
	}

	public int getNumRecords()
			throws RecordStoreNotOpenException {
		checkOpen();
		return dbNumLiveRecords;
	}

	public int getSize()
			throws RecordStoreNotOpenException {
		checkOpen();
		return dbDataEnd;
	}

	public int getSizeAvailable() throws RecordStoreNotOpenException {
		checkOpen();
		int rv = dbraf.spaceAvailable() - 16 - 16;
		return rv >= 0 ? rv : 0;
	}

	public int addRecord(byte data[], int offset, int numBytes)
			throws RecordStoreNotOpenException, RecordStoreException, RecordStoreFullException {
		int id;
		checkOpen();
		if (!checkWritable())
			throw new SecurityException();
		if (data == null && numBytes > 0)
			throw new NullPointerException("illegal arguments: null data,  numBytes > 0");
		id = dbNextRecordID++;
		RecordHeader rh = allocateNewRecordStorage(id, numBytes);
		try {
			if (data != null)
				rh.write(data, offset);
		} catch (IOException ioe) {
			throw new RecordStoreException("error writing new record data");
		}
		dbNumLiveRecords++;
		dbVersion++;
		storeDBState();
		notifyRecordAddedListeners(id);
		return id;
	}

	public void deleteRecord(int recordId)
			throws RecordStoreNotOpenException, InvalidRecordIDException, RecordStoreException {
		synchronized (rsLock) {
			checkOpen();
			if (!checkWritable())
				throw new SecurityException();
			RecordHeader rh;
			try {
				rh = findRecord(recordId, false);
				freeRecord(rh);
				recHeadCache.invalidate(rh.id);
			} catch (IOException ioe) {
				throw new RecordStoreException("error updating file after record deletion");
			}
			dbNumLiveRecords--;
			dbVersion++;
			storeDBState();
			notifyRecordDeletedListeners(recordId);
		}
	}

	public void addRecordListener(RecordListener listener)
	{
		synchronized(rsLock)
		{
			if(!recordListener.contains(listener))
				recordListener.addElement(listener);
		}
	}

	public void removeRecordListener(RecordListener listener)
	{
		synchronized(rsLock)
		{
			recordListener.removeElement(listener);
		}
	}

	public byte[] getRecord(int recordId) throws RecordStoreNotOpenException,
			InvalidRecordIDException, RecordStoreException {
		byte data[];
		checkOpen();
		RecordHeader rh = null;
		try {
			rh = findRecord(recordId, true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (rh.dataLenOrNextFree == 0)
			return null;
		try {
			data = new byte[rh.dataLenOrNextFree];
			rh.read(data, 0);
		} catch (IOException ioe) {
			throw new RecordStoreException("error reading record data");
		}
		return data;
	}

	public void setRecord(int recordId, byte newData[], int offset, int numBytes)
			throws RecordStoreNotOpenException, InvalidRecordIDException, RecordStoreException, RecordStoreFullException {
		checkOpen();
		if (!checkWritable())
			throw new SecurityException();
		if (newData == null && numBytes > 0)
			throw new NullPointerException();
		RecordHeader rh = null;
		RecordHeader newrh = null;
		try {
			rh = findRecord(recordId, false);
		} catch (IOException ioe) {
			throw new RecordStoreException("error finding record data");
		}
		if (numBytes <= rh.blockSize - 16) {
			int allocSize = getAllocSize(numBytes);
			if (rh.blockSize - allocSize >= 32)
				splitRecord(rh, allocSize);
			rh.dataLenOrNextFree = numBytes;
			try {
				rh.store();
				recHeadCache.insert(rh);
				if (newData != null)
					rh.write(newData, offset);
			} catch (IOException ioe) {
				throw new RecordStoreException("error writing record data");
			}
		} else {
			freeRecord(rh);
			newrh = allocateNewRecordStorage(recordId, numBytes);
			try {
				if (newData != null)
					newrh.write(newData, offset);
			} catch (IOException ioe) {
				throw new RecordStoreException("error moving record data");
			}
		}
		dbVersion++;
		storeDBState();
		notifyRecordChangedListeners(recordId);
	}

	public RecordEnumeration enumerateRecords(RecordFilter filter, RecordComparator comparator, boolean keepUpdated)
			throws RecordStoreNotOpenException {
		checkOpen();
		return new RecordEnumerationImpl(this, filter, comparator, keepUpdated);
	}

	private RecordHeader findRecord(int recordId, boolean addToCache)
			throws InvalidRecordIDException, IOException {
		int cur_offset = dbFirstRecordOffset;
		if (cur_offset == 0)
			throw new InvalidRecordIDException();
		RecordHeader rh = recHeadCache.get(recordId);
		if (rh != null)
			return rh;
		rh = new RecordHeader();
		do {
			if (cur_offset == 0)
				break;
			rh.load(cur_offset);
			if (rh.id == recordId)
				break;
			cur_offset = rh.nextOffset;
		} while (true);
		if (cur_offset == 0)
			throw new InvalidRecordIDException();
		if (addToCache)
			recHeadCache.insert(rh);
		return rh;
	}

	private int getAllocSize(int numBytes) {
		int rv = 16 + numBytes;
		int pad = 16 - rv % 16;
		if (pad != 16)
			rv += pad;
		return rv;
	}

	private RecordHeader allocateNewRecordStorage(int id, int dataSize)
			throws RecordStoreException, RecordStoreFullException {
		int allocSize = getAllocSize(dataSize);
		boolean foundBlock = false;
		RecordHeader block = new RecordHeader();
		try {
			int offset = dbFirstFreeBlockOffset;
			do {
				if (offset == 0)
					break;
				block.load(offset);
				if (block.blockSize >= allocSize) {
					foundBlock = true;
					break;
				}
				offset = block.dataLenOrNextFree;
			} while (true);
		} catch (IOException ioe) {
			throw new RecordStoreException("error finding first fit block");
		}
		if (!foundBlock) {
			if (dbraf.spaceAvailable() < allocSize)
				throw new RecordStoreFullException();
			block = new RecordHeader(dbDataEnd, id, dbFirstRecordOffset, allocSize, dataSize);
			try {
				block.store();
			} catch (IOException ioe) {
				throw new RecordStoreException("error writing new record data");
			}
			dbFirstRecordOffset = dbDataEnd;
			dbDataEnd += allocSize;
		} else {
			if (block.id != -1)
				throw new RecordStoreException("ALLOC ERR " + block.id + " is not a free block!");
			removeFreeBlock(block);
			block.id = id;
			if (block.blockSize - allocSize >= 32)
				splitRecord(block, allocSize);
			block.dataLenOrNextFree = dataSize;
			try {
				block.store();
			} catch (IOException ioe) {
				throw new RecordStoreException("error writing free block after alloc");
			}
		}
		recHeadCache.insert(block);
		return block;
	}

	private void splitRecord(RecordHeader recHead, int allocSize)
			throws RecordStoreException {
		int extraSpace = recHead.blockSize - allocSize;
		int oldBlockSize = recHead.blockSize;
		recHead.blockSize = allocSize;
		if (recHead.offset != dbFirstRecordOffset) {
			int fboffset = recHead.offset + allocSize;
			RecordHeader newfb = new RecordHeader(fboffset, -1, recHead.offset, extraSpace, 0);
			try {
				freeRecord(newfb);
				RecordHeader prh = new RecordHeader(recHead.offset + oldBlockSize);
				prh.nextOffset = fboffset;
				prh.store();
				recHeadCache.invalidate(prh.id);
				storeDBState();
			} catch (IOException ioe) {
				throw new RecordStoreException("splitRecord error");
			}
		} else {
			dbDataEnd = recHead.offset + recHead.blockSize;
		}
	}

	private void freeRecord(RecordHeader rh)
			throws RecordStoreException {
		if (rh.offset == dbFirstRecordOffset) {
			dbFirstRecordOffset = rh.nextOffset;
			dbDataEnd = rh.offset;
		} else {
			rh.id = -1;
			rh.dataLenOrNextFree = dbFirstFreeBlockOffset;
			dbFirstFreeBlockOffset = rh.offset;
			try {
				rh.store();
			} catch (IOException ioe) {
				throw new RecordStoreException("free record failed");
			}
		}
	}

	private void removeFreeBlock(RecordHeader blockToFree)
			throws RecordStoreException {
		RecordHeader block = new RecordHeader();
		RecordHeader prev = new RecordHeader();
		RecordHeader tmp = null;
		try {
			for (int offset = dbFirstFreeBlockOffset; offset != 0; ) {
				block.load(offset);
				if (block.offset == blockToFree.offset) {
					if (block.id != -1)
						throw new RecordStoreException("removeFreeBlock id is not -1");
					if (prev.offset == 0) {
						dbFirstFreeBlockOffset = block.dataLenOrNextFree;
					} else {
						prev.dataLenOrNextFree = block.dataLenOrNextFree;
						prev.store();
					}
				}
				offset = block.dataLenOrNextFree;
				tmp = prev;
				prev = block;
				block = tmp;
			}

		} catch (IOException ioe) {
			throw new RecordStoreException("removeFreeBlock block not found");
		}
	}

	private void storeDBState()
			throws RecordStoreException {
		try {
			dbLastModified = System.currentTimeMillis();
			putInt(dbNumLiveRecords, dbState, 8);
			putInt(dbAuthMode, dbState, 12);
			putInt(dbVersion, dbState, 16);
			putInt(dbNextRecordID, dbState, 20);
			putInt(dbFirstRecordOffset, dbState, 24);
			putInt(dbFirstFreeBlockOffset, dbState, 28);
			putLong(dbLastModified, dbState, 32);
			putInt(dbDataStart, dbState, 40);
			putInt(dbDataEnd, dbState, 44);
			dbraf.seek(8);
			int numbytes = DB_INIT.length - 8;
			dbraf.write(dbState, 8, numbytes);
		} catch (IOException ioe) {
			throw new RecordStoreException("error writing record store attributes");
		}
	}

	boolean isOpen() {
		return dbraf != null;
	}

	private void checkOpen() throws RecordStoreNotOpenException {
		if (dbraf == null)
			throw new RecordStoreNotOpenException();
	}

	private void notifyRecordChangedListeners(int recordId) {
		for (int i = 0; i < recordListener.size(); i++) {
			RecordListener rl = recordListener.elementAt(i);
			rl.recordChanged(this, recordId);
		}

	}

	private void notifyRecordAddedListeners(int recordId) {
		for (int i = 0; i < recordListener.size(); i++) {
			RecordListener rl = recordListener.elementAt(i);
			rl.recordAdded(this, recordId);
		}

	}

	private void notifyRecordDeletedListeners(int recordId) {
		for (int i = 0; i < recordListener.size(); i++) {
			RecordListener rl = recordListener.elementAt(i);
			rl.recordDeleted(this, recordId);
		}

	}

	int[] getRecordIDs() {
		if (dbraf == null)
			return null;
		int index = 0;
		int tmp[] = new int[dbNumLiveRecords];
		int offset = dbFirstRecordOffset;
		RecordHeader rh = new RecordHeader();
		try {
			for (; offset != 0; offset = rh.nextOffset) {
				rh.load(offset);
				if (rh.id > 0)
					tmp[index++] = rh.id;
			}

		} catch (IOException ioe) {
			return null;
		}
		return tmp;
	}

	private void compactRecords()
			throws RecordStoreNotOpenException, RecordStoreException {
		int offset = dbDataStart;
		int target = 0;
		byte chunkBuffer[] = new byte[64];
		RecordHeader rh = new RecordHeader();
		int prevRec = 0;
		while (offset < dbDataEnd) {
			try {
				rh.load(offset);
			} catch (IOException ioe) {
				System.out.println("Unexpected IOException in CompactRS!");
			}
			if (rh.id == -1) {
				if (target == 0)
					target = offset;
				offset += rh.blockSize;
			} else if (target == 0) {
				prevRec = offset;
				offset += rh.blockSize;
			} else {
				int old_offset = target;
				rh.offset = target;
				rh.nextOffset = prevRec;
				try {
					rh.store();
					offset += 16;
					target += 16;
					int numToMove;
					for (int bytesLeft = rh.blockSize - 16; bytesLeft > 0; bytesLeft -= numToMove) {
						if (bytesLeft < 64)
							numToMove = bytesLeft;
						else
							numToMove = 64;
						dbraf.seek(offset);
						dbraf.read(chunkBuffer, 0, numToMove);
						dbraf.seek(target);
						dbraf.write(chunkBuffer, 0, numToMove);
						offset += numToMove;
						target += numToMove;
					}

				} catch (IOException ioe) {
					System.out.println("Unexpected IOException in CompactRS!");
				}
				prevRec = old_offset;
			}
		}
		if (rh.offset != 0)
			dbDataEnd = rh.offset + rh.blockSize;
		dbFirstRecordOffset = rh.offset;
		dbFirstFreeBlockOffset = 0;
		storeDBState();
	}

	private boolean checkOwner() {
		String myUid = dbraf.getUniqueIdPath(recordStoreName);
		String rsfUid = dbraf.getUniqueIdPath();
		return myUid.equals(rsfUid);
	}

	private boolean checkWritable() {
		return checkOwner() || dbAuthMode == 1;
	}

	private class RecordHeader {

		private static final int REC_ID = 0;
		private static final int NEXT_OFFSET = 4;
		private static final int BLOCK_SIZE = 8;
		private static final int DATALEN_OR_NEXTFREE = 12;
		private static final int DATA_OFFSET = 16;
		int offset;
		int id;
		int nextOffset;
		int blockSize;
		int dataLenOrNextFree;

		RecordHeader() {
		}

		RecordHeader(int _offset)
				throws IOException {
			load(_offset);
		}

		RecordHeader(int _offset, int _id, int next_offset, int size, int len_or_free) {
			offset = _offset;
			id = _id;
			nextOffset = next_offset;
			blockSize = size;
			dataLenOrNextFree = len_or_free;
		}

		void load(int _offset) throws IOException {
			offset = _offset;
			dbraf.seek(offset);
			dbraf.read(dbState, 0, 16);
			id = RecordStore.getInt(dbState, 0);
			nextOffset = RecordStore.getInt(dbState, 4);
			blockSize = RecordStore.getInt(dbState, 8);
			dataLenOrNextFree = RecordStore.getInt(dbState, 12);
		}

		void store() throws IOException {
			RecordStore.putInt(id, dbState, 0);
			RecordStore.putInt(nextOffset, dbState, 4);
			RecordStore.putInt(blockSize, dbState, 8);
			RecordStore.putInt(dataLenOrNextFree, dbState, 12);
			dbraf.seek(offset);
			dbraf.write(dbState, 0, 16);
		}

		int read(byte buf[], int _offset) throws IOException {
			dbraf.seek(offset + 16);
			RecordStore.addLatency();
			int bytesRead = dbraf.read(buf, _offset, dataLenOrNextFree);
			int tmp = bytesRead;
			while (tmp > 0) {
				tmp -= RecordStore.getBytesQuota(tmp);
			}
			return bytesRead;
		}

		void write(byte buf[], int _offset) throws IOException {
			dbraf.seek(offset + 16);
			RecordStore.addLatency();
			dbraf.write(buf, _offset, dataLenOrNextFree);
			int tmp = dataLenOrNextFree;
			while (tmp > 0) {
				tmp -= RecordStore.getBytesQuota(tmp);
			}
		}
	}

	private class RecordHeaderCache {

		private RecordStore.RecordHeader mCache[];

		RecordHeaderCache(int size) {
			mCache = new RecordStore.RecordHeader[size];
		}

		RecordStore.RecordHeader get(int rec_id) {
			int idx = rec_id % mCache.length;
			RecordStore.RecordHeader rh = mCache[idx];
			if (mCache[idx] != null && mCache[idx].id != rec_id)
				return null;
			else
				return rh;
		}

		void insert(RecordStore.RecordHeader rh) {
			int idx = rh.id % mCache.length;
			mCache[idx] = rh;
		}

		void invalidate(int rec_id) {
			if (rec_id > 0) {
				int idx = rec_id % mCache.length;
				if (mCache[idx] != null && mCache[idx].id == rec_id)
					mCache[idx] = null;
			}
		}
	}

}