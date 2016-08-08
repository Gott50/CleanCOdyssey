package Box_Plot.solution1;

import java.util.ArrayList;
import java.util.Arrays;

class BoxPlot {
    private ArrayList<Integer> values;

    BoxPlot(Integer... values) {
        this.values = new ArrayList<>(Arrays.asList(values));
    }

    int getMinimum() {
        return values.get(0);
    }

    int getMaximum() {
        return values.get(values.size() - 1);
    }

    float getMedian() {
        return generateMedian(2);
    }

    private float generateMedian(int power) {
        if (values.size() % power == power / 2)
            return values.get(values.size() / power);
        else
            return ((float) values.get(values.size() / power) +
                    (float) values.get(values.size() / power - 1)) / 2;
    }

    float getLowerQuartile() {
        return generateMedian(4);
    }
}
