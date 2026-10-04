/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 */
import javax.microedition.lcdui.Graphics;
import rpg.GameMIDlet;

public final class bl
extends cb {
    private char[] var_char_arr_a;
    private short[] var_short_arr_a;

    public bl(cb cb2, boolean bl2) {
        super(cb2, (byte)1);
        GameMIDlet gameMIDlet = GameMIDlet.var_rpg_GameMIDlet_a;
        String string = bh.java_lang_String_a(3927);
        String string2 = bh.java_lang_String_a(3928);
        String string3 = gameMIDlet.getAppProperty("MIDlet-Name").toUpperCase();
        String string4 = bh.java_lang_String_a(bh.r);
        String string5 = gameMIDlet.getAppProperty("MIDlet-Vendor");
        String string6 = bh.java_lang_String_a(bh.var_char_arr_b);
        String string7 = bh.java_lang_String_a(3905);
        String string8 = string3 + "\n\n" + string7 + "\n\n" + string + '\n' + string5 + '\n' + string6 + "\n\n" + string2 + "\nv." + string4;
        this.var_char_arr_a = string8.toCharArray();
        short[] sArray = new short[20];
        int n2 = 0;
        for (int i2 = 0; i2 < this.var_char_arr_a.length; i2 += bh.a(this.var_char_arr_a, i2, 176, 18)) {
            sArray[n2++] = (short)i2;
        }
        this.var_short_arr_a = new short[n2];
        System.arraycopy(sArray, 0, this.var_short_arr_a, 0, this.var_short_arr_a.length);
        this.var_byte_a = (byte)this.var_short_arr_a.length;
        bh.var_b_a.var_boolean_b = false;
        bh.var_b_b.var_boolean_b = false;
        bh.var_b_c.var_boolean_b = false;
    }

    public final boolean a(int n2, int n3) {
        if (this.b(n2, n3)) {
            return true;
        }
        if (this.c(n2, n3)) {
            return true;
        }
        if (n3 == bh.var_int_a) {
            this.var_cb_a.void_a();
            bh.var_b_a.var_boolean_b = true;
            bh.var_b_b.var_boolean_b = true;
            bh.var_b_c.var_boolean_b = true;
            return true;
        }
        return true;
    }

    public final void a(Graphics graphics, int n2, int n3) {
        short s2;
        boolean bl2 = false;
        boolean bl3 = false;
        graphics.setColor(0x3F1F3F);
        graphics.fillRect(0, 0, r.g, r.h);
        bf.c(graphics, n2, n3);
        bh.a(graphics, 9, n2 + 201 >> 1, n3 + 5);
        bf.b(graphics, n2, n3 + 24, 3);
        n2 += 12;
        n3 += 42;
        if (this.var_byte_a > 1) {
            if (this.var_byte_b > 0) {
                graphics.drawImage(ce.var_javax_microedition_lcdui_Image_l, n2 + 62 + 13, n3 - 6, 20);
            }
            if (this.var_byte_b < this.var_byte_a - 1) {
                graphics.drawImage(ce.var_javax_microedition_lcdui_Image_o, n2 + 62 + 13, n3 + 114 + 26 + 40, 20);
            }
        }
        r.d(graphics, n2 + 201 - 25, n3 - 8, this.var_byte_b + 1, this.var_byte_a);
        short s3 = this.var_short_arr_a[this.var_byte_b];
        short s4 = s2 = this.var_byte_b == this.var_byte_a - 1 ? (short)this.var_char_arr_a.length : this.var_short_arr_a[this.var_byte_b + 1];
        if (this.var_char_arr_a[0] == '!' && s3 == 0) {
            s3 = 1;
        }
        graphics.setColor(0);
        bh.b(graphics, n2 + 201 >> 1, (n3 += 25) + 3, 176, 1, this.var_char_arr_a, s3, 0, s2 - s3);
        bh.a(graphics, null, bh.var_char_arr_e);
    }
}

