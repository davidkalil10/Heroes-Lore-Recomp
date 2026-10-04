/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Canvas
 *  javax.microedition.lcdui.Graphics
 *  javax.microedition.lcdui.Image
 */
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public abstract class r
extends Canvas {
    public boolean a = false;
    public int f = 0;
    public static int g;
    public static int h;
    public static int i;
    public static int j;
    public static boolean b;
    public static int k;
    public static int l;
    public static int m;
    public boolean c = false;

    public r() {
        this.setFullScreenMode(true);
        g = this.getWidth();
        i = g / 2;
    }

    public final void i() {
        this.a = false;
        if (this.f != 0) {
            this.keyReleased(this.f);
            this.f = 0;
        }
    }

    public static final void c(Graphics graphics, int n2, int n3, int n4, int n5) {
        r.a(graphics, n2, n3, n4, n5, 0);
    }

    public static final void a(Graphics graphics, int n2, int n3, int n4, int n5, int n6) {
        byte[] byArray = new byte[9];
        int n7 = 0;
        int n8 = 0;
        int n9 = 0;
        Image image = null;
        switch (n6) {
            case 0: {
                n7 = 5;
                n8 = 4;
                n9 = 7;
                image = ce.var_javax_microedition_lcdui_Image_s;
                break;
            }
            case 1: {
                n7 = 7;
                n8 = 6;
                n9 = 9;
                image = ce.x;
                break;
            }
            case 2: {
                n7 = 7;
                n8 = 6;
                n9 = 9;
                image = ce.y;
                break;
            }
            case 3: {
                n7 = 9;
                n8 = 8;
                n9 = 14;
                image = ce.z;
                break;
            }
            case 4: {
                n7 = 9;
                n8 = 8;
                n9 = 14;
                image = ce.A;
            }
        }
        int n10 = graphics.getClipX();
        int n11 = graphics.getClipY();
        int n12 = graphics.getClipWidth();
        int n13 = graphics.getClipHeight();
        int n14 = 0;
        do {
            byte by2 = (byte)(n2 % 10);
            int n15 = n14;
            n14 = (byte)(n14 + 1);
            byArray[n15] = by2;
        } while ((n2 /= 10) != 0);
        int n16 = n3;
        if ((n5 | 1) == n5) {
            n16 -= n14 * n8 / 2;
        } else if ((n5 | 8) == n5) {
            n16 -= n14 * n8;
        }
        for (int i2 = 0; i2 < n14; ++i2) {
            if (n6 != 0) {
                as.a(graphics, n16 + i2 * n8, n4, n7, n9);
            } else {
                graphics.setClip(n16 + i2 * n8, n4, n7, n9);
            }
            graphics.drawImage(image, n16 + i2 * n8 - byArray[n14 - i2 - 1] * n7, n4, 20);
        }
        graphics.setClip(n10, n11, n12, n13);
    }

    public static final void d(Graphics graphics, int n2, int n3, int n4, int n5) {
        r.c(graphics, n5, n2, n3, 8);
        int n6 = r.int_a(n5);
        graphics.drawImage(ce.var_javax_microedition_lcdui_Image_j, n2 - n6, n3, 24);
        r.c(graphics, n4, n2 - n6 - 9, n3, 8);
    }

    public static final int int_a(int n2) {
        int n3 = 1;
        do {
            n3 += 4;
        } while ((n2 /= 10) != 0);
        return n3;
    }

    public static final int a(Graphics graphics, String string, int n2, int n3) {
        return r.a(graphics, string.toCharArray(), n2, n3);
    }

    public static final int a(Graphics graphics, char[] cArray, int n2, int n3) {
        int n4 = bh.int_a(cArray) + 2;
        int n5 = bh.int_a() + 2;
        graphics.setColor(0);
        graphics.fillRect(n2 - 1, n3 - 1, n4, n5);
        graphics.setColor(0xFFFFFF);
        bh.int_a(graphics, n2, n3, cArray, 1);
        return n2 + n4;
    }

    public static final void c(Graphics graphics) {
        if (m >= 3) {
            graphics.setColor(0);
            graphics.fillRect(0, 0, g, h);
            graphics.setColor(14663551);
            bh.int_a(graphics, i - 48, j - 12, bh.p, 0);
            graphics.drawLine(i - 50, j, i + 48, j);
            graphics.fillRect(i - 51, j + 1, 2, 2);
            graphics.fillRect(i + 48, j + 1, 2, 2);
            graphics.setColor(10452799);
            graphics.drawLine(i - 50, j + 5, i + 48, j + 5);
            graphics.fillRect(i - 51, j + 3, 2, 2);
            graphics.fillRect(i + 48, j + 3, 2, 2);
            char[] cArray = bh.o;
            bh.int_a(graphics, i - bh.int_a(cArray) / 2, j + 50, cArray, 0);
        }
        if (m > 3) {
            graphics.setColor(0);
            graphics.fillRect(i + 20, j - 12, 18, 10);
            graphics.setColor(14663551);
            bh.int_a(graphics, i + 20, j - 12, "...".substring(0, m % 4).toCharArray(), 0);
            graphics.setColor(14655295);
            graphics.fillRect(i - 48, j + 2, 95 * (k < l ? k : l) / l, 1);
            graphics.setColor(0xFFFF7F);
            graphics.fillRect(i - 48, j + 3, 95 * (k < l ? k : l) / l, 1);
        } else if (m < 3) {
            graphics.setColor(0);
            int n2 = (h + 11) / 12;
            for (int i2 = 0; i2 < n2; ++i2) {
                graphics.fillRect(0, i2 * 12 + m * 4, g, 4);
            }
        }
        ++m;
    }

    public static final void a(String string, int n2) {
        bs.var_bs_a.g();
        if (b) {
            if (m < 3) {
                m = 3;
            }
            b = false;
            return;
        }
        l = n2;
        k = 0;
        m = 0;
    }

    public final void j() {
        this.repaint();
    }

    public final void void_a(int n2) {
        h = n2 < 0 || n2 < 350 ? n2 : this.getHeight();
        j = h / 2;
    }

    public static final void k() {
        if (++k % 6 == 0) {
            try {
                Thread.sleep(50L);
                return;
            }
            catch (InterruptedException interruptedException) {}
        }
    }

    static {
        int[] nArray = new int[]{5, 5, 5, 5, 5, 5, 5, 5, 3, 4, 5, 5, 7, 5, 5, 5, 5, 5, 5, 5, 5, 5, 7, 5, 5, 5};
        int[] nArray2 = new int[]{0, 4, 8, 12, 16, 20, 24, 28, 32, 34, 37, 41, 45, 51, 55, 59, 63, 67, 71, 75, 79, 83, 87, 93, 97, 101};
        b = false;
        k = 0;
        l = 100;
        m = 0;
    }
}

