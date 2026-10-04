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

    public final void d() {
        this.k = (byte)(this.k + 1);
        this.n();
        this.o();
    }

    public final void h() {
        if (this.k >= this.a.j) {
            this.a((byte)1);
        }
    }

    public final void i() {
        if (this.p == 0) {
            this.q();
            return;
        }
    }

    public final void j() {
        ao ao2 = n.a();
        if (this.k == 4) {
            this.a(((ck)ao2).a, ((ck)ao2).b);
            this.v = ((ck)ao2).a;
            this.w = ((ck)ao2).b;
        }
        if (this.k == 7 && this.v == ((ck)ao2).a && this.w == ((ck)ao2).b) {
            ao2.a(this, this.i);
        }
    }

    public final void m() {
        this.q = 0;
    }
}

