/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 */
import javax.microedition.lcdui.Graphics;

public final class bi
extends cb {
    private short var_short_a;
    private short[] var_short_arr_a;

    public bi(q q2) {
        super(q2, (byte)4);
        this.var_short_a = n.ao_a().var_short_a;
        this.var_short_arr_a = new short[4];
    }

    public final boolean a(int n2, int n3) {
        if (this.b(n2, n3)) {
            return true;
        }
        if (this.c(n2, n3)) {
            return true;
        }
        if (n3 == 52 || n2 == 2) {
            this.b((byte)3);
            return true;
        }
        if (n3 == 54 || n2 == 5) {
            this.b((byte)4);
            return true;
        }
        if (n3 == 53 || n2 == 8) {
            if (this.var_short_arr_a[0] != 0 || this.var_short_arr_a[1] != 0 || this.var_short_arr_a[2] != 0 || this.var_short_arr_a[3] != 0) {
                Object[] objectArray = new Object[]{ai.var_z_a.a(33)};
                this.a((byte)2, (byte)2, objectArray);
            } else {
                Object[] objectArray = new Object[]{ai.var_z_a.a(34), ai.var_z_a.a(35)};
                this.a((byte)1, (byte)1, objectArray);
            }
            return true;
        }
        if (n3 == bh.var_int_a) {
            this.var_cb_a.a((byte)-1, (byte)-1);
            return true;
        }
        return true;
    }

    public final void a(Graphics graphics, int n2, int n3) {
        ao ao2 = n.ao_a();
        cb.b(graphics, n2 += 36, n3 += 37, 147, 26, 0x3F1F3F);
        graphics.setColor(0xFFFFFF);
        r.a(graphics, ai.var_z_a.a(36), n2 + 3, n3 + 3);
        r.a(graphics, ai.var_z_a.a(37), n2 + 3, n3 + 10 + 4);
        r.c(graphics, this.var_short_a, n2 + 65, n3 + 10 + 4, 8);
        graphics.setColor(0x5F3F3F);
        graphics.fillRect(n2, n3 + 30, 151, 62);
        for (byte by2 = 0; by2 < 4; by2 = (byte)(by2 + 1)) {
            if (this.var_byte_b == by2) {
                graphics.setColor(0xFFFFFF);
                graphics.drawImage(ce.var_javax_microedition_lcdui_Image_e, n2 + 2, n3 + 35 + by2 * 15, 20);
            } else {
                graphics.setColor(14663551);
            }
            int n4 = this.var_short_arr_a[by2];
            switch (by2) {
                case 0: {
                    n4 += ao2.var_short_b + ao2.m;
                    break;
                }
                case 1: {
                    n4 += ao2.var_short_e + ao2.n;
                    break;
                }
                case 2: {
                    n4 += ao2.var_short_f + ao2.o;
                    break;
                }
                case 3: {
                    n4 += ao2.var_short_g + ao2.p;
                }
            }
            bh.int_a(graphics, n2 + 10, n3 + 35 + by2 * 15, ce.var_z_a.a(9 + by2), 1);
            graphics.drawImage(ce.var_javax_microedition_lcdui_Image_p, n2 + 45 + 25, n3 + 35 + by2 * 15, 20);
            r.c(graphics, n4, n2 + 65 + 25, n3 + 35 + by2 * 15, 8);
            graphics.drawImage(ce.var_javax_microedition_lcdui_Image_e, n2 + 67 + 25, n3 + 35 + by2 * 15, 20);
        }
    }

    public final void a(byte by2, byte by3) {
        cb cb2 = this.var_cb_b;
        super.a(by2, by3);
        if (cb2 instanceof af && by2 == 2 && by3 == 0) {
            ao ao2 = n.ao_a();
            ao2.var_short_b = (short)(ao2.var_short_b + this.var_short_arr_a[0]);
            ao2.var_short_e = (short)(ao2.var_short_e + this.var_short_arr_a[1]);
            ao2.var_short_f = (short)(ao2.var_short_f + this.var_short_arr_a[2]);
            ao2.var_short_g = (short)(ao2.var_short_g + this.var_short_arr_a[3]);
            ao2.var_short_a = this.var_short_a;
            ao2.n();
            this.var_cb_a.a((byte)-1, (byte)-1);
        }
    }

    private void b(byte by2) {
        if (by2 == 4 && this.var_short_a > 0) {
            byte by3 = this.var_byte_b;
            this.var_short_arr_a[by3] = (short)(this.var_short_arr_a[by3] + 1);
            this.var_short_a = (short)(this.var_short_a - 1);
            return;
        }
        if (by2 == 3 && this.var_short_arr_a[this.var_byte_b] > 0) {
            byte by4 = this.var_byte_b;
            this.var_short_arr_a[by4] = (short)(this.var_short_arr_a[by4] - 1);
            this.var_short_a = (short)(this.var_short_a + 1);
        }
    }
}

