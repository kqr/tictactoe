
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

public class MenuButton implements ButtonLike {
    private Rectangle shape;
    private String text;

    public MenuButton(String text, int x, int y) {
        this.text = text;
        shape = new Rectangle(x, y, 128, 24);
    }

    public boolean clicked(int x, int y) {
        return shape.contains(x, y);
    }

    public void postTrigger() {}

    public void render(Graphics g) {
        g.drawString(text, shape.getX()+4, shape.getY()+4);
        g.drawRect(shape.getX(), shape.getY(), shape.getWidth(), shape.getHeight());
    }
}
