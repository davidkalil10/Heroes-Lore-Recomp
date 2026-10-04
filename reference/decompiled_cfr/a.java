/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 */
import javax.microedition.lcdui.Graphics;

public final class a
extends cb {
    private byte[] h;
    private byte c;

    public a(bf bf2, byte[] byArray) {
        super(bf2, (byte)(byArray.length / 4));
        this.h = byArray;
        this.c = 0;
    }

    public final boolean a(int n2, int n3) {
        if (this.b(n2, n3)) {
            return true;
        }
        if (this.var_byte_a > 1 && this.c(n2, n3)) {
            this.c = 0;
            return true;
        }
        if (n3 == 53 || n2 == 8) {
            n.a(true, this.h[this.var_byte_b * 4], null);
            return true;
        }
        if (n3 == bh.var_int_a) {
            this.var_cb_a.a((byte)-1, (byte)-1);
            return true;
        }
        return true;
    }

    public final void a(Graphics graphics, int n2, int n3) {
        byte by2;
        graphics.setColor(0x3F1F3F);
        graphics.fillRect(0, 0, r.g, r.h);
        bf.c(graphics, n2, n3);
        bh.a(graphics, 3, r.g >> 1, n3 + 5);
        bf.b(graphics, n2, n3 + 24, 3);
        n3 += 30;
        n2 += 35;
        for (by2 = 0; by2 < 5; ++by2) {
            graphics.drawImage(ce.var_javax_microedition_lcdui_Image_arr_k[19], n2 + 13, n3 + 49 + by2 * 16, 20);
        }
        switch (this.c) {
            case 0: {
                graphics.drawImage(ce.var_javax_microedition_lcdui_Image_arr_k[14], n2 + 5, n3 + 31 + this.var_byte_b * 16, 20);
                break;
            }
            case 1: {
                graphics.drawImage(ce.var_javax_microedition_lcdui_Image_arr_k[16], n2 + 5, n3 + 31 + this.var_byte_b * 16, 20);
                break;
            }
            default: {
                graphics.drawImage(ce.var_javax_microedition_lcdui_Image_arr_k[18], n2 + 5, n3 + 31 + this.var_byte_b * 16, 20);
            }
        }
        if (this.c < 2) {
            this.var_boolean_a = true;
            this.c = (byte)(this.c + 1);
        }
        for (by2 = 0; by2 < this.var_byte_a; by2 = (byte)(by2 + 1)) {
            if (this.var_byte_b == by2) {
                graphics.setColor(0xFFFFFF);
            } else {
                graphics.setColor(0x9F7F7F);
            }
            bh.int_a(graphics, n2 + 21, n3 + 36 + by2 * 16, ce.var_z_a.a(this.h[by2 * 4] - 6), 1);
        }
        graphics.drawImage(ce.var_javax_microedition_lcdui_Image_h, n2 + 15, n3 + 104, 20);
        r.c(graphics, this.h[this.var_byte_b * 4 + 1], n2 + 30, n3 + 104, 4);
        graphics.setColor(0x7F5F5F);
        bh.int_a(graphics, n2 + 15, n3 + 117, (bh.var_java_lang_String_b + this.h[this.var_byte_b * 4 + 2] + "%").toCharArray(), 1);
        if (r.g > 128) {
            graphics.drawImage(ce.var_javax_microedition_lcdui_Image_arr_l[this.h[this.var_byte_b * 4] - 6], n2 + 61 + 22, n3 + 74 + 15, 20);
        } else {
            graphics.drawImage(ce.var_javax_microedition_lcdui_Image_arr_l[this.h[this.var_byte_b * 4] - 6], n2 + 61, n3 + 74, 20);
        }
        bh.a(graphics, bh.var_char_arr_d, bh.var_char_arr_e);
    }
}

