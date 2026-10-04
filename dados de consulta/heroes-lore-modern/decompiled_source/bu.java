/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 *  javax.microedition.lcdui.Image
 */
import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public final class bu
implements Runnable {
    private static byte a = 0;
    private static final String[] g = new String[]{"a", "b", "e", "hA", "hB", "w", "s"};
    public static final String[] a = new String[]{"/c1/s/", "/c2/s/", "/c3/s/"};
    public static final String[] b = new String[]{"/c1/i/", "/c2/i/", "/c3/i/"};
    public static final String[] c = new String[]{"a1", "a2", "a3", "a4", "a5", "a6"};
    public static final String[] d = new String[]{"h1", "h2", "h3", "h4", "h5", "h6", "h7"};
    public static final String[] e = new String[]{"w1", "w2", "w3", "w4", "w5"};
    public static final String[] f = new String[]{"s1", "s2", "s3", "s4", "s5"};
    public static final byte[][] a = new byte[][]{{0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 0, 3, 3, 4, 1, 2, 3}, {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 0, 1, 2, 4, 3, 4, 1}, {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 0, 2, 4, 1, 2, 4, -1}};
    public static final byte[][] b = new byte[][]{{-1, 0, 0, 1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 2, 0, 4, 5, 4, 3}, {-1, 0, 0, 1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 2, 0, 4, 5, 4, 3}, {0, 0, 0, 1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 2, 0, 4, 5, 4, 3}};
    public static final byte[] a = new byte[]{0, 0, 0, 0, 1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6};
    public static final byte[] b = new byte[]{0, 0, 0, 1, 1, 1, 2, 2, 2, 4, 4, 4, 2, 0, 4};
    private static boolean a = false;

    public static final void a() {
        a = 1;
        r.a("- RESOURCE", 500);
        new Thread(new bu()).start();
    }

    public static final void b() {
        bs.a.f();
        a = (byte)2;
        r.a("- MAP", 200);
        new Thread(new bu()).start();
    }

    public static final void c() {
        a = (byte)3;
        r.a("\u00b0\u02c7\u00b5\u0111\u013e\u0111 \u013d\u0147\u010c\u017b\u00c1\u00df..", 120);
        new Thread(new bu()).start();
    }

    public static final void d() {
        a = (byte)5;
        r.a("- MAIN MENU", 100);
        new Thread(new bu()).start();
    }

    public final void run() {
        try {
            Thread.sleep(100L);
        }
        catch (InterruptedException interruptedException) {}
        switch (a) {
            case 2: {
                this.a();
                n.a((int)n.d, (int)n.e);
                n.a.d();
                n.a((byte)15, n.c);
                return;
            }
            case 1: {
                try {
                    Thread.sleep(1000L);
                }
                catch (InterruptedException interruptedException) {
                    InterruptedException interruptedException2 = interruptedException;
                    interruptedException.printStackTrace();
                }
                r.k();
                if (!a) {
                    this.i();
                }
                try {
                    ce.f = new z("/sgui/q" + n.a);
                    r.k();
                }
                catch (Exception exception) {}
                this.j();
                this.k();
                n.b(n.f, (byte)1, n.c, n.d);
                r.b = true;
                return;
            }
            case 3: {
                bu.h();
                this.k();
                n.a((byte)2, (byte)2, (byte)1);
                return;
            }
            case 5: {
                bu.g();
                bu.h();
                ce.A();
                n.p();
            }
        }
    }

    private final ae a() {
        r.k();
        ae ae2 = n.a;
        ao ao2 = n.a();
        if (ae2 != null) {
            ae2.a(ao2);
            ((ck)ao2).a = null;
            ((ck)ao2).b = null;
            p p2 = ao2.a();
            if (p2 != null) {
                ae2.a(p2);
                ((ck)p2).a = null;
                ((ck)p2).b = null;
            }
        }
        r.k();
        n.a = null;
        ae2 = new ae(n.f);
        n.a(ae2);
        r.k();
        ae2.a();
        r.k();
        return ae2;
    }

    public static final void e() {
        try {
            ce.b = new z("/grd/grd");
            r.k();
            ce.a = new z("/char/hero");
            r.k();
            return;
        }
        catch (IOException iOException) {
            IOException iOException2 = iOException;
            iOException.printStackTrace();
            return;
        }
    }

    private final void i() {
        byte by2;
        a = true;
        Object var1_1 = null;
        r.k();
        ce.n();
        r.k();
        ce.p();
        r.k();
        ce.t();
        r.k();
        ce.g();
        r.k();
        ce.i();
        r.k();
        ce.r();
        r.k();
        ce.l();
        r.k();
        try {
            ce.c = new z("/grd/grdsk");
            r.k();
            ce.d = new z("/m/name");
            r.k();
        }
        catch (IOException iOException) {
            IOException iOException2 = iOException;
            iOException.printStackTrace();
        }
        for (by2 = 5; by2 <= 8; by2 = (byte)((byte)(by2 + 1))) {
            bw.a(by2);
        }
        for (by2 = 12; by2 <= 15; by2 = (byte)(by2 + 1)) {
            bw.a(by2);
        }
    }

    public static final void f() {
        byte by2;
        a = false;
        ce.q();
        ce.u();
        ce.h();
        ce.j();
        ce.s();
        ce.c = null;
        ce.d = null;
        for (by2 = 5; by2 <= 8; by2 = (byte)((byte)(by2 + 1))) {
            bw.b(by2);
        }
        for (by2 = 12; by2 <= 15; by2 = (byte)(by2 + 1)) {
            bw.b(by2);
        }
    }

    private final void j() {
        ce.a = new Object[396];
        ao ao2 = n.a();
        if (ao2.a() != null) {
            String cfr_ignored_0 = "HERO ARMOR " + b[n.a - 6][ao2.a().g];
            r.k();
            bu.a(n.a, ao2.a((int)2).g);
        }
        r.k();
        bu.a(n.a, (byte)1, (byte)0, false, (byte)0);
        if (ao2.b() != null) {
            String cfr_ignored_1 = "HERO HEAD " + a[ao2.b().g];
            r.k();
            bu.b(n.a, ao2.b().g);
        } else {
            r.k();
            bu.b(n.a, (byte)0);
        }
        if (n.a == 8 && ao2.a() != null) {
            String cfr_ignored_2 = "HERO SHIELD " + b[ao2.a().g];
            r.k();
            bu.c(n.a, ao2.a().g);
        }
    }

    public static final void g() {
        bu.a(0);
        bu.a(1);
        bu.a(2);
        bu.a(5);
    }

    private final void k() {
        ao ao2 = n.a();
        p p2 = ao2.a();
        r.k();
        Object var3_3 = null;
        r.k();
        ce.f(p2.f);
        r.k();
        if (ao2.a(0) != null) {
            bu.a(n.a, (l)ao2.a(0), false, p2.a());
        }
        r.k();
        bu.d(n.a, p2.a());
        r.k();
        ce.a(p2.a());
        r.k();
        switch (p2.f) {
            case 0: {
                bw.a((byte)16);
                bw.a((byte)21);
                return;
            }
            case 1: {
                bw.a((byte)20);
                return;
            }
            case 2: {
                bw.a((byte)17);
                bw.a((byte)21);
                return;
            }
            case 3: {
                bw.a((byte)16);
                return;
            }
            case 4: {
                bw.a((byte)18);
                bw.a((byte)20);
                return;
            }
            case 5: {
                bw.a((byte)17);
            }
        }
    }

    public static final void h() {
        ce.v();
        bu.a(3);
        bu.a(4);
        ce.a();
        for (byte by2 = 16; by2 <= 21; by2 = (byte)(by2 + 1)) {
            bw.b(by2);
        }
    }

    public static final void a(byte by2, l l2, boolean bl2, byte by3) {
        bu.a(by2, (byte)5, a[n.a - 6][l2.g], bl2, by3);
    }

    public static final void a(byte by2, byte by3) {
        if (b[n.a - 6][by3] == -1) {
            bu.a(0);
            return;
        }
        bu.a(by2, (byte)0, b[n.a - 6][by3], false, (byte)0);
    }

    public static final void b(byte by2, byte by3) {
        byte by4 = 4;
        if (by2 == 6 && by3 >= 0 && by3 <= 3) {
            by4 = 3;
        }
        bu.a(by2, by4, a[by3], false, (byte)0);
    }

    public static final void c(byte by2, byte by3) {
        bu.a(by2, (byte)6, b[by3], false, (byte)0);
    }

    public static final void d(byte by2, byte by3) {
        bu.a(by2, (byte)2, (byte)0, false, by3);
    }

    private static final void a(byte by2, byte by3, byte by4, boolean bl2, byte by5) {
        byte by6 = (byte)(by2 - 6);
        try {
            br br2 = null;
            Image[] imageArray = null;
            Image[] imageArray2 = null;
            int n2 = -1;
            switch (by3) {
                case 0: {
                    br2 = new br(b[by6] + c[by4]);
                    imageArray = new Image[br2.a()];
                    ce.a[0] = imageArray;
                    imageArray2 = new Image[br2.a()];
                    ce.a[6] = imageArray2;
                    n2 = 0;
                    break;
                }
                case 1: {
                    br2 = new br(b[by6] + "b");
                    imageArray = new Image[br2.a()];
                    ce.a[1] = imageArray;
                    imageArray2 = new Image[br2.a()];
                    ce.a[7] = imageArray2;
                    n2 = 1;
                    break;
                }
                case 3: 
                case 4: {
                    br2 = new br(b[by6] + d[by4]);
                    imageArray = new Image[br2.a()];
                    ce.a[2] = imageArray;
                    imageArray2 = new Image[br2.a()];
                    ce.a[8] = imageArray2;
                    n2 = 2;
                    break;
                }
                case 5: {
                    br2 = new br(b[by6] + e[by4]);
                    if (bl2) {
                        imageArray = new Image[br2.a()];
                        imageArray2 = new Image[br2.a()];
                    } else {
                        imageArray = new Image[br2.a()];
                        ce.a[3] = imageArray;
                        imageArray2 = new Image[br2.a()];
                        ce.a[9] = imageArray2;
                    }
                    n2 = 3;
                    if (by5 == 0) break;
                    switch (by5) {
                        case 1: {
                            br2.a(255, 0xFF7F3F);
                            break;
                        }
                        case 2: {
                            br2.a(255, 6258623);
                            break;
                        }
                        case 3: {
                            br2.a(255, 0x7FFF7F);
                        }
                    }
                    break;
                }
                case 2: {
                    br2 = new br(b[by6] + "e");
                    imageArray = new Image[br2.a()];
                    ce.a[4] = imageArray;
                    imageArray2 = new Image[br2.a()];
                    ce.a[10] = imageArray2;
                    n2 = 4;
                    switch (by5) {
                        case 1: {
                            br2.a(0xBFDFFF, 0xFFFFC0);
                            br2.a(0x9FBFFF, 0xFFBF7F);
                            br2.a(6258623, 0xFF7F3F);
                            break;
                        }
                        case 3: {
                            br2.a(0xBFDFFF, 0xDFFFBF);
                            br2.a(0x9FBFFF, 0xBFDFBF);
                            br2.a(6258623, 10469247);
                        }
                    }
                    if (by2 != 8) break;
                    bu.a(br2, (byte)4, (byte)10);
                    break;
                }
                case 6: {
                    br2 = new br(b[by6] + f[by4]);
                    imageArray = new Image[br2.a()];
                    ce.a[5] = imageArray;
                    imageArray2 = new Image[br2.a()];
                    ce.a[11] = imageArray2;
                    n2 = 5;
                }
            }
            r.k();
            br2.a = true;
            byte[] byArray = ce.a(a[by6] + g[by3]);
            r.k();
            int n3 = 0;
            while (n3 < byArray.length) {
                byte by7;
                boolean bl3;
                int n4;
                int n5;
                byte[] byArray2;
                byte by8 = byArray[n3++];
                byte by9 = byArray[n3++];
                byte by10 = byArray[n3++];
                int n6 = byArray[n3++];
                if (by3 != 2) {
                    byArray2 = new byte[1 + n6 * 4];
                    n5 = 0;
                    ++n5;
                    byArray2[0] = n6;
                    for (n4 = 0; n4 < n6; ++n4) {
                        byArray2[n5++] = byArray[n3++];
                        byArray2[n5++] = byArray[n3++];
                        bl3 = byArray[n3++] != 0;
                        byArray2[n5++] = bl3 ? (int)(n2 + 6) : n2;
                        int n7 = n5++;
                        byte by11 = byArray[n3++];
                        byArray2[n7] = by11;
                        by7 = by11;
                        if (by11 == -1) continue;
                        if (!bl3 && imageArray[by7] == null) {
                            imageArray[by7] = br2.a(by7);
                            continue;
                        }
                        if (!bl3 || imageArray2[by7] != null) continue;
                        imageArray2[by7] = br2.b(by7);
                    }
                    if (!bl2) {
                        ce.a[by9 * 36 + by10 * 9 + by8] = byArray2;
                    } else {
                        ce.c[by9 * 4 + by10] = byArray2;
                    }
                } else {
                    int n8;
                    n5 = n3;
                    for (int i2 = 0; i2 < n6; ++i2) {
                        n4 = byArray[n5++];
                        for (n8 = 0; n8 < n4; ++n8) {
                            n5 += 4;
                        }
                    }
                    byArray2 = new byte[1 + (n5 - n3)];
                    n5 = 0;
                    ++n5;
                    byArray2[0] = n6;
                    for (n4 = 0; n4 < n6; ++n4) {
                        int n9 = n5++;
                        int n10 = byArray[n3++];
                        byArray2[n9] = n10;
                        n8 = n10;
                        for (int i3 = 0; i3 < n8; ++i3) {
                            byArray2[n5++] = byArray[n3++];
                            byArray2[n5++] = byArray[n3++];
                            bl3 = byArray[n3++] != 0;
                            byArray2[n5++] = bl3 ? (int)(n2 + 6) : n2;
                            int n11 = n5++;
                            byte by12 = byArray[n3++];
                            byArray2[n11] = by12;
                            by7 = by12;
                            if (!bl3 && imageArray[by7] == null) {
                                imageArray[by7] = br2.a(by7);
                                continue;
                            }
                            if (!bl3 || imageArray2[by7] != null) continue;
                            imageArray2[by7] = br2.b(by7);
                        }
                    }
                    ce.a[by9 * 36 + by10 * 9 + by8] = byArray2;
                }
                r.k();
            }
            br2.a();
            return;
        }
        catch (IOException iOException) {
            IOException iOException2 = iOException;
            iOException.printStackTrace();
            return;
        }
    }

    public static final void a(int n2) {
        ce.a[n2] = null;
        ce.a[n2 + 6] = null;
        if (n2 == 3) {
            ce.c = null;
        }
        for (int i2 = 0; i2 < 11; ++i2) {
            block9: for (int i3 = 0; i3 < 4; ++i3) {
                switch (n2) {
                    case 0: {
                        ce.a[i2 * 36 + i3 * 9 + 2] = null;
                        ce.a[i2 * 36 + i3 * 9 + 3] = null;
                        ce.a[i2 * 36 + i3 * 9 + 4] = null;
                        ce.a[i2 * 36 + i3 * 9 + 5] = null;
                        continue block9;
                    }
                    case 1: {
                        ce.a[i2 * 36 + i3 * 9 + 0] = null;
                        continue block9;
                    }
                    case 2: {
                        ce.a[i2 * 36 + i3 * 9 + 1] = null;
                        continue block9;
                    }
                    case 3: {
                        ce.a[i2 * 36 + i3 * 9 + 6] = null;
                        continue block9;
                    }
                    case 4: {
                        ce.a[i2 * 36 + i3 * 9 + 7] = null;
                        continue block9;
                    }
                    case 5: {
                        ce.a[i2 * 36 + i3 * 9 + 8] = null;
                    }
                }
            }
        }
    }

    public static final void a(br br2, byte by2, byte by3) {
        br2.a = true;
        ce.b = new Object[3];
        byte[] byArray = ce.a(a[2] + "ea2");
        ce.b[0] = byArray;
        ce.a(true, byArray, 0, by2, by3, br2);
        byArray = ce.a(a[2] + "ea3");
        ce.b[1] = byArray;
        ce.a(true, byArray, 0, by2, by3, br2);
        byArray = ce.a(a[2] + "ea4");
        ce.b[2] = byArray;
        ce.a(true, byArray, 0, by2, by3, br2);
    }

    public static final void a(Graphics graphics) {
        switch (a) {
            case 1: 
            case 2: {
                r.c(graphics);
                return;
            }
            case 3: {
                as.b(graphics, r.g - 191 >> 1, r.j - 15, 191, 30);
            }
        }
    }
}

