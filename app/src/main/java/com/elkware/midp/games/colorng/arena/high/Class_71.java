package com.elkware.midp.games.colorng.arena.high;

import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.List;

class Class_71 extends Thread {

	private CommandListener var_30;
	private int var_56;
	private final ArenaMidlet var_91;

	private Class_71(ArenaMidlet var1, CommandListener var2, int var3) {
		this.var_91 = var1;
		this.var_30 = var2;
		this.var_56 = var3;
	}

	public void run() {
		switch (this.var_56) {
		case 1:
			ArenaMidlet.sub_6af(this.var_91).sub_ffe();
			ArenaMidlet.sub_6f7(this.var_91, ArenaMidlet.sub_6af(this.var_91));
			ArenaMidlet.sub_6af(this.var_91).sub_704(0);
			if (ArenaMidlet.sub_865(this.var_91)) {
				ArenaMidlet.sub_6f7(this.var_91,
						ArenaMidlet.sub_814(this.var_91));
			} else if (ArenaMidlet.sub_884(this.var_91)) {
				ArenaMidlet.sub_8c9(this.var_91, (List) null);
				Form var1 = new Form(this.var_91.sub_383(401));
				var1.append(this.var_91.sub_383(403));
				var1.setCommandListener(this.var_30);
				var1.addCommand(ArenaMidlet.sub_8f2(this.var_91));
				ArenaMidlet.sub_6af(this.var_91).sub_704(100);
				ArenaMidlet.sub_6f7(this.var_91, var1);
			} else {
				this.var_91.sub_327(14);
			}

			return;
		case 2:
			try {
				ArenaMidlet.sub_6af(this.var_91).sub_ffe();
				this.var_91.var_e61 = true;
				ArenaMidlet.sub_6af(this.var_91).var_535 = true;
				ArenaMidlet.sub_6af(this.var_91).sub_704(0);
				ArenaMidlet.sub_6f7(this.var_91,
						ArenaMidlet.sub_6af(this.var_91));
				ArenaMidlet.sub_6af(this.var_91).sub_704(10);
				ArenaMidlet.sub_7b8(
						this.var_91,
						ArenaMidlet.sub_814(this.var_91).getString(
								ArenaMidlet.sub_814(this.var_91)
										.getSelectedIndex()));
				this.var_91.var_de1 = 45;
				ArenaMidlet.sub_760(this.var_91,
						ArenaMidlet.sub_731(this.var_91));
			} catch (Exception var2) {
				;
			}

			ArenaMidlet.sub_6af(this.var_91).sub_704(100);
			this.var_91.var_e61 = false;
			ArenaMidlet.sub_6af(this.var_91).var_3246 = true;
			ArenaMidlet.sub_6af(this.var_91).sub_6c0();
			return;
		case 3:
			ArenaMidlet.sub_6f7(this.var_91, ArenaMidlet.sub_6af(this.var_91));
			ArenaMidlet.sub_6af(this.var_91).sub_704(0);
			ArenaMidlet.sub_6af(this.var_91).sub_f99(this.var_91.var_d78);
			this.var_91.var_d78 = null;
			System.gc();
			this.var_91.var_de1 = 18;
			this.var_91.var_e61 = true;
			ArenaMidlet.sub_760(this.var_91, ArenaMidlet.sub_731(this.var_91));
			this.var_91.var_e61 = false;
			ArenaMidlet.sub_6af(this.var_91).var_535 = false;
			ArenaMidlet.sub_6af(this.var_91).sub_704(95);
			ArenaMidlet.sub_6af(this.var_91).sub_f51(this.var_91.var_d78);
			this.var_91.sub_327(25);
			ArenaMidlet.sub_6af(this.var_91).sub_704(100);
			return;
		default:
		}
	}

	Class_71(ArenaMidlet var1, CommandListener var2, int var3, Class_1bb var4) {
		this(var1, var2, var3);
	}
}
