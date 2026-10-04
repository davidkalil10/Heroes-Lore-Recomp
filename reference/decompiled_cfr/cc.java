/*
 * Decompiled with CFR 0.152.
 */
public final class cc
extends av {
    private static final byte[] h = new byte[]{1, 1, 2, 3, 2, 3};
    private static final byte[] i = new byte[]{2, 2, 12, 24, 12, 24};
    private byte v = 0;
    private byte w;
    private byte x;

    public cc(byte by2, byte by3, byte by4, byte by5) {
        super(by2, by3, by4, by5, (byte)1);
        this.d(h[this.v]);
    }

    public final void void_d() {
        this.k = (byte)(this.k + 1);
        ao ao2 = n.ao_a();
        this.f = this.a(ao2);
        this.g = this.b(ao2);
        this.n();
        this.o();
    }

    public final void n() {
        switch (((o)this).h) {
            case 2: {
                if (this.k < this.var_j_a.k) break;
                this.a(false);
                this.i();
                return;
            }
            case 1: {
                this.i();
                return;
            }
            case 3: {
                if (this.k < this.x) break;
                this.a(false);
                this.v = (byte)(this.v + 1);
                if (this.v >= h.length) {
                    this.v = 0;
                }
                this.d(h[this.v]);
                this.p = i[this.v];
                this.o = i[this.v];
                this.i();
                return;
            }
            case 4: {
                if (this.l < 1) {
                    this.void_a((byte)1);
                }
                this.l = (byte)(this.l - 1);
                return;
            }
            case 5: {
                if (this.q < 1) {
                    this.l();
                }
                this.q = (byte)(this.q - 1);
            }
        }
    }

    public final void i() {
        if (this.p == 0) {
            switch (this.w) {
                case 1: {
                    if (this.f >= 4 || this.g >= 4) break;
                    this.q();
                    return;
                }
                case 2: {
                    if (this.f * this.g != 0 || this.f >= 4 || this.g >= 4) break;
                    this.q();
                    return;
                }
                case 3: {
                    this.q();
                    return;
                }
            }
        }
        if (this.o == 0) {
            switch (this.w) {
                case 1: {
                    if (this.f < 4 && this.g < 4) break;
                    this.void_a((byte)2);
                    this.k = 0;
                    return;
                }
                case 2: {
                    if (this.f * this.g == 0 && this.f < 4 && this.g < 4) break;
                    this.void_a((byte)2);
                    this.k = 0;
                    return;
                }
                case 3: {
                    if (this.f < 3 && this.g < 3) break;
                    this.void_a((byte)2);
                    this.k = 0;
                }
            }
        }
    }

    public final void o() {
        switch (((o)this).h) {
            case 2: {
                int n2;
                if (this.k != 5) break;
                ae ae2 = n.var_ae_a;
                ao ao2 = n.ao_a();
                int n3 = -1;
                int n4 = -1;
                for (n2 = 3; n2 > 0 && !ae2.a(n3, n4); n2 = (int)((byte)(n2 - 1))) {
                    n3 = ao2.var_byte_a > this.var_byte_a ? (int)((byte)h.a(this.var_byte_a, ao2.var_byte_a + 1)) : (int)((byte)h.a(ao2.var_byte_a - 1, (int)this.var_byte_a));
                    n4 = ao2.var_byte_b > this.var_byte_b ? (int)((byte)h.a(this.var_byte_b, ao2.var_byte_b + 1)) : (int)((byte)h.a(ao2.var_byte_b - 1, (int)this.var_byte_b));
                }
                if (n2 <= 0) break;
                this.a((short)(n3 << 4), (short)(n4 << 4));
                return;
            }
            case 3: {
                this.j();
                return;
            }
            case 5: {
                this.k();
                return;
            }
            default: {
                if (this.k >= this.var_j_a.j) {
                    this.k = 0;
                }
                if (this.p > 0) {
                    this.p = (byte)(this.p - 1);
                }
                if (this.o <= 0) break;
                this.o = (byte)(this.o - 1);
            }
        }
    }

    public final void j() {
        ao ao2 = n.ao_a();
        switch (this.w) {
            case 1: {
                if (this.k == 7) {
                    ae ae2 = n.var_ae_a;
                    for (int n2 = 1; n2 <= 4; n2 = (int)((byte)(n2 + 1))) {
                        if (!ae2.a(this, ao2.var_byte_a + u.var_byte_arr_a[n2], ao2.var_byte_b + u.b[n2])) continue;
                        this.a((short)(ao2.var_byte_a + u.var_byte_arr_a[n2] << 4), (short)(ao2.var_byte_b + u.b[n2] << 4));
                        break;
                    }
                }
                if (this.k != 11 || this.f + this.g > 1) break;
                ao2.a(this, ((o)this).i);
                return;
            }
            case 2: {
                if (this.k != 7) break;
                for (byte by2 = 1; by2 <= 4; by2 = (byte)(by2 + 1)) {
                    n.var_ae_a.b(new i((byte)(this.var_byte_a + u.var_byte_arr_a[by2]), (byte)(this.var_byte_b + u.b[by2]), (byte[])ce.var_java_lang_Object_arr_f[this.n], this, by2, 3, 2));
                }
                break;
            }
            case 3: {
                if (this.k != 4 || this.f > 2 || this.g > 2) break;
                ao2.a(this, (short)(this.var_j_a.var_short_b * 2), ((o)this).i);
            }
        }
    }

    public final void k() {
        if (this.q > 8) {
            n.var_ae_a.b(new y((byte)(this.var_byte_a + h.a(-1, 1)), (byte)(this.var_byte_b + h.a(0, 3)), (byte[])ce.var_java_lang_Object_arr_f[this.n]));
        }
    }

    private final void d(byte by2) {
        this.w = by2;
        int n2 = (by2 - 1) * 4;
        for (int i2 = 0; i2 < 4; ++i2) {
            ce.var_java_lang_Object_arr_h[this.n * 16 + 12 + i2] = ce.var_java_lang_Object_arr_i[n2 + i2];
        }
        byte[] byArray = (byte[])ce.var_java_lang_Object_arr_h[this.n * 16 + 12];
        this.x = byArray[0];
    }

    public final void l() {
        super.l();
        ah.void_a((byte)1);
    }

    public final void m() {
        this.q = (byte)24;
    }
}

