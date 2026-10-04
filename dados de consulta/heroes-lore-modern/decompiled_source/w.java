/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.midlet.MIDlet
 */
import javax.microedition.midlet.MIDlet;
import rpg.GameMIDlet;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public final class w {
    public static MIDlet a;
    public static String[] a;
    public static String a;
    public static boolean a;
    public static boolean b;
    public static boolean c;
    public static boolean[] a;

    public static final void a(MIDlet mIDlet) {
        a = mIDlet;
        a = cj.a;
        x.a = c = w.c();
    }

    public static final void a() {
        if (w.a(true)) {
            a = true;
        }
        if (w.a(false)) {
            b = true;
        }
        bh.a[5] = a ? w.a(true).toCharArray() : bh.a[6];
        a = w.a();
        try {
            String string = GameMIDlet.a.getAppProperty("MIDlet-Version");
            if (string != null) {
                if (c) {
                    string = string + " " + bh.a(3917);
                }
                bh.r = string.toCharArray();
            }
            return;
        }
        catch (Exception exception) {
            return;
        }
    }

    private static boolean c() {
        String string = a.getAppProperty("HO-Demo");
        boolean bl2 = true;
        if (string != null && string.trim().equals("BEJ8K52N7A")) {
            bl2 = false;
        }
        return bl2;
    }

    public static final boolean a() {
        return c && a && a != null;
    }

    public static final boolean b() {
        return !c && a && a != null;
    }

    public static final int a() {
        int n2;
        int n3 = a.length;
        a = new boolean[n3];
        int n4 = 0;
        int n5 = -1;
        String string = a.getAppProperty("HO-LangList");
        if (string != null) {
            for (n2 = 0; n2 < n3; ++n2) {
                if (string.indexOf(a[n2]) < 0) continue;
                System.out.println(a[n2]);
                w.a[n2] = true;
                n5 = n2;
                ++n4;
            }
        }
        if (n4 == 1) {
            return n5;
        }
        if (n4 == 0) {
            for (n2 = 0; n2 < n3; ++n2) {
                w.a[n2] = true;
            }
        }
        return -1;
    }

    public static final String a(boolean bl2) {
        String string;
        String string2 = "HO-Label-" + a[cj.a.a];
        String string3 = a.getAppProperty(string2);
        if (string3 == null || string3.length() == 0 || !bl2) {
            if (c) {
                return cj.a.a(3931);
            }
            return cj.a.a(3925);
        }
        if (string3.indexOf("\\u") >= 0) {
            String string4;
            StringBuffer stringBuffer = new StringBuffer(string3);
            int n2 = 0;
            char[] cArray = new char[4];
            do {
                if (stringBuffer.charAt(n2++) != '\\' || stringBuffer.charAt(n2) != 'u') continue;
                stringBuffer.getChars(n2 + 1, n2 + 5, cArray, 0);
                stringBuffer.setCharAt(n2 - 1, (char)Integer.parseInt(bh.a(cArray), 16));
                stringBuffer.delete(n2, n2 + 5);
            } while (n2 < stringBuffer.length());
            int n3 = (string4 = stringBuffer.toString()).length();
            return string4.substring(0, n3 < 16 ? n3 : 16);
        }
        int n4 = (string = string3).length();
        return string.substring(0, n4 < 16 ? n4 : 16);
    }

    private static String a() {
        String string = "HO-URL-" + a[cj.a.a];
        String string2 = a.getAppProperty(string);
        if (string2 == null || string2.length() == 0) {
            return null;
        }
        return string2;
    }

    private static boolean a(boolean bl2) {
        String string = a.getAppProperty("HO-BuySetup");
        String string2 = "HO-URL-" + a[cj.a.a];
        String string3 = a.getAppProperty(string2);
        if (string == null || string.length() == 0) {
            return false;
        }
        if (string3 == null || string3.length() == 0) {
            return false;
        }
        if (bl2) {
            return string.indexOf("menu") > -1;
        }
        return string.indexOf("exit") > -1;
    }

    static {
        a = cj.a;
    }
}

