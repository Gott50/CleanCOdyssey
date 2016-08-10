package Box_Plot.solution1;

import org.junit.Test;

import static org.testng.AssertJUnit.assertEquals;

public class GraphicalControlTest {


    @Test
    public void printNumbers_1to4() {
        GraphicalControl graph = new GraphicalControl(new BoxPlot(1, 2, 3, 4));
        String expected = "1   2   3   4";
        assertEquals(expected, graph.printNumbers());
    }


}