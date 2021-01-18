package synthesizer;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(10);
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < arb.capacity(); i++) {
            arb.enqueue(i);
            list.add(i);
        }

        for (int i = 0; i < 3; i++) {
            assertEquals(list.remove(0), arb.dequeue());
        }

        for (int i = 0; i < 3; i++) {
            arb.enqueue(i + 10);
            list.add(i + 10);
        }

        while (!arb.isEmpty()) {
            assertEquals(list.remove(0), arb.dequeue());
        }
    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
