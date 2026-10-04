/*
 * Decompiled with CFR 0.152.
 */
/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public final class bw {
    public static int a = 10;
    private static int b = 0;
    private static ci a;
    private static ci b;
    private static ci c;
    private static ci[] a;
    private static final String[] a;

    public static final void a() {
        if (a != null) {
            a.b();
            return;
        }
        if (b != null) {
            b.b();
        }
    }

    public static final void b() {
        if (a != null) {
            a.a();
            return;
        }
        if (b != null) {
            b.a();
        }
    }

    public static final void c() {
        if (b != null) {
            b.b();
        }
    }

    public static final void d() {
        if (c != null) {
            c.b();
        }
    }

    public static final void e() {
        if (a != null) {
            a.b();
        }
    }

    public static final void f() {
        if (a != null) {
            a.c();
            a = null;
        }
        if (b != null) {
            b.c();
            b = null;
        }
    }

    public static final void a(byte by2, boolean bl2) {
        if (a[by2] != null) {
            c = a[by2];
            c.b(b);
            c.a();
        }
    }

    public static final void a(int n2) {
        if (n2 <= 0) {
            n2 = 0;
        } else if (n2 > a) {
            n2 = a;
        }
        if (b == 0 && n2 != 0) {
            bw.b();
        }
        if ((b = n2 * 10) == 0) {
            bw.a();
        }
        for (int i2 = 0; i2 < a.length; ++i2) {
            if (a[i2] == null) continue;
            a[i2].b(b);
        }
    }

    public static final void g() {
        try {
            bs.a.j();
        }
        catch (Exception exception) {
            Exception exception2 = exception;
            exception.printStackTrace();
        }
        bw.a(bs.a.a);
    }

    public static final void a(byte by2) {
        if (a[by2] == null) {
            try {
                String string = "resource:/snd/" + a[by2];
                bw.a[by2] = new ci(string);
                a[by2].b(b);
                return;
            }
            catch (Exception exception) {
                System.out.println(exception.toString());
            }
        }
    }

    public static final void b(byte by2) {
        if (a[by2] != null) {
            a[by2].c();
            bw.a[by2] = null;
        }
    }

    public static final void b(int n2) {
        if ((a = a[n2]) != null && !a.a()) {
            a.b(b);
            a.a(-1);
            a.a();
        }
    }

    public static final void c(int n2) {
        b = a[n2];
        if (b != null && !b.a()) {
            b.b(b);
            b.a(-1);
            b.a();
        }
    }

    static {
        a = new ci[32];
        a = new String[]{"00.mid", "01.mid", "02.mid", "03.mid", "04.mid", "05.mid", "06.mid", "07.mid", "08.wav", "def.mid", "def.mid", "def.mid", "12.mid", "13.wav", "14.wav", "15.wav", "16.wav", "17.wav", "18.wav", "def.mid", "20.wav", "21.wav", "22.mid", "23.mid", "24.mid", "25.mid", "26.mid", "27.mid", "28.mid", "29.mid", "30.mid", "31.mid"};
    }
}

