package com.elkware.midp.games.colorng;

import com.elkware.midp.games.colorng.arena.high.ArenaMidlet;

import java.io.IOException;
import java.util.Vector;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public abstract class CanvasView2 extends CanvasView1 implements Runnable, CommandListener {

	private static final int[] var_2b = new int[]{1, 2, 4, 8, 16, 32, 64,
			128, 256, 512, 1024, 2048, 4096, 8192, 16384, '\u8000', 65536,
			131072, 262144, 524288, 1048576, 2097152, 4194304, 8388608,
			16777216};
	public Thread var_35;
	private int var_6b = 0;
	public static boolean var_d3;
	private boolean var_e9;
	public byte[] var_120;
	public boolean var_1df;
	Thread var_207;
	public Image var_260 = null;
	public Image var_28e;
	public Image var_2ca;
	public int var_305 = 0;
	boolean var_34f;
	int var_368 = 7;
	int var_3ef = 7;
	boolean var_401;
	boolean var_454 = true;
	public int var_4a2 = 16777215;
	public int var_4d3 = this.getwidth() / 3;
	public int var_50d = this.getheight() - 20;
	public int var_543 = this.getwidth() / 3;
	public int var_556 = 6;
	public int var_56f = 0;
	int var_5a0 = this.getwidth() - 10;
	int var_5d6 = this.getheight() - 7;
	boolean var_635;
	private int[] var_66e = new int[]{48, 49, 50, 51, 52, 53, 54, 55, 56, 57,
			42, 35, 0, 0, 0, 0, -3, -4, -1, -2, -5, -5, 42, 48, 35};
	private int[] var_67b = new int[]{8388608, 327680, 262144, 393216, 65536,
			1048576, 131072, 589824, 524288, 655360, 4194304, 16777216};
	Command var_6a8;
	public boolean var_6d4;
	public int var_6fb = -1;
	public int var_75e = -1;
	public int var_7c0 = -1;
	public int var_813 = -1;
	public int var_831;
	String[] var_87b;
	boolean[] var_8b4;
	private Vector var_8c8 = new Vector();
	protected long var_8e2;
	protected long var_933;
	private boolean var_970;

	public CanvasView2(ArenaMidlet var1) {
		super(var1);
		int var2;
		if ((var2 = this.sub_462(5036)) != -1) {
			this.var_4d3 = var2;
		}

		if ((var2 = this.sub_462(5037)) != -1) {
			this.var_50d = var2;
		}

		if ((var2 = this.sub_462(5038)) != -1) {
			this.var_543 = var2;
		}

		if ((var2 = this.sub_462(5039)) != -1) {
			this.var_556 = var2;
		}

		int var3 = this.sub_462(5040);
		int var4 = this.sub_462(5041);
		int var5 = this.sub_462(5042);
		if (var3 != -1) {
			this.var_4a2 = var3 << 16 | var4 << 8 | var5;
		}

		var3 = this.sub_462(5043);
		var4 = this.sub_462(5044);
		var5 = this.sub_462(5045);
		if (var3 != -1) {
			this.var_56f = var3 << 16 | var4 << 8 | var5;
		}

		if ((var2 = this.sub_462(5046)) != -1) {
			this.var_454 = var2 == 1;
		}

		Display.getDisplay(var1);
		this.var_120 = var1.sub_499(5);
		if (this.var_120 == null) {
			try {
				throw new IOException("Sine table (ID 5) is missing!");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			this.var_e9 = this.sub_462(5000) != 0;

			int var6;
			int var7;
			for (var7 = 0; var7 < this.var_67b.length; ++var7) {
				if ((var6 = this.sub_462(5001 + var7)) != -1) {
					this.var_67b[var7] = var6 << 16;
				}
			}

			for (var7 = 12; var7 < this.var_66e.length; ++var7) {
				if ((var6 = this.sub_462(5001 + var7)) != -1) {
					this.var_66e[var7] = var6;
				}
			}

			this.var_66e[14] = this.sub_462(5015);
			this.var_66e[15] = this.sub_462(5016);
			this.var_1df = var1.sub_dc(5032) == 1;
			if (var1.sub_dc(5051) == 1) {
				this.var_6a8 = new Command(var1.getStr(43), 4, 0);
				this.setCommandListener(this);
			}

			this.sub_15();
		}
	}

	int sub_15() {
		if ((this.var_831 = super.arena.sub_dc(5030)) == -1) {
			this.var_831 = 15;
		}

		this.var_6d4 = this.var_831 != 0;
		int var1 = 0;
		if ((this.var_831 & 1) != 0) {
			this.var_6fb = var1++;
		}

		if (this.var_1df && (this.var_831 & 2) != 0) {
			this.var_75e = var1++;
		}

		if ((this.var_831 & 4) != 0) {
			this.var_7c0 = var1++;
		}

		if ((this.var_831 & 8) != 0) {
			this.var_813 = var1++;
		}

		if (this.var_87b != null) {
			var1 += this.var_87b.length;
		}

		this.var_8b4 = new boolean[var1];
		return var1;
	}

	public boolean[] sub_6e() {
		return this.var_8b4;
	}

	public void sub_e9a() {
		String var1 = super.arena.sub_13f("cfg");
		if (var1 == null) {
			var1 = "1111";
		}

		for (int var2 = 0; var2 < Math.min(var1.length(), this.var_8b4.length); ++var2) {
			this.var_8b4[var2] = var1.charAt(var2) == 49;
		}

		this.sub_af(this.var_8b4);
	}

	public void sub_ec7() {
		String var1 = "";

		for (boolean aVar_8b4 : this.var_8b4) {
			var1 = var1 + (aVar_8b4 ? '1' : '0');
		}

		super.arena.sub_f0("cfg", var1, true);
	}

	public void sub_af(boolean[] var1) {
		if (this.var_6fb != -1) {
			this.sub_2e9(this.var_6fb, var1[this.var_6fb]);
		}

		if (this.var_75e != -1) {
			this.sub_2e9(this.var_75e, var1[this.var_75e]);
		}

		if (this.var_7c0 != -1) {
			this.sub_2e9(this.var_7c0, var1[this.var_7c0]);
		}

		if (this.var_813 != -1) {
			this.sub_2e9(this.var_813, var1[this.var_813]);
		}
	}

	public void sub_2e9(int var1, boolean var2) {
		this.var_8b4[var1] = var2;
		if (var1 == this.var_6fb) {
			super.arena.var_b4 = var2;
			if (!this.var_1df) {
				super.arena.var_166 = var2;
				if (!var2) {
					this.sub_7a1(0);
				}
			}

			if (!var2) {
				this.sub_7a1(1);
			}
		} else if (var1 == this.var_75e) {
			super.arena.var_166 = var2;
			if (!var2) {
				this.sub_7a1(0);
			}
		} else if (var1 == this.var_7c0) {
			super.arena.var_e8 = var2;
		} else if (var1 == this.var_813) {
			super.arena.var_105 = var2;
			this.sub_1e0(0, var2);
		}

	}

	public boolean sub_100(int var1) {
		return this.var_8b4[var1];
	}

	public abstract void sub_7a1(int var1);

	public Image openImage(int var1) throws IOException {
		try {
			if (var1 == 2) {
				byte[] var3 = arena.sub_499(var1);
				if (var3 != null) {
					return Image.createImage(var3, 0, var3.length);
				}
			} else {
				return Image.createImage("_" + Integer.toHexString(var1).toUpperCase() + ".png", arena);
			}
		} catch (Exception var4) {
			System.out.print("Image 0x" + Integer.toHexString(var1) + " not found!");
		}
		throw new IOException();
	}

	public int sub_160() {
		this.var_8c8.addElement(new Vector());
		return this.var_8c8.size() - 1;
	}

	public void sub_16c(int var1, Object var2) {
		((Vector) this.var_8c8.elementAt(var1)).addElement(var2);
	}

	public void sub_1b1(int var1, Object var2) {
		((Vector) this.var_8c8.elementAt(var1)).removeElement(var2);
	}

	public void sub_1d7(int var1) {
		Vector var2 = (Vector) this.var_8c8.elementAt(var1);

		while (var2.size() > 0) {
			var2.removeElementAt(0);
		}

	}

	public void sub_21e(int var1, Graphics var2, int var3, int var4) {
		Vector var5 = (Vector) this.var_8c8.elementAt(var1);

		for (int var7 = var5.size() - 1; var7 >= 0; --var7) {
			Object var6 = var5.elementAt(var7);
			if (var6 instanceof Class_3a) {
				((Class_3a) var6).sub_90(var2, var3, var4);
			} else if (var6 instanceof Class_151) {
				((Class_151) var6).sub_3d(var2, var3, var4);
			}
		}

		if (super.var_24f) {
			this.sub_63(var2);
		}
	}

	public void sub_248() {
		this.sub_359(this.var_3ef);
	}

	public void sub_267() {
		this.var_207 = this.var_35 = null;
	}

	@Override
	public void run() {
		Thread var1 = Thread.currentThread();
		if (var1 == this.var_207) {
			this.sub_29e();

			try {
				this.sub_83e();
				this.var_34f = true;
				if (this.var_6a8 != null) {
					this.addCommand(this.var_6a8);
				}

				while (this.var_207 != null) {
					try {
						Thread.sleep(250L);
					} catch (Exception var5) {
						var5.printStackTrace();
					}

					this.var_635 = !this.var_635;
					this.redraw();
				}
			} catch (Exception var6) {
				super.arena.sub_4e9("initGame: " + var6);
			}
		} else if (var1 == this.var_35) {
			synchronized (this) {
				this.var_260 = null;
				this.var_28e = this.var_2ca = null;
				System.gc();
			}

			this.sub_2d3();
		}
	}

	@Override
	public synchronized void draw(Graphics var1) {
		var1.setClip(0, 0, this.getwidth(), this.getheight());
		if (this.var_368 == 7) {
			if (this.var_260 != null) {
				var1.fillRect(0, 0, this.getwidth(), this.getheight());
				var1.drawImage(this.var_260,
						(this.getwidth() - this.var_260.getWidth()) / 2,
						(this.getheight() - this.var_260.getHeight()) / 2, 20);
			}

			if (this.var_34f) {
				if (this.var_28e != null) {
					var1.drawImage(this.var_28e, this.var_4d3, this.var_50d, 20);
				}

				if (this.var_635 && this.var_6a8 == null) {
					for (int var2 = 0; var2 < 5; ++var2) {
						var1.setColor(this.var_56f);
						var1.drawLine(this.var_5a0 + var2 + 1, this.var_5d6
										+ var2 + 1, this.var_5a0 + 9 - var2,
								this.var_5d6 + var2 + 1);
						var1.setColor(this.var_4a2);
						var1.drawLine(this.var_5a0 + var2, this.var_5d6 + var2,
								this.var_5a0 + 8 - var2, this.var_5d6 + var2);
					}
				} else if (this.var_2ca != null) {
					var1.drawImage(this.var_2ca, this.var_5a0, this.var_5d6, 20);
				}
			} else if (this.var_305 > 0) {
				var1.setColor(this.var_4a2);
				var1.fillRect(this.var_4d3, this.var_50d, (this.var_305 - 1)
						* this.var_543 / 100, this.var_556);
				var1.setColor(this.var_56f);
				var1.drawRect(this.var_4d3, this.var_50d, this.var_543 - 1,
						this.var_556 - 1);
			}
		}
	}

	public void sub_29e() {
		try {
			this.var_260 = this.openImage(0);
			this.redraw();
			Thread.sleep(2000L);
		} catch (Exception var3) {
			var3.printStackTrace();
		}

		try {
			this.var_260 = this.openImage(2);
			this.var_28e = Image.createImage(this.var_543, this.var_556);
			this.var_28e.getGraphics().drawImage(
					this.var_260,
					(this.getwidth() - this.var_260.getWidth()) / 2
							- this.var_4d3,
					(this.getheight() - this.var_260.getHeight()) / 2
							- this.var_50d, 20);
			this.var_2ca = Image.createImage(10, 10);
			this.var_2ca.getGraphics().drawImage(
					this.var_260,
					(this.getwidth() - this.var_260.getWidth()) / 2
							- this.var_5a0,
					(this.getheight() - this.var_260.getHeight()) / 2
							- this.var_5d6, 20);
			this.redraw();
		} catch (Exception var2) {
			System.out.println("ERROR: Title picture not found: " + var2);
		}
	}

	public abstract void sub_83e();

	public void sub_2d3() {
		long var1 = System.currentTimeMillis();

		while (Thread.currentThread() == this.var_35) {
			long var5 = System.currentTimeMillis();
			long var3 = (long) ((int) (var5 - var1));
			var1 = var5;
			if (var_d3) {
				var_d3 = false;
				this.sub_359(super.arena.sub_239(this.sub_1148()));
				this.var_3ef = 1;
			} else {
				try {
					this.sub_1fd(var3);
				} catch (Exception var9) {
					super.arena.sub_4e9("update: " + var9);
					return;
				}

				this.var_933 = 0L;
				this.redraw();

				try {
					Thread.sleep(Math.max(5L,
							(long) this.var_6b
									- (System.currentTimeMillis() - var5)));
				} catch (Exception var8) {
					var8.printStackTrace();
				}
			}
		}
	}

	public abstract int sub_1148();

	public abstract void sub_86();

	public abstract void sub_1a7();

	public abstract void sub_204();

	public abstract void sub_251();

	public abstract void sub_25b();

	public abstract void sub_2a0();

	public abstract void sub_2dd();

	public void setCurDisp(Displayable var1) {
		Display var2 = Display.getDisplay(super.arena);
		if (var2 != null) {
			var2.setCurrent(var1);
		}
	}

	public void sub_359(int var1) {
		this.var_3ef = this.var_368;
		this.sub_267();
		this.var_8e2 = this.var_933 = 0L;
		switch (var1) {
			case 1:
			case 8:
				this.var_260 = null;
				this.sub_86();
				this.sub_204();
				break;
			case 2:
				if (!this.var_401) {
					if (!this.sub_36b()) {
						this.sub_39a();
					} else {
						this.sub_3d2();
					}
				}

				this.var_3ef = 8;
				this.sub_251();
				this.var_35 = new Thread(this);
				this.var_35.start();
				this.var_401 = true;
				break;
			case 3:
				this.sub_1a7();
				break;
			case 4:
				this.sub_25b();
				break;
			case 5:
				this.sub_2a0();
				break;
			case 6:
				this.sub_2dd();
				break;
			case 7:
				this.setCurDisp(this);
				this.var_207 = new Thread(this);
				this.var_207.start();
		}

		this.var_368 = var1;
	}

	public abstract void sub_1fd(long var1);

	public abstract boolean sub_36b();

	public abstract void sub_39a();

	public void sub_3d2() {
	}

	public void sub_93a(long var1) {
	}

	public void sub_84f(long var1) {
		if (this.var_368 == 7) {
			if (this.var_34f) {
				this.sub_359(1);
			}
		} else if (this.var_368 == 2 && (var1 & 16384L) > 0L && this.var_34f) {
			this.sub_359(8);
		}

	}

	int sub_40a(int var1) {
		int var2 = 0;

		int var3;
		for (var3 = 0; var3 < this.var_66e.length; ++var3) {
			if (var1 == this.var_66e[var3]) {
				var2 |= var_2b[var3];
			}
		}

		if (this.var_e9) {
			for (var3 = 0; var3 < this.var_67b.length; ++var3) {
				if (var1 == this.var_66e[var3]) {
					var2 |= this.var_67b[var3];
				}
			}
		}

		return var2;
	}

	@Override
	public void keyPressed(int var1) {
		int var2 = this.sub_40a(var1);
		this.var_933 |= (long) var2;
		this.var_8e2 |= (long) var2;
		if (var2 != 0) {
			this.sub_93a((long) var2);
		}
	}

	@Override
	public final void keyReleased(int var1) {
		int var2 = this.sub_40a(var1);
		this.var_8e2 &= (long) (~var2);
		if (var2 != 0) {
			this.sub_84f((long) var2);
		}
	}

	@Override
	public void showNotify() {
		if (this.var_970) {
			this.sub_248();
			this.var_970 = false;
		}
	}

	@Override
	public void hideNotify() {
		if (this.var_368 == 2) {
			this.var_970 = true;
			this.sub_267();
		}
	}

	public String sub_446(int var1) {
		return super.arena.getStr(var1);
	}

	public int sub_462(int var1) {
		return super.arena.sub_dc(var1);
	}

	public void commandAction(Command var1, Displayable var2) {
		if (var1 == this.var_6a8) {
			this.sub_93a(16384L);
		}
	}

}
