
import java.util.Random;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.List;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Collectors;

public class AI {
    Random gen;
    int maxConsider = 1;

    public AI(int difficulty) {
        gen = new Random();
        switch (difficulty) {
            case TicTacToe.EASY: maxConsider = 6; break;
            case TicTacToe.MEDIUM: maxConsider = 3; break;
            case TicTacToe.HARD: maxConsider = 2; break;
            case TicTacToe.IMPOSSIBLE: maxConsider = 1; break;
        }
    }

    public int play(Board board) {
        class ValueComparator implements Comparator<Integer> {
            Board board;
            public ValueComparator(Board b) { board = b; }

            public int compare(Integer v1, Integer v2) {
                return -Double.compare(value(board, v1), value(board, v2));
            }
        }

        PriorityQueue<Integer> bestMoves = new PriorityQueue<>(9, new ValueComparator(board));
        legalMoves(board).boxed().forEach(bestMoves::add);

        List<Integer> candidates = bestMoves.stream().limit(maxConsider).collect(Collectors.toList());
        return candidates.get(gen.nextInt(candidates.size()));
    }


    public double value(Board position, int move) {
        return values(position.copy(), position.nextPlayer(), move, 1).average().orElse(0.0);
    }

    public IntStream legalMoves(Board position) {
        return IntStream.range(0, 9).filter(i -> !position.taken(i));
    }


    private DoubleStream values(Board position, int player, int move, int depth) {
        try {
            position.take(move);
        } catch (AlreadyTakenException e) {
            return DoubleStream.of(0);
        }

        if (position.gameEnded()) {
            double complexityPenalty = 1.0/Math.pow(depth, 2);

            if (position.getWinner() == -1) {
                return DoubleStream.of(0.8*complexityPenalty);
            } else if (position.getWinner() != player) {
                return DoubleStream.of(-1*complexityPenalty);
            } else {
                return DoubleStream.of(1*complexityPenalty);
            }
        } else {
            return legalMoves(position).boxed().flatMapToDouble(i ->
                    values(position.copy(), player, i, depth+1)
            );
        }
    }
}
