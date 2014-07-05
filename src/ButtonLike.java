import org.newdawn.slick.Graphics;

public interface ButtonLike {
    public boolean clicked(int x, int y);
    public void postTrigger();
    public void render(Graphics g);
}
