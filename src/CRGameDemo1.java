/**
 * Use Case 1: Basic gameplay demonstration. Shows how to create a game
 * instance, move the player, and check game state using the CRGame component.
 */
public final class CRGameDemo1 {

    /**
     * Private constructor.
     */
    private CRGameDemo1() {
    }

    /**
     * Main method demonstrating basic gameplay.
     *
     * @param args
     *            command line arguments (not used)
     */
    public static void main(String[] args) {

        CRGame1L game = new CRGame1L();

        System.out.println("=== Basic Crossy Road Game Demo ===");
        System.out.println();

        System.out.println("Initial game state:");
        game.printGrid();
        System.out.println("Score: " + game.score());
        System.out.println();

        game.addObstacle(1, 2);
        game.addObstacle(3, 1);
        game.addObstacle(2, 0);

        System.out.println("After adding obstacles:");
        game.printGrid();
        System.out.println();

        final int moves = 3;
        for (int i = 0; i < moves; i++) {
            System.out.println("Moving up...");
            game.movePlayer("up");
            game.printGrid();
            System.out.println("Score: " + game.score());
            System.out.println();

            if (game.checkCollision()) {
                System.out.println("COLLISION DETECTED!");
                break;
            }
        }

        if (game.isGameOver()) {
            System.out.println("Game Over!");
        } else {
            System.out.println("Game still in progress.");
        }
        System.out.println("Final Score: " + game.score());
    }
}
