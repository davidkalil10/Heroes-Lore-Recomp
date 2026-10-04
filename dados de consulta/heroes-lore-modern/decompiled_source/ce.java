/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Image
 */
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.microedition.lcdui.Image;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public final class ce
implements u {
    public static Object[] a;
    public static Object[] b;
    public static Object[] c;
    public static Object[] d;
    public static byte[] h;
    public static Object[] e;
    public static Object[] f;
    public static Object[] g;
    public static Object[] h;
    public static Object[] i;
    public static Object[] j;
    public static byte[] i;
    public static byte[] j;
    public static byte[] k;
    public static byte[] l;
    public static Image[][] a;
    public static z a;
    public static Image[] a;
    public static Image[] b;
    public static Image[] c;
    public static z b;
    public static z c;
    public static Image[] d;
    public static Image[] e;
    public static Image[] f;
    public static Image[] g;
    public static Image[] h;
    public static z d;
    public static Image a;
    public static Image[] i;
    public static Image[] j;
    public static Image[] k;
    public static Image[] l;
    public static Image[][] b;
    public static Image[] m;
    public static Image[] n;
    public static Image b;
    public static Image c;
    public static Image d;
    public static Image e;
    public static Image f;
    public static Image g;
    public static Image h;
    public static Image i;
    public static Image j;
    public static Image k;
    public static Image l;
    public static Image m;
    public static Image n;
    public static Image o;
    public static Image p;
    public static Image[] o;
    public static Image q;
    public static Image r;
    public static z e;
    public static Image[] p;
    public static Image s;
    public static Image[] q;
    public static z f;
    public static byte[] m;
    public static Image[] r;
    public static Image[] s;
    public static Image[] t;
    public static Image t;
    public static Image u;
    public static Image v;
    public static Image w;
    public static Image x;
    public static Image y;
    public static Image z;
    public static Image A;
    public static Image B;
    public static Image C;
    public static Image D;
    public static Image E;
    public static Image[] u;
    public static Image[] v;
    public static z g;
    public static byte[] n;

    private ce() {
    }

    public static final void a(byte by2) {
        try {
            br br2 = new br("/img/atteff1");
            new br("/img/atteff1").a = true;
            ce.a(br2, by2);
            r = new Image[3];
            ce.r[0] = br2.a(0);
            ce.r[1] = br2.a(1);
            ce.r[2] = br2.a(2);
            r.k();
            br2.a("/img/atteff2");
            ce.a(br2, by2);
            s = new Image[3];
            ce.s[0] = br2.a(0);
            ce.s[1] = br2.a(1);
            ce.s[2] = br2.a(2);
            r.k();
            br2.a("/img/atteff3");
            t = new Image[3];
            ce.t[0] = br2.a(0);
            ce.t[1] = br2.a(1);
            ce.t[2] = br2.a(2);
            r.k();
            return;
        }
        catch (IOException iOException) {
            IOException iOException2 = iOException;
            iOException.printStackTrace();
            return;
        }
    }

    public static final void a() {
        r = null;
        s = null;
        t = null;
    }

    private static final void a(br br2, byte by2) {
        switch (by2) {
            case 1: {
                br2.a(0xBFDFFF, 0xFFFFC0);
                br2.a(0x9FBFFF, 0xFFBF7F);
                br2.a(6258623, 0xFF7F3F);
                return;
            }
            case 3: {
                br2.a(0xBFDFFF, 0xDFFFBF);
                br2.a(0x9FBFFF, 0xBFDFBF);
                br2.a(6258623, 10469247);
            }
        }
    }

    public static final void b() {
        e = null;
    }

    public static final void c() {
        f = null;
    }

    public static final void d() {
        g = null;
    }

    public static final void e() {
        e = new Object[60];
        h = new Object[80];
        k = new byte[5];
        for (int i2 = 0; i2 < 10; ++i2) {
            ce.a[15 + i2] = null;
        }
    }

    public static final void a(byte by2, byte by3) {
        if (by2 == 0 && by3 == 0) {
            ce.d[by3] = ce.a("/grd/spr/0_01.eif");
            ce.a(true, (byte[])d[by3], 0, (byte)12, (byte)-1, null);
            m = ce.a("/grd/spr/0_02.eif");
            ce.a(true, m, 0, (byte)12, (byte)-1, null);
        } else {
            String string = "/grd/spr/" + by2 + "_" + by3 + ".eif";
            ce.d[by3] = ce.a(string);
            ce.a(true, (byte[])d[by3], 0, (byte)12, (byte)-1, null);
        }
        String cfr_ignored_0 = "GuardianSprite : " + by2 + ", " + by3;
    }

    public static final void f() {
        d = new Object[3];
        m = null;
    }

    public static final void a(boolean bl2, byte[] byArray, int n2, byte by2, byte by3, br br2) {
        int n3 = byArray[n2++];
        if (br2 != null) {
            br2.a = true;
            if (a[by2] == null) {
                ce.a[by2] = new Image[br2.a()];
            }
            if (by3 != -1 && a[by3] == null) {
                ce.a[by3] = new Image[br2.a()];
            }
        }
        r.k();
        for (int i2 = 0; i2 < n3; ++i2) {
            int n4 = bl2 ? byArray[n2++] : 1;
            for (int i3 = 0; i3 < n4; ++i3) {
                Image[] imageArray;
                ++n2;
                int n5 = ++n2;
                ++n2;
                boolean bl3 = byArray[n5] != 0;
                byte by4 = byArray[n2++];
                byte by5 = bl3 ? by3 : by2;
                byArray[n2 - 2] = by5;
                x.a(by5 > 0);
                if (br2 == null || (imageArray = a[by5])[by4] != null) continue;
                imageArray[by4] = bl3 ? br2.b(by4) : br2.a(by4);
                r.k();
            }
        }
    }

    public static final void g() {
        br br2 = null;
        try {
            br2 = new br("/img/uifrm");
            new br("/img/uifrm").a = true;
            p = new Image[7];
            for (int i2 = 0; i2 < 7; ++i2) {
                ce.p[i2] = br2.a(i2);
                r.k();
            }
            q = new Image[4];
            ce.q[0] = br2.a(7);
            ce.q[1] = br2.b(7);
            r.k();
            ce.q[2] = br2.a(8);
            ce.q[3] = br2.b(8);
            r.k();
            br2 = new br("/img/etcui");
            new br("/img/etcui").a = true;
            t = bh.a("_img_etcui__0.png");
            u = bh.a("_img_etcui__1.png");
            br2.a(2);
            r.k();
            v = br2.a(3);
            w = bh.a("_img_etcui__4.png");
            x = br2.a(5);
            y = br2.a(6);
            r.k();
            z = br2.a(7);
            A = br2.a(8);
            B = br2.a(9);
            C = br2.a(10);
            D = br2.a(11);
            r.k();
            br2 = new br("/char/lvup");
            h = ce.a("/char/lvup.eif");
            r.k();
            ce.a(true, h, 0, (byte)13, (byte)-1, br2);
            return;
        }
        catch (Exception exception) {
            System.out.println(exception);
            return;
        }
    }

    public static final void h() {
        p = null;
        q = null;
        t = null;
        u = null;
        v = null;
        w = null;
        x = null;
        y = null;
        z = null;
        A = null;
        B = null;
        C = null;
        D = null;
        h = null;
        ce.a[13] = null;
    }

    public static final void i() {
        br br2 = null;
        try {
            br2 = new br("/img/keepst");
            new br("/img/keepst").a = true;
            E = br2.a(0);
            r.k();
            u = new Image[8];
            for (int i2 = 0; i2 < 8; ++i2) {
                ce.u[i2] = br2.a(i2 + 1);
            }
            r.k();
            br2 = new br("/img/emoti");
            v = br2.a();
            r.k();
            return;
        }
        catch (Exception exception) {
            System.out.println(exception);
            return;
        }
    }

    public static final void j() {
        E = null;
        u = null;
        v = null;
    }

    public static final void b(byte by2) {
        a = new Image[2];
        br br2 = null;
        try {
            switch (by2) {
                case 0: 
                case 3: {
                    br2 = new br("/grd/fi");
                    break;
                }
                case 1: 
                case 4: {
                    br2 = new br("/grd/wa");
                    break;
                }
                case 2: 
                case 5: {
                    br2 = new br("/grd/gr");
                }
            }
            ce.a[12] = br2.a();
            br2 = new br("/grd/" + by2);
            new br("/grd/" + by2).a = true;
            ce.a[0] = br2.a(0);
            ce.a[1] = br2.a(1);
            return;
        }
        catch (IOException iOException) {
            IOException iOException2 = iOException;
            iOException.printStackTrace();
            return;
        }
    }

    public static final void k() {
        a = null;
        ce.a[12] = null;
    }

    public static final void a(short s2, byte by2, boolean bl2) {
        try {
            byte by3;
            br br2 = new br("/enm/" + (s2 < 10 ? "0" : "") + s2);
            new br("/enm/" + (s2 < 10 ? "0" : "") + s2).a = true;
            r.k();
            byte[] byArray = ce.a("/enm/spr/" + (s2 < 10 ? "0" : "") + s2);
            r.k();
            for (int i2 = 0; i2 < byArray.length; i2 += by3) {
                byte by4 = byArray[i2++];
                byte by5 = byArray[i2++];
                by3 = byArray[i2++];
                if (bl2) {
                    if (by4 == 0) {
                        ce.i[by2] = byArray[i2];
                    } else if (by4 == 1) {
                        ce.j[by2] = byArray[i2];
                    }
                    ce.j[by2 * 12 + by4 * 4 + by5] = new byte[by3];
                    ce.a(true, byArray, i2, (byte)(27 + by2), (byte)(27 + by2 + 5), br2);
                    System.arraycopy(byArray, i2, j[by2 * 12 + by4 * 4 + by5], 0, by3);
                } else {
                    ce.e[by2 * 12 + by4 * 4 + by5] = new byte[by3];
                    ce.a(true, byArray, i2, (byte)(15 + by2), (byte)(15 + by2 + 5), br2);
                    System.arraycopy(byArray, i2, e[by2 * 12 + by4 * 4 + by5], 0, by3);
                }
                r.k();
            }
            if (!bl2 && j.a[by2].d >= 2) {
                byArray = ce.a("/enm/atef/" + (s2 < 10 ? "0" : "") + s2);
                ce.a(true, byArray, 0, (byte)(15 + by2), (byte)(15 + by2 + 5), br2);
                ce.f[by2] = byArray;
            }
            r.k();
            return;
        }
        catch (IOException iOException) {
            IOException iOException2 = iOException;
            iOException.printStackTrace();
            return;
        }
    }

    public static final void c(byte by2) {
        ce.a[15 + by2] = null;
        ce.a[15 + by2 + 5] = null;
        for (int i2 = 0; i2 < 12; ++i2) {
            ce.e[by2 * 12 + i2] = null;
        }
    }

    public static final void l() {
        try {
            br br2 = new br("/enm/die/bang");
            new br("/enm/die/bang").a = true;
            for (int i2 = 0; i2 < 3; ++i2) {
                byte[] byArray = ce.a("/enm/die/" + i2);
                ce.a(true, byArray, 0, (byte)37, (byte)-1, br2);
                ce.g[i2] = byArray;
            }
            return;
        }
        catch (IOException iOException) {
            return;
        }
    }

    public static final void b(byte by2, byte by3) {
        try {
            byte by4;
            br br2 = new br("/npc/" + (by2 < 10 ? "0" : "") + by2);
            new br("/npc/" + (by2 < 10 ? "0" : "") + by2).a = true;
            r.k();
            byte[] byArray = ce.a("/npc/spr/" + (by2 < 10 ? "0" : "") + by2);
            r.k();
            for (int i2 = 0; i2 < byArray.length; i2 += by4) {
                byte by5 = byArray[i2++];
                byte by6 = byArray[i2++];
                by4 = byArray[i2++];
                ce.j[by3 * 12 + by5 * 4 + by6] = new byte[by4];
                if (by5 == 0) {
                    ce.i[by3] = byArray[i2];
                } else if (by5 == 1) {
                    ce.j[by3] = byArray[i2];
                }
                ce.a(true, byArray, i2, (byte)(27 + by3), (byte)(27 + by3 + 5), br2);
                System.arraycopy(byArray, i2, j[by3 * 12 + by5 * 4 + by6], 0, by4);
                r.k();
            }
            return;
        }
        catch (IOException iOException) {
            IOException iOException2 = iOException;
            iOException.printStackTrace();
            return;
        }
    }

    public static final void d(byte by2) {
        ce.a[27 + by2] = null;
        ce.a[27 + by2 + 5] = null;
        for (int i2 = 0; i2 < 12; ++i2) {
            ce.j[by2 * 12 + i2] = null;
        }
    }

    public static final void e(byte by2) {
        try {
            int n2;
            br br2 = new br("/boss/" + by2);
            new br("/boss/" + by2).a = true;
            switch (by2) {
                case 1: {
                    byte by3 = 32;
                    n2 = 32;
                    break;
                }
                case 2: {
                    byte by3 = 35;
                    n2 = 38;
                    break;
                }
                case 3: {
                    byte by3 = 39;
                    n2 = 41;
                    break;
                }
                case 4: {
                    byte by3 = 42;
                    n2 = 42;
                    break;
                }
                default: {
                    x.a(false);
                    byte by3 = -1;
                    n2 = -1;
                }
            }
            for (byte by4 = by3; by4 <= n2; by4 = (byte)(by4 + 1)) {
                byte[] byArray = ce.a("/boss/spr/" + by2 + "_" + by4);
                int n3 = 0;
                while (n3 < byArray.length) {
                    byte by5 = ce.a(by4);
                    byte by6 = byArray[n3++];
                    byte by7 = byArray[n3++];
                    byte by8 = byArray[n3++];
                    byte[] byArray2 = new byte[by8 & 0xFF];
                    ce.a(true, byArray, n3, (byte)25, (byte)26, br2);
                    System.arraycopy(byArray, n3, byArray2, 0, byArray2.length);
                    n3 += byArray2.length;
                    if (by6 <= 3) {
                        ce.h[by5 * 16 + by6 * 4 + by7] = byArray2;
                    }
                    if (by2 != 1 || by6 < 3) continue;
                    ce.i[(by6 - 3) * 4 + by7] = byArray2;
                }
                byArray = ce.a("/boss/atef/" + (by4 < 10 ? "0" : "") + by4);
                if (byArray == null) continue;
                ce.a(true, byArray, 0, (byte)25, (byte)26, br2);
                ce.f[ce.a((byte)by4)] = byArray;
            }
            return;
        }
        catch (IOException iOException) {
            IOException iOException2 = iOException;
            iOException.printStackTrace();
            return;
        }
    }

    public static final void m() {
        ce.a[25] = null;
        ce.a[26] = null;
        i = new Object[12];
    }

    public static final void n() {
        br br2 = null;
        m = new Image[6];
        n = new Image[5];
        try {
            int n2;
            br2 = new br("/sgui/gmico");
            new br("/sgui/gmico").a = true;
            for (n2 = 0; n2 < 6; n2 = (int)((byte)(n2 + 1))) {
                ce.m[n2] = br2.a(n2 == 5 ? 6 : n2);
            }
            for (n2 = 0; n2 < 5; n2 = (int)((byte)(n2 + 1))) {
                ce.n[n2] = br2.a(n2 + 7);
            }
            return;
        }
        catch (Exception exception) {
            System.out.println(exception);
            return;
        }
    }

    public static final void o() {
        br br2 = null;
        try {
            br2 = new br("/img/glb");
            new br("/img/glb").a = true;
            l = br2.a(0);
            o = br2.a(1);
            p = br2.a(2);
            e = br2.a(3);
            s = br2.a(5);
            m = br2.a(6);
            n = br2.a(7);
            b = br2.a(8);
            f = bh.a("_img_glb__9.png");
            g = bh.a("_img_glb__10.png");
            h = bh.a("_img_glb__11.png");
            i = bh.a("_img_glb__12.png");
            bh.a("_img_glb__13.png");
            j = br2.a(14);
            k = bh.a("_img_glb__15.png");
            br2.a(16);
            e = new z("/sgui/help");
            return;
        }
        catch (Exception exception) {
            System.out.println(exception);
            return;
        }
    }

    public static final void p() {
        try {
            br br2 = new br("/img/icoitm");
            d = br2.a();
            return;
        }
        catch (Exception exception) {
            Exception exception2 = exception;
            exception.printStackTrace();
            return;
        }
    }

    public static final void q() {
        d = null;
    }

    public static final void r() {
        r.k();
        o = new Image[6];
        br br2 = null;
        try {
            br2 = new br("/sgui/shop");
            new br("/sgui/shop").a = true;
            r.k();
            for (int n2 = 0; n2 < 6; n2 = (int)((byte)(n2 + 1))) {
                ce.o[n2] = br2.a(n2);
            }
            r = br2.a(6);
            q = br2.a(7);
            r.k();
            c = bh.a("_sgui_shop__8.png");
            d = bh.a("_sgui_shop__9.png");
            return;
        }
        catch (Exception exception) {
            System.out.println(exception);
            return;
        }
    }

    public static final void s() {
        o = null;
        r = null;
        q = null;
        c = null;
        d = null;
    }

    public static final void t() {
        b = new Image[6];
        c = new Image[24];
        br br2 = null;
        try {
            br2 = new br("/grd/grdico");
            new br("/grd/grdico").a = true;
            for (int n2 = 0; n2 < 6; n2 = (int)((byte)(n2 + 1))) {
                ce.b[n2] = br2.a(n2);
                for (int n3 = 0; n3 < 4; n3 = (int)((byte)(n3 + 1))) {
                    ce.c[n2 * 4 + n3] = br2.a(6 + n2 * 4 + n3);
                }
                r.k();
            }
            return;
        }
        catch (Exception exception) {
            System.out.println(exception);
            return;
        }
    }

    public static final void u() {
        b = null;
        c = null;
    }

    public static final byte[] a(byte by2, byte n2) {
        InputStream inputStream = null;
        byte[] byArray = null;
        String string = String.valueOf(by2);
        if (by2 < 10) {
            string = "0" + string;
        }
        try {
            inputStream = new Object().getClass().getResourceAsStream("/itm/" + string);
            int n3 = 0;
            for (int i2 = 0; i2 < n2; ++i2) {
                n3 = inputStream.read();
                inputStream.skip(n3);
            }
            n3 = inputStream.read();
            byArray = new byte[n3];
            inputStream.read(byArray);
            inputStream.close();
        }
        catch (IOException iOException) {
            IOException iOException2 = iOException;
            iOException.printStackTrace();
        }
        return byArray;
    }

    public static final byte[] a() {
        return ce.a("/itm/forshop");
    }

    public static final void f(byte by2) {
        r.k();
        ce.b(by2);
        switch (by2) {
            case 0: {
                ce.a(by2, (byte)0);
                ce.a(by2, (byte)1);
                return;
            }
            case 1: {
                ce.a(by2, (byte)0);
                ce.a(by2, (byte)1);
                return;
            }
            case 2: 
            case 3: 
            case 4: 
            case 5: {
                ce.a(by2, (byte)0);
                ce.a(by2, (byte)1);
                ce.a(by2, (byte)2);
            }
        }
    }

    public static final void v() {
        ce.k();
        ce.f();
    }

    public static final void w() {
        try {
            a = Image.createImage((String)"/handsonlogo.png");
            return;
        }
        catch (Exception exception) {
            System.out.println("error loading Handson Logo");
            return;
        }
    }

    public static final void x() {
        a = null;
    }

    public static final void y() {
        try {
            br br2 = new br("/img/title1");
            i = br2.a();
            r.k();
            br2 = new br("/img/title2");
            new br("/img/title2").a = true;
            j = new Image[10];
            for (int i2 = 0; i2 < 5; ++i2) {
                ce.j[i2] = br2.a(i2);
                ce.j[i2 + 5] = br2.b(i2);
                r.k();
            }
            r.k();
            bw.a((byte)22);
            return;
        }
        catch (IOException iOException) {
            IOException iOException2 = iOException;
            iOException.printStackTrace();
            return;
        }
    }

    public static final void z() {
        i = null;
        j = null;
        bw.b((byte)22);
    }

    public static final void A() {
        try {
            br br2 = new br("/sgui/mm/face");
            new br("/sgui/mm/face").a = true;
            l = new Image[6];
            ce.l[0] = br2.a(0);
            ce.l[1] = br2.a(1);
            ce.l[2] = br2.a(2);
            r.k();
            ce.l[3] = br2.c(0);
            ce.l[4] = br2.c(1);
            ce.l[5] = br2.c(2);
            r.k();
            br2 = new br("/sgui/mm/etc");
            k = br2.a();
            b = new Image[3][2];
            for (int i2 = 0; i2 < 3; ++i2) {
                br2 = new br("/grd/" + i2);
                new br("/grd/" + i2).a = true;
                ce.b[i2][0] = br2.a(0);
                ce.b[i2][1] = br2.a(1);
                r.k();
            }
            return;
        }
        catch (IOException iOException) {
            IOException iOException2 = iOException;
            iOException.printStackTrace();
            return;
        }
    }

    public static final void B() {
        k = null;
        l = null;
        b = null;
    }

    public static final byte[] a(String string) {
        Object object;
        System.gc();
        String string2 = string;
        InputStream inputStream = null;
        byte[] byArray = null;
        try {
            int n2;
            inputStream = new Object().getClass().getResourceAsStream(string);
            if (inputStream == null) {
                return null;
            }
            object = new ByteArrayOutputStream();
            while ((n2 = inputStream.read(n)) != -1) {
                ((ByteArrayOutputStream)object).write(n, 0, n2);
            }
            byArray = ((ByteArrayOutputStream)object).toByteArray();
            ((ByteArrayOutputStream)object).close();
        }
        catch (Exception exception) {
            String cfr_ignored_0 = "miss - " + string2;
            exception.printStackTrace();
        }
        while (n.e == 15) {
            try {
                Thread.sleep(100L);
            }
            catch (InterruptedException interruptedException) {
                object = interruptedException;
                interruptedException.printStackTrace();
            }
        }
        return byArray;
    }

    public static final byte a(byte by2) {
        for (byte by3 = 0; by3 < k.length; by3 = (byte)(by3 + 1)) {
            if (k[by3] != by2) continue;
            return by3;
        }
        return -1;
    }

    static {
        d = new Object[3];
        e = new Object[60];
        f = new Object[5];
        g = new Object[3];
        h = new Object[80];
        i = new Object[12];
        j = new Object[60];
        i = new byte[5];
        j = new byte[5];
        k = new byte[5];
        l = new byte[5];
        a = new Image[38][];
        n = new byte[512];
    }
}

