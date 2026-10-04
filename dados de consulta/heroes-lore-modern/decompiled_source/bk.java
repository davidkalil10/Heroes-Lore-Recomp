/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 */
import javax.microedition.lcdui.Graphics;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public final class bk
extends cb {
    private boolean c;
    private boolean d;
    private byte c = false;
    private boolean[] b = new boolean[3];
    private boolean e = true;
    private byte d = false;

    public bk(by by2, byte by3) {
        super(by2, (byte)3);
        this.c = by3;
        this.a(new Object[]{ce.g.a(16), ce.g.a(13)});
    }

    public final boolean a(int n2, int n3) {
        if (this.b(n2, n3)) {
            return true;
        }
        if (!this.c) {
            if (this.d(n2, n3)) {
                this.d = 0;
                return true;
            }
            if (n3 == 53 || n2 == 8) {
                this.b[((cb)this).b] = !this.b[((cb)this).b];
                int n4 = 0;
                for (int i2 = 0; i2 < 3; ++i2) {
                    if (!this.b[i2]) continue;
                    n4 = (byte)(n4 + 1);
                }
                if (n4 == 2) {
                    this.c = true;
                    this.d = false;
                }
            }
            if (n3 == bh.a) {
                this.a.a((byte)-1, (byte)-1);
                return true;
            }
        } else {
            switch (n2) {
                case 2: 
                case 5: {
                    this.d = !this.d;
                    break;
                }
                case 8: {
                    if (this.d) {
                        this.d();
                        break;
                    }
                    this.b = new boolean[3];
                    this.c = false;
                    break;
                }
                default: {
                    if (n3 == 52 || n3 == 54) {
                        this.d = !this.d;
                        break;
                    }
                    if (n3 == bh.a) {
                        this.b = new boolean[3];
                        this.c = false;
                        break;
                    }
                    if (n3 != 53) break;
                    if (this.d) {
                        this.d();
                        break;
                    }
                    this.b = new boolean[3];
                    this.c = false;
                }
            }
        }
        return true;
    }

    public final void a(Graphics graphics, int n2, int n3) {
        graphics.setColor(0x3F1F3F);
        graphics.fillRect(0, 0, r.g, r.h);
        bf.c(graphics, n2, n3);
        bh.a(graphics, 1, n2 + 201 >> 1, n3 + 5);
        bf.b(graphics, n2, n3 + 24, 3);
        graphics.drawImage(ce.k[19], (n2 += 40) + 11, (n3 += 35) + 82, 20);
        for (byte by2 = 0; by2 < 3; by2 = (byte)(by2 + 1)) {
            if (this.b[by2]) {
                graphics.drawImage(ce.b[by2][1], n2 + 22 + by2 * 34, n3 + 66 - 5, 3);
                continue;
            }
            graphics.drawImage(ce.b[by2][0], n2 + 22 + by2 * 34, n3 + 59 - 5 + (((cb)this).b == by2 ? this.d : (byte)0), 3);
        }
        if (r.h < 160) {
            n3 -= 8;
        }
        if (!this.c) {
            graphics.drawImage(ce.k[20], n2 + 19 + ((cb)this).b * 34, n3 + 73, 20);
        }
        graphics.setColor(0);
        if (!this.c) {
            bh.a(graphics, n2 + 11, n3 + 94, ce.b.a(((cb)this).b), 1);
            bh.a(graphics, n2 + 11, n3 + 109, 100, 1, ce.b.a(12 + ((cb)this).b));
        } else {
            bh.a(graphics, n2 + 11, n3 + 104, ce.g.a(17), 1);
            if (r.g <= 128) {
                n2 -= 20;
            }
            graphics.drawImage(ce.k[17], n2 + 60 + (this.d ? 0 : 28), n3 + 118, 20);
            if (this.d) {
                graphics.setColor(0xFFFFFF);
            } else {
                graphics.setColor(0);
            }
            bh.a(graphics, n2 + 64, n3 + 121, ce.g.a(14), 1);
            if (this.d) {
                graphics.setColor(0);
            } else {
                graphics.setColor(0xFFFFFF);
            }
            bh.a(graphics, n2 + 92, n3 + 121, ce.g.a(15), 1);
        }
        if (this.d == 0) {
            this.d = (byte)(this.d + 1);
            this.e = true;
        } else if (this.d == 3) {
            this.d = (byte)(this.d - 1);
            this.e = false;
        } else {
            this.d = this.e ? (byte)(this.d + 1) : (byte)(this.d - 1);
        }
        if (((cb)this).b == null) {
            this.a = true;
        }
        bh.a(graphics, bh.d, bh.e);
    }

    private void d() {
        n.a(false, this.c, this.b);
    }
}

