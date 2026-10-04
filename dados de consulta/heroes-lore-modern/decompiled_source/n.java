/*
 * Decompiled with CFR 0.152.
 */
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FilterInputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public final class n
implements u {
    private static byte[] h = new byte[]{60, 30, 10};
    private static byte[] i = new byte[]{5, 11, 8, 81, 3, 20};
    private static final byte[] j = new byte[]{0, 22, 4, 60, 5, 36, 77, 10, 18};
    public static final String[] a = new String[]{"/k", "/s", "/w"};
    public static ae a;
    public static int a;
    public static int b;
    public static int c;
    public static int d;
    private static ao a;
    public static byte a;
    public static int e;
    public static byte b;
    public static byte c;
    public static byte d;
    public static byte e;
    public static byte f;
    private static byte h;
    private static byte i;
    private static byte[] k;
    private static byte[] l;
    public static byte g;
    public static final boolean[][] a;

    private n() {
    }

    public static final void a() {
        g = 0;
        a.c(a);
        try {
            byte[] byArray = new byte[2];
            au au2 = new au("/o", 1);
            au2.b(byArray, 0, byArray.length);
            byte[] byArray2 = new byte[(byArray[0] & 0xFF) << 8 | byArray[1] & 0xFF];
            au2.b(byArray2, 0, byArray2.length);
            byArray2 = bq.b(byArray2, i);
            n.a.b.a(byArray2);
            au2.a();
        }
        catch (Exception exception) {}
        n.l();
        n.m();
        n.b(0);
        f = j[(a - 6) * 3];
        c = j[(a - 6) * 3 + 1];
        d = j[(a - 6) * 3 + 2];
    }

    public static final void b() {
        n.l();
        n.m();
        n.b(0);
        try {
            n.r();
            return;
        }
        catch (Exception exception) {
            Exception exception2 = exception;
            exception.printStackTrace();
            n.a();
            return;
        }
    }

    public static final void c() {
        n.a.f = (byte)(n.a.f + n.a(a));
        if (n.a.f > 100) {
            n.a.f = (byte)100;
        }
        g = 1;
        bs.a.b = true;
        try {
            bs.a.i();
        }
        catch (Exception exception) {}
        n.l();
        n.m();
        n.b(0);
        n.a.a.c();
        n.a.a = n.a.d;
        n.a.b = n.a.e;
        f = j[(a - 6) * 3];
        c = j[(a - 6) * 3 + 1];
        d = j[(a - 6) * 3 + 2];
    }

    public static final synchronized void a(byte by2, byte by3, byte by4, byte by5) {
        c = by3;
        d = by4;
        e = by5;
        b = by2;
    }

    public static final synchronized void a(byte by2, byte by3, byte by4) {
        c = by3;
        d = by4;
        e = 0;
        b = by2;
    }

    public static final synchronized void a(byte by2, byte by3) {
        c = by3;
        d = 0;
        e = 0;
        b = by2;
    }

    public static final synchronized void a(byte by2) {
        c = 0;
        d = 0;
        e = 0;
        b = by2;
    }

    public static final void d() {
        b = 0;
        c = 0;
        d = 0;
        e = 0;
    }

    public static final void e() {
        if (b == 0) {
            return;
        }
        byte by2 = b;
        b = 0;
        switch (by2) {
            case 1: {
                n.a(1);
                bs.a.g();
                bu.b();
                return;
            }
            case 11: {
                switch (c) {
                    case 0: {
                        n.a(6);
                        bp.a().d();
                        break;
                    }
                    case 1: {
                        n.a(7);
                        ax.a().d();
                        break;
                    }
                    case 2: {
                        n.a(8);
                        aa.a().d();
                    }
                }
                return;
            }
            case 12: {
                n.a(2);
                switch (c) {
                    case 0: {
                        break;
                    }
                    case 1: {
                        ax.a().e();
                        break;
                    }
                    case 2: {
                        aa.a().e();
                    }
                }
                return;
            }
            case 13: {
                System.out.println("Inventory opened!");
                n.a(5);
                ai.a().d();
                if ((!x.a || c != 1) && (!w.c || n.a.g < 8)) break;
                ai.a().e();
                return;
            }
            case 14: {
                System.out.println("Inventory closed!");
                if (c == 1) {
                    ai.a().a(false);
                    n.a(1);
                    bu.d();
                    return;
                }
                ai.a().a(true);
                return;
            }
            case 2: {
                n.a((int)c);
                if (d == 0) {
                    bs.a.a((int)e);
                    return;
                }
                if (d == 1) {
                    bs.a.f();
                    return;
                }
                if (d == 2) {
                    bs.a.g();
                    return;
                }
                if (d != 3) break;
                bs.a.h();
                return;
            }
            case 15: {
                n.f();
                return;
            }
            case 16: {
                n.a(10);
                bw.a((byte)12);
                bw.a((byte)12, false);
                as.e = 16;
                return;
            }
            case 21: {
                if (c == 1) {
                    n.b();
                } else if (c == 0) {
                    n.a();
                } else if (c == 2) {
                    n.c();
                    ai.a().a(false);
                    n.a(1);
                    bu.d();
                    bw.f();
                    return;
                }
                n.a(1);
                bs.a.g();
                bu.a();
            }
        }
    }

    public static final void b(byte by2, byte by3, byte by4, byte by5) {
        System.gc();
        n.a((byte)1, by3, by4, by5);
        bw.e();
        bw.f();
        f = by2;
    }

    public static final void f() {
        a.b(a);
        a.m();
        a.a();
        a.b((byte)(c + 1));
        n.g();
        c = a;
        d = b;
        n.d();
        f = (byte)-1;
        a.a((byte)1);
        a.j();
        bs.a.b();
        n.a((byte)2, (byte)2, (byte)1);
    }

    public static final void g() {
        a = as.c - ((ck)n.a).c;
        b = as.d - ((ck)n.a).d;
    }

    public static final void a(int n2) {
        e = n2;
    }

    public static final void a(ae ae2) {
        a = ae2;
    }

    public static final void a(boolean bl2, boolean bl3) {
        if (bl3) {
            byte by2 = n.b();
            if (bl2) {
                b -= 15 * u.b[by2];
                a -= 15 * u.a[by2];
            }
            if (!u.a[by2] && d != b) {
                d += (b - d + 1) / 2 - 1;
            }
            if (u.a[by2] && c != a) {
                c += (a - c + 1) / 2 - 1;
                return;
            }
        } else {
            c += (a - c + 1) / 2 - 1;
            d += (b - d + 1) / 2 - 1;
        }
    }

    public static final void b(byte by2) {
        if (n.a() == 1) {
            h = 0;
            i = 0;
            n.c((byte)2);
            n.d(by2);
            return;
        }
        if (n.a() == 2) {
            h = (byte)2;
            i = by2;
        }
    }

    public static final void h() {
        h = 1;
        i = n.b();
    }

    public static final void a(boolean bl2) {
        if (!a.a(bl2)) {
            return;
        }
        if (n.a() == 2) {
            h = (byte)3;
            i = n.b();
            return;
        }
        if (n.a() == 1) {
            a.a((byte)3);
            a.i();
        }
    }

    public static final void i() {
        n.q();
        n.n();
        a.b();
    }

    public static final void j() {
        ah.a();
    }

    private static final void q() {
        if (!n.b()) {
            return;
        }
        if (h == 0) {
            return;
        }
        n.c(h);
        n.d(i);
        h = 0;
        i = 0;
    }

    public static final void k() {
        h = 0;
    }

    public static final boolean a() {
        byte[] byArray = a.a(((ck)n.a).a, ((ck)n.a).b);
        if (byArray != null) {
            if (byArray[2] == -1) {
                int n2 = byArray[3] * 100 + byArray[4];
                if (n2 > 0) {
                    n2 *= 9;
                }
                a.g(n2);
                bs.a.a((bh.a + n2 + bh.d).toCharArray(), 16);
            } else if (byArray[2] == 22) {
                n.a.b.a(ad.a(byArray[2], byArray[3], true, true), (int)byArray[4]);
                char[] cArray = h.a(bh.a.toCharArray(), ad.b.a(byArray[2]));
                bs.a.a(cArray, 16);
            } else {
                ad ad2 = ad.a(byArray[2], byArray[3], true, true);
                if (ad2 instanceof e && !((e)ad2).a) {
                    ((e)ad2).b = true;
                }
                n.a.a.a(ad2, (int)byArray[4]);
                char[] cArray = h.a(bh.a.toCharArray(), ad.b.a(byArray[2]));
                bs.a.a(cArray, 16);
            }
            return true;
        }
        if (a.a(((ck)n.a).a, ((ck)n.a).b)) {
            bs.a.a(bh.t, 16);
        }
        return false;
    }

    public static final void l() {
        for (int i2 = 0; i2 < 128; ++i2) {
            n.k[i2] = 0;
        }
    }

    public static final boolean a(int n2) {
        byte by2 = k[n2 / 8];
        return (by2 >> n2 % 8 & 1) == 1;
    }

    public static final void b(int n2) {
        byte by2 = k[n2 / 8];
        n.k[n2 / 8] = (byte)(by2 | 1 << n2 % 8);
    }

    public static final void c(int n2) {
        byte by2 = k[n2 / 8];
        n.k[n2 / 8] = (byte)(by2 & ~(1 << n2 % 8));
    }

    public static final void d(int n2) {
        if (n.a(n2)) {
            n.c(n2);
            return;
        }
        n.b(n2);
    }

    public static final void m() {
        for (int i2 = 0; i2 < 128; ++i2) {
            n.l[i2] = 0;
        }
    }

    public static final boolean b(int n2) {
        byte by2 = l[n2 / 8];
        return (by2 >> n2 % 8 & 1) == 1;
    }

    public static final void e(int n2) {
        byte by2 = l[n2 / 8];
        n.l[n2 / 8] = (byte)(by2 | 1 << n2 % 8);
        if (n2 == 29 && a == 6) {
            ao ao2 = n.a();
            ao2.d((byte)2);
        }
    }

    public static final void f(int n2) {
        byte by2 = l[n2 / 8];
        n.l[n2 / 8] = (byte)(by2 & ~(1 << n2 % 8));
    }

    public static final void g(int n2) {
        if (n.b(n2)) {
            n.f(n2);
            return;
        }
        n.e(n2);
    }

    public static final ao a() {
        return a;
    }

    public static final byte a() {
        return ((o)n.a).h;
    }

    public static final byte b() {
        return ((o)n.a).i;
    }

    public static final void c(byte by2) {
        a.a(by2);
    }

    public static final void d(byte by2) {
        a.b(by2);
    }

    public static final void n() {
        a.d();
        a.c(a);
        p p2 = a.a();
        if (p2 != null) {
            p2.e();
            a.c(p2);
        }
    }

    public static final void a(int n2, int n3) {
        a.a((short)(n2 * 16), (short)(n3 * 16));
        a.g();
    }

    public static final boolean b() {
        return !((ck)n.a).a && !((ck)n.a).b;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Loose catch block
     */
    private static final byte[] a() {
        ByteArrayOutputStream byteArrayOutputStream = null;
        FilterOutputStream filterOutputStream = null;
        byteArrayOutputStream = new ByteArrayOutputStream();
        filterOutputStream = new DataOutputStream(byteArrayOutputStream);
        ((OutputStream)filterOutputStream).write(k);
        ((OutputStream)filterOutputStream).write(l);
        ((DataOutputStream)filterOutputStream).writeByte(g);
        byte[] byArray = byteArrayOutputStream.toByteArray();
        try {
            if (filterOutputStream != null) {
                filterOutputStream.close();
            }
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.close();
            }
        }
        catch (IOException iOException) {}
        return byArray;
        catch (IOException iOException) {
            try {
                IOException iOException2 = iOException;
                iOException.printStackTrace();
            }
            catch (Throwable throwable) {
                try {
                    if (filterOutputStream != null) {
                        filterOutputStream.close();
                    }
                    if (byteArrayOutputStream != null) {
                        byteArrayOutputStream.close();
                    }
                }
                catch (IOException iOException3) {}
                throw throwable;
            }
            try {
                if (filterOutputStream != null) {
                    filterOutputStream.close();
                }
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
            }
            catch (IOException iOException4) {}
        }
        return null;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Loose catch block
     */
    private static final void a(byte[] byArray) {
        ByteArrayInputStream byteArrayInputStream = null;
        FilterInputStream filterInputStream = null;
        byteArrayInputStream = new ByteArrayInputStream(byArray);
        filterInputStream = new DataInputStream(byteArrayInputStream);
        ((DataInputStream)filterInputStream).read(k);
        ((DataInputStream)filterInputStream).read(l);
        g = ((DataInputStream)filterInputStream).readByte();
        try {
            if (filterInputStream != null) {
                filterInputStream.close();
            }
            if (byteArrayInputStream != null) {
                byteArrayInputStream.close();
            }
        }
        catch (IOException iOException) {}
        return;
        catch (IOException iOException) {
            try {
                IOException iOException2 = iOException;
                iOException.printStackTrace();
            }
            catch (Throwable throwable) {
                try {
                    if (filterInputStream != null) {
                        filterInputStream.close();
                    }
                    if (byteArrayInputStream != null) {
                        byteArrayInputStream.close();
                    }
                }
                catch (IOException iOException3) {}
                throw throwable;
            }
            try {
                if (filterInputStream != null) {
                    filterInputStream.close();
                }
                if (byteArrayInputStream != null) {
                    byteArrayInputStream.close();
                }
            }
            catch (IOException iOException4) {}
            return;
        }
    }

    public static final void o() throws Exception {
        ao ao2 = a;
        byte[] byArray = ao2.a();
        byte[] byArray2 = ao2.a.c();
        byte[] byArray3 = n.a();
        byte[] byArray4 = new byte[]{n.a.a, ((ck)ao2).a, ((ck)ao2).b};
        int n2 = 0;
        byArray = bq.a(byArray, i);
        byArray2 = bq.a(byArray2, i);
        byArray3 = bq.a(byArray3, i);
        byArray4 = bq.a(byArray4, i);
        byte[] byArray5 = new byte[byArray.length + byArray2.length + byArray3.length + byArray4.length + 8];
        byte[] byArray6 = byArray5;
        byArray5[0] = (byte)((byArray.length & 0xFF00) >> 8);
        byArray6[1] = (byte)(byArray.length & 0xFF);
        System.arraycopy(byArray, 0, byArray6, 2, byArray.length);
        n2 = 2 + byArray.length;
        byArray6[n2++] = (byte)((byArray2.length & 0xFF00) >> 8);
        byArray6[n2++] = (byte)(byArray2.length & 0xFF);
        System.arraycopy(byArray2, 0, byArray6, n2, byArray2.length);
        n2 += byArray2.length;
        byArray6[n2++] = (byte)((byArray3.length & 0xFF00) >> 8);
        byArray6[n2++] = (byte)(byArray3.length & 0xFF);
        System.arraycopy(byArray3, 0, byArray6, n2, byArray3.length);
        n2 += byArray3.length;
        byArray6[n2++] = (byte)((byArray4.length & 0xFF00) >> 8);
        byArray6[n2++] = (byte)(byArray4.length & 0xFF);
        System.arraycopy(byArray4, 0, byArray6, n2, byArray4.length);
        Object object = new au(a[a - 6], 0);
        ((au)object).a(byArray6, 0, byArray6.length);
        ((au)object).a();
        byte[] byArray7 = ao2.b.c();
        object = byArray7;
        object = bq.a(byArray7, i);
        au au2 = new au("/o", 0);
        byArray6 = new byte[]{(byte)((((Object)object).length & 0xFF00) >> 8), (byte)(((Object)object).length & 0xFF)};
        au2.a(byArray6, 0, byArray6.length);
        au2.a((byte[])object, 0, ((Object)object).length);
        au2.a();
    }

    private static final void r() throws Exception {
        byte[] byArray = new byte[2];
        au au2 = new au(a[a - 6], 1);
        au2.b(byArray, 0, byArray.length);
        byte[] byArray2 = new byte[(byArray[0] & 0xFF) << 8 | byArray[1] & 0xFF];
        au2.b(byArray2, 0, byArray2.length);
        byArray2 = bq.b(byArray2, i);
        a.a(byArray2);
        au2.b(byArray, 0, byArray.length);
        byArray2 = new byte[(byArray[0] & 0xFF) << 8 | byArray[1] & 0xFF];
        au2.b(byArray2, 0, byArray2.length);
        byArray2 = bq.b(byArray2, i);
        n.a.a.a(byArray2);
        au2.b(byArray, 0, byArray.length);
        byArray2 = new byte[(byArray[0] & 0xFF) << 8 | byArray[1] & 0xFF];
        au2.b(byArray2, 0, byArray2.length);
        byArray2 = bq.b(byArray2, i);
        n.a(byArray2);
        au2.b(byArray, 0, byArray.length);
        byArray2 = new byte[(byArray[0] & 0xFF) << 8 | byArray[1] & 0xFF];
        au2.b(byArray2, 0, byArray2.length);
        byArray2 = bq.b(byArray2, i);
        f = byArray2[0];
        c = byArray2[1];
        d = byArray2[2];
        au2.a();
        au2 = new au("/o", 1);
        au2.b(byArray, 0, byArray.length);
        byArray2 = new byte[(byArray[0] & 0xFF) << 8 | byArray[1] & 0xFF];
        au2.b(byArray2, 0, byArray2.length);
        byArray2 = bq.b(byArray2, i);
        n.a.b.a(byArray2);
        au2.a();
    }

    private static final byte[] a(byte by2) {
        byte[] byArray = null;
        try {
            if (au.a(a[by2 - 6])) {
                au au2 = new au(a[by2 - 6], 1);
                byArray = new byte[au2.a()];
                au2.b(byArray, 0, byArray.length);
                au2.a();
            }
        }
        catch (Exception exception) {}
        return byArray;
    }

    public static final byte a(byte by2) {
        int n2;
        if (g >= 3) {
            return 0;
        }
        int n3 = 0;
        for (n2 = 0; n2 < 20; ++n2) {
            if (!n.b(1 + n2 * 3 + 1)) continue;
            n3 = (byte)(n3 + 1);
        }
        for (n2 = 100; n2 <= 105; ++n2) {
            if (!n.a(n2)) continue;
            n3 = (byte)(n3 + 1);
        }
        switch (by2) {
            case 6: {
                return (byte)(n3 * h[g] / 19);
            }
            case 7: {
                return (byte)(n3 * h[g] / 21);
            }
            case 8: {
                return (byte)(n3 * h[g] / 16);
            }
        }
        return 0;
    }

    public static final void p() {
        int n2 = 0;
        Object[] objectArray = new Object[3];
        for (byte by2 = 6; by2 <= 8; by2 = (byte)(by2 + 1)) {
            objectArray[by2 - 6] = n.a(by2);
            if (objectArray[by2 - 6] == null) continue;
            ++n2;
        }
        byte[] byArray = new byte[n2 * 4];
        int n3 = 0;
        for (byte by3 = 6; by3 <= 8; by3 = (byte)((byte)(by3 + 1))) {
            Object object;
            if (objectArray[by3 - 6] == null) continue;
            byte[] byArray2 = (byte[])objectArray[by3 - 6];
            int n4 = 0;
            try {
                short s2 = h.a(byArray2, 0);
                object = new byte[s2];
                System.arraycopy(byArray2, 2, object, 0, s2);
                n4 = 2 + s2;
                object = bq.b((byte[])object, i);
                s2 = h.a(byArray2, n4);
                n4 += 2 + s2;
                s2 = h.a(byArray2, n4);
                byte[] byArray3 = new byte[s2];
                System.arraycopy(byArray2, n4 += 2, byArray3, 0, s2);
                byArray3 = bq.b(byArray3, i);
                n.a(byArray3);
                byArray[n3++] = by3;
                byArray[n3++] = (byte)object[1];
                byArray[n3++] = (byte)(object[0] + n.a(by3));
                byArray[n3++] = g;
                continue;
            }
            catch (Exception exception) {
                object = exception;
                exception.printStackTrace();
            }
        }
        bf.a(n2 > 0, byArray);
        ((cb)bf.a()).b = n2 > 0 ? (byte)1 : 0;
        n.a((byte)2, (byte)9, (byte)3);
    }

    public static final void a(boolean bl2, byte by2, boolean[] blArray) {
        bf.d();
        ce.B();
        a = by2;
        a = new ao(0, 0, 8, 8, by2);
        if (!bl2) {
            if (blArray[0]) {
                a.a((byte)0);
            }
            if (blArray[1]) {
                a.a((byte)1);
            }
            if (blArray[2]) {
                a.a((byte)2);
            }
        }
        n.a(0);
        n.a((byte)21, bl2 ? (byte)1 : 0);
    }

    static {
        e = 0;
        b = 0;
        h = 0;
        i = 0;
        k = new byte[128];
        l = new byte[128];
        a = new boolean[][]{{true, true, true, true, true, true, false, false, false, false, false, false, false, false, false}, {true, false, true, false, false, false, false, true, true, true, true, false, false, true, true}, {true, true, true, true, false, false, false, false, false, false, false, false, false, false, false}};
    }
}

