/**
 * 
 */
package problems;

import java.math.BigInteger;

/*
 * P37.java 
 *
 * @author Gott50
 * 18.06.2014 
 * 
 * Ergebnis: 748317
 */
public class P037_Truncatable_primes {

    /**
     * @param args
     */
    public static void main(String[] args) {
//	System.out.println(isSearched(3797));

	long sum = 0;
	for(int i = 9, c = 0; c < 11; i++)
	    if(isSearched(i)){
		c++;
		sum += i;
		System.out.println(c + ": " +i);
	    }
	System.out.println("Ergebnis: " + sum);    
	
	
	
	
	
    }
    public static boolean isPrime(int n, int cert){
	BigInteger bigI = new BigInteger(n + "");
	if(bigI.isProbablePrime(cert)) return true;
	else return false;
    }
    
    public static int cutLeft(int n){
	String sn = n + "";
	
	return Integer.parseInt(sn.substring(1));
    }
    
    public static int cutRight(int n){
	String sn = n + "";
	
	return Integer.parseInt(sn.substring(0, sn.length()-1));
    }

    public static boolean isSearched(int n){
	String sn = n + "";
	int nl = n, nr = n;
	
	if(!isPrime(n,10)) return false;
	
	for(int i = 1; i < sn.length(); i++){
	    nl = cutLeft(nl);
	    nr = cutRight(nr);
	    
	    if(!isPrime(nl, 10)) return false;
	    if(!isPrime(nr, 10)) return false;
	}
	return true;
    }
}
