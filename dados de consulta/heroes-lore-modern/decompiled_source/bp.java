/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 */
import java.io.IOException;
import java.util.Vector;
import javax.microedition.lcdui.Graphics;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public final class bp
extends cb {
    private Vector[] a = ad.a();
    public static int a;
    public static int b;
    public static z a;
    private static bp a;

    public static final bp a() {
        if (a == null) {
            a = new bp();
            a = r.i - 100;
            b = r.j - 122;
        }
        return a;
    }

    private bp() {
        super(null, (byte)6);
        ((cb)this).b = new v(this, this.a[((cb)this).b], ((cb)this).b);
    }

    public final void d() {
        try {
            a = new z("/sgui/shop");
            return;
        }
        catch (IOException iOException) {
            IOException iOException2 = iOException;
            iOException.printStackTrace();
            return;
        }
    }

    private void e() {
        a = null;
        a = null;
        this.a = null;
        ((cb)this).b = null;
        n.a(2);
        bs.a.b();
        System.gc();
    }

    public final boolean a(int n2, int n3) {
        if (this.b(n2, n3)) {
            return true;
        }
        if (this.d(n2, n3)) {
            return true;
        }
        if (n3 == bh.a) {
            this.e();
            return true;
        }
        return false;
    }

    public final void a(byte by2) {
        super.a(by2);
        ((cb)this).b = new v(this, this.a[((cb)this).b], ((cb)this).b);
    }

    public final void a(Graphics graphics) {
        this.b(graphics, a, b);
    }

    public final void a(Graphics graphics, int n2, int n3) {
        bh.a(graphics);
        bh.a(graphics, bh.d, bh.e);
        graphics.setColor(0x3F1F3F);
        graphics.fillRect(n2, n3, 201, 244);
        cb.c(graphics, n2 + 2, n3 + 15, 197, 229);
        graphics.setColor(0xFFDFBF);
        graphics.fillRect(n2 + 11 + ((cb)this).b * 16 + 1, n3, 14, 1);
        graphics.fillRect(n2 + 11 + ((cb)this).b * 16, n3 + 1, 1, 16);
        graphics.setColor(12558207);
        graphics.fillRect(n2 + 11 + ((cb)this).b * 16 + 15, n3 + 1, 1, 15);
        graphics.setColor(14663551);
        graphics.fillRect(n2 + 11 + ((cb)this).b * 16 + 1, n3 + 1, 14, 16);
        for (int i2 = 0; i2 < 6; ++i2) {
            graphics.drawImage(ce.o[i2], n2 + 13 + i2 * 16, n3 + 1, 20);
        }
        r.a(graphics, a.a(((cb)this).b + 1), n2 + 3, n3 + 15);
        graphics.drawImage(ce.p, n2 + 4, n3 + 4, 20);
        graphics.drawImage(ce.e, n2 + 109, n3 + 4, 20);
    }
}

