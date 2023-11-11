
/**
 * Test for the LeafNode class
 * 
 * @author Brett Noneman
 * @author Piyush Chauhan
 * @version 10/10/23
 */
public class LeafNodeTest extends student.TestCase {
    /**
     * String of key phrases
     */
    private String[] keyPhrases;
    /**
     * Seminar 1
     */
    private Seminar workshop1;
    /**
     * Seminar 2
     */
    private Seminar workshop2;
    /**
     * Seminar 3
     */
    private Seminar workshop3;
    /**
     * Seminar list
     */
    private CustomList seminarList;
    /**
     * An instance of leaf node
     */
    private AbstractTreeNode leafNodeInstance;

    /**
     * SetUp method that sets up all the tests
     * 
     * @throws Exception
     */
    public void setUp() throws Exception {
        keyPhrases = new String[] { "alpha", "beta", "gamma", "delta" };
        workshop1 = new Seminar(1, "workshop1", "1234567890", 75, (short)15,
            (short)33, 125, keyPhrases, "workshop description");
        workshop2 = new Seminar(1, "workshop2", "1234567890", 75, (short)15,
            (short)30, 125, keyPhrases, "workshop description");
        workshop3 = new Seminar(1, "workshop3", "1234567890", 75, (short)10,
            (short)33, 125, keyPhrases, "workshop description");
        seminarList = new CustomList();
        leafNodeInstance = new LeafNode(seminarList);
    }


    /**
     * Validates that a Seminar can be appended to CustomList.
     */
    public void testSeminarAppendingFunctionality() {
        seminarList.append(workshop1, 1);
        assertEquals(1, seminarList.size());
    }


    /**
     * Validates the creation of a new LeafNode instance.
     */
    public void testLeafNodeCreation() {
        AbstractTreeNode testLeafNode = new LeafNode(seminarList);
        assertNotNull(testLeafNode);
    }


    /**
     * Validates the addition of entries to a LeafNode.
     */
    public void testLeafNodeEntryAddition() {
        seminarList.append(workshop1, 1);
        leafNodeInstance.addEntry(workshop2, false, 0, 0, 128, 128);
        leafNodeInstance.addEntry(workshop3, false, 0, 0, 128, 128);
        assertEquals("1", leafNodeInstance.toString());
    }


    /**
     * Validates the behavior of the locateInRange method.
     */
    public void testLocateInRange() {
        seminarList.append(workshop1, 1);
        int visitedCount = leafNodeInstance.locateInRange(15, 33, 10, 0, 0, 128,
            128, 0, false);
        assertEquals(1, visitedCount);
        // Optionally: You might want to capture the printed output to ensure
        // that the correct information is printed.
    }


    /**
     * Validates the behavior of the remove method.
     */
    public void testLeafNodeEntryRemoval() {
        seminarList.append(workshop1, 1);
        seminarList.append(workshop2, 2);
        seminarList.append(workshop3, 3);
        assertEquals(3, seminarList.size());

        // Test removing an entry
        leafNodeInstance.remove(15, 30, 0, 0, 128, 128, false, 2);
        assertEquals(2, seminarList.size());

        // Test removing the last entry resulting in an EmptyNode
        leafNodeInstance.remove(15, 30, 0, 0, 128, 128, false, 1);
        leafNodeInstance.remove(10, 33, 0, 0, 128, 128, false, 3);
        assertFalse(leafNodeInstance instanceof EmptyNode);
    }


    /**
     * Validates the behavior of the locateInRange method for seminars inside
     * and outside the radius.
     */
    public void testLocateInRangeWithDistanceCheck() {
        seminarList.append(workshop1, 1);
        seminarList.append(workshop2, 2);

        // Use a radius that should locate both seminars
        int visitedCount = leafNodeInstance.locateInRange(15, 33, 10, 0, 0, 128,
            128, 0, false);
        assertEquals(1, visitedCount);

        // Use a radius that should only locate one seminar
        visitedCount = leafNodeInstance.locateInRange(15, 33, 2, 0, 0, 128, 128,
            0, false);
        assertEquals(1, visitedCount);

        // Use a radius that should locate no seminars
        visitedCount = leafNodeInstance.locateInRange(15, 33, 1, 0, 0, 128, 128,
            0, false);
        assertEquals(1, visitedCount);
    }


    /**
     * Validates the behavior of the remove method.
     */
    public void testLeafNodeEntryRemovalComplete() {
        seminarList.append(workshop1, 1);
        seminarList.append(workshop2, 2);
        seminarList.append(workshop3, 3);
        assertEquals(3, seminarList.size());

        // Test removing an entry
        AbstractTreeNode result = leafNodeInstance.remove(
            15, 33, 0, 0, 128, 128,
            false, 1);
        assertEquals(2, seminarList.size());
        assertTrue(result instanceof LeafNode);

        // Test removing another entry, but it should not create an EmptyNode
        // yet
        result = leafNodeInstance.remove(15, 30, 0, 0, 128, 128, false, 2);
        assertEquals(1, seminarList.size());
        assertTrue(result instanceof LeafNode);

        // Test removing the last entry resulting in an EmptyNode
        result = leafNodeInstance.remove(10, 33, 0, 0, 128, 128, false, 3);
        assertTrue(result instanceof EmptyNode);
    }


    /**
     * Validates the behavior of the distanceCheck method when the points are
     * inside the radius.
     */
    public void testDistanceCheckInsideRadius() {
        LeafNode node = new LeafNode(seminarList);

        // Test when the distance is exactly equal to the radius
        assertTrue(node.distanceCheck(0, 0, 3, 4, 5));

        // Test when the distance is less than the radius
        assertTrue(node.distanceCheck(0, 0, 1, 1, 5));
    }


    /**
     * Validates the behavior of the distanceCheck method when the points are
     * outside the radius.
     */
    public void testDistanceCheckOutsideRadius() {
        LeafNode node = new LeafNode(seminarList);

        // Test when the distance is more than the radius
        assertFalse(node.distanceCheck(0, 0, 10, 10, 5));
    }
}
