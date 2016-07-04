/**
 * 
 */
package problems;

/*
 * P34.java 
 *
 * @author Gott50
 * 17.06.2014 
 * 
 * Ergebins: 40730
 */
public class P034_Digit_factorials {
    public static void main(String[] args) {
//	System.out.println(issearched(145));
	
	long sum = 0;
	for(int i = 9; i < 100000 ; i++){
	    if(issearched(i)){
		sum += i;
		System.out.println("num: " + i + " Sum: " + sum);
	    }
		
	}
	System.out.println("Ergebins: " + sum);
	
    }
    
    
    public static boolean issearched(int n){
	int[] num = toArray(n);
	if(num.length <= 1) return false;
	
	int sum = 0;
	for(int i = 0; i < num.length; i++){
	    sum += factorial(num[i]);
	} 
//	System.out.println("sum: " + sum);
	
	if(n == sum) return true;
	else return false;
    }
    public static int[] toArray(int i){
//	macht aus zahlen int[], jedes Element ist eine Ziffer
	String si = i + "";
	int[] digit = new int[si.length()];
	for(int a = 0; a < si.length(); a++){
	    digit[a] = Integer.parseInt(new String(si.charAt(a) + "")); 
	}
//	for(int a : digit)
//	    System.out.println(a);
	
	return digit;
    }
    public static int factorial(int n){
//	wandelt eine Zahl in eine Fakult�t um
	int fac = 1;
	for(int i = 1; i <= n; i++){
	    fac *= i;
	}
//	System.out.println("frac: " + fac);
	return fac;
    }
}
