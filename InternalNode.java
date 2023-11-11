/**
 * Represents an intermediary node within a binary tree structure.
 * 
 * @author Brett Noneman
 * @author Piyush Chauhan
 * @version 10/10/23
 */
public class InternalNode extends AbstractTreeNode {
    private AbstractTreeNode leftChild;
    private AbstractTreeNode rightChild;

    /**
     * Constructs an intermediary node with two child nodes.
     * 
     * @param leftChild
     *            Node to the left.
     * @param rightChild
     *            Node to the right.
     */
    public InternalNode(
        AbstractTreeNode leftChild, AbstractTreeNode rightChild) {
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }


    /**
     * Retrieves a child node.
     * 
     * @param node which node to fetch
     * @return The node on the left.
     * @throws IllegalArgumentException if node value is not "left" or "right".
     */
    public AbstractTreeNode fetch(String node) {
        
        if ("left".equals(node))
        {
            return leftChild;
        }
        else if ("right".equals(node)) //Untested
        {
            return rightChild;
        }
        else
        {
            throw new IllegalArgumentException(
                "Invalid node value."
                + " Expected 'left' or 'right' but got: " + node);
        }  
    }


    /**
     * Incorporates a new Seminar object into the tree.
     * 
     * @param seminar
     *            The Seminar object to be incorporated.
     * @param isVertical
     *            Indicates if the division is vertical.
     * @param bottomX
     *            bottomX Base coordinates for bottom-left corner.
     * @param bottomY
     *            bottomY Base coordinates for bottom-left corner.
     * @param boundaryWidth
     *            Span of the enclosing box.
     * @param boundaryHeight
     *            Altitude of the enclosing box.
     * @return A fresh LeafNode housing the Seminar.
     */
    @Override
    public AbstractTreeNode addEntry(
        Seminar seminar,
        boolean isVertical,
        int bottomX,
        int bottomY,
        int boundaryWidth,
        int boundaryHeight) {
        AbstractTreeNode childToModify;
        int median;

        if (isVertical) {
            median = (bottomX + boundaryWidth) / 2;
            if (seminar.x() < median) {
                childToModify = leftChild;
                boundaryWidth /= 2;
            } 
            else {
                childToModify = rightChild;
                bottomX = median;
            }
        } 
        else {
            median = (bottomY + boundaryHeight) / 2; //Untested
            if (seminar.y() < median) {
                childToModify = leftChild;
                boundaryHeight /= 2;
            } 
            else {
                childToModify = rightChild;
                bottomY = median;
            }
        }

        if (childToModify == leftChild) {
            leftChild = childToModify.addEntry(
                seminar, !isVertical, bottomX, bottomY, 
                boundaryWidth, boundaryHeight);
        } 
        else {
            rightChild = childToModify.addEntry(
                seminar, !isVertical, bottomX, bottomY, 
                boundaryWidth, boundaryHeight); //Untested
        }

        return this;
    }


    /**
     * Locates nodes within a specified proximity.
     * 
     * @param centerX
     *            coordY Positional coordinates for the search point.
     * @param centerY
     *            coordY Positional coordinates for the search point.
     * @param radius
     *            Search circumference.
     * @param boundaryX
     *            baseY Base coordinates for bottom-left corner of bounding
     *            region.
     * @param boundaryY
     *            baseY Base coordinates for bottom-left corner of bounding
     *            region.
     * @param boundaryWidth
     *            Span of the enclosing region.
     * @param boundaryHeight
     *            Altitude of the enclosing region.
     * @param visitedCount
     *            Count of nodes encountered.
     * @param isVertical
     *            Indicates if the split is vertical.
     * @return Updated count of encountered nodes.
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
        boolean isVertical) {

        if (overlapExists(
            centerX, centerY, radius, boundaryX, 
            boundaryY, boundaryWidth, boundaryHeight)) {
            visitedCount++;

            if (isVertical) {
                visitedCount = processVertical(
                    centerX, centerY, radius, boundaryX,
                    boundaryY, boundaryWidth, boundaryHeight, visitedCount);
            } 
            else {
                visitedCount = processHorizontal(
                    centerX, centerY, radius, boundaryX, boundaryY,
                    boundaryWidth, boundaryHeight, visitedCount);
            }
        }
        return visitedCount;
    }
    
    /**
     * Process the given boundary vertically to determine if 
     * it's within the provided radius
     * from the center point (centerX, centerY) and 
     * updates the visitedCount accordingly.
     *
     * @param centerX       
     *      The x-coordinate of the center point.
     * @param centerY       
     *      The y-coordinate of the center point.
     * @param radius        
     *      The radius to determine the range.
     * @param boundaryX     
     *      The x-coordinate of the starting 
     *      point of the boundary.
     * @param boundaryY     
     *      The y-coordinate of the starting 
     *      point of the boundary.
     * @param boundaryWidth 
     *      The width of the boundary.
     * @param boundaryHeight 
     *      The height of the boundary.
     * @param visitedCount   
     *      The count of nodes visited so far.
     * @return              
     *      The updated count of nodes visited 
     *      after processing the boundary vertically.
     */
    public int processVertical(
        int centerX,
        int centerY,
        int radius,
        int boundaryX,
        int boundaryY,
        int boundaryWidth,
        int boundaryHeight,
        int visitedCount) {

        int medianX = boundaryX + boundaryWidth / 2;
        visitedCount = leftChild.locateInRange(
            centerX, centerY, radius, boundaryX, boundaryY,
            boundaryWidth / 2, boundaryHeight, visitedCount, false);
        visitedCount = rightChild.locateInRange(
            centerX, centerY, radius, medianX, boundaryY,
            boundaryWidth, boundaryHeight, visitedCount, false);
        return visitedCount;
    }

    /**
     * Process the given boundary horizontally 
     * to determine if it's within the provided radius
     * from the center point (centerX, centerY) 
     * and updates the visitedCount accordingly.
     *
     * @param centerX       
     *      The x-coordinate of the center point.
     * @param centerY       
     *      The y-coordinate of the center point.
     * @param radius        
     *      The radius to determine the range.
     * @param boundaryX     
     *      The x-coordinate of the starting point of the boundary.
     * @param boundaryY     
     *      The y-coordinate of the starting point of the boundary.
     * @param boundaryWidth 
     *      The width of the boundary.
     * @param boundaryHeight 
     *      The height of the boundary.
     * @param visitedCount   
     *      The count of nodes visited so far.
     * @return              
     *      The updated count of nodes visited 
     *      after processing the boundary horizontally.
     */
    public int processHorizontal(
        int centerX,
        int centerY,
        int radius,
        int boundaryX,
        int boundaryY,
        int boundaryWidth,
        int boundaryHeight,
        int visitedCount) {

        int medianY = boundaryY + boundaryHeight / 2;
        visitedCount = leftChild.locateInRange(
            centerX, centerY, radius, boundaryX, boundaryY,
            boundaryWidth, boundaryHeight / 2, visitedCount, true);
        visitedCount = rightChild.locateInRange(
            centerX, centerY, radius, boundaryX, medianY,
            boundaryWidth, boundaryHeight, visitedCount, true);
        return visitedCount;
    }


    /**
     * Erases a node using its identifier.
     * 
     * @param coordX
     *            coordY Positional coordinates of the node.
     * @param coordY
     *            coordY Positional coordinates of the node.
     * @param baseX
     *            baseY Base coordinates for top-left corner of bounding box.
     * @param baseY
     *            baseY Base coordinates for top-left corner of bounding box.
     * @param boundaryWidth
     *            Span of the bounding region.
     * @param boundaryHeight
     *            Altitude of the bounding region.
     * @param isVertical
     *            Indicates if the split is vertical.
     * @param nodeId
     *            Identifier for the node to erase.
     * @return Refreshed tree after node removal.
     */
    @Override
    public AbstractTreeNode remove(
        int coordX,
        int coordY,
        int baseX,
        int baseY,
        int boundaryWidth,
        int boundaryHeight,
        boolean isVertical,
        int nodeId) {
        
        int median = computeMedian(
            baseX, baseY, boundaryWidth, boundaryHeight, isVertical);
        boolean isLeft = isLeftOf(coordX, coordY, median, isVertical);

        if (isLeft) {
            leftChild = removeLeftChild(
                coordX, coordY, baseX, baseY, boundaryWidth,
                boundaryHeight, isVertical, nodeId);
        } 
        else {
            rightChild = removeRightChild(
                coordX, coordY, baseX, baseY, boundaryWidth,
                boundaryHeight, isVertical, nodeId);
        }

        return this;
    }

    /**
     * Computes the median value based on the boundary and orientation.
     *
     * @param baseX          The x-coordinate of the starting boundary.
     * @param baseY          The y-coordinate of the starting boundary.
     * @param boundaryWidth  The width of the boundary.
     * @param boundaryHeight The height of the boundary.
     * @param isVertical     Flag to check if the division is vertical.
     * @return               The median value.
     */
    public int computeMedian(
        int baseX, 
        int baseY, 
        int boundaryWidth, 
        int boundaryHeight, 
        boolean isVertical) {
        return isVertical ? baseX + boundaryWidth
            / 2 : baseY + boundaryHeight / 2;
    }

    /**
     * Determines if the given point (coordX, coordY) is left of the median.
     *
     * @param coordX     The x-coordinate of the point.
     * @param coordY     The y-coordinate of the point.
     * @param median     The median value.
     * @param isVertical Flag to check if the division is vertical.
     * @return           
     *      True if the point is left 
     *      of the median, false otherwise.
     */
    public boolean isLeftOf(
        int coordX, 
        int coordY, 
        int median, 
        boolean isVertical) {
        return isVertical ? coordX < median : coordY < median;
    }

    /**
     * Removes a node from the left child.
     *
     * @param coordX         The x-coordinate of the node to be removed.
     * @param coordY         The y-coordinate of the node to be removed.
     * @param baseX          The x-coordinate of the starting boundary.
     * @param baseY          The y-coordinate of the starting boundary.
     * @param boundaryWidth  The width of the boundary.
     * @param boundaryHeight The height of the boundary.
     * @param isVertical     Flag to check if the division is vertical.
     * @param nodeId         The id of the node to be removed.
     * @return               Modified left child after removing the node.
     */
    public AbstractTreeNode removeLeftChild(
        int coordX,
        int coordY,
        int baseX,
        int baseY,
        int boundaryWidth,
        int boundaryHeight,
        boolean isVertical,
        int nodeId) {

        return leftChild.remove(coordX, coordY, baseX, baseY,
            isVertical ? boundaryWidth / 2 : boundaryWidth, 
            isVertical ? boundaryHeight : boundaryHeight / 2,
            !isVertical, nodeId);
    }

    /**
     * Removes a node from the right child.
     *
     * @param coordX         The x-coordinate of the node to be removed.
     * @param coordY         The y-coordinate of the node to be removed.
     * @param baseX          The x-coordinate of the starting boundary.
     * @param baseY          The y-coordinate of the starting boundary.
     * @param boundaryWidth  The width of the boundary.
     * @param boundaryHeight The height of the boundary.
     * @param isVertical     Flag to check if the division is vertical.
     * @param nodeId         The id of the node to be removed.
     * @return               Modified right child after removing the node.
     */
    public AbstractTreeNode removeRightChild(
        int coordX,
        int coordY,
        int baseX,
        int baseY,
        int boundaryWidth,
        int boundaryHeight,
        boolean isVertical,
        int nodeId) {

        return rightChild.remove(coordX, coordY, 
            isVertical ? computeMedian(
                baseX, baseY, boundaryWidth, 
                boundaryHeight, isVertical) : baseX,
            isVertical ? baseY : computeMedian(
                baseX, baseY, boundaryWidth, 
                boundaryHeight, isVertical),
            boundaryWidth, boundaryHeight, 
            !isVertical, nodeId);
    }
}
