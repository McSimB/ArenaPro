package com.elkware.midp.games.colorng;

import java.util.Vector;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.List;

public abstract class Canvas4 extends Canvas3 {

	public Command continueOrStartCom;
	public Command headCom;
	public Command resumeCom;
	public Command helpCom;
	public Command aboutCom;
	public Command okStr;
	public Command backCom;
	public Command sendCom;
	public Command highscoresCom;
	public Command quitCom;
	public Command equipCom;
	Form aboutForm;
	Form helpForm;
	Form newGameForm;
	public List sendList;
	public List var_39a;
	boolean var_3e2 = true;
	boolean var_428;
	boolean var_453 = true;
	Thread var_47b;
	int var_4a5;

	public Canvas4(Arena3 var1) {
		super(var1);
		this.var_428 = var1.getParameter(5033) == 1;
		int var2 = this.sub_462(5047);
		if (var2 != -1) {
			this.var_453 = var2 == 1;
		}

		this.sub_33();
		if (this.var_428) {
			this.headCom = new Command(var1.getStr(40), 1, 0);
			this.sub_16();
		}
		
		int var3;
		this.helpForm = new Form(var1.getStr(2));
		this.helpForm.append(var1.getStr(200));
		this.helpForm.setCommandListener(this);
		this.helpForm.addCommand(this.okStr);
		this.aboutForm = new Form(var1.getStr(10));
		this.aboutForm.append(var1.getStr(42));
		this.aboutForm.setCommandListener(this);
		this.aboutForm.addCommand(this.okStr);
		this.sendList = new List(var1.getStr(19), 3);
		if (super.var_6fb != -1) {
			this.sendList.append(var1.getStr(3), null);
		}

		if (super.var_75e != -1) {
			this.sendList.append(var1.getStr(4), null);
		}

		if (super.var_7c0 != -1) {
			this.sendList.append(var1.getStr(5), null);
		}

		if (super.var_813 != -1) {
			this.sendList.append(var1.getStr(6), null);
		}

		if (super.var_87b != null) {
			for (var3 = 0; var3 < super.var_87b.length; ++var3) {
				this.sendList.append(super.var_87b[var3], null);
			}
		}

		this.sendList.setCommandListener(this);
		if (var1.getParameter(5053) == 1) {
			this.sendList.addCommand(this.okStr);
		} else {
			this.sendList.addCommand(this.backCom);
		}

		if (var1.getParameter(5052) == 1) {
			this.equipCom = new Command(var1.getStr(44), 4, 0);
		}

	}

	public void sub_16() {
		this.newGameForm = new Form(super.arena3.getStr(40));
		this.newGameForm.append(super.arena3.getStr(41));
		this.newGameForm.addCommand(this.okStr);
		this.newGameForm.addCommand(this.backCom);
		this.newGameForm.setCommandListener(this);
	}

	public void sub_33() {
		this.resumeCom = new Command(super.arena3.getStr(0), 1, 0);
		this.helpCom = new Command(super.arena3.getStr(2), 1, 1);
		this.sendCom = new Command(super.arena3.getStr(19), 1, 2);
		this.highscoresCom = new Command(super.arena3.getStr(1), 1, 5);
		this.aboutCom = new Command(super.arena3.getStr(10), 1, 6);
		this.quitCom = new Command(super.arena3.getStr(9), 6, 7);
		this.okStr = new Command(super.arena3.getStr(35), 4, 0);
		this.backCom = new Command(super.arena3.getStr(12), 2, 0);
	}

	public void sub_86() {
		boolean var1 = this.var_428 && this.sub_36b();
		this.var_39a = new List(super.arena3.getStr(201), 3);
		this.continueOrStartCom = new Command(super.arena3.getStr(super.var_401 ? 0
				: (var1 ? 13 : 31)), 1, 0);
		this.var_39a.append(this.continueOrStartCom.getLabel(), null);
		if (var1 || super.var_401) {
			this.var_39a.append(this.headCom.getLabel(), null);
		}

		Vector var2 = this.sub_d3();
		if (var2 != null) {
			for (int var3 = 0; var3 < var2.size() / 3; ++var3) {
				int var4 = (Integer) var2.elementAt(var3 * 3 + 2);
				if (!super.var_401 && (var4 & 1) == 1 || super.var_401
						&& (var4 & 2) == 2) {
					this.var_39a.append((String) var2.elementAt(var3 * 3), null);
				}
			}
		}

		this.var_39a.append(this.helpCom.getLabel(), null);
		if (super.var_6d4) {
			this.var_39a.append(this.sendCom.getLabel(), null);
		}

		if (this.var_453) {
			this.var_39a.append(this.highscoresCom.getLabel(), null);
		}

		if (this.var_3e2) {
			this.var_39a.append(this.aboutCom.getLabel(), null);
		}

		this.var_39a.append(this.quitCom.getLabel(), null);
		this.var_39a.setCommandListener(this);
		if (this.equipCom != null) {
			this.var_39a.addCommand(this.equipCom);
		}

	}

	public Vector sub_d3() {
		return null;
	}

	public void sub_171(int var1) {
		super.var_305 = var1;
		this.redraw();
	}

	public void sub_1a7() {
		Form highscoresForm = new Form(super.arena3.getStr(1));
		String var2 = "";

		for (int var3 = 0; var3 < super.arena3.var_5db; ++var3) {
			var2 = var2 + (var3 + 1) + ". " + super.arena3.var_64d[var3] + " "
					+ super.arena3.var_666[var3] + "\n";
		}

		highscoresForm.append(var2);
		highscoresForm.addCommand(this.okStr);
		highscoresForm.setCommandListener(this);
		this.setCurDisp(highscoresForm);
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
		this.setCurDisp(this.helpForm);
	}

	@Override
	public void sub_2a0() {
		this.setCurDisp(this.aboutForm);
	}

	@Override
	public void sub_2dd() {
		this.setCurDisp(this.sendList);
		this.sub_af(super.var_8b4);
	}

	@Override
	public void sub_2e9(int var1, boolean var2) {
		super.sub_2e9(var1, var2);
		this.sendList.set(var1, this.sendList.getString(var1), null);
	}

	public void sub_2fb() {
	}

	@Override
	public void commandAction(Command var1, Displayable var2) {
		super.commandAction(var1, var2);
		if (var1 != this.quitCom) {
			if (var1 == this.backCom) {
				this.sub_310(super.var_3ef);
			} else if (var1 == this.okStr) {
				if (var2 == this.newGameForm) {
					this.sub_39a();
					this.sub_310(2);
				} else {
					this.sub_310(1);
				}
			}
		}

		if (var2 == this.sendList && var1 == List.SELECT_COMMAND) {
			int var4 = this.sendList.getSelectedIndex();
			this.sub_2e9(var4, !this.sub_100(var4));
		} else if (var2 == this.var_39a) {
			String var3 = this.var_39a.getString(this.var_39a
					.getSelectedIndex());
			if (var3.equals(this.continueOrStartCom.getLabel())) {
				this.sub_310(2);
			} else if (var3.equals(this.headCom.getLabel())) {
				super.var_3ef = 1;
				this.setCurDisp(this.newGameForm);
			} else if (var3.equals(this.helpCom.getLabel())) {
				this.sub_310(4);
			} else if (var3.equals(this.highscoresCom.getLabel())) {
				this.sub_310(3);
			} else if (var3.equals(this.aboutCom.getLabel())) {
				this.sub_310(5);
			} else if (var3.equals(this.sendCom.getLabel())) {
				this.sub_359(6);
			} else if (var3.equals(this.quitCom.getLabel())) {
				super.arena3.destroyApp();
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
