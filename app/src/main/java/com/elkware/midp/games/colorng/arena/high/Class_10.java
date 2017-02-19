package com.elkware.midp.games.colorng.arena.high;

class Class_10 extends Thread {

	private final Arena arena;

	Class_10(Arena arena) {
		this.arena = arena;
	}

	public void run() {
		long var1 = System.currentTimeMillis();

		while (System.currentTimeMillis() - var1 < 20000L
				&& !Arena.sub_690(this.arena)) {
			try {
				Thread.currentThread();
				Thread.sleep(100L);
			} catch (Exception var4) {
				var4.printStackTrace();
			}
		}

		this.arena.destroyApp(true);
	}

}
