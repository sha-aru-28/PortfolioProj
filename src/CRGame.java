import java.util.Random;
import java.util.Scanner;

/**
 * Main file for the Crossy Road implementation.
 */
public final class CRGame {

    /**
     * Private constructor.
     */
    private CRGame() {
    }

    /**
     * Prints the grid.
     *
     * @param game
     *            the game instance
     */
    private static void printGrid(CRGame1L game) {
        int width = game.width();
        int height = game.height();

        int[] px = new int[1];
        int[] py = new int[1];
        game.getPlayerPosition(px, py);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {

                if (px[0] == x && py[0] == y) {
                    System.out.print("X ");
                } else {
                    boolean printed = false;
                    int obs = game.obstacleCount();
                    for (int i = 0; i < obs; i++) {
                        int[] ox = new int[1];
                        int[] oy = new int[1];
                        game.getObstaclePosition(i, ox, oy);

                        if (ox[0] == x && oy[0] == y) {
                            System.out.print("O ");
                            printed = true;
                            break;
                        }
                    }
                    if (!printed) {
                        System.out.print(". ");
                    }
                }
            }
            System.out.println();
        }
        System.out.println("Score: " + game.currentScore());
        System.out.println();
    }

    /**
     * Main method.
     *
     * @param args
     *
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Random rand = new Random();

        CRGame1L game = new CRGame1L();

        System.out.println("Welcome to Crossy Road!");
        System.out.println(
                "Controls: up = 'w', down = 's', left = 'a', right = 'd");
        System.out.println("Avoid obstacles and don't leave the grid!");
        System.out.println();

        final int initialObstacles = 3;
        for (int i = 0; i < initialObstacles; i++) {
            int x = rand.nextInt(game.width());
            int y = rand.nextInt(game.height() / 2);
            game.addObstacle(x, y);
        }

        while (!game.gameOverFlag()) {
            printGrid(game);

            System.out.print("Move (w/a/s/d): ");
            String input = in.nextLine().trim().toLowerCase();

            String move = "";
            if (input.equals("w")) {
                move = "up";
            } else if (input.equals("s")) {
                move = "down";
            } else if (input.equals("a")) {
                move = "left";
            } else if (input.equals("d")) {
                move = "right";
            } else {
                System.out.println("Invalid move. Try again.\n");
                continue;
            }

            game.movePlayer(move);
            game.moveObstacles();
            game.checkCollision();

            int x = rand.nextInt(game.width());
            game.addObstacle(x, 0);
        }

        System.out.println("GAME OVER!");
        printGrid(game);
        System.out.println("Final score: " + game.currentScore());

        in.close();
    }
}
