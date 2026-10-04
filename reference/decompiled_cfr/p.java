/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 *  javax.microedition.lcdui.Image
 */
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public final class p
extends ck
implements u {
    public static final short[] var_short_arr_a;
    public static final byte[] var_byte_arr_h;
    public static final short[] var_short_arr_b;
    private static final byte[] var_byte_arr_i;
    private static final short[] var_short_arr_d;
    private byte[] var_byte_arr_j = new byte[4];
    public byte f;
    public byte g;
    public byte var_byte_h;
    public short var_short_a;
    public int var_int_a;
    public int var_int_b;
    public byte var_byte_i;
    public byte var_byte_j;
    private short var_short_b;
    private short e;
    private byte k;
    private byte l;
    private byte m;
    private boolean var_boolean_d;
    public short[] c;

    public p(short s2, short s3, byte by2) {
        super(s2, s3, (byte)8, (byte)8);
        this.f = by2;
        this.c = new short[3];
        this.c[0] = var_short_arr_b[by2 * 3 + 0];
        this.c[1] = var_short_arr_b[by2 * 3 + 1];
        this.c[2] = var_short_arr_b[by2 * 3 + 2];
        this.var_short_a = var_short_arr_a[by2];
        this.void_a();
        this.var_int_a = 0;
        this.d();
        this.a(true, (byte)0, true);
        this.a(false, (byte)1, true);
    }

    public final void a(int n2) {
        this.var_int_a += n2;
        while (this.var_int_a >= this.var_int_b) {
            this.var_int_a -= this.var_int_b;
            this.f();
        }
    }

    private final void f() {
        this.var_short_a = (short)(this.var_short_a + 1);
        this.void_a();
        if (this.var_short_a == var_byte_arr_h[1]) {
            this.a(false, (byte)1, true);
        }
    }

    public final void void_a() {
        this.var_int_b = this.var_short_a * this.var_short_a * this.var_short_a - this.var_short_a * this.var_short_a + this.var_short_a * 80;
    }

    public final void c() {
        this.var_byte_i = 0;
    }

    public final byte byte_a() {
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

    public final boolean boolean_a() {
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
                this.var_byte_h = by2;
            }
            return true;
        }
        if (var_byte_arr_h[by2] > this.var_short_a) {
            return false;
        }
        if (bl2) {
            this.g = by2;
            if (bl3 && by2 != -1) {
                this.c[by2] = var_short_arr_b[this.f * 3 + by2];
            }
        } else {
            this.var_byte_h = by2;
            if (bl3 && by2 != -1) {
                this.c[by2] = var_short_arr_b[this.f * 3 + by2];
            }
        }
        return true;
    }

    public final void a(boolean bl2, byte by2, int n2, int n3) {
        if (bl2 && this.g >= 0 && this.g <= 2 && this.c[this.g] == 0) {
            this.m = this.g;
            this.c[this.g] = var_short_arr_b[this.f * 3 + this.g];
        } else if (!bl2 && this.var_byte_h >= 0 && this.var_byte_h <= 2 && this.c[this.var_byte_h] == 0) {
            this.m = this.var_byte_h;
            this.c[this.var_byte_h] = var_short_arr_b[this.f * 3 + this.var_byte_h];
        } else {
            return;
        }
        this.var_byte_i = 1;
        this.var_short_b = (short)-1;
        this.l = this.k = var_byte_arr_i[this.f * 3 + this.m];
        this.var_boolean_d = false;
        this.a((short)((n2 + u.var_byte_arr_a[by2]) * 16), (short)((n3 + u.b[by2]) * 16));
        this.var_byte_j = by2;
        this.var_byte_b = (byte)(this.var_short_d >> 4);
        this.var_byte_a = (byte)(this.var_short_c >> 4);
        this.var_boolean_b = false;
        this.var_boolean_a = false;
    }

    public final void e() {
        if ((this.var_byte_i == 0 || this.m != this.g) && this.g != -1 && this.c[this.g] > 0) {
            byte by2 = this.g;
            this.c[by2] = (short)(this.c[by2] - 1);
        }
        if ((this.var_byte_i == 0 || this.m != this.var_byte_h) && this.var_byte_h != -1 && this.c[this.var_byte_h] > 0) {
            byte by3 = this.var_byte_h;
            this.c[by3] = (short)(this.c[by3] - 1);
        }
        switch (this.var_byte_i) {
            case 0: {
                return;
            }
            case 1: {
                this.var_short_b = (short)(this.var_short_b + 1);
                if (this.var_short_b < 10) break;
                if (this.l > 0) {
                    this.var_byte_i = (byte)2;
                } else {
                    this.var_byte_i = (byte)3;
                    this.var_short_b = 0;
                    this.e = 0;
                }
                this.var_short_b = 0;
                break;
            }
            case 2: {
                this.var_short_b = (short)(this.var_short_b + 1);
                this.l = (byte)(this.l - 1);
                if (this.var_short_b >= 4) {
                    this.var_short_b = 0;
                }
                if (this.l > 0) break;
                this.var_byte_i = (byte)3;
                this.var_short_b = 0;
                this.e = 0;
                break;
            }
            case 3: {
                this.var_short_b = (short)(this.var_short_b + 1);
                if (this.var_short_b < 11) break;
                this.var_short_b = (short)11;
                if (!this.var_boolean_d) break;
                this.var_byte_i = 0;
                this.var_byte_j = 0;
            }
        }
        if (this.var_byte_i == 3) {
            this.void_a(this.f, this.m);
            this.e = (short)(this.e + 1);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private final void void_a(byte by2, byte by3) {
        ao ao2 = n.ao_a();
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
                        ao2.boolean_a((byte)5);
                        ao2.var_boolean_d = true;
                        bw.a((byte)21, false);
                        break;
                    }
                    case 2: {
                        if (this.e == 0) {
                            ao2.a(new bj(0, 160, by2, by3));
                            ao2.var_boolean_g = true;
                            bw.a((byte)21, false);
                        }
                        if (this.e != var_short_arr_d[by2 * 3 + by3]) break;
                        ao2.var_boolean_g = false;
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
                            ao2.var_boolean_h = true;
                            bw.a((byte)20, false);
                        }
                        if (this.e != var_short_arr_d[by2 * 3 + by3]) break;
                        ao2.var_boolean_h = false;
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
                        ao2.boolean_a((byte)6);
                        ao2.var_boolean_e = true;
                        bw.a((byte)21, false);
                        break;
                    }
                    case 2: {
                        if (this.e != 0) break;
                        ao2.a(new bj(0, 12, by2, by3));
                        ((o)ao2).b.removeAllElements();
                        ao2.var_boolean_d = false;
                        ao2.var_boolean_e = false;
                        bw.a((byte)21, false);
                        break;
                    }
                }
                break;
            }
            case 3: {
                switch (by3) {
                    case 0: {
                        this.a(4, 3, 3, this.var_short_a * 3 + 35 + n.ao_a().var_short_g);
                        this.a(10, 3, 3, this.var_short_a * 3 + 35 + n.ao_a().var_short_g);
                        this.a(16, 3, 3, this.var_short_a * 3 + 35 + n.ao_a().var_short_g);
                        this.a((byte)5, 0, 3);
                        this.a((byte)5, 6, 3);
                        this.a((byte)5, 12, 3);
                        if (this.e != 0 && this.e != 6 && this.e != 12) break;
                        bw.a((byte)16, false);
                        break;
                    }
                    case 1: {
                        this.b(2);
                        this.a((short)80, this.var_short_a + 45 + n.ao_a().var_short_g * 3 / 2);
                        this.a((int)this.var_short_c, (int)this.var_short_d, (short)80, (byte)6);
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
                        this.a(4, 5, 3, this.var_short_a * 3 + 35 + n.ao_a().var_short_g);
                        this.a(10, 5, 3, this.var_short_a * 3 + 35 + n.ao_a().var_short_g);
                        this.a(16, 5, 3, this.var_short_a * 3 + 35 + n.ao_a().var_short_g);
                        this.a((byte)7, 0, 5);
                        this.a((byte)7, 6, 5);
                        this.a((byte)7, 12, 5);
                        if (this.e != 0 && this.e != 6 && this.e != 12 && this.e != 18 && this.e != 24) break;
                        bw.a((byte)18, false);
                        break;
                    }
                    case 1: {
                        this.b(2);
                        ao2.var_boolean_f = Math.abs(this.var_byte_a - ao2.var_byte_a) + Math.abs(this.var_byte_b - ao2.var_byte_b) <= 2;
                        this.a((int)this.var_short_c, (int)this.var_short_d, (short)80, (byte)8);
                        if (this.e % 8 == 0) {
                            bw.a((byte)18, false);
                        }
                        if (this.e != var_short_arr_d[by2 * 3 + by3]) break;
                        ao2.var_boolean_f = false;
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
                        this.a(4, 5, 3, this.var_short_a * 3 + 35 + n.ao_a().var_short_g);
                        this.a(10, 5, 3, this.var_short_a * 3 + 35 + n.ao_a().var_short_g);
                        this.a(16, 5, 3, this.var_short_a * 3 + 35 + n.ao_a().var_short_g);
                        this.a((byte)4, 0, 5);
                        this.a((byte)4, 6, 5);
                        this.a((byte)4, 12, 5);
                        if (this.e != 0 && this.e != 6 && this.e != 12 && this.e != 18 && this.e != 24) break;
                        bw.a((byte)17, false);
                        break;
                    }
                    case 1: {
                        this.b(2);
                        this.a((short)80, this.var_short_a + 45 + n.ao_a().var_short_g * 3 / 2);
                        this.a((int)this.var_short_c, (int)this.var_short_d, (short)80, (byte)9);
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
        if (this.e == var_short_arr_d[by2 * 3 + by3]) {
            this.var_boolean_d = true;
        }
    }

    private final void g() {
        ck ck2;
        int n2 = this.var_short_a * 3 + 40 + n.ao_a().var_short_g;
        if ((this.e == 1 || this.e == 5) && (ck2 = this.ck_a(this.var_byte_j, (byte)0)) != null && ck2 instanceof al) {
            ((al)ck2).a(n2, this.byte_a());
        }
        if ((this.e == 2 || this.e == 6) && (ck2 = this.ck_a(this.var_byte_j, (byte)1)) != null && ck2 instanceof al) {
            ((al)ck2).a(n2, this.byte_a());
        }
        if ((this.e == 3 || this.e == 7) && (ck2 = this.ck_a(this.var_byte_j, (byte)2)) != null && ck2 instanceof al) {
            ((al)ck2).a(n2, this.byte_a());
        }
        if ((this.e == 5 || this.e == 9) && (ck2 = this.ck_a(this.var_byte_j, (byte)3)) != null && ck2 instanceof al) {
            ((al)ck2).a(n2, this.byte_a());
        }
        if (this.e == 8 || this.e == 12) {
            ck ck3;
            int n3 = this.var_byte_a + u.var_byte_arr_a[this.var_byte_j] * 3 + u.var_byte_arr_a[u.e[this.var_byte_j]];
            int n4 = this.var_byte_b + u.b[this.var_byte_j] * 3 + u.b[u.e[this.var_byte_j]];
            int n5 = this.var_byte_a + u.var_byte_arr_a[this.var_byte_j] * 3 + u.var_byte_arr_a[u.f[this.var_byte_j]];
            int n6 = this.var_byte_b + u.b[this.var_byte_j] * 3 + u.b[u.f[this.var_byte_j]];
            if (n3 > 0 && n3 < n.var_ae_a.var_int_a - 1 && n4 > 0 && n4 < n.var_ae_a.var_int_b - 1 && (ck3 = n.var_ae_a.var_ck_arr_arr_a[n4][n3]) != null && ck3 instanceof al) {
                ((al)ck3).a(n2, this.byte_a());
            }
            if (n5 > 0 && n5 < n.var_ae_a.var_int_a - 1 && n6 > 0 && n6 < n.var_ae_a.var_int_b - 1 && (ck3 = n.var_ae_a.var_ck_arr_arr_a[n6][n5]) != null && ck3 instanceof al) {
                ((al)ck3).a(n2, this.byte_a());
            }
        }
    }

    private final void h() {
        ck ck2;
        if (this.e >= 0 && this.e <= 5) {
            n.var_ae_a.var_int_e = h.a(-4, 4);
            n.var_ae_a.f = h.a(-4, 4);
        }
        int n2 = this.var_short_a * 3 + 35 + n.ao_a().var_short_g * 3 / 2;
        if ((this.e == 2 || this.e == 6) && (ck2 = this.ck_a(this.var_byte_j, (byte)0)) != null && ck2 instanceof al) {
            ((al)ck2).a(n2, this.byte_a());
        }
        if ((this.e == 3 || this.e == 7) && (ck2 = this.ck_a(this.var_byte_j, (byte)1)) != null && ck2 instanceof al) {
            ((al)ck2).a(n2, this.byte_a());
        }
        if ((this.e == 4 || this.e == 8) && (ck2 = this.ck_a(this.var_byte_j, (byte)2)) != null && ck2 instanceof al) {
            ((al)ck2).a(n2, this.byte_a());
        }
        if ((this.e == 5 || this.e == 9) && (ck2 = this.ck_a(this.var_byte_j, (byte)3)) != null && ck2 instanceof al) {
            ((al)ck2).a(n2, this.byte_a());
        }
    }

    private final void a(short s2, int n2) {
        ae ae2 = n.var_ae_a;
        if (this.e < s2) {
            ck ck2;
            int n3 = this.var_byte_a + this.var_byte_arr_j[0];
            int n4 = this.var_byte_b + this.var_byte_arr_j[1];
            if (n3 >= 0 && n4 >= 0 && n3 < ae2.var_int_a && n4 < ae2.var_int_b && (ck2 = ae2.var_ck_arr_arr_a[n4][n3]) != null && ck2 instanceof al) {
                ((al)ck2).a(n2, this.byte_a());
            }
            n3 = this.var_byte_a + this.var_byte_arr_j[2];
            n4 = this.var_byte_b + this.var_byte_arr_j[3];
            if (n3 >= 0 && n4 >= 0 && n3 < ae2.var_int_a && n4 < ae2.var_int_b && (ck2 = ae2.var_ck_arr_arr_a[n4][n3]) != null && ck2 instanceof al) {
                ((al)ck2).a(n2, this.byte_a());
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
            ck ck2 = this.ck_a(this.var_byte_j, (byte)0);
            if (ck2 != null && ck2 instanceof al) {
                ((al)ck2).a(n5, this.byte_a());
                return;
            }
        } else {
            for (byte by2 = 1; by2 <= 4; by2 = (byte)(by2 + 1)) {
                ck ck3 = this.ck_a(by2, (byte)n7);
                if (ck3 == null || !(ck3 instanceof al)) continue;
                ((al)ck3).a(n5, this.byte_a());
            }
        }
    }

    private final void a(byte by2) {
        if (this.e == 0) {
            for (int i2 = 0; i2 <= 3; ++i2) {
                ck ck2 = this.ck_a(this.var_byte_j, (byte)i2);
                if (ck2 == null || !(ck2 instanceof al) || ck2 instanceof av || ((al)ck2).var_j_a.var_byte_c != this.byte_a()) continue;
                ((al)ck2).c(by2);
            }
        }
    }

    public final void a(Graphics graphics, int n2, int n3) {
        int n4 = n2 + this.var_short_c + this.var_byte_c;
        int n5 = n3 + this.var_short_d + this.var_byte_d;
        int n6 = ce.var_javax_microedition_lcdui_Image_arr_a[0].getHeight();
        Image[] imageArray = ce.var_javax_microedition_lcdui_Image_arr_arr_a[12];
        Image image = imageArray[0];
        Image image2 = imageArray[1];
        Image image3 = imageArray[2];
        Image image4 = imageArray[3];
        Image image5 = ce.var_javax_microedition_lcdui_Image_arr_a[0];
        Image image6 = ce.var_javax_microedition_lcdui_Image_arr_a[1];
        switch (this.var_byte_i) {
            case 0: {
                return;
            }
            case 1: {
                switch (this.var_short_b) {
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
                        graphics.setClip(0, 0, as.var_int_a, as.b);
                        break;
                    }
                    case 6: {
                        n6 = n6 * 5 / 10;
                        as.a(graphics, n4 - 20, n5 - 50, 40, 50);
                        graphics.drawImage(image5, n4, n5 + n6, 33);
                        graphics.setClip(0, 0, as.var_int_a, as.b);
                        graphics.drawImage(image3, n4, n5, 33);
                        break;
                    }
                    case 7: {
                        n6 = n6 * 3 / 10;
                        as.a(graphics, n4 - 20, n5 - 50, 40, 50);
                        graphics.drawImage(image5, n4, n5 + n6, 33);
                        graphics.setClip(0, 0, as.var_int_a, as.b);
                        graphics.drawImage(image4, n4, n5, 33);
                        break;
                    }
                    case 8: {
                        n6 = n6 * 1 / 5;
                        as.a(graphics, n4 - 20, n5 - 50, 40, 50);
                        graphics.drawImage(image5, n4, n5 + n6, 33);
                        graphics.setClip(0, 0, as.var_int_a, as.b);
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
                switch (this.var_short_b) {
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
                if (this.var_short_b < 6) {
                    graphics.drawImage(image6, n4, n5, 33);
                }
                switch (this.var_short_b) {
                    case 6: {
                        n6 = n6 * 1 / 10;
                        as.a(graphics, n4 - 20, n5 - 50, 40, 50);
                        graphics.drawImage(image5, n4, n5 + n6, 33);
                        graphics.setClip(0, 0, as.var_int_a, as.b);
                        graphics.drawImage(image4, n4, n5, 33);
                        graphics.drawImage(image, n4, n5 + 3, 33);
                        graphics.drawImage(image2, n4, n5 + 6, 33);
                        return;
                    }
                    case 7: {
                        n6 = n6 * 3 / 10;
                        as.a(graphics, n4 - 20, n5 - 50, 40, 50);
                        graphics.drawImage(image5, n4, n5 + n6, 33);
                        graphics.setClip(0, 0, as.var_int_a, as.b);
                        graphics.drawImage(image4, n4, n5, 33);
                        graphics.drawImage(image, n4, n5 + 3, 33);
                        return;
                    }
                    case 8: {
                        n6 = n6 * 5 / 10;
                        as.a(graphics, n4 - 20, n5 - 50, 40, 50);
                        graphics.drawImage(image5, n4, n5 + n6, 33);
                        graphics.setClip(0, 0, as.var_int_a, as.b);
                        graphics.drawImage(image4, n4, n5, 33);
                        return;
                    }
                    case 9: {
                        n6 = n6 * 7 / 10;
                        as.a(graphics, n4 - 20, n5 - 50, 40, 50);
                        graphics.drawImage(image5, n4, n5 + n6, 33);
                        graphics.setClip(0, 0, as.var_int_a, as.b);
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
        cb.a(graphics, 0, 0, 80, 21, ce.var_z_c.a(this.f * 8 + this.m * 2), 0, 1, 6233919, 0xFFFFFF);
        graphics.setColor(0);
        graphics.fillRect(3, 18, 74, 2);
        graphics.translate(-(n2 + 2), -4);
        graphics.setColor(0xFF5555);
        graphics.fillRect(n2 + 5, 22, 70 * (this.k - this.l + 1) / this.k, 2);
    }

    private final void i() {
        ae ae2 = n.var_ae_a;
        switch (this.e) {
            case 0: {
                bw.a((byte)16, false);
            }
            case 5: {
                p.a(ae2, this.var_short_c, this.var_short_d, (byte)1);
                return;
            }
            case 1: 
            case 6: {
                p.a(ae2, (short)(this.var_short_c + u.var_byte_arr_a[this.var_byte_j] * 16), (short)(this.var_short_d + u.b[this.var_byte_j] * 16), (byte)1);
                return;
            }
            case 2: 
            case 7: {
                p.a(ae2, (short)(this.var_short_c + u.var_byte_arr_a[this.var_byte_j] * 32), (short)(this.var_short_d + u.b[this.var_byte_j] * 32), (byte)1);
                return;
            }
            case 4: 
            case 8: {
                bw.a((byte)16, false);
                p.a(ae2, (short)(this.var_short_c + u.var_byte_arr_a[this.var_byte_j] * 48), (short)(this.var_short_d + u.b[this.var_byte_j] * 48), (byte)2);
                p.a(ae2, (short)(this.var_short_c + u.var_byte_arr_a[this.var_byte_j] * 48 + u.var_byte_arr_a[u.e[this.var_byte_j]] * 16), (short)(this.var_short_d + u.b[this.var_byte_j] * 48 + u.b[u.e[this.var_byte_j]] * 16), (byte)2);
                p.a(ae2, (short)(this.var_short_c + u.var_byte_arr_a[this.var_byte_j] * 48 + u.var_byte_arr_a[u.f[this.var_byte_j]] * 16), (short)(this.var_short_d + u.b[this.var_byte_j] * 48 + u.b[u.f[this.var_byte_j]] * 16), (byte)2);
            }
        }
    }

    private final void j() {
        ae ae2 = n.var_ae_a;
        switch (this.e) {
            case 0: {
                bw.a((byte)17, false);
            }
            case 4: {
                p.a(ae2, this.var_short_c, this.var_short_d, (byte)4);
                return;
            }
            case 1: 
            case 5: {
                p.a(ae2, (short)(this.var_short_c + u.var_byte_arr_a[this.var_byte_j] * 16), (short)(this.var_short_d + u.b[this.var_byte_j] * 16), (byte)4);
                return;
            }
            case 2: 
            case 6: {
                p.a(ae2, (short)(this.var_short_c + u.var_byte_arr_a[this.var_byte_j] * 32), (short)(this.var_short_d + u.b[this.var_byte_j] * 32), (byte)4);
                return;
            }
            case 3: 
            case 7: {
                bw.a((byte)17, false);
                p.a(ae2, (short)(this.var_short_c + u.var_byte_arr_a[this.var_byte_j] * 48), (short)(this.var_short_d + u.b[this.var_byte_j] * 48), (byte)4);
            }
        }
    }

    private final void a(int n2, int n3, short s2, byte by2) {
        ae ae2 = n.var_ae_a;
        if (this.e < s2) {
            byte by3 = this.var_byte_arr_j[0];
            byte by4 = this.var_byte_arr_j[1];
            p.a(ae2, (short)(n2 + 16 * by3), (short)(n3 + 16 * by4), by2);
            by3 = this.var_byte_arr_j[2];
            by4 = this.var_byte_arr_j[3];
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
        ae ae2 = n.var_ae_a;
        if (n4 / n3 == 0) {
            p.a(ae2, this.var_short_c, this.var_short_d, by2);
            return;
        }
        if (n4 / n3 == 1) {
            p.a(ae2, (short)(this.var_short_c + 16), this.var_short_d, by2);
            p.a(ae2, (short)(this.var_short_c - 16), this.var_short_d, by2);
            p.a(ae2, this.var_short_c, (short)(this.var_short_d + 16), by2);
            p.a(ae2, this.var_short_c, (short)(this.var_short_d - 16), by2);
            return;
        }
        if (n4 / n3 == 2) {
            p.a(ae2, (short)(this.var_short_c + 32), this.var_short_d, by2);
            p.a(ae2, (short)(this.var_short_c - 32), this.var_short_d, by2);
            p.a(ae2, this.var_short_c, (short)(this.var_short_d + 32), by2);
            p.a(ae2, this.var_short_c, (short)(this.var_short_d - 32), by2);
            return;
        }
        if (n4 / n3 == 3) {
            p.a(ae2, (short)(this.var_short_c + 48), this.var_short_d, by2);
            p.a(ae2, (short)(this.var_short_c - 48), this.var_short_d, by2);
            p.a(ae2, this.var_short_c, (short)(this.var_short_d + 48), by2);
            p.a(ae2, this.var_short_c, (short)(this.var_short_d - 48), by2);
        }
    }

    private final void a(short s2) {
        if (this.e == 0) {
            ae ae2 = n.var_ae_a;
            for (int i2 = 1; i2 <= 3; ++i2) {
                ck ck2 = this.ck_a(this.var_byte_j, (byte)i2);
                if (ck2 != null && ck2 instanceof al && !(ck2 instanceof av) && ((al)ck2).var_j_a.var_byte_c == this.byte_a()) {
                    ((al)ck2).a(new bj(0, s2, this.f, this.m));
                    continue;
                }
                p.a(ae2, (short)(this.var_short_c + u.var_byte_arr_a[this.var_byte_j] * 16 * i2), (short)(this.var_short_d + u.b[this.var_byte_j] * 16 * i2), (byte)10);
            }
        }
    }

    private static final void a(ae ae2, short s2, short s3, byte by2) {
        ae2.b(new y(s2, s3, by2));
    }

    private final void b(int n2) {
        this.var_byte_arr_j[0] = (byte)(ck.var_java_util_Random_a.nextInt() % (n2 + 1));
        this.var_byte_arr_j[1] = (byte)(ck.var_java_util_Random_a.nextInt() % (n2 - Math.abs(this.var_byte_arr_j[0]) + 1));
        this.var_byte_arr_j[2] = (byte)(ck.var_java_util_Random_a.nextInt() % (n2 + 1));
        this.var_byte_arr_j[3] = (byte)(ck.var_java_util_Random_a.nextInt() % (n2 - Math.abs(this.var_byte_arr_j[2]) + 1));
    }

    static {
        var_short_arr_a = new short[]{1, 1, 1, 1, 10, 20};
        var_byte_arr_h = new byte[]{1, 9, 20, 30};
        var_short_arr_b = new short[]{56, 280, 280, 220, 160, 270, 56, 270, 220, 80, 270, 270, 80, 270, 220, 80, 270, 270};
        var_byte_arr_i = new byte[]{0, 0, 10, 4, 4, 10, 0, 4, 0, 4, 10, 8, 0, 6, 6, 0, 10, 8};
        var_short_arr_d = new short[]{16, 9, 161, 9, 10, 81, 16, 9, 13, 25, 81, 8, 31, 81, 16, 31, 87, 8};
    }
}

