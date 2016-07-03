package problems;

import java.math.BigInteger;

public class P027_Quadratic_primes {

    /**
     * @param args
     */
    public static void main(String[] args) {
	// n² + an + b, where |a| < 1000 and |b| < 1000
	
	int range = 1000;
	int a1 = -range;
	int b1 = -range;
	
	int greatestn = 0;
	int greatesta = 0;
	int greatestb = 0;
	
	BigInteger Prime = new BigInteger("0");
	for(int a = a1; a <= range; a++)
	    for(int b = b1; b <= range; b++){
		int n = 0;
		while(true){
		    Prime = new BigInteger(n + "").pow(2).add(new BigInteger(a*n + b + ""));
		    if(Prime.isProbablePrime(1))
		    n++;
		    else break;
		}
		System.out.println("a: "+a + " b: "+b + " n: " +n);
		if(greatestn < n){
		    greatestn = n;
		    greatesta = a;
		    greatestb = b;
		}
	    }
	
	System.out.println("greatestn: " + greatestn);
	System.out.println("greatesta: " + greatesta);
	System.out.println("greatestb: " + greatestb);
	System.out.println("Product: " + (greatesta*greatestb));

    }

}
