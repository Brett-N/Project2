/**
 * This class tests the implementation of the KVPair class
 * 
 * @author Piyush Chauhan
 * @author Brett Non
 * @version 2023.9.20
 *
 */
public class KVPairTest extends student.TestCase {
    private KVPair<String, Integer> test;

    /**
     * Sets up the KVPair test
     */
    public void setUp() {
        test = new KVPair<>("key", 1);
    }


    /**
     * Tests the getKey method for KVPair
     */
    public void testGetKey() {
        assertEquals("key", test.getKey());
    }


    /**
     * Tests the getValue method for KVPair
     */
    public void testGetValue() {
        assertTrue(1 == test.getValue());
    }


    /**
     * Tests the setKey method for KVPair
     */
    public void testSetKey() {
        test.setKey("newKey");
        assertEquals("newKey", test.getKey());
    }


    /**
     * Tests the setValue method for KVPair
     */
    public void testSetValue() {
        test.setValue(100);
        assertTrue(100 == test.getValue());
    }


    /**
     * Tests the toString method for KVPair
     */
    public void testToString() {
        assertEquals("(key, 1)", test.toString());
    }
}
