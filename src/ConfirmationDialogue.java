
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class ConfirmationDialogue {
    private MenuButton ok;
    private MenuButton cancel;
    private String message;
    private int x = 200, y = 200;
    private boolean confirmed = false;

    public ConfirmationDialogue(String text) {
        message = text;
        ok = new MenuButton("OK", x+16, y+64);
        cancel = new MenuButton("Cancel", x+176, y+64);
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public boolean clicked(int x, int y) {
        if (ok.clicked(x, y)) {
            confirmed = true;
            return true;
        } else if (cancel.clicked(x, y)) {
            confirmed = false;
            return true;
        } else {
            return false;
        }
    }

    public void render(Graphics g) {
        g.setColor(new Color(0, 0, 0));
        g.fillRect(x, y, 320, 96);
        g.setColor(new Color(1.0f, 1.0f, 1.0f));
        g.drawRect(x, y, 320, 96);

        g.drawString(message, x+16, y+16);
        ok.render(g);
        cancel.render(g);
    }
}
