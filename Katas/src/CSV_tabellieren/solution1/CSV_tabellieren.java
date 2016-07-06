package CSV_tabellieren.solution1;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CSV_tabellieren {

    public static ArrayList<String> tabelliere(ArrayList<String> list) {
        ArrayList<String> outs = new ArrayList<>();
        String[][] table = generateTable(list);
        for (int i = 0; i < table.length; i++) {
            if (i == 1) outs.add(generateLine(generateColumnSizes(table)));
            outs.add(generateRow(table[i], generateColumnSizes(table), " ", "|"));
        }
        return outs;
    }

    private static String[][] generateTable(ArrayList<String> list) {
        String[][] out = new String[list.size()][getNumberOfCoulums(list.get(0))];
        for (int x = 0; x < out.length; x++)
            out[x] = list.get(x).split(";");
        return out;
    }

    private static int[] generateColumnSizes(String[][] table) {
        int out[] = new int[table[0].length];
        for (int column = 0; column < table[0].length; column++)
            for (String[] row : table)
                if (row[column].length() > out[column])
                    out[column] = row[column].length();
        return out;
    }

    private static int getNumberOfCoulums(String string) {
        return string.split(";").length;
    }

    @NotNull
    private static String generateRow(String[] strings, int[] columnSizes, String filling, String lineEnd) {
        String out = "";
        for (int i = 0; i < columnSizes.length; i++) {
            out += (strings[i] != null ? strings[i] : "") + getFiller(strings[i], columnSizes[i], filling) + lineEnd;
        }
        return out;
    }

    private static String generateLine(int[] columnSizes) {
        String[] strings = new String[columnSizes.length];
        return generateRow(strings, columnSizes, "-", "+");
    }

    private static String getFiller(String string, int column, String filling) {
        String out = "";
        int num = column - (string != null ? string.length() : 0);
        for (int i = 0; i < num; i++) {
            out += filling;
        }
        return out;
    }
}
