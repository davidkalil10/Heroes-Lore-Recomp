/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 */
import javax.microedition.lcdui.Graphics;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public final class cf
extends f {
    public byte a;
    private static final short[] a = new short[]{40, 40, 40, 40, 40, 140, 160, 80};

    public cf(byte by2) {
        super(a[by2]);
        this.a = by2;
    }

    public final void a() {
        this.b = (short)(this.b + 1);
        if (this.b >= ((f)this).a) {
            ((f)this).a = true;
        }
    }

    public final void b() {
        ((f)this).a = true;
    }

    public final void a(Graphics graphics, int n2, int n3) {
        graphics.drawImage(ce.E, n2, n3 - 30, 17);
        graphics.drawImage(ce.u[this.a], n2, n3 - 29 + this.b % 2, 17);
    }

    public final void c() {
        this.b = 0;
    }

    public final short a() {
        return this.b;
    }
}

