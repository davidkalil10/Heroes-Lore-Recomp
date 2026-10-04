/*
 * Decompiled with CFR 0.152.
 */
public final class ar
extends av {
    public ar(byte by2, byte by3, byte by4, byte by5) {
        super(by2, by3, by4, by5, (byte)1);
    }

    public final boolean boolean_a() {
        return false;
    }

    public final void i() {
        ao ao2 = n.ao_a();
        if (this.p == 0 && this.f <= 1) {
            this.b((byte)2);
            this.q();
            return;
        }
        if (this.o == 0) {
            if (this.f > 1) {
                if (ao2.var_byte_a > this.var_byte_a) {
                    this.void_a((byte)2);
                    this.b((byte)4);
                    return;
                }
                if (ao2.var_byte_a < this.var_byte_a) {
                    this.void_a((byte)2);
                    this.b((byte)3);
                    return;
                }
            } else {
                this.void_a((byte)1);
                this.b((byte)2);
            }
        }
    }

    public final void j() {
        if (this.k == 2) {
            n.var_ae_a.b(new i((byte)(this.var_byte_a - 1), this.var_byte_b, (byte[])ce.var_java_lang_Object_arr_f[this.n], this, this.i, 13, 2));
            n.var_ae_a.b(new i((byte)(this.var_byte_a + 1), this.var_byte_b, (byte[])ce.var_java_lang_Object_arr_f[this.n], this, this.i, 13, 2));
            n.var_ae_a.b(new i(this.var_byte_a, (byte)(this.var_byte_b + 1), (byte[])ce.var_java_lang_Object_arr_f[this.n], this, this.i, 13, 2));
        }
    }

    public final void l() {
        super.l();
        n.var_ae_a.a(false);
    }

    public final void m() {
        this.q = 0;
    }
}

