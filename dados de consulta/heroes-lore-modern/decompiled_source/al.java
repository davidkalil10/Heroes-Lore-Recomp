/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 */
import javax.microedition.lcdui.Graphics;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class al
extends o {
    public byte m;
    public byte n;
    private int a;
    private int b;
    public j a;
    public short a;
    private byte r = 0;
    public byte o = 0;
    public byte p = 0;
    public byte q = 0;
    private byte s = 0;
    private byte t = 0;
    private boolean d;
    private boolean e;
    private boolean[] b;
    private ck c;
    private byte u;
    private boolean f;

    public al(short s2, short s3, byte by2, byte by3) {
        super(s2, s3, (byte)8, (byte)8);
        this.a = ((ck)this).a;
        this.b = ((ck)this).b;
        this.m = by2;
        this.n = by3;
        this.a = j.a[by3];
        this.a = this.a.a;
        this.o = this.a.h;
        this.p = this.a.i;
        this.u = (byte)-10;
        this.b = new boolean[5];
        this.d = false;
        if (this.a.d) {
            this.e = true;
        }
        ((ck)this).e = (byte)(this.a.a == 2 ? 2 : 1);
        this.a(s2, s3);
        this.f = true;
    }

    public final void a(short s2, short s3) {
        this.f();
        super.a(s2, s3);
        this.b();
        this.g();
    }

    public void a(Graphics graphics, int n2, int n3) {
        int n4 = n2 + ((ck)this).c + ((ck)this).c + (((ck)this).e - 1) * 8;
        int n5 = n3 + ((ck)this).d + ((ck)this).d;
        if (n4 + 16 < 0 || n5 < 0 || n4 - 16 > as.a || n5 > as.b + 32) {
            this.b(graphics, n4, n5);
            this.f = false;
            return;
        }
        this.f = true;
        if (this.e) {
            return;
        }
        int n6 = n4;
        int n7 = n5;
        if (this.s == 3 || this.s == 1) {
            n7 += u.b[this.t] * 3;
            n6 += u.a[this.t] * 3;
        }
        if (this.m != 22 && this.m != 16 && !(this instanceof av) || this instanceof cc) {
            if (((ck)this).e == 1) {
                graphics.drawImage(ce.v, n6, n7 - 3, 17);
            } else {
                graphics.setColor(0x1F3F3F);
                graphics.fillArc(n6 - 11, n7 - 6, 22, 9, 0, 360);
            }
        }
        int n8 = 0;
        switch (this.h) {
            case 2: {
                n8 = this.n * 12 + 4 + (this.j - 1);
                break;
            }
            case 3: {
                n8 = this.n * 12 + 8 + (this.j - 1);
                break;
            }
            default: {
                n8 = this.n * 12 + 0 + (this.j - 1);
            }
        }
        as.b(graphics, (byte[])ce.e[n8], this.k, n6, n7);
        this.c(graphics, n4, n5 - this.a.a * 3);
        this.b(graphics, n4, n5);
    }

    public final void a(byte by2) {
        this.h = by2;
    }

    public void d() {
        this.k = (byte)(this.k + 1);
        this.s();
        if (!this.f) {
            ao ao2 = n.a();
            byte by2 = this.a(ao2);
            byte by3 = this.b(ao2);
            if ((by2 > this.a.g || by3 > this.a.g) && this.c == null) {
                return;
            }
        }
        if (this.s > 0) {
            this.s = (byte)(this.s - 1);
        }
        this.n();
        this.e();
        this.o();
    }

    private final void s() {
        for (int i2 = ((o)this).b.size() - 1; i2 >= 0; --i2) {
            cf cf2 = (cf)((o)this).b.elementAt(i2);
            cf2.a();
            if (cf2.a == 3 && cf2.a() % 8 == 0) {
                this.b(h.a(15, 25));
            }
            if (!((f)cf2).a) continue;
            ((o)this).b.removeElementAt(i2);
            this.b[cf2.a] = false;
        }
    }

    public void n() {
        boolean bl2;
        boolean bl3 = bl2 = !((ck)this).a && !((ck)this).b;
        if (this.h == 5) {
            if (this.q < 1) {
                this.l();
                return;
            }
            this.q = (byte)(this.q - 1);
            return;
        }
        if (this.b[0] || this.b[2]) {
            this.a(false);
            return;
        }
        switch (this.h) {
            case 2: {
                if (!bl2) break;
                this.h();
                return;
            }
            case 1: {
                this.i();
                return;
            }
            case 3: {
                if (this.k < this.a.l) break;
                this.a(false);
                this.i();
                return;
            }
            case 4: {
                if (this.l < 1) {
                    this.a((byte)1);
                }
                this.l = (byte)(this.l - 1);
            }
        }
    }

    public void h() {
        if (this.r < this.a.h * 2 && ck.a.nextInt() > 0) {
            this.r = (byte)(this.r + this.a.h);
            this.o = 0;
            this.i();
            return;
        }
        this.a(false);
        this.i();
    }

    public void i() {
        byte by2;
        ao ao2 = n.a();
        p p2 = ao2.a();
        if (this.p == 0) {
            if ((this.a.d == 0 || this.a.d == 1) && this.a(this.i, ao2) == ao2) {
                this.q();
                return;
            }
            if (this.a.d == 2 || this.a.d == 3) {
                for (by2 = 1; by2 <= 3; by2 = (byte)(by2 + 1)) {
                    if (this.a(this.i, by2) != ao2) continue;
                    this.q();
                    return;
                }
            }
        }
        if (this.o == 0) {
            by2 = 1;
            if (this.a.d == 2 || this.a.d == 3) {
                by2 = 3;
            }
            if (this.c == p2 && p2.i == 2) {
                this.a(p2, by2);
                return;
            }
            if (this.c == ao2 && !p2.a()) {
                this.a((ck)ao2, by2);
                return;
            }
            ck ck2 = this.a(ao2, p2);
            if (ck2 != null) {
                this.a(ck2, by2);
                this.c = ck2;
                return;
            }
            this.r();
            return;
        }
    }

    public void o() {
        if (this.a.c && this.d) {
            if (this.u > 0) {
                this.u = (byte)(this.u - 1);
            }
            if (this.u == 0) {
                n.a.a(((ck)this).a, ((ck)this).b, this.m, this.n, true, (byte)1, (byte)5);
                this.u = (byte)-10;
            }
        }
        switch (this.h) {
            case 2: {
                if (this.k < this.a.k) break;
                this.k = 0;
                return;
            }
            case 3: {
                this.j();
                return;
            }
            case 5: {
                this.k();
                return;
            }
            default: {
                if (this.k >= this.a.j) {
                    this.k = 0;
                }
                if (this.p > 0) {
                    this.p = (byte)(this.p - 1);
                }
                if (this.o <= 0) break;
                this.o = (byte)(this.o - 1);
            }
        }
    }

    public void j() {
        block3: {
            ao ao2;
            block4: {
                ao2 = n.a();
                this.e = false;
                if (this.k != j.a[this.m] - 1) break block3;
                if ((this.a.d == 0 || this.a.d == 1) && this.a(this.i, ao2) == ao2) {
                    ao2.a(this, this.i);
                    return;
                }
                if (this.a.d != 2) break block4;
                for (byte by2 = 1; by2 <= 3; by2 = (byte)(by2 + 1)) {
                    if (this.a(this.i, by2) != ao2) continue;
                    ao2.a(new aw(9, -1, this.n));
                    ao2.a(this, this.i);
                    return;
                }
                break block3;
            }
            if (this.a.d != 3) break block3;
            for (byte by3 = 1; by3 <= 3; by3 = (byte)(by3 + 1)) {
                if (this.a(this.i, by3) != ao2) continue;
                n.a.b(new i((byte)(((ck)this).a + u.a[this.j]), (byte)(((ck)this).b + u.b[this.j]), (byte[])ce.f[this.n], this, this.j, 3, 2));
                return;
            }
        }
    }

    public void k() {
        if (this.k >= this.a.j) {
            this.k = 0;
        }
    }

    public final void a(byte by2, byte by3) {
        n.a.b(new y(by2, by3, (byte[])ce.f[this.n]));
    }

    public final void p() {
        n.a.b(new y(((ck)this).a, ((ck)this).b, (byte[])ce.g[this.a.a]));
    }

    public final void a(boolean bl2) {
        this.o = (byte)(this.a.h + this.r);
        this.r = 0;
        this.p = this.b[1] ? (byte)(this.a.i * 2 + 1) : (byte)(this.a.i + 1);
        if (bl2) {
            this.p = (byte)(this.p * h.a(1, 7) / 10);
        }
        this.a((byte)1);
        this.k = 0;
    }

    public final void q() {
        this.e = false;
        this.o = (byte)(this.a.h + this.r);
        this.r = 0;
        this.p = this.b[1] ? (byte)(this.a.i * 2 + 1) : (byte)(this.a.i + 1);
        this.a((byte)3);
        this.k = 0;
    }

    public final void r() {
        if (ck.a.nextInt() > 0) {
            this.a((byte)2);
        } else {
            this.a(true);
        }
        this.b((byte)((ck.a.nextInt() & 0xFF) % 4 + 1));
    }

    public void l() {
        int n2;
        int n3;
        this.a((byte)6);
        if (this.a.b != 2) {
            n.a.a((int)this.m, this.a.e, this.a, this.b);
        }
        int n4 = h.a(1, 150);
        int n5 = this.a.b.length / 3;
        byte by2 = -1;
        byte by3 = -1;
        for (n3 = 0; n3 < n5; ++n3) {
            if ((n4 -= this.a.b[n3 * 3 + 2]) > 0) continue;
            if (this.a.b[n3 * 3 + 2] == 1) {
                n2 = h.a(1, 100);
                if (n2 > 20) continue;
                by2 = this.a.b[n3 * 3];
                by3 = this.a.b[n3 * 3 + 1];
                break;
            }
            by2 = this.a.b[n3 * 3];
            by3 = this.a.b[n3 * 3 + 1];
            break;
        }
        if (by2 != -1) {
            n.a.a(((ck)this).a, ((ck)this).b, by2, by3);
        }
        if (h.a(1, 100) <= 60) {
            n.a.a(((ck)this).a, ((ck)this).b, (short)(this.a.f * 3));
        }
        if (h.a(1, 100) <= 20 + (this.a.f - n.a().g)) {
            n.a.a(((ck)this).a, ((ck)this).b, (byte)11, (byte)0);
        }
        if ((n3 = 20 - (n.a().g - this.a.f)) > 26) {
            n3 = 26;
        }
        if ((n2 = this.a.f * n3 / 2) > 0) {
            n.a().f(n2);
        }
        this.p();
    }

    public final byte a(ck ck2) {
        int n2 = ck2.a - ((ck)this).a;
        if (n2 > 0) {
            return (byte)n2;
        }
        return (byte)(-n2);
    }

    public final byte b(ck ck2) {
        int n2 = ck2.b - ((ck)this).b;
        if (n2 > 0) {
            return (byte)n2;
        }
        return (byte)(-n2);
    }

    public void a(int n2, byte by2) {
        if (this.h == 6 || this.h == 5) {
            return;
        }
        this.t();
        this.d = true;
        this.e = false;
        if (n2 < 0) {
            n2 = 0;
        }
        n2 = n2 * u.a[by2][this.a.c] / 10;
        this.a = (short)(this.a - n2);
        ((o)this).a.addElement(new aw(7, 4, (short)n2));
        ((o)this).a.addElement(new aw(1));
        this.s = (byte)4;
        this.t = (byte)4;
        if (this.a <= 0) {
            this.a((byte)5);
            this.k = 0;
            this.q = (byte)3;
        }
    }

    public void a(int n2, boolean bl2, byte by2, boolean bl3, byte by3, byte by4, ao ao2) {
        byte by5;
        int n3;
        if (this.h == 6 || this.h == 5) {
            return;
        }
        bs.a.a(this, false);
        l l2 = (l)ao2.a(0);
        byte by6 = ao2.a().a();
        if (!this.d && this.a.c && by6 != this.a.e) {
            this.u = (byte)40;
        }
        this.t();
        this.d = true;
        this.e = false;
        if (this.a.b) {
            n2 /= 2;
        }
        n2 = this.b[4] ? (n2 -= this.a.c / 2) : (n2 -= this.a.c);
        if (n2 < 0) {
            n2 = 0;
        }
        n2 = n2 * u.a[by6][this.a.c] / 10;
        if (bl3) {
            n2 += n2 * l2.b / 10;
        }
        n3 = (n3 = this.a.d - (ao2.f + ao2.o) - l2.e / 5 + 10) > 50 ? 50 : n3;
        boolean bl4 = h.a(0, 99) < n3;
        byte by7 = by5 = by4 == -1 ? (byte)-1 : t.h[by4];
        if (!bl4) {
            switch (by4) {
                case 2: {
                    n2 = this.a.a;
                    break;
                }
                case 3: {
                    ao2.d(n2 * 80 / 100);
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
            if (by5 != -1) {
                this.a(by5);
                this.b[by5] = true;
            }
            ((o)this).a.addElement(new aw(by3));
            if (bl2 && this.a > 0 && !((ck)this).a && !((ck)this).b) {
                this.a((byte)4);
                this.l = (byte)2;
                this.i = by2;
            }
            this.s = (byte)4;
            this.t = by2;
            this.b(n2);
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

    private final void t() {
        if (this.b[0]) {
            this.b[0] = false;
            for (int i2 = 0; i2 < ((o)this).b.size(); ++i2) {
                cf cf2 = (cf)((o)this).b.elementAt(i2);
                if (cf2.a != 0) continue;
                ((o)this).b.removeElementAt(i2);
                return;
            }
        }
    }

    public final void b(int n2) {
        this.a = (short)(this.a - n2);
        ((o)this).a.addElement(new aw(7, 4, (short)n2));
        if (this.a <= 0) {
            this.a((byte)5);
            this.k = (byte)-1;
            this.q = (byte)3;
        }
    }

    public final void c(int n2) {
        this.a = (short)(this.a + n2);
        if (this.a > this.a.a) {
            this.a = this.a.a;
        }
    }

    public final void c(byte by2) {
        this.e = false;
        if (this.h == 6 || this.h == 5) {
            return;
        }
        this.b(this.a.a);
    }

    private final ck a(ao ao2, p p2) {
        byte by2 = this.a(p2);
        byte by3 = this.b(p2);
        byte by4 = this.d ? (byte)(this.a.a ? 100 : 8) : this.a.g;
        boolean bl2 = this.a(ao2) <= by4 && this.b(ao2) <= by4;
        boolean bl3 = by2 <= by4 && by3 <= by4;
        if (bl3 && p2.i == 2 && h.a(0, 9) < 7) {
            return p2;
        }
        if (bl2) {
            return ao2;
        }
        return null;
    }
}

