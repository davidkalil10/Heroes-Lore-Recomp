/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.rms.RecordStore
 */
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import javax.microedition.rms.RecordStore;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public final class au {
    public RecordStore a;
    public boolean a;
    public String a;
    public ByteArrayInputStream a;
    public ByteArrayOutputStream a = null;

    public au(String string, int n2) throws Exception {
        this.a = string = string.replace('/', '_');
        try {
            this.a = RecordStore.openRecordStore((String)string, (n2 != 1 ? 1 : 0) != 0);
            if (this.a == null) {
                throw new Exception("");
            }
            return;
        }
        catch (Exception exception) {
            this.a = false;
            if (n2 == 1) {
                throw exception;
            }
            return;
        }
    }

    public final void a() {
        if (this.a != null) {
            try {
                if (this.a.getNumRecords() > 0) {
                    this.a.closeRecordStore();
                    RecordStore.deleteRecordStore((String)this.a);
                    this.a = RecordStore.openRecordStore((String)this.a, (boolean)true);
                }
                byte[] byArray = this.a.toByteArray();
                this.a.addRecord(byArray, 0, byArray.length);
            }
            catch (Exception exception) {}
        }
        try {
            this.a.closeRecordStore();
            return;
        }
        catch (Exception exception) {
            return;
        }
    }

    public final void a(byte[] byArray, int n2, int n3) throws Exception {
        if (this.a == null) {
            this.a = new ByteArrayOutputStream();
        }
        this.a.write(byArray, n2, n3);
    }

    public final void b(byte[] byArray, int n2, int n3) throws Exception {
        if (this.a == null) {
            this.a = new ByteArrayInputStream(this.a.getRecord(this.a.getNextRecordID() - 1));
        }
        this.a.read(byArray, n2, n3);
    }

    public final int a() throws Exception {
        if (this.a) {
            int n2 = this.a.getNextRecordID() - 1;
            int n3 = this.a.getRecordSize(n2);
            return n3;
        }
        throw new Exception("");
    }

    public static final boolean a(String string) {
        string = string.replace('/', '_');
        try {
            RecordStore recordStore = RecordStore.openRecordStore((String)string, (boolean)false);
            recordStore.closeRecordStore();
        }
        catch (Exception exception) {
            return false;
        }
        return true;
    }

    public static final void a(String string) {
        string = string.replace('/', '_');
        try {
            RecordStore.deleteRecordStore((String)string);
            return;
        }
        catch (Exception exception) {
            return;
        }
    }
}

