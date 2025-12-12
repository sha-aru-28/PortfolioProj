/**
 * Use Case 2: Obstacle interaction demonstration. Shows how to test obstacle
 * movement mechanics and collision detection using the CRGame component.
 */
public final class CRGameDemo2 {

    /**
     * Private constructor.
     */
    private CRGameDemo2() {
    }

    /**
     * Main method demonstrating obstacle movement and collisions.
     *
     * @param args
     *            command line arguments (not used)
     */
    public static void main(String[] args) {

        CRGame1L game = new CRGame1L();

        System.out.println("=== Obstacle Movement Test ===");
        System.out.println();

        game.addObstacle(2, 0);
        game.addObstacle(1, 1);
        game.addObstacle(3, 1);

        System.out.println("Initial setup with obstacles:");
        game.printGrid();
        System.out.println();

        final int turns = 6;
        for (int turn = 1; turn <= turns; turn++) {
            System.out.println("Turn " + turn + ":");

            game.moveObstacles();

            final int two = 2;
            if (turn % two == 0) {
                game.addObstacle(turn % game.width(), 0);
            }

            game.printGrid();

            if (game.checkCollision()) {
                System.out.println(">>> COLLISION! Player hit an obstacle!");
                break;
            }

            System.out.println("Obstacles remaining: " + game.obstacleCount());
            System.out.println();
        }

        System.out.println("=== Final Status ===");
        System.out.println("Game Over: " + game.isGameOver());
        System.out.println("Final Score: " + game.score());
        System.out.println("Obstacles on grid: " + game.obstacleCount());
    }
}
