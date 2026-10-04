/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 *  javax.microedition.lcdui.Image
 */
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public final class p
extends ck
implements u {
    public static final short[] a = new short[]{1, 1, 1, 1, 10, 20};
    public static final byte[] h = new byte[]{1, 9, 20, 30};
    public static final short[] b = new short[]{56, 280, 280, 220, 160, 270, 56, 270, 220, 80, 270, 270, 80, 270, 220, 80, 270, 270};
    private static final byte[] i = new byte[]{0, 0, 10, 4, 4, 10, 0, 4, 0, 4, 10, 8, 0, 6, 6, 0, 10, 8};
    private static final short[] d = new short[]{16, 9, 161, 9, 10, 81, 16, 9, 13, 25, 81, 8, 31, 81, 16, 31, 87, 8};
    private byte[] j;
    public byte f;
    public byte g;
    public byte h;
    public short a;
    public int a;
    public int b;
    public byte i;
    public byte j = new byte[4];
    private short b;
    private short e;
    private byte k;
    private byte l;
    private byte m;
    private boolean d;
    public short[] c;

    public p(short s2, short s3, byte by2) {
        super(s2, s3, (byte)8, (byte)8);
        this.f = by2;
        this.c = new short[3];
        this.c[0] = b[by2 * 3 + 0];
        this.c[1] = b[by2 * 3 + 1];
        this.c[2] = b[by2 * 3 + 2];
        this.a = a[by2];
        this.a();
        this.a = 0;
        this.d();
        this.a(true, (byte)0, true);
        this.a(false, (byte)1, true);
    }

    public final void a(int n2) {
        this.a += n2;
        while (this.a >= this.b) {
            this.a -= this.b;
            this.f();
        }
    }

    private final void f() {
        this.a = (short)(this.a + 1);
        this.a();
        if (this.a == h[1]) {
            this.a(false, (byte)1, true);
        }
    }

    public final void a() {
        this.b = this.a * this.a * this.a - this.a * this.a + this.a * 80;
    }

    public final void c() {
        this.i = 0;
    }

    public final byte a() {
        if (this.f == 0 || this.f == 3) {
            return 1;
        }
        if (this.f == 1 || this.f == 4) {
            return 2;
        }
        if (this.f == 2 || this.f == 5) {
            return 3;
        }
        return 0;
    }

    public final boolean a() {
        return this.k - this.l < 10;
    }

    public final void d() {
        this.a(true, (byte)-1, true);
        this.a(false, (byte)-1, true);
    }

    public final boolean a(boolean bl2, byte by2, boolean bl3) {
        if (by2 == -1) {
            if (bl2) {
                this.g = by2;
            } else {
                this.h = by2;
            }
            return true;
        }
        if (h[by2] > this.a) {
            return false;
        }
        if (bl2) {
            this.g = by2;
            if (bl3 && by2 != -1) {
                this.c[by2] = b[this.f * 3 + by2];
            }
        } else {
            this.h = by2;
            if (bl3 && by2 != -1) {
                this.c[by2] = b[this.f * 3 + by2];
            }
        }
        return true;
    }

    public final void a(boolean bl2, byte by2, int n2, int n3) {
        if (bl2 && this.g >= 0 && this.g <= 2 && this.c[this.g] == 0) {
            this.m = this.g;
            this.c[this.g] = b[this.f * 3 + this.g];
        } else if (!bl2 && this.h >= 0 && this.h <= 2 && this.c[this.h] == 0) {
            this.m = this.h;
            this.c[this.h] = b[this.f * 3 + this.h];
        } else {
            return;
        }
        this.i = 1;
        this.b = (short)-1;
        this.l = this.k = i[this.f * 3 + this.m];
        this.d = false;
        this.a((short)((n2 + u.a[by2]) * 16), (short)((n3 + u.b[by2]) * 16));
        this.j = by2;
        ((ck)this).b = (byte)(((ck)this).d >> 4);
        ((ck)this).a = (byte)(((ck)this).c >> 4);
        ((ck)this).b = false;
        ((ck)this).a = false;
    }

    public final void e() {
        if ((this.i == 0 || this.m != this.g) && this.g != -1 && this.c[this.g] > 0) {
            byte by2 = this.g;
            this.c[by2] = (short)(this.c[by2] - 1);
        }
        if ((this.i == 0 || this.m != this.h) && this.h != -1 && this.c[this.h] > 0) {
            byte by3 = this.h;
            this.c[by3] = (short)(this.c[by3] - 1);
        }
        switch (this.i) {
            case 0: {
                return;
            }
            case 1: {
                this.b = (short)(this.b + 1);
                if (this.b < 10) break;
                if (this.l > 0) {
                    this.i = (byte)2;
                } else {
                    this.i = (byte)3;
                    this.b = 0;
                    this.e = 0;
                }
                this.b = 0;
                break;
            }
            case 2: {
                this.b = (short)(this.b + 1);
                this.l = (byte)(this.l - 1);
                if (this.b >= 4) {
                    this.b = 0;
                }
                if (this.l > 0) break;
                this.i = (byte)3;
                this.b = 0;
                this.e = 0;
                break;
            }
            case 3: {
                this.b = (short)(this.b + 1);
                if (this.b < 11) break;
                this.b = (short)11;
                if (!this.d) break;
                this.i = 0;
                this.j = 0;
            }
        }
        if (this.i == 3) {
            this.a(this.f, this.m);
            this.e = (short)(this.e + 1);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private final void a(byte by2, byte by3) {
        ao ao2 = n.a();
        switch (by2) {
            case 0: {
                switch (by3) {
                    case 0: {
                        this.g();
                        this.i();
                        break;
                    }
                    case 1: {
                        if (this.e != 0) break;
                        ao2.a(new bj(0, 8, by2, by3));
                        ao2.a((byte)5);
                        ao2.d = true;
                        bw.a((byte)21, false);
                        break;
                    }
                    case 2: {
                        if (this.e == 0) {
                            ao2.a(new bj(0, 160, by2, by3));
                            ao2.g = true;
                            bw.a((byte)21, false);
                        }
                        if (this.e != d[by2 * 3 + by3]) break;
                        ao2.g = false;
                        break;
                    }
                }
                break;
            }
            case 1: {
                switch (by3) {
                    case 0: {
                        if (this.e != 0) break;
                        ao2.a(new bj(0, 8, by2, by3));
                        ao2.c(30);
                        bw.a((byte)20, false);
                        break;
                    }
                    case 1: {
                        if (this.e != 0) break;
                        ao2.a(new bj(0, 9, by2, by3));
                        ao2.e(20);
                        bw.a((byte)20, false);
                        break;
                    }
                    case 2: {
                        if (this.e == 0) {
                            ao2.a(new bj(0, 80, by2, by3));
                            ao2.a(new bj(4, 8, by2, 0));
                            ao2.a(new bj(24, 8, by2, 0));
                            ao2.a(new bj(44, 8, by2, 0));
                            ao2.h = true;
                            bw.a((byte)20, false);
                        }
                        if (this.e != d[by2 * 3 + by3]) break;
                        ao2.h = false;
                        break;
                    }
                }
                break;
            }
            case 2: {
                switch (by3) {
                    case 0: {
                        this.h();
                        this.j();
                        break;
                    }
                    case 1: {
                        if (this.e != 0) break;
                        ao2.a(new bj(0, 8, by2, by3));
                        ao2.a((byte)6);
                        ao2.e = true;
                        bw.a((byte)21, false);
                        break;
                    }
                    case 2: {
                        if (this.e != 0) break;
                        ao2.a(new bj(0, 12, by2, by3));
                        ((o)ao2).b.removeAllElements();
                        ao2.d = false;
                        ao2.e = false;
                        bw.a((byte)21, false);
                        break;
                    }
                }
                break;
            }
            case 3: {
                switch (by3) {
                    case 0: {
                        this.a(4, 3, 3, this.a * 3 + 35 + n.a().g);
                        this.a(10, 3, 3, this.a * 3 + 35 + n.a().g);
                        this.a(16, 3, 3, this.a * 3 + 35 + n.a().g);
                        this.a((byte)5, 0, 3);
                        this.a((byte)5, 6, 3);
                        this.a((byte)5, 12, 3);
                        if (this.e != 0 && this.e != 6 && this.e != 12) break;
                        bw.a((byte)16, false);
                        break;
                    }
                    case 1: {
                        this.b(2);
                        this.a((short)80, this.a + 45 + n.a().g * 3 / 2);
                        this.a((int)((ck)this).c, (int)((ck)this).d, (short)80, (byte)6);
                        if (this.e % 8 != 0) break;
                        bw.a((byte)16, false);
                        break;
                    }
                    case 2: {
                        if (this.e != 0) break;
                        this.a((byte)7);
                        this.a((short)7);
                        bw.a((byte)16, false);
                        break;
                    }
                }
                break;
            }
            case 4: {
                switch (by3) {
                    case 0: {
                        this.a(4, 5, 3, this.a * 3 + 35 + n.a().g);
                        this.a(10, 5, 3, this.a * 3 + 35 + n.a().g);
                        this.a(16, 5, 3, this.a * 3 + 35 + n.a().g);
                        this.a((byte)7, 0, 5);
                        this.a((byte)7, 6, 5);
                        this.a((byte)7, 12, 5);
                        if (this.e != 0 && this.e != 6 && this.e != 12 && this.e != 18 && this.e != 24) break;
                        bw.a((byte)18, false);
                        break;
                    }
                    case 1: {
                        this.b(2);
                        ao2.f = Math.abs(((ck)this).a - ((ck)ao2).a) + Math.abs(((ck)this).b - ((ck)ao2).b) <= 2;
                        this.a((int)((ck)this).c, (int)((ck)this).d, (short)80, (byte)8);
                        if (this.e % 8 == 0) {
                            bw.a((byte)18, false);
                        }
                        if (this.e != d[by2 * 3 + by3]) break;
                        ao2.f = false;
                        break;
                    }
                    case 2: {
                        if (this.e != 0) break;
                        ao2.a(new bj(0, 15, by2, by3));
                        ao2.c(20);
                        ao2.e(20);
                        bw.a((byte)20, false);
                        break;
                    }
                }
                break;
            }
            case 5: {
                switch (by3) {
                    case 0: {
                        this.a(4, 5, 3, this.a * 3 + 35 + n.a().g);
                        this.a(10, 5, 3, this.a * 3 + 35 + n.a().g);
                        this.a(16, 5, 3, this.a * 3 + 35 + n.a().g);
                        this.a((byte)4, 0, 5);
                        this.a((byte)4, 6, 5);
                        this.a((byte)4, 12, 5);
                        if (this.e != 0 && this.e != 6 && this.e != 12 && this.e != 18 && this.e != 24) break;
                        bw.a((byte)17, false);
                        break;
                    }
                    case 1: {
                        this.b(2);
                        this.a((short)80, this.a + 45 + n.a().g * 3 / 2);
                        this.a((int)((ck)this).c, (int)((ck)this).d, (short)80, (byte)9);
                        if (this.e % 8 != 0) break;
                        bw.a((byte)17, false);
                        break;
                    }
                    case 2: {
                        if (this.e != 0) break;
                        this.a((byte)9);
                        this.a((short)9);
                        bw.a((byte)17, false);
                    }
                }
                break;
            }
        }
        if (this.e == d[by2 * 3 + by3]) {
            this.d = true;
        }
    }

    private final void g() {
        ck ck2;
        int n2 = this.a * 3 + 40 + n.a().g;
        if ((this.e == 1 || this.e == 5) && (ck2 = this.a(this.j, (byte)0)) != null && ck2 instanceof al) {
            ((al)ck2).a(n2, this.a());
        }
        if ((this.e == 2 || this.e == 6) && (ck2 = this.a(this.j, (byte)1)) != null && ck2 instanceof al) {
            ((al)ck2).a(n2, this.a());
        }
        if ((this.e == 3 || this.e == 7) && (ck2 = this.a(this.j, (byte)2)) != null && ck2 instanceof al) {
            ((al)ck2).a(n2, this.a());
        }
        if ((this.e == 5 || this.e == 9) && (ck2 = this.a(this.j, (byte)3)) != null && ck2 instanceof al) {
            ((al)ck2).a(n2, this.a());
        }
        if (this.e == 8 || this.e == 12) {
            ck ck3;
            int n3 = ((ck)this).a + u.a[this.j] * 3 + u.a[u.e[this.j]];
            int n4 = ((ck)this).b + u.b[this.j] * 3 + u.b[u.e[this.j]];
            int n5 = ((ck)this).a + u.a[this.j] * 3 + u.a[u.f[this.j]];
            int n6 = ((ck)this).b + u.b[this.j] * 3 + u.b[u.f[this.j]];
            if (n3 > 0 && n3 < n.a.a - 1 && n4 > 0 && n4 < n.a.b - 1 && (ck3 = n.a.a[n4][n3]) != null && ck3 instanceof al) {
                ((al)ck3).a(n2, this.a());
            }
            if (n5 > 0 && n5 < n.a.a - 1 && n6 > 0 && n6 < n.a.b - 1 && (ck3 = n.a.a[n6][n5]) != null && ck3 instanceof al) {
                ((al)ck3).a(n2, this.a());
            }
        }
    }

    private final void h() {
        ck ck2;
        if (this.e >= 0 && this.e <= 5) {
            n.a.e = h.a(-4, 4);
            n.a.f = h.a(-4, 4);
        }
        int n2 = this.a * 3 + 35 + n.a().g * 3 / 2;
        if ((this.e == 2 || this.e == 6) && (ck2 = this.a(this.j, (byte)0)) != null && ck2 instanceof al) {
            ((al)ck2).a(n2, this.a());
        }
        if ((this.e == 3 || this.e == 7) && (ck2 = this.a(this.j, (byte)1)) != null && ck2 instanceof al) {
            ((al)ck2).a(n2, this.a());
        }
        if ((this.e == 4 || this.e == 8) && (ck2 = this.a(this.j, (byte)2)) != null && ck2 instanceof al) {
            ((al)ck2).a(n2, this.a());
        }
        if ((this.e == 5 || this.e == 9) && (ck2 = this.a(this.j, (byte)3)) != null && ck2 instanceof al) {
            ((al)ck2).a(n2, this.a());
        }
    }

    private final void a(short s2, int n2) {
        ae ae2 = n.a;
        if (this.e < s2) {
            ck ck2;
            int n3 = ((ck)this).a + this.j[0];
            int n4 = ((ck)this).b + this.j[1];
            if (n3 >= 0 && n4 >= 0 && n3 < ae2.a && n4 < ae2.b && (ck2 = ae2.a[n4][n3]) != null && ck2 instanceof al) {
                ((al)ck2).a(n2, this.a());
            }
            n3 = ((ck)this).a + this.j[2];
            n4 = ((ck)this).b + this.j[3];
            if (n3 >= 0 && n4 >= 0 && n3 < ae2.a && n4 < ae2.b && (ck2 = ae2.a[n4][n3]) != null && ck2 instanceof al) {
                ((al)ck2).a(n2, this.a());
            }
        }
    }

    private final void a(int n2, int n3, int n4, int n5) {
        int n6 = this.e - n2;
        int n7 = n6 / n3;
        if (n6 < 0) {
            return;
        }
        if (n6 % n3 != 0) {
            return;
        }
        if (n7 == 0) {
            ck ck2 = this.a(this.j, (byte)0);
            if (ck2 != null && ck2 instanceof al) {
                ((al)ck2).a(n5, this.a());
                return;
            }
        } else {
            for (byte by2 = 1; by2 <= 4; by2 = (byte)(by2 + 1)) {
                ck ck3 = this.a(by2, (byte)n7);
                if (ck3 == null || !(ck3 instanceof al)) continue;
                ((al)ck3).a(n5, this.a());
            }
        }
    }

    private final void a(byte by2) {
        if (this.e == 0) {
            for (int i2 = 0; i2 <= 3; ++i2) {
                ck ck2 = this.a(this.j, (byte)i2);
                if (ck2 == null || !(ck2 instanceof al) || ck2 instanceof av || ((al)ck2).a.c != this.a()) continue;
                ((al)ck2).c(by2);
            }
        }
    }

    public final void a(Graphics graphics, int n2, int n3) {
        int n4 = n2 + ((ck)this).c + ((ck)this).c;
        int n5 = n3 + ((ck)this).d + ((ck)this).d;
        int n6 = ce.a[0].getHeight();
        Image[] imageArray = ce.a[12];
        Image image = imageArray[0];
        Image image2 = imageArray[1];
        Image image3 = imageArray[2];
        Image image4 = imageArray[3];
        Image image5 = ce.a[0];
        Image image6 = ce.a[1];
        switch (this.i) {
            case 0: {
                return;
            }
            case 1: {
                switch (this.b) {
                    case 0: {
                        graphics.drawImage(image3, n4, n5, 33);
                        break;
                    }
                    case 1: {
                        graphics.drawImage(image4, n4, n5, 33);
                        break;
                    }
                    case 2: {
                        graphics.drawImage(image4, n4, n5, 33);
                        graphics.drawImage(image, n4, n5 + 3, 33);
                        break;
                    }
                    case 3: {
                        graphics.drawImage(image4, n4, n5, 33);
                        graphics.drawImage(image, n4, n5 + 3, 33);
                        graphics.drawImage(image2, n4, n5 + 6, 33);
                        break;
                    }
                    case 4: {
                        graphics.drawImage(image3, n4, n5, 33);
                        graphics.drawImage(image2, n4, n5 + 6, 33);
                        break;
                    }
                    case 5: {
                        n6 = n6 * 7 / 10;
                        as.a(graphics, n4 - 20, n5 - 50, 40, 50);
                        graphics.drawImage(image5, n4, n5 + n6, 33);
                        graphics.setClip(0, 0, as.a, as.b);
                        break;
                    }
                    case 6: {
                        n6 = n6 * 5 / 10;
                        as.a(graphics, n4 - 20, n5 - 50, 40, 50);
                        graphics.drawImage(image5, n4, n5 + n6, 33);
                        graphics.setClip(0, 0, as.a, as.b);
                        graphics.drawImage(image3, n4, n5, 33);
                        break;
                    }
                    case 7: {
                        n6 = n6 * 3 / 10;
                        as.a(graphics, n4 - 20, n5 - 50, 40, 50);
                        graphics.drawImage(image5, n4, n5 + n6, 33);
                        graphics.setClip(0, 0, as.a, as.b);
                        graphics.drawImage(image4, n4, n5, 33);
                        break;
                    }
                    case 8: {
                        n6 = n6 * 1 / 5;
                        as.a(graphics, n4 - 20, n5 - 50, 40, 50);
                        graphics.drawImage(image5, n4, n5 + n6, 33);
                        graphics.setClip(0, 0, as.a, as.b);
                        graphics.drawImage(image4, n4, n5, 33);
                        break;
                    }
                    case 9: {
                        graphics.drawImage(image5, n4, n5, 33);
                        graphics.drawImage(image3, n4, n5, 33);
                    }
                }
                return;
            }
            case 2: {
                graphics.drawImage(image6, n4, n5 + (this.l % 3 == 0 ? 1 : 0), 33);
                switch (this.b) {
                    case 1: {
                        graphics.drawImage(image, n4, n5 + 3, 33);
                        break;
                    }
                    case 2: {
                        graphics.drawImage(image, n4, n5 + 3, 33);
                        graphics.drawImage(image2, n4, n5 + 6, 33);
                        break;
                    }
                    case 3: {
                        graphics.drawImage(image2, n4, n5 + 6, 33);
                    }
                }
                return;
            }
            case 3: {
                if (this.b < 6) {
                    graphics.drawImage(image6, n4, n5, 33);
                }
                switch (this.b) {
                    case 6: {
                        n6 = n6 * 1 / 10;
                        as.a(graphics, n4 - 20, n5 - 50, 40, 50);
                        graphics.drawImage(image5, n4, n5 + n6, 33);
                        graphics.setClip(0, 0, as.a, as.b);
                        graphics.drawImage(image4, n4, n5, 33);
                        graphics.drawImage(image, n4, n5 + 3, 33);
                        graphics.drawImage(image2, n4, n5 + 6, 33);
                        return;
                    }
                    case 7: {
                        n6 = n6 * 3 / 10;
                        as.a(graphics, n4 - 20, n5 - 50, 40, 50);
                        graphics.drawImage(image5, n4, n5 + n6, 33);
                        graphics.setClip(0, 0, as.a, as.b);
                        graphics.drawImage(image4, n4, n5, 33);
                        graphics.drawImage(image, n4, n5 + 3, 33);
                        return;
                    }
                    case 8: {
                        n6 = n6 * 5 / 10;
                        as.a(graphics, n4 - 20, n5 - 50, 40, 50);
                        graphics.drawImage(image5, n4, n5 + n6, 33);
                        graphics.setClip(0, 0, as.a, as.b);
                        graphics.drawImage(image4, n4, n5, 33);
                        return;
                    }
                    case 9: {
                        n6 = n6 * 7 / 10;
                        as.a(graphics, n4 - 20, n5 - 50, 40, 50);
                        graphics.drawImage(image5, n4, n5 + n6, 33);
                        graphics.setClip(0, 0, as.a, as.b);
                        graphics.drawImage(image3, n4, n5, 33);
                    }
                }
            }
        }
    }

    public final void a(Graphics graphics) {
        int n2 = (r.g - 80) / 2;
        boolean bl2 = false;
        cb.a(graphics, n2, 2, 80, 25, false);
        graphics.setClip(0, 0, r.g, r.h);
        graphics.translate(n2 + 2, 4);
        boolean bl3 = false;
        cb.a(graphics, 0, 0, 80, 21, ce.c.a(this.f * 8 + this.m * 2), 0, 1, 6233919, 0xFFFFFF);
        graphics.setColor(0);
        graphics.fillRect(3, 18, 74, 2);
        graphics.translate(-(n2 + 2), -4);
        graphics.setColor(0xFF5555);
        graphics.fillRect(n2 + 5, 22, 70 * (this.k - this.l + 1) / this.k, 2);
    }

    private final void i() {
        ae ae2 = n.a;
        switch (this.e) {
            case 0: {
                bw.a((byte)16, false);
            }
            case 5: {
                p.a(ae2, ((ck)this).c, ((ck)this).d, (byte)1);
                return;
            }
            case 1: 
            case 6: {
                p.a(ae2, (short)(((ck)this).c + u.a[this.j] * 16), (short)(((ck)this).d + u.b[this.j] * 16), (byte)1);
                return;
            }
            case 2: 
            case 7: {
                p.a(ae2, (short)(((ck)this).c + u.a[this.j] * 32), (short)(((ck)this).d + u.b[this.j] * 32), (byte)1);
                return;
            }
            case 4: 
            case 8: {
                bw.a((byte)16, false);
                p.a(ae2, (short)(((ck)this).c + u.a[this.j] * 48), (short)(((ck)this).d + u.b[this.j] * 48), (byte)2);
                p.a(ae2, (short)(((ck)this).c + u.a[this.j] * 48 + u.a[u.e[this.j]] * 16), (short)(((ck)this).d + u.b[this.j] * 48 + u.b[u.e[this.j]] * 16), (byte)2);
                p.a(ae2, (short)(((ck)this).c + u.a[this.j] * 48 + u.a[u.f[this.j]] * 16), (short)(((ck)this).d + u.b[this.j] * 48 + u.b[u.f[this.j]] * 16), (byte)2);
            }
        }
    }

    private final void j() {
        ae ae2 = n.a;
        switch (this.e) {
            case 0: {
                bw.a((byte)17, false);
            }
            case 4: {
                p.a(ae2, ((ck)this).c, ((ck)this).d, (byte)4);
                return;
            }
            case 1: 
            case 5: {
                p.a(ae2, (short)(((ck)this).c + u.a[this.j] * 16), (short)(((ck)this).d + u.b[this.j] * 16), (byte)4);
                return;
            }
            case 2: 
            case 6: {
                p.a(ae2, (short)(((ck)this).c + u.a[this.j] * 32), (short)(((ck)this).d + u.b[this.j] * 32), (byte)4);
                return;
            }
            case 3: 
            case 7: {
                bw.a((byte)17, false);
                p.a(ae2, (short)(((ck)this).c + u.a[this.j] * 48), (short)(((ck)this).d + u.b[this.j] * 48), (byte)4);
            }
        }
    }

    private final void a(int n2, int n3, short s2, byte by2) {
        ae ae2 = n.a;
        if (this.e < s2) {
            byte by3 = this.j[0];
            byte by4 = this.j[1];
            p.a(ae2, (short)(n2 + 16 * by3), (short)(n3 + 16 * by4), by2);
            by3 = this.j[2];
            by4 = this.j[3];
            p.a(ae2, (short)(n2 + 16 * by3), (short)(n3 + 16 * by4), by2);
        }
    }

    private final void a(byte by2, int n2, int n3) {
        int n4 = this.e - n2;
        if (n4 < 0) {
            return;
        }
        if (n4 % n3 != 0) {
            return;
        }
        ae ae2 = n.a;
        if (n4 / n3 == 0) {
            p.a(ae2, ((ck)this).c, ((ck)this).d, by2);
            return;
        }
        if (n4 / n3 == 1) {
            p.a(ae2, (short)(((ck)this).c + 16), ((ck)this).d, by2);
            p.a(ae2, (short)(((ck)this).c - 16), ((ck)this).d, by2);
            p.a(ae2, ((ck)this).c, (short)(((ck)this).d + 16), by2);
            p.a(ae2, ((ck)this).c, (short)(((ck)this).d - 16), by2);
            return;
        }
        if (n4 / n3 == 2) {
            p.a(ae2, (short)(((ck)this).c + 32), ((ck)this).d, by2);
            p.a(ae2, (short)(((ck)this).c - 32), ((ck)this).d, by2);
            p.a(ae2, ((ck)this).c, (short)(((ck)this).d + 32), by2);
            p.a(ae2, ((ck)this).c, (short)(((ck)this).d - 32), by2);
            return;
        }
        if (n4 / n3 == 3) {
            p.a(ae2, (short)(((ck)this).c + 48), ((ck)this).d, by2);
            p.a(ae2, (short)(((ck)this).c - 48), ((ck)this).d, by2);
            p.a(ae2, ((ck)this).c, (short)(((ck)this).d + 48), by2);
            p.a(ae2, ((ck)this).c, (short)(((ck)this).d - 48), by2);
        }
    }

    private final void a(short s2) {
        if (this.e == 0) {
            ae ae2 = n.a;
            for (int i2 = 1; i2 <= 3; ++i2) {
                ck ck2 = this.a(this.j, (byte)i2);
                if (ck2 != null && ck2 instanceof al && !(ck2 instanceof av) && ((al)ck2).a.c == this.a()) {
                    ((al)ck2).a(new bj(0, s2, this.f, this.m));
                    continue;
                }
                p.a(ae2, (short)(((ck)this).c + u.a[this.j] * 16 * i2), (short)(((ck)this).d + u.b[this.j] * 16 * i2), (byte)10);
            }
        }
    }

    private static final void a(ae ae2, short s2, short s3, byte by2) {
        ae2.b(new y(s2, s3, by2));
    }

    private final void b(int n2) {
        this.j[0] = (byte)(ck.a.nextInt() % (n2 + 1));
        this.j[1] = (byte)(ck.a.nextInt() % (n2 - Math.abs(this.j[0]) + 1));
        this.j[2] = (byte)(ck.a.nextInt() % (n2 + 1));
        this.j[3] = (byte)(ck.a.nextInt() % (n2 - Math.abs(this.j[2]) + 1));
    }
}

