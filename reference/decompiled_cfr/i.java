/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 */
import javax.microedition.lcdui.Graphics;

public final class i
extends y {
    private o var_o_a;
    private boolean d;
    private byte var_byte_f;
    private byte g;
    private byte h;
    private boolean e;
    private int var_int_a;
    private byte i;
    private boolean var_boolean_f;

    public i(byte by2, byte by3, byte[] byArray, o o2, byte by4, byte by5, byte by6) {
        super(by2, by3, byArray);
        this.var_o_a = o2;
        this.d = false;
        this.var_byte_f = by4;
        this.g = (byte)(by5 - 1);
        this.h = by6;
    }

    public i(byte by2, byte by3, byte[] byArray, o o2, boolean bl2, byte by4, byte by5, byte by6, int n2, byte by7, boolean bl3) {
        super(by2, by3, byArray);
        this.var_o_a = o2;
        this.d = bl2;
        this.var_byte_f = by4;
        this.g = (byte)(by5 - 1);
        this.h = by6;
        this.var_int_a = n2;
        this.i = by7;
        this.var_boolean_f = bl3;
    }

    public final void void_a() {
        u u2;
        if (this.b == this.h && this.g > 0 && (this.d || !this.e)) {
            u2 = n.var_ae_a;
            byte by2 = (byte)(this.var_byte_a + u.var_byte_arr_a[this.var_byte_f]);
            byte by3 = (byte)(this.var_byte_b + u.b[this.var_byte_f]);
            if (by2 >= 0 && by2 < ((ae)u2).var_int_a && by3 >= 0 && by3 < ((ae)u2).var_int_b) {
                if (this.var_o_a instanceof al) {
                    ((ae)u2).b(new i(by2, by3, ((y)this).h, this.var_o_a, this.var_byte_f, this.g, this.h));
                } else if (this.var_o_a instanceof ao) {
                    ((ae)u2).b(new i(by2, by3, ((y)this).h, this.var_o_a, this.d, this.var_byte_f, this.g, this.h, this.var_int_a, this.i, this.var_boolean_f));
                }
            }
        }
        if ((this.d || !this.e) && this.b == 1) {
            u2 = n.var_ae_a.var_ck_arr_arr_a[this.var_byte_b][this.var_byte_a];
            if (this.var_o_a instanceof al) {
                if (u2 != null && u2 instanceof ao) {
                    ((ao)u2).a((al)this.var_o_a, this.var_byte_f);
                    this.e = true;
                    return;
                }
            } else if (this.var_o_a instanceof ao && u2 != null && u2 instanceof al) {
                ((al)u2).a(this.var_int_a, false, this.var_byte_f, this.var_boolean_f, (byte)1, this.i, (ao)this.var_o_a);
                this.e = true;
            }
        }
    }

    public final boolean boolean_a() {
        return this.b == this.var_short_a || this.var_short_a == 2 && !this.d && this.e && this.b >= 1;
    }

    public final void a(Graphics graphics, int n2, int n3) {
        int n4 = n2 + this.var_short_c + this.var_byte_c;
        int n5 = n3 + this.var_short_d + this.var_byte_d;
        if (this.var_short_a == 2 && this.b == 1) {
            n4 += u.var_byte_arr_a[this.var_byte_f] * 8;
            n5 += u.b[this.var_byte_f] * 8;
        }
        this.b(graphics, n4, n5);
        this.b = (short)(this.b + 1);
    }
}

