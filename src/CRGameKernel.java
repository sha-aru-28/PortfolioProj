import components.standard.Standard;

/**
 * kernel interface for the concept crossy road game.
 */
<<<<<<< Updated upstream
public interface ConceptCRGameKernel extends Standard<ConceptCRGame> {
=======
public interface CRGameKernel extends Standard<CRGameEnhanced> {
>>>>>>> Stashed changes

    /**
     * clears and resets all game values to start state.
     *
     * @clears all obstacles
     * @ensures player is set to center-bottom position and game is not over and
     *          score is zero
     */
    @Override
    void clear();

    /**
     * sets the player position.
     *
     * @param x
     *            x coordinate
     * @param y
     *            y coordinate
     * @updates player position
     * @ensures playerX = x and playerY = y
     */
    void setPlayerPosition(int x, int y);

    /**
     * adds an obstacle at the given position.
     *
     * @param x
     *            x coordinate of obstacle
     * @param y
     *            y coordinate of obstacle
     * @updates obstacle set
     * @ensures an obstacle exists at (x, y) after call
     */
    void addObstacle(int x, int y);

    /**
     * moves the player one step in the given direction.
     *
     * @param direction
     *            movement direction ("up", "down", "left", or "right")
     * @updates player position, gameOver
     * @requires direction is one of "up", "down", "left", "right"
     * @ensures player position changes by one cell in given direction
     */
    void movePlayer(String direction);

    /**
     * moves all obstacles down one step.
     *
     * @updates obstacles
     * @ensures each obstacle's y value increases by one
     */
    void moveObstacles();

    /**
     * checks if the player collides with any obstacle.
     *
     * @return true if a collision occurs and false if not
     * @updates gameOver
     * @ensures gameOver = true if result = true
     */
    boolean checkCollision();

    /**
     * returns the grid width (number of columns).
     *
     * @return grid width
     * @ensures result = width of game grid
     */
    int width();

    /**
     * returns the grid height (number of rows).
     *
     * @return grid height
     * @ensures result = height of game grid
     */
    int height();

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
    void getPlayerPosition(int[] outX, int[] outY);

    /**
     * returns the number of obstacles currently in the game.
     *
     * @return obstacle count
     * @ensures result = number of stored obstacles
     */
    int obstacleCount();

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
    void getObstaclePosition(int index, int[] outX, int[] outY);

    /**
     * returns the currently stored score.
     *
     * @return current score value
     * @ensures result = stored score
     */
    int currentScore();

    /**
     * returns the internal game over flag.
     *
     * @return true if game over, false otherwise
     * @ensures result = game over flag
     */
    boolean gameOverFlag();
}
