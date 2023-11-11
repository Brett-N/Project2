/**
 * Generic representation of a queue using a singly linked list.
 * 
 * @author Brett Noneman
 * @author Piyush Chauhan
 * @version 10/10/23
 * @param <E>
 *            the type of elements stored in this queue
 */
public class ElementQueue<E> {

    /**
     * Front of the queue.
     */
    private ListItem<E> head;

    /**
     * Rear of the queue.
     */
    private ListItem<E> tail;

    /**
     * Current size of the queue.
     */
    private int count;

    /**
     * Keeps track of nodes accessed.
     */
    private int traversalCount;

    /**
     * Represents an individual item in the queue's linked list.
     *
     * @param <E>
     *            type of element in the item
     */
    private static class ListItem<E> {
        private E element;
        private ListItem<E> nextItem;

        ListItem(E elem) {
            this.element = elem;
        }
    }

    /**
     * Initializes an empty queue.
     */
    public ElementQueue() {
        this.head = null;
        this.tail = null;
        this.count = 0;
    }


    /**
     * Gets the count of accessed nodes.
     *
     * @return the count of nodes accessed
     */
    public int accessedNodesCount() {
        return traversalCount;
    }


    /**
     * Increases the node access count.
     */
    public void markNodeAccessed() {
        this.traversalCount++;
    }


    /**
     * Returns the current queue size.
     *
     * @return the queue size
     */
    public int queueSize() {
        return count;
    }


    /**
     * Determines if the queue is empty.
     *
     * @return true if empty, false otherwise
     */
    public boolean hasNoElements() {
        return count == 0;
    }


    /**
     * Adds an element to the queue's end.
     *
     * @param data
     *            the element to be added
     */
    public void addElement(E data) {
        ListItem<E> newItem = new ListItem<>(data);
        if (tail != null) {
            tail.nextItem = newItem;
        }
        tail = newItem;
        if (head == null) {
            head = newItem;
        }
        count++;
    }


    /**
     * Removes and provides the front element from the queue.
     *
     * @return the front element
     * @throws IllegalStateException
     *             if the queue is empty
     */
    public E provideElement() {
        if (hasNoElements()) {
            throw new IllegalStateException("Empty Queue");
        }
        E data = head.element;
        head = head.nextItem;
        if (head == null) {
            tail = null;
        }
        count--;
        return data;
    }


    /**
     * Gives the node in the head of the queue
     * 
     * @return ListItem for the head
     */
    public ListItem<E> getHead() {
        return head;
    }


    /**
     * Gives the node in the tail of the queue
     * 
     * @return ListItem for the tail
     */
    public ListItem<E> getTail() {
        return tail;
    }
}
