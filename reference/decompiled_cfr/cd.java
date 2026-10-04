/*
 * Decompiled with CFR 0.152.
 */
public final class cd
extends av {
    private ag var_ag_a;
    private bd var_bd_a;
    private byte v = 0;

    public cd(byte by2, byte by3, byte by4, byte by5) {
        super(by2, by3, by4, by5, (byte)2);
    }

    public final void a(ag ag2, bd bd2) {
        this.var_ag_a = ag2;
        this.var_bd_a = bd2;
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
        if (this.k == 5) {
            if (this.var_ag_a.h != 6 && this.var_ag_a.h != 5 && this.var_ag_a.var_short_a < this.var_ag_a.var_j_a.var_short_a / 2) {
                this.a(this.var_ag_a);
                return;
            }
            if (this.var_bd_a.h != 6 && this.var_bd_a.h != 5 && this.var_bd_a.var_short_a < this.var_bd_a.var_j_a.var_short_a / 2) {
                this.a(this.var_bd_a);
                return;
            }
            if (this.h != 6 && this.h != 5 && this.var_short_a < this.var_j_a.var_short_a / 2) {
                this.a(this);
                return;
            }
            switch (this.v) {
                case 0: {
                    if (this.var_ag_a.var_short_a < this.var_ag_a.var_j_a.var_short_a) {
                        this.a(this.var_ag_a);
                    }
                    this.v = 1;
                    return;
                }
                case 1: {
                    if (this.var_bd_a.var_short_a < this.var_bd_a.var_j_a.var_short_a) {
                        this.a(this.var_bd_a);
                    }
                    this.v = (byte)2;
                    return;
                }
                case 2: {
                    if (this.var_short_a < this.var_j_a.var_short_a) {
                        this.a(this);
                    }
                    this.v = 0;
                }
            }
        }
    }

    private void a(al al2) {
        al2.a(new aw(9, -1, this.n));
        al2.c(this.var_ag_a.var_j_a.var_short_a / 10);
        al2.a(new aw(7, 4, (short)(-(this.var_ag_a.var_j_a.var_short_a / 10))));
    }

    public final void m() {
        this.q = 0;
    }
}

