import java.util.ArrayList;

public class MinimaxAI_DC {
    private int boardSize;
    private int currR, currC;
    private String playerState;
    private int depth;

    public MinimaxAI_DC(int boardSize, int initR, int initC) {
        this.boardSize = boardSize;
        currR = initR;
        currC = initC;
        depth = 3;
    }

    public int[] getAction(GameState_DC gameState) {
        int[] bestAction = {-2, -2};
        double bestVal = Double.POSITIVE_INFINITY;
        for (int[] action : gameState.getPossibleActions()) {
            double val = minimax(gameState.generateSuccessor(action), depth, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
            if (val < bestVal) {
                bestVal = val;
                bestAction = action;
            }
        }
        return bestAction;
    }

    private double minimax(GameState_DC gameState, int depth, double alpha, double beta) {
        if (depth == 0 || gameState.gameWinner() != 0) {  // depth = 0 or game over
            return evaluate(gameState);
        }
        ArrayList<int[]> actions = gameState.getPossibleActions();
        if (gameState.getCurrPlayer().equals(1)) {
            for (int[] action : actions) {
                double v = minimax(gameState.generateSuccessor(action), depth - 1, alpha, beta);
                alpha = Math.max(alpha, v);
                if (alpha >= beta) {
                    break;
                }
            }
            return alpha;
        }
        else {
            for (int[] action : actions) {
                double v = minimax(gameState.generateSuccessor(action), depth - 1, alpha, beta);
                beta = Math.min(beta, v);
                if (alpha >= beta) {
                    break;
                }
            }
            return beta;
        }

    }

    private double evaluate(GameState_DC gameState) {
        if (gameState.gameWinner() == 1) {
            return Double.POSITIVE_INFINITY;
        }
        if (gameState.gameWinner() == 2) {
            return Double.NEGATIVE_INFINITY;
        }

        double p1Pos = gameState.getPossibleMoves(1).size() - centerProximity(gameState, 1);
        double p2Pos = gameState.getPossibleMoves(2).size() - centerProximity(gameState, 2);

        return p1Pos - p2Pos;

    }

    private double centerProximity(GameState_DC gameState, int playerNum) {
        double centerX = (boardSize - 1) / 2.;
        double centerY = (boardSize - 1) / 2.;

        double posX, posY;

        if (playerNum == 1) {
            posX = gameState.getP1Pos()[0];
            posY = gameState.getP1Pos()[1];
        }
        else {
            posX = gameState.getP2Pos()[0];
            posY = gameState.getP2Pos()[1];
        }

        return Math.sqrt(Math.pow(posX - centerX, 2) + Math.pow(posY - centerY, 2));
    }

    public void setCurrR(int newR) {
        currR = newR;
    }

    public void setCurrC(int newC) {
        currC = newC;
    }

    public int getCurrR() {
        return currR;
    }

    public int getCurrC() {
        return currC;
    }
}