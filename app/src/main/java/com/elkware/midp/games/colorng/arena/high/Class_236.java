package com.elkware.midp.games.colorng.arena.high;

class Class_236 extends Thread {

	private String var_60;
	private final ArenaMidlet var_d2;

	private Class_236(ArenaMidlet var1, String var2) {
		this.var_d2 = var1;
		this.var_60 = var2;
	}

	public void run() {
		ArenaMidlet.sub_6eb(this.var_d2, ArenaMidlet.sub_6af(this.var_d2)
				.sub_4c8(), this.var_60);
		this.var_d2.var_af2 = true;
		this.var_d2.sub_327(10);
	}

	Class_236(ArenaMidlet var1, String var2, CanvView var3) {
		this(var1, var2);
	}
}
