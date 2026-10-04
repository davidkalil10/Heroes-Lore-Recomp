/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 */
import javax.microedition.lcdui.Graphics;
import rpg.GameMIDlet;

public final class bf
extends cb {
    public static int var_int_a;
    public static int b;
    private boolean e;
    private byte[] h;
    private byte var_byte_c;
    private byte var_byte_d;
    private long var_long_a;
    private static int var_int_c;
    private static int var_int_d;
    private static bf var_bf_a;
    public static boolean var_boolean_c;
    public static boolean var_boolean_d;

    public static final bf bf_a() {
        return var_bf_a;
    }

    private bf(boolean bl2, byte[] byArray) {
        super(null, (byte)6);
        if (w.var_boolean_a) {
            this.var_byte_a = (byte)(this.var_byte_a + 1);
        }
        this.e = bl2;
        this.h = byArray;
        this.var_byte_c = 0;
        if (var_boolean_d || var_boolean_c) {
            ce.w();
            this.var_long_a = System.currentTimeMillis() + 5000L;
            if (var_boolean_d) {
                this.var_byte_d = (byte)2;
                var_boolean_d = false;
                return;
            }
            if (var_boolean_c) {
                this.var_byte_d = (byte)3;
                var_boolean_c = false;
            }
        }
    }

    public static final void a(boolean bl2, byte[] byArray) {
        var_int_a = r.i - 100;
        b = r.j - 122;
        var_bf_a = new bf(bl2, byArray);
        if (w.var_boolean_a) {
            var_int_c = 6;
            var_int_d = 5;
        }
    }

    public static final void void_d() {
        var_bf_a = null;
    }

    public final boolean a(int n2, int n3) {
        Object[] objectArray;
        if (this.var_long_a > 0L) {
            if (!w.c && w.b) {
                if (n3 == 53) {
                    bh.void_a(w.var_java_lang_String_a);
                } else if (n3 == bh.var_int_a) {
                    GameMIDlet.var_rpg_GameMIDlet_a.a();
                }
            }
            return true;
        }
        if (this.b(n2, n3)) {
            return true;
        }
        if (this.a(n2, n3, false)) {
            if (!this.e && this.var_byte_b == 1) {
                this.var_byte_b = n2 == 6 || n3 == 56 ? (byte)(this.var_byte_b + 1) : (byte)(this.var_byte_b - 1);
            }
            this.var_byte_c = 0;
            return true;
        }
        if (n3 == bh.var_int_a) {
            objectArray = new Object[]{bh.var_char_arr_a};
            this.a((byte)2, (byte)2, objectArray);
            this.var_byte_d = (byte)2;
        }
        if (n2 == 8 || n3 == 53) {
            switch (this.var_byte_b) {
                case 0: {
                    if (this.e) {
                        this.var_byte_d = 0;
                        objectArray = new Object[]{bh.java_lang_String_a(3929).toCharArray()};
                        this.a((byte)12, (byte)2, objectArray, bh.var_char_arr_d, bh.var_char_arr_e);
                        break;
                    }
                    this.var_cb_b = new c(this);
                    break;
                }
                case 1: {
                    this.var_cb_b = new a(this, this.h);
                    break;
                }
                case 2: {
                    this.var_cb_b = new be((cb)this, false);
                    break;
                }
                case 3: {
                    this.var_cb_b = new bt((cb)this, false);
                    break;
                }
                case 4: {
                    this.var_cb_b = new bl((cb)this, false);
                    break;
                }
                default: {
                    if (this.var_byte_b == var_int_c) {
                        objectArray = new Object[]{bh.var_char_arr_a};
                        this.var_byte_d = (byte)2;
                        this.a((byte)2, (byte)2, objectArray);
                        break;
                    }
                    if (this.var_byte_b != var_int_d) break;
                    objectArray = new Object[]{bh.java_lang_String_a(3918).toCharArray()};
                    this.var_byte_d = (byte)3;
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
                switch (this.var_byte_d) {
                    case 1: {
                        break;
                    }
                    case 2: {
                        if (w.c) {
                            Object[] objectArray = new Object[]{bh.java_lang_String_a(3919).toCharArray()};
                            this.var_byte_d = (byte)4;
                            this.a((byte)12, (byte)2, objectArray, bh.j, bh.var_char_arr_c);
                            break;
                        }
                        ce.w();
                        this.var_long_a = System.currentTimeMillis() + 5000L;
                        break;
                    }
                    case 3: 
                    case 4: {
                        bh.void_a(w.var_java_lang_String_a);
                        break;
                    }
                    case 0: {
                        this.var_cb_b = new c(this);
                    }
                }
                return;
            }
            switch (this.var_byte_d) {
                case 4: {
                    ce.w();
                    this.var_long_a = System.currentTimeMillis() + 5000L;
                }
            }
        }
    }

    public final void a(Graphics graphics) {
        if (this.var_long_a > 0L) {
            graphics.setColor(0xFFFFFF);
            graphics.fillRect(0, 0, r.g, r.h);
            graphics.drawImage(ce.var_javax_microedition_lcdui_Image_a, r.i, as.var_int_d, 3);
            graphics.setColor(0);
            bh.void_a(graphics, r.g >> 1, r.h - 23, bh.var_char_arr_b, 1);
            bh.void_a(graphics, r.g >> 1, 10, cj.var_cj_a.a(3941).toCharArray(), 1);
            if (!w.c && w.b) {
                bh.a(graphics, w.java_lang_String_a(false).toCharArray(), bh.var_char_arr_c);
            }
            if (System.currentTimeMillis() > this.var_long_a) {
                GameMIDlet.var_rpg_GameMIDlet_a.a();
            }
            return;
        }
        this.b(graphics, var_int_a, b);
        if (this.var_byte_c < 2 && this.var_cb_b == null) {
            this.var_boolean_a = true;
            this.var_byte_c = (byte)(this.var_byte_c + 1);
        }
    }

    public final void a(Graphics graphics, int n2, int n3) {
        graphics.setColor(0x3F1F3F);
        graphics.fillRect(0, 0, r.g, r.h);
        bf.b(graphics, n2, (n3 += 13) - 12, 4);
        n3 += 35;
        int n4 = 18;
        if (this.var_byte_c == 0) {
            n4 = 14;
        } else if (this.var_byte_c == 1) {
            n4 = 16;
        }
        if (r.h <= 160) {
            n3 -= 10;
        }
        int n5 = n2 + (201 - ce.var_javax_microedition_lcdui_Image_arr_k[n4].getWidth()) >> 1;
        graphics.drawImage(ce.var_javax_microedition_lcdui_Image_arr_k[n4], n5 += 15, n3 + 12 + this.var_byte_b * 16, 20);
        for (int i2 = 0; i2 < this.var_byte_a; ++i2) {
            int n6 = n3 + 14 + i2 * 16;
            n4 = (byte)(i2 * 2);
            if (this.var_byte_b != i2 || this.var_byte_c < 2) {
                n4 = (byte)(n4 + 1);
            }
            bh.a(graphics, n4, n2 + 201 >> 1, n6);
        }
        bh.a(graphics, bh.var_char_arr_d, bh.var_char_arr_c);
    }

    public static final void c(Graphics graphics, int n2, int n3) {
        int n4;
        boolean bl2 = false;
        int n5 = n2 -= 7;
        graphics.drawImage(ce.var_javax_microedition_lcdui_Image_arr_k[0], n5, n3, 20);
        graphics.drawImage(ce.var_javax_microedition_lcdui_Image_arr_k[1], n5 += 12, n3, 20);
        for (n4 = 0; n4 < 5; ++n4) {
            graphics.drawImage(ce.var_javax_microedition_lcdui_Image_arr_k[1], n5 += 32, n3, 20);
        }
        graphics.drawImage(ce.var_javax_microedition_lcdui_Image_arr_k[2], n5 += 32, n3, 20);
        n5 = n2;
        graphics.drawImage(ce.var_javax_microedition_lcdui_Image_arr_k[11], n5, n3 + 12, 20);
        graphics.drawImage(ce.var_javax_microedition_lcdui_Image_arr_k[12], n5 += 12, n3 + 12, 20);
        for (n4 = 0; n4 < 5; ++n4) {
            graphics.drawImage(ce.var_javax_microedition_lcdui_Image_arr_k[12], n5 += 32, n3 + 12, 20);
        }
        graphics.drawImage(ce.var_javax_microedition_lcdui_Image_arr_k[13], n5 += 32, n3 + 12, 20);
    }

    public static final void b(Graphics graphics, int n2, int n3, int n4) {
        int n5;
        int n6;
        boolean bl2 = false;
        n4 += 4;
        int n7 = n2 -= 7;
        graphics.drawImage(ce.var_javax_microedition_lcdui_Image_arr_k[3], n7, n3, 20);
        graphics.drawImage(ce.var_javax_microedition_lcdui_Image_arr_k[4], n7 += 12, n3, 20);
        for (n6 = 0; n6 < 5; ++n6) {
            graphics.drawImage(ce.var_javax_microedition_lcdui_Image_arr_k[4], n7 += 32, n3, 20);
        }
        graphics.drawImage(ce.var_javax_microedition_lcdui_Image_arr_k[5], n7 += 32, n3, 20);
        n7 = n2;
        graphics.drawImage(ce.var_javax_microedition_lcdui_Image_arr_k[6], n7, n3 + 12, 20);
        graphics.drawImage(ce.var_javax_microedition_lcdui_Image_arr_k[7], n7 += 12, n3 + 12, 20);
        for (n6 = 0; n6 < 5; ++n6) {
            graphics.drawImage(ce.var_javax_microedition_lcdui_Image_arr_k[7], n7 += 32, n3 + 12, 20);
        }
        graphics.drawImage(ce.var_javax_microedition_lcdui_Image_arr_k[8], n7 += 32, n3 + 12, 20);
        n7 = n2;
        for (n5 = 0; n5 < n4; ++n5) {
            graphics.drawImage(ce.var_javax_microedition_lcdui_Image_arr_k[9], n7, n3 + 36 + 24 * n5, 20);
            graphics.drawImage(ce.var_javax_microedition_lcdui_Image_arr_k[10], n7 + 12 + 192, n3 + 36 + 24 * n5, 20);
        }
        graphics.setColor(16763769);
        graphics.fillRect(n7 + 12, n3 + 36, 192, 24 * n4);
        n7 = n2;
        graphics.drawImage(ce.var_javax_microedition_lcdui_Image_arr_k[11], n7, n3 + 36 + 24 * n4, 20);
        graphics.drawImage(ce.var_javax_microedition_lcdui_Image_arr_k[12], n7 += 12, n3 + 36 + 24 * n4, 20);
        for (n5 = 0; n5 < 5; ++n5) {
            graphics.drawImage(ce.var_javax_microedition_lcdui_Image_arr_k[12], n7 += 32, n3 + 36 + 24 * n4, 20);
        }
        graphics.drawImage(ce.var_javax_microedition_lcdui_Image_arr_k[13], n7 += 32, n3 + 36 + 24 * n4, 20);
    }

    static {
        var_int_c = 5;
        var_int_d = 5;
    }
}

