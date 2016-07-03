/**
 * 
 */
package problems;

import java.math.BigInteger;

/*
 * P35.java 
 *
 * @author Gott50
 * 17.06.2014 
 * 
 * Ergebnis: 55
 */
public class P035_Circular_primes {

    /**
     * @param args
     */
    public static void main(String[] args) {
//	System.out.println(isSearched(197));
	
	int count = 0;
	BigInteger num = new BigInteger(2 + "");
	while(num.compareTo(new BigInteger(1000000 + "")) < 0){
	    if(isSearched(Integer.parseInt(num.toString()))) {
		count++;
		System.out.println(count + ": " + num);
	    }
	    
	    num = num.nextProbablePrime();
	}
	System.out.println("Ergebnis: " + count);
	
    }
    public static boolean isPrime(int n, int cert){
	BigInteger bigI = new BigInteger(n + "");
	if(bigI.isProbablePrime(cert)) return true;
	else return false;
    }
    public static int[] circular(int n){
	String sn = n + "";
	int[] out = new int[sn.length()];
	out[0] = n;
	for(int i = 1; i < sn.length(); i++){
	    out[i] = Integer.parseInt(new String(sn.substring(1) + sn.charAt(0)));
	    sn = out[i] + "";
//	    System.out.println(sn);
	}
	return out;
    }
    public static boolean isSearched(int n){
	int[] cir = circular(n);
	
	for(int i = 1; i < cir.length; i++){
	    if(!isPrime(cir[i],5)) return false;
	}
	return true;
    }
}
