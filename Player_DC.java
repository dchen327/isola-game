import java.util.Scanner;

public class Player_DC {
    private int boardSize;
    private int currR, currC;
    private String playerState;

    public Player_DC(int boardSize, int initR, int initC) {
        this.boardSize = boardSize;
        currR = initR;
        currC = initC;
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
        // TODO
        // can only move one position in the 8 directions
        int[] move = {0, 0};
        return move;
    }

    public int[] getDestroy() {
        // TODO
        int[] move = {0, 0};
        return move;
    }

    public int[] nearestLoc() {
        // basically just find the closest square with some rounding
        // then swap r and c and subtract c from boardSize to convert between XY and RC
        int c = (int) Math.round(StdDraw.mouseX() - 0.5);
        int r = boardSize - (int) Math.round(StdDraw.mouseY() + 0.5);
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