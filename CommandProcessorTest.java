import student.TestCase;
import java.io.File;

/**
 * Test class for the CommandProcessor. This class provides unit tests for the
 * CommandProcessor to ensure it processes various commands correctly.
 * 
 * @author Piyush Chauhan
 * @author Brett Noneman
 * @version 2023.9.19
 */
public class CommandProcessorTest extends TestCase {

    /** The processor under test. */
    private CommandProcessor processor;

    /** Mock seminar search engine used for the tests. */
    private SemSearch searchEngine;

    /** Directory containing test files. */
    private static final String TEST_FILES_DIR = "./testFiles"; // Path to the
                                                                // directory
                                                                // containing
                                                                // your test
                                                                // files

    /**
     * Setup method to prepare objects for each test.
     */
    public void setUp() {
        searchEngine = new SemSearch(128);
    }


    /**
     * Test the processCommands method of CommandProcessor.
     * 
     * @throws Exception
     *             if an error occurs during processing
     */
    public void testProcessCommands() throws Exception {
        processor = new CommandProcessor("lib/processCommands.txt",
            searchEngine);

        String result = processor.processCommands();
        String expected = "ID: 1, Title: SeminarTitle\r\n"
            + "Date: 2023-09-27, Length: 10, X: 20, Y: 30, Cost: 40\r\n"
            + "Description: This is a seminar description.\r\n"
            + "Keywords: A, B, C\r\n" + "ID: 2, Title: AnotherSeminar\r\n"
            + "Date: 2023-10-27, Length: 50, X: 60, Y: 70, Cost: 80\r\n"
            + "Description: This is another seminar description.\r\n"
            + "Keywords: D, E, F\r\n";
        assertEquals(expected, result);
    }


    /**
     * Test the processing of a delete command.
     * 
     * @throws Exception
     *             if an error occurs during processing
     */
    public void testDeleteCommand() throws Exception {
        processor = new CommandProcessor("lib/deleteCommand.txt", searchEngine);

        String result = processor.processCommands();
        String expected = "123\n";
        assertEquals(expected, result);
    }


    /**
     * Test the processing of a print command.
     * 
     * @throws Exception
     *             if an error occurs during processing
     */
    public void testPrintCommand() throws Exception {
        processor = new CommandProcessor("lib/printCommand.txt", searchEngine);

        String result = processor.processCommands();
        String expected = "ID\n";
        assertEquals(expected, result);
    }


    /**
     * Test the processing of a search command.
     * 
     * @throws Exception
     *             if an error occurs during processing
     */
    public void testSearchCommand() throws Exception {
        processor = new CommandProcessor("lib/searchCommand.txt", searchEngine);

        String result = processor.processCommands();
        String expected = "ID\n"; // Assuming this is the expected output
        assertEquals(expected, result);
    }
}
