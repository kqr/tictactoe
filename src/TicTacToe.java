
import java.util.Arrays;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;



public class TicTacToe extends StateBasedGame {
    public static final int MAINMENU = 0;
    public static final int LEVELSELECT = 1;
    public static final int GAME = 2;
    public static final int CREDITS = 3;

    public static final int CROSS = 0;
    public static final int CIRCLE = 1;

    public static final int EASY = 0;
    public static final int MEDIUM = 1;
    public static final int HARD = 2;
    public static final int IMPOSSIBLE = 3;

    private static int difficulty = MEDIUM;
    public static void setDifficulty(int difficulty) {
        TicTacToe.difficulty = difficulty;
    }
    public static int getDifficulty() {
        return difficulty;
    }

    private static boolean[] humans = {true, false};
    public static void toggleHuman(int player) {
        humans[player] = !humans[player];
    }
    public static boolean[] getHumans() {
        return Arrays.copyOf(humans, 2);
    }


    public TicTacToe() throws SlickException {
        super("Tic-tac-toe");
    }

    public void initStatesList(GameContainer gc) throws SlickException {
        addState(new MainMenu(MAINMENU));
        addState(new LevelSelect(LEVELSELECT));
        addState(new Game(GAME));
        addState(new Credits(CREDITS));
    }

    public static void main(String[] args) {
        try {
            AppGameContainer app = new AppGameContainer(new TicTacToe());
            app.setDisplayMode(640, 480, false);
            app.setShowFPS(false);
            app.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

}