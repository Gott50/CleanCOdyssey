package Priority_Queue.solution1;

import org.junit.Before;
import org.junit.Test;

import static org.testng.AssertJUnit.assertEquals;

public class PriorityQueueTest {

    private PriorityQueue<Integer> test;

    @Before
    public void setUp() throws Exception {
        test = new PriorityQueue<>();
    }

    @Test
    public void givenNull_CountReturns0() throws Exception {
        assertCount(0);
    }

    private void assertCount(int expected) {
        assertEquals(expected, test.count());
    }

    @Test
    public void given1Queue_CountReturns1() throws Exception {
        test.enqueue(1, 5);
        assertCount(1);
    }

    @Test
    public void given3TimesEnqueue_CountReturns3() throws Exception {
        enqueueArray(5, 1, 2, 3);

        assertCount(3);
    }

    private void enqueueArray(int priority, int... elements) {
        for (int element : elements) {
            test.enqueue(element, priority);
        }
    }

    @Test
    public void given3TimesEnqueueWithSameElement_CountReturns1() throws Exception {
        int element = 1;
        enqueueArray(5, element, element, element, element);

        assertCount(element);
    }

    @Test
    public void givenEnqueue1_DequeueReturns1() throws Exception {
        test.enqueue(1, 5);

        assertDequeue(1);
    }

    @Test
    public void givenEnqueue1And2WithSamePriority_DequeueReturns1And2() throws Exception {
        enqueueArray(5, 1, 2);

        assertDequeue(1, 2);
    }

    @Test
    public void givenEnqueue1p5And2p9_DequeueReturns2And1() throws Exception {
        test.enqueue(1, 5);
        test.enqueue(2, 9);

        assertDequeue(2, 1);
    }

    @Test
    public void givenEnqueue5ElementsWithSamePriority_DequeueReturns12345() throws Exception {
        enqueueArray(5, 1, 2, 3, 4, 5);

        assertDequeue(1, 2, 3, 4, 5);
    }

    private void assertDequeue(int expected) {
        assertEquals(new Integer(expected), test.dequeue());
    }

    private void assertDequeue(int... expected) {
        for (int exp : expected) {
            assertDequeue(exp);
        }
    }

    @Test
    public void integration() throws Exception {
        test.enqueue(1, 5);
        test.enqueue(2, 5);
        assertDequeue(1);
        test.enqueue(3, 7);
        test.enqueue(4, 7);
        test.enqueue(5, 3);
        assertCount(4);

        assertDequeue(3, 4, 2, 5);
    }


}