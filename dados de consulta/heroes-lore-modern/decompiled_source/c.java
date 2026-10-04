/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 */
import javax.microedition.lcdui.Graphics;

public final class c
extends cb {
    public c(bf bf2) {
        super(bf2, (byte)3);
        this.b = (byte)2;
    }

    public final boolean a(int n2, int n3) {
        if (this.b(n2, n3)) {
            return true;
        }
        if (this.d(n2, n3)) {
            return true;
        }
        if (n3 == 53 || n2 == 8) {
            if (this.b == 0 || this.b == 1) {
                if (!bs.a.b) {
                    Object[] objectArray = new Object[]{ce.g.a(6), ce.g.a(7)};
                    this.a(objectArray);
                } else {
                    boolean bl2 = false;
                    this.b = new by(this, (byte)(6 + (2 - this.b)));
                }
            } else {
                this.b = new by(this, (byte)(6 + (2 - this.b)));
            }
            return true;
        }
        if (n3 == bh.a) {
            this.a.a();
            return true;
        }
        return true;
    }

    public final void a(byte by2, byte by3) {
        super.a(by2, by3);
        if ((by2 == 2 || by2 == 12) && by3 == 0) {
            bf.d();
            ce.B();
            bs.a.e();
            n.e = 0;
        }
    }

    public final void a(Graphics graphics, int n2, int n3) {
        graphics.setColor(0x3F1F3F);
        graphics.fillRect(0, 0, r.g, r.h);
        bf.c(graphics, n2, n3);
        bh.a(graphics, 1, n2 + 201 >> 1, n3 + 5);
        bf.b(graphics, n2, n3 + 24, 3);
        n2 += 40;
        n3 += 35;
        if (this.b != 0) {
            graphics.drawImage(ce.l[5], n2 + 6, n3 + 38, 20);
        }
        if (this.b != 1) {
            graphics.drawImage(ce.l[4], n2 + 34, n3 + 38, 20);
        }
        if (this.b != 2) {
            graphics.drawImage(ce.l[3], n2 + 59, n3 + 38, 20);
        }
        if (this.b == 0) {
            graphics.drawImage(ce.l[2], n2 + 6, n3 + 38, 20);
        }
        if (this.b == 1) {
            graphics.drawImage(ce.l[1], n2 + 34, n3 + 38, 20);
        }
        if (this.b == 2) {
            graphics.drawImage(ce.l[0], n2 + 59, n3 + 38, 20);
        }
        graphics.setColor(0);
        bh.a(graphics, n2 + 11, n3 + 104, ce.g.a(12), 1);
        bh.a(graphics, n2 + 11, n3 + 119, ce.g.a(13), 1);
        bh.a(graphics, bh.d, bh.e);
    }
}

