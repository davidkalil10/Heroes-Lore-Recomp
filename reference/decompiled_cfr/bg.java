/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 */
import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import rpg.GameMIDlet;

public final class bg
extends r
implements Runnable {
    private byte var_byte_a;
    private byte var_byte_b;
    private int var_int_a;
    private short var_short_a;
    private short var_short_b;
    private byte var_byte_c;
    private short var_short_c;
    private short var_short_d;
    private byte var_byte_d;
    private boolean var_boolean_d = false;
    public static bg var_bg_a;

    public bg() {
        new Object();
        this.var_byte_b = 0;
    }

    public final void keyPressed(int n2) {
        if (((r)this).c) {
            if (n2 != -11) {
                ((r)this).c = false;
                this.showNotify();
            }
            return;
        }
        boolean bl2 = false;
        if (n2 != bh.var_int_b && n2 != bh.var_int_c) {
            this.getGameAction(n2);
        }
        if (bs.var_bs_a == null || bs.var_bs_a.e) {
            return;
        }
        switch (this.var_byte_a) {
            case 0: {
                return;
            }
            case 10: {
                this.c();
                return;
            }
            case 1: {
                bw.f();
                ce.x();
                ce.z();
                this.a(false, (byte)1);
                return;
            }
            case 2: {
                this.var_byte_a = (byte)3;
                this.var_int_a = 0;
                bs.var_bs_a.g();
                var_bg_a = this;
                new Thread(var_bg_a).start();
                return;
            }
            case 6: {
                GameMIDlet.var_rpg_GameMIDlet_a.destroyApp(true);
            }
        }
    }

    public final void run() {
        if (((r)this).c) {
            return;
        }
        switch (this.var_byte_a) {
            case 0: {
                switch (this.var_byte_b) {
                    case 1: {
                        ce.o();
                        r.k();
                        try {
                            ad.var_z_b = new z("/itm/itmtp");
                            r.k();
                            t.a = new z("/itm/itmatt");
                            r.k();
                        }
                        catch (IOException iOException) {}
                        ce.w();
                        ce.y();
                        try {
                            if (au.boolean_a("/c")) {
                                bs.var_bs_a.j();
                            } else {
                                if (!au.boolean_a("/c")) {
                                    if (au.boolean_a(n.var_java_lang_String_arr_a[0])) {
                                        au.void_a(n.var_java_lang_String_arr_a[0]);
                                    }
                                    if (au.boolean_a(n.var_java_lang_String_arr_a[1])) {
                                        au.void_a(n.var_java_lang_String_arr_a[1]);
                                    }
                                    if (au.boolean_a(n.var_java_lang_String_arr_a[2])) {
                                        au.void_a(n.var_java_lang_String_arr_a[2]);
                                    }
                                    if (au.boolean_a("/o")) {
                                        au.void_a("/o");
                                    }
                                }
                                bs.var_bs_a.i();
                            }
                        }
                        catch (Exception exception) {}
                        this.b();
                        r.k();
                        return;
                    }
                    case 2: {
                        bu.e();
                        ce.A();
                        bs.var_bs_a.d();
                        this.var_byte_a = (byte)-1;
                        this.var_byte_b = 0;
                    }
                }
            }
        }
    }

    public final void paint(Graphics graphics) {
        if (((r)this).c) {
            graphics.setColor(0);
            graphics.fillRect(0, 0, r.g, r.h);
            graphics.setColor(0xFFFFFF);
            if (bh.n != null) {
                bh.void_a(graphics, r.g / 2, r.h / 3, bh.n, 0);
            }
            if (bh.q != null) {
                bh.void_a(graphics, r.g / 2, r.h / 2, bh.q, 0);
            }
            return;
        }
        this.void_a(graphics.getClipHeight());
        switch (this.var_byte_a) {
            case 0: {
                break;
            }
            case 10: {
                graphics.setColor(0xFFFFFF);
                graphics.fillRect(0, 0, r.g, r.h);
                if (this.var_byte_c > 40) {
                    this.var_short_a = (short)(this.var_short_a * 2);
                }
                graphics.drawImage(ce.var_javax_microedition_lcdui_Image_a, r.i, this.var_int_a - this.var_short_a, 3);
                if (this.var_byte_c == 0) {
                    if (this.var_int_a < r.j - 1) {
                        this.var_int_a += (r.j - this.var_int_a) / 2;
                    } else {
                        this.var_byte_c = 1;
                    }
                } else {
                    switch (this.var_byte_c) {
                        case 1: 
                        case 3: {
                            this.var_int_a = r.j - 1;
                            break;
                        }
                        case 2: 
                        case 4: {
                            this.var_int_a = r.j;
                        }
                    }
                    this.var_byte_c = (byte)(this.var_byte_c + 1);
                }
                if (this.var_short_a <= r.h) break;
                this.c();
                break;
            }
            case 1: {
                graphics.setColor(0xFFFFFF);
                graphics.fillRect(0, 0, r.g, r.h);
                int n2 = r.j - 68;
                int n3 = r.i - 60;
                graphics.drawImage(ce.var_javax_microedition_lcdui_Image_arr_i[2], n3 + 0, n2 + 25, 20);
                graphics.drawImage(ce.var_javax_microedition_lcdui_Image_arr_i[3], n3 + 52, n2 + 25, 20);
                graphics.drawImage(ce.var_javax_microedition_lcdui_Image_arr_i[4], n3 + 93, n2 + 2, 20);
                boolean bl2 = false;
                graphics.setColor(0x3F1F3F);
                if (bh.r != null) {
                    bh.int_a(graphics, r.g - 2 - bh.int_a(bh.r), r.h - 31, bh.r, 0);
                }
                graphics.drawImage(ce.var_javax_microedition_lcdui_Image_arr_j[this.var_byte_c < 4 ? this.var_byte_c : 8 - this.var_byte_c], (int)this.var_short_a, (int)this.var_short_b, 33);
                graphics.drawImage(ce.var_javax_microedition_lcdui_Image_arr_j[(this.var_byte_d < 4 ? this.var_byte_d : 8 - this.var_byte_d) + 5], (int)this.var_short_c, (int)this.var_short_d, 33);
                this.var_short_a = (short)(this.var_short_a + 10 * (this.var_byte_c < 4 ? 1 : -1));
                this.var_short_b = (short)(this.var_short_b + h.a(-1, 4));
                this.var_short_c = (short)(this.var_short_c + 10 * (this.var_byte_d < 4 ? -1 : 1));
                this.var_short_d = (short)(this.var_short_d + h.a(-1, 4));
                this.var_byte_c = (byte)(this.var_byte_c + 1);
                this.var_byte_d = (byte)(this.var_byte_d + 1);
                if (this.var_byte_c > 7) {
                    this.var_byte_c = 0;
                }
                if (this.var_byte_d > 7) {
                    this.var_byte_d = 0;
                }
                if (this.var_short_b > r.h + 10) {
                    this.var_short_a = (short)h.a(10, r.g / 2 - 10);
                    this.var_short_b = (short)(-10 * h.a(0, 4));
                    this.var_byte_c = (byte)h.a(0, 7);
                }
                if (this.var_short_d > r.h + 10) {
                    this.var_short_c = (short)h.a(r.g / 2 + 10, r.g - 10);
                    this.var_short_d = (short)(-10 * h.a(3, 7));
                    this.var_byte_d = (byte)h.a(0, 7);
                }
                if (this.var_int_a % 4 < 2) {
                    graphics.setColor(0);
                    bh.void_a(graphics, r.i, r.h - 45, bh.q, 1);
                }
                ++this.var_int_a;
            }
        }
        bs.var_bs_a.b();
    }

    public final void a() {
        r.a("- INITIALIZE", 30);
        bh.void_a();
        w.a(GameMIDlet.var_rpg_GameMIDlet_a);
        int n2 = w.int_a();
        if (n2 >= 0) {
            r.m = 3;
            this.var_byte_b = 1;
            this.var_byte_a = 0;
            this.b(n2);
            new Thread(this).start();
            return;
        }
        r.m = 3;
        this.var_byte_b = 1;
        this.var_byte_a = 0;
        this.b(0);
        new Thread(this).start();
    }

    private void b(int n2) {
        cj.var_cj_a.a("/lang", "", n2);
        bh.a(cj.var_cj_a);
        w.void_a();
        try {
            ce.var_z_g = new z("/sgui/com");
            bh.p = ce.var_z_g.a(37);
            bh.o = ce.var_z_g.a(38);
            r.k();
            this.var_byte_b = 1;
            return;
        }
        catch (IOException iOException) {
            return;
        }
    }

    public final void b() {
        bs.var_bs_a.a(20);
        this.var_int_a = -20;
        this.var_byte_c = 0;
        this.var_short_a = 1;
        this.var_byte_a = (byte)10;
    }

    public final void hideNotify() {
        if (bh.q != null) {
            ((r)this).c = true;
        }
        bw.a();
    }

    public final void showNotify() {
        if (((r)this).c) {
            return;
        }
        bw.b();
    }

    private final void c() {
        bs.var_bs_a.a(15);
        this.var_int_a = 0;
        this.var_byte_a = 1;
        this.var_short_a = (short)h.a(0, r.g / 2 - 10);
        this.var_short_b = (short)(10 * h.a(0, 4));
        this.var_byte_c = (byte)h.a(0, 7);
        this.var_short_c = (short)h.a(r.g / 2, r.g - 10);
        this.var_short_d = (short)(10 * h.a(3, 7));
        this.var_byte_d = (byte)h.a(0, 7);
        bw.b(22);
    }

    public final void a(boolean bl2, byte by2) {
        bw.d();
        if (!bl2 && (bs.var_bs_a.var_byte_b & (by2 == 1 ? 8 : 2)) != 0) {
            bl2 = true;
        }
        if (!bl2 || this.var_boolean_d) {
            this.var_byte_a = (byte)2;
            return;
        }
        this.var_byte_a = 0;
        this.var_byte_b = (byte)2;
        r.a("- STORY MODE", 52);
        bs.var_bs_a.g();
        new Thread(this).start();
    }

    static {
        "*:MAP UPDATE".toCharArray();
    }
}

