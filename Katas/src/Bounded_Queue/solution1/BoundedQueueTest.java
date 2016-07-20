package Bounded_Queue.solution1;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BoundedQueueTest {
    private final int size = 2;
    private BoundedQueue boundedQueue;

    @Before
    public void setUp() throws Exception {
        boundedQueue = new BoundedQueue<Integer>(size);
    }

    @Test
    public void getSize() {
        assertEquals(2, boundedQueue.getSize());
    }

    @Test
    public void Enqueue() {
        int value = 1;
        boundedQueue.enqueue(value);
        assertEquals(value, boundedQueue.values().get(0));
    }

    @Test
    public void Enqueue_TwoTimes() {
        boundedQueue.enqueue(1);
        boundedQueue.enqueue(2);
        List expected = Arrays.asList(1, 2);
        assertEquals(expected.toString(), boundedQueue.values().toString());
    }

    @Test
    public void Enqueue_Block() {
        assertEquals(true, false);
    }

    @Test
    public void Dequeue() {
        int value = 1;
        boundedQueue.enqueue(value);
        assertEquals(value, boundedQueue.dequeue());
        assertEquals(0, boundedQueue.values().size());
    }

    @Test
    public void Dequeue_Block() {
        assertEquals(true, false);
    }

    @Test
    public void Count() {
        boundedQueue.enqueue(1);
        boundedQueue.enqueue(2);
        boundedQueue.enqueue(3);
        assertEquals(3, boundedQueue.count());
    }


}