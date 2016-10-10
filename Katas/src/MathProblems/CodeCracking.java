package MathProblems;


/**
 * A Mathe Problem by Univ.-Prof. Dr. Thomas Huckle
 * http://www5.in.tum.de/wiki/index.php/Univ.-Prof._Dr._Thomas_Huckle
 * <p>
 * Problem source:
 * http://www5.in.tum.de/persons/huckle/R%C3%A4tsel_Code.pdf
 */
class CodeCracking {

    static long getOriginal(long encrypted) {
        for (long i = encrypted; i > 0; i--) {
            if (encrypt(i) == encrypted) return i;
        }
        return 0;
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
        String s = number + "";
        String substring = s.substring(0, s.length() - 1);
        if (substring.isEmpty()) return 0;

        return Long.parseLong(substring);
    }
}
