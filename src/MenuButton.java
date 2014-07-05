
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

public class MenuButton {
    private Rectangle shape;
    private String text;
    private int action;

    public MenuButton(String text, int x, int y, int action) {
        this.text = text;
        shape = new Rectangle(x, y, 128, 24);
        this.action = action;
    }

    public void mouseReleased(int x, int y, StateBasedGame thisGame) {
        if (shape.contains(x, y)) {
            if (action < thisGame.getStateCount()) {
                thisGame.enterState(action);
            } else {
                switch (action) {
                    case 10: thisGame.getContainer().exit();
                }
            }
        }
    }

    public void render(Graphics g) {
        g.drawString(text, shape.getX()+4, shape.getY()+4);
        g.drawRect(shape.getX(), shape.getY(), shape.getWidth(), shape.getHeight());
    }
}
