/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 */
import javax.microedition.lcdui.Graphics;

public final class am
extends cb {
    private char[] a;
    private char[] b;
    private byte d;
    public byte c;

    public am(cb cb2, char[] cArray, char[] cArray2, byte by2) {
        super(cb2, (byte)0);
        this.a = cArray;
        this.b = cArray2;
        this.c = by2;
        this.d = 0;
        this.d = (byte)(this.d + bh.a(cArray, 181));
        this.d = (byte)(this.d + bh.a(cArray2, 181));
    }

    public final boolean a(int n2, int n3) {
        if (this.b(n2, n3)) {
            return true;
        }
        if (n3 == 53 || n2 == 8) {
            ((cb)this).a.a(this.c, (byte)1);
            return true;
        }
        if (n3 == bh.a) {
            ((cb)this).a.a(this.c, (byte)0);
            return true;
        }
        return true;
    }

    public final void a(Graphics graphics, int n2, int n3) {
        int n4 = this.d * 15 + 10;
        n2 = r.i - 95;
        n3 = r.j - n4 / 2;
        cb.a(graphics, n2, n3, 191, n4);
        cb.b(graphics, n2, n3, 191, n4);
        int n5 = n3 + 5;
        graphics.setColor(14663551);
        bh.a(graphics, n2 + 5, n5, 181, 1, this.a);
        graphics.setColor(0xFFFFFF);
        bh.a(graphics, n2 + 5, n5 += 15 * bh.a(this.a, 181), 181, 1, this.b);
    }
}

