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
public final class ap
extends cb {
    private t a;
    private ad a;

    public ap(cb cb2) {
        super(cb2, (byte)3);
    }

    public final boolean a(int n2, int n3) {
        if (this.b(n2, n3)) {
            return true;
        }
        if (this.c(n2, n3)) {
            return true;
        }
        if (n3 == 53 || n2 == 8) {
            ao ao2 = n.a();
            if (this.b == 0) {
                byte[] byArray = ao2.a(true, (byte)1);
                x.a(byArray.length > 0);
                this.b = new m(this, byArray, this.b, ax.a.a(3));
            } else if (this.b == 1) {
                byte[] byArray = ao2.a.a((byte)17);
                if (byArray.length > 0) {
                    this.b = new m(this, byArray, this.b, ax.a.a(4));
                } else {
                    Object[] objectArray = new Object[]{ax.a.a(5)};
                    this.a(objectArray);
                }
            } else if (this.b == 2) {
                if (this.a == null) {
                    Object[] objectArray = new Object[]{ax.a.a(6)};
                    this.a(objectArray);
                } else if (this.a == null) {
                    Object[] objectArray = new Object[]{ax.a.a(7)};
                    this.a(objectArray);
                } else if (ao2.a.a < 500) {
                    Object[] objectArray = new Object[]{ax.a.a(8)};
                    this.a(objectArray);
                } else {
                    Object[] objectArray = new Object[]{ax.a.a(9)};
                    this.a((byte)2, (byte)2, objectArray);
                }
            }
            return true;
        }
        return false;
    }

    public final void a(byte by2, byte by3) {
        cb cb2 = this.b;
        super.a(by2, by3);
        if (cb2 instanceof af && by2 == 2 && by3 == 0) {
            ao ao2 = n.a();
            this.a.c = this.a.g;
            ao2.a.a -= 500;
            ao2.a.a(this.a, (byte)1);
            byte[] byArray = new byte[]{ao2.a(this.a)};
            this.b = new m(this, byArray, 10, ax.a.a(10));
            this.a = null;
            this.a = null;
            return;
        }
        if (cb2 instanceof m && (by2 == 0 || by2 == 1)) {
            ad ad2 = by3 >= 100 ? n.a().a(by3 - 100) : n.a().a.a((int)by3);
            if (by2 == 0) {
                x.a(ad2 instanceof t);
                t t2 = (t)ad2;
                if (!t2.b) {
                    Object[] objectArray = new Object[]{ax.a.a(11), ax.a.a(13)};
                    this.a(objectArray);
                    return;
                }
                if (t2.c != -1) {
                    Object[] objectArray = new Object[]{ax.a.a(12), ax.a.a(13)};
                    this.a(objectArray);
                    return;
                }
                this.a = (t)ad2;
                return;
            }
            x.a(ad2.f == 17);
            this.a = ad2;
        }
    }

    public final void a(Graphics graphics, int n2, int n3) {
        int n4;
        graphics.setColor(0x3F1F3F);
        graphics.fillRect(n2, n3, 201, 244);
        cb.c(graphics, n2 + 2, n3 + 4, 197, 236);
        if (r.g >= 176) {
            r.a(graphics, ax.a.a(14), n2 + 3, n3 - 2);
        } else {
            bh.a(graphics, n2 + 3, n3 - 2, r.g - (n2 - 3 << 1), 1, ax.a.a(14));
        }
        cb.a(graphics, n2 + 4, n3 + 9, this.a, (byte)1, ax.a.a(15), this.b == 0);
        cb.a(graphics, n2 + 4, n3 + 9 + 36, this.a, (byte)2, ax.a.a(16), this.b == 1);
        cb.b(graphics, n2 + 4, n3 + 9 + 72, 193, 31, 12558207);
        if (this.a != null && this.a != null) {
            graphics.setColor(0xFFFFFF);
            n4 = n2 + 6;
            n4 += 2 + bh.a(graphics, n4, n3 + 9 + 72 + 4, t.a.a(this.a.g), 1);
            n4 += 2 + bh.a(graphics, n4, n3 + 9 + 72 + 4, ax.a.a(17), 1);
            bh.a(graphics, n4, n3 + 9 + 72 + 4, ax.a.a(18), 1);
            cb.a(graphics, n2 + 201 - 10, n3 + 9 + 72 + 5, 500);
            cb.a(graphics, n2 + 201 - 10, n3 + 9 + 72 + 20, n.a().a.a);
        }
        n4 = bh.a(201, 80);
        if (r.g < 176) {
            n3 -= 22;
        }
        cb.a(graphics, n2 + (201 - n4 >> 1), n3 + 138, n4, ax.a.a(19), this.b == 2);
    }
}

