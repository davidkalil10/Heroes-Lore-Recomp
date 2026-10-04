/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 */
import javax.microedition.lcdui.Graphics;

public final class be
extends cb {
    private boolean c;
    private bs a;

    public be(cb cb2, boolean bl2) {
        super(cb2, (byte)4);
        this.c = bl2;
        this.a = bs.var_bs_a;
    }

    public final boolean a(int n2, int n3) {
        if (this.b(n2, n3)) {
            return true;
        }
        if (this.a(n2, n3, false)) {
            return true;
        }
        if (n3 == 52 || n2 == 2 || n3 == 54 || n2 == 5) {
            switch (this.var_byte_b) {
                case 0: {
                    if (n3 == 52 || n2 == 2 || n3 == 54 || n2 == 5) {
                        this.a.var_int_a = this.a.var_int_a == 0 ? bw.var_int_a : 0;
                    }
                    bw.a(this.a.var_int_a);
                    if (this.a.var_int_a != 0) break;
                    bw.d();
                    break;
                }
                case 1: {
                    if (n3 == 52 || n2 == 2) {
                        this.a.var_byte_a = (byte)(this.a.var_byte_a - 1);
                        if (this.a.var_byte_a < 0) {
                            this.a.var_byte_a = (byte)2;
                        }
                    }
                    if (n3 == 54 || n2 == 5) {
                        this.a.var_byte_a = (byte)(this.a.var_byte_a + 1);
                        if (this.a.var_byte_a > 2) {
                            this.a.var_byte_a = 0;
                        }
                    }
                    this.a.a(this.a.var_byte_a);
                    break;
                }
                case 2: {
                    this.a.var_boolean_c = !this.a.var_boolean_c;
                    break;
                }
                case 3: {
                    boolean bl2 = this.a.var_boolean_d = !this.a.var_boolean_d;
                }
            }
        }
        if (n3 == bh.var_int_a) {
            if (this.a.var_boolean_d) {
                n.var_int_c = n.var_int_a;
                n.var_int_d = n.var_int_b;
            }
            try {
                bs.var_bs_a.i();
            }
            catch (Exception exception) {
                Exception exception2 = exception;
                exception.printStackTrace();
            }
            this.var_cb_a.void_a();
            return true;
        }
        return true;
    }

    public final void a(Graphics graphics, int n2, int n3) {
        int n4 = 0;
        int n5 = 0;
        if (this.c) {
            cb.a(graphics, n2 += 6, n3 += 25, 189, 213);
            cb.b(graphics, n2, n3, 189, 213);
            n4 = 10452799;
            n5 = 0xFFFFFF;
            n2 += 5;
            n3 += 15;
            bh.a(graphics, bh.m, bh.var_char_arr_e);
            n2 += 40;
            n3 += 35;
        } else {
            graphics.setColor(0x3F1F3F);
            graphics.fillRect(0, 0, r.g, r.h);
            bf.c(graphics, n2, n3);
            bh.a(graphics, 5, n2 + 201 >> 1, n3 + 5);
            bf.b(graphics, n2, n3 + 24, 3);
            n2 += 40;
            n3 += 35;
            graphics.drawImage(ce.var_javax_microedition_lcdui_Image_arr_k[19], (n2 += 12) + 1, (n3 += 46) + 16, 20);
            graphics.drawImage(ce.var_javax_microedition_lcdui_Image_arr_k[19], n2 + 1, n3 + 36, 20);
            graphics.drawImage(ce.var_javax_microedition_lcdui_Image_arr_k[19], n2 + 1, n3 + 56, 20);
            bh.a(graphics, null, bh.var_char_arr_e);
        }
        int n6 = n3;
        byte by2 = this.var_byte_b;
        graphics.setColor(by2 == 0 ? 0xFFFFFF : n4);
        bh.int_a(graphics, n2, n6, ce.var_z_g.a(18), 1);
        graphics.setColor(n5);
        if (this.a.var_int_a == 0) {
            bh.void_a(graphics, n2 + 70, n6, cj.var_cj_a.a(3945).toCharArray(), 0);
        } else {
            bh.void_a(graphics, n2 + 70, n6, cj.var_cj_a.a(3944).toCharArray(), 0);
        }
        graphics.setColor(by2 == 1 ? 0xFFFFFF : n4);
        bh.int_a(graphics, n2, n6 += 20, ce.var_z_g.a(19), 1);
        graphics.setColor(n5);
        char[] cArray = ce.var_z_g.a(60 + this.a.var_byte_a);
        bh.void_a(graphics, n2 + 70, n6, cArray, 0);
        graphics.setColor(by2 == 2 ? 0xFFFFFF : n4);
        bh.int_a(graphics, n2, n6 += 20, ce.var_z_g.a(20), 1);
        graphics.setColor(n5);
        bh.void_a(graphics, n2 + 70, n6, (this.a.var_boolean_c ? cj.var_cj_a.a(3942) : cj.var_cj_a.a(3943)).toCharArray(), 0);
        graphics.setColor(by2 == 3 ? 0xFFFFFF : n4);
        bh.int_a(graphics, n2, n6 += 20, ce.var_z_g.a(21), 1);
        graphics.setColor(n5);
        bh.void_a(graphics, n2 + 70, n6, (this.a.var_boolean_d ? cj.var_cj_a.a(3944) : cj.var_cj_a.a(3945)).toCharArray(), 0);
        for (byte by3 = 0; by3 < this.var_byte_a; by3 = (byte)(by3 + 1)) {
            graphics.drawImage(ce.var_javax_microedition_lcdui_Image_p, n2 + 42, n3 + by3 * 20, 20);
            graphics.drawImage(ce.var_javax_microedition_lcdui_Image_e, n2 + 92, n3 + by3 * 20, 20);
        }
    }
}

