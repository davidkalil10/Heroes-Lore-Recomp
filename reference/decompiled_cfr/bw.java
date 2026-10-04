/*
 * Decompiled with CFR 0.152.
 */
public final class bw {
    public static int var_int_a;
    private static int var_int_b;
    private static ci var_ci_a;
    private static ci var_ci_b;
    private static ci c;
    private static ci[] var_ci_arr_a;
    private static final String[] var_java_lang_String_arr_a;

    public static final void a() {
        if (var_ci_a != null) {
            var_ci_a.b();
            return;
        }
        if (var_ci_b != null) {
            var_ci_b.b();
        }
    }

    public static final void b() {
        if (var_ci_a != null) {
            var_ci_a.void_a();
            return;
        }
        if (var_ci_b != null) {
            var_ci_b.void_a();
        }
    }

    public static final void c() {
        if (var_ci_b != null) {
            var_ci_b.b();
        }
    }

    public static final void d() {
        if (c != null) {
            c.b();
        }
    }

    public static final void e() {
        if (var_ci_a != null) {
            var_ci_a.b();
        }
    }

    public static final void f() {
        if (var_ci_a != null) {
            var_ci_a.c();
            var_ci_a = null;
        }
        if (var_ci_b != null) {
            var_ci_b.c();
            var_ci_b = null;
        }
    }

    public static final void a(byte by2, boolean bl2) {
        if (var_ci_arr_a[by2] != null) {
            c = var_ci_arr_a[by2];
            c.b(var_int_b);
            c.void_a();
        }
    }

    public static final void a(int n2) {
        if (n2 <= 0) {
            n2 = 0;
        } else if (n2 > var_int_a) {
            n2 = var_int_a;
        }
        if (var_int_b == 0 && n2 != 0) {
            bw.b();
        }
        if ((var_int_b = n2 * 10) == 0) {
            bw.a();
        }
        for (int i2 = 0; i2 < var_ci_arr_a.length; ++i2) {
            if (var_ci_arr_a[i2] == null) continue;
            var_ci_arr_a[i2].b(var_int_b);
        }
    }

    public static final void g() {
        try {
            bs.var_bs_a.j();
        }
        catch (Exception exception) {
            Exception exception2 = exception;
            exception.printStackTrace();
        }
        bw.a(bs.var_bs_a.var_int_a);
    }

    public static final void a(byte by2) {
        if (var_ci_arr_a[by2] == null) {
            try {
                String string = "resource:/snd/" + var_java_lang_String_arr_a[by2];
                bw.var_ci_arr_a[by2] = new ci(string);
                var_ci_arr_a[by2].b(var_int_b);
                return;
            }
            catch (Exception exception) {
                System.out.println(exception.toString());
            }
        }
    }

    public static final void b(byte by2) {
        if (var_ci_arr_a[by2] != null) {
            var_ci_arr_a[by2].c();
            bw.var_ci_arr_a[by2] = null;
        }
    }

    public static final void b(int n2) {
        var_ci_a = var_ci_arr_a[n2];
        if (var_ci_a != null && !var_ci_a.boolean_a()) {
            var_ci_a.b(var_int_b);
            var_ci_a.a(-1);
            var_ci_a.void_a();
        }
    }

    public static final void c(int n2) {
        var_ci_b = var_ci_arr_a[n2];
        if (var_ci_b != null && !var_ci_b.boolean_a()) {
            var_ci_b.b(var_int_b);
            var_ci_b.a(-1);
            var_ci_b.void_a();
        }
    }

    static {
        var_int_a = 10;
        var_int_b = 0;
        var_ci_arr_a = new ci[32];
        var_java_lang_String_arr_a = new String[]{"00.mid", "01.mid", "02.mid", "03.mid", "04.mid", "05.mid", "06.mid", "07.mid", "08.wav", "def.mid", "def.mid", "def.mid", "12.mid", "13.wav", "14.wav", "15.wav", "16.wav", "17.wav", "18.wav", "def.mid", "20.wav", "21.wav", "22.mid", "23.mid", "24.mid", "25.mid", "26.mid", "27.mid", "28.mid", "29.mid", "30.mid", "31.mid"};
    }
}

