package com.siemens.mp.lcdui;

import android.graphics.Bitmap;
import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import static com.elkware.midp.games.Arena1.APP_DATA_DIR;

public class Image {

    public static void writePngToFile(javax.microedition.lcdui.Image image, String name)
            throws IOException {
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() +
                APP_DATA_DIR;
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File file = new File(dir, name);
        FileOutputStream fOut = new FileOutputStream(file);

        image.getBitmap().compress(Bitmap.CompressFormat.PNG, 85, fOut);
        fOut.flush();
        fOut.close();
    }

    public static void writeImageToFile(javax.microedition.lcdui.Image image,
                                        String s, int i) throws IOException {
        // TODO : writeImageToFile
    }

    public static javax.microedition.lcdui.Image createImageFromFile(String name, int w, int h)
            throws IOException {
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() +
                APP_DATA_DIR;
        File dir = new File(path);
        if (!dir.exists()) {
            throw new IOException();
        }
        File file = new File(dir, name);
        FileInputStream fIn = new FileInputStream(file);
        javax.microedition.lcdui.Image image = javax.microedition.lcdui.Image.createImage(fIn);
        image = image.scale(w, h, true);
        fIn.close();

        return image;
    }

    public static int getPixelColor(javax.microedition.lcdui.Image image, int x, int y)
            throws IllegalArgumentException {
        return image.getBitmap().getPixel(x, y);
    }

    public static void setPixelColor(javax.microedition.lcdui.Image image, int x, int y, int col)
            throws IllegalArgumentException {
        image.getBitmap().setPixel(x, y, col);
    }
}