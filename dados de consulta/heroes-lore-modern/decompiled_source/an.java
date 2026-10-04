/*
 * Decompiled with CFR 0.152.
 */
/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public final class an {
    private int a = 1;

    public final void a(byte[] byArray, int n2, int n3) {
        int n4 = this.a & 0xFFFF;
        int n5 = this.a >> 16 & 0xFFFF;
        while (n3 > 0) {
            int n6 = n3 < 5552 ? n3 : 5552;
            n3 -= n6;
            while (n6-- > 0) {
                n5 += (n4 += byArray[n2++] & 0xFF);
            }
            n4 %= 65521;
            n5 %= 65521;
        }
        this.a = n5 << 16 | n4;
    }

    public final void a() {
        this.a = 1;
    }

    public final long a() {
        return (long)this.a & 0xFFFFFFFFFFFFFFFFL;
    }
}

