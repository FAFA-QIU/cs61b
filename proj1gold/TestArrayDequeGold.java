import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {
    @Test
    public void studentArrayTest() {
        StudentArrayDeque<Integer> deque = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ad = new ArrayDequeSolution<>();
        String message = "";
        while (true) {
            int num = StdRandom.uniform(4);
            int value = StdRandom.uniform(10);
            if (num == 0) {
                deque.addFirst(value);
                ad.addFirst(value);
                message += String.format("deque.addFirst(%d)\n", value);
                message += "deque.get(0)\n";
                assertEquals(message, ad.get(0), deque.get(0));
            } else if (num == 1) {
                deque.addLast(value);
                message += String.format("deque.addLast(%d)\n", value);
                ad.addLast(value);
                message += "deque.get(deque.size() - 1)\n";
                message += "deque.size()\n";
                assertEquals(message, ad.get(ad.size() - 1), deque.get(deque.size() - 1));
            } else if (num == 2) {
                if (!ad.isEmpty() && !deque.isEmpty()) {
                    message += "deque.removeFirst()\n";
                    assertEquals(message, ad.removeFirst(), deque.removeFirst());
                }
            } else {
                if (!ad.isEmpty() && !deque.isEmpty()) {
                    message += "deque.removeLast()\n";
                    assertEquals(message, ad.removeLast(), deque.removeLast());
                }
            }
        }
    }
}
