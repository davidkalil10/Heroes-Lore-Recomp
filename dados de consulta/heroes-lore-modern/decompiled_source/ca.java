/*
 * Decompiled with CFR 0.152.
 */
/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public final class ca {
    private static final int[] a = new int[256];
    private int a = -1;

    public final void a() {
        this.a = -1;
    }

    public final void a(byte[] byArray, int n2, int n3) {
        for (int i2 = n2; i2 < n3 + n2; ++i2) {
            this.a = this.a >>> 8 & 0xFFFFFF ^ a[(this.a ^ byArray[i2]) & 0xFF];
        }
    }

    public final int a() {
        return ~this.a;
    }

    static {
        for (int n2 = 0; n2 < 256; n2 = (int)((short)(n2 + 1))) {
            int n3 = n2;
            for (int n4 = 1; n4 < 9; n4 = (int)((byte)(n4 + 1))) {
                n3 = (n3 & 1) == 1 ? n3 >>> 1 ^ 0xEDB88320 : n3 >>> 1;
            }
            ca.a[n2] = n3;
        }
    }
}

