import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * simplified implementation for proof of concept for crossy road.
 */
public class CRGameConcept {

    /**
     * player position x value.
     */
    private int playerX;

    /**
     * player position y value.
     */
    private int playerY;

    /**
     * keeping track of if game is still to continue.
     */
    private boolean gameOver;

    /**
     * keeping track of score value.
     */
    private int score;

    /**
     * grid dimensions for gameboard wicth.
     */
    private static final int WIDTH = 5;

    /**
     * grid dimensions for gameboard height.
     */
    private static final int HEIGHT = 5;

    /**
     * obstacle postions.
     */

    private class Obstacle {

        /**
         * obstacle x position.
         */
        private int x;

        /**
         * obstacle y position.
         */
        private int y;

        /**
         * constructor for obstacles with given x and y positions.
         *
         * @param xPos
         *            x position
         * @param yPos
         *            y position
         */
        Obstacle(int xPos, int yPos) {
            this.x = xPos;
            this.y = yPos;
        }
    }

    /**
     * list storing all obstacles in the game.
     */
    private List<Obstacle> obstacles;

    /**
     * constructor initializing a new game.
     */
    public CRGameConcept() {
        this.obstacles = new ArrayList<Obstacle>();
        this.clear();
    }

    /**
     * sets the player position.
     *
     * @param x
     *            x coordinate
     * @param y
     *            y coordinate
     */
    public void setPlayerPosition(int x, int y) {
        this.playerX = x;
        this.playerY = y;
    }

    /**
     * adds an obstacle to the grid.
     *
     * @param x
     *            x coordinate
     * @param y
     *            y coordinate
     */
    public void addObstacle(int x, int y) {
        Obstacle o = new Obstacle(x, y);
        this.obstacles.add(o);
    }

    /**
     * clears and resets all game values to start state.
     */
    public void clear() {
        this.playerX = WIDTH / 2;
        this.playerY = HEIGHT - 1;
        this.obstacles.clear();
        this.score = 0;
        this.gameOver = false;
    }

    /**
     * moving the player 1 step.
     *
     * @param direction
     *            movement direction in 4 directions: up, down, left, right.
     */
    public void movePlayer(String direction) {
        if (direction.equalsIgnoreCase("up")) {
            this.playerY = this.playerY - 1;
        } else if (direction.equalsIgnoreCase("down")) {
            this.playerY = this.playerY + 1;
        } else if (direction.equalsIgnoreCase("left")) {
            this.playerX = this.playerX - 1;
        } else if (direction.equalsIgnoreCase("right")) {
            this.playerX = this.playerX + 1;
        }
        if (this.playerX < 0 || this.playerX >= WIDTH || this.playerY < 0
                || this.playerY >= HEIGHT) {
            this.gameOver = true;
        }
    }

    /**
     * moves all obstacles down 1 step.
     */
    public void moveObstacles() {
        for (Obstacle o : this.obstacles) {
            o.y = o.y + 1;
        }
    }

    /**
     * checks if player collides with obstacles.
     *
     * @return true if there is a collision and false if not
     */
    public boolean checkCollision() {
        boolean check = false;
        for (Obstacle o : this.obstacles) {
            if (o.x == this.playerX && o.y == this.playerY) {
                this.gameOver = true;
                check = true;
            }
        }
        return check;
    }

    /**
     * returns if game is over.
     *
     * @return true if game over and false if not
     */
    public boolean isGameOver() {
        return this.gameOver;
    }

    /**
     * returns the current score.
     *
     * @return current score
     */
    public int score() {
        return this.score;
    }

    /**
     * creates the grid with the player and obtacles.
     */
    public void printGrid() {
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                boolean printed = false;

                if (this.playerX == x && this.playerY == y) {
                    System.out.print("P ");
                    printed = true;
                } else {
                    for (Obstacle o : this.obstacles) {
                        if (o.x == x && o.y == y) {
                            System.out.print("O ");
                            printed = true;
                        }
                    }
                }

                if (!printed) {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * main method for game.
     *
     * @param args
     *
     */
    public static void main(String[] args) {
        CRGameConcept game = new CRGameConcept();
        Random rand = new Random();
        final Integer t = 3;
        System.out.println("Welcome to Crossy Road Game!");
        for (int i = 0; i < t; i++) {
            int randomX = rand.nextInt(WIDTH);
            int randomY = rand.nextInt(HEIGHT / 2);
            game.addObstacle(randomX, randomY);
        }

        // code for movement and checking collision

        if (game.isGameOver()) {
            System.out.println("Game over. Final score: " + game.score());
        } else {
            System.out.println("You Won! Final score: " + game.score());
        }
    }
}
