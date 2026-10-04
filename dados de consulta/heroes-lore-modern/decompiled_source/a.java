/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 */
import javax.microedition.lcdui.Graphics;

public final class a
extends cb {
    private byte[] h;
    private byte c;

    public a(bf bf2, byte[] byArray) {
        super(bf2, (byte)(byArray.length / 4));
        this.h = byArray;
        this.c = 0;
    }

    public final boolean a(int n2, int n3) {
        if (this.b(n2, n3)) {
            return true;
        }
        if (this.a > 1 && this.c(n2, n3)) {
            this.c = 0;
            return true;
        }
        if (n3 == 53 || n2 == 8) {
            n.a(true, this.h[this.b * 4], null);
            return true;
        }
        if (n3 == bh.a) {
            this.a.a((byte)-1, (byte)-1);
            return true;
        }
        return true;
    }

    public final void a(Graphics graphics, int n2, int n3) {
        byte by2;
        graphics.setColor(0x3F1F3F);
        graphics.fillRect(0, 0, r.g, r.h);
        bf.c(graphics, n2, n3);
        bh.a(graphics, 3, r.g >> 1, n3 + 5);
        bf.b(graphics, n2, n3 + 24, 3);
        n3 += 30;
        n2 += 35;
        for (by2 = 0; by2 < 5; ++by2) {
            graphics.drawImage(ce.k[19], n2 + 13, n3 + 49 + by2 * 16, 20);
        }
        switch (this.c) {
            case 0: {
                graphics.drawImage(ce.k[14], n2 + 5, n3 + 31 + this.b * 16, 20);
                break;
            }
            case 1: {
                graphics.drawImage(ce.k[16], n2 + 5, n3 + 31 + this.b * 16, 20);
                break;
            }
            default: {
                graphics.drawImage(ce.k[18], n2 + 5, n3 + 31 + this.b * 16, 20);
            }
        }
        if (this.c < 2) {
            this.a = true;
            this.c = (byte)(this.c + 1);
        }
        for (by2 = 0; by2 < this.a; by2 = (byte)(by2 + 1)) {
            if (this.b == by2) {
                graphics.setColor(0xFFFFFF);
            } else {
                graphics.setColor(0x9F7F7F);
            }
            bh.a(graphics, n2 + 21, n3 + 36 + by2 * 16, ce.a.a(this.h[by2 * 4] - 6), 1);
        }
        graphics.drawImage(ce.h, n2 + 15, n3 + 104, 20);
        r.c(graphics, this.h[this.b * 4 + 1], n2 + 30, n3 + 104, 4);
        graphics.setColor(0x7F5F5F);
        bh.a(graphics, n2 + 15, n3 + 117, (bh.b + this.h[this.b * 4 + 2] + "%").toCharArray(), 1);
        if (r.g > 128) {
            graphics.drawImage(ce.l[this.h[this.b * 4] - 6], n2 + 61 + 22, n3 + 74 + 15, 20);
        } else {
            graphics.drawImage(ce.l[this.h[this.b * 4] - 6], n2 + 61, n3 + 74, 20);
        }
        bh.a(graphics, bh.d, bh.e);
    }
}

