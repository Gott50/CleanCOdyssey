/**
 * 
 */
package Problems;

/*
 * P39.java 
 *
 * @author Timo Morawitz
 * 18.06.2014 
 * 
 * Ergebnis: 840
 */
public class P39 {
    public static void main(String[] args) {
//	System.out.println(isSearched(30, 40, 120));

	int solution = 0, count = 0;
	for (int p = 1; p <= 1000; p++) {
	    int co = 0;
	    for (int b = 1; b < p; b++){
		for (int a = 1; a < b; a++)
		    if (isSearched(a, b, p)) {
			co++;
			System.out.println(co + ": {" + a + "," + b + "," + getC(a, b) + "}");
		    }
		if(count < co) {
		    count = co;
		    solution = p;
		}
	    }
	}
	System.out.println("Ergebnis: " + solution);

    }

    public static double getC(int a, int b) {
	double c = Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
	return c;
    }

    public static boolean isSearched(int a, int b, double p) {
	if (p == a + b + getC(a, b))
	    return true;
	else
	    return false;
    }
}
