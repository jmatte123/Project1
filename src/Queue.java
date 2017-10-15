/**
 * @author Joe Matteson
 * @author Edgar Centeno
 *
 * This is a queue class that represents a queue.
 * @param <T> The type of Queue.
 *
 * First in First out.
 */
public class Queue<T>{

    // --- Fields --- //
    private Node first;
    private Node last;
    private int amount = 0;
    private boolean initialized = false;

    /**
     * Constructor which initializes a new queue.
     */
    public Queue() {
        initialized = true;
    }

    /**
     * Add data to the linked list in the form of a queue.
     *
     * @param data the data that is being added to the queue.
     * @return true if adding was successful.
     */
    public boolean Enqueue(T data) {
        assert initialized;
        Node newNode = new Node(data);
        if (isEmpty()) {
            first = newNode;
            last = newNode;
        } else {
            last.next = newNode;
            last = newNode;
        }
        amount++;
        return true;
    }

    /**
     * removes the first piece of data in the queue.
     *
     * @return the data that was removed.
     */
    public T Dequeue() {
        assert !isEmpty();
        T data = first.data;
        Node nextNode = first.next;
        first.next = null;
        first = nextNode;
        amount--;
        return data;
    }

    /**
     * peek at the first nodes data in the queue.
     *
     * @return the first piece of data in the queue.
     */
    public T peek() {
        return first.data;
    }

    /**
     * Gets the size of the list.
     *
     * @return the amount.
     */
    public int size() {
        return amount;
    }

    /**
     * Checks to see if the queue is empty.
     *
     * @return true if it is, otherwise false.
     */
    public boolean isEmpty() {
        return first == null && initialized;
    }

    /**
     * represents a node of data for a linked list.
     */
    class Node {
        // --- Fields --- //
        Node next;
        T data;

        /**
         * constructor for initializing the a Node.
         *
         * @param data the data stored in the node.
         */
        Node (T data) {
            this.data = data;
            this.next = null;
        }
    }
}
