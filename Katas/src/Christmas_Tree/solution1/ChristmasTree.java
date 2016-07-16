package Christmas_Tree.solution1;

import org.jetbrains.annotations.NotNull;

class ChristmasTree {
    static String draw(int height) {
        return getCrown(height) + getEnd(height, "I");
    }

    @NotNull
    private static String getCrown(int height) {
        String out = "";
        for (int row = 0; row < height; row++) {
            String whiteSpace = getMultiples(height, row, " ");
            String leaves = getMultiples(row * 2 + 2, 0, "X");
            out += whiteSpace + leaves + "\n";
        }
        return out;
    }

    private static String getMultiples(int height, int row, String string) {
        String out = "";
        for (int j = 0; j < height - row - 1; j++) out += string;
        return out;
    }

    @NotNull
    private static String getEnd(int height, String end) {
        return getMultiples(height, 0, " ") + end;
    }

    static String drawWithTop(int height) {
        return getEnd(height, "*" + "\n") + draw(height);
    }
}
