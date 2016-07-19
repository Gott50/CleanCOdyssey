package Count_Characters.solution1;

import com.beust.jcommander.internal.Maps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class CountCharactersTest {
    private final String input;
    private final Map expectedOutput;
    private final Map expectedOutputWithoutCase;

    public CountCharactersTest(String input, Map expectedOutput, Map expectedOutputWithoutCase) {
        this.input = input;
        this.expectedOutput = expectedOutput;
        this.expectedOutputWithoutCase = expectedOutputWithoutCase;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"", Maps.newHashMap(), Maps.newHashMap()},
                {"H", Maps.newHashMap('H', 1), Maps.newHashMap('H', 1)},
                {"HH", Maps.newHashMap('H', 2), Maps.newHashMap('H', 2)},
                {"HelLo", Maps.newHashMap('H', 1, 'e', 1, 'l', 1, 'L', 1, 'o', 1),
                        Maps.newHashMap('H', 1, 'E', 1, 'L', 2, 'O', 1)},
                {"HelLo wOrld", Maps.newHashMap('H', 1, 'e', 1, 'l', 2, 'L', 1, 'o', 1, 'w', 1, 'O', 1, 'r', 1, 'd', 1),
                        Maps.newHashMap('H', 1, 'E', 1, 'L', 3, 'O', 2, 'W', 1, 'R', 1, 'D', 1)},
        });

    }

    @Test
    public void count() {
        assertEquals(expectedOutput, CountCharacters.count(input));
    }

    @Test
    public void countWithoutCase() {
        assertEquals(expectedOutputWithoutCase, CountCharacters.countWithoutCase(input));
    }


}