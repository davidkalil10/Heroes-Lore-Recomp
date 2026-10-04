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

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class GameMIDlet
extends MIDlet {
    public static GameMIDlet a;
    private Display a;
    public boolean a = false;

    public GameMIDlet() {
        a = this;
    }

    public void startApp() {
        if (!this.a) {
            this.a = true;
            this.a = Display.getDisplay((MIDlet)this);
            bs.a(this.a);
            bs.a.c();
            String string = this.getAppProperty("HO-LSK");
            if (string != null) {
                try {
                    bh.b = Integer.parseInt(string);
                }
                catch (NumberFormatException numberFormatException) {
                    bh.b = -6;
                }
            } else {
                bh.b = -6;
            }
            if ((string = this.getAppProperty("HO-RSK")) != null) {
                try {
                    bh.c = Integer.parseInt(string);
                }
                catch (NumberFormatException numberFormatException) {
                    bh.c = -7;
                }
            } else {
                bh.c = -7;
            }
            if ((string = this.getAppProperty("HO-CLR")) != null) {
                try {
                    bh.a = Integer.parseInt(string);
                    return;
                }
                catch (NumberFormatException numberFormatException) {
                    bh.a = -8;
                    return;
                }
            }
            bh.a = -8;
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

