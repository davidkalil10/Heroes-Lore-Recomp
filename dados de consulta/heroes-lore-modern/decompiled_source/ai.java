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
public final class ai
extends cb
implements u {
    public static int a;
    public static int b;
    private byte[] h = new byte[5];
    private byte c;
    public static z a;
    private static ai a;

    public static final ai a() {
        if (a == null) {
            a = new ai();
            ((cb)ai.a).b = new q(a);
            a = r.i - 100;
            b = r.j - 122;
        }
        return a;
    }

    private ai() {
        super(null, (byte)6);
    }

    public final void d() {
        ao ao2 = n.a();
        p p2 = ao2.a();
        for (int n2 = 0; n2 < 5; n2 = (int)((byte)(n2 + 1))) {
            this.h[n2] = -1;
        }
        if (ao2.a() != null) {
            this.h[0] = bu.a[n.a - 6][ao2.a().g];
        }
        if (ao2.a() != null) {
            this.h[1] = bu.b[ao2.a().g];
        }
        if (ao2.a() != null) {
            this.h[2] = bu.b[n.a - 6][ao2.a().g];
        }
        if (ao2.b() != null) {
            this.h[3] = bu.a[ao2.b().g];
        }
        this.c = (byte)-1;
        if (p2 != null) {
            this.c = p2.f;
        }
        try {
            a = new z("/sgui/gm");
        }
        catch (IOException iOException) {
            IOException iOException2 = iOException;
            iOException.printStackTrace();
        }
        bs.a.h();
    }

    public final void e() {
        ((cb)this).b = (byte)6;
        d d2 = new d(this);
        d2.d();
        ((cb)this).b = d2;
        ((cb)this).b.b = 1;
    }

    public final void a(boolean bl2) {
        ao ao2 = n.a();
        p p2 = ao2.a();
        a = null;
        if (!bl2) {
            return;
        }
        if (ao2.a() != null && this.h[1] != bu.b[ao2.a().g]) {
            ao2.e((byte)1);
        }
        if (ao2.a() != null && this.h[2] != bu.b[n.a - 6][ao2.a().g]) {
            ao2.e((byte)2);
        }
        if (ao2.b() != null && this.h[3] != bu.a[ao2.b().g]) {
            ao2.e((byte)3);
        }
        if (p2 != null && this.c != p2.f) {
            ao2.l();
        } else {
            if (ao2.a() != null && this.h[0] != bu.a[n.a - 6][ao2.a().g]) {
                ao2.e((byte)0);
            }
            n.a(2);
            bs.a.f();
        }
        ((cb)this).b = null;
        a = null;
        this.h = null;
        a = null;
        bs.a.b();
    }

    public final boolean a(int n2, int n3) {
        if (this.b(n2, n3)) {
            return true;
        }
        if (n3 == bh.a) {
            n.a((byte)14, (byte)0);
            return true;
        }
        return this.d(n2, n3);
    }

    public final void a(Graphics graphics) {
        this.b(graphics, a, b);
    }

    public final void a(Graphics graphics, int n2, int n3) {
        if (n3 < 0) {
            n3 = 0;
        }
        if (((cb)this).b != null) {
            bh.a(graphics);
        }
        boolean bl2 = false;
        graphics.setColor(0x3F1F3F);
        graphics.fillRect(n2, n3, 201, 250);
        cb.c(graphics, n2 + 2, n3 + 15, 197, 234);
        graphics.setColor(0xFFDFBF);
        graphics.fillRect(n2 + 5 + ((cb)this).b * 16 + 1, n3, 14, 1);
        graphics.fillRect(n2 + 5 + ((cb)this).b * 16, n3 + 1, 1, 16);
        graphics.setColor(12558207);
        graphics.fillRect(n2 + 5 + ((cb)this).b * 16 + 15, n3 + 1, 1, 15);
        graphics.setColor(14663551);
        graphics.fillRect(n2 + 5 + ((cb)this).b * 16 + 1, n3 + 1, 14, 16);
        int n4 = n2 + 7;
        for (int n5 = 0; n5 < 6; n5 = (int)((byte)(n5 + 1))) {
            graphics.drawImage(ce.m[n5], n4, n3 + 1, 20);
            n4 += 16;
        }
        if (((cb)this).b != null) {
            bh.a(graphics, bh.d, bh.e);
        }
    }

    public final void a(byte by2) {
        super.a(by2);
        ((cb)this).b = null;
        switch (((cb)this).b) {
            case 0: {
                ((cb)this).b = new q(this);
                break;
            }
            case 1: {
                ((cb)this).b = new ay(this);
                break;
            }
            case 2: {
                ((cb)this).b = new bz(this);
                break;
            }
            case 3: {
                ((cb)this).b = new bm(this);
                break;
            }
            case 4: {
                ((cb)this).b = new s(this);
                break;
            }
            case 5: {
                ((cb)this).b = new d(this);
            }
        }
        ((cb)this).a = true;
    }
}

