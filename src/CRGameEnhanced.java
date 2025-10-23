/**
 * enhanced interface for the concept crossy road game.
 */
public interface ConceptCRGame extends ConceptCRGameKernel {

    /**
     * returns whether the game is over.
     *
     * @return true if game is over and false if not
     * @ensures result = gameOver
     */
    boolean isGameOver();

    /**
     * returns the current player score.
     *
     * @return current score
     * @ensures result = current stored score value
     */
    int score();

    /**
     * prints the grid to the console showing player (P) and obstacles (O).
     *
     * @ensures the console shows a textual grid with current positions
     */
    void printGrid();

    /**
     * returns the player position as two integers via parameters.
     *
     * @param outX
     *            an array of length >= 1 where outX[0] will be set to player x
     * @param outY
     *            an array of length >= 1 where outY[0] will be set to player y
     * @requires outX.length >= 1 and outY.length >= 1
     * @ensures outX[0] = playerX and outY[0] = playerY
     */
    void getPlayerPosition(int[] outX, int[] outY);
}
