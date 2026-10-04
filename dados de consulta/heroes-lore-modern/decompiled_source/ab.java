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
public final class ab
extends cb {
    private ad a;
    private byte c;
    private boolean c;

    public ab(cb cb2, ad ad2, boolean bl2) {
        super(cb2, (byte)0);
        this.a = ad2;
        this.c = 1;
        this.c = bl2;
    }

    public final boolean a(int n2, int n3) {
        if (this.b(n2, n3)) {
            return true;
        }
        if (this.d(n2, n3)) {
            return true;
        }
        if (n3 == 53 || n2 == 8) {
            if (this.c) {
                Object[] objectArray = new Object[]{bp.a.a(7)};
                if (this.a.f == 0 && n.a != 6 || this.a.f == 2 && n.a != 7 || this.a.f == 1 && n.a != 8 || this.a.f == 3 && n.a != 8) {
                    objectArray = new Object[]{bp.a.a(26), bp.a.a(7)};
                }
                this.a((byte)2, (byte)2, objectArray);
            } else {
                this.a((byte)2, (byte)2, new Object[]{bp.a.a(23)});
            }
            return true;
        }
        if (n3 == bh.a) {
            ((cb)this).a.a();
            return true;
        }
        return true;
    }

    public final void a(byte by2, byte by3) {
        super.a(by2, by3);
        ao ao2 = n.a();
        if (by2 == 2 && by3 == 0) {
            if (this.c) {
                int n2;
                ad ad2 = ad.a(this.a.f, this.a.g, true, false);
                if (ad2 instanceof e) {
                    ((e)ad2).b = true;
                }
                if (ao2.a.a < (n2 = ad2.a * this.c)) {
                    this.a(new Object[]{bp.a.a(8)});
                    return;
                }
                if (!ao2.a.a(ad2, (int)this.c)) {
                    this.a(new Object[]{bp.a.a(9), bp.a.a(10)});
                    return;
                }
                ao2.a.a -= n2;
                this.a(new Object[]{bp.a.a(11), bp.a.a(12)});
                return;
            }
            ao2.a.a(this.a, this.c);
            ao2.a.a += this.a.a * this.c / 5;
            this.a(new Object[]{this.a.a, bp.a.a(24)});
            return;
        }
        if (by2 == 1) {
            ((cb)this).a.a((byte)-1, (byte)-1);
        }
    }

    public final void a(byte by2) {
        if (this.c && ad.b[this.a.f] || !this.c && this.a.h > 1) {
            if (by2 == 4 && this.c < (this.c ? (byte)99 : this.a.h)) {
                this.c = (byte)(this.c + 1);
                return;
            }
            if (by2 == 4 && this.c && this.c == 99) {
                this.c = 1;
                return;
            }
            if (by2 == 3 && this.c > 1) {
                this.c = (byte)(this.c - 1);
                return;
            }
            if (by2 == 3 && this.c && this.c == 1) {
                this.c = (byte)99;
            }
        }
    }

    public final void a(Graphics graphics, int n2, int n3) {
        bh.a(graphics);
        if (this.c) {
            bh.a(graphics, bh.j, bh.e);
        } else {
            bh.a(graphics, bh.h, bh.e);
        }
        boolean bl2 = false;
        cb.a(graphics, n2 += 3, n3 += 20, 195, 29);
        cb.b(graphics, n2, n3, 195, 29);
        cb.a(graphics, n2, n3 + 31, 195, 67);
        cb.b(graphics, n2, n3 + 31, 195, 67);
        graphics.setColor(14663551);
        bh.a(graphics, (n2 += 15) + 8, n3 + 7, bp.a.a(13), 1);
        cb.a(graphics, n2 + 102, n3 + 11, n.a().a.a);
        graphics.setColor(0xFFFFFF);
        if (this.c && ad.b[this.a.f] || !this.c && this.a.h > 1) {
            if (this.c) {
                bh.a(graphics, n2 + 6, n3 + 38, bp.a.a(14), 1);
            } else {
                bh.a(graphics, n2 + 6, n3 + 38, bp.a.a(25), 1);
            }
            graphics.drawImage(ce.p, n2 + 32, n3 + 65, 20);
            r.c(graphics, this.c, n2 + 68, n3 + 65, 8);
            graphics.drawImage(ce.e, n2 + 77, n3 + 65, 20);
        } else {
            bh.a(graphics, n2 + 6, n3 + 38, bp.a.a(15), 1);
        }
        graphics.drawImage(ce.d[this.a.f], n2 + 45, n3 + 57, 20);
        if (this.c) {
            cb.a(graphics, n2 + 77, n3 + 85, this.c * this.a.a);
            return;
        }
        cb.a(graphics, n2 + 77, n3 + 85, this.c * this.a.a / 5);
    }
}

