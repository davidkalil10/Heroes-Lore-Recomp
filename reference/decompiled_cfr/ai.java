/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 */
import java.io.IOException;
import javax.microedition.lcdui.Graphics;

public final class ai
extends cb
implements u {
    public static int var_int_a;
    public static int b;
    private byte[] h = new byte[5];
    private byte c;
    public static z var_z_a;
    private static ai var_ai_a;

    public static final ai ai_a() {
        if (var_ai_a == null) {
            var_ai_a = new ai();
            ai.var_ai_a.var_cb_b = new q(var_ai_a);
            var_int_a = r.i - 100;
            b = r.j - 122;
        }
        return var_ai_a;
    }

    private ai() {
        super(null, (byte)6);
    }

    public final void void_d() {
        ao ao2 = n.ao_a();
        p p2 = ao2.p_a();
        for (int n2 = 0; n2 < 5; n2 = (int)((byte)(n2 + 1))) {
            this.h[n2] = -1;
        }
        if (ao2.l_a() != null) {
            this.h[0] = bu.var_byte_arr_arr_a[n.var_byte_a - 6][ao2.l_a().g];
        }
        if (ao2.t_a() != null) {
            this.h[1] = bu.var_byte_arr_b[ao2.t_a().g];
        }
        if (ao2.e_a() != null) {
            this.h[2] = bu.var_byte_arr_arr_b[n.var_byte_a - 6][ao2.e_a().g];
        }
        if (ao2.e_b() != null) {
            this.h[3] = bu.var_byte_arr_a[ao2.e_b().g];
        }
        this.c = (byte)-1;
        if (p2 != null) {
            this.c = p2.f;
        }
        try {
            var_z_a = new z("/sgui/gm");
        }
        catch (IOException iOException) {
            IOException iOException2 = iOException;
            iOException.printStackTrace();
        }
        bs.var_bs_a.h();
    }

    public final void e() {
        this.var_byte_b = (byte)6;
        d d2 = new d(this);
        d2.void_d();
        this.var_cb_b = d2;
        this.var_cb_b.var_byte_b = 1;
    }

    public final void a(boolean bl2) {
        ao ao2 = n.ao_a();
        p p2 = ao2.p_a();
        var_ai_a = null;
        if (!bl2) {
            return;
        }
        if (ao2.t_a() != null && this.h[1] != bu.var_byte_arr_b[ao2.t_a().g]) {
            ao2.e((byte)1);
        }
        if (ao2.e_a() != null && this.h[2] != bu.var_byte_arr_arr_b[n.var_byte_a - 6][ao2.e_a().g]) {
            ao2.e((byte)2);
        }
        if (ao2.e_b() != null && this.h[3] != bu.var_byte_arr_a[ao2.e_b().g]) {
            ao2.e((byte)3);
        }
        if (p2 != null && this.c != p2.f) {
            ao2.l();
        } else {
            if (ao2.l_a() != null && this.h[0] != bu.var_byte_arr_arr_a[n.var_byte_a - 6][ao2.l_a().g]) {
                ao2.e((byte)0);
            }
            n.void_a(2);
            bs.var_bs_a.f();
        }
        this.var_cb_b = null;
        var_ai_a = null;
        this.h = null;
        var_z_a = null;
        bs.var_as_a.b();
    }

    public final boolean a(int n2, int n3) {
        if (this.b(n2, n3)) {
            return true;
        }
        if (n3 == bh.var_int_a) {
            n.a((byte)14, (byte)0);
            return true;
        }
        return this.d(n2, n3);
    }

    public final void a(Graphics graphics) {
        this.b(graphics, var_int_a, b);
    }

    public final void a(Graphics graphics, int n2, int n3) {
        if (n3 < 0) {
            n3 = 0;
        }
        if (this.var_cb_b != null) {
            bh.a(graphics);
        }
        boolean bl2 = false;
        graphics.setColor(0x3F1F3F);
        graphics.fillRect(n2, n3, 201, 250);
        cb.c(graphics, n2 + 2, n3 + 15, 197, 234);
        graphics.setColor(0xFFDFBF);
        graphics.fillRect(n2 + 5 + this.var_byte_b * 16 + 1, n3, 14, 1);
        graphics.fillRect(n2 + 5 + this.var_byte_b * 16, n3 + 1, 1, 16);
        graphics.setColor(12558207);
        graphics.fillRect(n2 + 5 + this.var_byte_b * 16 + 15, n3 + 1, 1, 15);
        graphics.setColor(14663551);
        graphics.fillRect(n2 + 5 + this.var_byte_b * 16 + 1, n3 + 1, 14, 16);
        int n4 = n2 + 7;
        for (int n5 = 0; n5 < 6; n5 = (int)((byte)(n5 + 1))) {
            graphics.drawImage(ce.var_javax_microedition_lcdui_Image_arr_m[n5], n4, n3 + 1, 20);
            n4 += 16;
        }
        if (this.var_cb_b != null) {
            bh.a(graphics, bh.var_char_arr_d, bh.var_char_arr_e);
        }
    }

    public final void a(byte by2) {
        super.a(by2);
        this.var_cb_b = null;
        switch (this.var_byte_b) {
            case 0: {
                this.var_cb_b = new q(this);
                break;
            }
            case 1: {
                this.var_cb_b = new ay(this);
                break;
            }
            case 2: {
                this.var_cb_b = new bz(this);
                break;
            }
            case 3: {
                this.var_cb_b = new bm(this);
                break;
            }
            case 4: {
                this.var_cb_b = new s(this);
                break;
            }
            case 5: {
                this.var_cb_b = new d(this);
            }
        }
        this.var_boolean_a = true;
    }
}

