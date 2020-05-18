import java.util.Scanner;

public class Player_DC {
    private String gamePiece;
    private int currR, currC;

    public Player_DC(int initR, int initC) {
        // this.gamePiece = gamePiece;
        currR = initR;
        currC = initC;
    }

    // prompt the user for a move
    public GameMove_DC getMove() {
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