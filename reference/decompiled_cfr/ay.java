/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 */
import javax.microedition.lcdui.Graphics;

public final class ay
extends cb {
    public ay(cb cb2) {
        super(cb2, (byte)30);
    }

    public final boolean a(int n2, int n3) {
        if (this.b(n2, n3)) {
            return true;
        }
        if (this.c(n2, n3)) {
            this.var_cb_a.var_boolean_a = true;
            return true;
        }
        if (n3 == 53 || n2 == 8) {
            ad ad2 = n.ao_a().var_g_a.a((int)this.var_byte_b);
            if (ad2 != null) {
                if (ad2 instanceof e) {
                    e e2 = (e)ad2;
                    if (e2.b) {
                        if (e2 instanceof l) {
                            if (n.var_byte_a == 6 && e2.f == 0 || n.var_byte_a == 7 && e2.f == 2 || n.var_byte_a == 8 && e2.f == 1) {
                                Object[] objectArray = new Object[]{ai.var_z_a.a(11), ai.var_z_a.a(10)};
                                this.a((byte)4, (byte)2, objectArray);
                            } else {
                                Object[] objectArray = new Object[2];
                                if (e2.f == 0) {
                                    objectArray[0] = ai.var_z_a.a(8);
                                } else if (e2.f == 2) {
                                    objectArray[0] = ai.var_z_a.a(9);
                                } else if (e2.f == 1) {
                                    objectArray[0] = ai.var_z_a.a(50);
                                }
                                objectArray[1] = ai.var_z_a.a(12);
                                this.a((byte)6, (byte)2, objectArray);
                            }
                        } else if (e2.f == 3 && n.var_byte_a != 8) {
                            Object[] objectArray = new Object[]{ai.var_z_a.a(12)};
                            this.a((byte)6, (byte)2, objectArray);
                        } else {
                            Object[] objectArray = new Object[]{ai.var_z_a.a(11), ai.var_z_a.a(10)};
                            this.a((byte)4, (byte)2, objectArray);
                        }
                    } else {
                        Object[] objectArray = new Object[]{ai.var_z_a.a(12)};
                        this.a((byte)6, (byte)2, objectArray);
                    }
                } else if (ad2.boolean_a()) {
                    Object[] objectArray = new Object[]{ai.var_z_a.a(13), ai.var_z_a.a(10)};
                    this.a((byte)5, (byte)2, objectArray);
                } else if (ad2.b()) {
                    Object[] objectArray = new Object[]{ai.var_z_a.a(14)};
                    this.a(objectArray);
                } else {
                    Object[] objectArray = new Object[]{ai.var_z_a.a(12)};
                    this.a((byte)6, (byte)2, objectArray);
                }
            }
            return true;
        }
        return false;
    }

    public final void a(byte by2, byte by3) {
        super.a(by2, by3);
        ao ao2 = n.ao_a();
        g g2 = ao2.var_g_a;
        if (by2 == 4 && by3 == 0) {
            e e2 = (e)g2.a((int)this.var_byte_b);
            switch (e2.f) {
                case 0: 
                case 1: 
                case 2: {
                    ao2.b(this.var_byte_b, (byte)0);
                    break;
                }
                case 5: {
                    ao2.b(this.var_byte_b, (byte)2);
                    break;
                }
                case 6: {
                    ao2.b(this.var_byte_b, (byte)3);
                    break;
                }
                case 3: {
                    ao2.b(this.var_byte_b, (byte)1);
                    break;
                }
                case 4: {
                    ao2.b(this.var_byte_b, (byte)4);
                }
            }
            return;
        }
        if (by2 == 5 && by3 == 0) {
            ao2.void_a(g2.a((int)this.var_byte_b));
            return;
        }
        if (by2 == 4 && by3 == 1 || by2 == 5 && by3 == 1) {
            Object[] objectArray = new Object[]{ai.var_z_a.a(12)};
            this.a((byte)6, (byte)2, objectArray);
            return;
        }
        if (by2 == 6 && by3 == 0) {
            g2.void_a(this.var_byte_b, (byte)1);
        }
    }

    public final void a(Graphics graphics, int n2, int n3) {
        ad ad2;
        n2 += 2;
        n3 += 15;
        g g2 = n.ao_a().var_g_a;
        if (r.h > 128) {
            r.a(graphics, ce.var_z_g.a(2), n2 + 5, n3);
        }
        this.a(graphics, n2, n3, true);
        for (int i2 = this.int_c(); i2 <= this.int_d(); ++i2) {
            ad2 = g2.a(i2);
            if (ad2 == null) continue;
            cb.a(graphics, n2 + 13, n3 + 18 + 23 * (i2 % 5), ad2, true);
        }
        ad2 = g2.a((int)this.var_byte_b);
        if (ad2 != null) {
            cb.a(graphics, n2 + 33, n3 + 14, ad2);
            return;
        }
        graphics.setColor(0xFFFFFF);
        bh.int_a(graphics, n2 + 33, n3 + 14, ai.var_z_a.a(15), 1);
    }
}

