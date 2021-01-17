public class LinkedListDeque<T> implements Deque<T> {
    private class Node {
        T item;
        Node prev;
        Node next;

        Node(T item, Node prev, Node next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }

        Node(T item) {
            this(item, null, null);
        }

        Node() {
            this(null, null, null);
        }
    }

    private Node sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new Node();
        size = 0;
    }

    // Returns true if deque is empty, false otherwise.
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    // Returns the number of items in the deque.
    @Override
    public int size() {
        return size;
    }

    // Adds an item of type T to the front of the deque.
    @Override
    public void addFirst(T item) {
        if (isEmpty()) {
            sentinel.next = new Node(item, sentinel, sentinel);
            sentinel.prev = sentinel.next;
        } else {
            Node node = new Node(item, sentinel, sentinel.next);
            sentinel.next.prev = node;
            sentinel.next = node;
        }
        size++;
    }

    // Adds an item of type T to the back of the deque.
    @Override
    public void addLast(T item) {
        if (isEmpty()) {
            sentinel.next = new Node(item, sentinel, sentinel);
            sentinel.prev = sentinel.next;
        } else {
            Node node = new Node(item, sentinel.prev, sentinel);
            sentinel.prev.next = node;
            sentinel.prev = node;
        }
        size++;
    }

    // Removes and returns the item at the front of the deque.
    // If no such item exists, returns null.
    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        Node node = sentinel.next;
        if (size == 1) {
            sentinel.next = null;
            sentinel.prev = null;
        } else {
            sentinel.next = node.next;
            node.next.prev = sentinel;
        }
        node.next = null;
        node.prev = null;
        size--;
        return node.item;
    }


    // Removes and returns the item at the back of the deque.
    // If no such item exists, returns null.
    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        Node node = sentinel.prev;
        if (size == 1) {
            sentinel.prev = null;
            sentinel.next = null;
        } else {
            sentinel.prev = node.prev;
            node.prev.next = sentinel;
        }
        node.next = null;
        node.prev = null;
        size--;
        return node.item;
    }

    // Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
    // If no such item exists, returns null. Must not alter the deque!
    @Override
    public T get(int index) {
        if (size == 0 || index >= size || index < 0) {
            return null;
        }
        Node node = sentinel;
        for (int i = index; i >= 0; i--) {
            node = node.next;
        }
        return node.item;
    }

    // use recursion to get item at the given index
    public T getRecursive(int index) {
        if (size == 0 || index >= size || index < 0) {
            return null;
        }
        return getRecursiveHelper(index, sentinel.next);
    }

    private T getRecursiveHelper(int index, Node node) {
        if (index == 0) {
            return node.item;
        }
        return getRecursiveHelper(index - 1, node.next);
    }

    // // Prints the items in the deque from first to last, separated by a space.
    @Override
    public void printDeque() {
        if (size == 0) {
            return;
        }
        Node node = sentinel.next;
        do {
            System.out.print(node.item + " ");
            node = node.next;
        } while (node != sentinel);
        System.out.println();
    }
}
