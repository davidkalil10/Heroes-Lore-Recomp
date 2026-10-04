/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 */
import java.io.IOException;
import javax.microedition.lcdui.Graphics;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public final class ax
extends cb {
    public static int a;
    public static int b;
    public static z a;
    private static ax a;

    public static final ax a() {
        if (a == null) {
            a = new ax();
            a = r.i - 100;
            b = r.j - 122;
        }
        return a;
    }

    public ax() {
        super(null, (byte)0);
    }

    public final void d() {
        Object[] objectArray;
        try {
            a = new z("/sgui/refi");
        }
        catch (IOException objectArray2) {
            objectArray = objectArray2;
            objectArray2.printStackTrace();
        }
        objectArray = new Object[]{a.a(0), a.a(1), a.a(2)};
        this.a((byte)8, (byte)2, objectArray);
    }

    public final void e() {
        a = null;
        a = null;
        ((cb)this).b = null;
        n.a(2);
        bs.a.b();
        System.gc();
    }

    public final boolean a(int n2, int n3) {
        if (this.b(n2, n3)) {
            return true;
        }
        if (n3 == bh.a) {
            this.e();
        }
        return false;
    }

    public final void a(byte by2, byte by3) {
        super.a(by2, by3);
        if (by2 == 8 && by3 == 0) {
            ((cb)this).b = new ap(this);
            return;
        }
        if (by2 == 8 && by3 == 1) {
            ((cb)this).b = new k(this);
            return;
        }
        this.e();
    }

    public final void a(Graphics graphics) {
        this.b(graphics, a, b);
    }

    public final void a(Graphics graphics, int n2, int n3) {
        bh.a(graphics);
        bh.a(graphics, bh.d, bh.e);
    }
}

