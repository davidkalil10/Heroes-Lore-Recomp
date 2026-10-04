/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 */
import javax.microedition.lcdui.Graphics;

public final class c
extends cb {
    public c(bf bf2) {
        super(bf2, (byte)3);
        this.var_byte_b = (byte)2;
    }

    public final boolean a(int n2, int n3) {
        if (this.b(n2, n3)) {
            return true;
        }
        if (this.d(n2, n3)) {
            return true;
        }
        if (n3 == 53 || n2 == 8) {
            if (this.var_byte_b == 0 || this.var_byte_b == 1) {
                if (!bs.var_bs_a.var_boolean_b) {
                    Object[] objectArray = new Object[]{ce.var_z_g.a(6), ce.var_z_g.a(7)};
                    this.a(objectArray);
                } else {
                    boolean bl2 = false;
                    this.var_cb_b = new by(this, (byte)(6 + (2 - this.var_byte_b)));
                }
            } else {
                this.var_cb_b = new by(this, (byte)(6 + (2 - this.var_byte_b)));
            }
            return true;
        }
        if (n3 == bh.var_int_a) {
            this.var_cb_a.void_a();
            return true;
        }
        return true;
    }

    public final void a(byte by2, byte by3) {
        super.a(by2, by3);
        if ((by2 == 2 || by2 == 12) && by3 == 0) {
            bf.void_d();
            ce.B();
            bs.var_bs_a.e();
            n.var_int_e = 0;
        }
    }

    public final void a(Graphics graphics, int n2, int n3) {
        graphics.setColor(0x3F1F3F);
        graphics.fillRect(0, 0, r.g, r.h);
        bf.c(graphics, n2, n3);
        bh.a(graphics, 1, n2 + 201 >> 1, n3 + 5);
        bf.b(graphics, n2, n3 + 24, 3);
        n2 += 40;
        n3 += 35;
        if (this.var_byte_b != 0) {
            graphics.drawImage(ce.var_javax_microedition_lcdui_Image_arr_l[5], n2 + 6, n3 + 38, 20);
        }
        if (this.var_byte_b != 1) {
            graphics.drawImage(ce.var_javax_microedition_lcdui_Image_arr_l[4], n2 + 34, n3 + 38, 20);
        }
        if (this.var_byte_b != 2) {
            graphics.drawImage(ce.var_javax_microedition_lcdui_Image_arr_l[3], n2 + 59, n3 + 38, 20);
        }
        if (this.var_byte_b == 0) {
            graphics.drawImage(ce.var_javax_microedition_lcdui_Image_arr_l[2], n2 + 6, n3 + 38, 20);
        }
        if (this.var_byte_b == 1) {
            graphics.drawImage(ce.var_javax_microedition_lcdui_Image_arr_l[1], n2 + 34, n3 + 38, 20);
        }
        if (this.var_byte_b == 2) {
            graphics.drawImage(ce.var_javax_microedition_lcdui_Image_arr_l[0], n2 + 59, n3 + 38, 20);
        }
        graphics.setColor(0);
        bh.int_a(graphics, n2 + 11, n3 + 104, ce.var_z_g.a(12), 1);
        bh.int_a(graphics, n2 + 11, n3 + 119, ce.var_z_g.a(13), 1);
        bh.a(graphics, bh.var_char_arr_d, bh.var_char_arr_e);
    }
}

