/*
 * Decompiled with CFR 0.152.
 */
public final class ca {
    private static final int[] var_int_arr_a;
    private int var_int_a = -1;

    public final void void_a() {
        this.var_int_a = -1;
    }

    public final void a(byte[] byArray, int n2, int n3) {
        for (int i2 = n2; i2 < n3 + n2; ++i2) {
            this.var_int_a = this.var_int_a >>> 8 & 0xFFFFFF ^ var_int_arr_a[(this.var_int_a ^ byArray[i2]) & 0xFF];
        }
    }

    public final int int_a() {
        return ~this.var_int_a;
    }

    static {
        var_int_arr_a = new int[256];
        for (int n2 = 0; n2 < 256; n2 = (int)((short)(n2 + 1))) {
            int n3 = n2;
            for (int n4 = 1; n4 < 9; n4 = (int)((byte)(n4 + 1))) {
                n3 = (n3 & 1) == 1 ? n3 >>> 1 ^ 0xEDB88320 : n3 >>> 1;
            }
            ca.var_int_arr_a[n2] = n3;
        }
    }
}

