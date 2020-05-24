/**
Isola is a 2 player game played on a 7x7 board with the following rules: 

1) Each of the two players has one piece.
2) The board has 7x7 positions which initially contain squares, except for the initial positions of the pieces.
3) A move consists of two subsequent actions:
   1) moving one's piece to a neighboring (horizontally, vertically, or diagonally) field that contains a square but not the opponent's piece,
   2) removing any square with no piece on it
4) If a player cannot move any more, he/she loses the game.

Info from (https://www.cs.umb.edu/~yunxu/isola/rules.html)


 * @author David Chen
 * @version Java 1.8.0 - 3/17/20
 */

import java.util.ArrayList;

public class IsolaGame_DC {
    private GameState_DC gameBoard;
    private int boardSize;
    private Player_DC player1;
    private MinimaxAI_DC player2;
    private int currPlayer;
    boolean isGameOver;

    public IsolaGame_DC(int boardSize, int currPlayer) {
        this.boardSize = boardSize;
        this.currPlayer = currPlayer;
        player1 = new Player_DC(boardSize, 0, boardSize / 2);
        // player2 = new Player_DC(boardSize, boardSize - 1, boardSize / 2);
        player2 = new MinimaxAI_DC(boardSize, boardSize - 1, boardSize / 2);
        gameBoard = new GameState_DC(boardSize, player1, player2, currPlayer);
        gameBoard.stdDrawInit();
        isGameOver = false;
    }

    // run the game loop and return the player number who won
    public int playGameAndGetWinner() {
        gameBoard.draw();
        while (!isGameOver) {
            if (currPlayer == 1) {
                player1Turn();
            }
            else {
                player2Turn();
            }
            if (gameBoard.gameWinner() != 0) {
                return gameBoard.gameWinner();
            }
            // swap player
            currPlayer = 3 - currPlayer;
        }
        return 0;
    }

    public void player1Turn() {
        int[] move1 = {-2, -2};
        while (!gameBoard.isValidMove(player1, move1)) {
            if (StdDraw.isMousePressed()) {
                move1 = player1.getMove();
            }
        }
        StdDraw.pause(50);
        gameBoard.makeMove(move1);
        gameBoard.draw();
        int[] destroy1 = {-2, -2};
        while (!gameBoard.isValidDestroy(destroy1)) {
            if (StdDraw.isMousePressed()) {
                destroy1 = player1.getDestroy();
            }
        }
        StdDraw.pause(50);
        gameBoard.destroyLoc(destroy1);
        gameBoard.draw();
    }

    public void player2Turn() {
        int[] move2 = player2.getAction(gameBoard);
        gameBoard.makeMove(move2);
        gameBoard.draw();
        int[] destroy2 = player2.getAction(gameBoard);
        gameBoard.destroyLoc(destroy2);
        gameBoard.draw();
    }

}