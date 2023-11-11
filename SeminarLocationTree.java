/**
 * BinaryTreeContainer class
 * 
 * @author Brett Noneman
 * @author Piyush Chauhan
 * 
 * @version 10/10/23
 */
public class SeminarLocationTree {
    private AbstractTreeNode rootNode;
    private int startX;
    private int startY;
    private int boundaryWidth;
    private int boundaryHeight;
    private static final String INDENTATION_STEP = "  ";

    /**
     * Constructor for the binary tree container
     * 
     * @param startX
     *            Starting x-coordinate
     * @param startY
     *            Starting y-coordinate
     * @param canvasWidth
     *            Size of the canvas width-wise
     * @param canvasHeight
     *            Size of the canvas height-wise
     */
    public SeminarLocationTree(
        int startX,
        int startY,
        int canvasWidth,
        int canvasHeight) {
        this.rootNode = new EmptyNode();
        this.startX = startX;
        this.startY = startY;
        this.boundaryWidth = canvasWidth;
        this.boundaryHeight = canvasHeight;
    }


    /**
     * Recursive insertion method
     * 
     * @param newSeminar
     *            New seminar data to insert
     */
    public void addSeminar(Seminar newSeminar) {
        rootNode = rootNode.addEntry(
            newSeminar, true, startX, startY,
            boundaryWidth, boundaryHeight);
    }


    /**
     * Search records within a certain radius
     * 
     * @param x
     *            x-coordinate
     * @param y
     *            y-coordinate
     * @param radius
     *            Search radius
     */
    public void searchWithinRadius(int x, int y, int radius) {
        System.out.print(rootNode.locateInRange(
            x, y, radius, startX, startY, boundaryWidth, boundaryHeight,
            0, true) + " nodes visited in this search\n");
    }


    /**
     * Remove a record
     * 
     * @param id
     *            Seminar identifier
     * @param x
     *            x-cordinate
     * @param y
     *            y-coordinate
     */
    public void removeSeminar(int id, int x, int y) {
        rootNode = rootNode.remove(
            x, y, startX, startY, boundaryWidth,
            boundaryHeight, true, id);
    }


    /**
     * Represents the binary tree as a string
     * 
     * @return Tree representation as string
     */
    @Override
    public String toString() {
        return treeString(
            rootNode, "", new StringBuilder()).toString();
    }

    /**
     * Private method to build a String
     * representation of the tree
     * 
     * @param current
     *            Current node being traversed
     * 
     * @param indentation
     *            Indentation to use for representation
     * 
     * @param strRep
     *            String builder instance
     */
    private StringBuilder treeString(
        AbstractTreeNode current,
        String indentation,
        StringBuilder strRep) {
        if (current instanceof EmptyNode) {
            appendEmptyNode(strRep, indentation);
        } 
        else if (current instanceof InternalNode) {
            appendInternalNode(
                strRep, indentation, (InternalNode) current);
        } 
        else {
            appendLeafNode(
                strRep, indentation, (LeafNode) current);
        }
        return strRep;
    }

    /**
     * Appends the string representation of an empty 
     * node to the StringBuilder object.
     *
     * @param strRep      
     *      The StringBuilder object to 
     *      append the representation to.
     * @param indentation 
     *      The current level of 
     *      indentation for the representation.
     */
    private void appendEmptyNode(
        StringBuilder strRep,
        String indentation) {
        strRep.append(indentation).append("E\n");
    }

    /**
     * Appends the string representation of an internal
     *  node and its children to the StringBuilder object.
     *
     * @param strRep      
     *      The StringBuilder object to
     *       append the representation to.
     * @param indentation 
     *      The current level of 
     *      indentation for the representation.
     * @param node        
     *      The internal node whose string
     *       representation is being built.
     */
    private void appendInternalNode(
        StringBuilder strRep,
        String indentation,
        InternalNode node) {
        strRep.append(indentation).append("I\n");
        treeString(node.fetch(
            "left"), indentation + INDENTATION_STEP, strRep);
        treeString(node.fetch(
            "right"), indentation + INDENTATION_STEP, strRep);
    }

    /**
     * Appends the string representation of a 
     * leaf node to the StringBuilder object.
     *
     * @param strRep      
     *      The StringBuilder object
     *       to append the representation to.
     * @param indentation 
     *      The current level of 
     *      indentation for the representation.
     * @param node        
     *      The leaf node whose 
     *      string representation is being built.
     */
    private void appendLeafNode(
        StringBuilder strRep,
        String indentation,
        LeafNode node) {
        strRep.append(indentation).append(
            "Leaf with ").append(node.getCount()).append(
                " objects: ").append(node.toString()).append("\n");
    }
}