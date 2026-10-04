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
public final class bj
extends f {
    private byte a;
    private byte b;
    private short c;
    private Image[] a;
    private Image[] b;
    private Object[] a;

    public bj(short s2, short s3, byte by2, byte by3) {
        super(s3);
        this.c = s2;
        this.b = by2;
        this.a = by3;
        this.a = new Image[2];
        Image[] imageArray = ce.a[12];
        this.a[0] = imageArray[0];
        this.a[1] = imageArray[1];
        this.b = ce.a[12];
        this.a = ce.d;
    }

    public final void a(Graphics graphics, int n2, int n3) {
        if (this.c > 0) {
            this.c = (short)(this.c - 1);
            return;
        }
        if (this.b == 0 && this.a == 0) {
            switch (((f)this).b) {
                case 0: {
                    graphics.drawImage(this.b[7], n2, n3, 33);
                    break;
                }
                case 1: {
                    graphics.drawImage(this.b[8], n2, n3 - 1, 33);
                    break;
                }
                case 2: {
                    graphics.drawImage(this.b[7], n2, n3 - 2, 33);
                    break;
                }
                case 3: {
                    graphics.drawImage(this.b[8], n2, n3 - 3, 33);
                    break;
                }
                case 4: 
                case 5: {
                    graphics.drawImage(this.b[9], n2, n3 - 4, 33);
                }
            }
        } else if (this.b == 0 && this.a == 2 || this.b == 1 && this.a == 2) {
            switch (((f)this).b % 4) {
                case 1: {
                    graphics.drawImage(this.a[0], n2, n3 + 9, 33);
                    break;
                }
                case 2: {
                    graphics.drawImage(this.a[0], n2, n3 + 9, 33);
                    graphics.drawImage(this.a[1], n2, n3 + 12, 33);
                    break;
                }
                case 3: {
                    graphics.drawImage(this.a[1], n2, n3 + 12, 33);
                }
            }
        } else {
            as.b(graphics, (byte[])this.a[this.a], (byte)((f)this).b, n2, n3);
        }
        ((f)this).b = (short)(((f)this).b + 1);
        if (((f)this).b >= ((f)this).a) {
            ((f)this).a = true;
        }
    }
}

