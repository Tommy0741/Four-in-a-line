import java.util.Scanner;

public class Grid {

    private final int maxRow, maxCol;
    private final int[][] grid;
    private final int[] topRow;
    private int discs = 0;

    public Grid(int maxRow, int maxCol) {
        this.maxRow = maxRow;
        this.maxCol = maxCol;
        grid = new int[maxRow][maxCol];
        // topRow keep check the slot above the inserted disc
        topRow = new int[maxCol];
    }

    public Grid() {
        // initiate a 6 * 7 grid
        this(6, 7);
    }

    public void drawGrid() {
        // print out each row on the grid
        for (int i = maxRow - 1; i >= 0; i--) {
            System.out.print("\n" + i + " |");
            for (int j = 0; j < maxCol; j++) {
                System.out.print(" " + grid[i][j]);
            }
        }
        System.out.print("\n  +");
        for (int i = 0; i < maxCol; i++) {
            System.out.print("--");
        }
        System.out.print("\n   ");
        for (int i = 0; i < maxCol; i++) {
            System.out.print(" " + i);
        }
        System.out.println();
    }

    public void insert(Scanner sc, int player) {
        // the cycle won't end until 0-6 or 9 is inputted
        while (true) {
            // ask which column to insert disc
            System.out.println("Player " + player + " type a column (0-"
                    + (maxCol - 1) + ") or 9 to quit current game: ");
            // exit the game if the input is not a integer
            if (!sc.hasNextInt()) {
                System.out.println("Wrong input type!!");
                System.exit(0);
            }
            int j = sc.nextInt();
            if (j == 9) {
                // exit game
                System.out.println("Bye Bye!");
                System.exit(0);
            } else if (getError(j)) {
                // getError will handle the error verification
            } else {
                // add 1 to topRow[j] after insertion
                grid[topRow[j]++][j] = player;
                discs++;
                // output the new grid and check if anyone win
                drawGrid();
                checkResult(player, topRow[j] - 1, j);
                // break the outside loop
                break;
            }
        }
    }

    public boolean getError(int j) {
        if (j < 0 || j > maxCol - 1) {
            // input out of bound exception
            System.out.println("Range of column should be 0 to "+ (maxCol - 1) +"!");
            return true;
        }
        // check if the column is full
        if (topRow[j] < maxRow) {
            return false;
        }
        System.out.println("Column " + j + " is full!");
        return true;
    }

    public void checkResult(int player, int row, int col) {
        // check whether there are >4 in a row horizontally
        if (countPiece(player, row, col, 0, -1)
                + countPiece(player, row, col, 0, 1) + 1 >= 4)
            winHandler(player);
        // check whether there are >4 in a row vertically
        if (countPiece(player, row, col, -1, 0) + 1 >= 4)
            winHandler(player);
        // check whether there are >4 in a row diagonally "/"
        if (countPiece(player, row, col, 1, 1)
                + countPiece(player, row, col, -1, -1) + 1 >= 4)
            winHandler(player);
        // check whether there are >4 in a row diagonally "\"
        if (countPiece(player, row, col, 1, -1)
                + countPiece(player, row, col, -1, 1) + 1 >= 4)
            winHandler(player);
        if (discs == maxRow * maxCol) {
            System.out.println("It's a tie!!");
            System.exit(0);
        }
    }

    public int countPiece(int player, int row, int col, int directionY, int directionX) {
        int count = 0;
        row += directionY;
        col += directionX;
        // ensure the row/col is within the grid
        while (row >= 0 && row < maxRow && col >= 0 && col < maxCol) {
            // stop loop if opponent disc detected
            if (grid[row][col] != player)
                break;
            row += directionY;
            col += directionX;
            count++;
        }
        return count;
    }

    public void winHandler(int player) {
        System.out.println("Player " + player + " win this game!");
        System.exit(0);
    }
}
