import java.util.ArrayList;
import java.awt.Font;

public class GameState_DC {
    private int boardSize;
    private String[][] grid;
    private int currPlayer;
    private String currAction;
    private int currMoveNum;
    Player_DC player1;
    MinimaxAI_DC player2;

    // initialize empty grid (all ""), then place two player pieces
    public GameState_DC(int boardSize, Player_DC player1, MinimaxAI_DC player2, int currPlayer) {
        this.boardSize = boardSize;
        this.player1 = player1;
        this.player2 = player2;
        grid = new String[boardSize][boardSize];
        for (int r = 0; r < boardSize; r++) {
            for (int c = 0; c < boardSize; c++) {
                grid[r][c] = "";
            }
        }
        grid[player1.getCurrR()][player1.getCurrC()] = "1";
        grid[player2.getCurrR()][player2.getCurrC()] = "2";
        this.currPlayer = currPlayer;
        currAction = "move";
        currMoveNum = 0;
    }

    // used for cloning GameState
    public GameState_DC(GameState_DC gameState) {
        this.boardSize = gameState.boardSize;
        this.grid = new String[boardSize][boardSize];
        for (int r = 0; r < boardSize; r++) {
            for (int c = 0; c < boardSize; c++) {
                this.grid[r][c] = gameState.grid[r][c];
            }
        }
        this.currPlayer = gameState.currPlayer;
        this.currAction = gameState.currAction;
        this.currMoveNum = gameState.currMoveNum;
        this.player1 = new Player_DC(gameState.player1);
        this.player2 = new MinimaxAI_DC(gameState.player2);
    }

    // place pieces in spots
    public void makeMove(int[] move) {
        int moveR = move[0];
        int moveC = move[1];

        grid[moveR][moveC] = Integer.toString(currPlayer);
        if (currPlayer == 1) {
            grid[player1.getCurrR()][player1.getCurrC()] = "";
            player1.setCurrR(moveR);
            player1.setCurrC(moveC);
        }
        else {
            grid[player2.getCurrR()][player2.getCurrC()] = "";
            player2.setCurrR(moveR);
            player2.setCurrC(moveC);
        }
        currMoveNum++;
        currAction = "destroy";
    }

    // destroy given spot[]
    public void destroyLoc(int[] destroy) {
        grid[destroy[0]][destroy[1]] = "X";
        changePlayer();
        currMoveNum++;
        currAction = "move";
    }

    // swap player
    public void changePlayer() {
        // if (currPlayer.equals("1")) {
        //     currPlayer = "2";
        // }
        // else {
        //     currPlayer = "1";
        // }
        currPlayer = 3 - currPlayer;
    }

    // returns 0 if no winner, 1 or 2 if either player has won
    public int gameWinner() {
        int numP1Moves = getPossibleMoves(1).size();
        int numP2Moves = getPossibleMoves(2).size();

        if (numP1Moves == 0 && numP2Moves == 0) {  // tie game
            return 3;
        }
        else if (numP1Moves == 0) {
            return 2;
        }
        else if (numP2Moves == 0) {
            return 1;
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
                if (!(i == 0 && j == 0) && 0 <= pR + i && pR + i < boardSize && 0 <= pC + j && pC + j < boardSize) {
                    if (grid[pR + i][pC + j].isEmpty()  ) {
                        int move[] =  {pR + i, pC + j};
                        possibleMoves.add(move);
                    }
                }
            }
        }

        return possibleMoves;
    }

    public ArrayList<int[]> getPossibleActions() {
        if (currAction.equals("move")) {
            if (currPlayer == 1) {
                return getPossibleMoves(1);
            }
            else {
                return getPossibleMoves(2);
            }
        }
        else {  // destroy
            ArrayList<int[]> possibleDestroy = new ArrayList<int[]>();
            // look for places to destroy in the 5x5 grid surrounding each player
            // doesn't check all possible empty tiles, gives some speedup
            int p1R = player1.getCurrR();
            int p1C = player1.getCurrC();
            int p2R = player2.getCurrR();
            int p2C = player2.getCurrC();

            for (int i = 0; i < boardSize; i++) {
                for (int j = 0; j < boardSize; j++) {
                    if (grid[i][j].isEmpty()) {
                        if ((Math.abs(i - p1R) <= 2 && Math.abs(j - p1C) <= 2) || 
                            Math.abs(i - p2R) <= 2 && Math.abs(j - p2C) <= 2) {
                            int destroy[] = {i, j};
                            possibleDestroy.add(destroy);
                        }
                    }
                }
            }

            return possibleDestroy;
        }
    }

    // take the action in the current state -> return new state
    public GameState_DC generateSuccessor(int[] action) {
        GameState_DC newState = new GameState_DC(this);
        if (newState.getCurrAction().equals("move")) {
            newState.makeMove(action);
        }
        else {
            newState.destroyLoc(action);
        }

        return newState;
    }

    public boolean isValidMove(Player_DC player, int[] move) {
        int r = move[0];
        int c = move[1];
        return (Math.abs(r - player.getCurrR()) <= 1 && Math.abs(c - player.getCurrC()) <= 1 && grid[r][c].isEmpty()    );
    }

    public boolean isValidDestroy(int[] destroy) {
        int r = destroy[0];
        int c = destroy[1];
        return (0 <= r && r < boardSize && 0 <= c && c < boardSize && grid[r][c].isEmpty()  );
    }

    public void stdDrawInit() {
        StdDraw.setCanvasSize(100 * boardSize, 100 * (boardSize + 1));
        StdDraw.setXscale(0, boardSize);
        StdDraw.setYscale(0, boardSize + 1);

        Font font = new Font("Sans Serif", Font.PLAIN, 30);
        StdDraw.setFont(font);

        StdDraw.enableDoubleBuffering();
    }

    // given a row and column, convert to X and Y for drawing on canvas
    public double[] convertRCToXY(double r, double c) {
        // in 2D array, we use rows and columns
        // however, to draw we need X and Y where (0, 0) is bottom left
        // we swap rows and columns and then subtract column from total height
        double[] xy = {c + 0.5, boardSize - r - 0.5};
        return xy;
    }

    public void draw() {
        StdDraw.clear();
        // draw horizontal and vertical gridlines
        for (int i = 0; i <= boardSize; i++) {  
            StdDraw.line(0, i, boardSize, i);
            StdDraw.line(i, 0, i, boardSize);
        }

        // draw game pieces and destroyed tiles
        for (int r = 0; r < boardSize; r++) {
            for (int c = 0; c < boardSize; c++) {
                double[] xy = convertRCToXY(r, c);
                double x = xy[0];
                double y = xy[1];
                if (grid[r][c].equals("1")) {  // p1
                    StdDraw.setPenColor(StdDraw.GREEN);
                    StdDraw.filledCircle(x, y, 0.5);
                    StdDraw.setPenColor(StdDraw.BLACK);
                }
                else if (grid[r][c].equals("2")) { // p2
                    StdDraw.setPenColor(StdDraw.BLUE);
                    StdDraw.filledCircle(x, y, 0.5);
                    StdDraw.setPenColor(StdDraw.BLACK);
                }
                else if (grid[r][c].equals("X")) {  // destroyed
                    StdDraw.filledSquare(x, y, 0.5);
                }
            }
        }

        // text info above board: 'Player 1: destroy'
        String currInfo = "Player " + currPlayer + ": " + currAction;
        if (gameWinner() != 0) {  // game over
            currInfo = "Player " + gameWinner() + " wins! Click to EXIT.";
        }
        StdDraw.text(boardSize / 2.0, boardSize + 0.5, currInfo);

        StdDraw.show();
    }

    public int[] getP1Pos() {
        int[] rc = {player1.getCurrR(), player1.getCurrC()};
        return rc;
    }
    
    public int[] getP2Pos() {
        int[] rc = {player2.getCurrR(), player2.getCurrC()};
        return rc;
    }

    public int getCurrPlayer() {
        return currPlayer;
    }

    public String getCurrAction() {
        return currAction;
    }

    public int getMoveNum() {
        return currMoveNum;
    }

    public String toString() {
        String board = "";
        for (int r = 0; r < boardSize; r++) {
            for (int c = 0; c < boardSize; c++) {
                if (grid[r][c].isEmpty()    ) {  // no pieces here
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