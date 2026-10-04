/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 */
import javax.microedition.lcdui.Graphics;

public final class bn
extends cb {
    private boolean c = true;
    private p a;

    public bn(bm bm2, p p2) {
        super(bm2, (byte)3);
        this.a = p2;
    }

    public final boolean a(int n2, int n3) {
        if (this.b(n2, n3)) {
            return true;
        }
        if (this.c(n2, n3)) {
            return true;
        }
        if (n3 == 52 || n3 == 54 || n2 == 2 || n2 == 5) {
            this.c = !this.c;
            return true;
        }
        if (n3 == 53 || n2 == 8) {
            if (this.var_byte_b == 3) {
                Object[] objectArray = new Object[]{ai.var_z_a.a(59), ai.var_z_a.a(60)};
                this.a((byte)1, (byte)0, objectArray);
            } else if (this.a.g == this.var_byte_b || this.a.var_byte_h == this.var_byte_b) {
                Object[] objectArray = new Object[]{ai.var_z_a.a(61)};
                this.a((byte)1, (byte)0, objectArray);
            } else if (!this.a.a(this.c, this.var_byte_b, false)) {
                Object[] objectArray = new Object[]{ai.var_z_a.a(62), ai.var_z_a.a(63)};
                this.a((byte)1, (byte)0, objectArray);
            }
            return true;
        }
        if (n3 == bh.var_int_a) {
            this.var_cb_a.void_a();
            return true;
        }
        return true;
    }

    public final void a(Graphics graphics, int n2, int n3) {
        cb.a(graphics, n2 += 2, n3 += 10, 132, 22);
        cb.b(graphics, n2, n3, 132, 22);
        cb.a(graphics, n2, n3 += 19, 195, 214, 0x1F1F3F, 10452799, 0x3F3F3F);
        cb.a(graphics, n2, n3, 195, 214, 0x5F3F3F);
        int n4 = n2 + (this.c ? 0 : 66);
        graphics.setColor(0x5F3F3F);
        graphics.fillRect(n4 + 2, (n3 -= 19) + 2, 62, 19);
        graphics.setColor(0x3F3F3F);
        graphics.fillRect(n4 + 44 + 20, n3 + 1, 1, 19);
        graphics.setColor(0x1F1F3F);
        graphics.fillRect(n4 + 45 + 20, n3 + 1, 1, 17);
        graphics.fillRect(n4, n3 + 1, 1, 18);
        graphics.fillRect(n4 + 1, n3, 64, 1);
        graphics.setColor(10452799);
        graphics.fillRect(n4 + 1, n3 + 2, 1, 18);
        graphics.fillRect(n4 + 1, n3 + 1, 63, 1);
        int n5 = n3 + 5 + (this.c ? 0 : 2);
        int n6 = r.a(graphics, ai.var_z_a.a(38), n2 + 6, n5);
        r.c(graphics, 1, n6 + 3, n5, 4);
        if (this.a.g != -1) {
            graphics.drawImage(ce.var_javax_microedition_lcdui_Image_arr_c[this.a.f * 4 + this.a.g], n6 + 13, n5 - 2, 20);
        }
        int n7 = n3 + 5 + (this.c ? 2 : 0);
        n6 = r.a(graphics, ai.var_z_a.a(38), n2 + 51 + 20, n7);
        r.c(graphics, 3, n6 + 3, n7, 4);
        if (this.a.var_byte_h != -1) {
            graphics.drawImage(ce.var_javax_microedition_lcdui_Image_arr_c[this.a.f * 4 + this.a.var_byte_h], n6 + 13, n7 - 2, 20);
        }
        graphics.drawImage(ce.var_javax_microedition_lcdui_Image_p, n2, n3 + 7, 20);
        graphics.drawImage(ce.var_javax_microedition_lcdui_Image_e, n2 + 90 + 40, n3 + 7, 20);
        graphics.setColor(0x3F1F3F);
        cb.b(graphics, n2 + 28, n3 + 26, 160, 201, 0x3F1F3F);
        cb.b(graphics, n2 + 3, n3 + 31 + this.var_byte_b * 20, 26, 19, 0x3F1F3F);
        for (n5 = 0; n5 < 3; ++n5) {
            graphics.drawImage(ce.var_javax_microedition_lcdui_Image_arr_c[this.a.f * 4 + n5], n2 + 5, n3 + 48 + n5 * 20, 36);
        }
        graphics.setColor(0xFFFFFF);
        bh.int_a(graphics, n2 + 34, n3 + 29, ce.var_z_c.a(this.a.f * 8 + this.var_byte_b * 2), 1);
        n5 = r.a(graphics, bh.s, n2 + 34, n3 + 44);
        r.c(graphics, p.var_byte_arr_h[this.var_byte_b], n5 + 3, n3 + 44, 4);
        graphics.setColor(14663551);
        char[] cArray = ce.var_z_c.a(this.a.f * 8 + this.var_byte_b * 2 + 1);
        bh.a(graphics, n2 + 34, n3 + 53, 46, 1, cArray);
    }
}

