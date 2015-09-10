package Problems;

public class P012_Highly_divisible_triangular_number {

    /**
     * @param args
     */
    public static void main(String[] args) {

	int divisors = 500, num;
	long n = 1;
	long tri = 1;

	System.out.println("Starto");
	while (true) {
	    num = 1;
	    tri = n * (n + 1) / 2;
	    System.out.println("Triangle: " + (tri));

	    for (int q = 1; q <= tri / 2; q++) {
		// System.out.println("q " +q);
		if (tri % 2 != 0)
		    break;
		if (tri % q == 0) {
		    num++;
		    // System.out.println("num"+ num +": "+q);
		}
		if (tri == q)
		    break;
	    }

	    if (num > divisors) {
		System.out.println("Ergebnis: " + tri);
		break;
	    }

	    n++;

	}

    }

}
