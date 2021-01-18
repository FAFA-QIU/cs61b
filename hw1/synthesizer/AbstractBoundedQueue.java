package synthesizer;

public abstract class AbstractBoundedQueue<T> implements BoundedQueue<T> {
    protected int fillCount;
    protected int capacity;

    // Return capacity
    public abstract int capacity();

    // Return fillCount
    public abstract int fillCount();
}
