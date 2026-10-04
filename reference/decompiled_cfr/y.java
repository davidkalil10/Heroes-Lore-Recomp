/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 *  javax.microedition.lcdui.Image
 */
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class y
extends ck {
    private static final short[] var_short_arr_a;
    private static final byte[] i;
    public short var_short_a;
    public short b;
    private byte f;
    private Image[] var_javax_microedition_lcdui_Image_arr_a;
    public byte[] h;

    public y(short s2, short s3, byte by2) {
        super(s2, s3, (byte)8, (byte)9);
        this.var_short_a = var_short_arr_a[by2];
        this.b = 0;
        this.f = by2;
        this.var_javax_microedition_lcdui_Image_arr_a = ce.var_javax_microedition_lcdui_Image_arr_arr_a[12];
        if (i[by2] != -1) {
            this.h = (byte[])ce.var_java_lang_Object_arr_d[i[by2]];
        }
    }

    public y(byte by2, byte by3, byte[] byArray) {
        super((short)(by2 << 4), (short)(by3 << 4), (byte)8, (byte)9);
        this.var_short_a = byArray[0];
        this.b = 0;
        this.f = (byte)100;
        this.h = byArray;
    }

    public void void_a() {
    }

    public boolean boolean_a() {
        return this.b >= this.var_short_a;
    }

    public void a(Graphics graphics, int n2, int n3) {
        n.var_ae_a.c(this);
        int n4 = n2 + this.var_short_c + this.var_byte_c;
        int n5 = n3 + this.var_short_d + this.var_byte_d;
        switch (this.f) {
            case 2: {
                this.c(graphics, n4, n5);
                break;
            }
            case 4: {
                this.a(graphics, n4, n5, var_short_arr_a[4], this.var_javax_microedition_lcdui_Image_arr_a[8]);
                break;
            }
            case 5: {
                this.a(graphics, n4, n5, var_short_arr_a[5], this.var_javax_microedition_lcdui_Image_arr_a[11]);
                break;
            }
            case 7: {
                this.a(graphics, n4, n5, var_short_arr_a[7], this.var_javax_microedition_lcdui_Image_arr_a[11]);
                break;
            }
            case 1: 
            case 6: 
            case 8: 
            case 9: 
            case 100: {
                this.b(graphics, n4, n5);
                break;
            }
            case 10: {
                this.d(graphics, n4, n5);
            }
        }
        this.b = (short)(this.b + 1);
        if (this.boolean_a()) {
            n.var_ae_a.a(this);
        }
    }

    private void c(Graphics graphics, int n2, int n3) {
        if (this.b >= 0 && this.b < this.var_short_a) {
            as.b(graphics, ce.var_byte_arr_m, (byte)this.b, n2, n3);
        }
    }

    private void a(Graphics graphics, int n2, int n3, int n4, Image image) {
        if (this.b >= n4) {
            return;
        }
        int n5 = image.getHeight();
        switch (this.b) {
            case 0: {
                n5 = n5 * 7 / 10;
                as.a(graphics, n2 - 20, n3 - 50, 40, 50);
                graphics.drawImage(image, n2, n3 + n5, 33);
                graphics.setClip(0, 0, as.var_int_a, as.b);
                return;
            }
            case 1: {
                n5 = n5 * 5 / 10;
                as.a(graphics, n2 - 20, n3 - 50, 40, 50);
                graphics.drawImage(image, n2, n3 + n5, 33);
                graphics.setClip(0, 0, as.var_int_a, as.b);
                return;
            }
            case 2: {
                n5 = n5 * 3 / 10;
                as.a(graphics, n2 - 20, n3 - 50, 40, 50);
                graphics.drawImage(image, n2, n3 + n5, 33);
                graphics.setClip(0, 0, as.var_int_a, as.b);
                return;
            }
        }
        as.b(graphics, this.h, (byte)(this.b - 3), n2, n3);
    }

    public final void b(Graphics graphics, int n2, int n3) {
        if (this.b >= 0 && this.b < this.var_short_a) {
            as.b(graphics, this.h, (byte)this.b, n2, n3);
        }
    }

    private void d(Graphics graphics, int n2, int n3) {
        switch (this.b) {
            case 0: {
                graphics.drawImage(this.var_javax_microedition_lcdui_Image_arr_a[0], n2, n3, 33);
                return;
            }
            case 1: {
                graphics.drawImage(this.var_javax_microedition_lcdui_Image_arr_a[0], n2, n3, 33);
                graphics.drawImage(this.var_javax_microedition_lcdui_Image_arr_a[1], n2, n3 + 3, 33);
                return;
            }
            case 2: {
                graphics.drawImage(this.var_javax_microedition_lcdui_Image_arr_a[1], n2, n3 + 3, 33);
            }
        }
    }

    static {
        var_short_arr_a = new short[]{-1, 4, 8, 6, 10, 11, 7, 9, 6, 4, 3};
        i = new byte[]{-1, 0, -1, -1, 0, 0, 1, 0, 1, 1, -1};
    }
}

