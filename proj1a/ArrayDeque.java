public class ArrayDeque<T> {
    private T[] items;
    private int front;
    private int rear;
    private int actualSize;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        front = 0;
        rear = 0;
        actualSize = 8;
    }

    // Returns true if deque is empty, false otherwise.
    public boolean isEmpty() {
        return front == rear;
    }

    // Returns the number of items in the deque.
    public int size() {
        return (rear + actualSize - front) % actualSize;
    }

    // return if circular array is full
    private boolean isFull() {
        return (rear + 1) % actualSize == front;
    }

    // resize array
    private  void resize() {
        T[] newArray = (T[]) new Object[2 * actualSize];
        int index = 0;
        for (int i = front; i < (front + size()); i++) {
            newArray[index++] = items[i % actualSize];
        }
        actualSize = actualSize * 2;
        items = newArray;
        front = 0;
        rear = index;
    }

    // Adds an item of type T to the front of the deque.
    public void addFirst(T item) {
        if (isFull()) {
            resize();
        }
        front = (actualSize + front - 1) % actualSize;
        items[front] = item;
    }

    // Adds an item of type T to the back of the deque.
    public void addLast(T item) {
        if (isFull()) {
            resize();
        }
        items[rear] = item;
        rear = (rear + 1) % actualSize;
    }

    // shrink array
    private void shrink() {
        T[] newArray = (T[]) new Object[actualSize / 2];
        int index = 0;
        for (int i = front; i < front + size(); i++) {
            newArray[index++] = items[i % actualSize];
        }
        actualSize = actualSize / 2;
        front = 0;
        rear = index;
        items = newArray;
    }

    // Removes and returns the item at the front of the deque.
    // If no such item exists, returns null.
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T item = items[front];
        items[front] = null;
        front = (front + 1) % actualSize;
        if ((double) size() / actualSize < 0.25) {
            shrink();
        }
        return item;
    }

    // Removes and returns the item at the back of the deque.
    // If no such item exists, returns null.
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        rear = (actualSize + rear - 1) % actualSize;
        T item = items[rear];
        items[rear] = null;
        if ((double) size() / actualSize < 0.25) {
            shrink();
        }
        return item;
    }

    // Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
    // If no such item exists, returns null. Must not alter the deque!
    public T get(int index) {
        if (isEmpty() || index >= size() || index < 0) {
            return null;
        }
        return items[(front + index) % actualSize];
    }

    // Prints the items in the deque from first to last, separated by a space.
    public void printDeque() {
        for (int i = front; i < front + size(); i++) {
            System.out.print(items[i % actualSize] + " ");
        }
        System.out.println();
    }

////    some tests
//    public static void main(String[] args) {
//        ArrayDeque<Integer> deque = new ArrayDeque<>();
//        deque.addFirst(1);
//        deque.addLast(2);
//        deque.addLast(3);
//        deque.addLast(4);
//        deque.addFirst(0);
//        deque.addFirst(-1);
//        deque.addLast(5);
//        deque.addFirst(6);
////        while (deque.size() != 1) {
////            System.out.println(deque.removeLast());
////        }
////        System.out.println();
////        System.out.println(deque.actualSize);
////        System.out.println(deque.size());
//        System.out.println(deque.get(3));
//        System.out.println(deque.get(7));
//        System.out.println(deque.get(0));
//    }
}
