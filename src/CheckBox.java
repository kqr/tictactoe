
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.Image;

public class CheckBox implements ButtonLike {
    private Rectangle shape;
    private String text;
    private Image[] sprites;
    public boolean checked;

    public CheckBox(String text, int x, int y, boolean initial, Image[] sprites) {
        this.text = text;
        shape = new Rectangle(x, y, 128, 24);
        this.sprites = sprites;
        checked = initial;
    }

    public boolean clicked(int x, int y) {
        return shape.contains(x, y);
    }

    public void postTrigger() {
        checked = !checked;
    }

    public void render(Graphics g) {
        sprites[checked ? 1 : 0].draw(shape.getX(), shape.getY());
        g.drawString(text, shape.getX()+30, shape.getY()+4);
    }
}
