package LOC.solution1;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class LOC {
    public static int countCode(String code) {
        if (code == null || code.isEmpty()) return 0;
        return filter(getList(filterOpenCloseComments(code))).size();
    }

    private static String filterOpenCloseComments(String code) {
        String out = "";
        String[] strings = code.split("/\\*|\\*/");
        for (int i = 0; i < strings.length; i += 2) {
            out += strings[i];
        }
        return out;
    }

    @NotNull
    private static List<String> getList(String code) {
        return new ArrayList<>(Arrays.asList(code.split("\n")));
    }

    private static @NotNull List<String> filter(@NotNull List<String> split) {
        split.removeIf(s -> s.trim().isEmpty() || isComment(s.trim()));
        return split;
    }

    private static boolean isComment(String line) {
        return line.startsWith("//");
    }

    public static int countComments(String code) {
        return getList(code).size() - countCode(code);
    }
}
