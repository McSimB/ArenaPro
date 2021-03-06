package com.elkware.midp.games;

import com.elkware.midp.games.colorng.arena.high.MyCanvas;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.midlet.MIDlet;

public abstract class Arena1 extends MIDlet {

    public MyCanvas myCanvas;
    public Display display;
    public String appID = null;
    private String var_198 = null;

    public void startApp() {
        display = Display.getDisplay();
    }

    public byte[] sub_fb(String var1) {
        char[] var2 = var1.toCharArray();
        byte[] var3 = new byte[var2.length * 2];

        for (int var4 = 0; var4 < var2.length; ++var4) {
            var3[var4 * 2] = (byte) (var2[var4] >> 8 & 255);
            var3[var4 * 2 + 1] = (byte) (var2[var4] & 255);
        }
        return var3;
    }

    public String sub_137(byte[] var1) {
        StringBuilder var2 = new StringBuilder();

        for (byte aVar1 : var1) {
            var2.append(Integer.toHexString((aVar1 & 240) >> 4));
            var2.append(Integer.toHexString(aVar1 & 15));
        }

        return var2.toString();
    }

    private String sub_149(String var1) throws IOException {
        System.out.println(var1);
        InputStream var2;
        HttpConnection var3 = (HttpConnection) Connector.open(var1);
        var2 = var3.openInputStream();
        var3.getType();
        int var6 = var3.getLength();
        String var5;
        int var8;
        if (var6 > 0) {
            byte[] var7 = new byte[var6];
            var8 = var2.read(var7);
            var5 = new String(var7, 0, var8);
        } else {
            ByteArrayOutputStream var9 = new ByteArrayOutputStream();

            while ((var8 = var2.read()) != -1) {
                var9.write(var8);
            }

            var5 = new String(var9.toByteArray());
        }

        var2.close();
        var3.close();
        return var5;
    }

    private int sub_163(byte[] var1, int var2) {
        return (var1[var2] & 255) << 24 | (var1[var2 + 1] & 255) << 16
                | (var1[var2 + 2] & 255) << 8 | var1[var2 + 3] & 255;
    }

    private String sub_19f(byte[] var1, int var2, int var3) {
        String var4 = "";

        for (int var5 = 0; var5 < var3; ++var5) {
            if (var1[var5 * 2 + var2 + 1] != 0) {
                var4 = var4 + (char) (((var1[var5 * 2 + var2] & 255) << 8) + (var1[var5
                        * 2 + var2 + 1] & 255));
            }
        }

        return var4;
    }

    private byte[] sub_1fa(String var1) {
        byte[] var2 = var1.getBytes();
        byte[] var3 = new byte[var2.length / 2];

        for (int var7 = 0; var7 < var3.length; ++var7) {
            int var6 = var7 * 2;
            int var4 = var2[var6] - 48;
            if (var4 > 9) {
                var4 = var2[var6] - 55;
            }

            int var5 = var2[var6 + 1] - 48;
            if (var5 > 9) {
                var5 = var2[var6 + 1] - 55;
            }

            var3[var7] = (byte) (var4 << 4 | var5);
        }

        return var3;
    }

    public int sub_239(int var1) {
        this.sub_29e(var1);
        return 3;
    }

    public abstract void sub_28a(int var1);

    public boolean sub_29e(int var1) {
        if (this.sub_53f(var1)) {
            this.sub_28a(var1);
        }

        if (!this.sub_4af(null)) {
            return false;
        } else {
            this.sub_309();
            this.sub_486(var1, this.sub_3e1());
            boolean var2;
            if (this.sub_5af() == 1) {
                var2 = this.sub_2b9(var1);
                String var3 = this.sub_309();
                if (var3 == null) {
                    if (var2) {
                        this.sub_5de(true);
                        this.sub_36e();
                    } else {
                        var3 = this.getStr(124);
                    }
                }

                if (var3 != null) {
                    this.makeAlert(var3);
                }
            } else {
                var2 = true;
            }

            this.saveHighscore();
            return var2;
        }
    }

    public final boolean sub_2b9(int var1) {
        try {
            boolean var2;
            String var3 = System.getProperty("com.siemens.IMEI");
            if (var3 == null) {
                return false;
            } else {
                Form var4 = new Form("Info");
                var4.append(this.getStr(127));

                do {
                    Display.getDisplay().setCurrent(var4);

                    try {
                        Thread.currentThread();
                        Thread.sleep(50L);
                    } catch (Exception var10) {
                        var10.printStackTrace();
                    }

                    StringBuffer var5 = new StringBuffer(
                            "http://139.23.38.140/hs/");
                    var5.append("user.asp?imei=");
                    var5.append(var3.substring(1));
                    var5.append("&nick=");
                    var5.append(this.sub_137(this.sub_fb(this.sub_3e1()))
                            .toUpperCase());
                    var5.append("&appid=");
                    var5.append(this.appID);
                    var5.append("&mnc=");
                    var5.append(System.getProperty("com.siemens.MNC"));
                    var5.append("&mcc=");
                    var5.append(System.getProperty("com.siemens.MCC"));
                    String var6 = this.sub_149(var5.toString());
                    if (!var6.startsWith("exists")
                            && !var6.startsWith("registration successful")) {
                        String var12;
                        if (var6.startsWith("alternative nick")) {
                            var12 = var6.substring(17);
                            byte[] var13 = this.sub_1fa(var12);
                            var12 = this.getStr(128);
                            this.sub_3f3(this.sub_19f(var13, 0,
                                    var13.length / 2));
                        } else {
                            var12 = var6;
                        }

                        var2 = !this.sub_4af(new Alert("Nickname", var12, null, null));
                    } else {
                        int var_ad = -1577856450;
                        var1 ^= var_ad;
                        var5 = new StringBuffer("http://139.23.38.140/hs/");
                        var5.append("hs.asp?imei=");
                        var5.append(var3.substring(1));
                        var5.append("&nick=");
                        var5.append(this.sub_137(this.sub_fb(this.sub_3e1())));
                        var5.append("&appid=");
                        var5.append(this.appID);
                        var5.append("&hs=");
                        var5.append(this
                                .sub_137(new byte[]{
                                        (byte) (var1 >> 24 & 255),
                                        (byte) (var1 >> 16 & 255),
                                        (byte) (var1 >> 8 & 255),
                                        (byte) (var1 & 255)}));
                        var6 = this.sub_149(var5.toString());
                        byte[] var7 = this.sub_1fa(var6);
                        if (var6.length() <= 0 || var7[0] != 0) {
                            return false;
                        }

                        this.sub_45b();
                        this.sub_448(this.sub_163(var7, 1));

                        for (int var8 = 0; var8 < 3; ++var8) {
                            this.sub_486(this.sub_163(var7, 29 + 28 * var8),
                                    this.sub_19f(var7, 5 + 28 * var8, 12));
                        }

                        var2 = true;
                    }
                } while (!var2);

                return true;
            }
        } catch (Exception var11) {
            var11.printStackTrace();
            Display.getDisplay().setCurrent(new Alert("Info", "" + var11, null, null));

            try {
                Thread.currentThread();
                Thread.sleep(5000L);
            } catch (Exception var9) {
                var9.printStackTrace();
            }

            Display.getDisplay().setCurrent(null);
            return false;
        }
    }

    public String sub_309() {
        String var1 = this.var_198;
        this.var_198 = null;
        return var1;
    }

    public void commandAction(Command var1, Displayable var2) {
    }

    public void sub_36e() {
    }

    public abstract String getStr(int var1);

    public abstract String sub_3e1();

    public abstract void sub_3f3(String var1);

    public abstract void sub_448(int var1);

    public abstract void sub_45b();

    public abstract boolean sub_486(int var1, String var2);

    public abstract boolean sub_4af(Alert var1);

    abstract void makeAlert(String var1);

    public abstract boolean sub_53f(int var1);

    public abstract void saveHighscore();

    abstract int sub_5af();

    abstract void sub_5de(boolean var1);

    @Override
    public String getAppProperty(String s) {
        return super.getAppProperty(s);
    }
}
