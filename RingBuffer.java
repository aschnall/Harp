// TODO: FILE HEADER

import java.util.Arrays;

public class RingBuffer {
    private double[] bufferArray; // items in the buffer
    private int first;            // index for the next dequeue or peek
    private int last;             // index for the next enqueue
    private int currentSize;      // number of items in the buffer
    private int capacity;

    // create an empty buffer, with given max capacity
    public RingBuffer(int capacity) {
        this.capacity = capacity;
        bufferArray = new double[capacity];
    }

    // return number of items currently in the buffer
    public int currentSize() {
        return currentSize;
    }

    // is the buffer empty (size equals zero)?
    public boolean isEmpty() {
        if (currentSize == 0) {
            return true;
        }
        return false;
    }

    // is the buffer full (size equals array capacity)?
    public boolean isFull() {
        if (currentSize == capacity) {
            return true;
        }
        return false;
    }

    // add item x to the end
    public void enqueue(double x) {
        if (isFull()) {
            throw new RuntimeException("ERROR: Attempting to enqueue " +
                                       "to a full buffer.");
        }
        bufferArray[last] = x;
        last = last + 1;
        if (last > capacity - 1) {
            last = 0;
        }
        currentSize = currentSize + 1;
    }

    // delete and return item from the front
    public double dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("ERROR: Attempting to dequeue " +
                                       "from an empty buffer.");
        }
        double firstElement = bufferArray[first];
        bufferArray[first] = 0;
        first = first + 1;
        if (first > capacity - 1) {
            first = 0;
        }
        currentSize = currentSize - 1;
        return firstElement;
    }

    // return (but do not delete) item from the front
    public double peek() {
        if (isEmpty()) {
            throw new RuntimeException("ERROR: Attempting to peek " +
                                       "at an empty buffer.");
        }
        return bufferArray[first];
    }

    // print the contents of the RingBuffer object for debugging
    private void printBufferContents() {
        // print out first, last, and currentSize
        System.out.println("first:        " + first);
        System.out.println("last:         " + last);
        System.out.println("currentSize:  " + currentSize);

        // print bufferArray's length and contents if it is not null
        // otherwise just print a message that it is null
        if (bufferArray != null) {
            System.out.println("array length: " + bufferArray.length);
            System.out.println("Buffer Contents:");
            for (int i = 0; i < bufferArray.length; i++) {
                System.out.println(bufferArray[i]);
            }
        } else {
            System.out.println("bufferArray is null");
        }
    }

    // a simple test of the constructor and methods in RingBuffer
    public static void main(String[] args) {
        // create a RingBuffer with bufferSize elements
        // where bufferSize is a command-line argument
        int bufferSize = Integer.parseInt(args[0]);
        RingBuffer buffer = new RingBuffer(bufferSize);

    }

}
