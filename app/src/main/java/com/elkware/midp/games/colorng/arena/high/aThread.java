package com.elkware.midp.games.colorng.arena.high;

class aThread extends Thread {

	private String var_60;
	private final Arena var_d2;

	private aThread(Arena var1, String var2) {
		this.var_d2 = var1;
		this.var_60 = var2;
	}

	public void run() {
		Arena.sub_6eb(this.var_d2, Arena.getMyCanvas(this.var_d2)
				.sub_4c8(), this.var_60);
		this.var_d2.var_af2 = true;
		this.var_d2.commandManage(10);
	}

	aThread(Arena var1, String var2, MoreCanvas var3) {
		this(var1, var2);
	}
}
