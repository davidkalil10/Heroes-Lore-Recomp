/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 */
import javax.microedition.lcdui.Graphics;

public final class s
extends cb {
    private byte[] h;
    private byte c;
    private byte d;
    private char[] a;
    private char[] b;

    public s(cb cb2) {
        super(cb2, (byte)0);
        int n2 = n.var_boolean_arr_arr_a[n.var_byte_a - 6].length;
        int n3 = 0;
        this.h = new byte[n2 * 2];
        for (int n4 = 0; n4 < n2; n4 = (int)((byte)(n4 + 1))) {
            if (n.boolean_b(1 + n4 * 3 + 1)) continue;
            if (n.boolean_b(1 + n4 * 3 + 2)) {
                if (!n.boolean_b(1 + n4 * 3) || ce.var_z_f.a(n4 * 7 + 2).length <= 0) continue;
                this.h[n3++] = n4;
                this.h[n3++] = 2;
                continue;
            }
            if (!n.boolean_b(1 + n4 * 3) || ce.var_z_f.a(n4 * 7).length <= 0) continue;
            this.h[n3++] = n4;
            this.h[n3++] = 0;
        }
        this.var_byte_a = (byte)(n3 / 2);
        this.void_d();
    }

    public final boolean a(int n2, int n3) {
        if (this.b(n2, n3)) {
            return true;
        }
        if (this.c(n2, n3)) {
            this.var_cb_a.var_boolean_a = true;
            this.void_d();
            return true;
        }
        if (n3 == 53 || n2 == 8) {
            if (this.var_byte_a > 0) {
                this.var_cb_b = new am(this, this.a, this.b, 0);
            }
            return true;
        }
        return false;
    }

    public final void a(byte by2, byte by3) {
        super.a(by2, by3);
        if (by2 == 0 && by3 == 1) {
            this.var_cb_b = new am(this, ai.var_z_a.a(54), ce.var_z_f.a(this.c * 7 + 6), 1);
        }
    }

    private final void void_d() {
        this.c = this.h[this.var_byte_b * 2];
        this.d = this.h[this.var_byte_b * 2 + 1];
        if (this.d == 2) {
            int n2 = this.c * 7 + 2;
            this.a = ce.var_z_f.a(n2);
            this.b = ce.var_z_f.a(n2 + 1);
            return;
        }
        int n3 = this.c * 7 + 0;
        this.a = ce.var_z_f.a(n3);
        this.b = ce.var_z_f.a(n3 + 1);
    }

    public final void a(Graphics graphics, int n2, int n3) {
        n2 += 2;
        n3 += 15;
        if (r.h > 128) {
            r.a(graphics, ai.var_z_a.a(39), n2 + 5, n3);
        }
        if (this.var_byte_a > 0) {
            int n4;
            this.a(graphics, n2, n3, true);
            for (n4 = this.int_c(); n4 <= this.int_d(); ++n4) {
                graphics.drawImage(ce.var_javax_microedition_lcdui_Image_arr_d[18], n2 + 13, n3 + 18 + 23 * (n4 % 5), 3);
            }
            n4 = n3 + 14;
            graphics.setColor(0xFFFFFF);
            bh.a(graphics, n2 + 33, n4, 151, 1, this.a);
            graphics.setColor(14663551);
            bh.int_a(graphics, n2 + 33, n4 += bh.a(this.a, 151) * 15, n.var_boolean_arr_arr_a[n.var_byte_a - 6][this.c] ? ai.var_z_a.a(55) : ai.var_z_a.a(56), 1);
            bh.int_a(graphics, n2 + 33, n4 += 15, ai.var_z_a.a(57), 1);
            n4 += 15;
            if (ce.var_z_f.a(this.c * 7 + 4).length > 0) {
                graphics.setColor(0xFFFFFF);
                bh.int_a(graphics, n2 + 33, n4, ce.var_z_f.a(this.c * 7 + 4), 1);
                n4 += 15;
            }
            if (ce.var_z_f.a(this.c * 7 + 5).length > 0) {
                graphics.setColor(0xFFFFFF);
                bh.int_a(graphics, n2 + 33, n4, ce.var_z_f.a(this.c * 7 + 5), 1);
                return;
            }
        } else {
            if (r.h > 160) {
                cb.a(graphics, n2 + 4, n3 + 10, 189, 211, 0x3F1F3F, 10452799, 0x3F3F3F);
                cb.a(graphics, n2 + 4, n3 + 10, 189, 211, 0x5F3F3F);
            } else {
                cb.a(graphics, n2 + 4, n3 + 10, 189, r.h - (n3 + 10) - 8, 0x3F1F3F, 10452799, 0x3F3F3F);
                cb.a(graphics, n2 + 4, n3 + 10, 189, r.h - (n3 + 10) - 8, 0x5F3F3F);
            }
            graphics.setColor(0xFFFFFF);
            bh.a(graphics, n2 + 10, n3 + 15, 96, 1, ai.var_z_a.a(58));
        }
    }
}

