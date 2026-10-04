/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 */
import javax.microedition.lcdui.Graphics;

public abstract class av
extends al {
    public byte f;
    public byte g;

    public av(byte by2, byte by3, byte by4, byte by5, byte by6) {
        super((short)(by2 << 4), (short)(by3 << 4), by4, by5);
        ((ck)this).e = by6;
        this.g();
    }

    public void d() {
        this.k = (byte)(this.k + 1);
        ao ao2 = n.a();
        this.f = this.a(ao2);
        this.g = this.b(ao2);
        this.n();
        this.e();
        this.o();
    }

    public abstract void m();

    public void a(Graphics graphics, int n2, int n3) {
        int n4 = n2 + ((ck)this).c + ((ck)this).c + (((ck)this).e - 1) * 8;
        int n5 = n3 + ((ck)this).d + ((ck)this).d;
        int n6 = 0;
        switch (this.h) {
            case 2: {
                n6 = this.n * 16 + 4 + (this.j - 1);
                break;
            }
            case 3: {
                n6 = this.n * 16 + 12 + (this.j - 1);
                break;
            }
            case 5: {
                n6 = this.n * 16 + 8 + (this.j - 1);
                break;
            }
            default: {
                n6 = this.n * 16 + 0 + (this.j - 1);
            }
        }
        if (ce.h[n6] == null) {
            n6 = this.n * 16 + 0 + (this.j - 1);
        }
        as.b(graphics, (byte[])ce.h[n6], this.k, n4, n5);
        this.c(graphics, n4, n5 - this.a.a * 3);
        this.b(graphics, n4, n5);
    }

    public final void a(int n2, byte by2) {
        if (this.h == 6 || this.h == 5) {
            return;
        }
        if (n2 < 0) {
            n2 = 0;
        }
        n2 = n2 * u.a[by2][this.a.c] / 10;
        this.a = (short)(this.a - n2);
        ((o)this).a.addElement(new aw(7, 4, (short)n2));
        ((o)this).a.addElement(new aw(1));
        if (this.a <= 0) {
            this.a((byte)5);
            this.k = 0;
            this.m();
        }
    }

    public final void a(int n2, boolean bl2, byte by2, boolean bl3, byte by3, byte by4, ao ao2) {
        boolean bl4;
        if (this.h == 6 || this.h == 5) {
            return;
        }
        bs.a.a(this, false);
        l l2 = (l)ao2.a(0);
        byte by5 = ao2.a().a();
        if ((n2 -= this.a.c) < 0) {
            n2 = 0;
        }
        n2 = n2 * u.a[by5][this.a.c] / 10;
        if (bl3) {
            n2 += n2 * l2.b / 10;
        }
        if (!(bl4 = h.a(0, 99) < (this.a.d - (ao2.f + ao2.o) - l2.e / 5 + 10) * 2)) {
            switch (by4) {
                case 3: {
                    ao2.d(n2 * 30 / 100);
                    break;
                }
                case 4: {
                    ao2.b(n2 / 2);
                    break;
                }
                case 8: {
                    n2 *= 2;
                }
            }
            ((o)this).a.addElement(new aw(by3));
            this.b(n2);
            if (this.a <= 0) {
                this.m();
            }
        } else {
            ((o)this).a.addElement(new aw(2));
        }
        if (bl4) {
            bw.a((byte)14, false);
            return;
        }
        if (bl3) {
            bw.a((byte)15, false);
            return;
        }
        bw.a((byte)13, false);
    }

    public void l() {
        n.a.a(this);
    }
}

