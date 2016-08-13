package Box_Plot.solution1;

import org.junit.Ignore;
import org.junit.Test;

import static org.testng.AssertJUnit.assertEquals;

public class GraphicalControlTest {


    @Test
    public void printNumbers_1to4() {
        GraphicalControl graph = new GraphicalControl(new BoxPlot(1, 2, 3, 4));
        String expected = "1   2   3   4";
        assertEquals(expected, graph.printNumbers());
    }

    @Test
    public void printNumberLine_1to4() {
        GraphicalControl graph = new GraphicalControl(new BoxPlot(1, 2, 3, 4));
        String expected = "|-|-|-|-|-|-|";
        assertEquals(expected, graph.printNumberLine());
    }

    @Test
    public void printUpperEdge_1to4() {
        GraphicalControl graph = new GraphicalControl(new BoxPlot(1, 2, 3, 4));
        String expected = "   _______";
        assertEquals(expected, graph.printUpperEdge());
    }

    @Test
    public void printLowerEdge_1to4() {
        GraphicalControl graph = new GraphicalControl(new BoxPlot(1, 2, 3, 4));
        String expected = "   ‾‾‾‾‾‾‾";
        assertEquals(expected, graph.printLowerEdge());
    }

    @Test
    public void printBoxPlotBody_1to4() {
        GraphicalControl graph = new GraphicalControl(new BoxPlot(1, 2, 3, 4));
        String expected = "|-|   |   |-|";
        assertEquals(expected, graph.generateBoxPlotBody());
    }

    @Test
    public void printBoxPlot1to4() {
        GraphicalControl graph = new GraphicalControl(new BoxPlot(1, 2, 3, 4));
        String expected;
        expected = "1   2   3   4" + "\n"
                + "|-|-|-|-|-|-|" + "\n"
                + "   _______" + "\n"
                + "|-|   |   |-|" + "\n"
                + "   ‾‾‾‾‾‾‾";

        assertEquals(expected, graph.printBoxPlot());
    }


    @Test
    @Ignore
    public void integration() {
        GraphicalControl graph = new GraphicalControl(new BoxPlot(17, 18, 18, 19, 19, 20, 24, 24, 24, 25));
        String expected;
        expected = "17    18    19    20    21    22    23    24    25" + "\n"
                + "|--|--|--|--|--|--|--|--|--|--|--|--|--|--|--|--|" + "\n"
                + "       ___________________________________" + "\n"
                + "|-----|        |                          |-----|" + "\n"
                + "       ‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾";

        assertEquals(expected, graph.printBoxPlot());
    }


}