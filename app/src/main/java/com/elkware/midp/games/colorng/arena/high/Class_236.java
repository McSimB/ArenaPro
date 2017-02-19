package com.elkware.midp.games.colorng.arena.high;

class Class_236 extends Thread {

	private String var_60;
	private final Arena var_d2;

	private Class_236(Arena var1, String var2) {
		this.var_d2 = var1;
		this.var_60 = var2;
	}

	public void run() {
		Arena.sub_6eb(this.var_d2, Arena.sub_6af(this.var_d2)
				.sub_4c8(), this.var_60);
		this.var_d2.var_af2 = true;
		this.var_d2.commandManage(10);
	}

	Class_236(Arena var1, String var2, MoreCanvas var3) {
		this(var1, var2);
	}
}
