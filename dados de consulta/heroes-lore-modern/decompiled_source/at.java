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
public final class at
extends cb {
    private e a;
    private int a;
    private byte c;
    private byte d;
    private boolean c;

    public at(cb cb2) {
        super(cb2, (byte)2);
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
                byte[] byArray = ao2.a.a(false, (byte)0);
                if (byArray.length > 0) {
                    this.b = new m(this, byArray, this.b, aa.a.a(3));
                } else {
                    this.a(new Object[]{cj.a.a(3936).toCharArray()});
                }
            } else if (this.b == 1) {
                int n4 = ao2.a.a((byte)11, (byte)0);
                byte by2 = ao2.a.a((byte)11, (byte)0);
                if (this.a == null) {
                    this.a(new Object[]{aa.a.a(3)});
                } else if (this.a.e >= this.a.d) {
                    this.a(new Object[]{aa.a.a(6), aa.a.a(7)});
                } else if (by2 < 0 || n4 < this.c) {
                    this.a(new Object[]{aa.a.a(5)});
                } else if (ao2.a.a < this.a) {
                    this.a(new Object[]{aa.a.a(8)});
                } else {
                    this.a((byte)2, (byte)2, new Object[]{aa.a.a(9)});
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
            x.a(ao2.a.a((byte)11, (byte)0) != -1);
            ao2.a.a((byte)11, (byte)0, this.c);
            ao2.a.a -= this.a;
            if (100 * (this.a.d - this.a.e) / this.a.d + 30 < h.a(1, 100)) {
                this.d = (byte)2;
                this.c = false;
                return;
            }
            this.d = (byte)2;
            this.c = true;
            return;
        }
        if (cb2 instanceof m && by2 == 0) {
            ad ad2 = by3 >= 100 ? n.a().a(by3 - 100) : n.a().a.a((int)by3);
            x.a(ad2 instanceof e);
            e e2 = (e)ad2;
            if (!e2.b) {
                this.a(new Object[]{aa.a.a(11), aa.a.a(12)});
                return;
            }
            this.a = (e)ad2;
            this.a = this.a.e * 100;
            this.c = (byte)(this.a.e + 1);
        }
    }

    public final void a(Graphics graphics, int n2, int n3) {
        Object[] objectArray;
        ao ao2 = n.a();
        graphics.setColor(0x3F1F3F);
        graphics.fillRect(n2, n3, 201, 244);
        cb.c(graphics, n2 + 2, n3 + 4, 197, 236);
        r.a(graphics, aa.a.a(13), n2 + 3, n3 - 2);
        cb.b(graphics, n2 + 3, n3 + 7, 195, 19, 0x9F7F7F);
        graphics.setColor(0xFFFFFF);
        if (r.g >= 176) {
            bh.a(graphics, n2 + 6, n3 + 11, aa.a.a(14), 1);
        } else {
            bh.a(graphics, r.g >> 1, n3 + 8, r.g - (n2 + 6 << 1), 1, aa.a.a(14), 17);
        }
        cb.a(graphics, n2 + 4, n3 + 30, this.a, (byte)1, aa.a.a(15), this.b == 0);
        cb.a(graphics, n2 + 201 - 8, n3 + 65, ao2.a.a);
        cb.b(graphics, n2 + 4, n3 + 73, 193, 38, 0x9F7F7F);
        graphics.setColor(0xFFFFFF);
        bh.a(graphics, n2 + 8, n3 + 80, aa.a.a(16), 1);
        if (this.a != null) {
            cb.a(graphics, n2 + 201 - 8, n3 + 80, this.a);
            objectArray = h.a(aa.a.a(17), (" : " + this.c + "\u00b0\u0142").toCharArray());
            bh.a(graphics, n2 + 8, n3 + 93, objectArray, 1);
        }
        if (this.d == 2) {
            this.d = 1;
            int n4 = r.i - 55;
            int n5 = r.j - 11;
            cb.a(graphics, n4, n5, 110, 22);
            cb.b(graphics, n4, n5, 110, 22);
            graphics.setColor(0xFFFFFF);
            bh.a(graphics, n4 + 5, n5 + 5, aa.a.a(28), 1);
            ((cb)this).a = true;
        } else if (this.d == 1) {
            this.d = 0;
            try {
                Thread.sleep(500L);
                if (this.c) {
                    Thread.sleep(1000L);
                    this.a.e = (byte)(this.a.e + 1);
                    objectArray = new byte[]{ao2.a(this.a)};
                    this.b = new m(this, (byte[])objectArray, 10, aa.a.a(10));
                    this.a = this.a.e * 100;
                    this.c = (byte)(this.a.e + 1);
                } else {
                    n.a().a.a((ad)this.a, (byte)1);
                    this.a = null;
                    n.o();
                    this.a(new Object[]{aa.a.a(26), aa.a.a(29)});
                }
            }
            catch (Exception exception) {}
        }
        int n6 = bh.a(201, 80);
        if (r.g < 176) {
            n3 -= 22;
        }
        cb.a(graphics, n2 + (201 - n6 >> 1), n3 + 138, n6, aa.a.a(18), this.b == 1);
    }
}

