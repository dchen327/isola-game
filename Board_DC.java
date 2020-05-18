public class Board_DC {
    private int boardSize;
    private String[][] grid;

    public Board_DC(int boardSize) {
        this.boardSize = boardSize;
        grid = new String[boardSize][boardSize];
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