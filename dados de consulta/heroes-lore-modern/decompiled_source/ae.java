/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 *  javax.microedition.lcdui.Image
 */
import java.io.IOException;
import java.util.Vector;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public final class ae
implements u {
    private static final int[] a = new int[]{0xFFDF3F, 0x3F1F3F, 0xFFDF3F, 0x3F1F3F, 0xFFDFBF, 0x7F3F3F, 0xBFFF3F, 0x1F3F3F, 0, 0, 0xBFFF3F, 0x1F3F3F, 0x3FBFFF, 0x1F3F3F, 0xBFFF3F, 0x1F3F3F, 0xBFFF3F, 0x1F3F3F, 0xBFFF3F, 0x1F3F3F, 0, 0, 0xFFDFBF, 0x7F3F3F, 0xDFFFFF, 2047871, 0xDFFFFF, 2047871, 0xFFDFBF, 0x7F3F3F};
    private static byte c = (byte)2;
    private static byte d;
    public byte a;
    public byte b;
    public boolean a;
    public boolean b;
    private byte e;
    public boolean c;
    public int a;
    public int b;
    public int c;
    public int d;
    public byte[][] b;
    public byte[][] c;
    public ck[][] a;
    public ac[] a;
    public aj[] a;
    private aq a;
    private Vector a;
    private Vector b;
    private int g;
    public int e = 0;
    public int f = 0;
    public Object[] a;
    public Object[] b;
    public Object[] c;
    private boolean d;
    private byte[] h;
    public char[] a;

    public static final boolean a(int n2) {
        return n2 == 11 || n2 == 13 || n2 == 15 || n2 == 82;
    }

    public ae(byte by2) {
        this.a = by2;
        this.c = false;
        this.a = ae.a(by2);
        this.b = by2 == 13 || by2 == 15;
        this.a = new aq();
        this.b = new Vector();
        this.g = 16;
        this.a = new Vector();
        if (r.g >= 240 && r.h >= 240) {
            c = (byte)3;
        }
    }

    public final void a() {
        int n2;
        ce.c();
        ce.d();
        ce.e();
        ce.m();
        this.a = null;
        this.b = null;
        this.c = null;
        this.l();
        for (n2 = 0; n2 <= 4; n2 = (int)((byte)(n2 + 1))) {
            bw.b((byte)n2);
        }
        for (n2 = 24; n2 <= 31; n2 = (byte)(n2 + 1)) {
            bw.b((byte)n2);
        }
        r.k();
        this.h = ce.a("/m/" + (n.f < 10 ? "0" : "") + n.f + ".map");
        r.k();
        this.b = this.h[0];
        this.a = this.h[1];
        this.b = this.h[2];
        if (this.b != 1 && this.b != 5 && this.b != 9 && this.b != 15) {
            this.c = true;
        }
        this.a = new ck[this.b][this.a];
        r.k();
        this.c = this.a * 16;
        this.d = this.b * 16;
        r.k();
        this.a(this.h, 3);
        this.h = null;
        if (d != this.b) {
            ce.b();
        }
        System.gc();
        this.h = ce.a("/m/" + n.a + "/" + (n.f < 10 ? "0" : "") + n.f + ".evt");
        r.k();
        r.k();
        this.b(this.h, 0);
        n2 = 0 + this.a * this.b;
        r.k();
        n2 += this.a(this.h, n2);
        r.k();
        n2 += this.b(this.h, n2);
        r.k();
        n2 += this.c(this.h, n2);
        r.k();
        n2 += this.d(this.h, n2);
        r.k();
        n2 += this.e(this.h, n2);
        this.c(this.h, n2);
        this.h = null;
        switch (this.a) {
            case 11: {
                ae.e();
                this.f();
                break;
            }
            case 13: {
                ae.g();
                this.a(true);
                break;
            }
            case 15: {
                ae.h();
                this.i();
                break;
            }
            case 82: {
                ae.j();
                this.k();
            }
        }
        if (ce.e == null) {
            r.k();
            try {
                br br2 = new br("/m/t/t" + (this.b < 10 ? "0" : "") + this.b);
                ce.e = br2.a();
                r.k();
            }
            catch (IOException iOException) {}
        }
        d = this.b;
        this.a = n.a == 8 && this.a == 65 ? ce.d.a(85) : ce.d.a(this.a);
        this.e = this.a != null && this.a.length > 0 ? (byte)10 : (byte)0;
        if (this.a == 79 || this.a == 80 || this.a == 81) {
            bw.a((byte)4);
            bw.a((byte)8);
            bw.b(4);
        } else if (this.b == 1 || this.b == 5 || this.b == 9 || this.b == 15) {
            bw.a((byte)8);
        }
        n.a().c();
    }

    private final void a(byte[] byArray, int n2) {
        this.b = new byte[this.b][this.a];
        r.k();
        for (int i2 = 0; i2 < this.b; ++i2) {
            System.arraycopy(byArray, n2, this.b[i2], 0, this.a);
            n2 += this.a;
        }
        r.k();
    }

    private final void b(byte[] byArray, int n2) {
        this.c = new byte[this.b][this.a];
        r.k();
        for (int i2 = 0; i2 < this.b; ++i2) {
            System.arraycopy(byArray, n2, this.c[i2], 0, this.a);
            n2 += this.a;
        }
        r.k();
    }

    private final int a(byte[] byArray, int n2) {
        int n3;
        int n4;
        Image[] imageArray = null;
        if ((n4 = byArray[n2++] & 0xFF) > 0) {
            Object object;
            try {
                object = new br("/m/t/o" + (this.b < 10 ? "0" : "") + this.b);
                new br("/m/t/o" + (this.b < 10 ? "0" : "") + this.b).a = true;
                r.k();
                imageArray = ce.f = new Image[((br)object).a()];
                r.k();
                for (n3 = 0; n3 < n4; ++n3) {
                    int s2 = byArray[n2++] & 0xFF;
                    imageArray[s2] = ((br)object).a(s2);
                    r.k();
                }
            }
            catch (IOException iOException) {
                object = iOException;
                iOException.printStackTrace();
            }
        }
        r.k();
        int n5 = byArray[n2++] & 0xFF;
        this.a = new aj[n5];
        for (n3 = 0; n3 < n5; ++n3) {
            short s2 = (short)((byArray[n2++] & 0xFF) * 16);
            short s3 = (short)((byArray[n2++] & 0xFF) * 16);
            byte by2 = byArray[n2++];
            byte by3 = byArray[n2++];
            int n6 = byArray[n2++] & 0xFF;
            aj aj2 = new aj(s2, s3, by2, by3, imageArray[n6]);
            this.a.b(aj2);
            this.a.c(aj2);
            this.a[n3] = aj2;
        }
        r.k();
        return 1 + n4 + 1 + n5 * 5;
    }

    private final int b(byte[] byArray, int n2) {
        byte by2;
        int n3;
        int n4;
        Object object;
        byte by3;
        int n5 = 0;
        Image[] imageArray = null;
        int n6 = byArray[n2++] & 0xFF;
        ++n5;
        for (by3 = 0; by3 < 5; by3 = (byte)(by3 + 1)) {
            ce.d(by3);
        }
        ce.l = new byte[5];
        for (by3 = 0; by3 < 5; by3 = (byte)(by3 + 1)) {
            ce.l[by3] = -1;
        }
        try {
            object = new br("/npc/all");
            new br("/npc/all").a = true;
            r.k();
            imageArray = ce.g = new Image[((br)object).a()];
            n4 = 0;
            for (n3 = 0; n3 < n6; n3 = (int)((byte)(n3 + 1))) {
                by2 = byArray[n2++];
                ++n5;
                if (by2 >= 18) {
                    imageArray[by2 - 18] = ((br)object).a(by2 - 18);
                    r.k();
                    continue;
                }
                if (by2 == 3) {
                    ce.l[n4] = by2;
                    ce.a((short)17, (byte)n4, true);
                    n4 = (byte)(n4 + 1);
                    continue;
                }
                if (by2 == 6) {
                    ce.l[n4] = by2;
                    ce.a((short)20, (byte)n4, true);
                    n4 = (byte)(n4 + 1);
                    continue;
                }
                ce.l[n4] = by2;
                r.k();
                ce.b(by2, (byte)n4);
                n4 = (byte)(n4 + 1);
                String cfr_ignored_0 = "Npc Loaded - " + by2;
            }
        }
        catch (IOException iOException) {
            object = iOException;
            iOException.printStackTrace();
        }
        r.k();
        int n7 = byArray[n2++] & 0xFF;
        ++n5;
        this.a = new ac[n7];
        for (n4 = 0; n4 < n7; ++n4) {
            n3 = byArray[n2++];
            by2 = byArray[n2++];
            byte by4 = byArray[n2++];
            byte by5 = -1;
            if (by4 >= 18) {
                by5 = -1;
            } else {
                for (byte by6 = 0; by6 < ce.l.length; by6 = (byte)(by6 + 1)) {
                    if (ce.l[by6] != by4) continue;
                    by5 = by6;
                    break;
                }
                x.a(by5 != -1);
            }
            ac ac2 = new ac((short)(n3 * 16), (short)(by2 * 16), by4, by5);
            this.a.b(ac2);
            this.a.c(ac2);
            ac2.g();
            n5 += 3;
            this.a[n4] = ac2;
        }
        r.k();
        return n5;
    }

    private final int c(byte[] byArray, int n2) {
        int n3;
        byte by2;
        int by3;
        int n4;
        for (n4 = 0; n4 < 5; n4 = (byte)(n4 + 1)) {
            ce.c((byte)n4);
        }
        ce.k = new byte[5];
        for (n4 = 0; n4 < 5; n4 = (byte)(n4 + 1)) {
            ce.k[n4] = -1;
        }
        n4 = 0;
        x.a((by3 = byArray[n2++] & 0xFF) <= 5);
        ++n4;
        r.k();
        byte[] byArray2 = null;
        if (by3 != 0) {
            j.a(5);
            byArray2 = ce.a("/enm/data" + (n.g >= 2 ? 2 : (int)n.g));
            r.k();
        }
        boolean by22 = false;
        while (by2 < by3) {
            n3 = byArray[n2++] & 0xFF;
            ++n4;
            ce.k[by2] = n3;
            j.a(byArray2, (byte)n3, by2);
            r.k();
            ce.a((short)n3, by2, false);
            j.a(by2);
            r.k();
            String cfr_ignored_0 = "Enemy Loaded - " + n3;
            by2 = (byte)(by2 + true);
        }
        r.k();
        int n5 = byArray[n2++] & 0xFF;
        ++n4;
        for (n3 = 0; n3 < n5; ++n3) {
            int n6 = byArray[n2++] & 0xFF;
            int n7 = byArray[n2++] & 0xFF;
            byte by4 = byArray[n2++];
            n4 += 3;
            this.a((int)by4, 0, n6, n7);
            r.k();
        }
        return n4;
    }

    private final int d(byte[] byArray, int n2) {
        int n3 = byArray[n2++];
        try {
            br br2 = new br("/m/face");
            new br("/m/face").a = true;
            ce.h = new Image[br2.a()];
            for (int i2 = 0; i2 < n3; ++i2) {
                byte by2 = byArray[n2++];
                ce.h[by2] = br2.a(by2);
            }
        }
        catch (IOException iOException) {
            IOException iOException2 = iOException;
            iOException.printStackTrace();
        }
        return n3 + 1;
    }

    private final void l() {
        ce.h = null;
    }

    private final int e(byte[] byArray, int n2) {
        int n3;
        int n4;
        int n5 = 0;
        int n6 = byArray[n2++] & 0xFF;
        ++n5;
        this.a = new Object[n6];
        r.k();
        for (n4 = 0; n4 < n6; ++n4) {
            n3 = byArray[n2++] & 0xFF;
            ++n5;
            if (n3 <= 0) continue;
            byte[][] byArray2 = new byte[n3][7];
            for (int i2 = 0; i2 < n3; ++i2) {
                System.arraycopy(byArray, n2, byArray2[i2], 0, 7);
                n2 += 7;
                n5 += 7;
            }
            this.a[n4] = byArray2;
        }
        r.k();
        n4 = byArray[n2++] & 0xFF;
        ++n5;
        this.b = new Object[n4];
        for (n3 = 0; n3 < n4; ++n3) {
            int n7 = byArray[n2++] & 0xFF;
            ++n5;
            if (n7 <= 0) continue;
            byte[][] byArray3 = new byte[n7][3];
            for (int i3 = 0; i3 < n7; ++i3) {
                System.arraycopy(byArray, n2, byArray3[i3], 0, 3);
                n2 += 3;
                n5 += 3;
            }
            this.b[n3] = byArray3;
        }
        r.k();
        n3 = byArray[n2++] & 0xFF;
        ++n5;
        this.c = new Object[n3];
        for (int i4 = 0; i4 < n3; ++i4) {
            int n8 = byArray[n2++] & 0xFF;
            ++n5;
            this.c[i4] = bh.a(new String(byArray, n2, n8));
            n2 += n8;
            n5 += n8;
        }
        r.k();
        return n5;
    }

    private final void c(byte[] byArray, int n2) {
        int n3;
        int n4;
        int n5 = -1;
        int n6 = byArray[n2++];
        for (n4 = 0; n4 < n6; n4 = (int)((byte)(n4 + 1))) {
            n3 = 0;
            n3 = 0 | (byArray[n2++] & 3) << 8;
            if (n.a(n3 |= byArray[n2++]) && n5 == -1) {
                n5 = byArray[n2];
            }
            ++n2;
        }
        n4 = byArray[n2++];
        for (n3 = 0; n3 < n4; n3 = (int)((byte)(n3 + 1))) {
            byte by2 = byArray[n2++];
            if (n5 == n3) {
                for (byte by3 = 0; by3 < by2; by3 = (byte)(by3 + 1)) {
                    byte by4 = byArray[n2++];
                    byte by5 = byArray[n2++];
                    byte by6 = byArray[n2++];
                    byte by7 = byArray[n2++];
                    this.b(by4, by5, by6, by7);
                }
                continue;
            }
            n2 += by2 * 4;
        }
    }

    private final void b(byte by2, byte by3, byte by4, byte by5) {
        switch (by2) {
            case 100: {
                this.b[by4][by3] = by5;
                return;
            }
            case 101: {
                this.c[by4][by3] = by5;
                return;
            }
            case 102: {
                aj aj2 = this.a[by5 & 0xFF];
                aj2.a((short)((by3 & 0xFF) << 4), (short)((by4 & 0xFF) << 4));
                return;
            }
            case 103: {
                aj aj3 = this.a[by3 & 0xFF];
                this.a(aj3);
                this.a[by3 & 0xFF] = null;
                return;
            }
            case 104: {
                ac ac2 = this.a[by5 & 0xFF];
                ac2.a((short)((by3 & 0xFF) << 4), (short)((by4 & 0xFF) << 4));
                return;
            }
            case 105: {
                ac ac3 = this.a[by3 & 0xFF];
                this.a[by3 & 0xFF].d = false;
                ac3.f();
                return;
            }
            case 106: {
                x.a(false);
                return;
            }
            case 107: {
                x.a(false);
                return;
            }
            case 108: {
                return;
            }
            case 109: {
                aj aj4 = this.a[by3 & 0xFF];
                this.a[by3 & 0xFF].a = ce.f[by4 & 0xFF];
                return;
            }
            case 110: {
                ac ac4 = this.a[by3 & 0xFF];
                this.a[by3 & 0xFF].f = by4;
                return;
            }
            case 111: {
                ao ao2 = n.a();
                ao2.c();
                if (by3 == 0) break;
                ao2.a(new aw(10, -1, (short)(by3 - 1)));
                return;
            }
            case 112: {
                ac ac5 = this.a[by3];
                ac5.c();
                if (by4 == 0) break;
                ac5.a(new aw(10, -1, (short)(by4 - 1)));
            }
        }
    }

    public final void a(Graphics graphics) {
        int n2 = bs.a.d ? n.c : n.a;
        int n3 = bs.a.d ? n.d : n.b;
        int n4 = as.a;
        int n5 = as.b;
        if (this.b) {
            n2 = n.a;
            n3 = n.b + 30;
        }
        if (n2 > 0) {
            n2 = 0;
        }
        if (n2 < n4 - this.c) {
            n2 = n4 - this.c;
        }
        if (n3 > 0) {
            n3 = 0;
        }
        if (n3 < n5 - this.d) {
            n3 = n5 - this.d;
        }
        if (n2 > 0) {
            n2 = (n4 - this.c) / 2;
            graphics.setColor(0);
            graphics.fillRect(0, 0, n4, n5);
        }
        if (n3 > 0) {
            n3 = (n5 - this.d) / 2;
            graphics.setColor(0);
            graphics.fillRect(0, 0, n4, n5);
        }
        graphics.setClip(0, 0, n4, n5);
        if (this.e != 0) {
            n2 += this.e;
            this.e = 0;
        }
        if (this.f != 0) {
            n3 += this.f;
            this.f = 0;
        }
        this.a(graphics, n2, n3, n4, n5);
        this.a(graphics, n2, n3);
        this.b(graphics, n2, n3);
        if (this.e > 0) {
            int n6 = n5 / 4;
            if (this.e > 8) {
                graphics.setClip(0, n6 + 4 * (this.e - 8), n4, 8 * (10 - this.e + 1));
            } else if (this.e < 3) {
                graphics.setClip(0, n6 + 4 * (3 - this.e), n4, 8 * this.e);
            } else {
                graphics.setClip(0, 0, r.g, r.h);
            }
            graphics.setColor(0);
            graphics.fillRect(0, n6, n4, 22);
            graphics.setColor(14663551);
            graphics.drawLine(0, n6, n4, n6);
            graphics.drawLine(0, n6 + 21, n4, n6 + 21);
            graphics.setColor(0xFFFFFF);
            bh.a(graphics, n4 / 2, n6 + 12 - 4, this.a, 1);
            this.e = (byte)(this.e - 1);
        }
        graphics.setClip(0, 0, r.g, r.h);
    }

    private final void a(Graphics graphics, int n2, int n3, int n4, int n5) {
        int n6 = -n2 / 16;
        int n7 = -n3 / 16;
        int n8 = (n4 - n2 - 1) / 16;
        int n9 = (n5 - n3 - 1) / 16;
        if (n6 < 0) {
            n6 = 0;
        }
        if (n7 < 0) {
            n7 = 0;
        }
        if (n8 >= this.a) {
            n8 = this.a - 1;
        }
        if (n9 >= this.b) {
            n9 = this.b - 1;
        }
        Image[] imageArray = ce.e;
        for (int i2 = n7; i2 <= n9; ++i2) {
            int n10 = n3 + i2 * 16;
            int n11 = n2 + n6 * 16;
            for (int i3 = n6; i3 <= n8; ++i3) {
                Image image = imageArray[this.b[i2][i3]];
                if (image == null) {
                    if (this.c[i2][i3] < 0) {
                        graphics.setColor(0);
                    } else if (this.c[i2][i3] >= 0) {
                        graphics.setColor(0xFFFFFF);
                    }
                    graphics.fillRect(n11, n10, 16, 16);
                } else {
                    graphics.drawImage(image, n11, n10, 20);
                }
                n11 += 16;
            }
        }
    }

    private final void a(Graphics graphics, int n2, int n3) {
        for (int i2 = 0; i2 < this.a.size(); ++i2) {
            byte[] byArray = (byte[])this.a.elementAt(i2);
            if (byArray[5] <= 16 && byArray[5] % 3 == 0) continue;
            graphics.drawImage(byArray[2] == -1 ? ce.C : ce.B, n2 + (byArray[0] << 4) + 8, n3 + (byArray[1] << 4) + 8, 33);
        }
    }

    private final void b(Graphics graphics, int n2, int n3) {
        ck ck2 = this.a.a;
        while (ck2 != null) {
            ck2.a(graphics, n2, n3);
            ck2 = ck2.a;
        }
    }

    public final void b(Graphics graphics) {
        int n2 = this.a * c;
        int n3 = this.b * c;
        int n4 = r.i - n2 / 2;
        int n5 = r.j - n3 / 2;
        graphics.setColor(0);
        graphics.drawRect(n4 - 1, n5 - 1, n2 + 1, n3 + 1);
        for (int n6 = 0; n6 < this.b; n6 = (int)((byte)(n6 + 1))) {
            for (int n7 = 0; n7 < this.a; n7 = (int)((byte)(n7 + 1))) {
                if (this.c[n6][n7] < 0) {
                    graphics.setColor(a[this.b * 2 + 1]);
                } else if (this.c[n6][n7] >= 0) {
                    graphics.setColor(a[this.b * 2]);
                }
                if (this.c[n6][n7] != 0 && this.c[n6][n7] != -128 && ah.a(this.c[n6][n7] < 0 ? (byte)(-this.c[n6][n7]) : this.c[n6][n7])) {
                    if (this.b == 6) {
                        graphics.setColor(0xFF3FBF);
                    } else {
                        graphics.setColor(0x3F7FFF);
                    }
                }
                graphics.fillRect(n4, n5, (int)c, (int)c);
                n4 += c;
            }
            n5 += c;
            n4 = r.i - n2 / 2;
        }
        if (this.d) {
            ao ao2 = n.a();
            graphics.setColor(0xFF3F3F);
            graphics.fillRect(r.i - n2 / 2 + ((ck)ao2).a * c, r.j - n3 / 2 + ((ck)ao2).b * c, (int)c, (int)c);
        }
        this.d = !this.d;
        graphics.setColor(0);
        graphics.fillRect(0, 0, r.g, 20);
        graphics.setColor(0xFFFFFF);
        bh.a(graphics, r.i, 8, this.a, 1);
    }

    public final void b() {
        this.a(true, (byte)3);
        this.o();
        this.m();
    }

    public final void c() {
        this.n();
    }

    public final void d() {
        this.g = 0;
        this.a(false, (byte)3);
    }

    private final void a(boolean bl2, byte by2) {
        --this.g;
        if (this.g < 0) {
            this.g = 16;
            for (int i2 = this.b.size() - 1; i2 >= 0; --i2) {
                int[] nArray = (int[])this.b.elementAt(i2);
                int[] nArray2 = nArray;
                nArray[0] = nArray[0] - 16;
                if (nArray2[0] >= 0) continue;
                byte by3 = (byte)nArray2[1];
                byte by4 = (byte)nArray2[2];
                byte by5 = (byte)nArray2[3];
                byte by6 = -1;
                for (byte by7 = 0; by7 < ce.k.length; by7 = (byte)((byte)(by7 + 1))) {
                    if (ce.k[by7] != by3) continue;
                    by6 = by7;
                    break;
                }
                if (j.a[by6].a == 2 && by4 >= this.a - 1) {
                    System.out.println("INVALID location for enemy - delayed creation.");
                    this.b.removeElementAt(i2);
                    this.a(nArray2[1], 0, by4 - 1, (int)by5);
                    continue;
                }
                if (this.a(by4, by5, by3, by6, bl2, by2, (byte)5)) {
                    this.b.removeElementAt(i2);
                    continue;
                }
                nArray2[0] = 0;
            }
        }
    }

    public final boolean a(byte by2, byte by3, byte by4, byte by5, boolean bl2, byte by6, byte by7) {
        byte by8 = 1;
        if (j.a[by5].a == 2) {
            by8 = 2;
        }
        if (bl2) {
            by2 = (byte)(by2 + h.a(-by6, (int)by6));
            by3 = (byte)(by3 + h.a(-by6, (int)by6));
        }
        while (!this.a((int)by2, (int)by3, by8) && by7 > 0) {
            by7 = (byte)(by7 - 1);
            by2 = (byte)(by2 + h.a(-by6, (int)by6));
            by3 = (byte)(by3 + h.a(-by6, (int)by6));
        }
        if (by7 > 0) {
            al al2 = new al((short)(by2 << 4), (short)(by3 << 4), by4, by5);
            this.a.b(al2);
            this.a.c(al2);
            al2.a((byte)1);
            al2.b((byte)2);
            return true;
        }
        return false;
    }

    private final void m() {
        ck ck2 = this.a.a;
        while (ck2 != null) {
            if (ck2 instanceof aj) {
                ck2 = ck2.a;
                continue;
            }
            if (ck2 instanceof al && !ck2.c) {
                al al2 = (al)ck2;
                al2.d();
                ck2 = ck2.a;
                this.a.c(al2);
                if (al2.h != 6) continue;
                this.a(al2);
                continue;
            }
            if (ck2 instanceof y && !ck2.c) {
                y y2 = (y)ck2;
                y2.a();
                ck2 = ck2.a;
                this.a.c(y2);
                if (!y2.a()) continue;
                this.a(y2);
                continue;
            }
            if (ck2.c) {
                ck2.c = false;
                ck2 = ck2.a;
                continue;
            }
            ck2 = ck2.a;
        }
    }

    private final void n() {
        ck ck2 = this.a.a;
        while (ck2 != null) {
            if (ck2 instanceof ac && !ck2.c) {
                ac ac2 = (ac)ck2;
                ac2.d();
                ck2 = ck2.a;
                this.a.c(ac2);
                continue;
            }
            if (ck2.c) {
                ck2.c = false;
                ck2 = ck2.a;
                continue;
            }
            ck2 = ck2.a;
        }
    }

    private final void o() {
        for (int i2 = this.a.size() - 1; i2 >= 0; --i2) {
            byte[] byArray = (byte[])this.a.elementAt(i2);
            byte[] byArray2 = byArray;
            byArray[5] = (byte)(byArray[5] - 1);
            if (byArray2[5] >= 0) continue;
            this.a.removeElementAt(i2);
        }
    }

    public final boolean a(int n2, int n3) {
        if (n2 < 0 || n3 < 0 || n2 >= this.a || n3 >= this.b) {
            return false;
        }
        return this.c[n3][n2] >= 0 && this.a[n3][n2] == null;
    }

    public final boolean a(int n2, int n3, byte n4) {
        for (int i2 = 0; i2 < n4; ++i2) {
            if (this.a(n2 + i2, n3)) continue;
            return false;
        }
        return true;
    }

    public final boolean a(o o2, int n2, int n3) {
        for (int i2 = 0; i2 < o2.e; ++i2) {
            if (n2 + i2 < 0 || n3 < 0 || n2 + i2 >= this.a || n3 >= this.b) {
                return false;
            }
            if (this.a(n2 + i2, n3) || this.a[n3][n2 + i2] == o2) continue;
            return false;
        }
        return true;
    }

    public final boolean a(o o2, byte by2) {
        return this.a(o2, ((ck)o2).a + u.a[by2], ((ck)o2).b + u.b[by2]);
    }

    public final void a(ck ck2) {
        if (ck2 instanceof o) {
            ((o)ck2).f();
        }
        this.a.a(ck2);
    }

    public final void b(ck ck2) {
        this.a.b(ck2);
        this.a.c(ck2);
    }

    public final void c(ck ck2) {
        this.a.c(ck2);
    }

    public final void a(int n2, int n3, int n4, int n5) {
        int[] nArray = new int[4];
        int[] nArray2 = nArray;
        nArray[0] = n3;
        nArray2[1] = n2;
        nArray2[2] = n4;
        nArray2[3] = n5;
        this.b.addElement(nArray2);
    }

    public final void a(byte by2, byte by3, byte by4, byte by5) {
        if (by4 == 22) {
            return;
        }
        byte[] byArray = new byte[]{by2, by3, by4, by5, 1, 120};
        this.a.addElement(byArray);
    }

    public final void a(byte by2, byte by3, short s2) {
        byte[] byArray = new byte[]{by2, by3, -1, (byte)(s2 / 100), (byte)(s2 % 100), 120};
        this.a.addElement(byArray);
    }

    public final byte[] a(byte by2, byte by3) {
        int n2;
        byte[] byArray = null;
        for (n2 = 0; n2 < this.a.size(); ++n2) {
            byte[] byArray2 = (byte[])this.a.elementAt(n2);
            if (byArray2[0] != by2 || byArray2[1] != by3) continue;
            if (byArray2[2] == -1) {
                byArray = byArray2;
                break;
            }
            if (byArray2[2] == 22) {
                if (!n.a().b.a(byArray2[2], byArray2[3], (int)byArray2[4])) continue;
                byArray = byArray2;
                break;
            }
            if (!n.a().a.a(byArray2[2], byArray2[3], (int)byArray2[4])) continue;
            byArray = byArray2;
            break;
        }
        if (byArray != null) {
            this.a.removeElementAt(n2);
            return byArray;
        }
        return null;
    }

    public final boolean a(byte by2, byte by3) {
        for (int i2 = 0; i2 < this.a.size(); ++i2) {
            byte[] byArray = (byte[])this.a.elementAt(i2);
            if (byArray[0] != by2 || byArray[1] != by3) continue;
            return true;
        }
        return false;
    }

    public static final void e() {
        j.a(5);
        byte[] byArray = ce.a("/enm/data" + (n.g >= 2 ? 2 : (int)n.g));
        ce.k[0] = 32;
        j.a(byArray, (byte)32, (byte)0);
        ce.e((byte)1);
        j.b((byte)0);
    }

    public final void f() {
        cc cc2 = new cc(10, 9, 32, 0);
        this.b(cc2);
        cc2.a((byte)1);
        cc2.b((byte)2);
    }

    public static final void g() {
        j.a(5);
        byte[] byArray = ce.a("/enm/data" + (n.g >= 2 ? 2 : (int)n.g));
        ce.k[0] = 35;
        ce.k[1] = 36;
        ce.k[2] = 37;
        ce.k[3] = 38;
        ce.k[4] = 4;
        j.a(byArray, (byte)35, (byte)0);
        j.a(byArray, (byte)36, (byte)1);
        j.a(byArray, (byte)37, (byte)2);
        j.a(byArray, (byte)38, (byte)3);
        j.a(byArray, (byte)4, (byte)4);
        ce.e((byte)2);
        ce.a((short)4, (byte)4, false);
        j.b((byte)0);
        j.b((byte)1);
        j.b((byte)2);
        j.b((byte)3);
        j.a((byte)4);
    }

    public final void a(boolean bl2) {
        if (bl2) {
            ar ar2 = new ar(9, 5, 35, 0);
            this.b(ar2);
            ar2.a((byte)1);
            ar2.b((byte)2);
            return;
        }
        ag ag2 = new ag(9, 5, 36, 1);
        this.b(ag2);
        ag2.a((byte)2);
        ag2.b((byte)2);
        bd bd2 = new bd(6, 5, 37, 2);
        this.b(bd2);
        bd2.a((byte)2);
        bd2.b((byte)2);
        cd cd2 = new cd(13, 5, 38, 3);
        this.b(cd2);
        cd2.a((byte)2);
        cd2.b((byte)2);
        cd2.a(ag2, bd2);
        ag2.a(cd2, bd2);
    }

    public static final void h() {
        j.a(5);
        byte[] byArray = ce.a("/enm/data" + (n.g >= 2 ? 2 : (int)n.g));
        ce.k[0] = 39;
        ce.k[1] = 40;
        ce.k[2] = 41;
        j.a(byArray, (byte)39, (byte)0);
        j.a(byArray, (byte)40, (byte)1);
        j.a(byArray, (byte)41, (byte)2);
        ce.e((byte)3);
        j.b((byte)0);
        j.b((byte)1);
        j.b((byte)2);
    }

    public final void i() {
        ba ba2 = new ba(this, 0, 7, 40, 1);
        this.b(ba2);
        ba2.a((byte)1);
        ba2.b((byte)1);
        ak ak2 = new ak(this, 13, 7, 41, 2);
        this.b(ak2);
        ak2.a((byte)1);
        ak2.b((byte)1);
        cg cg2 = new cg(7, 7, 39, 0, ba2, ak2);
        this.b(cg2);
        cg2.a((byte)2);
        cg2.b((byte)1);
    }

    public static final void j() {
        byte[] byArray = ce.a("/enm/data" + (n.g >= 2 ? 2 : (int)n.g));
        ce.k[1] = 42;
        j.a(byArray, (byte)42, (byte)1);
        ce.e((byte)4);
        j.b((byte)1);
    }

    public final void k() {
        bv bv2 = new bv(this, 7, 10, 42, 1);
        this.b(bv2);
        bv2.a((byte)1);
        bv2.b((byte)2);
    }

    static {
        String[] stringArray = new String[]{"SET_TILE", "SET_COLI", "OBJ_XY  ", "OBJ_DEL ", "NPC_XY  ", "NPC_DEL ", "ENM_XY  ", "ENM_DEL ", "END     ", "OBJ_NUM ", "NPC_NUM ", "EMO_HERO", "EMO_NPC "};
        d = (byte)-1;
    }
}

