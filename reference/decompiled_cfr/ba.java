/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 */
import javax.microedition.lcdui.Graphics;

public final class ba
extends av {
    private byte v;

    public ba(ae ae2, byte by2, byte by3, byte by4, byte by5) {
        super(by2, (byte)(by3 + 5), by4, by5, (byte)2);
        ae2.var_ck_arr_arr_a[by3 + 5][by2] = null;
        ae2.var_ck_arr_arr_a[by3 + 5][by2 + 1] = null;
        this.var_j_a.h = (byte)2;
        this.v = 0;
    }

    public final void void_d() {
        this.k = (byte)(this.k + 1);
        this.v = (byte)(this.v + 1);
        if (this.v > 100) {
            this.var_j_a.h = 0;
        }
        this.n();
        if (this.h == 2) {
            switch (this.i) {
                case 1: {
                    this.var_short_d = (short)(this.var_short_d - 8);
                    break;
                }
                case 2: {
                    this.var_short_d = (short)(this.var_short_d + 8);
                }
            }
            this.void_b();
        }
        this.o();
    }

    public final void a(Graphics graphics, int n2, int n3) {
        byte by2 = this.j;
        this.j = 1;
        super.a(graphics, n2, n3 -= 80);
        this.j = by2;
    }

    public final void i() {
        ao ao2 = n.ao_a();
        int n2 = ao2.var_byte_b - (this.var_byte_b - 5 + 3);
        if (this.p == 0 && n2 >= -2 && n2 <= 3 && ao2.var_byte_a <= this.var_byte_a + 5) {
            this.q();
            return;
        }
        if (this.o == 0) {
            if (n2 > 3) {
                this.void_a((byte)2);
                this.b((byte)2);
                return;
            }
            if (n2 < -2) {
                this.void_a((byte)2);
                this.b((byte)1);
                return;
            }
            this.void_a((byte)1);
            this.b((byte)2);
        }
    }

    public final void j() {
        ao ao2 = n.ao_a();
        if (this.k == 6) {
            n.var_ae_a.var_int_e = 2;
            n.var_ae_a.f = 3;
        } else if (this.k == 7) {
            n.var_ae_a.var_int_e = -3;
            n.var_ae_a.f = -1;
        } else if (this.k == 8) {
            n.var_ae_a.var_int_e = 2;
            n.var_ae_a.f = -3;
        }
        if (this.k == 5) {
            byte by2 = (byte)(this.var_byte_a + 2);
            byte by3 = (byte)(this.var_byte_a + 5);
            byte by4 = (byte)(this.var_byte_b - 5 + 1);
            byte by5 = (byte)(this.var_byte_b - 5 + 6);
            if (ao2.var_byte_a >= by2 && ao2.var_byte_a <= by3 && ao2.var_byte_b >= by4 && ao2.var_byte_b <= by5) {
                ao2.a(this, (byte)2);
                this.v = 0;
                this.var_j_a.h = (byte)2;
            }
        }
    }

    public final void m() {
        this.q = 0;
    }
}

