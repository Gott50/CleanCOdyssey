package Box_Plot.solution1;

class GraphicalControl {
    private final BoxPlot boxPlot;

    GraphicalControl(BoxPlot boxPlot) {
        this.boxPlot = boxPlot;
    }

    String printNumbers() {
        String out = "";
        for (int number = boxPlot.getMinimum();
             number <= boxPlot.getMaximum(); number++) {
            if (!out.isEmpty()) out += "   ";
            out += number + "";
        }
        return out;
    }

    String printNumberLine() {
        String out = "";
        for (int number = boxPlot.getMinimum();
             number <= boxPlot.getMaximum(); number++) {
            if (number == boxPlot.getMaximum()) out += "|";
            else
                out += "|-|-";
        }
        return out;
    }

    private String generateBoxPlotEdge(char character) {
        String out = generatePause(' ', boxPlot.getMinimum(), boxPlot.getLowerQuartile());
        for (float number = boxPlot.getLowerQuartile();
             number < boxPlot.getUpperQuartile() - 0.5f; number += 0.5f) {
            out += character + "" + character;
        }
        out += character;
        return out;
    }

    private String generatePause(char pauseChar, float start, float end) {
        String out = String.valueOf(pauseChar);
        for (float number = start;
             number <= end; number += 0.5f) {
            out += pauseChar;
        }
        return out;
    }

    String printUpperEdge() {
        return generateBoxPlotEdge('_');
    }

    String printLowerEdge() {
        return generateBoxPlotEdge('‾');
    }

    String generateBoxPlotBody() {
        return "|" + generatePause('-', boxPlot.getMinimum(), boxPlot.getLowerQuartile() - 1) + "|"
                + generatePause(' ', boxPlot.getLowerQuartile(), boxPlot.getMedian() - 0.5f) + "|"
                + generatePause(' ', boxPlot.getMedian(), boxPlot.getUpperQuartile() - 0.5f) + "|"
                + generatePause('-', boxPlot.getUpperQuartile(), boxPlot.getMaximum() - 1) + "|";
    }

    String printBoxPlot() {
        return printNumbers() + "\n"
                + printNumberLine() + "\n"
                + printUpperEdge() + "\n"
                + generateBoxPlotBody() + "\n"
                + printLowerEdge();
    }
}
