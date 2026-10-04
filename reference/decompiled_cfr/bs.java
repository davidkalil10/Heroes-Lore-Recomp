/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Display
 *  javax.microedition.lcdui.Displayable
 */
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;

public final class bs
implements Runnable {
    public static final int[] var_int_arr_a;
    private Display var_javax_microedition_lcdui_Display_a;
    private r var_r_a;
    public static as var_as_a;
    private int var_int_c;
    private int var_int_d;
    public int var_int_a = bw.var_int_a;
    public boolean var_boolean_a = !x.a;
    public boolean var_boolean_b = false;
    public boolean var_boolean_c = false;
    public boolean var_boolean_d = true;
    public byte var_byte_a = (byte)2;
    public byte var_byte_b = 0;
    public int var_int_b = 0;
    private long var_long_a;
    public boolean e;
    private boolean f = true;
    public static Object var_java_lang_Object_a;
    public static bs var_bs_a;

    public static final void a(Display display) {
        var_bs_a = new bs(display);
    }

    private bs(Display display) {
        this.var_javax_microedition_lcdui_Display_a = display;
        this.var_int_c = var_int_arr_a[this.var_byte_a];
        this.f();
        this.var_byte_b = (byte)(this.var_byte_b | 8);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final void run() {
        if (this.f) {
            this.f = false;
            this.var_r_a = new bg();
            this.var_javax_microedition_lcdui_Display_a.setCurrent((Displayable)this.var_r_a);
            ((bg)this.var_r_a).a();
            bw.g();
            this.g();
        }
        Object object = var_java_lang_Object_a;
        synchronized (object) {
            if (this.e) {
                return;
            }
            this.void_a();
            this.var_r_a.i();
            this.var_r_a.j();
            this.var_javax_microedition_lcdui_Display_a.callSerially((Runnable)this);
            return;
        }
    }

    public final void void_a() {
        this.var_long_a = System.currentTimeMillis();
    }

    public final void b() {
        if (!ah.var_boolean_a) {
            this.a(this.var_long_a, this.var_int_d);
        }
    }

    public final void a(long l2, long l3) {
        long l4 = System.currentTimeMillis() - l2;
        if (l4 < l3) {
            try {
                Thread.sleep(l3 - l4);
                return;
            }
            catch (InterruptedException interruptedException) {
                return;
            }
        }
        Thread.yield();
    }

    public final void c() {
        this.f = true;
        new Thread(this).start();
    }

    public final void d() {
        this.var_r_a = new as();
        var_as_a = (as)this.var_r_a;
        this.var_javax_microedition_lcdui_Display_a.setCurrent((Displayable)this.var_r_a);
        n.p();
    }

    public final void e() {
        this.var_r_a = new bg();
        var_as_a = null;
        ((bg)this.var_r_a).a(false, (byte)2);
        this.var_javax_microedition_lcdui_Display_a.setCurrent((Displayable)this.var_r_a);
        var_bs_a.g();
    }

    public final void a(int n2) {
        this.var_int_d = 1000 / n2;
    }

    public final void f() {
        this.a(this.var_int_c);
    }

    public final void g() {
        this.a(5);
    }

    public final void h() {
        this.a(20);
    }

    public final void a(byte by2) {
        this.var_byte_a = by2;
        this.var_int_c = var_int_arr_a[by2];
    }

    public final byte[] byte_arr_a() {
        int n2 = 0;
        n2 = 0 | (this.var_int_a & 0xF) << 4;
        if (x.a && this.var_boolean_a) {
            n2 |= 8;
        }
        if (this.var_boolean_b) {
            n2 |= 4;
        }
        if (this.var_boolean_c) {
            n2 |= 2;
        }
        if (this.var_boolean_d) {
            n2 |= 1;
        }
        byte[] byArray = new byte[6];
        byte[] byArray2 = byArray;
        byArray[0] = (byte)n2;
        byArray2[1] = (byte)((this.var_byte_a & 0xF) << 4 | this.var_byte_b);
        h.a(this.var_int_b ^ 0xE1F084DE, byArray2, 2);
        return byArray2;
    }

    public final void a(byte[] byArray) {
        this.var_int_a = (byte)((byArray[0] & 0xF0) >> 4);
        if (x.a) {
            this.var_boolean_a = (byArray[0] & 8) != 0;
        }
        this.var_boolean_b = (byArray[0] & 4) != 0;
        this.var_boolean_c = (byArray[0] & 2) != 0;
        this.var_boolean_d = (byArray[0] & 1) != 0;
        this.var_byte_a = (byte)((byArray[1] & 0xF0) >> 4);
        this.var_byte_b = (byte)(byArray[1] & 0xF);
        bw.a(this.var_int_a);
        this.a(this.var_byte_a);
        this.var_int_b = h.int_a(byArray, 2) ^ 0xE1F084DE;
    }

    public final void i() throws Exception {
        byte[] byArray = this.byte_arr_a();
        au au2 = new au("/c", 0);
        au2.a(byArray, 0, byArray.length);
        au2.void_a();
    }

    public final void j() throws Exception {
        byte[] byArray = new byte[6];
        au au2 = new au("/c", 1);
        au2.b(byArray, 0, byArray.length);
        au2.void_a();
        this.a(byArray);
    }

    static {
        var_int_arr_a = new int[]{8, 10, 14, 18};
        var_java_lang_Object_a = new Object();
    }
}

