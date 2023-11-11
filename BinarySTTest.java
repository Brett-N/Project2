/**
 * This class tests the Lab14BinarySeachTree class
 * 
 * @author Piyush Chauhan
 * @author Brett Noneman
 * @version 2023.9.19
 *
 */
public class BinarySTTest extends student.TestCase {
    private BinaryST<String, Integer> test;
    private BinaryST<Integer, String> singleObj;

    /**
     * Sets up the BinarySearchTree objects initialized earlier
     */
    public void setUp() {
        test = new BinaryST<String, Integer>();
        singleObj = new BinaryST<Integer, String>();
        singleObj.add(1, "val");
    }


    /**
     * tests insert method
     */
    public void testInsert() {
        assertTrue(test.isEmpty());
        test.add("val", 1);
        assertFalse(test.isEmpty());
        assertTrue(test.size() == 1);
        assertFalse(singleObj.isEmpty());
    }


    /**
     * tests remove method
     */
    public void testRemove() {
        assertFalse(singleObj.isEmpty());
        // System.out.println(singleObj.toString());
        singleObj.remove2(1);
        // System.out.println(singleObj.toString());
        assertTrue(singleObj.isEmpty());
        assertEquals(null, singleObj.remove2(6));
        singleObj.add(1, "val1");
        assertFalse(null == singleObj.remove2(1));

        singleObj.add(7, "val7");
        assertTrue(singleObj.size() == 1);
        singleObj.add(2, "val2");
        assertTrue(singleObj.size() == 2);
        singleObj.add(5, "val5");

        singleObj.add(3, "val3");
        singleObj.add(8, "val8");
        singleObj.add(10, "val10");
        System.out.println(singleObj.toString());
        TreeNode<Integer, String> comp = singleObj.remove2(5);
        assertEquals("val5", comp.getValue());
        System.out.println(singleObj.toString());
        TreeNode<Integer, String> comp2 = singleObj.remove2(7);
        assertEquals("val3", comp2.getValue());
        System.out.println(singleObj.toString());
        TreeNode<Integer, String> comp3 = singleObj.remove2(2);
        assertEquals("val2", comp3.getValue());
        singleObj.remove2(3);
        test.add("10", 10);
        test.add("6", 6);
        test.add("9", 9);
        test.add("7", 7);
        TreeNode<String, Integer> comp4 = test.remove2("9");
        assertEquals("9", comp4.getKey());
        singleObj.add(5, "5");
        singleObj.add(2, "2");
        comp2 = singleObj.remove2(8);

        assertEquals(comp2.getValue(), "5");
        System.out.println(singleObj.toString());

        singleObj.remove2(5);
        singleObj.remove2(10);
        singleObj.remove2(2);
        singleObj.remove2(9);

        singleObj.add(37, "37");
        singleObj.add(24, "24");
        singleObj.add(42, "42");
        singleObj.add(7, "7");
        singleObj.add(2, "2");
        singleObj.add(32, "32");
        singleObj.add(42, "42");
        singleObj.add(40, "40");
        singleObj.add(120, "120");
        singleObj.add(30, "30");

        singleObj.remove2(37);
        singleObj.remove2(32);
        singleObj.remove2(120);
        singleObj.remove2(24);
        System.out.println(singleObj.toString());
        System.out.println(singleObj.findInRange(29, 42));
    }


    /**
     * tests find method
     */
    public void testSearch() {
        singleObj.add(2, "val2");
        singleObj.add(3, "val3");
        singleObj.add(4, "val4");
        assertTrue(3 == singleObj.search(3).getKey());
        assertTrue(2 == singleObj.search(2).getKey());
        assertNull(singleObj.search(5));

        test.add("val", 2);
        assertEquals("val", test.search("val").getKey());
        assertTrue(2 == test.search("val").getValue());
    }


    /**
     * tests makeEmpty and isEmpty methods
     */
    public void testMakeAndIsEmpty() {
        assertTrue(test.isEmpty());
        test.add("val", 1);
        assertFalse(test.isEmpty());
        test.makeEmpty();
        assertTrue(test.isEmpty());
    }


    /**
     * tests the findInRange method
     */
    public void testFindInRange() {
        assertEquals("", test.findInRange("", ""));

        singleObj.add(2, "val2");
        singleObj.add(5, "val5");
        singleObj.add(3, "val3");
        singleObj.add(7, "val7");
        singleObj.add(8, "val8");
        singleObj.add(10, "val10");
        // System.out.println(singleObj.findInRange(5, 10));
// System.out.println(singleObj.searchCount(2, 10));
// System.out.println(singleObj.searchCount(2, 5));
// assertEquals(13, singleObj.searchCount(2, 10));
// System.out.println(singleObj.toString());
        assertEquals(7, singleObj.searchCount(2, 5));
        assertEquals(7, singleObj.searchCount(1, 3));
        assertEquals(9, singleObj.searchCount(7, 10));

        assertEquals("val5\nval7\nval8\nval10", singleObj.findInRange(5, 10));
        assertEquals("val2\nval3\nval5\nval7", singleObj.findInRange(2, 7));
    }


    /**
     * tests toString method
     */
    public void testToStringNonEmpty() {
        assertEquals("This tree is empty", test.toString());

        test.add("M", 50);
        test.add("L", 40);
        test.add("R", 60);
        test.add("A", 10);
        test.add("Z", 70);

        String expected = "      null\n" + "    Z\n" + "      null\n" + "  R\n"
            + "    null\n" + "M\n" + "    null\n" + "  L\n" + "      null\n"
            + "    A\n" + "      null\n" + "Number of records: 5";

        assertEquals(expected, test.toString());
    }


    /**
     * test kwSearch method
     */
    public void testKWSearch() {
        assertEquals("", test.kwSearch(""));
        singleObj.add(2, "val2");
        singleObj.add(5, "val5");
        singleObj.add(3, "val3");
        singleObj.add(7, "val7");
        singleObj.add(8, "val8");
        singleObj.add(10, "val10");
        singleObj.add(1, "clone1");
        singleObj.add(1, "clone2");
        singleObj.add(1, "clone3");
        assertEquals("clone3\nclone2\nclone1\nval\n", singleObj.kwSearch(1));
    }
}
