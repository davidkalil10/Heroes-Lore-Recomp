/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 */
import javax.microedition.lcdui.Graphics;

public final class bo
extends cb {
    private char[] var_char_arr_a;
    private Object[] var_java_lang_Object_arr_a;
    private char[] b;
    private int var_int_a;
    private byte c;

    public bo(cb cb2, char[] cArray, Object[] objectArray, char[] cArray2, int n2, byte by2) {
        super(cb2, (byte)0);
        this.var_char_arr_a = cArray;
        this.c = by2;
        this.var_java_lang_Object_arr_a = objectArray;
        this.b = cArray2;
        this.var_int_a = n2;
    }

    public final boolean a(int n2, int n3) {
        if (this.b(n2, n3)) {
            return true;
        }
        if (n3 == bh.var_int_a) {
            this.var_cb_a.void_a();
            return true;
        }
        if (n3 == 53 || n2 == 8) {
            this.var_cb_a.a(this.c, (byte)0);
            return true;
        }
        return true;
    }

    public final void a(Graphics graphics, int n2, int n3) {
        boolean bl2 = false;
        n2 = r.i - 90;
        n3 = r.j - 60;
        ao ao2 = n.ao_a();
        cb.c(graphics, n2, n3, 181, 120);
        cb.b(graphics, n2 + 3, n3 + 3, 175, 17, 0x9F7F7F);
        graphics.setColor(0xFFFFFF);
        bh.int_a(graphics, n2 + 6, n3 + 4, this.var_char_arr_a, 1);
        cb.b(graphics, n2 + 3, n3 + 25, 175, 60, 0x9F7F7F);
        graphics.setColor(0xFFFFFF);
        for (int i2 = 0; i2 < this.var_java_lang_Object_arr_a.length; ++i2) {
            if (this.var_java_lang_Object_arr_a[i2] == null) continue;
            bh.int_a(graphics, n2 + 6, n3 + 27 + i2 * 18, (char[])this.var_java_lang_Object_arr_a[i2], 1);
        }
        cb.a(graphics, n2 + 181 - 5, n3 + 90, ao2.var_g_a.var_int_a);
        cb.b(graphics, n2 + 3, n3 + 98, 175, 15, 0x9F7F7F);
        graphics.setColor(0xFFFFFF);
        bh.int_a(graphics, n2 + 6, n3 + 99, this.b, 1);
        cb.a(graphics, n2 + 181 - 5, n3 + 105, this.var_int_a);
    }
}

