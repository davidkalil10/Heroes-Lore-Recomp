/*
 * Decompiled with CFR 0.152.
 */
public class t
extends e
implements u {
    public static final byte[] h = new byte[]{0, 1, -1, -1, -1, 4, 3, 2, -1};
    public static z a;
    public static final byte[] i;
    public byte c;

    public t(byte by2, byte by3) {
        super(by2, by3);
    }

    public int a(boolean bl2, byte[] byArray, int n2) {
        n2 = super.a(bl2, byArray, n2);
        this.c = byArray[n2++];
        return n2;
    }

    public final byte[] a() {
        byte[] byArray = super.a();
        byte[] byArray2 = byArray;
        byArray[9] = this.c;
        return byArray2;
    }

    static {
        i = new byte[]{20, 16, 6, 13, 13, 10, 10, 10, 10};
    }
}

