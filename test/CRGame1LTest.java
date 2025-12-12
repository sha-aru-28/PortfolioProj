import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * JUnit tests for the kernel implementation CRGame1L.
 */
public class CRGame1LTest {

    /**
     * Game instance used for testing.
     */
    private CRGame1L game;

    /**
     * Setting up game before testing.
     */
    @Before
    public void setUp() {
        this.game = new CRGame1L();
    }

    /**
     * Test constructor / clear initial state.
     */
    @Test
    public void testConstructorInitialState1() {
        int[] px = new int[1];
        int[] py = new int[1];
        this.game.getPlayerPosition(px, py);
        assertEquals(5, this.game.width());
        assertEquals(5, this.game.height());
        assertEquals(2, px[0]);
        assertEquals(4, py[0]);
        assertEquals(0, this.game.currentScore());
        assertEquals(false, this.game.gameOverFlag());
        assertEquals(0, this.game.obstacleCount());
    }

    /**
     * Test clear restores initial state after changes.
     */
    @Test
    public void testClearResetsState2() {
        this.game.addObstacle(1, 1);
        this.game.movePlayer("up");
        this.game.setPlayerPosition(0, 0);
        assertTrue(
                this.game.obstacleCount() > 0 || this.game.currentScore() > 0);
        this.game.clear();
        int[] px = new int[1];
        int[] py = new int[1];
        this.game.getPlayerPosition(px, py);
        assertEquals(2, px[0]);
        assertEquals(4, py[0]);
        assertEquals(0, this.game.currentScore());
        assertEquals(false, this.game.gameOverFlag());
        assertEquals(0, this.game.obstacleCount());
    }

    /**
     * Test newInstance returns a fresh, empty instance.
     */
    @Test
    public void testNewInstance3() {
        CRGameEnhanced n = this.game.newInstance();
        assertNotSame(this.game, n);
        assertEquals(true, n instanceof CRGame1L);
        CRGame1L nn = (CRGame1L) n;
        int[] px = new int[1];
        int[] py = new int[1];
        nn.getPlayerPosition(px, py);
        assertEquals(2, px[0]);
        assertEquals(4, py[0]);
        assertEquals(0, nn.currentScore());
        assertEquals(false, nn.gameOverFlag());
        assertEquals(0, nn.obstacleCount());
    }

    /**
     * Test transferFrom moves all state and clears source.
     */
    @Test
    public void testTransferFrom1() {
        CRGame1L src = new CRGame1L();
        src.addObstacle(1, 1);
        src.addObstacle(3, 0);
        src.setPlayerPosition(1, 3);
        src.movePlayer("up");
        assertEquals(2, src.obstacleCount());
        this.game.transferFrom(src);
        assertEquals(2, this.game.obstacleCount());
        int[] dPx = new int[1];
        int[] dPy = new int[1];
        this.game.getPlayerPosition(dPx, dPy);
        assertEquals(1, dPx[0]);
        int[] sPx = new int[1];
        int[] sPy = new int[1];
        src.getPlayerPosition(sPx, sPy);
        assertEquals(2, sPx[0]);
        assertEquals(4, sPy[0]);
        assertEquals(0, src.obstacleCount());
        assertEquals(0, src.currentScore());
        assertEquals(false, src.gameOverFlag());
    }

    /**
     * Test transferFrom from empty source leaves destination default.
     */
    @Test
    public void testTransferFrom2() {
        CRGame1L src = new CRGame1L();
        this.game.addObstacle(1, 1);
        this.game.transferFrom(src);
        int[] px = new int[1];
        int[] py = new int[1];
        this.game.getPlayerPosition(px, py);
        assertEquals(2, px[0]);
        assertEquals(4, py[0]);
        assertEquals(0, this.game.obstacleCount());
    }

    /**
     * Test setPlayerPosition in-bounds does not set game over.
     */
    @Test
    public void testSetPlayerPositionInBounds1() {
        this.game.setPlayerPosition(0, 0);
        int[] px = new int[1];
        int[] py = new int[1];
        this.game.getPlayerPosition(px, py);
        assertEquals(0, px[0]);
        assertEquals(0, py[0]);
        assertEquals(false, this.game.gameOverFlag());
    }

    /**
     * Test setPlayerPosition out-of-bounds sets game over.
     */
    @Test
    public void testSetPlayerPositionOutOfBounds2() {
        this.game.setPlayerPosition(-1, 0);
        assertEquals(true, this.game.gameOverFlag());
    }

    /**
     * Test addObstacle and getObstaclePosition for valid coordinates.
     */
    @Test
    public void testAddObstacleAndGetPosition1() {
        this.game.addObstacle(1, 2);
        assertEquals(1, this.game.obstacleCount());
        int[] ox = new int[1];
        int[] oy = new int[1];
        this.game.getObstaclePosition(0, ox, oy);
        assertEquals(1, ox[0]);
        assertEquals(2, oy[0]);
    }

    /**
     * Test addObstacle ignores invalid coordinates.
     */
    @Test
    public void testAddObstacleInvalidIgnored2() {
        this.game.addObstacle(-1, 0);
        this.game.addObstacle(0, 5);
        assertEquals(0, this.game.obstacleCount());
    }

    /**
     * Test getObstaclePosition throws on invalid index.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetObstaclePositionInvalidIndexThrows1() {
        this.game.getObstaclePosition(0, new int[1], new int[1]);
    }

    /**
     * Test obstacleCount with multiple obstacles.
     */
    @Test
    public void testObstacleCountMultiple2() {
        this.game.addObstacle(0, 0);
        this.game.addObstacle(1, 1);
        this.game.addObstacle(2, 2);
        assertEquals(3, this.game.obstacleCount());
    }

    /**
     * Test movePlayer up increments score when moving into bounds.
     */
    @Test
    public void testMovePlayerUpIncrementsScore1() {
        int before = this.game.currentScore();
        this.game.movePlayer("up");
        assertEquals(before + 1, this.game.currentScore());
    }

    /**
     * Test movePlayer with null direction does nothing.
     */
    @Test
    public void testMovePlayerNullDoesNothing2() {
        int before = this.game.currentScore();
        int[] px = new int[1];
        int[] py = new int[1];
        this.game.getPlayerPosition(px, py);
        this.game.movePlayer(null);
        int[] px2 = new int[1];
        int[] py2 = new int[1];
        this.game.getPlayerPosition(px2, py2);
        assertEquals(px[0], px2[0]);
        assertEquals(py[0], py2[0]);
        assertEquals(before, this.game.currentScore());
    }

    /**
     * Test movePlayer going out of bounds sets game over.
     */
    @Test
    public void testMovePlayerOutOfBoundsSetsGameOver3() {
        while (!this.game.gameOverFlag()) {
            this.game.movePlayer("up");
        }
        assertEquals(true, this.game.gameOverFlag());
    }

    /**
     * Test moveObstacles removes off-grid obstacles.
     */
    @Test
    public void testMoveObstaclesRemovesOffGrid1() {
        int bottomY = this.game.height() - 1;
        this.game.addObstacle(2, bottomY);
        assertEquals(1, this.game.obstacleCount());
        this.game.moveObstacles();
        assertEquals(0, this.game.obstacleCount());
    }

    /**
     * Test moveObstacles shifts multiple obstacles downward.
     */
    @Test
    public void testMoveObstaclesMultiple2() {
        this.game.addObstacle(0, 0);
        this.game.addObstacle(1, 2);
        this.game.moveObstacles();
        int count = this.game.obstacleCount();
        assertTrue(count == 2);
        for (int i = 0; i < count; i++) {
            int[] tx = new int[1];
            int[] ty = new int[1];
            this.game.getObstaclePosition(i, tx, ty);
            assertTrue(ty[0] > 0 && ty[0] < this.game.height());
        }
    }

    /**
     * Test checkCollision detects collision and sets gameOver.
     */
    @Test
    public void testCheckCollisionSetsGameOver1() {
        int[] px = new int[1];
        int[] py = new int[1];
        this.game.getPlayerPosition(px, py);
        this.game.addObstacle(px[0], py[0]);
        assertEquals(true, this.game.checkCollision());
        assertEquals(true, this.game.gameOverFlag());
    }

    /**
     * Test checkCollision after obstacle moves onto player.
     */
    @Test
    public void testCheckCollisionAfterObstaclesMove2() {
        int[] px = new int[1];
        int[] py = new int[1];
        this.game.getPlayerPosition(px, py);
        int spawnY = py[0] - 1;
        if (spawnY < 0) {
            spawnY = 0;
        }
        this.game.addObstacle(px[0], spawnY);
        this.game.moveObstacles();
        assertEquals(true, this.game.checkCollision());
        assertEquals(true, this.game.gameOverFlag());
    }

    /**
     * Test width, height, and getPlayerPosition accessors.
     */
    @Test
    public void testWidthHeightGetPlayerPosition1() {
        assertEquals(5, this.game.width());
        assertEquals(5, this.game.height());
        int[] px = new int[1];
        int[] py = new int[1];
        this.game.getPlayerPosition(px, py);
        assertEquals(2, px[0]);
        assertEquals(4, py[0]);
    }

    /**
     * Test currentScore and gameOverFlag accessors change appropriately.
     */
    @Test
    public void testCurrentScoreAndGameOverFlag2() {
        assertEquals(0, this.game.currentScore());
        assertEquals(false, this.game.gameOverFlag());
        this.game.movePlayer("up");
        assertEquals(1, this.game.currentScore());
    }
}
