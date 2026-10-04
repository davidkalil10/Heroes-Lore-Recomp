/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 *  javax.microedition.lcdui.Image
 */
import java.io.DataInputStream;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Vector;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class az {
    public boolean a;
    public boolean b;
    private Image a;
    public int a;
    private int c;
    public int b = 2;
    private int d;
    private int e;
    private short[] a;
    private boolean c = true;
    private static final int[] a = new int[256];

    public az(String string, int n2, int n3, boolean bl2) {
        this.a = bl2;
        InputStream inputStream = this.getClass().getResourceAsStream("/" + string + ".mf");
        this.a(inputStream, n2, n3);
    }

    public final void a(InputStream inputStream, int n2, int n3) {
        try {
            int n4;
            int n5;
            int n6;
            inputStream.read();
            inputStream.read();
            inputStream.read();
            inputStream.read();
            this.a = inputStream.read();
            this.c = false;
            if (this.a - 100 > 0) {
                this.a -= 100;
                this.c = true;
            }
            this.c = inputStream.read();
            this.d = inputStream.read();
            this.e = inputStream.read();
            this.a = new short[95 + (this.c ? 9 : 0)];
            for (n6 = 0; n6 < 95 + (this.c ? 9 : 0); ++n6) {
                n5 = inputStream.read();
                n4 = inputStream.read();
                this.a[n6] = (short)((n5 & 0xFF) << 8 | n4 & 0xFF);
            }
            n6 = inputStream.read();
            n5 = inputStream.read();
            n4 = (n6 & 0xFF) << 8 | n5 & 0xFF;
            int n7 = inputStream.read();
            int n8 = inputStream.read();
            int n9 = (n7 & 0xFF) << 8 | n8 & 0xFF;
            int n10 = inputStream.read();
            int n11 = inputStream.read();
            int n12 = n10 == 255 && n11 == 255 ? -1 : (n10 & 0xFF) << 8 | n11 & 0xFF;
            int n13 = inputStream.read();
            int n14 = inputStream.read();
            int n15 = (n13 & 0xFF) << 8 | n14 & 0xFF;
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            byte[] byArray = new byte[n4];
            dataInputStream.readFully(byArray);
            dataInputStream.close();
            if (n12 > 0) {
                this.a(byArray, n9, n12, n2, n15, n3);
            }
            this.a = Image.createImage((byte[])byArray, (int)0, (int)n4);
            return;
        }
        catch (Exception exception) {
            throw new RuntimeException("MFont: " + exception);
        }
    }

    public final int a(String string) {
        if (string == null) {
            return 0;
        }
        char[] cArray = string.toCharArray();
        return this.a(cArray, 0, cArray.length);
    }

    public final int a(char[] cArray, int n2, int n3) {
        int n4 = 0;
        int n5 = n2 + n3;
        for (int i2 = n2; i2 < n5; ++i2) {
            if (this.a(cArray[i2])) continue;
            n4 += this.a(cArray[i2]) - this.e;
        }
        return n4;
    }

    public final int a(char c2) {
        if (c2 > ' ' && c2 < '\u0100') {
            boolean bl2 = '\u00c0' <= c2 && '\u00df' > c2;
            if (bl2) {
                c2 = (char)(c2 + 32);
            }
            if (c2 > '\u007f') {
                switch (c2) {
                    case '\u00e8': 
                    case '\u00e9': 
                    case '\u00ea': 
                    case '\u00eb': {
                        c2 = (char)101;
                        break;
                    }
                    case '\u00e0': 
                    case '\u00e1': 
                    case '\u00e2': 
                    case '\u00e3': 
                    case '\u00e4': 
                    case '\u00e5': {
                        c2 = (char)97;
                        break;
                    }
                    case '\u00f9': 
                    case '\u00fa': 
                    case '\u00fb': 
                    case '\u00fc': {
                        c2 = (char)117;
                        break;
                    }
                    case '\u00f2': 
                    case '\u00f3': 
                    case '\u00f4': 
                    case '\u00f5': 
                    case '\u00f6': {
                        c2 = (char)111;
                        break;
                    }
                    case '\u00ec': 
                    case '\u00ed': 
                    case '\u00ee': 
                    case '\u00ef': {
                        c2 = (char)105;
                        break;
                    }
                    case '\u00f1': {
                        c2 = (char)110;
                        break;
                    }
                    case '\u00df': {
                        c2 = (char)127;
                        break;
                    }
                    case '\u00a1': {
                        c2 = (char)129;
                        break;
                    }
                    case '\u00bf': {
                        c2 = (char)130;
                        break;
                    }
                    case '\u00e7': {
                        c2 = (char)(bl2 ? 128 : 137);
                        break;
                    }
                    case '\u00e6': {
                        c2 = (char)(bl2 ? 136 : 138);
                        break;
                    }
                    case '\u008c': {
                        c2 = (char)139;
                        break;
                    }
                    case '\u009c': {
                        c2 = (char)140;
                        break;
                    }
                    default: {
                        c2 = (char)46;
                    }
                }
            }
            if (c2 >= '\u0088') {
                switch (c2) {
                    case '\u0088': {
                        return 8;
                    }
                    case '\u0089': {
                        return 5;
                    }
                    case '\u008a': {
                        return 7;
                    }
                }
            }
            int n2 = this.a[c2 - 33 + 1] - this.a[c2 - 33];
            if ((this.a || bl2) && c2 >= 'a' && c2 <= 'z') {
                c2 = (char)(c2 - 32);
                n2 = this.a[c2 - 33 + 1] - this.a[c2 - 33];
            }
            return n2;
        }
        if (c2 == ' ') {
            return this.d;
        }
        return 0;
    }

    public final int b(char[] cArray, int n2, int n3) {
        int n4 = n2 + n3;
        for (int i2 = n2; i2 < n4; ++i2) {
            char c2 = cArray[i2];
            if ("gjpqy,;_|\u00e7\u00a1\u00bf".indexOf(c2) == -1) continue;
            return this.a;
        }
        return this.c + 1;
    }

    public final int a(Vector vector) {
        return (this.a + this.b) * vector.size();
    }

    public final int a(Graphics graphics, String string, int n2, int n3, int n4) {
        return this.a(graphics, string, 0, string.length(), n2, n3, n4);
    }

    public final int a(Graphics graphics, char[] cArray, int n2, int n3, int n4) {
        return this.a(graphics, cArray, 0, cArray.length, n2, n3, n4);
    }

    public final int a(Graphics graphics, Vector vector, int n2, int n3, int n4, int n5) {
        int n6 = n3;
        int n7 = this.a + this.b;
        Enumeration enumeration = vector.elements();
        while (enumeration.hasMoreElements()) {
            String string = (String)enumeration.nextElement();
            if (n6 + n7 >= graphics.getClipY() && n6 < n4) {
                this.a(graphics, string.toCharArray(), 0, string.length(), n2, n6, n5);
            }
            n6 += n7;
        }
        return n6 - n3;
    }

    public final int a(Graphics graphics, String string, int n2, int n3, int n4, int n5, int n6) {
        return this.a(graphics, string.substring(n2, n3).toCharArray(), 0, n3 - n2, n4, n5, n6);
    }

    public final int a(Graphics graphics, char[] cArray, int n2, int n3, int n4, int n5, int n6) {
        int n7 = graphics.getClipX();
        int n8 = graphics.getClipY();
        int n9 = graphics.getClipWidth();
        int n10 = graphics.getClipHeight();
        if ((n6 & 1) != 0) {
            n4 -= this.a(cArray, n2, n3) / 2;
        } else if ((n6 & 8) != 0) {
            n4 -= this.a(cArray, n2, n3);
        }
        if ((n6 & 0x20) != 0) {
            n5 -= this.b(cArray, n2, n3);
        } else if ((n6 & 0x40) != 0) {
            n5 -= this.c;
        }
        int n11 = 0;
        int n12 = n2 + n3;
        boolean bl2 = false;
        boolean bl3 = false;
        for (int i2 = n2; i2 < n12; ++i2) {
            int n13;
            if (n4 > n7 + n9) {
                graphics.setClip(n7, n8, n9, n10);
                return n11;
            }
            int n14 = cArray[i2];
            if (this.a((char)n14)) continue;
            int n15 = -1;
            int n16 = 0;
            if (n14 == 32) {
                n4 += this.d;
                n11 += this.d;
                continue;
            }
            if (n14 <= 32 || n14 >= 256) continue;
            boolean bl4 = 192 <= n14 && 223 > n14;
            if (bl4) {
                n14 = (char)(n14 + 32);
            }
            if (n14 > 127) {
                switch (n14) {
                    case 232: {
                        n14 = 101;
                        n15 = 0;
                        n16 = 1;
                        break;
                    }
                    case 233: {
                        n14 = 101;
                        n15 = 1;
                        n16 = 1;
                        break;
                    }
                    case 234: {
                        n14 = 101;
                        n15 = 2;
                        n16 = 1;
                        break;
                    }
                    case 235: {
                        n14 = 101;
                        n15 = 3;
                        n16 = 1;
                        break;
                    }
                    case 224: {
                        n14 = 97;
                        n15 = 0;
                        n16 = 1;
                        break;
                    }
                    case 225: {
                        n14 = 97;
                        n15 = 1;
                        n16 = 1;
                        break;
                    }
                    case 226: {
                        n14 = 97;
                        n15 = 2;
                        n16 = 1;
                        break;
                    }
                    case 227: {
                        n14 = 97;
                        n15 = 3;
                        n16 = 1;
                        break;
                    }
                    case 228: {
                        n14 = 97;
                        n15 = 4;
                        n16 = 1;
                        break;
                    }
                    case 229: {
                        n14 = 97;
                        break;
                    }
                    case 249: {
                        n14 = 117;
                        n15 = 0;
                        n16 = 1;
                        break;
                    }
                    case 250: {
                        n14 = 117;
                        n15 = 1;
                        n16 = 1;
                        break;
                    }
                    case 251: {
                        n14 = 117;
                        n15 = 2;
                        n16 = 1;
                        break;
                    }
                    case 252: {
                        n14 = 117;
                        n15 = 4;
                        n16 = 1;
                        break;
                    }
                    case 242: {
                        n14 = 111;
                        n15 = 0;
                        n16 = 1;
                        break;
                    }
                    case 243: {
                        n14 = 111;
                        n15 = 1;
                        n16 = 1;
                        break;
                    }
                    case 244: {
                        n14 = 111;
                        n15 = 2;
                        n16 = 1;
                        break;
                    }
                    case 245: {
                        n14 = 111;
                        n15 = 3;
                        n16 = 1;
                        break;
                    }
                    case 246: {
                        n14 = 111;
                        n15 = 4;
                        n16 = 1;
                        break;
                    }
                    case 236: {
                        n14 = 105;
                        n15 = 0;
                        n16 = -1;
                        break;
                    }
                    case 237: {
                        n14 = 105;
                        n15 = 1;
                        n16 = 0;
                        break;
                    }
                    case 238: {
                        n14 = 105;
                        n15 = 2;
                        n16 = -1;
                        break;
                    }
                    case 239: {
                        n14 = 105;
                        n15 = 4;
                        n16 = -1;
                        break;
                    }
                    case 241: {
                        n14 = 110;
                        n15 = 3;
                        n16 = 1;
                        break;
                    }
                    case 223: {
                        n14 = 127;
                        break;
                    }
                    case 161: {
                        n14 = 129;
                        break;
                    }
                    case 191: {
                        n14 = 130;
                        break;
                    }
                    case 231: {
                        n14 = this.a || bl4 ? 128 : 137;
                        break;
                    }
                    case 230: {
                        n14 = this.a || bl4 ? 136 : 138;
                        break;
                    }
                    case 140: {
                        n14 = 139;
                        break;
                    }
                    case 156: {
                        n14 = this.a || bl4 ? 139 : 140;
                        break;
                    }
                    default: {
                        n14 = 46;
                    }
                }
            }
            if ((this.a || bl4) && n14 >= 97 && n14 <= 122) {
                n14 = (char)(n14 - 32);
            }
            short s2 = 0;
            int n17 = 0;
            switch (n14) {
                case 136: {
                    s2 = this.a[this.a.length - 1];
                    n17 = 8;
                    break;
                }
                case 137: {
                    s2 = (short)(this.a[this.a.length - 1] + 8);
                    n17 = 5;
                    break;
                }
                case 138: {
                    s2 = (short)(this.a[this.a.length - 1] + 8 + 5);
                    n17 = 7;
                    break;
                }
                case 140: {
                    s2 = (short)(this.a[this.a.length - 1] + 8 + 5 + 7);
                    n17 = 7;
                    break;
                }
                case 156: {
                    s2 = (short)(this.a[this.a.length - 1] + 8 + 5 + 7 + 7);
                    n17 = 7;
                    break;
                }
                default: {
                    s2 = this.a[n14 - 33];
                    n17 = this.a[n14 - 33 + 1] - s2;
                }
            }
            short s3 = this.c ? this.a[98 + n15] : (short)0;
            int n18 = n13 = this.c ? this.a[98 + n15 + 1] - s3 : 0;
            if (n4 + n17 - this.e < n7) {
                n4 += n17 - this.e;
                n11 += n17 - this.e;
                continue;
            }
            graphics.setClip(n7, n8, n9, n10);
            if (n14 == 105 && n15 >= 0) {
                graphics.clipRect(n4, n5 + 1, n17, this.a);
            } else {
                graphics.clipRect(n4, n5, n17, this.a);
            }
            graphics.drawImage(this.a, n4 - s2, n5, 20);
            if (n15 >= 0) {
                graphics.setClip(n7, n8, n9, n10);
                n13 = n15 != 4 ? n13 : n13 + 1;
                int n19 = 0;
                if (this.a || bl4) {
                    n19 = 2;
                }
                if (n14 == 105 && n15 >= 0) {
                    graphics.clipRect(n4 - 1, n5 - 1 - n19, n13 + n16, this.a + n19);
                } else {
                    graphics.clipRect(n4, n5 - 1 - n19, n13 + n16, this.a + n19);
                }
                graphics.drawImage(this.a, n4 - s3 + n16, n5 - 1 - n19, 20);
            }
            n4 += n17 - this.e;
            n11 += n17 - this.e;
        }
        graphics.setClip(n7, n8, n9, n10);
        return n11;
    }

    private boolean a(char c2) {
        if (this.b) {
            switch (c2) {
                case '$': 
                case '@': 
                case '|': {
                    return true;
                }
            }
        }
        return false;
    }

    private void a(byte[] byArray, int n2, int n3, int n4, int n5, int n6) {
        int n7 = (byArray[n2] & 0xFF) << 24 | (byArray[n2 + 1] & 0xFF) << 16 | (byArray[n2 + 2] & 0xFF) << 8 | byArray[n2 + 3] & 0xFF;
        byArray[n3] = (byte)(n4 >> 16);
        byArray[n3 + 1] = (byte)(n4 >> 8);
        byArray[n3 + 2] = (byte)n4;
        if (n5 > 0 && n6 >= 0) {
            byArray[n5] = (byte)(n6 >> 16);
            byArray[n5 + 1] = (byte)(n6 >> 8);
            byArray[n5 + 2] = (byte)n6;
        }
        int n8 = this.a(byArray, n2 + 4, n7 + 4);
        int n9 = n2 + 8 + n7;
        byArray[n9] = (byte)(n8 >> 24);
        byArray[n9 + 1] = (byte)(n8 >> 16);
        byArray[n9 + 2] = (byte)(n8 >> 8);
        byArray[n9 + 3] = (byte)n8;
    }

    private int a(byte[] byArray, int n2, int n3) {
        int n4 = -1;
        while (--n3 >= 0) {
            n4 = a[(n4 ^ byArray[n2++]) & 0xFF] ^ n4 >>> 8;
        }
        return ~n4;
    }

    static {
        for (int i2 = 0; i2 < 256; ++i2) {
            int n2 = i2;
            for (int i3 = 0; i3 < 8; ++i3) {
                n2 = (n2 & 1) != 0 ? 0xEDB88320 ^ n2 >>> 1 : (n2 >>>= 1);
                az.a[i2] = n2;
            }
        }
    }
}

