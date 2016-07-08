package From_Roman_Numerals.solution1;

import java.util.TreeMap;

class FromRomanNumerals {
    private static final TreeMap<String, Integer> dictionary = createDictionary();

    private static TreeMap<String, Integer> createDictionary() {
        TreeMap<String, Integer> out = new TreeMap<>();
        out.put("I", 1);
        out.put("V", 5);
        out.put("X", 10);
        out.put("L", 50);
        out.put("C", 100);
        out.put("D", 500);
        out.put("M", 1000);
        return out;
    }


    static int map(String roman) throws Exception {
        int out = 0;
        String[] digits = roman.replace(" ", "").toUpperCase().split("");
        for (int index = digits.length - 1; index >= 0; index--) {
            int thisDigit = dictionary.get(digits[index]);
            int previousDigit = index < digits.length - 1 ? dictionary.get(digits[index + 1]) : 0;
            if (thisDigit < previousDigit)
                if (!isCorrect(thisDigit, previousDigit))
                    throw new Exception("Error in roman numerals!");
                else
                    out -= thisDigit;
            else
                out += thisDigit;
        }

        return out;
    }

    private static boolean isCorrect(int thisDigit, int previousDigit) {
        return thisDigit * 5 == previousDigit || thisDigit * 10 == previousDigit;
    }
}
