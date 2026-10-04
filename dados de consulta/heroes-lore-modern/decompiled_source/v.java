/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 */
import java.util.Vector;
import javax.microedition.lcdui.Graphics;

public final class v
extends cb {
    private ad[] a;
    public byte c;

    public v(cb cb2, Vector vector, byte by2) {
        super(cb2, (byte)vector.size());
        this.a = new ad[vector.size()];
        for (int i2 = 0; i2 < this.a.length; ++i2) {
            this.a[i2] = (ad)vector.elementAt(i2);
        }
        this.c = by2;
    }

    public final boolean a(int n2, int n3) {
        if (this.b(n2, n3)) {
            return true;
        }
        if (this.c(n2, n3)) {
            ((cb)this).a.a = true;
            return true;
        }
        if (n3 == 53 || n2 == 8) {
            this.b = new ab(this, this.a[this.b], true);
            return true;
        }
        if (n3 == 35) {
            byte[] byArray = n.a().a.a();
            if (byArray.length > 0) {
                this.b = new bb((cb)this, byArray);
            } else {
                this.a((byte)1, (byte)0, new Object[]{bp.a.a(16), bp.a.a(17)});
            }
            return true;
        }
        return false;
    }

    public final void a(Graphics graphics, int n2, int n3) {
        ad ad2;
        bh.a(graphics, bh.i, bh.e);
        this.a(graphics, n2 += 2, n3 += 15, true);
        short s2 = -1;
        ao ao2 = n.a();
        switch (this.c) {
            case 1: {
                if (ao2.a() == null) break;
                s2 = ((e)ao2.a()).a;
                break;
            }
            case 2: {
                if (ao2.a() == null) break;
                s2 = ((e)ao2.a()).a;
                break;
            }
            case 3: {
                if (ao2.a() == null) break;
                s2 = ao2.a().a;
                break;
            }
            case 4: {
                if (ao2.b() == null) break;
                s2 = ao2.b().a;
                break;
            }
            case 5: {
                if (ao2.c() == null) break;
                s2 = ao2.c().a;
            }
        }
        for (int i2 = this.c(); i2 <= this.d(); ++i2) {
            ad2 = this.a[i2];
            if (ad2 == null) continue;
            cb.a(graphics, n2 + 13, n3 + 18 + 23 * (i2 % 5), ad2, false);
            if (r.h <= 160 || this.c == 0) continue;
            short s3 = ((e)this.a[this.b]).a;
            if (s2 > s3) {
                graphics.drawImage(ce.r, n2 + 20, n3 + 18 + this.b % 5 * 23, 33);
                continue;
            }
            if (s2 >= s3) continue;
            graphics.drawImage(ce.q, n2 + 20, n3 + 18 + this.b % 5 * 23, 33);
        }
        ad2 = this.a[this.b];
        if (ad2 != null) {
            cb.a(graphics, n2 + 33, n3 + 14, ad2);
        }
        if (r.g < 176) {
            String string = String.valueOf(bp.a.a(17));
            bh.a(graphics, bp.a + 201 - 34, bp.b + 244 - 12, "#" + string.substring(0, string.length() - 1).toUpperCase(), 40);
            return;
        }
        graphics.drawImage(ce.d, bp.a + 201 - 40, bp.b + 244 - 20, 20);
    }
}

