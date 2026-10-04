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
public final class bo
extends cb {
    private char[] a;
    private Object[] a;
    private char[] b;
    private int a;
    private byte c;

    public bo(cb cb2, char[] cArray, Object[] objectArray, char[] cArray2, int n2, byte by2) {
        super(cb2, (byte)0);
        this.a = cArray;
        this.c = by2;
        this.a = objectArray;
        this.b = cArray2;
        this.a = n2;
    }

    public final boolean a(int n2, int n3) {
        if (this.b(n2, n3)) {
            return true;
        }
        if (n3 == bh.a) {
            ((cb)this).a.a();
            return true;
        }
        if (n3 == 53 || n2 == 8) {
            ((cb)this).a.a(this.c, (byte)0);
            return true;
        }
        return true;
    }

    public final void a(Graphics graphics, int n2, int n3) {
        boolean bl2 = false;
        n2 = r.i - 90;
        n3 = r.j - 60;
        ao ao2 = n.a();
        cb.c(graphics, n2, n3, 181, 120);
        cb.b(graphics, n2 + 3, n3 + 3, 175, 17, 0x9F7F7F);
        graphics.setColor(0xFFFFFF);
        bh.a(graphics, n2 + 6, n3 + 4, this.a, 1);
        cb.b(graphics, n2 + 3, n3 + 25, 175, 60, 0x9F7F7F);
        graphics.setColor(0xFFFFFF);
        for (int i2 = 0; i2 < this.a.length; ++i2) {
            if (this.a[i2] == null) continue;
            bh.a(graphics, n2 + 6, n3 + 27 + i2 * 18, (char[])this.a[i2], 1);
        }
        cb.a(graphics, n2 + 181 - 5, n3 + 90, ao2.a.a);
        cb.b(graphics, n2 + 3, n3 + 98, 175, 15, 0x9F7F7F);
        graphics.setColor(0xFFFFFF);
        bh.a(graphics, n2 + 6, n3 + 99, this.b, 1);
        cb.a(graphics, n2 + 181 - 5, n3 + 105, this.a);
    }
}

