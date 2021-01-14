/**
 * Author: Mathias Bak Bertelsen
 * Email:  bufas@cs.au.dk
 * Date:   03-08-2014
 * 
 * A very simple implementation of a generic FIFO queue.
 */

public class Queue<T> {
    
    private Object[] queue;       // The underlying array
    private int size;             // The maximal capacity
    private int head      = 0;    // Pointer to head of queue
    private int tail      = 0;    // Pointer to tail of queue
    private boolean empty = true; // Whether the queue is empty or not

    /**
     * Implements a generic FIFO queue with only the two basic
     * operations, enqueue and dequeue that inserts and retrieves
     * and element respectively.
     * @param size the number of elements the queue can maximally hold
     */
    public Queue(int size) {
        this.queue = new Object[size];
        this.size  = size;
    }

    /**
     * Inserts an element into the queue.
     * @param elem the element to insert into the queue
     * @throws Exception when the queue is full
     */
    public void enqueue(T elem) throws Exception {
        // Check if the queue is full and throw exception
        if (head == tail && !empty) {
            throw new Exception("Cannot enqueue " + elem);
        }

        // The queue has space left, enqueue the item
        queue[tail] = elem;
        tail        = (tail + 1) % size;
        empty       = false;
    }

    /**
     * Removes an element from the queue and returns it.
     * @throws Exception when the queue is empty
     */
    public T dequeue() throws Exception {
        // Check if queue is empty and throw exception
        if (empty) {
            throw new Exception("The queue is empty");
        }

        // The queue is not empty, return element
        T elem = (T) queue[head];
        head   = (head + 1) % size;
        empty  = (head == tail);
        return elem;
    }

    /**
     * A simple test driver for the queue.
     * It creates the queue, inserts and element, and then
     * fetches and prints the element.
     */
    public static void main(String[] args) throws Exception {
        // Create a queue of integers of size 10
        Queue<Integer> q = new Queue<Integer>(4);
        // Insert a number
        q.enqueue(42);
        // Get an element form the queue and print it
        System.out.println(q.dequeue());
    }

}
