package javax.wireless.messaging;

import java.io.IOException;
import java.io.InterruptedIOException;

import javax.microedition.io.Connection;

public interface MessageConnection extends Connection {

	public abstract Message newMessage(String s);

	public abstract Message newMessage(String s, String s1);

	public abstract void send(Message message)
			throws IOException, InterruptedIOException;

	public abstract Message receive()
			throws IOException, InterruptedIOException;

	public abstract int numberOfSegments(Message message);

	public static final String TEXT_MESSAGE = "text";
	public static final String BINARY_MESSAGE = "binary";
	public static final String MULTIPART_MESSAGE = "multipart";
}