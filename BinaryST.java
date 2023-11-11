// -------------------------------------------------------------------------
/**
 * Implements an unbalanced binary search tree. Note that all "matching" is
 * based on the compareTo() method.
 *
 * @author Mark Allen Weiss (with modifications by John Lewis)
 * @author Piyush Chauhan
 * @author Brett Noneman
 * @version 2023.9.19
 */

/**
 * Implements an unbalanced binary search tree. Note that all "matching" is
 * based on the compareTo() method.
 *
 * @author Mark Allen Weiss (with modifications by John Lewis)
 * @author Piyush Chauhan: cpiyush854
 * @author Brett Non:
 * @version 2023.9.19
 *
 * @param <K>
 *            key for the KVPair
 * @param <V>
 *            value for the KVPair
 */
public class BinaryST<K extends Comparable<K>, V> {

    private TreeNode<K, V> root;
    private int nodecount;

    /**
     * Initializes an empty binary search tree.
     */
    public BinaryST() {
        root = null;
        nodecount = 0;
    }


    /**
     * Returns the size of the bst
     * 
     * @return int representing the bst size
     */
    public int size() {
        return nodecount;
    }


    /**
     * Adds an element to the tree.
     * 
     * @param key
     *            The key to be inserted.
     * @param value
     *            The associated value.
     */
    public void add(K key, V value) {
        root = addRecursive(root, key, value);
        nodecount++;
    }


    /**
     * Recursive method for adding an element.
     * 
     * @param currentNode
     *            The current node.
     * @param key
     *            The key to be inserted.
     * @param value
     *            The associated value.
     * @return Modified node.
     */
    private TreeNode<K, V> addRecursive(
        TreeNode<K, V> currentNode,
        K key,
        V value) {
        if (currentNode == null) {
            currentNode = new TreeNode<>(key, value);
        }
        else if (currentNode.getKey().compareTo(key) >= 0) {
            currentNode.setLeft(addRecursive(currentNode.getLeft(), key,
                value));
        }
        else {
            currentNode.setRight(addRecursive(currentNode.getRight(), key,
                value));
        }
        return currentNode;
    }


    /**
     * Make the tree logically empty.
     */
    public void makeEmpty() {
        root = null;
        nodecount = 0;
    }


    /**
     * Test if the tree is logically empty.
     *
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty() {
        return root == null;
    }

// public TreeNode<K, V> remove(K key) {
// return remove(key, root);
// }


    /**
     * removes the ndoe that matched the key
     * 
     * @param key
     *            the identifier for the node to be removed
     * @return the TreeNode object that was removed
     */
    public TreeNode<K, V> remove2(K key) {
        TreeNode<K, V> temp = search(key, root); // First find it
        if (temp != null) {
            root = removehelp(root, key); // Now remove it
            nodecount--;
        }
        return temp;
    }


    /**
     * the recursive method that carries out the remove method
     * 
     * @param root2
     *            the node that roots the tree
     * @param key
     *            the identifier for the node to be removed
     * @return the TreeNode object that was removed
     */
    private TreeNode<K, V> removehelp(TreeNode<K, V> root2, K key) {
        if (root2 == null)
            return null;
        if (root2.getKey().compareTo(key) > 0) {
            root2.setLeft(removehelp(root2.getLeft(), key));
        }
        else if (root2.getKey().compareTo(key) < 0) {
            root2.setRight(removehelp(root2.getRight(), key));
        }
        else {
            if (root2.getLeft() == null)
                return root2.getRight();
            else if (root2.getRight() == null)
                return root2.getLeft();
            else {
                TreeNode<K, V> temp = findMaxNode(root2.getLeft());
                root2.setKey(temp.getKey());
                root2.setValue(temp.getValue());
                root2.setLeft(deletemax(root2.getLeft()));
            }
        }
        return root2;
    }


    /**
     * deletes the largest node in the tree
     * 
     * @param rt
     *            the node that roots the tree
     * @return the TreeNode object that was deleted
     */
    private TreeNode<K, V> deletemax(TreeNode<K, V> rt) {
        if (rt.getRight() == null)
            return rt.getLeft();
        rt.setRight(deletemax(rt.getRight()));
        return rt;
    }

// private TreeNode<K, V> remove(K key, TreeNode<K, V> node){
// TreeNode<K, V> result = node;
//
// if(node == null) {
// throw new ItemNotFoundException(key.toString());
// }
//
// if(key.compareTo(node.getKey()) < 0) {
// node.setLeft(remove(key, node.getLeft()));
// }else if(key.compareTo(node.getKey())>0) {
// node.setRight(remove(key, node.getRight()));
// }else {
//
// if(node.getLeft() != null && node.getRight() != null) {
// K successorData = findMin(node.getRight()).getKey();
// result = remove(successorData, result);
// result.setKey(successorData);
// }else if(node.getLeft()!= null) {
// result = node.getLeft();
// }else {
// result = node.getRight();
// }
// }
// return result;
// }
// /**
// * Find the smallest item in the tree.
// *
// * @return The smallest item, or null if the tree is empty.
// */
// public K findMin()
// {
// return findMin(root).getKey();
// }
//
// /**
// * Internal method to find the smallest item in a subtree
// * @param node the node that roots the tree
// * @return node containing the smallest item
// */
// private TreeNode<K, V> findMin(TreeNode<K, V> node){
// if(node == null) {
// return node;
// }else if(node.getLeft() == null) {
// return node;
// }else {
// return findMin(node.getLeft());
// }
// }


    /**
     * Search for an item in the tree
     * 
     * @param key
     *            the key to search for
     * @return the matching item or null if not found
     */
    public TreeNode<K, V> search(K key) {
        return search(key, root);
    }


    /**
     * Internal method to find an item in a subtree
     * 
     * @param key
     *            the key to search for
     * @param node
     *            the node that roots the tree
     * @return node containing the matched key
     */
    private TreeNode<K, V> search(K key, TreeNode<K, V> node) {
        if (node == null) {
            return null;
        }
        else if (key.compareTo(node.getKey()) < 0) {
            return search(key, node.getLeft());
        }
        else if (key.compareTo(node.getKey()) == 0) {
            return node;
        }
        else {
            return search(key, node.getRight());
        }
    }


    /**
     * Search for item in the tree with matching keyword
     * 
     * @param key
     *            the keyword to search for
     * @return string with all the nodes that contain the keyword
     */
    public String kwSearch(K key) {
        StringBuilder str = new StringBuilder();
        String output = kwSearch(key, root, str);
        return output.substring(0, output.length());

    }


    /**
     * Internal method to search based on key words
     * 
     * @param key
     *            the keyword to search for
     * @param currentNode
     *            the node that roots the tree
     * @param str
     *            StringBuilder that is updated with the object
     * @return String with all objects containing the keyword
     */
    private String kwSearch(
        K key,
        TreeNode<K, V> currentNode,
        StringBuilder str) {
        if (currentNode == null)
            return str.toString();

        kwSearch(key, currentNode.getLeft(), str);

        if (currentNode.getKey().compareTo(key) == 0) {
            str.append(currentNode.getValue()).append("\n");
        }

        kwSearch(key, currentNode.getRight(), str);

        return str.toString();
    }


    /**
     * Finds the node with the maximum key.
     * 
     * @param currentNode
     *            The starting node.
     * @return Node with the maximum key.
     */
    private TreeNode<K, V> findMaxNode(TreeNode<K, V> currentNode) {
        while (currentNode.getRight() == null) {
            return currentNode;
        }
        return findMaxNode(currentNode.getRight());
    }


    /**
     * Searches and prints keys within the given bounds.
     * 
     * @param lowerLimit
     *            Lower key limit.
     * @param upperLimit
     *            Upper key limit.
     * @return String with all objects in the range
     */
    public String findInRange(K lowerLimit, K upperLimit) {
        StringBuilder str = new StringBuilder();
        String output = findInRangeRecursive(root, lowerLimit, upperLimit, str);
        if (output.length() == 0) {
            return output;
        }
        else {
            return output.substring(0, output.length() - 1);
        }
    }


    /**
     * Recursively searches and prints keys within the given bounds.
     * 
     * @param currentNode
     *            The current node.
     * @param lowerLimit
     *            Lower key limit.
     * @param upperLimit
     *            Upper key limit.
     */
    private String findInRangeRecursive(
        TreeNode<K, V> currentNode,
        K lowerLimit,
        K upperLimit,
        StringBuilder str) {

        if (currentNode == null)
            return str.toString();

        findInRangeRecursive(currentNode.getLeft(), lowerLimit, upperLimit,
            str);

        int cmpLow = currentNode.getKey().compareTo(lowerLimit);
        int cmpHigh = currentNode.getKey().compareTo(upperLimit);

        if (cmpLow >= 0 && cmpHigh <= 0) {
            str.append(currentNode.getValue()).append("\n");
        }

        findInRangeRecursive(currentNode.getRight(), lowerLimit, upperLimit,
            str);

        return str.toString();
    }


    /**
     * Counts the nodes visited in a search
     * 
     * @param lowerLimit
     *            Lower key limit
     * @param upperLimit
     *            Upper key limit
     * @return int representing the nodes visited in the search
     */
    public int searchCount(K lowerLimit, K upperLimit) {
        return searchCount(root, lowerLimit, upperLimit);
    }


    /**
     * Internal method to count the nodes visited during search
     * 
     * @param currentNode
     *            the node that roots the tree
     * @param lowerLimit
     *            Lower key limit
     * @param upperLimit
     *            Upper key limit
     * @return int representing the number of nodes visited in the search
     */
    private int searchCount(
        TreeNode<K, V> currentNode,
        K lowerLimit,
        K upperLimit) {

        if (currentNode == null)
            return 1;

        int totalCount = 1;

        int cmpLow = currentNode.getKey().compareTo(lowerLimit);
        int cmpHigh = currentNode.getKey().compareTo(upperLimit);

        if (cmpLow < 0) {
            totalCount += searchCount(currentNode.getRight(), lowerLimit,
                upperLimit);
        }
        else if (cmpHigh > 0) {
            totalCount += searchCount(currentNode.getLeft(), lowerLimit,
                upperLimit);
        }
        else {
            totalCount += searchCount(currentNode.getLeft(), lowerLimit,
                upperLimit);

            if (cmpHigh != 0) {
                totalCount += searchCount(currentNode.getRight(), lowerLimit,
                    upperLimit);
            }

        }

        return totalCount;
    }


    /**
     * Forms a representation of the tree structure.
     * 
     * @param currentNode
     *            The current node.
     * @param builder
     *            Holds the string representation.
     * @param depth
     *            Current tree depth.
     * @param nodeCount
     *            Keeps track of the node count.
     */
    private void treeStructure(
        TreeNode<K, V> currentNode,
        StringBuilder builder,
        int depth,
        int[] nodeCount) {
        if (currentNode == null) {
            for (int i = 0; i < depth; i++) {
                builder.append("  ");
            }
            builder.append("null\n");
            return;
        }

        treeStructure(currentNode.getRight(), builder, depth + 1, nodeCount);

        for (int i = 0; i < depth; i++) {
            builder.append("  ");
        }

        builder.append(currentNode.getKey()).append("\n");

        nodeCount[0]++;

        treeStructure(currentNode.getLeft(), builder, depth + 1, nodeCount);
    }


    /**
     * Returns the string representation of the tree.
     * 
     * @return String representation of the tree.
     */
    @Override
    public String toString() {
        if (root == null)
            return "This tree is empty";

        StringBuilder output = new StringBuilder();
        int[] totalNodes = new int[1];

        treeStructure(root, output, 0, totalNodes);

        output.append("Number of records: ").append(totalNodes[0]);

        return output.toString();
    }
}
