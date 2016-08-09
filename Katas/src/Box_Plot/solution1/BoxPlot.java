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
        return generateMedian(2, 0);
    }

    private float generateMedian(int power, double shiftPower) {
        int shift = (int) (values.size() * (shiftPower));
        int index = values.size() / power + shift;
        if (values.size() % power == power / 2)
            return values.get(index);
        else
            return ((float) values.get(index) +
                    (float) values.get(index - 1)) / 2;
    }

    float getLowerQuartile() {
        return generateMedian(4, 0);
    }

    float getUpperQuartile() {
        return generateMedian(4, 0.5);
    }
}
