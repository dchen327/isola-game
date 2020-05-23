import java.util.ArrayList;

public class MinimaxAI_DC {
    private int boardSize;
    private int currR, currC;
    private String playerState;
    private int minimaxDepth;

    public MinimaxAI_DC(int boardSize, int initR, int initC) {
        this.boardSize = boardSize;
        currR = initR;
        currC = initC;
        minimaxDepth = 4;
    }

    public MinimaxAI_DC(MinimaxAI_DC player) {
        this.boardSize = player.boardSize;
        this.currR = player.currR;
        this.currC = player.currC;
        this.minimaxDepth = player.minimaxDepth;
    }

    public int[] getAction(GameState_DC gameState) {
        int[] bestAction = {-2, -2};
        double bestVal = Double.POSITIVE_INFINITY;
        for (int[] action : gameState.getPossibleActions()) {
            double val = minimax(gameState.generateSuccessor(action), minimaxDepth, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
            // System.out.println(action[0] + " " + action[1] + " " + val);
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
        String currPlayer = gameState.getCurrPlayer();
        String currAction = gameState.getCurrAction();
        
        if (currPlayer.equals("1")) {
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
        // take into account number of moves to prioritize faster wins
        if (gameState.gameWinner() == 1) {
            return 1000000.0 - gameState.getMoveNum();
        }
        if (gameState.gameWinner() == 2) {
            
            return -1000000.0 + gameState.getMoveNum();
        }
        if (gameState.gameWinner() == 3) {  // tie
            return -100.0;
        }

        double p1Pos = gameState.getPossibleMoves(1).size() - centerProximity(gameState, 1);
        double p2Pos = gameState.getPossibleMoves(2).size() - centerProximity(gameState, 2);

        return p1Pos - p2Pos;
        // not sure if this works
        // if (gameState.getCurrAction().equals("destroy")) {
        //     return (3 * p1Pos / 2) - p2Pos / 2;
        // }
        // else {
        //     return p1Pos / 2 - (3 * p2Pos / 2);
        // }
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