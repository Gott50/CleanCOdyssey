package To_Dictionary.solution1;

import java.util.Map;
import java.util.TreeMap;

class ToDictionary {
    static Map<String, String> toDictionary(String input) throws Exception {
        Map<String, String> dic = new TreeMap<>();
        if (input == null || input.isEmpty()) return dic;
        for (String entry : input.split(";"))
            makeEntry(entry, dic);
        return dic;
    }

    private static void makeEntry(String input, Map<String, String> out) throws Exception {
        String[] split = input.split("=");
        String value = "";
        if (split[0].isEmpty()) throw new Exception("Key can not be Empty!");
        if (split.length > 2) value = moveEqualSigns(split);
        if (split.length >= 2)
            value += split[split.length - 1];
        out.put(split[0], value);

    }

    private static String moveEqualSigns(String[] split) {
        String value = "";
        for (int i = 2; i < split.length; i++) value += "=";
        return value;
    }
}
