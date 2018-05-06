package com.elkware.midp.games.colorng;

import com.elkware.midp.games.Arena2;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.List;

public abstract class Arena3 extends Arena2 implements CommandListener {

    Canvas3 canvas3;
    boolean bool_81;
    int int_c5;
    private List list;
    private boolean bool_148 = true;

    public abstract Canvas3 createCanvas();

    @Override
    public void startApp() {
        if (this.canvas3 != null) {
            Display.getDisplay().setCurrent(this.canvas3);
            this.canvas3.showNotify();
        } else {
            super.startApp();
            if ((this.int_c5 = this.getParameter(5030)) == -1) {
                this.int_c5 = 15;
            }

            this.bool_81 = this.getParameter(5035) != 0;

            try {
                this.canvas3 = this.createCanvas();
            } catch (Exception var2) {
                this.makeAlert("getGameCanvas: " + var2);
                return;
            }

            if (this.sub_2e6()) {
                this.canvas3.sub_e9a();
                if (this.bool_81) {
                    this.sub_60();
                } else {
                    this.canvas3.sub_248();
                }
            }
        }
    }

    private void sub_60() {
        if ((this.int_c5 & 3) > 0 && (super._forPlayMus || super._forPlayMus1)) {
            this.list = new List(this.getStr(3) + " " + this.getStr(4) + "?", 3,  //Sound OFF Sound ON?
                    new String[]{this.getStr(7), this.getStr(8)}, null);  //Backlight OFF, Backlight ON
            this.list.setCommandListener(this);
            this.bool_148 = false;
            super.display.setCurrent(this.list);
        } else {
            this.canvas3.sub_248();
        }
    }

    @Override
    public void commandAction(Command var1, Displayable var2) {
        super.commandAction(var1, var2);
        if (!this.bool_148) {
            this.bool_148 = true;
            boolean var3 = this.list.isSelected(0);
            this.list = null;
            if (!var3) {
                boolean[] var4 = this.canvas3.sub_6e();
                int var5 = 0;
                if ((this.int_c5 & 1) > 0) {
                    var4[var5++] = false;
                }

                if ((this.int_c5 & 2) > 0) {
                    var4[var5] = false;
                }

                this.canvas3.sub_af(var4);
            }

            this.canvas3.sub_248();
        }
    }

    @Override
    public void pauseApp() {
        if(this.canvas3 != null) {
            this.canvas3.hideNotify();
        }
        this.notifyPaused();
    }

    @Override
    public void destroyApp() {
        this.canvas3.sub_267();
        this.canvas3.sub_ec7_saveSettings();
        this.notifyDestroyed();
    }
}
