/*
 * Decompiled with CFR 0.152.
 */
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public final class cj {
    public static cj var_cj_a;
    public byte[] var_byte_arr_a;
    public byte var_byte_a = 0;
    public DataInputStream var_java_io_DataInputStream_a;
    public static final String[] var_java_lang_String_arr_a;

    public cj() {
        String[] stringArray = new String[]{"Select", "S\u00e9lection.", "W\u00e4hlen", "Selez.", "Elegir"};
        String[] stringArray2 = new String[]{"Exit", "Quitter", "Beenden", "Esci", "Salir"};
    }

    private int a(String string) {
        int n2 = -1;
        try {
            if (string == null) {
                string = System.getProperty("microedition.locale");
            }
        }
        catch (Exception exception) {
            string = null;
        }
        if (string != null) {
            int n3;
            for (n3 = 0; n3 < var_java_lang_String_arr_a.length; ++n3) {
                if (var_java_lang_String_arr_a[n3].toLowerCase().compareTo(string.toLowerCase()) != 0) continue;
                n2 = n3;
                break;
            }
            if (n2 == -1) {
                for (n3 = 0; n3 < var_java_lang_String_arr_a.length; ++n3) {
                    if (var_java_lang_String_arr_a[n3].toLowerCase().substring(0, 2).compareTo(string.toLowerCase().substring(0, 2)) != 0) continue;
                    n2 = n3 | 0x8000;
                    break;
                }
            }
        }
        return n2;
    }

    public final void a(String string, String string2, int n2) {
        try {
            int n3;
            if (n2 < 0 && (n2 = this.a(string2)) == -1) {
                n2 = 0;
            }
            this.var_byte_a = (byte)(n2 & Short.MAX_VALUE);
            InputStream inputStream = Runtime.getRuntime().getClass().getResourceAsStream(string + "." + var_java_lang_String_arr_a[this.var_byte_a]);
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            int n4 = dataInputStream.readInt();
            this.var_byte_arr_a = new byte[n4];
            int n5 = 0;
            while ((n5 += (n3 = dataInputStream.read(this.var_byte_arr_a, n5, n4 - n5))) < n4) {
            }
        }
        catch (IOException iOException) {
            System.out.println("ERROR: Couldn't load babble file." + iOException);
        }
        this.var_java_io_DataInputStream_a = new DataInputStream(new ByteArrayInputStream(this.var_byte_arr_a));
    }

    public final String a(int n2) {
        try {
            boolean bl2 = false;
            this.var_java_io_DataInputStream_a.reset();
            this.var_java_io_DataInputStream_a.skip((n2 -= 0) << 2);
            this.var_java_io_DataInputStream_a.skip(this.var_java_io_DataInputStream_a.readInt());
            this.var_java_io_DataInputStream_a.skip(2L);
            String string = this.var_java_io_DataInputStream_a.readUTF();
            return string;
        }
        catch (Exception exception) {
            return n2 + "." + exception.toString();
        }
    }

    static {
        var_cj_a = new cj();
        String[] stringArray = new String[]{"English", "Fran\u00c7ais", "Deutsch", "Italiano", "Espa\u00d1ol"};
        var_java_lang_String_arr_a = new String[]{"en-GB"};
    }
}

