package MathProblems;

import java.util.ArrayList;

/**
 * A Maths Problems Solution
 * Ger: Gesucht ist eine sechsstellige Zahl N e N, für die gilt: Nimmt man ihre letzte Ziffer fort und setzt sie an den Anfang, so entsteht eine neue sechsstellige Zahl M e N, die fünfmal so groß ist wie die ursprüngliche Zahl.
 * Eng: Look for a 6 digit integer, with thees requirements: If you take it's last digit away and place it a the stat, the result is a 6 digit integer 5 times greater than the number you had before.
 */
class MathProblemSolution {
    static int getResultingNumber(int oldNumber) {
        String string = oldNumber + "";
        String out = string.charAt(string.length() - 1) + string.substring(0, string.length() - 1);
        return Integer.parseInt(out);
    }

    static ArrayList<Integer> generateNumbers() {
        ArrayList<Integer> out = new ArrayList<>();
        for (int i = 100000; i <= 999999; i++)
            if (i * 5 == MathProblemSolution.getResultingNumber(i)) out.add(i);
        return out;
    }

}
