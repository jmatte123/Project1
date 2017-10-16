/**
 * @author Joe Matteson
 * @author Edgar Centeno
 *
 * This is a Stack class that represents a Stack.
 * @param <T> The type of Queue.
 *
 * First in Last out.
 */
public class Stack<T>{

    // --- Fields --- //
    private Node first;
    private Node last;
    private int amount = 0;
    private boolean initialized = false;

    /**
     * Constructor which initializes a new Stack.
     */
    public Stack() {
        this.initialized = true;
    }

    /**
     * Add data to the linked list in the form of a Stack.
     *
     * @param data the data that is being added to the Stack.
     * @return true if adding was successful.
     */
    public boolean push(T data) {
        assert initialized;
        Node newNode = new Node(data);
        if (isEmpty()) {
            first = newNode;
            last = newNode;
        } else {
            Node lastCurr = last;
            last.next = newNode;
            last = newNode;
            last.previous = lastCurr;
        }
        amount++;
        return true;
    }

    /**
     * removes the first piece of data in the Stack.
     *
     * @return the data that was removed.
     */
    public T pop() {
        assert !isEmpty();
        T data = last.data;
        Node prevNode = last.previous;
        prevNode.next = null;
        last.previous = null;
        last = prevNode;
        amount--;
        return data;
    }

    /**
     * peek at the first nodes data in the Stack.
     *
     * @return the first piece of data in the Stack.
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
     * Checks to see if the stack is empty.
     *
     * @return true if it is, otherwise false.
     */
    public boolean isEmpty() {
        return first == null && initialized;
    }

    /**
     * Converts the Stack into an array.
     *
     * @return an array of the stack.
     */
    public T[] toArray() {
        T[] array = (T[]) new Object[amount];
        return getArrayOfData(array, 0, first);
    }

    /**
     * Recursively puts all the data from the stack into an array.
     *
     * @param arr the array holding all the data.
     * @param index the counter to hit every position in the array.
     * @param curr the current node in which the data is being extracted.
     * @return the final array with all the data.
     */
    private T[] getArrayOfData(T[] arr, int index, Node curr) {
        if (curr.data != null)
            arr[index] = curr.data;
        if (curr == last)
            return arr;
        return getArrayOfData(arr, index + 1, curr.next);
    }

    /**
     * clears data from Stack.
     */
    public void clear() {
        first = null;
        last = null;
        amount = 0;
    }

    /**
     * represents a node of data for a linked list.
     */
    class Node {
        // --- Fields --- //
        Node next;
        Node previous;
        T data;

        /**
         * constructor for initializing the a Node.
         *
         * @param data the data stored in the node.
         */
        Node (T data) {
            this.data = data;
            this.next = null;
            this.previous = null;
        }
    }
}
