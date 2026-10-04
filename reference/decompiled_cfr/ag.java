/*
 * Decompiled with CFR 0.152.
 */
public final class ag
extends av {
    private cd var_cd_a;
    private bd var_bd_a;

    public ag(byte by2, byte by3, byte by4, byte by5) {
        super(by2, by3, by4, by5, (byte)3);
    }

    public final void a(cd cd2, bd bd2) {
        this.var_cd_a = cd2;
        this.var_bd_a = bd2;
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
        if (this.p == 0) {
            this.b((byte)2);
            this.q();
            return;
        }
    }

    public final void j() {
        if (this.k == 2) {
            n.var_ae_a.b(new i((byte)(this.var_byte_a - 1), (byte)(this.var_byte_b - 1), (byte[])ce.var_java_lang_Object_arr_f[this.n], this, this.i, 13, 2));
            n.var_ae_a.b(new i((byte)(this.var_byte_a + 3), (byte)(this.var_byte_b - 1), (byte[])ce.var_java_lang_Object_arr_f[this.n], this, this.i, 13, 2));
            n.var_ae_a.b(new i(this.var_byte_a, this.var_byte_b, (byte[])ce.var_java_lang_Object_arr_f[this.n], this, this.i, 13, 2));
            n.var_ae_a.b(new i((byte)(this.var_byte_a + 2), this.var_byte_b, (byte[])ce.var_java_lang_Object_arr_f[this.n], this, this.i, 13, 2));
            n.var_ae_a.b(new i((byte)(this.var_byte_a + 1), (byte)(this.var_byte_b + 1), (byte[])ce.var_java_lang_Object_arr_f[this.n], this, this.i, 13, 2));
        }
    }

    public final void k() {
        if (this.q > 8) {
            n.var_ae_a.b(new y((byte)(this.var_byte_a + h.a(-2, 2)), (byte)(this.var_byte_b + h.a(-2, 2)), (byte[])ce.var_java_lang_Object_arr_f[this.var_cd_a.n]));
            n.var_ae_a.b(new y((byte)(this.var_byte_a + h.a(-2, 2)), (byte)(this.var_byte_b + h.a(-2, 2)), (byte[])ce.var_java_lang_Object_arr_f[this.var_cd_a.n]));
        }
    }

    public final void l() {
        super.l();
        ah.void_a((byte)1);
    }

    public final void m() {
        this.var_cd_a.l();
        this.var_bd_a.l();
        this.q = (byte)24;
    }
}

