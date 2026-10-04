/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 */
import javax.microedition.lcdui.Graphics;

public final class cf
extends f {
    public byte var_byte_a;
    private static final short[] var_short_arr_a;

    public cf(byte by2) {
        super(var_short_arr_a[by2]);
        this.var_byte_a = by2;
    }

    public final void void_a() {
        this.b = (short)(this.b + 1);
        if (this.b >= this.var_short_a) {
            this.var_boolean_a = true;
        }
    }

    public final void b() {
        this.var_boolean_a = true;
    }

    public final void a(Graphics graphics, int n2, int n3) {
        graphics.drawImage(ce.E, n2, n3 - 30, 17);
        graphics.drawImage(ce.var_javax_microedition_lcdui_Image_arr_u[this.var_byte_a], n2, n3 - 29 + this.b % 2, 17);
    }

    public final void c() {
        this.b = 0;
    }

    public final short short_a() {
        return this.b;
    }

    static {
        var_short_arr_a = new short[]{40, 40, 40, 40, 40, 140, 160, 80};
    }
}

