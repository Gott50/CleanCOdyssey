/**
 * 
 */
package problems;

import java.math.BigInteger;
import java.util.ArrayList;

/*
 * P41.java 
 *
 * @author Gott50
 * 15.07.2014 
 * 
 * Answer: 7652413
 */
public class P041_Pandigital_multiples {

    /**
     * @param args
     */
    public static void main(String[] args) {
	System.out.println(isPrime("7"));
	System.out.println("Answer: " +getAnswer());
	//Answer: 7652413

    }
    public static ArrayList<String> list = new ArrayList<String>();
    

  /*  public static void genNum() {
	BigInteger prime;
	BigInteger i = new BigInteger("970000000");
	while (true) {
	    prime = i.nextProbablePrime();
	    i = prime;
	    System.out.println("Test: " + prime);
	    if (isPandigital(prime.toString())) {
		System.out.println(prime);
		break;
	    }
	}

    }

    public static boolean isPandigital(String all) {
	// System.out.println(all);

	if (all.length() != 9)
	    return false;
	// System.out.println("9 Ziffern");

	int[] digit = new int[9];
	int qsum = 0;
	for (int a = 0; a < 9; a++) {
	    digit[a] = Integer.parseInt(new String(all.charAt(a) + ""));
	    qsum += digit[a];
	}
	if (qsum != 45)
	    return false; // die Zahlen 1 - 9 addiert ergeben 45
	    // System.out.println("QSumme: " + qsum);

	for (int x = 1; x <= 9; x++) { // Z�hlt von 1 - 9
	    int counter = 0;
	    for (int y = 0; y < 9; y++) { // Geht durch digit
	    // System.out.println(digit[y]);
		if (digit[y] == 0)
		    return false;
		// System.out.println("Enth�lt keine 0");
		if (digit[y] == x) {
		    counter++;
		    if (counter > 1)
			return false;
		}
	    }
	}

	return true;
    } */

    public static boolean isPrime(String str) {
	BigInteger bin1 = new BigInteger(str);
	return bin1.isProbablePrime(5);
    }

    // print N! permutation of the characters of the string s (in order)
    public static void perm1(String s) {
	perm1("", s);
    }

    private static void perm1(String prefix, String s) {
	int N = s.length();
	if (N == 0) {
//	    System.out.println(prefix);
	    list.add(prefix);
	} else {
	    for (int i = 0; i < N; i++)
		perm1(prefix + s.charAt(i),
			s.substring(0, i) + s.substring(i + 1, N));
	}

    }

    public static String getHigestPanPrime(ArrayList<String> list){
	String out = "";
	for(String str: list)
	    if(isPrime(str)){
		out = str;
		System.out.println(str);
	    }
	return out;
    }
    
    public static String getAnswer(){
	perm1("1234567");
	return(getHigestPanPrime(list));
    }

}

