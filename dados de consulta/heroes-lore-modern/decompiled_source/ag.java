/*
 * Decompiled with CFR 0.152.
 */
/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public final class ag
extends av {
    private cd a;
    private bd a;

    public ag(byte by2, byte by3, byte by4, byte by5) {
        super(by2, by3, by4, by5, (byte)3);
    }

    public final void a(cd cd2, bd bd2) {
        this.a = cd2;
        this.a = bd2;
    }

    public final void d() {
        this.k = (byte)(this.k + 1);
        this.n();
        this.o();
    }

    public final void h() {
        if (this.k >= ((al)this).a.k) {
            this.a((byte)1);
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
            n.a.b(new i((byte)(((ck)this).a - 1), (byte)(((ck)this).b - 1), (byte[])ce.f[this.n], this, this.i, 13, 2));
            n.a.b(new i((byte)(((ck)this).a + 3), (byte)(((ck)this).b - 1), (byte[])ce.f[this.n], this, this.i, 13, 2));
            n.a.b(new i(((ck)this).a, ((ck)this).b, (byte[])ce.f[this.n], this, this.i, 13, 2));
            n.a.b(new i((byte)(((ck)this).a + 2), ((ck)this).b, (byte[])ce.f[this.n], this, this.i, 13, 2));
            n.a.b(new i((byte)(((ck)this).a + 1), (byte)(((ck)this).b + 1), (byte[])ce.f[this.n], this, this.i, 13, 2));
        }
    }

    public final void k() {
        if (this.q > 8) {
            n.a.b(new y((byte)(((ck)this).a + h.a(-2, 2)), (byte)(((ck)this).b + h.a(-2, 2)), (byte[])ce.f[this.a.n]));
            n.a.b(new y((byte)(((ck)this).a + h.a(-2, 2)), (byte)(((ck)this).b + h.a(-2, 2)), (byte[])ce.f[this.a.n]));
        }
    }

    public final void l() {
        super.l();
        ah.a((byte)1);
    }

    public final void m() {
        this.a.l();
        this.a.l();
        this.q = (byte)24;
    }
}

