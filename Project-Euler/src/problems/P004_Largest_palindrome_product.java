package problems;

import java.math.BigInteger;

public class P004_Largest_palindrome_product {

    /**
     * @param args
     */
    public static void main(String[] args) {
	
	String pal = "0";
	String palindrome = "0";
	
	for(int f1 = 100; f1 <= 999; f1++)
	    for(int f2 = 100; f2 <= 999; f2++){
		BigInteger I = new BigInteger(f1 * f2 +"");
//		System.out.println("I: " + I);
		
		String test = I.toString();
//		System.out.println("test: " + test);
		
		
		for(int t = 0; t < test.length(); t++){
		    if(test.charAt(t) == test.charAt(test.length()-1-t)){
			palindrome = test;
			continue;
		    }else{
			palindrome = "0";
			break;
		    }
		}
		
		if(palindrome != "0") {
		    if(Integer.parseInt(pal) < Integer.parseInt(palindrome)) {
			pal = palindrome;
			System.out.println("palindrome: " + pal);
		    }
		    
		}
		
	    }
	
    }

}
