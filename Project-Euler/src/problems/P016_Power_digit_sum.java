package problems;

import java.math.BigInteger;

public class P016_Power_digit_sum {

    /**
     * @param args
     */
    public static void main(String[] args) {
	BigInteger Zahl = new BigInteger("2").pow(1000);
	System.out.println(Zahl);
	BigInteger sum = new BigInteger("0");
	BigInteger rem = new BigInteger("0");
	for(int d = 1; Zahl.compareTo(new BigInteger("10").pow(d-1)) == 1; d++){
	    rem = Zahl.remainder(new BigInteger("10").pow(d)).divide(new BigInteger("10").pow(d-1));
	    Zahl = Zahl.subtract(rem);
	    System.out.println(rem);
	    sum = sum.add(rem);
	}
	System.out.println("Das Ergebniss ist: " + sum);

    }

}
