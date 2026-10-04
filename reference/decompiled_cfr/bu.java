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

public final class bu
implements Runnable {
    private static byte var_byte_a;
    private static final String[] g;
    public static final String[] var_java_lang_String_arr_a;
    public static final String[] var_java_lang_String_arr_b;
    public static final String[] c;
    public static final String[] d;
    public static final String[] e;
    public static final String[] f;
    public static final byte[][] var_byte_arr_arr_a;
    public static final byte[][] var_byte_arr_arr_b;
    public static final byte[] var_byte_arr_a;
    public static final byte[] var_byte_arr_b;
    private static boolean var_boolean_a;

    public static final void void_a() {
        var_byte_a = 1;
        r.a("- RESOURCE", 500);
        new Thread(new bu()).start();
    }

    public static final void b() {
        bs.var_as_a.f();
        var_byte_a = (byte)2;
        r.a("- MAP", 200);
        new Thread(new bu()).start();
    }

    public static final void c() {
        var_byte_a = (byte)3;
        r.a("\u00b0\u02c7\u00b5\u0111\u013e\u0111 \u013d\u0147\u010c\u017b\u00c1\u00df..", 120);
        new Thread(new bu()).start();
    }

    public static final void d() {
        var_byte_a = (byte)5;
        r.a("- MAIN MENU", 100);
        new Thread(new bu()).start();
    }

    public final void run() {
        try {
            Thread.sleep(100L);
        }
        catch (InterruptedException interruptedException) {}
        switch (var_byte_a) {
            case 2: {
                this.ae_a();
                n.a((int)n.var_byte_d, (int)n.var_byte_e);
                n.var_ae_a.d();
                n.a((byte)15, n.var_byte_c);
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
                if (!var_boolean_a) {
                    this.i();
                }
                try {
                    ce.var_z_f = new z("/sgui/q" + n.var_byte_a);
                    r.k();
                }
                catch (Exception exception) {}
                this.j();
                this.k();
                n.b(n.f, (byte)1, n.var_byte_c, n.var_byte_d);
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

    private final ae ae_a() {
        r.k();
        ae ae2 = n.var_ae_a;
        ao ao2 = n.ao_a();
        if (ae2 != null) {
            ae2.a(ao2);
            ao2.var_ck_a = null;
            ao2.var_ck_b = null;
            p p2 = ao2.p_a();
            if (p2 != null) {
                ae2.a(p2);
                p2.var_ck_a = null;
                p2.var_ck_b = null;
            }
        }
        r.k();
        n.var_ae_a = null;
        ae2 = new ae(n.f);
        n.a(ae2);
        r.k();
        ae2.a();
        r.k();
        return ae2;
    }

    public static final void e() {
        try {
            ce.var_z_b = new z("/grd/grd");
            r.k();
            ce.var_z_a = new z("/char/hero");
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
        var_boolean_a = true;
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
            ce.var_z_c = new z("/grd/grdsk");
            r.k();
            ce.var_z_d = new z("/m/name");
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
        var_boolean_a = false;
        ce.q();
        ce.u();
        ce.h();
        ce.j();
        ce.s();
        ce.var_z_c = null;
        ce.var_z_d = null;
        for (by2 = 5; by2 <= 8; by2 = (byte)((byte)(by2 + 1))) {
            bw.b(by2);
        }
        for (by2 = 12; by2 <= 15; by2 = (byte)(by2 + 1)) {
            bw.b(by2);
        }
    }

    private final void j() {
        ce.var_java_lang_Object_arr_a = new Object[396];
        ao ao2 = n.ao_a();
        if (ao2.e_a() != null) {
            String cfr_ignored_0 = "HERO ARMOR " + var_byte_arr_arr_b[n.var_byte_a - 6][ao2.e_a().g];
            r.k();
            bu.a(n.var_byte_a, ao2.ad_a((int)2).g);
        }
        r.k();
        bu.a(n.var_byte_a, (byte)1, (byte)0, false, (byte)0);
        if (ao2.e_b() != null) {
            String cfr_ignored_1 = "HERO HEAD " + var_byte_arr_a[ao2.e_b().g];
            r.k();
            bu.b(n.var_byte_a, ao2.e_b().g);
        } else {
            r.k();
            bu.b(n.var_byte_a, (byte)0);
        }
        if (n.var_byte_a == 8 && ao2.t_a() != null) {
            String cfr_ignored_2 = "HERO SHIELD " + var_byte_arr_b[ao2.t_a().g];
            r.k();
            bu.c(n.var_byte_a, ao2.t_a().g);
        }
    }

    public static final void g() {
        bu.a(0);
        bu.a(1);
        bu.a(2);
        bu.a(5);
    }

    private final void k() {
        ao ao2 = n.ao_a();
        p p2 = ao2.p_a();
        r.k();
        Object var3_3 = null;
        r.k();
        ce.f(p2.f);
        r.k();
        if (ao2.ad_a(0) != null) {
            bu.a(n.var_byte_a, (l)ao2.ad_a(0), false, p2.byte_a());
        }
        r.k();
        bu.d(n.var_byte_a, p2.byte_a());
        r.k();
        ce.void_a(p2.byte_a());
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
        ce.void_a();
        for (byte by2 = 16; by2 <= 21; by2 = (byte)(by2 + 1)) {
            bw.b(by2);
        }
    }

    public static final void a(byte by2, l l2, boolean bl2, byte by3) {
        bu.a(by2, (byte)5, var_byte_arr_arr_a[n.var_byte_a - 6][l2.g], bl2, by3);
    }

    public static final void a(byte by2, byte by3) {
        if (var_byte_arr_arr_b[n.var_byte_a - 6][by3] == -1) {
            bu.a(0);
            return;
        }
        bu.a(by2, (byte)0, var_byte_arr_arr_b[n.var_byte_a - 6][by3], false, (byte)0);
    }

    public static final void b(byte by2, byte by3) {
        byte by4 = 4;
        if (by2 == 6 && by3 >= 0 && by3 <= 3) {
            by4 = 3;
        }
        bu.a(by2, by4, var_byte_arr_a[by3], false, (byte)0);
    }

    public static final void c(byte by2, byte by3) {
        bu.a(by2, (byte)6, var_byte_arr_b[by3], false, (byte)0);
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
                    br2 = new br(var_java_lang_String_arr_b[by6] + c[by4]);
                    imageArray = new Image[br2.int_a()];
                    ce.var_javax_microedition_lcdui_Image_arr_arr_a[0] = imageArray;
                    imageArray2 = new Image[br2.int_a()];
                    ce.var_javax_microedition_lcdui_Image_arr_arr_a[6] = imageArray2;
                    n2 = 0;
                    break;
                }
                case 1: {
                    br2 = new br(var_java_lang_String_arr_b[by6] + "b");
                    imageArray = new Image[br2.int_a()];
                    ce.var_javax_microedition_lcdui_Image_arr_arr_a[1] = imageArray;
                    imageArray2 = new Image[br2.int_a()];
                    ce.var_javax_microedition_lcdui_Image_arr_arr_a[7] = imageArray2;
                    n2 = 1;
                    break;
                }
                case 3: 
                case 4: {
                    br2 = new br(var_java_lang_String_arr_b[by6] + d[by4]);
                    imageArray = new Image[br2.int_a()];
                    ce.var_javax_microedition_lcdui_Image_arr_arr_a[2] = imageArray;
                    imageArray2 = new Image[br2.int_a()];
                    ce.var_javax_microedition_lcdui_Image_arr_arr_a[8] = imageArray2;
                    n2 = 2;
                    break;
                }
                case 5: {
                    br2 = new br(var_java_lang_String_arr_b[by6] + e[by4]);
                    if (bl2) {
                        imageArray = new Image[br2.int_a()];
                        imageArray2 = new Image[br2.int_a()];
                    } else {
                        imageArray = new Image[br2.int_a()];
                        ce.var_javax_microedition_lcdui_Image_arr_arr_a[3] = imageArray;
                        imageArray2 = new Image[br2.int_a()];
                        ce.var_javax_microedition_lcdui_Image_arr_arr_a[9] = imageArray2;
                    }
                    n2 = 3;
                    if (by5 == 0) break;
                    switch (by5) {
                        case 1: {
                            br2.void_a(255, 0xFF7F3F);
                            break;
                        }
                        case 2: {
                            br2.void_a(255, 6258623);
                            break;
                        }
                        case 3: {
                            br2.void_a(255, 0x7FFF7F);
                        }
                    }
                    break;
                }
                case 2: {
                    br2 = new br(var_java_lang_String_arr_b[by6] + "e");
                    imageArray = new Image[br2.int_a()];
                    ce.var_javax_microedition_lcdui_Image_arr_arr_a[4] = imageArray;
                    imageArray2 = new Image[br2.int_a()];
                    ce.var_javax_microedition_lcdui_Image_arr_arr_a[10] = imageArray2;
                    n2 = 4;
                    switch (by5) {
                        case 1: {
                            br2.void_a(0xBFDFFF, 0xFFFFC0);
                            br2.void_a(0x9FBFFF, 0xFFBF7F);
                            br2.void_a(6258623, 0xFF7F3F);
                            break;
                        }
                        case 3: {
                            br2.void_a(0xBFDFFF, 0xDFFFBF);
                            br2.void_a(0x9FBFFF, 0xBFDFBF);
                            br2.void_a(6258623, 10469247);
                        }
                    }
                    if (by2 != 8) break;
                    bu.a(br2, (byte)4, (byte)10);
                    break;
                }
                case 6: {
                    br2 = new br(var_java_lang_String_arr_b[by6] + f[by4]);
                    imageArray = new Image[br2.int_a()];
                    ce.var_javax_microedition_lcdui_Image_arr_arr_a[5] = imageArray;
                    imageArray2 = new Image[br2.int_a()];
                    ce.var_javax_microedition_lcdui_Image_arr_arr_a[11] = imageArray2;
                    n2 = 5;
                }
            }
            r.k();
            br2.var_boolean_a = true;
            byte[] byArray = ce.a(var_java_lang_String_arr_a[by6] + g[by3]);
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
                            imageArray[by7] = br2.javax_microedition_lcdui_Image_a(by7);
                            continue;
                        }
                        if (!bl3 || imageArray2[by7] != null) continue;
                        imageArray2[by7] = br2.javax_microedition_lcdui_Image_b(by7);
                    }
                    if (!bl2) {
                        ce.var_java_lang_Object_arr_a[by9 * 36 + by10 * 9 + by8] = byArray2;
                    } else {
                        ce.var_java_lang_Object_arr_c[by9 * 4 + by10] = byArray2;
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
                                imageArray[by7] = br2.javax_microedition_lcdui_Image_a(by7);
                                continue;
                            }
                            if (!bl3 || imageArray2[by7] != null) continue;
                            imageArray2[by7] = br2.javax_microedition_lcdui_Image_b(by7);
                        }
                    }
                    ce.var_java_lang_Object_arr_a[by9 * 36 + by10 * 9 + by8] = byArray2;
                }
                r.k();
            }
            br2.void_a();
            return;
        }
        catch (IOException iOException) {
            IOException iOException2 = iOException;
            iOException.printStackTrace();
            return;
        }
    }

    public static final void a(int n2) {
        ce.var_javax_microedition_lcdui_Image_arr_arr_a[n2] = null;
        ce.var_javax_microedition_lcdui_Image_arr_arr_a[n2 + 6] = null;
        if (n2 == 3) {
            ce.var_java_lang_Object_arr_c = null;
        }
        for (int i2 = 0; i2 < 11; ++i2) {
            block9: for (int i3 = 0; i3 < 4; ++i3) {
                switch (n2) {
                    case 0: {
                        ce.var_java_lang_Object_arr_a[i2 * 36 + i3 * 9 + 2] = null;
                        ce.var_java_lang_Object_arr_a[i2 * 36 + i3 * 9 + 3] = null;
                        ce.var_java_lang_Object_arr_a[i2 * 36 + i3 * 9 + 4] = null;
                        ce.var_java_lang_Object_arr_a[i2 * 36 + i3 * 9 + 5] = null;
                        continue block9;
                    }
                    case 1: {
                        ce.var_java_lang_Object_arr_a[i2 * 36 + i3 * 9 + 0] = null;
                        continue block9;
                    }
                    case 2: {
                        ce.var_java_lang_Object_arr_a[i2 * 36 + i3 * 9 + 1] = null;
                        continue block9;
                    }
                    case 3: {
                        ce.var_java_lang_Object_arr_a[i2 * 36 + i3 * 9 + 6] = null;
                        continue block9;
                    }
                    case 4: {
                        ce.var_java_lang_Object_arr_a[i2 * 36 + i3 * 9 + 7] = null;
                        continue block9;
                    }
                    case 5: {
                        ce.var_java_lang_Object_arr_a[i2 * 36 + i3 * 9 + 8] = null;
                    }
                }
            }
        }
    }

    public static final void a(br br2, byte by2, byte by3) {
        br2.var_boolean_a = true;
        ce.var_java_lang_Object_arr_b = new Object[3];
        byte[] byArray = ce.a(var_java_lang_String_arr_a[2] + "ea2");
        ce.var_java_lang_Object_arr_b[0] = byArray;
        ce.a(true, byArray, 0, by2, by3, br2);
        byArray = ce.a(var_java_lang_String_arr_a[2] + "ea3");
        ce.var_java_lang_Object_arr_b[1] = byArray;
        ce.a(true, byArray, 0, by2, by3, br2);
        byArray = ce.a(var_java_lang_String_arr_a[2] + "ea4");
        ce.var_java_lang_Object_arr_b[2] = byArray;
        ce.a(true, byArray, 0, by2, by3, br2);
    }

    public static final void a(Graphics graphics) {
        switch (var_byte_a) {
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

    static {
        var_byte_a = 0;
        g = new String[]{"a", "b", "e", "hA", "hB", "w", "s"};
        var_java_lang_String_arr_a = new String[]{"/c1/s/", "/c2/s/", "/c3/s/"};
        var_java_lang_String_arr_b = new String[]{"/c1/i/", "/c2/i/", "/c3/i/"};
        c = new String[]{"a1", "a2", "a3", "a4", "a5", "a6"};
        d = new String[]{"h1", "h2", "h3", "h4", "h5", "h6", "h7"};
        e = new String[]{"w1", "w2", "w3", "w4", "w5"};
        f = new String[]{"s1", "s2", "s3", "s4", "s5"};
        var_byte_arr_arr_a = new byte[][]{{0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 0, 3, 3, 4, 1, 2, 3}, {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 0, 1, 2, 4, 3, 4, 1}, {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 0, 2, 4, 1, 2, 4, -1}};
        var_byte_arr_arr_b = new byte[][]{{-1, 0, 0, 1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 2, 0, 4, 5, 4, 3}, {-1, 0, 0, 1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 2, 0, 4, 5, 4, 3}, {0, 0, 0, 1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 2, 0, 4, 5, 4, 3}};
        var_byte_arr_a = new byte[]{0, 0, 0, 0, 1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6};
        var_byte_arr_b = new byte[]{0, 0, 0, 1, 1, 1, 2, 2, 2, 4, 4, 4, 2, 0, 4};
        var_boolean_a = false;
    }
}

