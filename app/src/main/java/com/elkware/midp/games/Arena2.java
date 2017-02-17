package com.elkware.midp.games;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Locale;

import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.TextBox;
import javax.microedition.rms.RecordEnumeration;
import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;

public abstract class Arena2 extends Arena1 implements CommandListener {

	private final String[] var_43 = new String[] { "RSF6", "RSHS7", "RSAD8" };
	public Display var_52;
	public boolean var_b4 = true;
	public boolean var_e8 = true;
	public boolean var_105 = true;
	public boolean var_166 = false;
	boolean var_188 = false;
	private String var_1d6 = "Afgh6Sg";
	public Command var_20a;
	Hashtable var_247 = new Hashtable();
	Alert var_2a5;
	public String[] stringResources = new String[0];
	byte[] var_310;
	byte[] var_33f;
	static Object var_359;
	static String var_391;
	static int var_3ae;
	static int var_3ed;
	static byte[] var_43e;
	static byte[] var_4e6;
	static RecordStore var_541;
	static boolean var_562;
	public static long var_585;
	public String var_590;
	public int var_5c8 = 5;
	public int var_5db;
	public long var_5ee;
	public String[] var_64d;
	public int[] var_666;
	public int var_675;
	public int var_68e;
	public boolean var_6b2 = true;
	public Command var_706;
	public Command var_75b;
	public Command var_78b;
	private TextBox var_83e;
	private int var_859;

	public void readStringResources(String fileName, String var2) throws IOException {
		DataInputStream var3 = new DataInputStream(getAssets().open(fileName));
		int var4 = var3.readInt();
		int var5 = 0;
		int var7 = 4;

		int var6;
		String var8;
		do {
			var8 = var3.readUTF();
			var6 = var3.readInt();
			var7 += 4 + var8.length() + 2;
			++var5;
		} while (var5 < var4 && !var2.startsWith(var8));

		var3.skip((long) (var6 - var7));
		int var9 = var3.readInt();
		int var10 = var3.readInt();
		if (var10 > this.stringResources.length - 1) {
			String[] var12 = this.stringResources;
			this.stringResources = new String[var10 + 1];
			System.arraycopy(var12, 0, this.stringResources, 0, var12.length);
		}

		for (var5 = 0; var5 < var9; ++var5) {
			int var11 = var3.readInt();
			this.stringResources[var11] = var3.readUTF();
		}
	}

	void checkLocale() {
		String var1;
		if ((var1 = Locale.getDefault().getLanguage()) == null) {
			var1 = "";
		}

		try {
			this.readStringResources("sr_" + var1 + ".ini", var1);
		} catch (Exception var7) {
			try {
				this.readStringResources("sr_en.ini", var1);
			} catch (Exception var6) {
				try {
					this.readStringResources("sr.ini", var1);
				} catch (Exception var5) {
					System.out.println("ERROR: loadStrings: " + var5);
				}
			}
		}
	}

	@Override
	public String sub_383(int var1) {
		if (this.stringResources.length == 0) {
			this.checkLocale();
		}

		return this.stringResources[var1];
	}

	public void sub_a3(int var1) {
		if (this.var_310 == null) {
			this.var_310 = sub_499(var1);
		}

		if (this.var_310 == null) {
			System.out.println("Parameter file " + var1 + " is missing!");
		}

	}

	public int sub_dc(int var1) {
		for (int var2 = 0; var2 < this.var_310.length; var2 += 4) {
			if (var1 == ((this.var_310[var2] & 255) << 8)
					+ (this.var_310[var2 + 1] & 255)) {
				return (short) ((this.var_310[var2 + 2] & 255) << 8)
						+ (this.var_310[var2 + 3] & 255);
			}
		}

		System.out.println("Parameter " + var1 + " not found!");
		return -1;
	}

	public void startApp() {
		this.checkLocale();
		if (this.var_64d == null) {
			this.sub_1ea();
		}

		this.var_310 = sub_32f("_FF.ini");
		if (this.var_310 != null) {
			var_562 = this.sub_dc(5050) == 1;
		}

		sub_38f(this, "res.raw");
		this.sub_a3(255);
	}

	public void sub_f0(String var1, String var2, boolean var3) {
		if (var2 == null) {
			this.var_247.remove(var1);
		} else {
			this.var_247.put(var1, var2);
		}

		if (var3) {
			this.sub_249();
		}

	}

	public String sub_13f(String var1) {
		return (String) this.var_247.get(var1);
	}

	public byte[] sub_170(String var1) {
		try {
			RecordStore var2 = null;
			var2 = RecordStore.openRecordStore(var1, false);
			RecordEnumeration var3 = var2.enumerateRecords(null, null, false);
			if (var3.hasNextElement()) {
				byte[] var4 = var3.nextRecord();
				System.out.println("loadRecordStore " + var1 + ": "
						+ var4.length + " bytes.");
				var2.closeRecordStore();
				this.sub_255(var4);
				return var4;
			}

			var2.closeRecordStore();
		} catch (Exception var5) {
			System.out.println("loadRecordStore: " + var5);
		}

		return null;
	}

	public DataInputStream sub_1c4(String var1) {
		byte[] var2 = this.sub_170(var1);
		return var2 != null ? new DataInputStream(
				new ByteArrayInputStream(var2)) : null;
	}

	public void sub_1ea() {
		this.sub_4b4();
		this.sub_20f();

		try {
			DataInputStream var1 = this.sub_1c4(this.sub_383(201)
					+ this.var_43[2]);
			if (var1 != null) {
				int var2 = var1.readInt();

				for (int var3 = 0; var3 < var2; ++var3) {
					this.var_247.put(var1.readUTF(), var1.readUTF());
				}
			}
		} catch (Exception var4) {
			System.out.println("Error loading additional data " + var4);
		}

	}

	public void sub_201(String var1, byte[] var2) {
		RecordStore var3 = null;

		try {
			this.sub_255(var2);
			var3 = RecordStore.openRecordStore(var1, true);
			int var4 = -1;
			if (var3.getNumRecords() > 0) {
				RecordEnumeration var5 = var3.enumerateRecords(null, null, false);
				var4 = var5.nextRecordId();
			}

			var3.addRecord(var2, 0, var2.length);
			System.out.println("saveRecordStore " + var1 + ": writing "
					+ var2.length + " bytes.");
			if (var4 != -1) {
				var3.deleteRecord(var4);
				System.out.println("saveRecordStore " + var1
						+ ": deleted old record " + var4);
			}

			System.out.println("saveRecordStore " + var1 + ": Total size "
					+ var3.getSize() + "/" + var3.getSizeAvailable()
					+ " bytes.");
			var3.closeRecordStore();
			this.var_188 = false;
		} catch (Exception var7) {
			System.out.println("saveRecordStore: " + var7);
			this.var_188 = true;

			try {
				if (var3 != null) {
					var3.closeRecordStore();
				}
			} catch (RecordStoreException var6) {
				var6.printStackTrace();
			}
		}
	}

	public void sub_20f() {
		try {
			DataInputStream var1 = this.sub_1c4(this.sub_383(201)
					+ this.var_43[1]);
			if (var1 != null) {
				this.var_5ee = (long) (var1.readInt() * 1000);
				this.var_5db = var1.readInt();
				this.var_68e = var1.readInt();
				this.var_675 = var1.readInt();

				for (int var2 = 0; var2 < this.var_5db; ++var2) {
					this.var_64d[var2] = var1.readUTF();
					this.var_666[var2] = var1.readInt();
				}

				this.var_6b2 = var1.readBoolean();
				this.var_590 = var1.readUTF();
			}
		} catch (Exception var3) {
			System.out.println("Loading highscore failed " + var3);
			this.sub_45b();
		}

	}

	@Override
	public void sub_571() {
		try {
			ByteArrayOutputStream var1 = new ByteArrayOutputStream();
			DataOutputStream var2 = new DataOutputStream(var1);
			var2.writeInt((int) (this.var_5ee / 1000L));
			var2.writeInt(this.var_5db);
			var2.writeInt(this.var_68e);
			var2.writeInt(this.var_675);

			for (int var3 = 0; var3 < this.var_5db; ++var3) {
				var2.writeUTF(this.var_64d[var3]);
				var2.writeInt(this.var_666[var3]);
			}

			var2.writeBoolean(this.var_6b2);
			var2.writeUTF(this.var_590 != null ? this.var_590 : "");
			this.sub_201(this.sub_383(201) + this.var_43[1], var1.toByteArray());
		} catch (Exception var4) {
			System.out.println("Saving highscore failed " + var4);
		}

	}

	public void sub_249() {
		try {
			ByteArrayOutputStream var1 = new ByteArrayOutputStream();
			DataOutputStream var2 = new DataOutputStream(var1);
			var2.writeInt(this.var_247.size());
			if (!this.var_247.isEmpty()) {
				Enumeration var3 = this.var_247.keys();
				Enumeration var4 = this.var_247.elements();

				while (var3.hasMoreElements()) {
					var2.writeUTF((String) var3.nextElement());
					var2.writeUTF((String) var4.nextElement());
				}
			}

			this.sub_201(this.sub_383(201) + this.var_43[2], var1.toByteArray());
		} catch (Exception var5) {
			System.out.println("saveFeatureData failed " + var5);
		}

	}

	private void sub_255(byte[] var1) {
		if (this.var_1d6 != null) {
			this.var_33f = this.var_1d6.getBytes();
			this.var_1d6 = null;
		}

		for (int var2 = 0; var2 < var1.length; ++var2) {
			var1[var2] = (byte) (var1[var2]
					^ this.var_33f[var2 % this.var_33f.length] ^ var2);
		}

	}

	public final void sub_291(String var1) {
		super.var_153 = var1;
	}

	@Override
	public void sub_4e9(String var1) {
		this.var_2a5 = new Alert(this.sub_383(25), var1, null, null);
		this.var_2a5.setTimeout('\uea60');
		//TODO Alert
		Display.getDisplay(this).setCurrent(this.var_2a5);
	}

	public boolean sub_2a7() {
		String var1 = this.sub_383(98);
		if (var1 == null) {
			return true;
		} else {
			int var2;
			try {
				var2 = Integer.parseInt(var1);
			} catch (NumberFormatException var14) {
				return true;
			}

			long var3 = System.currentTimeMillis();
			long var5 = var3;
			long var7 = 0L;
			DataInputStream var9 = this.sub_1c4(this.sub_383(201) + "TS");

			try {
				if (var9 != null) {
					var5 = var9.readLong();
					var7 = var9.readLong();
				}
			} catch (Exception var13) {
				var5 = var3;
			}

			var7 += Math.max(0L, var3 - var5);
			ByteArrayOutputStream var10 = new ByteArrayOutputStream();

			try {
				DataOutputStream var11 = new DataOutputStream(var10);
				var11.writeLong(var3);
				var11.writeLong(var7);
			} catch (Exception var12) {
				var12.printStackTrace();
			}

			this.sub_201(this.sub_383(201) + "TS", var10.toByteArray());
			return var5 > var3 || var7 > 86400000L * (long) var2;
		}
	}

	public boolean sub_2e6() {
		this.var_52 = Display.getDisplay(this);
		String var2 = null;
		if (this.sub_2a7()) {
			var2 = this.sub_383(99);
		} else if (this.var_188) {
			var2 = this.sub_383(26);
		}

		this.var_20a = new Command(this.sub_383(9), 3, 10);
		if (var2 != null) {
			if (!(this.var_52.getCurrent() instanceof Form)) {
				Form var3 = new Form(this.sub_383(14));
				var3.append(var2);
				var3.setCommandListener(this);
				var3.addCommand(this.var_20a);
				this.var_52.setCurrent(var3);
			}

			return false;
		} else {
			return true;
		}
	}

	public byte[] sub_32f(String var0) {
		try {
			DataInputStream var1 = new DataInputStream(getAssets().open(var0));
			ByteArrayOutputStream var2 = new ByteArrayOutputStream();

			int var3;
			while ((var3 = var1.read()) != -1) {
				var2.write(var3);
			}

			var1.close();
			return var2.toByteArray();
		} catch (Throwable var4) {
			System.out.println("ERROR: Resource " + var0 + " not found!");
			return null;
		}
	}

	public byte[] sub_370(String var1) {
		try {
			DataInputStream var2 = new DataInputStream(getAssets().open(var1));
			int var3 = var2.readInt();
			System.gc();
			byte[] var4 = new byte[var3];
			var2.readFully(var4);
			var2.close();
			return var4;
		} catch (Throwable var5) {
			System.out.println("ERROR: Resource " + var1 + " not found!");
			return null;
		}
	}

	public int sub_37b() {
		return var_3ed;
	}

	public void sub_38f(Object var0, String var1) {
		sub_3ee();
		var_359 = var0;
		var_391 = var1;
		if (var_562) {
			var_43e = sub_370(var1);
			System.out.println(
					"Caching res.raw (" + (var_43e != null ? var_43e.length : 0) + " bytes)");
		}
	}

	public static void sub_3ee() {
		var_43e = null;
		var_4e6 = null;
		if (var_541 != null) {
			try {
				var_541.closeRecordStore();
			} catch (Exception var1) {
				var1.printStackTrace();
			}
		}
		System.gc();
	}

	public InputStream sub_439(int var0) {
		try {
			DataInputStream var1;
			int var2;
			if (var_43e != null) {
				var1 = new DataInputStream(new ByteArrayInputStream(var_43e));
			} else {
				if (var_541 != null) {
					var2 = 0;
					while ((var_4e6[var2] & 255) != var0 && var2 < var_4e6.length) {
						var2 += 3;
					}

					if (var2 >= var_4e6.length) {
						return null;
					}

					var_3ed = var_4e6[var2 + 1] & 255;
					return new DataInputStream(new ByteArrayInputStream(
							var_541.getRecord(var_4e6[var2 + 2] & 255)));
				}

				//var1 = new DataInputStream(var_359.getClass()
				//		.getResourceAsStream(var_391));
				var1 = new DataInputStream(getAssets().open(var_391));
				var1.skip(4L);
			}

			var2 = var1.read() & 255;
			int var7 = 0;

			int var3;
			int var4;
			int var5;
			int var6;
			do {
				var3 = var1.readByte() & 255;
				var6 = var1.readByte() & 255;
				var4 = var1.readInt();
				var5 = var1.readInt();
				++var7;
			} while (var7 < var2 && var0 != var3);

			if (var0 != var3) {
				return null;
			} else {
				var1.skip((long) ((var2 - var7) * 10 + var4));
				var_3ae = var5;
				var_3ed = var6;
				return var1;
			}
		} catch (Exception var8) {
			System.out.println("ERROR: getPackedResourceStream " + var0 + " : "
					+ var8);
			return null;
		}
	}

	public synchronized byte[] sub_499(int var0) {
		try {
			int var1;
			if (var_541 != null) {
				var1 = 0;
				while ((var_4e6[var1] & 255) != var0 && var1 < var_4e6.length) {
					var1 += 3;
				}
				if (var1 >= var_4e6.length) {
					return null;
				} else {
					var_3ed = var_4e6[var1 + 1] & 255;
					return var_541.getRecord(var_4e6[var1 + 2] & 255);
				}
			} else if (var_43e == null) {
				DataInputStream var10 = (DataInputStream) sub_439(var0);
				byte[] var11 = new byte[var_3ae];
				if (var10 != null) {
					var10.readFully(var11);
					var10.close();
				}
				return var11;
			} else {
				var1 = var_43e[0] & 255;
				int var6 = 0;
				int var7 = 1;

				int var2;
				int var3;
				int var4;
				int var5;
				do {
					var2 = var_43e[var7] & 255;
					var5 = var_43e[var7 + 1] & 255;
					var3 = (var_43e[var7 + 2] & 255) << 24
							| (var_43e[var7 + 3] & 255) << 16
							| (var_43e[var7 + 4] & 255) << 8
							| var_43e[var7 + 5] & 255;
					var4 = (var_43e[var7 + 6] & 255) << 24
							| (var_43e[var7 + 7] & 255) << 16
							| (var_43e[var7 + 8] & 255) << 8
							| var_43e[var7 + 9] & 255;
					++var6;
					var7 += 10;
				} while (var6 < var1 && var0 != var2);

				if (var0 != var2) {
					return null;
				} else {
					var_3ed = var5;
					byte[] var8 = new byte[var4];
					var7 += (var1 - var6) * 10 + var3;
					System.arraycopy(var_43e, var7, var8, 0, var4);
					return var8;
				}
			}
		} catch (Exception var9) {
			System.out.println("ERROR: loadRAWFromPacked " + var0 + " : "
					+ var9);
			return null;
		}
	}

	public void sub_4b4() {
		this.var_590 = "";
		this.var_83e = new TextBox(this.sub_383(18), this.var_590, 12, 0);
		if (this.sub_485()) {
			this.var_706 = new Command(this.sub_383(20), 4, 0);
			this.var_83e.addCommand(this.var_706);
		}

		if (this.sub_447()) {
			this.var_75b = new Command(this.sub_383(21), 4, 0);
			this.var_83e.addCommand(this.var_75b);
		}

		this.var_78b = new Command(this.sub_383(22), 2, 0);
		this.var_83e.addCommand(this.var_78b);
		this.var_83e.setCommandListener(this);
		this.sub_45b();
	}

	public boolean sub_447() {
		return true;
	}

	@Override
	public String sub_3e1() {
		return this.var_590;
	}

	@Override
	public void sub_3f3(String var1) {
		this.var_590 = var1;
	}

	@Override
	public void sub_448(int var1) {
		this.var_675 = var1;
	}

	@Override
	public void sub_45b() {
		this.var_64d = new String[this.var_5c8];
		this.var_666 = new int[this.var_5c8];
		this.var_5db = 0;
		this.var_5ee = System.currentTimeMillis();
	}

	@Override
	public final boolean sub_53f(int var1) {
		return var1 > this.var_68e;
	}

	@Override
	public void sub_28a(int var1) {
		this.var_68e = var1;
		this.var_6b2 = false;
	}

	@Override
	public int sub_5af() {
		return this.var_859;
	}

	@Override
	public void sub_5de(boolean var1) {
		this.var_6b2 = var1;
	}

	@Override
	public boolean sub_4af(Alert var1) {
		this.var_859 = 0;
		this.var_83e.setString(this.var_590 != null ? this.var_590 : "");
		if (var1 != null) {
			this.var_52.setCurrent(var1, this.var_83e);
		} else {
			this.var_52.setCurrent(this.var_83e);
		}

		try {
			while (this.var_859 == 0) {
				Thread.sleep(250L);
			}
		} catch (Exception var3) {
			return false;
		}

		return this.var_859 < 3;
	}

	@Override
	public void commandAction(Command var1, Displayable var2) {
		if (var1 != this.var_706 && var1 != this.var_75b) {
			if (var1 == this.var_78b) {
				this.var_859 = 3;
			} else if (var1 == this.var_20a) {
				try {
					this.destroyApp(true);
					this.notifyDestroyed();
				} catch (Exception var4) {
					var4.printStackTrace();
				}
			} else {
				super.commandAction(var1, var2);
			}
		} else {
			this.var_590 = this.sub_4c6(this.var_83e.getString());
			if (this.var_590.length() < 3) {
				//TODO Alert
				//this.var_52.setCurrent(new Alert("Info", this.sub_383(23), null, null));
			} else if (var1 == this.var_75b) {
				this.var_859 = 2;
			} else {
				this.var_859 = 1;
			}
		}
	}

	public String sub_4c6(String var1) {
		if (var1.length() > 12) {
			var1 = var1.substring(0, 12);
		}
		return var1;
	}

	@Override
	public boolean sub_486(int var1, String var2) {
		int var3;
		var3 = 0;
		while (var3 < this.var_5db && var1 <= this.var_666[var3]) {
			++var3;
		}

		if (var3 == this.var_5c8) {
			return false;
		} else {
			if (this.var_5db < this.var_5c8) {
				++this.var_5db;
			}

			for (int var4 = this.var_5db - 1; var4 > var3; --var4) {
				this.var_64d[var4] = this.var_64d[var4 - 1];
				this.var_666[var4] = this.var_666[var4 - 1];
			}

			this.var_666[var3] = var1;
			this.var_64d[var3] = var2;
			return true;
		}
	}

	static {
		System.gc();
		var_585 = System.currentTimeMillis();
	}

}