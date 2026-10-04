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

public final class ae
implements u {
    private static final int[] var_int_arr_a;
    private static byte var_byte_c;
    private static byte var_byte_d;
    public byte var_byte_a;
    public byte var_byte_b;
    public boolean var_boolean_a;
    public boolean var_boolean_b;
    private byte var_byte_e;
    public boolean var_boolean_c;
    public int var_int_a;
    public int var_int_b;
    public int var_int_c;
    public int var_int_d;
    public byte[][] var_byte_arr_arr_b;
    public byte[][] var_byte_arr_arr_c;
    public ck[][] var_ck_arr_arr_a;
    public ac[] var_ac_arr_a;
    public aj[] var_aj_arr_a;
    private aq var_aq_a;
    private Vector var_java_util_Vector_a;
    private Vector var_java_util_Vector_b;
    private int g;
    public int var_int_e = 0;
    public int f = 0;
    public Object[] var_java_lang_Object_arr_a;
    public Object[] var_java_lang_Object_arr_b;
    public Object[] var_java_lang_Object_arr_c;
    private boolean var_boolean_d;
    private byte[] h;
    public char[] var_char_arr_a;

    public static final boolean a(int n2) {
        return n2 == 11 || n2 == 13 || n2 == 15 || n2 == 82;
    }

    public ae(byte by2) {
        this.var_byte_a = by2;
        this.var_boolean_c = false;
        this.var_boolean_a = ae.a(by2);
        this.var_boolean_b = by2 == 13 || by2 == 15;
        this.var_aq_a = new aq();
        this.var_java_util_Vector_b = new Vector();
        this.g = 16;
        this.var_java_util_Vector_a = new Vector();
        if (r.g >= 240 && r.h >= 240) {
            var_byte_c = (byte)3;
        }
    }

    public final void a() {
        int n2;
        ce.c();
        ce.d();
        ce.e();
        ce.m();
        this.var_java_lang_Object_arr_a = null;
        this.var_java_lang_Object_arr_b = null;
        this.var_java_lang_Object_arr_c = null;
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
        this.var_byte_b = this.h[0];
        this.var_int_a = this.h[1];
        this.var_int_b = this.h[2];
        if (this.var_byte_b != 1 && this.var_byte_b != 5 && this.var_byte_b != 9 && this.var_byte_b != 15) {
            this.var_boolean_c = true;
        }
        this.var_ck_arr_arr_a = new ck[this.var_int_b][this.var_int_a];
        r.k();
        this.var_int_c = this.var_int_a * 16;
        this.var_int_d = this.var_int_b * 16;
        r.k();
        this.void_a(this.h, 3);
        this.h = null;
        if (var_byte_d != this.var_byte_b) {
            ce.b();
        }
        System.gc();
        this.h = ce.a("/m/" + n.var_byte_a + "/" + (n.f < 10 ? "0" : "") + n.f + ".evt");
        r.k();
        r.k();
        this.void_b(this.h, 0);
        n2 = 0 + this.var_int_a * this.var_int_b;
        r.k();
        n2 += this.int_a(this.h, n2);
        r.k();
        n2 += this.int_b(this.h, n2);
        r.k();
        n2 += this.int_c(this.h, n2);
        r.k();
        n2 += this.d(this.h, n2);
        r.k();
        n2 += this.e(this.h, n2);
        this.void_c(this.h, n2);
        this.h = null;
        switch (this.var_byte_a) {
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
        if (ce.var_javax_microedition_lcdui_Image_arr_e == null) {
            r.k();
            try {
                br br2 = new br("/m/t/t" + (this.var_byte_b < 10 ? "0" : "") + this.var_byte_b);
                ce.var_javax_microedition_lcdui_Image_arr_e = br2.javax_microedition_lcdui_Image_arr_a();
                r.k();
            }
            catch (IOException iOException) {}
        }
        var_byte_d = this.var_byte_b;
        this.var_char_arr_a = n.var_byte_a == 8 && this.var_byte_a == 65 ? ce.var_z_d.a(85) : ce.var_z_d.a(this.var_byte_a);
        this.var_byte_e = this.var_char_arr_a != null && this.var_char_arr_a.length > 0 ? (byte)10 : (byte)0;
        if (this.var_byte_a == 79 || this.var_byte_a == 80 || this.var_byte_a == 81) {
            bw.a((byte)4);
            bw.a((byte)8);
            bw.b(4);
        } else if (this.var_byte_b == 1 || this.var_byte_b == 5 || this.var_byte_b == 9 || this.var_byte_b == 15) {
            bw.a((byte)8);
        }
        n.ao_a().void_c();
    }

    private final void void_a(byte[] byArray, int n2) {
        this.var_byte_arr_arr_b = new byte[this.var_int_b][this.var_int_a];
        r.k();
        for (int i2 = 0; i2 < this.var_int_b; ++i2) {
            System.arraycopy(byArray, n2, this.var_byte_arr_arr_b[i2], 0, this.var_int_a);
            n2 += this.var_int_a;
        }
        r.k();
    }

    private final void void_b(byte[] byArray, int n2) {
        this.var_byte_arr_arr_c = new byte[this.var_int_b][this.var_int_a];
        r.k();
        for (int i2 = 0; i2 < this.var_int_b; ++i2) {
            System.arraycopy(byArray, n2, this.var_byte_arr_arr_c[i2], 0, this.var_int_a);
            n2 += this.var_int_a;
        }
        r.k();
    }

    private final int int_a(byte[] byArray, int n2) {
        int n3;
        int n4;
        Image[] imageArray = null;
        if ((n4 = byArray[n2++] & 0xFF) > 0) {
            Object object;
            try {
                object = new br("/m/t/o" + (this.var_byte_b < 10 ? "0" : "") + this.var_byte_b);
                new br("/m/t/o" + (this.var_byte_b < 10 ? "0" : "") + this.var_byte_b).var_boolean_a = true;
                r.k();
                imageArray = ce.var_javax_microedition_lcdui_Image_arr_f = new Image[((br)object).int_a()];
                r.k();
                for (n3 = 0; n3 < n4; ++n3) {
                    int s2 = byArray[n2++] & 0xFF;
                    imageArray[s2] = ((br)object).javax_microedition_lcdui_Image_a(s2);
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
        this.var_aj_arr_a = new aj[n5];
        for (n3 = 0; n3 < n5; ++n3) {
            short s2 = (short)((byArray[n2++] & 0xFF) * 16);
            short s3 = (short)((byArray[n2++] & 0xFF) * 16);
            byte by2 = byArray[n2++];
            byte by3 = byArray[n2++];
            int n6 = byArray[n2++] & 0xFF;
            aj aj2 = new aj(s2, s3, by2, by3, imageArray[n6]);
            this.var_aq_a.b(aj2);
            this.var_aq_a.c(aj2);
            this.var_aj_arr_a[n3] = aj2;
        }
        r.k();
        return 1 + n4 + 1 + n5 * 5;
    }

    private final int int_b(byte[] byArray, int n2) {
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
        ce.var_byte_arr_l = new byte[5];
        for (by3 = 0; by3 < 5; by3 = (byte)(by3 + 1)) {
            ce.var_byte_arr_l[by3] = -1;
        }
        try {
            object = new br("/npc/all");
            new br("/npc/all").var_boolean_a = true;
            r.k();
            imageArray = ce.var_javax_microedition_lcdui_Image_arr_g = new Image[((br)object).int_a()];
            n4 = 0;
            for (n3 = 0; n3 < n6; n3 = (int)((byte)(n3 + 1))) {
                by2 = byArray[n2++];
                ++n5;
                if (by2 >= 18) {
                    imageArray[by2 - 18] = ((br)object).javax_microedition_lcdui_Image_a(by2 - 18);
                    r.k();
                    continue;
                }
                if (by2 == 3) {
                    ce.var_byte_arr_l[n4] = by2;
                    ce.a((short)17, (byte)n4, true);
                    n4 = (byte)(n4 + 1);
                    continue;
                }
                if (by2 == 6) {
                    ce.var_byte_arr_l[n4] = by2;
                    ce.a((short)20, (byte)n4, true);
                    n4 = (byte)(n4 + 1);
                    continue;
                }
                ce.var_byte_arr_l[n4] = by2;
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
        this.var_ac_arr_a = new ac[n7];
        for (n4 = 0; n4 < n7; ++n4) {
            n3 = byArray[n2++];
            by2 = byArray[n2++];
            byte by4 = byArray[n2++];
            byte by5 = -1;
            if (by4 >= 18) {
                by5 = -1;
            } else {
                for (byte by6 = 0; by6 < ce.var_byte_arr_l.length; by6 = (byte)(by6 + 1)) {
                    if (ce.var_byte_arr_l[by6] != by4) continue;
                    by5 = by6;
                    break;
                }
                x.a(by5 != -1);
            }
            ac ac2 = new ac((short)(n3 * 16), (short)(by2 * 16), by4, by5);
            this.var_aq_a.b(ac2);
            this.var_aq_a.c(ac2);
            ac2.g();
            n5 += 3;
            this.var_ac_arr_a[n4] = ac2;
        }
        r.k();
        return n5;
    }

    private final int int_c(byte[] byArray, int n2) {
        int n3;
        byte by2;
        int by3;
        int n4;
        for (n4 = 0; n4 < 5; n4 = (byte)(n4 + 1)) {
            ce.c((byte)n4);
        }
        ce.var_byte_arr_k = new byte[5];
        for (n4 = 0; n4 < 5; n4 = (byte)(n4 + 1)) {
            ce.var_byte_arr_k[n4] = -1;
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
            ce.var_byte_arr_k[by2] = n3;
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
            new br("/m/face").var_boolean_a = true;
            ce.var_javax_microedition_lcdui_Image_arr_h = new Image[br2.int_a()];
            for (int i2 = 0; i2 < n3; ++i2) {
                byte by2 = byArray[n2++];
                ce.var_javax_microedition_lcdui_Image_arr_h[by2] = br2.javax_microedition_lcdui_Image_a(by2);
            }
        }
        catch (IOException iOException) {
            IOException iOException2 = iOException;
            iOException.printStackTrace();
        }
        return n3 + 1;
    }

    private final void l() {
        ce.var_javax_microedition_lcdui_Image_arr_h = null;
    }

    private final int e(byte[] byArray, int n2) {
        int n3;
        int n4;
        int n5 = 0;
        int n6 = byArray[n2++] & 0xFF;
        ++n5;
        this.var_java_lang_Object_arr_a = new Object[n6];
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
            this.var_java_lang_Object_arr_a[n4] = byArray2;
        }
        r.k();
        n4 = byArray[n2++] & 0xFF;
        ++n5;
        this.var_java_lang_Object_arr_b = new Object[n4];
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
            this.var_java_lang_Object_arr_b[n3] = byArray3;
        }
        r.k();
        n3 = byArray[n2++] & 0xFF;
        ++n5;
        this.var_java_lang_Object_arr_c = new Object[n3];
        for (int i4 = 0; i4 < n3; ++i4) {
            int n8 = byArray[n2++] & 0xFF;
            ++n5;
            this.var_java_lang_Object_arr_c[i4] = bh.char_arr_a(new String(byArray, n2, n8));
            n2 += n8;
            n5 += n8;
        }
        r.k();
        return n5;
    }

    private final void void_c(byte[] byArray, int n2) {
        int n3;
        int n4;
        int n5 = -1;
        int n6 = byArray[n2++];
        for (n4 = 0; n4 < n6; n4 = (int)((byte)(n4 + 1))) {
            n3 = 0;
            n3 = 0 | (byArray[n2++] & 3) << 8;
            if (n.boolean_a(n3 |= byArray[n2++]) && n5 == -1) {
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
                this.var_byte_arr_arr_b[by4][by3] = by5;
                return;
            }
            case 101: {
                this.var_byte_arr_arr_c[by4][by3] = by5;
                return;
            }
            case 102: {
                aj aj2 = this.var_aj_arr_a[by5 & 0xFF];
                aj2.a((short)((by3 & 0xFF) << 4), (short)((by4 & 0xFF) << 4));
                return;
            }
            case 103: {
                aj aj3 = this.var_aj_arr_a[by3 & 0xFF];
                this.a(aj3);
                this.var_aj_arr_a[by3 & 0xFF] = null;
                return;
            }
            case 104: {
                ac ac2 = this.var_ac_arr_a[by5 & 0xFF];
                ac2.a((short)((by3 & 0xFF) << 4), (short)((by4 & 0xFF) << 4));
                return;
            }
            case 105: {
                ac ac3 = this.var_ac_arr_a[by3 & 0xFF];
                this.var_ac_arr_a[by3 & 0xFF].d = false;
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
                aj aj4 = this.var_aj_arr_a[by3 & 0xFF];
                this.var_aj_arr_a[by3 & 0xFF].var_javax_microedition_lcdui_Image_a = ce.var_javax_microedition_lcdui_Image_arr_f[by4 & 0xFF];
                return;
            }
            case 110: {
                ac ac4 = this.var_ac_arr_a[by3 & 0xFF];
                this.var_ac_arr_a[by3 & 0xFF].f = by4;
                return;
            }
            case 111: {
                ao ao2 = n.ao_a();
                ao2.void_c();
                if (by3 == 0) break;
                ao2.a(new aw(10, -1, (short)(by3 - 1)));
                return;
            }
            case 112: {
                ac ac5 = this.var_ac_arr_a[by3];
                ac5.void_c();
                if (by4 == 0) break;
                ac5.a(new aw(10, -1, (short)(by4 - 1)));
            }
        }
    }

    public final void a(Graphics graphics) {
        int n2 = bs.var_bs_a.var_boolean_d ? n.var_int_c : n.var_int_a;
        int n3 = bs.var_bs_a.var_boolean_d ? n.var_int_d : n.var_int_b;
        int n4 = as.var_int_a;
        int n5 = as.b;
        if (this.var_boolean_b) {
            n2 = n.var_int_a;
            n3 = n.var_int_b + 30;
        }
        if (n2 > 0) {
            n2 = 0;
        }
        if (n2 < n4 - this.var_int_c) {
            n2 = n4 - this.var_int_c;
        }
        if (n3 > 0) {
            n3 = 0;
        }
        if (n3 < n5 - this.var_int_d) {
            n3 = n5 - this.var_int_d;
        }
        if (n2 > 0) {
            n2 = (n4 - this.var_int_c) / 2;
            graphics.setColor(0);
            graphics.fillRect(0, 0, n4, n5);
        }
        if (n3 > 0) {
            n3 = (n5 - this.var_int_d) / 2;
            graphics.setColor(0);
            graphics.fillRect(0, 0, n4, n5);
        }
        graphics.setClip(0, 0, n4, n5);
        if (this.var_int_e != 0) {
            n2 += this.var_int_e;
            this.var_int_e = 0;
        }
        if (this.f != 0) {
            n3 += this.f;
            this.f = 0;
        }
        this.a(graphics, n2, n3, n4, n5);
        this.a(graphics, n2, n3);
        this.b(graphics, n2, n3);
        if (this.var_byte_e > 0) {
            int n6 = n5 / 4;
            if (this.var_byte_e > 8) {
                graphics.setClip(0, n6 + 4 * (this.var_byte_e - 8), n4, 8 * (10 - this.var_byte_e + 1));
            } else if (this.var_byte_e < 3) {
                graphics.setClip(0, n6 + 4 * (3 - this.var_byte_e), n4, 8 * this.var_byte_e);
            } else {
                graphics.setClip(0, 0, r.g, r.h);
            }
            graphics.setColor(0);
            graphics.fillRect(0, n6, n4, 22);
            graphics.setColor(14663551);
            graphics.drawLine(0, n6, n4, n6);
            graphics.drawLine(0, n6 + 21, n4, n6 + 21);
            graphics.setColor(0xFFFFFF);
            bh.void_a(graphics, n4 / 2, n6 + 12 - 4, this.var_char_arr_a, 1);
            this.var_byte_e = (byte)(this.var_byte_e - 1);
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
        if (n8 >= this.var_int_a) {
            n8 = this.var_int_a - 1;
        }
        if (n9 >= this.var_int_b) {
            n9 = this.var_int_b - 1;
        }
        Image[] imageArray = ce.var_javax_microedition_lcdui_Image_arr_e;
        for (int i2 = n7; i2 <= n9; ++i2) {
            int n10 = n3 + i2 * 16;
            int n11 = n2 + n6 * 16;
            for (int i3 = n6; i3 <= n8; ++i3) {
                Image image = imageArray[this.var_byte_arr_arr_b[i2][i3]];
                if (image == null) {
                    if (this.var_byte_arr_arr_c[i2][i3] < 0) {
                        graphics.setColor(0);
                    } else if (this.var_byte_arr_arr_c[i2][i3] >= 0) {
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
        for (int i2 = 0; i2 < this.var_java_util_Vector_a.size(); ++i2) {
            byte[] byArray = (byte[])this.var_java_util_Vector_a.elementAt(i2);
            if (byArray[5] <= 16 && byArray[5] % 3 == 0) continue;
            graphics.drawImage(byArray[2] == -1 ? ce.C : ce.B, n2 + (byArray[0] << 4) + 8, n3 + (byArray[1] << 4) + 8, 33);
        }
    }

    private final void b(Graphics graphics, int n2, int n3) {
        ck ck2 = this.var_aq_a.var_ck_a;
        while (ck2 != null) {
            ck2.a(graphics, n2, n3);
            ck2 = ck2.var_ck_a;
        }
    }

    public final void b(Graphics graphics) {
        int n2 = this.var_int_a * var_byte_c;
        int n3 = this.var_int_b * var_byte_c;
        int n4 = r.i - n2 / 2;
        int n5 = r.j - n3 / 2;
        graphics.setColor(0);
        graphics.drawRect(n4 - 1, n5 - 1, n2 + 1, n3 + 1);
        for (int n6 = 0; n6 < this.var_int_b; n6 = (int)((byte)(n6 + 1))) {
            for (int n7 = 0; n7 < this.var_int_a; n7 = (int)((byte)(n7 + 1))) {
                if (this.var_byte_arr_arr_c[n6][n7] < 0) {
                    graphics.setColor(var_int_arr_a[this.var_byte_b * 2 + 1]);
                } else if (this.var_byte_arr_arr_c[n6][n7] >= 0) {
                    graphics.setColor(var_int_arr_a[this.var_byte_b * 2]);
                }
                if (this.var_byte_arr_arr_c[n6][n7] != 0 && this.var_byte_arr_arr_c[n6][n7] != -128 && ah.boolean_a(this.var_byte_arr_arr_c[n6][n7] < 0 ? (byte)(-this.var_byte_arr_arr_c[n6][n7]) : this.var_byte_arr_arr_c[n6][n7])) {
                    if (this.var_byte_b == 6) {
                        graphics.setColor(0xFF3FBF);
                    } else {
                        graphics.setColor(0x3F7FFF);
                    }
                }
                graphics.fillRect(n4, n5, (int)var_byte_c, (int)var_byte_c);
                n4 += var_byte_c;
            }
            n5 += var_byte_c;
            n4 = r.i - n2 / 2;
        }
        if (this.var_boolean_d) {
            ao ao2 = n.ao_a();
            graphics.setColor(0xFF3F3F);
            graphics.fillRect(r.i - n2 / 2 + ao2.var_byte_a * var_byte_c, r.j - n3 / 2 + ao2.var_byte_b * var_byte_c, (int)var_byte_c, (int)var_byte_c);
        }
        this.var_boolean_d = !this.var_boolean_d;
        graphics.setColor(0);
        graphics.fillRect(0, 0, r.g, 20);
        graphics.setColor(0xFFFFFF);
        bh.void_a(graphics, r.i, 8, this.var_char_arr_a, 1);
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
            for (int i2 = this.var_java_util_Vector_b.size() - 1; i2 >= 0; --i2) {
                int[] nArray = (int[])this.var_java_util_Vector_b.elementAt(i2);
                int[] nArray2 = nArray;
                nArray[0] = nArray[0] - 16;
                if (nArray2[0] >= 0) continue;
                byte by3 = (byte)nArray2[1];
                byte by4 = (byte)nArray2[2];
                byte by5 = (byte)nArray2[3];
                byte by6 = -1;
                for (byte by7 = 0; by7 < ce.var_byte_arr_k.length; by7 = (byte)((byte)(by7 + 1))) {
                    if (ce.var_byte_arr_k[by7] != by3) continue;
                    by6 = by7;
                    break;
                }
                if (j.var_j_arr_a[by6].var_byte_a == 2 && by4 >= this.var_int_a - 1) {
                    System.out.println("INVALID location for enemy - delayed creation.");
                    this.var_java_util_Vector_b.removeElementAt(i2);
                    this.a(nArray2[1], 0, by4 - 1, (int)by5);
                    continue;
                }
                if (this.a(by4, by5, by3, by6, bl2, by2, (byte)5)) {
                    this.var_java_util_Vector_b.removeElementAt(i2);
                    continue;
                }
                nArray2[0] = 0;
            }
        }
    }

    public final boolean a(byte by2, byte by3, byte by4, byte by5, boolean bl2, byte by6, byte by7) {
        byte by8 = 1;
        if (j.var_j_arr_a[by5].var_byte_a == 2) {
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
            this.var_aq_a.b(al2);
            this.var_aq_a.c(al2);
            al2.void_a((byte)1);
            al2.b((byte)2);
            return true;
        }
        return false;
    }

    private final void m() {
        ck ck2 = this.var_aq_a.var_ck_a;
        while (ck2 != null) {
            if (ck2 instanceof aj) {
                ck2 = ck2.var_ck_a;
                continue;
            }
            if (ck2 instanceof al && !ck2.var_boolean_c) {
                al al2 = (al)ck2;
                al2.void_d();
                ck2 = ck2.var_ck_a;
                this.var_aq_a.c(al2);
                if (al2.h != 6) continue;
                this.a(al2);
                continue;
            }
            if (ck2 instanceof y && !ck2.var_boolean_c) {
                y y2 = (y)ck2;
                y2.void_a();
                ck2 = ck2.var_ck_a;
                this.var_aq_a.c(y2);
                if (!y2.boolean_a()) continue;
                this.a(y2);
                continue;
            }
            if (ck2.var_boolean_c) {
                ck2.var_boolean_c = false;
                ck2 = ck2.var_ck_a;
                continue;
            }
            ck2 = ck2.var_ck_a;
        }
    }

    private final void n() {
        ck ck2 = this.var_aq_a.var_ck_a;
        while (ck2 != null) {
            if (ck2 instanceof ac && !ck2.var_boolean_c) {
                ac ac2 = (ac)ck2;
                ac2.void_d();
                ck2 = ck2.var_ck_a;
                this.var_aq_a.c(ac2);
                continue;
            }
            if (ck2.var_boolean_c) {
                ck2.var_boolean_c = false;
                ck2 = ck2.var_ck_a;
                continue;
            }
            ck2 = ck2.var_ck_a;
        }
    }

    private final void o() {
        for (int i2 = this.var_java_util_Vector_a.size() - 1; i2 >= 0; --i2) {
            byte[] byArray = (byte[])this.var_java_util_Vector_a.elementAt(i2);
            byte[] byArray2 = byArray;
            byArray[5] = (byte)(byArray[5] - 1);
            if (byArray2[5] >= 0) continue;
            this.var_java_util_Vector_a.removeElementAt(i2);
        }
    }

    public final boolean a(int n2, int n3) {
        if (n2 < 0 || n3 < 0 || n2 >= this.var_int_a || n3 >= this.var_int_b) {
            return false;
        }
        return this.var_byte_arr_arr_c[n3][n2] >= 0 && this.var_ck_arr_arr_a[n3][n2] == null;
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
            if (n2 + i2 < 0 || n3 < 0 || n2 + i2 >= this.var_int_a || n3 >= this.var_int_b) {
                return false;
            }
            if (this.a(n2 + i2, n3) || this.var_ck_arr_arr_a[n3][n2 + i2] == o2) continue;
            return false;
        }
        return true;
    }

    public final boolean a(o o2, byte by2) {
        return this.a(o2, o2.var_byte_a + u.var_byte_arr_a[by2], o2.var_byte_b + u.b[by2]);
    }

    public final void a(ck ck2) {
        if (ck2 instanceof o) {
            ((o)ck2).f();
        }
        this.var_aq_a.ck_a(ck2);
    }

    public final void b(ck ck2) {
        this.var_aq_a.b(ck2);
        this.var_aq_a.c(ck2);
    }

    public final void c(ck ck2) {
        this.var_aq_a.c(ck2);
    }

    public final void a(int n2, int n3, int n4, int n5) {
        int[] nArray = new int[4];
        int[] nArray2 = nArray;
        nArray[0] = n3;
        nArray2[1] = n2;
        nArray2[2] = n4;
        nArray2[3] = n5;
        this.var_java_util_Vector_b.addElement(nArray2);
    }

    public final void a(byte by2, byte by3, byte by4, byte by5) {
        if (by4 == 22) {
            return;
        }
        byte[] byArray = new byte[]{by2, by3, by4, by5, 1, 120};
        this.var_java_util_Vector_a.addElement(byArray);
    }

    public final void a(byte by2, byte by3, short s2) {
        byte[] byArray = new byte[]{by2, by3, -1, (byte)(s2 / 100), (byte)(s2 % 100), 120};
        this.var_java_util_Vector_a.addElement(byArray);
    }

    public final byte[] byte_arr_a(byte by2, byte by3) {
        int n2;
        byte[] byArray = null;
        for (n2 = 0; n2 < this.var_java_util_Vector_a.size(); ++n2) {
            byte[] byArray2 = (byte[])this.var_java_util_Vector_a.elementAt(n2);
            if (byArray2[0] != by2 || byArray2[1] != by3) continue;
            if (byArray2[2] == -1) {
                byArray = byArray2;
                break;
            }
            if (byArray2[2] == 22) {
                if (!n.ao_a().var_g_b.a(byArray2[2], byArray2[3], (int)byArray2[4])) continue;
                byArray = byArray2;
                break;
            }
            if (!n.ao_a().var_g_a.a(byArray2[2], byArray2[3], (int)byArray2[4])) continue;
            byArray = byArray2;
            break;
        }
        if (byArray != null) {
            this.var_java_util_Vector_a.removeElementAt(n2);
            return byArray;
        }
        return null;
    }

    public final boolean boolean_a(byte by2, byte by3) {
        for (int i2 = 0; i2 < this.var_java_util_Vector_a.size(); ++i2) {
            byte[] byArray = (byte[])this.var_java_util_Vector_a.elementAt(i2);
            if (byArray[0] != by2 || byArray[1] != by3) continue;
            return true;
        }
        return false;
    }

    public static final void e() {
        j.a(5);
        byte[] byArray = ce.a("/enm/data" + (n.g >= 2 ? 2 : (int)n.g));
        ce.var_byte_arr_k[0] = 32;
        j.a(byArray, (byte)32, (byte)0);
        ce.e((byte)1);
        j.b((byte)0);
    }

    public final void f() {
        cc cc2 = new cc(10, 9, 32, 0);
        this.b(cc2);
        cc2.void_a((byte)1);
        cc2.b((byte)2);
    }

    public static final void g() {
        j.a(5);
        byte[] byArray = ce.a("/enm/data" + (n.g >= 2 ? 2 : (int)n.g));
        ce.var_byte_arr_k[0] = 35;
        ce.var_byte_arr_k[1] = 36;
        ce.var_byte_arr_k[2] = 37;
        ce.var_byte_arr_k[3] = 38;
        ce.var_byte_arr_k[4] = 4;
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
            ar2.void_a((byte)1);
            ar2.b((byte)2);
            return;
        }
        ag ag2 = new ag(9, 5, 36, 1);
        this.b(ag2);
        ag2.void_a((byte)2);
        ag2.b((byte)2);
        bd bd2 = new bd(6, 5, 37, 2);
        this.b(bd2);
        bd2.void_a((byte)2);
        bd2.b((byte)2);
        cd cd2 = new cd(13, 5, 38, 3);
        this.b(cd2);
        cd2.void_a((byte)2);
        cd2.b((byte)2);
        cd2.a(ag2, bd2);
        ag2.a(cd2, bd2);
    }

    public static final void h() {
        j.a(5);
        byte[] byArray = ce.a("/enm/data" + (n.g >= 2 ? 2 : (int)n.g));
        ce.var_byte_arr_k[0] = 39;
        ce.var_byte_arr_k[1] = 40;
        ce.var_byte_arr_k[2] = 41;
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
        ba2.void_a((byte)1);
        ba2.b((byte)1);
        ak ak2 = new ak(this, 13, 7, 41, 2);
        this.b(ak2);
        ak2.void_a((byte)1);
        ak2.b((byte)1);
        cg cg2 = new cg(7, 7, 39, 0, ba2, ak2);
        this.b(cg2);
        cg2.void_a((byte)2);
        cg2.b((byte)1);
    }

    public static final void j() {
        byte[] byArray = ce.a("/enm/data" + (n.g >= 2 ? 2 : (int)n.g));
        ce.var_byte_arr_k[1] = 42;
        j.a(byArray, (byte)42, (byte)1);
        ce.e((byte)4);
        j.b((byte)1);
    }

    public final void k() {
        bv bv2 = new bv(this, 7, 10, 42, 1);
        this.b(bv2);
        bv2.void_a((byte)1);
        bv2.b((byte)2);
    }

    static {
        var_int_arr_a = new int[]{0xFFDF3F, 0x3F1F3F, 0xFFDF3F, 0x3F1F3F, 0xFFDFBF, 0x7F3F3F, 0xBFFF3F, 0x1F3F3F, 0, 0, 0xBFFF3F, 0x1F3F3F, 0x3FBFFF, 0x1F3F3F, 0xBFFF3F, 0x1F3F3F, 0xBFFF3F, 0x1F3F3F, 0xBFFF3F, 0x1F3F3F, 0, 0, 0xFFDFBF, 0x7F3F3F, 0xDFFFFF, 2047871, 0xDFFFFF, 2047871, 0xFFDFBF, 0x7F3F3F};
        var_byte_c = (byte)2;
        String[] stringArray = new String[]{"SET_TILE", "SET_COLI", "OBJ_XY  ", "OBJ_DEL ", "NPC_XY  ", "NPC_DEL ", "ENM_XY  ", "ENM_DEL ", "END     ", "OBJ_NUM ", "NPC_NUM ", "EMO_HERO", "EMO_NPC "};
        var_byte_d = (byte)-1;
    }
}

