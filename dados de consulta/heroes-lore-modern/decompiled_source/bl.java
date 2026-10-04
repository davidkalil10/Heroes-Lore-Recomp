/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 */
import javax.microedition.lcdui.Graphics;
import rpg.GameMIDlet;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public final class bl
extends cb {
    private char[] a;
    private short[] a;

    public bl(cb cb2, boolean bl2) {
        super(cb2, (byte)1);
        GameMIDlet gameMIDlet = GameMIDlet.a;
        String string = bh.a(3927);
        String string2 = bh.a(3928);
        String string3 = gameMIDlet.getAppProperty("MIDlet-Name").toUpperCase();
        String string4 = bh.a(bh.r);
        String string5 = gameMIDlet.getAppProperty("MIDlet-Vendor");
        String string6 = bh.a(bh.b);
        String string7 = bh.a(3905);
        String string8 = string3 + "\n\n" + string7 + "\n\n" + string + '\n' + string5 + '\n' + string6 + "\n\n" + string2 + "\nv." + string4;
        this.a = string8.toCharArray();
        short[] sArray = new short[20];
        int n2 = 0;
        for (int i2 = 0; i2 < this.a.length; i2 += bh.a(this.a, i2, 176, 18)) {
            sArray[n2++] = (short)i2;
        }
        this.a = new short[n2];
        System.arraycopy(sArray, 0, this.a, 0, this.a.length);
        ((cb)this).a = (byte)this.a.length;
        bh.a.b = false;
        bh.b.b = false;
        bh.c.b = false;
    }

    public final boolean a(int n2, int n3) {
        if (this.b(n2, n3)) {
            return true;
        }
        if (this.c(n2, n3)) {
            return true;
        }
        if (n3 == bh.a) {
            ((cb)this).a.a();
            bh.a.b = true;
            bh.b.b = true;
            bh.c.b = true;
            return true;
        }
        return true;
    }

    public final void a(Graphics graphics, int n2, int n3) {
        short s2;
        boolean bl2 = false;
        boolean bl3 = false;
        graphics.setColor(0x3F1F3F);
        graphics.fillRect(0, 0, r.g, r.h);
        bf.c(graphics, n2, n3);
        bh.a(graphics, 9, n2 + 201 >> 1, n3 + 5);
        bf.b(graphics, n2, n3 + 24, 3);
        n2 += 12;
        n3 += 42;
        if (((cb)this).a > 1) {
            if (this.b > 0) {
                graphics.drawImage(ce.l, n2 + 62 + 13, n3 - 6, 20);
            }
            if (this.b < ((cb)this).a - 1) {
                graphics.drawImage(ce.o, n2 + 62 + 13, n3 + 114 + 26 + 40, 20);
            }
        }
        r.d(graphics, n2 + 201 - 25, n3 - 8, this.b + 1, ((cb)this).a);
        short s3 = this.a[this.b];
        short s4 = s2 = this.b == ((cb)this).a - 1 ? (short)this.a.length : this.a[this.b + 1];
        if (this.a[0] == '!' && s3 == 0) {
            s3 = 1;
        }
        graphics.setColor(0);
        bh.b(graphics, n2 + 201 >> 1, (n3 += 25) + 3, 176, 1, this.a, s3, 0, s2 - s3);
        bh.a(graphics, null, bh.e);
    }
}

