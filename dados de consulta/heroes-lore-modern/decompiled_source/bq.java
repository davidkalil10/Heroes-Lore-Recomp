/*
 * Decompiled with CFR 0.152.
 */
public final class bq {
    public static final byte[] a(byte[] byArray, byte[] byArray2) {
        byte[] byArray3 = new byte[byArray.length + 1];
        int n2 = 0;
        int n3 = 0;
        for (int i2 = 0; i2 < byArray.length; ++i2) {
            int n4 = byArray[i2];
            if (++n3 == byArray2.length) {
                n3 = 0;
            }
            n2 += byArray2[n3] & 0xFF;
            byArray3[i2] = (byte)(n4 ^= byArray2[n3]);
        }
        byArray3[byArray.length] = (byte)n2;
        return byArray3;
    }

    public static final byte[] b(byte[] byArray, byte[] byArray2) {
        int n2;
        byte[] byArray3 = new byte[byArray.length + 1];
        int n3 = 0;
        if (byArray.length < 1) {
            return null;
        }
        int n4 = 0;
        try {
            for (n2 = 0; n2 < byArray.length - 1; ++n2) {
                int n5 = byArray[n2];
                if (++n4 == byArray2.length) {
                    n4 = 0;
                }
                n3 += byArray2[n4] & 0xFF;
                byArray3[n2] = (byte)(n5 ^= byArray2[n4]);
            }
        }
        catch (Exception exception) {
            return null;
        }
        if ((n3 & 0xFF) != (byArray[n2] & 0xFF)) {
            return null;
        }
        return byArray3;
    }
}

