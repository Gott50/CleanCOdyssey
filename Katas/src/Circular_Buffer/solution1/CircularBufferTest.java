package Circular_Buffer.solution1;

import org.junit.Before;
import org.junit.Test;

import static org.testng.AssertJUnit.assertEquals;

public class CircularBufferTest {

    private final int SIZE = 3;
    private CircularBuffer<Integer> test;

    @Before
    public void setUp() throws Exception {
        test = new CircularBuffer<>(SIZE);
    }

    @Test
    public void givenSize3_Returns3() throws Exception {
        assertSize(SIZE);
    }

    private void assertSize(int expected) {
        assertEquals(expected, test.getSize());
    }

    @Test
    public void givenNothing_CountReturns0() throws Exception {
        assertCount(0);

    }

    private void assertCount(int expected) {
        assertEquals(expected, test.count());
    }

    @Test
    public void AddGiven5_CountReturns1() throws Exception {
        Integer i = 5;
        test.add(i);
        assertCount(1);
    }

    @Test
    public void AddGiven5_TakeReturns5() throws Exception {
        addElements(5);
        assertTake(5);
    }

    @Test
    public void AddGivenElements567_TakesReturn567() throws Exception {
        addElements(5, 6, 7);
        assertTake(5, 6, 7);
    }

    @Test
    public void AddGivenElements5678_TakesReturn678() throws Exception {
        addElements(5, 6, 7, 8);
        assertCount(3);
        assertTake(6, 7, 8);
    }

    private void assertTake(Integer... elements) {
        for (Integer element : elements) {
            assertEquals(element, test.take());
        }
    }

    private void addElements(Integer... elements) {
        for (Integer element : elements) {
            test.add(element);
        }
    }

    @Test
    public void integration() throws Exception {
        addElements(1, 2);
        assertSize(3);
        assertCount(2);
        assertTake(1);
        addElements(3, 4, 5);
        assertTake(3);
        addElements(6, 7);
        assertTake(5, 6, 7);
    }
}