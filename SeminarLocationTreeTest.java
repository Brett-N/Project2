import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for SeminarLocationTree
 * 
 * @author Brett Noneman
 * @author Piyush Chauhan
 * @version 10/10/23
 */
public class SeminarLocationTreeTest {
    private final ByteArrayOutputStream capturedOutput =
        new ByteArrayOutputStream();
    private final PrintStream standardOutput = System.out;

    /**
     * Redirects system output for testing purposes.
     */
    @Before
    public void redirectSystemOut() {
        System.setOut(new PrintStream(capturedOutput));
    }


    /**
     * Returns system output to its original state.
     */
    @After
    public void revertSystemOut() {
        System.setOut(standardOutput);
    }


    /**
     * Verifies the behavior with file content.
     */
    @Test
    public void fileContentCheck() {
        performInsertionScenario("I\n  I\n    I\n      I\n        I\n    "
            + "      I\n            I\n             "
            + " Leaf with 1 objects: 3\n           "
            + "   Leaf with 1 objects: 1\n        "
            + "    E\n          Leaf with 1 objects: 2\n "
            + "       E\n      E\n    E\n  E\n");
    }


    /**
     * Validates the search operation.
     */
    @Test
    public void searchCheck() {
        SeminarLocationTree organizer = initializeTree();
        String expectedOutput = "I\n  I\n    I\n      I\n        I\n    "
            + "      I\n            I\n           "
            + "   Leaf with 1 objects: 3\n        "
            + "      Leaf with 1 objects: 1\n     "
            + "       E\n          Leaf with 1 objects: 2\n"
            + "        E\n      E\n    E\n  E\n"
            + "Found a record with key value 3 at 0, 0\n"
            + "8 nodes visited in this search\n";
        System.out.print(organizer.toString());
        organizer.searchWithinRadius(1, 1, 3);
        assertEquals(expectedOutput, capturedOutput.toString());
    }


    /**
     * Validates the delete operation.
     */
    @Test
    public void deletionCheck() {
        SeminarLocationTree organizer = initializeTree();
        organizer.removeSeminar(1, 10, 10);
        String expectedOutput = "I\n  I\n    I\n      I\n      "
            + "  I\n          I\n         "
            + "   I\n              Leaf with 1 objects: 3\n "
            + "             E\n            E\n        "
            + "  Leaf with 1 objects: 2\n        E\n" + "      E\n    E\n  E\n";
        System.out.print(organizer.toString());
        assertEquals(expectedOutput, capturedOutput.toString());
    }


    private void performInsertionScenario(String expectedOutput) {
        SeminarLocationTree organizer = initializeTree();
        System.out.print(organizer.toString());
        assertEquals(expectedOutput, capturedOutput.toString());
    }


    private SeminarLocationTree initializeTree() {
        SeminarLocationTree organizer = new SeminarLocationTree(0, 0, 128, 128);
        String[] topics = { "one", "two", "three", "four" };
        organizer.addSeminar(createSeminar(1, (short)10, (short)10));
        organizer.addSeminar(createSeminar(2, (short)30, (short)10));
        organizer.addSeminar(createSeminar(3, (short)0, (short)0));
        return organizer;
    }


    private Seminar createSeminar(int id, short x, short y) {
        String[] topics = { "one", "two", "three", "four" };
        return new Seminar(id, "Seminar Title", "2405231000", 75, x, y, 125,
            topics, "This is a great seminar");
    }


    /**
     * Utility function to create a test tree.
     * 
     * @return a SeminarLocationTree filled with test data.
     */
    private SeminarLocationTree createTestTree() {
        SeminarLocationTree organizer = new SeminarLocationTree(0, 0, 128, 128);
        String[] keywords = { "one", "two", "three", "four" };

        Seminar seminarA = new Seminar(1, "Seminar Title", "2405231000", 75,
            (short)10, (short)10, 125, keywords,
            "This is an insightful seminar");
        Seminar seminarB = new Seminar(2, "Seminar Title", "2405231000", 75,
            (short)30, (short)10, 125, keywords,
            "This is an insightful seminar");
        Seminar seminarC = new Seminar(3, "Seminar Title", "2405231000", 75,
            (short)0, (short)0, 125, keywords, "This is an insightful seminar");

        organizer.addSeminar(seminarA);
        organizer.addSeminar(seminarB);
        organizer.addSeminar(seminarC);

        return organizer;
    }
    
    /**
     * Verifies the basic insertion functionality.
     */
    @Test
    public void insertionCheck() {
        SeminarLocationTree organizer = createTestTree();

        System.out.print(organizer);
        String expectedOutput = "I\n" + "  I\n" + "    I\n" + "      I\n"
            + "        I\n" + "          I\n" + "            I\n"
            + "              Leaf with 1 objects: 3\n"
            + "              Leaf with 1 objects: 1\n" + "            E\n"
            + "          Leaf with 1 objects: 2\n" + "        E\n" + "      E\n"
            + "    E\n" + "  E\n";
        assertEquals(expectedOutput, capturedOutput.toString());
    }


    /**
     * Verifies insertion of multiple seminars.
     */
    @Test
    public void multipleInsertionCheck() {
        SeminarLocationTree organizer = createTestTree();

        System.out.print(organizer);
        String expectedOutput = "I\n" + "  I\n" + "    I\n" + "      I\n"
            + "        I\n" + "          I\n" + "            I\n"
            + "              Leaf with 1 objects: 3\n"
            + "              Leaf with 1 objects: 1\n" + "            E\n"
            + "          Leaf with 1 objects: 2\n" + "        E\n" + "      E\n"
            + "    E\n" + "  E\n";
        assertEquals(expectedOutput, capturedOutput.toString());
    }
}
