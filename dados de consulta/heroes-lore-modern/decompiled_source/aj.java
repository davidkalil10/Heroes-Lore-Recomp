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
public final class aj
extends ck {
    public Image a;
    private short a;
    private short b;
    private short e;

    public aj(short s2, short s3, byte by2, byte by3, Image image) {
        super(s2, s3, by2, by3);
        this.a = image;
        if (image != null) {
            this.a = (short)(-(image.getWidth() >> 1));
            this.b = (short)(as.a + (image.getWidth() >> 1));
            this.e = (short)(as.b + image.getHeight());
        }
    }

    public final void a(Graphics graphics, int n2, int n3) {
        int n4 = n2 + this.c + this.c;
        int n5 = n3 + this.d + this.d;
        if (n4 < this.a || n4 > this.b || n5 < 0 || n5 > this.e) {
            return;
        }
        graphics.drawImage(this.a, n4, n5, 33);
    }
}

