/*
 * Decompiled with CFR 0.152.
 */
public final class l
extends t {
    public byte a;
    public byte b;

    public l(byte by2, byte by3) {
        super(by2, by3);
    }

    public final int a(boolean bl2, byte[] byArray, int n2) {
        n2 += this.a(byArray, n2);
        n2 += this.b(byArray, n2);
        n2 += this.c(byArray, n2);
        n2 += this.a(byArray, n2, bl2);
        this.a = byArray[n2++];
        this.b = byArray[n2++];
        this.c = byArray[n2++];
        return n2;
    }
}

