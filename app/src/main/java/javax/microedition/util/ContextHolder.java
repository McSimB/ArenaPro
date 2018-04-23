/*
 * Copyright 2012 Kulikov Dmitriy
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package javax.microedition.util;

import android.content.Context;
import android.os.Environment;
import android.view.Display;
import android.view.WindowManager;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class ContextHolder {

    private static MainActivity context;
    private static Display display;

    public static void setContext(MainActivity cx) {
        context = cx;
    }

    public static MainActivity getContext() {
        if (context == null) {
            throw new IllegalStateException("call setContext() before calling getContext()");
        }

        return context;
    }

    public static Display getDisplay() {
        if (display == null) {
            WindowManager manager = (WindowManager) getContext()
                    .getSystemService(Context.WINDOW_SERVICE);
            if (manager != null) {
                display = manager.getDefaultDisplay();
            }
        }

        return display;
    }

    public static int getDisplayWidth() {
        return getDisplay().getWidth();
    }

    public static int getDisplayHeight() {
        return getDisplay().getHeight();
    }

    public static InputStream getResourceAsStream(String filename) throws IOException {
        if (filename.startsWith("/")) {
            filename = filename.substring(1);
        }

        return getContext().getAssets().open(filename);
    }

    public static File getCacheDir() {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            return getContext().getExternalCacheDir();
        } else {
            return getContext().getCacheDir();
        }
    }
}