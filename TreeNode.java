/**
 * Represents a node in a tree structure, encapsulating a key-value pair.
 *
 * @author Piyush Chauhan
 * @author Brett Noneman
 * @version 2023.9.19
 * 
 * @param <K>
 *            Key type, must be comparable
 * @param <V>
 *            Value type
 */
public class TreeNode<K extends Comparable<K>, V> {

    private K key;
    private V value;
    private TreeNode<K, V> left;
    private TreeNode<K, V> right;

    /**
     * Constructs a new TreeNode with the given key and value.
     *
     * @param key
     *            The node's key
     * @param value
     *            The node's associated value
     */
    public TreeNode(K key, V value) {
        this.key = key;
        this.value = value;
    }


    /**
     * Retrieves the key associated with this node.
     *
     * @return The key
     */
    public K getKey() {
        return this.key;
    }


    /**
     * Updates the key of this node.
     *
     * @param key
     *            The new key
     */
    public void setKey(K key) {
        this.key = key;
    }


    /**
     * Retrieves the value associated with this node.
     *
     * @return The value
     */
    public V getValue() {
        return this.value;
    }


    /**
     * Updates the value of this node.
     *
     * @param value
     *            The new value
     */
    public void setValue(V value) {
        this.value = value;
    }


    /**
     * Retrieves the left child node.
     *
     * @return The left child, or null if absent
     */
    public TreeNode<K, V> getLeft() {
        return this.left;
    }


    /**
     * Assigns a node as the left child.
     *
     * @param left
     *            The node to be set as left child
     */
    public void setLeft(TreeNode<K, V> left) {
        this.left = left;
    }


    /**
     * Retrieves the right child node.
     *
     * @return The right child, or null if absent
     */
    public TreeNode<K, V> getRight() {
        return this.right;
    }


    /**
     * Assigns a node as the right child.
     *
     * @param right
     *            The node to be set as right child
     */
    public void setRight(TreeNode<K, V> right) {
        this.right = right;
    }


    /**
     * Gives the KVPair associated with the TreeNode
     * 
     * @return KVPair for the TreeNode
     */
    public KVPair<K, V> getPair() {
        KVPair<K, V> pair = new KVPair<>(key, value);
        return pair;
    }
}