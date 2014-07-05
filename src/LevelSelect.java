
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
                new MenuButton("Back", 256, 420),
                new CheckBox("Cross", 270, 60, !TicTacToe.getHumans()[TicTacToe.CROSS], checkSprites),
                new CheckBox("Circle", 270, 100, !TicTacToe.getHumans()[TicTacToe.CIRCLE], checkSprites),
                new CheckBox("Easy", 40, 230, TicTacToe.getDifficulty() == TicTacToe.EASY, checkSprites),
                new CheckBox("Medium", 180, 230, TicTacToe.getDifficulty() == TicTacToe.MEDIUM, checkSprites),
                new CheckBox("Hard", 320, 230, TicTacToe.getDifficulty() == TicTacToe.HARD, checkSprites),
                new CheckBox("Impossible", 460, 230, TicTacToe.getDifficulty() == TicTacToe.IMPOSSIBLE, checkSprites)
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
                        case 0: thisGame.enterState(TicTacToe.GAME); break;
                        case 1: thisGame.enterState(TicTacToe.MAINMENU); break;
                        case 2: TicTacToe.toggleHuman(TicTacToe.CROSS); break;
                        case 3: TicTacToe.toggleHuman(TicTacToe.CIRCLE); break;
                        case 4: case 5: case 6: case 7:
                            buttons[TicTacToe.getDifficulty() + 4].postTrigger();
                            TicTacToe.setDifficulty(i-4);
                            break;
                    }

                    buttons[i].postTrigger();
                }
            }
        }
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) {}

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) {}

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) {
        g.drawRect(160, 10, 320, 135);
        g.drawRect(10, 180, 620, 90);
        Arrays.stream(buttons).forEach(button -> button.render(g));
        g.drawString("Computer players:", 240, 25);
        g.drawString("Computer strength:", 240, 190);
    }

}


