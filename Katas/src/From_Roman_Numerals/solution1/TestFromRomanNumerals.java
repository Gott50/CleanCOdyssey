package From_Roman_Numerals.solution1;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class TestFromRomanNumerals {
    private final String input;
    private final int expectedOutput;

    public TestFromRomanNumerals(String input, int expectedOutput) {
        this.input = input;
        this.expectedOutput = expectedOutput;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"I", 1},
                {"II", 2},
                {"IV", 4},
                {"V", 5},
                {"IX", 9},
                {"XLII", 42},
                {"XCIX", 99},
                {"MMXIII", 2013}
        });
    }

    @org.junit.Test
    public void simpleTranslation() throws Exception {
        assertEquals(expectedOutput, FromRomanNumerals.map(input));
    }

    @org.junit.Test
    public void detectSyntactic() throws Exception {
        assertEquals(9, FromRomanNumerals.map("I X"));
    }

    @org.junit.Test(expected = Exception.class)
    public void errorsInRomanNumerals() throws Exception {
        FromRomanNumerals.map("IC");
    }


}