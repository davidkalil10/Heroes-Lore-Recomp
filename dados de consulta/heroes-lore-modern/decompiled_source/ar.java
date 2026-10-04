/*
 * Decompiled with CFR 0.152.
 */
public final class ar
extends av {
    public ar(byte by2, byte by3, byte by4, byte by5) {
        super(by2, by3, by4, by5, (byte)1);
    }

    public final boolean a() {
        return false;
    }

    public final void i() {
        ao ao2 = n.a();
        if (this.p == 0 && this.f <= 1) {
            this.b((byte)2);
            this.q();
            return;
        }
        if (this.o == 0) {
            if (this.f > 1) {
                if (((ck)ao2).a > ((ck)this).a) {
                    this.a((byte)2);
                    this.b((byte)4);
                    return;
                }
                if (((ck)ao2).a < ((ck)this).a) {
                    this.a((byte)2);
                    this.b((byte)3);
                    return;
                }
            } else {
                this.a((byte)1);
                this.b((byte)2);
            }
        }
    }

    public final void j() {
        if (this.k == 2) {
            n.a.b(new i((byte)(((ck)this).a - 1), ((ck)this).b, (byte[])ce.f[this.n], this, this.i, 13, 2));
            n.a.b(new i((byte)(((ck)this).a + 1), ((ck)this).b, (byte[])ce.f[this.n], this, this.i, 13, 2));
            n.a.b(new i(((ck)this).a, (byte)(((ck)this).b + 1), (byte[])ce.f[this.n], this, this.i, 13, 2));
        }
    }

    public final void l() {
        super.l();
        n.a.a(false);
    }

    public final void m() {
        this.q = 0;
    }
}

