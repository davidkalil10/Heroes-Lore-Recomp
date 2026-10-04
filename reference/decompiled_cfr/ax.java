/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 */
import java.io.IOException;
import javax.microedition.lcdui.Graphics;

public final class ax
extends cb {
    public static int var_int_a;
    public static int b;
    public static z var_z_a;
    private static ax var_ax_a;

    public static final ax ax_a() {
        if (var_ax_a == null) {
            var_ax_a = new ax();
            var_int_a = r.i - 100;
            b = r.j - 122;
        }
        return var_ax_a;
    }

    public ax() {
        super(null, (byte)0);
    }

    public final void void_d() {
        Object[] objectArray;
        try {
            var_z_a = new z("/sgui/refi");
        }
        catch (IOException objectArray2) {
            objectArray = objectArray2;
            objectArray2.printStackTrace();
        }
        objectArray = new Object[]{var_z_a.a(0), var_z_a.a(1), var_z_a.a(2)};
        this.a((byte)8, (byte)2, objectArray);
    }

    public final void e() {
        var_ax_a = null;
        var_z_a = null;
        this.var_cb_b = null;
        n.void_a(2);
        bs.var_as_a.b();
        System.gc();
    }

    public final boolean a(int n2, int n3) {
        if (this.b(n2, n3)) {
            return true;
        }
        if (n3 == bh.var_int_a) {
            this.e();
        }
        return false;
    }

    public final void a(byte by2, byte by3) {
        super.a(by2, by3);
        if (by2 == 8 && by3 == 0) {
            this.var_cb_b = new ap(this);
            return;
        }
        if (by2 == 8 && by3 == 1) {
            this.var_cb_b = new k(this);
            return;
        }
        this.e();
    }

    public final void a(Graphics graphics) {
        this.b(graphics, var_int_a, b);
    }

    public final void a(Graphics graphics, int n2, int n3) {
        bh.a(graphics);
        bh.a(graphics, bh.var_char_arr_d, bh.var_char_arr_e);
    }
}

