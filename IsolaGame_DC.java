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
    private Player_DC player2;
    boolean isGameOver;

    public IsolaGame_DC(int boardSize) {
        this.boardSize = boardSize;
        player1 = new Player_DC(0, boardSize / 2);
        player2 = new Player_DC(boardSize - 1, boardSize / 2);
        gameBoard = new GameState_DC(boardSize, player1, player2);
        gameBoard.stdDrawInit();
        isGameOver = false;
    }

    // run the game loop and return the player number who won
    public int playGameAndGetWinner() {
        gameBoard.draw();
        while (!isGameOver) {
            // System.out.println(gameBoard);
            if (StdDraw.isMousePressed()) {
                int c = (int) Math.round(StdDraw.mouseX() - 0.5);
                int r = boardSize - (int) Math.round(StdDraw.mouseY() + 0.5);
                System.out.println(r + " " + c);
            }
            // int[] move1 = player1.getMoveCLI();
            // gameBoard.makeMove(move1);
            // System.out.println(gameBoard);
            // gameBoard.draw();
            // int[] destroy1 = player1.getDestroyCLI();
            // gameBoard.destroyLoc(destroy1);
            // System.out.println(gameBoard);
            // gameBoard.draw();
            // ArrayList<int[]> possibleMoves1 = gameBoard.getPossibleMoves(1);
            // if (possibleMoves1.size() == 0) {  // stuck, game over
            //     isGameOver = true;
            //     return 2;
            // }
            // int[] move2 = player2.getMoveCLI();
            // gameBoard.makeMove(move2);
            // System.out.println(gameBoard);
            // gameBoard.draw();
            // int[] destroy2 = player2.getDestroyCLI();
            // gameBoard.destroyLoc(destroy2);
            // System.out.println(gameBoard);
            // gameBoard.draw();
            // ArrayList<int[]> possibleMoves2 = gameBoard.getPossibleMoves(2);
            // if (possibleMoves2.size() == 0) {  // stuck, game over
            //     isGameOver = true;
            //     return 1;
            // }
        }
        return 0;
    }
}