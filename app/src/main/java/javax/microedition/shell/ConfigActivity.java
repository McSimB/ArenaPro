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

package javax.microedition.shell;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;

import com.elkware.midp.games.colorng.arena.high.R;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.MicroActivity;
import javax.microedition.lcdui.XMLForm;
import javax.microedition.lcdui.pointer.VirtualKeyboard;
import javax.microedition.midlet.MIDlet;
import javax.microedition.param.DataContainer;
import javax.microedition.param.DataEditor;
import javax.microedition.param.SharedPreferencesContainer;
import javax.microedition.util.ContextHolder;

import yuku.ambilwarna.AmbilWarnaDialog;

public class ConfigActivity extends MicroActivity implements CommandListener, View.OnKeyListener,
        View.OnClickListener {

    protected XMLForm form;

    protected EditText tfScreenWidth;
    protected EditText tfScreenHeight;
    protected EditText tfScreenBack;
    protected CheckBox cxScaleToFit;
    protected CheckBox cxKeepAspectRatio;
    protected CheckBox cxFilter;

    protected EditText tfFontSizeSmall;
    protected EditText tfFontSizeMedium;
    protected EditText tfFontSizeLarge;
    protected CheckBox cxFontSizeInSP;

    protected SeekBar sbVKAlpha;
    protected EditText tfVKHideDelay;
    protected EditText tfVKLayoutKeyCode;
    protected EditText tfVKFore;
    protected EditText tfVKBack;
    protected EditText tfVKSelFore;
    protected EditText tfVKSelBack;
    protected EditText tfVKOutline;

    protected ArrayList<Integer> screenWidths;
    protected ArrayList<Integer> screenHeights;
    protected ArrayList<String> screenAdapter;

    protected ArrayList<Integer> fontSmall;
    protected ArrayList<Integer> fontMedium;
    protected ArrayList<Integer> fontLarge;
    protected ArrayList<String> fontAdapter;

    protected String locale;

    protected Handler handler;
    protected Runnable resetMessage;

	/*
	 * <xml locale=en>../../../../res/values/strings.xml</xml>
	 * <xml locale=ru>../../../../res/values-ru/strings.xml</xml>
	 */

    public String getString(int index, String token) {
        return getString(index).replace("%A", token);
    }

    public String getString(int index, String[] tokens) {
        String res = getString(index);

        for (int i = 0; i < tokens.length; i++) {
            res = res.replace("%" + (char) ('A' + i), tokens[i]);
        }

        return res;
    }

    @SuppressLint("StringFormatMatches")
    public void onCreate(Bundle savedInstanceState) {
        DataContainer params = new SharedPreferencesContainer("RunConfiguration",
                Context.MODE_PRIVATE);

        locale = params.getString("Locale", MIDlet.getDefaultLocale());
        ((MIDlet) getApplication()).setLocale(locale);

        form = new XMLForm(getApplicationInfo().name, R.layout.config_all);

        form.addCommand(new Command(getString(R.string.START_CMD), Command.OK, 1));
        form.addCommand(new Command(getString(R.string.RESET_CMD), Command.ITEM, 2));
        form.addCommand(new Command(getString(R.string.CANCEL_CMD), Command.EXIT, 3));
        form.setCommandListener(this);

        setCurrent(form);

        tfScreenWidth = findViewById(R.id.tfScreenWidth);
        tfScreenHeight = findViewById(R.id.tfScreenHeight);
        tfScreenBack = findViewById(R.id.tfScreenBack);
        cxScaleToFit = findViewById(R.id.cxScaleToFit);
        cxKeepAspectRatio = findViewById(R.id.cxKeepAspectRatio);
        cxFilter = findViewById(R.id.cxFilter);

        tfFontSizeSmall = findViewById(R.id.tfFontSizeSmall);
        tfFontSizeMedium = findViewById(R.id.tfFontSizeMedium);
        tfFontSizeLarge = findViewById(R.id.tfFontSizeLarge);
        cxFontSizeInSP = findViewById(R.id.cxFontSizeInSP);

        sbVKAlpha = findViewById(R.id.sbVKAlpha);
        tfVKHideDelay = findViewById(R.id.tfVKHideDelay);
        tfVKLayoutKeyCode = findViewById(R.id.tfVKLayoutKeyCode);
        tfVKFore = findViewById(R.id.tfVKFore);
        tfVKBack = findViewById(R.id.tfVKBack);
        tfVKSelFore = findViewById(R.id.tfVKSelFore);
        tfVKSelBack = findViewById(R.id.tfVKSelBack);
        tfVKOutline = findViewById(R.id.tfVKOutline);

        screenWidths = new ArrayList();
        screenHeights = new ArrayList();
        screenAdapter = new ArrayList();

        fillScreenSizePresets(ContextHolder.getDisplayWidth(), ContextHolder.getDisplayHeight());

        fontSmall = new ArrayList();
        fontMedium = new ArrayList();
        fontLarge = new ArrayList();
        fontAdapter = new ArrayList();

        addFontSizePreset("128 x 128", 9, 13, 15);
        addFontSizePreset("128 x 160", 13, 15, 20);
        addFontSizePreset("176 x 220", 15, 18, 22);
        addFontSizePreset("240 x 320", 18, 22, 26);

        tfVKLayoutKeyCode.setOnKeyListener(this);

        findViewById(R.id.cmdScreenSizePresets).setOnClickListener(this);
        findViewById(R.id.cmdFontSizePresets).setOnClickListener(this);
        findViewById(R.id.cmdScreenBack).setOnClickListener(this);
        findViewById(R.id.cmdVKBack).setOnClickListener(this);
        findViewById(R.id.cmdVKFore).setOnClickListener(this);
        findViewById(R.id.cmdVKSelBack).setOnClickListener(this);
        findViewById(R.id.cmdVKSelFore).setOnClickListener(this);
        findViewById(R.id.cmdVKOutline).setOnClickListener(this);
        findViewById(R.id.cmdLanguage).setOnClickListener(this);

        handler = new Handler();

        resetMessage = new Runnable() {
            public void run() {
                SharedPreferencesContainer params = new SharedPreferencesContainer("RunConfiguration", Context.MODE_WORLD_READABLE);
                params.edit().clear().commit();
                params.close();

                loadParams(params);
            }
        };

        loadParams(params);

        String[] locales = getResources().getStringArray(R.array.locales);
        int index = -1;

        for (int i = 0; i < locales.length; i++) {
            if (locales[i].equalsIgnoreCase(locale)) {
                index = i;
                break;
            }
        }

        String language;

        if (index >= 0) {
            language = getResources().getStringArray(R.array.languages)[index];
        } else {
            language = locale;
        }

        //TODO : lang
        ((Button) findViewById(R.id.cmdLanguage)).setText(getString(R.string.PREF_LANGUAGE, language));

        super.onCreate(savedInstanceState);
    }

    public void onPause() {
        SharedPreferencesContainer params = new SharedPreferencesContainer("RunConfiguration", Context.MODE_WORLD_READABLE);

        params.edit();
        saveParams(params);
        params.close();

        super.onPause();
    }

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        fillScreenSizePresets(ContextHolder.getDisplayWidth(), ContextHolder.getDisplayHeight());
    }

    public void fillScreenSizePresets(int w, int h) {
        screenWidths.clear();
        screenHeights.clear();
        screenAdapter.clear();

        addScreenSizePreset(128, 128);
        addScreenSizePreset(128, 160);
        addScreenSizePreset(132, 176);
        addScreenSizePreset(176, 220);
        addScreenSizePreset(240, 320);

        int w2 = w / 2;
        int h2 = h / 2;

        if (w > h) {
            addScreenSizePreset(h2 * 3 / 4, h2);
            addScreenSizePreset(h2 * 4 / 3, h2);

            addScreenSizePreset(h * 3 / 4, h);
            addScreenSizePreset(h * 4 / 3, h);
        } else {
            addScreenSizePreset(w2, w2 * 4 / 3);
            addScreenSizePreset(w2, w2 * 3 / 4);

            addScreenSizePreset(w, w * 4 / 3);
            addScreenSizePreset(w, w * 3 / 4);
        }

        addScreenSizePreset(w, h);
    }

    public void addScreenSizePreset(int width, int height) {
        screenWidths.add(width);
        screenHeights.add(height);
        screenAdapter.add(Integer.toString(width) + " x " + Integer.toString(height));
    }

    public void addFontSizePreset(String title, int small, int medium, int large) {
        fontSmall.add(small);
        fontMedium.add(medium);
        fontLarge.add(large);
        fontAdapter.add(title);
    }

    public void loadParams(DataContainer params) {
        tfScreenWidth.setText(Integer.toString(params.getInt("ScreenWidth", 240)));
        tfScreenHeight.setText(Integer.toString(params.getInt("ScreenHeight", 320)));
        tfScreenBack.setText(Integer.toHexString(params.getInt("ScreenBackgroundColor", 0xD0D0D0)).toUpperCase());
        cxScaleToFit.setChecked(params.getBoolean("ScreenScaleToFit", true));
        cxKeepAspectRatio.setChecked(params.getBoolean("ScreenKeepAspectRatio", true));
        cxFilter.setChecked(params.getBoolean("ScreenFilter", true));

        tfFontSizeSmall.setText(Integer.toString(params.getInt("FontSizeSmall", 18)));
        tfFontSizeMedium.setText(Integer.toString(params.getInt("FontSizeMedium", 22)));
        tfFontSizeLarge.setText(Integer.toString(params.getInt("FontSizeLarge", 26)));
        cxFontSizeInSP.setChecked(params.getBoolean("FontApplyDimensions", false));

        sbVKAlpha.setProgress(params.getInt("VirtualKeyboardAlpha", 64));
        tfVKHideDelay.setText(Integer.toString(params.getInt("VirtualKeyboardDelay", -1)));
        tfVKLayoutKeyCode.setText(Integer.toString(params.getInt("VirtualKeyboardLayoutKeyCode", Canvas.convertAndroidKeyCode(KeyEvent.KEYCODE_MENU))));
        tfVKBack.setText(Integer.toHexString(params.getInt("VirtualKeyboardColorBackground", 0xD0D0D0)).toUpperCase());
        tfVKFore.setText(Integer.toHexString(params.getInt("VirtualKeyboardColorForeground", 0x000080)).toUpperCase());
        tfVKSelBack.setText(Integer.toHexString(params.getInt("VirtualKeyboardColorBackgroundSelected", 0x000080)).toUpperCase());
        tfVKSelFore.setText(Integer.toHexString(params.getInt("VirtualKeyboardColorForegroundSelected", 0xFFFFFF)).toUpperCase());
        tfVKOutline.setText(Integer.toHexString(params.getInt("VirtualKeyboardColorOutline", 0xFFFFFF)).toUpperCase());
    }

    public void saveParams(DataEditor editor) {
        try {
            editor.putString("Locale", locale);

            editor.putInt("ScreenWidth", Integer.parseInt(tfScreenWidth.getText().toString()));
            editor.putInt("ScreenHeight", Integer.parseInt(tfScreenHeight.getText().toString()));
            editor.putInt("ScreenBackgroundColor", Integer.parseInt(tfScreenBack.getText().toString(), 16));
            editor.putBoolean("ScreenScaleToFit", cxScaleToFit.isChecked());
            editor.putBoolean("ScreenKeepAspectRatio", cxKeepAspectRatio.isChecked());
            editor.putBoolean("ScreenFilter", cxFilter.isChecked());

            editor.putInt("FontSizeSmall", Integer.parseInt(tfFontSizeSmall.getText().toString()));
            editor.putInt("FontSizeMedium", Integer.parseInt(tfFontSizeMedium.getText().toString()));
            editor.putInt("FontSizeLarge", Integer.parseInt(tfFontSizeLarge.getText().toString()));
            editor.putBoolean("FontApplyDimensions", cxFontSizeInSP.isChecked());

            editor.putInt("VirtualKeyboardAlpha", sbVKAlpha.getProgress());
            editor.putInt("VirtualKeyboardDelay", Integer.parseInt(tfVKHideDelay.getText().toString()));
            editor.putInt("VirtualKeyboardLayoutKeyCode", Integer.parseInt(tfVKLayoutKeyCode.getText().toString()));
            editor.putInt("VirtualKeyboardColorBackground", Integer.parseInt(tfVKBack.getText().toString(), 16));
            editor.putInt("VirtualKeyboardColorForeground", Integer.parseInt(tfVKFore.getText().toString(), 16));
            editor.putInt("VirtualKeyboardColorBackgroundSelected", Integer.parseInt(tfVKSelBack.getText().toString(), 16));
            editor.putInt("VirtualKeyboardColorForegroundSelected", Integer.parseInt(tfVKSelFore.getText().toString(), 16));
            editor.putInt("VirtualKeyboardColorOutline", Integer.parseInt(tfVKOutline.getText().toString(), 16));

            editor.apply();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public void applyConfiguration() {
        try {
            int fontSizeSmall = Integer.parseInt(tfFontSizeSmall.getText().toString());
            int fontSizeMedium = Integer.parseInt(tfFontSizeMedium.getText().toString());
            int fontSizeLarge = Integer.parseInt(tfFontSizeLarge.getText().toString());
            boolean fontApplyDimensions = cxFontSizeInSP.isChecked();

            int screenWidth = Integer.parseInt(tfScreenWidth.getText().toString());
            int screenHeight = Integer.parseInt(tfScreenHeight.getText().toString());
            int screenBackgroundColor = Integer.parseInt(tfScreenBack.getText().toString(), 16);
            boolean screenScaleToFit = cxScaleToFit.isChecked();
            boolean screenKeepAspectRatio = cxKeepAspectRatio.isChecked();
            boolean screenFilter = cxFilter.isChecked();

            int vkAlpha = sbVKAlpha.getProgress();
            int vkDelay = Integer.parseInt(tfVKHideDelay.getText().toString());
            int vkLayoutKeyCode = Integer.parseInt(tfVKLayoutKeyCode.getText().toString());
            int vkColorBackground = Integer.parseInt(tfVKBack.getText().toString(), 16);
            int vkColorForeground = Integer.parseInt(tfVKFore.getText().toString(), 16);
            int vkColorBackgroundSelected = Integer.parseInt(tfVKSelBack.getText().toString(), 16);
            int vkColorForegroundSelected = Integer.parseInt(tfVKSelFore.getText().toString(), 16);
            int vkColorOutline = Integer.parseInt(tfVKOutline.getText().toString(), 16);

            Font.setSize(Font.SIZE_SMALL, fontSizeSmall);
            Font.setSize(Font.SIZE_MEDIUM, fontSizeMedium);
            Font.setSize(Font.SIZE_LARGE, fontSizeLarge);
            Font.setApplyDimensions(fontApplyDimensions);

            Canvas.setVirtualSize(screenWidth, screenHeight, screenScaleToFit, screenKeepAspectRatio);
            Canvas.setFilterBitmap(screenFilter);
            Canvas.setBackgroundColor(screenBackgroundColor);

            VirtualKeyboard vk = new VirtualKeyboard();

            vk.setOverlayAlpha(vkAlpha);
            vk.setHideDelay(vkDelay);
            vk.setLayoutEditKey(vkLayoutKeyCode);

            try {
                DataInputStream dis = new DataInputStream(ContextHolder.getContext().openFileInput("VirtualKeyboardLayout"));
                vk.readLayout(dis);
                dis.close();
            } catch (FileNotFoundException fnfe) {
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }

            vk.setColor(VirtualKeyboard.BACKGROUND, vkColorBackground);
            vk.setColor(VirtualKeyboard.FOREGROUND, vkColorForeground);
            vk.setColor(VirtualKeyboard.BACKGROUND_SELECTED, vkColorBackgroundSelected);
            vk.setColor(VirtualKeyboard.FOREGROUND_SELECTED, vkColorForegroundSelected);
            vk.setColor(VirtualKeyboard.OUTLINE, vkColorOutline);

            VirtualKeyboard.LayoutListener listener = new VirtualKeyboard.LayoutListener() {
                public void layoutChanged(VirtualKeyboard vk) {
                    try {
                        DataOutputStream dos = new DataOutputStream(ContextHolder.getContext()
                                .openFileOutput("VirtualKeyboardLayout", Context.MODE_PRIVATE));
                        vk.writeLayout(dos);
                        dos.close();
                    } catch (IOException ioe) {
                        ioe.printStackTrace();
                    }
                }
            };

            vk.setLayoutListener(listener);

            Display.getDisplay((MIDlet) getApplication()).setOverlay(vk);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public void commandAction(Command c, Displayable dp) {
        MIDlet midlet = (MIDlet) getApplication();

        switch (c.getCommandType()) {
            case Command.OK:
                try {
                    applyConfiguration();
                    midlet.startApp();
                    finish();
                } catch (Throwable t) {
                    t.printStackTrace();
                }

                break;

            case Command.ITEM:
                handler.post(resetMessage);
                break;

            case Command.EXIT:
                midlet.notifyDestroyed();
        }
    }

    public void onNothingSelected(AdapterView<?> parent) {
    }

    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (v == tfVKLayoutKeyCode && (event.getFlags() & KeyEvent.FLAG_SOFT_KEYBOARD) == 0) {
            tfVKLayoutKeyCode.setText(Integer.toString(Canvas.convertAndroidKeyCode(keyCode)));
            return true;
        }

        return false;
    }

    public void setLanguage(int which) {
        locale = getResources().getStringArray(R.array.locales)[which];
        restart();
    }

    public void onClick(View v) {
        String[] presets = null;
        DialogInterface.OnClickListener presetListener = null;

        int color = 0;
        AmbilWarnaDialog.OnAmbilWarnaListener colorListener = null;

        int id = v.getId();

        if (id == R.id.cmdScreenSizePresets) {
            presets = screenAdapter.toArray(new String[0]);

            presetListener = new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    tfScreenWidth.setText(Integer.toString(screenWidths.get(which)));
                    tfScreenHeight.setText(Integer.toString(screenHeights.get(which)));
                }
            };
        } else if (id == R.id.cmdFontSizePresets) {
            presets = fontAdapter.toArray(new String[0]);

            presetListener = new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    tfFontSizeSmall.setText(Integer.toString(fontSmall.get(which)));
                    tfFontSizeMedium.setText(Integer.toString(fontMedium.get(which)));
                    tfFontSizeLarge.setText(Integer.toString(fontLarge.get(which)));
                }
            };
        } else if (id == R.id.cmdLanguage) {
            presets = getResources().getStringArray(R.array.languages);

            presetListener = new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    setLanguage(which);
                }
            };
        } else if (id == R.id.cmdScreenBack) {
            color = Integer.parseInt(tfScreenBack.getText().toString(), 16);

            colorListener = new AmbilWarnaDialog.OnAmbilWarnaListener() {
                public void onOk(AmbilWarnaDialog dialog, int color) {
                    tfScreenBack.setText(Integer.toHexString(color & 0xFFFFFF).toUpperCase());
                }

                public void onCancel(AmbilWarnaDialog dialog) {
                }
            };
        } else if (id == R.id.cmdVKBack) {
            color = Integer.parseInt(tfVKBack.getText().toString(), 16);

            colorListener = new AmbilWarnaDialog.OnAmbilWarnaListener() {
                public void onOk(AmbilWarnaDialog dialog, int color) {
                    tfVKBack.setText(Integer.toHexString(color & 0xFFFFFF).toUpperCase());
                }

                public void onCancel(AmbilWarnaDialog dialog) {
                }
            };
        } else if (id == R.id.cmdVKFore) {
            color = Integer.parseInt(tfVKFore.getText().toString(), 16);

            colorListener = new AmbilWarnaDialog.OnAmbilWarnaListener() {
                public void onOk(AmbilWarnaDialog dialog, int color) {
                    tfVKFore.setText(Integer.toHexString(color & 0xFFFFFF).toUpperCase());
                }

                public void onCancel(AmbilWarnaDialog dialog) {
                }
            };
        } else if (id == R.id.cmdVKSelFore) {
            color = Integer.parseInt(tfVKSelFore.getText().toString(), 16);

            colorListener = new AmbilWarnaDialog.OnAmbilWarnaListener() {
                public void onOk(AmbilWarnaDialog dialog, int color) {
                    tfVKSelFore.setText(Integer.toHexString(color & 0xFFFFFF).toUpperCase());
                }

                public void onCancel(AmbilWarnaDialog dialog) {
                }
            };
        } else if (id == R.id.cmdVKSelBack) {
            color = Integer.parseInt(tfVKSelBack.getText().toString(), 16);

            colorListener = new AmbilWarnaDialog.OnAmbilWarnaListener() {
                public void onOk(AmbilWarnaDialog dialog, int color) {
                    tfVKSelBack.setText(Integer.toHexString(color & 0xFFFFFF).toUpperCase());
                }

                public void onCancel(AmbilWarnaDialog dialog) {
                }
            };
        } else if (id == R.id.cmdVKOutline) {
            color = Integer.parseInt(tfVKOutline.getText().toString(), 16);

            colorListener = new AmbilWarnaDialog.OnAmbilWarnaListener() {
                public void onOk(AmbilWarnaDialog dialog, int color) {
                    tfVKOutline.setText(Integer.toHexString(color & 0xFFFFFF).toUpperCase());
                }

                public void onCancel(AmbilWarnaDialog dialog) {
                }
            };
        } else {
            return;
        }

        if (presetListener != null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(getString(R.string.SIZE_PRESETS));
            builder.setItems(presets, presetListener);

            AlertDialog alert = builder.create();
            alert.show();
        } else if (colorListener != null) {
            AmbilWarnaDialog dialog = new AmbilWarnaDialog(this, color | 0xFF000000, colorListener);
            dialog.show();
        }
    }
}
