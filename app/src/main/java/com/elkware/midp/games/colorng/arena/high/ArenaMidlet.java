package com.elkware.midp.games.colorng.arena.high;

import android.os.Bundle;

import com.elkware.midp.games.colorng.Arena3;
import com.elkware.midp.games.colorng.CanvasView3;

import java.io.DataInputStream;
import java.util.Enumeration;

import javax.microedition.io.Connector;
import javax.microedition.io.file.FileConnection;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.List;
import javax.microedition.lcdui.TextBox;
import javax.wireless.messaging.BinaryMessage;
import javax.wireless.messaging.MessageConnection;
import javax.wireless.messaging.MessagePart;
import javax.wireless.messaging.MultipartMessage;

public class ArenaMidlet extends Arena3 implements CommandListener {

	public static boolean var_24 = true;
	private List var_85;
	private List var_be;
	private List var_114;
	private List var_144;
	private List var_185;
	private List var_1cc;
	private Command var_267;
	private Command var_2b9;
	private Command var_2f6;
	private Command var_353;
	private Command var_37e;
	private Command var_394;
	private Command var_3c4;
	private Command var_41a;
	private Command var_442;
	private Command var_460;
	private Command var_46d;
	private Command var_491;
	private Command var_4a4;
	private Command var_4e2;
	private Command var_52c;
	private Form var_573;
	private Form var_5bd;
	private Form var_62f = null;
	private List var_644;
	private List var_670;
	private List var_6a4;
	private List var_6ea;
	private List var_730;
	private List var_787;
	private CanvasView canvasView;
	private int var_845;
	private int var_8c5;
	private String var_91e;
	private TextBox var_940;
	private TextBox var_9a1;
	public boolean var_9d2 = true;
	public boolean var_9f3 = true;
	private boolean var_a44 = false;
	private boolean var_a8d;
	private boolean var_aa0 = false;
	private Thread var_ab2 = new Class_10(this);
	public boolean var_af2 = false;
	private Class_177 var_b43 = null;
	private boolean var_b9f = false;
	public int var_bd9 = -1;
	private int var_c15 = 0;
	private byte[] var_c76 = null;
	private byte[][] var_cb0 = (byte[][]) null;
	private boolean[] var_cf4 = null;
	private String var_d05;
	private boolean var_d38;
	private boolean var_d64 = true;
	public Image var_d78;
	public int var_de1 = 18;
	public boolean var_e61 = false;
	private TextBox var_f23 = null;

	public ArenaView arenaView;

	public ArenaMidlet() {
		String var_88a = "033751AD";
		this.sub_291(var_88a);
	}

	private synchronized void sub_11(Displayable var1) {
		this.var_9d2 = var1 != this.canvasView;
		this.var_9f3 = var1 == this.canvasView;

		try {
			Thread.currentThread();
			Thread.sleep(50L);
		} catch (Exception var3) {
			var3.printStackTrace();
		}

		super.var_52.setCurrent(var1);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		arenaView = new ArenaView(this);
		setContentView(arenaView);
		startApp();
	}

	@Override
	public void startApp() {
		if (!this.var_a44) {
			this.var_a44 = true;
			super.startApp();
			this.canvasView.sub_5d4();
			this.var_2f6 = new Command(this.sub_383(215), 2, 0);
			this.var_353 = new Command(this.sub_383(220), 1, 0);
			this.var_37e = new Command(this.sub_383(220), 1, 0);
			this.var_394 = new Command(this.sub_383(222), 2, 0);
			this.var_2b9 = new Command("", 2, 0);
			Command var_230 = new Command("", 2, 0);
			this.var_267 = new Command("", 2, 0);
			this.var_3c4 = new Command(this.sub_383(215), 2, 0);
			this.var_41a = new Command(this.sub_383(459), 2, 0);
			this.var_442 = new Command(this.sub_383(220), 4, 0);
			this.var_460 = new Command(this.sub_383(220), 4, 0);
			this.var_46d = new Command(this.sub_383(400), 1, 0);
			this.var_491 = new Command(this.sub_383(400), 1, 0);
			this.var_4a4 = new Command(this.sub_383(12), 2, 0);
			this.var_52c = new Command(this.sub_383(523), 2, 0);
			this.var_4e2 = new Command(this.sub_383(522), 1, 0);

			this.var_670 = new List(this.sub_383(208), 3);
			for (int var1 = 0; var1 < 5; ++var1) {
				this.var_670.append(this.sub_383(209 + var1), null);
			}

			this.var_670.addCommand(this.var_2f6);
			this.var_670.setCommandListener(this);
			this.var_644 = new List(this.sub_383(206), 2);
			this.var_644.append(this.sub_383(231), null);
			this.var_644.append(this.sub_383(233), null);
			this.var_644.append(this.sub_383(230), null);
			this.var_644.addCommand(this.var_394);
			this.var_644.setCommandListener(this);
			this.var_940 = new TextBox(this.sub_383(219), this.canvasView.var_2f0, 12, 0);
			this.var_940.addCommand(this.var_37e);
			this.var_940.addCommand(this.var_3c4);
			this.var_940.setCommandListener(this);
			this.var_9a1 = new TextBox(this.sub_383(454), "", 30, 3);
			this.var_9a1.addCommand(this.var_460);
			this.var_9a1.addCommand(this.var_3c4);
			this.var_9a1.setCommandListener(this);
			this.var_573 = new Form(this.sub_383(217));
			String var4 = this.getAppProperty("MIDlet-Version");
			if (var4 == null) {
				var4 = "";
			}

			this.var_573.append("(c) 2003/2004\nelkware &\nSiemens mobile\n\ndeveloped by\nelkware GmbH\n\nwww.elkware.com\n\nVersion "
							+ var4 + "\nDate " + "02.06.04");
			this.var_573.addCommand(this.var_52c);
			this.var_573.setCommandListener(this);
			this.var_6ea = new List(this.sub_383(285), 3, new String[]{
					this.sub_383(283), this.sub_383(284)}, null);
			this.var_6ea.setCommandListener(this);
			this.var_6ea.addCommand(this.var_2f6);
			this.var_730 = new List(this.sub_383(285), 3,
					new String[]{this.sub_383(281)}, null);
			this.var_730.setCommandListener(this);
			this.var_a8d = this.sub_dc(472) == 1;
			String[] var2;
			if (this.var_a8d) {
				var2 = new String[]{this.sub_383(202), this.sub_383(203),
						this.sub_383(204), this.sub_383(206),
						this.sub_383(218), this.sub_383(217), this.sub_383(207)};
			} else {
				var2 = new String[]{this.sub_383(202), this.sub_383(203),
						this.sub_383(286), this.sub_383(204),
						this.sub_383(206), this.sub_383(218),
						this.sub_383(217), this.sub_383(207)};
			}

			this.var_85 = new List("Contest Arena Pro", 3, var2, null);
			this.var_85.setCommandListener(this);
			this.var_85.addCommand(this.var_267);
			String[] var3 = new String[]{this.sub_383(520)};
			this.var_144 = new List(this.sub_383(286), 3, var3, null);
			this.var_144.addCommand(var_230);
			this.var_144.setCommandListener(this);
			this.var_114 = new List(this.sub_383(282), 3, new String[]{
					this.sub_383(201), this.sub_383(216), this.sub_383(521),
					this.sub_383(205), this.sub_383(206), this.sub_383(218),
					this.sub_383(217), this.sub_383(207)}, null);
			this.var_114.addCommand(var_230);
			this.var_114.setCommandListener(this);
			this.var_be = new List("", 3, new String[]{this.sub_383(240),
					this.sub_383(241), this.sub_383(242), this.sub_383(243),
					this.sub_383(222)}, null);
			this.var_be.addCommand(var_230);
			this.var_be.setCommandListener(this);
			this.var_185  = new List("", 3, new String[]{this.sub_383(226),
					this.sub_383(215)}, null);
			this.var_185.addCommand(var_230);
			this.var_185.setCommandListener(this);
			this.var_1cc = new List("", 3, new String[]{this.sub_383(226),
					this.sub_383(215)}, null);
			this.var_1cc.addCommand(var_230);
			this.var_1cc.setCommandListener(this);
		}
	}

	public void sub_48() {
		this.sub_11(this.var_be);
	}

	public void sub_90() {
		this.sub_11(this.var_185);
	}

	public void sub_a7() {
		this.var_af2 = true;
		this.sub_327(10);
		this.sub_11(this.var_85);
	}

	public void sub_df() {
		this.canvasView.sub_af9();
		this.var_af2 = true;
		this.sub_327(14);
	}

	private void sub_fa(boolean var1) {
		if (var1 && this.var_a8d) {
			Image downloadMoreImage = this.canvasView.openImage(230);
			CanvView var3 = new CanvView(this, downloadMoreImage);
			super.var_52.setCurrent(var3);
			this.var_ab2.start();
		} else {
			this.destroyApp(true);
		}
	}

	public void sub_14a() {
		this.canvasView.sub_8ab();
		this.canvasView.var_c5d = null;
		System.gc();
		if (this.canvasView.var_270a) {
			this.canvasView.sub_769(0, 64, -1, true);
		}

		this.canvasView.sub_ffe();
		this.var_af2 = true;
		this.sub_327(10);
	}

	public void sub_16b() {
		this.canvasView.sub_8ab();
		this.canvasView.var_92a = false;
		this.canvasView.var_c8a = null;
		this.canvasView.sub_54();
		this.canvasView.sub_ffe();
		this.var_af2 = true;
		this.sub_327(10);
	}

	public void sub_1b1() {
		this.var_d78 = null;
		System.gc();
		this.canvasView.var_535 = false;
		this.sub_327(14);
	}

	public void commandAction(Command var1, Displayable var2) {
		super.commandAction(var1, var2);
		if (var1 == this.var_267) {
			this.sub_fa(false);
		} else if (var1 == this.var_2b9) {
			this.sub_14a();
		} else {
			int var5;
			if (var2 == this.var_85) {
				var5 = this.var_85.getSelectedIndex();
				if (this.var_a8d && var5 > 1) {
					++var5;
				}

				switch (var5) {
					case 0:
						this.canvasView.var_91b = this.canvasView.var_92a = false;
						this.sub_327(11);
						break;
					case 1:
						this.canvasView.var_91b = true;
						this.canvasView.var_92a = false;
						if (this.canvasView.var_270a) {
							this.canvasView.sub_7c4();
							this.canvasView.sub_769(0, 67, 1, true);
						}

						if (this.canvasView.sub_ddd()) {
							this.sub_327(22);
						} else {
							this.canvasView.var_35e9 = true;
							this.sub_327(11);
						}

						this.canvasView.sub_1035();
						break;
					case 2:
						this.canvasView.sub_704(0);
						this.sub_11(this.canvasView);
						this.canvasView.var_30a2 = true;
						this.canvasView.sub_6c0();
						this.canvasView.var_92a = true;
						this.canvasView.var_91b = false;
						this.canvasView.var_a17 = true;
						this.canvasView.sub_704(10);
						this.sub_327(29);
						break;
					case 3:
						this.canvasView.sub_a65();
						this.sub_327(14);
						this.canvasView.sub_1035();
						break;
					case 4:
						this.sub_327(15);
						break;
					case 5:
						this.sub_327(17);
						break;
					case 6:
						this.sub_327(16);
						break;
					case 7:
						this.sub_fa(true);
				}

			} else if (var2 == this.var_be) {
				if (var1.getCommandType() == 2) {
					this.sub_a7();
				} else {
					var5 = this.var_be.getSelectedIndex();
					switch (var5) {
						case 0:
							this.canvasView.var_4c6 = true;
							this.sub_327(18);
							break;
						case 1:
							this.canvasView.var_4c6 = false;
							this.sub_327(19);
							break;
						case 2:
							Display.getDisplay(this).setCurrent(this.var_9a1);
							break;
						case 3:
							this.sub_327(23);
							break;
						case 4:
							this.sub_11(this.canvasView);
					}

				}
			} else if (var2 == this.var_185) {
				if (var1.getCommandType() == 2) {
					this.sub_df();
				} else {
					var5 = this.var_185.getSelectedIndex();
					switch (var5) {
						case 0:
							this.canvasView.sub_a65();
							this.var_af2 = true;
							this.sub_327(14);
							break;
						case 1:
							this.sub_df();
					}

				}
			} else if (var2 == this.var_1cc) {
				if (var1.getCommandType() == 2) {
					this.sub_1b1();
				} else {
					var5 = this.var_1cc.getSelectedIndex();
					switch (var5) {
						case 0:
							(new Class_71(this, this, 3, null)).start();
							break;
						case 1:
							this.sub_1b1();
					}

				}
			} else if (var2 == this.var_114) {
				if (var1.getCommandType() == 2) {
					this.canvasView.sub_9e4();
				} else {
					var5 = this.var_114.getSelectedIndex();
					switch (var5) {
						case 0:
							this.canvasView.sub_9e4();
							break;
						case 1:
							this.canvasView.var_2de8 = false;
							this.canvasView.sub_704(0);
							this.canvasView.sub_7c4();
							this.canvasView.sub_8ab();
							break;
						case 2:
							this.canvasView.var_2de8 = false;
							this.sub_63b();
							break;
						case 3:
							this.canvasView.var_2de8 = true;
							this.sub_11(this.canvasView);
							break;
						case 4:
							this.canvasView.var_2de8 = false;
							this.sub_327(15);
							break;
						case 5:
							this.canvasView.var_2de8 = false;
							this.sub_327(17);
							break;
						case 6:
							this.canvasView.var_2de8 = false;
							this.sub_327(16);
							break;
						case 7:
							this.sub_fa(true);
					}

				}
			} else if (var2 == this.var_730) {
				var5 = this.var_730.getSelectedIndex();
				switch (var5) {
					case 0:
						this.var_730.removeCommand(this.var_2b9);
						this.canvasView.var_2976 = false;
						this.canvasView.var_35b0 = false;
						if (this.canvasView.var_37be) {
							this.canvasView.sub_8ab();
						} else {
							if (this.canvasView.var_3706 == 0) {
								this.canvasView.var_891 = Class_2b8.sub_62(4) + 1;
							}

							this.canvasView.var_8f4 = Class_2b8
									.sub_62(CanvasView.var_154[this.canvasView.var_891]) + 1;
						}

						this.canvasView.sub_171(0);
						this.sub_11(this.canvasView);
					default:
				}
			} else if (var2 == this.var_6ea) {
				if (var1 == this.var_2f6) {
					this.canvasView.sub_8ab();
					this.canvasView.var_92a = false;
					this.canvasView.var_c8a = null;
					this.canvasView.sub_54();
					this.canvasView.sub_ffe();
					this.var_af2 = true;
					this.sub_327(10);
				} else {
					this.canvasView.var_35e9 = this.var_6ea.getSelectedIndex() == 1;
					if (this.canvasView.var_35e9) {
						this.sub_327(11);
					} else {
						this.canvasView.sub_704(0);
						this.sub_327(13);
					}

				}
			} else if (var2 == this.var_144) {
				if (var1.getCommandType() == 2) {
					this.sub_16b();
				} else {
					var5 = this.var_144.getSelectedIndex();
					switch (var5) {
						case 0:
							this.canvasView.var_891 = 5;
							this.canvasView.var_8f4 = 1;
							this.canvasView.var_35b0 = this.canvasView.var_a17 = false;
							this.sub_11(this.canvasView);
							return;
						case 1:
							this.canvasView.sub_ffe();
							this.canvasView.var_2f12 = new Thread(this.canvasView);
							this.canvasView.var_2f12.start();
							return;
						default:
					}
				}
			} else {
				if (var1.getCommandType() == 2) {
					switch (this.var_845) {
						case 16:
							this.var_af2 = true;
							this.sub_327(10);
							return;
						case 17:
							this.var_af2 = true;
							this.sub_327(10);
							return;
						case 25:
							this.var_af2 = true;
							this.sub_327(10);
							return;
					}
				}

				if (var1.getCommandType() == 2 && this.var_845 == 15) {
					this.canvasView.var_395[0] = this.var_644.isSelected(2);
					this.canvasView.var_395[1] = this.var_644.isSelected(0);
					this.canvasView.var_395[2] = false;
					this.canvasView.var_395[3] = this.var_644.isSelected(1);
					this.canvasView.var_395[4] = false;
					this.canvasView.sub_ec7();
					this.canvasView.sub_7c4();
					if (this.canvasView.var_270a) {
						this.canvasView.sub_769(0, 64, 10, true);
					}

					this.canvasView.sub_116d(this.canvasView.var_395[1]);
					this.sub_327(10);
				} else {
					if (var1 == this.var_46d) {
						this.var_62f = new Form(this.sub_383(400));
						this.var_62f.append(this.sub_383(406));
						this.var_62f.setCommandListener(this);
						this.sub_327(28);
					} else if (var1 == this.var_491) {
						this.var_62f = new Form(this.sub_383(400));
						this.var_62f.append(this.sub_383(455));
						this.var_62f.setCommandListener(this);
						this.sub_327(28);
					} else if (var1 == this.var_41a) {
						this.var_c76 = null;
						this.var_af2 = true;
						this.sub_327(10);
					} else if (var1 == this.var_442) {
						this.sub_11(this.canvasView);
						this.canvasView.sub_704(0);
						this.canvasView.sub_679(this.canvasView);
					} else if (var1 != this.var_2f6 && var1 != this.var_394
							&& var1 != this.var_52c) {
						if (var1.getCommandType() == 1 && this.var_845 == 11) {
							this.canvasView.var_891 = this.var_670
									.getSelectedIndex();
							if (this.canvasView.var_91b) {
								this.canvasView.sub_704(0);
								this.sub_11(this.canvasView);
								this.canvasView.sub_704(0);
								this.sub_327(13);
							} else {
								this.sub_327(12);
							}
						} else if (var1.getCommandType() == 1
								&& this.var_845 == 12) {
							this.canvasView.var_8f4 = this.var_6a4
									.getSelectedIndex() + 1;
							this.sub_327(13);
						} else if (var1 == this.var_3c4) {
							this.var_af2 = true;
							this.canvasView.var_4c6 = false;
							this.canvasView.var_4ee = false;
							this.sub_327(14);
							this.canvasView.var_4c6 = false;
							this.canvasView.var_4ee = false;
						} else {
							String var3;
							if (var1 == this.var_37e) {
								this.canvasView.sub_a84(false);
								var3 = this.var_940.getString().replace('\n',
										' ');
								this.canvasView.var_2f0 = var3.substring(0,
										Math.min(var3.length(), 10));
								this.sub_327(19);
							} else if (var1 == this.var_353) {
								this.canvasView.var_4ee = false;
								this.sub_11(this.canvasView);
								this.canvasView.sub_6c0();
							} else if (this.var_845 == 23) {
								this.sub_11(this.canvasView);
								this.sub_327(24);
							} else if (var1 == this.var_460) {
								this.sub_11(this.canvasView);
								this.canvasView.sub_6a1(this.canvasView);
							} else if (var1 == this.var_4e2) {
								var3 = this.var_f23.getString();
								Form var4 = new Form(this.sub_383(19));
								var4.append(this.sub_383(127));
								var4.addCommand(this.var_394);
								var4.setCommandListener(this);
								this.sub_11(var4);
								(new Class_236(this, var3, null))
										.start();
							}
						}
					} else {
						this.canvasView.sub_704(0);
						if (this.canvasView.var_92a) {
							this.canvasView.sub_8ab();
							this.canvasView.var_92a = false;
							this.canvasView.var_c8a = null;
						}

						this.canvasView.sub_704(25);
						this.canvasView.sub_704(50);
						if (this.canvasView.var_91b && this.canvasView.var_37be) {
							this.canvasView.var_35b0 = false;
							this.canvasView.sub_8ab();
							this.canvasView.var_c5d = null;
							System.gc();
						}

						this.canvasView.sub_704(75);
						this.canvasView.sub_704(100);
						this.canvasView.sub_54();
						this.canvasView.repaint();
						this.canvasView.serviceRepaints();
						this.var_af2 = true;
						this.sub_327(10);
						this.canvasView.sub_ffe();
					}

				}
			}
		}
	}

	public void sub_1fb() {
		this.canvasView.sub_704(0);
		boolean var1 = false;
		byte var2 = -1;
		String var3 = this.var_9a1.getString();
		String var4 = String.valueOf(this.var_8c5);
		String var5 = "sms://" + var3 + ":" + var4;
		MessageConnection var6 = null;

		try {
			byte[] var7 = this.sub_5a0();
			int var8 = 0;
			this.canvasView.sub_704(90);
			var6 = (MessageConnection) Connector.open(var5);

			for (int var13 = 0; var13 < 3; ++var13) {
				byte[] var9 = new byte[2 + Math.min(125, var7.length - var8)];
				if (var9.length > 2) {
					var9[0] = (byte) var13;
					System.arraycopy(var7, var8, var9, 2, var9.length - 2);
					var8 += var9.length - 2;
					if (var8 == var7.length) {
						var9[1] = 1;
					}

					BinaryMessage var10 = (BinaryMessage) var6.newMessage("binary");
					var10.setAddress(var5);
					var10.setPayloadData(var9);
					var6.send(var10);
					this.canvasView.sub_704(90 + (var13 + 1) * 2);
				}
			}

			var6.close();
			this.canvasView.sub_704(100);
		} catch (Exception var12) {
			var1 = true;
			if (var6 != null) {
				try {
					var6.close();
				} catch (Exception var11) {
					var11.printStackTrace();
				}
			}

			this.canvasView.sub_704(100);
			this.sub_24e("sendSMS: " + var12 + ", sms #" + var2);
		}

		if (!var1) {
			this.sub_24e(this.sub_383(456));
		}

		this.var_af2 = true;
		this.sub_327(14);
	}

	public void sub_24e(String var1) {
		try {
			this.sub_4e9(var1);
			Thread.currentThread();
			Thread.sleep(5000L);
		} catch (Exception var3) {
			var3.printStackTrace();
		}

	}

	public void sub_25c() {
		this.sub_11(this.var_1cc);
	}

	public void sub_27c() {
		this.sub_11(this.var_114);
	}

	public void sub_28d() {
		this.var_730.addCommand(this.var_2b9);
		this.sub_11(this.var_730);
	}

	public void sub_2ec() {
		if (this.canvasView.var_2a7b != null && this.var_144.size() == 1) {
			this.var_144.append(this.sub_383(292), null);
		}

		this.sub_11(this.var_144);
	}

	public void sub_327(int var1) {
		if (var1 != this.var_845 || this.var_af2) {
			this.var_845 = var1;
			int var4;
			int var6;
			switch (var1) {
				case 10:
					this.canvasView.var_70c = null;
					this.canvasView.sub_ffe();
					System.gc();
					if (this.canvasView.var_3463) {
						this.sub_27c();
					} else {
						if (this.var_d38) {
							Form var_604 = new Form(this.sub_383(457));
							var_604.append(this.sub_383(458) + " "
									+ this.var_d05);
							if (this.var_b9f) {
								var_604.append("\n" + this.sub_383(460));
							}

							var_604.addCommand(this.var_442);
							var_604.addCommand(this.var_41a);
							var_604.setCommandListener(this);
							this.sub_11(var_604);
							this.var_d38 = false;
							return;
						}

						this.sub_11(this.var_85);
					}
					break;
				case 11:
					this.sub_11(this.var_670);
					break;
				case 12:
					if (this.canvasView.var_891 != 0) {
						this.var_6a4 = new List(this.sub_383(300), 3);
						var4 = Math.max(2, this.canvasView.var_891);

						for (var6 = 0; var6 < CanvasView.var_154[this.canvasView.var_891]; ++var6) {
							this.var_6a4.append(
									this.sub_383(CanvasView.var_1a6[var4 + var6 / 5
											% 5]
											+ var6 % 5), null);
						}

						this.var_6a4.addCommand(this.var_2f6);
						this.var_6a4.setCommandListener(this);
						this.sub_11(this.var_6a4);
					} else {
						this.sub_327(13);
					}
					break;
				case 13:
					this.sub_11(this.canvasView);
					this.canvasView.sub_6b4(this.canvasView);
					break;
				case 14:
					this.canvasView.var_4ee = false;
					if (this.canvasView.sub_a8()) {
						this.canvasView.sub_110();
					}

					this.sub_11(this.canvasView);
					this.canvasView.sub_6c0();
					break;
				case 15:
					this.var_644.setSelectedFlags(new boolean[]{
							this.canvasView.var_395[1], this.canvasView.var_395[3],
							this.canvasView.var_395[0]});
					this.sub_11(this.var_644);
					break;
				case 16:
					this.sub_11(this.var_573);
					break;
				case 17:
					if (this.var_5bd == null) {
						this.var_5bd = new Form(this.sub_383(218));
						String var5 = this.sub_383(200);

						while ((var6 = var5.indexOf("\nn")) != -1) {
							var5 = var5.substring(0, var6) + '\n'
									+ var5.substring(var6 + 2, var5.length() - var6 - 2);
						}

						this.var_5bd.append(var5);
						this.var_5bd.addCommand(this.var_52c);
						this.var_5bd.setCommandListener(this);
					}

					this.sub_11(this.var_5bd);
					break;
				case 18:
					this.sub_11(this.var_940);
					break;
				case 19:
					this.canvasView.var_535 = false;
					this.canvasView.var_5fb = null;

					for (var4 = 0; var4 < 4; ++var4) {
						this.canvasView.var_295[var4] = this.canvasView.var_234[var4 + 2];
					}

					this.canvasView.var_4ee = true;
					this.canvasView.sub_aad();
					this.sub_11(this.canvasView);
					this.canvasView.sub_6c0();
					break;
				case 20:
					this.sub_11(this.canvasView);
				case 21:
				case 26:
				case 27:
				default:
					break;
				case 22:
					this.sub_11(this.var_6ea);
					break;
				case 23:
					(new Class_71(this, this, 1, null)).start();
					break;
				case 24:
					(new Class_71(this, this, 2, null)).start();
					break;
				case 25:
					Form var2 = new Form(this.sub_383(404));
					var2.append(this.sub_383(402));
					var2.setCommandListener(this);
					var2.addCommand(this.var_353);
					this.sub_11(var2);
					break;
				case 28:
					this.var_62f.addCommand(this.var_4a4);
					this.sub_11(this.var_62f);
					break;
				case 29:
					this.sub_11(this.canvasView);
					this.canvasView.var_92a = true;
					this.sub_327(13);
			}

			this.var_af2 = false;
		}
	}

	public void sub_371() {
		this.var_8c5 = this.sub_dc(5105);
		if (this.var_b43 == null) {
			this.var_b43 = new Class_177(this, String.valueOf(this.var_8c5));
			this.var_b43.start();
		}

	}

	public void sub_3d5() {
	}

	public void sub_41d(byte[] var1) {
		this.sub_4ab(var1);
	}

	@Override
	public boolean sub_447() {
		return false;
	}

	public void sub_47a(int var1, String var2) {
		this.sub_3f3(var2);
		boolean var3;

		try {
			var3 = this.sub_29e(var1);
		} catch (Exception var7) {
			return;
		}

		boolean var4 = var3 && super.var_64d != null
				&& super.var_64d[0] != null && super.var_64d[1] != null
				&& super.var_64d[2] != null;
		if (var4) {
			this.canvasView.var_20cb = new String[3];
			this.canvasView.var_20f8 = new int[3];

			for (int var5 = 0; var5 < 3; ++var5) {
				if (super.var_64d[var5] == null) {
					this.canvasView.var_20cb[var5] = "- - -";
				} else {
					this.canvasView.var_20cb[var5] = super.var_64d[var5];
				}

				this.canvasView.var_20f8[var5] = super.var_666[var5];
			}

			try {
				this.canvasView.sub_c65(var1, this.canvasView.var_20cb,
						this.canvasView.var_20f8);
			} catch (Exception var6) {
				var6.printStackTrace();
			}
		} else {
			String var8 = this.sub_309();
			if (var8 != null) {
				this.sub_24e(this.sub_383(24) + " " + var8);
			} else {
				this.sub_24e(this.sub_383(24));
			}
		}

		if (var4) {
			this.canvasView.sub_c2e();
		}

		this.sub_327(29);
		this.canvasView.var_2f12 = null;
	}

	public boolean sub_485() {
		return true;
	}

	public void sub_4ab(byte[] var1) {
		if (this.var_cf4 == null) {
			this.var_cf4 = new boolean[5];
			this.var_cb0 = new byte[5][];
			this.var_c15 = 5;
		}

		this.var_cf4[var1[0]] = true;
		this.var_c15 = var1[1] == 1 ? var1[0] : this.var_c15;
		this.var_cb0[var1[0]] = new byte[var1.length - 2];
		System.arraycopy(var1, 2, this.var_cb0[var1[0]], 0, var1.length - 2);
		System.gc();

		int var2;
		for (var2 = 0; var2 <= this.var_c15; ++var2) {
			if (!this.var_cf4[var2]) {
				return;
			}
		}

		var2 = 0;

		int var3;
		for (var3 = 0; var3 <= this.var_c15; ++var3) {
			var2 += this.var_cb0[var3].length;
		}

		this.var_c76 = new byte[var2];
		var3 = 0;

		int var4;
		for (var4 = 0; var4 <= this.var_c15; ++var4) {
			System.arraycopy(this.var_cb0[var4], 0, this.var_c76, var3,
					this.var_cb0[var4].length);
			var3 += this.var_cb0[var4].length;
		}

		this.var_cb0 = null;
		this.var_cf4 = null;
		System.gc();
		this.var_bd9 = -1;
		this.var_d05 = "";

		for (var4 = 1; var4 < 1 + this.var_c76[0]; ++var4) {
			this.var_d05 = this.var_d05 + (char) this.var_c76[var4];
		}

		this.var_b9f = false;

		for (var4 = 0; var4 < this.canvasView.var_733; ++var4) {
			if (this.var_d05
					.compareTo(this.canvasView.var_b2f[this.canvasView.var_b2f.length
							- this.canvasView.var_733 + var4]) == 0) {
				this.var_bd9 = var4;
				this.var_b9f = true;
			}
		}

		this.var_d38 = true;
	}

	private boolean sub_4f0() {
		this.var_d64 = true;
		this.var_787 = new List(this.sub_383(401), 3);

		try {
			FileConnection var1 = (FileConnection) Connector.open(
					"file:///0:/Pictures", 3);
			this.canvasView.sub_704(35);
			Enumeration var2 = var1.list("*.jpg", false);
			System.gc();
			this.canvasView.sub_704(40);

			while (var2.hasMoreElements()) {
				String var3 = (String) var2.nextElement();
				if (!var3.endsWith("/")) {
					this.var_787.append(var3, (Image) null);
				}
			}

			if (this.var_787.size() == 0) {
				return false;
			}

			var1.close();
			this.canvasView.sub_704(80);
		} catch (Exception var4) {
			this.var_d64 = false;
			this.sub_24e(var4.toString());
		}

		this.canvasView.sub_704(100);
		this.var_787.addCommand(this.var_3c4);
		this.var_787.setCommandListener(this);
		return this.var_787.size() > 0;
	}

	private void sub_532(Image var1, int var2) {
		int[] var3 = new int[3];
		int var4 = var1.getWidth();
		int var5 = var1.getHeight();
		short var6 = 200;

		int var8;
		int var9;
		for (int var7 = 0; var7 < var4; ++var7) {
			for (var8 = 0; var8 < var5; ++var8) {
				var9 = com.siemens.mp.lcdui.Image.getPixelColor(var1, var7,
						var8);
				var3[0] = var9 & 255;
				var3[1] = var9 >> 8 & 255;
				var3[2] = var9 >> 16 & 255;
				if (var3[0] > var6 && var3[1] > var6 && var3[2] > var6) {
					com.siemens.mp.lcdui.Image.setPixelColor(var1, var7, var8,
							var2);
				}
			}
		}

		Graphics var13 = var1.getGraphics();
		var8 = var13.getDisplayColor(var2) | -16777216;
		var9 = var4 / 2;

		for (int var10 = 0; var10 < var5; ++var10) {
			int var11;
			int var12;
			for (var11 = var9; var11 > 1; --var11) {
				if (com.siemens.mp.lcdui.Image
						.getPixelColor(var1, var11, var10) == var8
						&& com.siemens.mp.lcdui.Image.getPixelColor(var1,
						var11 - 1, var10) == var8
						&& com.siemens.mp.lcdui.Image.getPixelColor(var1,
						var11 - 2, var10) == var8) {
					for (var12 = var11; var12 >= 0; --var12) {
						com.siemens.mp.lcdui.Image.setPixelColor(var1, var12,
								var10, var2);
					}

					var11 = 0;
				}
			}

			for (var11 = var9; var11 < var4 - 2; ++var11) {
				if (com.siemens.mp.lcdui.Image
						.getPixelColor(var1, var11, var10) == var8
						&& com.siemens.mp.lcdui.Image.getPixelColor(var1,
						var11 + 1, var10) == var8
						&& com.siemens.mp.lcdui.Image.getPixelColor(var1,
						var11 + 2, var10) == var8) {
					for (var12 = var11; var12 < var4; ++var12) {
						com.siemens.mp.lcdui.Image.setPixelColor(var1, var12,
								var10, var2);
					}

					var11 = var4;
				}
			}
		}

	}

	private boolean sub_552(String var1) {
		try {
			this.canvasView.sub_704(10);
			this.var_d78 = null;
			this.canvasView.var_c21 = null;
			System.gc();
			this.canvasView.sub_704(20);
			if (this.var_de1 == 18) {
				this.var_d78 = com.siemens.mp.lcdui.Image.createImageFromFile(
						"0:/pictures/headTmp.bmp", this.var_de1, this.var_de1);
				this.sub_532(this.var_d78, 7799014);
			} else {
				this.var_d78 = com.siemens.mp.lcdui.Image.createImageFromFile(
						"0:/pictures/" + var1, 64, 48);
				this.canvasView.sub_704(30);
				Image var2 = Image.createImage(this.var_de1, this.var_de1);
				this.canvasView.sub_704(40);
				var2.getGraphics().drawImage(this.var_d78, -9, -2, 0);
				this.canvasView.sub_704(50);
				this.sub_532(var2, -1);
				this.canvasView.sub_704(60);
				com.siemens.mp.lcdui.Image.writeBmpToFile(var2,
						"0:/pictures/headTmp.bmp");
				this.canvasView.sub_704(70);
				this.var_d78 = var2;
				var2 = null;
				this.canvasView.sub_704(80);
			}

			System.gc();
			this.canvasView.sub_704(90);
			return true;
		} catch (Exception var3) {
			this.sub_4e9("computeImage: " + var3);
			return false;
		}
	}

	private byte[] sub_5a0() {
		String var1 = this.canvasView.var_2f0.substring(0,
				Math.min(50, this.canvasView.var_2f0.length()));
		byte[] var2 = var1.getBytes();
		byte var3 = (byte) this.canvasView.var_234[0];
		byte[] var4 = new byte[1];
		this.canvasView.getClass();
		if (var3 >= 4) {
			var4 = new byte[307];
		}

		var4[0] = var3;
		int var_ebf = 6;
		byte[] var5 = new byte[7 + var2.length + var4.length + var_ebf];
		this.canvasView.sub_704(20);
		if (var4.length > 1) {
			Image var6 = null;

			try {
				var6 = this.canvasView.var_f45[var3];
			} catch (Exception var13) {
				var13.printStackTrace();
			}

			byte[] var7 = new byte[3];
			int var8 = var6.getWidth();
			int[] var9 = new int[var8 * var6.getHeight()];
			var6.getRGB(var9, 0, var8, 0, 0, var8, var6.getHeight());

			for (int var10 = 0; var10 < 18; ++var10) {
				for (int var11 = 0; var11 < 17; ++var11) {
					int var12 = var9[var11 + var10 * var8];
					var7[0] = (byte) ((var12 & 255) >> 6);
					var7[2] = (byte) ((var12 >> 16 & 255) >> 5);
					if (var12 >> 24 != 0) {
						var7[1] = (byte) ((var12 >> 8 & 255) >> 5);
					} else {
						var7[0] = 0;
						var7[2] = 0;
						var7[1] = 7;
					}

					var4[1 + var10 * 17 + var11] = (byte) (var7[2] << 5
							| var7[1] << 2 | var7[0]);
				}

				this.canvasView.sub_704(20 + var10 * 3);
			}
		}

		byte var14 = 0;
		int var15 = var14 + 1;
		var5[var14] = (byte) var2.length;

		int var16;
		for (var16 = 0; var16 < var2.length; ++var16) {
			var5[var15++] = var2[var16];
		}

		this.canvasView.sub_704(80);

		for (var16 = 0; var16 < 6; ++var16) {
			var5[var15++] = (byte) this.canvasView.var_234[var16];
		}

		for (var16 = 0; var16 < var4.length; ++var16) {
			var5[var15++] = var4[var16];
		}

		this.canvasView.sub_704(85);
		return var5;
	}

	public void sub_5c4() {
		this.canvasView.sub_704(0);
		this.canvasView.sub_f85(this.var_c76, this.var_d05, this.var_b9f);
		this.var_c76 = null;
		this.var_af2 = true;
		this.canvasView.sub_704(100);
		this.sub_327(10);
	}

	public CanvasView3 getCanvasView() {
		try {
			this.canvasView = new CanvasView(this);
			arenaView.canvasView = this.canvasView;
			return this.canvasView;
		} catch (Exception var2) {
			var2.printStackTrace();
			return null;
		}
	}

	private boolean sub_5f0(Image var1, String var2) {
		String var3 = "ContestArenaProShot";
		if (var2.equals("")) {
			return false;
		} else {
			MessagePart var4 = this.sub_625(var1, "Arena.jpg", "Image1");
			if (var4 == null) {
				return false;
			} else {
				try {
					MessageConnection var5 = (MessageConnection) Connector
							.open(var2);
					MultipartMessage var6 = (MultipartMessage) var5.newMessage("multipart");
					var6.setSubject(var3);
					var6.addAddress("to", var2);
					var6.addMessagePart(var4);
					var5.send(var6);
					var5.close();
					return true;
				} catch (Exception var7) {
					System.out.println("MMSException: " + var7);
					return false;
				}
			}
		}
	}

	private MessagePart sub_625(Image var1, String var2, String var3) {
		MessagePart var4 = null;

		try {
			com.siemens.mp.lcdui.Image.writeImageToFile(var1, "0:/Pictures/"
					+ var2, 2);
			FileConnection var5 = (FileConnection) Connector
					.open("file:///0:/Pictures/" + var2);
			DataInputStream var6 = var5.openDataInputStream();
			var4 = new MessagePart(var6, "image/jpeg", var3, var2,
					(String) null);
			var5.close();
		} catch (Exception var7) {
			var7.printStackTrace();
		}

		return var4;
	}

	private void sub_63b() {
		try {
			this.var_f23 = new TextBox(this.sub_383(526), "mms://", 256, 0);
			this.var_f23.addCommand(this.var_394);
			this.var_f23.addCommand(this.var_4e2);
			this.var_f23.setCommandListener(this);
			this.sub_11(this.var_f23);
		} catch (Exception var2) {
			var2.printStackTrace();
		}

	}

	public void destroyApp(boolean var1) {
		if (this.var_b43 != null) {
			this.var_b43.sub_9d();
			this.var_b43 = null;
		}

		this.canvasView.sub_267();
		this.notifyDestroyed();
	}

	static boolean sub_64f(ArenaMidlet var0, boolean var1) {
		return var0.var_aa0 = var1;
	}

	static boolean sub_690(ArenaMidlet var0) {
		return var0.var_aa0;
	}

	static CanvasView sub_6af(ArenaMidlet var0) {
		return var0.canvasView;
	}

	static boolean sub_6eb(ArenaMidlet var0, Image var1, String var2) {
		return var0.sub_5f0(var1, var2);
	}

	static void sub_6f7(ArenaMidlet var0, Displayable var1) {
		var0.sub_11(var1);
	}

	static String sub_731(ArenaMidlet var0) {
		return var0.var_91e;
	}

	static boolean sub_760(ArenaMidlet var0, String var1) {
		return var0.sub_552(var1);
	}

	static String sub_7b8(ArenaMidlet var0, String var1) {
		return var0.var_91e = var1;
	}

	static List sub_814(ArenaMidlet var0) {
		return var0.var_787;
	}

	static boolean sub_865(ArenaMidlet var0) {
		return var0.sub_4f0();
	}

	static boolean sub_884(ArenaMidlet var0) {
		return var0.var_d64;
	}

	static List sub_8c9(ArenaMidlet var0, List var1) {
		return var0.var_787 = var1;
	}

	static Command sub_8f2(ArenaMidlet var0) {
		return var0.var_3c4;
	}

}
