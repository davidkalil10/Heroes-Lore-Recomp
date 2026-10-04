/*
 * Decompiled with CFR 0.152.
 */
/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public final class cd
extends av {
    private ag a;
    private bd a;
    private byte v = 0;

    public cd(byte by2, byte by3, byte by4, byte by5) {
        super(by2, by3, by4, by5, (byte)2);
    }

    public final void a(ag ag2, bd bd2) {
        this.a = ag2;
        this.a = bd2;
    }

    public final void d() {
        this.k = (byte)(this.k + 1);
        this.n();
        this.o();
    }

    public final void h() {
        if (this.k >= ((al)this).a.j) {
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
        if (this.k == 5) {
            if (this.a.h != 6 && this.a.h != 5 && ((al)this.a).a < ((al)this.a).a.a / 2) {
                this.a(this.a);
                return;
            }
            if (this.a.h != 6 && this.a.h != 5 && this.a.a < this.a.a.a / 2) {
                this.a(this.a);
                return;
            }
            if (this.h != 6 && this.h != 5 && ((al)this).a < ((al)this).a.a / 2) {
                this.a(this);
                return;
            }
            switch (this.v) {
                case 0: {
                    if (((al)this.a).a < ((al)this.a).a.a) {
                        this.a(this.a);
                    }
                    this.v = 1;
                    return;
                }
                case 1: {
                    if (this.a.a < this.a.a.a) {
                        this.a(this.a);
                    }
                    this.v = (byte)2;
                    return;
                }
                case 2: {
                    if (((al)this).a < ((al)this).a.a) {
                        this.a(this);
                    }
                    this.v = 0;
                }
            }
        }
    }

    private void a(al al2) {
        al2.a(new aw(9, -1, this.n));
        al2.c(((al)this.a).a.a / 10);
        al2.a(new aw(7, 4, (short)(-(((al)this.a).a.a / 10))));
    }

    public final void m() {
        this.q = 0;
    }
}

