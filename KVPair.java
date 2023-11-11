/**
 * This class implements a Key Value pair class
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
public class KVPair<K extends Comparable<? super K>, V>
    implements Comparable<KVPair<K, V>> {
    private K key;
    private V value;

    /**
     * Constructor for KVPair class
     * 
     * @param key
     *            the key for the KVPair
     * @param value
     *            the value for the KVPair
     */
    public KVPair(K key, V value) {
        this.key = key;
        this.value = value;
    }


    /**
     * Gets the key for the KVPair
     * 
     * @return K key
     */
    public K getKey() {
        return key;
    }


    /**
     * Gets the value for the KVPair
     * 
     * @return V value
     */
    public V getValue() {
        return value;
    }


    /**
     * Sets the key for the KVPair
     * 
     * @param key
     *            the val for key to be set as
     */
    public void setKey(K key) {
        this.key = key;
    }


    /**
     * Sets the value for the KVPair
     * 
     * @param value
     *            the val for value to be set as
     */
    public void setValue(V value) {
        this.value = value;
    }


    /**
     * Prints out formatted output for KVPair
     * 
     * @return String with formatted KVPair output
     */
    @Override
    public String toString() {
        return "(" + key.toString() + ", " + value.toString() + ")";
    }


    /**
     * Compares 2 different object keys together
     * 
     * @param o
     *            the other KVPair to compare to
     * @return 0 if equal, -1 if less, and 1 if more than
     */
    @Override
    public int compareTo(KVPair<K, V> o) {
        return this.key.compareTo(o.key);
    }
}
