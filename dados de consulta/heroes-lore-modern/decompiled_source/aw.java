/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 *  javax.microedition.lcdui.Image
 */
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public final class aw
extends f {
    public static final byte[] h = new byte[]{-1, 3, 4, 11, 9, 3, 3, -1, 8, -1, -1};
    private Image[] a;
    private byte[] i;
    private byte a;
    private short c;

    public aw(byte by2) {
        this(by2, h[by2], 0);
    }

    public aw(byte by2, short s2, short s3) {
        super(s2);
        this.a = by2;
        this.c = s3;
        this.a();
    }

    private final void a() {
        switch (this.a) {
            case 1: {
                this.a = ce.r;
                return;
            }
            case 5: {
                this.a = ce.s;
                return;
            }
            case 6: {
                this.a = ce.t;
                return;
            }
            case 4: {
                this.i = ce.h;
                return;
            }
            case 9: {
                this.i = (byte[])ce.f[this.c];
                ((f)this).a = this.i[0];
                return;
            }
            case 10: {
                this.a = ce.v;
            }
        }
    }

    public final void a(Graphics graphics, int n2, int n3) {
        switch (this.a) {
            case 2: {
                graphics.drawImage(ce.u, n2, n3 - 30 - this.b * 4, 17);
                break;
            }
            case 3: {
                if (this.b % 4 >= 3) break;
                graphics.drawImage(ce.t, n2, n3 + 5, 33);
                break;
            }
            case 4: 
            case 9: {
                as.b(graphics, this.i, (byte)this.b, n2, n3);
                break;
            }
            case 7: {
                int n4 = this.b < 2 ? (this.c < 0 ? 4 : 3) : (this.c < 0 ? 2 : 1);
                r.a(graphics, this.c < 0 ? -this.c : this.c, n2 + 1, n3 - 30 - this.b * 4, 1, n4);
                break;
            }
            case 1: {
                if (this.b == 0) {
                    n3 -= 10;
                    n2 -= 3;
                } else if (this.b == 1) {
                    n3 -= 8;
                }
                graphics.drawImage(this.a[this.b], n2, n3 + 3, 33);
                break;
            }
            case 5: {
                if (this.b == 2) {
                    n3 -= 5;
                }
                graphics.drawImage(this.a[this.b], n2, n3 + 3, 33);
                break;
            }
            case 6: {
                if (this.b == 1) {
                    n3 -= 2;
                } else if (this.b == 2) {
                    n3 -= 6;
                }
                graphics.drawImage(this.a[this.b], n2, n3 + 3, 33);
                break;
            }
            case 10: {
                if (this.c != 8 && this.c != 9) {
                    graphics.drawImage(ce.E, n2, n3 - 40, 17);
                }
                graphics.drawImage(this.a[this.c], n2, n3 - 39 + this.b % 2, 17);
                break;
            }
            default: {
                graphics.drawImage(this.a[this.b], n2, n3 + 3, 33);
            }
        }
        this.b = (short)(this.b + 1);
        if (this.b >= ((f)this).a && ((f)this).a != -1) {
            ((f)this).a = true;
        }
    }
}

