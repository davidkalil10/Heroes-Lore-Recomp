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
public final class i
extends y {
    private o a;
    private boolean d;
    private byte f;
    private byte g;
    private byte h;
    private boolean e;
    private int a;
    private byte i;
    private boolean f;

    public i(byte by2, byte by3, byte[] byArray, o o2, byte by4, byte by5, byte by6) {
        super(by2, by3, byArray);
        this.a = o2;
        this.d = false;
        this.f = by4;
        this.g = (byte)(by5 - 1);
        this.h = by6;
    }

    public i(byte by2, byte by3, byte[] byArray, o o2, boolean bl2, byte by4, byte by5, byte by6, int n2, byte by7, boolean bl3) {
        super(by2, by3, byArray);
        this.a = o2;
        this.d = bl2;
        this.f = by4;
        this.g = (byte)(by5 - 1);
        this.h = by6;
        this.a = n2;
        this.i = by7;
        this.f = bl3;
    }

    public final void a() {
        u u2;
        if (this.b == this.h && this.g > 0 && (this.d || !this.e)) {
            u2 = n.a;
            byte by2 = (byte)(((ck)this).a + u.a[this.f]);
            byte by3 = (byte)(((ck)this).b + u.b[this.f]);
            if (by2 >= 0 && by2 < ((ae)u2).a && by3 >= 0 && by3 < ((ae)u2).b) {
                if (this.a instanceof al) {
                    ((ae)u2).b(new i(by2, by3, ((y)this).h, this.a, this.f, this.g, this.h));
                } else if (this.a instanceof ao) {
                    ((ae)u2).b(new i(by2, by3, ((y)this).h, this.a, this.d, this.f, this.g, this.h, this.a, this.i, this.f));
                }
            }
        }
        if ((this.d || !this.e) && this.b == 1) {
            u2 = n.a.a[((ck)this).b][((ck)this).a];
            if (this.a instanceof al) {
                if (u2 != null && u2 instanceof ao) {
                    ((ao)u2).a((al)this.a, this.f);
                    this.e = true;
                    return;
                }
            } else if (this.a instanceof ao && u2 != null && u2 instanceof al) {
                ((al)u2).a(this.a, false, this.f, this.f, (byte)1, this.i, (ao)this.a);
                this.e = true;
            }
        }
    }

    public final boolean a() {
        return this.b == ((y)this).a || ((y)this).a == 2 && !this.d && this.e && this.b >= 1;
    }

    public final void a(Graphics graphics, int n2, int n3) {
        int n4 = n2 + this.c + this.c;
        int n5 = n3 + ((ck)this).d + ((ck)this).d;
        if (((y)this).a == 2 && this.b == 1) {
            n4 += u.a[this.f] * 8;
            n5 += u.b[this.f] * 8;
        }
        this.b(graphics, n4, n5);
        this.b = (short)(this.b + 1);
    }
}

