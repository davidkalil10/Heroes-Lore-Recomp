/*
 * Decompiled with CFR 0.152.
 */
import java.io.IOException;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public final class z {
    private int[] a;
    public short a;

    public z(String string) throws IOException {
        byte[] byArray = ce.a(string + ".tdf");
        int n2 = 0;
        ++n2;
        this.a = (short)(byArray[0] & 0xFF);
        this.a = new int[this.a];
        for (int i2 = 0; i2 < this.a; ++i2) {
            int n3 = (byArray[n2++] & 0xFF) << 8;
            String string2 = new String(byArray, n2, n3 += byArray[n2++] & 0xFF);
            this.a[i2] = Integer.parseInt(string2.trim());
            n2 += n3;
        }
    }

    public final char[] a(int n2) {
        return cj.a.a(this.a[n2]).replace(';', '\n').toCharArray();
    }
}

