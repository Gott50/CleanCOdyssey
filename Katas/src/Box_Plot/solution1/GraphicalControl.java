package Box_Plot.solution1;

class GraphicalControl {
    private final BoxPlot boxPlot;

    GraphicalControl(BoxPlot boxPlot) {
        this.boxPlot = boxPlot;
    }

    String printNumbers() {
        String out = "";
        for (int number = boxPlot.getMinimum(); number <= boxPlot.getMaximum(); number++) {
            if (!out.isEmpty()) out += "   ";
            out += number + "";
        }
        return out;
    }
}
