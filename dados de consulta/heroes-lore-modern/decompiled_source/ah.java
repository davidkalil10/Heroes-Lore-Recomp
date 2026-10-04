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
public final class ah
implements u {
    private static byte a;
    private static int a;
    private static int b;
    private static boolean b;
    private static char[] a;
    private static int c;
    private static int d;
    private static int e;
    private static int f;
    private static boolean c;
    private static boolean d;
    private static byte[][] b;
    private static boolean e;
    private static byte b;
    private static byte c;
    private static byte d;
    private static byte[] h;
    private static byte e;
    private static byte f;
    private static char[] b;
    private static char[] c;
    private static boolean f;
    public static boolean a;
    private static boolean g;

    private ah() {
    }

    public static final boolean a(ao ao2) {
        if (((ck)ao2).a || ((ck)ao2).b) {
            return false;
        }
        byte by2 = n.a.c[((ck)ao2).b][((ck)ao2).a];
        if (by2 == 0) {
            return false;
        }
        return ah.a((byte)0, by2);
    }

    public static final boolean a() {
        ao ao2 = n.a();
        if (((ck)ao2).a || ((ck)ao2).b) {
            return false;
        }
        byte by2 = n.a.c[((ck)ao2).b][((ck)ao2).a];
        if ((by2 = (byte)Math.abs(by2)) >= 1 && by2 <= 127 && ah.a((byte)1, by2)) {
            return true;
        }
        by2 = n.a.c[((ck)ao2).b + u.b[((o)ao2).i]][((ck)ao2).a + u.a[((o)ao2).i]];
        return (by2 = (byte)Math.abs(by2)) >= 1 && by2 <= 127 && ah.a((byte)2, by2);
    }

    public static final boolean b() {
        ao ao2 = n.a();
        if (((ck)ao2).a || ((ck)ao2).b) {
            return false;
        }
        byte by2 = 0;
        by2 = n.a.c[((ck)ao2).b + u.b[((o)ao2).i]][((ck)ao2).a + u.a[((o)ao2).i]];
        if ((by2 = (byte)Math.abs(by2)) < 1 || by2 > 127) {
            return false;
        }
        return ah.a((byte)3, by2);
    }

    public static final void a(byte by2) {
        ah.a((byte)0, by2);
    }

    private static final boolean a(byte by2, byte by3) {
        byte[][] byArray = (byte[][])n.a.a[by3 - 1];
        byte[] byArray2 = null;
        for (int i2 = 0; !(i2 >= byArray.length || ((byArray2 = byArray[i2])[0] >> 6 & 3) == by2 && ah.a(byArray2)); ++i2) {
            byArray2 = null;
        }
        if (byArray2 == null) {
            return false;
        }
        if (byArray2[6] == -1) {
            return false;
        }
        ah.a((byte[][])n.a.b[byArray2[6]]);
        return true;
    }

    public static final boolean a(byte by2) {
        byte[][] byArray = (byte[][])n.a.a[by2 - 1];
        for (int i2 = 0; i2 < byArray.length; ++i2) {
            if (!ah.a(byArray[i2])) continue;
            return true;
        }
        return false;
    }

    private static final boolean a(byte[] byArray) {
        boolean bl2;
        ao ao2 = n.a();
        int n2 = (byArray[0] & 0xC) << 6 | byArray[1] & 0xFF;
        int n3 = (byArray[0] & 3) << 8 | byArray[2] & 0xFF;
        boolean bl3 = ((byArray[0] & 0xFF) >> 5 & 1) == 0;
        boolean bl4 = ((byArray[0] & 0xFF) >> 4 & 1) == 0;
        x.a(n2 != -1);
        x.a(n3 != -1);
        if (bl3 && !n.a(n2)) {
            return false;
        }
        if (!bl3 && !n.b(n2)) {
            return false;
        }
        if (bl4 && !n.a(n3)) {
            return false;
        }
        if (!bl4 && !n.b(n3)) {
            return false;
        }
        return byArray[3] == -1 || (bl2 = ao2.a.a(byArray[3], byArray[4], byArray[5]));
    }

    private static final void b(byte by2) {
        b = 0;
        a = by2;
    }

    private static final void a(byte[][] byArray) {
        a = false;
        b = byArray;
        n.a(4);
        n.k();
        a = 0;
        ah.b((byte)0);
        b = (byte)-1;
        e = true;
        f = 0;
    }

    private static final void c() {
        a = 0;
        b = null;
        a = false;
        b = (byte)-1;
        e = true;
        bs.a.b();
        ac[] acArray = n.a.a;
        for (int i2 = 0; i2 < acArray.length; ++i2) {
            if (acArray[i2] == null || !acArray[i2].d) continue;
            acArray[i2].g();
        }
        bw.c();
    }

    public static final void a(int n2, int n3) {
        if (n3 == bh.a) {
            a = true;
        }
        switch (a) {
            case 2: {
                if (n2 != 8 && n3 != 53) break;
                b = true;
                return;
            }
            case 3: {
                if (n2 == 6 || n2 == 1 || n3 == 50 || n3 == 56) {
                    c = !c;
                    return;
                }
                if (n2 != 8 && n3 != 53) break;
                d = true;
                return;
            }
            case 4: {
                if (n2 != 8 && n3 != 53) break;
                f = true;
            }
        }
    }

    public static final void a() {
        if (a != 3 && a) {
            b = true;
            f = true;
        }
        ao ao2 = n.a();
        if (a == 0) {
            switch (b[a][0]) {
                case 3: 
                case 4: 
                case 5: 
                case 6: 
                case 15: 
                case 16: 
                case 17: 
                case 34: 
                case 35: 
                case 36: 
                case 37: 
                case 38: 
                case 39: {
                    ah.b((byte)1);
                    break;
                }
                case 1: {
                    if (a) {
                        ++a;
                        break;
                    }
                    a = (char[])n.a.c[b[a][1]];
                    c = 0;
                    d = bh.a(a, c, r.g - 28, 3);
                    e = 0;
                    f = 0;
                    ah.b((byte)2);
                    break;
                }
                case 2: {
                    d = false;
                    c = true;
                    a = false;
                    ah.b((byte)3);
                    break;
                }
                case 99: {
                    ah.c();
                    n.a(2);
                    return;
                }
                case 45: {
                    n.a((byte)11, (byte)0);
                    ah.c();
                    return;
                }
                case 48: {
                    n.a((byte)11, (byte)1);
                    ah.c();
                    return;
                }
                case 44: {
                    n.a((byte)11, (byte)2);
                    ah.c();
                    return;
                }
                case 31: {
                    a += b[a][1];
                    break;
                }
                case 32: {
                    a -= b[a][1];
                    break;
                }
                case 7: {
                    ah.i();
                    ah.c();
                    break;
                }
                case 8: {
                    ah.s();
                    break;
                }
                case 9: {
                    ah.j();
                    break;
                }
                case 30: {
                    ah.k();
                    break;
                }
                case 19: {
                    ah.l();
                    break;
                }
                case 13: {
                    ah.m();
                    break;
                }
                case 14: {
                    ah.n();
                    break;
                }
                case 12: {
                    ah.o();
                    break;
                }
                case 10: {
                    ah.p();
                    break;
                }
                case 11: {
                    ah.q();
                    break;
                }
                case 18: {
                    ah.r();
                    break;
                }
                case 42: {
                    ah.t();
                    break;
                }
                case 40: {
                    ah.u();
                    break;
                }
                case 41: {
                    ah.v();
                    break;
                }
                case 46: {
                    ac ac2 = n.a.a[b[a++][1]];
                    n.a.a[b[a++][1]].d = false;
                    ac2.f();
                    break;
                }
                case 47: {
                    ac ac3 = n.a.a[b[a++][1]];
                    n.a.a[b[a++][1]].d = true;
                    ac3.a(ac3.c, ((ck)ac3).d);
                    break;
                }
                case 49: {
                    byte by2 = b[a][1];
                    ao2.c();
                    if (by2 != 0) {
                        ao2.a(new aw(10, -1, (short)(by2 - 1)));
                    }
                    ++a;
                    break;
                }
                case 50: {
                    ac ac4 = n.a.a[b[a][1]];
                    byte by3 = b[a][2];
                    ac4.c();
                    if (by3 != 0) {
                        ac4.a(new aw(10, -1, (short)(by3 - 1)));
                    }
                    ++a;
                    break;
                }
                case 22: {
                    f = 1;
                    ++a;
                    break;
                }
                case 23: {
                    f = 0;
                    ++a;
                    break;
                }
                case 25: {
                    h = new byte[6];
                    for (int n2 = 0; n2 < 6; n2 = (int)((byte)(n2 + 1))) {
                        ah.h[n2] = (byte)h.a(-5, 5);
                    }
                    ah.b((byte)5);
                    break;
                }
                case 24: {
                    e = (byte)5;
                    ah.b((byte)5);
                    break;
                }
                case 26: 
                case 29: {
                    byte by4 = b[a][1];
                    System.out.println("=====[EVENT BGM] " + by4);
                    if (by4 == 5 || by4 == 6 || by4 == 7) {
                        bw.c(by4);
                    } else if (by4 == 8) {
                        bw.a(by4, false);
                    }
                    ++a;
                    break;
                }
                case 27: {
                    bw.c();
                    ++a;
                    break;
                }
                case 20: {
                    ah.c();
                    bs.a.g();
                    n.a(12);
                }
            }
        }
        switch (a) {
            case 1: {
                ah.d();
                n.n();
                n.a.c();
                break;
            }
            case 3: {
                ah.f();
                break;
            }
            case 2: {
                ah.e();
                break;
            }
            case 4: {
                ah.g();
                break;
            }
            case 5: {
                ah.h();
            }
        }
        if (b != null && a >= b.length) {
            ah.c();
            n.a(2);
        }
    }

    private static final void d() {
        if (b > 0) {
            if (--b == 0) {
                ++a;
            }
            return;
        }
        ao ao2 = n.a();
        while (a < b.length && b[a][0] != 4) {
            switch (b[a][0]) {
                case 3: {
                    ao2.a((byte)2);
                    ao2.b(b[a][1]);
                    break;
                }
                case 5: {
                    ao2.a((byte)1);
                    break;
                }
                case 6: {
                    ao2.b(b[a][1]);
                    break;
                }
                case 15: {
                    ac ac2 = n.a.a[b[a][1]];
                    ac2.a((byte)2);
                    ac2.b(b[a][2]);
                    break;
                }
                case 16: {
                    ac ac2 = n.a.a[b[a][1]];
                    ac2.a((byte)1);
                    break;
                }
                case 17: {
                    ac ac2 = n.a.a[b[a][1]];
                    ac2.b(b[a][2]);
                    break;
                }
                case 34: {
                    ah.w();
                    e = true;
                    ah.b();
                    n.c = n.a;
                    n.d = n.b;
                    break;
                }
                case 35: {
                    ah.w();
                    break;
                }
                case 36: {
                    ah.w();
                    b = b[a][1];
                    break;
                }
                case 37: {
                    ah.w();
                    c = b[a][1];
                    d = b[a][2];
                    break;
                }
                case 38: {
                    ah.w();
                    break;
                }
                case 39: {
                    ah.w();
                    n.a = -(b[a][1] * 16) + r.i;
                    n.b = -(b[a][2] * 16) + r.j;
                    break;
                }
                default: {
                    ah.b((byte)0);
                    return;
                }
            }
            ++a;
        }
        if (a != b.length && (b = b[a][1] - 1) == 0) {
            ++a;
        }
    }

    private static final void e() {
        if (e < d) {
            if (b || bs.a.c) {
                e = d;
            } else if ((e = bh.a(a, c, e)) + 1 >= d) {
                e = d;
            }
        } else if (c + d >= a.length && b) {
            a = null;
            ++a;
            ah.b((byte)0);
        } else if (b) {
            c += d;
            d = bh.a(a, c, r.g - 28, 3);
            e = 0;
            f = 0;
        }
        b = false;
    }

    private static final void f() {
        if (g) {
            if (d) {
                if (c) {
                    try {
                        System.out.println("save!!!!!!");
                        n.o();
                    }
                    catch (Exception exception) {
                        Exception exception2 = exception;
                        exception.printStackTrace();
                    }
                }
                ah.i();
                ah.c();
                g = false;
            }
            return;
        }
        if (d) {
            if (c) {
                ++a;
                ah.b((byte)0);
                return;
            }
            a += b[a][2];
            ah.b((byte)0);
        }
    }

    private static final void g() {
        if (f) {
            b = null;
            c = null;
            ah.c();
            n.a(2);
        }
    }

    private static final void h() {
        switch (b[a][0]) {
            case 25: {
                if (h != null && h.length > 0) {
                    n.a.e = h[0];
                    n.a.f = h[1];
                    byte[] byArray = new byte[h.length - 2];
                    System.arraycopy(h, 2, byArray, 0, byArray.length);
                    h = byArray;
                    return;
                }
                ah.b((byte)0);
                ++a;
                return;
            }
            case 24: {
                if (e > 0) {
                    f = e % 2 == 1 ? (byte)2 : (byte)0;
                    e = (byte)(e - 1);
                    return;
                }
                ah.b((byte)0);
                ++a;
                f = 0;
            }
        }
    }

    private static final void i() {
        byte by2 = b[a][1];
        byte by3 = b[a][2];
        if (b[++a][0] != 8) {
            System.out.println("[ERROR:EventScript] No hero position after map change.");
            return;
        }
        byte by4 = b[a][1];
        byte by5 = b[a][2];
        n.b(by2, by3, by4, by5);
    }

    private static final void j() {
        int n2 = b[a][1] & 0xFF;
        n2 |= (b[a][2] & 0xFF) << 2 & 0x300;
        switch (b[a][2] & 3) {
            case 0: {
                n.b(n2);
                break;
            }
            case 1: {
                n.c(n2);
                break;
            }
            case 2: {
                n.d(n2);
            }
        }
        ++a;
    }

    private static final void k() {
        int n2 = b[a][1] & 0xFF;
        n2 |= (b[a][2] & 0xFF) << 2 & 0x300;
        switch (b[a][2] & 3) {
            case 0: {
                n.e(n2);
                break;
            }
            case 1: {
                n.f(n2);
                break;
            }
            case 2: {
                n.g(n2);
            }
        }
        ++a;
    }

    private static final void l() {
        n.a().d(b[a][1]);
        ++a;
    }

    private static final void m() {
        int n2 = b[a][2] & 0xFF;
        n.a().b(n2 |= (b[a][1] & 0xFF) << 8);
        ++a;
    }

    private static final void n() {
        int n2 = b[a][2] & 0xFF;
        n.a().d(n2 |= (b[a][1] & 0xFF) << 8);
        ++a;
    }

    private static final void o() {
        int n2 = b[a][2] & 0xFF;
        n.a().f(n2 |= (b[a][1] & 0xFF) << 8);
        ++a;
    }

    private static final void p() {
        int n2 = b[a][2] & 0xFF;
        n.a().g(n2 |= (b[a][1] & 0xFF) << 8);
        ++a;
    }

    private static final void q() {
        byte by2 = b[a][1];
        byte by3 = b[a][2];
        if (b[++a][0] != 21) {
            System.out.println("[ERROR:EventScript] No CMD_HANDLE_ITEM_NUM after CMD_HANDLE_ITEM.");
            return;
        }
        ao ao2 = n.a();
        byte by4 = b[a][1];
        ++a;
        if (by4 > 0) {
            ad ad2 = ad.a(by2, by3, true, true);
            if (ad2 instanceof e) {
                ((e)ad2).b = true;
            }
            if (!ao2.a.a(ad2, (int)by4)) {
                ah.a(cj.a.a(3938).toCharArray(), "".toCharArray());
                return;
            }
        } else if (by4 < 0) {
            by4 = -by4;
            if (ao2.a.a(by2, by3) >= by4) {
                ao2.a.a(by2, by3, by4);
                return;
            }
            ah.a(cj.a.a(3939).toCharArray(), "".toCharArray());
        }
    }

    private static final void r() {
        byte by2;
        switch (b[a][1]) {
            case 0: {
                by2 = 4;
                break;
            }
            case 1: {
                by2 = 3;
                break;
            }
            case 2: {
                by2 = 5;
                break;
            }
            default: {
                return;
            }
        }
        ++a;
        n.a().a(by2);
    }

    private static final void s() {
        ao ao2 = n.a();
        ao2.f();
        ao2.a((short)(b[a][1] * 16), (short)(b[a][2] * 16));
        ao2.g();
        ++a;
    }

    private static final void t() {
        ae ae2 = n.a;
        byte by2 = b[a][1];
        byte by3 = b[a][2];
        if (b[++a][0] != 43) {
            System.out.println("[ERROR:EventScript] No CMD_TILE_PROPERTY after CMD_CHANGE_TILE.");
            return;
        }
        byte by4 = b[a][1];
        byte by5 = b[a][2];
        ++a;
        ae2.c[by3][by2] = by5;
        ae2.b[by3][by2] = by4;
    }

    private static final void u() {
        ae ae2 = n.a;
        byte by2 = b[a][1];
        byte by3 = b[a][2];
        ++a;
        aj aj2 = ae2.a[by2];
        ae2.a[by2].a = ce.f[by3];
    }

    private static final void v() {
        ae ae2 = n.a;
        byte by2 = b[a][1];
        byte by3 = b[a][2];
        ++a;
        ae2.a[by2].f = by3;
    }

    public static final void a(Graphics graphics) {
        if (a == 0) {
            return;
        }
        n.a(false, false);
        if (a != 3 && a) {
            return;
        }
        if (f == 0) {
            n.a.a(graphics);
        } else {
            if (f == 1) {
                graphics.setColor(0);
            } else if (f == 2) {
                graphics.setColor(0xFFFFFF);
            }
            graphics.fillRect(0, 0, as.a, as.b);
        }
        bs.a.a(graphics);
        switch (a) {
            case 3: {
                ah.c(graphics);
                bh.a(graphics, bh.d, null);
                return;
            }
            case 2: {
                ah.b(graphics);
                bs.a.b();
                bh.a(graphics, bh.g, bh.f);
                return;
            }
            case 4: {
                ah.a(graphics, (r.g >> 1) - 60, (r.h >> 1) - 25, 120, 45, b, c);
                bh.a(graphics, bh.d, null);
            }
        }
    }

    private static final void b(Graphics graphics) {
        int n2 = r.g - 8;
        boolean bl2 = false;
        int n3 = r.i - n2 / 2;
        int n4 = r.h - 41 - 10;
        graphics.translate(n3, n4);
        graphics.setColor(0x1F1F3F);
        graphics.drawRect(0, 0, n2 - 1, 40);
        graphics.setColor(0x5F5F7F);
        graphics.drawRect(1, 1, n2 - 3, 38);
        graphics.setColor(0);
        graphics.fillRect(2, 2, n2 - 4, 37);
        graphics.drawImage(ce.q[0], 2, 2, 20);
        graphics.drawImage(ce.q[1], 0 + n2 - 2, 2, 24);
        graphics.drawImage(ce.q[2], 2, 39, 36);
        graphics.drawImage(ce.q[3], 0 + n2 - 2, 39, 40);
        graphics.setColor(0xFFFFFF);
        bh.c(graphics, 10, 5, n2 - 20, 1, a, c, f, e);
        f = e;
        graphics.translate(-n3, -n4);
        graphics.setClip(0, 0, r.g, r.h);
        byte by2 = b[a][2];
        if (by2 > 0) {
            graphics.drawImage(ce.h[by2 - 1], n3, n4, 36);
            return;
        }
        if (by2 < 0) {
            graphics.drawImage(ce.h[-by2 - 1], n3 + n2, n4, 40);
        }
    }

    private static final void c(Graphics graphics) {
        try {
            char[] cArray = g ? cj.a.a(3940).toCharArray() : (char[])n.a.c[b[a][1]];
            Object[] objectArray = new Object[]{cArray, cj.a.a(3915).toCharArray(), cj.a.a(3916).toCharArray()};
            int n2 = bh.a(r.g, 70);
            ah.a(graphics, r.i - (n2 >> 1), r.j - 30, n2, 60, objectArray, 6, 1, c ? 1 : 2);
            return;
        }
        catch (Exception exception) {
            return;
        }
    }

    public static final void a(Graphics graphics, int n2, int n3, int n4, int n5, Object[] objectArray, int n6, int n7, int n8) {
        cb.a(graphics, n2, n3, n4, n5);
        cb.b(graphics, n2, n3, n4, n5);
        graphics.setColor(255, 255, 255);
        n3 += 6;
        for (int i2 = 0; i2 < objectArray.length; ++i2) {
            if (i2 >= n7 && n7 != -1) {
                bh.a(graphics, n2 + n6 + 9, n3, (char[])objectArray[i2], 1);
                if (i2 == n8) {
                    graphics.drawImage(ce.e, n2 + n6, n3, 20);
                }
                n3 += bh.a() + 3;
                continue;
            }
            n3 += bh.a(graphics, n2 + n6, n3, n4 - n6 - n6, 1, (char[])objectArray[i2]);
            n3 += 5;
        }
    }

    public static final void a(Graphics graphics, int n2, int n3, int n4, int n5, char[] cArray, char[] cArray2) {
        cb.a(graphics, n2, n3, n4, n5);
        cb.b(graphics, n2, n3, n4, n5);
        graphics.setColor(255, 255, 255);
        bh.a(graphics, n2 + 6, n3 + 7, cArray, 1);
        bh.a(graphics, n2 + 6, n3 + 23, cArray2, 1);
    }

    private static void w() {
        e = false;
        b = (byte)-1;
        c = 0;
        d = 0;
    }

    private static final void a(char[] cArray, char[] cArray2) {
        b = cArray;
        c = cArray2;
        f = false;
        ah.b((byte)4);
    }

    public static final void b() {
        if (e) {
            n.g();
            return;
        }
        if (!e && b == -1 && c != 0) {
            n.a -= d * u.a[c];
            n.b -= d * u.b[c];
        }
    }

    static {
        String[] stringArray = new String[]{"CMD_IDLE", "TALKTEXT", "YES/NO  ", "MV_H_MOV", "MV_DELAY", "MV_H_STP", "MV_H_DIR", "MAP_CHNG", "MAP_HERO", "SWI_DEF ", "MONEY   ", "ITEM    ", "EXP     ", "HP      ", "SP      ", "MV_N_MOV", "MV_N_STP", "MV_N_DIR", "GUARDIAN", "COMBO   ", "GAMEOVER", "ITEM_NUM", "SCR_DEL ", "SCR_SHOW", "SCR_FLAS", "SCR_SHAK", "BGM_PLAY", "BGM_STOP", "SYSBGM  ", "SOUND   ", "SWI_QUE ", "GOTO_FOR", "GOTO_BAK", "SWI_MAP ", "MV_FO_HE", "MV_FO_NO", "MV_FO_NP", "MV_CA_MV", "MV_CA_ST", "MV_CA_XY", "CHG_OBJ ", "CHG_NPC ", "CHGTL_XY", "CHGTL_VA", "OPN_BLAK", "OPEN_SHP", "HIDE_NPC", "SHOW_NPC", "OPN_REFI", "EMO_HERO", "EMO_NPC ", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, "END_EVNT"};
        a = 0;
        a = 0;
        b = 0;
        b = false;
        a = null;
        c = 0;
        d = 0;
        e = 0;
        f = 0;
        c = true;
        d = false;
        e = true;
        b = (byte)-1;
        c = 0;
        d = 0;
        f = 0;
        a = false;
    }
}

