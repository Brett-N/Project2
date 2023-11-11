/**
 * LinkedList implementation
 * 
 * 
 * @author Brett Noneman
 * @author Piyush Chauhan
 * @version 10/10/23
 */
public class CustomList {
    private Node start;
    private Node tail;

    /**
     * Constructs an empty CustomList.
     */
    public CustomList() {
        start = null;
        tail = null;
    }
    
    /**
     * Returns the number of Seminars in this list.
     *
     * @return the number of Seminars in this list.
     */
    public int size() {
        int count = 0;
        Node iterator = start;
        while (iterator != null) {
            iterator = iterator.next;
            count++;
        }
        return count;
    }
    
    /**
     * Retrieves the first Seminar in this list.
     *
     * @return the first Seminar in this list.
     * @throws IllegalStateException if the list is empty.
     */
    public Seminar fetchPrimary() {
        if (isVacant()) { //Untested
            throw new IllegalStateException("List is empty.");
        }
        return start.data;
    }

    /**
     * Checks if the list is empty.
     *
     * @return true if the list is empty, false otherwise.
     */
    public boolean isVacant() {
        return start == null;
    }

    /**
     * Appends a Seminar with a unique identifier to the end of this list.
     *
     * @param item       The Seminar to be added to the list.
     * @param identifier The unique identifier for the Seminar.
     */
    public void append(Seminar item, int identifier) {
        Node newNode = new Node(item, identifier);
        if (start == null) {
            start = newNode;
            tail = newNode;
            return;
        }
        tail.next = newNode;
        tail = newNode;
    }

    /**
     * Removes the Seminar with the 
     * specified identifier from this list, if present.
     *
     * @param identifier The identifier of the Seminar to be removed.
     * @return true if the Seminar was found and removed, false otherwise.
     */
    public boolean removeByIdentifier(int identifier) {
        if (start == null) {
            return false;
        }
        if (start.id == identifier) { //Untested
            start = start.next;
            return true;
        }
        Node iterator = start;
        Node prior = null;
        while (iterator != null && iterator.id != identifier) {
            prior = iterator;
            iterator = iterator.next;
        }
        if (iterator != null) {
            prior.next = iterator.next;
            return true;
        }
        return false;
    }

    /**
     * Returns a string representation of all Seminar identifiers in this list.
     *
     * @return a whitespace-separated string of identifiers.
     */
    public String identifierString() {
        StringBuilder result = new StringBuilder();
        Node iterator = start;
        while (iterator != null) {
            result.append(iterator.id).append(" ");
            iterator = iterator.next;
        }
        return result.toString().trim();
    }

    /**
     * Converts the Seminars in this list to an array.
     *
     * @return an array containing all of 
     * the Seminars in this list in proper sequence.
     */
    public Seminar[] convertToArray() {
        Seminar[] arr = new Seminar[size()];
        Node iterator = start;
        int idx = 0;
        while (iterator != null) {
            arr[idx] = iterator.data;
            iterator = iterator.next;
            idx++;
        }
        return arr;
    }

    /**
     * Represents a node in the CustomList. 
     * Each node has a Seminar, a unique identifier, 
     * and a reference to the next node.
     * 
     * @author Brett Noneman
     * @version 10/10/23
     */
    private static class Node {
        private Seminar data;
        private int id;
        private Node next;

        public Node(Seminar content, int id) {
            this.data = content;
            this.id = id;
        }
    }
}