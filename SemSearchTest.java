import student.TestCase;

/**
 * @author Piyush Chauhan
 * @author Brett Noneman
 * @version 2023.9.19
 */
public class SemSearchTest extends TestCase {

    /** The SemSearch object being tested. */
    private SemSearch search;

    /** A mock seminar object used for testing. */
    private Seminar mockSeminar;

    /** A constant for a valid seminar identifier used in tests. */
    private final static int VALID_IDENTIFIER = 1;

    /**
     * A constant for an invalid seminar identifier that is not
     * added to the tree and used for negative test cases.
     */
    private final static int INVALID_IDENTIFIER = 99;

    /**
     * Sets up the test environment before each test.
     * <p>
     * Initializes a {@code SemSearch} object and a mock seminar object
     * and adds the seminar to the search object.
     * </p>
     */
    public void setUp() {
        systemOut().clearHistory(); // Clearing output buffer
        search = new SemSearch(40);

        String[] keywords = { "Good", "Bad", "Ugly" };
        String expected = "ID: 1729, Title: Seminar Title\n"
            + "Date: 2405231000, Length: 75, X: 15, Y: 33, Cost: 125\n"
            + "Description: This is a great seminar\n"
            + "Keywords: Good, Bad, Ugly";
        mockSeminar = new Seminar(1729, "Seminar Title", "2405231000", 75,
            (short)15, (short)33, 125, keywords, "This is a great seminar");
        search.addSeminar(mockSeminar, VALID_IDENTIFIER);
    }


    /**
     * Tests the scenario where an attempt is made to remove
     * a seminar using an invalid ID.
     */
    public void testRemoveSeminarWithInvalidID() {
        search.removeSeminar(INVALID_IDENTIFIER);
        assertTrue(systemOut().getHistory().contains(
            "Delete FAILED -- There is no record with ID "
                + INVALID_IDENTIFIER));
    }


    /**
     * Tests the scenario where a seminar is removed using a valid ID.
     */
    public void testRemoveSeminarWithValidID() {
        search.removeSeminar(VALID_IDENTIFIER);
        assertTrue(systemOut().getHistory().contains("Record with ID "
            + VALID_IDENTIFIER + " successfully deleted from the database"));
    }


    /**
     * Tests the scenario where a keyword search is done
     * with a keyword that exists in the keyTree.
     */
    public void testSearchSeminarWithExistingKeyword() {
        String existingKeyword = "Good";
        String[] criteria = { "search", "keyword", existingKeyword };
        search.searchSeminar(criteria);
        assertTrue(systemOut().getHistory().contains(
            "Seminars matching keyword " + existingKeyword));

        String[] criteria2 = { "search", "cost", "20", "45" };
        search.searchSeminar(criteria2);
        assertTrue(systemOut().getHistory().contains(
            "Seminars with costs in range 20 to 45:\n2 nodes visited in"
                + " this search"));

        String[] criteria3 = { "search", "cost", "120", "130" };
        search.searchSeminar(criteria3);
        assertTrue(systemOut().getHistory().contains(
            "Seminars with costs in range 120 to 130:\n"
                + "ID: 1729, Title: Seminar Title\n"
                + "Date: 2405231000, Length: 75, X: 15, Y: 33, Cost: 125\n"
                + "Description: This is a great seminar\n"
                + "Keywords: Good, Bad, Ugly\n"
                + "3 nodes visited in this search"));

        String[] criteria4 = { "search", "date", "120", "130" };
        search.searchSeminar(criteria4);

        assertTrue(systemOut().getHistory().contains(
            "Seminars with dates in range 120 to 130:\n2 nodes visited in "
                + "this search"));

        String[] criteria5 = { "search", "date", "2405231000", "2405231000" };
        search.searchSeminar(criteria5);
        assertTrue(systemOut().getHistory().contains(
            "Seminars with dates in range 2405231000 to 2405231000:\n"
                + "ID: 1729, Title: Seminar Title\n"
                + "Date: 2405231000, Length: 75, X: 15, Y: 33, Cost: 125\n"
                + "Description: This is a great seminar\n"
                + "Keywords: Good, Bad, Ugly\n"
                + "2 nodes visited in this search"));

        systemOut().clearHistory();
        String[] criteria6 = { "search", "ID", "22" };
        search.searchSeminar(criteria6);
        assertTrue(systemOut().getHistory().contains(
            "Search FAILED -- There is no record with ID 22"));

        String[] crit = { "search", "ID", "1" };
        search.displayTreeByType("ID");
        search.searchSeminar(crit);
        assertTrue(systemOut().getHistory().contains("Found record with ID 1:\n"
            + "ID: 1729, Title: Seminar Title\n"
            + "Date: 2405231000, Length: 75, X: 15, Y: 33, Cost: 125\n"
            + "Description: This is a great seminar\n"
            + "Keywords: Good, Bad, Ugly"));

        systemOut().clearHistory();
        String[] criteria7 = { "search", "keyword", "random" };
        search.searchSeminar(criteria7);
        assertTrue(systemOut().getHistory().contains(
            "Seminars matching keyword random:\n"));

        String[] criteria8 = { "search", "keyword", "Good" };
        search.searchSeminar(criteria8);
        assertTrue(systemOut().getHistory().contains(
            "Seminars matching keyword Good:\n"
                + "ID: 1729, Title: Seminar Title\n"
                + "Date: 2405231000, Length: 75, X: 15, Y: 33, Cost: 125\n"
                + "Description: This is a great seminar\n"
                + "Keywords: Good, Bad, Ugly"));

    }


    /**
     * tests add method for SemSearch
     */
    public void testAdd() {
        String[] keywords = { "Good", "Bad", "Ugly" };
        mockSeminar = new Seminar(1729, "Seminar Title", "2405231000", 75,
            (short)15, (short)128, 125, keywords, "This is a great seminar");
        search.addSeminar(mockSeminar, VALID_IDENTIFIER);
        assertTrue(systemOut().getHistory().contains(
            "Insert FAILED - Bad x, y coordinates: 15, 128"));
        mockSeminar = new Seminar(1729, "Seminar Title", "2405231000", 75,
            (short)128, (short)15, 125, keywords, "This is a great seminar");
        search.addSeminar(mockSeminar, VALID_IDENTIFIER);
        assertTrue(systemOut().getHistory().contains(
            "Insert FAILED - Bad x, y coordinates: 128, 15"));
        mockSeminar = new Seminar(1729, "Seminar Title", "2405231000", 75,
            (short)-5, (short)15, 125, keywords, "This is a great seminar");
        search.addSeminar(mockSeminar, VALID_IDENTIFIER);
        assertTrue(systemOut().getHistory().contains(
            "Insert FAILED - Bad x, y coordinates: -5, 15"));
        mockSeminar = new Seminar(1729, "Seminar Title", "2405231000", 75,
            (short)15, (short)-5, 125, keywords, "This is a great seminar");
        search.addSeminar(mockSeminar, VALID_IDENTIFIER);
        assertTrue(systemOut().getHistory().contains(
            "Insert FAILED - Bad x, y coordinates: 15, -5"));
        mockSeminar = new Seminar(1729, "Seminar Title", "2405231000", 75,
            (short)15, (short)15, 125, keywords, "This is a great seminar");
        search.addSeminar(mockSeminar, VALID_IDENTIFIER);
        assertTrue(systemOut().getHistory().contains(
            "Insert FAILED - There is already a record with ID 1"));
    }

}
