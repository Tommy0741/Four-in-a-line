
import java.util.Scanner;

public class main {
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int player = 1;
        Grid grid = new Grid();
        grid.drawGrid();
        while (true) {
            grid.insert(sc, player);
            // switch player
            if (player == 1) {
                player = 2;
            } else {
                player = 1;
            }
        }
    }
}