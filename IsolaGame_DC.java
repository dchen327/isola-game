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

public class IsolaGame_DC {
    private Board_DC gameBoard;
    private int boardSize;
    private Player_DC player;

    public IsolaGame_DC(int boardSize) {
        this.boardSize = boardSize;
        gameBoard = new Board_DC(boardSize);
        System.out.println(gameBoard);
    }
}