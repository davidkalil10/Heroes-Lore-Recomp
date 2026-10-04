/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 */
import javax.microedition.lcdui.Graphics;

public final class s
extends cb {
    private byte[] h;
    private byte c;
    private byte d;
    private char[] a;
    private char[] b;

    public s(cb cb2) {
        super(cb2, (byte)0);
        int n2 = n.a[n.a - 6].length;
        int n3 = 0;
        this.h = new byte[n2 * 2];
        for (int n4 = 0; n4 < n2; n4 = (int)((byte)(n4 + 1))) {
            if (n.b(1 + n4 * 3 + 1)) continue;
            if (n.b(1 + n4 * 3 + 2)) {
                if (!n.b(1 + n4 * 3) || ce.f.a(n4 * 7 + 2).length <= 0) continue;
                this.h[n3++] = n4;
                this.h[n3++] = 2;
                continue;
            }
            if (!n.b(1 + n4 * 3) || ce.f.a(n4 * 7).length <= 0) continue;
            this.h[n3++] = n4;
            this.h[n3++] = 0;
        }
        ((cb)this).a = (byte)(n3 / 2);
        this.d();
    }

    public final boolean a(int n2, int n3) {
        if (this.b(n2, n3)) {
            return true;
        }
        if (this.c(n2, n3)) {
            ((cb)this).a.a = true;
            this.d();
            return true;
        }
        if (n3 == 53 || n2 == 8) {
            if (((cb)this).a > 0) {
                ((cb)this).b = new am(this, this.a, this.b, 0);
            }
            return true;
        }
        return false;
    }

    public final void a(byte by2, byte by3) {
        super.a(by2, by3);
        if (by2 == 0 && by3 == 1) {
            ((cb)this).b = new am(this, ai.a.a(54), ce.f.a(this.c * 7 + 6), 1);
        }
    }

    private final void d() {
        this.c = this.h[((cb)this).b * 2];
        this.d = this.h[((cb)this).b * 2 + 1];
        if (this.d == 2) {
            int n2 = this.c * 7 + 2;
            this.a = ce.f.a(n2);
            this.b = ce.f.a(n2 + 1);
            return;
        }
        int n3 = this.c * 7 + 0;
        this.a = ce.f.a(n3);
        this.b = ce.f.a(n3 + 1);
    }

    public final void a(Graphics graphics, int n2, int n3) {
        n2 += 2;
        n3 += 15;
        if (r.h > 128) {
            r.a(graphics, ai.a.a(39), n2 + 5, n3);
        }
        if (((cb)this).a > 0) {
            int n4;
            this.a(graphics, n2, n3, true);
            for (n4 = this.c(); n4 <= this.d(); ++n4) {
                graphics.drawImage(ce.d[18], n2 + 13, n3 + 18 + 23 * (n4 % 5), 3);
            }
            n4 = n3 + 14;
            graphics.setColor(0xFFFFFF);
            bh.a(graphics, n2 + 33, n4, 151, 1, this.a);
            graphics.setColor(14663551);
            bh.a(graphics, n2 + 33, n4 += bh.a(this.a, 151) * 15, n.a[n.a - 6][this.c] ? ai.a.a(55) : ai.a.a(56), 1);
            bh.a(graphics, n2 + 33, n4 += 15, ai.a.a(57), 1);
            n4 += 15;
            if (ce.f.a(this.c * 7 + 4).length > 0) {
                graphics.setColor(0xFFFFFF);
                bh.a(graphics, n2 + 33, n4, ce.f.a(this.c * 7 + 4), 1);
                n4 += 15;
            }
            if (ce.f.a(this.c * 7 + 5).length > 0) {
                graphics.setColor(0xFFFFFF);
                bh.a(graphics, n2 + 33, n4, ce.f.a(this.c * 7 + 5), 1);
                return;
            }
        } else {
            if (r.h > 160) {
                cb.a(graphics, n2 + 4, n3 + 10, 189, 211, 0x3F1F3F, 10452799, 0x3F3F3F);
                cb.a(graphics, n2 + 4, n3 + 10, 189, 211, 0x5F3F3F);
            } else {
                cb.a(graphics, n2 + 4, n3 + 10, 189, r.h - (n3 + 10) - 8, 0x3F1F3F, 10452799, 0x3F3F3F);
                cb.a(graphics, n2 + 4, n3 + 10, 189, r.h - (n3 + 10) - 8, 0x5F3F3F);
            }
            graphics.setColor(0xFFFFFF);
            bh.a(graphics, n2 + 10, n3 + 15, 96, 1, ai.a.a(58));
        }
    }
}

