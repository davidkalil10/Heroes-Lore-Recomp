/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 */
import javax.microedition.lcdui.Graphics;

public class m
extends cb {
    public byte[] h;
    public byte c;
    private char[] a;

    public m(cb cb2, byte[] byArray, byte by2, char[] cArray) {
        super(cb2, (byte)byArray.length);
        this.h = byArray;
        this.c = by2;
        this.a = cArray;
    }

    public boolean a(int n2, int n3) {
        if (this.b(n2, n3)) {
            return true;
        }
        if (this.c(n2, n3)) {
            return true;
        }
        if (n3 == 53 || n2 == 8) {
            ((cb)this).a.a(this.c, this.h[this.b]);
            return true;
        }
        if (n3 == bh.a) {
            ((cb)this).a.a((byte)-1, (byte)-1);
            return true;
        }
        return true;
    }

    public void a(Graphics graphics, int n2, int n3) {
        ad ad2;
        ao ao2 = n.a();
        n3 -= 3;
        cb.c(graphics, n2 += 2, (n3 += 14) - 14, 197, 244);
        boolean bl2 = this.b() > 1;
        cb.b(graphics, n2 + 3, n3 - 13 + (bl2 ? 0 : 3), 191, 14, 0x9F7F7F);
        graphics.setColor(0xFFFFFF);
        bh.a(graphics, n2 + 6, n3 - 10 + (bl2 ? 0 : 3), this.a, 1);
        this.a(graphics, n2, n3, bl2);
        for (int i2 = this.c(); i2 <= this.d(); ++i2) {
            ad2 = this.h[i2] >= 100 ? ao2.a(this.h[i2] - 100) : (this.h[i2] < 0 ? ao2.b.a(-this.h[i2] - 1) : ao2.a.a((int)this.h[i2]));
            if (ad2 == null) continue;
            cb.a(graphics, n2 + 13, n3 + 18 + 23 * (i2 % 5), ad2, true);
        }
        ad2 = this.h[this.b] >= 100 ? ao2.a(this.h[this.b] - 100) : (this.h[this.b] < 0 ? ao2.b.a(-this.h[this.b] - 1) : ao2.a.a((int)this.h[this.b]));
        if (ad2 != null) {
            cb.a(graphics, n2 + 33, n3 + 14, ad2);
        }
    }
}

