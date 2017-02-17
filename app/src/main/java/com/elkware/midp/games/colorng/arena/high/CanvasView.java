package com.elkware.midp.games.colorng.arena.high;

import com.elkware.midp.games.Arena2;
import com.elkware.midp.games.colorng.Class_151;
import com.elkware.midp.games.colorng.Class_3a;
import com.elkware.midp.games.colorng.arena.CanvasView5;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Vector;

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.media.PlayerListener;
import javax.microedition.rms.RecordComparator;
import javax.microedition.rms.RecordEnumeration;
import javax.microedition.rms.RecordFilter;
import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;

public class CanvasView extends CanvasView5 implements Runnable, PlayerListener {

	public final int var_c = 15;
	public final int var_20 = 20;
	public final int var_50 = 4;
	public final int var_6d = 4;
	private int var_eb;
	public ArenaMidlet var_11b;
	private final String[] var_12d = new String[]{"RSPL", "RSTU", "RSOPT",
			"RSWAR", "RSPHO", "RSSMS", "RSMAX", "RSHI"};
	private static final String[] var_139 = new String[]{"bt", "bt", "bt",
			"kh", "cc", "bt"};
	public static final int[] var_154 = new int[]{7, 7, 7, 7, 7, 5};
	public static final int[] var_1a6 = new int[]{301, 301, 301, 330, 360,
			301};
	public static final int[][] var_1da = new int[][]{
			{301, 302, 303, 304, 305, 330, 331},
			{330, 331, 332, 333, 334, 360, 361},
			{360, 361, 362, 363, 364, 301, 302}};
	public int[] var_234 = new int[9];
	public int[] var_295 = new int[4];
	public String var_2f0 = "Player";
	private int var_30a = 0;
	private int var_33e = 0;
	public boolean[] var_395 = new boolean[5];
	private byte var_3e7 = 1;
	private byte var_3fd = 1;
	private byte var_40e = 0;
	private byte var_44a = 0;
	public byte var_458 = 0;
	private Font[] var_498 = new Font[7];
	public boolean var_4c6 = false;
	public boolean var_4ee = false;
	public boolean var_535 = false;
	private int var_567 = 0;
	private String var_5a4;
	private String var_5b6;
	private String var_5e3 = null;
	public String var_5fb = null;
	private String[] var_62c = null;
	private Image var_686;
	private int var_692 = 0;
	private boolean var_6e8 = false;
	public Image var_70c;
	public int var_733 = 0;
	public final int var_760 = 0;
	public final int var_793 = 1;
	public final int var_7b0 = 2;
	public final int var_7f1 = 4;
	public final int var_812 = 5;
	private int var_844 = 0;
	public int var_891 = -1;
	public int var_8f4;
	public boolean var_91b;
	public boolean var_92a;
	public boolean var_97a = true;
	public final long var_987 = 120000L;
	public long var_9b6 = 120000L;
	public long var_9dd;
	public boolean var_a17 = false;
	public boolean var_a67 = false;
	public boolean var_a72 = false;
	private int var_a89 = 0;
	public final int var_ad3 = 4;
	public String[] var_b2f;
	public int[] var_b57;
	public int var_ba9 = 0;
	private Image logoImage;
	public Image var_c21;
	public Image var_c5d = null;
	public Image var_c8a = null;
	private Image var_ced;
	private Image var_d15;
	private Image var_d74;
	private Image var_d92 = null;
	private Image var_de4;
	private Image var_e3e;
	private int var_e7f = 0;
	private int[] var_ec4;
	private int[] var_ef3;
	public Image[] var_f45;
	public Image[] var_f8e;
	public int var_fa2;
	public int var_fb7;
	public int var_1011;
	public int var_1074;
	public int[] var_10c6;
	private Image var_10fc;
	private Image var_1139;
	private Image var_1174;
	private Image var_11ab;
	private int var_11c8;
	private int var_11d4;
	private int var_11fa;
	private Image var_1250 = null;
	private int var_132b;
	private int var_134f;
	private int var_137d;
	private long var_13a7;
	private int[] var_13c4;
	public int var_140d = -1;
	public int var_1462 = -1;
	public int var_146d = 0;
	public int var_14a4 = 0;
	public int var_1504 = 0;
	private long var_151f = 0L;
	public int var_157f = -1;
	public final byte var_15a0 = 1;
	public final byte var_15c0 = 32;
	public final byte var_1601 = 4;
	public final byte var_1646 = 32;
	public final byte var_165a = 71;
	public final byte var_167c = 72;
	public final byte var_16c8 = 57;
	public final byte var_16fd = 60;
	public final byte var_1756 = 61;
	public final byte var_1799 = 64;
	public final byte var_17ef = 53;
	public final byte var_1811 = 65;
	public final byte var_183c = 79;
	public final byte var_187a = 100;
	public final byte var_18b4 = 65;
	public final byte var_18d6 = 66;
	public final byte var_190b = 67;
	public final byte var_194d = 70;
	public final byte var_19a4 = 74;
	public final byte var_19eb = 7;
	public final byte var_1a20 = 8;
	public final byte var_1a83 = -20;
	public final byte var_1a8e = -3;
	public final byte var_1aad = 80;
	public final byte var_1af2 = 79;
	public final byte var_1b50 = 73;
	public final byte var_1b72 = 75;
	public final byte var_1ba1 = 78;
	public Class_151 var_1c70;
	private Class_151 var_1c8d;
	public byte[] var_1c98;
	public byte[] var_1cdd;
	public int var_1d1c;
	public int var_1d37;
	public int var_1d77;
	private int var_1d81;
	public Vector var_1db7;
	public Class_3a[] var_1dec;
	public Class_308[] var_1e04;
	public Hashtable var_1e1d = new Hashtable();
	public Class_2b8[] var_1efb;
	public Class_2b8 var_1f25;
	private byte[][] var_1f6c;
	public int[] var_1f77;
	public int var_1fa2 = 0;
	private Image[] var_1ff8;
	private boolean var_2039 = false;
	private Image var_2097;
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
	public Class_3a[] var_22ea;
	private Class_202[] var_2329;
	private Class_202[] var_238b;
	private int var_23a7 = 0;
	private int var_23cd = 0;
	public int[] var_23fa;
	public int[] var_2415;
	public Class_24e[] var_2445;
	public Class_24e[] var_2455;
	public int var_24b6 = 0;
	public int var_2510 = 0;
	public Class_3a[] var_254a;
	private int var_257a = 0;
	private int[] var_25c8;
	private int[] var_25f2;
	private int[] var_2650;
	public boolean var_26b0 = false;
	public boolean var_270a = false;
	private boolean var_2717 = false;
	private Image[] var_28d9;
	private int var_28fd;
	private int var_2923;
	private int var_296c;
	public boolean var_2976 = false;
	public boolean var_29c3 = false;
	private long[] var_2a04 = new long[20];
	private boolean var_2a29;
	private long var_2a41 = 0L;
	public byte[] var_2a7b = null;
	private String var_2acf;
	private String var_2ae9;
	private String var_2b02 = null;
	private String var_2b2d;
	private String var_2b60 = null;
	private boolean var_2b6c = false;
	private boolean var_2bba = false;
	private int var_2c1c = 0;
	private int var_2c78 = 0;
	private int var_2cdb = 0;
	private int var_2ced = 0;
	private final Font var_2d3f = Font.getFont(64, 0, 0);
	private String var_2d8c;
	public boolean var_2de8 = false;
	private int var_2e22 = 0;
	public Thread var_2e63 = null;
	public Thread var_2eb2 = null;
	public Thread var_2f12 = null;
	private Thread var_2f3f = null;
	long var_2f55 = 0L;
	boolean var_2f99 = false;
	Object var_2fa5 = new Integer(0);
	public boolean var_2ffa = false;
	private boolean var_3051 = false;
	public boolean var_30a2 = false;
	public boolean var_3103 = false;
	private boolean var_3124 = false;
	private Image var_3133 = null;
	private Class_114 var_3180;
	private boolean var_31be = false;
	private boolean var_31f0 = false;
	public boolean var_3246 = false;
	private int[] var_326a;
	private int var_32c0 = 0;
	public boolean var_3309 = false;
	private boolean var_3366 = false;
	public boolean var_3386 = false;
	private int var_339d = 0;
	private int var_33b6 = 0;
	private static int var_3417 = 8;
	public boolean var_3463 = false;
	private long var_3486 = 0L;
	private boolean var_349e = false;
	private int var_34fd = 0;
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
	private String var_3723 = null;
	private int[] var_373b;
	private int var_375d;
	public boolean var_37be = false;
	private int var_3817 = 0;
	private static int var_3870 = 45;
	private long var_3882 = 0L;
	private boolean var_38d3 = true;

	public CanvasView(ArenaMidlet var1) {
		super(var1);
		this.var_11b = var1;
		this.var_fa2 = this.sub_160();
		Class_2b8.sub_1d(this, this.var_fa2);
		this.var_eb = 20;
	}

	public void sub_54() {
		this.var_844 = 0;
	}

	public boolean sub_a8() {
		return 0 == this.var_844;
	}

	public boolean sub_b6() {
		return 1 == this.var_844;
	}

	public void sub_c2() {
		if (this.var_91b) {
			this.sub_bd4();
		} else if (this.var_92a && this.var_844 == 0) {
			this.sub_c11();
		} else if (this.var_891 == 0) {
			this.var_891 = Class_2b8.sub_62(3) + 1;
			this.var_8f4 = Class_2b8.sub_62(var_154[this.var_891]) + 1;
		}

		this.var_3601 = false;
		this.var_844 = 1;
		this.sub_630();
	}

	public int getheight() {
		return super.getheight() - 20;
	}

	public void sub_110() {
		if (this.var_62c == null) {
			this.var_62c = new String[4];
			System.gc();

			for (int var1 = 0; var1 < 4; ++var1) {
				this.var_62c[var1] = this.var_11b.sub_383(262 + var1);
			}

			this.var_686 = this.openImage(173);
		}

		this.var_844 = 2;
		this.sub_630();
	}

	private final void sub_15c() {
		this.repaint();
		this.serviceRepaints();

		try {
			Thread.sleep(500L);
		} catch (Exception var2) {
			;
		}

		this.sub_83e();
	}

	private final void sub_180() {
		if (this.var_91b) {
			if ((this.var_134f == 4 || this.var_31f0) && this.var_6e8) {
				if (this.var_32c0 < 1) {
					this.var_32c0 = 3;
					this.var_692 = Math.min(this.var_692 + 1,
							this.var_3657.length - 4);
					this.var_6e8 = false;
					this.repaint();
					this.serviceRepaints();
				}

				this.var_31f0 = false;
			} else if ((this.var_134f == 3 || this.var_31be) && this.var_6e8) {
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
			this.var_b2f[0] = this.var_2f0;
			this.var_b57 = new int[4];
			this.var_b57[0] = 0;
			if (!this.var_91b) {
				boolean[] var1 = new boolean[this.var_eb + 1];
				var1[0] = true;

				for (int var2 = 1; var2 < 4; ++var2) {
					int var8;
					do {
						var8 = Class_2b8.sub_62(this.var_eb) + 1;
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

			this.sub_737(this.var_8f4);
			this.var_29c3 = true;
		} else if (this.var_844 == 2) {
			if (this.var_1250 == null) {
				this.var_1250 = this.var_d74;
				this.var_2ae9 = this.var_11b.sub_383(221);
				this.var_5a4 = this.var_11b.sub_383(223);
				this.var_5b6 = this.var_11b.sub_383(224);
				this.var_5e3 = this.var_11b.sub_383(225);
			}

			if (this.var_4ee) {
				if (this.var_134f != 3 && !this.var_31be) {
					if (this.var_134f != 4 && !this.var_31f0) {
						if (this.var_567 < 2) {
							if (this.var_567 == 0) {
								var6 = this.var_f45.length;
							} else {
								var6 = this.var_f8e.length;
							}

							if (this.var_567 == 0) {
								if (this.var_132b == 1) {
									this.var_234[this.var_567] = Math.max(0,
											this.var_234[this.var_567] - 1);
								} else if (this.var_132b == 2) {
									this.var_234[this.var_567] = Math.min(
											var6 - 1,
											this.var_234[this.var_567] + 1);
								}
							} else if (this.var_132b == 1) {
								this.var_234[this.var_567] = Math.max(0,
										this.var_234[this.var_567] - 1);
							} else if (this.var_132b == 2) {
								this.var_234[this.var_567] = Math.min(var6 - 1,
										this.var_234[this.var_567] + 1);
							}
						} else if (this.var_132b == 1
								&& (this.var_4c6 || this.var_234[this.var_567] > this.var_295[this.var_567 - 2])) {
							this.var_234[this.var_567] = Math.max(0,
									this.var_234[this.var_567] - 1);
						} else if (this.var_132b == 2 && this.var_234[8] > 0) {
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

	private void sub_1ef(long var1) {
		this.var_ba9 = (this.var_ba9 + 1) % 100;

		int var3;
		for (var3 = 0; var3 < this.var_1e04.length; ++var3) {
			if (this.var_1e04[var3].var_15e) {
				this.var_1e04[var3].sub_3f();
			}
		}

		for (var3 = 0; var3 < this.var_1db7.size(); ++var3) {
			Class_27a var4 = (Class_27a) this.var_1db7.elementAt(var3);
			if (var4.sub_20()) {
				this.var_1db7.removeElementAt(var3);
				this.sub_1b1(this.var_fb7, var4.var_314);
			}
		}

		try {
			for (int var15 = 0; var15 < this.var_23a7; ++var15) {
				Class_202 var16 = this.var_2329[var15];
				if (var16.sub_c5()) {
					this.sub_1b1(this.var_1011, var16.var_18c);
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

		int var5;
		Class_2b8 var17;
		for (var3 = 0; var3 < this.var_1efb.length; ++var3) {
			try {
				var17 = this.var_1efb[var3];
				if (var17.var_bd6) {
					var17.sub_272();
				} else {
					if (this.var_132b == 1) {
						var17.sub_d8();
					}

					if (this.var_132b == 2) {
						var17.sub_11d();
					}

					if (this.var_134f == 3) {
						var17.sub_143();
					}

					var17.var_7c1 = this.var_134f == 4;
					if (this.var_137d == 6) {
						var17.var_c05 = true;
					} else if (this.var_137d == 5 && var17.var_aaf) {
						this.var_1f25.sub_2cf();
					}
				}

				var17.sub_8f();
				this.var_2a04[6 + var3 * 3] = System.currentTimeMillis() - var1;
				if (!(var17.var_716 | var17.var_74b)) {
					var17.var_4dc = var17.var_60f;
				}

				if (var17.var_579 >= 0) {
					try {
						this.var_2923 = Math.min(var17.var_4dc + var17.var_ff9
								+ 1, this.var_1d37 * 15 - 5);
						this.var_28fd = var17.var_8ee / 15 + this.var_2923 / 15
								* this.var_1d1c;
						var17.var_6b1 = this.var_1c98[this.var_28fd] >= 1
								&& this.var_1c98[this.var_28fd] <= 32
								&& this.var_1c98[this.var_28fd] != 8
								&& this.var_1c98[this.var_28fd] != 7;
					} catch (Exception var12) {
						this.var_2923 -= 15;
						var17.var_4dc -= 15;
						this.var_28fd = var17.var_8ee / 15 + this.var_2923 / 15
								* this.var_1d1c;
						if (var17.var_aaf) {
							var17.sub_1da(200);
						}
					}

					if (var17.var_6b1) {
						var17.var_4dc -= this.var_2923 % 15 - 1;
						var17.var_579 = 0;
					}
				} else if (var17.var_579 < 0) {
					this.var_2923 = var17.var_4dc - 1;
					this.var_28fd = var17.var_8ee / 15 + this.var_2923 / 15
							* this.var_1d1c;
					if (this.var_1c98[this.var_28fd] >= 4
							&& this.var_1c98[this.var_28fd] <= 32
							&& this.var_1c98[this.var_28fd] != 8
							&& this.var_1c98[this.var_28fd] != 7) {
						var17.var_579 = 0;
						var17.var_4dc += 15 - var17.var_4dc % 15;
					}
				}

				if (var17.var_53c + var17.var_5ef != 0) {
					this.var_28fd = (var17.var_53c + var17.var_5ef < 0 ? var17.var_48e
							: var17.var_48e + var17.var_feb)
							/ 15
							+ (var17.var_4dc + var17.var_ff9 - 1)
							/ 15
							* this.var_1d1c;
					this.var_296c = var17.var_ff9 / 15 + 1;

					for (var5 = 0; var5 < this.var_296c; ++var5) {
						if (this.var_1c98[this.var_28fd] >= 4
								&& this.var_1c98[this.var_28fd] <= 32
								&& this.var_1c98[this.var_28fd] != 8
								&& this.var_1c98[this.var_28fd] != 7) {
							var17.var_48e += var17.var_53c + var17.var_5ef < 0 ? 15 - var17.var_48e % 15
									: -(15 - var17.var_48e % 15);
							var17.var_53c /= -2;
							var17.var_5ef /= -2;
							break;
						}

						this.var_28fd -= this.var_1d1c;
					}

					this.var_2a04[7 + var3 * 3] = System.currentTimeMillis()
							- var1;
					this.var_28fd = (var17.var_53c + var17.var_5ef < 0 ? var17.var_48e
							: var17.var_48e + var17.var_feb)
							/ 15
							+ (var17.var_4dc + var17.var_ff9 - 1)
							/ 15
							* this.var_1d1c;
					if (this.var_1c98[this.var_28fd] >= 4
							&& this.var_1c98[this.var_28fd] <= 32
							&& this.var_1c98[this.var_28fd] != 8
							&& this.var_1c98[this.var_28fd] != 7) {
						var17.var_48e = var17.var_9de;
					}

					var17.var_9de = var17.var_48e;
					var17.var_a0d = var17.var_4dc;
				}

				this.var_28fd = var17.var_6f5;
				if (this.var_1c98[this.var_28fd] > 0) {
					if (this.var_1c98[this.var_28fd] == 71) {
						if (var17.var_c39) {
							Class_308 var19 = (Class_308) this.var_1e1d
									.get(new Integer(this.var_28fd));
							var19.sub_6c(var3);
							this.var_1c98[this.var_28fd] = 72;
							this.var_1c70.setCell(
									this.var_28fd % this.var_1d1c,
									this.var_28fd / this.var_1d1c, 72);
						}
					} else if (this.var_1c98[this.var_28fd] >= 57
							&& this.var_1c98[this.var_28fd] <= 64
							&& !var17.var_899) {
						var17.sub_1da(500);
					} else if (this.var_1c98[this.var_28fd] >= 65
							&& this.var_1c98[this.var_28fd] <= 79
							&& this.var_1c98[this.var_28fd] != 72
							&& this.var_1c98[this.var_28fd] != 71
							&& (this.var_1c98[this.var_28fd] < 75 || this.var_1c98[this.var_28fd] > 78)) {
						switch (this.var_1c98[this.var_28fd]) {
							case 65:
								var17.var_b81 = Math.min(var17.var_b81 + 40,
										var17.var_af7);
								this.var_3386 = true;
								break;
							case 66:
								var17.sub_1a1();
								this.sub_a24(3, var17.var_48e - 2, var17.var_4dc
										+ var17.var_ff9 - this.var_2415[3]);
								break;
							case 73:
								var17.sub_1da(500);
								break;
							case 79:
								this.var_157f = var3;
								this.var_1c98[this.var_28fd] = 0;
						}

						if (this.var_1c98[this.var_28fd] >= 67
								&& this.var_1c98[this.var_28fd] <= 70
								|| this.var_1c98[this.var_28fd] == 74) {
							var17.var_f3a = this.var_1c98[this.var_28fd] == 74 ? 5
									: this.var_1c98[this.var_28fd] - 67 + 1;
							var17.var_f87 = Class_2b8.var_248[var17.var_f3a];
						}

						this.var_25c8[this.var_257a] = 100;
						this.var_2650[this.var_257a] = this.var_28fd;
						this.var_25f2[this.var_257a++] = this.var_1c98[this.var_28fd];
						this.var_1c98[this.var_28fd] = 0;
						this.var_1c70.setCell(this.var_28fd % this.var_1d1c,
								this.var_28fd / this.var_1d1c, 0);
					}
				}

				var17.var_98b = this.var_28fd;
				if (var17.var_aaf) {
					try {
						for (var5 = 0; var5 < this.var_1db7.size(); ++var5) {
							Class_27a var6 = (Class_27a) this.var_1db7
									.elementAt(var5);
							if (var6.var_314.sub_1cc(var17.var_e6d, true)) {
								var6.sub_36(var17);
							}
						}
					} catch (Exception var11) {
						;
					}

					for (var5 = 0; var5 < this.var_24b6; ++var5) {
						if (this.var_2445[var5].var_3ab != var17
								&& this.sub_5f7(this.var_2445[var5].var_389,
								var17.var_e6d)) {
							this.var_2445[var5].sub_bb(var17);
						}
					}

					if (var17.var_822) {
						var17.var_e6d.defineCollisionRectangle(0, 2,
								var17.var_feb, var17.var_ff9 - 10);
						var17.var_e6d.setPosition(var17.var_48e,
								var17.var_4dc + 8);
						var17.var_ea2.setPosition(var17.var_48e, var17.var_4dc
								+ var17.var_e6d.sub_96());
					} else {
						var17.var_e6d.defineCollisionRectangle(0, 0,
								var17.var_feb, var17.var_ff9);
						var17.var_e6d.setPosition(var17.var_48e, var17.var_4dc);
						var17.var_ea2.setPosition(var17.var_48e, var17.var_4dc
								+ var17.var_e6d.sub_96());
					}
				}
			} catch (Exception var13) {
				;
			}
		}

		var5 = 0;
		int var7;
		int var8;
		for (var7 = this.var_1efb.length; var7 > 0; --var7) {
			Class_2b8 var18 = this.var_1efb[var5++];
			int var21 = var5;
			if (var18.var_aaf) {
				for (var8 = this.var_1efb.length - var5; var8 > 0; --var8) {
					var17 = this.var_1efb[var21++];
					if (var17.var_aaf
							&& this.sub_5f7(var18.var_e6d, var17.var_e6d)) {
						var18.var_5ef = var18.var_48e < var17.var_48e ? -8 : 8;
						var17.var_5ef = var17.var_48e < var18.var_48e ? -8 : 8;
						var17.var_53c = var17.var_5ef / 4;
						var18.var_53c = var18.var_5ef / 4;
						if (var18.var_a35 == -1) {
							var18.var_a35 = var17.var_b9b;
							var18.var_a79 = 20;
						}

						if (var17.var_a35 == -1) {
							var17.var_a35 = var18.var_b9b;
							var17.var_a79 = 20;
						}

						if ((!var17.var_bd6 || !var18.var_bd6)
								&& this.var_395[2]) {
							this.sub_11d4(40);
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
				Class_24e var23 = this.var_2445[var9];
				if (var23.sub_7a()) {
					this.sub_a24(
							Class_202.var_9d[(int) (System.currentTimeMillis() % 2L)],
							var23.var_172 - 10, var23.var_1bf - 10);
					this.sub_1b1(this.var_1074, var23.var_389);
					this.var_2455[this.var_2510++] = var23;
					this.var_2445[var9] = this.var_2445[this.var_24b6 - 1];
					this.var_2445[this.var_24b6 - 1] = null;
					--this.var_24b6;
				}
			}
		} catch (Exception var10) {
			;
		}

	}

	public void sub_1fd(long var1) {
		long var3 = System.currentTimeMillis();
		if (this.var_844 == 5) {
			this.sub_15c();
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
					this.sub_1ef(var3);
				} catch (Exception var9) {
					;
				}
			}

			long var5 = System.currentTimeMillis();
			int var8;
			if (!this.var_a17 && !this.var_3463 && this.var_891 > 2
					&& (var5 - 1000L) / 1000L % 5L == 0L
					&& var5 > this.var_151f + 2000L) {
				if (this.var_891 == 3) {
					for (var8 = 0; var8 < this.var_1efb.length; ++var8) {
						Class_2b8 var7 = this.var_1efb[var8];
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
				if (!this.var_a17 && !this.var_2717 && this.var_26b0
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
				this.var_20f8[var2] = this.sub_1092(var4, 0);
				String var5 = "";

				for (int var6 = 0; var6 < var3; ++var6) {
					var5 = var5 + (char) this.var_2a7b[15 + var1 + var6];
				}

				var1 += var3;
				this.var_20cb[var2] = var5;
			}
		}

	}

	private final void sub_2b3(Graphics var1) {
		var1.setClip(0, 0, this.var_1d77, this.var_1d81 + 20);
		var1.drawImage(this.var_d74, 0, 0, 0);
		var1.setFont(this.var_498[1]);
		var1.setColor(50, 50, 50);
		var1.drawString(this.sub_446(520), 12, 10, 0);
		var1.setColor(255, 255, 255);
		var1.drawString(this.sub_446(520), 11, 8, 0);
		var1.drawImage(this.var_c8a, 0,
				this.var_1d81 - this.var_c8a.getHeight() + 20, 0);
		var1.setColor(0, 0, 0);
		var1.fillRect(11, 24, this.var_1d77 - 22, 19);
		var1.setColor(100, 0, 0);
		var1.fillRect(12, 25, this.var_1d77 - 24, 17);
		String var2 = "";

		try {
			var2 = this.var_2a7b != null ? this.sub_446(32) + " "
					+ this.sub_1092(this.var_2a7b, 0) : this.sub_446(287);
		} catch (Exception var8) {
			var2 = this.sub_446(287);
		}

		var1.setColor(255, 255, 255);
		var1.setFont(this.var_498[0]);
		this.sub_1068(var1, var2, 14, 28, this.var_1d77 - 28);
		var1.setColor(200, 200, 200);
		var1.fillRect(12, this.var_1d81 + 20 - 66, this.var_1d77 - 24, 52);
		var1.setColor(0, 0, 80);
		var1.fillRect(13, this.var_1d81 + 20 - 65, this.var_1d77 - 26, 50);
		var1.setColor(255, 255, 255);
		var1.setFont(this.var_498[6]);
		int var3 = this.var_498[6].stringWidth("5555");
		int var4 = this.var_498[6].stringWidth("2.");
		if (this.var_20cb != null) {
			for (int var5 = 0; var5 < 3; ++var5) {
				int var6 = this.var_1d81 + 20 - 63 + var5 * 15;
				var1.setColor(255, 255, 255);
				var1.setFont(this.var_498[6]);
				var1.drawString(var5 + 1 + ". ", 17, var6, 0);
				String var7 = Integer.toString(this.var_20f8[var5]);
				var1.setColor(255, 255, 255);
				var1.setFont(this.var_498[6]);
				var1.drawString(var7,
						this.var_1d77 - 17 - this.var_498[6].stringWidth(var7),
						var6, 0);
				var1.setColor(255, 255, 255);
				var1.setFont(this.var_498[6]);
				this.sub_1068(var1, this.var_20cb[var5], 18 + var4, var6,
						this.var_1d77 - 35 - var3 - var4);
			}
		} else {
			var1.setColor(255, 255, 255);
			var1.setFont(this.var_498[6]);
			var1.drawString(this.sub_446(29), 14, this.var_1d81 + 20 - 63, 0);
		}

	}

	private final void sub_2f6(Graphics var1) {
		if (this.var_2b02 == null) {
			this.var_2b02 = this.var_11b.sub_383(227);
			this.var_2b2d = this.var_11b.sub_383(228);
		}

		var1.setClip(0, 0, this.var_1d77, this.var_1d81 + 20);
		var1.drawImage(this.var_d74, 0, 0, 0);
		var1.setColor(255, 255, 255);
		var1.fillRect(124, 55, 8, 92);
		var1.setColor(0, 0, 0);
		var1.fillRect(124, 55 + this.var_692 * 92 / (this.var_3657.length - 3),
				8, 92 / (this.var_3657.length - 3) + 1);
		var1.setFont(this.var_498[5]);
		var1.setColor(50, 50, 50);
		String var2 = this.var_3657.length > 4 ? this.var_2b02 + " "
				+ this.var_3612 : this.var_2b2d;
		int var3 = this.var_498[5].stringWidth(var2);
		this.sub_1068(var1, var2, (this.var_1d77 - var3) / 2 + 1, 25,
				this.var_1d77);
		var1.setColor(250, 250, 250);
		this.sub_1068(var1, var2, (this.var_1d77 - var3) / 2, 24, this.var_1d77);

		try {
			var1.setColor(200, 200, 200);
			var1.setFont(this.var_498[1]);

			for (int var4 = this.var_692; var4 < 4 + this.var_692; ++var4) {
				var1.setClip(4, 55 + (var4 - this.var_692) * 23, 18, 18);

				try {
					Image var5;
					if (this.var_3657[var4] != 0) {
						var5 = this.var_f45[this.var_1f6c[this.var_3657[var4] - 1][0] - 1];
					} else {
						var5 = this.var_f45[this.var_234[0]];
					}

					var1.drawImage(var5, 5, 55 + (var4 - this.var_692) * 23, 0);
				} catch (Exception var6) {
					;
				}

				var1.setColor(200, 200, 200);
				this.sub_1068(var1, var4 + 1 + ". "
								+ this.var_36a6[this.var_3657[var4]], 22,
						58 + (var4 - this.var_692) * 23, 87);
				var1.setColor(200, 200, 210);
				this.sub_1068(var1,
						(this.var_3670[this.var_3657[var4]] < 10 ? "0" : "")
								+ this.var_3670[this.var_3657[var4]], 104,
						58 + (var4 - this.var_692) * 23, 15);
			}
		} catch (Exception var7) {
			;
		}

	}

	private final void sub_352(Graphics var1) {
		int var2;
		int var3;
		if (this.var_3657.length > 4) {
			if (this.var_3723 == null) {
				this.var_3723 = this.var_11b.sub_383(280);
			}

			System.gc();
			var1.setClip(0, 0, this.var_1d77, this.var_1d81 + 20);
			if (this.var_70c == null) {
				this.var_70c = this.openImage(33);
			}

			var1.drawImage(this.var_70c, 0, 0, 0);
			var1.setFont(this.var_498[1]);
			if (this.var_3657.length > 4) {
				var1.setColor(0, 0, 0);
				var2 = this.var_498[1].stringWidth(this.var_3723) / 2;
				var1.drawString(this.var_3723, this.var_1d77 / 2 - 3 - var2,
						this.var_1d81 / 2, 20);
				var1.drawString(this.var_3723, this.var_1d77 / 2 - 3 - var2,
						this.var_1d81 / 2, 20);
				var1.setColor(255, 0, 0);
				var1.drawString(this.var_3723, this.var_1d77 / 2 - var2,
						this.var_1d81 / 2, 20);
			} else {
				var1.setColor(255, 0, 0);
				String var7 = this.var_3817 + ". " + this.sub_446(524);
				var3 = this.var_498[1].stringWidth(var7) / 2;
				var1.drawString(var7, this.var_1d77 / 2 - 3 - var3,
						this.var_1d81 / 2, 20);
				var1.drawString(var7, this.var_1d77 / 2 + 3 - var3,
						this.var_1d81 / 2, 20);
				var1.setColor(255, 255, 255);
				var1.drawString(var7, this.var_1d77 / 2 - var3,
						this.var_1d81 / 2, 20);
			}
		} else if (this.var_c5d == null || this.var_2c1c < 75) {
			System.gc();
			if (this.var_c5d == null) {
				this.var_c5d = this.openImage(32);
			}

			var1.setClip(0, 0, this.var_1d77, this.var_1d81 + 20);
			var1.drawImage(this.var_c5d, 0, 0, 0);

			int var4;
			try {
				for (var4 = 0; var4 < 3; ++var4) {
					var2 = this.var_11b.sub_dc(270 + var4 * 2);
					var3 = this.var_11b.sub_dc(271 + var4 * 2) - 41;
					var1.setClip(var2, var3, 18, 41);
					if (this.var_3657[var4] != 0) {
						var1.drawImage(
								this.var_f45[this.var_1f6c[this.var_3657[var4] - 1][0] - 1],
								var2, var3, 0);
						var1.drawImage(
								this.var_f8e[this.var_1f6c[this.var_3657[var4] - 1][1] - 1],
								var2, var3 + 18, 0);
					} else {
						var1.drawImage(this.var_f45[this.var_234[0]], var2,
								var3, 0);
						var1.drawImage(this.var_f8e[this.var_234[1]], var2,
								var3 + 18, 0);
					}
				}
			} catch (Exception var6) {
				;
			}

			var1.setFont(this.var_498[1]);

			for (var2 = 2; var2 >= 0; --var2) {
				try {
					String var8 = var2 + 1 + ". "
							+ this.var_36a6[this.var_373b[this.var_326a[var2]]];
					var4 = this.var_498[1].stringWidth(var8);
					var1.setColor(30, 30, 30);
					this.sub_1068(var1, var8, (this.var_1d77 - var4) / 2 + 1,
							14 + var2 * 15, 100);
					this.sub_1068(var1, var8, (this.var_1d77 - var4) / 2 - 1,
							16 + var2 * 15, 100);
					this.sub_1068(var1, var8, (this.var_1d77 - var4) / 2 + 1,
							16 + var2 * 15, 100);
					this.sub_1068(var1, var8, (this.var_1d77 - var4) / 2 - 1,
							14 + var2 * 15, 100);
					var1.setColor(255, 0, 0);
					this.sub_1068(var1, var8, (this.var_1d77 - var4) / 2,
							15 + var2 * 15, 100);
				} catch (Exception var5) {
					;
				}
			}
		}

	}

	private final void sub_3a3(Graphics var1) {
		var1.setClip(0, 0, this.var_1d77, this.var_1d81 + 20);
		var1.drawImage(this.var_1250, 0, 0, 0);
		var1.setClip(0, 0, 200, 200);
		var1.drawImage(this.var_686, 25 - this.var_686.getWidth(), 100, 0);
		if (this.var_535) {
			if (this.var_11b.var_e61) {
				this.var_2b6c = false;
				return;
			}

			var1.drawImage(this.var_d74, 0, 0, 0);
			this.var_5fb = this.var_11b.sub_383(407);
			var1.setFont(this.var_498[1]);
			var1.setColor(200, 200, 200);
			var1.drawString(this.var_5fb, 12, 14, 20);
			var1.setColor(255, 0, 0);
			var1.fillRect(
					(this.var_1d77 - this.var_11b.var_d78.getWidth()) / 2,
					(this.var_1d81 - this.var_11b.var_d78.getHeight()) / 2,
					this.var_11b.var_de1, this.var_11b.var_de1);
			var1.setClip((this.var_1d77 - this.var_11b.var_d78.getWidth()) / 2,
					(this.var_1d81 - this.var_11b.var_d78.getHeight()) / 2,
					this.var_11b.var_de1, 15 + this.var_11b.var_de1);
			var1.drawImage(this.var_11b.var_d78,
					(this.var_1d77 - this.var_11b.var_d78.getWidth()) / 2,
					(this.var_1d81 - this.var_11b.var_d78.getHeight()) / 2, 0);
		} else if (this.var_4ee) {
			var1.setFont(this.var_498[2]);
			var1.setColor(220, 220, 220);
			this.sub_1068(var1, this.var_5b6 + " " + this.var_234[6] + "/"
					+ this.var_234[7] * 5, 15, 20, this.var_1d77 - 30);
			var1.setFont(this.var_498[1]);
			this.sub_1068(var1, this.var_5e3 + ": " + this.var_234[8], 15, 35,
					this.var_1d77 - 30);

			try {
				var1.setClip(this.var_1d77 - 15 - 18, 39, 18, 41);
				var1.drawImage(this.var_f45[this.var_234[0]],
						this.var_1d77 - 15 - 18, 39, 0);
				var1.drawImage(this.var_f8e[this.var_234[1]],
						this.var_1d77 - 15 - 18, 57, 0);
			} catch (Exception var3) {
				;
			}

			var1.setFont(this.var_498[1]);
			var1.setColor(255, 255, 255);
			var1.setClip(0, 0, this.var_1d77, this.var_1d81 + 20);
			if (this.var_567 < 2) {
				var1.drawString("[", this.var_1d77 - 15 - 18 - 7,
						40 + this.var_567 * 20, 20);
				var1.drawString("]", this.var_1d77 - 13,
						40 + this.var_567 * 20, 20);
			} else {
				var1.drawString("[", 25, 98 + (this.var_567 - 2) * 12, 20);
				var1.drawString("]", 123, 98 + (this.var_567 - 2) * 12, 20);
				var1.setColor(200, 200, 200);
				this.sub_1068(var1, this.var_62c[this.var_567 - 2], 11, 80,
						this.var_1d77 - 17);
			}
		} else {
			var1.setFont(this.var_498[1]);
			var1.setColor(0, 0, 0);
			this.sub_1068(var1, this.var_2ae9, 14, 19, this.var_1d77 - 28);
			this.sub_1068(var1, this.var_2ae9, 16, 21, this.var_1d77 - 28);
			var1.setColor(255, 255, 255);
			this.sub_1068(var1, this.var_2ae9, 15, 17, this.var_1d77 - 28);
			var1.setColor(0, 0, 0);
			this.sub_1068(var1, this.var_2f0, 14, 38, this.var_1d77 - 17 - 14);
			this.sub_1068(var1, this.var_2f0, 16, 40, this.var_1d77 - 17 - 16);
			var1.setColor(255, 200, 200);
			this.sub_1068(var1, this.var_2f0, 15, 39, this.var_1d77 - 17 - 15);
			var1.setColor(0, 0, 0);
			this.sub_1068(var1, this.var_5a4 + " " + this.var_234[7], 14, 54,
					this.var_1d77 - 17 - 14);
			this.sub_1068(var1, this.var_5a4 + " " + this.var_234[7], 16, 56,
					this.var_1d77 - 17 - 16);
			var1.setColor(255, 200, 200);
			this.sub_1068(var1, this.var_5a4 + " " + this.var_234[7], 15, 55,
					this.var_1d77 - 17 - 15);
			var1.setClip(this.var_1d77 - 17 - 18, 39, 18, 41);
			var1.drawImage(this.var_f45[this.var_234[0]],
					this.var_1d77 - 17 - 18, 39, 0);
			var1.drawImage(this.var_f8e[this.var_234[1]],
					this.var_1d77 - 17 - 18, 57, 0);
		}

		if (!this.var_535) {
			var1.setClip(0, 0, this.var_1d77, this.var_1d81);

			for (int var2 = 0; var2 < 4; ++var2) {
				var1.setColor(0, 200, 0);
				var1.fillRect(30, 100 + var2 * 12,
						110 * this.var_234[2 + var2] / 25, 8);
				var1.setColor(0, 50, 0);
				var1.fillRect(30 + 110 * this.var_234[2 + var2] / 25,
						100 + var2 * 12,
						90 - 110 * this.var_234[2 + var2] / 25, 8);
			}
		}

	}

	private synchronized void paintLogo(Graphics var1) {
		var1.setClip(0, 0, this.var_1d77, this.var_1d81 + 20);
		var1.setFont(this.var_2d3f);
		if (this.var_34fd != 0 && this.var_3559) {
			var1.setClip(0, 0, this.getwidth(), this.getheight());
			var1.setColor(0, 0, 0);
			var1.fillRect(0, 100, this.var_1d77, 20);
			var1.setColor(200, 200, 200);
			String var3 = this.var_34fd + "%";
			var1.drawString(
					var3,
					Math.max(
							(this.var_1d77 - this.var_498[1].stringWidth(var3)) / 2,
							0), 102, 0);
			/*if (this.var_34fd == 100) {
				;
			}*/
		} else {
			var1.drawImage(this.logoImage, 0, 0, 0);
			var1.setColor(250, 250, 250);
			int var2 = this.var_498[1].stringWidth(this.var_2acf);
			this.sub_1068(var1, this.var_2acf, (this.var_1d77 - var2) / 2, 70,
					80);
			var1.setColor(0, 0, 0);
			this.sub_1068(var1, this.var_2acf, (this.var_1d77 - var2) / 2 - 1,
					69, 80);
			this.var_3559 = true;
		}

	}

	private synchronized void sub_421(Graphics var1) {
		int var2 = (this.var_1d77 - 10) * this.var_34fd / 100;
		var1.setColor(100, 100, 100);
		var1.fillRect(5, 100, this.var_1d77 - 10, 20);
		var1.setColor(0, 250, 0);
		var1.fillRect(5, 100, var2, 20);
		if (!ArenaMidlet.var_24) {
			var1.setColor(255, 0, 0);
			var1.drawString(this.var_34fd + "%", this.var_1d77 / 2 - 15, 102, 0);
		}

	}

	private final synchronized void sub_446(Graphics var1) {
		var1.setClip(0, 0, this.var_1d77, this.var_1d81 + 20);
		var1.setColor(50, 50, 50);
		var1.fillRect(0, 0, this.var_1d77, this.var_1d81 + 20);
		int var2;
		if (this.var_891 > 0 && this.var_891 <= 4) {
			String var3 = this.sub_446(210 + this.var_891 - 1);
			var1.setFont(this.var_498[0]);
			var1.setColor(150, 0, 0);
			byte var4 = 30;
			var2 = this.var_498[0].stringWidth(var3);
			this.sub_1068(var1, var3, (this.var_1d77 - var2) / 2 - 1, var4 - 1,
					this.var_1d77);
			this.sub_1068(var1, var3, (this.var_1d77 - var2) / 2 + 1, var4 + 1,
					this.var_1d77);
			var1.setColor(200, 200, 200);
			this.sub_1068(var1, var3, (this.var_1d77 - var2) / 2, var4,
					this.var_1d77);
		}

		var1.setColor(150, 0, 0);
		if (this.var_2d8c == null) {
			this.var_2d8c = this.sub_446(250);
		}

		var2 = this.var_498[0].stringWidth(this.var_2d8c);
		this.sub_1068(var1, this.var_2d8c, (this.var_1d77 - var2) / 2 - 1, 69,
				this.var_1d77);
		this.sub_1068(var1, this.var_2d8c, (this.var_1d77 - var2) / 2 + 1, 71,
				this.var_1d77);
		var1.setColor(200, 200, 200);
		this.sub_1068(var1, this.var_2d8c, (this.var_1d77 - var2) / 2, 70,
				this.var_1d77);
		this.sub_421(var1);
		this.var_2b6c = false;
	}

	private final synchronized void sub_49a(Graphics var1) {
		var1.setClip(0, 0, this.var_1d77, this.var_1d81 + 20);
		if (this.var_34fd == 0 || this.var_30a2) {
			this.var_30a2 = false;
			if (this.var_2b60 == null) {
				this.var_2b60 = this.var_11b.sub_383(405);
			}

			var1.setColor(50, 50, 50);
			var1.fillRect(0, 0, this.var_1d77, this.var_1d81 + 20);
			var1.setFont(this.var_498[0]);
			var1.setColor(150, 0, 0);
			int var2 = this.var_498[0].stringWidth(this.var_2b60);
			this.sub_1068(var1, this.var_2b60, (this.var_1d77 - var2) / 2 - 1,
					69, this.var_1d77);
			this.sub_1068(var1, this.var_2b60, (this.var_1d77 - var2) / 2 + 1,
					71, this.var_1d77);
			var1.setColor(200, 200, 200);
			this.sub_1068(var1, this.var_2b60, (this.var_1d77 - var2) / 2, 70,
					this.var_1d77);
		}

		this.sub_421(var1);
	}

	public Image sub_4c8() {
		Image var1 = Image.createImage(this.var_1d77, this.var_1d81 + 20);
		this.var_2bba = true;
		this.sub_50d(var1.getGraphics());
		this.var_2bba = false;
		return var1;
	}

	private final void sub_50d(Graphics var1) {
		if (this.var_a72) {
			++this.var_2a41;
			var1.setClip(0, 0, this.var_1d77, this.var_1d81);
			var1.drawImage(this.var_c21, this.var_2c78 / this.var_11d4,
					this.var_2cdb / this.var_11fa, 0);
			var1.setClip(0, 0, this.var_1d77, this.var_1d81);
			this.var_1c70.paint(var1);
			++this.var_2ced;
			this.var_2ced %= 57;
			int var3 = Math.max(2, 6 - 6 * this.var_1f25.var_b81
					/ this.var_1f25.var_af7);
			int var15;
			if (this.var_1f25.var_aaf) {
				var15 = this.var_2ced % 12 < var3 ? this.var_2ced % (var3 * 2)
						: Math.max(0, var3 * 2 - this.var_2ced % 12);
			} else {
				var15 = 0;
			}

			var1.setClip(66, this.var_1d81 + 6, 57 - this.var_2ced, 10);
			var1.drawImage(this.var_1139, 66 - this.var_2ced - var15 * 57,
					this.var_1d81 + 6, 0);
			var1.setClip(123 - this.var_2ced, this.var_1d81 + 6, this.var_2ced,
					10);
			var1.drawImage(this.var_1139, 123 - this.var_2ced - var15 * 57,
					this.var_1d81 + 6, 0);

			try {
				var1.setClip(0, 0, this.var_1d77, this.var_1d81);
				this.sub_21e(this.var_1074, var1, this.var_2c78, this.var_2cdb);
			} catch (Exception var13) {
				;
			}

			this.sub_21e(this.var_fb7, var1, this.var_2c78, this.var_2cdb);

			Class_2b8 var4;
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
					var1.setClip(
							var5,
							var6,
							this.var_ec4[var4.var_f3a],
							Math.min(this.var_ef3[var4.var_f3a],
									Math.max(0, this.var_1d81 - var6)));
					var1.drawImage(this.var_1ff8[var4.var_f3a], var5, var6, 0);
				}
			}

			var1.setClip(0, 0, this.var_1d77, this.var_1d81);
			this.sub_21e(this.var_fa2, var1, this.var_2c78, this.var_2cdb);

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
					var1.setClip(
							var5,
							var6,
							this.var_ec4[var4.var_f3a],
							Math.min(this.var_ef3[var4.var_f3a],
									Math.max(0, this.var_1d81 - var6)));
					var1.drawImage(this.var_1ff8[var4.var_f3a], var5, var6
							- this.var_ef3[var4.var_f3a], 0);
				}
			}

			var1.setClip(0, 0, this.var_1d77, this.var_1d81);
			this.sub_21e(this.var_1011, var1, this.var_2c78, this.var_2cdb);
			var1.setClip(0, 0, this.var_1d77, this.var_1d81);

			for (var7 = 0; var7 < this.var_1efb.length; ++var7) {
				if (this.var_1efb[var7].var_bd6 && this.var_1efb[var7].var_aaf) {
					var1.setColor(0, 200, 0);
					var1.fillRect(this.var_1efb[var7].var_48e + this.var_2c78,
							this.var_1efb[var7].var_4dc - 5 + this.var_2cdb,
							this.var_1efb[var7].var_ec1, 2);
					var1.setColor(200, 0, 0);
					var1.fillRect(this.var_1efb[var7].var_48e + this.var_2c78
									+ this.var_1efb[var7].var_ec1,
							this.var_1efb[var7].var_4dc - 5 + this.var_2cdb,
							12 - this.var_1efb[var7].var_ec1, 2);
				}
			}

			var1.setColor(0, 0, 0);

			try {
				if (this.var_1f25 != null && this.var_1f25.var_aaf
						&& !this.var_a17) {
					this.var_30a = Math.min(this.var_33e++ + this.var_30a, 0);
					if (this.var_30a >= 0) {
						this.var_33e = -4;
					}

					var1.drawImage(this.var_2097, this.var_1f25.var_48e
									+ this.var_2c78 + this.var_40e,
							this.var_1f25.var_4dc - this.var_44a + this.var_30a
									+ this.var_2cdb, 0);
				}
			} catch (Exception var12) {
				;
			}

			if (this.var_157f > -1 && this.var_1efb[this.var_157f].var_aaf) {
				var1.drawImage(this.var_d92,
						this.var_1efb[this.var_157f].var_48e + this.var_2c78
								+ 1, this.var_2cdb
								+ this.var_1efb[this.var_157f].var_e6d.getY()
								- this.var_d92.getHeight() + 3, 0);
			}

			if (this.var_2bba) {
				this.sub_95f(var1);
			} else {
				var1.setFont(this.var_498[1]);
				if (this.var_2de8) {
					try {
						byte var19 = 40;
						byte var8 = 18;

						for (int var17 = 0; var17 < 4; ++var17) {
							String var10 = this.var_b2f[this.var_b57[var17]]
									+ "  " + this.var_1efb[var17].var_a1b;
							int var11 = this.var_498[1].stringWidth(var10);
							var1.setColor(0, 0, 0);
							this.sub_1068(var1, var10,
									(this.var_1d77 - var11) / 2 - 1, var19
											+ var17 * var8, 90);
							this.sub_1068(var1, var10,
									(this.var_1d77 - var11) / 2 + 1, var19 + 2
											+ var17 * var8, 90);
							this.sub_1068(var1, var10,
									(this.var_1d77 - var11) / 2 + 1, var19
											+ var17 * var8, 90);
							this.sub_1068(var1, var10,
									(this.var_1d77 - var11) / 2 - 1, var19 + 2
											+ var17 * var8, 90);
							var1.setColor(255, 0, 0);
							this.sub_1068(var1, var10,
									(this.var_1d77 - var11) / 2, var19 + 1
											+ var17 * var8, 90);
							if (this.var_92a) {
								var17 = 4;
							}
						}
					} catch (Exception var14) {
						;
					}

					this.sub_95f(var1);
					this.var_2b6c = false;
				} else {
					if (this.var_a17) {
						if (this.var_d15 == null) {
							this.var_11c8 /= 2;
						} else if (this.var_11c8 < this.var_1d77) {
							this.var_11c8 = Math.max(this.var_11c8 + 2,
									this.var_11c8 * 2);
						}

						if (this.var_11c8 < this.var_1d77) {
							var1.drawImage(this.var_de4,
									(this.var_1d77 - this.var_de4.getWidth())
											/ 2 + this.var_11c8, this.var_1d81
											/ 2 - this.var_de4.getHeight(), 0);
							var1.drawImage(this.var_e3e,
									(this.var_1d77 - this.var_e3e.getWidth())
											/ 2 - this.var_11c8,
									this.var_1d81 / 2, 0);
						} else if (this.var_d15 != null) {
							if (this.var_11c8 > this.var_1d77) {
								this.var_e7f = Math.min(
										(this.getheight() - this.var_e7f) / 2
												+ this.var_e7f + 1,
										this.getheight());
							}

							var1.setClip(0, 0, this.var_1d77, this.var_e7f);
							var1.drawImage(this.var_d15, 0, this.var_e7f
									- this.var_d15.getHeight(), 0);
						}
					} else if (this.var_9b6 > 0L) {
						var1.setColor(30, 30, 30);
						long var16 = this.var_9b6
								- (System.currentTimeMillis() - this.var_9dd);
						String var9 = var16 / 60000L + ":"
								+ (var16 % 60000L / 10000L == 0L ? "0" : "")
								+ var16 % 60000L / 1000L;
						var1.drawString(var9, this.var_1d77 - 28, 4, 20);
						if ((this.var_891 == 3 || this.var_891 == 4)
								&& var16 / 1000L % 5L == 0L) {
							var1.setColor(255, 100, 100);
						} else {
							var1.setColor(255, 0, 0);
						}

						var1.drawString(var9, this.var_1d77 - 29, 3, 20);
					} else if (this.var_1462 > 0) {
						String var18 = "" + this.var_1462;
						var1.setColor(0, 0, 0);
						var1.drawString(var18, this.var_1d77 - 5
										- Font.getDefaultFont().stringWidth(var18), 3,
								0);
						var1.setColor(255, 0, 0);
						var1.drawString(var18, this.var_1d77 - 7
										- Font.getDefaultFont().stringWidth(var18), 1,
								0);
					}

					if (this.var_3386 || this.var_3366) {
						this.sub_95f(var1);
					}

				}
			}
		}
	}

	private final void sub_564(Graphics var1) {
		if (this.var_11b.var_9f3) {
			if (!this.var_2b6c || this.var_a72) {
				synchronized (var1) {
					try {
						this.var_2b6c = true;
						if (this.var_1c70 != null) {
							this.var_2c78 = this.var_1c70.getX();
							this.var_2cdb = this.var_1c70.getY();
						}

						if (this.var_3594) {
							this.sub_446(var1);
						} else if (this.var_3051
								&& Thread.currentThread() != this.var_2f3f) {
							this.sub_49a(var1);
						} else if (this.var_35b0) {
							if (this.var_92a) {
								this.sub_2b3(var1);
							} else if (!this.var_37be) {
								this.sub_2f6(var1);
							} else {
								this.sub_352(var1);
							}
						} else if (this.var_844 == 2) {
							this.sub_3a3(var1);
						} else if (!this.var_3124) {
							this.paintLogo(var1);
						} else if (this.var_844 != 0 && this.var_9dd != 0L) {
							this.sub_50d(var1);
						}
					} catch (Exception var5) {
						;
					}
				}

				var1.setColor(255, 255, 255);
				this.var_2b6c = false;
			}
		}
	}

	private int sub_587(int var1) {
		return var1 == 0 ? (System.getProperty("microedition.locale")
				.startsWith("zh") ? 0 : 8) : (var1 == 1 ? 0 : (var1 == 2 ? 8
				: 16));
	}

	public void sub_5d4() {
		Class_202.sub_2c(this);
		Class_2b8.sub_3eb(this.var_11b);
		this.var_498[0] = Font.getFont(32, this.var_11b.sub_dc(401) == 0 ? 0
				: 1, this.sub_587(this.var_11b.sub_dc(400)));
		this.var_498[1] = Font.getFont(64, this.var_11b.sub_dc(403) == 0 ? 0
				: 1, this.sub_587(this.var_11b.sub_dc(402)));
		this.var_498[2] = Font.getFont(64, this.var_11b.sub_dc(405) == 0 ? 0
				: 1, this.sub_587(this.var_11b.sub_dc(404)));
		this.var_498[3] = Font.getFont(64, this.var_11b.sub_dc(407) == 0 ? 0
				: 1, this.sub_587(this.var_11b.sub_dc(406)));
		this.var_498[4] = Font.getFont(64, (this.var_11b.sub_dc(409) == 0 ? 0
				: 1) | 4, this.sub_587(this.var_11b.sub_dc(408)));
		this.var_498[5] = Font.getFont(32, this.var_11b.sub_dc(411) == 0 ? 0
				: 1, this.sub_587(this.var_11b.sub_dc(410)));
		this.var_498[6] = Font.getFont(32, this.var_11b.sub_dc(413) == 0 ? 0
				: 1, this.sub_587(this.var_11b.sub_dc(412)));
		System.gc();
		this.logoImage = this.openImage(2);
		this.var_844 = 5;
		this.var_2acf = this.var_11b.sub_383(250);
		this.var_1d77 = this.getwidth();
		this.var_1d81 = this.getheight();
		this.sub_630();
	}

	public boolean sub_5f7(Class_3a var1, Class_3a var2) {
		return var1.sub_1cc(var2, false);
	}

	private void sub_630() {
		if (this.var_844 != 2) {
			this.var_1250 = null;
			System.gc();
		}

		this.var_2e63 = new Thread(this);
		this.var_2e63.start();
	}

	public void sub_679(CanvasView var1) {
		synchronized (this.var_2fa5) {
			this.var_2e22 = 2;
			this.var_2eb2 = new Thread(var1);
			this.var_2eb2.start();
		}
	}

	public void sub_6a1(CanvasView var1) {
		synchronized (this.var_2fa5) {
			this.var_2e22 = 1;
			this.var_2eb2 = new Thread(var1);
			this.var_2eb2.start();
		}
	}

	public void sub_6b4(CanvasView var1) {
		synchronized (this.var_2fa5) {
			this.var_2e22 = 3;
			this.var_2eb2 = new Thread(var1);
			this.var_2eb2.start();
		}
	}

	public void run() {
		if (Thread.currentThread() == this.var_2eb2) {
			switch (this.var_2e22) {
				case 1:
					this.var_11b.sub_1fb();
					break;
				case 2:
					this.var_11b.sub_5c4();
					break;
				case 3:
					this.sub_c2();
			}

			this.var_2eb2 = null;
		} else {
			if (Thread.currentThread() == this.var_2f12) {
				this.var_11b.sub_47a(this.sub_1092(this.var_2a7b, 0),
						this.var_2f0);
				this.var_2f12 = null;
				this.sub_1035();
			} else if (Thread.currentThread() == this.var_2f3f) {
				while (Thread.currentThread() == this.var_2f3f) {
					try {
						if (!this.var_2f99
								&& System.currentTimeMillis() - this.var_3882 > 4000L) {
							this.var_3882 = System.currentTimeMillis();
							this.var_2f99 = true;
							Thread.currentThread();
							Thread.sleep(100L);
						}
					} catch (Exception var7) {
						;
					}
				}

				this.var_2f3f = null;
				return;
			}

			if (this.var_844 != 2 && this.var_844 != 4) {
				long var1 = 0L;
				long var3 = 0L;
				if (Thread.currentThread() == this.var_2e63) {
					if (this.var_2ffa) {
						return;
					}

					this.var_2ffa = true;

					while (Thread.currentThread() == this.var_2e63) {
						try {
							var1 = System.currentTimeMillis();
							this.sub_1fd(var3);
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
								;
							}
						}
					}

					this.var_2ffa = false;
				}

			} else {
				this.sub_1fd(0L);
				this.repaint();
				this.serviceRepaints();
			}
		}
	}

	public void sub_6c0() {
		try {
			this.sub_1fd(0L);
			this.repaint();
			this.serviceRepaints();
			Thread.currentThread();
			Thread.sleep(5L);
		} catch (Exception var2) {
			;
		}

	}

	public void sub_704(int var1) {
		this.var_3051 = true;
		this.var_34fd = var1;
		this.repaint();
		this.serviceRepaints();
		this.var_3051 = var1 < 100;
		this.var_30a2 = false;
	}

	private void sub_737(int var1) {
		this.sub_ffe();
		this.var_a67 = false;
		this.var_3594 = true;
		System.gc();
		this.var_2976 = this.var_a72 = false;
		this.var_2a41 = 0L;
		this.var_33e = -3;
		this.var_30a = 0;
		if (this.var_2097 == null) {
			this.var_2097 = this.openImage(209);
		}

		this.var_2717 = this.var_2039 = false;
		this.sub_b71(15);
		this.var_1db7 = new Vector();
		this.var_2445 = new Class_24e[50];
		this.var_2455 = new Class_24e[50];
		this.var_2329 = new Class_202[50];
		this.var_238b = new Class_202[50];
		this.var_24b6 = this.var_2510 = this.var_23a7 = this.var_23cd = 0;
		this.var_1c98 = null;
		this.var_1cdd = null;
		this.var_d15 = null;
		this.var_1e1d.clear();
		this.var_3386 = true;
		this.var_3103 = false;
		this.var_a17 = this.var_3463 = this.var_349e = false;
		this.var_e7f = 0;
		this.var_13c4 = null;
		this.var_157f = -1;
		this.sub_1d7(this.var_fa2);
		this.sub_1d7(this.var_fb7);
		this.sub_1d7(this.var_1011);
		this.sub_1d7(this.var_1074);
		this.var_1efb = null;
		this.var_11c8 = this.var_1d77 - 1;
		Class_2b8.var_452 = 0;
		this.var_a89 = 0;
		this.sub_b71(16);
		if (this.var_ced == null) {
			this.var_ced = this.openImage(164);
		}

		System.gc();
		System.gc();
		this.sub_b71(20);
		if (this.var_c21 == null) {
			this.var_c21 = this.openImage(163);
		}

		this.sub_f23();
		System.gc();
		this.sub_b71(23);
		this.var_1efb = new Class_2b8[4];

		try {
			DataInputStream var2 = new DataInputStream(this.var_11b.getClass()
					.getResourceAsStream(
							"/l" + var1 + var_139[this.var_891] + ".lvl"));
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

			this.sub_b71(30);
			this.var_3103 = var4 > 0;
			this.var_1e04 = new Class_308[var4];
			this.var_10c6 = new int[var5];
			this.var_1f77 = new int[var6];
			this.var_25c8 = new int[var7];
			this.var_25f2 = new int[var7];
			this.var_2650 = new int[var7];
			this.var_21de = new int[var8];
			this.var_220e = new int[var4];
			this.var_2230 = new int[var9];
			if (var10 > 0) {
				this.var_13c4 = new int[var10];
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
						this.var_1e04[var4] = new Class_308(var2.readByte(),
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
							this.var_13c4[var10++] = var12;
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

			this.sub_b71(40);
			this.var_2272 = var2.readByte();
			this.sub_b71(45);
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

			this.sub_b71(50);

			int var14;
			try {
				byte var25 = var2.readByte();
				this.sub_b71(55);

				for (var14 = 0; var14 < var25; ++var14) {
					this.var_1e04[var2.readByte()].sub_bb(var2.readByte(),
							var2.readByte());
				}

				this.sub_b71(65);
			} catch (Exception var19) {
				;
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

			this.sub_b71(70);
			this.var_1c70 = new Class_151(this.var_1d1c, this.var_1d37,
					this.var_ced, 15, 15, this.var_1c98);
			this.sub_b71(75);
			this.var_146d = 10000;
			this.var_14a4 = 0;
			if (this.var_13c4 != null) {
				for (var13 = 0; var13 < this.var_13c4.length; ++var13) {
					this.var_146d = Math.min(this.var_146d,
							this.var_13c4[var13] % this.var_1d1c * 15 + 7);
					this.var_14a4 = Math.max(this.var_14a4,
							this.var_13c4[var13] % this.var_1d1c * 15 + 7);
					this.var_1504 = this.var_13c4[var13] / this.var_1d1c * 15
							+ 15;
					this.var_1c70.setCell(this.var_13c4[var13] % this.var_1d1c,
							this.var_13c4[var13] / this.var_1d1c + 1, 80);
				}
			}

			this.sub_b71(80);
			this.var_1efb[0] = new Class_2b8(new Class_3a(
					this.var_f45[this.var_234[0]], 18, 18), new Class_3a(
					this.var_f8e[this.var_234[1]], 18, 23), 50, 20,
					this.var_234[2] * 5 + 20, (this.var_234[3] / 2 + 3)
					* this.var_3e7 / this.var_3fd,
					(this.var_234[4] / 2 + 5) * this.var_3e7 / this.var_3fd * 3
							/ 4, (this.var_234[5] / 3 + 1) * this.var_3e7
					/ this.var_3fd);
			this.var_1f25 = this.var_1efb[0];
			this.sub_b71(83);

			for (var13 = 1; var13 < 4; ++var13) {
				if (this.var_b57[var13] == 4) {
					this.var_b57[var13] = 5;
				}

				if (this.var_92a && this.var_20cb != null) {
					try {
						byte var27 = 4;
						int var26 = this.var_20f8[var13 - 1] / 10 + 2;
						this.var_1efb[var13] = new Class_2b8(
								new Class_3a(
										this.var_f45[this.var_1f6c[this.var_b57[var13] - 1][0] - 1],
										18, 18),
								new Class_3a(
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
						;
					}

					this.var_1efb[var13].sub_236(
							this.var_1f6c[this.var_b57[var13] - 1][6], 2, 3);
					this.var_1efb[var13].var_a1b = 0;
				} else {
					try {
						this.var_1efb[var13] = new Class_2b8(
								new Class_3a(
										this.var_f45[this.var_1f6c[this.var_b57[var13] - 1][0] - 1],
										18, 18),
								new Class_3a(
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
						;
					}

					this.var_1efb[var13].sub_236(
							this.var_1f6c[this.var_b57[var13] - 1][6],
							this.var_1f6c[this.var_b57[var13] - 1][7],
							this.var_1f6c[this.var_b57[var13] - 1][8]);
					this.var_1efb[var13].var_a1b = 0;
				}
			}

			this.var_11d4 = this.var_1d1c * 15
					/ (this.var_c21.getWidth() - this.var_1d77);
			this.var_11fa = this.var_1d37 * 15
					/ (this.var_c21.getHeight() - this.var_1d81);
		} catch (Exception var20) {
			;
		}

		this.sub_b71(90);
		this.var_140d = this.var_1462 = -1;
		this.var_9b6 = -1L;
		boolean var22 = this.var_395[3];
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

		this.sub_b71(100);
		this.sub_7c4();
		this.var_a67 = true;
		this.var_2a41 = 0L;
		this.var_3594 = false;
		this.var_9dd = this.var_151f = System.currentTimeMillis();
		System.gc();
	}

	public void sub_769(int var1, int var2, int var3, boolean var4) {
		if (ArenaMidlet.var_24) {
			super.arena.var_166 = true;
			super.arena.var_b4 = true;
			this.var_3180.sub_53(var1, var2, var3, var4);
		}
	}

	public void sub_7a1(int var1) {
		if (ArenaMidlet.var_24) {
			this.var_3180.sub_1a8(var1);
		}

	}

	public void sub_7c4() {
		if (ArenaMidlet.var_24) {
			this.var_3180.sub_1ba();
		}

	}

	private void sub_819() {
		int var1 = this.var_1d77 / 2 - this.var_1efb[0].var_48e;
		int var2 = Math.max(Math.min(var1, 0), this.var_1d77 - this.var_1d1c
				* 15);
		int var3 = Math.max(
				Math.min(this.var_1d81 / 3 - this.var_1efb[0].var_4dc, 0),
				this.var_1d81 - this.var_1d37 * 15);
		this.var_1c70.setPosition(var2, var3);
	}

	public void sub_83e() {
		if (!this.var_3124 && this.var_2e63 == Thread.currentThread()) {
			this.sub_116d(true);
			this.sub_fea();
			this.var_eb = 20;
			this.var_3559 = false;
			this.sub_b50(0);
			this.sub_e9a();
			this.sub_f23();

			try {
				this.var_3180 = new Class_114(super.arena);
				this.var_3180.start();
			} catch (Exception var23) {
				;
			}

			if (ArenaMidlet.var_24) {
				for (int var1 = 0; var1 < 8; ++var1) {
					;
				}
			}

			System.gc();
			this.sub_b50(10);
			System.gc();
			this.var_c21 = this.openImage(163);
			this.sub_b50(15);
			System.gc();
			this.sub_b50(20);
			this.sub_b50(30);
			this.sub_8c4();
			System.gc();
			this.sub_b50(35);
			this.var_458 = (byte) this.var_11b.sub_dc(417);
			this.var_40e = (byte) this.var_11b.sub_dc(470);
			this.var_44a = (byte) this.var_11b.sub_dc(471);
			this.var_3e7 = (byte) this.var_11b.sub_dc(418);
			this.var_3fd = (byte) this.var_11b.sub_dc(419);
			this.var_fb7 = this.sub_160();
			this.var_1011 = this.sub_160();
			this.var_1074 = this.sub_160();
			this.sub_b50(55);
			System.gc();
			this.var_de4 = this.openImage(166);
			this.var_e3e = this.openImage(169);
			this.sub_b50(60);
			String[] var29 = null;
			int[][] var2 = (int[][]) null;
			RecordStore var3 = null;

			int var5;
			int var7;
			int var8;
			int var9;
			try {
				var3 = RecordStore.openRecordStore(this.var_12d[5], false);
				this.var_733 = 0;
				if (var3 != null) {
					this.var_733 = var3.getNumRecords();
					var29 = new String[this.var_733];
					var2 = new int[this.var_733][9];
					RecordEnumeration var4 = var3
							.enumerateRecords((RecordFilter) null,
									(RecordComparator) null, false);

					for (var5 = 0; var5 < this.var_733; ++var5) {
						byte[] var6 = var4.nextRecord();
						var7 = this.var_733 - var5 - 1;
						var29[var7] = "";

						for (var8 = 1; var8 < 1 + var6[0]; ++var8) {
							var29[var7] = var29[var7] + (char) var6[var8];
						}

						var8 = 0;

						for (var9 = var29[var7].length() + 1; var9 < var6.length; ++var9) {
							var2[var7][var8++] = var6[var9];
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
						;
					}
				}

			}

			byte var32;
			DataInputStream var30;
			try {
				var32 = 0;
				Image[] var33 = null;
				var30 = this.var_11b.sub_1c4(this.var_12d[4]);
				if (var30 != null) {
					var32 = var30.readByte();
					var33 = new Image[var32];

					for (var7 = 0; var7 < var32; ++var7) {
						var33[var7] = Image.createImage(18, 18);

						for (var8 = 0; var8 < 18; ++var8) {
							for (var9 = 0; var9 < 18; ++var9) {
								int var10 = 255 & var30.readByte()
										| (255 & var30.readByte()) << 8
										| (255 & var30.readByte()) << 16;
								if (var10 == 7799014) {
									var10 &= 16777215;
								} else {
									var10 |= -16777216;
								}

								com.siemens.mp.lcdui.Image.setPixelColor(
										var33[var7], var8, var9, var10);
							}
						}
					}
				}

				if (var30 != null) {
					var30.close();
				}

				this.var_f45 = new Image[4 + var32];
				System.gc();

				for (var7 = 0; var7 < 4; ++var7) {
					this.var_f45[var7] = this.openImage(177 + var7);
				}

				for (var7 = 0; var7 < var32; ++var7) {
					this.var_f45[4 + var7] = this.sub_f5e(var33[var7]);
				}
			} catch (Exception var26) {
				;
			}

			this.sub_b50(70);
			this.var_f8e = new Image[4];
			System.gc();

			for (int var31 = 0; var31 < 4; ++var31) {
				this.var_f8e[var31] = this.openImage(16 + var31);
			}

			this.sub_b50(80);
			var30 = this.var_11b.sub_1c4(this.var_12d[0]);
			if (var30 == null) {
				this.sub_a84(true);
			} else {
				try {
					this.var_2f0 = var30.readUTF();

					for (var5 = 0; var5 < this.var_234.length; ++var5) {
						this.var_234[var5] = var30.readInt();
					}

					var30.close();
				} catch (Exception var25) {
					;
				}
			}

			this.sub_b50(90);
			var32 = 0;

			int var34;
			try {
				var30 = new DataInputStream(this.var_11b.getClass()
						.getResourceAsStream("/warrior.dat"));
				var32 = var30.readByte();
				this.var_1f6c = new byte[var32 + this.var_733][9];

				for (var34 = 0; var34 < var32; ++var34) {
					for (var7 = 0; var7 < 9; ++var7) {
						this.var_1f6c[var34][var7] = var30.readByte();
						if (var7 == 1
								&& (this.var_1f6c[var34][var7] < 1 || this.var_1f6c[var34][var7] > 4)) {
							this.var_1f6c[var34][var7] = 1;
						}
					}
				}

				var30.close();
			} catch (Exception var24) {
				;
			}

			if (var2 != null) {
				for (var34 = 0; var34 < this.var_733; ++var34) {
					for (var7 = 0; var7 < 9; ++var7) {
						this.var_1f6c[var34 + var32][var7] = (byte) var2[var34][var7];
					}
				}
			}

			this.sub_b50(95);
			this.var_b2f = new String[this.var_eb + 1 + this.var_733];
			this.var_b2f[0] = this.var_2f0;

			for (var34 = 0; var34 < this.var_eb; ++var34) {
				this.var_b2f[var34 + 1] = this.var_11b.sub_383(500 + var34);
			}

			if (var29 != null) {
				for (var34 = 0; var34 < this.var_733; ++var34) {
					this.var_b2f[this.var_eb + var34 + 1] = var29[var34];
				}
			}

			this.var_eb += this.var_733;
			this.var_d74 = this.openImage(168);
			this.sub_b50(100);
			this.var_11b.sub_371();
			this.sub_107d();
			this.var_3124 = true;
			this.sub_8ab();
			this.sub_ffe();
			System.gc();
			Arena2.sub_3ee();
		}
	}

	public void sub_84f(long var1) {
		super.sub_84f(var1);
		this.var_13a7 = (long) (this.var_134f = this.var_132b = this.var_137d = 0);
		this.var_13a7 = 0L;
		this.var_6e8 = this.var_31be = this.var_31f0 = false;
	}

	public void sub_8ab() {
		if (this.var_270a) {
			this.sub_769(0, 64, 1, true);
		}

		System.gc();
		this.var_844 = 0;
		this.var_a89 = 0;
		this.var_a67 = false;
		this.var_a17 = true;
		this.var_11b.var_af2 = true;
		this.sub_8c4();
		this.var_3463 = false;
		this.var_2e63 = null;
		System.gc();
		this.var_35b0 = false;
		this.var_37be = false;
		this.var_3051 = false;
		this.var_91b = false;
		this.var_92a = false;
		this.var_11b.sub_327(10);
	}

	private void sub_8c4() {
		this.var_29c3 = false;
		this.var_9dd = 0L;
		Class_2b8.var_452 = 0;
		System.gc();
		this.var_11c8 = this.var_1d77;
		this.var_a67 = false;
	}

	public void keyPressed(int var1) {
		if (!this.var_3594 && !this.var_3051) {
			if (this.var_91b && this.var_a17 && this.var_37be) {
				this.sub_8ab();
				this.var_11b.sub_a7();
			} else {
				if (this.var_844 == 2) {
					if (this.var_535 && !this.var_11b.var_e61 && this.var_3246) {
						if (var1 == -4) {
							this.var_3246 = false;
							this.var_11b.sub_25c();
							return;
						}

						if (var1 == -12) {
							this.var_3246 = false;
							this.var_11b.sub_1b1();
							return;
						}

						return;
					}

					if (this.var_4ee) {
						switch (var1) {
							case -12:
								this.var_11b.sub_df();
								return;
							case -4:
								this.var_11b.sub_90();
								return;
						}
					} else {
						switch (var1) {
							case -12:
								this.sub_8ab();
								this.var_11b.sub_a7();
								return;
							case -4:
								this.var_11b.sub_48();
								return;
						}
					}
				} else if (this.var_844 == 1) {
					if (this.var_91b) {
						if (!this.var_a67 && this.var_a17 && !this.var_35b0) {
							this.sub_910(var1);
							return;
						}
					} else if (this.var_92a) {
						if (!this.var_a67 && this.var_a17 && !this.var_35b0) {
							this.sub_910(var1);
							return;
						}

						if (!this.var_a67 && var1 == -4) {
							this.var_11b.sub_2ec();
							return;
						}

						if (!this.var_a67 && var1 == -12) {
							this.var_11b.sub_16b();
							return;
						}
					} else if (this.var_a17) {
						this.sub_910(var1);
						return;
					}

					if (var1 == -4 || var1 == -12) {
						if (this.var_91b) {
							if (!this.var_a67) {
								if (this.var_a17 && !this.var_35b0) {
									return;
								}

								if (var1 == -4) {
									this.var_11b.sub_28d();
								} else {
									this.var_11b.sub_14a();
								}

								return;
							}

							if (this.var_3463) {
								this.var_11b.var_af2 = true;
								this.var_11b.sub_327(10);
								return;
							}

							this.sub_9bf();
							return;
						}

						if (!this.var_92a) {
							if (!this.var_3463) {
								this.sub_9bf();
								return;
							}

							this.var_11b.var_af2 = true;
							this.var_11b.sub_327(10);
							return;
						}

						if (!this.var_a67) {
							this.var_11b.sub_2ec();
							return;
						}

						if (this.var_2de8) {
							this.var_11b.var_af2 = true;
							this.var_11b.sub_327(10);
							return;
						}

						if (!this.var_3463) {
							this.sub_9bf();
							return;
						}
					}
				}

				this.sub_910(var1);
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
			this.sub_6c0();
		}

	}

	public void sub_93a(long var1) {
		super.sub_93a(var1);

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
			if ((this.var_13a7 & 2L) > 0L) {
				this.var_134f = 3;
				this.var_132b = 1;
			}

			if ((this.var_13a7 & 32L) > 0L) {
				this.var_137d = 5;
			}

			if ((this.var_13a7 & 8L) > 0L) {
				this.var_134f = 3;
				this.var_132b = 2;
			}

			if ((this.var_13a7 & 128L) > 0L) {
				this.var_134f = 4;
				this.var_132b = 1;
				this.var_137d = 5;
			}

			if ((this.var_13a7 & 512L) > 0L) {
				this.var_134f = 4;
				this.var_132b = 2;
				this.var_137d = 5;
			}

			if ((this.var_13a7 & 65536L) > 0L) {
				this.var_132b = 1;
			}

			if ((this.var_13a7 & 1048576L) > 0L) {
				this.var_137d = 5;
			}

			if ((this.var_13a7 & 131072L) > 0L) {
				this.var_132b = 2;
			}

			if ((this.var_13a7 & 262144L) > 0L || this.var_31be) {
				this.var_134f = 3;
				this.var_31be = false;
			}

			if ((this.var_13a7 & 524288L) > 0L || this.var_31f0) {
				this.var_134f = 4;
				this.var_31f0 = false;
			}

			if ((this.var_13a7 & 1L) > 0L) {
				this.var_137d = 6;
			}
		} catch (Exception var10) {
			;
		}

		if (this.var_844 == 2 || this.var_844 == 4) {
			this.sub_6c0();
		}

		if (this.var_a89 > 10 && this.var_d15 == null) {
			if ((this.var_91b || this.var_92a) && this.var_91b) {
				this.var_3309 = true;
			}

			this.var_a89 = 0;
			this.var_326a = new int[this.var_1efb.length];

			int var3;
			for (var3 = 0; var3 < this.var_326a.length; this.var_326a[var3] = var3++) {
				;
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
					if (this.var_1efb[this.var_326a[var3]] == this.var_1f25) {
						this.sub_b1e(Math.max(0, 3 - var3 * 2));
					}
				}
			} else if (this.var_91b) {
				if (this.var_3612 > 1) {
					this.sub_b1e(1);
				}

				if (this.var_3657.length == 4) {
					for (var3 = 0; var3 < this.var_326a.length; ++var3) {
						if (this.var_1efb[this.var_326a[var3]] == this.var_1f25) {
							this.sub_b1e(Math.max(0, 15 - var3 * 5));
						}
					}
				}
			}

			try {
				System.gc();
				this.var_d15 = Image.createImage(this.var_1d77,
						this.getheight());
				Graphics var11 = this.var_d15.getGraphics();
				var11.setClip(0, 0, this.var_1d77, this.var_1d81);
				var11.drawImage(this.var_d74, 0, 0, 0);
				var11.drawImage(this.var_3133,
						(this.var_1d77 - this.var_3133.getWidth()) / 2, 15, 0);
				var11.setFont(this.var_498[1]);
				if (this.var_891 != 5) {
					for (var4 = 0; var4 < this.var_326a.length; ++var4) {
						if (this.var_b57[this.var_326a[var4]] == 0) {
							var11.setColor(255, 50, 50);
						} else {
							var11.setColor(230, 230, 230);
						}

						var11.setClip(8, 28 + var4 * 20, 18, 18);

						try {
							var5 = this.var_326a[var4];
							var11.drawImage(
									var5 != 0 ? this.var_f45[this.var_1f6c[this.var_b57[var5] - 1][0] - 1]
											: this.var_f45[this.var_234[0]], 8,
									28 + var4 * 20, 0);
						} catch (Exception var8) {
							;
						}

						this.sub_1068(
								var11,
								var4
										+ 1
										+ ". "
										+ this.var_b2f[this.var_b57[this.var_326a[var4]]],
								25, 31 + var4 * 20, 70);
						this.sub_1068(
								var11,
								(this.var_1efb[this.var_326a[var4]].var_a1b < 10 ? "0"
										: "")
										+ this.var_1efb[this.var_326a[var4]].var_a1b,
								this.var_1d77 - 20, 31 + var4 * 20, 15);
					}
				} else {
					var4 = this.var_1f25.var_a1b
							* (this.var_20f8 != null ? this.var_20f8[0] / 5 + 5
							: 5) / 5;
					var11.setColor(0, 0, 0);
					var11.drawString(this.sub_446(295) + " " + var4, 19, 31, 0);
					var11.setColor(200, 200, 200);
					var11.drawString(this.sub_446(295) + " " + var4, 20, 32, 0);
					this.sub_c2e();
					if (this.var_2a7b != null) {
						var5 = this.sub_1092(this.var_2a7b, 0);
					} else {
						var5 = 0;
					}

					if (var4 > var5) {
						this.sub_c65(var4, this.var_20cb, this.var_20f8);
					}

					RecordStore var6 = RecordStore.openRecordStore(
							this.var_12d[7], true);
					RecordEnumeration var7 = var6
							.enumerateRecords((RecordFilter) null,
									(RecordComparator) null, false);
					if (var7.numRecords() > 0) {
						var11.setColor(0, 0, 0);
						this.sub_1068(var11, this.sub_446(1) + ": " + var5, 19,
								49, this.var_1d77 - 36);
						if (var5 >= this.var_1f25.var_a1b) {
							var11.setColor(250, 150, 150);
						} else {
							var11.setColor(150, 250, 150);
						}

						this.sub_1068(var11, this.sub_446(1) + ": " + var5, 20,
								50, this.var_1d77 - 36);
					} else {
						var11.setColor(0, 0, 0);
						var11.setFont(this.var_498[0]);
						var11.drawString(super.arena.sub_383(287), 15, 44, 0);
						var11.setColor(200, 200, 200);
						var11.drawString(super.arena.sub_383(287), 16, 45, 0);
					}

					var6.closeRecordStore();
				}
			} catch (Exception var9) {
				;
			}
		} else if (this.var_a17 && this.var_d15 != null && this.var_a89 > 10) {
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

	private final void sub_95f(Graphics var1) {
		var1.setClip(0, this.var_1d81, this.var_1d77, 20);
		var1.drawImage(this.var_10fc, 0, this.var_1d81, 0);
		int var2 = this.var_1174.getWidth() / 3 * this.var_1f25.var_b81
				/ this.var_1f25.var_af7;
		int var3 = var2 * 3;
		if (var2 > 0) {
			var1.setClip(var_3417, this.var_1d81 + 3, var3,
					this.var_1174.getHeight());
			var1.drawImage(this.var_1174, var_3417, this.var_1d81 + 3, 0);
		}

		this.var_3366 = var2 != this.var_339d;
		if (var2 < this.var_339d) {
			this.var_33b6 = (this.var_339d - var2) * 3;
			var1.setClip(var_3417 + var3, this.var_1d81 + 3, this.var_33b6,
					this.var_1174.getHeight());
			var1.drawImage(this.var_11ab, var_3417 + var3, this.var_1d81 + 3, 0);
			--this.var_339d;
		} else {
			this.var_339d = var2;
		}

		this.var_3386 = false;
	}

	public void sub_9bf() {
		if (!this.var_3463) {
			this.var_3463 = true;
			this.var_11b.sub_327(10);
			this.var_3486 = System.currentTimeMillis();

			try {
				Thread.sleep(10L);
			} catch (Exception var2) {
				;
			}

			this.var_2e63 = null;
			this.var_2a41 = 0L;
			if (this.var_270a) {
				this.sub_769(0, 67, 10, true);
			}

		}
	}

	public void showNotify() {
		if (!this.var_11b.var_9d2) {
			super.showNotify();
		}
	}

	public void hideNotify() {
		if (!this.var_11b.var_9d2) {
			super.hideNotify();
			if (this.var_844 == 1 && this.var_29c3 && this.var_2a41 > 6L
					&& !this.var_3463) {
				this.sub_9bf();
			}

		}
	}

	public void sub_9e4() {
		this.var_2de8 = false;
		System.gc();
		this.var_9dd += System.currentTimeMillis() - this.var_3486;
		this.var_3463 = false;
		this.var_11b.var_af2 = true;
		this.var_2a41 = 0L;
		this.var_3386 = this.var_3601 = true;
		this.var_35b0 = false;
		this.var_37be = false;
		System.gc();
		this.var_11b.sub_327(13);
		this.sub_7c4();
		if (!this.var_26b0 && this.var_270a) {
			;
		}

	}

	public void sub_a24(int var1, int var2, int var3) {
		if (this.var_23cd == 0) {
			this.var_2329[this.var_23a7] = new Class_202(var1, var2, var3, this);
		} else {
			this.var_2329[this.var_23a7] = this.var_238b[--this.var_23cd];
			this.var_238b[this.var_23cd] = null;
			this.var_2329[this.var_23a7].sub_6f(var1, var2, var3, this);
		}

		++this.var_23a7;
	}

	public void sub_a65() {
		try {
			this.var_b2f[0] = this.var_2f0;
			ByteArrayOutputStream var1 = new ByteArrayOutputStream();
			DataOutputStream var2 = new DataOutputStream(var1);
			var2.writeUTF(this.var_2f0);

			for (int var3 = 0; var3 < this.var_234.length; ++var3) {
				var2.writeInt(this.var_234[var3]);
			}

			this.var_11b.sub_201(this.var_12d[0], var1.toByteArray());
			var2.close();
			if (this.var_4c6) {
				var1 = new ByteArrayOutputStream();
				DataOutputStream var5 = new DataOutputStream(var1);
				var5.writeByte(0);
				this.var_11b.sub_201(this.var_12d[1], var1.toByteArray());
				var5.close();
			}
		} catch (Exception var4) {
			;
		}

	}

	public void sub_a84(boolean var1) {
		try {
			this.var_2f0 = this.sub_446(525);

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
				var3.writeUTF(this.var_2f0);

				for (int var4 = 0; var4 < this.var_234.length; ++var4) {
					var3.writeInt(this.var_234[var4]);
				}

				this.var_11b.sub_201(this.var_12d[0], var6.toByteArray());
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
		DataInputStream var1 = this.var_11b.sub_1c4(this.var_12d[0]);
		if (var1 == null) {
			this.sub_a84(true);
		} else {
			try {
				this.var_2f0 = var1.readUTF();

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

	private void sub_b50(int var1) {
		this.var_3559 = true;
		this.var_34fd = var1;
		this.repaint();
		this.serviceRepaints();
	}

	private void sub_b71(int var1) {
		this.sub_ffe();
		this.var_38d3 = true;
		this.var_34fd = var1;
		this.repaint();
		this.serviceRepaints();
	}

	private void sub_bd4() {
		this.var_30a2 = true;
		if (this.var_3601) {
			this.var_3601 = false;
		} else {
			this.sub_704(10);
			this.var_37be = false;
			if (this.var_35e9) {
				this.sub_704(20);
				this.var_3612 = 1;
				this.var_3706 = this.var_891;
				this.var_3657 = new int[this.var_eb + 1];
				this.var_3670 = new int[this.var_eb + 1];
				this.var_36a6 = new String[this.var_b2f.length];
				this.sub_704(50);

				for (int var1 = 0; var1 < this.var_3657.length; ++var1) {
					this.var_3657[var1] = var1;
					this.var_36a6[var1] = this.var_b2f[var1];
				}

				this.sub_704(80);
				this.sub_d6b();
			} else {
				this.sub_704(20);
				this.sub_d9b();
				this.sub_704(70);
			}

			this.sub_704(100);
			this.sub_1035();
			this.var_35b0 = true;
		}
	}

	private void sub_c11() {
		this.var_2e63 = null;
		System.gc();
		this.sub_704(20);
		this.var_30a2 = true;
		this.var_c8a = this.openImage(210);
		this.sub_704(50);
		if (this.var_3601) {
			this.var_3601 = false;
		} else {
			this.sub_c2e();
			this.sub_704(80);
			this.sub_704(100);
			this.sub_1035();
			this.var_35b0 = true;
		}
	}

	public void sub_c2e() {
		RecordStore var1 = null;

		try {
			var1 = RecordStore.openRecordStore(this.var_12d[7], true);
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
			var19 = RecordStore.openRecordStore(this.var_12d[7], true);
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
			if (Thread.currentThread() == this.var_2e63) {
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
							var11 = Class_2b8.sub_62(this.var_3657.length);
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
							var11 = Class_2b8.sub_62(this.var_3657.length);
						} while (var1[var11]);

						var4[var5] = var11;
						var1[var11] = true;
					}

					int[] var12 = new int[4];

					int var6;
					try {
						for (var6 = 0; var6 < 4; ++var6) {
							var12[var6] = Class_2b8.sub_62(15)
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

			this.var_11b.sub_201(this.var_12d[1], var1.toByteArray());
			var2.close();
		} catch (Exception var4) {
			;
		}

	}

	private void sub_d9b() {
		try {
			DataInputStream var1 = this.var_11b.sub_1c4(this.var_12d[1]);
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
			this.var_36a6[0] = this.var_2f0;

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
			DataInputStream var2 = this.var_11b.sub_1c4(this.var_12d[1]);
			var1 = var2 != null && var2.readByte() == 1;
			var2.close();
		} catch (Exception var3) {
			;
		}

		return var1;
	}

	private void sub_e41() {
		this.var_395 = new boolean[5];

		for (int var1 = 0; var1 < 3; ++var1) {
			this.var_395[var1] = true;
		}

		this.sub_ec7();
	}

	public void sub_e9a() {
		try {
			String var1 = this.var_11b.sub_13f(this.var_12d[2]);
			if (var1 == null) {
				this.sub_e41();
			} else {
				for (int var2 = 0; var2 < 5; ++var2) {
					char var3 = var1.charAt(var2);
					if (var3 != 49 && var3 != 48) {
						this.sub_e41();
						break;
					}

					this.var_395[var2] = var3 == 49;
				}
			}
		} catch (Exception var4) {
			;
		}

		this.sub_116d(this.var_395[1]);
	}

	public void sub_ec7() {
		try {
			StringBuffer var1 = new StringBuffer();

			for (int var2 = 0; var2 < 5; ++var2) {
				if (this.var_395[var2]) {
					var1.append('1');
				} else {
					var1.append('0');
				}
			}

			this.var_11b.sub_f0(this.var_12d[2], var1.toString(), true);
		} catch (Exception var3) {
			;
		}

		this.sub_f23();
	}

	private void sub_f23() {
		this.var_26b0 = !this.var_395[4] && this.var_395[0];
		this.var_270a = this.var_395[4] || this.var_395[0];
	}

	public void sub_f51(Image var1) {
		Image[] var2 = new Image[this.var_f45.length + 1];

		int var3;
		for (var3 = 0; var3 < this.var_f45.length; ++var3) {
			var2[var3] = this.var_f45[var3];
		}

		var2[var2.length - 1] = this.sub_f5e(var1);
		this.var_f45 = var2;
		var3 = var1.getWidth();
		int var4 = var1.getHeight();
		int[] var5 = new int[var3 * var4];
		byte[] var6 = new byte[var5.length * 3];
		var1.getRGB(var5, 0, var3, 0, 0, var3, var4);

		for (int var7 = 0; var7 < var3; ++var7) {
			for (int var8 = 0; var8 < var4; ++var8) {
				var5[var7 * var3 + var8] = com.siemens.mp.lcdui.Image
						.getPixelColor(var1, var7, var8);
				if (var5[var7 * var3 + var8] >> 24 == 0) {
					var5[var7 * var3 + var8] = 7799014;
				}

				var6[(var7 * var3 + var8) * 3] = (byte) (var5[var7 * var3
						+ var8] & 255);
				var6[(var7 * var3 + var8) * 3 + 1] = (byte) (var5[var7 * var3
						+ var8] >> 8 & 255);
				var6[(var7 * var3 + var8) * 3 + 2] = (byte) (var5[var7 * var3
						+ var8] >> 16 & 255);
			}
		}

		DataInputStream var15 = this.var_11b.sub_1c4(this.var_12d[4]);
		boolean var16 = var15 == null;
		byte[] var9 = null;
		byte var10 = 0;

		try {
			if (!var16) {
				var10 = var15.readByte();
				var9 = new byte[var6.length * var10];
				var15.read(var9);
				var15.close();
			}
		} catch (Exception var14) {
			;
		}

		try {
			ByteArrayOutputStream var11 = new ByteArrayOutputStream();
			DataOutputStream var12 = new DataOutputStream(var11);
			if (var16) {
				var12.writeByte(1);
			} else {
				var12.writeByte(var10 + 1);
				var12.write(var9);
			}

			var12.write(var6);
			this.var_11b.sub_201(this.var_12d[4], var11.toByteArray());
			var12.close();
		} catch (Exception var13) {
			;
		}

	}

	private Image sub_f5e(Image var1) {
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
				com.siemens.mp.lcdui.Image
						.setPixelColor(var2, var4, var3, var5);
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
		this.sub_704(15);

		try {
			int var9;
			int var12;
			if (var15 >= 4) {
				this.var_11b.var_de1 = 18;
				int[] var8 = new int[this.var_11b.var_de1
						* this.var_11b.var_de1];

				for (var9 = 0; var9 < this.var_11b.var_de1; ++var9) {
					for (int var10 = 0; var10 < this.var_11b.var_de1; ++var10) {
						if (var10 < this.var_11b.var_de1 - 1) {
							byte var11 = var1[var4++];
							var12 = -16777216;
							var12 = var12 | (var11 >> 5 & 255) << 5 << 16
									| (var11 >> 2 & 255) << 5 << 8
									| (var11 & 255) << 5;
							if ((var12 & '\uff00') == '\ue300') {
								var12 &= 16777215;
							}

							var8[var10 + var9 * this.var_11b.var_de1] = var12;
						} else {
							var8[var10 + var9 * this.var_11b.var_de1] = 7799014;
						}
					}
				}

				this.sub_704(25);
				System.gc();
				Image var17 = Image.createRGBImage(var8, this.var_11b.var_de1,
						this.var_11b.var_de1, true);
				this.sub_704(30);
				this.sub_f51(var17);
				this.sub_704(40);
				Image var18 = Image.createImage(20, 20);
				this.sub_704(50);
				var18.getGraphics().drawImage(var17, 4, 4, 0);
				this.sub_f99(var18);
				this.sub_704(70);
				var7 = (byte) this.var_f45.length;
			}

			byte[] var16 = new byte[10 + var2.length()];

			for (var9 = 0; var9 < 7 + var2.length(); ++var9) {
				var16[var9] = var1[var9];
			}

			++var16[1 + var2.length() + 1];
			this.sub_704(80);
			var16[1 + var2.length()] = var7;
			var16[7 + var2.length()] = (byte) (60 + Class_2b8.sub_62(80));
			var16[8 + var2.length()] = (byte) Class_2b8.sub_62(2);
			var16[9 + var2.length()] = (byte) (Class_2b8.sub_62(2) + 1);
			RecordStore var19 = RecordStore.openRecordStore(this.var_12d[5],
					true);
			if (var3) {
				var19.setRecord(this.var_11b.var_bd9, var16, 0, var16.length);
			} else {
				var19.addRecord(var16, 0, var16.length);
			}

			this.sub_704(85);
			this.var_733 = var19.getNumRecords();
			var19.closeRecordStore();
			this.sub_704(90);
			String[] var20 = new String[this.var_b2f.length + 1];

			for (int var21 = 0; var21 < this.var_b2f.length; ++var21) {
				var20[var21] = this.var_b2f[var21];
			}

			var20[this.var_b2f.length] = var2;
			this.var_b2f = var20;
			++this.var_eb;
			this.sub_704(95);
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
			;
		}

	}

	public void sub_f99(Image var1) {
		int var2 = var1.getWidth();
		int var3 = var1.getHeight();
		if (this.var_28d9 == null) {
			this.var_28d9 = new Image[0];
		}

		Image[] var4 = new Image[this.var_28d9.length + 1];
		System.arraycopy(this.var_28d9, 0, var4, 0, this.var_28d9.length);
		var4[this.var_28d9.length] = var1;
		this.var_28d9 = var4;
		var4 = null;
		int[] var5 = new int[var2 * var3];
		byte[] var6 = new byte[var2 * var3 * 3];
		int var7 = var2;
		int var8 = var3;

		for (int var9 = 0; var9 < var7; ++var9) {
			for (int var10 = 0; var10 < var8; ++var10) {
				var5[var9 * var7 + var10] = com.siemens.mp.lcdui.Image
						.getPixelColor(var1, var9, var10);
				if (var5[var9 * var7 + var10] >> 24 == 0) {
					var5[var9 * var7 + var10] = 7799014;
				}

				var6[(var9 * var7 + var10) * 3] = (byte) (var5[var9 * var7
						+ var10] & 255);
				var6[(var9 * var7 + var10) * 3 + 1] = (byte) (var5[var9 * var7
						+ var10] >> 8 & 255);
				var6[(var9 * var7 + var10) * 3 + 2] = (byte) (var5[var9 * var7
						+ var10] >> 16 & 255);
			}
		}

		try {
			RecordStore var12 = RecordStore.openRecordStore(this.var_12d[6],
					true);
			var12.addRecord(var6, 0, var6.length);
			var12.closeRecordStore();
		} catch (Exception var11) {
			;
		}

	}

	private int sub_fea() {
		try {
			RecordStore var2 = RecordStore.openRecordStore(this.var_12d[6],
					true);
			RecordEnumeration var3 = var2.enumerateRecords((RecordFilter) null,
					(RecordComparator) null, false);
			var3.reset();
			int var11 = var2.getNumRecords();
			if (var11 <= 0) {
				return 0;
			} else {
				this.var_28d9 = new Image[var11];

				for (int var4 = 0; var4 < var11; ++var4) {
					this.var_28d9[var11 - var4 - 1] = Image.createImage(
							var_3870, var_3870);
					byte[] var5 = var3.nextRecord();
					int var6 = 0;

					for (int var7 = 0; var7 < var_3870; ++var7) {
						for (int var8 = 0; var8 < var_3870; ++var8) {
							int var9 = 255 & var5[var6++]
									| (255 & var5[var6++]) << 8
									| (255 & var5[var6++]) << 16;
							if (var9 == 7799014) {
								var9 &= 16777215;
							} else {
								var9 |= -16777216;
							}

							com.siemens.mp.lcdui.Image.setPixelColor(
									this.var_28d9[var11 - var4 - 1], var7,
									var8, var9);
						}
					}
				}

				var2.closeRecordStore();
				return var11;
			}
		} catch (Exception var10) {
			return 0;
		}
	}

	public void sub_ffe() {
		this.var_2f3f = null;
	}

	public void sub_1035() {
		if (this.var_2f3f == null) {
			this.var_2f3f = new Thread(this);
			this.var_2f3f.start();
		}

	}

	private final void sub_1068(Graphics var1, String var2, int var3, int var4,
								int var5) {
		int var6 = var1.getClipX();
		int var7 = var1.getClipY();
		int var8 = var1.getClipWidth();
		int var9 = var1.getClipHeight();
		int var10 = var1.getFont().stringWidth(var2);
		if (this.var_2f99) {
			this.var_2f99 = false;
			this.var_38d3 = !this.var_38d3;
		}

		var1.setClip(var3, var4, var5, 20);
		if (!this.var_38d3 && var10 > var5) {
			var1.drawString(var2, var3 + var5, var4, 24);
		} else {
			var1.drawString(var2, var3, var4, 20);
		}

		var1.setClip(var6, var7, var8, var9);
	}

	private final void sub_107d() {
		this.sub_704(0);
		if (this.var_2097 == null) {
			this.var_2097 = this.openImage(209);
		}

		this.sub_704(5);
		if (this.var_ced == null) {
			this.var_ced = this.openImage(164);
		}

		this.sub_704(25);
		this.var_1139 = this.openImage(205);
		this.var_1174 = this.openImage(203);
		this.var_11ab = this.openImage(204);
		this.sub_704(35);
		if (this.var_1c8d == null) {
			Image var1 = this.openImage(211);
			this.sub_704(45);

			try {
				byte[] var2 = new byte[16];

				for (int var3 = 0; var3 < 16; ++var3) {
					var2[var3] = 1;
				}

				this.var_1c8d = new Class_151(4, 4, var1, var1.getWidth(),
						var1.getHeight(), var2);
				var1 = null;
				System.gc();
			} catch (Exception var6) {
				;
			}
		}

		this.sub_704(50);
		this.var_22ea = new Class_3a[5];
		System.gc();

		int var7;
		for (var7 = 0; var7 < this.var_22ea.length; ++var7) {
			this.var_22ea[var7] = new Class_3a(this.openImage(198 + var7),
					this.var_11b.sub_dc(223 + var7 * 2),
					this.var_11b.sub_dc(224 + var7 * 2));
		}

		this.var_23fa = new int[5];
		this.var_2415 = new int[5];
		this.var_2415[0] = this.var_2415[4] = -this.var_22ea[0].getHeight() / 2;
		this.var_23fa[0] = this.var_23fa[4] = -this.var_22ea[0].getWidth() / 2;
		this.var_2415[2] = this.var_2415[1] = this.var_22ea[2].getHeight();
		this.var_2415[2] -= 7;
		this.var_23fa[2] = this.var_23fa[1] = this.var_22ea[2].getWidth() / 2;
		this.var_2415[3] = this.var_22ea[3].getHeight();
		this.var_23fa[3] = this.var_22ea[3].getWidth() / 2;
		this.sub_704(55);
		this.sub_704(60);
		this.var_1dec = new Class_3a[3];
		System.gc();

		for (var7 = 0; var7 < this.var_1dec.length; ++var7) {
			this.var_1dec[var7] = new Class_3a(this.openImage(192 + var7), 17,
					17);
		}

		this.sub_704(70);

		try {
			this.var_1ff8 = new Image[Class_24e.var_19.length];
			this.var_ec4 = new int[Class_24e.var_19.length];
			this.var_ef3 = new int[Class_24e.var_19.length];
			System.gc();

			for (var7 = 0; var7 < Class_24e.var_19.length; ++var7) {
				this.var_1ff8[var7] = this.openImage(48 + var7);
				this.var_ec4[var7] = this.var_1ff8[var7].getWidth();
				this.var_ef3[var7] = this.var_1ff8[var7].getHeight() / 2;
			}
		} catch (Exception var5) {
			;
		}

		this.sub_704(80);
		this.var_254a = new Class_3a[Class_24e.var_19.length];

		for (var7 = 0; var7 < this.var_254a.length; ++var7) {
			try {
				System.gc();
				this.var_254a[var7] = new Class_3a(this.openImage(224 + var7),
						this.var_11b.sub_dc(250 + var7 * 2),
						this.var_11b.sub_dc(251 + var7 * 2));
			} catch (Exception var4) {
				;
			}
		}

		this.sub_704(85);
		this.var_3133 = this.openImage(167);
		this.sub_704(95);
		System.gc();
		if (this.var_10fc == null) {
			this.var_10fc = this.openImage(165);
		}

		this.sub_704(100);
	}

	private int sub_1092(byte[] var1, int var2) {
		return 255 & var1[0 + var2] | (255 & var1[1 + var2]) << 8
				| (255 & var1[2 + var2]) << 16;
	}

	private byte[] sub_10eb(int var1) {
		byte[] var2 = new byte[]{(byte) (var1 & 255),
				(byte) (var1 >> 8 & 255), (byte) (var1 >> 16 & 255)};
		return var2;
	}

	@Override
	public void draw(Graphics var1) {
		this.sub_564(var1);
	}

	public int sub_1148() {
		return 0;
	}

	public void sub_116d(boolean var1) {
		this.sub_1e0(0, var1);
	}

	@Override
	public Image openImage(int var1) {
		try {
			return super.openImage(var1);
		} catch (IOException var3) {
			return null;
		}
	}

	public void sub_11d4(int var1) {
		super.sub_196((long) var1);
	}

}