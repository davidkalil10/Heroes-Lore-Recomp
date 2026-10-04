/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 */
import java.io.IOException;
import java.util.Vector;
import javax.microedition.lcdui.Graphics;

public final class bp
extends cb {
    private Vector[] var_java_util_Vector_arr_a = ad.java_util_Vector_arr_a();
    public static int var_int_a;
    public static int b;
    public static z var_z_a;
    private static bp var_bp_a;

    public static final bp bp_a() {
        if (var_bp_a == null) {
            var_bp_a = new bp();
            var_int_a = r.i - 100;
            b = r.j - 122;
        }
        return var_bp_a;
    }

    private bp() {
        super(null, (byte)6);
        this.var_cb_b = new v(this, this.var_java_util_Vector_arr_a[this.var_byte_b], this.var_byte_b);
    }

    public final void void_d() {
        try {
            var_z_a = new z("/sgui/shop");
            return;
        }
        catch (IOException iOException) {
            IOException iOException2 = iOException;
            iOException.printStackTrace();
            return;
        }
    }

    private void e() {
        var_bp_a = null;
        var_z_a = null;
        this.var_java_util_Vector_arr_a = null;
        this.var_cb_b = null;
        n.void_a(2);
        bs.var_as_a.b();
        System.gc();
    }

    public final boolean a(int n2, int n3) {
        if (this.b(n2, n3)) {
            return true;
        }
        if (this.d(n2, n3)) {
            return true;
        }
        if (n3 == bh.var_int_a) {
            this.e();
            return true;
        }
        return false;
    }

    public final void a(byte by2) {
        super.a(by2);
        this.var_cb_b = new v(this, this.var_java_util_Vector_arr_a[this.var_byte_b], this.var_byte_b);
    }

    public final void a(Graphics graphics) {
        this.b(graphics, var_int_a, b);
    }

    public final void a(Graphics graphics, int n2, int n3) {
        bh.a(graphics);
        bh.a(graphics, bh.var_char_arr_d, bh.var_char_arr_e);
        graphics.setColor(0x3F1F3F);
        graphics.fillRect(n2, n3, 201, 244);
        cb.c(graphics, n2 + 2, n3 + 15, 197, 229);
        graphics.setColor(0xFFDFBF);
        graphics.fillRect(n2 + 11 + this.var_byte_b * 16 + 1, n3, 14, 1);
        graphics.fillRect(n2 + 11 + this.var_byte_b * 16, n3 + 1, 1, 16);
        graphics.setColor(12558207);
        graphics.fillRect(n2 + 11 + this.var_byte_b * 16 + 15, n3 + 1, 1, 15);
        graphics.setColor(14663551);
        graphics.fillRect(n2 + 11 + this.var_byte_b * 16 + 1, n3 + 1, 14, 16);
        for (int i2 = 0; i2 < 6; ++i2) {
            graphics.drawImage(ce.var_javax_microedition_lcdui_Image_arr_o[i2], n2 + 13 + i2 * 16, n3 + 1, 20);
        }
        r.a(graphics, var_z_a.a(this.var_byte_b + 1), n2 + 3, n3 + 15);
        graphics.drawImage(ce.var_javax_microedition_lcdui_Image_p, n2 + 4, n3 + 4, 20);
        graphics.drawImage(ce.var_javax_microedition_lcdui_Image_e, n2 + 109, n3 + 4, 20);
    }
}

