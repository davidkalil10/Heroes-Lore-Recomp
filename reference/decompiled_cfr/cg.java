/*
 * Decompiled with CFR 0.152.
 */
public final class cg
extends av {
    private ba var_ba_a;
    private ak var_ak_a;
    private boolean g;
    private byte v;

    public cg(byte by2, byte by3, byte by4, byte by5, ba ba2, ak ak2) {
        super(by2, by3, by4, by5, (byte)2);
        this.var_ba_a = ba2;
        this.var_ak_a = ak2;
        this.g = false;
        this.v = 0;
    }

    public final void void_d() {
        this.k = (byte)(this.k + 1);
        this.n();
        this.o();
    }

    public final void h() {
        if (this.k >= this.var_j_a.k) {
            this.void_a((byte)1);
        }
    }

    public final void i() {
        if (this.v >= 3) {
            this.p = (byte)40;
            this.v = 0;
        }
        if (this.p == 0) {
            this.b((byte)2);
            this.q();
            this.v = (byte)(this.v + 1);
            return;
        }
    }

    public final void j() {
        ao ao2 = n.ao_a();
        ae ae2 = n.var_ae_a;
        switch (this.k) {
            case 6: {
                this.a(ao2, ae2, this.var_byte_b);
                return;
            }
            case 7: {
                this.a(ao2, ae2, (byte)(this.var_byte_b + 1));
                return;
            }
            case 8: {
                this.a(ao2, ae2, (byte)(this.var_byte_b + 2));
                return;
            }
            case 9: {
                this.a(ao2, ae2, (byte)(this.var_byte_b + 3));
                return;
            }
            case 11: {
                this.a(ao2, ae2, (byte)(this.var_byte_b + 4));
                return;
            }
            case 12: {
                for (int n2 = 6; n2 <= 9; n2 = (int)((byte)(n2 + 1))) {
                    if (ae2.var_ck_arr_arr_a[this.var_byte_b + 4][n2] != this) continue;
                    ae2.var_ck_arr_arr_a[this.var_byte_b + 4][n2] = null;
                }
                if (ao2.var_byte_a < 6 || ao2.var_byte_a > 9 || ao2.var_byte_b < this.var_byte_b + 5 || ao2.var_byte_b > this.var_byte_b + 8) break;
                ao2.a(this, (short)(this.var_j_a.var_short_b / 2), (byte)2);
            }
        }
    }

    public final void k() {
        if (this.k >= this.var_j_a.m) {
            this.k = (byte)(this.var_j_a.m - 1);
        }
    }

    private void a(ao ao2, ae ae2, byte by2) {
        int n2;
        for (n2 = 6; n2 <= 9; n2 = (int)((byte)(n2 + 1))) {
            if (ae2.var_ck_arr_arr_a[by2][n2] != ao2) continue;
            ao2.void_a((byte)2, (byte)16);
            ao2.a(this, (byte)2);
            break;
        }
        for (n2 = 6; n2 <= 9; n2 = (int)((byte)(n2 + 1))) {
            if (ae2.var_ck_arr_arr_a[by2 - 1][n2] != this) continue;
            ae2.var_ck_arr_arr_a[by2 - 1][n2] = null;
        }
        for (n2 = 6; n2 <= 9; n2 = (int)((byte)(n2 + 1))) {
            x.a(ae2.var_ck_arr_arr_a[by2][n2] != ao2);
            ae2.var_ck_arr_arr_a[by2][n2] = this;
        }
        this.g();
    }

    public final void l() {
        if (!this.g) {
            ae ae2 = n.var_ae_a;
            for (int n2 = 6; n2 <= 9; n2 = (int)((byte)(n2 + 1))) {
                for (byte by2 = this.var_byte_b; by2 <= this.var_byte_b + 2; by2 = (byte)(by2 + 1)) {
                    ae2.var_byte_arr_arr_c[by2][n2] = 1;
                }
            }
            this.g = true;
        }
    }

    public final void m() {
        this.q = (byte)12;
        ae ae2 = n.var_ae_a;
        for (int n2 = 6; n2 <= 9; n2 = (int)((byte)(n2 + 1))) {
            for (int i2 = this.var_byte_b + 1; i2 <= this.var_byte_b + 5; ++i2) {
                if (ae2.var_ck_arr_arr_a[i2][n2] != this) continue;
                ae2.var_ck_arr_arr_a[i2][n2] = null;
            }
        }
        n.var_ae_a.a(this.var_ba_a);
        n.var_ae_a.a(this.var_ak_a);
    }
}

