import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * kernel implementation for the crossy road game (crgame).
 *
 * convention: - 0 <= playerX < WIDTH - 0 <= playerY < HEIGHT - obstacles list
 * contains only obstacles with 0 <= x < WIDTH and 0 <= y < HEIGHT - score >= 0
 * - gameOver = true iff player collided or out of bounds
 *
 * correspondence: - abstract state (playerX, playerY, obstacles, score,
 * gameOver) - rep fields map directly to abstract state
 */
public class CRGame1L extends CRGameSecondary {

    /**
     * grid width (columns).
     */
    private static final int WIDTH = 5;

    /**
     * grid height (rows).
     */
    private static final int HEIGHT = 5;

    /**
     * player x position.
     */
    private int playerX;

    /**
     * player y position.
     */
    private int playerY;

    /**
     * obstacle positions.
     */
    private List<Obstacle> obstacles;

    /**
     * stored score.
     */
    private int score;

    /**
     * game over flag.
     */
    private boolean gameOver;

    /**
     * obstacle helper class.
     */
    private static class Obstacle {

        /**
         * obstacle x position.
         */
        private int x;

        /**
         * obstacle y position.
         */
        private int y;

        /**
         * constructor for obstacle at (xPos, yPos).
         *
         * @param xPos
         *            x coordinate
         * @param yPos
         *            y coordinate
         */
        Obstacle(int xPos, int yPos) {
            this.x = xPos;
            this.y = yPos;
        }
    }

    /**
     * constructor. creates empty rep and sets start state.
     */
    public CRGame1L() {
        super();
        this.obstacles = new ArrayList<Obstacle>();
        this.clear();
    }

    /**
     * creates a new instance of CRGame1L.
     *
     * @return new CRGame1L object
     * @ensures result is a new empty CRGame1L
     */
    @Override
    public CRGameEnhanced newInstance() {
        return new CRGame1L();
    }

    /**
     * Moves all the state from the given source CRGame into this one.
     *
     * @param source
     *            the CRGame to take state from
     * @clears source
     * @updates this
     * @ensures this = #source and source is empty
     */
    @Override
    public void transferFrom(CRGameEnhanced source) {

        CRGame1L src = (CRGame1L) source;
        this.playerX = src.playerX;
        this.playerY = src.playerY;
        this.score = src.score;
        this.gameOver = src.gameOver;
        this.obstacles = src.obstacles;
        src.obstacles = new ArrayList<>();
        src.clear();
    }

    /**
     * clears and resets all game values to start state.
     *
     * @clears obstacles
     * @ensures player is set to center-bottom, score = 0, gameOver = false
     */
    @Override
    public void clear() {
        this.playerX = WIDTH / 2;
        this.playerY = HEIGHT - 1;
        this.obstacles.clear();
        this.score = 0;
        this.gameOver = false;
    }

    /**
     * sets the player position.
     *
     * @param x
     *            x coordinate
     * @param y
     *            y coordinate
     * @updates player position
     * @ensures playerX = x and playerY = y (if within grid)
     */
    @Override
    public void setPlayerPosition(int x, int y) {
        this.playerX = x;
        this.playerY = y;
        if (this.playerX < 0 || this.playerX >= WIDTH || this.playerY < 0
                || this.playerY >= HEIGHT) {
            this.gameOver = true;
        }
    }

    /**
     * adds an obstacle at the given position.
     *
     * @param x
     *            x coordinate of obstacle
     * @param y
     *            y coordinate of obstacle
     * @updates obstacle set
     * @ensures an obstacle exists at (x, y) after call if coordinates valid
     */
    @Override
    public void addObstacle(int x, int y) {
        if (x >= 0 && x < WIDTH && y >= 0 && y < HEIGHT) {
            this.obstacles.add(new Obstacle(x, y));
        }
    }

    /**
     * moves the player one step in the given direction.
     *
     * @param direction
     *            movement direction ("up","down","left","right")
     * @updates player position, gameOver, score
     * @requires direction is one of the four accepted strings
     * @ensures player position changes by one cell in given direction
     */
    @Override
    public void movePlayer(String direction) {
        if (direction == null) {
            return;
        }
        String d = direction.trim().toLowerCase();
        if (d.equals("up")) {
            this.playerY = this.playerY - 1;
            if (this.playerY >= 0 && this.playerY < HEIGHT) {
                this.score = this.score + 1;
            }
        } else if (d.equals("down")) {
            this.playerY = this.playerY + 1;
        } else if (d.equals("left")) {
            this.playerX = this.playerX - 1;
        } else if (d.equals("right")) {
            this.playerX = this.playerX + 1;
        }
        if (this.playerX < 0 || this.playerX >= WIDTH || this.playerY < 0
                || this.playerY >= HEIGHT) {
            this.gameOver = true;
        }
    }

    /**
     * moves all obstacles down one step.
     *
     * @updates obstacles
     * @ensures each obstacle's y value increases by one; obstacles off-grid are
     *          removed
     */
    @Override
    public void moveObstacles() {
        Iterator<Obstacle> it = this.obstacles.iterator();
        while (it.hasNext()) {
            Obstacle o = it.next();
            o.y = o.y + 1;
            if (o.y >= HEIGHT) {
                it.remove();
            }
        }
    }

    /**
     * checks if the player collides with any obstacle.
     *
     * @return true if collision occurs, false otherwise
     * @updates gameOver
     * @ensures gameOver = true if result = true
     */
    @Override
    public boolean checkCollision() {
        for (Obstacle o : this.obstacles) {
            if (o.x == this.playerX && o.y == this.playerY) {
                this.gameOver = true;
                return true;
            }
        }
        return false;
    }

    /**
     * returns the grid width (number of columns).
     *
     * @return grid width
     * @ensures result = width of game grid
     */
    @Override
    public int width() {
        return WIDTH;
    }

    /**
     * returns the grid height (number of rows).
     *
     * @return grid height
     * @ensures result = height of game grid
     */
    @Override
    public int height() {
        return HEIGHT;
    }

    /**
     * returns the player position via output arrays.
     *
     * @param outX
     *            array of length >= 1; outX[0] set to player x
     * @param outY
     *            array of length >= 1; outY[0] set to player y
     * @requires outX.length >= 1 and outY.length >= 1
     * @ensures outX[0] = playerX and outY[0] = playerY
     */
    @Override
    public void getPlayerPosition(int[] outX, int[] outY) {
        outX[0] = this.playerX;
        outY[0] = this.playerY;
    }

    /**
     * returns the number of obstacles currently in the game.
     *
     * @return obstacle count
     * @ensures result = number of stored obstacles
     */
    @Override
    public int obstacleCount() {
        return this.obstacles.size();
    }

    /**
     * returns the obstacle position at the given index via output arrays.
     *
     * @param index
     *            0 .. obstacleCount()-1
     * @param outX
     *            array of length >= 1; outX[0] set to obstacle x
     * @param outY
     *            array of length >= 1; outY[0] set to obstacle y
     * @requires 0 <= index < obstacleCount() and outX.length >= 1 and
     *           outY.length >= 1
     * @ensures outX[0] = obstacle x at index and outY[0] = obstacle y at index
     */
    @Override
    public void getObstaclePosition(int index, int[] outX, int[] outY) {
        if (index < 0 || index >= this.obstacles.size()) {
            throw new IndexOutOfBoundsException("invalid obstacle index");
        }
        Obstacle o = this.obstacles.get(index);
        outX[0] = o.x;
        outY[0] = o.y;
    }

    /**
     * returns the currently stored score.
     *
     * @return current score value
     * @ensures result = stored score
     */
    @Override
    public int currentScore() {
        return this.score;
    }

    /**
     * returns the internal game over flag.
     *
     * @return true if game over, false otherwise
     * @ensures result = game over flag
     */
    @Override
    public boolean gameOverFlag() {
        return this.gameOver;
    }
}
