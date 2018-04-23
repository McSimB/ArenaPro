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
import javax.microedition.util.ContextHolder;

public abstract class Arena2 extends Arena1 implements CommandListener {

	private final String[] recStores = new String[]{"RSF6", "RSHS7", "RSAD8"};
	public boolean _forPlayMus1 = true;
	public boolean var_e8 = true;
	public boolean var_105 = true;
	public boolean _forPlayMus = false;
	boolean var_188 = false;
	private String var_1d6 = "Afgh6Sg";
	public Command quitStr;
	Hashtable var_247 = new Hashtable();
	Alert alert;
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
	public Command saveAndSaveCom;
	public Command saveCom;
	public Command cancelCom;
	private TextBox highscoreNameTextBox;
	private int var_859;

	public void readStringResources(String fileName, String var2) throws IOException {
		//DataInputStream stream = new DataInputStream(getAssets().open(fileName));
		DataInputStream stream = new DataInputStream(ContextHolder.getResourceAsStream(fileName));
		int _int = stream.readInt();
		int i = 0;
		int var7 = 4;

		int var6;
		String s;
		do {
			s = stream.readUTF();
			var6 = stream.readInt();
			var7 += 4 + s.length() + 2;
			++i;
		} while (i < _int && !var2.startsWith(s));

		stream.skip((long) (var6 - var7));
		int var9 = stream.readInt();
		int var10 = stream.readInt();
		if (var10 > this.stringResources.length - 1) {
			String[] var12 = this.stringResources;
			this.stringResources = new String[var10 + 1];
			System.arraycopy(var12, 0, this.stringResources, 0, var12.length);
		}

		for (i = 0; i < var9; ++i) {
			int index = stream.readInt();
			this.stringResources[index] = stream.readUTF();
		}
	}

	void checkLocale() {
		String var1;
		/*if ((var1 = Locale.getDefault().getLanguage()) != null) { // ==
			var1 = "";
		}*/
		if((var1 = System.getProperty("microedition.locale")) == null) {
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
	public String getStr(int var1) {
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

	public int getParameter(int p) {
		for (int i = 0; i < this.var_310.length; i += 4) {
			if (p == ((this.var_310[i] & 255) << 8)
					+ (this.var_310[i + 1] & 255)) {
				return (short) ((this.var_310[i + 2] & 255) << 8)
						+ (this.var_310[i + 3] & 255);
			}
		}

		System.out.println("Parameter " + p + " not found!");
		return -1;
	}

	@Override
	public void startApp() {
		super.startApp();

		this.checkLocale();
		if (this.var_64d == null) {
			this.sub_1ea();
		}

		this.var_310 = sub_32f("_FF.ini");
		if (this.var_310 != null) {
			var_562 = this.getParameter(5050) == 1;
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

	public byte[] loadRecStore(String storeName) {
		try {
			RecordStore store;
			store = RecordStore.openRecordStore(storeName, false);
			RecordEnumeration enumeration = store.enumerateRecords(null, null, false);
			if (enumeration.hasNextElement()) {
				byte[] bytes = enumeration.nextRecord();
				System.out.println("loadRecordStore " + storeName + ": "
						+ bytes.length + " bytes.");
				store.closeRecordStore();
				this.sub_255(bytes);
				return bytes;
			}

			store.closeRecordStore();
		} catch (Exception var5) {
			System.out.println("loadRecordStore: " + var5 + ". " + storeName);
		}

		return null;
	}

	public DataInputStream getDIStream(String s) {
		byte[] bytes = this.loadRecStore(s);
		return bytes != null ? new DataInputStream(new ByteArrayInputStream(bytes)) : null;
	}

	public void sub_1ea() {
		this.sub_4b4();
		this.loadHighscore();

		try {
			DataInputStream var1 = this.getDIStream(this.getStr(201)
					+ this.recStores[2]);
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

	public void saveRecordStore(String name, byte[] bytes) {
		RecordStore var3 = null;

		try {
			this.sub_255(bytes);
			var3 = RecordStore.openRecordStore(name, true);
			int var4 = -1;
			if (var3.getNumRecords() > 0) {
				RecordEnumeration var5 = var3.enumerateRecords(null, null, false);
				var4 = var5.nextRecordId();
			}

			var3.addRecord(bytes, 0, bytes.length);
			System.out.println("saveRecordStore " + name + ": writing "
					+ bytes.length + " bytes.");
			if (var4 != -1) {
				var3.deleteRecord(var4);
				System.out.println("saveRecordStore " + name + ": deleted old record " + var4);
			}

			System.out.println("saveRecordStore " + name + ": Total size "
					+ var3.getSize() + "/" + var3.getSizeAvailable()
					+ " bytes.");
			var3.closeRecordStore();
			this.var_188 = false;
		} catch (Exception var7) {
			System.out.println("saveRecordStore: " + var7 + ". " + name);
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

	public void loadHighscore() {
		try {
			DataInputStream diStream = this.getDIStream(this.getStr(201) + this.recStores[1]);
			if (diStream != null) {
				this.var_5ee = (long) (diStream.readInt() * 1000);
				this.var_5db = diStream.readInt();
				this.var_68e = diStream.readInt();
				this.var_675 = diStream.readInt();

				for (int i = 0; i < this.var_5db; ++i) {
					this.var_64d[i] = diStream.readUTF();
					this.var_666[i] = diStream.readInt();
				}

				this.var_6b2 = diStream.readBoolean();
				this.var_590 = diStream.readUTF();
			}
		} catch (Exception var3) {
			System.out.println("Loading highscore failed " + var3);
			this.sub_45b();
		}

	}

	@Override
	public void saveHighscore() {
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
			this.saveRecordStore(this.getStr(201) + this.recStores[1], var1.toByteArray());
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

			this.saveRecordStore(this.getStr(201) + this.recStores[2], var1.toByteArray());
		} catch (Exception var5) {
			System.out.println("saveFeatureData failed " + var5);
		}

	}

	private void sub_255(byte[] bytes) {
		if (this.var_1d6 != null) {
			this.var_33f = this.var_1d6.getBytes();
			this.var_1d6 = null;
		}

		for (int i = 0; i < bytes.length; ++i) {
			bytes[i] = (byte) (bytes[i] ^ this.var_33f[i % this.var_33f.length] ^ i);
		}

	}

	public final void setAppID(String s) {
		super.appID = s;
	}

	@Override
	public void makeAlert(String s) {
		this.alert = new Alert(this.getStr(25), s, null, null);
		this.alert.setTimeout('\uea60');
		//TODO Alert
		Display.getDisplay(this).setCurrent(this.alert);
	}

	public boolean sub_2a7() {
		String var1 = this.getStr(98);
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
			DataInputStream var9 = this.getDIStream(this.getStr(201) + "TS");

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

			this.saveRecordStore(this.getStr(201) + "TS", var10.toByteArray());
			return var5 > var3 || var7 > 86400000L * (long) var2;
		}
	}

	public boolean sub_2e6() {
		this.display = Display.getDisplay(this);
		String var2 = null;
		if (this.sub_2a7()) {
			var2 = this.getStr(99);
		} else if (this.var_188) {
			var2 = this.getStr(26);
		}

		this.quitStr = new Command(this.getStr(9), 3, 10);
		if (var2 != null) {
			if (!(this.display.getCurrent() instanceof Form)) {
				Form var3 = new Form(this.getStr(14)); // Error
				var3.append(var2);
				var3.setCommandListener(this);
				var3.addCommand(this.quitStr);
				this.display.setCurrent(var3);
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

	public static InputStream sub_439(int var0) {
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
				var1 = new DataInputStream(ContextHolder.getResourceAsStream(var_391));
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

	public static synchronized byte[] sub_499(int var0) {
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
		this.highscoreNameTextBox = new TextBox(this.getStr(18), this.var_590, 12, 0);
		this.saveAndSaveCom = new Command(this.getStr(20), 4, 0);
		this.highscoreNameTextBox.addCommand(this.saveAndSaveCom);

		if (this.sub_447()) {
			this.saveCom = new Command(this.getStr(21), 4, 0);
			this.highscoreNameTextBox.addCommand(this.saveCom);
		}

		this.cancelCom = new Command(this.getStr(22), 2, 0);
		this.highscoreNameTextBox.addCommand(this.cancelCom);
		this.highscoreNameTextBox.setCommandListener(this);
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
		this.highscoreNameTextBox.setString(this.var_590 != null ? this.var_590 : "");
		if (var1 != null) {
			this.display.setCurrent(var1, this.highscoreNameTextBox);
		} else {
			this.display.setCurrent(this.highscoreNameTextBox);
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
		if (var1 != this.saveAndSaveCom && var1 != this.saveCom) {
			if (var1 == this.cancelCom) {
				this.var_859 = 3;
			} else if (var1 == this.quitStr) {
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
			this.var_590 = this.sub_4c6(this.highscoreNameTextBox.getString());
			if (this.var_590.length() < 3) {
				//TODO Alert
				//this.display.setCurrent(new Alert("Info", this.getStr(23), null, null));
			} else if (var1 == this.saveCom) {
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
