public class Board_DC {
    private int boardSize;
    private String[][] grid;
    private String currPlayer;
    Player_DC player1, player2;

    // initialize empty grid (all null), then place two player pieces
    public Board_DC(int boardSize, Player_DC player1, Player_DC player2) {
        this.boardSize = boardSize;
        this.player1 = player1;
        this.player2 = player2;
        grid = new String[boardSize][boardSize];
        grid[player1.getCurrR()][player1.getCurrC()] = "1";
        grid[player2.getCurrR()][player2.getCurrC()] = "2";
        currPlayer = "1";
    }

    // place pieces in spots as specified by GameMove_DC argument
    public void makeMove(GameMove_DC move) {
        // there's definitely a better way to do this
        int moveR = move.getMoveR();
        int moveC = move.getMoveC();
        int destroyR = move.getDestroyR();
        int destroyC = move.getDestroyC();

        grid[moveR][moveC] = currPlayer;
        if (currPlayer.equals("1")) {
            grid[player1.getCurrR()][player1.getCurrC()] = null;
            player1.setCurrR(moveR);
            player1.setCurrC(moveC);
        }
        else {
            grid[player2.getCurrR()][player2.getCurrC()] = null;
            player2.setCurrR(moveR);
            player2.setCurrC(moveC);
        }
        grid[destroyR][destroyC] = "X";
        changePlayer();
    }

    // swap player
    public void changePlayer() {
        if (currPlayer.equals("1")) {
            currPlayer = "2";
        }
        else {
            currPlayer = "1";
        }
    }

    public String toString() {
        String board = "";
        for (int r = 0; r < boardSize; r++) {
            for (int c = 0; c < boardSize; c++) {
                if (grid[r][c] == null) {  // no pieces here
                    board += "-";
                }
                else {  // if not empty then it's 1, 2, or X
                    board += grid[r][c];
                }
            }
            board += "\n";  // add newline to split rows
        }
        return board;
    }
}