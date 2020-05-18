import java.util.Scanner;

public class Player_DC {
    private String gamePiece;

    public Player_DC(String gamePiece) {
        this.gamePiece = gamePiece;
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
        return GameMove_DC(moveR, moveC, destroyR, destroyC);
    }
}