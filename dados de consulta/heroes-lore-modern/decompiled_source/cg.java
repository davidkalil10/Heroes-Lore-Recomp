/*
 * Decompiled with CFR 0.152.
 */
/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public final class cg
extends av {
    private ba a;
    private ak a;
    private boolean g;
    private byte v;

    public cg(byte by2, byte by3, byte by4, byte by5, ba ba2, ak ak2) {
        super(by2, by3, by4, by5, (byte)2);
        this.a = ba2;
        this.a = ak2;
        this.g = false;
        this.v = 0;
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
        ao ao2 = n.a();
        ae ae2 = n.a;
        switch (this.k) {
            case 6: {
                this.a(ao2, ae2, ((ck)this).b);
                return;
            }
            case 7: {
                this.a(ao2, ae2, (byte)(((ck)this).b + 1));
                return;
            }
            case 8: {
                this.a(ao2, ae2, (byte)(((ck)this).b + 2));
                return;
            }
            case 9: {
                this.a(ao2, ae2, (byte)(((ck)this).b + 3));
                return;
            }
            case 11: {
                this.a(ao2, ae2, (byte)(((ck)this).b + 4));
                return;
            }
            case 12: {
                for (int n2 = 6; n2 <= 9; n2 = (int)((byte)(n2 + 1))) {
                    if (ae2.a[((ck)this).b + 4][n2] != this) continue;
                    ae2.a[((ck)this).b + 4][n2] = null;
                }
                if (((ck)ao2).a < 6 || ((ck)ao2).a > 9 || ((ck)ao2).b < ((ck)this).b + 5 || ((ck)ao2).b > ((ck)this).b + 8) break;
                ao2.a(this, (short)(((al)this).a.b / 2), (byte)2);
            }
        }
    }

    public final void k() {
        if (this.k >= ((al)this).a.m) {
            this.k = (byte)(((al)this).a.m - 1);
        }
    }

    private void a(ao ao2, ae ae2, byte by2) {
        int n2;
        for (n2 = 6; n2 <= 9; n2 = (int)((byte)(n2 + 1))) {
            if (ae2.a[by2][n2] != ao2) continue;
            ao2.a((byte)2, (byte)16);
            ao2.a(this, (byte)2);
            break;
        }
        for (n2 = 6; n2 <= 9; n2 = (int)((byte)(n2 + 1))) {
            if (ae2.a[by2 - 1][n2] != this) continue;
            ae2.a[by2 - 1][n2] = null;
        }
        for (n2 = 6; n2 <= 9; n2 = (int)((byte)(n2 + 1))) {
            x.a(ae2.a[by2][n2] != ao2);
            ae2.a[by2][n2] = this;
        }
        this.g();
    }

    public final void l() {
        if (!this.g) {
            ae ae2 = n.a;
            for (int n2 = 6; n2 <= 9; n2 = (int)((byte)(n2 + 1))) {
                for (byte by2 = ((ck)this).b; by2 <= ((ck)this).b + 2; by2 = (byte)(by2 + 1)) {
                    ae2.c[by2][n2] = 1;
                }
            }
            this.g = true;
        }
    }

    public final void m() {
        this.q = (byte)12;
        ae ae2 = n.a;
        for (int n2 = 6; n2 <= 9; n2 = (int)((byte)(n2 + 1))) {
            for (int i2 = ((ck)this).b + 1; i2 <= ((ck)this).b + 5; ++i2) {
                if (ae2.a[i2][n2] != this) continue;
                ae2.a[i2][n2] = null;
            }
        }
        n.a.a(this.a);
        n.a.a(this.a);
    }
}

