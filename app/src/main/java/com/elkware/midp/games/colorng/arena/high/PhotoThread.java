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
			Arena.getMyCanvas(this.arena).setPercentLogoProgressBar(0);
			if (Arena.hasPhotoStatic(this.arena)) {
				Arena.setCurDispStatic(this.arena, Arena.getSelectPhotoList(this.arena));
			} else if (Arena.isNoExcept(this.arena)) {
				Arena.setSelectPhotoList(this.arena, null);
				Form selectFotoForm = new Form(this.arena.getStr(401));
				selectFotoForm.append(this.arena.getStr(403)); // There are no images in the camera's directory.
				selectFotoForm.setCommandListener(this.listener);
				selectFotoForm.addCommand(Arena.getCommandBack(this.arena));
				Arena.getMyCanvas(this.arena).setPercentLogoProgressBar(100);
				Arena.setCurDispStatic(this.arena, selectFotoForm);
			} else {
				this.arena.commandManage(14);
			}

			return;
		case 2:
			try {
				Arena.getMyCanvas(this.arena).threadToNull();
				this.arena.boolPhoto = true;
				Arena.getMyCanvas(this.arena).var_535 = true;
				Arena.getMyCanvas(this.arena).setPercentLogoProgressBar(0);
				Arena.setCurDispStatic(this.arena, Arena.getMyCanvas(this.arena));
				Arena.getMyCanvas(this.arena).setPercentLogoProgressBar(10);
				Arena.setPhotoString(
						this.arena,
						Arena.getSelectPhotoList(this.arena).getString(
								Arena.getSelectPhotoList(this.arena).getSelectedIndex()));
				this.arena.photoSize = 45;
				Arena.computeImageStatic(this.arena, Arena.getPhotoString(this.arena));
			} catch (Exception var2) {
				var2.printStackTrace();
			}

			Arena.getMyCanvas(this.arena).setPercentLogoProgressBar(100);
			this.arena.boolPhoto = false;
			Arena.getMyCanvas(this.arena).var_3246 = true;
			Arena.getMyCanvas(this.arena).oneTimeLoop();
			return;
		case 3:
			Arena.setCurDispStatic(this.arena, Arena.getMyCanvas(this.arena));
			Arena.getMyCanvas(this.arena).setPercentLogoProgressBar(0);
			Arena.getMyCanvas(this.arena).addPhotoToStore(this.arena.image);
			this.arena.image = null;
			System.gc();
			this.arena.photoSize = 18;
			this.arena.boolPhoto = true;
			Arena.computeImageStatic(this.arena, Arena.getPhotoString(this.arena));
			this.arena.boolPhoto = false;
			Arena.getMyCanvas(this.arena).var_535 = false;
			Arena.getMyCanvas(this.arena).setPercentLogoProgressBar(95);
			Arena.getMyCanvas(this.arena).addHeadImage(this.arena.image);
			this.arena.commandManage(25);
			Arena.getMyCanvas(this.arena).setPercentLogoProgressBar(100);
			return;
		default:
		}
	}

}
