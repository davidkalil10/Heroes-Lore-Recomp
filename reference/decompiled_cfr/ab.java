/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 */
import javax.microedition.lcdui.Graphics;

public final class ab
extends cb {
    private ad a;
    private byte var_byte_c;
    private boolean var_boolean_c;

    public ab(cb cb2, ad ad2, boolean bl2) {
        super(cb2, (byte)0);
        this.a = ad2;
        this.var_byte_c = 1;
        this.var_boolean_c = bl2;
    }

    public final boolean a(int n2, int n3) {
        if (this.b(n2, n3)) {
            return true;
        }
        if (this.d(n2, n3)) {
            return true;
        }
        if (n3 == 53 || n2 == 8) {
            if (this.var_boolean_c) {
                Object[] objectArray = new Object[]{bp.var_z_a.a(7)};
                if (this.a.f == 0 && n.var_byte_a != 6 || this.a.f == 2 && n.var_byte_a != 7 || this.a.f == 1 && n.var_byte_a != 8 || this.a.f == 3 && n.var_byte_a != 8) {
                    objectArray = new Object[]{bp.var_z_a.a(26), bp.var_z_a.a(7)};
                }
                this.a((byte)2, (byte)2, objectArray);
            } else {
                this.a((byte)2, (byte)2, new Object[]{bp.var_z_a.a(23)});
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
        ao ao2 = n.ao_a();
        if (by2 == 2 && by3 == 0) {
            if (this.var_boolean_c) {
                int n2;
                ad ad2 = ad.a(this.a.f, this.a.g, true, false);
                if (ad2 instanceof e) {
                    ((e)ad2).b = true;
                }
                if (ao2.var_g_a.var_int_a < (n2 = ad2.var_int_a * this.var_byte_c)) {
                    this.a(new Object[]{bp.var_z_a.a(8)});
                    return;
                }
                if (!ao2.var_g_a.a(ad2, (int)this.var_byte_c)) {
                    this.a(new Object[]{bp.var_z_a.a(9), bp.var_z_a.a(10)});
                    return;
                }
                ao2.var_g_a.var_int_a -= n2;
                this.a(new Object[]{bp.var_z_a.a(11), bp.var_z_a.a(12)});
                return;
            }
            ao2.var_g_a.void_a(this.a, this.var_byte_c);
            ao2.var_g_a.var_int_a += this.a.var_int_a * this.var_byte_c / 5;
            this.a(new Object[]{this.a.var_char_arr_a, bp.var_z_a.a(24)});
            return;
        }
        if (by2 == 1) {
            this.var_cb_a.a((byte)-1, (byte)-1);
        }
    }

    public final void a(byte by2) {
        if (this.var_boolean_c && ad.var_boolean_arr_b[this.a.f] || !this.var_boolean_c && this.a.h > 1) {
            if (by2 == 4 && this.var_byte_c < (this.var_boolean_c ? (byte)99 : this.a.h)) {
                this.var_byte_c = (byte)(this.var_byte_c + 1);
                return;
            }
            if (by2 == 4 && this.var_boolean_c && this.var_byte_c == 99) {
                this.var_byte_c = 1;
                return;
            }
            if (by2 == 3 && this.var_byte_c > 1) {
                this.var_byte_c = (byte)(this.var_byte_c - 1);
                return;
            }
            if (by2 == 3 && this.var_boolean_c && this.var_byte_c == 1) {
                this.var_byte_c = (byte)99;
            }
        }
    }

    public final void a(Graphics graphics, int n2, int n3) {
        bh.a(graphics);
        if (this.var_boolean_c) {
            bh.a(graphics, bh.j, bh.var_char_arr_e);
        } else {
            bh.a(graphics, bh.h, bh.var_char_arr_e);
        }
        boolean bl2 = false;
        cb.a(graphics, n2 += 3, n3 += 20, 195, 29);
        cb.b(graphics, n2, n3, 195, 29);
        cb.a(graphics, n2, n3 + 31, 195, 67);
        cb.b(graphics, n2, n3 + 31, 195, 67);
        graphics.setColor(14663551);
        bh.int_a(graphics, (n2 += 15) + 8, n3 + 7, bp.var_z_a.a(13), 1);
        cb.a(graphics, n2 + 102, n3 + 11, n.ao_a().var_g_a.var_int_a);
        graphics.setColor(0xFFFFFF);
        if (this.var_boolean_c && ad.var_boolean_arr_b[this.a.f] || !this.var_boolean_c && this.a.h > 1) {
            if (this.var_boolean_c) {
                bh.int_a(graphics, n2 + 6, n3 + 38, bp.var_z_a.a(14), 1);
            } else {
                bh.int_a(graphics, n2 + 6, n3 + 38, bp.var_z_a.a(25), 1);
            }
            graphics.drawImage(ce.var_javax_microedition_lcdui_Image_p, n2 + 32, n3 + 65, 20);
            r.c(graphics, this.var_byte_c, n2 + 68, n3 + 65, 8);
            graphics.drawImage(ce.var_javax_microedition_lcdui_Image_e, n2 + 77, n3 + 65, 20);
        } else {
            bh.int_a(graphics, n2 + 6, n3 + 38, bp.var_z_a.a(15), 1);
        }
        graphics.drawImage(ce.var_javax_microedition_lcdui_Image_arr_d[this.a.f], n2 + 45, n3 + 57, 20);
        if (this.var_boolean_c) {
            cb.a(graphics, n2 + 77, n3 + 85, this.var_byte_c * this.a.var_int_a);
            return;
        }
        cb.a(graphics, n2 + 77, n3 + 85, this.var_byte_c * this.a.var_int_a / 5);
    }
}

