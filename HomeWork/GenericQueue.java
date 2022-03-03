package HomeWork;

public class GenericQueue<T> {
    protected T[] data;
    protected int head = 0;
    protected int tail;
    protected int numberOfElements = 0;

    public GenericQueue() {
        data = (T[]) new Object[10];
        tail = data.length - 1;
    }
    public GenericQueue(int givenMaxSize) {
        data = (T[]) new Object[givenMaxSize];
        tail = givenMaxSize - 1;
    }
    public boolean isEmpty() {
        return (numberOfElements == 0);
    }

    public boolean isFull() {
        return (numberOfElements == data.length);
    }

    public void enqueue(T element) {
        if (!isFull()){
            tail = (tail + 1) % data.length;

            data[tail] = element;
            numberOfElements++;
        } else {
            throw new IndexOutOfBoundsException("Queue is full.");
        }
    }

    public T dequeue() {
        if (!isEmpty()) {
            T dequeuedValue = data[head];
            data[head] = null;
            head = (head + 1) % data.length;
            numberOfElements--;
            return dequeuedValue;
        } else {
            throw new IndexOutOfBoundsException("Queue is empty.");
        }
    }
    public int size() {
        return numberOfElements;
    }


}