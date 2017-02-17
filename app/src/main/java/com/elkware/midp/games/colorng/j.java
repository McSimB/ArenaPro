package com.elkware.midp.games.colorng;

import com.elkware.midp.games.a;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.List;

public abstract class j extends a implements CommandListener {

	Class_12b var_3c;
	boolean var_81;
	int var_c5;
	private List var_dd;
	private boolean var_148 = true;

	public abstract Class_12b sub_5da();

	public void startApp() {
		if (this.var_3c != null) {
			Display.getDisplay(this).setCurrent(this.var_3c);
			this.var_3c.showNotify();
		} else {
			super.startApp();
			if ((this.var_c5 = this.sub_dc(5030)) == -1) {
				this.var_c5 = 15;
			}

			this.var_81 = this.sub_dc(5035) != 0;

			try {
				this.var_3c = this.sub_5da();
			} catch (Exception var2) {
				this.sub_4e9("getGameCanvas: " + var2);
				return;
			}

			if (this.sub_2e6(this.var_3c)) {
				this.var_3c.sub_e9a();
				if (this.var_81) {
					this.sub_60();
				} else {
					this.var_3c.sub_248();
				}

			}
		}
	}

	private void sub_60() {
		if ((this.var_c5 & 3) > 0 && (super.var_166 || super.var_b4)) {
			this.var_dd = new List(this.sub_383(3) + " " + this.sub_383(4)
					+ "?", 3,
					new String[] { this.sub_383(7), this.sub_383(8) },
					(Image[]) null);
			this.var_dd.setCommandListener(this);
			this.var_148 = false;
			super.var_52.setCurrent(this.var_dd);
		} else {
			this.var_3c.sub_248();
		}

	}

	public void commandAction(Command var1, Displayable var2) {
		super.commandAction(var1, var2);
		if (!this.var_148) {
			this.var_148 = true;
			boolean var3 = this.var_dd.isSelected(0);
			this.var_dd = null;
			if (!var3) {
				boolean[] var4 = this.var_3c.sub_6e();
				int var5 = 0;
				if ((this.var_c5 & 1) > 0) {
					var4[var5++] = false;
				}

				if ((this.var_c5 & 2) > 0) {
					var4[var5] = false;
				}

				this.var_3c.sub_af(var4);
			}

			this.var_3c.sub_248();
		}

	}

	public void pauseApp() {
		if (this.var_3c != null) {
			this.var_3c.hideNotify();
		}

		this.notifyPaused();
	}

	public void destroyApp(boolean var1) {
		this.var_3c.sub_267();
		this.var_3c.sub_ec7();
		this.notifyDestroyed();
	}
}
