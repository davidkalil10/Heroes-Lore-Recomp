/*
 * Decompiled with CFR 0.152.
 */
public final class bd
extends av {
    private byte v;
    private byte w;

    public bd(byte by2, byte by3, byte by4, byte by5) {
        super(by2, by3, by4, by5, (byte)2);
    }

    public final void void_d() {
        this.k = (byte)(this.k + 1);
        this.n();
        this.o();
    }

    public final void h() {
        if (this.k >= this.var_j_a.j) {
            this.void_a((byte)1);
        }
    }

    public final void i() {
        if (this.p == 0) {
            this.q();
            return;
        }
    }

    public final void j() {
        ao ao2 = n.ao_a();
        if (this.k == 4) {
            this.void_a(ao2.var_byte_a, ao2.var_byte_b);
            this.v = ao2.var_byte_a;
            this.w = ao2.var_byte_b;
        }
        if (this.k == 7 && this.v == ao2.var_byte_a && this.w == ao2.var_byte_b) {
            ao2.a(this, this.i);
        }
    }

    public final void m() {
        this.q = 0;
    }
}

