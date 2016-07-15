package Russian_Peasant_Multiplication.solution1;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RussianPeasantMultiplicationTest {
    @Test
    public void given_1_10() {
        assertEquals(10, RussianPeasantMultiplication.mul(1, 10));
    }

    @Test
    public void given_2_10() {
        assertEquals(20, RussianPeasantMultiplication.mul(2, 10));
    }

    @Test
    public void given_5_10() {
        assertEquals(50, RussianPeasantMultiplication.mul(5, 10));
    }

    @Test
    public void given_47_42() {
        assertEquals(1974, RussianPeasantMultiplication.mul(47, 42));
    }


}