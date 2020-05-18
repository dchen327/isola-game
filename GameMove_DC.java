public class GameMove_DC {
    private int moveR, moveC;
    private int destroyR, destroyC;

    public GameMove_DC(int moveR, int moveC, int destroyR, int destroyC) {
        this.moveR = moveR;
        this.moveC = moveC;
        this.destroyR = destroyR;
        this.destroyC = destroyC;
    }

    public int getMoveR() {
        return moveR;
    }

    public int getMoveC() {
        return moveC;
    }

    public int getDestroyR() {
        return destroyR;
    }

    public int getDestroyC() {
        return destroyC;
    }
}