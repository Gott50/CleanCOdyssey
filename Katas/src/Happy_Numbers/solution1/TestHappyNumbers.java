package Happy_Numbers.solution1;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestHappyNumbers {
    private HappyNumbers happy;

    @Before
    public void setUp() {
        happy = new HappyNumbers();

    }

    @Test
    public void isHappy_1() {
        assertEquals(true, happy.isHappy(1));
    }

    @Test
    public void isHappy_100() {
        assertEquals(true, happy.isHappy(100));
    }

    @Test
    public void isHappy_19() {
        assertEquals(true, happy.isHappy(19));
    }

    @Test
    public void isHappy_4() {
        assertEquals(false, happy.isHappy(4));
    }

    @Test
    public void calculateNumbers() {
        List<Integer> expected = Arrays.asList(10, 13, 19);
        assertEquals(expected, happy.calculateNumbers(10, 20));
    }

    @Test
    public void calculateNumbersTo100() {
        List<Integer> expected = Arrays.asList(1, 7, 10, 13, 19, 23, 28, 31, 32, 44, 49, 68, 70, 79, 82, 86, 91, 94, 97, 100);
        assertEquals(expected, happy.calculateNumbers(1, 100));
    }


}