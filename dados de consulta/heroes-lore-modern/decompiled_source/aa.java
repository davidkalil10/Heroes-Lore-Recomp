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
public final class aa
extends cb {
    public static int a;
    public static int b;
    public static z a;
    private static aa a;

    public static final aa a() {
        if (a == null) {
            a = new aa();
            a = r.i - 100;
            b = r.j - 122;
        }
        return a;
    }

    public aa() {
        super(null, (byte)0);
    }

    public final void d() {
        try {
            a = new z("/sgui/blak");
        }
        catch (IOException iOException) {}
        this.a((byte)8, (byte)2, new Object[]{a.a(0), a.a(1), a.a(2)});
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
            ((cb)this).b = new at(this);
            ((cb)this).b.a(new Object[]{a.a(30), a.a(31), a.a(32), a.a(33)});
            return;
        }
        if (by2 == 8 && by3 == 1) {
            ((cb)this).b = new ch(this);
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

