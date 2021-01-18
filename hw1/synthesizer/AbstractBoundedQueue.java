package synthesizer;

public abstract class AbstractBoundedQueue<T> implements BoundedQueue<T> {
    protected int fillCount;
    protected int capacity;

    // Return capacity
    public int capacity() {
        return capacity;
    }

    // Return fillCount
    public int fillCount() {
        return fillCount;
    }
}
