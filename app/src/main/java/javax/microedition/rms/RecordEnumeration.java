package javax.microedition.rms;

public interface RecordEnumeration {

	public abstract int numRecords();

	public abstract byte[] nextRecord()
			throws InvalidRecordIDException, RecordStoreNotOpenException, RecordStoreException;

	public abstract int nextRecordId()
			throws InvalidRecordIDException;

	public abstract byte[] previousRecord()
			throws InvalidRecordIDException, RecordStoreNotOpenException, RecordStoreException;

	public abstract int previousRecordId()
			throws InvalidRecordIDException;

	public abstract boolean hasNextElement();

	public abstract boolean hasPreviousElement();

	public abstract void reset();

	public abstract void rebuild();

	public abstract void keepUpdated(boolean flag);

	public abstract boolean isKeptUpdated();

	public abstract void destroy();
}
