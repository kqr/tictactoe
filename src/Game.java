
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Game extends BasicGameState {
    private Board board;
    private AI[] computers = new AI[2];
    private double moveValue;

    private MenuButton backButton;
    private Rectangle[] uiSquares = new Rectangle[9];
    private Image[] sprites = new Image[2];

    private int stateId;
    private StateBasedGame thisGame;

    public Game(int id) throws SlickException {
        stateId = id;

        sprites[TicTacToe.CROSS] = new Image("res/cross.png");
        sprites[TicTacToe.CIRCLE] = new Image("res/circle.png");

        backButton = new MenuButton("Back", 500, 440);

        for (int i = 0; i < 9; i++) {
            uiSquares[i] = new Rectangle(57 + 130 * (i % 3),
                                         57 + 130 * (i / 3),
                                         105, 105);
        }
    }

    @Override
    public int getID() {
        return stateId;
    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) {
        thisGame = game;
        board = new Board();

        computers[TicTacToe.CROSS] = new AI(TicTacToe.getDifficulty());
        computers[TicTacToe.CIRCLE] = new AI(TicTacToe.getDifficulty());
    }

    @Override
    public void mouseMoved(int oldx, int oldy, int newx, int newy) {
        for (int i = 0; i < 9; i++) {
            if (uiSquares[i].contains(newx, newy)) {
                moveValue = computers[0].value(board.copy(), i);
            }
        }
    }

    @Override
    public void mouseReleased(int mouseButton, int x, int y) {
        if (mouseButton == 0) {
            if (backButton.clicked(x, y)) {
                thisGame.enterState(TicTacToe.LEVELSELECT);
            }

            if (board.humanTurn()) {
                for (int i = 0; i < 9; i++) {
                    if (uiSquares[i].contains(x, y)) {
                        try {
                            board.take(i);
                        } catch (AlreadyTakenException e) {
                            System.out.println("Already taken!!");
                        }
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
        if (!board.gameEnded()) {
            if (!board.humanTurn()) {
                try {
                    board.take(computers[board.nextPlayer()].play(board.copy()));
                } catch (AlreadyTakenException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) {
        for (int i = 0; i < 9; i++) {
            if (board.taken(i)) {
                sprites[board.owns(i)].draw(uiSquares[i].getX() - 12, uiSquares[i].getY() - 12);
            }
        }

        g.drawLine(45, 175, 435, 175);
        g.drawLine(45, 305, 435, 305);

        g.drawLine(175, 45, 175, 435);
        g.drawLine(305, 45, 305, 435);

        g.drawLine(480, 0, 480, 480);

        if (!board.gameEnded()) {
            g.drawString("Turn: " + (board.nextPlayer() == TicTacToe.CROSS ? "Cross" : "Circle"), 500, 40);
            g.drawString("(" + (board.humanTurn() ? "Human" : "Computer") + ")", 500, 60);

            g.drawString("Value: " + String.format("%.2f", moveValue), 500, 100);
        } else {
            g.drawString("Game over!", 500, 220);
            if (board.getWinner() == -1) {
                g.drawString("Tie!", 500, 240);
            } else {
                g.drawString("Winner: " + (board.getWinner() == TicTacToe.CROSS ? "Cross!" : "Circle!"), 500, 240);
            }
        }

        backButton.render(g);
    }

}
