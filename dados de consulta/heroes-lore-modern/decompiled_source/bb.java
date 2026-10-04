/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 */
import javax.microedition.lcdui.Graphics;

public final class bb
extends m {
    public bb(cb cb2, byte[] byArray) {
        super(cb2, byArray, (byte)0, bp.a.a(18));
    }

    public final boolean a(int n2, int n3) {
        if (this.b(n2, n3)) {
            return true;
        }
        if (this.c(n2, n3)) {
            return true;
        }
        if (n3 == 53 || n2 == 8) {
            ad ad2 = n.a().a.a((int)this.h[this.b]);
            if (ad2.b()) {
                this.a((byte)1, (byte)0, new Object[]{bp.a.a(19), bp.a.a(20)});
            } else {
                this.b = new ab(this, ad2, false);
            }
            return true;
        }
        if (n3 == bh.a || n3 == 35) {
            ((cb)this).a.a((byte)-1, (byte)-1);
            return true;
        }
        return true;
    }

    public final void a(byte by2, byte by3) {
        cb cb2 = this.b;
        super.a(by2, by3);
        if (cb2 instanceof ab) {
            ((cb)this).a.a();
            byte[] byArray = n.a().a.a();
            if (byArray.length > 0) {
                ((cb)this).a.b = new bb(((cb)this).a, byArray);
                return;
            }
            ((cb)this).a.a((byte)1, (byte)0, new Object[]{bp.a.a(21), bp.a.a(22)});
        }
    }

    public final void a(Graphics graphics, int n2, int n3) {
        bh.a(graphics);
        bh.a(graphics, bh.i, bh.e);
        super.a(graphics, n2, n3);
        graphics.drawImage(ce.c, bp.a + 201 - 38, bp.b + 244 - 22, 20);
    }
}

