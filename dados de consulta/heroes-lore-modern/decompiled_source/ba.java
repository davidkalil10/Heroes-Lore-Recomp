/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 */
import javax.microedition.lcdui.Graphics;

public final class ba
extends av {
    private byte v;

    public ba(ae ae2, byte by2, byte by3, byte by4, byte by5) {
        super(by2, (byte)(by3 + 5), by4, by5, (byte)2);
        ae2.a[by3 + 5][by2] = null;
        ae2.a[by3 + 5][by2 + 1] = null;
        this.a.h = (byte)2;
        this.v = 0;
    }

    public final void d() {
        this.k = (byte)(this.k + 1);
        this.v = (byte)(this.v + 1);
        if (this.v > 100) {
            this.a.h = 0;
        }
        this.n();
        if (this.h == 2) {
            switch (this.i) {
                case 1: {
                    ((ck)this).d = (short)(((ck)this).d - 8);
                    break;
                }
                case 2: {
                    ((ck)this).d = (short)(((ck)this).d + 8);
                }
            }
            this.b();
        }
        this.o();
    }

    public final void a(Graphics graphics, int n2, int n3) {
        byte by2 = this.j;
        this.j = 1;
        super.a(graphics, n2, n3 -= 80);
        this.j = by2;
    }

    public final void i() {
        ao ao2 = n.a();
        int n2 = ((ck)ao2).b - (((ck)this).b - 5 + 3);
        if (this.p == 0 && n2 >= -2 && n2 <= 3 && ((ck)ao2).a <= ((ck)this).a + 5) {
            this.q();
            return;
        }
        if (this.o == 0) {
            if (n2 > 3) {
                this.a((byte)2);
                this.b((byte)2);
                return;
            }
            if (n2 < -2) {
                this.a((byte)2);
                this.b((byte)1);
                return;
            }
            this.a((byte)1);
            this.b((byte)2);
        }
    }

    public final void j() {
        ao ao2 = n.a();
        if (this.k == 6) {
            n.a.e = 2;
            n.a.f = 3;
        } else if (this.k == 7) {
            n.a.e = -3;
            n.a.f = -1;
        } else if (this.k == 8) {
            n.a.e = 2;
            n.a.f = -3;
        }
        if (this.k == 5) {
            byte by2 = (byte)(((ck)this).a + 2);
            byte by3 = (byte)(((ck)this).a + 5);
            byte by4 = (byte)(((ck)this).b - 5 + 1);
            byte by5 = (byte)(((ck)this).b - 5 + 6);
            if (((ck)ao2).a >= by2 && ((ck)ao2).a <= by3 && ((ck)ao2).b >= by4 && ((ck)ao2).b <= by5) {
                ao2.a(this, (byte)2);
                this.v = 0;
                this.a.h = (byte)2;
            }
        }
    }

    public final void m() {
        this.q = 0;
    }
}

