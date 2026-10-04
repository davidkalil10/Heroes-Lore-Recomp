/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 */
import javax.microedition.lcdui.Graphics;

public final class bm
extends cb {
    public bm(cb cb2) {
        super(cb2, (byte)5);
        ao ao2 = n.a();
        for (int n2 = 0; n2 < 5; n2 = (int)((byte)(n2 + 1))) {
            if (ao2.a[n2] != ao2.a()) continue;
            this.b = (byte)n2;
            return;
        }
    }

    public final boolean a(int n2, int n3) {
        if (this.b(n2, n3)) {
            return true;
        }
        if (this.c(n2, n3)) {
            return true;
        }
        ao ao2 = n.a();
        if ((n3 == 53 || n2 == 8) && ao2.a[this.b] != null) {
            Object[] objectArray = new Object[]{ai.a.a(22), ai.a.a(23)};
            this.a((byte)3, (byte)2, objectArray);
        }
        return false;
    }

    public final void a(byte by2, byte by3) {
        cb cb2 = this.b;
        super.a(by2, by3);
        ao ao2 = n.a();
        if (cb2 instanceof af && by2 == 3) {
            switch (by3) {
                case 0: {
                    if (ao2.a(ao2.a[this.b])) {
                        char[] cArray = (cj.a.a(3933) + " " + new String(ce.b.a(ao2.a[this.b].f))).toCharArray();
                        Object[] objectArray = new Object[]{cArray};
                        this.a(objectArray);
                        return;
                    }
                    Object[] objectArray = new Object[]{ai.a.a(27), ai.a.a(28), ai.a.a(29)};
                    this.a(objectArray);
                    return;
                }
                case 1: {
                    this.b = new bn(this, ao2.a[this.b]);
                }
            }
        }
    }

    public final void a(Graphics graphics, int n2, int n3) {
        n2 += 2;
        n3 += 15;
        ao ao2 = n.a();
        if (r.h > 128) {
            r.a(graphics, ai.a.a(30), n2 + 5, n3);
        }
        this.a(graphics, n2, n3, false);
        for (int i2 = 0; i2 < 5; ++i2) {
            if (ao2.a[i2] == null) continue;
            graphics.drawImage(ce.b[ao2.a[i2].f], n2 + 15, n3 + 19 + i2 % 5 * 23, 3);
        }
        if (ao2.a[this.b] == null) {
            graphics.setColor(14663551);
            bh.a(graphics, n2 + 34, n3 + 18, ai.a.a(31), 1);
            return;
        }
        p p2 = ao2.a[this.b];
        r.a(graphics, ai.a.a(32), n2 + 89, n3 + 22);
        if (p2 == ao2.a()) {
            graphics.drawImage(ce.m, n2 + 100, n3 + 26, 36);
        }
        graphics.setColor(0xFFFFFF);
        bh.a(graphics, n2 + 34, n3 + 18, ce.b.a(ao2.a[this.b].f), 1);
        graphics.setColor(14663551);
        bh.a(graphics, n2 + 34, n3 + 35, ce.b.a(ao2.a[this.b].f + 6), 1);
        graphics.drawImage(ce.h, n2 + 34, n3 + 53, 20);
        r.c(graphics, p2.a, n2 + 50, n3 + 53, 4);
        graphics.drawImage(ce.f, n2 + 34, n3 + 67, 20);
        r.c(graphics, p2.a, n2 + 102, n3 + 67, 8);
        graphics.setColor(0x3F1F3F);
        graphics.fillRect(n2 + 34, n3 + 76, 72, 3);
        graphics.setColor(0xFFFFFF);
        graphics.fillRect(n2 + 35, n3 + 77, p2.a * 70 / p2.b, 1);
        graphics.drawImage(ce.i, n2 + 38, n3 + 81, 20);
        r.c(graphics, p2.b, n2 + 102, n3 + 81, 8);
    }
}

