/*
 * Decompiled with CFR 0.152.
 */
import java.util.Vector;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class ad {
    public static z b;
    public static final boolean[] b;
    public static final boolean[] c;
    public byte f;
    public byte g;
    public char[] a;
    public char[] b;
    public int a;
    public byte h;

    public ad(byte by2, byte by3) {
        this.f = by2;
        this.g = by3;
        this.h = 1;
    }

    public final void a(boolean bl2) {
        byte[] byArray = ce.a(this.f, this.g);
        boolean bl3 = false;
        this.a(bl2, byArray, 1);
    }

    public int a(boolean bl2, byte[] byArray, int n2) {
        n2 += this.a(byArray, n2);
        n2 += this.b(byArray, n2);
        n2 += this.c(byArray, n2);
        return n2;
    }

    public final int a(byte[] byArray, int n2) {
        byte by2 = byArray[n2++];
        this.a = bh.a(new String(byArray, n2, (int)by2));
        return 1 + by2;
    }

    public final int b(byte[] byArray, int n2) {
        byte by2 = byArray[n2++];
        this.b = bh.a(new String(byArray, n2, (int)by2));
        return 1 + by2;
    }

    public final int c(byte[] byArray, int n2) {
        this.a += (byArray[n2 + 3] & 0xFF) * 0x1000000;
        this.a += (byArray[n2 + 2] & 0xFF) * 65536;
        this.a += (byArray[n2 + 1] & 0xFF) * 256;
        this.a += byArray[n2] & 0xFF;
        return 4;
    }

    public byte[] a() {
        byte[] byArray = new byte[10];
        byte[] byArray2 = byArray;
        byArray[0] = this.f;
        byArray2[1] = this.g;
        byArray2[2] = this.h;
        return byArray2;
    }

    public final void a(byte by2) {
        this.h = (byte)(this.h + by2);
    }

    public final void b(byte by2) {
        this.h = (byte)(this.h - by2);
    }

    public final char[] a() {
        return b.a(this.f);
    }

    public final boolean a() {
        return this.f == 10 || this.f == 7 || this.f == 8 || this.f == 9;
    }

    public final boolean b() {
        return this.f == 18 || this.f == 19 || this.f == 20 || this.f == 21;
    }

    public static final ad a(byte by2, byte by3, boolean bl2, boolean bl3) {
        ad ad2 = null;
        switch (by2) {
            case 0: 
            case 1: 
            case 2: {
                ad2 = new l(by2, by3);
                break;
            }
            case 3: {
                ad2 = new t(by2, by3);
                break;
            }
            case 4: 
            case 5: 
            case 6: {
                ad2 = new e(by2, by3);
                break;
            }
            default: {
                ad2 = new ad(by2, by3);
            }
        }
        if (bl2) {
            ad2.a(bl3);
        }
        ad2.h = 1;
        return ad2;
    }

    public static final ad a(byte[] byArray, int n2, boolean bl2, boolean bl3) {
        ad ad2 = null;
        byte by2 = byArray[n2++];
        byte by3 = byArray[n2++];
        switch (by2) {
            case 0: 
            case 1: 
            case 2: {
                ad2 = new l(by2, by3);
                break;
            }
            case 3: {
                ad2 = new t(by2, by3);
                break;
            }
            case 4: 
            case 5: 
            case 6: {
                ad2 = new e(by2, by3);
                break;
            }
            default: {
                ad2 = new ad(by2, by3);
            }
        }
        if (bl2) {
            ad2.a(bl3, byArray, n2);
        }
        return ad2;
    }

    public static final ad a(byte[] byArray) {
        ad ad2 = ad.a(byArray[0], byArray[1], true, true);
        ad.a(byArray[0], byArray[1], true, true).h = byArray[2];
        if (ad2 instanceof e) {
            ((e)ad2).b = byArray[3] == 1;
            ((e)ad2).e = byArray[4];
            ((e)ad2).a(byArray[5], byArray[6], byArray[7], byArray[8]);
        }
        if (ad2 instanceof t) {
            ((t)ad2).c = byArray[9];
        }
        return ad2;
    }

    public static final Vector[] a() {
        Vector[] vectorArray = new Vector[6];
        for (int i2 = 0; i2 < 6; ++i2) {
            vectorArray[i2] = new Vector();
        }
        byte[] byArray = ce.a();
        int n2 = 0;
        while (n2 < byArray.length) {
            byte by2 = byArray[n2++];
            ad ad2 = ad.a(byArray, n2, true, false);
            n2 += by2;
            switch (ad2.f) {
                case 7: 
                case 9: 
                case 10: {
                    vectorArray[0].addElement(ad2);
                    break;
                }
                case 0: 
                case 1: 
                case 2: {
                    ((e)ad2).b = true;
                    vectorArray[1].addElement(ad2);
                    break;
                }
                case 3: {
                    ((e)ad2).b = true;
                    vectorArray[2].addElement(ad2);
                    break;
                }
                case 5: {
                    ((e)ad2).b = true;
                    vectorArray[3].addElement(ad2);
                    break;
                }
                case 6: {
                    ((e)ad2).b = true;
                    vectorArray[4].addElement(ad2);
                    break;
                }
                case 4: {
                    ((e)ad2).b = true;
                    vectorArray[5].addElement(ad2);
                }
            }
        }
        for (int i3 = 0; i3 < 6; ++i3) {
            vectorArray[i3].trimToSize();
        }
        return vectorArray;
    }

    public static final ad a(ad ad2, ad ad3, ad ad4) {
        byte by2 = 0;
        if (ad2 != null) {
            ++by2;
        }
        if (ad3 != null) {
            ++by2;
        }
        if (ad4 != null) {
            ++by2;
        }
        byte[] byArray = ce.a("/itm/mixtbl");
        int n2 = 0;
        while (n2 < byArray.length) {
            byte by3;
            byte by4;
            ad[] adArray = new ad[]{ad2, ad3, ad4};
            byte by5 = byArray[n2++];
            boolean bl2 = true;
            for (by4 = 0; by4 < by5; ++by4) {
                by3 = byArray[n2++];
                byte by6 = byArray[n2++];
                boolean bl3 = false;
                for (int i2 = 0; i2 < 3; ++i2) {
                    if (adArray[i2] == null || adArray[i2].f != by3 || adArray[i2].g != by6) continue;
                    bl3 = true;
                    adArray[i2] = null;
                    break;
                }
                if (bl3) continue;
                bl2 = false;
            }
            by4 = byArray[n2++];
            by3 = byArray[n2++];
            if (by5 != by2) {
                bl2 = false;
            }
            if (!bl2) continue;
            return ad.a(by4, by3, true, true);
        }
        return null;
    }

    static {
        b = new boolean[]{false, false, false, false, false, false, false, true, true, true, true, true, true, true, true, true, true, true, false, false, false, false, true, true};
        c = new boolean[]{false, false, false, false, false, false, false, true, false, true, false, false, true, true, true, true, true, false, false, false, false, false, false, false};
    }
}

