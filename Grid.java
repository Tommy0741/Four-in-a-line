
import java.util.Scanner;

public class Grid {

    private final int[][] grid;
    private final int[] topRow;

    public Grid() {
        // initiate a 6 * 7 grid
        grid = new int[6][7];
        topRow = new int[6];
    }

    public void drawGrid() {
        // print out each row on the grid
        for (int i = grid.length - 1; i >= 0; i--) {
            System.out.print("\n" + i + " |");
            for (int j = 0; j < grid[i].length; j++) {
                System.out.print(" " + grid[i][j]);
            }
        }
        System.out.println("\n  +--------------");
        System.out.println("    0 1 2 3 4 5 6");
    }

    public void insert(Scanner sc, int player) {
        // the cycle won't end until 0-6 or 9 is inputted
        while (true) {
            // ask which column to insert disc
            System.out.println("Player " + player + " type a column (0-6) or 9 to quit current game: ");
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
                // find which row to place the disc
                grid[topRow[j]++][j] = player;
                // break the outside loop
                break;
            }
        }
        // output the new grid and check if anyone win
        drawGrid();
        checkResult(player);
        checkResult(player, i , j);
    }

    public boolean getError(int j) {
        if (j < 0 || j > 6) {
            // input out of bound exception
            System.out.println("Range of column should be 0 to 6!");
            return true;
        }
        // check if the column is full
        if (topRow[j] <= 5) {
            return false;
        }
        System.out.println("Column " + j + " is full!");
        return true;
    }

    public void checkResult(int player, int i, int j) {
        int k;
        // check 
        if (i )
    }

    public void checkResult(int player) {
        int k;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == player) {
                    // check vertically
                    if (i < 3) {
                        k = 1;
                        while (k < 4) {
                            if (grid[i + k][j] == player) {
                                k++; 
                            }else {
                                break;
                            }
                        }
                        // if k run through the whole loop,
                        // there is four discs in a vertical line
                        if (k == 4) {
                            winHandler(player);
                        }
                    }
                    // check horizontally
                    if (j < 4) {
                        k = 1;
                        while (k < 4) {
                            if (grid[i][j + k] == player) {
                                k++; 
                            }else {
                                break;
                            }
                        }
                        // if k run through the whole loop,
                        // there is four discs in a horizontal line
                        if (k == 4) {
                            winHandler(player);
                        }
                    }
                    // check "/" diagonally
                    if (i < 3 && j < 4) {
                        k = 1;
                        while (k < 4) {
                            if (grid[i + k][j + k] == player) {
                                k++; 
                            }else {
                                break;
                            }
                        }
                        // if k run through the whole loop,
                        // there is four discs in diagonal line "/"
                        if (k == 4) {
                            winHandler(player);
                        }
                    }
                    // check "\" diagonally
                    if (i > 2 && j < 4) {
                        k = 1;
                        while (k < 4) {
                            if (grid[i - k][j + k] == player) {
                                k++; 
                            }else {
                                break;
                            }
                        }
                        // if k run through the whole loop,
                        // there is four discs in diagonal line "\"
                        if (k == 4) {
                            winHandler(player);
                        }
                    }
                }
            }
        }
    }

    public void winHandler(int player) {
        System.out.println("Player " + player + " win this game!");
        System.exit(0);
    }
}
