import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for InternalNode
 * 
 * @author Brett Noneman
 * @version 10/10/23
 */
public class InternalNodeTest {

    private InternalNode testedNode;
    private String[] topicKeywords = { "one", "two", "three", "four" };
    private Seminar seminarExample1;
    private Seminar seminarExample2;
    

    /**
     * Sets up all the tests before they run
     */
    @Before
    public void setup() {
        testedNode = new InternalNode(new EmptyNode(), new EmptyNode());
        seminarExample1 = new Seminar(1, "Seminar Title 1", "2405231000", 75,
            (short)10, (short)10, 125, topicKeywords, "First Seminar");
        seminarExample2 = new Seminar(2, "Seminar Title 2", "2405231100", 85,
            (short)15, (short)15, 130, topicKeywords, "Second Seminar");
    }


    /**
     * Validate the add entry operation.
     */
    @Test
    public void testEntryAddition() {
        testedNode.addEntry(seminarExample1, true, 0, 0, 32, 32);
        assertTrue(testedNode.fetch("left") instanceof LeafNode);
        testedNode.addEntry(seminarExample2, true, 0, 0, 32, 32);
        assertFalse(testedNode.fetch("right") instanceof LeafNode);

        testedNode.addEntry(seminarExample1, false, 0, 0, 32, 32);
        assertFalse(testedNode.fetch("left") instanceof LeafNode);

        testedNode.addEntry(seminarExample2, false, 0, 0, 32, 32);
        assertFalse(testedNode.fetch("right") instanceof LeafNode);
    }


    /**
     * test method for add Entry with vertical
     */
    @Test
    public void testAddEntryToggleVertical() {
        InternalNode tn = new InternalNode(new EmptyNode(),
            new EmptyNode());
        Seminar leftSeminar = new Seminar(1, "Left Seminar", "2405231000", 75,
            (short)10, (short)5, 125, null, "Left seminar for testing");
        Seminar rightSeminar = new Seminar(2, "Right Seminar", "2405231000", 75,
            (short)20, (short)5, 125, null, "Right seminar for testing");

        tn.addEntry(leftSeminar, true, 0, 0, 32, 32);
        assertTrue(tn.fetch("left") instanceof LeafNode);
        tn.addEntry(rightSeminar, true, 0, 0, 32, 32);
        assertTrue(tn.fetch("right") instanceof LeafNode);
    }


    /**
     * test method for locateInRange()
     */
    @Test
    public void testLocateInRange() {
        InternalNode tn = new InternalNode(new EmptyNode(),
            new EmptyNode());
        int result = tn.locateInRange(15, 15, 10, 0, 0, 32, 32, 0,
            true);
        assertEquals(3, result);
    }


    /**
     * test method for remove()
     */
    @Test
    public void testRemove() {
        InternalNode tn = new InternalNode(new EmptyNode(),
            new EmptyNode());
        Seminar leftSeminar = new Seminar(1, "Left Seminar", "2405231000", 75,
            (short)10, (short)5, 125, null, "Left seminar for testing");
        tn.addEntry(leftSeminar, true, 0, 0, 32, 32);
        assertTrue(tn.fetch("left") instanceof LeafNode);

        tn.remove(10, 5, 0, 0, 32, 32, true, 1);
        assertTrue(tn.fetch("left") instanceof EmptyNode);
    }


    /**
     * test method for add entry with modifications
     */
    @Test
    public void testAddEntryMedianX() {
        InternalNode tn = new InternalNode(new EmptyNode(),
            new EmptyNode());
        Seminar leftSeminar = new Seminar(1, "Left Seminar", "2405231000", 75,
            (short)10, (short)5, 125, null, "Left seminar for testing");
        Seminar rightSeminar = new Seminar(2, "Right Seminar", "2405231000", 75,
            (short)20, (short)5, 125, null, "Right seminar for testing");

        tn.addEntry(leftSeminar, true, 0, 0, 32, 32);
        assertTrue(tn.fetch("left") instanceof LeafNode);
        tn.addEntry(rightSeminar, true, 0, 0, 32, 32);
        assertTrue(tn.fetch("right") instanceof LeafNode);
    }


    /**
     * test method for add entry with more modifications
     */
    @Test
    public void testAddEntryMedianY() {
        InternalNode tn = new InternalNode(new EmptyNode(),
            new EmptyNode());
        Seminar bottomSeminar = new Seminar(1, "Bottom Seminar", "2405231000",
            75, (short)10, (short)5, 125, null, "Bottom seminar for testing");
        Seminar topSeminar = new Seminar(2, "Top Seminar", "2405231000", 75,
            (short)10, (short)20, 125, null, "Top seminar for testing");

        tn.addEntry(bottomSeminar, false, 0, 0, 32, 32);
        assertTrue(tn.fetch("left") instanceof LeafNode);
        tn.addEntry(topSeminar, false, 0, 0, 32, 32);
        assertTrue(tn.fetch("right") instanceof LeafNode);
    }


    /**
     * Test method for locateInRange() and ovelapExists()
     */
    @Test
    public void testLocateInRangeOverlapExists() {
        InternalNode tn = new InternalNode(new EmptyNode(),
            new EmptyNode());
        assertEquals(3, tn.locateInRange(15, 15, 10, 0, 0, 32, 32, 0,
            true));
        assertEquals(3, tn.locateInRange(15, 15, 10, 0, 0, 32, 32, 0,
            false));
    }


    /**
     * Test method for locateInRange and medians
     */
    @Test
    public void testLocateInRangeMedians() {
        InternalNode tn = new InternalNode(new EmptyNode(),
            new EmptyNode());
        assertEquals(3, tn.locateInRange(15, 25, 10, 0, 0, 32, 32, 0,
            true));
        assertEquals(3, tn.locateInRange(25, 15, 10, 0, 0, 32, 32, 0,
            false));
    }


    /**
     * Test method for remove()
     */
    @Test
    public void testRemoveNode() {
        InternalNode tn = new InternalNode(new EmptyNode(),
            new EmptyNode());
        Seminar leftSeminar = new Seminar(1, "Left Seminar", "2405231000", 75,
            (short)10, (short)5, 125, null, "Left seminar for testing");
        tn.addEntry(leftSeminar, true, 0, 0, 32, 32);
        assertTrue(tn.fetch("left") instanceof LeafNode);
        tn.remove(10, 5, 0, 0, 32, 32, true, 1);
        assertTrue(tn.fetch("left") instanceof EmptyNode);
        tn.addEntry(leftSeminar, false, 0, 0, 32, 32);
        assertTrue(tn.fetch("left") instanceof LeafNode);
        tn.remove(10, 20, 0, 0, 32, 32, false, 1);
        assertTrue(tn.fetch("right") instanceof EmptyNode);
    }


    /**
     * test method for Median/Vertical
     */
    @Test
    public void testMedianXIsVertical() {
        testedNode.addEntry(
            seminarExample1, true, 0, 0, 32, 32);
        assertTrue(
            testedNode.fetch("left") instanceof LeafNode);

        testedNode.addEntry(
            seminarExample2, true, 0, 0, 32, 32);
        assertFalse(
            testedNode.fetch("right") instanceof LeafNode);
    }


    /**
     * test method for Median/not Vertical
     */
    @Test
    public void testMedianYNotVertical() {
        testedNode.addEntry(
            seminarExample1, false, 0, 0, 32, 32);
        assertTrue(
            testedNode.fetch("left") instanceof LeafNode);

        testedNode.addEntry(
            seminarExample2, false, 0, 0, 32, 32);
        assertFalse(
            testedNode.fetch("right") instanceof LeafNode);
    }


    /**
     * test method for LIR and overlap
     */
    @Test
    public void testLocateInRangeOverlap() {
        testedNode.addEntry(
            seminarExample1, true, 0, 0, 32, 32);

        int visitCount = 0;
        visitCount = testedNode.locateInRange(
            10, 10, 5, 0, 0, 32, 32,
            visitCount, true);
        assertEquals(2, visitCount);
    }


    /**
     * test method for split LIR
     */
    @Test
    public void testLocateInRangeMedianSplit() {
        testedNode.addEntry(
            seminarExample1, true, 0, 0, 32, 32);
        testedNode.addEntry(
            seminarExample2, false, 0, 0, 32, 32);

        int visitCount = 0;
        visitCount = testedNode.locateInRange(10, 15, 5, 0, 0, 32, 32,
            visitCount, true);
        assertEquals(8, visitCount);
    }


    /**
     * test method for removal/medianX
     */
    @Test
    public void testRemovalMedianX() {
        testedNode.addEntry(
            seminarExample1, true, 0, 0, 32, 32);
        testedNode.remove(
            10, 10, 0, 0, 32, 32, true, 1);

        assertTrue(testedNode.fetch("left") instanceof EmptyNode);
    }


    /**
     * test method for removal/medianX
     */
    @Test
    public void testRemovalMedianY() {
        testedNode.addEntry(
            seminarExample1, false, 0, 0, 32, 32);
        testedNode.remove(
            10, 10, 0, 0, 32, 32, false, 1);

        assertTrue(testedNode.fetch("left") instanceof EmptyNode);
    }
    
    
    /**
     * Tests the computeMedian method with a vertical orientation.
     * Verifies that the median calculation for a 
     * vertical split provides the correct value.
     */
    @Test
    public void testComputeMedianVertical() {
        int result = testedNode.computeMedian(
            10, 20, 30, 40, true);
        assertEquals(25, result);
    }

    /**
     * Tests the computeMedian method with a horizontal orientation.
     * Verifies that the median calculation for a 
     * horizontal split provides the correct value.
     */
    @Test
    public void testComputeMedianHorizontal() {
        int result = testedNode.computeMedian(
            10, 20, 30, 40, false);
        assertEquals(40, result);
    }

    /**
     * Tests the isLeftOf method with a vertical orientation 
     * where the provided point is to the left of the median.
     * Verifies that the method correctly identifies the 
     * position of the point relative to the median.
     */
    @Test
    public void testIsLeftOfVerticalTrue() {
        boolean result = testedNode.isLeftOf(
            10, 20, 15, true);
        assertTrue(result);
    }

    /**
     * Tests the isLeftOf method with a vertical orientation 
     * where the provided point is to the right of the median.
     * Verifies that the method correctly identifies the 
     * position of the point relative to the median.
     */
    @Test
    public void testIsLeftOfVerticalFalse() {
        boolean result = testedNode.isLeftOf(
            20, 20, 15, true);
        assertFalse(result);
    }

    /**
     * Tests the isLeftOf method with a horizontal orientation
     *  where the provided point is above the median.
     * Verifies that the method correctly identifies the 
     * position of the point relative to the median.
     */
    @Test
    public void testIsLeftOfHorizontalTrue() {
        boolean result = testedNode.isLeftOf(
            10, 20, 25, false);
        assertTrue(result);
    }

    /**
     * Tests the isLeftOf method with a horizontal orientation
     *  where the provided point is below the median.
     * Verifies that the method correctly identifies the 
     * position of the point relative to the median.
     */
    @Test
    public void testIsLeftOfHorizontalFalse() {
        boolean result = testedNode.isLeftOf(
            10, 30, 25, false);
        assertFalse(result);
    }

}
