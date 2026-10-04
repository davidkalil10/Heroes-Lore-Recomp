/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 *  javax.microedition.lcdui.Image
 */
import java.io.IOException;
import java.util.Vector;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import rpg.GameMIDlet;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public final class bh {
    public static int a = -8;
    public static int b = -6;
    public static int c = -7;
    public static String a;
    public static String b;
    public static char[] a;
    public static char[] b;
    public static char[] c;
    public static char[] d;
    public static char[] e;
    public static char[] f;
    public static char[] g;
    public static char[] h;
    public static char[] i;
    public static char[] j;
    public static char[] k;
    public static char[] l;
    public static char[][] a;
    public static char[] m;
    public static char[] n;
    public static char[] o;
    public static char[] p;
    public static String c;
    public static char[] q;
    public static char[] r;
    public static char[] s;
    public static char[] t;
    public static String d;
    public static boolean a;
    private static b g;
    public static b a;
    public static b b;
    public static b c;
    public static b d;
    public static b e;
    public static b f;
    public static Vector a;
    public static String e;

    public static final void a(Graphics graphics, char[] cArray, char[] cArray2) {
        int n2;
        int n3;
        int n4;
        char[] cArray3;
        graphics.setClip(0, 0, r.g, r.h);
        int n5 = bh.a() + 5;
        if (cArray != null) {
            cArray3 = cArray;
            n4 = bh.a(cArray) + 2;
            n3 = 0;
            n2 = r.h - n5 + 3;
            graphics.setColor(0);
            graphics.fillRect(0, n2, n4, n5);
            graphics.setColor(0xFFFFFF);
            bh.a(graphics, 1, n2 + 1, cArray3, 1);
        }
        if (cArray2 != null) {
            cArray3 = cArray2;
            n4 = bh.a(cArray2) + 2;
            n3 = r.g - n4;
            n2 = r.h - n5 + 3;
            graphics.setColor(0);
            graphics.fillRect(n3, n2, n4, n5);
            graphics.setColor(0xFFFFFF);
            bh.a(graphics, n3 + 1, n2 + 1, cArray3, 1);
        }
        graphics.setClip(0, 0, r.g, r.h);
    }

    public static final void a(Graphics graphics) {
        graphics.setClip(0, 0, r.g, r.h);
        graphics.setColor(0);
        graphics.fillRect(0, 0, r.g, r.h);
    }

    public static final void a(String string) {
        try {
            GameMIDlet.a.platformRequest(w.a);
        }
        catch (Exception exception) {
            Exception exception2 = exception;
            exception.printStackTrace();
        }
        GameMIDlet.a.a();
    }

    public static final void a(Graphics graphics, int n2, int n3, int n4) {
        n3 = r.g >> 1;
        bh.a(true);
        int n5 = n2 >> 1;
        if (n2 % 2 == 0) {
            graphics.setColor(0xFFFFFF);
        } else {
            graphics.setColor(0);
        }
        bh.a(graphics, n3, n4 + 4, a[n5], 1);
        bh.a(false);
    }

    public static final void a(boolean bl2) {
        a = bl2;
        if (a) {
            g = d;
            return;
        }
        g = a;
    }

    public static final int a(int n2, int n3) {
        return n2 * n3 / 100;
    }

    public static final void a(cj cj2) {
        a = bh.a(3902) + " ";
        b = bh.a(3903);
        a = bh.a(3904).toCharArray();
        b = bh.a(3906).toCharArray();
        c = bh.a(3907).toCharArray();
        d = bh.a(3908).toCharArray();
        e = bh.a(3909).toCharArray();
        f = bh.a(3910).toCharArray();
        g = bh.a(3911).toCharArray();
        h = bh.a(3912).toCharArray();
        i = bh.a(3913).toCharArray();
        j = bh.a(3914).toCharArray();
        k = bh.a(3915).toCharArray();
        l = bh.a(3916).toCharArray();
        bh.a[0] = bh.a(3920).toCharArray();
        bh.a[1] = bh.a(3921).toCharArray();
        bh.a[2] = bh.a(3922).toCharArray();
        bh.a[3] = bh.a(3923).toCharArray();
        bh.a[4] = bh.a(3924).toCharArray();
        bh.a[5] = bh.a(3924).toCharArray();
        bh.a[6] = bh.a(3926).toCharArray();
        s = bh.a(3932).toCharArray();
        n = bh.a(3946).toCharArray();
        t = bh.a(3947).toCharArray();
        d = bh.a(3948);
        c = bh.a(3949);
        q = bh.a(3950).toCharArray();
    }

    public static final String a(int n2) {
        return cj.a.a(n2).replace(';', '\n');
    }

    public static final char[] a(String string) {
        try {
            return cj.a.a(Integer.parseInt(string.trim())).replace(';', '\n').toCharArray();
        }
        catch (Exception exception) {
            return ("2." + exception.toString()).toCharArray();
        }
    }

    public static final void a() {
        a = (b)b.a("fonts/small", 0, false);
        b = (b)b.a("fonts/small", 0xFFFFFF, false);
        c = (b)b.a("fonts/small", 0xFF8800, false);
        d = (b)b.a("fonts/big", 0, 0xFFFFFF, true);
        e = (b)b.a("fonts/big", 0xFFFFFF, 0, true);
        f = d;
        bh.a.b = true;
        bh.b.b = true;
        bh.c.b = true;
        bh.d.b = true;
        bh.e.b = true;
        bh.f.b = true;
        g = a;
    }

    public static final int a() {
        return bh.g.a;
    }

    public static final int a(char[] cArray) {
        return g.a(bh.a(cArray));
    }

    private static final boolean a(char c2) {
        return c2 == ';';
    }

    public static final int a(char[] cArray, int n2, int n3) {
        int n4 = n2 + n3;
        boolean bl2 = false;
        while (n4 < cArray.length) {
            if (bh.a(cArray[n4])) {
                ++n4;
                continue;
            }
            if (bl2) {
                return n4 + 1 - n2;
            }
            bl2 = true;
            ++n4;
        }
        return cArray.length - n2;
    }

    public static final int a(char[] cArray, int n2, int n3, int n4) {
        String string = new String(cArray, n2, cArray.length - n2);
        return g.a(string, n3, n4);
    }

    public static final int a(char[] cArray, int n2) {
        String string = bh.a(cArray);
        Vector vector = bh.a(string, n2);
        return vector.size();
    }

    public static final int a(int n2, int n3, char[] cArray, int n4, int n5, int n6) {
        return bh.a(new String(cArray), 0, 0, n2);
    }

    private static int b() {
        return bh.g.b;
    }

    private static int a(String string, int n2, int n3, int n4) {
        int n5 = g.a(bh.a(string, n4));
        return n5 - bh.b();
    }

    private static Vector a(String string, int n2) {
        if (!string.equals(e)) {
            a.setSize(0);
            g.a(a, string, n2);
        }
        return a;
    }

    public static final void a(Graphics graphics, int n2, int n3, String string, int n4) {
        bh.a(graphics, n2, n3, string.toCharArray(), n4);
    }

    public static final int a(Graphics graphics, int n2, int n3, char[] cArray, int n4) {
        az az2 = bh.a(graphics.getColor());
        return az2.a(graphics, cArray, n2, n3, 20);
    }

    private static az a(int n2) {
        if (a) {
            if (n2 == 0) {
                return d;
            }
            if (n2 == 0xFFFFFF) {
                return e;
            }
            return f;
        }
        if (n2 == 0) {
            return a;
        }
        if (n2 == 0xFFFFFF) {
            return b;
        }
        return c;
    }

    public static final void a(Graphics graphics, int n2, int n3, char[] cArray, int n4) {
        az az2 = bh.a(graphics.getColor());
        az2.a(graphics, cArray, n2, n3, 17);
    }

    public static final void a(Graphics graphics, int n2, int n3, int n4, int n5, char[] cArray, int n6, int n7, int n8) {
        graphics.setClip(0, 0, r.g, r.h);
        az az2 = bh.a(graphics.getColor());
        if (n6 + n8 > cArray.length) {
            n8 = cArray.length - n6;
        }
        String string = new String(cArray, n6, n8);
        az2.a(graphics, bh.a(string, n4), n2, n3, r.h, 20);
    }

    public static final void b(Graphics graphics, int n2, int n3, int n4, int n5, char[] cArray, int n6, int n7, int n8) {
        graphics.setClip(0, 0, r.g, r.h);
        az az2 = bh.a(graphics.getColor());
        if (n6 + n8 > cArray.length) {
            n8 = cArray.length - n6;
        }
        String string = new String(cArray, n6, n8);
        az2.a(graphics, bh.a(string, n4), n2, n3, r.h, 17);
    }

    public static final void c(Graphics graphics, int n2, int n3, int n4, int n5, char[] cArray, int n6, int n7, int n8) {
        graphics.setClip(0, 0, r.g, r.h);
        az az2 = bh.a(graphics.getColor());
        Vector vector = bh.a(new String(cArray, n6, cArray.length - n6), n4);
        int n9 = Math.min(vector.size(), 3);
        for (int i2 = 0; i2 < n9; ++i2) {
            String string = (String)vector.elementAt(i2);
            if (n8 <= string.length()) {
                az2.a(graphics, string, 0, n8, n2, n3, 20);
                return;
            }
            az2.a(graphics, string, n2, n3, 20);
            n8 -= string.length() + 1;
            n3 += bh.g.a + 2;
        }
        graphics.setColor(0xFFFFFF);
    }

    public static final int a(Graphics graphics, int n2, int n3, int n4, int n5, char[] cArray, int n6) {
        graphics.setClip(0, 0, r.g, r.h);
        az az2 = bh.a(graphics.getColor());
        String string = bh.a(cArray);
        return az2.a(graphics, bh.a(string, n4), n2, n3, r.h, n6);
    }

    public static final int a(Graphics graphics, int n2, int n3, int n4, int n5, char[] cArray) {
        return bh.a(graphics, n2, n3, n4, n5, cArray, 20);
    }

    public static final String a(char[] cArray) {
        return new String(cArray);
    }

    public static final String a(String string, String string2, String string3) {
        int n2;
        while ((n2 = string.indexOf(string2)) >= 0) {
            String string4 = string.substring(0, n2);
            String string5 = string.substring(n2 + string2.length());
            string = string4 + string3 + string5;
        }
        return string;
    }

    public static final Image a(String string) throws IOException {
        string = "/" + cj.a[cj.a.a] + "/" + string;
        return Image.createImage((String)string);
    }

    static {
        a = new char[7][];
        m = "               ".toCharArray();
        n = null;
        o = null;
        p = null;
        c = null;
        q = null;
        r = null;
        t = null;
        d = null;
        a = false;
        a = new Vector();
        e = "";
    }
}

