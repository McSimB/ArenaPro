package javax.wireless.messaging;

public interface Message {

	String getAddress();

	void setAddress(String s);

	String getPayloadText();
}
