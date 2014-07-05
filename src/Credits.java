import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Credits extends BasicGameState {
    private int stateId;
    private StateBasedGame thisGame;
    private float y = 480;

    public Credits(int id) { stateId = id; }

    @Override
    public int getID() { return stateId; }

    @Override
    public void enter(GameContainer container, StateBasedGame game) { thisGame = game; }

    @Override
    public void init(GameContainer container, StateBasedGame game) {}

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) {
        y -= delta/10.0;
        if (y < -200) {
            thisGame.enterState(TicTacToe.MAINMENU);
        }
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) {
        String str = "Most awesomest code by ~kqr\n" +
                "Best graphics everrr by ~kqr\n\n" +
                "Uses state handling in Slick2D!\n" +
                "\n\n\nYeaaah!";
        g.drawString(str, 200, y);
    }
}
