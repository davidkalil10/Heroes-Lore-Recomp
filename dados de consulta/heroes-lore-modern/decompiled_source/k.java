/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 */
import javax.microedition.lcdui.Graphics;

public final class k
extends cb {
    private ad[] a = new ad[3];

    public k(cb cb2) {
        super(cb2, (byte)4);
    }

    public final boolean a(int n2, int n3) {
        if (this.b(n2, n3)) {
            return true;
        }
        if (this.b != null && this.b.a(n2, n3)) {
            return true;
        }
        if (this.c(n2, n3)) {
            return true;
        }
        if (n3 == 53 || n2 == 8) {
            ao ao2 = n.a();
            if (this.b < 3) {
                byte[] byArray = ao2.a.b();
                if (byArray.length < 1) {
                    Object[] objectArray = new Object[]{ax.a.a(20)};
                    this.a(objectArray);
                } else {
                    this.b = new m(this, byArray, this.b, ax.a.a(21));
                }
            } else {
                int n4 = 0;
                Object[] objectArray = new Object[3];
                if (this.a[0] != null) {
                    n4 = 1;
                    objectArray[0] = this.a[0].a;
                }
                if (this.a[1] != null) {
                    int n5 = n4;
                    n4 = (byte)(n4 + 1);
                    objectArray[n5] = this.a[1].a;
                }
                if (this.a[2] != null) {
                    int n6 = n4;
                    n4 = (byte)(n4 + 1);
                    objectArray[n6] = this.a[2].a;
                }
                if (n4 < 2) {
                    Object[] objectArray2 = new Object[]{ax.a.a(22), ax.a.a(23)};
                    this.a(objectArray2);
                } else if (500 > ao2.a.a) {
                    Object[] objectArray3 = new Object[]{ax.a.a(24)};
                    this.a(objectArray3);
                } else {
                    this.b = new bo(this, ax.a.a(25), objectArray, ax.a.a(26), 500, 20);
                }
            }
            return true;
        }
        return false;
    }

    public final void a(byte n2, byte by2) {
        cb cb2 = this.b;
        super.a((byte)n2, by2);
        if (cb2 instanceof af && n2 == 2 && by2 == 0) {
            ao ao2 = n.a();
            ad ad2 = ad.a(this.a[0], this.a[1], this.a[2]);
            if (ad2 != null) {
                if (this.a[0] != null) {
                    ao2.a.a(this.a[0], (byte)1);
                }
                if (this.a[1] != null) {
                    ao2.a.a(this.a[1], (byte)1);
                }
                if (this.a[2] != null) {
                    ao2.a.a(this.a[2], (byte)1);
                }
                if (ao2.a.a(ad2, 1)) {
                    byte[] byArray = new byte[]{ao2.a.a(ad2.f, ad2.g)};
                    this.b = new m(this, byArray, 10, ax.a.a(27));
                    this.a[0] = null;
                    this.a[1] = null;
                    this.a[2] = null;
                    return;
                }
                if (this.a[0] != null) {
                    ao2.a.a(this.a[0], 1);
                }
                if (this.a[1] != null) {
                    ao2.a.a(this.a[1], 1);
                }
                if (this.a[2] != null) {
                    ao2.a.a(this.a[2], 1);
                }
                Object[] objectArray = new Object[]{ax.a.a(28), ax.a.a(29)};
                this.a(objectArray);
                return;
            }
            if (this.a[0] != null) {
                ao2.a.a(this.a[0], (byte)1);
            }
            if (this.a[1] != null) {
                ao2.a.a(this.a[1], (byte)1);
            }
            if (this.a[2] != null) {
                ao2.a.a(this.a[2], (byte)1);
            }
            this.a[0] = null;
            this.a[1] = null;
            this.a[2] = null;
            Object[] objectArray = new Object[]{ax.a.a(30)};
            this.a(objectArray);
            return;
        }
        if (cb2 instanceof m && (n2 == 0 || n2 == 1 || n2 == 2)) {
            ao ao3 = n.a();
            ad ad3 = by2 >= 100 ? n.a().a(by2 - 100) : n.a().a.a((int)by2);
            x.a(ad.c[ad3.f]);
            int n3 = 0;
            for (int i2 = 0; i2 < 3; ++i2) {
                if (n2 == i2 || this.a[i2] == null || this.a[i2].f != ad3.f || this.a[i2].g != ad3.g) continue;
                ++n3;
            }
            if (ao3.a.a(ad3.f, ad3.g) <= n3) {
                Object[] objectArray = new Object[]{ax.a.a(31)};
                this.a(objectArray);
                return;
            }
            this.a[n2] = ad3;
            return;
        }
        if (cb2 instanceof bo && n2 == 20) {
            Object[] objectArray = new Object[]{ax.a.a(32)};
            this.a((byte)2, (byte)2, objectArray);
        }
    }

    public final void a(Graphics graphics, int n2, int n3) {
        graphics.setColor(0x3F1F3F);
        graphics.fillRect(n2, n3, 201, 244);
        cb.c(graphics, n2 + 2, n3 + 4, 197, 236);
        if (r.g >= 176) {
            r.a(graphics, ax.a.a(14), n2 + 3, n3 - 2);
        } else {
            bh.a(graphics, n2 + 3, n3 - 2, r.g - (n2 - 3 << 1), 1, ax.a.a(14));
        }
        cb.a(graphics, n2 + 4, n3 + 9, this.a[0], (byte)1, ax.a.a(33), this.b == 0);
        cb.a(graphics, n2 + 4, n3 + 9 + 36, this.a[1], (byte)2, ax.a.a(33), this.b == 1);
        cb.a(graphics, n2 + 4, n3 + 9 + 72, this.a[2], (byte)3, ax.a.a(33), this.b == 2);
        int n4 = bh.a(201, 80);
        if (r.g < 176) {
            n3 -= 22;
        }
        cb.a(graphics, n2 + (201 - n4 >> 1), n3 + 138, n4, ax.a.a(25), this.b == 3);
    }
}

