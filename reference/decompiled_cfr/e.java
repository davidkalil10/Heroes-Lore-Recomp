/*
 * Decompiled with CFR 0.152.
 */
public class e
extends ad {
    public short var_short_a;
    public byte d;
    public boolean var_boolean_a;
    public boolean b;
    public byte e;
    public byte[] j = new byte[4];

    public e(byte by2, byte by3) {
        super(by2, by3);
    }

    public int a(boolean bl2, byte[] byArray, int n2) {
        n2 = super.a(bl2, byArray, n2);
        n2 += this.a(byArray, n2, bl2);
        return n2;
    }

    public final int a(byte[] byArray, int n2, boolean bl2) {
        this.var_short_a = (short)(byArray[n2++] & 0xFF);
        this.d = byArray[n2++];
        this.var_boolean_a = byArray[n2++] != 0;
        boolean bl3 = false;
        for (int i2 = 1; i2 <= 8; ++i2) {
            if (byArray[n2 + i2] == 0) continue;
            bl3 = true;
            break;
        }
        if (!bl3) {
            this.b = true;
        }
        if (bl2 && bl3) {
            byte[] byArray2 = new byte[]{byArray[n2 + 1], byArray[n2 + 3], byArray[n2 + 5], byArray[n2 + 7]};
            byte[] byArray3 = new byte[]{byArray[n2 + 2], byArray[n2 + 4], byArray[n2 + 6], byArray[n2 + 8]};
            this.a(byArray[n2], byArray2, byArray3, (byte)0);
        }
        return 12;
    }

    public byte[] byte_arr_a() {
        byte[] byArray = super.byte_arr_a();
        byte[] byArray2 = byArray;
        byArray[3] = this.b ? (byte)1 : 0;
        byArray2[4] = this.e;
        System.arraycopy(this.j, 0, byArray2, 5, 4);
        return byArray2;
    }

    public final void a(byte n2, byte[] byArray, byte[] byArray2, byte by2) {
        for (int i2 = 0; i2 < n2; ++i2) {
            int n3;
            while (this.j[n3 = h.a(0, 3)] != 0 || byArray[n3] == 0 && byArray2[n3] == 0) {
            }
            this.j[n3] = (byte)h.a(byArray[n3], (int)byArray2[n3]);
        }
        this.e = by2;
    }

    public final void a(byte by2, byte by3, byte by4, byte by5) {
        this.j[0] = by2;
        this.j[1] = by3;
        this.j[2] = by4;
        this.j[3] = by5;
    }
}

