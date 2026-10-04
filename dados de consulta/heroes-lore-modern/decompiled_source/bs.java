/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Display
 *  javax.microedition.lcdui.Displayable
 */
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public final class bs
implements Runnable {
    public static final int[] a = new int[]{8, 10, 14, 18};
    private Display a;
    private r a;
    public static as a;
    private int c;
    private int d;
    public int a;
    public boolean a;
    public boolean b;
    public boolean c = false;
    public boolean d = true;
    public byte a;
    public byte b;
    public int b = 0;
    private long a = (byte)2;
    public boolean e;
    private boolean f = true;
    public static Object a;
    public static bs a;

    public static final void a(Display display) {
        a = new bs(display);
    }

    private bs(Display display) {
        this.a = display;
        this.c = a[this.a];
        this.f();
        this.b = (byte)(this.b | 8);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final void run() {
        if (this.f) {
            this.f = false;
            this.a = new bg();
            this.a.setCurrent((Displayable)this.a);
            ((bg)this.a).a();
            bw.g();
            this.g();
        }
        Object object = a;
        synchronized (object) {
            if (this.e) {
                return;
            }
            this.a();
            this.a.i();
            this.a.j();
            this.a.callSerially((Runnable)this);
            return;
        }
    }

    public final void a() {
        this.a = System.currentTimeMillis();
    }

    public final void b() {
        if (!ah.a) {
            this.a(this.a, this.d);
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
        this.a = new as();
        a = (as)this.a;
        this.a.setCurrent((Displayable)this.a);
        n.p();
    }

    public final void e() {
        this.a = new bg();
        a = null;
        ((bg)this.a).a(false, (byte)2);
        this.a.setCurrent((Displayable)this.a);
        a.g();
    }

    public final void a(int n2) {
        this.d = 1000 / n2;
    }

    public final void f() {
        this.a(this.c);
    }

    public final void g() {
        this.a(5);
    }

    public final void h() {
        this.a(20);
    }

    public final void a(byte by2) {
        this.a = by2;
        this.c = a[by2];
    }

    public final byte[] a() {
        int n2 = 0;
        n2 = 0 | (this.a & 0xF) << 4;
        if (x.a && this.a) {
            n2 |= 8;
        }
        if (this.b) {
            n2 |= 4;
        }
        if (this.c) {
            n2 |= 2;
        }
        if (this.d) {
            n2 |= 1;
        }
        byte[] byArray = new byte[6];
        byte[] byArray2 = byArray;
        byArray[0] = (byte)n2;
        byArray2[1] = (byte)((this.a & 0xF) << 4 | this.b);
        h.a(this.b ^ 0xE1F084DE, byArray2, 2);
        return byArray2;
    }

    public final void a(byte[] byArray) {
        this.a = (byte)((byArray[0] & 0xF0) >> 4);
        if (x.a) {
            this.a = (byArray[0] & 8) != 0;
        }
        this.b = (byArray[0] & 4) != 0;
        this.c = (byArray[0] & 2) != 0;
        this.d = (byArray[0] & 1) != 0;
        this.a = (byte)((byArray[1] & 0xF0) >> 4);
        this.b = (byte)(byArray[1] & 0xF);
        bw.a(this.a);
        this.a(this.a);
        this.b = h.a(byArray, 2) ^ 0xE1F084DE;
    }

    public final void i() throws Exception {
        byte[] byArray = this.a();
        au au2 = new au("/c", 0);
        au2.a(byArray, 0, byArray.length);
        au2.a();
    }

    public final void j() throws Exception {
        byte[] byArray = new byte[6];
        au au2 = new au("/c", 1);
        au2.b(byArray, 0, byArray.length);
        au2.a();
        this.a(byArray);
    }

    static {
        a = new Object();
    }
}

