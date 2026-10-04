/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 */
import javax.microedition.lcdui.Graphics;
import rpg.GameMIDlet;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public final class bf
extends cb {
    public static int a;
    public static int b;
    private boolean e;
    private byte[] h;
    private byte c;
    private byte d;
    private long a;
    private static int c;
    private static int d;
    private static bf a;
    public static boolean c;
    public static boolean d;

    public static final bf a() {
        return a;
    }

    private bf(boolean bl2, byte[] byArray) {
        super(null, (byte)6);
        if (w.a) {
            ((cb)this).a = (byte)(((cb)this).a + 1);
        }
        this.e = bl2;
        this.h = byArray;
        this.c = 0;
        if (d || c) {
            ce.w();
            this.a = System.currentTimeMillis() + 5000L;
            if (d) {
                this.d = (byte)2;
                d = false;
                return;
            }
            if (c) {
                this.d = (byte)3;
                c = false;
            }
        }
    }

    public static final void a(boolean bl2, byte[] byArray) {
        a = r.i - 100;
        b = r.j - 122;
        a = new bf(bl2, byArray);
        if (w.a) {
            c = 6;
            d = 5;
        }
    }

    public static final void d() {
        a = null;
    }

    public final boolean a(int n2, int n3) {
        Object[] objectArray;
        if (this.a > 0L) {
            if (!w.c && w.b) {
                if (n3 == 53) {
                    bh.a(w.a);
                } else if (n3 == bh.a) {
                    GameMIDlet.a.a();
                }
            }
            return true;
        }
        if (this.b(n2, n3)) {
            return true;
        }
        if (this.a(n2, n3, false)) {
            if (!this.e && ((cb)this).b == 1) {
                ((cb)this).b = n2 == 6 || n3 == 56 ? (byte)(((cb)this).b + 1) : (byte)(((cb)this).b - 1);
            }
            this.c = 0;
            return true;
        }
        if (n3 == bh.a) {
            objectArray = new Object[]{bh.a};
            this.a((byte)2, (byte)2, objectArray);
            this.d = (byte)2;
        }
        if (n2 == 8 || n3 == 53) {
            switch (((cb)this).b) {
                case 0: {
                    if (this.e) {
                        this.d = 0;
                        objectArray = new Object[]{bh.a(3929).toCharArray()};
                        this.a((byte)12, (byte)2, objectArray, bh.d, bh.e);
                        break;
                    }
                    ((cb)this).b = new c(this);
                    break;
                }
                case 1: {
                    ((cb)this).b = new a(this, this.h);
                    break;
                }
                case 2: {
                    ((cb)this).b = new be((cb)this, false);
                    break;
                }
                case 3: {
                    ((cb)this).b = new bt((cb)this, false);
                    break;
                }
                case 4: {
                    ((cb)this).b = new bl((cb)this, false);
                    break;
                }
                default: {
                    if (((cb)this).b == c) {
                        objectArray = new Object[]{bh.a};
                        this.d = (byte)2;
                        this.a((byte)2, (byte)2, objectArray);
                        break;
                    }
                    if (((cb)this).b != d) break;
                    objectArray = new Object[]{bh.a(3918).toCharArray()};
                    this.d = (byte)3;
                    this.a((byte)12, (byte)2, objectArray);
                }
            }
        }
        return false;
    }

    public final void a(byte by2, byte by3) {
        super.a(by2, by3);
        if (by2 == 2 || by2 == 12) {
            if (by3 == 0) {
                switch (this.d) {
                    case 1: {
                        break;
                    }
                    case 2: {
                        if (w.c) {
                            Object[] objectArray = new Object[]{bh.a(3919).toCharArray()};
                            this.d = (byte)4;
                            this.a((byte)12, (byte)2, objectArray, bh.j, bh.c);
                            break;
                        }
                        ce.w();
                        this.a = System.currentTimeMillis() + 5000L;
                        break;
                    }
                    case 3: 
                    case 4: {
                        bh.a(w.a);
                        break;
                    }
                    case 0: {
                        ((cb)this).b = new c(this);
                    }
                }
                return;
            }
            switch (this.d) {
                case 4: {
                    ce.w();
                    this.a = System.currentTimeMillis() + 5000L;
                }
            }
        }
    }

    public final void a(Graphics graphics) {
        if (this.a > 0L) {
            graphics.setColor(0xFFFFFF);
            graphics.fillRect(0, 0, r.g, r.h);
            graphics.drawImage(ce.a, r.i, as.d, 3);
            graphics.setColor(0);
            bh.a(graphics, r.g >> 1, r.h - 23, bh.b, 1);
            bh.a(graphics, r.g >> 1, 10, cj.a.a(3941).toCharArray(), 1);
            if (!w.c && w.b) {
                bh.a(graphics, w.a(false).toCharArray(), bh.c);
            }
            if (System.currentTimeMillis() > this.a) {
                GameMIDlet.a.a();
            }
            return;
        }
        this.b(graphics, a, b);
        if (this.c < 2 && ((cb)this).b == null) {
            ((cb)this).a = true;
            this.c = (byte)(this.c + 1);
        }
    }

    public final void a(Graphics graphics, int n2, int n3) {
        graphics.setColor(0x3F1F3F);
        graphics.fillRect(0, 0, r.g, r.h);
        bf.b(graphics, n2, (n3 += 13) - 12, 4);
        n3 += 35;
        int n4 = 18;
        if (this.c == 0) {
            n4 = 14;
        } else if (this.c == 1) {
            n4 = 16;
        }
        if (r.h <= 160) {
            n3 -= 10;
        }
        int n5 = n2 + (201 - ce.k[n4].getWidth()) >> 1;
        graphics.drawImage(ce.k[n4], n5 += 15, n3 + 12 + ((cb)this).b * 16, 20);
        for (int i2 = 0; i2 < ((cb)this).a; ++i2) {
            int n6 = n3 + 14 + i2 * 16;
            n4 = (byte)(i2 * 2);
            if (((cb)this).b != i2 || this.c < 2) {
                n4 = (byte)(n4 + 1);
            }
            bh.a(graphics, n4, n2 + 201 >> 1, n6);
        }
        bh.a(graphics, bh.d, bh.c);
    }

    public static final void c(Graphics graphics, int n2, int n3) {
        int n4;
        boolean bl2 = false;
        int n5 = n2 -= 7;
        graphics.drawImage(ce.k[0], n5, n3, 20);
        graphics.drawImage(ce.k[1], n5 += 12, n3, 20);
        for (n4 = 0; n4 < 5; ++n4) {
            graphics.drawImage(ce.k[1], n5 += 32, n3, 20);
        }
        graphics.drawImage(ce.k[2], n5 += 32, n3, 20);
        n5 = n2;
        graphics.drawImage(ce.k[11], n5, n3 + 12, 20);
        graphics.drawImage(ce.k[12], n5 += 12, n3 + 12, 20);
        for (n4 = 0; n4 < 5; ++n4) {
            graphics.drawImage(ce.k[12], n5 += 32, n3 + 12, 20);
        }
        graphics.drawImage(ce.k[13], n5 += 32, n3 + 12, 20);
    }

    public static final void b(Graphics graphics, int n2, int n3, int n4) {
        int n5;
        int n6;
        boolean bl2 = false;
        n4 += 4;
        int n7 = n2 -= 7;
        graphics.drawImage(ce.k[3], n7, n3, 20);
        graphics.drawImage(ce.k[4], n7 += 12, n3, 20);
        for (n6 = 0; n6 < 5; ++n6) {
            graphics.drawImage(ce.k[4], n7 += 32, n3, 20);
        }
        graphics.drawImage(ce.k[5], n7 += 32, n3, 20);
        n7 = n2;
        graphics.drawImage(ce.k[6], n7, n3 + 12, 20);
        graphics.drawImage(ce.k[7], n7 += 12, n3 + 12, 20);
        for (n6 = 0; n6 < 5; ++n6) {
            graphics.drawImage(ce.k[7], n7 += 32, n3 + 12, 20);
        }
        graphics.drawImage(ce.k[8], n7 += 32, n3 + 12, 20);
        n7 = n2;
        for (n5 = 0; n5 < n4; ++n5) {
            graphics.drawImage(ce.k[9], n7, n3 + 36 + 24 * n5, 20);
            graphics.drawImage(ce.k[10], n7 + 12 + 192, n3 + 36 + 24 * n5, 20);
        }
        graphics.setColor(16763769);
        graphics.fillRect(n7 + 12, n3 + 36, 192, 24 * n4);
        n7 = n2;
        graphics.drawImage(ce.k[11], n7, n3 + 36 + 24 * n4, 20);
        graphics.drawImage(ce.k[12], n7 += 12, n3 + 36 + 24 * n4, 20);
        for (n5 = 0; n5 < 5; ++n5) {
            graphics.drawImage(ce.k[12], n7 += 32, n3 + 36 + 24 * n4, 20);
        }
        graphics.drawImage(ce.k[13], n7 += 32, n3 + 36 + 24 * n4, 20);
    }

    static {
        c = 5;
        d = 5;
    }
}

