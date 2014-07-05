
import java.util.Arrays;

import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class LevelSelect extends BasicGameState {
    private int stateId;
    private ButtonLike[] buttons;
    private StateBasedGame thisGame;

    public LevelSelect(int id) throws SlickException {
        stateId = id;

        Image[] checkSprites = new Image[]{
                new Image("res/check_false.png"),
                new Image("res/check_true.png")
        };

        buttons = new ButtonLike[]{
                new MenuButton("Start Game!", 256, 300),
                new MenuButton("Back", 256, 340),
                new CheckBox("Cross", 270, 60, TicTacToe.getHumans()[TicTacToe.CROSS], checkSprites),
                new CheckBox("Circle", 270, 100, TicTacToe.getHumans()[TicTacToe.CIRCLE], checkSprites)
        };
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
            for (int i = 0; i < buttons.length; i++) {
                if (buttons[i].clicked(x, y)) {
                    switch (i) {
                        case 0: thisGame.enterState(TicTacToe.GAME); break;
                        case 1: thisGame.enterState(TicTacToe.MAINMENU); break;
                        case 2: TicTacToe.toggleHuman(TicTacToe.CROSS); break;
                        case 3: TicTacToe.toggleHuman(TicTacToe.CIRCLE); break;
                    }

                    buttons[i].postTrigger();
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
        g.drawString("Computer players:", 240, 25);
    }

}


