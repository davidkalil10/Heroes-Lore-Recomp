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
public final class bx
extends cb {
    private char[] a;
    private char[] b;
    private boolean c;
    private short[] a;

    public bx(bt bt2, char[] cArray, boolean bl2, char[] cArray2) {
        super(bt2, (byte)1);
        this.a = cArray;
        this.b = cArray2;
        this.c = bl2;
        short[] sArray = new short[20];
        int n2 = 0;
        for (int i2 = 0; i2 < cArray.length; i2 += bh.a(cArray, i2, 176, 18)) {
            sArray[n2++] = (short)i2;
        }
        this.a = new short[n2];
        System.arraycopy(sArray, 0, this.a, 0, this.a.length);
        ((cb)this).a = (byte)this.a.length;
    }

    public final boolean a(int n2, int n3) {
        if (this.b(n2, n3)) {
            return true;
        }
        if (this.c(n2, n3)) {
            return true;
        }
        if (n3 == bh.a) {
            ((cb)this).a.a((byte)-1, (byte)-1);
            return true;
        }
        return true;
    }

    public final void a(Graphics graphics, int n2, int n3) {
        short s2;
        boolean bl2 = false;
        if (this.c) {
            cb.b(graphics, (n2 += 2) + 4, (n3 += 15) + 10, 189, 213);
            n2 += 8;
            n3 += 25;
            graphics.setColor(0xFFFFFF);
            bh.a(graphics, bh.m, bh.e);
        } else {
            graphics.setColor(0x3F1F3F);
            graphics.fillRect(0, 0, r.g, r.h);
            bf.c(graphics, n2, n3);
            bh.a(true);
            graphics.setColor(0);
            bh.a(graphics, n2 + 201 >> 1, n3 + 5 + 4, this.b, 1);
            bh.a(false);
            bf.b(graphics, n2, n3 + 24, 3);
            n2 += 10;
            n3 += 43;
            graphics.setColor(0x5F3F3F);
            bh.a(graphics, null, bh.e);
        }
        r.d(graphics, n2 + 201 - 25, n3 - 8, ((cb)this).b + 1, ((cb)this).a);
        if (((cb)this).a > 1) {
            if (((cb)this).b > 0) {
                graphics.drawImage(ce.l, n2 + 62 + 13, n3 - 6, 20);
            }
            if (((cb)this).b < ((cb)this).a - 1) {
                graphics.drawImage(ce.o, n2 + 62 + 13, n3 + 114 + 26 + 40, 20);
            }
        }
        short s3 = this.a[((cb)this).b];
        short s4 = s2 = ((cb)this).b == ((cb)this).a - 1 ? (short)this.a.length : this.a[((cb)this).b + 1];
        if (this.a[0] == '!' && s3 == 0) {
            s3 = 1;
        }
        if (this.c) {
            graphics.setColor(0xFFFFFF);
        } else {
            graphics.setColor(0);
        }
        bh.a(graphics, n2, (n3 -= 3) + 3, 176, 1, this.a, s3, 0, s2 - s3);
    }
}

