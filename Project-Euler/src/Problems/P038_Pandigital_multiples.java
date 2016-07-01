/**
 * 
 */
package Problems;

/*
 * P38.java 
 *
 * @author Gott50
 * 18.06.2014 
 * 
 * Ergebnis: 932718654
 */
public class P038_Pandigital_multiples {
    public static int largest = 0;
    
    public static void main(String[] args) {
	System.out.println(isSearched(192));
	
	
	
	
	for(int i = 1; i < 9999; i++)
	    if(isSearched(i)){
		System.out.println(i);
		
	    }
	
	System.out.println("Ergebnis: " + largest);
	
	
    }
    
    public static String product(int n, int f){
	return (n*f) + "";
    }
    
    public static boolean isSearched(int n){
	String str = "";
	
	int count = 0;
	for(int i = 1; str.length() < 9; i++){
	    str = str + product(n , i);
//	    System.out.println(str);
	    count++;
	}
	if(count < 2) return false;
	
	if(isPandigital(str)){
	    if(largest < Integer.parseInt(str)) largest = Integer.parseInt(str);
	    return true;
	}
	else return false;
	
    }
    
    public static boolean isPandigital(String all){
//	System.out.println(all);
	
	if(all.length() != 9) return false;
//	System.out.println("9 Ziffern");
	
	int[] digit = new int[9];
	int qsum = 0;
	for(int a = 0; a < 9; a++){
	    digit[a] = Integer.parseInt(new String(all.charAt(a) + "")); 
	    qsum += digit[a];
	}
	if (qsum != 45) return false;		//die Zahlen 1 - 9 addiert ergeben 45
//	System.out.println("QSumme: " + qsum);
	
	for(int x = 1; x <= 9; x++){ 	// Z�hlt von 1 - 9
	    int counter = 0;
	    for(int y = 0; y < 9; y++){	//Geht durch digit
//		System.out.println(digit[y]);
		if(digit[y] == 0) return false;
//		System.out.println("Enth�lt keine 0");
		if(digit[y] == x) {
		    counter++;
		    if(counter > 1) return false;
		}
	    }
	}
	
	return true;
    }
}
