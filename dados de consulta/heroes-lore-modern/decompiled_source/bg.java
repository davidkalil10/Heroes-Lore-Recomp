/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 */
import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import rpg.GameMIDlet;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public final class bg
extends r
implements Runnable {
    private byte a;
    private byte b;
    private int a;
    private short a;
    private short b;
    private byte c;
    private short c;
    private short d;
    private byte d;
    private boolean d = false;
    public static bg a;

    public bg() {
        new Object();
        this.b = 0;
    }

    public final void keyPressed(int n2) {
        if (((r)this).c) {
            if (n2 != -11) {
                ((r)this).c = false;
                this.showNotify();
            }
            return;
        }
        boolean bl2 = false;
        if (n2 != bh.b && n2 != bh.c) {
            this.getGameAction(n2);
        }
        if (bs.a == null || bs.a.e) {
            return;
        }
        switch (this.a) {
            case 0: {
                return;
            }
            case 10: {
                this.c();
                return;
            }
            case 1: {
                bw.f();
                ce.x();
                ce.z();
                this.a(false, (byte)1);
                return;
            }
            case 2: {
                this.a = (byte)3;
                this.a = 0;
                bs.a.g();
                a = this;
                new Thread(a).start();
                return;
            }
            case 6: {
                GameMIDlet.a.destroyApp(true);
            }
        }
    }

    public final void run() {
        if (((r)this).c) {
            return;
        }
        switch (this.a) {
            case 0: {
                switch (this.b) {
                    case 1: {
                        ce.o();
                        r.k();
                        try {
                            ad.b = new z("/itm/itmtp");
                            r.k();
                            t.a = new z("/itm/itmatt");
                            r.k();
                        }
                        catch (IOException iOException) {}
                        ce.w();
                        ce.y();
                        try {
                            if (au.a("/c")) {
                                bs.a.j();
                            } else {
                                if (!au.a("/c")) {
                                    if (au.a(n.a[0])) {
                                        au.a(n.a[0]);
                                    }
                                    if (au.a(n.a[1])) {
                                        au.a(n.a[1]);
                                    }
                                    if (au.a(n.a[2])) {
                                        au.a(n.a[2]);
                                    }
                                    if (au.a("/o")) {
                                        au.a("/o");
                                    }
                                }
                                bs.a.i();
                            }
                        }
                        catch (Exception exception) {}
                        this.b();
                        r.k();
                        return;
                    }
                    case 2: {
                        bu.e();
                        ce.A();
                        bs.a.d();
                        this.a = (byte)-1;
                        this.b = 0;
                    }
                }
            }
        }
    }

    public final void paint(Graphics graphics) {
        if (((r)this).c) {
            graphics.setColor(0);
            graphics.fillRect(0, 0, r.g, r.h);
            graphics.setColor(0xFFFFFF);
            if (bh.n != null) {
                bh.a(graphics, r.g / 2, r.h / 3, bh.n, 0);
            }
            if (bh.q != null) {
                bh.a(graphics, r.g / 2, r.h / 2, bh.q, 0);
            }
            return;
        }
        this.a(graphics.getClipHeight());
        switch (this.a) {
            case 0: {
                break;
            }
            case 10: {
                graphics.setColor(0xFFFFFF);
                graphics.fillRect(0, 0, r.g, r.h);
                if (this.c > 40) {
                    this.a = (short)(this.a * 2);
                }
                graphics.drawImage(ce.a, r.i, this.a - this.a, 3);
                if (this.c == 0) {
                    if (this.a < r.j - 1) {
                        this.a += (r.j - this.a) / 2;
                    } else {
                        this.c = 1;
                    }
                } else {
                    switch (this.c) {
                        case 1: 
                        case 3: {
                            this.a = r.j - 1;
                            break;
                        }
                        case 2: 
                        case 4: {
                            this.a = r.j;
                        }
                    }
                    this.c = (byte)(this.c + 1);
                }
                if (this.a <= r.h) break;
                this.c();
                break;
            }
            case 1: {
                graphics.setColor(0xFFFFFF);
                graphics.fillRect(0, 0, r.g, r.h);
                int n2 = r.j - 68;
                int n3 = r.i - 60;
                graphics.drawImage(ce.i[2], n3 + 0, n2 + 25, 20);
                graphics.drawImage(ce.i[3], n3 + 52, n2 + 25, 20);
                graphics.drawImage(ce.i[4], n3 + 93, n2 + 2, 20);
                boolean bl2 = false;
                graphics.setColor(0x3F1F3F);
                if (bh.r != null) {
                    bh.a(graphics, r.g - 2 - bh.a(bh.r), r.h - 31, bh.r, 0);
                }
                graphics.drawImage(ce.j[this.c < 4 ? this.c : 8 - this.c], (int)this.a, (int)this.b, 33);
                graphics.drawImage(ce.j[(this.d < 4 ? this.d : 8 - this.d) + 5], (int)this.c, (int)this.d, 33);
                this.a = (short)(this.a + 10 * (this.c < 4 ? 1 : -1));
                this.b = (short)(this.b + h.a(-1, 4));
                this.c = (short)(this.c + 10 * (this.d < 4 ? -1 : 1));
                this.d = (short)(this.d + h.a(-1, 4));
                this.c = (byte)(this.c + 1);
                this.d = (byte)(this.d + 1);
                if (this.c > 7) {
                    this.c = 0;
                }
                if (this.d > 7) {
                    this.d = 0;
                }
                if (this.b > r.h + 10) {
                    this.a = (short)h.a(10, r.g / 2 - 10);
                    this.b = (short)(-10 * h.a(0, 4));
                    this.c = (byte)h.a(0, 7);
                }
                if (this.d > r.h + 10) {
                    this.c = (short)h.a(r.g / 2 + 10, r.g - 10);
                    this.d = (short)(-10 * h.a(3, 7));
                    this.d = (byte)h.a(0, 7);
                }
                if (this.a % 4 < 2) {
                    graphics.setColor(0);
                    bh.a(graphics, r.i, r.h - 45, bh.q, 1);
                }
                ++this.a;
            }
        }
        bs.a.b();
    }

    public final void a() {
        r.a("- INITIALIZE", 30);
        bh.a();
        w.a(GameMIDlet.a);
        int n2 = w.a();
        if (n2 >= 0) {
            r.m = 3;
            this.b = 1;
            this.a = 0;
            this.b(n2);
            new Thread(this).start();
            return;
        }
        r.m = 3;
        this.b = 1;
        this.a = 0;
        this.b(0);
        new Thread(this).start();
    }

    private void b(int n2) {
        cj.a.a("/lang", "", n2);
        bh.a(cj.a);
        w.a();
        try {
            ce.g = new z("/sgui/com");
            bh.p = ce.g.a(37);
            bh.o = ce.g.a(38);
            r.k();
            this.b = 1;
            return;
        }
        catch (IOException iOException) {
            return;
        }
    }

    public final void b() {
        bs.a.a(20);
        this.a = -20;
        this.c = 0;
        this.a = 1;
        this.a = (byte)10;
    }

    public final void hideNotify() {
        if (bh.q != null) {
            ((r)this).c = true;
        }
        bw.a();
    }

    public final void showNotify() {
        if (((r)this).c) {
            return;
        }
        bw.b();
    }

    private final void c() {
        bs.a.a(15);
        this.a = 0;
        this.a = 1;
        this.a = (short)h.a(0, r.g / 2 - 10);
        this.b = (short)(10 * h.a(0, 4));
        this.c = (byte)h.a(0, 7);
        this.c = (short)h.a(r.g / 2, r.g - 10);
        this.d = (short)(10 * h.a(3, 7));
        this.d = (byte)h.a(0, 7);
        bw.b(22);
    }

    public final void a(boolean bl2, byte by2) {
        bw.d();
        if (!bl2 && (bs.a.b & (by2 == 1 ? 8 : 2)) != 0) {
            bl2 = true;
        }
        if (!bl2 || this.d) {
            this.a = (byte)2;
            return;
        }
        this.a = 0;
        this.b = (byte)2;
        r.a("- STORY MODE", 52);
        bs.a.g();
        new Thread(this).start();
    }

    static {
        "*:MAP UPDATE".toCharArray();
    }
}

