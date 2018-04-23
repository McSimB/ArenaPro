package javax.microedition.media;

public class MediaException extends Exception {

    public MediaException(Exception e) {
        super(e);
    }

    public MediaException(String message) {
        super(message);
    }
}