package CSV_Tableizer.solution1;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;

public class CSV_Tableizer {

    public static Iterable<String> toTable(Iterable<String> list) {
        ArrayList<String> outs = new ArrayList<>();
        String[][] table = generateTable(list);
        for (int i = 0; i < table.length; i++) {
            if (i == 1) outs.add(generateLine(generateColumnSizes(table)));
            outs.add(generateRow(table[i], generateColumnSizes(table), " ", "|"));
        }
        return outs;
    }

    private static String[][] generateTable(Iterable<String> list1) {
        ArrayList<String> list = new ArrayList<>((Collection<? extends String>) list1);

        String[][] out = new String[list.size()][getNumberOfColumns(list.get(0))];
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

    private static int getNumberOfColumns(String string) {
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
