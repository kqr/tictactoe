
import java.util.*;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Collectors;

public class AI {
    Random gen;
    int maxConsider = 1;

    public AI(int difficulty) {
        gen = new Random();
        switch (difficulty) {
            case TicTacToe.EASY: maxConsider = 4; break;
            case TicTacToe.MEDIUM: maxConsider = 3; break;
            case TicTacToe.HARD: maxConsider = 2; break;
            case TicTacToe.IMPOSSIBLE: maxConsider = 1; break;
        }
    }

    public int play(Board board) {
        double[] values = IntStream.range(0, 9).mapToDouble(i -> value(board, i)).toArray();

        class ValueComparator implements Comparator<Integer> {
            public int compare(Integer v1, Integer v2) {
                return -Double.compare(values[v1], values[v2]);
            }
        }

        PriorityQueue<Integer> bestMoves =
                legalMoves(board).boxed().collect(Collectors.toCollection(() ->
                                new PriorityQueue<>(9, new ValueComparator())));

        int prevMove = bestMoves.peek();

        // i keeps track of how many unique values we've encountered
        // take keeps track of how many actual values that is
        int i = 0, take = 0;
        for (int move : bestMoves) {
            if (values[move] < values[prevMove]) {
                i++;
                if (i >= maxConsider) {
                    break;
                }
            }

            take++;
        }

        List<Integer> candidates = bestMoves.stream().limit(take).collect(Collectors.toList());
        return candidates.get(gen.nextInt(candidates.size()));
    }


    public double value(Board position, int move) {
        return scores(position.copy(), position.nextPlayer(), move, 1).average().orElse(0.0);
    }

    public IntStream legalMoves(Board position) {
        return IntStream.range(0, 9).filter(i -> !position.taken(i));
    }


    private DoubleStream scores(Board position, int player, int move, int depth) {
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
                    scores(position.copy(), player, i, depth + 1)
            );
        }
    }
}
