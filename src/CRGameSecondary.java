/**
 * secondary implementation for the crossy road game component. this class
 * provides the bodies for the enhanced methods using kernel ops.
 *
 * author: Sharmi
 */
public abstract class CRGameSecondary implements CRGame {

    /**
     * default constructor. calls the parent constructor so everything is set
     * up.
     */
    protected CRGameSecondary() {
        super();
    }

    /**
     * prints the game grid using kernel info. shows P for player and O for each
     * obstacle.
     *
     * @ensures console output shows current gameboard state
     */
    @Override
    public void printGrid() {

        int width = this.width();
        int height = this.height();

        int[] px = new int[1];
        int[] py = new int[1];
        this.getPlayerPosition(px, py);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {

                boolean printed = false;

                if (px[0] == x && py[0] == y) {
                    System.out.print("P ");
                    printed = true;
                } else {
                    int count = this.obstacleCount();
                    for (int i = 0; i < count; i++) {
                        int[] ox = new int[1];
                        int[] oy = new int[1];
                        this.getObstaclePosition(i, ox, oy);

                        if (ox[0] == x && oy[0] == y) {
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
     * returns if the game is over.
     *
     * @return true if game ended, false if still playing
     * @ensures result = gameOver flag
     */
    @Override
    public boolean isGameOver() {
        return this.gameOverFlag();
    }

    /**
     * returns the current score value.
     *
     * @return stored score
     * @ensures result = score
     */
    @Override
    public int score() {
        return this.currentScore();
    }

    /**
     * text description of the current game state.
     *
     * @return string showing player pos, score, and if game is over
     * @ensures result = readable summary
     */
    @Override
    public String toString() {

        int[] px = new int[1];
        int[] py = new int[1];
        this.getPlayerPosition(px, py);

        return "CRGame[player=(" + px[0] + "," + py[0] + "), score="
                + this.score() + ", gameOver=" + this.isGameOver() + "]";
    }

    /**
     * checks if two game states match.
     *
     * @param obj
     *            another object
     * @return true if same state, false if not
     * @ensures consistent equality behavior
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CRGame)) {
            return false;
        }

        CRGame other = (CRGame) obj;

        int[] px1 = new int[1];
        int[] py1 = new int[1];
        int[] px2 = new int[1];
        int[] py2 = new int[1];

        this.getPlayerPosition(px1, py1);
        other.getPlayerPosition(px2, py2);

        return px1[0] == px2[0] && py1[0] == py2[0]
                && this.score() == other.score()
                && this.isGameOver() == other.isGameOver();
    }

    /**
     * makes a hash code based on position, score, and gameOver flag.
     *
     * @return hash value for storing in collections
     */
    @Override
    public int hashCode() {

        int[] px = new int[1];
        int[] py = new int[1];
        this.getPlayerPosition(px, py);

        int h = 17;
        h = 31 * h + px[0];
        h = 31 * h + py[0];
        h = 31 * h + this.score();
        h = 31 * h + (this.isGameOver() ? 1 : 0);

        return h;
    }
}
