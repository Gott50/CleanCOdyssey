package Box_Plot.solution1;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.testng.AssertJUnit.assertEquals;

@RunWith(Parameterized.class)
public class BoxPlotTest {

    private final Integer[] values;
    private final int minimum;
    private final int maximum;
    private final float median;
    private final float lowerQuartile;
    private final float upperQuartile;
    private BoxPlot boxPlot;

    public BoxPlotTest(Integer[] values, int minimum, int maximum, float median, float lowerQuartile, float upperQuartile) {
        this.values = values;
        this.minimum = minimum;
        this.maximum = maximum;
        this.median = median;
        this.lowerQuartile = lowerQuartile;
        this.upperQuartile = upperQuartile;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {new Integer[]{1, 2, 3, 4}, 1, 4, 2.5f, 1.5f, 3.5f},
                // {new Integer[]{1, 2, 3}, 1, 3, 2.0f, 1.5f, 2.5f},
        });
    }

    @Before
    public void setUp() throws Exception {

        boxPlot = new BoxPlot(values);

    }

    @Test
    @Ignore
    public void median_unevenList() {
        BoxPlot boxPlot = new BoxPlot(1, 2, 3);
        assertEquals(2.0f, boxPlot.getMedian());
    }

    @Test
    @Ignore
    public void lowerQuartile_unevenQuartile() {
        BoxPlot boxPlot = new BoxPlot(1, 2, 3, 4, 5, 6);
        assertEquals(2.0f, boxPlot.getLowerQuartile());
    }

    @Test
    @Ignore
    public void upperQuartile_unevenQuartile() {
        BoxPlot boxPlot = new BoxPlot(1, 2, 3, 4, 5, 6);
        assertEquals(5.0f, boxPlot.getUpperQuartile());
    }

    @Test
    public void minimum() {
        assertEquals(minimum, boxPlot.getMinimum());
    }

    @Test
    public void maximum() {
        assertEquals(maximum, boxPlot.getMaximum());
    }

    @Test
    public void median() {
        assertEquals(median, boxPlot.getMedian());
    }

    @Test
    public void lowerQuartile() {
        assertEquals(lowerQuartile, boxPlot.getLowerQuartile());
    }

    @Test
    public void upperQuartile() {
        assertEquals(upperQuartile, boxPlot.getUpperQuartile());
    }


}