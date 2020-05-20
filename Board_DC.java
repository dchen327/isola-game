import java.util.ArrayList;

public class Board_DC {
    private int boardSize;
    private String[][] grid;
    private String currPlayer;
    private int currMoveNum;
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
        currMoveNum = 1;
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
            if (currMoveNum <= 2) {  // the spawn positions are considered destroyed after the players move
                grid[player1.getCurrR()][player1.getCurrC()] = "X";  // destroy spawn pos
            }
            else {
                grid[player1.getCurrR()][player1.getCurrC()] = null;
            }
            player1.setCurrR(moveR);
            player1.setCurrC(moveC);
        }
        else {
            if (currMoveNum <= 2) {
                grid[player2.getCurrR()][player2.getCurrC()] = "X";
            }
            else {
                grid[player2.getCurrR()][player2.getCurrC()] = null;
            }
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

    // returns 0 if no winner, 1 or 2 if either player has won
    public int gameWinner() {
        if (getPossibleMoves(1).size() == 0) {
            return 1;
        }
        else if (getPossibleMoves(2).size() == 0) {
            return 2;
        }
        return 0;
    }

    public ArrayList<int[]> getPossibleMoves(int playerNum) {
        ArrayList<int[]> possibleMoves = new ArrayList<int[]>();
        int pR, pC;
        if (playerNum == 1) {
            pR = player1.getCurrR();
            pC = player1.getCurrC();
        }
        else {
            pR = player2.getCurrR();
            pC = player2.getCurrC();
        }
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (!(i == 0 && j == 0) && 0 <= pR + i && pR + i < boardSize && 0 <= pC + j && pC + j <= boardSize) {
                    if (grid[pR + i][pC + j] == null) {
                        int move[] =  {pR + i, pC + j};
                        possibleMoves.add(move);
                    }
                }
            }
        }
        for (int[] move : possibleMoves) {
            System.out.print(move[0] + " " + move[1] + ", ");
        }
        System.out.println();
        return possibleMoves;
    }

    public String toString() {
        String board = "";
        for (int r = 0; r < boardSize; r++) {
            for (int c = 0; c < boardSize; c++) {
                if (grid[r][c] == null) {  // no pieces here
                    board += " -";
                }
                else {  // if not empty then it's 1, 2, or X
                    board += " " + grid[r][c];
                }
            }
            board += "\n";  // add newline to split rows
        }
        return board;
    }
}