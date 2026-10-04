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
public final class bi
extends cb {
    private short a;
    private short[] a;

    public bi(q q2) {
        super(q2, (byte)4);
        this.a = n.a().a;
        this.a = new short[4];
    }

    public final boolean a(int n2, int n3) {
        if (this.b(n2, n3)) {
            return true;
        }
        if (this.c(n2, n3)) {
            return true;
        }
        if (n3 == 52 || n2 == 2) {
            this.b((byte)3);
            return true;
        }
        if (n3 == 54 || n2 == 5) {
            this.b((byte)4);
            return true;
        }
        if (n3 == 53 || n2 == 8) {
            if (this.a[0] != 0 || this.a[1] != 0 || this.a[2] != 0 || this.a[3] != 0) {
                Object[] objectArray = new Object[]{ai.a.a(33)};
                this.a((byte)2, (byte)2, objectArray);
            } else {
                Object[] objectArray = new Object[]{ai.a.a(34), ai.a.a(35)};
                this.a((byte)1, (byte)1, objectArray);
            }
            return true;
        }
        if (n3 == bh.a) {
            ((cb)this).a.a((byte)-1, (byte)-1);
            return true;
        }
        return true;
    }

    public final void a(Graphics graphics, int n2, int n3) {
        ao ao2 = n.a();
        cb.b(graphics, n2 += 36, n3 += 37, 147, 26, 0x3F1F3F);
        graphics.setColor(0xFFFFFF);
        r.a(graphics, ai.a.a(36), n2 + 3, n3 + 3);
        r.a(graphics, ai.a.a(37), n2 + 3, n3 + 10 + 4);
        r.c(graphics, this.a, n2 + 65, n3 + 10 + 4, 8);
        graphics.setColor(0x5F3F3F);
        graphics.fillRect(n2, n3 + 30, 151, 62);
        for (byte by2 = 0; by2 < 4; by2 = (byte)(by2 + 1)) {
            if (this.b == by2) {
                graphics.setColor(0xFFFFFF);
                graphics.drawImage(ce.e, n2 + 2, n3 + 35 + by2 * 15, 20);
            } else {
                graphics.setColor(14663551);
            }
            int n4 = this.a[by2];
            switch (by2) {
                case 0: {
                    n4 += ao2.b + ao2.m;
                    break;
                }
                case 1: {
                    n4 += ao2.e + ao2.n;
                    break;
                }
                case 2: {
                    n4 += ao2.f + ao2.o;
                    break;
                }
                case 3: {
                    n4 += ao2.g + ao2.p;
                }
            }
            bh.a(graphics, n2 + 10, n3 + 35 + by2 * 15, ce.a.a(9 + by2), 1);
            graphics.drawImage(ce.p, n2 + 45 + 25, n3 + 35 + by2 * 15, 20);
            r.c(graphics, n4, n2 + 65 + 25, n3 + 35 + by2 * 15, 8);
            graphics.drawImage(ce.e, n2 + 67 + 25, n3 + 35 + by2 * 15, 20);
        }
    }

    public final void a(byte by2, byte by3) {
        cb cb2 = this.b;
        super.a(by2, by3);
        if (cb2 instanceof af && by2 == 2 && by3 == 0) {
            ao ao2 = n.a();
            ao2.b = (short)(ao2.b + this.a[0]);
            ao2.e = (short)(ao2.e + this.a[1]);
            ao2.f = (short)(ao2.f + this.a[2]);
            ao2.g = (short)(ao2.g + this.a[3]);
            ao2.a = this.a;
            ao2.n();
            ((cb)this).a.a((byte)-1, (byte)-1);
        }
    }

    private void b(byte by2) {
        if (by2 == 4 && this.a > 0) {
            byte by3 = this.b;
            this.a[by3] = (short)(this.a[by3] + 1);
            this.a = (short)(this.a - 1);
            return;
        }
        if (by2 == 3 && this.a[this.b] > 0) {
            byte by4 = this.b;
            this.a[by4] = (short)(this.a[by4] - 1);
            this.a = (short)(this.a + 1);
        }
    }
}

