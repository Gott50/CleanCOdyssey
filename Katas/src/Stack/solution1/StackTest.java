package Stack.solution1;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StackTest {

    private Stack<Integer> test;

    @Before
    public void setUp() throws Exception {
        test = new Stack<>();
    }

    @Test(expected = IllegalOperationException.class)
    public void pop_GivenEmpty_ThrowsException() throws Exception {
        test.pop();
    }

    @Test
    public void push_Given1_StackIncludes1() throws Exception {
        pushArray(1);
        assertToArray(new Integer[]{1});
    }

    @Test
    public void push_Given123_StackIncludes123() throws Exception {
        pushArray(1, 2, 3);
        assertToArray(new Integer[]{1, 2, 3});
    }

    private void assertToArray(Integer[] expected) {
        Assert.assertArrayEquals(expected, test.toArray());
    }

    private void pushArray(int... elements) {
        for (int element : elements) {
            test.push(element);
        }
    }

    @Test
    public void pop_Given1_Returns1() throws Exception {
        Integer input = 1;
        test.setElements(input);
        assertPops(input);
    }

    @Test
    public void pop_Given123_Returns123() throws Exception {
        test.setElements(1, 2, 3);
        assertPops(3, 2, 1);
    }

    @Test
    public void pop_GivenPush1_Returns1() throws Exception {
        Integer input = 1;
        pushArray(input);
        assertPops(input);
    }

    @Test
    public void pop_GivenPush123_Returns321() throws Exception {
        pushArray(1, 2, 3);
        assertPops(3, 2, 1);
    }

    private void assertPops(Integer... expected) throws IllegalOperationException {
        for (Integer element : expected) {
            Assert.assertEquals(element, test.pop());
        }
    }

    @Test
    public void integration() throws Exception {
        test.push(5);
        assertPops(5);
        boolean expectedExceptionThrown = false;
        try {
            test.pop();
        } catch (IllegalOperationException ioe) {
            expectedExceptionThrown = true;
        }
        Assert.assertTrue(expectedExceptionThrown);
        pushArray(7, 9, 1);
        assertPops(1, 9, 7);
        Assert.assertEquals(0, test.toArray().length);
    }
}