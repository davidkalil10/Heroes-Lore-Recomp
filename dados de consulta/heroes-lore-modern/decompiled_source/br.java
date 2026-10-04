/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Image
 */
import java.io.IOException;
import javax.microedition.lcdui.Image;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public final class br {
    private static final String[] a = new String[]{"IHDR", "cHRM", "gAMA", "iCCP", "sBIT", "sRGB", "tEXt", "zTXt", "iTXt", "pHYs", "sPLT", "tIME", "PLTE", "tRNS", "hIST", "bKGD", "IDAT", "IEND"};
    private static final byte[] a = new byte[]{-119, 80, 78, 71, 13, 10, 26, 10};
    private static final byte[] b = new byte[]{0, 0, 0, 0, 73, 69, 78, 68, -82, 66, 96, -126};
    private String a;
    private boolean b;
    private boolean c;
    private int a;
    private int[] a;
    private byte[] c;
    private Object[] a;
    private char[] a;
    private int b;
    private int c;
    public boolean a = false;
    private static ca a = new ca();
    private static an a = new an();

    public br() {
    }

    public br(String string) throws IOException {
        this.a(string);
    }

    public final void a(String string) throws IOException {
        this.a = null;
        this.c = null;
        this.a = null;
        this.a = null;
        this.a = string;
        this.b();
    }

    private void b() throws IOException {
        this.c = ce.a(this.a + ".mph");
        this.c();
    }

    public final void a(int n2) throws IOException {
        this.a[n2] = ce.a(this.a + "_" + n2 + ".mpd");
    }

    public final void b(int n2) {
        this.a[n2] = null;
    }

    public final void a() {
        for (int i2 = 0; i2 < this.a; ++i2) {
            this.b(i2);
        }
        System.gc();
    }

    private void c() {
        int n2;
        int n3 = br.a(this.c, 0);
        this.b = (n3 >> 27) % 2 == 1;
        this.c = (n3 >> 26) % 2 == 1;
        int n4 = this.a();
        this.a = 0;
        for (n2 = 0; n2 < n4; ++n2) {
            if (this.a >= br.a(this.c, 8 + 8 * n2) + '\u0001') continue;
            this.a = br.a(this.c, 8 + 8 * n2) + '\u0001';
        }
        this.a = new int[this.a];
        for (n2 = 0; n2 < n4; ++n2) {
            char c2 = br.a(this.c, 8 + 8 * n2);
            this.a[c2] = this.a[c2] + 1;
        }
        this.a = new Object[this.a];
        this.a = new char[n4];
        for (n2 = 0; n2 < n4; ++n2) {
            this.a[n2] = br.a(this.c, 8 + 8 * n2 + 6);
        }
        this.b = br.b(this.c, 12);
        this.c = br.b(this.c, 13);
    }

    public final int a() {
        return br.a(this.c, 4);
    }

    public final Image a(int n2) {
        byte[] byArray = this.b(n2);
        Image image = Image.createImage((byte[])byArray, (int)0, (int)byArray.length);
        return image;
    }

    public final Image[] a() {
        this.a = true;
        int n2 = this.a();
        Image[] imageArray = new Image[n2];
        for (int i2 = 0; i2 < n2; ++i2) {
            imageArray[i2] = this.a(i2);
            r.k();
        }
        this.a();
        return imageArray;
    }

    public final Image b(int n2) {
        if (!this.c) {
            return this.a(n2);
        }
        byte[] byArray = this.b(n2);
        br.a(byArray);
        return Image.createImage((byte[])byArray, (int)0, (int)byArray.length);
    }

    public final Image c(int n2) {
        byte[] byArray = this.b(n2);
        br.a(byArray, 1);
        return Image.createImage((byte[])byArray, (int)0, (int)byArray.length);
    }

    public final void a(int n2, int n3) {
        if (!this.b) {
            return;
        }
        br.b(this.c, this.b, 4, n2, n3);
    }

    private byte[] a(int n2) {
        int n3 = this.a(n2);
        if (this.a && this.a[n3] == null) {
            this.a();
            try {
                this.a(n3);
            }
            catch (IOException iOException) {
                System.out.println("[PNGMerger ERROR] cannot load mpd '" + this.a + "' no." + n3);
                iOException.printStackTrace();
            }
        }
        return (byte[])this.a[n3];
    }

    private int a(int n2) {
        return br.a(this.c, 8 + 8 * n2);
    }

    private byte[] b(int n2) {
        if (this.b) {
            return this.d(n2);
        }
        return this.c(n2);
    }

    private byte[] c(int n2) {
        byte[] byArray = this.a(n2);
        int n3 = 0;
        int n4 = br.a(this.c, 8 + n2 * 8 + 2);
        int n5 = this.b(n2);
        int n6 = 0;
        n6 = 8 + n5;
        byte[] byArray2 = new byte[n6 += 12];
        System.arraycopy(a, 0, byArray2, 0, 8);
        System.arraycopy(byArray, n4, byArray2, 8, n5);
        n3 = 8 + n5;
        System.arraycopy(b, 0, byArray2, n3, 12);
        return byArray2;
    }

    private byte[] d(int n2) {
        byte[] byArray = this.a(n2);
        int n3 = 0;
        int n4 = br.a(this.c, 8 + n2 * 8 + 2);
        int n5 = this.b(n2);
        int n6 = 0;
        n6 = 8 + (this.c.length - (br.a(this.c, 4) * 8 + 8));
        n6 += n5;
        byte[] byArray2 = new byte[n6 += 12];
        System.arraycopy(a, 0, byArray2, 0, 8);
        int n7 = br.a(byArray, 0, n4, n5);
        if (n7 == -1) {
            return null;
        }
        int n8 = br.a(byArray, n7) + 12;
        System.arraycopy(byArray, n7, byArray2, 8, n8);
        n3 = 8 + n8;
        block3: for (int i2 = 0; i2 < 18; ++i2) {
            if (!this.a(n2, i2)) continue;
            switch (i2) {
                case 1: 
                case 2: 
                case 3: 
                case 4: 
                case 5: 
                case 9: 
                case 10: {
                    n7 = br.a(byArray, i2, n4, n5);
                    if (n7 == -1) continue block3;
                    n8 = br.a(byArray, n7) + 12;
                    System.arraycopy(byArray, n7, byArray2, n3, n8);
                    n3 += n8;
                }
            }
        }
        n7 = this.b;
        n8 = br.a(this.c, n7) + 12;
        System.arraycopy(this.c, n7, byArray2, n3, n8);
        n3 += n8;
        n7 = this.c;
        if (n7 != -1) {
            n8 = br.a(this.c, n7) + 12;
            System.arraycopy(this.c, n7, byArray2, n3, n8);
            n3 += n8;
        }
        if (this.a(n2, 14) && (n7 = br.a(byArray, 14, n4, n5)) != -1) {
            n8 = br.a(byArray, n7) + 12;
            System.arraycopy(byArray, n7, byArray2, n3, n8);
            n3 += n8;
        }
        if (this.a(n2, 15) && (n7 = br.a(byArray, 15, n4, n5)) != -1) {
            n8 = br.a(byArray, n7) + 12;
            System.arraycopy(byArray, n7, byArray2, n3, n8);
            n3 += n8;
        }
        n7 = br.a(byArray, 16, n4, n5);
        n8 = br.a(byArray, n7) + 12;
        System.arraycopy(byArray, n7, byArray2, n3, n8);
        System.arraycopy(b, 0, byArray2, n3 += n8, 12);
        return byArray2;
    }

    private int b(int n2) {
        byte[] byArray = this.a(n2);
        int n3 = 0;
        int n4 = 0;
        n3 = br.a(this.c, 8 + n2 * 8 + 2);
        n4 = n2 == this.a() - 1 || br.a(this.c, 8 + n2 * 8) != br.a(this.c, 8 + (n2 + 1) * 8) ? byArray.length : br.a(this.c, 8 + (n2 + 1) * 8 + 2);
        return n4 - n3;
    }

    private static int a(byte[] byArray, int n2, int n3, int n4) {
        String string = a[n2];
        int n5 = n4 == -1 ? byArray.length : n3 + n4;
        for (int i2 = n3; i2 < n5; i2 += br.a(byArray, i2) + 12) {
            if (byArray[i2 + 4] != string.charAt(0) || byArray[i2 + 5] != string.charAt(1) || byArray[i2 + 6] != string.charAt(2) || byArray[i2 + 7] != string.charAt(3)) continue;
            return i2;
        }
        return -1;
    }

    private static int a(byte[] byArray, int n2) {
        if (byArray.length - 4 < n2) {
            throw new ArrayIndexOutOfBoundsException();
        }
        int n3 = 0;
        n3 = 0 + (byArray[n2] & 0xFF) * 0x1000000;
        n3 += (byArray[n2 + 1] & 0xFF) * 65536;
        n3 += (byArray[n2 + 2] & 0xFF) * 256;
        return n3 += byArray[n2 + 3] & 0xFF;
    }

    private static char a(byte[] byArray, int n2) {
        if (byArray.length - 2 < n2) {
            throw new ArrayIndexOutOfBoundsException();
        }
        char c2 = '\u0000';
        c2 = (char)(0 + (byArray[n2] & 0xFF) * 256);
        c2 = (char)(c2 + (byArray[n2 + 1] & 0xFF));
        return c2;
    }

    private boolean a(int n2, int n3) {
        char c2 = this.a[n2];
        if (n3 < 1 || n3 > 16) {
            return false;
        }
        return (c2 >> n3 - 1 & 1) == 1;
    }

    private static int b(byte[] byArray, int n2) {
        String string = a[n2];
        int n3 = byArray.length;
        for (int i2 = 0; i2 < n3 - 3; ++i2) {
            if (byArray[i2] != string.charAt(0) || byArray[i2 + 1] != string.charAt(1) || byArray[i2 + 2] != string.charAt(2) || byArray[i2 + 3] != string.charAt(3)) continue;
            return i2 - 4;
        }
        return -1;
    }

    public static final void a(byte[] byArray) {
        int n2 = br.a(byArray, 16, 8, byArray.length);
        int n3 = br.a(byArray, 0, 8, byArray.length);
        int n4 = br.a(byArray, n3 + 8);
        int n5 = br.a(byArray, n3 + 12);
        byte by2 = byArray[n3 + 16];
        br.a(byArray, n2, n4, n5, by2);
    }

    private static void a(byte[] byArray, int n2, int n3, int n4, int n5) {
        int n6;
        int n7;
        int n8 = 8 / n5;
        int n9 = n4;
        int n10 = (n3 - 1) / n8 + 1;
        byte by2 = (byte)(255 >> 8 - n5);
        int n11 = n2 + 15;
        int n12 = (n10 + 1) * n9;
        int n13 = n3 / 2;
        int n14 = n11 + n12;
        int n15 = n14 + 4;
        int n16 = n2 + 4;
        for (n7 = 0; n7 < n9; ++n7) {
            if (byArray[n11 + (n10 + 1) * n7] == 0) continue;
            return;
        }
        for (int i2 = 0; i2 < n9; ++i2) {
            n7 = n11 + (n10 + 1) * i2 + 1;
            for (int i3 = 0; i3 < n13; ++i3) {
                n6 = n3 - 1 - i3;
                int n17 = n7 + i3 / n8;
                int n18 = n7 + n6 / n8;
                int n19 = i3 % n8;
                int n20 = n6 % n8;
                byte by3 = (byte)((n8 - n19 - 1) * n5);
                byte by4 = (byte)((n8 - n20 - 1) * n5);
                byte by5 = (byte)(byArray[n17] >> by3 & by2);
                byte by6 = (byte)(byArray[n18] >> by4 & by2);
                byArray[n17] = (byte)(byArray[n17] & ~(by2 << by3) | by6 << by3);
                byArray[n18] = (byte)(byArray[n18] & ~(by2 << by4) | by5 << by4);
            }
        }
        a.a();
        a.a(byArray, n11, n12);
        long l2 = a.a();
        System.arraycopy(br.e((int)l2), 0, byArray, n14, 4);
        a.a();
        a.a(byArray, n16, n12 + 15);
        n6 = a.a();
        System.arraycopy(br.e(n6), 0, byArray, n15, 4);
    }

    public static final void a(byte[] byArray, int n2) {
        br.a(byArray, n2, 0);
    }

    public static final void a(byte[] byArray, int n2, int n3) {
        int n4 = br.a(byArray, 12, 8, byArray.length);
        br.b(byArray, n4, n2, n3, 0);
    }

    private static void b(byte[] byArray, int n2, int n3, int n4, int n5) {
        int n6;
        int n7 = br.a(byArray, n2);
        int n8 = n2 + 8;
        int n9 = n8 + n7;
        switch (n3) {
            case 0: {
                switch (n4) {
                    case 0: {
                        for (n6 = 0; n6 < n7 / 3; ++n6) {
                            byte by2 = byArray[n8 + n6 * 3];
                            byArray[n8 + n6 * 3] = byArray[n8 + n6 * 3 + 1];
                            byArray[n8 + n6 * 3 + 1] = by2;
                        }
                        break;
                    }
                    case 1: {
                        for (n6 = 0; n6 < n7 / 3; ++n6) {
                            byte by3 = byArray[n8 + n6 * 3 + 1];
                            byArray[n8 + n6 * 3 + 1] = byArray[n8 + n6 * 3 + 2];
                            byArray[n8 + n6 * 3 + 2] = by3;
                        }
                        break;
                    }
                    case 2: {
                        for (n6 = 0; n6 < n7 / 3; ++n6) {
                            byte by4 = byArray[n8 + n6 * 3];
                            byArray[n8 + n6 * 3] = byArray[n8 + n6 * 3 + 2];
                            byArray[n8 + n6 * 3 + 2] = by4;
                        }
                        break;
                    }
                    case 3: {
                        for (n6 = 0; n6 < n7 / 3; ++n6) {
                            byte by5 = byArray[n8 + n6 * 3];
                            byArray[n8 + n6 * 3] = byArray[n8 + n6 * 3 + 2];
                            byArray[n8 + n6 * 3 + 2] = byArray[n8 + n6 * 3 + 1];
                            byArray[n8 + n6 * 3 + 1] = by5;
                        }
                        break;
                    }
                    case 4: {
                        for (n6 = 0; n6 < n7 / 3; ++n6) {
                            byte by6 = byArray[n8 + n6 * 3];
                            byArray[n8 + n6 * 3] = byArray[n8 + n6 * 3 + 1];
                            byArray[n8 + n6 * 3 + 1] = byArray[n8 + n6 * 3 + 2];
                            byArray[n8 + n6 * 3 + 2] = by6;
                        }
                        break;
                    }
                }
                break;
            }
            case 1: {
                for (n6 = 0; n6 < n7 / 3; ++n6) {
                    byte by7;
                    int n10 = byArray[n8 + n6 * 3] & 0xFF;
                    int n11 = byArray[n8 + n6 * 3 + 1] & 0xFF;
                    int n12 = byArray[n8 + n6 * 3 + 2] & 0xFF;
                    byArray[n8 + n6 * 3] = by7 = (byte)((n10 + n11 + n12) / 3);
                    byArray[n8 + n6 * 3 + 1] = by7;
                    byArray[n8 + n6 * 3 + 2] = by7;
                }
                break;
            }
            case 2: {
                for (n6 = 0; n6 < n7 / 3; ++n6) {
                    int n13 = byArray[n8 + n6 * 3] & 0xFF;
                    int n14 = byArray[n8 + n6 * 3 + 1] & 0xFF;
                    int n15 = byArray[n8 + n6 * 3 + 2] & 0xFF;
                    byArray[n8 + n6 * 3] = (byte)(n13 * (n4 * 10) / 1000 < 255 ? n13 * (n4 * 10) / 1000 : 255);
                    byArray[n8 + n6 * 3 + 1] = (byte)(n14 * (n4 * 10) / 1000 < 255 ? n14 * (n4 * 10) / 1000 : 255);
                    byArray[n8 + n6 * 3 + 2] = (byte)(n15 * (n4 * 10) / 1000 < 255 ? n15 * (n4 * 10) / 1000 : 255);
                }
                break;
            }
            case 3: {
                for (n6 = 0; n6 < n7 / 3; ++n6) {
                    byArray[n8 + n6 * 3] = ~byArray[n8 + n6 * 3];
                    byArray[n8 + n6 * 3 + 1] = ~byArray[n8 + n6 * 3 + 1];
                    byArray[n8 + n6 * 3 + 2] = ~byArray[n8 + n6 * 3 + 2];
                }
                break;
            }
            case 4: {
                n6 = (byte)(n4 >> 16 & 0xFF);
                byte by8 = (byte)(n4 >> 8 & 0xFF);
                byte by9 = (byte)(n4 & 0xFF);
                byte by10 = (byte)(n5 >> 16 & 0xFF);
                byte by11 = (byte)(n5 >> 8 & 0xFF);
                byte by12 = (byte)(n5 & 0xFF);
                for (int i2 = 0; i2 < n7 / 3; ++i2) {
                    if (byArray[n8 + i2 * 3] != n6 || byArray[n8 + i2 * 3 + 1] != by8 || byArray[n8 + i2 * 3 + 2] != by9) continue;
                    byArray[n8 + i2 * 3] = by10;
                    byArray[n8 + i2 * 3 + 1] = by11;
                    byArray[n8 + i2 * 3 + 2] = by12;
                }
                break;
            }
        }
        a.a();
        a.a(byArray, n2 + 4, n7 + 4);
        n6 = a.a();
        System.arraycopy(br.e(n6), 0, byArray, n9, 4);
    }

    private static byte[] e(int n2) {
        byte[] byArray = new byte[4];
        byte[] byArray2 = byArray;
        byArray[0] = (byte)(n2 >> 24 & 0xFF);
        byArray2[1] = (byte)(n2 >> 16 & 0xFF);
        byArray2[2] = (byte)(n2 >> 8 & 0xFF);
        byArray2[3] = (byte)(n2 & 0xFF);
        return byArray2;
    }
}

