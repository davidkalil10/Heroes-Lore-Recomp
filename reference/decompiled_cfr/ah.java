/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 */
import javax.microedition.lcdui.Graphics;

public final class ah
implements u {
    private static byte var_byte_a;
    private static int var_int_a;
    private static int var_int_b;
    private static boolean var_boolean_b;
    private static char[] var_char_arr_a;
    private static int var_int_c;
    private static int var_int_d;
    private static int var_int_e;
    private static int var_int_f;
    private static boolean var_boolean_c;
    private static boolean var_boolean_d;
    private static byte[][] var_byte_arr_arr_b;
    private static boolean var_boolean_e;
    private static byte var_byte_b;
    private static byte var_byte_c;
    private static byte var_byte_d;
    private static byte[] h;
    private static byte var_byte_e;
    private static byte var_byte_f;
    private static char[] var_char_arr_b;
    private static char[] var_char_arr_c;
    private static boolean var_boolean_f;
    public static boolean var_boolean_a;
    private static boolean g;

    private ah() {
    }

    public static final boolean a(ao ao2) {
        if (ao2.var_boolean_a || ao2.var_boolean_b) {
            return false;
        }
        byte by2 = n.var_ae_a.var_byte_arr_arr_c[ao2.var_byte_b][ao2.var_byte_a];
        if (by2 == 0) {
            return false;
        }
        return ah.a((byte)0, by2);
    }

    public static final boolean boolean_a() {
        ao ao2 = n.ao_a();
        if (ao2.var_boolean_a || ao2.var_boolean_b) {
            return false;
        }
        byte by2 = n.var_ae_a.var_byte_arr_arr_c[ao2.var_byte_b][ao2.var_byte_a];
        if ((by2 = (byte)Math.abs(by2)) >= 1 && by2 <= 127 && ah.a((byte)1, by2)) {
            return true;
        }
        by2 = n.var_ae_a.var_byte_arr_arr_c[ao2.var_byte_b + u.b[((o)ao2).i]][ao2.var_byte_a + u.var_byte_arr_a[((o)ao2).i]];
        return (by2 = (byte)Math.abs(by2)) >= 1 && by2 <= 127 && ah.a((byte)2, by2);
    }

    public static final boolean boolean_b() {
        ao ao2 = n.ao_a();
        if (ao2.var_boolean_a || ao2.var_boolean_b) {
            return false;
        }
        byte by2 = 0;
        by2 = n.var_ae_a.var_byte_arr_arr_c[ao2.var_byte_b + u.b[((o)ao2).i]][ao2.var_byte_a + u.var_byte_arr_a[((o)ao2).i]];
        if ((by2 = (byte)Math.abs(by2)) < 1 || by2 > 127) {
            return false;
        }
        return ah.a((byte)3, by2);
    }

    public static final void void_a(byte by2) {
        ah.a((byte)0, by2);
    }

    private static final boolean a(byte by2, byte by3) {
        byte[][] byArray = (byte[][])n.var_ae_a.var_java_lang_Object_arr_a[by3 - 1];
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
        ah.a((byte[][])n.var_ae_a.var_java_lang_Object_arr_b[byArray2[6]]);
        return true;
    }

    public static final boolean boolean_a(byte by2) {
        byte[][] byArray = (byte[][])n.var_ae_a.var_java_lang_Object_arr_a[by2 - 1];
        for (int i2 = 0; i2 < byArray.length; ++i2) {
            if (!ah.a(byArray[i2])) continue;
            return true;
        }
        return false;
    }

    private static final boolean a(byte[] byArray) {
        boolean bl2;
        ao ao2 = n.ao_a();
        int n2 = (byArray[0] & 0xC) << 6 | byArray[1] & 0xFF;
        int n3 = (byArray[0] & 3) << 8 | byArray[2] & 0xFF;
        boolean bl3 = ((byArray[0] & 0xFF) >> 5 & 1) == 0;
        boolean bl4 = ((byArray[0] & 0xFF) >> 4 & 1) == 0;
        x.a(n2 != -1);
        x.a(n3 != -1);
        if (bl3 && !n.boolean_a(n2)) {
            return false;
        }
        if (!bl3 && !n.boolean_b(n2)) {
            return false;
        }
        if (bl4 && !n.boolean_a(n3)) {
            return false;
        }
        if (!bl4 && !n.boolean_b(n3)) {
            return false;
        }
        return byArray[3] == -1 || (bl2 = ao2.var_g_a.boolean_a(byArray[3], byArray[4], byArray[5]));
    }

    private static final void b(byte by2) {
        var_int_b = 0;
        var_byte_a = by2;
    }

    private static final void a(byte[][] byArray) {
        var_boolean_a = false;
        var_byte_arr_arr_b = byArray;
        n.void_a(4);
        n.k();
        var_int_a = 0;
        ah.b((byte)0);
        var_byte_b = (byte)-1;
        var_boolean_e = true;
        var_byte_f = 0;
    }

    private static final void c() {
        var_byte_a = 0;
        var_byte_arr_arr_b = null;
        var_boolean_a = false;
        var_byte_b = (byte)-1;
        var_boolean_e = true;
        bs.var_as_a.b();
        ac[] acArray = n.var_ae_a.var_ac_arr_a;
        for (int i2 = 0; i2 < acArray.length; ++i2) {
            if (acArray[i2] == null || !acArray[i2].d) continue;
            acArray[i2].g();
        }
        bw.c();
    }

    public static final void a(int n2, int n3) {
        if (n3 == bh.var_int_a) {
            var_boolean_a = true;
        }
        switch (var_byte_a) {
            case 2: {
                if (n2 != 8 && n3 != 53) break;
                var_boolean_b = true;
                return;
            }
            case 3: {
                if (n2 == 6 || n2 == 1 || n3 == 50 || n3 == 56) {
                    var_boolean_c = !var_boolean_c;
                    return;
                }
                if (n2 != 8 && n3 != 53) break;
                var_boolean_d = true;
                return;
            }
            case 4: {
                if (n2 != 8 && n3 != 53) break;
                var_boolean_f = true;
            }
        }
    }

    public static final void void_a() {
        if (var_byte_a != 3 && var_boolean_a) {
            var_boolean_b = true;
            var_boolean_f = true;
        }
        ao ao2 = n.ao_a();
        if (var_byte_a == 0) {
            switch (var_byte_arr_arr_b[var_int_a][0]) {
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
                    if (var_boolean_a) {
                        ++var_int_a;
                        break;
                    }
                    var_char_arr_a = (char[])n.var_ae_a.var_java_lang_Object_arr_c[var_byte_arr_arr_b[var_int_a][1]];
                    var_int_c = 0;
                    var_int_d = bh.a(var_char_arr_a, var_int_c, r.g - 28, 3);
                    var_int_e = 0;
                    var_int_f = 0;
                    ah.b((byte)2);
                    break;
                }
                case 2: {
                    var_boolean_d = false;
                    var_boolean_c = true;
                    var_boolean_a = false;
                    ah.b((byte)3);
                    break;
                }
                case 99: {
                    ah.c();
                    n.void_a(2);
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
                    var_int_a += var_byte_arr_arr_b[var_int_a][1];
                    break;
                }
                case 32: {
                    var_int_a -= var_byte_arr_arr_b[var_int_a][1];
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
                    ac ac2 = n.var_ae_a.var_ac_arr_a[var_byte_arr_arr_b[var_int_a++][1]];
                    n.var_ae_a.var_ac_arr_a[var_byte_arr_arr_b[var_int_a++][1]].d = false;
                    ac2.f();
                    break;
                }
                case 47: {
                    ac ac3 = n.var_ae_a.var_ac_arr_a[var_byte_arr_arr_b[var_int_a++][1]];
                    n.var_ae_a.var_ac_arr_a[var_byte_arr_arr_b[var_int_a++][1]].d = true;
                    ac3.a(ac3.var_short_c, ac3.var_short_d);
                    break;
                }
                case 49: {
                    byte by2 = var_byte_arr_arr_b[var_int_a][1];
                    ao2.void_c();
                    if (by2 != 0) {
                        ao2.a(new aw(10, -1, (short)(by2 - 1)));
                    }
                    ++var_int_a;
                    break;
                }
                case 50: {
                    ac ac4 = n.var_ae_a.var_ac_arr_a[var_byte_arr_arr_b[var_int_a][1]];
                    byte by3 = var_byte_arr_arr_b[var_int_a][2];
                    ac4.void_c();
                    if (by3 != 0) {
                        ac4.a(new aw(10, -1, (short)(by3 - 1)));
                    }
                    ++var_int_a;
                    break;
                }
                case 22: {
                    var_byte_f = 1;
                    ++var_int_a;
                    break;
                }
                case 23: {
                    var_byte_f = 0;
                    ++var_int_a;
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
                    var_byte_e = (byte)5;
                    ah.b((byte)5);
                    break;
                }
                case 26: 
                case 29: {
                    byte by4 = var_byte_arr_arr_b[var_int_a][1];
                    System.out.println("=====[EVENT BGM] " + by4);
                    if (by4 == 5 || by4 == 6 || by4 == 7) {
                        bw.c(by4);
                    } else if (by4 == 8) {
                        bw.a(by4, false);
                    }
                    ++var_int_a;
                    break;
                }
                case 27: {
                    bw.c();
                    ++var_int_a;
                    break;
                }
                case 20: {
                    ah.c();
                    bs.var_as_a.g();
                    n.void_a(12);
                }
            }
        }
        switch (var_byte_a) {
            case 1: {
                ah.d();
                n.n();
                n.var_ae_a.c();
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
        if (var_byte_arr_arr_b != null && var_int_a >= var_byte_arr_arr_b.length) {
            ah.c();
            n.void_a(2);
        }
    }

    private static final void d() {
        if (var_int_b > 0) {
            if (--var_int_b == 0) {
                ++var_int_a;
            }
            return;
        }
        ao ao2 = n.ao_a();
        while (var_int_a < var_byte_arr_arr_b.length && var_byte_arr_arr_b[var_int_a][0] != 4) {
            switch (var_byte_arr_arr_b[var_int_a][0]) {
                case 3: {
                    ao2.void_a((byte)2);
                    ao2.b(var_byte_arr_arr_b[var_int_a][1]);
                    break;
                }
                case 5: {
                    ao2.void_a((byte)1);
                    break;
                }
                case 6: {
                    ao2.b(var_byte_arr_arr_b[var_int_a][1]);
                    break;
                }
                case 15: {
                    ac ac2 = n.var_ae_a.var_ac_arr_a[var_byte_arr_arr_b[var_int_a][1]];
                    ac2.void_a((byte)2);
                    ac2.b(var_byte_arr_arr_b[var_int_a][2]);
                    break;
                }
                case 16: {
                    ac ac2 = n.var_ae_a.var_ac_arr_a[var_byte_arr_arr_b[var_int_a][1]];
                    ac2.void_a((byte)1);
                    break;
                }
                case 17: {
                    ac ac2 = n.var_ae_a.var_ac_arr_a[var_byte_arr_arr_b[var_int_a][1]];
                    ac2.b(var_byte_arr_arr_b[var_int_a][2]);
                    break;
                }
                case 34: {
                    ah.w();
                    var_boolean_e = true;
                    ah.void_b();
                    n.var_int_c = n.var_int_a;
                    n.var_int_d = n.var_int_b;
                    break;
                }
                case 35: {
                    ah.w();
                    break;
                }
                case 36: {
                    ah.w();
                    var_byte_b = var_byte_arr_arr_b[var_int_a][1];
                    break;
                }
                case 37: {
                    ah.w();
                    var_byte_c = var_byte_arr_arr_b[var_int_a][1];
                    var_byte_d = var_byte_arr_arr_b[var_int_a][2];
                    break;
                }
                case 38: {
                    ah.w();
                    break;
                }
                case 39: {
                    ah.w();
                    n.var_int_a = -(var_byte_arr_arr_b[var_int_a][1] * 16) + r.i;
                    n.var_int_b = -(var_byte_arr_arr_b[var_int_a][2] * 16) + r.j;
                    break;
                }
                default: {
                    ah.b((byte)0);
                    return;
                }
            }
            ++var_int_a;
        }
        if (var_int_a != var_byte_arr_arr_b.length && (var_int_b = var_byte_arr_arr_b[var_int_a][1] - 1) == 0) {
            ++var_int_a;
        }
    }

    private static final void e() {
        if (var_int_e < var_int_d) {
            if (var_boolean_b || bs.var_bs_a.var_boolean_c) {
                var_int_e = var_int_d;
            } else if ((var_int_e = bh.a(var_char_arr_a, var_int_c, var_int_e)) + 1 >= var_int_d) {
                var_int_e = var_int_d;
            }
        } else if (var_int_c + var_int_d >= var_char_arr_a.length && var_boolean_b) {
            var_char_arr_a = null;
            ++var_int_a;
            ah.b((byte)0);
        } else if (var_boolean_b) {
            var_int_c += var_int_d;
            var_int_d = bh.a(var_char_arr_a, var_int_c, r.g - 28, 3);
            var_int_e = 0;
            var_int_f = 0;
        }
        var_boolean_b = false;
    }

    private static final void f() {
        if (g) {
            if (var_boolean_d) {
                if (var_boolean_c) {
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
        if (var_boolean_d) {
            if (var_boolean_c) {
                ++var_int_a;
                ah.b((byte)0);
                return;
            }
            var_int_a += var_byte_arr_arr_b[var_int_a][2];
            ah.b((byte)0);
        }
    }

    private static final void g() {
        if (var_boolean_f) {
            var_char_arr_b = null;
            var_char_arr_c = null;
            ah.c();
            n.void_a(2);
        }
    }

    private static final void h() {
        switch (var_byte_arr_arr_b[var_int_a][0]) {
            case 25: {
                if (h != null && h.length > 0) {
                    n.var_ae_a.var_int_e = h[0];
                    n.var_ae_a.f = h[1];
                    byte[] byArray = new byte[h.length - 2];
                    System.arraycopy(h, 2, byArray, 0, byArray.length);
                    h = byArray;
                    return;
                }
                ah.b((byte)0);
                ++var_int_a;
                return;
            }
            case 24: {
                if (var_byte_e > 0) {
                    var_byte_f = var_byte_e % 2 == 1 ? (byte)2 : (byte)0;
                    var_byte_e = (byte)(var_byte_e - 1);
                    return;
                }
                ah.b((byte)0);
                ++var_int_a;
                var_byte_f = 0;
            }
        }
    }

    private static final void i() {
        byte by2 = var_byte_arr_arr_b[var_int_a][1];
        byte by3 = var_byte_arr_arr_b[var_int_a][2];
        if (var_byte_arr_arr_b[++var_int_a][0] != 8) {
            System.out.println("[ERROR:EventScript] No hero position after map change.");
            return;
        }
        byte by4 = var_byte_arr_arr_b[var_int_a][1];
        byte by5 = var_byte_arr_arr_b[var_int_a][2];
        n.b(by2, by3, by4, by5);
    }

    private static final void j() {
        int n2 = var_byte_arr_arr_b[var_int_a][1] & 0xFF;
        n2 |= (var_byte_arr_arr_b[var_int_a][2] & 0xFF) << 2 & 0x300;
        switch (var_byte_arr_arr_b[var_int_a][2] & 3) {
            case 0: {
                n.void_b(n2);
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
        ++var_int_a;
    }

    private static final void k() {
        int n2 = var_byte_arr_arr_b[var_int_a][1] & 0xFF;
        n2 |= (var_byte_arr_arr_b[var_int_a][2] & 0xFF) << 2 & 0x300;
        switch (var_byte_arr_arr_b[var_int_a][2] & 3) {
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
        ++var_int_a;
    }

    private static final void l() {
        n.ao_a().d(var_byte_arr_arr_b[var_int_a][1]);
        ++var_int_a;
    }

    private static final void m() {
        int n2 = var_byte_arr_arr_b[var_int_a][2] & 0xFF;
        n.ao_a().b(n2 |= (var_byte_arr_arr_b[var_int_a][1] & 0xFF) << 8);
        ++var_int_a;
    }

    private static final void n() {
        int n2 = var_byte_arr_arr_b[var_int_a][2] & 0xFF;
        n.ao_a().d(n2 |= (var_byte_arr_arr_b[var_int_a][1] & 0xFF) << 8);
        ++var_int_a;
    }

    private static final void o() {
        int n2 = var_byte_arr_arr_b[var_int_a][2] & 0xFF;
        n.ao_a().f(n2 |= (var_byte_arr_arr_b[var_int_a][1] & 0xFF) << 8);
        ++var_int_a;
    }

    private static final void p() {
        int n2 = var_byte_arr_arr_b[var_int_a][2] & 0xFF;
        n.ao_a().g(n2 |= (var_byte_arr_arr_b[var_int_a][1] & 0xFF) << 8);
        ++var_int_a;
    }

    private static final void q() {
        byte by2 = var_byte_arr_arr_b[var_int_a][1];
        byte by3 = var_byte_arr_arr_b[var_int_a][2];
        if (var_byte_arr_arr_b[++var_int_a][0] != 21) {
            System.out.println("[ERROR:EventScript] No CMD_HANDLE_ITEM_NUM after CMD_HANDLE_ITEM.");
            return;
        }
        ao ao2 = n.ao_a();
        byte by4 = var_byte_arr_arr_b[var_int_a][1];
        ++var_int_a;
        if (by4 > 0) {
            ad ad2 = ad.a(by2, by3, true, true);
            if (ad2 instanceof e) {
                ((e)ad2).b = true;
            }
            if (!ao2.var_g_a.a(ad2, (int)by4)) {
                ah.a(cj.var_cj_a.a(3938).toCharArray(), "".toCharArray());
                return;
            }
        } else if (by4 < 0) {
            by4 = -by4;
            if (ao2.var_g_a.int_a(by2, by3) >= by4) {
                ao2.var_g_a.void_a(by2, by3, by4);
                return;
            }
            ah.a(cj.var_cj_a.a(3939).toCharArray(), "".toCharArray());
        }
    }

    private static final void r() {
        byte by2;
        switch (var_byte_arr_arr_b[var_int_a][1]) {
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
        ++var_int_a;
        n.ao_a().p_a(by2);
    }

    private static final void s() {
        ao ao2 = n.ao_a();
        ao2.f();
        ao2.a((short)(var_byte_arr_arr_b[var_int_a][1] * 16), (short)(var_byte_arr_arr_b[var_int_a][2] * 16));
        ao2.g();
        ++var_int_a;
    }

    private static final void t() {
        ae ae2 = n.var_ae_a;
        byte by2 = var_byte_arr_arr_b[var_int_a][1];
        byte by3 = var_byte_arr_arr_b[var_int_a][2];
        if (var_byte_arr_arr_b[++var_int_a][0] != 43) {
            System.out.println("[ERROR:EventScript] No CMD_TILE_PROPERTY after CMD_CHANGE_TILE.");
            return;
        }
        byte by4 = var_byte_arr_arr_b[var_int_a][1];
        byte by5 = var_byte_arr_arr_b[var_int_a][2];
        ++var_int_a;
        ae2.var_byte_arr_arr_c[by3][by2] = by5;
        ae2.var_byte_arr_arr_b[by3][by2] = by4;
    }

    private static final void u() {
        ae ae2 = n.var_ae_a;
        byte by2 = var_byte_arr_arr_b[var_int_a][1];
        byte by3 = var_byte_arr_arr_b[var_int_a][2];
        ++var_int_a;
        aj aj2 = ae2.var_aj_arr_a[by2];
        ae2.var_aj_arr_a[by2].var_javax_microedition_lcdui_Image_a = ce.var_javax_microedition_lcdui_Image_arr_f[by3];
    }

    private static final void v() {
        ae ae2 = n.var_ae_a;
        byte by2 = var_byte_arr_arr_b[var_int_a][1];
        byte by3 = var_byte_arr_arr_b[var_int_a][2];
        ++var_int_a;
        ae2.var_ac_arr_a[by2].f = by3;
    }

    public static final void a(Graphics graphics) {
        if (var_byte_a == 0) {
            return;
        }
        n.a(false, false);
        if (var_byte_a != 3 && var_boolean_a) {
            return;
        }
        if (var_byte_f == 0) {
            n.var_ae_a.a(graphics);
        } else {
            if (var_byte_f == 1) {
                graphics.setColor(0);
            } else if (var_byte_f == 2) {
                graphics.setColor(0xFFFFFF);
            }
            graphics.fillRect(0, 0, as.var_int_a, as.b);
        }
        bs.var_as_a.a(graphics);
        switch (var_byte_a) {
            case 3: {
                ah.c(graphics);
                bh.a(graphics, bh.var_char_arr_d, null);
                return;
            }
            case 2: {
                ah.b(graphics);
                bs.var_as_a.b();
                bh.a(graphics, bh.var_char_arr_g, bh.var_char_arr_f);
                return;
            }
            case 4: {
                ah.a(graphics, (r.g >> 1) - 60, (r.h >> 1) - 25, 120, 45, var_char_arr_b, var_char_arr_c);
                bh.a(graphics, bh.var_char_arr_d, null);
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
        graphics.drawImage(ce.var_javax_microedition_lcdui_Image_arr_q[0], 2, 2, 20);
        graphics.drawImage(ce.var_javax_microedition_lcdui_Image_arr_q[1], 0 + n2 - 2, 2, 24);
        graphics.drawImage(ce.var_javax_microedition_lcdui_Image_arr_q[2], 2, 39, 36);
        graphics.drawImage(ce.var_javax_microedition_lcdui_Image_arr_q[3], 0 + n2 - 2, 39, 40);
        graphics.setColor(0xFFFFFF);
        bh.c(graphics, 10, 5, n2 - 20, 1, var_char_arr_a, var_int_c, var_int_f, var_int_e);
        var_int_f = var_int_e;
        graphics.translate(-n3, -n4);
        graphics.setClip(0, 0, r.g, r.h);
        byte by2 = var_byte_arr_arr_b[var_int_a][2];
        if (by2 > 0) {
            graphics.drawImage(ce.var_javax_microedition_lcdui_Image_arr_h[by2 - 1], n3, n4, 36);
            return;
        }
        if (by2 < 0) {
            graphics.drawImage(ce.var_javax_microedition_lcdui_Image_arr_h[-by2 - 1], n3 + n2, n4, 40);
        }
    }

    private static final void c(Graphics graphics) {
        try {
            char[] cArray = g ? cj.var_cj_a.a(3940).toCharArray() : (char[])n.var_ae_a.var_java_lang_Object_arr_c[var_byte_arr_arr_b[var_int_a][1]];
            Object[] objectArray = new Object[]{cArray, cj.var_cj_a.a(3915).toCharArray(), cj.var_cj_a.a(3916).toCharArray()};
            int n2 = bh.a(r.g, 70);
            ah.a(graphics, r.i - (n2 >> 1), r.j - 30, n2, 60, objectArray, 6, 1, var_boolean_c ? 1 : 2);
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
                bh.int_a(graphics, n2 + n6 + 9, n3, (char[])objectArray[i2], 1);
                if (i2 == n8) {
                    graphics.drawImage(ce.var_javax_microedition_lcdui_Image_e, n2 + n6, n3, 20);
                }
                n3 += bh.int_a() + 3;
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
        bh.int_a(graphics, n2 + 6, n3 + 7, cArray, 1);
        bh.int_a(graphics, n2 + 6, n3 + 23, cArray2, 1);
    }

    private static void w() {
        var_boolean_e = false;
        var_byte_b = (byte)-1;
        var_byte_c = 0;
        var_byte_d = 0;
    }

    private static final void a(char[] cArray, char[] cArray2) {
        var_char_arr_b = cArray;
        var_char_arr_c = cArray2;
        var_boolean_f = false;
        ah.b((byte)4);
    }

    public static final void void_b() {
        if (var_boolean_e) {
            n.g();
            return;
        }
        if (!var_boolean_e && var_byte_b == -1 && var_byte_c != 0) {
            n.var_int_a -= var_byte_d * u.var_byte_arr_a[var_byte_c];
            n.var_int_b -= var_byte_d * u.b[var_byte_c];
        }
    }

    static {
        String[] stringArray = new String[]{"CMD_IDLE", "TALKTEXT", "YES/NO  ", "MV_H_MOV", "MV_DELAY", "MV_H_STP", "MV_H_DIR", "MAP_CHNG", "MAP_HERO", "SWI_DEF ", "MONEY   ", "ITEM    ", "EXP     ", "HP      ", "SP      ", "MV_N_MOV", "MV_N_STP", "MV_N_DIR", "GUARDIAN", "COMBO   ", "GAMEOVER", "ITEM_NUM", "SCR_DEL ", "SCR_SHOW", "SCR_FLAS", "SCR_SHAK", "BGM_PLAY", "BGM_STOP", "SYSBGM  ", "SOUND   ", "SWI_QUE ", "GOTO_FOR", "GOTO_BAK", "SWI_MAP ", "MV_FO_HE", "MV_FO_NO", "MV_FO_NP", "MV_CA_MV", "MV_CA_ST", "MV_CA_XY", "CHG_OBJ ", "CHG_NPC ", "CHGTL_XY", "CHGTL_VA", "OPN_BLAK", "OPEN_SHP", "HIDE_NPC", "SHOW_NPC", "OPN_REFI", "EMO_HERO", "EMO_NPC ", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, "END_EVNT"};
        var_byte_a = 0;
        var_int_a = 0;
        var_int_b = 0;
        var_boolean_b = false;
        var_char_arr_a = null;
        var_int_c = 0;
        var_int_d = 0;
        var_int_e = 0;
        var_int_f = 0;
        var_boolean_c = true;
        var_boolean_d = false;
        var_boolean_e = true;
        var_byte_b = (byte)-1;
        var_byte_c = 0;
        var_byte_d = 0;
        var_byte_f = 0;
        var_boolean_a = false;
    }
}

