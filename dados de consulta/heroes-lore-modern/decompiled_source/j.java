/*
 * Decompiled with CFR 0.152.
 */
/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public final class j {
    public static j[] a;
    public static final byte[] a;
    public char[] a;
    public byte a;
    public byte b;
    public byte c;
    public byte d;
    public boolean a;
    public boolean b;
    public boolean c;
    public boolean d;
    public byte e;
    public byte f;
    public short a;
    public short b;
    public short c;
    public short d;
    public byte g;
    public byte h;
    public byte i;
    public short e;
    public byte[] b;
    public byte j;
    public byte k;
    public byte l;
    public byte m;

    public static final void a(int n2) {
        a = new j[n2];
    }

    public static final void a(byte[] byArray, byte n2, byte by2) {
        int n3 = 1;
        for (int i2 = 0; i2 < n2; ++i2) {
            short s2 = h.a(byArray, n3);
            n3 += 2 + s2;
        }
        n3 += 2;
        j j2 = new j();
        int n4 = ++n3;
        byte by3 = byArray[n4];
        j2.a = bh.a(new String(byArray, ++n3, (int)by3));
        n3 += by3;
        by3 = byArray[n3++];
        j2.a = (byte)(by3 >> 6 & 3);
        j2.b = (byte)(by3 >> 4 & 3);
        j2.c = (byte)(by3 >> 2 & 3);
        j2.d = (byte)(by3 & 3);
        j2.a = ((by3 = byArray[n3++]) >> 3 & 1) == 1;
        j2.b = (by3 >> 2 & 1) == 1;
        j2.c = (by3 >> 1 & 1) == 1;
        boolean bl2 = j2.d = (by3 & 1) == 1;
        if (j2.c) {
            j2.e = (byte)(by3 >> 6 & 3);
        }
        j2.f = byArray[n3++];
        j2.a = h.a(byArray, n3);
        j2.b = h.a(byArray, n3 += 2);
        j2.c = h.a(byArray, n3 += 2);
        j2.d = h.a(byArray, n3 += 2);
        n3 += 2;
        j2.g = byArray[n3++];
        j2.h = byArray[n3++];
        j2.i = byArray[n3++];
        j2.e = h.a(byArray, n3);
        n3 += 2;
        by3 = byArray[n3++];
        j2.b = new byte[3 * by3];
        System.arraycopy(byArray, n3, j2.b, 0, j2.b.length);
        j.a[by2] = j2;
    }

    public static final void a(byte by2) {
        j j2 = a[by2];
        byte[] byArray = (byte[])ce.e[by2 * 12 + 0];
        x.a(byArray != null);
        j2.j = byArray[0];
        byArray = (byte[])ce.e[by2 * 12 + 4];
        x.a(byArray != null);
        j2.k = byArray[0];
        byArray = (byte[])ce.e[by2 * 12 + 8];
        x.a(byArray != null);
        j2.l = byArray[0];
    }

    public static final void b(byte by2) {
        j j2 = a[by2];
        byte[] byArray = (byte[])ce.h[by2 * 16 + 0];
        x.a(byArray != null);
        j2.j = byArray[0];
        byArray = (byte[])ce.h[by2 * 16 + 4];
        j2.k = byArray != null ? byArray[0] : (byte)-1;
        byArray = (byte[])ce.h[by2 * 16 + 12];
        j2.l = byArray != null ? byArray[0] : (byte)-1;
        byArray = (byte[])ce.h[by2 * 16 + 8];
        if (byArray != null) {
            j2.m = byArray[0];
            return;
        }
        j2.m = (byte)-1;
    }

    static {
        a = new byte[]{3, 2, 6, 2, 2, 1, 3, 4, 3, 2, 3, 4, 2, 3, 2, 2, 2, 3, 3, 3, 3, 3, 6, 3, 3, 3, 2, 2, 2, 3, 3, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
    }
}

