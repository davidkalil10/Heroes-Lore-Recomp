/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 */
import javax.microedition.lcdui.Graphics;

public final class bk
extends cb {
    private boolean var_boolean_c = false;
    private boolean var_boolean_d = false;
    private byte var_byte_c;
    private boolean[] b = new boolean[3];
    private boolean e = true;
    private byte var_byte_d = 0;

    public bk(by by2, byte by3) {
        super(by2, (byte)3);
        this.var_byte_c = by3;
        this.a(new Object[]{ce.var_z_g.a(16), ce.var_z_g.a(13)});
    }

    public final boolean a(int n2, int n3) {
        if (this.b(n2, n3)) {
            return true;
        }
        if (!this.var_boolean_c) {
            if (this.d(n2, n3)) {
                this.var_byte_d = 0;
                return true;
            }
            if (n3 == 53 || n2 == 8) {
                this.b[this.var_byte_b] = !this.b[this.var_byte_b];
                int n4 = 0;
                for (int i2 = 0; i2 < 3; ++i2) {
                    if (!this.b[i2]) continue;
                    n4 = (byte)(n4 + 1);
                }
                if (n4 == 2) {
                    this.var_boolean_c = true;
                    this.var_boolean_d = false;
                }
            }
            if (n3 == bh.var_int_a) {
                this.var_cb_a.a((byte)-1, (byte)-1);
                return true;
            }
        } else {
            switch (n2) {
                case 2: 
                case 5: {
                    this.var_boolean_d = !this.var_boolean_d;
                    break;
                }
                case 8: {
                    if (this.var_boolean_d) {
                        this.void_d();
                        break;
                    }
                    this.b = new boolean[3];
                    this.var_boolean_c = false;
                    break;
                }
                default: {
                    if (n3 == 52 || n3 == 54) {
                        this.var_boolean_d = !this.var_boolean_d;
                        break;
                    }
                    if (n3 == bh.var_int_a) {
                        this.b = new boolean[3];
                        this.var_boolean_c = false;
                        break;
                    }
                    if (n3 != 53) break;
                    if (this.var_boolean_d) {
                        this.void_d();
                        break;
                    }
                    this.b = new boolean[3];
                    this.var_boolean_c = false;
                }
            }
        }
        return true;
    }

    public final void a(Graphics graphics, int n2, int n3) {
        graphics.setColor(0x3F1F3F);
        graphics.fillRect(0, 0, r.g, r.h);
        bf.c(graphics, n2, n3);
        bh.a(graphics, 1, n2 + 201 >> 1, n3 + 5);
        bf.b(graphics, n2, n3 + 24, 3);
        graphics.drawImage(ce.var_javax_microedition_lcdui_Image_arr_k[19], (n2 += 40) + 11, (n3 += 35) + 82, 20);
        for (byte by2 = 0; by2 < 3; by2 = (byte)(by2 + 1)) {
            if (this.b[by2]) {
                graphics.drawImage(ce.var_javax_microedition_lcdui_Image_arr_arr_b[by2][1], n2 + 22 + by2 * 34, n3 + 66 - 5, 3);
                continue;
            }
            graphics.drawImage(ce.var_javax_microedition_lcdui_Image_arr_arr_b[by2][0], n2 + 22 + by2 * 34, n3 + 59 - 5 + (this.var_byte_b == by2 ? this.var_byte_d : (byte)0), 3);
        }
        if (r.h < 160) {
            n3 -= 8;
        }
        if (!this.var_boolean_c) {
            graphics.drawImage(ce.var_javax_microedition_lcdui_Image_arr_k[20], n2 + 19 + this.var_byte_b * 34, n3 + 73, 20);
        }
        graphics.setColor(0);
        if (!this.var_boolean_c) {
            bh.int_a(graphics, n2 + 11, n3 + 94, ce.var_z_b.a(this.var_byte_b), 1);
            bh.a(graphics, n2 + 11, n3 + 109, 100, 1, ce.var_z_b.a(12 + this.var_byte_b));
        } else {
            bh.int_a(graphics, n2 + 11, n3 + 104, ce.var_z_g.a(17), 1);
            if (r.g <= 128) {
                n2 -= 20;
            }
            graphics.drawImage(ce.var_javax_microedition_lcdui_Image_arr_k[17], n2 + 60 + (this.var_boolean_d ? 0 : 28), n3 + 118, 20);
            if (this.var_boolean_d) {
                graphics.setColor(0xFFFFFF);
            } else {
                graphics.setColor(0);
            }
            bh.int_a(graphics, n2 + 64, n3 + 121, ce.var_z_g.a(14), 1);
            if (this.var_boolean_d) {
                graphics.setColor(0);
            } else {
                graphics.setColor(0xFFFFFF);
            }
            bh.int_a(graphics, n2 + 92, n3 + 121, ce.var_z_g.a(15), 1);
        }
        if (this.var_byte_d == 0) {
            this.var_byte_d = (byte)(this.var_byte_d + 1);
            this.e = true;
        } else if (this.var_byte_d == 3) {
            this.var_byte_d = (byte)(this.var_byte_d - 1);
            this.e = false;
        } else {
            this.var_byte_d = this.e ? (byte)(this.var_byte_d + 1) : (byte)(this.var_byte_d - 1);
        }
        if (this.var_cb_b == null) {
            this.var_boolean_a = true;
        }
        bh.a(graphics, bh.var_char_arr_d, bh.var_char_arr_e);
    }

    private void void_d() {
        n.a(false, this.var_byte_c, this.b);
    }
}

