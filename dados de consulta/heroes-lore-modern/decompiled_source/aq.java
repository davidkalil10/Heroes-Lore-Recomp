/*
 * Decompiled with CFR 0.152.
 */
/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public final class aq {
    public int a;
    public ck a = 0;
    public ck b = null;

    public final void a(ck ck2) {
        ck2.a = this.a;
        ck2.b = null;
        if (this.a != null) {
            this.a.b = ck2;
        }
        this.a = ck2;
        if (this.b == null) {
            this.b = this.a;
        }
        ++this.a;
    }

    public final void b(ck ck2) {
        ck2.b = this.b;
        ck2.a = null;
        if (this.b != null) {
            this.b.a = ck2;
        }
        this.b = ck2;
        if (this.a == null) {
            this.a = this.b;
        }
        ++this.a;
    }

    public final ck a(ck ck2) {
        ck ck3 = this.a;
        while (ck3 != null && !ck3.equals(ck2)) {
            ck3 = ck3.a;
        }
        if (ck3 != null) {
            if (ck3.b != null) {
                ck3.b.a = ck3.a;
            } else {
                this.a = ck3.a;
            }
            if (ck3.a != null) {
                ck3.a.b = ck3.b;
            } else {
                this.b = ck3.b;
            }
            --this.a;
            return ck3;
        }
        return null;
    }

    public final void c(ck ck2) {
        if (ck2.b != null && ck2.d + ck2.d < ck2.b.d + ck2.b.d) {
            ck2.b.a = ck2.a;
            if (ck2.a == null) {
                this.b = ck2.b;
            } else {
                ck2.a.b = ck2.b;
            }
            ck ck3 = ck2.b;
            while (ck3 != null && ck2.d + ck2.d < ck3.d + ck3.d) {
                ck3 = ck3.b;
            }
            if (ck3 == null) {
                this.a(ck2);
                return;
            }
            ck3.a.b = ck2;
            ck2.a = ck3.a;
            ck3.a = ck2;
            ck2.b = ck3;
            return;
        }
        if (ck2.a != null && ck2.d + ck2.d > ck2.a.d + ck2.a.d) {
            ck2.c = true;
            ck2.a.b = ck2.b;
            if (ck2.b == null) {
                this.a = ck2.a;
            } else {
                ck2.b.a = ck2.a;
            }
            ck ck4 = ck2.a;
            while (ck4 != null && ck2.d + ck2.d > ck4.d + ck4.d) {
                ck4 = ck4.a;
            }
            if (ck4 == null) {
                this.b(ck2);
                return;
            }
            ck4.b.a = ck2;
            ck2.b = ck4.b;
            ck4.b = ck2;
            ck2.a = ck4;
        }
    }
}

