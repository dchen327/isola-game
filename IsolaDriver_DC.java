/**
 * This is the driver class for IsolaGame. It allows for changing board size and starting player,
 * then plays a game until completion, allowing the user to click to exit at the end.
 * 
 * This program should be run with the following command to compile all java files in the directory
 * and run the driver:
 *
 * javac $(find . -name "*.java")  && java IsolaDriver_DC 
 *
 * @author David Chen
 * @version Java 1.8.0 - 3/17/20
 */

public class IsolaDriver_DC {
    public static void main(String[] args) {
        int boardSize = 7;
        int startingPlayer = 1;
        IsolaGame_DC game = new IsolaGame_DC(boardSize, startingPlayer);
        int winner = game.playGameAndGetWinner();
        StdDraw.pause(500);
        while (true) {
            if (StdDraw.isMousePressed()) {
                System.exit(0);
            }
        }
    }
}