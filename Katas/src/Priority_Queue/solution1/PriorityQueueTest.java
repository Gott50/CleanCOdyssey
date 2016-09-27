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
        assertEquals(0, test.count());
    }

    @Test
    public void given1Queue_CountReturns1() throws Exception {
        Integer element = 1;
        int priority = 5;
        test.enqueue(element, priority);

        assertEquals(1, test.count());
    }

    @Test
    public void given3TimesEnqueue_CountReturns3() throws Exception {
        test.enqueue(1, 5);
        test.enqueue(2, 5);
        test.enqueue(3, 5);

        assertEquals(3, test.count());
    }

    @Test
    public void given3TimesEnqueueWithSameElement_CountReturns1() throws Exception {
        int element = 1;
        test.enqueue(element, 5);
        test.enqueue(element, 7);
        test.enqueue(element, 5);

        assertEquals(1, test.count());
    }

    @Test
    public void givenEnqueue1_DequeueReturns1() throws Exception {
        test.enqueue(1, 5);

        assertEquals(new Integer(1), test.dequeue());
    }


}