/**
 * enhanced interface for the concept crossy road game.
 */

public interface CRGameEnhanced extends CRGameKernel {

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

}
