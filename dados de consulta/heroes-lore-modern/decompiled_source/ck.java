/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 */
import java.util.Random;
import javax.microedition.lcdui.Graphics;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public abstract class ck
implements u {
    public byte a;
    public byte b;
    public boolean a;
    public boolean b;
    public short c;
    public short d;
    public byte c;
    public byte d;
    public byte e = 1;
    public static Random a = new Random();
    public ck a;
    public ck b;
    public boolean c = false;

    public ck(short s2, short s3, byte by2, byte by3) {
        this.a(s2, s3);
        this.b();
        this.c = by2;
        this.d = by3;
    }

    public void a(short s2, short s3) {
        this.c = s2;
        this.d = s3;
    }

    public final void b() {
        this.b = (byte)(this.d >> 4);
        this.a = (byte)(this.c >> 4);
        this.b = (this.d & 0xF) != 0;
        this.a = (this.c & 0xF) != 0;
    }

    public final ck a(byte by2, byte by3) {
        ae ae2 = n.a;
        switch (by2) {
            case 1: {
                if (this.b - by3 < 0) {
                    return null;
                }
                return ae2.a[this.b - by3][this.a];
            }
            case 2: {
                if (this.b + by3 >= ae2.b) {
                    return null;
                }
                return ae2.a[this.b + by3][this.a];
            }
            case 3: {
                if (this.a - by3 < 0) {
                    return null;
                }
                return ae2.a[this.b][this.a - by3];
            }
            case 4: {
                if (this.a + by3 >= ae2.a) {
                    return null;
                }
                return ae2.a[this.b][this.a + by3];
            }
        }
        return null;
    }

    public abstract void a(Graphics var1, int var2, int var3);
}

