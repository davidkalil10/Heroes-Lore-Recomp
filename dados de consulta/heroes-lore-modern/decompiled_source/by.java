/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 */
import javax.microedition.lcdui.Graphics;

public final class by
extends cb {
    private byte c;

    public by(c c2, byte by2) {
        super(c2, (byte)2);
        this.b = 1;
        this.c = by2;
    }

    public final boolean a(int n2, int n3) {
        if (this.b(n2, n3)) {
            return true;
        }
        if (this.d(n2, n3)) {
            return true;
        }
        switch (n2) {
            case 8: {
                if (this.b == 0) {
                    this.b = new bk(this, this.c);
                    break;
                }
                this.a.a();
                break;
            }
            default: {
                if (n3 == bh.a) {
                    this.a.a();
                    break;
                }
                if (n3 != 53) break;
                if (this.b == 0) {
                    this.b = new bk(this, this.c);
                    break;
                }
                this.a.a();
            }
        }
        return true;
    }

    public final void a(Graphics graphics, int n2, int n3) {
        graphics.setColor(0x3F1F3F);
        graphics.fillRect(0, 0, r.g, r.h);
        bf.c(graphics, n2, n3);
        bh.a(graphics, 1, n2 + 100, n3 + 5);
        bf.b(graphics, n2, n3 + 24, 3);
        graphics.setColor(0);
        bh.a(graphics, (n2 += 35) + 11, (n3 += 30) + 34, 179, 1, ce.a.a(15 + this.c - 6));
        graphics.drawImage(ce.k[19], n2 + 7, n3 + 80, 20);
        bh.a(graphics, n2 + 11, n3 + 84, ce.a.a(this.c - 6), 1);
        graphics.drawImage(ce.l[this.c - 6], n2 + 125, n3 + 137, 40);
        graphics.drawImage(ce.k[17], n2 + 5 + (this.b == 0 ? 0 : 28), n3 + 118, 20);
        if (this.b == 0) {
            graphics.setColor(0xFFFFFF);
        } else {
            graphics.setColor(0);
        }
        bh.a(graphics, n2 + 9, n3 + 121, ce.g.a(14), 1);
        if (this.b == 1) {
            graphics.setColor(0xFFFFFF);
        } else {
            graphics.setColor(0);
        }
        bh.a(graphics, n2 + 37, n3 + 121, ce.g.a(15), 1);
        bh.a(graphics, bh.d, bh.e);
    }
}

