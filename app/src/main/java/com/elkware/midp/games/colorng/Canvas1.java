package com.elkware.midp.games.colorng;

import com.elkware.midp.games.colorng.arena.high.Arena;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Graphics;

public abstract class Canvas1 extends Canvas {

	public int[] var_ab;
	public int[] var_12f;
	public int var_16c;
	public int var_17c;
	public int var_1fb;
	public int var_245;
	public boolean var_24f;
	public boolean var_292 = true;

	public Canvas1(Arena var1) {
		super(var1);
		this.sub_44(0, 0, this.getwidth(), this.getheight());
	}

	public void redraw() {
		this.repaint();
		this.serviceRepaints();
	}

	@Override
	public void paint(Graphics var1) {
		try {
			this.draw(var1);
		} catch (Exception var3) {
			this.arena.sub_4e9("paintFrame: " + var3);
		}
	}

	public abstract void draw(Graphics var1);

	public void sub_44(int var1, int var2, int var3, int var4) {
		this.var_1fb = var1;
		this.var_245 = var2;
		if (var3 != this.var_16c || var4 != this.var_17c) {
			this.var_ab = this.var_12f = null;
		}

		this.var_16c = var3;
		this.var_17c = var4;
	}

	public final void sub_63(Graphics var1) {
		this.sub_9a(var1, this.var_1fb, this.var_245);
	}

	public void sub_9a(Graphics var1, int var2, int var3) {
		if (this.var_24f) {
			int var4;
			label38: {
				var4 = 0;
				int var5 = this.var_16c;
				int var6 = this.var_17c;
				var1.setClip(var2, var3, this.var_16c, this.var_17c);
				if (var2 < 0) {
					if (var2 < -var5) {
						break label38;
					}

					var4 = -var2;
					var5 += var2;
					var2 = 0;
				}

				if (var3 < 0) {
					if (var3 < -var6) {
						break label38;
					}

					var4 += this.var_16c * -var3;
					var6 += var3;
					var3 = 0;
				}

				var1.drawRGB(this.var_ab, var4, this.var_16c, var2, var3, var5,
						var6, true);
			}

			if (this.var_292) {
				for (var4 = 0; var4 < this.var_ab.length; var4 += this.var_16c) {
					System.arraycopy(this.var_12f, 0, this.var_ab, var4,
							this.var_16c);
				}
			}
		}

		this.var_24f = false;
	}

	protected void sub_dd() {
		if (this.var_ab == null) {
			this.var_ab = new int[this.var_16c * (this.var_17c + 1)];
			if (this.var_292) {
				this.var_12f = new int[this.var_16c];
			}
		}

	}

	public void sub_135(Graphics var1, byte[] var2, boolean var3, int var4,
			int var5, int[] var6, int var7, int var8, boolean var9,
			boolean var10, int var11, int var12, int var13, int var14) {
		int var18 = 0;
		int var19 = 0;
		int var22 = var4;
		int var23 = var5;
		var7 += var1.getTranslateX();
		var8 += var1.getTranslateY();
		var7 -= this.var_1fb;
		var8 -= this.var_245;
		var11 -= this.var_1fb;
		var12 -= this.var_245;
		var13 -= this.var_1fb;
		var14 -= this.var_245;
		if (var11 < 0) {
			var11 = 0;
		}

		if (var13 > this.var_16c) {
			var13 = this.var_16c;
		}

		if (var12 < 0) {
			var12 = 0;
		}

		if (var14 > this.var_17c) {
			var14 = this.var_17c;
		}

		if (var8 < var12) {
			if ((var5 += var8 - var12) <= 0) {
				return;
			}

			var18 = -(var8 - var12);
			var8 = var12;
		}

		if (var8 + var5 <= var14 || (var5 = var14 - var8) > 0) {
			if (var7 < var11) {
				if ((var4 += var7 - var11) <= 0) {
					return;
				}

				var19 = -(var7 - var11);
				var7 = var11;
			}

			if (var7 + var4 <= var13 || (var4 = var13 - var7) > 0) {
				int var15 = var8 * this.var_16c + var7;
				int var16 = this.var_16c - var4;
				this.sub_dd();
				if (var10) {
					var18 = var23 - 1 - var18;
				}

				if (var9) {
					var19 = var22 - 1 - var19;
				}

				int var17;
				int var20;
				int var24;
				if (var3) {
					boolean var25 = (var19 & 1) == 1;
					boolean var26 = (var19 + var4 & 1) == 1;
					var22 = var22 + 1 >> 1;
					if (!var9) {
						var4 = (var19 & 1) + var4 + 1 >> 1;
						if (!var10) {
							var20 = var22 - var4;
						} else {
							var20 = -var22 - var4;
						}
					} else {
						var4 = 1 - (var19 & 1) + var4 + 1 >> 1;
						var20 = var22 + var4;
						var25 = !var25;
						var26 = !var26;
					}

					var17 = var18 * var22 + (var19 >> 1);
					if (var25) {
						--var4;
					}

					if (var26) {
						--var4;
					}

					byte var21;
					int var27;
					int var28;
					if (!var9) {
						for (var27 = var5; var27 > 0; --var27) {
							if (var25) {
								var21 = var2[var17++];
								if ((var24 = var6[var21 & 15]) != 0) {
									this.var_ab[var15] = var24;
								}

								++var15;
							}

							for (var28 = var4; var28 > 0; --var28) {
								var21 = var2[var17++];
								if ((var24 = var6[var21 & 240]) != 0) {
									this.var_ab[var15] = var24;
								}

								++var15;
								if ((var24 = var6[var21 & 15]) != 0) {
									this.var_ab[var15] = var24;
								}

								++var15;
							}

							if (var26) {
								var21 = var2[var17++];
								if ((var24 = var6[var21 & 240]) != 0) {
									this.var_ab[var15] = var24;
								}

								++var15;
							}

							var15 += var16;
							var17 += var20;
						}
					} else {
						for (var27 = var5; var27 > 0; --var27) {
							if (var25) {
								var21 = var2[var17--];
								if ((var24 = var6[var21 & 240]) != 0) {
									this.var_ab[var15] = var24;
								}

								++var15;
							}

							for (var28 = var4; var28 > 0; --var28) {
								var21 = var2[var17--];
								if ((var24 = var6[var21 & 15]) != 0) {
									this.var_ab[var15] = var24;
								}

								++var15;
								if ((var24 = var6[var21 & 240]) != 0) {
									this.var_ab[var15] = var24;
								}

								++var15;
							}

							if (var26) {
								var21 = var2[var17--];
								if ((var24 = var6[var21 & 15]) != 0) {
									this.var_ab[var15] = var24;
								}

								++var15;
							}

							var15 += var16;
							var17 += var20;
						}
					}
				} else {
					if (!var9) {
						if (!var10) {
							var20 = var22 - var4;
						} else {
							var20 = -var22 - var4;
						}
					} else {
						var20 = var22 + var4;
					}

					var17 = var18 * var22 + var19;
					int var29;
					int var30;
					if (!var9) {
						for (var29 = var5; var29 > 0; --var29) {
							for (var30 = var4; var30 > 0; --var30) {
								if ((var24 = var6[var2[var17++]]) != 0) {
									this.var_ab[var15] = var24;
								}

								++var15;
							}

							var15 += var16;
							var17 += var20;
						}
					} else {
						for (var29 = var5; var29 > 0; --var29) {
							for (var30 = var4; var30 > 0; --var30) {
								if ((var24 = var6[var2[var17--]]) != 0) {
									this.var_ab[var15] = var24;
								}

								++var15;
							}

							var15 += var16;
							var17 += var20;
						}
					}
				}

				this.var_24f = true;
			}
		}
	}

	public void sub_14b(Graphics var1, byte[] var2, boolean var3, int var4,
			int var5, int[] var6, int var7, int var8, int var9, int var10,
			boolean var11, boolean var12, int var13, int var14, int var15,
			int var16) {
		int var17 = 0;
		int var19 = 0;
		if (var9 != 0 && var10 != 0) {
			int var23 = var5 * 65536 / var10;
			int var24 = var4 * 65536 / var9;
			var7 += var1.getTranslateX();
			var8 += var1.getTranslateY();
			var7 -= this.var_1fb;
			var8 -= this.var_245;
			var13 -= this.var_1fb;
			var14 -= this.var_245;
			var15 -= this.var_1fb;
			var16 -= this.var_245;
			if (var13 < 0) {
				var13 = 0;
			}

			if (var15 > this.var_16c) {
				var15 = this.var_16c;
			}

			if (var14 < 0) {
				var14 = 0;
			}

			if (var16 > this.var_17c) {
				var16 = this.var_17c;
			}

			if (var8 < var14) {
				if ((var10 += var8 - var14) <= 0) {
					return;
				}

				var17 += (var14 - var8) * var23;
				var8 = var14;
			}

			if (var8 + var10 <= var16 || (var10 = var16 - var8) > 0) {
				if (var7 < var13) {
					if ((var9 += var7 - var13) <= 0) {
						return;
					}

					var19 += (var13 - var7) * var24;
					var7 = var13;
				}

				if (var7 + var9 <= var15 || (var9 = var15 - var7) > 0) {
					int var29 = var8 * this.var_16c + var7;
					int var22 = this.var_16c - var9;
					if (var11) {
						var19 = (var4 - 1 << 16) + '\uffff' - var19;
						var24 = -var24;
					} else if (var12) {
						var17 = (var5 - 1 << 16) + '\uffff' - var17;
						var23 = -var23;
					}

					this.sub_dd();
					int var18;
					int var20;
					int var25;
					int var26;
					int var27;
					if (var3) {
						int var28 = var4 + 1 >> 1;

						for (var26 = var10; var26 > 0; --var26) {
							var18 = (var17 >> 16) * var28;
							var20 = var19;

							for (var25 = var9; var25 > 0; --var25) {
								if ((var20 & 65536) == 0) {
									var27 = var6[var2[var18 + (var20 >> 17)] & 240];
								} else {
									var27 = var6[var2[var18 + (var20 >> 17)] & 15];
								}

								if (var27 != 0) {
									this.var_ab[var29] = var27;
								}

								++var29;
								var20 += var24;
							}

							var17 += var23;
							var29 += var22;
						}
					} else {
						for (var26 = var10; var26 > 0; --var26) {
							var18 = (var17 >> 16) * var4;
							var20 = var19;

							for (var25 = var9; var25 > 0; --var25) {
								if ((var27 = var6[var2[var18 + (var20 >> 16)]]) != 0) {
									this.var_ab[var29] = var27;
								}

								++var29;
								var20 += var24;
							}

							var17 += var23;
							var29 += var22;
						}
					}

					this.var_24f = true;
				}
			}
		}
	}

	@Override
	public int getheight() {
		return 176;
	}

	public void sub_196(long var1) {
		Display.getDisplay(this.arena).vibrate((int) var1);
	}

	public void sub_1e0(int var1, boolean var2) {
		if (0 == var1) {
			if (var2) {
				//Light.setLightOn();
			} else {
				//Light.setLightOff();
			}
		}
	}

}
