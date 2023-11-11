/**
 * The test class for element queue
 * 
 * @author Brett Noneman
 * @author Piyush Chauhan
 * @version 10/10/23
 *
 */
public class ElementQueueTest extends student.TestCase {
    private ElementQueue<Integer> queue;

    /**
     * sets up the object to be tested
     */
    public void setUp() {
        queue = new ElementQueue<>();
    }


    /**
     * tests the size method for queue
     */
    public void testQueueSizeWhenEmpty() {
        assertEquals(0, queue.queueSize());
    }


    /**
     * tests add method for queue
     */
    public void testAdd() {
        queue.addElement(42);
        assertFalse(queue.hasNoElements());
        assertEquals(1, queue.queueSize());

        queue.addElement(1);
        queue.addElement(2);
        queue.addElement(3);

        assertTrue(Integer.valueOf(42) == queue.provideElement());
        assertTrue(Integer.valueOf(1) == queue.provideElement());
        assertTrue(Integer.valueOf(2) == queue.provideElement());
        assertTrue(Integer.valueOf(3) == queue.provideElement());
    }


    /**
     * tests remove method for queue
     */
    public void testRemove() {
        queue.addElement(42);
        queue.addElement(24);
        queue.provideElement();
        assertEquals(1, queue.queueSize());
    }


    /**
     * tests provide method for queue
     */
    public void testProvideElement() {
        queue.addElement(42);
        assertTrue(Integer.valueOf(42) == queue.provideElement());
        assertNull(queue.getHead());
        assertNull(queue.getTail());
        assertTrue(queue.queueSize() == 0);

        queue.addElement(1);
        queue.addElement(2);
        queue.provideElement();
        assertFalse(queue.getTail() == null);
        assertTrue(queue.queueSize() == 1);
    }


    /**
     * tests provide method for queue
     */
    public void testProvideElementFromEmptyQueue() {
        Exception e = null;
        try {
            queue.provideElement();
        }
        catch (Exception exception) {
            e = exception;
        }
        assertTrue(e instanceof IllegalStateException);
    }


    /**
     * tests nodecount method for queue
     */
    public void testNodesCount() {
        queue.addElement(1);
        queue.addElement(2);
        queue.addElement(3);
        queue.provideElement();
        assertEquals(0, queue.accessedNodesCount());
        queue.markNodeAccessed();
        assertEquals(1, queue.accessedNodesCount());
        queue.markNodeAccessed();
        assertEquals(2, queue.accessedNodesCount());
    }
}
