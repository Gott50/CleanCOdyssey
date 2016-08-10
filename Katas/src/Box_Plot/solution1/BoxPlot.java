package Box_Plot.solution1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

class BoxPlot {
    private ArrayList<Integer> values;

    BoxPlot(Integer... values) {
        this.values = new ArrayList<>(Arrays.asList(values));
        Collections.sort(this.values);

    }


    int getMinimum() {
        return values.get(0);
    }

    int getMaximum() {
        return values.get(values.size() - 1);
    }

    float getMedian() {
        return generateQuartile(0.5);
    }

    private float generateQuartile(double order) {
        double index = values.size() * order;
        if (index % 1 != 0)
            return values.get((int) index);
        else
            return ((float) values.get((int) index) +
                    (float) values.get((int) (index - 1))) / 2;
    }

    float getLowerQuartile() {
        return generateQuartile(0.25);
    }

    float getUpperQuartile() {
        return generateQuartile(0.75);
    }

}
