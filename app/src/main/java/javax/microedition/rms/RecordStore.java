package javax.microedition.rms;

public class RecordStore {

	public static RecordStore openRecordStore(String var1, boolean b) {
		return null;
	}

	public RecordEnumeration enumerateRecords(RecordFilter recordFilter,
											  RecordComparator recordComparator, boolean b) {
		return null;
	}

	public void closeRecordStore() throws RecordStoreNotOpenException, RecordStoreException {
	}

	public int getNumRecords() {
		return 0;
	}

	public void addRecord(byte[] var2, int i, int length) {
	}

	public void deleteRecord(int var4) {
	}

	public String getSize() {
		return "";
	}

	public String getSizeAvailable() {
		return "";
	}

	public byte[] getRecord(int i) {
		return null;
	}

	public void setRecord(int var_bd9, byte[] var16, int i, int length) {
	}

}