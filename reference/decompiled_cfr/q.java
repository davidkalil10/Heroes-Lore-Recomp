/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 */
import javax.microedition.lcdui.Graphics;

public final class q
extends cb {
    private char[] a = ce.var_z_a.a(n.var_byte_a - 6);
    private char[] b;

    public q(cb cb2) {
        super(cb2, (byte)4);
        int n2 = 3 + n.var_byte_a - 6;
        if (n.g == 1) {
            n2 += 15;
        } else if (n.g >= 2) {
            n2 += 18;
        }
        this.b = ce.var_z_a.a(n2);
    }

    public final boolean a(int n2, int n3) {
        if (this.b(n2, n3)) {
            return true;
        }
        if (this.c(n2, n3)) {
            return true;
        }
        if (this.var_byte_b == 3 && (n3 == 53 || n2 == 8)) {
            if (n.ao_a().var_short_a > 0) {
                this.var_cb_b = new bi(this);
            } else {
                Object[] objectArray = new Object[]{ai.var_z_a.a(0), ai.var_z_a.a(1)};
                this.a(objectArray);
            }
            return true;
        }
        return false;
    }

    public final void a(Graphics graphics, int n2, int n3) {
        n2 += 2;
        n3 += 15;
        ao ao2 = n.ao_a();
        char[] cArray = ai.var_z_a.a(2);
        if (r.h > 128) {
            r.a(graphics, cArray, n2 + 5, n3);
        }
        if (r.g >= 176) {
            cb.a(graphics, n2 + 110, n3 + 2, ao2.var_g_a.var_int_a);
        } else {
            cb.a(graphics, r.g - 3, 3, ao2.var_g_a.var_int_a);
        }
        this.a(graphics, n2, n3, false);
        r.c(graphics, 1, n2 + 12, n3 + 16, 4);
        r.c(graphics, 2, n2 + 12, n3 + 16 + 23, 4);
        r.c(graphics, 3, n2 + 12, n3 + 16 + 46, 4);
        graphics.drawImage(ce.var_javax_microedition_lcdui_Image_b, n2 + 10, n3 + 14 + 69, 20);
        if (ao2.var_short_a > 0) {
            graphics.drawImage(ce.var_javax_microedition_lcdui_Image_m, n2 + 3, n3 + 18 + 69, 36);
        }
        switch (this.var_byte_b) {
            case 0: {
                graphics.setColor(0xFFFFFF);
                bh.int_a(graphics, n2 + 35, n3 + 18, this.a, 1);
                graphics.setColor(14663551);
                if (r.g < 176) {
                    bh.a(graphics, n2 + 35, n3 + 29, 75, 1, this.b);
                } else {
                    bh.int_a(graphics, n2 + 33, n3 + 35, this.b, 1);
                }
                graphics.drawImage(ce.var_javax_microedition_lcdui_Image_h, n2 + 35, n3 + 52, 20);
                r.c(graphics, ao2.var_byte_g, n2 + 52, n3 + 52, 4);
                graphics.drawImage(ce.var_javax_microedition_lcdui_Image_f, n2 + 34, n3 + 70, 20);
                r.c(graphics, ao2.var_int_c, n2 + 102, n3 + 70, 8);
                graphics.setColor(0x3F1F3F);
                graphics.fillRect(n2 + 34, n3 + 79, 72, 3);
                graphics.setColor(0xFFFFFF);
                graphics.fillRect(n2 + 34 + 1, n3 + 79 + 1, ao2.var_int_c * 70 / ao2.var_int_f, 1);
                graphics.drawImage(ce.var_javax_microedition_lcdui_Image_i, n2 + 38, n3 + 84, 20);
                r.c(graphics, ao2.var_int_f, n2 + 102, n3 + 84, 8);
                graphics.drawImage(ce.var_javax_microedition_lcdui_Image_g, n2 + 34, n3 + 97, 20);
                r.d(graphics, n2 + 102, n3 + 96, ao2.var_int_a, ao2.var_int_d);
                graphics.drawImage(ce.var_javax_microedition_lcdui_Image_k, n2 + 34, n3 + 106, 20);
                r.d(graphics, n2 + 102, n3 + 105, ao2.var_int_b, ao2.var_int_e);
                return;
            }
            case 1: {
                graphics.setColor(14663551);
                for (int i2 = 0; i2 < 6; ++i2) {
                    bh.int_a(graphics, n2 + 38, n3 + 21 + i2 * 15, ce.var_z_a.a(9 + i2), 1);
                }
                r.c(graphics, ao2.var_short_b + ao2.m, n2 + 100, n3 + 22, 8);
                r.c(graphics, ao2.var_short_e + ao2.n, n2 + 100, n3 + 22 + 15, 8);
                r.c(graphics, ao2.var_short_f + ao2.o, n2 + 100, n3 + 22 + 30, 8);
                r.c(graphics, ao2.var_short_g + ao2.p, n2 + 100, n3 + 22 + 45, 8);
                r.c(graphics, ao2.var_short_h, n2 + 100, n3 + 22 + 60, 8);
                r.c(graphics, ao2.var_short_i, n2 + 100, n3 + 22 + 75, 8);
                return;
            }
            case 2: {
                graphics.setColor(14663551);
                bh.int_a(graphics, n2 + 34, n3 + 18, this.a, 1);
                graphics.setColor(0xFFFFFF);
                char[] cArray2 = ce.var_z_a.a(n.var_byte_a);
                if (r.g > 128) {
                    bh.a(graphics, n2 + 34, n3 + 30, 110, 1, cArray2);
                    return;
                }
                bh.a(graphics, n2 + 34, n3 + 30, 75, 1, cArray2);
                return;
            }
            case 3: {
                cb.b(graphics, n2 + 34, n3 + 22, 151, 26, 0x3F1F3F);
                graphics.setColor(0xFFFFFF);
                r.a(graphics, ai.var_z_a.a(3), n2 + 37, n3 + 25);
                r.a(graphics, ai.var_z_a.a(4), n2 + 37, n3 + 32 + 4);
                r.c(graphics, n.ao_a().var_short_a, n2 + 99, n3 + 32 + 4, 8);
                cb.b(graphics, n2 + 34, n3 + 62, 151, 33, 0x3F1F3F);
                graphics.setColor(0xFFFFFF);
                bh.int_a(graphics, n2 + 40, n3 + 72, ai.var_z_a.a(5), 1);
                graphics.setColor(14663551);
                bh.int_a(graphics, n2 + 60, n3 + 67, ai.var_z_a.a(6), 1);
                bh.int_a(graphics, n2 + 60, n3 + 80, ai.var_z_a.a(7), 1);
            }
        }
    }
}

