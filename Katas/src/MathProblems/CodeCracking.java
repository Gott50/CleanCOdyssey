package MathProblems;


/**
 * A Mathe Problem by Univ.-Prof. Dr. Thomas Huckle
 * http://www5.in.tum.de/wiki/index.php/Univ.-Prof._Dr._Thomas_Huckle
 *
 * Problem source:
 * http://www5.in.tum.de/persons/huckle/R%C3%A4tsel_Code.pdf
 *
 * Version 2:
 * http://www5.in.tum.de/persons/huckle/R%C3%A4tsel_Code_besser.pdf
 */
class CodeCracking {

    static long getOriginal(long encrypted) {
        for (long i = encrypted; i > encrypted / 10; i--) {
            if (encrypt(i) == encrypted) return i;
        }
        return -1;
    }

    static long encrypt(long number) {
        long out = 0;
        long newNum = number;
        for (long i = 0; i < (number + "").length(); i++) {
            out += newNum;
            newNum = subNumber(newNum);
        }
        return out;

    }

    static long subNumber(long number) {
        String digits = number + "";
        String substring = digits.substring(0, digits.length() - 1);
        if (substring.isEmpty()) return 0;

        return Long.parseLong(substring);
    }

    static long getOriginal2(int encrypted) {
        for (long i = encrypted; i > encrypted / 10; i--) {
            if (encrypt2(i) == encrypted) return i;
        }
        return -1;
    }

    static long encrypt2(long number) {
        return encrypt(number) + pepper(number);
    }

    private static long pepper(long number) {
        String digits = number + "";
        long lastDigit = Long.parseLong(digits.charAt(digits.length() - 1) + "");

        return (long) (lastDigit * Math.pow(10, digits.length() - 1));
    }


}
