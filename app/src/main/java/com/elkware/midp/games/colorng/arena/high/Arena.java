package com.elkware.midp.games.colorng.arena.high;

import android.os.Bundle;

import com.elkware.midp.games.colorng.Arena3;
import com.elkware.midp.games.colorng.Canvas3;

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

public class Arena extends Arena3 implements CommandListener {

	public static boolean var_24 = true;
	private List mainMenuList;
	private List warriorSettingsList;
	private List pauseList;
	private List challengeModeList;
	private List saveMenuList;
	private List saveMenuList2;
	private Command mainMenuBack;
	private Command tournamentNextMatchBack;
	private Command gameModeBack;
	private Command commandAccept;
	private Command warriorNameScreenCommand;
	private Command settingsBack;
	private Command textBoxBack;
	private Command incommingWarriorBack;
	private Command incommingWarriorOk;
	private Command phoneNumberOk;
	private Command helpScreenCommand;
	private Command helpScreenCommand2;
	private Command helpBack;
	private Command sendSMSScreenCommand;
	private Command aboutBack;
	private Form aboutForm;
	private Form var_5bd;
	private Form helpForm = null;
	private List settingsList;
	private List gameModeList;
	private List var_6a4;
	private List tournamentList;
	private List tournamentNextMatchList;
	private List var_787;
	private MyCanvas myCanvas;
	private int var_845;
	private int var_8c5;
	private String var_91e;
	private TextBox warriorNameTextBox;
	private TextBox phoneNumberTextBox;
	public boolean isNotMyCanvasCurrent = true;
	public boolean isMyCanvasCurrent = true;
	private boolean var_a44 = false;
	private boolean var_a8d;
	private boolean var_aa0 = false;
	private Thread var_ab2 = new ExitTimer(this);
	public boolean var_af2 = false;
	private Class_177 var_b43 = null;
	private boolean var_b9f = false;
	public int var_bd9 = -1;
	private int var_c15 = 0;
	private byte[] var_c76 = null;
	private byte[][] var_cb0 = null;
	private boolean[] var_cf4 = null;
	private String var_d05;
	private boolean var_d38;
	private boolean var_d64 = true;
	public Image var_d78;
	public int var_de1 = 18;
	public boolean var_e61 = false;
	private TextBox receiverTextBox = null;

	public Arena() {
		String appID = "033751AD";
		this.setAppID(appID);
	}

	private synchronized void setCurrentDisp(Displayable displayable) {
		this.isNotMyCanvasCurrent = displayable != this.myCanvas;
		this.isMyCanvasCurrent = displayable == this.myCanvas;

		try {
			Thread.currentThread();
			Thread.sleep(50L);
		} catch (Exception var3) {
			var3.printStackTrace();
		}

		super.display.setCurrent(displayable);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		startApp();
		boolean adf = false;
	}

	@Override
	public void startApp() {
		if (!this.var_a44) {
			this.var_a44 = true;
			super.startApp();
			this.myCanvas.initLogo();

			this.gameModeBack = new Command(this.getStr(215), 2, 0);
			this.commandAccept = new Command(this.getStr(220), 1, 0);
			this.warriorNameScreenCommand = new Command(this.getStr(220), 1, 0);
			this.settingsBack = new Command(this.getStr(222), 2, 0);
			this.tournamentNextMatchBack = new Command("", 2, 0);
			Command commandBack = new Command("", 2, 0);
			this.mainMenuBack = new Command("", 2, 0);
			this.textBoxBack = new Command(this.getStr(215), 2, 0);
			this.incommingWarriorBack = new Command(this.getStr(459), 2, 0);
			this.incommingWarriorOk = new Command(this.getStr(220), 4, 0);
			this.phoneNumberOk = new Command(this.getStr(220), 4, 0);
			this.helpScreenCommand = new Command(this.getStr(400), 1, 0);
			this.helpScreenCommand2 = new Command(this.getStr(400), 1, 0);
			this.helpBack = new Command(this.getStr(12), 2, 0);
			this.aboutBack = new Command(this.getStr(523), 2, 0);
			this.sendSMSScreenCommand = new Command(this.getStr(522), 1, 0);

			this.gameModeList = new List(this.getStr(208), 3, this);
			for (int var1 = 0; var1 < 5; ++var1) {
				this.gameModeList.append(this.getStr(209 + var1));
			}
			this.gameModeList.addCommand(this.gameModeBack);
			this.gameModeList.setCommandListener(this);

			this.settingsList = new List(this.getStr(206), 2, this);
			this.settingsList.append(this.getStr(231));
			this.settingsList.append(this.getStr(233));
			this.settingsList.append(this.getStr(230));
			this.settingsList.addCommand(this.settingsBack);
			this.settingsList.setCommandListener(this);

			this.warriorNameTextBox = new TextBox(this.getStr(219), this.myCanvas.var_2f0, 12, 0);
			this.warriorNameTextBox.addCommand(this.warriorNameScreenCommand);
			this.warriorNameTextBox.addCommand(this.textBoxBack);
			this.warriorNameTextBox.setCommandListener(this);

			this.phoneNumberTextBox = new TextBox(this.getStr(454), "", 30, 3);
			this.phoneNumberTextBox.addCommand(this.phoneNumberOk);
			this.phoneNumberTextBox.addCommand(this.textBoxBack);
			this.phoneNumberTextBox.setCommandListener(this);

			this.aboutForm = new Form(this.getStr(217));
			String var4 = this.getAppProperty("MIDlet-Version");
			if (var4 == null) {
				var4 = "";
			}

			this.aboutForm.append("(c) 2003/2004\nelkware &\nSiemens mobile\n\ndeveloped by\nelkware GmbH\n\nwww.elkware.com\n\nVersion "
							+ var4 + "\nDate " + "02.06.04");
			this.aboutForm.addCommand(this.aboutBack);
			this.aboutForm.setCommandListener(this);

			this.tournamentList = new List(this.getStr(285), 3, new String[]{
					this.getStr(283), this.getStr(284)}, this);
			this.tournamentList.setCommandListener(this);
			this.tournamentList.addCommand(this.gameModeBack);

			this.tournamentNextMatchList = new List(this.getStr(285), 3,
					new String[]{this.getStr(281)}, this);
			this.tournamentNextMatchList.setCommandListener(this);

			this.var_a8d = this.sub_dc(472) == 1;
			String[] var2;
			if (this.var_a8d) {
				var2 = new String[]{this.getStr(202), this.getStr(203),
						this.getStr(204), this.getStr(206),
						this.getStr(218), this.getStr(217), this.getStr(207)};
			} else {
				var2 = new String[]{this.getStr(202), this.getStr(203),
						this.getStr(286), this.getStr(204),
						this.getStr(206), this.getStr(218),
						this.getStr(217), this.getStr(207)};
			}

			this.mainMenuList = new List("Contest Arena Pro", 3, var2, this);
			this.mainMenuList.setCommandListener(this);
			this.mainMenuList.addCommand(this.mainMenuBack);
			
			String[] var3 = new String[]{this.getStr(520)};
			this.challengeModeList = new List(this.getStr(286), 3, var3, this);
			this.challengeModeList.addCommand(commandBack);
			this.challengeModeList.setCommandListener(this);

			this.pauseList = new List(this.getStr(282), 3, new String[]{
					this.getStr(201), this.getStr(216), this.getStr(521),
					this.getStr(205), this.getStr(206), this.getStr(218),
					this.getStr(217), this.getStr(207)}, this);
			this.pauseList.addCommand(commandBack);
			this.pauseList.setCommandListener(this);

			this.warriorSettingsList = new List("", 3, new String[]{this.getStr(240),
					this.getStr(241), this.getStr(242), this.getStr(243),
					this.getStr(222)}, this);
			this.warriorSettingsList.addCommand(commandBack);
			this.warriorSettingsList.setCommandListener(this);

			this.saveMenuList = new List("", 3, new String[]{this.getStr(226),
					this.getStr(215)}, this);
			this.saveMenuList.addCommand(commandBack);
			this.saveMenuList.setCommandListener(this);

			this.saveMenuList2 = new List("", 3, new String[]{this.getStr(226),
					this.getStr(215)}, this);
			this.saveMenuList2.addCommand(commandBack);
			this.saveMenuList2.setCommandListener(this);
		}
	}

	public void sub_48() {
		this.setCurrentDisp(this.warriorSettingsList);
	}

	public void sub_90() {
		this.setCurrentDisp(this.saveMenuList);
	}

	public void sub_a7() {
		this.var_af2 = true;
		this.commandManage(10);
		this.setCurrentDisp(this.mainMenuList);
	}

	public void sub_df() {
		this.myCanvas.sub_af9();
		this.var_af2 = true;
		this.commandManage(14);
	}

	private void exit(boolean var1) {
		if (var1 && this.var_a8d) {
			Image downloadMoreImage = this.myCanvas.openImage(230);
			MoreCanvas var3 = new MoreCanvas(this, downloadMoreImage);
			super.display.setCurrent(var3);
			this.var_ab2.start();
		} else {
			this.destroyApp(true);
		}
	}

	public void sub_14a() {
		this.myCanvas.sub_8ab();
		this.myCanvas.championsBg = null;
		System.gc();
		if (this.myCanvas.var_270a) {
			this.myCanvas.sub_769(0, 64, -1, true);
		}

		this.myCanvas.sub_ffe();
		this.var_af2 = true;
		this.commandManage(10);
	}

	public void sub_16b() {
		this.myCanvas.sub_8ab();
		this.myCanvas.var_92a = false;
		this.myCanvas.warriorImage = null;
		this.myCanvas.sub_54();
		this.myCanvas.sub_ffe();
		this.var_af2 = true;
		this.commandManage(10);
	}

	public void sub_1b1() {
		this.var_d78 = null;
		System.gc();
		this.myCanvas.var_535 = false;
		this.commandManage(14);
	}

	@Override
	public void commandAction(Command command, Displayable displayable) {
		super.commandAction(command, displayable);
		if (command == this.mainMenuBack) {
			this.exit(false);
		} else if (command == this.tournamentNextMatchBack) {
			this.sub_14a();
		} else {
			int i;
			if (displayable == this.mainMenuList) {
				i = this.mainMenuList.getSelectedIndex();
				if (this.var_a8d && i > 1) {
					++i;
				}

				switch (i) {
					case 0:
						this.myCanvas.var_91b = this.myCanvas.var_92a = false;
						this.commandManage(11);
						break;
					case 1:
						this.myCanvas.var_91b = true;
						this.myCanvas.var_92a = false;
						if (this.myCanvas.var_270a) {
							this.myCanvas.sub_7c4();
							this.myCanvas.sub_769(0, 67, 1, true);
						}

						if (this.myCanvas.sub_ddd()) {
							this.commandManage(22);
						} else {
							this.myCanvas.var_35e9 = true;
							this.commandManage(11);
						}

						this.myCanvas.sub_1035();
						break;
					case 2:
						this.myCanvas.setPercent(0);
						this.setCurrentDisp(this.myCanvas);
						this.myCanvas._chall = true;
						this.myCanvas.sub_6c0();
						this.myCanvas.var_92a = true;
						this.myCanvas.var_91b = false;
						this.myCanvas.var_a17 = true;
						this.myCanvas.setPercent(10);
						this.commandManage(29);
						break;
					case 3:
						this.myCanvas.sub_a65();
						this.commandManage(14);
						this.myCanvas.sub_1035();
						break;
					case 4:
						this.commandManage(15);
						break;
					case 5:
						this.commandManage(17);
						break;
					case 6:
						this.commandManage(16);
						break;
					case 7:
						this.exit(true);
				}

			} else if (displayable == this.warriorSettingsList) {
				if (command.getCommandType() == 2) {
					this.sub_a7();
				} else {
					i = this.warriorSettingsList.getSelectedIndex();
					switch (i) {
						case 0:
							this.myCanvas.var_4c6 = true;
							this.commandManage(18);
							break;
						case 1:
							this.myCanvas.var_4c6 = false;
							this.commandManage(19);
							break;
						case 2:
							Display.getDisplay(this).setCurrent(this.phoneNumberTextBox);
							break;
						case 3:
							this.commandManage(23);
							break;
						case 4:
							this.setCurrentDisp(this.myCanvas);
					}

				}
			} else if (displayable == this.saveMenuList) {
				if (command.getCommandType() == 2) {
					this.sub_df();
				} else {
					i = this.saveMenuList.getSelectedIndex();
					switch (i) {
						case 0:
							this.myCanvas.sub_a65();
							this.var_af2 = true;
							this.commandManage(14);
							break;
						case 1:
							this.sub_df();
					}

				}
			} else if (displayable == this.saveMenuList2) {
				if (command.getCommandType() == 2) {
					this.sub_1b1();
				} else {
					i = this.saveMenuList2.getSelectedIndex();
					switch (i) {
						case 0:
							(new Class_71(this, this, 3, null)).start();
							break;
						case 1:
							this.sub_1b1();
					}

				}
			} else if (displayable == this.pauseList) {
				if (command.getCommandType() == 2) {
					this.myCanvas.sub_9e4();
				} else {
					i = this.pauseList.getSelectedIndex();
					switch (i) {
						case 0:
							this.myCanvas.sub_9e4();
							break;
						case 1:
							this.myCanvas.var_2de8 = false;
							this.myCanvas.setPercent(0);
							this.myCanvas.sub_7c4();
							this.myCanvas.sub_8ab();
							break;
						case 2:
							this.myCanvas.var_2de8 = false;
							this.sendWarrior();
							break;
						case 3:
							this.myCanvas.var_2de8 = true;
							this.setCurrentDisp(this.myCanvas);
							break;
						case 4:
							this.myCanvas.var_2de8 = false;
							this.commandManage(15);
							break;
						case 5:
							this.myCanvas.var_2de8 = false;
							this.commandManage(17);
							break;
						case 6:
							this.myCanvas.var_2de8 = false;
							this.commandManage(16);
							break;
						case 7:
							this.exit(true);
					}

				}
			} else if (displayable == this.tournamentNextMatchList) {
				i = this.tournamentNextMatchList.getSelectedIndex();
				switch (i) {
					case 0:
						this.tournamentNextMatchList.removeCommand(this.tournamentNextMatchBack);
						this.myCanvas.var_2976 = false;
						this.myCanvas.var_35b0 = false;
						if (this.myCanvas.var_37be) {
							this.myCanvas.sub_8ab();
						} else {
							if (this.myCanvas.var_3706 == 0) {
								this.myCanvas.var_891 = Class_2b8.sub_62(4) + 1;
							}

							this.myCanvas.var_8f4 = Class_2b8
									.sub_62(MyCanvas.var_154[this.myCanvas.var_891]) + 1;
						}

						this.myCanvas.sub_171(0);
						this.setCurrentDisp(this.myCanvas);
					default:
				}
			} else if (displayable == this.tournamentList) {
				if (command == this.gameModeBack) {
					this.myCanvas.sub_8ab();
					this.myCanvas.var_92a = false;
					this.myCanvas.warriorImage = null;
					this.myCanvas.sub_54();
					this.myCanvas.sub_ffe();
					this.var_af2 = true;
					this.commandManage(10);
				} else {
					this.myCanvas.var_35e9 = this.tournamentList.getSelectedIndex() == 1;
					if (this.myCanvas.var_35e9) {
						this.commandManage(11);
					} else {
						this.myCanvas.setPercent(0);
						this.commandManage(13);
					}

				}
			} else if (displayable == this.challengeModeList) {
				if (command.getCommandType() == 2) {
					this.sub_16b();
				} else {
					i = this.challengeModeList.getSelectedIndex();
					switch (i) {
						case 0:
							this.myCanvas.var_891 = 5;
							this.myCanvas.var_8f4 = 1;
							this.myCanvas.var_35b0 = this.myCanvas.var_a17 = false;
							this.setCurrentDisp(this.myCanvas);
							return;
						case 1:
							this.myCanvas.sub_ffe();
							this.myCanvas.var_2f12 = new Thread(this.myCanvas);
							this.myCanvas.var_2f12.start();
							return;
						default:
					}
				}
			} else {
				if (command.getCommandType() == 2) {
					switch (this.var_845) {
						case 16:
							this.var_af2 = true;
							this.commandManage(10);
							return;
						case 17:
							this.var_af2 = true;
							this.commandManage(10);
							return;
						case 25:
							this.var_af2 = true;
							this.commandManage(10);
							return;
					}
				}

				if (command.getCommandType() == 2 && this.var_845 == 15) {
					this.myCanvas.var_395[0] = this.settingsList.isSelected(2);
					this.myCanvas.var_395[1] = this.settingsList.isSelected(0);
					this.myCanvas.var_395[2] = false;
					this.myCanvas.var_395[3] = this.settingsList.isSelected(1);
					this.myCanvas.var_395[4] = false;
					this.myCanvas.sub_ec7();
					this.myCanvas.sub_7c4();
					if (this.myCanvas.var_270a) {
						this.myCanvas.sub_769(0, 64, 10, true);
					}

					this.myCanvas.sub_116d(this.myCanvas.var_395[1]);
					this.commandManage(10);
				} else {
					if (command == this.helpScreenCommand) {
						this.helpForm = new Form(this.getStr(400));
						this.helpForm.append(this.getStr(406));
						this.helpForm.setCommandListener(this);
						this.commandManage(28);
					} else if (command == this.helpScreenCommand2) {
						this.helpForm = new Form(this.getStr(400));
						this.helpForm.append(this.getStr(455));
						this.helpForm.setCommandListener(this);
						this.commandManage(28);
					} else if (command == this.incommingWarriorBack) {
						this.var_c76 = null;
						this.var_af2 = true;
						this.commandManage(10);
					} else if (command == this.incommingWarriorOk) {
						this.setCurrentDisp(this.myCanvas);
						this.myCanvas.setPercent(0);
						this.myCanvas.sub_679(this.myCanvas);
					} else if (command != this.gameModeBack && command != this.settingsBack
							&& command != this.aboutBack) {
						if (command.getCommandType() == 1 && this.var_845 == 11) {
							this.myCanvas.var_891 = this.gameModeList
									.getSelectedIndex();
							if (this.myCanvas.var_91b) {
								this.myCanvas.setPercent(0);
								this.setCurrentDisp(this.myCanvas);
								this.myCanvas.setPercent(0);
								this.commandManage(13);
							} else {
								this.commandManage(12);
							}
						} else if (command.getCommandType() == 1
								&& this.var_845 == 12) {
							this.myCanvas.var_8f4 = this.var_6a4
									.getSelectedIndex() + 1;
							this.commandManage(13);
						} else if (command == this.textBoxBack) {
							this.var_af2 = true;
							this.myCanvas.var_4c6 = false;
							this.myCanvas.var_4ee = false;
							this.commandManage(14);
							this.myCanvas.var_4c6 = false;
							this.myCanvas.var_4ee = false;
						} else {
							String var3;
							if (command == this.warriorNameScreenCommand) {
								this.myCanvas.sub_a84(false);
								var3 = this.warriorNameTextBox.getString().replace('\n',
										' ');
								this.myCanvas.var_2f0 = var3.substring(0,
										Math.min(var3.length(), 10));
								this.commandManage(19);
							} else if (command == this.commandAccept) {
								this.myCanvas.var_4ee = false;
								this.setCurrentDisp(this.myCanvas);
								this.myCanvas.sub_6c0();
							} else if (this.var_845 == 23) {
								this.setCurrentDisp(this.myCanvas);
								this.commandManage(24);
							} else if (command == this.phoneNumberOk) {
								this.setCurrentDisp(this.myCanvas);
								this.myCanvas.sub_6a1(this.myCanvas);
							} else if (command == this.sendSMSScreenCommand) {
								var3 = this.receiverTextBox.getString();
								Form var4 = new Form(this.getStr(19));
								var4.append(this.getStr(127));
								var4.addCommand(this.settingsBack);
								var4.setCommandListener(this);
								this.setCurrentDisp(var4);
								(new Class_236(this, var3, null))
										.start();
							}
						}
					} else {
						this.myCanvas.setPercent(0);
						if (this.myCanvas.var_92a) {
							this.myCanvas.sub_8ab();
							this.myCanvas.var_92a = false;
							this.myCanvas.warriorImage = null;
						}

						this.myCanvas.setPercent(25);
						this.myCanvas.setPercent(50);
						if (this.myCanvas.var_91b && this.myCanvas.var_37be) {
							this.myCanvas.var_35b0 = false;
							this.myCanvas.sub_8ab();
							this.myCanvas.championsBg = null;
							System.gc();
						}

						this.myCanvas.setPercent(75);
						this.myCanvas.setPercent(100);
						this.myCanvas.sub_54();
						this.myCanvas.repaint();
						this.myCanvas.serviceRepaints();
						this.var_af2 = true;
						this.commandManage(10);
						this.myCanvas.sub_ffe();
					}

				}
			}
		}
	}

	public void sub_1fb() {
		this.myCanvas.setPercent(0);
		boolean var1 = false;
		byte var2 = -1;
		String var3 = this.phoneNumberTextBox.getString();
		String var4 = String.valueOf(this.var_8c5);
		String var5 = "sms://" + var3 + ":" + var4;
		MessageConnection var6 = null;

		try {
			byte[] var7 = this.sub_5a0();
			int var8 = 0;
			this.myCanvas.setPercent(90);
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
					this.myCanvas.setPercent(90 + (var13 + 1) * 2);
				}
			}

			var6.close();
			this.myCanvas.setPercent(100);
		} catch (Exception var12) {
			var1 = true;
			if (var6 != null) {
				try {
					var6.close();
				} catch (Exception var11) {
					var11.printStackTrace();
				}
			}

			this.myCanvas.setPercent(100);
			this.sub_24e("sendSMS: " + var12 + ", sms #" + var2);
		}

		if (!var1) {
			this.sub_24e(this.getStr(456));
		}

		this.var_af2 = true;
		this.commandManage(14);
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
		this.setCurrentDisp(this.saveMenuList2);
	}

	public void sub_27c() {
		this.setCurrentDisp(this.pauseList);
	}

	public void sub_28d() {
		this.tournamentNextMatchList.addCommand(this.tournamentNextMatchBack);
		this.setCurrentDisp(this.tournamentNextMatchList);
	}

	public void sub_2ec() {
		if (this.myCanvas.var_2a7b != null && this.challengeModeList.size() == 1) {
			this.challengeModeList.append(this.getStr(292));
		}

		this.setCurrentDisp(this.challengeModeList);
	}

	public void commandManage(int var1) {
		if (var1 != this.var_845 || this.var_af2) {
			this.var_845 = var1;
			int var4;
			int var6;
			switch (var1) {
				case 10:
					this.myCanvas.gameOverBg = null;
					this.myCanvas.sub_ffe();
					System.gc();
					if (this.myCanvas.var_3463) {
						this.sub_27c();
					} else {
						if (this.var_d38) {
							Form imcommingWarriorForm = new Form(this.getStr(457));
							imcommingWarriorForm.append(this.getStr(458) + " "
									+ this.var_d05);
							if (this.var_b9f) {
								imcommingWarriorForm.append("\n" + this.getStr(460));
							}

							imcommingWarriorForm.addCommand(this.incommingWarriorOk);
							imcommingWarriorForm.addCommand(this.incommingWarriorBack);
							imcommingWarriorForm.setCommandListener(this);
							this.setCurrentDisp(imcommingWarriorForm);
							this.var_d38 = false;
							return;
						}

						this.setCurrentDisp(this.mainMenuList);
					}
					break;
				case 11:
					this.setCurrentDisp(this.gameModeList);
					break;
				case 12:
					if (this.myCanvas.var_891 != 0) {
						this.var_6a4 = new List(this.getStr(300), 3, this);
						var4 = Math.max(2, this.myCanvas.var_891);

						for (var6 = 0; var6 < MyCanvas.var_154[this.myCanvas.var_891]; ++var6) {
							this.var_6a4.append(
									this.getStr(MyCanvas.var_1a6[var4 + var6 / 5
											% 5]
											+ var6 % 5));
						}

						this.var_6a4.addCommand(this.gameModeBack);
						this.var_6a4.setCommandListener(this);
						this.setCurrentDisp(this.var_6a4);
					} else {
						this.commandManage(13);
					}
					break;
				case 13:
					this.setCurrentDisp(this.myCanvas);
					this.myCanvas.sub_6b4(this.myCanvas);
					break;
				case 14:
					this.myCanvas.var_4ee = false;
					if (this.myCanvas.sub_a8()) {
						this.myCanvas.sub_110();
					}

					this.setCurrentDisp(this.myCanvas);
					this.myCanvas.sub_6c0();
					break;
				case 15:
					this.settingsList.setSelectedFlags(new boolean[]{
							this.myCanvas.var_395[1], this.myCanvas.var_395[3],
							this.myCanvas.var_395[0]});
					this.setCurrentDisp(this.settingsList);
					break;
				case 16:
					this.setCurrentDisp(this.aboutForm);
					break;
				case 17:
					if (this.var_5bd == null) {
						this.var_5bd = new Form(this.getStr(218));
						String var5 = this.getStr(200);

						while ((var6 = var5.indexOf("\nn")) != -1) {
							var5 = var5.substring(0, var6) + '\n'
									+ var5.substring(var6 + 2, var5.length() - var6 - 2);
						}

						this.var_5bd.append(var5);
						this.var_5bd.addCommand(this.aboutBack);
						this.var_5bd.setCommandListener(this);
					}

					this.setCurrentDisp(this.var_5bd);
					break;
				case 18:
					this.setCurrentDisp(this.warriorNameTextBox);
					break;
				case 19:
					this.myCanvas.var_535 = false;
					this.myCanvas.var_5fb = null;

					for (var4 = 0; var4 < 4; ++var4) {
						this.myCanvas.var_295[var4] = this.myCanvas.var_234[var4 + 2];
					}

					this.myCanvas.var_4ee = true;
					this.myCanvas.sub_aad();
					this.setCurrentDisp(this.myCanvas);
					this.myCanvas.sub_6c0();
					break;
				case 20:
					this.setCurrentDisp(this.myCanvas);
				case 21:
				case 26:
				case 27:
				default:
					break;
				case 22:
					this.setCurrentDisp(this.tournamentList);
					break;
				case 23:
					(new Class_71(this, this, 1, null)).start();
					break;
				case 24:
					(new Class_71(this, this, 2, null)).start();
					break;
				case 25:
					Form processCompleteForm = new Form(this.getStr(404));
					processCompleteForm.append(this.getStr(402)); // Photo has been added to database. You can select it in your player setup.
					processCompleteForm.setCommandListener(this);
					processCompleteForm.addCommand(this.commandAccept);
					this.setCurrentDisp(processCompleteForm);
					break;
				case 28:
					this.helpForm.addCommand(this.helpBack);
					this.setCurrentDisp(this.helpForm);
					break;
				case 29:
					this.setCurrentDisp(this.myCanvas);
					this.myCanvas.var_92a = true;
					this.commandManage(13);
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
			this.myCanvas.var_20cb = new String[3];
			this.myCanvas.var_20f8 = new int[3];

			for (int var5 = 0; var5 < 3; ++var5) {
				if (super.var_64d[var5] == null) {
					this.myCanvas.var_20cb[var5] = "- - -";
				} else {
					this.myCanvas.var_20cb[var5] = super.var_64d[var5];
				}

				this.myCanvas.var_20f8[var5] = super.var_666[var5];
			}

			try {
				this.myCanvas.sub_c65(var1, this.myCanvas.var_20cb,
						this.myCanvas.var_20f8);
			} catch (Exception var6) {
				var6.printStackTrace();
			}
		} else {
			String var8 = this.sub_309();
			if (var8 != null) {
				this.sub_24e(this.getStr(24) + " " + var8);
			} else {
				this.sub_24e(this.getStr(24));
			}
		}

		if (var4) {
			this.myCanvas.sub_c2e();
		}

		this.commandManage(29);
		this.myCanvas.var_2f12 = null;
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

		for (var4 = 0; var4 < this.myCanvas.var_733; ++var4) {
			if (this.var_d05
					.compareTo(this.myCanvas.var_b2f[this.myCanvas.var_b2f.length
							- this.myCanvas.var_733 + var4]) == 0) {
				this.var_bd9 = var4;
				this.var_b9f = true;
			}
		}

		this.var_d38 = true;
	}

	private boolean sub_4f0() {
		this.var_d64 = true;
		this.var_787 = new List(this.getStr(401), 3, this);

		try {
			FileConnection var1 = (FileConnection) Connector.open(
					"file:///0:/Pictures", 3);
			this.myCanvas.setPercent(35);
			Enumeration var2 = var1.list("*.jpg", false);
			System.gc();
			this.myCanvas.setPercent(40);

			while (var2.hasMoreElements()) {
				String var3 = (String) var2.nextElement();
				if (!var3.endsWith("/")) {
					this.var_787.append(var3);
				}
			}

			if (this.var_787.size() == 0) {
				return false;
			}

			var1.close();
			this.myCanvas.setPercent(80);
		} catch (Exception var4) {
			this.var_d64 = false;
			this.sub_24e(var4.toString());
		}

		this.myCanvas.setPercent(100);
		this.var_787.addCommand(this.textBoxBack);
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
			this.myCanvas.setPercent(10);
			this.var_d78 = null;
			this.myCanvas.var_c21 = null;
			System.gc();
			this.myCanvas.setPercent(20);
			if (this.var_de1 == 18) {
				this.var_d78 = com.siemens.mp.lcdui.Image.createImageFromFile(
						"0:/pictures/headTmp.bmp", this.var_de1, this.var_de1);
				this.sub_532(this.var_d78, 7799014);
			} else {
				this.var_d78 = com.siemens.mp.lcdui.Image.createImageFromFile(
						"0:/pictures/" + var1, 64, 48);
				this.myCanvas.setPercent(30);
				Image var2 = Image.createImage(this.var_de1, this.var_de1);
				this.myCanvas.setPercent(40);
				var2.getGraphics().drawImage(this.var_d78, -9, -2, 0);
				this.myCanvas.setPercent(50);
				this.sub_532(var2, -1);
				this.myCanvas.setPercent(60);
				com.siemens.mp.lcdui.Image.writeBmpToFile(var2,
						"0:/pictures/headTmp.bmp");
				this.myCanvas.setPercent(70);
				this.var_d78 = var2;
				var2 = null;
				this.myCanvas.setPercent(80);
			}

			System.gc();
			this.myCanvas.setPercent(90);
			return true;
		} catch (Exception var3) {
			this.sub_4e9("computeImage: " + var3);
			return false;
		}
	}

	private byte[] sub_5a0() {
		String var1 = this.myCanvas.var_2f0.substring(0,
				Math.min(50, this.myCanvas.var_2f0.length()));
		byte[] var2 = var1.getBytes();
		byte var3 = (byte) this.myCanvas.var_234[0];
		byte[] var4 = new byte[1];
		this.myCanvas.getClass();
		if (var3 >= 4) {
			var4 = new byte[307];
		}

		var4[0] = var3;
		int var_ebf = 6;
		byte[] var5 = new byte[7 + var2.length + var4.length + var_ebf];
		this.myCanvas.setPercent(20);
		if (var4.length > 1) {
			Image var6 = null;

			try {
				var6 = this.myCanvas.var_f45[var3];
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

				this.myCanvas.setPercent(20 + var10 * 3);
			}
		}

		byte var14 = 0;
		int var15 = var14 + 1;
		var5[var14] = (byte) var2.length;

		int var16;
		for (var16 = 0; var16 < var2.length; ++var16) {
			var5[var15++] = var2[var16];
		}

		this.myCanvas.setPercent(80);

		for (var16 = 0; var16 < 6; ++var16) {
			var5[var15++] = (byte) this.myCanvas.var_234[var16];
		}

		for (var16 = 0; var16 < var4.length; ++var16) {
			var5[var15++] = var4[var16];
		}

		this.myCanvas.setPercent(85);
		return var5;
	}

	public void sub_5c4() {
		this.myCanvas.setPercent(0);
		this.myCanvas.sub_f85(this.var_c76, this.var_d05, this.var_b9f);
		this.var_c76 = null;
		this.var_af2 = true;
		this.myCanvas.setPercent(100);
		this.commandManage(10);
	}

	@Override
	public Canvas3 getCanvas() {
		try {
			this.myCanvas = new MyCanvas(this);
			canvasView.initView(myCanvas);
			return this.myCanvas;
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

	private void sendWarrior() {
		try {
			this.receiverTextBox = new TextBox(this.getStr(526), "mms://", 256, 0);
			this.receiverTextBox.addCommand(this.settingsBack);
			this.receiverTextBox.addCommand(this.sendSMSScreenCommand);
			this.receiverTextBox.setCommandListener(this);
			this.setCurrentDisp(this.receiverTextBox);
		} catch (Exception var2) {
			var2.printStackTrace();
		}
	}

	@Override
	public void destroyApp(boolean var1) {
		if (this.var_b43 != null) {
			this.var_b43.sub_9d();
			this.var_b43 = null;
		}

		this.myCanvas.sub_267();
		this.notifyDestroyed();
	}

	static boolean sub_64f(Arena arena, boolean var1) {
		return arena.var_aa0 = var1;
	}

	static boolean sub_690(Arena arena) {
		return arena.var_aa0;
	}

	static MyCanvas sub_6af(Arena var0) {
		return var0.myCanvas;
	}

	static boolean sub_6eb(Arena var0, Image var1, String var2) {
		return var0.sub_5f0(var1, var2);
	}

	static void sub_6f7(Arena var0, Displayable var1) {
		var0.setCurrentDisp(var1);
	}

	static String sub_731(Arena var0) {
		return var0.var_91e;
	}

	static boolean sub_760(Arena var0, String var1) {
		return var0.sub_552(var1);
	}

	static String sub_7b8(Arena var0, String var1) {
		return var0.var_91e = var1;
	}

	static List sub_814(Arena var0) {
		return var0.var_787;
	}

	static boolean sub_865(Arena var0) {
		return var0.sub_4f0();
	}

	static boolean sub_884(Arena var0) {
		return var0.var_d64;
	}

	static List sub_8c9(Arena var0, List var1) {
		return var0.var_787 = var1;
	}

	static Command sub_8f2(Arena var0) {
		return var0.textBoxBack;
	}

}
