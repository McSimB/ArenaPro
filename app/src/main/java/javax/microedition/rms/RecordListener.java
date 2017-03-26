package javax.microedition.rms;

public interface RecordListener {

	public abstract void recordAdded(RecordStore recordstore, int i);

	public abstract void recordChanged(RecordStore recordstore, int i);

	public abstract void recordDeleted(RecordStore recordstore, int i);

}