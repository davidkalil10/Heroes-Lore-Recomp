/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 */
import javax.microedition.lcdui.Graphics;

public final class ak
extends av {
    private byte v;
    private byte w = (byte)2;

    public ak(ae ae2, byte by2, byte by3, byte by4, byte by5) {
        super(by2, (byte)(by3 + 4), by4, by5, (byte)1);
        ae2.a[by3 + 4][by2] = null;
        ae2.a[by3 + 4][by2 + 1] = null;
        this.a.h = (byte)2;
        this.v = 0;
    }

    public final void d() {
        this.k = (byte)(this.k + 1);
        this.v = (byte)(this.v + 1);
        if (this.v > 100) {
            this.a.h = 0;
        }
        if (this.h == 3) {
            byte[] byArray = (byte[])ce.h[this.n * 16 + 12 + (this.j - 1)];
            if (this.k >= byArray[0]) {
                this.a(false);
                this.i();
            }
        } else {
            this.n();
        }
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
        if (this.h != 3) {
            this.j = 1;
        }
        super.a(graphics, n2, n3 -= 64);
        this.j = by2;
    }

    public final void i() {
        ao ao2 = n.a();
        int n2 = ((ck)ao2).b - (((ck)this).b - 4 + 2);
        if (this.p == 0 && n2 >= -1 && n2 <= 2 && ((ck)ao2).a >= ((ck)this).a - 7) {
            this.q();
            this.w = u.g[this.w];
            this.b(this.w);
            return;
        }
        if (this.o == 0) {
            if (n2 > 2) {
                this.a((byte)2);
                this.b((byte)2);
                return;
            }
            if (n2 < -1) {
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
        if (this.k == 5 && this.w == 1) {
            byte by2 = (byte)(((ck)this).a - 7);
            byte by3 = (byte)(((ck)this).a - 1);
            byte by4 = (byte)(((ck)this).b - 4 + 1);
            byte by5 = (byte)(((ck)this).b - 4 + 4);
            if (((ck)ao2).a >= by2 && ((ck)ao2).a <= by3 && ((ck)ao2).b >= by4 && ((ck)ao2).b <= by5) {
                ao2.a(this, (byte)3);
                return;
            }
        } else if (this.k == 8 && this.w == 2) {
            byte by6 = (byte)(((ck)this).a - 5);
            byte by7 = (byte)(((ck)this).a - 1);
            byte by8 = (byte)(((ck)this).b - 4 + 1);
            byte by9 = (byte)(((ck)this).b - 4 + 4);
            if (((ck)ao2).a >= by6 && ((ck)ao2).a <= by7 && ((ck)ao2).b >= by8 && ((ck)ao2).b <= by9) {
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

