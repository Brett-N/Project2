/**
 * Test class for CustomList
 * 
 * @author Brett Noneman
 * @author Piyush Chauhan
 * @version 10/10/23
 */
public class CustomListTest extends student.TestCase {

    /** Custom list instance used for testing. */
    private CustomList list;

    /** Sample seminar for testing. */
    private Seminar seminar1;

    /** Another sample seminar for testing. */
    private Seminar seminar2;
    
    /**
     * Sets up the test environment.
     * This method is called before every test case method.
     */
    public void setUp() {
        list = new CustomList();

        seminar1 = new Seminar(
            1, "Title1", "2023-09-01", 120, (short) 10, (short) 10, 100,
            new String[] { "key1", "key2" }, "Desc1"
        );

        seminar2 = new Seminar(
            2, "Title2", "2023-09-02", 150, (short) 20, (short) 20, 200,
            new String[] { "key2", "key3" }, "Desc2"
        );
    }

    /**
     * Tests the append method and size method of CustomList.
     */
    public void testAppendAndSize() {
        assertEquals(0, list.size());

        list.append(seminar1, seminar1.id());
        assertEquals(1, list.size());

        list.append(seminar2, seminar2.id());
        assertEquals(2, list.size());
    }

    /**
     * Tests the isVacant method of CustomList.
     */
    public void testIsVacant() {
        assertTrue(list.isVacant());
        list.append(seminar1, seminar1.id());
        assertFalse(list.isVacant());
    }

    /**
     * Tests the removeByIdentifier method of CustomList.
     */
    public void testRemoveByIdentifier() {
        assertFalse(list.removeByIdentifier(1));
        list.append(seminar1, seminar1.id());
        list.append(seminar2, seminar2.id());
        
        assertTrue(list.removeByIdentifier(1));
        assertEquals(1, list.size());
        assertEquals(seminar2, list.fetchPrimary());
    }

    /**
     * Tests the identifierString method of CustomList.
     */
    public void testIdentifierString() {
        assertEquals("", list.identifierString());

        list.append(seminar1, seminar1.id());
        assertEquals("1", list.identifierString());

        list.append(seminar2, seminar2.id());
        assertEquals("1 2", list.identifierString());
    }
    
    /**
     * Tests the fetchPrimary method of CustomList when the list is empty.
     * Ensures that an exception is thrown.
     */
    public void testFetchPrimaryOnEmptyList() {
        try {
            list.fetchPrimary();
            fail("Expected an IllegalStateException to be thrown");
        } 
        catch (IllegalStateException e) {
            assertEquals("List is empty.", e.getMessage());
        }
    }

    /**
     * Tests the removeByIdentifier method 
     * of CustomList when removing from the start.
     */
    public void testRemoveByIdentifierFromStart() {
        list.append(seminar1, seminar1.id());
        list.append(seminar2, seminar2.id());
        assertTrue(list.removeByIdentifier(seminar1.id()));
        
        assertEquals(1, list.size());
        assertEquals(seminar2, list.fetchPrimary());
    }
}
