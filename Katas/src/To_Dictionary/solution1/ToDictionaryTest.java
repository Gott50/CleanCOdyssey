package To_Dictionary.solution1;

import com.beust.jcommander.internal.Maps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.Assert.assertEquals;


@RunWith(Parameterized.class)
public class ToDictionaryTest {
    private final String input;
    private final Map<String, String> expectedOutput;

    public ToDictionaryTest(String input, Map<String, String> expectedOutput) {
        this.input = input;
        this.expectedOutput = expectedOutput;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"", new TreeMap<String, String>()},
                {"a=1", Maps.newHashMap("a", "1")},
                {"a=1;b=2;c=3", Maps.newHashMap("a", "1", "b", "2", "c", "3")},
                {"a=1;a=2", Maps.newHashMap("a", "2")},
                {"a=", Maps.newHashMap("a", "")},
                {"a==1", Maps.newHashMap("a", "=1")},
                {"a===1", Maps.newHashMap("a", "==1")},
        });
    }

    @Test
    public void toDictionary() throws Exception {
        assertEquals(expectedOutput, ToDictionary.toDictionary(input));
    }

    @Test(expected = Exception.class)
    public void noKey() throws Exception {
        ToDictionary.toDictionary(input.substring(1));
    }


}