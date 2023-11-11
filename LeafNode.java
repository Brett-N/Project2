/**
 * An implementation of a leaf node for bintree
 * 
 * @author Brett Noneman
 * @author Piyush Chauhan
 * @version 10/10/23
 */
public class LeafNode extends AbstractTreeNode {
    private CustomList seminarList;

    /**
     * Constructs a leaf node.
     * 
     * @param seminarList
     *            List of seminar objects.
     */
    public LeafNode(CustomList seminarList) {
        this.seminarList = seminarList;
    }
    
    /**
     * Constructs a leaf node.
     * 
     * @param addedSeminar
     *            Seminar being added
     */
    public LeafNode(Seminar addedSeminar) {
        seminarList = new CustomList();
        seminarList.append(addedSeminar, addedSeminar.id());
    }


    /**
     * Fetches the count of items in the list.
     * 
     * @return Count of seminars.
     */
    public int getCount() {
        return seminarList.size();
    }


    /**
     * Method to add an item
     * 
     * @param seminar
     *            item to add
     * @param axisSplit
     *            orientation flag
     * @param xBase
     *            initial x-coordinate
     * @param yBase
     *            initial y-coordinate
     * @param boundaryWidth
     *            width of boundary
     * @param boundaryHeight
     *            height of boundary
     * @return updated element
     */
    @Override
    public AbstractTreeNode addEntry(
        Seminar seminar,
        boolean axisSplit,
        int xBase,
        int yBase,
        int boundaryWidth,
        int boundaryHeight) {

        boolean coordinatesMatch = false;

        for (Seminar s : seminarList.convertToArray()) {
            if (seminar.x() == s.x() && seminar.y() == s.y()) {
                coordinatesMatch = true;
                break;
            }
        }

        if (coordinatesMatch) {
            seminarList.append(seminar, seminar.id());
            return this;
        } 
        else {
            InternalNode partitionedNode = 
                new InternalNode(new EmptyNode(), new EmptyNode());
            
            for (Seminar s : seminarList.convertToArray()) {
                partitionedNode.addEntry(s, axisSplit,
                    xBase, yBase, boundaryWidth, boundaryHeight);
            }
            partitionedNode.addEntry(seminar, axisSplit, xBase,
                yBase, boundaryWidth, boundaryHeight);
            return partitionedNode;
        }
    }


    /**
     * Locate elements within a given range
     * 
     * @param centerX
     *            x-coordinate for search
     * @param centerY
     *            y-coordinate for search
     * @param radius
     *            search range
     * @param boundaryX
     *            x-origin
     * @param boundaryY
     *            y-origin
     * @param boundaryWidth
     *            width of boundary
     * @param boundaryHeight
     *            height of boundary
     * @param visitedCount
     *            count of elements encountered
     * @param axisSplit
     *            orientation flag
     * @return count of encountered elements
     */
    @Override
    public int locateInRange(
        int centerX,
        int centerY,
        int radius,
        int boundaryX,
        int boundaryY,
        int boundaryWidth,
        int boundaryHeight,
        int visitedCount,
        boolean axisSplit) {
        
        if (overlapExists(
            centerX, centerY,
            radius, boundaryX, 
            boundaryY, boundaryWidth,
            boundaryHeight)) {
            visitedCount++;
            for (Seminar s : seminarList.convertToArray()) {
                if (distanceCheck(centerX, centerY, s.x(), s.y(), radius)) {
                    System.out.print("Found a record with key value " + s.id()
                        + " at " + s.x() + ", " + s.y() + "\n");
                }
            }
        }
        return visitedCount;
    }
    
    /**
     * Distance checker method
     * 
     * @param startX
     *            Starting X position
     * @param startY
     *            Starting Y position
     * @param endX
     *            Ending X position
     * @param endY
     *            Ending Y position
     * @param radius
     *            The radius
     * @return True or false depending on X and Y values
     */
    public boolean distanceCheck(
        int startX,
        int startY,
        int endX,
        int endY,
        int radius) {
        return Math.sqrt(Math.pow(endX - startX, 2) + Math.pow(endY - startY,
            2)) <= radius;
    }


    /**
     * Removes the node from the tree
     * 
     * @param xPos
     *            the x for search
     * @param yPos
     *            the y for search
     * @param xBase
     *            x origin
     * @param yBase
     *            y origin
     * @param boundaryWidth
     *            width of boundary
     * @param boundaryHeight
     *            height of boundary
     * @param axisSplit
     *            orientation flag
     * @param identifier
     *            id for item to be entered
     * @return the removed node
     */
    @Override
    public AbstractTreeNode remove(
        int xPos,
        int yPos,
        int xBase,
        int yBase,
        int boundaryWidth,
        int boundaryHeight,
        boolean axisSplit,
        int identifier) {
        if (seminarList.removeByIdentifier(
            identifier) && seminarList.isVacant()) {
            return new EmptyNode();
        }
        return this;
    }


    /**
     * Represents the seminars as a string.
     * 
     * @return String representation.
     */
    public String toString() {
        return seminarList.identifierString();
    }
}
