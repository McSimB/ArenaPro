package com.elkware.midp.games.colorng.arena.high;

import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Form;

class PhotoThread extends Thread {

	private CommandListener var_30;
	private int var_56;
	private final Arena arena;

	PhotoThread(Arena var1, CommandListener var2, int var3, MoreCanvas var4) {
		this(var1, var2, var3);
	}

	private PhotoThread(Arena var1, CommandListener var2, int var3) {
		this.arena = var1;
		this.var_30 = var2;
		this.var_56 = var3;
	}

	public void run() {
		switch (this.var_56) {
		case 1:
			Arena.sub_6af(this.arena).sub_ffe();
			Arena.sub_6f7(this.arena, Arena.sub_6af(this.arena));
			Arena.sub_6af(this.arena).setPercent(0);
			if (Arena.sub_865(this.arena)) {
				Arena.sub_6f7(this.arena,
						Arena.sub_814(this.arena));
			} else if (Arena.sub_884(this.arena)) {
				Arena.sub_8c9(this.arena, null);
				Form selectFotoForm = new Form(this.arena.getStr(401), arena);
				selectFotoForm.append(this.arena.getStr(403)); // There are no images in the camera's directory.
				selectFotoForm.setCommandListener(this.var_30);
				selectFotoForm.addCommand(Arena.sub_8f2(this.arena));
				Arena.sub_6af(this.arena).setPercent(100);
				Arena.sub_6f7(this.arena, selectFotoForm);
			} else {
				this.arena.commandManage(14);
			}

			return;
		case 2:
			try {
				Arena.sub_6af(this.arena).sub_ffe();
				this.arena.var_e61 = true;
				Arena.sub_6af(this.arena).var_535 = true;
				Arena.sub_6af(this.arena).setPercent(0);
				Arena.sub_6f7(this.arena,
						Arena.sub_6af(this.arena));
				Arena.sub_6af(this.arena).setPercent(10);
				Arena.sub_7b8(
						this.arena,
						Arena.sub_814(this.arena).getString(
								Arena.sub_814(this.arena)
										.getSelectedIndex()));
				this.arena.var_de1 = 45;
				Arena.sub_760(this.arena,
						Arena.sub_731(this.arena));
			} catch (Exception var2) {
				;
			}

			Arena.sub_6af(this.arena).setPercent(100);
			this.arena.var_e61 = false;
			Arena.sub_6af(this.arena).var_3246 = true;
			Arena.sub_6af(this.arena).sub_6c0();
			return;
		case 3:
			Arena.sub_6f7(this.arena, Arena.sub_6af(this.arena));
			Arena.sub_6af(this.arena).setPercent(0);
			Arena.sub_6af(this.arena).sub_f99(this.arena.var_d78);
			this.arena.var_d78 = null;
			System.gc();
			this.arena.var_de1 = 18;
			this.arena.var_e61 = true;
			Arena.sub_760(this.arena, Arena.sub_731(this.arena));
			this.arena.var_e61 = false;
			Arena.sub_6af(this.arena).var_535 = false;
			Arena.sub_6af(this.arena).setPercent(95);
			Arena.sub_6af(this.arena).sub_f51(this.arena.var_d78);
			this.arena.commandManage(25);
			Arena.sub_6af(this.arena).setPercent(100);
			return;
		default:
		}
	}

}
