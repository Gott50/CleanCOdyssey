/**
 * 
 */
package Problems;

/*
 * P40.java 
 *
 * @author Timo Morawitz
 * 15.07.2014 
 * 
 * Ergebnis: 210
 */
public class P40 {

    /**
     * @param args
     */
    public static void main(String[] args) {
	System.out.println("Antwort: " + getAnswer(genNum()));
	// 210

    }

    public static String genNum() {
	String out = "", str = "";
	long digit = 0;

	for (int i = 1; digit <= 1100000; i++) {
	    str = i + "";
	    digit += str.length();
	    out = out + str;
	    System.out.println(digit);

	}
	// System.out.println(out);
	return out;
    }

    public static int getDigitAt(int at, String all) {
	return Integer.parseInt(all.charAt(at - 1) + "");
    }

    public static int getAnswer(String all) {
	int out = 1;
	for (int i = 0; i < 7; i++) {
	    // System.out.println(getDigitAt((int)(Math.pow(10,i)), all));
	    out *= getDigitAt((int) (Math.pow(10, i)), all);
	}
	return out;
    }

}
