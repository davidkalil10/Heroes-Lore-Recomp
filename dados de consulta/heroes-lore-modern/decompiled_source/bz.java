/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 */
import javax.microedition.lcdui.Graphics;

public final class bz
extends cb {
    public bz(cb cb2) {
        super(cb2, (byte)5);
    }

    public final boolean a(int n2, int n3) {
        if (this.b(n2, n3)) {
            return true;
        }
        if (this.c(n2, n3)) {
            this.a.a = true;
            return true;
        }
        if (n3 == 53 || n2 == 8) {
            byte by2 = -1;
            switch (this.b) {
                case 0: {
                    switch (n.a) {
                        case 6: {
                            by2 = 0;
                            break;
                        }
                        case 7: {
                            by2 = 2;
                            break;
                        }
                        case 8: {
                            by2 = 1;
                        }
                    }
                    break;
                }
                case 2: {
                    by2 = 5;
                    break;
                }
                case 1: {
                    if (n.a != 8) break;
                    by2 = 3;
                    break;
                }
                case 3: {
                    by2 = 6;
                    break;
                }
                case 4: {
                    by2 = 4;
                }
            }
            if (by2 != -1) {
                byte[] byArray = n.a().a.a(by2);
                if (byArray.length > 0) {
                    this.b = new m(this, byArray, this.b, ai.a.a(16));
                } else {
                    Object[] objectArray = new Object[]{cj.a.a(3937).toCharArray()};
                    this.a(objectArray);
                }
            } else {
                Object[] objectArray = new Object[]{cj.a.a(3937).toCharArray()};
                this.a(objectArray);
            }
            return true;
        }
        return false;
    }

    public final void a(byte by2, byte by3) {
        cb cb2 = this.b;
        super.a(by2, by3);
        if (cb2 instanceof m && by2 != -1) {
            ao ao2 = n.a();
            e e2 = (e)ao2.a.a((int)by3);
            if (!e2.b) {
                Object[] objectArray = new Object[]{ai.a.a(18), ai.a.a(19)};
                this.a(objectArray);
                return;
            }
            ao2.b(by3, by2);
        }
    }

    public final void a(Graphics graphics, int n2, int n3) {
        ad ad2;
        n2 += 2;
        n3 += 15;
        ao ao2 = n.a();
        if (r.h > 128) {
            r.a(graphics, ai.a.a(20), n2 + 5, n3);
        }
        this.a(graphics, n2, n3, false);
        for (int i2 = this.c(); i2 <= this.d(); ++i2) {
            ad2 = ao2.a(i2);
            if (ad2 != null) {
                cb.a(graphics, n2 + 13, n3 + 18 + 23 * (i2 % 5), ad2, false);
                continue;
            }
            graphics.drawImage(ce.n[i2], n2 + 13, n3 + 19 + 23 * (i2 % 5), 3);
        }
        ad2 = ao2.a((int)this.b);
        if (ad2 != null) {
            cb.a(graphics, n2 + 33, n3 + 14, ad2);
            return;
        }
        graphics.setColor(0xFFFFFF);
        if (this.b == 1 && n.a != 8) {
            if (r.g > 128) {
                bh.a(graphics, n2 + 30, n3 + 14, ai.a.a(49), 1);
                return;
            }
            bh.a(graphics, n2 + 30, n3 + 14, 75, 1, ai.a.a(49));
            return;
        }
        bh.a(graphics, n2 + 33, n3 + 14, ai.a.a(21), 1);
    }
}

