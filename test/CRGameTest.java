
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

/**
 * JUnit tests for CRGame.
 */
public class CRGameTest {

    /**
     * First game instance used for testing.
     */
    private CRGame1L g1;

    /**
     * Second game instance used for testing.
     */
    private CRGame1L g2;

    /**
     * Setting up game before testing.
     */
    @Before
    public void setUp() {
        this.g1 = new CRGame1L();
        this.g2 = new CRGame1L();
    }

    /**
     * Test isGameOver and score delegation to kernel methods.
     */
    @Test
    public void testIsGameOverAndScoreDelegation1() {
        assertEquals(false, this.g1.isGameOver());
        assertEquals(0, this.g1.score());
        this.g1.movePlayer("up");
        assertEquals(1, this.g1.score());
    }

    /**
     * Test isGameOver after out-of-bounds setPlayerPosition.
     */
    @Test
    public void testIsGameOverAfterSetPosition2() {
        this.g1.setPlayerPosition(-1, 0);
        assertEquals(true, this.g1.isGameOver());
    }

    /**
     * Test equals and hashCode consistent for equal states.
     */
    @Test
    public void testEqualsAndHashCode1() {
        assertEquals(true, this.g1.equals(this.g2));
        assertEquals(this.g1.hashCode(), this.g2.hashCode());
    }

    /**
     * Test equals changes when state changes.
     */
    @Test
    public void testEqualsAfterStateChange2() {
        this.g2.movePlayer("up");
        assertEquals(false, this.g1.equals(this.g2));
    }

    /**
     * Test equals handles null and symmetry.
     */
    @Test
    public void testEqualsNullAndSymmetry3() {
        assertEquals(false, this.g1.equals(null));
        assertEquals(true, this.g1.equals(this.g2) && this.g2.equals(this.g1));
    }

    /**
     * Test toString contains readable summary.
     */
    @Test
    public void testToStringContainsInfo1() {
        String s = this.g1.toString();
        assertEquals(true, s.contains("CRGame[player=("));
        assertEquals(true, s.contains("score="));
        assertEquals(true, s.contains("gameOver="));
    }

    /**
     * Test toString changes after state change.
     */
    @Test
    public void testToStringAfterStateChange2() {
        this.g1.movePlayer("up");
        String s = this.g1.toString();
        assertEquals(true, s.contains("score="));
    }

    /**
     * Test printGrid output contains player marker.
     */
    @Test
    public void testPrintGridOutputContainsPlayer1() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(out);
        PrintStream old = System.out;
        System.setOut(ps);

        this.g1.printGrid();

        System.out.flush();
        System.setOut(old);

        String s = out.toString();
        assertTrue(s.contains("P "));
    }

    /**
     * Test printGrid output contains obstacle marker after adding one.
     */
    @Test
    public void testPrintGridShowsObstacle2() {
        this.g1.addObstacle(0, 0);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(out);
        PrintStream old = System.out;
        System.setOut(ps);

        this.g1.printGrid();

        System.out.flush();
        System.setOut(old);

        String s = out.toString();
        assertTrue(s.contains("O "));
    }

    /**
     * Test printGrid shows multiple obstacles and player positions.
     */
    @Test
    public void testPrintGridMultipleMarkers3() {
        this.g1.addObstacle(0, 0);
        this.g1.addObstacle(2, 1);
        this.g1.setPlayerPosition(2, 2);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(out);
        PrintStream old = System.out;
        System.setOut(ps);

        this.g1.printGrid();

        System.out.flush();
        System.setOut(old);

        String s = out.toString();
        assertTrue(s.contains("P "));
        assertTrue(s.contains("O "));
    }

    /**
     * Test hashCode remains consistent for equal states after operations that
     * don't change state.
     */
    @Test
    public void testHashCodeConsistency2() {
        int h1 = this.g1.hashCode();
        int h2 = this.g2.hashCode();
        assertEquals(h1, h2);
        this.g1.movePlayer("up");
        this.g2.movePlayer("up");
        assertEquals(this.g1.hashCode(), this.g2.hashCode());
    }
}
