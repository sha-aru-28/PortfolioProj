/**
 * kernel interface for the concept crossy road game.
 */
public interface ConceptCRGameKernel extends Standard<ConceptCRGame> {

    /**
     * clears and resets all game values to start state.
     *
     * @clears all obstacles
     * @ensures player is set to center-bottom position and
     *          game is not over and score is zero
     */
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
}
