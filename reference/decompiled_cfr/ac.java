/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 */
import javax.microedition.lcdui.Graphics;

public final class ac
extends o {
    public byte f;
    public byte g;
    public boolean d;

    public ac(short s2, short s3, byte by2, byte by3) {
        super(s2, s3, (byte)8, (byte)8);
        this.f = by2;
        this.d = true;
        this.g = by3;
    }

    public final void a(short s2, short s3) {
        this.f();
        super.a(s2, s3);
        this.void_b();
        if (this.h == 1) {
            this.g();
        }
    }

    public final void void_a(int n2) {
        this.f();
        this.var_short_c = (short)(this.var_short_c + n2 * u.var_byte_arr_a[this.i]);
        this.var_short_d = (short)(this.var_short_d + n2 * u.b[this.i]);
        this.void_b();
        if (this.h == 1) {
            this.g();
        }
    }

    public final boolean boolean_a() {
        if (this.var_boolean_a || this.var_boolean_b) {
            return false;
        }
        return false;
    }

    public final void a(Graphics graphics, int n2, int n3) {
        if (!this.d) {
            return;
        }
        int n4 = n2 + this.var_short_c + this.var_byte_c;
        int n5 = n3 + this.var_short_d + this.var_byte_d;
        if (n4 + 16 < 0 || n5 < 0 || n4 - 16 > as.var_int_a || n5 > as.b + 32) {
            return;
        }
        graphics.drawImage(ce.var_javax_microedition_lcdui_Image_v, n4, n5 - 3, 17);
        if (this.f >= 18) {
            graphics.drawImage(ce.var_javax_microedition_lcdui_Image_arr_g[this.f - 18], n4, n5, 33);
        } else {
            int n6 = 0;
            n6 = this.h == 2 ? this.g * 12 + 4 + (this.j - 1) : this.g * 12 + 0 + (this.j - 1);
            as.b(graphics, (byte[])ce.var_java_lang_Object_arr_j[n6], this.k, n4, n5);
            this.k = (byte)(this.k + 1);
            if (this.h == 1 && ce.var_byte_arr_i[this.g] <= this.k) {
                this.k = 0;
            } else if (this.h == 2 && ce.var_byte_arr_j[this.g] <= this.k) {
                this.k = 0;
            }
        }
        this.b(graphics, n4, n5);
    }
}

