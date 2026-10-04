/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.media.Manager
 *  javax.microedition.media.MediaException
 *  javax.microedition.media.Player
 *  javax.microedition.media.PlayerListener
 *  javax.microedition.media.control.VolumeControl
 */
import java.io.InputStream;
import javax.microedition.media.Manager;
import javax.microedition.media.MediaException;
import javax.microedition.media.Player;
import javax.microedition.media.PlayerListener;
import javax.microedition.media.control.VolumeControl;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public final class ci
implements PlayerListener {
    private Player a;

    public ci(String string) {
        this.a(string);
    }

    public final void a(int n2) {
        if (this.a != null) {
            this.a.setLoopCount(n2);
        }
    }

    public final void a() {
        if (bs.a.a > 0) {
            this.d();
        }
    }

    public final void b() {
        try {
            if (this.a != null) {
                this.a.stop();
            }
            return;
        }
        catch (MediaException mediaException) {
            MediaException mediaException2 = mediaException;
            mediaException.printStackTrace();
            return;
        }
    }

    public final void c() {
        if (this.a != null) {
            this.a.close();
            this.a = null;
        }
    }

    public final boolean a() {
        return this.a != null && this.a.getState() >= 400;
    }

    private static String a(String string) throws Exception {
        String string2;
        if (string.endsWith("wav")) {
            string2 = "audio/x-wav";
        } else if (string.endsWith("jts")) {
            string2 = "audio/x-tone-seq";
        } else if (string.endsWith("mid")) {
            string2 = "audio/midi";
        } else {
            throw new Exception("Cannot guess content type from URL: " + string);
        }
        return string2;
    }

    private void a(String string) {
        block6: {
            if (this.a == null) {
                try {
                    if (string.startsWith("http:")) {
                        this.a = Manager.createPlayer((String)string);
                    } else if (string.startsWith("resource")) {
                        int n2 = string.indexOf(58);
                        String string2 = string.substring(n2 + 1);
                        InputStream inputStream = this.getClass().getResourceAsStream(string2);
                        String string3 = ci.a(string);
                        this.a = Manager.createPlayer((InputStream)inputStream, (String)string3);
                        this.a.realize();
                    }
                    this.a.addPlayerListener((PlayerListener)this);
                    return;
                }
                catch (Exception exception) {
                    if (this.a == null) break block6;
                    this.a.close();
                    this.a = null;
                }
            }
        }
    }

    public final void b(int n2) {
        VolumeControl volumeControl;
        if (this.a != null && (volumeControl = (VolumeControl)this.a.getControl("VolumeControl")) != null) {
            volumeControl.setLevel(n2);
        }
    }

    public final void d() {
        if (this.a != null) {
            try {
                this.a.realize();
                this.a.prefetch();
                this.a.start();
                return;
            }
            catch (MediaException mediaException) {
                MediaException mediaException2 = mediaException;
                mediaException.printStackTrace();
                return;
            }
        }
    }

    public final void playerUpdate(Player player, String string, Object object) {
    }
}

