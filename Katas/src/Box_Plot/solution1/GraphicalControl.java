package Box_Plot.solution1;

import org.jetbrains.annotations.NotNull;

class GraphicalControl {
    private final BoxPlot boxPlot;

    @NotNull GraphicalControl(BoxPlot boxPlot) {
        this.boxPlot = boxPlot;
    }

    String printNumbers() {
        String out = "";
        for (int number = boxPlot.getMinimum();
             number <= boxPlot.getMaximum(); number++) {
            if (!out.isEmpty()) out += getMarkAndFiller("  ", ' ');
            out += number;
        }
        return out;
    }

    @NotNull
    private String getMarkAndFiller(String mark, char filler) {
        return mark + getCharTimesMaxLength(filler);
    }

    private String getCharTimesMaxLength(char c) {
        String out = "";
        for (int i = 0; i < String.valueOf(boxPlot.getMaximum()).length(); i++) {
            out += c;
        }
        return out;
    }

    String printNumberLine() {
        return generateMarks('-', '|', boxPlot.getMinimum(), boxPlot.getMaximum());
    }

    @NotNull
    private String generateMarks(char filler, char mark, float start, float end) {
        String out = "";
        for (float number = start; number <= end; number += 0.5f)
            if (number == end)
                out += String.valueOf(mark);
            else out += getMarkAndFiller(String.valueOf(mark), filler);
        return out;
    }

    private String generatePause(char startChar, char pauseChar, float start, float end, char endChar) {
        String out = generateMarks(pauseChar, pauseChar, start, end);
        if (out.length() >= 2)
            return startChar + out.substring(1, out.length() - 1) + endChar;
        else return out;
    }

    private String generatePause(char pauseChar, float start, float end) {
        return generatePause(pauseChar, pauseChar, start, end, pauseChar);
    }

    String printUpperEdge() {
        return generateBoxPlotEdge('_');
    }

    String printLowerEdge() {
        return generateBoxPlotEdge('‾');
    }

    private String generateBoxPlotEdge(char character) {
        String out = generatePause(' ', boxPlot.getMinimum(), boxPlot.getLowerQuartile())
                + generatePause(character, boxPlot.getLowerQuartile(), boxPlot.getUpperQuartile());
        return out.substring(0, out.length() - 2);
    }

    String printBoxPlotBody() {
        String lowerQToMedian = generatePause(' ', boxPlot.getLowerQuartile(), boxPlot.getMedian());
        String medianToUpperQ = generatePause(' ', boxPlot.getMedian(), boxPlot.getUpperQuartile());
        return generatePause('|', '-', boxPlot.getMinimum(), boxPlot.getLowerQuartile(), '|')
                + lowerQToMedian.substring(0, lowerQToMedian.length() - 2)
                + '|'
                + medianToUpperQ.substring(0, medianToUpperQ.length() - 2)
                + generatePause('|', '-', boxPlot.getUpperQuartile(), boxPlot.getMaximum(), '|');
    }

    String printBoxPlot() {
        return printNumbers() + "\n"
                + printNumberLine() + "\n"
                + printUpperEdge() + "\n"
                + printBoxPlotBody() + "\n"
                + printLowerEdge();
    }
}
