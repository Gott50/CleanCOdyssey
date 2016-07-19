package Count_Characters.solution1;

import java.util.Map;
import java.util.TreeMap;

class CountCharacters {
    public static Map<Character, Integer> count(String input) {
        Map<Character, Integer> map = new TreeMap<>();
        for (char c : input.toCharArray()) {
            if (isEmpty(c)) continue;
            map.put(c, getNextValue(map.get(c)));
        }
        return map;
    }

    private static boolean isEmpty(char c) {
        return String.valueOf(c).trim().isEmpty();
    }

    private static int getNextValue(Integer presentValue) {
        return presentValue != null ? presentValue + 1 : 1;
    }

    public static Map<Character, Integer> countWithoutCase(String input) {
        return count(input.toUpperCase());
    }
}
