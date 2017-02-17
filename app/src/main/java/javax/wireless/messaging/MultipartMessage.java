package javax.wireless.messaging;

public interface MultipartMessage extends Message {

	public abstract boolean addAddress(String s, String s1);

	public abstract void addMessagePart(MessagePart messagepart);

	public abstract String getAddress();

	public abstract String[] getAddresses(String s);

	public abstract String getHeader(String s);

	public abstract MessagePart getMessagePart(String s);

	public abstract MessagePart[] getMessageParts();

	public abstract String getStartContentId();

	public abstract String getSubject();

	public abstract boolean removeAddress(String s, String s1);

	public abstract void removeAddresses();

	public abstract void removeAddresses(String s);

	public abstract boolean removeMessagePart(MessagePart messagepart);

	public abstract boolean removeMessagePartId(String s);

	public abstract boolean removeMessagePartLocation(String s);

	public abstract void setAddress(String s);

	public abstract void setHeader(String s, String s1);

	public abstract void setStartContentId(String s);

	public abstract void setSubject(String s);
}
