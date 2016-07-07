package FizzBuzz.test1;

import FizzBuzz.solution1.FizzBuzz;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

@SuppressWarnings("unused")
public class TestFizzBuzz {

    @Test
    public void testReturnNumbers() {
        assertEquals(Arrays.asList("1", "2"), FizzBuzz.compute(2));
    }

    @Test
    public void testReturnFizz() throws Exception {
        assertEquals(Arrays.asList("1", "2", "Fizz"), FizzBuzz.compute(3));
    }

    @Test
    public void testReturnBuzz() throws Exception {
        assertEquals(Arrays.asList("1", "2", "Fizz", "4", "Buzz"), FizzBuzz.compute(5));
    }

    @Test
    public void testReturnFizzBuzz() throws Exception {
        assertEquals("FizzBuzz", FizzBuzz.compute(15).get(14));
    }

    @Test
    public void testReturnFizzVariation() throws Exception {
        assertEquals("Fizz", FizzBuzz.compute(13).get(12));
    }

    @Test
    public void testReturnBuzzVariation() throws Exception {
        assertEquals("Buzz", FizzBuzz.compute(52).get(51));
    }
}