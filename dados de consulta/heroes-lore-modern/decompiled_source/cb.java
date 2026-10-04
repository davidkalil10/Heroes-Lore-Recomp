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
public abstract class cb
implements u {
    public cb a;
    public cb b;
    private boolean b;
    public boolean a;
    public byte a;
    public byte b;

    public cb(cb cb2, byte by2) {
        this.a = cb2;
        this.b = null;
        this.b = true;
        this.a = true;
        this.a = by2;
        this.b = 0;
    }

    public abstract boolean a(int var1, int var2);

    public final boolean b(int n2, int n3) {
        if (this.b != null && this.b.a(n2, n3)) {
            return true;
        }
        this.a = true;
        return false;
    }

    public abstract void a(Graphics var1, int var2, int var3);

    public final void b(Graphics graphics, int n2, int n3) {
        boolean bl2 = false;
        if (this.a) {
            this.a = false;
            this.a(graphics, n2, n3);
            bl2 = true;
        }
        if (this.b == null) {
            if (this.b) {
                if (!bl2) {
                    this.a(graphics, n2, n3);
                }
                this.b = false;
                return;
            }
        } else {
            this.b.b(graphics, n2, n3);
        }
    }

    public final boolean a(int n2, int n3, boolean bl2) {
        switch (n3) {
            case 50: {
                this.a((byte)3, bl2);
                return true;
            }
            case 56: {
                this.a((byte)4, bl2);
                return true;
            }
        }
        switch (n2) {
            case 1: {
                this.a((byte)3, bl2);
                return true;
            }
            case 6: {
                this.a((byte)4, bl2);
                return true;
            }
        }
        return false;
    }

    public final boolean c(int n2, int n3) {
        return this.a(n2, n3, false);
    }

    public final boolean d(int n2, int n3) {
        switch (n3) {
            case 52: {
                this.a((byte)3);
                return true;
            }
            case 54: {
                this.a((byte)4);
                return true;
            }
        }
        switch (n2) {
            case 2: {
                this.a((byte)3);
                return true;
            }
            case 5: {
                this.a((byte)4);
                return true;
            }
        }
        return false;
    }

    public void a(byte by2) {
        this.a(by2, true);
    }

    public final void a(byte by2, boolean bl2) {
        if (by2 == 4) {
            this.b = (byte)(this.b + 1);
            if (this.b >= this.a) {
                if (bl2) {
                    this.b = 0;
                    return;
                }
                this.b = (byte)(this.a - 1);
                if (this.b < 0) {
                    this.b = 0;
                    return;
                }
            }
        } else {
            this.b = (byte)(this.b - 1);
            if (this.b < 0) {
                if (bl2) {
                    this.b = (byte)(this.a - 1);
                    return;
                }
                this.b = 0;
            }
        }
    }

    public void a(byte by2, byte by3) {
        this.b = null;
        if (bs.a != null) {
            bs.a.a();
        }
        this.b();
    }

    public final void a() {
        this.b = null;
        if (bs.a != null) {
            bs.a.a();
        }
        this.b();
    }

    public final void a(byte by2, byte by3, Object[] objectArray) {
        this.b = new af(this, by2, by3, objectArray, null, null);
    }

    public final void a(byte by2, byte by3, Object[] objectArray, char[] cArray, char[] cArray2) {
        this.b = new af(this, by2, by3, objectArray, cArray, cArray2);
    }

    public final void a(Object[] objectArray) {
        this.b = new af(this, 1, 0, objectArray, null, null);
    }

    public final void b() {
        if (this.a != null) {
            this.a.b();
        }
        this.a = true;
    }

    public final void c() {
        if (this.b != null) {
            this.b.c();
        }
        this.a = true;
    }

    public final int a() {
        return this.b / 5 + 1;
    }

    public final int b() {
        return (this.a - 1) / 5 + 1;
    }

    public final int c() {
        return (this.a() - 1) * 5;
    }

    public final int d() {
        int n2 = this.a() * 5 - 1;
        if (n2 > this.a - 1) {
            return this.a - 1;
        }
        return n2;
    }

    public final void a(Graphics graphics, int n2, int n3, boolean bl2) {
        byte by2 = (byte)(this.b % 5);
        byte by3 = this.a - (this.a() - 1) * 5;
        if (by3 > 5) {
            by3 = 5;
        }
        for (byte by4 = 0; by4 < by3; by4 = (byte)((byte)(by4 + 1))) {
            if (by4 == by2) continue;
            cb.a(graphics, n2, n3, by4, false);
        }
        if (r.h > 160) {
            cb.a(graphics, n2 + 27, n3 + 10, 166, 211, 0x3F1F3F, 10452799, 0x3F3F3F);
            cb.a(graphics, n2 + 27, n3 + 10, 166, 211, 0x5F3F3F);
        } else {
            cb.a(graphics, n2 + 27, n3 + 10, 166, r.h - (n3 + 10) - 8, 0x3F1F3F, 10452799, 0x3F3F3F);
            cb.a(graphics, n2 + 27, n3 + 10, 166, r.h - (n3 + 10) - 8, 0x5F3F3F);
        }
        cb.a(graphics, n2, n3, by2, true);
        if (bl2) {
            if (this.a() > 1) {
                graphics.drawImage(ce.l, n2 + 93, n3 + 4, 20);
            }
            if (this.a() < this.b()) {
                graphics.drawImage(ce.o, n2 + 93, n3 + 222, 20);
            }
        }
    }

    private static final void a(Graphics graphics, int n2, int n3, int n4, int n5, int n6, int n7) {
        graphics.setColor(n6);
        graphics.drawLine(n2 + 1, n3, n2 + n4 - 2, n3);
        graphics.drawLine(n2, n3 + 1, n2, n3 + n5 - 2);
        graphics.setColor(n7);
        graphics.drawLine(n2 + n4 - 1, n3 + 1, n2 + n4 - 1, n3 + n5 - 1);
        graphics.drawLine(n2 + 1, n3 + n5 - 1, n2 + n4 - 2, n3 + n5 - 1);
    }

    private static final void c(Graphics graphics, int n2, int n3, int n4, int n5, int n6) {
        graphics.setColor(n6);
        graphics.fillRect(n2 + 1, n3 + 1, n4 - 2, n5 - 2);
    }

    public static final void a(Graphics graphics, int n2, int n3, int n4, int n5, int n6, int n7, int n8) {
        graphics.setColor(n6);
        graphics.drawLine(n2 + 1, n3, n2 + n4 - 2, n3);
        graphics.drawLine(n2 + n4 - 1, n3 + 1, n2 + n4 - 1, n3 + n5 - 2);
        graphics.drawLine(n2 + 1, n3 + n5 - 1, n2 + n4 - 2, n3 + n5 - 1);
        graphics.drawLine(n2, n3 + 1, n2, n3 + n5 - 2);
        graphics.setColor(n7);
        graphics.drawLine(n2 + 1, n3 + 1, n2 + n4 - 3, n3 + 1);
        graphics.drawLine(n2 + 1, n3 + 1, n2 + 1, n3 + n5 - 3);
        graphics.setColor(n8);
        graphics.drawLine(n2 + n4 - 2, n3 + 1, n2 + n4 - 2, n3 + n5 - 3);
        graphics.drawLine(n2 + 1, n3 + n5 - 2, n2 + n4 - 2, n3 + n5 - 2);
    }

    public static final void a(Graphics graphics, int n2, int n3, int n4, int n5, int n6) {
        graphics.setColor(n6);
        graphics.fillRect(n2 + 2, n3 + 2, n4 - 4, n5 - 4);
    }

    public static final void b(Graphics graphics, int n2, int n3, int n4, int n5, int n6) {
        graphics.setColor(n6);
        graphics.drawLine(n2 + 1, n3, n2 + n4 - 2, n3);
        graphics.drawLine(n2, n3 + 1, n2, n3 + n5 - 2);
        graphics.drawLine(n2 + n4 - 1, n3 + 1, n2 + n4 - 1, n3 + n5 - 2);
        graphics.drawLine(n2 + 1, n3 + n5 - 1, n2 + n4 - 2, n3 + n5 - 1);
        graphics.fillRect(n2 + 1, n3 + 1, n4 - 2, n5 - 2);
    }

    public static final void a(Graphics graphics, int n2, int n3, int n4, int n5) {
        cb.a(graphics, n2, n3, n4, n5, 0x1F1F3F, 0x5F3F3F, 0x1F1F3F);
    }

    public static final void b(Graphics graphics, int n2, int n3, int n4, int n5) {
        cb.a(graphics, n2, n3, n4, n5, 0x3F1F3F);
    }

    public static final void a(Graphics graphics, int n2, int n3, byte by2, boolean bl2) {
        int n4 = n2 + 3;
        int n5 = n3 + 10 + by2 * 23;
        graphics.setColor(bl2 ? 0x3F1F3F : 0x5F3F3F);
        graphics.fillRect(n4 + 1, n5, 24, 1);
        graphics.fillRect(n4, n5 + 1, 1, 16);
        graphics.fillRect(n4 + 1, n5 + 17, 24, 1);
        graphics.setColor(bl2 ? 10452799 : 14663551);
        graphics.fillRect(n4 + 1, n5 + 1, 24, 1);
        graphics.fillRect(n4 + 1, n5 + 1, 1, 16);
        graphics.setColor(bl2 ? 0x3F3F3F : 0x7F5F7F);
        graphics.fillRect(n4 + 2, n5 + 16, 23, 1);
        graphics.setColor(bl2 ? 0x5F3F3F : 0x9F7F7F);
        graphics.fillRect(n4 + 2, n5 + 2, 24, 14);
    }

    public static final void a(Graphics graphics, int n2, int n3, int n4) {
        r.c(graphics, n4, n2, n3, 8);
        int n5 = r.a(n4);
        graphics.drawImage(ce.n, n2 - n5, n3, 24);
    }

    public static final void a(Graphics graphics, int n2, int n3, ad ad2, boolean bl2) {
        graphics.drawImage(ce.d[ad2.f], n2, n3 + 1, 3);
        if (bl2 && ad2.h > 1) {
            r.c(graphics, ad2.h, n2 + 11, n3 + 2, 8);
        }
    }

    public static final void a(Graphics graphics, int n2, int n3, ad ad2) {
        boolean bl2 = false;
        if (ad2 instanceof e) {
            Object object;
            e e2 = (e)ad2;
            if (!e2.b) {
                graphics.setColor(14663551);
                n3 += bh.a(graphics, n2, n3, 161, 1, e2.a());
                graphics.setColor(0xFFFFFF);
                bh.a(graphics, n2, (n3 -= bh.a() + 2) + 14, 161, 1, ce.g.a(5));
                return;
            }
            graphics.setColor(0xFFFFFF);
            n3 += bh.a(graphics, n2, n3, 161, 1, ((ad)e2).a);
            graphics.setColor(14663551);
            bh.a(graphics, n2, (n3 -= bh.a() + 2) + 23, ce.g.a(e2 instanceof l ? 4 : 46), 1);
            r.c(graphics, e2.a, n2 + 201 - 47, n3 + 23, 8);
            if (ad2 instanceof t) {
                object = (t)ad2;
                if (((t)object).c != -1) {
                    graphics.setColor(0xFF0000);
                    n3 += bh.a(graphics, n2 + 50, n3 + 8, 161, 1, t.a.a(((t)object).c));
                    n3 -= bh.a() + 2;
                }
            }
            graphics.setColor(14663551);
            bh.a(graphics, n2, n3 + 38, ce.g.a(3), 1);
            r.d(graphics, n2 + 201 - 47, n3 + 38, e2.e, e2.d);
            object = new StringBuffer();
            for (int i2 = 0; i2 < e2.j.length; ++i2) {
                if (e2.j[i2] <= 0) continue;
                ((StringBuffer)object).append(bh.a(ce.a.a(9 + i2))).append("+").append(e2.j[i2]).append("  ");
            }
            ((StringBuffer)object).append(bh.a(((ad)e2).b));
            char[] cArray = null;
            cArray = ((StringBuffer)object).toString().toCharArray();
            if (r.g > 128) {
                bh.a(graphics, n2, n3 + 53, 110, 1, cArray);
                return;
            }
            bh.a(graphics, n2, n3 + 53, 75, 1, cArray);
            return;
        }
        graphics.setColor(0xFFFFFF);
        n3 += bh.a(graphics, n2, n3, 161, 1, ad2.a);
        n3 -= bh.a() + 2;
        graphics.setColor(14663551);
        if (r.g > 128) {
            bh.a(graphics, n2, n3 + 13, 110, 1, ad2.b);
            return;
        }
        bh.a(graphics, n2, n3 + 13, 75, 1, ad2.b);
    }

    public static final void a(Graphics graphics, int n2, int n3, ad ad2, byte by2, char[] cArray, boolean bl2) {
        cb.b(graphics, n2, n3 + 1, 28, 31, 12558207);
        int n4 = r.a(graphics, ce.g.a(2), n2 + 2, n3 + 1);
        r.c(graphics, by2, n4 + 2, n3 + 1, 4);
        graphics.setColor(0xFFFFFF);
        bh.a(graphics, n2 + 90, n3 + 2, cArray, 1);
        boolean bl3 = false;
        if (bl2) {
            cb.a(graphics, n2 + 30, n3 + 14, 163, 19, 0x3F1F3F, 10452799, 0x3F3F3F);
            cb.a(graphics, n2 + 30, n3 + 14, 163, 19, 6233919);
        } else {
            cb.a(graphics, n2 + 30, n3 + 14, 163, 19, 0x5F3F3F, 14663551, 0x7F5F7F);
            cb.a(graphics, n2 + 30, n3 + 14, 163, 19, 0x9F7F7F);
        }
        if (ad2 != null) {
            graphics.drawImage(ce.d[ad2.f], n2 + 14, n3 + 19, 3);
            graphics.setColor(0xFFFFFF);
            if (ad2 instanceof e && !((e)ad2).b) {
                bh.a(graphics, n2 + 34, n3 + 20, ad2.a(), 1);
                return;
            }
            bh.a(graphics, n2 + 34, n3 + 20, ad2.a, 1);
        }
    }

    public static final void a(Graphics graphics, int n2, int n3, int n4, char[] cArray, boolean bl2) {
        if (bl2) {
            cb.a(graphics, n2, n3, n4, 19, 0x3F1F3F, 10452799, 0x3F3F3F);
            cb.a(graphics, n2, n3, n4, 19, 6233919);
        } else {
            cb.a(graphics, n2, n3, n4, 19, 0x5F3F3F, 14663551, 0x7F5F7F);
            cb.a(graphics, n2, n3, n4, 19, 0x9F7F7F);
        }
        if (cArray != null) {
            graphics.setColor(0xFFFFFF);
            bh.a(graphics, n2 + n4 / 2 - bh.a(cArray) / 2, n3 + 5, cArray, 1);
        }
    }

    public static final void a(Graphics graphics, int n2, int n3, int n4, int n5, boolean bl2) {
        if (bl2) {
            cb.a(graphics, n2, n3, n4, n5, 0x5F3F3F, 14663551, 0x7F5F7F);
            return;
        }
        cb.a(graphics, n2, n3, n4, n5, 0x3F1F3F, 10452799, 0x3F3F3F);
    }

    public static final void a(Graphics graphics, int n2, int n3, int n4, int n5, char[] cArray, int n6, int n7, int n8, int n9) {
        graphics.setColor(n8);
        graphics.fillRect(n2, n3, n4, n5);
        if (cArray == null) {
            return;
        }
        n4 -= n6;
        n4 -= n6;
        graphics.setColor(n9);
        if (n7 == 1) {
            bh.b(graphics, n2 + n6 + (n4 >> 1), n3 + 1, n4, 1, cArray, 0, 0, cArray.length);
            return;
        }
        bh.a(graphics, n2 + n6, n3 + 1, n4, 1, cArray);
    }

    public static final void c(Graphics graphics, int n2, int n3, int n4, int n5) {
        cb.a(graphics, n2, n3, n4, n5, 0xFFDFBF, 12558207);
        cb.c(graphics, n2, n3, n4, n5, 14663551);
    }
}

