package Problems;

import java.math.BigInteger;

public class P20_Factorial_digit_sum {

    /**
     * @param args
     */
    public static void main(String[] args) {
	int n = 100;
	BigInteger factorial = new BigInteger("1");
	
	for(long f = 1; f <= n; f++){
	    BigInteger fac = new BigInteger(f+"");
	    factorial = factorial.multiply(fac);
	    System.out.println(factorial);
	}
	
	
	BigInteger Zahl = new BigInteger(factorial + "");
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
