package javax.wireless.messaging;

public interface BinaryMessage extends Message {

	public abstract byte[] getPayloadData();

	public abstract void setPayloadData(byte abyte0[]);
}