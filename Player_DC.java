import java.util.Scanner;

public class Player_DC {
    private String gamePiece;
    private int currR, currC;
    private String playerState;

    public Player_DC(int initR, int initC) {
        // this.gamePiece = gamePiece;
        currR = initR;
        currC = initC;
        playerState = "nothing";
    }

    // prompt the user for a move through command line
    public GameMove_DC getMoveCLI() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter row for move: ");
        int moveR = scan.nextInt();
        System.out.println("Enter col for move: ");
        int moveC = scan.nextInt();  
        System.out.println("Enter row to destroy: ");
        int destroyR = scan.nextInt();  
        System.out.println("Enter row to destroy: ");
        int destroyC = scan.nextInt();

        return new GameMove_DC(moveR, moveC, destroyR, destroyC);
    }

    // get player's move on the drawn board
    public GameMove_DC getMove() {
        // TODO

        return new GameMove_DC(0, 0, 0, 0);
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