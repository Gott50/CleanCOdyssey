package Box_Plot.solution1;

import org.junit.Test;

import static org.testng.AssertJUnit.assertEquals;

public class BoxPlotTest {

    @Test
    public void minimum() {
        BoxPlot boxPlot = new BoxPlot(1, 2, 3, 4);
        assertEquals(1, boxPlot.getMinimum());
    }

    @Test
    public void maximum() {
        BoxPlot boxPlot = new BoxPlot(1, 2, 3, 4);
        assertEquals(4, boxPlot.getMaximum());
    }

    @Test
    public void median_unevenList() {
        BoxPlot boxPlot = new BoxPlot(1, 2, 3);
        assertEquals(2.0f, boxPlot.getMedian());
    }

    @Test
    public void median_evenList() {
        BoxPlot boxPlot = new BoxPlot(1, 2, 3, 4);
        assertEquals(2.5f, boxPlot.getMedian());
    }

    @Test
    public void lowerQuartile_unevenQuartile() {
        BoxPlot boxPlot = new BoxPlot(1, 2, 3, 4, 5, 6);
        assertEquals(2.0f, boxPlot.getLowerQuartile());
    }

    @Test
    public void lowerQuartile_evenQuartile() {
        BoxPlot boxPlot = new BoxPlot(1, 2, 3, 4);
        assertEquals(1.5f, boxPlot.getLowerQuartile());
    }


}