// javac $(find . -name "*.java")  && java IsolaDriver_DC 
public class IsolaDriver_DC {
    public static void main(String[] args) {
        IsolaGame_DC game = new IsolaGame_DC(7);
        int winner = game.playGameAndGetWinner();
        System.out.println(winner);
    }
}