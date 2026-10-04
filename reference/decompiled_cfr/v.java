/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 */
import java.util.Vector;
import javax.microedition.lcdui.Graphics;

public final class v
extends cb {
    private ad[] a;
    public byte c;

    public v(cb cb2, Vector vector, byte by2) {
        super(cb2, (byte)vector.size());
        this.a = new ad[vector.size()];
        for (int i2 = 0; i2 < this.a.length; ++i2) {
            this.a[i2] = (ad)vector.elementAt(i2);
        }
        this.c = by2;
    }

    public final boolean a(int n2, int n3) {
        if (this.b(n2, n3)) {
            return true;
        }
        if (this.c(n2, n3)) {
            this.var_cb_a.var_boolean_a = true;
            return true;
        }
        if (n3 == 53 || n2 == 8) {
            this.var_cb_b = new ab(this, this.a[this.var_byte_b], true);
            return true;
        }
        if (n3 == 35) {
            byte[] byArray = n.ao_a().var_g_a.byte_arr_a();
            if (byArray.length > 0) {
                this.var_cb_b = new bb((cb)this, byArray);
            } else {
                this.a((byte)1, (byte)0, new Object[]{bp.var_z_a.a(16), bp.var_z_a.a(17)});
            }
            return true;
        }
        return false;
    }

    public final void a(Graphics graphics, int n2, int n3) {
        ad ad2;
        bh.a(graphics, bh.i, bh.var_char_arr_e);
        this.a(graphics, n2 += 2, n3 += 15, true);
        short s2 = -1;
        ao ao2 = n.ao_a();
        switch (this.c) {
            case 1: {
                if (ao2.l_a() == null) break;
                s2 = ao2.l_a().var_short_a;
                break;
            }
            case 2: {
                if (ao2.t_a() == null) break;
                s2 = ao2.t_a().var_short_a;
                break;
            }
            case 3: {
                if (ao2.e_a() == null) break;
                s2 = ao2.e_a().var_short_a;
                break;
            }
            case 4: {
                if (ao2.e_b() == null) break;
                s2 = ao2.e_b().var_short_a;
                break;
            }
            case 5: {
                if (ao2.e_c() == null) break;
                s2 = ao2.e_c().var_short_a;
            }
        }
        for (int i2 = this.int_c(); i2 <= this.int_d(); ++i2) {
            ad2 = this.a[i2];
            if (ad2 == null) continue;
            cb.a(graphics, n2 + 13, n3 + 18 + 23 * (i2 % 5), ad2, false);
            if (r.h <= 160 || this.c == 0) continue;
            short s3 = ((e)this.a[this.var_byte_b]).var_short_a;
            if (s2 > s3) {
                graphics.drawImage(ce.var_javax_microedition_lcdui_Image_r, n2 + 20, n3 + 18 + this.var_byte_b % 5 * 23, 33);
                continue;
            }
            if (s2 >= s3) continue;
            graphics.drawImage(ce.var_javax_microedition_lcdui_Image_q, n2 + 20, n3 + 18 + this.var_byte_b % 5 * 23, 33);
        }
        ad2 = this.a[this.var_byte_b];
        if (ad2 != null) {
            cb.a(graphics, n2 + 33, n3 + 14, ad2);
        }
        if (r.g < 176) {
            String string = String.valueOf(bp.var_z_a.a(17));
            bh.a(graphics, bp.var_int_a + 201 - 34, bp.b + 244 - 12, "#" + string.substring(0, string.length() - 1).toUpperCase(), 40);
            return;
        }
        graphics.drawImage(ce.var_javax_microedition_lcdui_Image_d, bp.var_int_a + 201 - 40, bp.b + 244 - 20, 20);
    }
}

