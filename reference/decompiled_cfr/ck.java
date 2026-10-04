/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 */
import java.util.Random;
import javax.microedition.lcdui.Graphics;

public abstract class ck
implements u {
    public byte var_byte_a;
    public byte var_byte_b;
    public boolean var_boolean_a;
    public boolean var_boolean_b;
    public short var_short_c;
    public short var_short_d;
    public byte var_byte_c;
    public byte var_byte_d;
    public byte e = 1;
    public static Random var_java_util_Random_a;
    public ck var_ck_a;
    public ck var_ck_b;
    public boolean var_boolean_c = false;

    public ck(short s2, short s3, byte by2, byte by3) {
        this.a(s2, s3);
        this.void_b();
        this.var_byte_c = by2;
        this.var_byte_d = by3;
    }

    public void a(short s2, short s3) {
        this.var_short_c = s2;
        this.var_short_d = s3;
    }

    public final void void_b() {
        this.var_byte_b = (byte)(this.var_short_d >> 4);
        this.var_byte_a = (byte)(this.var_short_c >> 4);
        this.var_boolean_b = (this.var_short_d & 0xF) != 0;
        this.var_boolean_a = (this.var_short_c & 0xF) != 0;
    }

    public final ck ck_a(byte by2, byte by3) {
        ae ae2 = n.var_ae_a;
        switch (by2) {
            case 1: {
                if (this.var_byte_b - by3 < 0) {
                    return null;
                }
                return ae2.var_ck_arr_arr_a[this.var_byte_b - by3][this.var_byte_a];
            }
            case 2: {
                if (this.var_byte_b + by3 >= ae2.var_int_b) {
                    return null;
                }
                return ae2.var_ck_arr_arr_a[this.var_byte_b + by3][this.var_byte_a];
            }
            case 3: {
                if (this.var_byte_a - by3 < 0) {
                    return null;
                }
                return ae2.var_ck_arr_arr_a[this.var_byte_b][this.var_byte_a - by3];
            }
            case 4: {
                if (this.var_byte_a + by3 >= ae2.var_int_a) {
                    return null;
                }
                return ae2.var_ck_arr_arr_a[this.var_byte_b][this.var_byte_a + by3];
            }
        }
        return null;
    }

    public abstract void a(Graphics var1, int var2, int var3);

    static {
        var_java_util_Random_a = new Random();
    }
}

