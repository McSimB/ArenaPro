package com.elkware.midp.games.colorng.arena.high;

import com.elkware.midp.games.Arena2;
import com.elkware.midp.games.colorng.Arena3;
import com.elkware.midp.games.colorng.Canvas3;
import com.elkware.midp.games.colorng.MySprite;
import com.elkware.midp.games.colorng.MyTiledLayer;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Vector;

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.rms.RecordComparator;
import javax.microedition.rms.RecordEnumeration;
import javax.microedition.rms.RecordFilter;
import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;

public class MyCanvas extends Canvas3 implements Runnable {

    private int var_eb;
    private final String[] recStorages = new String[]{"RSPL", "RSTU", "RSOPT",
            "RSWAR", "RSPHO", "RSSMS", "RSMAX", "RSHI"};
    private static final String[] typesBattles = new String[]{"bt", "bt", "bt",
            "kh", "cc", "bt"};
    public static final int[] var_154 = new int[]{7, 7, 7, 7, 7, 5};
    public static final int[] var_1a6 = new int[]{301, 301, 301, 330, 360, 301};
    public int[] var_234 = new int[9];
    public int[] var_295 = new int[4];
    public Arena arena;
    public String playerName = "Player";
    private int var_30a = 0;
    private int var_33e = 0;
    public boolean[] settings = new boolean[5];
    private byte var_3e7 = 1;
    private byte var_3fd = 1;
    private byte var_40e = 0;
    private byte arrowParam = 0;
    public byte var_458 = 0;
    private Font[] fonts = new Font[7];
    public boolean var_4c6 = false;
    public boolean var_4ee = false;
    public boolean var_535 = false;
    private int var_567 = 0;
    private String var_5a4;
    private String var_5b6;
    private String var_5e3 = null;
    public String var_5fb = null;
    private String[] var_62c = null;
    private Image statIcons;
    private int var_692 = 0;
    private boolean var_6e8 = false;
    public Image gameOverBg;
    public int var_733 = 0;
    private int var_844 = 0;
    public int var_891 = -1;
    public int var_8f4;
    public boolean var_91b;
    public boolean var_92a;
    public long var_9b6 = 120000L;
    public long var_9dd;
    public boolean var_a17 = false;
    public boolean var_a67 = false;
    public boolean var_a72 = false;
    private int var_a89 = 0;
    public String[] var_b2f;
    public int[] var_b57;
    public int var_ba9 = 0;
    private Image logoImage;
    public Image bgImage;
    public Image championsBg = null;
    public Image warriorImage = null;
    private Image tilesImage;
    private Image _image;
    private Image optionBg;
    private Image var_d92 = null;
    private Image battleImage;
    private Image overImage;
    private int var_e7f = 0;
    private int[] var_ec4;
    private int[] var_ef3;
    public Image[] headsImage;
    public Image[] var_f8e;
    public int var_fa2;
    public int var_fb7;
    public int var_1011;
    public int var_1074;
    public int[] var_10c6;
    private Image panelImage;
    private Image cardioImage;
    private Image greenHPImage;
    private Image whiteHPImage;
    private int var_11c8;
    private int var_11d4;
    private int var_11fa;
    private Image optionBg2 = null;
    private int leftRight;
    private int upDown;
    private int _fireUse;
    private long var_13a7;
    public int var_140d = -1;
    public int var_1462 = -1;
    public int var_146d = 0;
    public int var_14a4 = 0;
    public int var_1504 = 0;
    private long var_151f = 0L;
    public int var_157f = -1;
    public MyTiledLayer var_1c70;
    private MyTiledLayer var_1c8d;
    public byte[] var_1c98;
    public byte[] var_1cdd;
    public int var_1d1c;
    public int var_1d37;
    public int width;
    private int height;
    public Vector var_1db7;
    public MySprite[] fallingsAnims;
    public Tiled[] var_1e04;
    public Hashtable var_1e1d = new Hashtable();
    public Warrior[] var_1efb;
    public Warrior var_1f25_player;
    private byte[][] var_1f6c;
    public int[] var_1f77;
    public int var_1fa2 = 0;
    private Image[] var_1ff8;
    private boolean var_2039 = false;
    private Image arrowImage;
    public String[] var_20cb;
    public int[] var_20f8;
    public int[] var_210c;
    public byte[][] var_212d;
    public byte[][] var_217e;
    public int[] var_21de;
    public int[] var_220e;
    public int[] var_2230;
    public int var_2272 = 0;
    public boolean[][] var_22a0;
    public MySprite[] effectsAnims;
    private Effects[] var_2329;
    private Effects[] var_238b;
    private int var_23a7 = 0;
    private int var_23cd = 0;
    public int[] var_23fa;
    public int[] var_2415;
    public Bullets[] var_2445;
    public Bullets[] var_2455;
    public int var_24b6 = 0;
    public int var_2510 = 0;
    public MySprite[] bulletsAnims;
    private int var_257a = 0;
    private int[] var_25c8;
    private int[] var_25f2;
    private int[] var_2650;
    public boolean isSoundPlay = false;
    public boolean isMusicPlay = false;
    private boolean var_2717 = false;
    private Image[] photoImages;
    private int var_28fd;
    private int var_2923;
    private int var_296c;
    public boolean var_2976 = false;
    public boolean var_29c3 = false;
    private long[] var_2a04 = new long[20];
    private boolean var_2a29;
    private long var_2a41 = 0L;
    public byte[] var_2a7b = null;
    private String loadingStr;
    private String var_2ae9;
    private String roundStr = null;
    private String finalMatchStr;
    private String workingStr = null;
    private boolean var_2b6c = false;
    private boolean var_2bba = false;
    private int var_2c1c = 0;
    private int var_2c78 = 0;
    private int var_2cdb = 0;
    private int var_2ced = 0;
    private final Font font = Font.getFont(64, 0, 0);
    private String loadingStr2;
    public boolean var_2de8 = false;
    private int var_2e22 = 0;
    public Thread mThread = null;
    public Thread var_2eb2 = null;
    public Thread var_2f12 = null;
    private Thread var_2f3f = null;
    boolean var_2f99 = false;
    final Object locker = 0;
    public boolean var_2ffa = false;
    private boolean var_3051 = false;
    public boolean _chall = false;
    public boolean var_3103 = false;
    private boolean var_3124 = false;
    private Image resultImage = null;
    private Music music;
    private boolean var_31be = false;
    private boolean var_31f0 = false;
    public boolean var_3246 = false;
    private int[] var_326a;
    private int var_32c0 = 0;
    public boolean var_3309 = false;
    private boolean var_3366 = false;
    public boolean var_3386 = false;
    private int var_339d = 0;
    private static int var_3417 = 8;
    public boolean var_3463 = false;
    private long var_3486 = 0L;
    private boolean var_349e = false;
    private int percent = 0;
    private boolean var_3559 = false;
    private boolean var_3594 = false;
    public boolean var_35b0 = false;
    public boolean var_35e9 = false;
    private boolean var_3601 = false;
    private int var_3612 = 0;
    private int[] var_3657;
    private int[] var_3670;
    private String[] var_36a6;
    public int var_3706 = 0;
    private String gameOverStr = null;
    private int[] var_373b;
    private int var_375d;
    public boolean var_37be = false;
    private int var_3817 = 0;
    private static int value45 = 45;
    private long var_3882 = 0L;
    private boolean var_38d3 = true;

    public MyCanvas(Arena3 var1) {
        super(var1);
        arena = (Arena) var1;
        this.var_fa2 = this.sub_160();
        Warrior.sub_1d(this, this.var_fa2);
        this.var_eb = 20;
    }

    public void sub_54() {
        this.var_844 = 0;
    }

    public boolean sub_a8() {
        return 0 == this.var_844;
    }

    public void sub_c2() {
        if (this.var_91b) {
            this.sub_bd4();
        } else if (this.var_92a && this.var_844 == 0) {
            this.sub_c11();
        } else if (this.var_891 == 0) {
            this.var_891 = Warrior.random(3) + 1;
            this.var_8f4 = Warrior.random(var_154[this.var_891]) + 1;
        }

        this.var_3601 = false;
        this.var_844 = 1;
        this.startMThread();
    }

    @Override
    public int getHeight() {
        return super.getHeight() - 20;
    }

    public void sub_110() {
        if (this.var_62c == null) {
            this.var_62c = new String[4];
            System.gc();

            for (int var1 = 0; var1 < 4; ++var1) {
                this.var_62c[var1] = arena.getStr(262 + var1);
            }

            this.statIcons = this.openImage(173);
        }

        this.var_844 = 2;
        this.startMThread();
    }

    private void beginLoadGame() {
        this.repaint();
        this.serviceRepaints();

        try {
            Thread.sleep(500L);
        } catch (Exception var2) {
            var2.printStackTrace();
        }

        this.loadGame();
    }

    private void sub_180() {
        if (this.var_91b) {
            if ((this.upDown == 4 || this.var_31f0) && this.var_6e8) {
                if (this.var_32c0 < 1) {
                    this.var_32c0 = 3;
                    this.var_692 = Math.min(this.var_692 + 1,
                            this.var_3657.length - 4);
                    this.var_6e8 = false;
                    this.repaint();
                    this.serviceRepaints();
                }

                this.var_31f0 = false;
            } else if ((this.upDown == 3 || this.var_31be) && this.var_6e8) {
                if (this.var_32c0 < 1) {
                    this.var_32c0 = 3;
                    this.var_692 = Math.max(this.var_692 - 1, 0);
                    this.var_6e8 = false;
                    this.repaint();
                    this.serviceRepaints();
                }

                this.var_31be = false;
            }
        } else if (this.var_92a && this.var_2a29) {
            this.var_2a29 = false;
            this.sub_c2e();
        }

    }

    private void sub_1e2() {
        int var6;
        if (this.var_844 == 1) {
            if (this.var_2976) {
                return;
            }

            this.var_2976 = true;
            System.gc();
            this.var_b2f[0] = this.playerName;
            this.var_b57 = new int[4];
            this.var_b57[0] = 0;
            if (!this.var_91b) {
                boolean[] var1 = new boolean[this.var_eb + 1];
                var1[0] = true;

                for (int var2 = 1; var2 < 4; ++var2) {
                    int var8;
                    do {
                        var8 = Warrior.random(this.var_eb) + 1;
                    } while (var1[var8]);

                    var1[var8] = true;
                    this.var_b57[var2] = var8;
                }
            } else {
                this.sub_c93();

                for (var6 = 1; var6 < 4; ++var6) {
                    try {
                        this.var_b57[var6] = this.var_373b[var6];
                    } catch (Exception var5) {
                        var5.printStackTrace();
                    }
                }
            }

            this.repaint();
            this.serviceRepaints();

            try {
                Thread.sleep(50L);
            } catch (Exception var4) {
                var4.printStackTrace();
            }

            this.gameLoading(this.var_8f4);
            this.var_29c3 = true;
        } else if (this.var_844 == 2) {
            if (this.optionBg2 == null) {
                this.optionBg2 = this.optionBg;
                this.var_2ae9 = arena.getStr(221);
                this.var_5a4 = arena.getStr(223);
                this.var_5b6 = arena.getStr(224);
                this.var_5e3 = arena.getStr(225);
            }

            if (this.var_4ee) {
                if (this.upDown != 3 && !this.var_31be) {
                    if (this.upDown != 4 && !this.var_31f0) {
                        if (this.var_567 < 2) {
                            if (this.var_567 == 0) {
                                var6 = this.headsImage.length;
                            } else {
                                var6 = this.var_f8e.length;
                            }

                            if (this.var_567 == 0) {
                                if (this.leftRight == 1) {
                                    this.var_234[this.var_567] = Math.max(0,
                                            this.var_234[this.var_567] - 1);
                                } else if (this.leftRight == 2) {
                                    this.var_234[this.var_567] = Math.min(
                                            var6 - 1,
                                            this.var_234[this.var_567] + 1);
                                }
                            } else if (this.leftRight == 1) {
                                this.var_234[this.var_567] = Math.max(0,
                                        this.var_234[this.var_567] - 1);
                            } else if (this.leftRight == 2) {
                                this.var_234[this.var_567] = Math.min(var6 - 1,
                                        this.var_234[this.var_567] + 1);
                            }
                        } else if (this.leftRight == 1
                                && (this.var_4c6 || this.var_234[this.var_567] > this.var_295[this.var_567 - 2])) {
                            this.var_234[this.var_567] = Math.max(0,
                                    this.var_234[this.var_567] - 1);
                        } else if (this.leftRight == 2 && this.var_234[8] > 0) {
                            this.var_234[this.var_567] = Math.min(20,
                                    this.var_234[this.var_567] + 1);
                        }
                    } else {
                        this.var_567 = this.var_567 == 5 ? 0 : this.var_567 + 1;
                    }
                } else {
                    this.var_567 = this.var_567 == 0 ? 5 : this.var_567 - 1;
                }

                this.sub_aad();
            }
        }

    }

    private void update(long var1) {
        this.var_ba9 = (this.var_ba9 + 1) % 100;

        int var3;
        for (var3 = 0; var3 < this.var_1e04.length; ++var3) {
            if (this.var_1e04[var3].var_15e) {
                this.var_1e04[var3].sub_3f();
            }
        }

        for (var3 = 0; var3 < this.var_1db7.size(); ++var3) {
            Fallings var4 = (Fallings) this.var_1db7.elementAt(var3);
            if (var4.sub_20_update_fallings()) {
                this.var_1db7.removeElementAt(var3);
                this.sub_1b1(this.var_fb7, var4.sprite);
            }
        }

        try {
            for (int var15 = 0; var15 < this.var_23a7; ++var15) {
                Effects var16 = this.var_2329[var15];
                if (var16.sub_c5()) {
                    this.sub_1b1(this.var_1011, var16.sprite);
                    this.var_238b[this.var_23cd++] = var16;
                    this.var_2329[var15] = this.var_2329[this.var_23a7 - 1];
                    this.var_2329[this.var_23a7 - 1] = null;
                    --this.var_23a7;
                }
            }
        } catch (Exception var14) {
            var14.printStackTrace();
        }

        this.var_1fa2 = this.var_1fa2 % (this.var_1efb.length - 1) + 1;

        int i;
        Warrior warrior;
        for (var3 = 0; var3 < this.var_1efb.length; ++var3) {
            try {
                warrior = this.var_1efb[var3];
                if (warrior.var_bd6_is_enemy) {
                    warrior.sub_272_update();
                } else {
                    if (this.leftRight == 1) {
                        warrior.sub_d8_left();
                    }

                    if (this.leftRight == 2) {
                        warrior.sub_11d_right();
                    }

                    if (this.upDown == 3) {
                        warrior.sub_143_up();
                    }

                    warrior.var_7c1 = this.upDown == 4;
                    if (this._fireUse == 6) {
                        warrior.var_c05_use = true;
                    } else if (this._fireUse == 5 && warrior.var_aaf) {
                        this.var_1f25_player.sub_2cf_fire();
                    }
                }

                warrior.sub_8f();
                this.var_2a04[6 + var3 * 3] = System.currentTimeMillis() - var1;
                if (!(warrior.var_716 | warrior.var_74b)) {
                    warrior.var_4dc = warrior.var_60f;
                }

                if (warrior.var_579 >= 0) {
                    try {
                        this.var_2923 = Math.min(warrior.var_4dc + warrior.var_ff9
                                + 1, this.var_1d37 * 15 - 5);
                        this.var_28fd = warrior.var_8ee / 15 + this.var_2923 / 15
                                * this.var_1d1c;
                        warrior.var_6b1 = this.var_1c98[this.var_28fd] >= 1
                                && this.var_1c98[this.var_28fd] <= 32
                                && this.var_1c98[this.var_28fd] != 8
                                && this.var_1c98[this.var_28fd] != 7;
                    } catch (Exception var12) {
                        this.var_2923 -= 15;
                        warrior.var_4dc -= 15;
                        this.var_28fd = warrior.var_8ee / 15 + this.var_2923 / 15
                                * this.var_1d1c;
                        if (warrior.var_aaf) {
                            warrior.sub_1da_hit(200);
                        }
                    }

                    if (warrior.var_6b1) {
                        warrior.var_4dc -= this.var_2923 % 15 - 1;
                        warrior.var_579 = 0;
                    }
                } else if (warrior.var_579 < 0) {
                    this.var_2923 = warrior.var_4dc - 1;
                    this.var_28fd = warrior.var_8ee / 15 + this.var_2923 / 15
                            * this.var_1d1c;
                    if (this.var_1c98[this.var_28fd] >= 4
                            && this.var_1c98[this.var_28fd] <= 32
                            && this.var_1c98[this.var_28fd] != 8
                            && this.var_1c98[this.var_28fd] != 7) {
                        warrior.var_579 = 0;
                        warrior.var_4dc += 15 - warrior.var_4dc % 15;
                    }
                }

                if (warrior.var_53c + warrior.var_5ef != 0) {
                    this.var_28fd = (warrior.var_53c + warrior.var_5ef < 0 ? warrior.var_48e
                            : warrior.var_48e + warrior.var_feb)
                            / 15
                            + (warrior.var_4dc + warrior.var_ff9 - 1)
                            / 15
                            * this.var_1d1c;
                    this.var_296c = warrior.var_ff9 / 15 + 1;

                    for (i = 0; i < this.var_296c; ++i) {
                        if (this.var_1c98[this.var_28fd] >= 4
                                && this.var_1c98[this.var_28fd] <= 32
                                && this.var_1c98[this.var_28fd] != 8
                                && this.var_1c98[this.var_28fd] != 7) {
                            warrior.var_48e += warrior.var_53c + warrior.var_5ef < 0 ? 15 - warrior.var_48e % 15
                                    : -(15 - warrior.var_48e % 15);
                            warrior.var_53c /= -2;
                            warrior.var_5ef /= -2;
                            break;
                        }

                        this.var_28fd -= this.var_1d1c;
                    }

                    this.var_2a04[7 + var3 * 3] = System.currentTimeMillis()
                            - var1;
                    this.var_28fd = (warrior.var_53c + warrior.var_5ef < 0 ? warrior.var_48e
                            : warrior.var_48e + warrior.var_feb)
                            / 15
                            + (warrior.var_4dc + warrior.var_ff9 - 1)
                            / 15
                            * this.var_1d1c;
                    if (this.var_1c98[this.var_28fd] >= 4
                            && this.var_1c98[this.var_28fd] <= 32
                            && this.var_1c98[this.var_28fd] != 8
                            && this.var_1c98[this.var_28fd] != 7) {
                        warrior.var_48e = warrior.var_9de;
                    }

                    warrior.var_9de = warrior.var_48e;
                    warrior.var_a0d = warrior.var_4dc;
                }

                this.var_28fd = warrior.var_6f5;
                if (this.var_1c98[this.var_28fd] > 0) {
                    if (this.var_1c98[this.var_28fd] == 71) {
                        if (warrior.var_c39) {
                            Tiled var19 = (Tiled) this.var_1e1d.get(this.var_28fd);
                            var19.sub_6c(var3);
                            this.var_1c98[this.var_28fd] = 72;
                            this.var_1c70.setCell(
                                    this.var_28fd % this.var_1d1c,
                                    this.var_28fd / this.var_1d1c, 72);
                        }
                    } else if (this.var_1c98[this.var_28fd] >= 57
                            && this.var_1c98[this.var_28fd] <= 64
                            && !warrior.var_899) {
                        warrior.sub_1da_hit(500);
                    } else if (this.var_1c98[this.var_28fd] >= 65
                            && this.var_1c98[this.var_28fd] <= 79
                            && this.var_1c98[this.var_28fd] != 72
                            && this.var_1c98[this.var_28fd] != 71
                            && (this.var_1c98[this.var_28fd] < 75 || this.var_1c98[this.var_28fd] > 78)) {
                        switch (this.var_1c98[this.var_28fd]) {
                            case 65:
                                warrior.var_b81_hp = Math.min(warrior.var_b81_hp + 40,
                                        warrior.var_af7);
                                this.var_3386 = true;
                                break;
                            case 66:
                                warrior.sub_1a1();
                                this.sub_a24(3, warrior.var_48e - 2, warrior.var_4dc
                                        + warrior.var_ff9 - this.var_2415[3]);
                                break;
                            case 73:
                                warrior.sub_1da_hit(500);
                                break;
                            case 79:
                                this.var_157f = var3;
                                this.var_1c98[this.var_28fd] = 0;
                        }

                        if (this.var_1c98[this.var_28fd] >= 67
                                && this.var_1c98[this.var_28fd] <= 70
                                || this.var_1c98[this.var_28fd] == 74) {
                            warrior.var_f3a = this.var_1c98[this.var_28fd] == 74 ? 5
                                    : this.var_1c98[this.var_28fd] - 67 + 1;
                            warrior.var_f87 = Warrior.var_248[warrior.var_f3a];
                        }

                        this.var_25c8[this.var_257a] = 100;
                        this.var_2650[this.var_257a] = this.var_28fd;
                        this.var_25f2[this.var_257a++] = this.var_1c98[this.var_28fd];
                        this.var_1c98[this.var_28fd] = 0;
                        this.var_1c70.setCell(this.var_28fd % this.var_1d1c,
                                this.var_28fd / this.var_1d1c, 0);
                    }
                }

                warrior.var_98b = this.var_28fd;
                if (warrior.var_aaf) {
                    try {
                        for (i = 0; i < this.var_1db7.size(); ++i) {
                            Fallings var6 = (Fallings) this.var_1db7
                                    .elementAt(i);
                            if (var6.sprite.sub_1cc(warrior.var_e6d, true)) {
                                var6.sub_36(warrior);
                            }
                        }
                    } catch (Exception var11) {
                        ;
                    }

                    for (i = 0; i < this.var_24b6; ++i) {
                        if (this.var_2445[i].warrior != warrior
                                && this.sub_5f7(this.var_2445[i].sprite,
                                warrior.var_e6d)) {
                            this.var_2445[i].sub_bb_hit(warrior);
                        }
                    }

                    if (warrior.var_822) {
                        warrior.var_e6d.defineCollisionRectangle(0, 2,
                                warrior.var_feb, warrior.var_ff9 - 10);
                        warrior.var_e6d.setPosition(warrior.var_48e,
                                warrior.var_4dc + 8);
                        warrior.var_ea2.setPosition(warrior.var_48e, warrior.var_4dc
                                + warrior.var_e6d.sub_96());
                    } else {
                        warrior.var_e6d.defineCollisionRectangle(0, 0,
                                warrior.var_feb, warrior.var_ff9);
                        warrior.var_e6d.setPosition(warrior.var_48e, warrior.var_4dc);
                        warrior.var_ea2.setPosition(warrior.var_48e, warrior.var_4dc
                                + warrior.var_e6d.sub_96());
                    }
                }
            } catch (Exception var13) {
                ;
            }
        }

        i = 0;
        int var7;
        int var8;
        for (var7 = this.var_1efb.length; var7 > 0; --var7) {
            Warrior var18 = this.var_1efb[i++];
            int var21 = i;
            if (var18.var_aaf) {
                for (var8 = this.var_1efb.length - i; var8 > 0; --var8) {
                    warrior = this.var_1efb[var21++];
                    if (warrior.var_aaf
                            && this.sub_5f7(var18.var_e6d, warrior.var_e6d)) {
                        var18.var_5ef = var18.var_48e < warrior.var_48e ? -8 : 8;
                        warrior.var_5ef = warrior.var_48e < var18.var_48e ? -8 : 8;
                        warrior.var_53c = warrior.var_5ef / 4;
                        var18.var_53c = var18.var_5ef / 4;
                        if (var18.var_a35 == -1) {
                            var18.var_a35 = warrior.var_b9b;
                            var18.var_a79 = 20;
                        }

                        if (warrior.var_a35 == -1) {
                            warrior.var_a35 = var18.var_b9b;
                            warrior.var_a79 = 20;
                        }

                        if ((!warrior.var_bd6_is_enemy || !var18.var_bd6_is_enemy)
                                && this.settings[2]) {
                            this.makeVibrate(40);
                        }
                    }
                }
            }
        }

        for (var8 = 0; var8 < this.var_10c6.length; ++var8) {
            byte var22 = this.var_1c98[this.var_10c6[var8]];
            if (var22 < 57) {
                var7 = var22 + 1;
                var7 = var22 % 4 + 53;
            } else if (var22 <= 60) {
                var7 = var22 + 1;
                var7 = var22 % 4 + 57;
            } else {
                var7 = var22 + 1;
                var7 = var22 % 4 + 61;
            }

            this.var_1c98[this.var_10c6[var8]] = (byte) var7;
            this.var_1c70.setCell(this.var_10c6[var8] % this.var_1d1c,
                    this.var_10c6[var8] / this.var_1d1c, var7);
        }

        for (var8 = 0; var8 < this.var_257a; ++var8) {
            if (this.var_25c8[var8]-- < 1) {
                this.var_1c98[this.var_2650[var8]] = (byte) this.var_25f2[var8];
                this.var_1c70.setCell(this.var_2650[var8] % this.var_1d1c,
                        this.var_2650[var8] / this.var_1d1c,
                        this.var_25f2[var8]);
                this.var_25c8[var8] = this.var_25c8[this.var_257a - 1];
                this.var_25f2[var8] = this.var_25f2[this.var_257a - 1];
                this.var_2650[var8--] = this.var_2650[this.var_257a-- - 1];
            }
        }

        try {
            for (int var9 = 0; var9 < this.var_24b6; ++var9) {
                Bullets var23 = this.var_2445[var9];
                if (var23.sub_7a_update_bullets()) {
                    this.sub_a24(
                            Effects.var_9d[(int) (System.currentTimeMillis() % 2L)],
                            var23.var_172 - 10, var23.var_1bf - 10);
                    this.sub_1b1(this.var_1074, var23.sprite);
                    this.var_2455[this.var_2510++] = var23;
                    this.var_2445[var9] = this.var_2445[this.var_24b6 - 1];
                    this.var_2445[this.var_24b6 - 1] = null;
                    --this.var_24b6;
                }
            }
        } catch (Exception var10) {
            var10.printStackTrace();
        }
    }

    @Override
    public void mainLoop(long var1) {
        long var3 = System.currentTimeMillis();
        if (this.var_844 == 5) {
            this.beginLoadGame();
        } else if (this.var_3463) {
            return;
        }

        if (this.var_35b0) {
            this.sub_180();
        } else if (!this.var_29c3) {
            this.sub_1e2();
        } else {
            if (!this.var_a17) {
                try {
                    this.update(var3);
                } catch (Exception var9) {
                    var9.printStackTrace();
                }
            }

            long var5 = System.currentTimeMillis();
            int var8;
            if (!this.var_a17 && !this.var_3463 && this.var_891 > 2
                    && (var5 - 1000L) / 1000L % 5L == 0L
                    && var5 > this.var_151f + 2000L) {
                if (this.var_891 == 3) {
                    for (var8 = 0; var8 < this.var_1efb.length; ++var8) {
                        Warrior var7 = this.var_1efb[var8];
                        if (var7.var_4dc + var7.var_ff9 <= this.var_1504 + 15
                                && var7.var_4dc + var7.var_ff9 >= this.var_1504 - 45
                                && var7.var_48e + var7.var_feb / 2 >= this.var_146d
                                && var7.var_48e - var7.var_feb / 2 <= this.var_14a4
                                && var7.var_aaf) {
                            ++var7.var_a1b;
                        }
                    }
                } else if (this.var_157f > -1) {
                    ++this.var_1efb[this.var_157f].var_a1b;
                }

                this.var_151f = var5;
            }

            this.var_2a04[17] = System.currentTimeMillis() - var3;
            if (this.var_9b6 > -1L) {
                this.var_a17 = var5 - this.var_9dd > this.var_9b6;
                if (!this.var_a17 && !this.var_2717 && this.isSoundPlay
                        && var5 - this.var_9dd + 30000L > this.var_9b6) {
                    this.var_2717 = true;
                }
            } else if (this.var_140d > -1) {
                for (var8 = 0; var8 < this.var_1efb.length; ++var8) {
                    if (this.var_1efb[var8].var_a1b >= this.var_140d) {
                        this.var_a17 = true;
                    }
                }
            } else if (this.var_1462 == 0) {
                this.var_a17 = true;
            }

            this.var_a67 = this.var_a67 && !this.var_a17;
            if (this.var_a17) {
                ++this.var_a89;
            }

            this.var_2a04[18] = System.currentTimeMillis() - var3;
            this.sub_819();
        }
    }

    private void sub_260() {
        if (this.var_2a7b != null && this.var_2a7b[3] > 0) {
            int var1 = 0;
            this.var_20cb = new String[3];
            this.var_20f8 = new int[3];

            for (int var2 = 0; var2 < 3; ++var2) {
                byte var3 = this.var_2a7b[3 + var2];
                byte[] var4 = new byte[3];
                System.arraycopy(this.var_2a7b, (var2 + 2) * 3, var4, 0, 3);
                this.var_20f8[var2] = this.sub_1092(var4);
                String var5 = "";

                for (int var6 = 0; var6 < var3; ++var6) {
                    var5 = var5 + (char) this.var_2a7b[15 + var1 + var6];
                }

                var1 += var3;
                this.var_20cb[var2] = var5;
            }
        }

    }

    private void paintChallenge(Graphics g) {
        g.setClip(0, 0, this.width, this.height + 20);
        g.drawImage(this.optionBg, 0, 0, 0);
        g.setFont(this.fonts[1]);
        g.setColor(50, 50, 50);
        g.drawString(this.getStr(520), 12, 10, 0); // Challenge
        g.setColor(255, 255, 255);
        g.drawString(this.getStr(520), 11, 8, 0);
        g.drawImage(this.warriorImage, 0, this.height - this.warriorImage.getHeight() + 20, 0);
        g.setColor(0, 0, 0);
        g.fillRect(11, 24, this.width - 22, 19);
        g.setColor(100, 0, 0);
        g.fillRect(12, 25, this.width - 24, 17);
        String var2 = "";

        try {
            var2 = this.var_2a7b != null ? this.getStr(32) + " "
                    + this.sub_1092(this.var_2a7b) : this.getStr(287); // No personal highscore
        } catch (Exception var8) {
            var2 = this.getStr(287);
        }

        g.setColor(255, 255, 255);
        g.setFont(this.fonts[0]);
        this.paintString(g, var2, 14, 28, this.width - 28);
        g.setColor(200, 200, 200);
        g.fillRect(12, this.height + 20 - 66, this.width - 24, 52);
        g.setColor(0, 0, 80);
        g.fillRect(13, this.height + 20 - 65, this.width - 26, 50);
        g.setColor(255, 255, 255);
        g.setFont(this.fonts[6]);
        int var3 = this.fonts[6].stringWidth("5555");
        int var4 = this.fonts[6].stringWidth("2.");
        if (this.var_20cb != null) {
            for (int i = 0; i < 3; ++i) {
                int var6 = this.height + 20 - 63 + i * 15;
                g.setColor(255, 255, 255);
                g.setFont(this.fonts[6]);
                g.drawString(i + 1 + ". ", 17, var6, 0);
                String var7 = Integer.toString(this.var_20f8[i]);
                g.setColor(255, 255, 255);
                g.setFont(this.fonts[6]);
                g.drawString(var7,
                        this.width - 17 - this.fonts[6].stringWidth(var7), var6, 0);
                g.setColor(255, 255, 255);
                g.setFont(this.fonts[6]);
                this.paintString(g, this.var_20cb[i], 18 + var4, var6,
                        this.width - 35 - var3 - var4);
            }
        } else {
            g.setColor(255, 255, 255);
            g.setFont(this.fonts[2]);
            g.drawString(this.getStr(29), 14, this.height + 20 - 63, 0);  // No global ranking
        }

    }

    private void paintTournir(Graphics var1) {
        if (this.roundStr == null) {
            this.roundStr = arena.getStr(227); // Round
            this.finalMatchStr = arena.getStr(228);  // Final match
        }

        var1.setClip(0, 0, this.width, this.height + 20);
        var1.drawImage(this.optionBg, 0, 0, 0);
        var1.setColor(255, 255, 255);
        var1.fillRect(124, 55, 8, 92);
        var1.setColor(0, 0, 0);
        var1.fillRect(124, 55 + this.var_692 * 92 / (this.var_3657.length - 3),
                8, 92 / (this.var_3657.length - 3) + 1);
        var1.setFont(this.fonts[5]);
        var1.setColor(50, 50, 50);
        String var2 = this.var_3657.length > 4 ? this.roundStr + " "
                + this.var_3612 : this.finalMatchStr;
        int var3 = this.fonts[5].stringWidth(var2);
        this.paintString(var1, var2, (this.width - var3) / 2 + 1, 25,
                this.width);
        var1.setColor(250, 250, 250);
        this.paintString(var1, var2, (this.width - var3) / 2, 24, this.width);

        try {
            var1.setColor(200, 200, 200);
            var1.setFont(this.fonts[1]);

            for (int var4 = this.var_692; var4 < 4 + this.var_692; ++var4) {
                var1.setClip(4, 55 + (var4 - this.var_692) * 23, 18, 18);

                try {
                    Image var5;
                    if (this.var_3657[var4] != 0) {
                        var5 = this.headsImage[this.var_1f6c[this.var_3657[var4] - 1][0] - 1];
                    } else {
                        var5 = this.headsImage[this.var_234[0]];
                    }

                    var1.drawImage(var5, 5, 55 + (var4 - this.var_692) * 23, 0);
                } catch (Exception var6) {
                    var6.printStackTrace();
                }

                var1.setColor(200, 200, 200);
                this.paintString(var1, var4 + 1 + ". "
                                + this.var_36a6[this.var_3657[var4]], 22,
                        58 + (var4 - this.var_692) * 23, 87);
                var1.setColor(200, 200, 210);
                this.paintString(var1,
                        (this.var_3670[this.var_3657[var4]] < 10 ? "0" : "")
                                + this.var_3670[this.var_3657[var4]], 104,
                        58 + (var4 - this.var_692) * 23, 15);
            }
        } catch (Exception var7) {
            var7.printStackTrace();
        }
    }

    private void paintGameOver(Graphics var1) {
        int var2;
        int var3;
        if (this.var_3657.length > 4) {
            if (this.gameOverStr == null) {
                this.gameOverStr = arena.getStr(280);
            }

            System.gc();
            var1.setClip(0, 0, this.width, this.height + 20);
            if (this.gameOverBg == null) {
                this.gameOverBg = this.openImage(33);
            }

            var1.drawImage(this.gameOverBg, 0, 0, 0);
            var1.setFont(this.fonts[1]);
            if (this.var_3657.length > 4) {
                var1.setColor(0, 0, 0);
                var2 = this.fonts[1].stringWidth(this.gameOverStr) / 2;
                var1.drawString(this.gameOverStr, this.width / 2 - 3 - var2,
                        this.height / 2, 20);
                var1.drawString(this.gameOverStr, this.width / 2 - 3 - var2,
                        this.height / 2, 20);
                var1.setColor(255, 0, 0);
                var1.drawString(this.gameOverStr, this.width / 2 - var2,
                        this.height / 2, 20);
            } else {
                var1.setColor(255, 0, 0);
                String var7 = this.var_3817 + ". " + this.getStr(524);
                var3 = this.fonts[1].stringWidth(var7) / 2;
                var1.drawString(var7, this.width / 2 - 3 - var3,
                        this.height / 2, 20);
                var1.drawString(var7, this.width / 2 + 3 - var3,
                        this.height / 2, 20);
                var1.setColor(255, 255, 255);
                var1.drawString(var7, this.width / 2 - var3,
                        this.height / 2, 20);
            }
        } else if (this.championsBg == null || this.var_2c1c < 75) {
            System.gc();
            if (this.championsBg == null) {
                this.championsBg = this.openImage(32);
            }

            var1.setClip(0, 0, this.width, this.height + 20);
            var1.drawImage(this.championsBg, 0, 0, 0);

            int var4;
            try {
                for (var4 = 0; var4 < 3; ++var4) {
                    var2 = arena.getParameter(270 + var4 * 2);
                    var3 = arena.getParameter(271 + var4 * 2) - 41;
                    var1.setClip(var2, var3, 18, 41);
                    if (this.var_3657[var4] != 0) {
                        var1.drawImage(
                                this.headsImage[this.var_1f6c[this.var_3657[var4] - 1][0] - 1],
                                var2, var3, 0);
                        var1.drawImage(
                                this.var_f8e[this.var_1f6c[this.var_3657[var4] - 1][1] - 1],
                                var2, var3 + 18, 0);
                    } else {
                        var1.drawImage(this.headsImage[this.var_234[0]], var2,
                                var3, 0);
                        var1.drawImage(this.var_f8e[this.var_234[1]], var2,
                                var3 + 18, 0);
                    }
                }
            } catch (Exception ignored) {
            }

            var1.setFont(this.fonts[1]);

            for (var2 = 2; var2 >= 0; --var2) {
                try {
                    String var8 = var2 + 1 + ". "
                            + this.var_36a6[this.var_373b[this.var_326a[var2]]];
                    var4 = this.fonts[1].stringWidth(var8);
                    var1.setColor(30, 30, 30);
                    this.paintString(var1, var8, (this.width - var4) / 2 + 1,
                            14 + var2 * 15, 100);
                    this.paintString(var1, var8, (this.width - var4) / 2 - 1,
                            16 + var2 * 15, 100);
                    this.paintString(var1, var8, (this.width - var4) / 2 + 1,
                            16 + var2 * 15, 100);
                    this.paintString(var1, var8, (this.width - var4) / 2 - 1,
                            14 + var2 * 15, 100);
                    var1.setColor(255, 0, 0);
                    this.paintString(var1, var8, (this.width - var4) / 2,
                            15 + var2 * 15, 100);
                } catch (Exception var5) {
                    var5.printStackTrace();
                }
            }
        }
    }

    private void paintEquip(Graphics g) {
        g.setClip(0, 0, this.width, this.height + 20);
        g.drawImage(this.optionBg2, 0, 0, 0);
        g.setClip(0, 0, 200, 200);
        g.drawImage(this.statIcons, 25 - this.statIcons.getWidth(), 100, 0);
        if (this.var_535) {
            if (arena.boolPhoto) {
                this.var_2b6c = false;
                return;
            }

            g.drawImage(this.optionBg, 0, 0, 0);
            this.var_5fb = arena.getStr(407);
            g.setFont(this.fonts[1]);
            g.setColor(200, 200, 200);
            g.drawString(this.var_5fb, 12, 14, 20);
            g.setColor(255, 0, 0);
            g.fillRect(
                    (this.width - arena.image.getWidth()) / 2,
                    (this.height - arena.image.getHeight()) / 2,
                    arena.photoSize, arena.photoSize);
            g.setClip((this.width - arena.image.getWidth()) / 2,
                    (this.height - arena.image.getHeight()) / 2,
                    arena.photoSize, 15 + arena.photoSize);
            g.drawImage(arena.image,
                    (this.width - arena.image.getWidth()) / 2,
                    (this.height - arena.image.getHeight()) / 2, 0);
        } else if (this.var_4ee) {
            g.setFont(this.fonts[2]);
            g.setColor(220, 220, 220);
            this.paintString(g, this.var_5b6 + " " + this.var_234[6] + "/"
                    + this.var_234[7] * 5, 15, 20, this.width - 30);
            g.setFont(this.fonts[1]);
            this.paintString(g, this.var_5e3 + ": " + this.var_234[8], 15, 35,
                    this.width - 30);

            try {
                g.setClip(this.width - 15 - 18, 39, 18, 41);
                g.drawImage(this.headsImage[this.var_234[0]],
                        this.width - 15 - 18, 39, 0);
                g.drawImage(this.var_f8e[this.var_234[1]],
                        this.width - 15 - 18, 57, 0);
            } catch (Exception var3) {
                var3.printStackTrace();
            }

            g.setFont(this.fonts[1]);
            g.setColor(255, 255, 255);
            g.setClip(0, 0, this.width, this.height + 20);
            if (this.var_567 < 2) {
                g.drawString("[", this.width - 15 - 18 - 7,
                        40 + this.var_567 * 20, 20);
                g.drawString("]", this.width - 13,
                        40 + this.var_567 * 20, 20);
            } else {
                g.drawString("[", 25, 98 + (this.var_567 - 2) * 12, 20);
                g.drawString("]", 123, 98 + (this.var_567 - 2) * 12, 20);
                g.setColor(200, 200, 200);
                this.paintString(g, this.var_62c[this.var_567 - 2], 11, 80,
                        this.width - 17);
            }
        } else {
            g.setFont(this.fonts[1]);
            g.setColor(0, 0, 0);
            this.paintString(g, this.var_2ae9, 14, 19, this.width - 28);
            this.paintString(g, this.var_2ae9, 16, 21, this.width - 28);
            g.setColor(255, 255, 255);
            this.paintString(g, this.var_2ae9, 15, 17, this.width - 28);
            g.setColor(0, 0, 0);
            this.paintString(g, this.playerName, 14, 38, this.width - 17 - 14);
            this.paintString(g, this.playerName, 16, 40, this.width - 17 - 16);
            g.setColor(255, 200, 200);
            this.paintString(g, this.playerName, 15, 39, this.width - 17 - 15);
            g.setColor(0, 0, 0);
            this.paintString(g, this.var_5a4 + " " + this.var_234[7], 14, 54,
                    this.width - 17 - 14);
            this.paintString(g, this.var_5a4 + " " + this.var_234[7], 16, 56,
                    this.width - 17 - 16);
            g.setColor(255, 200, 200);
            this.paintString(g, this.var_5a4 + " " + this.var_234[7], 15, 55,
                    this.width - 17 - 15);
            g.setClip(this.width - 17 - 18, 39, 18, 41);
            g.drawImage(this.headsImage[this.var_234[0]],
                    this.width - 17 - 18, 39, 0);
            g.drawImage(this.var_f8e[this.var_234[1]],
                    this.width - 17 - 18, 57, 0);
        }

        if (!this.var_535) {
            g.setClip(0, 0, this.width, this.height);

            for (int var2 = 0; var2 < 4; ++var2) {
                g.setColor(0, 200, 0);
                g.fillRect(30, 100 + var2 * 12,
                        110 * this.var_234[2 + var2] / 25, 8);
                g.setColor(0, 50, 0);
                g.fillRect(30 + 110 * this.var_234[2 + var2] / 25,
                        100 + var2 * 12,
                        90 - 110 * this.var_234[2 + var2] / 25, 8);
            }
        }

    }

    private synchronized void paintLogo(Graphics g) {
        g.setClip(0, 0, this.width, this.height + 20);
        g.setFont(this.font);
        if (this.percent != 0 && this.var_3559) {
            g.setClip(0, 0, this.getWidth(), this.getHeight());
            g.setColor(0, 0, 0);
            g.fillRect(0, 100, this.width, 20);
            g.setColor(200, 200, 200);
            String var3 = this.percent + "%";
            g.drawString(var3, Math.max((this.width - this.fonts[1].stringWidth(var3)) / 2, 0), 102, 0);
        } else {
            g.drawImage(this.logoImage, 0, 0, 0);
            g.setColor(250, 250, 250);
            int w = this.fonts[1].stringWidth(this.loadingStr);
            this.paintString(g, this.loadingStr, (this.width - w) / 2, 70,
                    80);
            g.setColor(0, 0, 0);
            this.paintString(g, this.loadingStr, (this.width - w) / 2 - 1,
                    69, 80);
            this.var_3559 = true;
        }
    }

    private synchronized void paintProgres(Graphics g) {
        int w = (this.width - 10) * this.percent / 100;
        g.setColor(100, 100, 100);
        g.fillRect(5, 100, this.width - 10, 20);
        g.setColor(0, 250, 0);
        g.fillRect(5, 100, w, 20);
        if (!Arena.alwaysTrue) { //
            g.setColor(255, 0, 0);
            g.drawString(this.percent + "%", this.width / 2 - 15, 102, 0);
        }
    }

    private synchronized void paintGameLoading(Graphics var1) {
        var1.setClip(0, 0, this.width, this.height + 20);
        var1.setColor(50, 50, 50);
        var1.fillRect(0, 0, this.width, this.height + 20);
        int var2;
        if (this.var_891 > 0 && this.var_891 <= 4) {
            String var3 = this.getStr(210 + this.var_891 - 1);
            var1.setFont(this.fonts[0]);
            var1.setColor(150, 0, 0);
            byte var4 = 30;
            var2 = this.fonts[0].stringWidth(var3);
            this.paintString(var1, var3, (this.width - var2) / 2 - 1, var4 - 1,
                    this.width);
            this.paintString(var1, var3, (this.width - var2) / 2 + 1, var4 + 1,
                    this.width);
            var1.setColor(200, 200, 200);
            this.paintString(var1, var3, (this.width - var2) / 2, var4,
                    this.width);
        }

        var1.setColor(150, 0, 0);
        if (this.loadingStr2 == null) {
            this.loadingStr2 = this.getStr(250);
        }

        var2 = this.fonts[0].stringWidth(this.loadingStr2);
        this.paintString(var1, this.loadingStr2, (this.width - var2) / 2 - 1, 69,
                this.width);
        this.paintString(var1, this.loadingStr2, (this.width - var2) / 2 + 1, 71,
                this.width);
        var1.setColor(200, 200, 200);
        this.paintString(var1, this.loadingStr2, (this.width - var2) / 2, 70,
                this.width);
        this.paintProgres(var1);
        this.var_2b6c = false;
    }

    private synchronized void paintLoading(Graphics g) {
        g.setClip(0, 0, this.width, this.height + 20);
        if (this.percent == 0 || this._chall) {
            this._chall = false;
            if (this.workingStr == null) {
                this.workingStr = arena.getStr(405);
            }

            g.setColor(50, 50, 50);
            g.fillRect(0, 0, this.width, this.height + 20);
            g.setFont(this.fonts[0]);
            g.setColor(150, 0, 0);
            int var2 = this.fonts[0].stringWidth(this.workingStr);
            this.paintString(g, this.workingStr, (this.width - var2) / 2 - 1,
                    69, this.width);
            this.paintString(g, this.workingStr, (this.width - var2) / 2 + 1,
                    71, this.width);
            g.setColor(200, 200, 200);
            this.paintString(g, this.workingStr, (this.width - var2) / 2, 70,
                    this.width);
        }

        this.paintProgres(g);
    }

    public Image sub_4c8() {
        Image var1 = Image.createImage(this.width, this.height + 20);
        this.var_2bba = true;
        this.paintGame(var1.getGraphics());
        this.var_2bba = false;
        return var1;
    }

    private void paintGame(Graphics g) {
        if (this.var_a72) {
            ++this.var_2a41;
            g.setClip(0, 0, this.width, this.height);
            g.drawImage(this.bgImage, this.var_2c78 / this.var_11d4,
                    this.var_2cdb / this.var_11fa, 0);
            g.setClip(0, 0, this.width, this.height);
            this.var_1c70.paint(g);
            ++this.var_2ced;
            this.var_2ced %= 57;
            int var3 = Math.max(2, 6 - 6 * this.var_1f25_player.var_b81_hp
                    / this.var_1f25_player.var_af7);
            int var15;
            if (this.var_1f25_player.var_aaf) {
                var15 = this.var_2ced % 12 < var3 ? this.var_2ced % (var3 * 2)
                        : Math.max(0, var3 * 2 - this.var_2ced % 12);
            } else {
                var15 = 0;
            }

            g.setClip(66, this.height + 6, 57 - this.var_2ced, 10);
            g.drawImage(this.cardioImage, 66 - this.var_2ced - var15 * 57, this.height + 6, 0);
            g.setClip(123 - this.var_2ced, this.height + 6, this.var_2ced, 10);
            g.drawImage(this.cardioImage, 123 - this.var_2ced - var15 * 57, this.height + 6, 0);

            try {
                g.setClip(0, 0, this.width, this.height);
                this.sub_21e(this.var_1074, g, this.var_2c78, this.var_2cdb);
            } catch (Exception var13) {
                var13.printStackTrace();
            }

            this.sub_21e(this.var_fb7, g, this.var_2c78, this.var_2cdb);

            Warrior var4;
            int var5;
            int var6;
            int var7;
            for (var7 = 0; var7 < this.var_1efb.length; ++var7) {
                var4 = this.var_1efb[var7];
                if (var4.var_aaf && var4.var_596 == 0) {
                    var5 = var4.var_48e
                            + this.var_2c78
                            - this.var_ec4[var4.var_f3a]
                            / 2
                            + var4.var_ee8
                            - (var4.var_6b1 && var4.var_5ef == 0 ? var4.var_48e % 4
                            : 0);
                    var6 = var4.var_4dc + this.var_2cdb + 18 + 5
                            + (var4.var_822 ? 5 : 0);
                    g.setClip(
                            var5,
                            var6,
                            this.var_ec4[var4.var_f3a],
                            Math.min(this.var_ef3[var4.var_f3a],
                                    Math.max(0, this.height - var6)));
                    g.drawImage(this.var_1ff8[var4.var_f3a], var5, var6, 0);
                }
            }

            g.setClip(0, 0, this.width, this.height);
            this.sub_21e(this.var_fa2, g, this.var_2c78, this.var_2cdb);

            for (var7 = 0; var7 < this.var_1efb.length; ++var7) {
                var4 = this.var_1efb[var7];
                if (var4.var_aaf && var4.var_596 == 1) {
                    var5 = var4.var_48e
                            + this.var_2c78
                            + 18
                            - this.var_ec4[var4.var_f3a]
                            / 2
                            + var4.var_ee8
                            - (var4.var_6b1 && var4.var_5ef == 0 ? var4.var_48e % 4
                            : 0);
                    var6 = var4.var_4dc + this.var_2cdb + 18 + 5
                            + (var4.var_822 ? 5 : 0);
                    g.setClip(
                            var5,
                            var6,
                            this.var_ec4[var4.var_f3a],
                            Math.min(this.var_ef3[var4.var_f3a],
                                    Math.max(0, this.height - var6)));
                    g.drawImage(this.var_1ff8[var4.var_f3a], var5, var6
                            - this.var_ef3[var4.var_f3a], 0);
                }
            }

            g.setClip(0, 0, this.width, this.height);
            this.sub_21e(this.var_1011, g, this.var_2c78, this.var_2cdb);
            g.setClip(0, 0, this.width, this.height);

            for (var7 = 0; var7 < this.var_1efb.length; ++var7) {
                if (this.var_1efb[var7].var_bd6_is_enemy && this.var_1efb[var7].var_aaf) {
                    g.setColor(0, 200, 0);
                    g.fillRect(this.var_1efb[var7].var_48e + this.var_2c78,
                            this.var_1efb[var7].var_4dc - 5 + this.var_2cdb,
                            this.var_1efb[var7].var_ec1, 2);
                    g.setColor(200, 0, 0);
                    g.fillRect(this.var_1efb[var7].var_48e + this.var_2c78
                                    + this.var_1efb[var7].var_ec1,
                            this.var_1efb[var7].var_4dc - 5 + this.var_2cdb,
                            12 - this.var_1efb[var7].var_ec1, 2);
                }
            }

            g.setColor(0, 0, 0);

            try {
                if (this.var_1f25_player != null && this.var_1f25_player.var_aaf && !this.var_a17) {
                    this.var_30a = Math.min(this.var_33e++ + this.var_30a, 0);
                    if (this.var_30a >= 0) {
                        this.var_33e = -4;
                    }

                    g.drawImage(this.arrowImage,
                            this.var_1f25_player.var_48e + this.var_2c78 + this.var_40e,
                            this.var_1f25_player.var_4dc - this.arrowParam + this.var_30a + this.var_2cdb,
                            0);
                }
            } catch (Exception var12) {
                var12.printStackTrace();
            }

            if (this.var_157f > -1 && this.var_1efb[this.var_157f].var_aaf) {
                g.drawImage(this.var_d92,
                        this.var_1efb[this.var_157f].var_48e + this.var_2c78
                                + 1, this.var_2cdb
                                + this.var_1efb[this.var_157f].var_e6d.getY()
                                - this.var_d92.getHeight() + 3, 0);
            }

            if (this.var_2bba) {
                this.paintPanel(g);
            } else {
                g.setFont(this.fonts[1]);
                if (this.var_2de8) {
                    try {
                        byte var19 = 40;
                        byte var8 = 18;

                        for (int var17 = 0; var17 < 4; ++var17) {
                            String var10 = this.var_b2f[this.var_b57[var17]]
                                    + "  " + this.var_1efb[var17].var_a1b;
                            int var11 = this.fonts[1].stringWidth(var10);
                            g.setColor(0, 0, 0);
                            this.paintString(g, var10,
                                    (this.width - var11) / 2 - 1, var19
                                            + var17 * var8, 90);
                            this.paintString(g, var10,
                                    (this.width - var11) / 2 + 1, var19 + 2
                                            + var17 * var8, 90);
                            this.paintString(g, var10,
                                    (this.width - var11) / 2 + 1, var19
                                            + var17 * var8, 90);
                            this.paintString(g, var10,
                                    (this.width - var11) / 2 - 1, var19 + 2
                                            + var17 * var8, 90);
                            g.setColor(255, 0, 0);
                            this.paintString(g, var10,
                                    (this.width - var11) / 2, var19 + 1
                                            + var17 * var8, 90);
                            if (this.var_92a) {
                                var17 = 4;
                            }
                        }
                    } catch (Exception var14) {
                        var14.printStackTrace();
                    }

                    this.paintPanel(g);
                    this.var_2b6c = false;
                } else {
                    if (this.var_a17) {
                        if (this._image == null) {
                            this.var_11c8 /= 2;
                        } else if (this.var_11c8 < this.width) {
                            this.var_11c8 = Math.max(this.var_11c8 + 2,
                                    this.var_11c8 * 2);
                        }

                        if (this.var_11c8 < this.width) {
                            g.drawImage(this.battleImage,
                                    (this.width - this.battleImage.getWidth())
                                            / 2 + this.var_11c8, this.height
                                            / 2 - this.battleImage.getHeight(), 0);
                            g.drawImage(this.overImage,
                                    (this.width - this.overImage.getWidth())
                                            / 2 - this.var_11c8,
                                    this.height / 2, 0);
                        } else if (this._image != null) {
                            if (this.var_11c8 > this.width) {
                                this.var_e7f = Math.min(
                                        (this.getHeight() - this.var_e7f) / 2
                                                + this.var_e7f + 1,
                                        this.getHeight());
                            }

                            g.setClip(0, 0, this.width, this.var_e7f);
                            g.drawImage(this._image, 0, this.var_e7f
                                    - this._image.getHeight(), 0);
                        }
                    } else if (this.var_9b6 > 0L) {
                        g.setColor(30, 30, 30);
                        long var16 = this.var_9b6
                                - (System.currentTimeMillis() - this.var_9dd);
                        String var9 = var16 / 60000L + ":"
                                + (var16 % 60000L / 10000L == 0L ? "0" : "")
                                + var16 % 60000L / 1000L;
                        g.drawString(var9, this.width - 28, 4, 20);
                        if ((this.var_891 == 3 || this.var_891 == 4)
                                && var16 / 1000L % 5L == 0L) {
                            g.setColor(255, 100, 100);
                        } else {
                            g.setColor(255, 0, 0);
                        }

                        g.drawString(var9, this.width - 29, 3, 20);
                    } else if (this.var_1462 > 0) {
                        String var18 = "" + this.var_1462;
                        g.setColor(0, 0, 0);
                        g.drawString(var18, this.width - 5
                                - Font.getDefaultFont().stringWidth(var18), 3, 0);
                        g.setColor(255, 0, 0);
                        g.drawString(var18, this.width - 7
                                - Font.getDefaultFont().stringWidth(var18), 1, 0);
                    }

                    if (this.var_3386 || this.var_3366) {
                        this.paintPanel(g);
                    }

                }
            }
        }
    }

    private void paintBegin(Graphics g) {
        if (arena.isMyCanvasCurrent) {
            if (!this.var_2b6c || this.var_a72) {
                synchronized (g) {
                    try {
                        this.var_2b6c = true;
                        if (this.var_1c70 != null) {
                            this.var_2c78 = this.var_1c70.getX();
                            this.var_2cdb = this.var_1c70.getY();
                        }

                        if (this.var_3594) {
                            this.paintGameLoading(g);
                        } else if (this.var_3051 && Thread.currentThread() != this.var_2f3f) {
                            this.paintLoading(g);
                        } else if (this.var_35b0) {
                            if (this.var_92a) {
                                this.paintChallenge(g);
                            } else if (!this.var_37be) {
                                this.paintTournir(g);
                            } else {
                                this.paintGameOver(g);
                            }
                        } else if (this.var_844 == 2) {
                            this.paintEquip(g);
                        } else if (!this.var_3124) {
                            this.paintLogo(g);
                        } else if (this.var_844 != 0 && this.var_9dd != 0L) {
                            this.paintGame(g);
                        }
                    } catch (Exception var5) {
                        var5.printStackTrace();
                    }
                }

                g.setColor(255, 255, 255);
                this.var_2b6c = false;
            }
        }
    }

    private int sub_587(int var1) {
        return var1 == 0 ? (System.getProperty("microedition.locale")
                .startsWith("zh") ? 0 : 8) : (var1 == 1 ? 0 : (var1 == 2 ? 8
                : 16));
    }

    public void initLogo() {
        Effects.sub_2c(this);
        Warrior.sub_3eb(arena);
        this.fonts[0] = Font.getFont(32, arena.getParameter(401) == 0 ? 0
                : 1, this.sub_587(arena.getParameter(400)));
        this.fonts[1] = Font.getFont(64, arena.getParameter(403) == 0 ? 0
                : 1, this.sub_587(arena.getParameter(402)));
        this.fonts[2] = Font.getFont(64, arena.getParameter(405) == 0 ? 0
                : 1, this.sub_587(arena.getParameter(404)));
        this.fonts[3] = Font.getFont(64, arena.getParameter(407) == 0 ? 0
                : 1, this.sub_587(arena.getParameter(406)));
        this.fonts[4] = Font.getFont(64, (arena.getParameter(409) == 0 ? 0
                : 1) | 4, this.sub_587(arena.getParameter(408)));
        this.fonts[5] = Font.getFont(32, arena.getParameter(411) == 0 ? 0
                : 1, this.sub_587(arena.getParameter(410)));
        this.fonts[6] = Font.getFont(32, arena.getParameter(413) == 0 ? 0
                : 1, this.sub_587(arena.getParameter(412)));
        System.gc();
        this.logoImage = this.openImage(2);
        this.var_844 = 5;
        this.loadingStr = arena.getStr(250);
        this.width = this.getWidth();
        this.height = this.getHeight();
        this.startMThread();
    }

    public boolean sub_5f7(MySprite var1, MySprite var2) {
        return var1.sub_1cc(var2, false);
    }

    private void startMThread() {
        if (this.var_844 != 2) {
            this.optionBg2 = null;
            System.gc();
        }
        this.mThread = new Thread(this);
        this.mThread.start();
    }

    public void sub_679(MyCanvas var1) {
        synchronized (this.locker) {
            this.var_2e22 = 2;
            this.var_2eb2 = new Thread(var1);
            this.var_2eb2.start();
        }
    }

    public void sub_6a1(MyCanvas var1) {
        synchronized (this.locker) {
            this.var_2e22 = 1;
            this.var_2eb2 = new Thread(var1);
            this.var_2eb2.start();
        }
    }

    public void sub_6b4(MyCanvas var1) {
        synchronized (this.locker) {
            this.var_2e22 = 3;
            this.var_2eb2 = new Thread(var1);
            this.var_2eb2.start();
        }
    }

    @Override
    public void run() {
        if (Thread.currentThread() == this.var_2eb2) {
            switch (this.var_2e22) {
                case 1:
                    arena.sub_1fb();
                    break;
                case 2:
                    arena.sub_5c4();
                    break;
                case 3:
                    this.sub_c2();
            }

            this.var_2eb2 = null;
        } else {
            if (Thread.currentThread() == this.var_2f12) {
                arena.sub_47a(this.sub_1092(this.var_2a7b),
                        this.playerName);
                this.var_2f12 = null;
                this.sub_1035();
            } else if (Thread.currentThread() == this.var_2f3f) {
                while (Thread.currentThread() == this.var_2f3f) {
                    try {
                        if (!this.var_2f99 && System.currentTimeMillis() - this.var_3882 > 4000L) {
                            this.var_3882 = System.currentTimeMillis();
                            this.var_2f99 = true;
                            Thread.currentThread();
                            Thread.sleep(100L);
                        }
                    } catch (Exception var7) {
                        var7.printStackTrace();
                    }
                }

                this.var_2f3f = null;
                return;
            }

            if (this.var_844 != 2 && this.var_844 != 4) {
                long var1 = 0L;
                long var3 = 0L;
                if (Thread.currentThread() == this.mThread) {
                    if (this.var_2ffa) {
                        return;
                    }

                    this.var_2ffa = true;

                    while (Thread.currentThread() == this.mThread) {
                        try {
                            var1 = System.currentTimeMillis();
                            this.mainLoop(var3);
                            this.repaint();
                            this.serviceRepaints();
                            var3 = System.currentTimeMillis() - var1;
                            if (100L - var3 > 0L) {
                                Thread.sleep(100L - var3);
                            }
                        } catch (Exception var9) {
                            try {
                                Thread.sleep(10L);
                            } catch (Exception var8) {
                                var8.printStackTrace();
                            }
                        }
                    }

                    this.var_2ffa = false;
                }

            } else {
                this.mainLoop(0L);
                this.repaint();
                this.serviceRepaints();
            }
        }
    }

    public void oneTimeLoop() {
        try {
            this.mainLoop(0L);
            this.repaint();
            this.serviceRepaints();
            Thread.currentThread();
            Thread.sleep(5L);
        } catch (Exception var2) {
            var2.printStackTrace();
        }

    }

    public void setPercentLogoProgressBar(int var1) {
        this.var_3051 = true;
        this.percent = var1;
        this.repaint();
        this.serviceRepaints();
        this.var_3051 = var1 < 100;
        this._chall = false;

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void gameLoading(int var1) {
        if (isMusicPlay) {
            music.closePlayer(64); //
            playMusic(0, 65, -1, true);
        }

        this.threadToNull();
        this.var_a67 = false;
        this.var_3594 = true;
        System.gc();
        this.var_2976 = this.var_a72 = false;
        this.var_2a41 = 0L;
        this.var_33e = -3;
        this.var_30a = 0;
        if (this.arrowImage == null) {
            this.arrowImage = this.openImage(209);
        }

        this.var_2717 = this.var_2039 = false;
        this.setPercentGameProgressBar(15);
        this.var_1db7 = new Vector();
        this.var_2445 = new Bullets[50];
        this.var_2455 = new Bullets[50];
        this.var_2329 = new Effects[50];
        this.var_238b = new Effects[50];
        this.var_24b6 = this.var_2510 = this.var_23a7 = this.var_23cd = 0;
        this.var_1c98 = null;
        this.var_1cdd = null;
        this._image = null;
        this.var_1e1d.clear();
        this.var_3386 = true;
        this.var_3103 = false;
        this.var_a17 = this.var_3463 = this.var_349e = false;
        this.var_e7f = 0;
        int[] var_13c4 = null;
        this.var_157f = -1;
        this.sub_1d7(this.var_fa2);
        this.sub_1d7(this.var_fb7);
        this.sub_1d7(this.var_1011);
        this.sub_1d7(this.var_1074);
        this.var_1efb = null;
        this.var_11c8 = this.width - 1;
        Warrior.var_452 = 0;
        this.var_a89 = 0;
        this.setPercentGameProgressBar(16);
        if (this.tilesImage == null) {
            this.tilesImage = this.openImage(164);
        }

        System.gc();
        System.gc();
        this.setPercentGameProgressBar(20);
        if (this.bgImage == null) {
            this.bgImage = this.openImage(163);
        }

        this.soundAndMusicSetup();
        System.gc();
        this.setPercentGameProgressBar(23);
        this.var_1efb = new Warrior[4];

        try {
            DataInputStream var2 = new DataInputStream(arena.getAssets().
                    open("l" + var1 + typesBattles[this.var_891] + ".lvl"));
            this.var_1d1c = var2.readByte();
            this.var_1d37 = var2.readByte();
            this.var_1c98 = new byte[this.var_1d1c * this.var_1d37];
            this.var_1cdd = new byte[this.var_1d1c * this.var_1d37];

            for (int var3 = 0; var3 < this.var_1cdd.length; ++var3) {
                this.var_1cdd[var3] = -1;
            }

            var2.read(this.var_1c98);
            byte var21 = 0;
            int var4 = 0;
            int var5 = 0;
            int var6 = 0;
            int var7 = 0;
            int var8 = 0;
            int var9 = 0;
            int var10 = 0;

            for (int var11 = 0; var11 < this.var_1c98.length; ++var11) {
                if (this.var_1c98[var11] <= -20) {
                    this.var_1cdd[var11] = (byte) ((this.var_1c98[var11] - -20) * -1);
                    this.var_1c98[var11] = 0;
                }

                var21 = this.var_1c98[var11];
                if (var21 == -1) {
                    ++var4;
                } else if (var21 >= 53 && var21 <= 64) {
                    ++var5;
                } else if (var21 == 65) {
                    ++var8;
                } else if ((var21 < 67 || var21 > 70) && var21 != 74) {
                    if (var21 == -3) {
                        ++var10;
                    }
                } else {
                    ++var9;
                }

                if (var21 == 53) {
                    ++var6;
                }

                if (var21 >= 65 && var21 <= 79) {
                    ++var7;
                }
            }

            this.setPercentGameProgressBar(30);
            this.var_3103 = var4 > 0;
            this.var_1e04 = new Tiled[var4];
            this.var_10c6 = new int[var5];
            this.var_1f77 = new int[var6];
            this.var_25c8 = new int[var7];
            this.var_25f2 = new int[var7];
            this.var_2650 = new int[var7];
            this.var_21de = new int[var8];
            this.var_220e = new int[var4];
            this.var_2230 = new int[var9];
            if (var10 > 0) {
                var_13c4 = new int[var10];
            }

            var9 = 0;
            var10 = 0;
            var8 = 0;
            var6 = 0;
            var5 = 0;
            var4 = 0;

            int var12;
            for (var12 = 0; var12 < this.var_1c98.length; ++var12) {
                byte var23 = this.var_1c98[var12];
                if (var23 != 0) {
                    if (var23 == -1) {
                        this.var_1e04[var4] = new Tiled(var2.readByte(),
                                var2.readByte(), var2.readByte(),
                                var2.readByte(), var2.readByte(),
                                var2.readByte(), var2.readByte(), this, var12);
                        this.var_220e[var4] = var12;
                        this.var_1e1d.put(new Integer(var12),
                                this.var_1e04[var4++]);
                        this.var_1c98[var12] = 71;
                    } else if (var23 == -2) {
                        this.var_1c98[var12] = 0;
                    } else if (var23 >= 53 && var23 <= 64) {
                        this.var_10c6[var5++] = var12;
                    } else if (var23 == 65) {
                        this.var_21de[var8++] = var12;
                    } else if ((var23 < 67 || var23 > 70) && var21 != 74) {
                        if (var23 == -3) {
                            var_13c4[var10++] = var12;
                            this.var_1c98[var12] = 0;
                        }
                    } else {
                        this.var_2230[var9++] = var12;
                    }

                    if (var23 == 53) {
                        this.var_1f77[var6++] = var12;
                    }
                }
            }

            this.setPercentGameProgressBar(40);
            this.var_2272 = var2.readByte();
            this.setPercentGameProgressBar(45);
            this.var_212d = new byte[this.var_2272][this.var_2272];
            this.var_210c = new int[this.var_2272];

            for (var12 = 0; var12 < this.var_2272; ++var12) {
                this.var_210c[var12] = var2.readByte() + var2.readByte()
                        * this.var_1d1c;
            }

            byte var24 = var2.readByte();

            int var13;
            for (var13 = 0; var13 < var24; ++var13) {
                this.var_212d[var2.readByte()][var2.readByte()] = var2
                        .readByte();
            }

            for (var13 = this.var_1d1c; var13 < this.var_1cdd.length; ++var13) {
                if (this.var_1c98[var13] > 0) {
                    this.var_1cdd[var13] = this.var_1cdd[var13 - this.var_1d1c];
                }
            }

            this.setPercentGameProgressBar(50);

            int var14;
            try {
                byte var25 = var2.readByte();
                this.setPercentGameProgressBar(55);

                for (var14 = 0; var14 < var25; ++var14) {
                    this.var_1e04[var2.readByte()].sub_bb(var2.readByte(),
                            var2.readByte());
                }

                this.setPercentGameProgressBar(65);
            } catch (Exception var19) {
                var19.printStackTrace();
            }

            var2.close();
            this.var_217e = new byte[this.var_2272][];
            this.var_22a0 = new boolean[this.var_2272][this.var_2272];

            for (var13 = 0; var13 < this.var_2272; ++var13) {
                var14 = 0;

                int var16;
                for (var16 = 0; var16 < this.var_2272; ++var16) {
                    if (this.var_212d[var16][var13] > 0) {
                        ++var14;
                    }
                }

                byte[] var15 = new byte[var14];
                var14 = 0;

                for (var16 = 0; var16 < this.var_2272; ++var16) {
                    if (this.var_212d[var16][var13] > 0) {
                        var15[var14++] = (byte) var16;
                    }
                }

                this.var_217e[var13] = var15;
            }

            this.setPercentGameProgressBar(70);
            this.var_1c70 = new MyTiledLayer(this.var_1d1c, this.var_1d37,
                    this.tilesImage, 15, 15, this.var_1c98);
            this.setPercentGameProgressBar(75);
            this.var_146d = 10000;
            this.var_14a4 = 0;
            if (var_13c4 != null) {
                for (var13 = 0; var13 < var_13c4.length; ++var13) {
                    this.var_146d = Math.min(this.var_146d,
                            var_13c4[var13] % this.var_1d1c * 15 + 7);
                    this.var_14a4 = Math.max(this.var_14a4,
                            var_13c4[var13] % this.var_1d1c * 15 + 7);
                    this.var_1504 = var_13c4[var13] / this.var_1d1c * 15
                            + 15;
                    this.var_1c70.setCell(var_13c4[var13] % this.var_1d1c,
                            var_13c4[var13] / this.var_1d1c + 1, 80);
                }
            }

            this.setPercentGameProgressBar(80);
            this.var_1efb[0] = new Warrior(new MySprite(
                    this.headsImage[this.var_234[0]], 18, 18), new MySprite(
                    this.var_f8e[this.var_234[1]], 18, 23), 50, 20,
                    this.var_234[2] * 5 + 20, (this.var_234[3] / 2 + 3)
                    * this.var_3e7 / this.var_3fd,
                    (this.var_234[4] / 2 + 5) * this.var_3e7 / this.var_3fd * 3
                            / 4, (this.var_234[5] / 3 + 1) * this.var_3e7
                    / this.var_3fd);
            this.var_1f25_player = this.var_1efb[0];
            this.setPercentGameProgressBar(83);

            for (var13 = 1; var13 < 4; ++var13) {
                if (this.var_b57[var13] == 4) {
                    this.var_b57[var13] = 5;
                }

                if (this.var_92a && this.var_20cb != null) {
                    try {
                        byte var27 = 4;
                        int var26 = this.var_20f8[var13 - 1] / 10 + 2;
                        this.var_1efb[var13] = new Warrior(
                                new MySprite(
                                        this.headsImage[this.var_1f6c[this.var_b57[var13] - 1][0] - 1],
                                        18, 18),
                                new MySprite(
                                        this.var_f8e[this.var_1f6c[this.var_b57[var13] - 1][1] - 1],
                                        18, 23),
                                10,
                                10,
                                this.var_1f6c[this.var_b57[var13] - 1][2] > 0 ? this.var_1f6c[this.var_b57[var13] - 1][2]
                                        : 256 + this.var_1f6c[this.var_b57[var13] - 1][2],
                                this.var_1f6c[this.var_b57[var13] - 1][3]
                                        * this.var_3e7 / this.var_3fd * var26
                                        / var27,
                                this.var_1f6c[this.var_b57[var13] - 1][4]
                                        * this.var_3e7 / this.var_3fd * 3 / 4
                                        * var26 / var27,
                                this.var_1f6c[this.var_b57[var13] - 1][5]
                                        * this.var_3e7 / this.var_3fd * var26
                                        / var27);
                    } catch (Exception var17) {
                        var17.printStackTrace();
                    }

                    this.var_1efb[var13].sub_236(
                            this.var_1f6c[this.var_b57[var13] - 1][6], 2, 3);
                    this.var_1efb[var13].var_a1b = 0;
                } else {
                    try {
                        this.var_1efb[var13] = new Warrior(
                                new MySprite(
                                        this.headsImage[this.var_1f6c[this.var_b57[var13] - 1][0] - 1],
                                        18, 18),
                                new MySprite(
                                        this.var_f8e[this.var_1f6c[this.var_b57[var13] - 1][1] - 1],
                                        18, 23),
                                10,
                                10,
                                this.var_1f6c[this.var_b57[var13] - 1][2] > 0 ? this.var_1f6c[this.var_b57[var13] - 1][2]
                                        : 256 + this.var_1f6c[this.var_b57[var13] - 1][2],
                                this.var_1f6c[this.var_b57[var13] - 1][3]
                                        * this.var_3e7 / this.var_3fd,
                                this.var_1f6c[this.var_b57[var13] - 1][4]
                                        * this.var_3e7 / this.var_3fd * 3 / 4,
                                this.var_1f6c[this.var_b57[var13] - 1][5]
                                        * this.var_3e7 / this.var_3fd);
                    } catch (Exception var18) {
                        var18.printStackTrace();
                    }

                    this.var_1efb[var13].sub_236(
                            this.var_1f6c[this.var_b57[var13] - 1][6],
                            this.var_1f6c[this.var_b57[var13] - 1][7],
                            this.var_1f6c[this.var_b57[var13] - 1][8]);
                    this.var_1efb[var13].var_a1b = 0;
                }
            }

            this.var_11d4 = this.var_1d1c * 15
                    / (this.bgImage.getWidth() - this.width);
            this.var_11fa = this.var_1d37 * 15
                    / (this.bgImage.getHeight() - this.height);
        } catch (Exception var20) {
            var20.printStackTrace();
        }

        this.setPercentGameProgressBar(90);
        this.var_140d = this.var_1462 = -1;
        this.var_9b6 = -1L;
        boolean var22 = this.settings[3]; // long battles
        if (this.var_891 == 1) {
            this.var_9b6 = 120000L * (long) (var22 ? 2 : 1);
        } else if (this.var_891 == 2) {
            this.var_140d = 4 + (var22 ? 2 : 1) * 4;
        } else if (this.var_891 == 3) {
            this.var_9b6 = (long) (150000 * (var22 ? 2 : 1));
        } else if (this.var_891 == 4) {
            this.var_9b6 = 120000L * (long) (var22 ? 2 : 1);
            System.gc();
            if (this.var_d92 == null) {
                this.var_d92 = this.openImage(171);
            }
        } else if (this.var_891 == 5) {
            this.var_1462 = 5;
        }

        this.setPercentGameProgressBar(100);
        //this.stopPlayers();
        this.var_a67 = true;
        this.var_2a41 = 0L;
        this.var_3594 = false;
        this.var_9dd = this.var_151f = System.currentTimeMillis();
        System.gc();
    }

    public void playMusic(int var1, int var2, int var3, boolean var4) {
        super.arena3._forPlayMus = true;
        super.arena3._forPlayMus1 = true;
        this.music.beginPlay(var1, var2, var3, var4);
    }

    public void playSound(int id) {
        music.playSound(id);
    }

    public void stopPlayers(int var1) {
        if (Arena.alwaysTrue) {
            this.music.stopPlayers();
        }
    }

    public void stopPlayers() {
        if (Arena.alwaysTrue) {
            this.music.stopPlayers();
        }
    }

    private void sub_819() {
        int var1 = this.width / 2 - this.var_1efb[0].var_48e;
        int var2 = Math.max(Math.min(var1, 0), this.width - this.var_1d1c
                * 15);
        int var3 = Math.max(
                Math.min(this.height / 3 - this.var_1efb[0].var_4dc, 0),
                this.height - this.var_1d37 * 15);
        this.var_1c70.setPosition(var2, var3);
    }

    @Override
    public void loadGame() {
        if (!this.var_3124 && this.mThread == Thread.currentThread()) {
            this._setLight(true);
            this.sub_fea();
            this.var_eb = 20;
            this.var_3559 = false;
            this.setPercentLogo(0);
            this.loadSettings();
            this.soundAndMusicSetup();

            try {
                this.music = new Music(super.arena3);
                // TODO : music start
                //this.music.start();
            } catch (Exception var23) {
                var23.printStackTrace();
            }

            System.gc();
            this.setPercentLogo(10);
            System.gc();
            this.bgImage = this.openImage(163);
            this.setPercentLogo(15);
            System.gc();
            this.setPercentLogo(20);
            this.setPercentLogo(30);
            this.sub_8c4();
            System.gc();
            this.setPercentLogo(35);
            this.var_458 = (byte) arena.getParameter(417);
            this.var_40e = (byte) arena.getParameter(470);
            this.arrowParam = (byte) arena.getParameter(471);
            this.var_3e7 = (byte) arena.getParameter(418);
            this.var_3fd = (byte) arena.getParameter(419);
            this.var_fb7 = this.sub_160();
            this.var_1011 = this.sub_160();
            this.var_1074 = this.sub_160();
            this.setPercentLogo(55);
            System.gc();
            this.battleImage = this.openImage(166);
            this.overImage = this.openImage(169);
            this.setPercentLogo(60);
            String[] var29 = null;
            int[][] var2 = null;
            RecordStore var3 = null;

            int i;
            int i1;
            int i2;
            int i3;
            try {
                var3 = RecordStore.openRecordStore(this.recStorages[5], false);
                this.var_733 = 0;
                if (var3 != null) {
                    this.var_733 = var3.getNumRecords();
                    var29 = new String[this.var_733];
                    var2 = new int[this.var_733][9];
                    RecordEnumeration var4 = var3
                            .enumerateRecords(null, null, false);

                    for (i = 0; i < this.var_733; ++i) {
                        byte[] var6 = var4.nextRecord();
                        i1 = this.var_733 - i - 1;
                        var29[i1] = "";

                        for (i2 = 1; i2 < 1 + var6[0]; ++i2) {
                            var29[i1] = var29[i1] + (char) var6[i2];
                        }

                        i2 = 0;

                        for (i3 = var29[i1].length() + 1; i3 < var6.length; ++i3) {
                            var2[i1][i2++] = var6[i3];
                        }
                    }
                }

                var3 = null;
                System.gc();
            } catch (Exception var27) {
                this.var_733 = 0;
            } finally {
                if (var3 != null) {
                    try {
                        var3.closeRecordStore();
                    } catch (RecordStoreException var22) {
                        var22.printStackTrace();
                    }
                }

            }

            byte var32;
            DataInputStream dataInputStream;
            try {
                var32 = 0;
                Image[] var33 = null;
                dataInputStream = arena.getDIStream(this.recStorages[4]);
                if (dataInputStream != null) {
                    var32 = dataInputStream.readByte();
                    var33 = new Image[var32];

                    for (i1 = 0; i1 < var32; ++i1) {
                        var33[i1] = Image.createImage(18, 18);

                        for (i2 = 0; i2 < 18; ++i2) {
                            for (i3 = 0; i3 < 18; ++i3) {
                                int var10 = 255 & dataInputStream.readByte()
                                        | (255 & dataInputStream.readByte()) << 8
                                        | (255 & dataInputStream.readByte()) << 16;
                                if (var10 == 7799014) {
                                    var10 &= 16777215;
                                } else {
                                    var10 |= -16777216;
                                }

                                com.siemens.mp.lcdui.Image.setPixelColor(
                                        var33[i1], i2, i3, var10);
                            }
                        }
                    }
                }

                if (dataInputStream != null) {
                    dataInputStream.close();
                }

                this.headsImage = new Image[4 + var32];
                System.gc();

                for (i1 = 0; i1 < 4; ++i1) {
                    this.headsImage[i1] = this.openImage(177 + i1);
                }

                for (i1 = 0; i1 < var32; ++i1) {
                    this.headsImage[4 + i1] = this.createHeadImage(var33[i1]);
                }
            } catch (Exception var26) {
                var26.printStackTrace();
            }

            this.setPercentLogo(70);
            this.var_f8e = new Image[4];
            System.gc();

            for (int var31 = 0; var31 < 4; ++var31) {
                this.var_f8e[var31] = this.openImage(16 + var31);
            }

            this.setPercentLogo(80);
            dataInputStream = arena.getDIStream(this.recStorages[0]);
            if (dataInputStream == null) {
                this.sub_a84(true);
            } else {
                try {
                    this.playerName = dataInputStream.readUTF();

                    for (i = 0; i < this.var_234.length; ++i) {
                        this.var_234[i] = dataInputStream.readInt();
                    }

                    dataInputStream.close();
                } catch (Exception var25) {
                    var25.printStackTrace();
                }
            }

            this.setPercentLogo(90);
            var32 = 0;

            int var34;
            try {
                dataInputStream = new DataInputStream(arena.getAssets().open("warrior.dat"));
                var32 = dataInputStream.readByte();
                this.var_1f6c = new byte[var32 + this.var_733][9];

                for (var34 = 0; var34 < var32; ++var34) {
                    for (i1 = 0; i1 < 9; ++i1) {
                        this.var_1f6c[var34][i1] = dataInputStream.readByte();
                        if (i1 == 1
                                && (this.var_1f6c[var34][i1] < 1 || this.var_1f6c[var34][i1] > 4)) {
                            this.var_1f6c[var34][i1] = 1;
                        }
                    }
                }

                dataInputStream.close();
            } catch (Exception var24) {
                var24.printStackTrace();
            }

            if (var2 != null) {
                for (var34 = 0; var34 < this.var_733; ++var34) {
                    for (i1 = 0; i1 < 9; ++i1) {
                        this.var_1f6c[var34 + var32][i1] = (byte) var2[var34][i1];
                    }
                }
            }

            this.setPercentLogo(95);
            this.var_b2f = new String[this.var_eb + 1 + this.var_733];
            this.var_b2f[0] = this.playerName;

            for (var34 = 0; var34 < this.var_eb; ++var34) {
                this.var_b2f[var34 + 1] = arena.getStr(500 + var34);
            }

            if (var29 != null) {
                for (var34 = 0; var34 < this.var_733; ++var34)
                    this.var_b2f[this.var_eb + var34 + 1] = var29[var34];
            }
        }

        this.var_eb += this.var_733;
        this.optionBg = this.openImage(168);
        this.setPercentLogo(100);
        this.sub_107d();
        this.var_3124 = true;
        this.sub_8ab();
        this.threadToNull();
        System.gc();
        Arena2.sub_3ee();
    }


    public void sub_84f(long var1) {
        super.sub_84f(var1);
        this.var_13a7 = (long) (this.upDown = this.leftRight = this._fireUse = 0);
        this.var_13a7 = 0L;
        this.var_6e8 = this.var_31be = this.var_31f0 = false;
    }

    public void sub_8ab() {
        if (this.isMusicPlay) {
            music.closePlayer(65);
            music.closePlayer(67);
            this.playMusic(0, 64, 1, true);
        }

        System.gc();
        this.var_844 = 0;
        this.var_a89 = 0;
        this.var_a67 = false;
        this.var_a17 = true;
        arena.var_af2 = true;
        this.sub_8c4();
        this.var_3463 = false;
        this.mThread = null;
        System.gc();
        this.var_35b0 = false;
        this.var_37be = false;
        this.var_3051 = false;
        this.var_91b = false;
        this.var_92a = false;
        arena.commandManage(10);
    }

    private void sub_8c4() {
        this.var_29c3 = false;
        this.var_9dd = 0L;
        Warrior.var_452 = 0;
        System.gc();
        this.var_11c8 = this.width;
        this.var_a67 = false;
    }

    public void keyPressed(int key) {
        if (!this.var_3594 && !this.var_3051) {
            if (this.var_91b && this.var_a17 && this.var_37be) {
                this.sub_8ab();
                arena.sub_a7();
            } else {
                if (this.var_844 == 2) {
                    if (this.var_535 && !arena.boolPhoto && this.var_3246) {
                        if (key == -4) {
                            this.var_3246 = false;
                            arena.setSaveMenu2();
                            return;
                        }

                        if (key == -12) {
                            this.var_3246 = false;
                            arena.sub_1b1();
                            return;
                        }

                        return;
                    }

                    if (this.var_4ee) {
                        switch (key) {
                            case -12:
                                arena.sub_df();
                                return;
                            case -4:
                                arena.sub_90();
                                return;
                        }
                    } else {
                        switch (key) {
                            case -12:
                                this.sub_8ab();
                                arena.sub_a7();
                                return;
                            case -4:
                                arena.sub_48();
                                return;
                        }
                    }
                } else if (this.var_844 == 1) {
                    if (this.var_91b) {
                        if (!this.var_a67 && this.var_a17 && !this.var_35b0) {
                            this.sub_910(key);
                            return;
                        }
                    } else if (this.var_92a) {
                        if (!this.var_a67 && this.var_a17 && !this.var_35b0) {
                            this.sub_910(key);
                            return;
                        }

                        if (!this.var_a67 && key == -4) {
                            arena.sub_2ec();
                            return;
                        }

                        if (!this.var_a67 && key == -12) {
                            arena.sub_16b();
                            return;
                        }
                    } else if (this.var_a17) {
                        this.sub_910(key);
                        return;
                    }

                    if (key == -4 || key == -12) {
                        if (this.var_91b) {
                            if (!this.var_a67) {
                                if (this.var_a17 && !this.var_35b0) {
                                    return;
                                }

                                if (key == -4) {
                                    arena.sub_28d();
                                } else {
                                    arena.sub_14a();
                                }

                                return;
                            }

                            if (this.var_3463) {
                                arena.var_af2 = true;
                                arena.commandManage(10);
                                return;
                            }

                            this._toPause();
                            return;
                        }

                        if (!this.var_92a) {
                            if (!this.var_3463) {
                                this._toPause();
                                return;
                            }

                            arena.var_af2 = true;
                            arena.commandManage(10);
                            return;
                        }

                        if (!this.var_a67) {
                            arena.sub_2ec();
                            return;
                        }

                        if (this.var_2de8) {
                            arena.var_af2 = true;
                            arena.commandManage(10);
                            return;
                        }

                        if (!this.var_3463) {
                            this._toPause();
                            return;
                        }
                    }
                }

                this.sub_910(key);
            }
        }
    }

    private void sub_910(int var1) {
        this.var_31be = var1 == -59;
        this.var_31f0 = var1 == -60;
        if (!this.var_6e8) {
            this.var_6e8 = true;
            this.var_32c0 = 0;
        } else {
            --this.var_32c0;
        }

        super.keyPressed(var1);
        if (this.var_31f0 && this.var_4ee) {
            this.oneTimeLoop();
        }
    }

    @Override
    public void control(long var1) {
        super.control(var1);
        try {
            if (!this.var_6e8) {
                this.var_6e8 = true;
                this.var_32c0 = 0;
            } else {
                --this.var_32c0;
            }

            this.var_38d3 = true;
            if (this.var_37be && this.var_2c1c < -9) {
                this.var_2c1c = -9;
            }

            this.var_13a7 = var1;

            if ((this.var_13a7 & 2L) > 0L) { //jump,left
                this.upDown = 3;
                this.leftRight = 1;
            }

            if ((this.var_13a7 & 32L) > 0L) { //fire
                this._fireUse = 5;
            }

            if ((this.var_13a7 & 8L) > 0L) { //jump,right
                this.upDown = 3;
                this.leftRight = 2;
            }

            if ((this.var_13a7 & 128L) > 0L) { //down, left
                this.upDown = 4;
                this.leftRight = 1;
                this._fireUse = 5;
            }

            if ((this.var_13a7 & 512L) > 0L) { //down, right
                this.upDown = 4;
                this.leftRight = 2;
                this._fireUse = 5;
            }

            if ((this.var_13a7 & 65536L) > 0L) { //left
                this.leftRight = 1;
            }

            if ((this.var_13a7 & 1048576L) > 0L) { //fire
                this._fireUse = 5;
            }

            if ((this.var_13a7 & 131072L) > 0L) { //right
                this.leftRight = 2;
            }

            if ((this.var_13a7 & 262144L) > 0L || this.var_31be) { //jump
                this.upDown = 3;
                this.var_31be = false;
            }

            if ((this.var_13a7 & 524288L) > 0L || this.var_31f0) { //down
                this.upDown = 4;
                this.var_31f0 = false;
            }

            if ((this.var_13a7 & 1L) > 0L) { //use
                this._fireUse = 6;
            }
        } catch (Exception var10) {
            var10.printStackTrace();
        }

        if (this.var_844 == 2 || this.var_844 == 4) {
            this.oneTimeLoop();
        }

        if (this.var_a89 > 10 && this._image == null) {
            if ((this.var_91b || this.var_92a) && this.var_91b) {
                this.var_3309 = true;
            }

            this.var_a89 = 0;
            this.var_326a = new int[this.var_1efb.length];

            int var3;
            var3 = 0;
            while (var3 < this.var_326a.length) {
                this.var_326a[var3] = var3++;
            }

            int var4;
            int var5;
            for (var3 = 0; var3 < this.var_326a.length - 1; ++var3) {
                for (var4 = var3 + 1; var4 < this.var_326a.length; ++var4) {
                    if (this.var_1efb[this.var_326a[var3]].var_a1b < this.var_1efb[this.var_326a[var4]].var_a1b) {
                        var5 = this.var_326a[var4];
                        this.var_326a[var4] = this.var_326a[var3];
                        this.var_326a[var3] = var5;
                    }
                }
            }

            if (!this.var_91b) {
                for (var3 = 0; var3 < this.var_326a.length; ++var3) {
                    if (this.var_1efb[this.var_326a[var3]] == this.var_1f25_player) {
                        this.sub_b1e(Math.max(0, 3 - var3 * 2));
                    }
                }
            } else if (this.var_91b) {
                if (this.var_3612 > 1) {
                    this.sub_b1e(1);
                }

                if (this.var_3657.length == 4) {
                    for (var3 = 0; var3 < this.var_326a.length; ++var3) {
                        if (this.var_1efb[this.var_326a[var3]] == this.var_1f25_player) {
                            this.sub_b1e(Math.max(0, 15 - var3 * 5));
                        }
                    }
                }
            }

            try {
                System.gc();
                this._image = Image.createImage(this.width, this.getHeight());
                Graphics g = _image.getGraphics();
                g.setClip(0, 0, this.width, this.height);
                g.drawImage(this.optionBg, 0, 0, 0);
                g.drawImage(this.resultImage,
                        (this.width - this.resultImage.getWidth()) / 2, 15, 0);
                g.setFont(this.fonts[1]);
                if (this.var_891 != 5) {
                    for (var4 = 0; var4 < this.var_326a.length; ++var4) {
                        if (this.var_b57[this.var_326a[var4]] == 0) {
                            g.setColor(255, 50, 50);
                        } else {
                            g.setColor(230, 230, 230);
                        }

                        g.setClip(8, 28 + var4 * 20, 18, 18);

                        try {
                            var5 = this.var_326a[var4];
                            g.drawImage(
                                    var5 != 0 ? this.headsImage[this.var_1f6c[this.var_b57[var5] - 1][0] - 1]
                                            : this.headsImage[this.var_234[0]], 8,
                                    28 + var4 * 20, 0);
                        } catch (Exception var8) {
                            var8.printStackTrace();
                        }

                        this.paintString(
                                g,
                                var4 + 1 + ". "
                                        + this.var_b2f[this.var_b57[this.var_326a[var4]]],
                                25, 31 + var4 * 20, 70);
                        this.paintString(
                                g,
                                (this.var_1efb[this.var_326a[var4]].var_a1b < 10 ? "0"
                                        : "")
                                        + this.var_1efb[this.var_326a[var4]].var_a1b,
                                this.width - 20, 31 + var4 * 20, 15);
                    }
                } else {
                    var4 = this.var_1f25_player.var_a1b
                            * (this.var_20f8 != null ? this.var_20f8[0] / 5 + 5
                            : 5) / 5;
                    g.setColor(0, 0, 0);
                    g.drawString(this.getStr(295) + " " + var4, 19, 31, 0);
                    g.setColor(200, 200, 200);
                    g.drawString(this.getStr(295) + " " + var4, 20, 32, 0);
                    this.sub_c2e();
                    if (this.var_2a7b != null) {
                        var5 = this.sub_1092(this.var_2a7b);
                    } else {
                        var5 = 0;
                    }

                    if (var4 > var5) {
                        this.sub_c65(var4, this.var_20cb, this.var_20f8);
                    }

                    RecordStore var6 = RecordStore.openRecordStore(
                            this.recStorages[7], true);
                    RecordEnumeration var7 = var6.enumerateRecords(null, null, false);
                    if (var7.numRecords() > 0) {
                        g.setColor(0, 0, 0);
                        this.paintString(g, this.getStr(1) + ": " + var5, 19,
                                49, this.width - 36);
                        if (var5 >= this.var_1f25_player.var_a1b) {
                            g.setColor(250, 150, 150);
                        } else {
                            g.setColor(150, 250, 150);
                        }

                        this.paintString(g, this.getStr(1) + ": " + var5, 20,
                                50, this.width - 36);
                    } else {
                        g.setColor(0, 0, 0);
                        g.setFont(this.fonts[0]);
                        g.drawString(super.arena3.getStr(287), 15, 44, 0);
                        g.setColor(200, 200, 200);
                        g.drawString(super.arena3.getStr(287), 16, 45, 0);
                    }

                    var6.closeRecordStore();
                }
            } catch (Exception var9) {
                var9.printStackTrace();
            }
        } else if (this.var_a17 && this._image != null && this.var_a89 > 10) {
            if (this.var_92a) {
                this.var_2a29 = true;
            }

            System.gc();
            if (!this.var_91b && !this.var_92a) {
                if (this.var_844 != 2) {
                    this.sub_8ab();
                }
            } else {
                this.var_35b0 = true;
                if (this.var_91b && this.var_3309) {
                    this.var_3309 = false;
                    this.sub_cea(this.var_326a);
                }

                this.sub_8c4();
            }

            this.var_a89 = 0;
        }
    }

    private void paintPanel(Graphics var1) {
        var1.setClip(0, this.height, this.width, 20);
        var1.drawImage(this.panelImage, 0, this.height, 0);
        int var2 = this.greenHPImage.getWidth() / 3 * this.var_1f25_player.var_b81_hp
                / this.var_1f25_player.var_af7;
        int var3 = var2 * 3;
        if (var2 > 0) {
            var1.setClip(var_3417, this.height + 3, var3,
                    this.greenHPImage.getHeight());
            var1.drawImage(this.greenHPImage, var_3417, this.height + 3, 0);
        }

        this.var_3366 = var2 != this.var_339d;
        if (var2 < this.var_339d) {
            int var_33b6 = (this.var_339d - var2) * 3;
            var1.setClip(var_3417 + var3, this.height + 3, var_33b6,
                    this.greenHPImage.getHeight());
            var1.drawImage(this.whiteHPImage, var_3417 + var3, this.height + 3, 0);
            --this.var_339d;
        } else {
            this.var_339d = var2;
        }

        this.var_3386 = false;
    }

    public void _toPause() {
        if (!this.var_3463) {
            this.var_3463 = true;
            arena.commandManage(10);
            this.var_3486 = System.currentTimeMillis();

            try {
                Thread.sleep(10L);
            } catch (Exception var2) {
                var2.printStackTrace();
            }

            this.mThread = null;
            this.var_2a41 = 0L;
            if (this.isMusicPlay) {
                music.closePlayer(65); //
                this.playMusic(0, 67, 10, true);
            }
        }
    }

    @Override
    public void showNotify() {
        if (!arena.isNotMyCanvasCurrent) {
            super.showNotify();
        }
    }

    public void hideNotify() {
        if (!arena.isNotMyCanvasCurrent) {
            super.hideNotify();
            if (this.var_844 == 1 && this.var_29c3 && this.var_2a41 > 6L && !this.var_3463) {
                this._toPause();
            }
        }
    }

    public void resumeGame() {
        this.var_2de8 = false;
        System.gc();
        this.var_9dd += System.currentTimeMillis() - this.var_3486;
        this.var_3463 = false;
        arena.var_af2 = true;
        this.var_2a41 = 0L;
        this.var_3386 = this.var_3601 = true;
        this.var_35b0 = false;
        this.var_37be = false;
        System.gc();
        arena.commandManage(13);
        if (this.isMusicPlay) {
            music.closePlayer(67);
            playMusic(0, 65, -1, true); //
        }
    }

    public void sub_a24(int var1, int var2, int var3) {
        if (this.var_23cd == 0) {
            this.var_2329[this.var_23a7] = new Effects(var1, var2, var3, this);
        } else {
            this.var_2329[this.var_23a7] = this.var_238b[--this.var_23cd];
            this.var_238b[this.var_23cd] = null;
            this.var_2329[this.var_23a7].sub_6f_update_effects(var1, var2, var3, this);
        }

        ++this.var_23a7;
    }

    public void sub_a65() {
        try {
            this.var_b2f[0] = this.playerName;
            ByteArrayOutputStream var1 = new ByteArrayOutputStream();
            DataOutputStream var2 = new DataOutputStream(var1);
            var2.writeUTF(this.playerName);

            for (int aVar_234 : this.var_234) {
                var2.writeInt(aVar_234);
            }

            arena.saveRecordStore(this.recStorages[0], var1.toByteArray());
            var2.close();
            if (this.var_4c6) {
                var1 = new ByteArrayOutputStream();
                DataOutputStream var5 = new DataOutputStream(var1);
                var5.writeByte(0);
                arena.saveRecordStore(this.recStorages[1], var1.toByteArray());
                var5.close();
            }
        } catch (Exception var4) {
            var4.printStackTrace();
        }

    }

    public void sub_a84(boolean var1) {
        try {
            this.playerName = this.getStr(525);

            for (int var2 = 0; var2 < this.var_234.length; ++var2) {
                this.var_234[var2] = 10;
            }

            this.var_234[0] = 1;
            this.var_234[1] = 1;
            this.var_234[6] = 0;
            this.var_234[8] = 0;
            this.var_234[7] = 1;
            if (var1) {
                ByteArrayOutputStream var6 = new ByteArrayOutputStream();
                DataOutputStream var3 = new DataOutputStream(var6);
                var3.writeUTF(this.playerName);

                for (int var4 = 0; var4 < this.var_234.length; ++var4) {
                    var3.writeInt(this.var_234[var4]);
                }

                arena.saveRecordStore(this.recStorages[0], var6.toByteArray());
                var3.close();
            }
        } catch (Exception var5) {
            ;
        }

    }

    public void sub_aad() {
        int var1 = 39 + this.var_234[7];

        for (int var2 = 0; var2 < 4; ++var2) {
            var1 -= this.var_234[2 + var2];
        }

        this.var_234[8] = var1;
    }

    public void sub_af9() {
        DataInputStream var1 = arena.getDIStream(this.recStorages[0]);
        if (var1 == null) {
            this.sub_a84(true);
        } else {
            try {
                this.playerName = var1.readUTF();

                for (int var2 = 0; var2 < this.var_234.length; ++var2) {
                    this.var_234[var2] = var1.readInt();
                }

                var1.close();
            } catch (Exception var3) {
                ;
            }
        }

    }

    private void sub_b1e(int var1) {
        if (!this.var_349e) {
            this.var_234[6] += var1;
            if (this.var_234[6] >= this.var_234[7] * 5) {
                this.var_234[6] -= this.var_234[7] * 5;
                ++this.var_234[7];
            }

            this.var_349e = true;
        }
    }

    private void setPercentLogo(int var1) {
        this.var_3559 = true;
        this.percent = var1;
        this.repaint();
        this.serviceRepaints();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void setPercentGameProgressBar(int var1) {
        this.threadToNull();
        this.var_38d3 = true;
        this.percent = var1;
        this.repaint();
        this.serviceRepaints();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void sub_bd4() {
        this._chall = true;
        if (this.var_3601) {
            this.var_3601 = false;
        } else {
            this.setPercentLogoProgressBar(10);
            this.var_37be = false;
            if (this.var_35e9) {
                this.setPercentLogoProgressBar(20);
                this.var_3612 = 1;
                this.var_3706 = this.var_891;
                this.var_3657 = new int[this.var_eb + 1];
                this.var_3670 = new int[this.var_eb + 1];
                this.var_36a6 = new String[this.var_b2f.length];
                this.setPercentLogoProgressBar(50);

                for (int var1 = 0; var1 < this.var_3657.length; ++var1) {
                    this.var_3657[var1] = var1;
                    this.var_36a6[var1] = this.var_b2f[var1];
                }

                this.setPercentLogoProgressBar(80);
                this.sub_d6b();
            } else {
                this.setPercentLogoProgressBar(20);
                this.sub_d9b();
                this.setPercentLogoProgressBar(70);
            }

            this.setPercentLogoProgressBar(100);
            this.sub_1035();
            this.var_35b0 = true;
        }
    }

    private void sub_c11() {
        this.mThread = null;
        System.gc();
        this.setPercentLogoProgressBar(20);
        this._chall = true;
        this.warriorImage = this.openImage(210);
        this.setPercentLogoProgressBar(50);
        if (this.var_3601) {
            this.var_3601 = false;
        } else {
            this.sub_c2e();
            this.setPercentLogoProgressBar(80);
            this.setPercentLogoProgressBar(100);
            this.sub_1035();
            this.var_35b0 = true;
        }
    }

    public void sub_c2e() {
        RecordStore var1 = null;

        try {
            var1 = RecordStore.openRecordStore(this.recStorages[7], true);
            RecordEnumeration var2 = var1.enumerateRecords((RecordFilter) null,
                    (RecordComparator) null, false);
            this.var_2a7b = var2.nextRecord();
            this.sub_260();
        } catch (Exception var12) {
            ;
        } finally {
            if (var1 != null) {
                try {
                    var1.closeRecordStore();
                } catch (Exception var11) {
                    ;
                }
            }

        }

    }

    public void sub_c65(int var1, String[] var2, int[] var3) {
        byte[] var4 = new byte[4];
        if (var2 != null) {
            var4 = new byte[15 + var2[0].length() + var2[1].length()
                    + var2[2].length()];

            int var5;
            for (var5 = 0; var5 < 3; ++var5) {
                var4[3 + var5] = (byte) var2[var5].length();
            }

            for (var5 = 0; var5 < 3; ++var5) {
                System.arraycopy(this.sub_10eb(var3[var5]), 0, var4,
                        (var5 + 2) * 3, 3);
            }

            var5 = 0;

            for (int var6 = 0; var6 < 3; ++var6) {
                System.arraycopy(var2[var6].getBytes(), 0, var4, 15 + var5,
                        var2[var6].length());
                var5 += var2[var6].length();
            }
        }

        System.arraycopy(this.sub_10eb(var1), 0, var4, 0, 3);
        RecordStore var19 = null;

        try {
            var19 = RecordStore.openRecordStore(this.recStorages[7], true);
            RecordEnumeration var18 = var19.enumerateRecords(
                    (RecordFilter) null, (RecordComparator) null, false);
            var18.destroy();
            var19.addRecord(var4, 0, var4.length);
        } catch (Exception var16) {
            ;
        } finally {
            if (var19 != null) {
                try {
                    var19.closeRecordStore();
                } catch (Exception var15) {
                    ;
                }
            }

        }

    }

    private final void sub_c93() {
        if (!this.var_2039) {
            if (Thread.currentThread() == this.mThread) {
                this.var_2039 = true;
                this.var_692 = 0;
                this.var_373b = new int[4];
                boolean[] var1 = new boolean[this.var_3657.length];
                this.var_373b[0] = 0;
                var1[0] = true;
                int var3;
                int var11;
                try {
                    for (var3 = 1; var3 < this.var_373b.length; ++var3) {
                        do {
                            var11 = Warrior.random(this.var_3657.length);
                        } while (var1[var11]);

                        this.var_373b[var3] = var11;
                        var1[var11] = true;
                    }
                } catch (Exception var10) {
                    ;
                }

                for (var3 = 0; var3 < (this.var_3657.length - 4) / 4; ++var3) {
                    int[] var4 = new int[4];

                    for (int var5 = 0; var5 < 4; ++var5) {
                        do {
                            var11 = Warrior.random(this.var_3657.length);
                        } while (var1[var11]);

                        var4[var5] = var11;
                        var1[var11] = true;
                    }

                    int[] var12 = new int[4];

                    int var6;
                    try {
                        for (var6 = 0; var6 < 4; ++var6) {
                            var12[var6] = Warrior.random(15)
                                    + this.var_1f6c[var4[var6] - 1][2] / 10
                                    + this.var_1f6c[var4[var6] - 1][3]
                                    + this.var_1f6c[var4[var6] - 1][4]
                                    + this.var_1f6c[var4[var6] - 1][5];
                        }
                    } catch (Exception var9) {
                        ;
                    }

                    for (var6 = 0; var6 < 3; ++var6) {
                        for (int var7 = var6 + 1; var7 < 4; ++var7) {
                            if (var12[var7] > var12[var6]) {
                                int var8 = var12[var7];
                                var12[var7] = var12[var6];
                                var12[var6] = var8;
                                var8 = var4[var7];
                                var4[var7] = var4[var6];
                                var4[var6] = var8;
                            }
                        }
                    }

                    this.var_375d = this.var_3612 + 2;

                    for (var6 = 0; var6 < 4; ++var6) {
                        this.var_3670[var4[var6]] += this.var_375d - var6;
                    }
                }

                for (var3 = 0; var3 < this.var_3657.length; ++var3) {
                    if (!var1[var3]) {
                        this.var_3670[var3] += this.var_375d / 2;
                    }
                }

            }
        }
    }

    private void sub_cea(int[] var1) {
        this.var_375d = this.var_3612 + 2;
        ++this.var_3612;

        int var2;
        int var3;
        for (var2 = 0; var2 < var1.length; ++var2) {
            for (var3 = 0; var3 < this.var_373b.length; ++var3) {
                if (var1[var2] == this.var_1efb[var3].var_b9b) {
                    this.var_3670[this.var_373b[var1[var2]]] += this.var_375d
                            - var2;
                }
            }
        }

        for (var2 = 0; var2 < this.var_3657.length - 1; ++var2) {
            for (var3 = var2 + 1; var3 < this.var_3657.length; ++var3) {
                if (this.var_3670[this.var_3657[var2]] < this.var_3670[this.var_3657[var3]]) {
                    int var4 = this.var_3657[var2];
                    this.var_3657[var2] = this.var_3657[var3];
                    this.var_3657[var3] = var4;
                }
            }
        }

        this.sub_d42();
        this.repaint();
        this.serviceRepaints();
        this.sub_d6b();
    }

    private void sub_d42() {
        int var1;
        if (this.var_3612 > 2 && this.var_3657.length > 4) {
            var1 = 2;
            if (this.var_3657.length > 18) {
                var1 = this.var_3657.length - 18;
            }

            this.var_37be = false;

            for (int var2 = 0; var2 < var1; ++var2) {
                this.var_37be = this.var_37be
                        || this.var_3657[this.var_3657.length - 1 - var2] == 0;
            }

            if (this.var_37be) {
                return;
            }

            int[] var4 = new int[Math.max(4, this.var_3657.length - var1)];

            for (int var3 = 0; var3 < var4.length; ++var3) {
                var4[var3] = this.var_3657[var3];
            }

            this.var_3657 = var4;
            System.gc();
        } else if (this.var_3657.length == 4) {
            for (var1 = 0; var1 < this.var_326a.length; ++var1) {
                if (this.var_326a[var1] == 0) {
                    this.var_3817 = var1 + 1;
                }
            }

            this.var_37be = true;
            this.var_2c1c = -20;
        }

    }

    private void sub_d6b() {
        try {
            ByteArrayOutputStream var1 = new ByteArrayOutputStream();
            DataOutputStream var2 = new DataOutputStream(var1);
            var2.writeByte(this.var_37be ? 0 : 1);
            if (!this.var_37be) {
                var2.writeByte(this.var_3612);
                var2.writeByte(this.var_3706);
                var2.writeByte(this.var_3657.length);

                int var3;
                for (var3 = 0; var3 < this.var_3657.length; ++var3) {
                    var2.writeByte(this.var_3657[var3]);
                }

                var2.writeByte(this.var_3670.length);

                for (var3 = 0; var3 < this.var_3670.length; ++var3) {
                    var2.writeByte(this.var_3670[var3]);
                }
            }

            arena.saveRecordStore(this.recStorages[1], var1.toByteArray());
            var2.close();
        } catch (Exception var4) {
            ;
        }

    }

    private void sub_d9b() {
        try {
            DataInputStream var1 = arena.getDIStream(this.recStorages[1]);
            var1.readByte();
            this.var_3612 = var1.readByte();
            this.var_3706 = var1.readByte();
            byte var2 = var1.readByte();
            this.var_3657 = new int[var2];

            for (int var3 = 0; var3 < var2; ++var3) {
                this.var_3657[var3] = var1.readByte();
            }

            byte var6 = var1.readByte();
            this.var_3670 = new int[var6];

            int var4;
            for (var4 = 0; var4 < var6; ++var4) {
                this.var_3670[var4] = var1.readByte();
            }

            this.var_36a6 = new String[var6];
            this.var_36a6[0] = this.playerName;

            for (var4 = 1; var4 < var6; ++var4) {
                this.var_36a6[var4] = this.var_b2f[var4];
            }

            var1.close();
        } catch (Exception var5) {
            ;
        }

        this.var_37be = false;
        this.var_891 = this.var_3706;
    }

    public boolean sub_ddd() {
        boolean var1 = false;

        try {
            DataInputStream var2 = arena.getDIStream(this.recStorages[1]);
            var1 = var2 != null && var2.readByte() == 1;
            var2.close();
        } catch (Exception var3) {
            ;
        }

        return var1;
    }

    private void defaultSettings() {
        this.settings = new boolean[5];

        for (int var1 = 0; var1 < 3; ++var1) {
            this.settings[var1] = true;
        }
        settings[4] = true;

        this.saveSettings();
    }

    @Override
    public void loadSettings() {
        try {
            String str = arena.getFromTable(this.recStorages[2]);
            if (str == null) {
                this.defaultSettings();
            } else {
                for (int i = 0; i < 5; ++i) {
                    char chr = str.charAt(i);
                    if (chr != 49 && chr != 48) {
                        this.defaultSettings();
                        break;
                    }

                    this.settings[i] = chr == 49;
                }
            }
        } catch (Exception var4) {
            var4.printStackTrace();
        }

        this._setLight(this.settings[1]);
    }

    @Override
    public void saveSettings() {
        try {
            StringBuilder var1 = new StringBuilder();

            for (int var2 = 0; var2 < 5; ++var2) {
                if (this.settings[var2]) {
                    var1.append('1');
                } else {
                    var1.append('0');
                }
            }

            arena.prepareToSaveAD(this.recStorages[2], var1.toString(), true);
        } catch (Exception var3) {
            var3.printStackTrace();
        }

        this.soundAndMusicSetup();
    }

    private void soundAndMusicSetup() {
        this.isSoundPlay = this.settings[0];
        this.isMusicPlay = this.settings[4];
    }

    public void addHeadImage(Image image) {
        Image[] images = new Image[this.headsImage.length + 1];

        int i;
        for (i = 0; i < this.headsImage.length; ++i) {
            images[i] = this.headsImage[i];
        }

        images[images.length - 1] = this.createHeadImage(image);
        this.headsImage = images;
        i = image.getWidth();
        int h = image.getHeight();
        int[] pix = new int[i * h];
        byte[] bytes = new byte[pix.length * 3];
        image.getRGB(pix, 0, i, 0, 0, i, h);

        for (int i1 = 0; i1 < i; ++i1) {
            for (int i2 = 0; i2 < h; ++i2) {
                pix[i1 * i + i2] = com.siemens.mp.lcdui.Image.getPixelColor(image, i1, i2);
                if (pix[i1 * i + i2] >> 24 == 0) {
                    pix[i1 * i + i2] = 7799014;
                }

                bytes[(i1 * i + i2) * 3] = (byte) (pix[i1 * i + i2] & 255);
                bytes[(i1 * i + i2) * 3 + 1] = (byte) (pix[i1 * i + i2] >> 8 & 255);
                bytes[(i1 * i + i2) * 3 + 2] = (byte) (pix[i1 * i + i2] >> 16 & 255);
            }
        }

        DataInputStream diStream = arena.getDIStream(this.recStorages[4]);
        boolean isDiSNull = diStream == null;
        byte[] bytes1 = null;
        byte b = 0;

        try {
            if (!isDiSNull) {
                b = diStream.readByte();
                bytes1 = new byte[bytes.length * b];
                diStream.read(bytes1);
                diStream.close();
            }
        } catch (Exception var14) {
            var14.printStackTrace();
        }

        try {
            ByteArrayOutputStream baoStream = new ByteArrayOutputStream();
            DataOutputStream doStream = new DataOutputStream(baoStream);
            if (isDiSNull) {
                doStream.writeByte(1);
            } else {
                doStream.writeByte(b + 1);
                doStream.write(bytes1);
            }

            doStream.write(bytes);
            arena.saveRecordStore(this.recStorages[4], baoStream.toByteArray());
            doStream.close();
        } catch (Exception var13) {
            var13.printStackTrace();
        }
    }

    private Image createHeadImage(Image var1) {
        Image var2 = Image.createImage(36, 18);

        for (int var3 = 0; var3 < 18; ++var3) {
            for (int var4 = 0; var4 < 18; ++var4) {
                int var5 = com.siemens.mp.lcdui.Image.getPixelColor(var1, var4,
                        var3);
                if (var5 != 0) {
                    var5 |= -16777216;
                }

                com.siemens.mp.lcdui.Image.setPixelColor(var2, 35 - var4, var3,
                        var5);
                com.siemens.mp.lcdui.Image.setPixelColor(var2, var4, var3, var5);
            }
        }

        return var2;
    }

    public void sub_f85(byte[] var1, String var2, boolean var3) {
        int var4 = 1 + var2.length();
        byte[] var5 = new byte[6];

        for (int var6 = 0; var6 < 6; ++var6) {
            var5[var6] = var1[var4++];
        }

        byte var15 = var1[var4++];
        byte var7 = var15;
        this.setPercentLogoProgressBar(15);

        try {
            int var9;
            int var12;
            if (var15 >= 4) {
                arena.photoSize = 18;
                int[] var8 = new int[arena.photoSize
                        * arena.photoSize];

                for (var9 = 0; var9 < arena.photoSize; ++var9) {
                    for (int var10 = 0; var10 < arena.photoSize; ++var10) {
                        if (var10 < arena.photoSize - 1) {
                            byte var11 = var1[var4++];
                            var12 = -16777216;
                            var12 = var12 | (var11 >> 5 & 255) << 5 << 16
                                    | (var11 >> 2 & 255) << 5 << 8
                                    | (var11 & 255) << 5;
                            if ((var12 & '\uff00') == '\ue300') {
                                var12 &= 16777215;
                            }

                            var8[var10 + var9 * arena.photoSize] = var12;
                        } else {
                            var8[var10 + var9 * arena.photoSize] = 7799014;
                        }
                    }
                }

                this.setPercentLogoProgressBar(25);
                System.gc();
                Image image = Image.createRGBImage(var8, arena.photoSize,
                        arena.photoSize, true);
                this.setPercentLogoProgressBar(30);
                this.addHeadImage(image);
                this.setPercentLogoProgressBar(40);
                Image var18 = Image.createImage(20, 20);
                this.setPercentLogoProgressBar(50);
                var18.getGraphics().drawImage(image, 4, 4, 0);
                this.addPhotoToStore(var18);
                this.setPercentLogoProgressBar(70);
                var7 = (byte) this.headsImage.length;
            }

            byte[] var16 = new byte[10 + var2.length()];

            for (var9 = 0; var9 < 7 + var2.length(); ++var9) {
                var16[var9] = var1[var9];
            }

            ++var16[1 + var2.length() + 1];
            this.setPercentLogoProgressBar(80);
            var16[1 + var2.length()] = var7;
            var16[7 + var2.length()] = (byte) (60 + Warrior.random(80));
            var16[8 + var2.length()] = (byte) Warrior.random(2);
            var16[9 + var2.length()] = (byte) (Warrior.random(2) + 1);
            RecordStore var19 = RecordStore.openRecordStore(this.recStorages[5],
                    true);
            if (var3) {
                var19.setRecord(arena.var_bd9, var16, 0, var16.length);
            } else {
                var19.addRecord(var16, 0, var16.length);
            }

            this.setPercentLogoProgressBar(85);
            this.var_733 = var19.getNumRecords();
            var19.closeRecordStore();
            this.setPercentLogoProgressBar(90);
            String[] var20 = new String[this.var_b2f.length + 1];

            for (int var21 = 0; var21 < this.var_b2f.length; ++var21) {
                var20[var21] = this.var_b2f[var21];
            }

            var20[this.var_b2f.length] = var2;
            this.var_b2f = var20;
            ++this.var_eb;
            this.setPercentLogoProgressBar(95);
            byte[][] var22 = new byte[this.var_1f6c.length + 1][9];

            for (var12 = 0; var12 < this.var_1f6c.length; ++var12) {
                for (int var13 = 0; var13 < 9; ++var13) {
                    var22[var12][var13] = this.var_1f6c[var12][var13];
                }
            }

            for (var12 = 0; var12 < 9; ++var12) {
                var22[this.var_1f6c.length][var12] = var16[var2.length()
                        + var12 + 1];
            }

            this.var_1f6c = var22;
        } catch (Exception var14) {
            var14.printStackTrace();
        }

    }

    public void addPhotoToStore(Image image) {
        int w = image.getWidth();
        int h = image.getHeight();
        if (this.photoImages == null) {
            this.photoImages = new Image[0];
        }

        Image[] images = new Image[this.photoImages.length + 1];
        System.arraycopy(this.photoImages, 0, images, 0, this.photoImages.length);
        images[this.photoImages.length] = image;
        this.photoImages = images;
        int[] pixels = new int[w * h];
        byte[] bytes = new byte[w * h * 3];

        for (int i = 0; i < w; ++i) {
            for (int i1 = 0; i1 < h; ++i1) {
                pixels[i * w + i1] = com.siemens.mp.lcdui.Image.getPixelColor(image, i, i1);
                if (pixels[i * w + i1] >> 24 == 0) {
                    pixels[i * w + i1] = 7799014;
                }

                bytes[(i * w + i1) * 3] = (byte) (pixels[i * w + i1] & 255);
                bytes[(i * w + i1) * 3 + 1] = (byte) (pixels[i * w + i1] >> 8 & 255);
                bytes[(i * w + i1) * 3 + 2] = (byte) (pixels[i * w + i1] >> 16 & 255);
            }
        }

        try {
            RecordStore store = RecordStore.openRecordStore(this.recStorages[6], true);
            store.addRecord(bytes, 0, bytes.length);
            store.closeRecordStore();
        } catch (Exception var11) {
            var11.printStackTrace();
        }

    }

    private int sub_fea() {
        try {
            RecordStore store = RecordStore.openRecordStore(this.recStorages[6], true);
            RecordEnumeration enumeration = store.enumerateRecords(null, null, false);
            enumeration.reset();
            int countImages = store.getNumRecords();
            if (countImages <= 0) {
                return 0;
            } else {
                this.photoImages = new Image[countImages];
                for (int i = 0; i < countImages; ++i) {
                    this.photoImages[countImages - i - 1] = Image.createImage(value45, value45);
                    byte[] data = enumeration.nextRecord();
                    int i2 = 0;

                    for (int x = 0; x < value45; ++x) {
                        for (int y = 0; y < value45; ++y) {
                            int col = 255 & data[i2++]
                                    | (255 & data[i2++]) << 8
                                    | (255 & data[i2++]) << 16;
                            if (col == 7799014) {
                                col &= 16777215;
                            } else {
                                col |= -16777216;
                            }

                            com.siemens.mp.lcdui.Image.setPixelColor(
                                    this.photoImages[countImages - i - 1], x, y, col);
                        }
                    }
                }

                store.closeRecordStore();
                return countImages;
            }
        } catch (Exception var10) {
            return 0;
        }
    }

    public void threadToNull() {
        this.var_2f3f = null;
    }

    public void sub_1035() {
        if (this.var_2f3f == null) {
            this.var_2f3f = new Thread(this);
            this.var_2f3f.start();
        }
    }

    private void paintString(Graphics g, String str, int x, int y, int var5) {
        int var6 = g.getClipX();
        int var7 = g.getClipY();
        int var8 = g.getClipWidth();
        int var9 = g.getClipHeight();
        int w = g.getFont().stringWidth(str);
        if (this.var_2f99) {
            this.var_2f99 = false;
            this.var_38d3 = !this.var_38d3;
        }

        g.setClip(x, y, var5, 20);
        if (!this.var_38d3 && w > var5) {
            g.drawString(str, x + var5, y, 24);
        } else {
            g.drawString(str, x, y, 20);
        }
        g.setClip(var6, var7, var8, var9);
    }

    private void sub_107d() {
        this.setPercentLogoProgressBar(0);
        if (this.arrowImage == null) {
            this.arrowImage = this.openImage(209);
        }

        this.setPercentLogoProgressBar(5);
        if (this.tilesImage == null) {
            this.tilesImage = this.openImage(164);
        }

        this.setPercentLogoProgressBar(25);
        this.cardioImage = this.openImage(205);
        this.greenHPImage = this.openImage(203);
        this.whiteHPImage = this.openImage(204);
        this.setPercentLogoProgressBar(35);
        if (this.var_1c8d == null) {
            Image var1 = this.openImage(211);
            this.setPercentLogoProgressBar(45);

            try {
                byte[] var2 = new byte[16];

                for (int var3 = 0; var3 < 16; ++var3) {
                    var2[var3] = 1;
                }

                this.var_1c8d = new MyTiledLayer(4, 4, var1, var1.getWidth(),
                        var1.getHeight(), var2);
                var1 = null;
                System.gc();
            } catch (Exception var6) {
                var6.printStackTrace();
            }
        }

        this.setPercentLogoProgressBar(50);
        this.effectsAnims = new MySprite[5];
        System.gc();

        int var7;
        for (var7 = 0; var7 < this.effectsAnims.length; ++var7) {
            this.effectsAnims[var7] = new MySprite(this.openImage(198 + var7),
                    arena.getParameter(223 + var7 * 2),
                    arena.getParameter(224 + var7 * 2));
        }

        this.var_23fa = new int[5];
        this.var_2415 = new int[5];
        this.var_2415[0] = this.var_2415[4] = -this.effectsAnims[0].getHeight() / 2;
        this.var_23fa[0] = this.var_23fa[4] = -this.effectsAnims[0].getWidth() / 2;
        this.var_2415[2] = this.var_2415[1] = this.effectsAnims[2].getHeight();
        this.var_2415[2] -= 7;
        this.var_23fa[2] = this.var_23fa[1] = this.effectsAnims[2].getWidth() / 2;
        this.var_2415[3] = this.effectsAnims[3].getHeight();
        this.var_23fa[3] = this.effectsAnims[3].getWidth() / 2;
        this.setPercentLogoProgressBar(55);
        this.setPercentLogoProgressBar(60);
        this.fallingsAnims = new MySprite[3];
        System.gc();

        for (var7 = 0; var7 < this.fallingsAnims.length; ++var7) {
            this.fallingsAnims[var7] = new MySprite(this.openImage(192 + var7), 17,
                    17);
        }

        this.setPercentLogoProgressBar(70);

        try {
            this.var_1ff8 = new Image[Bullets.var_19.length];
            this.var_ec4 = new int[Bullets.var_19.length];
            this.var_ef3 = new int[Bullets.var_19.length];
            System.gc();

            for (var7 = 0; var7 < Bullets.var_19.length; ++var7) {
                this.var_1ff8[var7] = this.openImage(48 + var7);
                this.var_ec4[var7] = this.var_1ff8[var7].getWidth();
                this.var_ef3[var7] = this.var_1ff8[var7].getHeight() / 2;
            }
        } catch (Exception var5) {
            var5.printStackTrace();
        }

        this.setPercentLogoProgressBar(80);
        this.bulletsAnims = new MySprite[Bullets.var_19.length];

        for (var7 = 0; var7 < this.bulletsAnims.length; ++var7) {
            try {
                System.gc();
                this.bulletsAnims[var7] = new MySprite(this.openImage(224 + var7),
                        arena.getParameter(250 + var7 * 2),
                        arena.getParameter(251 + var7 * 2));
            } catch (Exception var4) {
                var4.printStackTrace();
            }
        }

        this.setPercentLogoProgressBar(85);
        this.resultImage = this.openImage(167);
        this.setPercentLogoProgressBar(95);
        System.gc();
        if (this.panelImage == null) {
            this.panelImage = this.openImage(165);
        }

        this.setPercentLogoProgressBar(100);
    }

    private int sub_1092(byte[] var1) {
        return 255 & var1[0] | (255 & var1[1]) << 8
                | (255 & var1[2]) << 16;
    }

    private byte[] sub_10eb(int var1) {
        return new byte[]{(byte) (var1 & 255),
                (byte) (var1 >> 8 & 255), (byte) (var1 >> 16 & 255)};
    }

    @Override
    public void draw(Graphics var1) {
        this.paintBegin(var1);
    }

    public int sub_1148() {
        return 0;
    }

    public void _setLight(boolean var1) {
        this.setLight(0, var1);
    }

    @Override
    public Image openImage(int var1) {
        try {
            return super.openImage(var1);
        } catch (IOException var3) {
            return null;
        }
    }

    public void makeVibrate(int var1) {
        super.vibrate((long) var1);
    }
}
