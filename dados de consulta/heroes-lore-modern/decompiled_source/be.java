/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 */
import javax.microedition.lcdui.Graphics;

public final class be
extends cb {
    private boolean c;
    private bs a;

    public be(cb cb2, boolean bl2) {
        super(cb2, (byte)4);
        this.c = bl2;
        this.a = bs.a;
    }

    public final boolean a(int n2, int n3) {
        if (this.b(n2, n3)) {
            return true;
        }
        if (this.a(n2, n3, false)) {
            return true;
        }
        if (n3 == 52 || n2 == 2 || n3 == 54 || n2 == 5) {
            switch (this.b) {
                case 0: {
                    if (n3 == 52 || n2 == 2 || n3 == 54 || n2 == 5) {
                        this.a.a = this.a.a == 0 ? bw.a : 0;
                    }
                    bw.a(this.a.a);
                    if (this.a.a != 0) break;
                    bw.d();
                    break;
                }
                case 1: {
                    if (n3 == 52 || n2 == 2) {
                        this.a.a = (byte)(this.a.a - 1);
                        if (this.a.a < 0) {
                            this.a.a = (byte)2;
                        }
                    }
                    if (n3 == 54 || n2 == 5) {
                        this.a.a = (byte)(this.a.a + 1);
                        if (this.a.a > 2) {
                            this.a.a = 0;
                        }
                    }
                    this.a.a(this.a.a);
                    break;
                }
                case 2: {
                    this.a.c = !this.a.c;
                    break;
                }
                case 3: {
                    boolean bl2 = this.a.d = !this.a.d;
                }
            }
        }
        if (n3 == bh.a) {
            if (this.a.d) {
                n.c = n.a;
                n.d = n.b;
            }
            try {
                bs.a.i();
            }
            catch (Exception exception) {
                Exception exception2 = exception;
                exception.printStackTrace();
            }
            ((cb)this).a.a();
            return true;
        }
        return true;
    }

    public final void a(Graphics graphics, int n2, int n3) {
        int n4 = 0;
        int n5 = 0;
        if (this.c) {
            cb.a(graphics, n2 += 6, n3 += 25, 189, 213);
            cb.b(graphics, n2, n3, 189, 213);
            n4 = 10452799;
            n5 = 0xFFFFFF;
            n2 += 5;
            n3 += 15;
            bh.a(graphics, bh.m, bh.e);
            n2 += 40;
            n3 += 35;
        } else {
            graphics.setColor(0x3F1F3F);
            graphics.fillRect(0, 0, r.g, r.h);
            bf.c(graphics, n2, n3);
            bh.a(graphics, 5, n2 + 201 >> 1, n3 + 5);
            bf.b(graphics, n2, n3 + 24, 3);
            n2 += 40;
            n3 += 35;
            graphics.drawImage(ce.k[19], (n2 += 12) + 1, (n3 += 46) + 16, 20);
            graphics.drawImage(ce.k[19], n2 + 1, n3 + 36, 20);
            graphics.drawImage(ce.k[19], n2 + 1, n3 + 56, 20);
            bh.a(graphics, null, bh.e);
        }
        int n6 = n3;
        byte by2 = this.b;
        graphics.setColor(by2 == 0 ? 0xFFFFFF : n4);
        bh.a(graphics, n2, n6, ce.g.a(18), 1);
        graphics.setColor(n5);
        if (this.a.a == 0) {
            bh.a(graphics, n2 + 70, n6, cj.a.a(3945).toCharArray(), 0);
        } else {
            bh.a(graphics, n2 + 70, n6, cj.a.a(3944).toCharArray(), 0);
        }
        graphics.setColor(by2 == 1 ? 0xFFFFFF : n4);
        bh.a(graphics, n2, n6 += 20, ce.g.a(19), 1);
        graphics.setColor(n5);
        char[] cArray = ce.g.a(60 + this.a.a);
        bh.a(graphics, n2 + 70, n6, cArray, 0);
        graphics.setColor(by2 == 2 ? 0xFFFFFF : n4);
        bh.a(graphics, n2, n6 += 20, ce.g.a(20), 1);
        graphics.setColor(n5);
        bh.a(graphics, n2 + 70, n6, (this.a.c ? cj.a.a(3942) : cj.a.a(3943)).toCharArray(), 0);
        graphics.setColor(by2 == 3 ? 0xFFFFFF : n4);
        bh.a(graphics, n2, n6 += 20, ce.g.a(21), 1);
        graphics.setColor(n5);
        bh.a(graphics, n2 + 70, n6, (this.a.d ? cj.a.a(3944) : cj.a.a(3945)).toCharArray(), 0);
        for (byte by3 = 0; by3 < ((cb)this).a; by3 = (byte)(by3 + 1)) {
            graphics.drawImage(ce.p, n2 + 42, n3 + by3 * 20, 20);
            graphics.drawImage(ce.e, n2 + 92, n3 + by3 * 20, 20);
        }
    }
}

