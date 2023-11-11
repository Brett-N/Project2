/**
 * Represents a base class for tree nodes.
 * 
 * @author Brett Noneman
 * @version 10/10/23
 */
public abstract class AbstractTreeNode {
    
    /**
     * Method to add an item
     * 
     * @param itemToAdd
     *            item to add
     * @param isVertical
     *            orientation flag
     * @param startX
     *            initial x-coordinate
     * @param startY
     *            initial y-coordinate
     * @param boundaryWidth
     *            width of boundary
     * @param boundaryHeight
     *            height of boundary
     * @return updated element
     */
    public abstract AbstractTreeNode addEntry(
        Seminar itemToAdd,
        boolean isVertical,
        int startX,
        int startY,
        int boundaryWidth,
        int boundaryHeight);

    /**
     * Locate elements within a given range
     * 
     * @param searchX
     *            x-coordinate for search
     * @param searchY
     *            y-coordinate for search
     * @param range
     *            search range
     * @param originX
     *            x-origin
     * @param originY
     *            y-origin
     * @param boundaryWidth
     *            width of boundary
     * @param boundaryHeight
     *            height of boundary
     * @param elementsCount
     *            count of elements encountered
     * @param isVertical
     *            orientation flag
     * @return count of encountered elements
     */
    public abstract int locateInRange(
        int searchX,
        int searchY,
        int range,
        int originX,
        int originY,
        int boundaryWidth,
        int boundaryHeight,
        int elementsCount,
        boolean isVertical);

    /**
     * Removes the node from the tree
     * 
     * @param searchX
     *            the x for search
     * @param searchY
     *            the y for search
     * @param originX
     *            x origin
     * @param originY
     *            y origin
     * @param boundaryWidth
     *            width of boundary
     * @param boundaryHeight
     *            height of boundary
     * @param isVertical
     *            orientation flag
     * @param entryId
     *            id for item to be entered
     * @return the removed node
     */
    public abstract AbstractTreeNode remove(
        int searchX,
        int searchY,
        int originX,
        int originY,
        int boundaryWidth,
        int boundaryHeight,
        boolean isVertical,
        int entryId);

    
    /**
     * Check overlap between circle and rectangle
     * 
     * @param centerX
     *            center x of circle
     * @param centerY
     *            center y of circle
     * @param radius
     *            circle's radius
     * @param boundaryX
     *            x-coordinate for rectangle
     * @param boundaryY
     *            y-coordinate for rectangle
     * @param boundaryWidth
     *            rectangle's width
     * @param boundaryHeight
     *            rectangle's height
     * @return true if overlap exists, false otherwise
     */
    public boolean overlapExists(
        int centerX,
        int centerY,
        int radius,
        int boundaryX,
        int boundaryY,
        int boundaryWidth,
        int boundaryHeight) {
        int nearestX = Math.max(boundaryX, Math.min(centerX, boundaryX
            + boundaryWidth)); // Untested
        int nearestY = Math.max(boundaryY, Math.min(centerY, boundaryY
            + boundaryHeight)); // Untested
        return Math.pow(centerX - nearestX, 2) + Math.pow(centerY - nearestY,
            2) <= Math.pow(radius, 2);
    }
}