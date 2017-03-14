package com.elkware.midp.games.colorng.arena.high;

import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Form;

class PhotoThread extends Thread {

	private CommandListener var_30;
	private int var_56;
	private final Arena var_91;

	PhotoThread(Arena var1, CommandListener var2, int var3, MoreCanvas var4) {
		this(var1, var2, var3);
	}

	private PhotoThread(Arena var1, CommandListener var2, int var3) {
		this.var_91 = var1;
		this.var_30 = var2;
		this.var_56 = var3;
	}

	public void run() {
		switch (this.var_56) {
		case 1:
			Arena.sub_6af(this.var_91).sub_ffe();
			Arena.sub_6f7(this.var_91, Arena.sub_6af(this.var_91));
			Arena.sub_6af(this.var_91).setPercent(0);
			if (Arena.sub_865(this.var_91)) {
				Arena.sub_6f7(this.var_91,
						Arena.sub_814(this.var_91));
			} else if (Arena.sub_884(this.var_91)) {
				Arena.sub_8c9(this.var_91, null);
				Form selectFotoForm = new Form(this.var_91.getStr(401));
				selectFotoForm.append(this.var_91.getStr(403)); // There are no images in the camera's directory.
				selectFotoForm.setCommandListener(this.var_30);
				selectFotoForm.addCommand(Arena.sub_8f2(this.var_91));
				Arena.sub_6af(this.var_91).setPercent(100);
				Arena.sub_6f7(this.var_91, selectFotoForm);
			} else {
				this.var_91.commandManage(14);
			}

			return;
		case 2:
			try {
				Arena.sub_6af(this.var_91).sub_ffe();
				this.var_91.var_e61 = true;
				Arena.sub_6af(this.var_91).var_535 = true;
				Arena.sub_6af(this.var_91).setPercent(0);
				Arena.sub_6f7(this.var_91,
						Arena.sub_6af(this.var_91));
				Arena.sub_6af(this.var_91).setPercent(10);
				Arena.sub_7b8(
						this.var_91,
						Arena.sub_814(this.var_91).getString(
								Arena.sub_814(this.var_91)
										.getSelectedIndex()));
				this.var_91.var_de1 = 45;
				Arena.sub_760(this.var_91,
						Arena.sub_731(this.var_91));
			} catch (Exception var2) {
				;
			}

			Arena.sub_6af(this.var_91).setPercent(100);
			this.var_91.var_e61 = false;
			Arena.sub_6af(this.var_91).var_3246 = true;
			Arena.sub_6af(this.var_91).sub_6c0();
			return;
		case 3:
			Arena.sub_6f7(this.var_91, Arena.sub_6af(this.var_91));
			Arena.sub_6af(this.var_91).setPercent(0);
			Arena.sub_6af(this.var_91).sub_f99(this.var_91.var_d78);
			this.var_91.var_d78 = null;
			System.gc();
			this.var_91.var_de1 = 18;
			this.var_91.var_e61 = true;
			Arena.sub_760(this.var_91, Arena.sub_731(this.var_91));
			this.var_91.var_e61 = false;
			Arena.sub_6af(this.var_91).var_535 = false;
			Arena.sub_6af(this.var_91).setPercent(95);
			Arena.sub_6af(this.var_91).sub_f51(this.var_91.var_d78);
			this.var_91.commandManage(25);
			Arena.sub_6af(this.var_91).setPercent(100);
			return;
		default:
		}
	}

}
