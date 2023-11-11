/**
 * Empty Node class
 * 
 * @author Brett Noneman
 * @author Piyush Chauhan
 * @version 10/10/23
 */
public class EmptyNode extends AbstractTreeNode {
    /**
     * Constructor for the BlankNode.
     */
    public EmptyNode() {
    }


    /**
     * Adds a new Seminar instance to the tree.
     * 
     * @param seminarInstance
     *            Instance of the Seminar to be added.
     * @param isVertical
     *            Indicator for vertical split.
     * @param startX
     *            Starting x-coordinate.
     * @param startY
     *            Starting y-coordinate.
     * @param boundaryWidth
     *            Width of the boundary.
     * @param boundaryHeight
     *            Height of the boundary.
     * @return A fresh LeafNode with the given Seminar.
     */
    @Override
    public AbstractTreeNode addEntry(
        Seminar seminarInstance,
        boolean isVertical,
        int startX,
        int startY,
        int boundaryWidth,
        int boundaryHeight) {
        return new LeafNode(seminarInstance);

    }


    /**
     * Locates nodes within a specified radius.
     * 
     * @param centerX
     *            X-coordinate for search.
     * @param centerY
     *            Y-coordinate for search.
     * @param radius
     *            Radius of search.
     * @param boundaryX
     *            Starting x-coordinate of boundary.
     * @param boundaryY
     *            Starting y-coordinate of boundary.
     * @param boundaryWidth
     *            Width of the boundary.
     * @param boundaryHeight
     *            Height of the boundary.
     * @param visitedCount
     *            Number of nodes visited.
     * @param isVertical
     *            Indicator for vertical split.
     * @return Total number of nodes visited.
     */
    public int locateInRange(
        int centerX,
        int centerY,
        int radius,
        int boundaryX,
        int boundaryY,
        int boundaryWidth,
        int boundaryHeight,
        int visitedCount,
        boolean isVertical) {
        
        if (overlapExists(centerX, centerY, radius, boundaryX, boundaryY,
            boundaryWidth, boundaryHeight)) {
            visitedCount++;
        }
        return visitedCount;

    }
    
    


    /**
     * Eliminates a node using its unique ID.
     * 
     * @param nodeX
     *            X-coordinate of the target node.
     * @param nodeY
     *            Y-coordinate of the target node.
     * @param startX
     *            Starting x-coordinate of boundary.
     * @param startY
     *            Starting y-coordinate of boundary.
     * @param boundaryWidth
     *            Width of the boundary.
     * @param boundaryHeight
     *            Height of the boundary.
     * @param isVertical
     *            Indicator for vertical split.
     * @param nodeId
     *            Unique ID for the node.
     * @return Tree after node removal.
     */
    @Override
    public AbstractTreeNode remove(
        int nodeX,
        int nodeY,
        int startX,
        int startY,
        int boundaryWidth,
        int boundaryHeight,
        boolean isVertical,
        int nodeId) {
        return this;
    }
}