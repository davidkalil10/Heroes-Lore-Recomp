/*
 * Decompiled with CFR 0.152.
 */
public final class aq {
    public int var_int_a = 0;
    public ck var_ck_a = null;
    public ck b = null;

    public final void void_a(ck ck2) {
        ck2.var_ck_a = this.var_ck_a;
        ck2.var_ck_b = null;
        if (this.var_ck_a != null) {
            this.var_ck_a.var_ck_b = ck2;
        }
        this.var_ck_a = ck2;
        if (this.b == null) {
            this.b = this.var_ck_a;
        }
        ++this.var_int_a;
    }

    public final void b(ck ck2) {
        ck2.var_ck_b = this.b;
        ck2.var_ck_a = null;
        if (this.b != null) {
            this.b.var_ck_a = ck2;
        }
        this.b = ck2;
        if (this.var_ck_a == null) {
            this.var_ck_a = this.b;
        }
        ++this.var_int_a;
    }

    public final ck ck_a(ck ck2) {
        ck ck3 = this.var_ck_a;
        while (ck3 != null && !ck3.equals(ck2)) {
            ck3 = ck3.var_ck_a;
        }
        if (ck3 != null) {
            if (ck3.var_ck_b != null) {
                ck3.var_ck_b.var_ck_a = ck3.var_ck_a;
            } else {
                this.var_ck_a = ck3.var_ck_a;
            }
            if (ck3.var_ck_a != null) {
                ck3.var_ck_a.var_ck_b = ck3.var_ck_b;
            } else {
                this.b = ck3.var_ck_b;
            }
            --this.var_int_a;
            return ck3;
        }
        return null;
    }

    public final void c(ck ck2) {
        if (ck2.var_ck_b != null && ck2.var_byte_d + ck2.var_short_d < ck2.var_ck_b.var_byte_d + ck2.var_ck_b.var_short_d) {
            ck2.var_ck_b.var_ck_a = ck2.var_ck_a;
            if (ck2.var_ck_a == null) {
                this.b = ck2.var_ck_b;
            } else {
                ck2.var_ck_a.var_ck_b = ck2.var_ck_b;
            }
            ck ck3 = ck2.var_ck_b;
            while (ck3 != null && ck2.var_byte_d + ck2.var_short_d < ck3.var_byte_d + ck3.var_short_d) {
                ck3 = ck3.var_ck_b;
            }
            if (ck3 == null) {
                this.void_a(ck2);
                return;
            }
            ck3.var_ck_a.var_ck_b = ck2;
            ck2.var_ck_a = ck3.var_ck_a;
            ck3.var_ck_a = ck2;
            ck2.var_ck_b = ck3;
            return;
        }
        if (ck2.var_ck_a != null && ck2.var_byte_d + ck2.var_short_d > ck2.var_ck_a.var_byte_d + ck2.var_ck_a.var_short_d) {
            ck2.var_boolean_c = true;
            ck2.var_ck_a.var_ck_b = ck2.var_ck_b;
            if (ck2.var_ck_b == null) {
                this.var_ck_a = ck2.var_ck_a;
            } else {
                ck2.var_ck_b.var_ck_a = ck2.var_ck_a;
            }
            ck ck4 = ck2.var_ck_a;
            while (ck4 != null && ck2.var_byte_d + ck2.var_short_d > ck4.var_byte_d + ck4.var_short_d) {
                ck4 = ck4.var_ck_a;
            }
            if (ck4 == null) {
                this.b(ck2);
                return;
            }
            ck4.var_ck_b.var_ck_a = ck2;
            ck2.var_ck_b = ck4.var_ck_b;
            ck4.var_ck_b = ck2;
            ck2.var_ck_a = ck4;
        }
    }
}

