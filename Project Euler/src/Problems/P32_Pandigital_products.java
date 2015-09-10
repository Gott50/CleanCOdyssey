/**
 * 
 */
package Problems;

import java.util.ArrayList;

/*
 * P32_Pandigital_products.java 
 *
 * @author Timo Morawitz
 * 15.06.2014 
 */
public class P32_Pandigital_products {

    private int i;
    /**
     * @param args
     */
    public static void main(String[] args) {
	
	long sum = 0;
	ArrayList<Integer> pandigital = new ArrayList<Integer>();
	
	for(int i = 1; i < 10000; i++){
	    for(int j = 1; j < 10000; j++){
		if(isPandigital(i,j)){
		    if(!pandigital.contains(i*j)){
			pandigital.add(i*j);
			System.out.println("PD: " + i*j);
		    }
		    
		}
		    
	    }
	}
	
	for(int a : pandigital)
	    sum += a;
	
	System.out.println("Ergebniss: " + sum);
	
	
	
//	System.out.println(isPandigital(39,186));
	

	
    }
    
    public static boolean isPandigital(int i, int j){
	String all = i + "" + j + "" + (i*j);
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
	
	for(int x = 1; x <= 9; x++){ 	// ZŠhlt von 1 - 9
	    int counter = 0;
	    for(int y = 0; y < 9; y++){	//Geht durch digit
//		System.out.println(digit[y]);
		if(digit[y] == 0) return false;
//		System.out.println("EnthŠlt keine 0");
		if(digit[y] == x) {
		    counter++;
		    if(counter > 1) return false;
		}
	    }
	}
	
	return true;
    }

}



