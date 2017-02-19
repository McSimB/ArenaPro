package com.elkware.midp.games.colorng.arena.high;

import java.io.IOException;

import javax.microedition.io.Connector;
import javax.microedition.io.PushRegistry;
import javax.wireless.messaging.BinaryMessage;
import javax.wireless.messaging.Message;
import javax.wireless.messaging.MessageConnection;
import javax.wireless.messaging.TextMessage;

class Class_177 extends Thread {

	private String var_1b;
	private byte[] var_4a;
	private String var_8a;
	private MessageConnection var_af;
	private Arena var_fa;
	private String var_115 = "0";
	private boolean var_130 = false;

	public Class_177(Arena var1, String var2) {
		this.var_fa = var1;
		this.var_115 = var2;
	}

	public void sub_46(String var1) {
		String[] var2 = new String[0];
		boolean var3 = false;

		try {
			var2 = PushRegistry.listConnections(true);
			int var4;
			if (var2.length > 0) {
				for (var4 = 0; var4 < var2.length; ++var4) {
					if (!var2[var4].equals("")) {
						this.var_af = (MessageConnection) Connector
								.open(var2[var4]);
						this.sub_8a();
						var3 = true;
						break;
					}
				}
			}

			if (!var3) {
				var2 = PushRegistry.listConnections(false);

				for (var4 = 0; var4 < var2.length; ++var4) {
					if (!var2[var4].equals("")) {
						this.var_af = (MessageConnection) Connector
								.open(var2[var4]);
					}
				}
			}
		} catch (Exception var5) {
			;
		}

	}

	public void run() {
		this.sub_46(this.var_115);

		while (!this.var_130) {
			this.sub_8a();

			try {
				Thread.sleep(500L);
			} catch (Exception var2) {
				;
			}
		}

	}

	public void sub_8a() {
		Message var1 = null;

		try {
			if (this.var_af != null) {
				var1 = this.var_af.receive();
				if (var1 != null) {
					this.var_8a = var1.getAddress();
					if (var1 instanceof TextMessage) {
						this.var_1b = ((TextMessage) var1).getPayloadText();
						this.var_fa.sub_3d5();
					} else {
						this.var_4a = ((BinaryMessage) var1).getPayloadData();
						this.var_fa.sub_41d(this.var_4a);
					}
				}
			}
		} catch (Exception var3) {
			;
		}

	}

	public void sub_9d() {
		this.var_130 = true;
		if (this.var_af != null) {
			try {
				this.var_af.close();
				this.var_af = null;
			} catch (IOException var2) {
				var2.printStackTrace();
			}
		}

	}
}
