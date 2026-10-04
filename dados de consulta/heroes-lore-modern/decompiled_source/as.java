/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 *  javax.microedition.lcdui.Image
 */
import java.io.IOException;
import java.util.Vector;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public final class as
extends r {
    public static int a;
    public static int b;
    public static int c;
    public static int d;
    private static int n;
    private static int o;
    private static int p;
    private boolean d;
    private boolean e;
    private boolean f;
    private boolean g;
    private boolean h;
    private int q;
    private int r;
    private boolean i;
    private int s;
    private al a;
    public static int e;
    private z a;
    private int t;
    private int u;
    private Image[] a;
    private Vector a;
    private boolean j;
    public char[] a = "".toCharArray();

    public as() {
        a = r.g;
        b = r.h - 21;
        c = a / 2 - 8;
        d = b / 2;
        n = (r.g - 74) / 6;
        o = r.g - 67;
        p = r.g - 6;
        this.e = true;
        this.q = 0;
        this.r = 0;
        this.s = 0;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final void paint(Graphics graphics) {
        if (((r)this).c) {
            graphics.setColor(0);
            graphics.fillRect(0, 0, r.g, r.h);
            graphics.setColor(0xFFFFFF);
            if (bh.n != null) {
                bh.a(graphics, r.g / 2, r.h / 3, bh.n, 0);
            }
            if (bh.q != null) {
                bh.a(graphics, r.g / 2, r.h / 2, bh.q, 0);
            }
            return;
        }
        Object object = bs.a;
        synchronized (object) {
            n.e();
            switch (n.e) {
                case 2: {
                    if (bs.a.d) {
                        n.g();
                        n.i();
                    } else {
                        n.i();
                        n.g();
                    }
                    if (n.e != 2) break;
                    if (!n.a.b && bs.a.d) {
                        n.a(true, true);
                    }
                    n.a.a(graphics);
                    this.a(graphics);
                    if (!x.a || bs.a.a || n.a().g < 8) break;
                    n.a((byte)13, (byte)1);
                    return;
                }
                case 4: {
                    n.j();
                    if (n.e != 4) break;
                    ah.b();
                    ah.a(graphics);
                    break;
                }
                case 1: {
                    bu.a(graphics);
                    break;
                }
                case 15: {
                    char[] cArray = bh.n;
                    bh.a(graphics);
                    graphics.setColor(0xFFFFFF);
                    bh.a(graphics, r.i, r.j - 15, cArray, 1);
                    bh.a(graphics, bh.d, null);
                    break;
                }
                case 5: {
                    ai.a().b();
                    ai.a().c();
                    ai.a().a(graphics);
                    break;
                }
                case 6: {
                    this.a(graphics, bp.a());
                    bp.a().a(graphics);
                    break;
                }
                case 7: {
                    this.a(graphics, ax.a());
                    ax.a().a(graphics);
                    break;
                }
                case 8: {
                    this.a(graphics, aa.a());
                    aa.a().a(graphics);
                    break;
                }
                case 9: {
                    bf.a().c();
                    bf.a().a(graphics);
                    break;
                }
                case 10: {
                    as.b(graphics);
                    if (e > 0) {
                        --e;
                    }
                    if (e != 0) break;
                    n.a(1);
                    bu.d();
                    bw.b((byte)12);
                    break;
                }
                case 11: {
                    this.a(graphics, null);
                    n.a.b(graphics);
                    break;
                }
                case 12: {
                    this.d(graphics);
                    break;
                }
                case 13: {
                    this.e(graphics);
                    break;
                }
                case 14: {
                    this.f(graphics);
                }
            }
            graphics.setColor(0xFFFFFF);
            bs.a.b();
            return;
        }
    }

    private final void a(Graphics graphics, cb cb2) {
        if (this.d) {
            n.a.a(graphics);
            this.a(graphics);
            if (cb2 != null) {
                cb2.c();
            }
        }
    }

    /*
     * Enabled aggressive block sorting
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    public final void hideNotify() {
        if (bh.q != null) {
            ((r)this).c = true;
        }
        bw.a();
        Object object = bs.a;
        // MONITORENTER : object
        // MONITOREXIT : object
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final void showNotify() {
        if (((r)this).c) {
            return;
        }
        bw.b();
        Object object = bs.a;
        synchronized (object) {
            if (n.e == 2) {
                n.a((byte)13);
            }
            return;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final void keyPressed(int n2) {
        if (((r)this).c) {
            if (n2 != -11) {
                ((r)this).c = false;
                this.showNotify();
            }
            return;
        }
        Object object = bs.a;
        synchronized (object) {
            if (n2 == bh.b) {
                n2 = 53;
            }
            if (n2 == bh.c) {
                n2 = bh.a;
            }
            if (bs.a == null || bs.a.e) {
                return;
            }
            ((r)this).a = true;
            int n3 = 0;
            if (n2 != 53 && n2 != bh.a) {
                n3 = this.getGameAction(n2);
            }
            switch (n.e) {
                case 2: {
                    this.a(n3, n2);
                    break;
                }
                case 4: {
                    ah.a(n3, n2);
                    break;
                }
                case 5: {
                    ai.a().a(n3, n2);
                    break;
                }
                case 15: {
                    if (n2 != 53) break;
                    n.a(1);
                    break;
                }
                case 6: {
                    bp.a().a(n3, n2);
                    break;
                }
                case 7: {
                    ax.a().a(n3, n2);
                    break;
                }
                case 8: {
                    aa.a().a(n3, n2);
                    break;
                }
                case 9: {
                    bf.a().a(n3, n2);
                    break;
                }
                case 11: {
                    n.a((byte)2, (byte)2, (byte)1);
                    bs.a.b();
                    break;
                }
                case 12: {
                    this.j = true;
                    break;
                }
                case 14: {
                    if (n3 != 8 && n2 != 53) break;
                    n.a((byte)21, (byte)2);
                }
            }
            return;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final void keyReleased(int n2) {
        Object object = bs.a;
        synchronized (object) {
            if (n2 == bh.b) {
                n2 = 53;
            }
            if (n2 == bh.c) {
                n2 = bh.a;
            }
            if (n.e != 2) {
                return;
            }
            if (((r)this).a) {
                ((r)this).f = n2;
                return;
            }
            switch (n.a()) {
                case 2: {
                    n.h();
                }
            }
            return;
        }
    }

    private final void a(int n2, int n3) {
        if (n3 == 50) {
            n.b((byte)1);
        } else if (n3 == 56) {
            n.b((byte)2);
        }
        if (n3 == 35) {
            n.a().a.b();
            this.b();
            return;
        }
        if (n3 == 52) {
            n.b((byte)3);
            return;
        }
        if (n3 == 54) {
            n.b((byte)4);
            return;
        }
        if (n3 == 53) {
            if (n.a()) {
                return;
            }
            if (ah.a()) {
                return;
            }
            n.a(false);
            return;
        }
        if (n3 == bh.a) {
            if (((o)n.a()).h == 1) {
                n.a((byte)13);
                return;
            }
        } else {
            if (n3 == 55) {
                n.a(true);
                return;
            }
            if (n3 == 57) {
                n.a().k();
                return;
            }
            if (n3 == 49) {
                n.a().a(true);
                return;
            }
            if (n3 == 51) {
                n.a().a(false);
                return;
            }
            if (n3 == 48) {
                if (((o)n.a()).h == 1 && n.a.b <= 14) {
                    n.a((byte)2, (byte)11, (byte)3);
                    return;
                }
            } else {
                switch (n2) {
                    case 1: {
                        n.b((byte)1);
                        return;
                    }
                    case 6: {
                        n.b((byte)2);
                        return;
                    }
                    case 2: {
                        n.b((byte)3);
                        return;
                    }
                    case 5: {
                        n.b((byte)4);
                        return;
                    }
                    case 8: {
                        if (n.a()) {
                            this.b();
                            return;
                        }
                        if (ah.a()) {
                            return;
                        }
                        n.a(false);
                    }
                }
            }
        }
    }

    public static final void a(Graphics graphics, int n2, int n3, int n4, int n5) {
        if (n3 + n5 > b) {
            n5 = b - n3;
        }
        graphics.setClip(n2, n3, n4, n5);
    }

    public final void a() {
        this.d = true;
        this.b();
    }

    public final void b() {
        this.e = true;
    }

    public final void c() {
        this.f = true;
    }

    public final void d() {
        this.g = true;
    }

    public final void e() {
        this.h = true;
    }

    public final void f() {
        this.r = 0;
        this.i = false;
        this.s = 0;
        this.a = null;
    }

    public final void g() {
        bu.f();
        bu.g();
        bu.h();
        bw.f();
        e = -16;
        this.t = 0;
        this.u = -1;
        try {
            this.a = new z("/sgui/ed" + n.a);
            bw.a((byte)23);
            bw.b(23);
            switch (n.a) {
                case 6: {
                    try {
                        br br2 = new br("/m/face");
                        new br("/m/face").a = true;
                        this.a = new Image[2];
                        this.a[0] = br2.c(0);
                        this.a[1] = br2.c(8);
                    }
                    catch (IOException iOException) {
                        IOException iOException2 = iOException;
                        iOException.printStackTrace();
                    }
                    break;
                }
                case 8: {
                    try {
                        br br3 = new br("/m/face");
                        new br("/m/face").a = true;
                        this.a = new Image[1];
                        this.a[0] = br3.a(17);
                        break;
                    }
                    catch (IOException iOException) {
                        IOException iOException3 = iOException;
                        iOException.printStackTrace();
                    }
                }
            }
            return;
        }
        catch (IOException iOException) {
            IOException iOException4 = iOException;
            iOException.printStackTrace();
            return;
        }
    }

    public final void h() {
        e = 0;
        this.t = 0;
        this.u = -1;
        this.a = new Vector();
        try {
            this.a = new z("/sgui/edsr");
            br br2 = new br("/img/end");
            new br("/img/end").a = true;
            this.a = new Image[1];
            this.a[0] = br2.a(n.a - 6);
            return;
        }
        catch (IOException iOException) {
            IOException iOException2 = iOException;
            iOException.printStackTrace();
            return;
        }
    }

    public final void a(char[] cArray, int n2) {
        if (this.r > 0) {
            this.i = true;
        }
        this.r = n2;
        this.a = cArray;
    }

    public final void a(al al2, boolean bl2) {
        this.s = 24;
        if (bl2 && this.a != null) {
            return;
        }
        if (this.a == al2) {
            return;
        }
        this.a = al2;
    }

    public final void a(Graphics graphics) {
        ao ao2 = n.a();
        p p2 = ao2.a();
        boolean bl2 = false;
        int n2 = r.h - 31 - 5;
        if (ao2.a > 0) {
            ++this.q;
            if (this.q < 5) {
                graphics.drawImage(ce.w, 5, n2 + 9, 36);
            }
            if (this.q >= 8) {
                this.q = 0;
            }
        }
        if (this.e) {
            graphics.setClip(0, 0, r.g, r.h);
        } else {
            graphics.setClip(0, n2, r.g, 15);
        }
        as.a(graphics, 0, n2);
        ad ad2 = ao2.a.a();
        int n3 = ao2.a.a();
        graphics.drawImage(ce.d[n3], r.g - 10, n2 + 19, 3);
        if (ad2 != null) {
            r.c(graphics, ad2.h, r.g - 4, n2 + 22, 24);
        } else {
            r.c(graphics, 0, r.g - 4, n2 + 22, 24);
        }
        if (p2.g != -1) {
            graphics.setClip(0, 0, r.g, r.h);
            graphics.setColor(0);
            graphics.drawRect(7, n2 + 15, 14, 14);
            graphics.drawImage(ce.c[p2.f * 4 + p2.g], 7, n2 + 15, 20);
            if (p2.c[p2.g] != 0) {
                graphics.setColor(12525375);
                graphics.drawRect(7, n2 + 15, 14, 14);
            }
            graphics.setClip(8, n2 + 16, 13, 13 * p2.c[p2.g] / p.b[p2.f * 3 + p2.g]);
            graphics.drawImage(ce.D, 7, n2 + 15, 20);
        }
        if (p2.h != -1) {
            graphics.setClip(0, 0, r.g, r.h);
            graphics.setColor(0);
            graphics.drawRect(29, n2 + 15, 14, 14);
            graphics.drawImage(ce.c[p2.f * 4 + p2.h], 29, n2 + 15, 20);
            if (p2.c[p2.h] != 0) {
                graphics.setColor(12525375);
                graphics.drawRect(29, n2 + 15, 14, 14);
            }
            graphics.setClip(30, n2 + 16, 13, 13 * p2.c[p2.h] / p.b[p2.f * 3 + p2.h]);
            graphics.drawImage(ce.D, 29, n2 + 15, 20);
        }
        graphics.setClip(0, 0, r.g, r.h);
        if (this.e || this.f) {
            int n4 = ao2.a * o / ao2.d;
            graphics.setClip(47, n2 + 18, o, 7);
            as.a(graphics, 0, n2);
            if (n4 > 0) {
                graphics.setColor(0xFF0000);
                graphics.fillRect(47, n2 + 20, n4, 4);
                graphics.setColor(0xFF9F3F);
                graphics.fillRect(47, n2 + 21, n4, 2);
            }
            r.c(graphics, ao2.a, 46 + o - 2, n2 + 18, 8);
            this.f = false;
            graphics.setClip(0, 0, r.g, r.h);
        }
        if (this.e || this.g) {
            int n5 = ao2.b * o / ao2.e;
            graphics.setColor(0x3FFFBF);
            graphics.fillRect(47, n2 + 27, n5, 2);
            graphics.setColor(0);
            graphics.fillRect(47 + n5, n2 + 27, o - n5, 2);
            this.g = false;
        }
        if (this.e || this.h) {
            int n6 = ao2.c * p / ao2.f;
            graphics.setColor(0x9F9F7F);
            graphics.fillRect(0, n2 + 31, r.g, 5);
            graphics.setColor(0x3F3F3F);
            graphics.fillRect(2, n2 + 32, r.g - 4, 3);
            graphics.setColor(0xBFBF7F);
            graphics.drawLine(3, n2 + 33, 3 + n6 - 1, n2 + 33);
            this.h = false;
        }
        if (this.s > 0 && this.a != null && this.a.h != 6) {
            int n7 = r.g - 105;
            n3 = 2;
            if (p2 != null && p2.i == 2) {
                n3 += 20;
            }
            cb.a(graphics, n7, n3, 105, 27, false);
            graphics.translate(n7 + 2, n3 + 2);
            int n8 = 0xFFFFFF;
            if (this.a.a.b == 1) {
                n8 = 0xFF7F2F;
            } else if (this.a.a.b == 2) {
                n8 = 0xFFFF1F;
            }
            cb.a(graphics, 0, 0, 101, 23, this.a.a.a, 0, 1, 6233919, n8);
            int n9 = 0;
            n9 = r.a(graphics, bh.c, 1, 16);
            r.c(graphics, this.a.a.f, n9, 16, 4);
            graphics.translate(-(n7 + 2), -(n3 + 2));
            graphics.setColor(0xFF3F2F);
            if (this.a.a > 0) {
                graphics.fillRect(n7 + 24 + 5, n3 + 22, 77 * (this.a.a - 1) / this.a.a.a + 1 - 5, 2);
            }
            --this.s;
        } else {
            this.a = null;
        }
        if (p2 != null && p2.i == 2) {
            p2.a(graphics);
        }
        graphics.setClip(0, 0, r.g, r.h);
        if (this.i) {
            this.i = false;
            return;
        }
        if (this.r > 0) {
            int n10 = r.i - 50;
            n3 = r.h - 46;
            cb.a(graphics, n10, n3, 100, 23, false);
            graphics.setClip(0, 0, r.g, r.h);
            cb.a(graphics, n10 + 2, n3 + 2, 96, 19, this.a, 0, 1, 6233919, 0xFFFFFF);
            --this.r;
        }
    }

    private static final void a(Graphics graphics, int n2, int n3) {
        graphics.drawImage(ce.p[1], n2, n3 + 12, 20);
        graphics.drawImage(ce.p[1], n2 + 22, n3 + 12, 20);
        graphics.drawImage(ce.p[2], n2 + 23, n3 + 23, 20);
        graphics.drawImage(ce.p[3], n2 + 44, n3 + 12, 20);
        for (int i2 = 0; i2 < n; ++i2) {
            graphics.drawImage(ce.p[4], n2 + 49 + i2 * 6, n3 + 14, 20);
        }
        graphics.drawImage(ce.p[0], n2, n3 + 9, 20);
        graphics.drawImage(ce.p[6], r.g - 26, n3, 20);
        graphics.drawImage(ce.p[5], r.g - 30, n3 + 11, 20);
    }

    public static final void a(Graphics graphics, byte[] byArray, byte by2, int n2, int n3) {
        if (byArray == null || by2 >= byArray[0]) {
            return;
        }
        int n4 = 1 + by2 * 4;
        Image[] imageArray = ce.a[byArray[n4 + 2]];
        byte by3 = byArray[n4 + 3];
        if (by3 != -1 && imageArray[by3] != null) {
            graphics.drawImage(imageArray[by3], n2 + byArray[n4], n3 + byArray[n4 + 1], 20);
        }
    }

    public static final void b(Graphics graphics, byte[] byArray, byte n2, int n3, int n4) {
        int n5;
        int n6;
        if (byArray == null || n2 >= byArray[0]) {
            return;
        }
        int n7 = 1;
        for (n6 = 0; n6 < n2; ++n6) {
            n5 = byArray[n7++];
            n7 += n5 * 4;
        }
        n6 = byArray[n7++];
        for (n5 = 0; n5 < n6; ++n5) {
            Image[] imageArray = ce.a[byArray[n7 + 2]];
            byte by2 = byArray[n7 + 3];
            if (by2 != -1 && imageArray[by2] != null) {
                graphics.drawImage(imageArray[by2], n3 + byArray[n7], n4 + byArray[n7 + 1], 20);
            }
            n7 += 4;
        }
    }

    public static final void b(Graphics graphics, int n2, int n3, int n4, int n5) {
        graphics.setColor(0);
        cb.a(graphics, n2, n3, n4, n5);
        cb.b(graphics, n2, n3, n4, n5);
        graphics.setColor(0xFFFFFF);
        bh.a(graphics, (n2 += 4) + 5, n3 += 6, ce.g.a(31), 1);
        graphics.setColor(0xFF2F2F);
        graphics.drawLine(n2, n3 + 17 + 0, n2 + (n4 -= 8) * r.k / r.l, n3 + 17 + 0);
        graphics.drawLine(n2, n3 + 17 + 1, n2 + n4 * r.k / r.l, n3 + 17 + 1);
    }

    public static final void b(Graphics graphics) {
        graphics.setColor(0);
        graphics.fillRect(0, 0, r.g, r.h);
        n.a().d(graphics, r.i, r.j + 20);
        char[] cArray = ce.g.a(32);
        int n2 = bh.a(cArray);
        System.out.println(bh.a(cArray));
        graphics.setColor(0x7F7F7F);
        bh.a(graphics, r.i - n2 / 2 + 1, r.j - 20 + 1, 200, 1, cArray, 0, 0, (17 - e) * 2);
        graphics.setColor(0xFFFFFF);
        bh.a(graphics, r.i - n2 / 2, r.j - 20, 200, 1, cArray, 0, 0, (17 - e) * 2);
    }

    private final void d(Graphics graphics) {
        if (e < 0) {
            int n2 = 255 * -e / 16;
            graphics.setColor(n2, n2, n2);
            graphics.fillRect(0, 0, r.g, r.h);
            ++e;
            return;
        }
        graphics.setColor(0);
        graphics.fillRect(0, 0, r.g, r.h);
        if (this.j || this.u == -1) {
            this.j = false;
            ++this.u;
            this.t = this.u;
            while (this.u < this.a.a) {
                char[] cArray = this.a.a(this.u);
                if (cArray[0] == '_') {
                    e = Integer.parseInt(new String(cArray, 1, cArray.length - 1));
                    break;
                }
                ++this.u;
            }
        }
        if (this.u >= this.a.a) {
            this.a = null;
            this.a = null;
            this.h();
            n.a((byte)2, (byte)13, (byte)1);
            return;
        }
        int n3 = r.j - (this.u - this.t + 1) * 15 / 2;
        switch (n.a) {
            case 6: {
                if (this.t != 2 && this.t != 6 && this.t != 9 && this.t != 13) break;
                graphics.setColor(0xBFBFBF);
                graphics.fillRect(0, 15, r.g, 40);
                graphics.setClip(0, 15, r.g, 40);
                graphics.drawImage(this.a[0], r.g / 4, 5, 17);
                if (this.t == 9) {
                    graphics.drawImage(this.a[1], r.g / 4 * 3 + (e >= 27 ? (e - 27) * 10 : 0), 5, 17);
                }
                graphics.setClip(0, 0, r.g, r.h);
                n3 += 30;
                break;
            }
            case 8: {
                graphics.drawImage(this.a[0], r.g, r.h, 40);
            }
        }
        graphics.setColor(0xFFFFFF);
        for (int i2 = this.t; i2 < this.u; ++i2) {
            char[] cArray = this.a.a(i2);
            bh.a(graphics, r.i - bh.a(cArray) / 2, n3, cArray, 1);
            n3 += 15;
        }
        if (e > 0) {
            --e;
        }
    }

    private final void e(Graphics graphics) {
        Object object;
        graphics.setColor(0);
        graphics.fillRect(0, 0, r.g, r.h);
        graphics.drawImage(this.a[0], 0, r.h / 2, 6);
        if (e == 0 && this.t < this.a.a) {
            object = this.a.a(this.t);
            if (object[0] == '-') {
                e = 4;
            } else if (object[0] == '=') {
                e = 10;
            } else {
                Image image = Image.createImage((int)bh.a(object), (int)bh.a());
                Graphics graphics2 = image.getGraphics();
                graphics2.setColor(0);
                graphics2.fillRect(0, 0, image.getWidth(), image.getHeight());
                graphics2.setColor(0xFFFFFF);
                bh.a(graphics2, 0, 0, object, 1);
                this.a.addElement(new bc(image, r.h));
                e = 5;
            }
            ++this.t;
        }
        if (this.t >= this.a.a && this.a.size() == 0) {
            this.a = null;
            this.a = null;
            this.a = null;
            bw.f();
            bw.b((byte)23);
            n.a((byte)21, (byte)2);
            return;
        }
        if (e > 0) {
            --e;
        }
        for (int i2 = this.a.size() - 1; i2 >= 0; --i2) {
            object = (bc)this.a.elementAt(i2);
            graphics.drawImage(object.a, r.i, object.a, 17);
            object.a -= 2;
            if (object.a >= -8) continue;
            this.a.removeElementAt(i2);
        }
    }

    private final void f(Graphics graphics) {
        char[] cArray = ce.g.a(33);
        char[] cArray2 = ce.g.a(34);
        char[] cArray3 = ce.g.a(35);
        char[] cArray4 = ce.g.a(36);
        graphics.setColor(0);
        graphics.fillRect(0, 0, r.g, r.h);
        int n2 = r.i - 55;
        int n3 = r.j - 36;
        cb.a(graphics, n2, n3, 110, 72);
        cb.b(graphics, n2, n3, 110, 72);
        graphics.setColor(0xFFFFFF);
        bh.a(graphics, n2 + 5, n3 + 5, cArray, 1);
        bh.a(graphics, n2 + 5, n3 + 21, cArray2, 1);
        bh.a(graphics, n2 + 5, n3 + 37, cArray3, 1);
        bh.a(graphics, n2 + 5, n3 + 53, cArray4, 1);
    }
}

