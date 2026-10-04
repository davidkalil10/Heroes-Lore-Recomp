/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 */
import java.util.Vector;
import javax.microedition.lcdui.Graphics;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public abstract class o
extends ck
implements u {
    public Vector a;
    public Vector b;
    public byte h;
    public byte i;
    public byte j;
    public byte k;
    public byte l = 0;

    public o(short s2, short s3, byte by2, byte by3) {
        super(s2, s3, by2, by3);
        this.a();
    }

    public void a() {
        if (this.a == null) {
            this.a = new Vector(2);
        }
        if (this.b == null) {
            this.b = new Vector(3);
        }
        this.h = 1;
        this.i = (byte)2;
        this.j = (byte)2;
        this.k = (byte)-1;
    }

    public final void c() {
        this.a = new Vector(2);
    }

    public void a(byte by2) {
        this.k = (byte)-1;
        this.h = by2;
    }

    public final void b(byte by2) {
        this.i = by2;
        this.j = by2;
    }

    public void d() {
        this.e();
    }

    public final void e() {
        if (this.h == 2 || this.h == 4) {
            this.a();
        }
        if (this.h == 2 || this.h == 4) {
            this.a(8);
        }
    }

    public final void f() {
        ae ae2 = n.a;
        for (byte by2 = 0; by2 < this.e; by2 = (byte)(by2 + 1)) {
            ae2.a[((ck)this).b][((ck)this).a + by2] = null;
            if (((ck)this).b) {
                ae2.a[((ck)this).b + 1][((ck)this).a + by2] = null;
                continue;
            }
            if (!((ck)this).a) continue;
            ae2.a[((ck)this).b][((ck)this).a + 1 + by2] = null;
        }
    }

    public final void g() {
        ae ae2 = n.a;
        for (byte by2 = 0; by2 < this.e; by2 = (byte)(by2 + 1)) {
            ae2.a[((ck)this).b][((ck)this).a + by2] = this;
            if (((ck)this).b) {
                ae2.a[((ck)this).b + 1][((ck)this).a + by2] = this;
                continue;
            }
            if (!((ck)this).a) continue;
            ae2.a[((ck)this).b][((ck)this).a + 1 + by2] = this;
        }
    }

    public boolean a() {
        ae ae2 = n.a;
        if (((ck)this).a || ((ck)this).b) {
            return false;
        }
        if (!ae2.a(this, this.i)) {
            this.a((byte)1);
            return true;
        }
        return false;
    }

    public void a(int n2) {
        this.f();
        switch (this.i) {
            case 1: {
                x.a(this.d > 0);
                this.d = (short)(this.d - n2);
                if (((ck)this).b) {
                    ((ck)this).b = false;
                    break;
                }
                ((ck)this).b = true;
                ((ck)this).b = (byte)(((ck)this).b - 1);
                break;
            }
            case 2: {
                x.a(this.d < n.a.d - 16);
                this.d = (short)(this.d + n2);
                if (((ck)this).b) {
                    ((ck)this).b = false;
                    ((ck)this).b = (byte)(((ck)this).b + 1);
                    break;
                }
                ((ck)this).b = true;
                break;
            }
            case 3: {
                x.a(this.c > 0);
                this.c = (short)(this.c - n2);
                if (((ck)this).a) {
                    ((ck)this).a = false;
                    break;
                }
                ((ck)this).a = true;
                ((ck)this).a = (byte)(((ck)this).a - 1);
                break;
            }
            case 4: {
                x.a(this.c < n.a.c - 16);
                this.c = (short)(this.c + n2);
                if (((ck)this).a) {
                    ((ck)this).a = false;
                    ((ck)this).a = (byte)(((ck)this).a + 1);
                    break;
                }
                ((ck)this).a = true;
            }
        }
        if (n2 != 8) {
            this.b();
        }
        this.g();
    }

    public final void a(ck ck2, byte by2) {
        byte by3;
        byte by4;
        byte by5;
        int n2;
        byte by6 = ck2.a;
        byte by7 = ck2.b;
        ae ae2 = n.a;
        int n3 = 100;
        for (n2 = 0; n2 < this.e; n2 = (int)((byte)(n2 + 1))) {
            if (Math.abs(n3) <= Math.abs(by6 - (((ck)this).a + n2))) continue;
            n3 = by6 - (((ck)this).a + n2);
        }
        n2 = by7 - ((ck)this).b;
        int n4 = Math.abs(n3);
        int n5 = Math.abs(n2);
        int n6 = ck.a.nextInt();
        if (n4 + n5 <= by2 && n4 * n5 == 0 || ck2 == this.a(this.j, ck2)) {
            byte by8 = n2 != 0 ? (n2 < 0 ? (byte)1 : 2) : (n3 < 0 ? (byte)3 : 4);
            this.b(by8);
            return;
        }
        byte by9 = 0;
        if (n5 == n4) {
            by5 = n3 > 0 ? (byte)4 : 3;
            by4 = n2 > 0 ? (byte)2 : 1;
            boolean bl2 = ae2.a(this, by5);
            boolean bl3 = ae2.a(this, by4);
            by9 = bl2 && bl3 ? (ck.a.nextInt() > 0 ? by5 : by4) : (bl2 ? by5 : by4);
        } else {
            by9 = n5 > n4 ? (n2 > 0 ? (byte)2 : 1) : (n3 > 0 ? (byte)4 : 3);
        }
        if ((n4 <= by2 || n5 <= by2) && n4 != n5) {
            if (n4 <= by2 && n5 < n4) {
                if (n2 > 0 && ae2.a(this, (byte)2)) {
                    by9 = 2;
                } else if (n2 < 0 && ae2.a(this, (byte)1)) {
                    by9 = 1;
                }
            } else if (n5 <= by2 && n5 > n4) {
                if (n3 > 0 && ae2.a(this, (byte)4)) {
                    by9 = 4;
                } else if (n3 < 0 && ae2.a(this, (byte)3)) {
                    by9 = 3;
                }
            }
        }
        by5 = 0;
        if (ae2.a(this, by9)) {
            by3 = by9;
            by5 = 1;
        } else {
            by4 = 1;
            if (by9 == 1 && n3 > 0 || by9 == 2 && n3 < 0 || by9 == 3 && n2 < 0 || by9 == 4 && n2 > 0) {
                by4 = 0;
            }
            byte by10 = this.a(ck2, by9, by4 != 0);
            by3 = by10;
            if (by10 != 0) {
                by5 = 1;
            } else {
                by3 = this.a(ck2, by9, by4 == 0);
                if (by3 != 0) {
                    by5 = 1;
                } else if (by4 != 0 && ae2.a(this, u.f[by9])) {
                    by3 = u.f[by9];
                    by5 = 1;
                } else if (by4 == 0 && ae2.a(this, u.e[by9])) {
                    by3 = u.e[by9];
                    by5 = 1;
                }
            }
        }
        if (by5 == 0) {
            by3 = (byte)((n6 & 0xFF) % 4 + 1);
        }
        this.a((byte)2);
        this.b(by3);
    }

    private final byte a(ck ck2, byte by2, boolean bl2) {
        byte[] byArray;
        byte[] byArray2;
        ae ae2 = n.a;
        if (!bl2) {
            byArray2 = u.e;
            byArray = u.c;
        } else {
            byArray2 = u.f;
            byArray = u.d;
        }
        byte by3 = by2 == 1 || by2 == 2 ? this.e : (byte)1;
        for (int i2 = -by3 + 1; i2 < by3; ++i2) {
            if (!ae2.a(this, ((ck)this).a + i2 + u.a[byArray2[by2]], ((ck)this).b + u.b[byArray2[by2]]) || !ae2.a(this, ((ck)this).a + i2 + u.a[byArray[by2]], ((ck)this).b + u.b[byArray[by2]]) && ck2 != ae2.a[((ck)this).b + u.b[byArray[by2]]][((ck)this).a + i2 + u.a[byArray[by2]]]) continue;
            return byArray2[by2];
        }
        return 0;
    }

    public final al a() {
        ck ck2 = this.a(this.i, null);
        if (ck2 instanceof al) {
            return (al)ck2;
        }
        return null;
    }

    public final al a(byte by2) {
        ck ck2 = this.a(by2, null);
        if (ck2 instanceof al) {
            return (al)ck2;
        }
        return null;
    }

    public final ck a(byte by2, ck ck2) {
        ae ae2 = n.a;
        ck ck3 = null;
        for (int i2 = 0; i2 < this.e; ++i2) {
            int n2 = ((ck)this).a + u.a[by2] + i2;
            int n3 = ((ck)this).b + u.b[by2];
            x.a(n2 >= 0);
            x.a(n2 < ae2.a);
            x.a(n3 >= 0);
            x.a(n3 < ae2.b);
            ck3 = ae2.a[n3][n2];
            if (ck3 == this) continue;
            if (ck2 == null && ck3 != null) {
                return ck3;
            }
            if (ck2 == null || ck3 != ck2) continue;
            return ck3;
        }
        return null;
    }

    public final void a(f f2) {
        this.a.addElement(f2);
    }

    public final boolean a(byte by2) {
        x.a(by2 >= 0 && by2 < 8);
        boolean bl2 = false;
        for (int i2 = 0; i2 < this.b.size(); ++i2) {
            cf cf2 = (cf)this.b.elementAt(i2);
            if (((f)cf2).a || cf2.a != by2) continue;
            cf2.c();
            bl2 = true;
            break;
        }
        if (!bl2) {
            this.b.addElement(new cf(by2));
        }
        return bl2;
    }

    public final void b(Graphics graphics, int n2, int n3) {
        for (int i2 = this.a.size() - 1; i2 >= 0; --i2) {
            f f2 = (f)this.a.elementAt(i2);
            f2.a(graphics, n2, n3);
            if (!f2.a) continue;
            this.a.removeElementAt(i2);
        }
    }

    public final void c(Graphics graphics, int n2, int n3) {
        int n4 = -6 * (this.b.size() - 1);
        for (int i2 = this.b.size() - 1; i2 >= 0; --i2) {
            cf cf2 = (cf)this.b.elementAt(i2);
            cf2.a(graphics, n2 + n4, n3);
            n4 += 12;
        }
    }
}

