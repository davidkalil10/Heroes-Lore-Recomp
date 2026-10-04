/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Display
 *  javax.microedition.midlet.MIDlet
 */
package rpg;

import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;

public class GameMIDlet
extends MIDlet {
    public static GameMIDlet var_rpg_GameMIDlet_a;
    private Display var_javax_microedition_lcdui_Display_a;
    public boolean var_boolean_a = false;

    public GameMIDlet() {
        var_rpg_GameMIDlet_a = this;
    }

    public void startApp() {
        if (!this.var_boolean_a) {
            this.var_boolean_a = true;
            this.var_javax_microedition_lcdui_Display_a = Display.getDisplay((MIDlet)this);
            bs.a(this.var_javax_microedition_lcdui_Display_a);
            bs.var_bs_a.c();
            String string = this.getAppProperty("HO-LSK");
            if (string != null) {
                try {
                    bh.var_int_b = Integer.parseInt(string);
                }
                catch (NumberFormatException numberFormatException) {
                    bh.var_int_b = -6;
                }
            } else {
                bh.var_int_b = -6;
            }
            if ((string = this.getAppProperty("HO-RSK")) != null) {
                try {
                    bh.var_int_c = Integer.parseInt(string);
                }
                catch (NumberFormatException numberFormatException) {
                    bh.var_int_c = -7;
                }
            } else {
                bh.var_int_c = -7;
            }
            if ((string = this.getAppProperty("HO-CLR")) != null) {
                try {
                    bh.var_int_a = Integer.parseInt(string);
                    return;
                }
                catch (NumberFormatException numberFormatException) {
                    bh.var_int_a = -8;
                    return;
                }
            }
            bh.var_int_a = -8;
        }
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean bl2) {
        this.a();
    }

    public final void a() {
        this.notifyDestroyed();
    }
}

