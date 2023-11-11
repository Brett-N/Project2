import java.util.Scanner;
import java.io.File;

/**
 * Processes the commands that are read from the input file
 * 
 * @author Piyush Chauhan
 * @author Brett Noneman
 * @version 2023.9.19
 */
public class CommandProcessor {

    private final String fileToRead;
    private final SemSearch seminarSearch;

    /**
     * Constructor for the CommandProcessor class
     * 
     * @param file
     *            the file to read commands from
     * @param searchEngine
     *            the SemSearch object
     */
    public CommandProcessor(String file, SemSearch searchEngine) {
        this.fileToRead = file;
        this.seminarSearch = searchEngine;
    }


    /**
     * Splits a string by the spaces in it
     * 
     * @param str
     *            the string to split
     * @return Array with each index containing a split string
     */
    private String[] splitBySpaces(String str) {
        return str.split("\\s+");
    }


    /**
     * Processes the main commands for the input
     * 
     * @return String that contains proper output for commands
     * @throws Exception
     *             if proper command not found
     */
    public String processCommands() throws Exception {
        StringBuilder result = new StringBuilder();
        Scanner scanner = new Scanner(new File(fileToRead));

        while (scanner.hasNextLine()) {
            String command = scanner.nextLine().trim();
            String[] parts = splitBySpaces(command);

            if (command.startsWith("insert")) {
                Seminar seminar = constructSeminarFromScanner(scanner,
                    parts[1]);
                result.append(seminar.toString()).append("\n");
                seminarSearch.addSeminar(seminar, Integer.parseInt(parts[1]));
            }
            else if (command.startsWith("search")) {
                result.append(parts[1]).append("\n");
                seminarSearch.searchSeminar(parts);
            }
            else if (command.startsWith("delete")) {
                result.append(parts[1]).append("\n");
                seminarSearch.removeSeminar(Integer.parseInt(parts[1]));
            }
            else if (command.startsWith("print")) {
                result.append(parts[1]).append("\n");
                seminarSearch.displayTreeByType(parts[1]);
            }
        }

        scanner.close();
        return result.toString();
    }


    /**
     * Creates a seminar object based on the line that was scanned
     * 
     * @param scanner
     *            scans the line in the input file
     * @param idString
     *            the string with the id for the seminar
     * @return fully constructed seminar object
     */
    private Seminar constructSeminarFromScanner(
        Scanner scanner,
        String idString) {
        int id = Integer.parseInt(idString);
        String title = scanner.nextLine().trim();
        String[] dateLineParts = splitBySpaces(scanner.nextLine().trim());
        String date = dateLineParts[0];
        int lValue = Integer.parseInt(dateLineParts[1]);
        short xValue = Short.parseShort(dateLineParts[2]);
        short yValue = Short.parseShort(dateLineParts[3]);
        int cValue = Integer.parseInt(dateLineParts[4]);
        String[] kArray = splitBySpaces(scanner.nextLine().trim());
        String description = scanner.nextLine().trim();

        return new Seminar(id, title, date, lValue, xValue, yValue, cValue,
            kArray, description);
    }
}
