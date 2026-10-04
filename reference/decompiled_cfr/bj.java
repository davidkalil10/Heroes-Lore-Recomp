/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 *  javax.microedition.lcdui.Image
 */
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public final class bj
extends f {
    private byte var_byte_a;
    private byte var_byte_b;
    private short c;
    private Image[] var_javax_microedition_lcdui_Image_arr_a;
    private Image[] var_javax_microedition_lcdui_Image_arr_b;
    private Object[] var_java_lang_Object_arr_a;

    public bj(short s2, short s3, byte by2, byte by3) {
        super(s3);
        this.c = s2;
        this.var_byte_b = by2;
        this.var_byte_a = by3;
        this.var_javax_microedition_lcdui_Image_arr_a = new Image[2];
        Image[] imageArray = ce.var_javax_microedition_lcdui_Image_arr_arr_a[12];
        this.var_javax_microedition_lcdui_Image_arr_a[0] = imageArray[0];
        this.var_javax_microedition_lcdui_Image_arr_a[1] = imageArray[1];
        this.var_javax_microedition_lcdui_Image_arr_b = ce.var_javax_microedition_lcdui_Image_arr_arr_a[12];
        this.var_java_lang_Object_arr_a = ce.var_java_lang_Object_arr_d;
    }

    public final void a(Graphics graphics, int n2, int n3) {
        if (this.c > 0) {
            this.c = (short)(this.c - 1);
            return;
        }
        if (this.var_byte_b == 0 && this.var_byte_a == 0) {
            switch (((f)this).b) {
                case 0: {
                    graphics.drawImage(this.var_javax_microedition_lcdui_Image_arr_b[7], n2, n3, 33);
                    break;
                }
                case 1: {
                    graphics.drawImage(this.var_javax_microedition_lcdui_Image_arr_b[8], n2, n3 - 1, 33);
                    break;
                }
                case 2: {
                    graphics.drawImage(this.var_javax_microedition_lcdui_Image_arr_b[7], n2, n3 - 2, 33);
                    break;
                }
                case 3: {
                    graphics.drawImage(this.var_javax_microedition_lcdui_Image_arr_b[8], n2, n3 - 3, 33);
                    break;
                }
                case 4: 
                case 5: {
                    graphics.drawImage(this.var_javax_microedition_lcdui_Image_arr_b[9], n2, n3 - 4, 33);
                }
            }
        } else if (this.var_byte_b == 0 && this.var_byte_a == 2 || this.var_byte_b == 1 && this.var_byte_a == 2) {
            switch (((f)this).b % 4) {
                case 1: {
                    graphics.drawImage(this.var_javax_microedition_lcdui_Image_arr_a[0], n2, n3 + 9, 33);
                    break;
                }
                case 2: {
                    graphics.drawImage(this.var_javax_microedition_lcdui_Image_arr_a[0], n2, n3 + 9, 33);
                    graphics.drawImage(this.var_javax_microedition_lcdui_Image_arr_a[1], n2, n3 + 12, 33);
                    break;
                }
                case 3: {
                    graphics.drawImage(this.var_javax_microedition_lcdui_Image_arr_a[1], n2, n3 + 12, 33);
                }
            }
        } else {
            as.b(graphics, (byte[])this.var_java_lang_Object_arr_a[this.var_byte_a], (byte)((f)this).b, n2, n3);
        }
        ((f)this).b = (short)(((f)this).b + 1);
        if (((f)this).b >= this.var_short_a) {
            this.var_boolean_a = true;
        }
    }
}

