import java.util.ArrayList;

import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class LevelSelect extends BasicGameState {
    private int stateId;
    private ArrayList<MenuButton> buttons = new ArrayList<>();
    private StateBasedGame thisGame;

    public LevelSelect(int id) {
        stateId = id;

        buttons.add(new MenuButton("Start Game!", 40, 40, TicTacToe.GAME));
        buttons.add(new MenuButton("Back", 40, 80, 1));
    }

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
            buttons.stream().forEach(button -> button.mouseReleased(x, y, thisGame));
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
        buttons.stream().forEach(button -> button.render(g));
    }

}


