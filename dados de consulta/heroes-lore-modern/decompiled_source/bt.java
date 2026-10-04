/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 */
import javax.microedition.lcdui.Graphics;

public final class bt
extends cb {
    private boolean c;
    private char[][] a;

    public bt(cb cb2, boolean bl2) {
        super(cb2, (byte)4);
        if (w.a() || w.b()) {
            ((cb)this).a = (byte)(((cb)this).a + 1);
        }
        this.c = bl2;
        bh.a.b = false;
        bh.b.b = false;
        bh.c.b = false;
        this.a = new char[((cb)this).a][];
        for (byte by2 = 0; by2 < ((cb)this).a; by2 = (byte)(by2 + 1)) {
            this.a[by2] = by2 == 4 ? w.a(true).toCharArray() : ce.e.a(by2);
        }
    }

    public final boolean a(int n2, int n3) {
        if (this.b(n2, n3)) {
            return true;
        }
        if (this.c(n2, n3)) {
            return true;
        }
        if (n3 == 53 || n2 == 8) {
            byte by2 = (byte)(this.b + 6);
            if (x.a && this.b == 5) {
                by2 = (byte)(by2 + 1);
            }
            char[] cArray = ce.e.a(by2);
            if (this.b == ((cb)this).a - 1) {
                if (w.a()) {
                    String string = cj.a.a(3930);
                    cArray = string.toCharArray();
                } else if (w.b()) {
                    String string = cj.a.a(3934);
                    string = bh.a(string, "XXX", new String(w.a(true)));
                    cArray = string.toCharArray();
                }
            }
            this.b = new bx(this, cArray, this.c, this.a[this.b]);
            return true;
        }
        if (n3 == bh.a) {
            ((cb)this).a.a((byte)-1, (byte)-1);
            bh.a.b = true;
            bh.b.b = true;
            bh.c.b = true;
            return true;
        }
        return true;
    }

    public final void a(Graphics graphics, int n2, int n3) {
        int n4 = 0xFFFFFF;
        int n5 = 0;
        if (this.c) {
            cb.a(graphics, n2 += 6, n3 += 25, 189, 213);
            cb.b(graphics, n2, n3, 189, 213);
            n5 = 10452799;
            n3 += 8;
        } else {
            graphics.setColor(0x3F1F3F);
            graphics.fillRect(0, 0, r.g, r.h);
            bf.c(graphics, n2, n3);
            bf.b(graphics, n2, n3 + 24, 3);
            bh.a(graphics, 7, r.g >> 1, n3 + 5);
            n3 += 41;
        }
        n3 += 35;
        bh.a(true);
        for (byte by2 = 0; by2 < ((cb)this).a; by2 = (byte)(by2 + 1)) {
            if (this.b == by2) {
                graphics.setColor(n4);
            } else {
                graphics.setColor(n5);
            }
            bh.a(graphics, r.g >> 1, n3 + by2 * 15, this.a[by2], 1);
        }
        bh.a(false);
        if (!this.c) {
            bh.a(graphics, bh.d, bh.e);
        }
    }
}

