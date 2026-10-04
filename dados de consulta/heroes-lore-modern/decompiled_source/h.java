/*
 * Decompiled with CFR 0.152.
 */
import java.util.Random;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public final class h {
    public static Random a = new Random();

    public static final int a(int n2, int n3) {
        x.a(n2 <= n3);
        int n4 = n3 - n2 + 1;
        if (n4 == 0) {
            return 0;
        }
        int n5 = Math.abs(a.nextInt()) % n4;
        return n2 + n5;
    }

    public static final short a(byte[] byArray, int n2) {
        if (byArray.length - 2 < n2) {
            throw new ArrayIndexOutOfBoundsException();
        }
        short s2 = 0;
        s2 = (short)(0 | (byArray[n2] & 0xFF) << 8);
        s2 = (short)(s2 | byArray[n2 + 1] & 0xFF);
        return s2;
    }

    public static final char[] a(char[] cArray, char[] cArray2) {
        char[] cArray3 = new char[cArray.length + cArray2.length];
        System.arraycopy(cArray, 0, cArray3, 0, cArray.length);
        System.arraycopy(cArray2, 0, cArray3, cArray.length, cArray2.length);
        return cArray3;
    }

    public static final int a(byte[] byArray, int n2) {
        int n3 = 0;
        if (byArray.length < n2 + 4) {
            return -1;
        }
        n3 = (byArray[n2] & 0xFF) << 24 | (byArray[n2 + 1] & 0xFF) << 16 | (byArray[n2 + 2] & 0xFF) << 8 | byArray[n2 + 3] & 0xFF;
        return n3;
    }

    public static final void a(int n2, byte[] byArray, int n3) {
        byte[] byArray2 = new byte[]{0, 0, 0, 0};
        int n4 = n2 & 0xFFFFFFFF;
        byArray2[0] = (byte)(n4 >> 24 & 0xFF);
        byArray2[1] = (byte)(n4 >> 16 & 0xFF);
        byArray2[2] = (byte)(n4 >> 8 & 0xFF);
        byArray2[3] = (byte)(n4 & 0xFF);
        System.arraycopy(byArray2, 0, byArray, n3, 4);
    }
}

