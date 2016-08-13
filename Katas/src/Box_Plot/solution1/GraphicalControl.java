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
            if (!out.isEmpty()) {
                out += "  ";
                out += getCharTimesMaxLength(' ');
            }
            out += number + "";
        }
        return out;
    }

    private String getCharTimesMaxLength(char c) {
        String out = "";
        for (int i = 0; i < String.valueOf(boxPlot.getMaximum()).length(); i++) {
            out += c;
        }
        return out;
    }

    String printNumberLine() {
        char filler = '-';
        char mark = '|';


        String markStr = String.valueOf(mark);

        String out = "";
        for (int number = boxPlot.getMinimum();
             number <= boxPlot.getMaximum(); number++) {
            if (number == boxPlot.getMaximum())
                out += markStr;
            else {
                out += markStr +
                        getCharTimesMaxLength(filler) +
                        markStr +
                        getCharTimesMaxLength(filler);
            }
        }
        return out;
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
        String out = generatePause(' ', boxPlot.getMinimum(), boxPlot.getLowerQuartile());
        for (float number = boxPlot.getLowerQuartile();
             number < boxPlot.getUpperQuartile() - 0.5f; number += 0.5f) {
            out += character + "" + character;
        }
        out += character;
        return out;
    }

    String generateBoxPlotBody() {
        return generatePause('|', '-', boxPlot.getMinimum(), boxPlot.getLowerQuartile(), '|')
                + generatePause(' ', boxPlot.getLowerQuartile(), boxPlot.getMedian() - 0.5f) + "|"
                + generatePause(' ', boxPlot.getMedian(), boxPlot.getUpperQuartile() - 0.5f) + "|"
                + generatePause('-', boxPlot.getUpperQuartile(), boxPlot.getMaximum() - 1) + "|";
    }

    private String generatePause(char startChar, char pauseChar, float start, float end, char endChar) {
        String out =
                String.valueOf(startChar) + getCharTimesMaxLength(pauseChar).substring(1);
        for (float number = start;
             number < end; number += 0.5f) {
            out += pauseChar + getCharTimesMaxLength(pauseChar);
        }
        return out.substring(0, out.length() - 1) + endChar;
    }

    String printBoxPlot() {
        return printNumbers() + "\n"
                + printNumberLine() + "\n"
                + printUpperEdge() + "\n"
                + generateBoxPlotBody() + "\n"
                + printLowerEdge();
    }
}
