// javac $(find . -name "*.java")  && java IsolaDriver_DC 
public class IsolaDriver_DC {
    public static void main(String[] args) {
        int boardSize = 7;
        int startingPlayer = 1;
        IsolaGame_DC game = new IsolaGame_DC(boardSize, startingPlayer);
        int winner = game.playGameAndGetWinner();
        StdDraw.pause(1000);
        while (true) {
            if (StdDraw.isMousePressed()) {
                System.exit(0);
            }
        }
    }
}