package javax.microedition.media;

import android.content.res.AssetFileDescriptor;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;

import javax.microedition.util.ContextHolder;

public class DataSource {

    protected String locator;
    protected File file;

    protected FileInputStream stream;
    protected AssetFileDescriptor asset;
    protected FileDescriptor descriptor;
    protected long offset;
    protected long length;

    public DataSource(String locator) {
        this.locator = locator;
    }

    public DataSource(File file) {
        this.file = file;
    }

    public void open() throws IOException {
        if (descriptor == null) {
            if (locator != null && !locator.contains("://")) {
                if (locator.startsWith("/")) {
                    locator = locator.substring(1);
                }

                asset = ContextHolder.getContext().getAssets().openFd(locator);

                descriptor = asset.getFileDescriptor();

                offset = asset.getStartOffset();
                length = asset.getLength();
            } else if (file != null) {
                stream = new FileInputStream(file);

                descriptor = stream.getFD();

                offset = 0;
                length = file.length();
            }
        }
    }

    public void close() {
        if (stream != null) {
            try {
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            stream = null;
        }

        if (asset != null) {
            try {
                asset.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            asset = null;
        }

        descriptor = null;
    }

    public String getURL() {
        if (locator != null) {
            return locator;
        } else {
            return "file://" + file.getAbsolutePath();
        }
    }

    public void setFor(MediaPlayer player) throws IOException {
        open();

        if (descriptor != null) {
            player.setDataSource(descriptor, offset, length);
        } else {
            player.setDataSource(locator);
        }
    }

    public void setFor(MediaMetadataRetriever retriever) throws IOException {
        open();

        if (descriptor != null) {
            retriever.setDataSource(descriptor, 0, length);
        } else {
            retriever.setDataSource(locator);
        }
    }
}