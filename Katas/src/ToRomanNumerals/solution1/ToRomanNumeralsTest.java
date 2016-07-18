package ToRomanNumerals.solution1;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ToRomanNumeralsTest {
    private final int input;
    private final String expectedOutput;

    public ToRomanNumeralsTest(int input, String expectedOutput) {
        this.input = input;
        this.expectedOutput = expectedOutput;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {1, "I"},
                {2, "II"},
                {4, "IV"},
                {5, "V"},
                {9, "IX"},
                {42, "XLII"},
                {99, "XCIX"},
                {2013, "MMXIII"},
                {901, "CMI"},
                {101, "CI"},
                {51, "LI"},
                {11, "XI"},
        });
    }

    @Test
    public void translate() {
        assertEquals(expectedOutput, ToRomanNumerals.translate(input));
    }


}