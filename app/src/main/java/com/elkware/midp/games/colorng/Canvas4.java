package com.elkware.midp.games.colorng;

import com.elkware.midp.games.colorng.arena.high.Arena;

import java.util.Vector;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.List;

public abstract class Canvas4 extends Canvas3 {

	public Command continueOrStartCom;
	public Command var_90;
	public Command resumeCom;
	public Command helpCom;
	public Command aboubCom;
	public Command okStr;
	public Command backStr;
	public Command sendCom;
	public Command highscoresCom;
	public Command quitCom;
	public Command var_2d8;
	Form var_301;
	Form var_332;
	Form var_372;
	public List var_384;
	public List var_39a;
	Image[] var_3cb;
	boolean var_3e2 = true;
	boolean var_428;
	boolean var_453 = true;
	Thread var_47b;
	int var_4a5;

	public Canvas4(Arena var1) {
		super(var1);
		this.var_428 = var1.sub_dc(5033) == 1;
		int var2 = this.sub_462(5047);
		if (var2 != -1) {
			this.var_453 = var2 == 1;
		}

		this.sub_33();
		if (this.var_428) {
			this.var_90 = new Command(var1.getStr(40), 1, 0);
			this.sub_16();
		}

		this.var_3cb = new Image[7];

		int var3;
		for (var3 = 0; var3 < this.var_3cb.length; ++var3) {
			this.var_3cb[var3] = this.sub_120(6 + var3);
		}

		this.var_332 = new Form(var1.getStr(2));
		this.var_332.append(var1.getStr(200));
		this.var_332.setCommandListener(this);
		this.var_332.addCommand(this.okStr);
		this.var_301 = new Form(var1.getStr(10));
		this.var_301.append(var1.getStr(42));
		this.var_301.setCommandListener(this);
		this.var_301.addCommand(this.okStr);
		this.var_384 = new List(var1.getStr(19), 3, arena);
		if (super.var_6fb != -1) {
			this.var_384.append(var1.getStr(3));
		}

		if (super.var_75e != -1) {
			this.var_384.append(var1.getStr(4));
		}

		if (super.var_7c0 != -1) {
			this.var_384.append(var1.getStr(5));
		}

		if (super.var_813 != -1) {
			this.var_384.append(var1.getStr(6));
		}

		if (super.var_87b != null) {
			for (var3 = 0; var3 < super.var_87b.length; ++var3) {
				this.var_384.append(super.var_87b[var3]);
			}
		}

		this.var_384.setCommandListener(this);
		if (var1.sub_dc(5053) == 1) {
			this.var_384.addCommand(this.okStr);
		} else {
			this.var_384.addCommand(this.backStr);
		}

		if (var1.sub_dc(5052) == 1) {
			this.var_2d8 = new Command(var1.getStr(44), 4, 0);
		}

	}

	public void sub_16() {
		this.var_372 = new Form(super.arena.getStr(40));
		this.var_372.append(super.arena.getStr(41));
		this.var_372.addCommand(this.okStr);
		this.var_372.addCommand(this.backStr);
		this.var_372.setCommandListener(this);
	}

	public void sub_33() {
		this.resumeCom = new Command(super.arena.getStr(0), 1, 0);
		this.helpCom = new Command(super.arena.getStr(2), 1, 1);
		this.sendCom = new Command(super.arena.getStr(19), 1, 2);
		this.highscoresCom = new Command(super.arena.getStr(1), 1, 5);
		this.aboubCom = new Command(super.arena.getStr(10), 1, 6);
		this.quitCom = new Command(super.arena.getStr(9), 6, 7);
		this.okStr = new Command(super.arena.getStr(35), 4, 0);
		this.backStr = new Command(super.arena.getStr(12), 2, 0);
	}

	public void sub_86() {
		boolean var1 = this.var_428 && this.sub_36b();
		this.var_39a = new List(super.arena.getStr(201), 3, arena);
		this.continueOrStartCom = new Command(super.arena.getStr(super.var_401 ? 0
				: (var1 ? 13 : 31)), 1, 0);
		this.var_39a.append(this.continueOrStartCom.getLabel());
		if (var1 || super.var_401) {
			this.var_39a.append(this.var_90.getLabel());
		}

		Vector var2 = this.sub_d3();
		if (var2 != null) {
			for (int var3 = 0; var3 < var2.size() / 3; ++var3) {
				int var4 = (Integer) var2.elementAt(var3 * 3 + 2);
				if (!super.var_401 && (var4 & 1) == 1 || super.var_401
						&& (var4 & 2) == 2) {
					this.var_39a.append((String) var2.elementAt(var3 * 3)
					);
				}
			}
		}

		this.var_39a.append(this.helpCom.getLabel());
		if (super.var_6d4) {
			this.var_39a.append(this.sendCom.getLabel());
		}

		if (this.var_453) {
			this.var_39a.append(this.highscoresCom.getLabel());
		}

		if (this.var_3e2) {
			this.var_39a.append(this.aboubCom.getLabel());
		}

		this.var_39a.append(this.quitCom.getLabel());
		this.var_39a.setCommandListener(this);
		if (this.var_2d8 != null) {
			this.var_39a.addCommand(this.var_2d8);
		}

	}

	public Vector sub_d3() {
		return null;
	}

	Image sub_120(int var1) {
		try {
			return this.openImage(var1);
		} catch (Exception var3) {
			return null;
		}
	}

	public void sub_171(int var1) {
		super.var_305 = var1;
		this.redraw();
	}

	public void sub_1a7() {
		Form var1 = new Form(super.arena.getStr(1));
		String var2 = "";

		for (int var3 = 0; var3 < super.arena.var_5db; ++var3) {
			var2 = var2 + (var3 + 1) + ". " + super.arena.var_64d[var3] + " "
					+ super.arena.var_666[var3] + "\n";
		}

		var1.append(var2);
		var1.addCommand(this.okStr);
		var1.setCommandListener(this);
		this.setCurDisp(var1);
	}

	@Override
	public void sub_204() {
		this.setCurDisp(this.var_39a);
	}

	@Override
	public void sub_251() {
		this.setCurDisp(this);
	}

	@Override
	public void sub_25b() {
		this.setCurDisp(this.var_332);
	}

	@Override
	public void sub_2a0() {
		this.setCurDisp(this.var_301);
	}

	@Override
	public void sub_2dd() {
		this.setCurDisp(this.var_384);
		this.sub_af(super.var_8b4);
	}

	@Override
	public void sub_2e9(int var1, boolean var2) {
		super.sub_2e9(var1, var2);
		this.var_384.set(var1, this.var_384.getString(var1)
		);
	}

	public void sub_2fb() {
	}

	@Override
	public void commandAction(Command var1, Displayable var2) {
		super.commandAction(var1, var2);
		if (var1 != this.quitCom) {
			if (var1 == this.backStr) {
				this.sub_310(super.var_3ef);
			} else if (var1 == this.okStr) {
				if (var2 == this.var_372) {
					this.sub_39a();
					this.sub_310(2);
				} else {
					this.sub_310(1);
				}
			}
		}

		if (var2 == this.var_384 && var1 == List.SELECT_COMMAND) {
			int var4 = this.var_384.getSelectedIndex();
			this.sub_2e9(var4, !this.sub_100(var4));
		} else if (var2 == this.var_39a) {
			String var3 = this.var_39a.getString(this.var_39a
					.getSelectedIndex());
			if (var3.equals(this.continueOrStartCom.getLabel())) {
				this.sub_310(2);
			} else if (var3.equals(this.var_90.getLabel())) {
				super.var_3ef = 1;
				this.setCurDisp(this.var_372);
			} else if (var3.equals(this.helpCom.getLabel())) {
				this.sub_310(4);
			} else if (var3.equals(this.highscoresCom.getLabel())) {
				this.sub_310(3);
			} else if (var3.equals(this.aboubCom.getLabel())) {
				this.sub_310(5);
			} else if (var3.equals(this.sendCom.getLabel())) {
				this.sub_359(6);
			} else if (var3.equals(this.quitCom.getLabel())) {
				super.arena.destroyApp(true);
			} else {
				this.sub_2fb();
			}
		}

	}

	void sub_310(int var1) {
		this.var_4a5 = var1;
		this.var_47b = new Thread(this);
		this.var_47b.start();
	}

	@Override
	public void run() {
		if (Thread.currentThread() == this.var_47b) {
			this.sub_359(this.var_4a5);
		} else {
			super.run();
		}
	}
}
