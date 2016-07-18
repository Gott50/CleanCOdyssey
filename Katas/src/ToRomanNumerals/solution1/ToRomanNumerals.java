package ToRomanNumerals.solution1;

import java.util.TreeMap;

class ToRomanNumerals {
    private static final TreeMap<Integer, String> map;

    static {
        map = new TreeMap<>();
        map.put(1, "I");
        map.put(4, "IV");
        map.put(5, "V");
        map.put(9, "IX");
        map.put(10, "X");
        map.put(40, "XL");
        map.put(50, "L");
        map.put(90, "XC");
        map.put(100, "C");
        map.put(900, "CM");
        map.put(1000, "M");
    }

    static String translate(int input) {
        String out = "";
        for (int integer = input; integer > 0; integer--)
            if (containsInputAMultipleOfAMapEntry(input, integer)) {
                out += translateMultiples(input, integer);
                input %= integer;
            }
        return out;
    }

    private static String translateMultiples(int input, int integer) {
        String out = "";
        for (int i = input / integer; i >= 1; i--) out += map.get(integer);
        return out;
    }

    private static boolean containsInputAMultipleOfAMapEntry(int input, int multiple) {
        return map.containsKey(multiple) && input / multiple >= 1;
    }
}
