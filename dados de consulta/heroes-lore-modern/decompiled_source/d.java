/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 */
import javax.microedition.lcdui.Graphics;

public final class d
extends cb {
    private byte c = 0;

    public d(cb cb2) {
        super(cb2, (byte)4);
    }

    public final synchronized boolean a(int n2, int n3) {
        if (this.b(n2, n3)) {
            return true;
        }
        if (this.c != 0) {
            return true;
        }
        if (this.a(n2, n3, false)) {
            return true;
        }
        if (n3 == 53 || n2 == 8) {
            switch (this.b) {
                case 0: {
                    if (w.c) {
                        this.d();
                        break;
                    }
                    ae ae2 = n.a;
                    if (ae2.a) {
                        this.a(new Object[]{ai.a.a(51), ai.a.a(52)});
                        break;
                    }
                    this.c = (byte)2;
                    this.b();
                    break;
                }
                case 1: {
                    this.b = new bt((cb)this, true);
                    break;
                }
                case 2: {
                    this.b = new be((cb)this, true);
                    break;
                }
                case 3: {
                    this.d();
                }
            }
            return true;
        }
        return false;
    }

    public final void d() {
        if (w.c) {
            Object[] objectArray = new Object[]{bh.a(3919).toCharArray()};
            this.a((byte)12, (byte)2, objectArray, bh.j, bh.c);
            return;
        }
        Object[] objectArray = new Object[]{bh.a};
        this.a((byte)2, (byte)2, objectArray);
    }

    public final void a(byte by2, byte by3) {
        super.a(by2, by3);
        if (by2 == 12 || by2 == 2) {
            if (w.c) {
                if (by3 == 0) {
                    bh.a(w.a);
                    return;
                }
                n.a((byte)14, (byte)1);
                bw.f();
                bf.d = true;
                return;
            }
            if (by3 == 0) {
                n.a((byte)14, (byte)1);
                bw.f();
            }
        }
    }

    public final void a(Graphics graphics, int n2, int n3) {
        n3 += 15;
        if (r.h > 128) {
            r.a(graphics, ai.a.a(41), n2 + 5, n3);
        }
        boolean bl2 = false;
        n2 = r.g - 108 >> 1;
        cb.a(graphics, n2, n3 + 15, 108, ai.a.a(42), this.b == 0);
        cb.a(graphics, n2, n3 + 37, 108, ai.a.a(43), this.b == 1);
        cb.a(graphics, n2, n3 + 59, 108, ai.a.a(44), this.b == 2);
        cb.a(graphics, n2, n3 + 81, 108, ai.a.a(45), this.b == 3);
        if (this.c == 2) {
            this.c = 1;
            int n4 = r.i - 55;
            int n5 = r.j - 11;
            cb.a(graphics, n4, n5, 110, 22);
            cb.b(graphics, n4, n5, 110, 22);
            graphics.setColor(0xFFFFFF);
            bh.a(graphics, n4 + 5, n5 + 5, ai.a.a(53), 1);
            this.b();
            return;
        }
        if (this.c == 1) {
            this.c = 0;
            try {
                n.o();
                this.a(new Object[]{ai.a.a(46)});
                return;
            }
            catch (Exception exception) {
                this.a(new Object[]{ai.a.a(47), ai.a.a(48)});
            }
        }
    }
}

