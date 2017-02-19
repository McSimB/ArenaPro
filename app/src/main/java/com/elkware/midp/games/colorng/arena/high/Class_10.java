package com.elkware.midp.games.colorng.arena.high;

class Class_10 extends Thread {

	private final Arena var_16;

	Class_10(Arena var1) {
		this.var_16 = var1;
	}

	public void run() {
		long var1 = System.currentTimeMillis();

		while (System.currentTimeMillis() - var1 < 20000L
				&& !Arena.sub_690(this.var_16)) {
			try {
				Thread.currentThread();
				Thread.sleep(100L);
			} catch (Exception var4) {
				;
			}
		}

		this.var_16.destroyApp(true);
	}
}
