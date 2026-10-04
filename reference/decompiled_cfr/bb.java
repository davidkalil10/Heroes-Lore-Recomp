/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 */
import javax.microedition.lcdui.Graphics;

public final class bb
extends m {
    public bb(cb cb2, byte[] byArray) {
        super(cb2, byArray, (byte)0, bp.var_z_a.a(18));
    }

    public final boolean a(int n2, int n3) {
        if (this.b(n2, n3)) {
            return true;
        }
        if (this.c(n2, n3)) {
            return true;
        }
        if (n3 == 53 || n2 == 8) {
            ad ad2 = n.ao_a().var_g_a.a((int)this.h[this.var_byte_b]);
            if (ad2.b()) {
                this.a((byte)1, (byte)0, new Object[]{bp.var_z_a.a(19), bp.var_z_a.a(20)});
            } else {
                this.var_cb_b = new ab(this, ad2, false);
            }
            return true;
        }
        if (n3 == bh.var_int_a || n3 == 35) {
            this.var_cb_a.a((byte)-1, (byte)-1);
            return true;
        }
        return true;
    }

    public final void a(byte by2, byte by3) {
        cb cb2 = this.var_cb_b;
        super.a(by2, by3);
        if (cb2 instanceof ab) {
            this.var_cb_a.void_a();
            byte[] byArray = n.ao_a().var_g_a.byte_arr_a();
            if (byArray.length > 0) {
                this.var_cb_a.var_cb_b = new bb(this.var_cb_a, byArray);
                return;
            }
            this.var_cb_a.a((byte)1, (byte)0, new Object[]{bp.var_z_a.a(21), bp.var_z_a.a(22)});
        }
    }

    public final void a(Graphics graphics, int n2, int n3) {
        bh.a(graphics);
        bh.a(graphics, bh.i, bh.var_char_arr_e);
        super.a(graphics, n2, n3);
        graphics.drawImage(ce.var_javax_microedition_lcdui_Image_c, bp.var_int_a + 201 - 38, bp.b + 244 - 22, 20);
    }
}

