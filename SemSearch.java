// On my honor:
// - I have not used source code obtained from another current or
// former student, or any other unauthorized source, either
// modified or unmodified.
//
// - All source code and documentation used in my program is
// either my original work, or was derived by me from the
// source code published in the textbook for this course.
//
// - I have not discussed coding details about this project with
// anyone other than my partner (in the case of a joint
// submission), instructor, ACM/UPE tutors or the TAs assigned
// to this course. I understand that I may discuss the concepts
// of this program with other students, and that another student
// may help me debug my program so long as neither of us writes
// anything during the discussion or modifies any computer file
// during the discussion. I have violated neither the spirit nor
// letter of this restriction.

/**
 * BST implementation for managing seminars
 */

/**
 * The class containing the main method.
 *
 * @author Piyush Chauhan
 * @author Brett Non
 * @version 9.27.2023
 */
public class SemSearch {

    private BinaryST<String, Seminar> dateTree;
    private BinaryST<String, Seminar> keyTree;
    private BinaryST<Integer, Seminar> idTree;
    private BinaryST<Integer, Seminar> costTree;
    private SeminarLocationTree locationTree;
    private int wdSize;

    /**
     * Constructor for SemSearch class
     * 
     * @param size
     *            the size of the world for the BinTree
     */
    public SemSearch(int size) {
        dateTree = new BinaryST<>();
        keyTree = new BinaryST<>();
        idTree = new BinaryST<>();
        costTree = new BinaryST<>();
        wdSize = size;
        locationTree = new SeminarLocationTree(0, 0, size, size);
    }

    
    /**
     * Searches the trees for a specific seminar(s)
     * 
     * @param criteria
     *            the identifier for the search
     */
    public void searchSeminar(String[] criteria) {
        String queryType = criteria[1];

        switch (queryType) {
            case "date":
                System.out.println("Seminars with dates in range " + criteria[2]
                    + " to " + criteria[3] + ":");
                if (dateTree.findInRange(criteria[2], criteria[3]).compareTo(
                    "") == 0) {
                    System.out.println(dateTree.searchCount(criteria[2],
                        criteria[3]) + " nodes visited in this search");
                }
                else {
                    System.out.println(dateTree.findInRange(criteria[2],
                        criteria[3]));
                    System.out.println(dateTree.searchCount(criteria[2],
                        criteria[3]) + " nodes visited in this search");
                }
                break;
            case "keyword":
                System.out.println("Seminars matching keyword " + criteria[2]
                    + ":");
                System.out.print(keyTree.kwSearch(criteria[2]));

                break;
            case "ID":
                if (idTree.search(Integer.parseInt(criteria[2])) == null) {
                    System.out.println(
                        "Search FAILED -- There is no record with ID "
                            + criteria[2]);
                }
                else {
                    System.out.println("Found record with ID " + criteria[2]
                        + ":");
                    System.out.println(idTree.search(Integer.parseInt(
                        criteria[2])).getValue().toString());
                }
                break;
            case "cost":
                System.out.println("Seminars with costs in range " + criteria[2]
                    + " to " + criteria[3] + ":");
                if (costTree.findInRange(Integer.parseInt(criteria[2]), Integer
                    .parseInt(criteria[3])).compareTo("") == 0) {
                    System.out.println(costTree.searchCount(Integer.parseInt(
                        criteria[2]), Integer.parseInt(criteria[3]))
                        + " nodes visited in this search");
                }
                else {
                    System.out.println(costTree.findInRange(Integer.parseInt(
                        criteria[2]), Integer.parseInt(criteria[3])));
                    System.out.println(costTree.searchCount(Integer.parseInt(
                        criteria[2]), Integer.parseInt(criteria[3]))
                        + " nodes visited in this search");
                }
                break;
            default:
                System.out.print("Seminars within " + criteria[4] + " units of "
                    + criteria[2] + ", " + criteria[3] + ":\n");
                short x = (short)Integer.parseInt(criteria[2]);
                short y = (short)Integer.parseInt(criteria[3]);
                short radius = (short)Integer.parseInt(criteria[4]);
                locationTree.searchWithinRadius(x, y, radius);
        }
    }

    /**
     * Adds a seminar into the different trees
     * 
     * @param seminar
     *            the seminar to be added
     * @param identifier
     *            the id for the seminar
     */
    public void addSeminar(Seminar seminar, int identifier) {
        if (seminar.x() < 0 
            || seminar.x() >= wdSize 
            || seminar.y() < 0
            || seminar.y() >= wdSize) {
            System.out.println("Insert FAILED - Bad x, y coordinates: "
                + seminar.x() + ", " + seminar.y());
        }
        else if (idTree.search(identifier) == null) {
            dateTree.add(seminar.date(), seminar);
            for (String keyword : seminar.keywords()) {
                keyTree.add(keyword, seminar);
            }
            idTree.add(identifier, seminar);
            costTree.add(seminar.cost(), seminar);
            locationTree.addSeminar(seminar);
            System.out.println("Successfully inserted record with ID "
                + identifier);
            System.out.println(idTree.search((identifier)).getValue()
                .toString());
        }
        else {
            System.out.println(
                "Insert FAILED - There is already a record with ID "
                    + identifier);
        }

    }


    /**
     * Deletes a seminar from the trees based on ID
     * 
     * @param identifier
     *            the id for the seminar to be deleted
     */
    public void removeSeminar(int identifier) {
        TreeNode<Integer, Seminar> seminarNode = idTree.remove2(identifier);

        if (seminarNode == null) {
            System.out.println("Delete FAILED -- There is no record with ID "
                + identifier);
            return;
        }

        costTree.remove2(seminarNode.getValue().cost());
        for (String keyword : seminarNode.getValue().keywords()) {
            keyTree.remove2(keyword);
        }
        dateTree.remove2(seminarNode.getValue().date());
        locationTree.removeSeminar(identifier, seminarNode.getValue().x(),
            seminarNode.getValue().y());

        System.out.println("Record with ID " + identifier
            + " successfully deleted from the database");
    }


    


    /**
     * Prints out a visual representation of the trees
     * 
     * @param category
     *            the type of tree to display
     */
    public void displayTreeByType(String category) {
        switch (category) {
            case "date":
                System.out.println("Date Tree:\n" + dateTree);
                break;
            case "keyword":
                System.out.println("Keyword Tree:\n" + keyTree);
                break;
            case "cost":
                System.out.println("Cost Tree:\n" + costTree);
                break;
            case "ID":
                System.out.println("ID Tree:\n" + idTree);
                break;
            case "location":
                System.out.print(
                    "Location Tree:\n" + locationTree.toString());
                break;
        }
    }


    /**
     * Main method that runs the code
     * 
     * @param args
     *            the arguments from the command line
     * @throws Exception
     *             when file not found
     */
    public static void main(String[] args) throws Exception {
        int size = Integer.parseInt(args[0]);
        String inputFile = args[1];

        SemSearch seminarSearcher = new SemSearch(size);
        CommandProcessor commandParser = new CommandProcessor(inputFile,
            seminarSearcher);
        commandParser.processCommands();
    }
}
