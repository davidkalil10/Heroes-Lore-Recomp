/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 */
import java.util.Vector;
import javax.microedition.lcdui.Graphics;

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
        this.void_a();
    }

    public void void_a() {
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

    public final void void_c() {
        this.a = new Vector(2);
    }

    public void void_a(byte by2) {
        this.k = (byte)-1;
        this.h = by2;
    }

    public final void b(byte by2) {
        this.i = by2;
        this.j = by2;
    }

    public void void_d() {
        this.void_e();
    }

    public final void void_e() {
        if (this.h == 2 || this.h == 4) {
            this.boolean_a();
        }
        if (this.h == 2 || this.h == 4) {
            this.void_a(8);
        }
    }

    public final void f() {
        ae ae2 = n.var_ae_a;
        for (byte by2 = 0; by2 < this.e; by2 = (byte)(by2 + 1)) {
            ae2.var_ck_arr_arr_a[this.var_byte_b][this.var_byte_a + by2] = null;
            if (this.var_boolean_b) {
                ae2.var_ck_arr_arr_a[this.var_byte_b + 1][this.var_byte_a + by2] = null;
                continue;
            }
            if (!this.var_boolean_a) continue;
            ae2.var_ck_arr_arr_a[this.var_byte_b][this.var_byte_a + 1 + by2] = null;
        }
    }

    public final void g() {
        ae ae2 = n.var_ae_a;
        for (byte by2 = 0; by2 < this.e; by2 = (byte)(by2 + 1)) {
            ae2.var_ck_arr_arr_a[this.var_byte_b][this.var_byte_a + by2] = this;
            if (this.var_boolean_b) {
                ae2.var_ck_arr_arr_a[this.var_byte_b + 1][this.var_byte_a + by2] = this;
                continue;
            }
            if (!this.var_boolean_a) continue;
            ae2.var_ck_arr_arr_a[this.var_byte_b][this.var_byte_a + 1 + by2] = this;
        }
    }

    public boolean boolean_a() {
        ae ae2 = n.var_ae_a;
        if (this.var_boolean_a || this.var_boolean_b) {
            return false;
        }
        if (!ae2.a(this, this.i)) {
            this.void_a((byte)1);
            return true;
        }
        return false;
    }

    public void void_a(int n2) {
        this.f();
        switch (this.i) {
            case 1: {
                x.a(this.var_short_d > 0);
                this.var_short_d = (short)(this.var_short_d - n2);
                if (this.var_boolean_b) {
                    this.var_boolean_b = false;
                    break;
                }
                this.var_boolean_b = true;
                this.var_byte_b = (byte)(this.var_byte_b - 1);
                break;
            }
            case 2: {
                x.a(this.var_short_d < n.var_ae_a.var_int_d - 16);
                this.var_short_d = (short)(this.var_short_d + n2);
                if (this.var_boolean_b) {
                    this.var_boolean_b = false;
                    this.var_byte_b = (byte)(this.var_byte_b + 1);
                    break;
                }
                this.var_boolean_b = true;
                break;
            }
            case 3: {
                x.a(this.var_short_c > 0);
                this.var_short_c = (short)(this.var_short_c - n2);
                if (this.var_boolean_a) {
                    this.var_boolean_a = false;
                    break;
                }
                this.var_boolean_a = true;
                this.var_byte_a = (byte)(this.var_byte_a - 1);
                break;
            }
            case 4: {
                x.a(this.var_short_c < n.var_ae_a.var_int_c - 16);
                this.var_short_c = (short)(this.var_short_c + n2);
                if (this.var_boolean_a) {
                    this.var_boolean_a = false;
                    this.var_byte_a = (byte)(this.var_byte_a + 1);
                    break;
                }
                this.var_boolean_a = true;
            }
        }
        if (n2 != 8) {
            this.void_b();
        }
        this.g();
    }

    public final void a(ck ck2, byte by2) {
        byte by3;
        byte by4;
        byte by5;
        int n2;
        byte by6 = ck2.var_byte_a;
        byte by7 = ck2.var_byte_b;
        ae ae2 = n.var_ae_a;
        int n3 = 100;
        for (n2 = 0; n2 < this.e; n2 = (int)((byte)(n2 + 1))) {
            if (Math.abs(n3) <= Math.abs(by6 - (this.var_byte_a + n2))) continue;
            n3 = by6 - (this.var_byte_a + n2);
        }
        n2 = by7 - this.var_byte_b;
        int n4 = Math.abs(n3);
        int n5 = Math.abs(n2);
        int n6 = ck.var_java_util_Random_a.nextInt();
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
            by9 = bl2 && bl3 ? (ck.var_java_util_Random_a.nextInt() > 0 ? by5 : by4) : (bl2 ? by5 : by4);
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
        this.void_a((byte)2);
        this.b(by3);
    }

    private final byte a(ck ck2, byte by2, boolean bl2) {
        byte[] byArray;
        byte[] byArray2;
        ae ae2 = n.var_ae_a;
        if (!bl2) {
            byArray2 = u.e;
            byArray = u.c;
        } else {
            byArray2 = u.f;
            byArray = u.d;
        }
        byte by3 = by2 == 1 || by2 == 2 ? this.e : (byte)1;
        for (int i2 = -by3 + 1; i2 < by3; ++i2) {
            if (!ae2.a(this, this.var_byte_a + i2 + u.var_byte_arr_a[byArray2[by2]], this.var_byte_b + u.b[byArray2[by2]]) || !ae2.a(this, this.var_byte_a + i2 + u.var_byte_arr_a[byArray[by2]], this.var_byte_b + u.b[byArray[by2]]) && ck2 != ae2.var_ck_arr_arr_a[this.var_byte_b + u.b[byArray[by2]]][this.var_byte_a + i2 + u.var_byte_arr_a[byArray[by2]]]) continue;
            return byArray2[by2];
        }
        return 0;
    }

    public final al al_a() {
        ck ck2 = this.a(this.i, null);
        if (ck2 instanceof al) {
            return (al)ck2;
        }
        return null;
    }

    public final al al_a(byte by2) {
        ck ck2 = this.a(by2, null);
        if (ck2 instanceof al) {
            return (al)ck2;
        }
        return null;
    }

    public final ck a(byte by2, ck ck2) {
        ae ae2 = n.var_ae_a;
        ck ck3 = null;
        for (int i2 = 0; i2 < this.e; ++i2) {
            int n2 = this.var_byte_a + u.var_byte_arr_a[by2] + i2;
            int n3 = this.var_byte_b + u.b[by2];
            x.a(n2 >= 0);
            x.a(n2 < ae2.var_int_a);
            x.a(n3 >= 0);
            x.a(n3 < ae2.var_int_b);
            ck3 = ae2.var_ck_arr_arr_a[n3][n2];
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

    public final boolean boolean_a(byte by2) {
        x.a(by2 >= 0 && by2 < 8);
        boolean bl2 = false;
        for (int i2 = 0; i2 < this.b.size(); ++i2) {
            cf cf2 = (cf)this.b.elementAt(i2);
            if (cf2.var_boolean_a || cf2.var_byte_a != by2) continue;
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
            if (!f2.var_boolean_a) continue;
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

