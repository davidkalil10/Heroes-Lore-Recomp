/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 */
import javax.microedition.lcdui.Graphics;

public final class ch
extends cb {
    private e a;

    public ch(cb cb2) {
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
                byte[] byArray = ao2.a(false, (byte)-1);
                if (byArray.length == 0) {
                    this.a(new Object[]{cj.a.a(3935).toCharArray()});
                } else {
                    this.b = new m(this, byArray, this.b, aa.a.a(3));
                }
            } else if (this.b == 1) {
                if (this.a == null) {
                    this.a(new Object[]{aa.a.a(3)});
                } else if (ao2.a.a < 100) {
                    this.a(new Object[]{aa.a.a(8)});
                } else {
                    this.a((byte)2, (byte)2, new Object[]{aa.a.a(19)});
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
            this.a.b = true;
            ao2.a.a -= 100;
            byte[] byArray = new byte[]{ao2.a(this.a)};
            this.b = new m(this, byArray, 10, aa.a.a(20));
            this.a = null;
            return;
        }
        if (cb2 instanceof m && by2 == 0) {
            ad ad2 = by3 >= 100 ? n.a().a(by3 - 100) : n.a().a.a((int)by3);
            x.a(ad2 instanceof e);
            e e2 = (e)ad2;
            if (e2.b) {
                this.a(new Object[]{aa.a.a(21)});
                return;
            }
            this.a = e2;
        }
    }

    public final void a(Graphics graphics, int n2, int n3) {
        ao ao2 = n.a();
        graphics.setColor(0x3F1F3F);
        graphics.fillRect(n2, n3, 201, 244);
        cb.c(graphics, n2 + 2, n3 + 4, 197, 236);
        r.a(graphics, aa.a.a(13), n2 + 3, n3 - 2);
        cb.b(graphics, n2 + 3, n3 + 7, 195, 19, 0x9F7F7F);
        graphics.setColor(0xFFFFFF);
        if (r.g >= 176) {
            bh.a(graphics, n2 + 6, n3 + 11, aa.a.a(23), 1);
        } else {
            bh.a(graphics, r.g >> 1, n3 + 8, r.g - (n2 + 6 << 1), 1, aa.a.a(23), 17);
        }
        cb.a(graphics, n2 + 4, n3 + 30, this.a, (byte)1, aa.a.a(15), this.b == 0);
        cb.a(graphics, n2 + 201 - 10, n3 + 75, ao2.a.a);
        cb.b(graphics, n2 + 4, n3 + 83, 193, 20, 0x9F7F7F);
        graphics.setColor(0xFFFFFF);
        bh.a(graphics, n2 + 8, n3 + 88, aa.a.a(24), 1);
        cb.a(graphics, n2 + 201 - 8, n3 + 89, 100);
        int n4 = bh.a(201, 80);
        if (r.g < 176) {
            n3 -= 22;
        }
        cb.a(graphics, n2 + (201 - n4 >> 1), n3 + 138, n4, aa.a.a(25), this.b == 1);
    }
}

