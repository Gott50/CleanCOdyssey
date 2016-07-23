package Bounded_Queue.solution1;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BoundedQueueTest {
    private final int size = 2;
    private final Integer[] values = new Integer[]{1, 2, 3, 4, 5};
    private BoundedQueue boundedQueue;
    private WritingThread<Integer> writing;
    private ReadingThread reading;

    @Before
    public void setUp() throws Exception {
        boundedQueue = new BoundedQueue<Integer>(size);
        writing = new WritingThread<>(boundedQueue, values);
        reading = new ReadingThread(boundedQueue);
    }

    @Test
    @Ignore
    public void integration() {
        writing.run();
        assertEquals(((List) Arrays.asList(1)).toString(), boundedQueue.values().toString());
        reading.run();
        writing.run();
        assertEquals(((List) Arrays.asList(2)).toString(), boundedQueue.values().toString());
        writing.run();
        assertEquals(((List) Arrays.asList(2, 3)).toString(), boundedQueue.values().toString());
        writing.run();
        reading.run();
        assertEquals(((List) Arrays.asList(3, 4)).toString(), boundedQueue.values().toString());
        reading.run();
        assertEquals(((List) Arrays.asList(4)).toString(), boundedQueue.values().toString());
        reading.run();
        reading.run();
        writing.run();
        assertEquals(((List) Arrays.asList(5)).toString(), boundedQueue.values().toString());
        reading.run();//unnecessary
        assertEquals(5, reading.lastOutput);
    }

    @Test
    public void getSize() {
        assertEquals(size, writing.boundedQueue.getSize());
        assertEquals(size, reading.boundedQueue.getSize());
    }

    @Test
    public void Enqueue() {
        int value = values[0];
        writing.start();
        waitForThread(writing);
        assertEquals(value, writing.boundedQueue.values().get(0));
    }

    private void waitForThread(Thread thread) {
        while (thread.isAlive()) ;
    }

    @Test
    public void Enqueue_TwoTimes() {
        writing.setLoop(2);
        writing.start();
        List expected = Arrays.asList(values[0], values[1]);

        waitForThread(writing);
        assertEquals(expected.toString(), writing.boundedQueue.values().toString());
    }

    @Test
    public void Dequeue() {
        writing.start();
        waitForThread(writing);
        reading.start();
        waitForThread(reading);
        assertEquals(values[0], reading.output.get(0));
        assertEquals(0, reading.boundedQueue.values().size());
    }

    @Test
    public void Dequeue_givenTwoElements() {
        writing.setLoop(2);
        writing.start();
        waitForThread(writing);
        reading.start();
        waitForThread(reading);
        assertEquals(values[0], reading.output.get(0));
        assertEquals(1, reading.boundedQueue.values().size());
    }

    @Test
    public void Count() {
        writing.setLoop(2);
        writing.start();
        waitForThread(writing);
        assertEquals(2, writing.boundedQueue.count());
    }

    @Test
    @Ignore
    public void Enqueue_BlockToMuch() {
        writing.setLoop(3);
        writing.start();
        waitForThread(writing);
        assertEquals((Arrays.asList(1, 2)).toString(), writing.boundedQueue.values().toString());
    }

    @Test
    @Ignore
    public void Enqueue_ReleaseBlock() {
        writing.setLoop(2);
        writing.start();
        waitForThread(writing);

        WritingThread writing1 = new WritingThread(writing.boundedQueue);
        writing1.start();

        reading.start();

        waitForThread(reading);
        waitForThread(writing1);
        List expected = Arrays.asList(2, 3);
        assertEquals(expected.toString(), boundedQueue.values().toString());
    }

    class WritingThread<T> extends Thread {
        final ArrayList<T> input;
        private final BoundedQueue boundedQueue;
        private int times = 1;

        public WritingThread(BoundedQueue boundedQueue, T... input) {
            this.boundedQueue = boundedQueue;
            this.input = new ArrayList(Arrays.asList(input));
        }

        public void setLoop(int times) {
            this.times = times;
        }

        @Override
        public void run() {
            for (int i = 0; i < times; i++)
                write();
        }

        private void write() {
            T value = input.get(0);
            input.remove(0);
            boundedQueue.enqueue(value);
        }
    }

    class ReadingThread extends Thread {
        final ArrayList output = new ArrayList();
        private final BoundedQueue boundedQueue;
        Object lastOutput;
        private int times = 1;

        public ReadingThread(BoundedQueue boundedQueue) {
            this.boundedQueue = boundedQueue;
        }

        public void setLoop(int times) {
            this.times = times;
        }

        @Override
        public void run() {
            for (int i = 0; i < times; i++) read();
        }

        private void read() {
            lastOutput = boundedQueue.dequeue();
            output.add(lastOutput);
        }
    }


}