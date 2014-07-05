
import java.util.Arrays;

import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class MainMenu extends BasicGameState {
    private int stateId;
    private ButtonLike[] buttons;
    private StateBasedGame thisGame;

    public MainMenu(int id) {
        stateId = id;

        buttons = new ButtonLike[]{
            new MenuButton("Play game", 256, 200),
            new MenuButton("Credits", 256, 300),
            new MenuButton("Exit", 256, 340)
        };
    }

    @Override
    public int getID() {
        return stateId;
    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) {
        thisGame = game;
    }

    @Override
    public void mouseReleased(int mouseButton, int x, int y) {
        if (mouseButton == 0) {
            for (int i = 0; i < buttons.length; i++) {
                if (buttons[i].clicked(x, y)) {
                    switch (i) {
                        case 0: thisGame.enterState(TicTacToe.LEVELSELECT); break;
                        case 1: thisGame.enterState(TicTacToe.CREDITS); break;
                        case 2: thisGame.getContainer().exit(); break;
                    }
                }
            }
        }
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) {

    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) {

    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) {
        Arrays.stream(buttons).forEach(button -> button.render(g));
    }

}
