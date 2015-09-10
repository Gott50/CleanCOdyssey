package Problems;

import java.math.BigInteger;

public class P25_1000_digit_Fibonacci_number {

    /**
     * @param args
     */
    public static void main(String[] args) {
	String fib = null;
	int i = 2, sum = 0;
	BigInteger[] num = new BigInteger [10000];
	num [0]= new BigInteger ("1");
	num [1]= new BigInteger ("2");
	
	while(true) {
	    System.out.println(fib);
	    
	    num[i] = num[i-1].add(num[i-2]);
	    
	    fib = num[i] + "";
	    if(fib.length() == 1000) break;
	    
	    i++;
	}
	
	System.out.println("Ergebniss: " + (i+2));
	
    

    }

}
