package com.elkware.midp.games.colorng;

import com.elkware.midp.games.Arena2;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.List;

public abstract class Arena3 extends Arena2 implements CommandListener {

	CanvasView3 canvasView3;
	boolean bool_81;
	int int_c5;
	private List list;
	private boolean bool_148 = true;

	public abstract CanvasView3 getCanvasView();

	@Override
	public void startApp() {
		if (this.canvasView3 != null) {
			Display.getDisplay(this).setCurrent(this.canvasView3);
			this.canvasView3.showNotify();
		} else {
			super.startApp();
			if ((this.int_c5 = this.sub_dc(5030)) == -1) {
				this.int_c5 = 15;
			}

			this.bool_81 = this.sub_dc(5035) != 0;

			try {
				this.canvasView3 = this.getCanvasView();
			} catch (Exception var2) {
				this.sub_4e9("getGameCanvas: " + var2);
				return;
			}

			if (this.sub_2e6()) {
				this.canvasView3.sub_e9a();
				if (this.bool_81) {
					this.sub_60();
				} else {
					this.canvasView3.sub_248();
				}
			}
		}
	}

	private void sub_60() {
		if ((this.int_c5 & 3) > 0 && (super.var_166 || super.var_b4)) {
			this.list = new List(this.getStr(3) + " " + this.getStr(4)
					+ "?", 3,
					new String[] { this.getStr(7), this.getStr(8) }, this);
			this.list.setCommandListener(this);
			this.bool_148 = false;
			super.display.setCurrent(this.list);
		} else {
			this.canvasView3.sub_248();
		}
	}

	@Override
	public void commandAction(Command var1, Displayable var2) {
		super.commandAction(var1, var2);
		if (!this.bool_148) {
			this.bool_148 = true;
			boolean var3 = this.list.isSelected(0);
			this.list = null;
			if (!var3) {
				boolean[] var4 = this.canvasView3.sub_6e();
				int var5 = 0;
				if ((this.int_c5 & 1) > 0) {
					var4[var5++] = false;
				}

				if ((this.int_c5 & 2) > 0) {
					var4[var5] = false;
				}

				this.canvasView3.sub_af(var4);
			}

			this.canvasView3.sub_248();
		}
	}

	@Override
	public void destroyApp(boolean var1) {
		this.canvasView3.sub_267();
		this.canvasView3.sub_ec7();
		this.notifyDestroyed();
	}

}
