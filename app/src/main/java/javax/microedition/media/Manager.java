package javax.microedition.media;

import android.webkit.MimeTypeMap;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import javax.microedition.io.file.FileConnection;
import javax.microedition.util.ContextHolder;

public class Manager {

    private static class StreamCacheCleaner implements PlayerListener {
        public void playerUpdate(Player player, String event, Object eventData) {
            if (PlayerListener.CLOSED.equals(event) && eventData instanceof String) {
                event = (String) eventData;
                int index = event.lastIndexOf('/');

                if (index >= 0) {
                    event = event.substring(index + 1);
                }

                File file = new File(ContextHolder.getCacheDir(), event);

                if (file.delete()) {
                    System.out.println("Temp file deleted: " + event);
                }
            }
        }
    }

    private static StreamCacheCleaner cleaner = new StreamCacheCleaner();

    @SuppressWarnings("unused")
    public static Player createPlayer(String locator) throws IOException {
        return new MicroPlayer(new DataSource(locator));
    }

    public static Player createPlayer(final InputStream stream, String type) throws IOException {
        if (type != null) {
            type = MimeTypeMap.getSingleton().getExtensionFromMimeType(type);

            if (type != null) {
                type = "." + type;
            }
        }

        File file = File.createTempFile("media", type, ContextHolder.getCacheDir());
        final RandomAccessFile raf = new RandomAccessFile(file, FileConnection.READ_WRITE);

        final String name = file.getName();
        System.out.println("Starting media pipe: " + name);

        int length = stream.available();

        if (length >= 0) {
            raf.setLength(length);
            System.out.println("Changing file size to " + length + " bytes: " + name);
        }

        final Object sync = new Object();

        Runnable runnable = new Runnable() {
            public void run() {
                byte[] buf = new byte[0x10000];
                int read;

                try {
                    while (true) {
                        read = stream.read(buf);

                        if (read > 0) {
                            synchronized (sync) {
                                raf.write(buf, 0, read);
                            }
                        } else if (read < 0) {
                            break;
                        }
                    }

                    raf.close();

                    System.out.println("Media pipe closed: " + name);
                } catch (IOException e) {
                    System.out.println("Media pipe failure: " + e.toString());
                }
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();

        try {
            MicroPlayer player = new MicroPlayer();
            player.addPlayerListener(cleaner);

            DataSource source = new DataSource(file);

            try {
                player.setDataSource(source);
            } catch (IOException e) {
                source.close();

                if (thread.isAlive()) {
                    System.out.println("Waiting for pipe to close: " + name);

                    try {
                        thread.join();
                    } catch (InterruptedException ee) {
                        ee.printStackTrace();
                    }

                    player.setDataSource(source);
                } else {
                    throw e;
                }
            }

            return player;
        } catch (IOException e) {
            try {
                synchronized (sync) {
                    raf.close();
                }
            } catch (IOException x) {
                System.out.println("File is not closing: " + name);
            }

            cleaner.playerUpdate(null, PlayerListener.CLOSED, name);

            throw e;
        }
    }
}
