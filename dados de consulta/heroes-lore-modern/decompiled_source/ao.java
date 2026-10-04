/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 */
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FilterInputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Vector;
import javax.microedition.lcdui.Graphics;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public final class ao
extends o
implements u {
    private static final byte[][] b = new byte[][]{{4, 4, 4, 4, 0}, {4, 0, 4, 4, 8}};
    private static final byte[][] c = new byte[][]{{3, 3, 3, 6, 0}, {4, 0, 7, 9, 14}};
    private static final byte[][] d = new byte[][]{{3, 3, 3, 3, 0}, {3, 0, 3, 3, 6}};
    private byte[] h;
    private byte q;
    private byte r = 0;
    private byte s;
    private byte t;
    private byte u;
    private byte v;
    private byte w;
    private byte x = 0;
    private byte y;
    private boolean i;
    private byte[][] e;
    public boolean d;
    public boolean e;
    public boolean f;
    public boolean g;
    public boolean h;
    private byte z;
    public short a;
    public byte f;
    public byte g;
    public short b;
    public short e;
    public short f;
    public short g;
    public byte m;
    public byte n;
    public byte o;
    public byte p;
    public int a;
    public int b;
    public int c;
    public short h;
    public short i;
    public int d;
    public int e;
    public int f;
    public g a;
    private e[] a;
    public p[] a;
    private p a = new p[5];
    public g b = new g(15);
    private int i;
    private boolean j;
    private byte A;
    public int g;
    public int h;

    public ao(short s2, short s3, byte by2, byte by3, byte by4) {
        super(s2, s3, by2, by3);
        this.a();
        this.h();
        switch (by4) {
            case 6: {
                this.e = b;
                break;
            }
            case 7: {
                this.e = c;
                break;
            }
            case 8: {
                this.e = d;
            }
        }
        this.g = (int)(System.currentTimeMillis() / 1000L);
    }

    public final void c(byte by2) {
        x.a(this.a[0] != null);
        x.a(this.a[1] != null);
        this.a(this.a[0]);
        switch (by2) {
            case 6: {
                this.b = (short)8;
                this.e = (short)5;
                this.f = (short)3;
                this.g = (short)4;
                this.a[0] = (e)ad.a((byte)0, (byte)0, true, false);
                this.a[0].b = true;
                this.a[0].h = 1;
                break;
            }
            case 7: {
                this.b = (short)3;
                this.e = (short)4;
                this.f = (short)8;
                this.g = (short)5;
                this.a[0] = (e)ad.a((byte)2, (byte)0, true, false);
                this.a[0].b = true;
                this.a[0].h = 1;
                break;
            }
            case 8: {
                this.b = (short)5;
                this.e = (short)8;
                this.f = (short)4;
                this.g = (short)3;
                this.a[0] = (e)ad.a((byte)1, (byte)0, true, false);
                this.a[0].b = true;
                this.a[0].h = 1;
                this.a[1] = (e)ad.a((byte)3, (byte)0, true, false);
                this.a[1].b = true;
                this.a[1].h = 1;
            }
        }
        this.a[2] = (e)ad.a((byte)5, (byte)0, true, false);
        this.a[2].b = true;
        this.a[2].h = 1;
        this.a[3] = (e)ad.a((byte)6, (byte)0, true, false);
        this.a[3].b = true;
        this.a[3].h = 1;
        this.a[4] = (e)ad.a((byte)4, (byte)0, true, false);
        this.a[4].b = true;
        this.a[4].h = 1;
        this.g = 1;
        this.z = 1;
        this.a.a = 300;
        this.a = 0;
        this.n();
        this.a = this.d;
        this.b = this.e;
        this.c = 0;
    }

    public final void a() {
        super.a();
        this.h = new byte[5];
        this.q = (byte)-1;
        this.v = (byte)(67 + this.g < 100 ? (int)(67 + this.g) : 100);
        this.w = (byte)21;
        this.y = 0;
        this.f = false;
        if (this.a != null) {
            this.a.c();
        }
        this.i = false;
    }

    public final void h() {
        ((o)this).b = new Vector(3);
        this.d = false;
        this.e = false;
        this.f = false;
        this.g = false;
        this.h = false;
    }

    public final void a(short s2, short s3) {
        super.a(s2, s3);
        this.b();
    }

    public final void i() {
        if (this.a() == null) {
            if (this.a(u.f[((o)this).i]) != null) {
                this.b(u.f[((o)this).i]);
                return;
            }
            if (this.a(u.e[((o)this).i]) != null) {
                this.b(u.e[((o)this).i]);
                return;
            }
            if (this.a(u.g[((o)this).i]) != null) {
                this.b(u.g[((o)this).i]);
            }
        }
    }

    public final void a(byte by2, byte by3) {
        this.f();
        ((ck)this).c = (short)(((ck)this).c + u.a[by2] * by3);
        ((ck)this).d = (short)(((ck)this).d + u.b[by2] * by3);
        this.b();
        this.g();
    }

    public final void d() {
        int n2;
        this.k = (byte)(this.k + 1);
        if (this.t > 0) {
            this.t = (byte)(this.t - 1);
        }
        if (n.e == 2) {
            if (((o)this).h == 1) {
                this.v = (byte)(this.v - 2);
            } else if (((o)this).h == 2) {
                this.v = (byte)(this.v - 1);
            }
            if (this.v <= 0) {
                this.b((this.e + this.n) * (this.h ? 2 : 1));
                this.v = (byte)(67 + this.g < 100 ? (int)(67 + this.g) : 100);
            }
            if (((o)this).h == 1) {
                this.w = (byte)(this.w - 3);
            } else if (((o)this).h == 2) {
                this.w = (byte)(this.w - 1);
            }
            if (this.w <= 0) {
                this.d((this.g + this.p) * (this.h ? 2 : 1));
                this.w = (byte)21;
            }
        }
        if (((o)this).h != 6 && ((o)this).h != 5) {
            block11: for (int i2 = ((o)this).b.size() - 1; i2 >= 0; --i2) {
                cf cf2 = (cf)((o)this).b.elementAt(i2);
                cf2.a();
                if (cf2.a == 7 && cf2.b % 10 == 0 && this.a > 1) {
                    n2 = this.d / 25;
                    if (n2 > this.a - 1) {
                        n2 = this.a - 1;
                    }
                    this.b(-n2);
                    this.a(new aw(7, 4, (short)(-(this.d / 12))));
                }
                if (!((f)cf2).a) continue;
                ((o)this).b.removeElementAt(i2);
                switch (cf2.a) {
                    case 5: {
                        this.d = false;
                        continue block11;
                    }
                    case 6: {
                        this.e = false;
                    }
                }
            }
        }
        switch (((o)this).h) {
            case 1: {
                this.x = 0;
                if (this.k != 1) break;
                this.k = 0;
                break;
            }
            case 2: {
                if (this.x != 0 && !((ck)this).a && !((ck)this).b) {
                    this.b(this.x);
                    this.x = 0;
                }
                if (this.k != 4) break;
                this.k = 0;
                break;
            }
            case 3: {
                if (this.q == -1) {
                    this.q = 0;
                }
                this.o();
                break;
            }
            case 6: {
                this.k = 0;
                if (this.u > 0) {
                    this.u = (byte)(this.u - 1);
                    break;
                }
                this.q();
                return;
            }
            case 0: {
                return;
            }
        }
        byte by2 = ((o)this).h;
        ae ae2 = n.a;
        if ((((o)this).h == 2 || ((o)this).h == 4) && this.a()) {
            al al2 = this.a();
            if (al2 != null) {
                bs.a.a(al2, false);
            }
            n2 = ((o)this).i;
            byte by3 = u.e[n2];
            byte by4 = u.f[n2];
            byte by5 = u.c[n2];
            byte by6 = u.d[n2];
            if (ae2.c[((ck)this).b + u.b[n2]][((ck)this).a + u.a[n2]] == -128 && ae2.a(((ck)this).a + u.a[by4], ((ck)this).b + u.b[by4]) && ae2.a(((ck)this).a + u.a[by6], ((ck)this).b + u.b[by6])) {
                this.x = ((o)this).i;
                this.a((byte)2);
                this.b(by4);
            } else if (ae2.c[((ck)this).b + u.b[n2]][((ck)this).a + u.a[n2]] == -128 && ae2.a(((ck)this).a + u.a[by3], ((ck)this).b + u.b[by3]) && ae2.a(((ck)this).a + u.a[by5], ((ck)this).b + u.b[by5])) {
                this.x = ((o)this).i;
                this.a((byte)2);
                this.b(by3);
            }
            this.k = 0;
        }
        if (((o)this).h == 2 || ((o)this).h == 4) {
            super.a(8);
            this.i = false;
        }
        if (n.e != 4) {
            n2 = 0;
            if (((o)this).h != 3 && !this.i) {
                n2 = ah.a(this) ? 1 : 0;
                this.i = true;
            }
            if (by2 == 2 && ((o)this).h == 1 && n2 == 0) {
                n2 = ah.b() ? 1 : 0;
            }
            if (n2 != 0) {
                this.a((byte)1);
                this.x = 0;
                this.k = 0;
            }
        }
    }

    private final void o() {
        int n2;
        if (this.k == this.e[this.h[this.q] - 1][this.q]) {
            if (this.q + 1 == this.z || this.h[this.q + 1] == 0) {
                this.h(this.q);
                return;
            }
            this.q = (byte)(this.q + 1);
            this.k = 0;
        }
        this.y = 0;
        if (this.k == 0) {
            n2 = ((e)((l)this.a((int)0))).a / 4 + 4;
            if (this.h[this.q] == 2) {
                n2 = n2 * 7 / 5;
            }
            if (this.b < n2 && (this.q != 0 || this.h[this.q] != 1)) {
                this.h(this.q - 1);
                return;
            }
            this.d(-n2);
        }
        n2 = this.h[this.q];
        switch (n.a) {
            case 6: {
                if (n2 == 2 && this.q == 4) {
                    if (this.k != 1 && this.k != 6) break;
                    this.p();
                    return;
                }
                if (this.k != 2) break;
                this.p();
                return;
            }
            case 7: {
                if (n2 == 2 && this.q == 4) {
                    if (this.k != 0 && this.k != 2 && this.k != 4 && this.k != 6 && this.k != 8 && this.k != 10) break;
                    this.p();
                    return;
                }
                if (n2 == 2 && this.q == 3) {
                    if (this.k != 4) break;
                    this.p();
                    return;
                }
                if (this.k != 1) break;
                this.p();
                return;
            }
            case 8: {
                if (n2 == 2 && this.q == 4) {
                    if (this.k != 4) break;
                    this.p();
                    return;
                }
                if (this.k != 1) break;
                this.p();
            }
        }
    }

    private final void p() {
        int n2;
        this.i = this.a();
        this.A = this.a();
        this.j = this.e();
        if (this.a() != null) {
            switch (this.A) {
                case 8: {
                    n2 = this.d / 25;
                    this.b(-n2);
                    ((o)this).a.addElement(new aw(7, 4, (short)(-n2)));
                    this.a(new aw(10, 8, 0));
                    break;
                }
                case 2: {
                    this.a(new aw(10, 8, 8));
                    break;
                }
                case 3: {
                    this.a(new aw(10, 8, 10));
                    break;
                }
                case 4: {
                    this.a(new aw(10, 8, 11));
                }
            }
        }
        n2 = 0;
        switch (n.a) {
            case 6: {
                n2 = this.b();
                break;
            }
            case 7: {
                n2 = this.c();
                break;
            }
            case 8: {
                n2 = this.d();
            }
        }
        if (n2 == 0) {
            bw.a((byte)14, false);
        }
    }

    private final boolean b() {
        byte by2 = this.h[this.q];
        byte by3 = 1;
        if (by2 == 1 && this.q == 3 || by2 == 2 && this.q == 4) {
            by3 = 5;
        }
        ae ae2 = n.a;
        al al2 = null;
        al al3 = null;
        boolean bl2 = false;
        if (by2 == 1 && (this.q == 0 || this.q == 3) || by2 == 2 && this.q == 4 && this.k == 6) {
            al2 = this.a();
            if (al2 != null) {
                al2.a(this.i, false, ((o)this).i, this.j, by3, this.A, this);
                bl2 = true;
            }
        } else if (by2 == 1 && (this.q == 1 || this.q == 2) || by2 == 2 && this.q == 0) {
            al2 = this.a(((o)this).i);
            if (al2 != null) {
                al2.a(this.i, false, ((o)this).i, this.j, by3, this.A, this);
                bl2 = true;
            }
            al3 = al2;
            al2 = this.a(u.d[((o)this).i]);
            if (al2 != null && al2 != al3) {
                al2.a(this.i, false, ((o)this).i, this.j, by3, this.A, this);
                bl2 = true;
            }
            al3 = al2;
            al2 = this.a(u.c[((o)this).i]);
            if (al2 != null && al2 != al3) {
                al2.a(this.i, false, ((o)this).i, this.j, by3, this.A, this);
                bl2 = true;
            }
        } else if (by2 == 2 && this.q == 4) {
            for (byte by4 = 1; by4 <= 8; by4 = (byte)(by4 + 1)) {
                al2 = this.a(by4);
                if (al2 == null) continue;
                al2.a(this.i, true, by4, this.j, by3, this.A, this);
                bl2 = true;
            }
        } else if (by2 == 2 && (this.q == 2 || this.q == 3)) {
            byte by5;
            byte by6;
            al2 = this.a();
            if (al2 != null) {
                al2.a(this.i, false, ((o)this).i, this.j, by3, this.A, this);
                bl2 = true;
            }
            if (ae2.c[((ck)this).b + (by6 = u.b[((o)this).i])][((ck)this).a + (by5 = u.a[((o)this).i])] == 0 && ae2.a(((ck)this).a + by5 * 2, ((ck)this).b + by6 * 2)) {
                super.a(32);
                this.i = false;
                this.y = (byte)2;
            } else if (ae2.a(((ck)this).a + by5, ((ck)this).b + by6)) {
                super.a(16);
                this.i = false;
                this.y = 1;
            }
            if (this.q == 3 && this.y != 0 && (al2 = this.a()) != null) {
                al2.a(this.i, false, ((o)this).i, this.j, by3, this.A, this);
                bl2 = true;
            }
        }
        return bl2;
    }

    private final boolean c() {
        byte by2 = this.h[this.q];
        boolean bl2 = false;
        al al2 = null;
        al al3 = null;
        boolean bl3 = false;
        if (by2 == 1 && this.q == 3) {
            al2 = this.a(((o)this).i);
            if (al2 != null) {
                al2.a(this.i, false, ((o)this).i, this.j, (byte)1, this.A, this);
                bl3 = true;
            }
            al3 = al2;
            al2 = this.a(u.d[((o)this).i]);
            if (al2 != null && al2 != al3) {
                al2.a(this.i, false, ((o)this).i, this.j, (byte)1, this.A, this);
                bl3 = true;
            }
            al3 = al2;
            al2 = this.a(u.c[((o)this).i]);
            if (al2 != null && al2 != al3) {
                al2.a(this.i, false, ((o)this).i, this.j, (byte)1, this.A, this);
                bl3 = true;
            }
        } else {
            ck ck2 = this.a(((o)this).i, (byte)1);
            if (ck2 != null && ck2 instanceof al) {
                ((al)ck2).a(this.i, false, ((o)this).i, this.j, (byte)1, this.A, this);
                bl3 = true;
            }
            if ((ck2 = this.a(((o)this).i, (byte)2)) != null && ck2 instanceof al) {
                ((al)ck2).a(this.i, false, ((o)this).i, this.j, (byte)1, this.A, this);
                bl3 = true;
            }
        }
        return bl3;
    }

    private final boolean d() {
        byte by2 = this.h[this.q];
        boolean bl2 = false;
        al al2 = null;
        boolean bl3 = false;
        if (by2 == 2 && this.q == 2) {
            n.a.b(new i((byte)(((ck)this).a + u.a[((o)this).i]), (byte)(((ck)this).b + u.b[((o)this).i]), (byte[])ce.b[0], this, true, ((o)this).i, 3, 2, this.i, this.A, this.j));
        } else if (by2 == 2 && this.q == 3) {
            n.a.b(new i((byte)(((ck)this).a + u.a[((o)this).i]), (byte)(((ck)this).b + u.b[((o)this).i]), (byte[])ce.b[1], this, true, ((o)this).i, 3, 2, this.i, this.A, this.j));
        } else if (by2 == 2 && this.q == 4) {
            n.a.b(new i((byte)(((ck)this).a + u.a[((o)this).i]), (byte)(((ck)this).b + u.b[((o)this).i]), (byte[])ce.b[2], this, true, ((o)this).i, 3, 2, this.i, this.A, this.j));
        } else {
            al2 = this.a();
            if (al2 != null) {
                al2.a(this.i, false, ((o)this).i, this.j, (byte)1, this.A, this);
                bl3 = true;
            }
        }
        return bl3;
    }

    private final int a() {
        int n2 = this.h;
        if (this.d) {
            n2 = this.h * 3 / 2;
        }
        if (this.h[this.q] == 2) {
            n2 = n2 * 17 / 10;
        } else {
            switch (this.q) {
                case 0: {
                    n2 = n2 * 10 / 10;
                    break;
                }
                case 1: {
                    n2 = n2 * 12 / 10;
                    break;
                }
                case 2: {
                    n2 = n2 * 13 / 10;
                    break;
                }
                case 3: {
                    n2 = n2 * 14 / 10;
                    break;
                }
                case 4: {
                    n2 = n2 * 17 / 10;
                }
            }
        }
        return n2 += n2 >= 10 ? ck.a.nextInt() % (n2 / 10) : 0;
    }

    private final boolean e() {
        return Math.abs(ck.a.nextInt() % 100) < (this.f + this.o) / 3 + (this.g + this.p) / 10 + ((l)this.a[0]).a;
    }

    private final byte a() {
        l l2 = (l)this.a[0];
        t t2 = (t)this.a[1];
        byte by2 = -1;
        if (l2.c != -1 && h.a(0, 99) < t.i[l2.c]) {
            by2 = l2.c;
        }
        if (by2 == -1 && t2 != null && t2.c != -1 && h.a(0, 99) < t.i[t2.c]) {
            by2 = t2.c;
        }
        return by2;
    }

    private final void h(int n2) {
        this.t = n2 == -1 || n2 == 0 && this.h[n2] == 1 ? (byte)1 : (n2 == 0 && this.h[n2] == 2 ? (byte)3 : (this.h[n2] == 1 ? (byte)1 : (byte)3));
        this.j();
        this.a((byte)1);
        this.k = 0;
    }

    private final void q() {
        n.a((byte)16);
        bw.f();
    }

    public final boolean a(boolean bl2) {
        if (!n.a.c) {
            return false;
        }
        if (this.q + 1 >= this.z) {
            return false;
        }
        if (this.t > 0) {
            return false;
        }
        if (this.h[this.q + 1] == 0) {
            if (this.q >= 0 && this.h[this.q] == 2) {
                return false;
            }
            if (this.q == 0 && bl2) {
                return false;
            }
            if (this.q == 3 && !bl2) {
                return false;
            }
            this.h[this.q + 1] = bl2 ? 2 : 1;
        }
        return true;
    }

    public final void j() {
        this.q = (byte)-1;
        for (int i2 = 0; i2 < this.h.length; ++i2) {
            this.h[i2] = 0;
        }
    }

    public final void a(Graphics graphics, int n2, int n3) {
        int n4 = n2 + ((ck)this).c + ((ck)this).c;
        int n5 = n3 + ((ck)this).d + ((ck)this).d;
        if (this.r == 1) {
            n4 += u.a[this.s] * 2;
            n5 += u.b[this.s] * 2;
            this.r = (byte)(this.r - 1);
        }
        graphics.drawImage(ce.v, n4, n5 - 3, 17);
        switch (((o)this).h) {
            case 1: 
            case 4: {
                this.a((byte)0, ((o)this).i, graphics, n4, n5);
                break;
            }
            case 2: {
                this.a((byte)1, ((o)this).i, graphics, n4, n5);
                break;
            }
            case 6: {
                this.a((byte)2, (byte)1, graphics, n4, n5);
                break;
            }
            case 3: {
                this.e(graphics, n4, n5);
                if (this.y == 0) break;
                this.e(graphics, n4 + u.a[u.g[((o)this).i]] * 16 * this.y, n5 + u.b[u.g[((o)this).i]] * 16 * this.y);
            }
        }
        this.c(graphics, n4, n5 - 8);
        this.b(graphics, n4, n5);
    }

    public final void d(Graphics graphics, int n2, int n3) {
        this.a((byte)2, (byte)1, graphics, n2, n3);
    }

    private void a(byte by2, byte by3, Graphics graphics, int n2, int n3) {
        int n4 = by2 * 36 + (by3 - 1) * 9;
        for (int i2 = 0; i2 < 9; ++i2) {
            if ((i2 == 6 || i2 == 7) && !n.a.c) continue;
            if (i2 == 7) {
                as.b(graphics, (byte[])ce.a[n4 + i2], this.k, n2, n3);
                continue;
            }
            as.a(graphics, (byte[])ce.a[n4 + i2], this.k, n2, n3);
        }
    }

    private void e(Graphics graphics, int n2, int n3) {
        int n4 = -1;
        switch (this.q) {
            case 0: {
                if (this.h[this.q] == 1) {
                    n4 = 3;
                    break;
                }
                n4 = 7;
                break;
            }
            case 1: {
                n4 = 4;
                break;
            }
            case 2: {
                if (this.h[this.q] == 1) {
                    n4 = 5;
                    break;
                }
                n4 = 8;
                break;
            }
            case 3: {
                if (this.h[this.q] == 1) {
                    n4 = 6;
                    break;
                }
                n4 = 9;
                break;
            }
            case 4: {
                n4 = 10;
            }
        }
        this.a((byte)n4, ((o)this).i, graphics, n2, n3);
    }

    public final void a(al al2, byte by2) {
        this.a(al2, al2.a.b, by2);
    }

    public final void a(al al2, short s2, byte by2) {
        if (((o)this).h == 6 || ((o)this).h == 5) {
            return;
        }
        if (this.f) {
            return;
        }
        if (this.g) {
            al2.b(this.a.a * 2 + 40 + this.g);
        }
        bs.a.a(al2, true);
        int n2 = this.f + this.o - al2.a.d + 10;
        if (this.a[2] != null) {
            n2 += this.a[2].e;
        }
        if (n2 > 60) {
            n2 = 60;
        }
        if (n2 < 8) {
            n2 = 8;
        }
        if (h.a(0, 99) < n2) {
            ((o)this).a.addElement(new aw(2));
            return;
        }
        int n3 = s2 + h.a(-(s2 / 10), s2 / 10) - (this.e ? this.i * 2 : this.i);
        if (n3 > 0) {
            this.b(-n3);
            this.a(new aw(6));
        }
        if (n3 < 0) {
            n3 = 0;
        }
        ((o)this).a.addElement(new aw(7, 4, (short)(-n3)));
        if (al2.a.d == 1 && h.a(0, 99) < 15) {
            this.a((byte)7);
        }
        this.r = 1;
        this.s = by2;
    }

    public final void b(int n2) {
        this.a += n2;
        if (this.a > this.d) {
            this.a = this.d;
        }
        if (this.a < 0) {
            this.a = 0;
        }
        bs.a.c();
        if (this.a == 0) {
            this.a((byte)6);
            this.k = 0;
            this.u = (byte)24;
            return;
        }
    }

    public final void c(int n2) {
        int n3 = this.d * n2 / 100;
        this.b(n3);
    }

    public final void d(int n2) {
        this.b += n2;
        if (this.b > this.e) {
            this.b = this.e;
        }
        if (this.b < 0) {
            this.b = 0;
        }
        bs.a.d();
    }

    public final void e(int n2) {
        int n3 = this.e * n2 / 100;
        this.d(n3);
    }

    public final void f(int n2) {
        this.c += (n2 *= 4);
        while (this.c >= this.f) {
            this.c -= this.f;
            this.r();
        }
        if (this.c < 0) {
            this.c = 0;
        }
        bs.a.e();
        this.a.a(n2);
    }

    public final void g(int n2) {
        this.a.a += n2;
        if (this.a.a < 0) {
            this.a.a = 0;
        }
    }

    private final void r() {
        if (this.g < 99) {
            this.g = (byte)(this.g + 1);
            this.n();
            ((o)this).a.addElement(new aw(3));
            ((o)this).a.addElement(new aw(4));
            this.a = (short)(this.a + 3);
        }
        this.c(100);
        this.e(100);
        if (w.c && this.g >= 8) {
            n.a((byte)13);
        }
    }

    public final void d(byte by2) {
        if (this.z < by2) {
            this.z = by2;
        }
    }

    public final void k() {
        if (((o)this).h == 6 || ((o)this).h == 5) {
            return;
        }
        ad ad2 = this.a.a();
        if (ad2 != null) {
            this.a(ad2);
        }
    }

    public final byte[] a(boolean bl2, byte by2) {
        byte[] byArray = this.a.a(bl2, by2);
        int n2 = 0;
        for (int i2 = 0; i2 < 5; ++i2) {
            if (this.a[i2] == null || bl2 && !(this.a[i2] instanceof t) || by2 == 1 && !this.a[i2].b || by2 == -1 && this.a[i2].b) continue;
            ++n2;
        }
        byte[] byArray2 = new byte[byArray.length + n2];
        int n3 = 0;
        for (int i3 = 0; i3 < 5; ++i3) {
            if (this.a[i3] == null || bl2 && !(this.a[i3] instanceof t) || by2 == 1 && !this.a[i3].b || by2 == -1 && this.a[i3].b) continue;
            byArray2[n3++] = (byte)(i3 + 100);
        }
        System.arraycopy(byArray, 0, byArray2, n3, byArray.length);
        return byArray2;
    }

    public final byte a(ad ad2) {
        byte by2 = this.a.a(ad2);
        if (by2 == -1) {
            for (int i2 = 0; i2 < 5; ++i2) {
                if (ad2 != this.a[i2]) continue;
                return (byte)(i2 + 100);
            }
        } else {
            return by2;
        }
        return -1;
    }

    public final void b(byte by2, byte by3) {
        this.a[by3] = (e)this.a.a((ad)this.a[by3], by2);
        this.n();
    }

    public final ad a(int n2) {
        return this.a[n2];
    }

    public final l a() {
        return (l)this.a[0];
    }

    public final t a() {
        return (t)this.a[1];
    }

    public final e a() {
        return this.a[2];
    }

    public final e b() {
        return this.a[3];
    }

    public final e c() {
        return this.a[4];
    }

    public final void a(ad ad2) {
        byte by2 = ad2.f;
        if (by2 == 7) {
            this.c(20);
        } else if (by2 == 8) {
            this.c(40);
        } else if (by2 == 10) {
            for (int i2 = 0; i2 < ((o)this).b.size(); ++i2) {
                cf cf2 = (cf)((o)this).b.elementAt(i2);
                if (cf2.a != 7) continue;
                cf2.b();
                break;
            }
        } else if (by2 == 9) {
            this.e(30);
        } else {
            x.a(false);
        }
        this.a.a(ad2, (byte)1);
        bs.a.b();
    }

    public final void e(byte by2) {
        byte by3 = 0;
        if (this.a != null) {
            by3 = this.a.a();
        }
        switch (by2) {
            case 0: {
                bu.a(n.a, (l)this.a[0], false, by3);
                return;
            }
            case 2: {
                bu.a(n.a, this.a[2].g);
                return;
            }
            case 3: {
                bu.b(n.a, this.a[3].g);
                return;
            }
            case 1: {
                bu.c(n.a, this.a[1].g);
            }
        }
    }

    public final p a(byte by2) {
        int n2;
        for (n2 = 0; n2 < this.a.length; ++n2) {
            if (this.a[n2] == null || this.a[n2].f != by2) continue;
            return null;
        }
        for (n2 = 0; n2 < this.a.length; ++n2) {
            if (this.a[n2] != null) continue;
            this.a[n2] = new p(0, 0, by2);
            return this.a[n2];
        }
        return null;
    }

    public final p a() {
        x.a(this.a != null);
        return this.a;
    }

    public final boolean a(p p2) {
        if (this.a != null && this.a.i != 0) {
            return false;
        }
        this.b();
        this.a = p2;
        return true;
    }

    public final void l() {
        n.a(1);
        this.m();
        bs.a.g();
        bu.c();
    }

    public final void a(boolean bl2) {
        if (!n.a.c) {
            return;
        }
        if (this.a == null || this.a.i != 0) {
            return;
        }
        this.a.a(bl2, ((o)this).i, (int)((ck)this).a, (int)((ck)this).b);
    }

    public final p b() {
        p p2 = this.a;
        if (p2 == null) {
            return null;
        }
        if (n.a != null) {
            n.a.a(p2);
        }
        this.a = null;
        return p2;
    }

    public final void m() {
        n.a.b(this.a);
    }

    public final void n() {
        e[] eArray = this.a;
        this.m = 0;
        this.n = 0;
        this.o = 0;
        this.p = 0;
        for (int i2 = 0; i2 < 5; ++i2) {
            if (eArray[i2] == null) continue;
            this.m = (byte)(this.m + eArray[i2].j[0]);
            this.n = (byte)(this.n + eArray[i2].j[1]);
            this.o = (byte)(this.o + eArray[i2].j[2]);
            this.p = (byte)(this.p + eArray[i2].j[3]);
        }
        this.d = 0;
        this.e = 0;
        this.f = 0;
        this.h = 0;
        this.i = 0;
        this.d = (this.e + this.n + this.g) * 12;
        this.e = (this.g + this.p + this.g) * 12;
        this.f = this.g * this.g * this.g - this.g * this.g + 80 * this.g;
        this.h = (short)(this.h + (eArray[0] != null ? eArray[0].a + eArray[0].e * 5 / 2 : 0));
        this.h = (short)(this.h + (this.b + this.m) * 4 / 5);
        this.i = (short)(this.i + (eArray[1] != null ? eArray[1].a + eArray[1].e : 0));
        this.i = (short)(this.i + (eArray[2] != null ? eArray[2].a + eArray[2].e * 2 : 0));
        this.i = (short)(this.i + (eArray[3] != null ? eArray[3].a : (short)0));
        this.i = (short)(this.i + (eArray[4] != null ? eArray[4].a : (short)0));
        this.i = (short)(this.i + (this.b + this.m) / 5);
        this.i = (short)(this.i + this.g / 3);
        if (this.a > this.d) {
            this.a = this.d;
        }
        if (this.b > this.e) {
            this.b = this.e;
        }
        bs.a.b();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Loose catch block
     */
    public final byte[] a() {
        int n2;
        ByteArrayOutputStream byteArrayOutputStream = null;
        FilterOutputStream filterOutputStream = null;
        byteArrayOutputStream = new ByteArrayOutputStream();
        filterOutputStream = new DataOutputStream(byteArrayOutputStream);
        ((DataOutputStream)filterOutputStream).writeByte(this.f);
        ((DataOutputStream)filterOutputStream).writeByte(this.g);
        ((DataOutputStream)filterOutputStream).writeInt(this.a);
        ((DataOutputStream)filterOutputStream).writeInt(this.b);
        ((DataOutputStream)filterOutputStream).writeInt(this.c);
        ((DataOutputStream)filterOutputStream).writeInt(this.d);
        ((DataOutputStream)filterOutputStream).writeInt(this.e);
        ((DataOutputStream)filterOutputStream).writeInt(this.f);
        ((DataOutputStream)filterOutputStream).writeByte(this.z);
        ((DataOutputStream)filterOutputStream).writeShort(this.a);
        ((DataOutputStream)filterOutputStream).writeShort(this.b);
        ((DataOutputStream)filterOutputStream).writeShort(this.e);
        ((DataOutputStream)filterOutputStream).writeShort(this.f);
        ((DataOutputStream)filterOutputStream).writeShort(this.g);
        for (n2 = 0; n2 < 5; ++n2) {
            if (this.a[n2] == null) {
                ((DataOutputStream)filterOutputStream).writeByte(0);
                continue;
            }
            ((DataOutputStream)filterOutputStream).writeByte(1);
            ((OutputStream)filterOutputStream).write(this.a[n2].a());
        }
        for (n2 = 0; n2 < this.a.length; ++n2) {
            if (this.a[n2] == null) {
                ((DataOutputStream)filterOutputStream).writeByte(0);
                continue;
            }
            ((DataOutputStream)filterOutputStream).writeByte(1);
            ((DataOutputStream)filterOutputStream).writeByte(this.a[n2].f);
            ((DataOutputStream)filterOutputStream).writeShort(this.a[n2].a);
            ((DataOutputStream)filterOutputStream).writeInt(1);
            ((DataOutputStream)filterOutputStream).writeInt(1);
            ((DataOutputStream)filterOutputStream).writeInt(this.a[n2].a);
            ((DataOutputStream)filterOutputStream).writeByte(this.a[n2].g);
            ((DataOutputStream)filterOutputStream).writeByte(this.a[n2].h);
        }
        x.a(this.a != null);
        n2 = -1;
        for (int n3 = 0; n3 < this.a.length; n3 = (int)((byte)(n3 + 1))) {
            if (this.a != this.a[n3]) continue;
            n2 = n3;
            break;
        }
        x.a(n2 != -1);
        ((DataOutputStream)filterOutputStream).writeByte(n2);
        ((DataOutputStream)filterOutputStream).writeInt(this.h + (int)(System.currentTimeMillis() / 1000L - (long)this.g));
        byte[] byArray = byteArrayOutputStream.toByteArray();
        try {
            if (filterOutputStream != null) {
                filterOutputStream.close();
            }
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.close();
            }
        }
        catch (IOException iOException) {}
        return byArray;
        catch (IOException iOException) {
            try {
                IOException iOException2 = iOException;
                iOException.printStackTrace();
            }
            catch (Throwable throwable) {
                try {
                    if (filterOutputStream != null) {
                        filterOutputStream.close();
                    }
                    if (byteArrayOutputStream != null) {
                        byteArrayOutputStream.close();
                    }
                }
                catch (IOException iOException3) {}
                throw throwable;
            }
            try {
                if (filterOutputStream != null) {
                    filterOutputStream.close();
                }
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
            }
            catch (IOException iOException4) {}
        }
        return null;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Loose catch block
     */
    public final void a(byte[] byArray) {
        block18: {
            int n2;
            ByteArrayInputStream byteArrayInputStream = null;
            FilterInputStream filterInputStream = null;
            byteArrayInputStream = new ByteArrayInputStream(byArray);
            filterInputStream = new DataInputStream(byteArrayInputStream);
            this.f = ((DataInputStream)filterInputStream).readByte();
            this.g = ((DataInputStream)filterInputStream).readByte();
            this.a = ((DataInputStream)filterInputStream).readInt();
            this.b = ((DataInputStream)filterInputStream).readInt();
            this.c = ((DataInputStream)filterInputStream).readInt();
            ((DataInputStream)filterInputStream).readInt();
            ((DataInputStream)filterInputStream).readInt();
            ((DataInputStream)filterInputStream).readInt();
            this.z = ((DataInputStream)filterInputStream).readByte();
            this.a = ((DataInputStream)filterInputStream).readShort();
            this.b = ((DataInputStream)filterInputStream).readShort();
            this.e = ((DataInputStream)filterInputStream).readShort();
            this.f = ((DataInputStream)filterInputStream).readShort();
            this.g = ((DataInputStream)filterInputStream).readShort();
            for (n2 = 0; n2 < 5; ++n2) {
                byte by2 = ((DataInputStream)filterInputStream).readByte();
                if (by2 == 0) continue;
                byte[] byArray2 = new byte[10];
                ((DataInputStream)filterInputStream).read(byArray2);
                this.a[n2] = (e)ad.a(byArray2);
            }
            x.a(this.a[0] == null);
            x.a(this.a == null);
            for (n2 = 0; n2 < this.a.length; ++n2) {
                if (((DataInputStream)filterInputStream).readByte() == 0) continue;
                p p2 = this.a(((DataInputStream)filterInputStream).readByte());
                this.a(((DataInputStream)filterInputStream).readByte()).a = ((DataInputStream)filterInputStream).readShort();
                ((DataInputStream)filterInputStream).readInt();
                ((DataInputStream)filterInputStream).readInt();
                p2.a = ((DataInputStream)filterInputStream).readInt();
                p2.a(true, ((DataInputStream)filterInputStream).readByte(), true);
                p2.a(false, ((DataInputStream)filterInputStream).readByte(), true);
                p2.a();
            }
            this.a(this.a[((DataInputStream)filterInputStream).readByte()]);
            this.h = ((DataInputStream)filterInputStream).readInt();
            try {
                if (filterInputStream != null) {
                    filterInputStream.close();
                }
                if (byteArrayInputStream != null) {
                    byteArrayInputStream.close();
                }
                break block18;
            }
            catch (IOException iOException) {}
            break block18;
            catch (IOException iOException) {
                try {
                    IOException iOException2 = iOException;
                    iOException.printStackTrace();
                }
                catch (Throwable throwable) {
                    try {
                        if (filterInputStream != null) {
                            filterInputStream.close();
                        }
                        if (byteArrayInputStream != null) {
                            byteArrayInputStream.close();
                        }
                    }
                    catch (IOException iOException3) {}
                    throw throwable;
                }
                try {
                    if (filterInputStream != null) {
                        filterInputStream.close();
                    }
                    if (byteArrayInputStream != null) {
                        byteArrayInputStream.close();
                    }
                }
                catch (IOException iOException4) {}
            }
        }
        this.n();
    }
}

