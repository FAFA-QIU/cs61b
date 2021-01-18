package synthesizer;

public abstract class AbstractBoundedQueue<T> implements BoundedQueue<T> {
    protected int fillCount;
    protected int capacity;

    // Return fillCount
    public int capacity() {
        return capacity;
    }

    // Return capacity
    public int fillCount() {
        return fillCount;
    }
}
