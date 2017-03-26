package com.elkware.midp.games.colorng.arena.high;

import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Form;

class PhotoThread extends Thread {

	private CommandListener listener;
	private int anInt;
	private final Arena arena;

	PhotoThread(Arena var1, CommandListener var2, int var3, MoreCanvas var4) {
		this(var1, var2, var3);
	}

	private PhotoThread(Arena var1, CommandListener var2, int var3) {
		this.arena = var1;
		this.listener = var2;
		this.anInt = var3;
	}

	public void run() {
		switch (this.anInt) {
		case 1:
			Arena.getMyCanvas(this.arena).threadToNull();
			Arena.setCurDispStatic(this.arena, Arena.getMyCanvas(this.arena));
			Arena.getMyCanvas(this.arena).setPercent(0);
			if (Arena.hasPhotoStatic(this.arena)) {
				Arena.setCurDispStatic(this.arena, Arena.getSelectPhotoList(this.arena));
			} else if (Arena.isNoExcept(this.arena)) {
				Arena.setSelectPhotoList(this.arena, null);
				Form selectFotoForm = new Form(this.arena.getStr(401), arena);
				selectFotoForm.append(this.arena.getStr(403)); // There are no images in the camera's directory.
				selectFotoForm.setCommandListener(this.listener);
				selectFotoForm.addCommand(Arena.getCommandBack(this.arena));
				Arena.getMyCanvas(this.arena).setPercent(100);
				Arena.setCurDispStatic(this.arena, selectFotoForm);
			} else {
				this.arena.commandManage(14);
			}

			return;
		case 2:
			try {
				Arena.getMyCanvas(this.arena).threadToNull();
				this.arena.var_e61 = true;
				Arena.getMyCanvas(this.arena).var_535 = true;
				Arena.getMyCanvas(this.arena).setPercent(0);
				Arena.setCurDispStatic(this.arena, Arena.getMyCanvas(this.arena));
				Arena.getMyCanvas(this.arena).setPercent(10);
				Arena.sub_7b8(
						this.arena,
						Arena.getSelectPhotoList(this.arena).getString(
								Arena.getSelectPhotoList(this.arena).getSelectedIndex()));
				this.arena.var_de1 = 45;
				Arena.sub_760(this.arena, Arena.sub_731(this.arena));
			} catch (Exception var2) {
				var2.printStackTrace();
			}

			Arena.getMyCanvas(this.arena).setPercent(100);
			this.arena.var_e61 = false;
			Arena.getMyCanvas(this.arena).var_3246 = true;
			Arena.getMyCanvas(this.arena).sub_6c0();
			return;
		case 3:
			Arena.setCurDispStatic(this.arena, Arena.getMyCanvas(this.arena));
			Arena.getMyCanvas(this.arena).setPercent(0);
			Arena.getMyCanvas(this.arena).sub_f99(this.arena.var_d78);
			this.arena.var_d78 = null;
			System.gc();
			this.arena.var_de1 = 18;
			this.arena.var_e61 = true;
			Arena.sub_760(this.arena, Arena.sub_731(this.arena));
			this.arena.var_e61 = false;
			Arena.getMyCanvas(this.arena).var_535 = false;
			Arena.getMyCanvas(this.arena).setPercent(95);
			Arena.getMyCanvas(this.arena).sub_f51(this.arena.var_d78);
			this.arena.commandManage(25);
			Arena.getMyCanvas(this.arena).setPercent(100);
			return;
		default:
		}
	}

}
