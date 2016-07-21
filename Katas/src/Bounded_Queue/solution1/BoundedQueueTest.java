package Bounded_Queue.solution1;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BoundedQueueTest {
    private BoundedQueue boundedQueue;
    private WritingThread<Integer> writhing;
    private ReadingThread reading;

    @Before
    public void setUp() throws Exception {
        int size = 2;
        boundedQueue = new BoundedQueue<Integer>(size);
        writhing = new WritingThread<>(1, 2, 3, 4, 5);
        reading = new ReadingThread();
    }

    @Test
    public void integration() {
        writhing.run();
        assertEquals(((List) Arrays.asList(1)).toString(), boundedQueue.values().toString());
        reading.run();
        writhing.run();
        assertEquals(((List) Arrays.asList(2)).toString(), boundedQueue.values().toString());
        writhing.run();
        assertEquals(((List) Arrays.asList(2, 3)).toString(), boundedQueue.values().toString());
        writhing.run();
        reading.run();
        assertEquals(((List) Arrays.asList(3, 4)).toString(), boundedQueue.values().toString());
        reading.run();
        assertEquals(((List) Arrays.asList(4)).toString(), boundedQueue.values().toString());
        reading.run();
        reading.run();
        writhing.run();
        assertEquals(((List) Arrays.asList(5)).toString(), boundedQueue.values().toString());
        reading.run();//unnecessary
        assertEquals(5, reading.lastOutput);
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
    public void Dequeue() {
        int value = 1;
        boundedQueue.enqueue(value);
        assertEquals(value, boundedQueue.dequeue());
        assertEquals(0, boundedQueue.values().size());
    }

    @Test
    public void Dequeue_givenTwoElements() {
        boundedQueue.enqueue(1);
        boundedQueue.enqueue(2);
        assertEquals(1, boundedQueue.dequeue());
        assertEquals(1, boundedQueue.values().size());
    }

    @Test
    public void Count() {
        boundedQueue.enqueue(1);
        boundedQueue.enqueue(2);
        assertEquals(2, boundedQueue.count());
    }

    @Test
    public void Enqueue_BlockToMuch() {
        boundedQueue.enqueue(1);
        boundedQueue.enqueue(2);
        boundedQueue.enqueue(3);
        List expected = Arrays.asList(1, 2);
        assertEquals(expected.toString(), boundedQueue.values().toString());
    }

    @Test
    public void Enqueue_ReleaseBlock() {
        boundedQueue.enqueue(1);
        boundedQueue.enqueue(2);
        boundedQueue.enqueue(3);
        boundedQueue.dequeue();
        List expected = Arrays.asList(2, 3);
        assertEquals(expected.toString(), boundedQueue.values().toString());
    }

    class WritingThread<T> extends Thread {
        final ArrayList<T> input;

        public WritingThread(T... input) {
            this.input = new ArrayList(Arrays.asList(input));
        }

        @Override
        public void run() {
            T value = input.get(0);
            input.remove(0);
            boundedQueue.enqueue(value);
        }
    }

    class ReadingThread extends Thread {
        final ArrayList output = new ArrayList();
        Object lastOutput;

        @Override
        public void run() {
            lastOutput = boundedQueue.dequeue();
            output.add(lastOutput);
        }
    }


}