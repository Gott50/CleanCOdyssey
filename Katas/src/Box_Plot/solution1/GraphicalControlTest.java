package Box_Plot.solution1;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.testng.AssertJUnit.assertEquals;

@RunWith(Parameterized.class)
public class GraphicalControlTest {

    private final Integer[] input;
    private final String output;
    private GraphicalControl graph;

    public GraphicalControlTest(Integer[] input, String output) {
        this.input = input;
        this.output = output;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {new Integer[]{1, 2, 3, 4}, "1   2   3   4" + "\n"
                        + "|-|-|-|-|-|-|" + "\n"
                        + "   _______" + "\n"
                        + "|-|   |   |-|" + "\n"
                        + "   ‾‾‾‾‾‾‾"},

                {new Integer[]{2, 4, 1, 3}, "1   2   3   4" + "\n"
                        + "|-|-|-|-|-|-|" + "\n"
                        + "   _______" + "\n"
                        + "|-|   |   |-|" + "\n"
                        + "   ‾‾‾‾‾‾‾"},
                {new Integer[]{17, 18, 18, 19, 19, 20, 24, 24, 24, 25},
                        "17    18    19    20    21    22    23    24    25" + "\n"
                                + "|--|--|--|--|--|--|--|--|--|--|--|--|--|--|--|--|" + "\n"
                                + "       ___________________________________" + "\n"
                                + "|-----|        |                          |-----|" + "\n"
                                + "       ‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾"},
        });

    }

    @Before
    public void setUp() throws Exception {
        graph = new GraphicalControl(new BoxPlot(input));

    }

    @Test
    public void printNumbers() {
        assertEquals(output.split("\n")[0], graph.printNumbers());
    }

    @Test
    public void printNumberLine() {
        assertEquals(output.split("\n")[1], graph.printNumberLine());
    }

    @Test
    public void printUpperEdge() {
        assertEquals(output.split("\n")[2], graph.printUpperEdge());
    }

    @Test
    public void printBoxPlotBody() {
        assertEquals(output.split("\n")[3], graph.printBoxPlotBody());
    }

    @Test
    public void printLowerEdge() {
        assertEquals(output.split("\n")[4], graph.printLowerEdge());
    }

    @Test
    public void printBoxPlot() {
        assertEquals(output, graph.printBoxPlot());
    }

}