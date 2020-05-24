/**
 * This class provides methods for human players. It can prompt for and receive input through text 
 * using java.util.Scanner. In addition, it can return a player's move based on mouse position.
 * Mutator and accessor methods are provided for the player's current location.
 *
 * @author David Chen
 * @version Java 1.8.0 - 3/17/20
 */

import java.util.Scanner;

public class Player_DC {
    private int boardSize;
    private int currR, currC;

    public Player_DC(int boardSize, int initR, int initC) {
        this.boardSize = boardSize;
        currR = initR;
        currC = initC;
    }

    // for making a copy of the object
    public Player_DC(Player_DC player) {
        this.boardSize = player.boardSize;
        this.currR = player.currR;
        this.currC = player.currC;
    }

    // prompt the user for a move through command line
    public int[] getMoveCLI() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter row for move: ");
        int moveR = scan.nextInt();
        System.out.println("Enter col for move: ");
        int moveC = scan.nextInt();  

        int move[] = {moveR, moveC};
        return move;
    }

    public int[] getDestroyCLI() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter row to destroy: ");
        int destroyR = scan.nextInt();  
        System.out.println("Enter row to destroy: ");
        int destroyC = scan.nextInt();

        int destroy[] = {destroyR, destroyC};
        return destroy;
    }

    // get player's move on the drawn board
    public int[] getMove() {
        return nearestLoc();
    }

    public int[] getDestroy() {
        return nearestLoc();
    }

    public int[] nearestLoc() {
        // basically just find the closest square with some rounding
        // then swap r and c and subtract c from boardSize to convert between XY and RC
        int c = (int) Math.round(StdDraw.mouseX() - 0.5);
        int r = boardSize - (int) Math.round(StdDraw.mouseY() + 0.5);
        // ensure values are actually on the game board
        r = Math.max(r, 0);
        r = Math.min(r, boardSize - 1);
        c = Math.max(c, 0);
        c = Math.min(c, boardSize - 1);
        int[] rc = {r, c};
        return rc;
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