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
public final class af
extends cb {
    private byte c;
    private Object[] a;
    private char[] a;
    private int a;
    private char[] b;
    private char[] c;

    public af(cb cb2, byte by2, byte by3, Object[] objectArray, char[] cArray, char[] cArray2) {
        super(cb2, by3);
        char[] cArray3;
        int n2;
        this.c = by2;
        StringBuffer stringBuffer = new StringBuffer();
        for (n2 = 0; n2 < objectArray.length; ++n2) {
            cArray3 = (char[])objectArray[n2];
            if (cArray3.length <= 0) continue;
            stringBuffer.append(cArray3);
            if (n2 == objectArray.length - 1) continue;
            stringBuffer.append('\n');
        }
        this.a = stringBuffer.toString().toCharArray();
        this.a = objectArray;
        if (by2 == 2 || by2 == 12) {
            if (cArray == null) {
                cArray = bh.k;
            }
            if (cArray2 == null) {
                cArray2 = bh.l;
            }
        } else if (by2 == 1 || by2 == 11) {
            if (cArray == null) {
                cArray = bh.d;
            }
        } else if (by2 != 9) {
            if (cArray == null) {
                cArray = bh.d;
            }
            if (cArray2 == null) {
                cArray2 = bh.e;
            }
        }
        this.b = cArray;
        this.c = cArray2;
        switch (by2) {
            case 2: 
            case 6: {
                this.a = 8 + bh.a(bh.a(r.g, 80) - 10, 1, this.a, 0, 0, this.a.length);
                break;
            }
            case 11: 
            case 12: {
                this.a = 8 + bh.a(bh.a(r.g, 80) - 10, 1, this.a, 0, 0, this.a.length);
                break;
            }
            case 3: 
            case 4: 
            case 5: 
            case 8: {
                this.a = 12;
                for (n2 = 0; n2 < this.a.length; ++n2) {
                    cArray3 = (char[])this.a[n2];
                    this.a += 3 + bh.a(bh.a(r.g, 80) - 10, 1, cArray3, 0, 0, cArray3.length);
                }
                break;
            }
            default: {
                this.a = 22 + bh.a(bh.a(r.g, 80) - 10, 1, this.a, 0, 0, this.a.length);
            }
        }
        if (by2 == 6) {
            ((cb)this).b = 1;
        }
    }

    public final boolean a(int n2, int n3) {
        if (this.b(n2, n3)) {
            return true;
        }
        switch (this.c) {
            case 1: 
            case 11: {
                if (n3 != 53 && n2 != 8) break;
                ((cb)this).a.a(this.c, (byte)0);
                return true;
            }
            case 2: 
            case 6: 
            case 12: {
                if (n3 == 53) {
                    ((cb)this).a.a(this.c, (byte)0);
                    return true;
                }
                if (n3 != bh.a) break;
                ((cb)this).a.a(this.c, (byte)99);
                break;
            }
            case 3: 
            case 4: 
            case 5: 
            case 8: {
                if (this.c(n2, n3)) {
                    return true;
                }
                if (n3 == 53 || n2 == 8) {
                    ((cb)this).a.a(this.c, ((cb)this).b);
                    return true;
                }
                if (n3 != bh.a) break;
                ((cb)this).a.a(this.c, (byte)99);
            }
        }
        return true;
    }

    public final void a(Graphics graphics, int n2, int n3) {
        bh.a(graphics);
        int n4 = bh.a(r.g, 80);
        n2 = r.i - (n4 >> 1);
        n3 = r.j - (this.a >> 1);
        cb.a(graphics, n2, n3, n4, this.a);
        cb.b(graphics, n2, n3, n4, this.a);
        switch (this.c) {
            case 11: {
                int n5 = n3 + 5;
                bh.a(graphics, n2 + 5, n5, n4 - 10, 1, this.a);
                break;
            }
            case 12: {
                int n6 = n3 + 5;
                bh.a(graphics, n2 + 5, n6, n4 - 10, 1, this.a);
                break;
            }
            case 1: 
            case 9: {
                graphics.setColor(0xFFFFFF);
                int n7 = n3 + 5;
                bh.a(graphics, n2 + 5, n7, n4 - 10, 1, this.a);
                if (this.c == 9) break;
                bh.a(graphics, this.b, null);
                break;
            }
            case 2: 
            case 6: {
                int n8 = n3 + 5;
                graphics.setColor(0xFFFFFF);
                bh.a(graphics, n2 + 5, n8, n4 - 10, 1, this.a);
                break;
            }
            case 8: {
                graphics.setColor(0xFFFFFF);
                n3 += 5;
                n3 += 3 + bh.a(graphics, n2 + 5, n3, n4 - 10, 1, (char[])this.a[0]);
                for (int n9 = 1; n9 < this.a.length; n9 = (int)((byte)(n9 + 1))) {
                    if (n9 == ((cb)this).b + 1) {
                        graphics.drawImage(ce.e, n2 + 5, n3, 20);
                    }
                    n3 += 3 + bh.a(graphics, n2 + 12, n3, n4 - 10, 1, (char[])this.a[n9]);
                }
                break;
            }
            case 3: 
            case 4: 
            case 5: {
                graphics.setColor(0xFFFFFF);
                n3 += 7;
                for (int n10 = 0; n10 < this.a.length; n10 = (int)((byte)(n10 + 1))) {
                    if (n10 == ((cb)this).b) {
                        graphics.drawImage(ce.e, n2 + 5, n3, 20);
                    }
                    n3 += 3 + bh.a(graphics, n2 + 12, n3, n4 - 10, 1, (char[])this.a[n10]);
                }
                break;
            }
        }
        bh.a(graphics, this.b, this.c);
    }
}

