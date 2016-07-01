package Problems;

import java.math.BigInteger;

@SuppressWarnings("unused")
public class P021_Amicable_numbers {

    /**
     * @param args
     */
    public static void main(String[] args) {
	int range = 10000; 
	
	int[] amicable = {220, 284, 1184, 1210, 2620, 2924, 5020, 5564, 6232, 6368, 10744, 10856, 12285, 14595, 17296, 18416, 63020, 66928, 66992, 67095, 69615, 71145, 76084, 79750, 87633, 88730, 100485, 122265, 122368, 123152, 124155, 139815, 141664, 142310};
	long summe = 0;
	for(int i = 0; amicable[i] <= range; i++){
	    summe += amicable[i];
	    System.out.println("Summe: "+ summe);
	    
	}
	
	long sum1 = 0;
	
	for(int tri = 2; tri <= range; tri++){
	    int a = 0;
	    int b = 0;
	    
	    for (int q = 1; q <= tri/2; q++) {
		// System.out.println("q " +q);
		if (tri % 2 != 0) break;
//		else if(new BigInteger(tri + "").isProbablePrime(1)) break;
		if (tri % q == 0) {
		    b += q;
		    
		}
	    }
//	    System.out.println("tri: " + tri +" b: "+b);
	    
	    for (int q = 1; q <= b/2; q++) {
		// System.out.println("q " +q);
		if (b % 2 != 0) break;
//		else if(new BigInteger(tri + "").isProbablePrime(1)) break;
		if (b % q == 0) {
		    a += q;
		    
		}
	    }
//	    System.out.println("tri: " + tri +" a: "+a);
	    
	    
	    if(tri == a && a != b){
		System.out.println("a: "+a +" b: "+ b);
		
		
		sum1 += tri;
		System.out.println("Summe: "+sum1);
		
		
	    }
	    
	    
	}
	
	/*
	
	for(int n = 1; unter <= range; n++){
	    int x = (int) Math.pow(2, n) * 3 -1;
	    if(new BigInteger(x + "").isProbablePrime(0)){
		int y = (int) Math.pow(2,n-1) * 3-1;
		if(new BigInteger(y + "").isProbablePrime(0)){
		    int z = (int) Math.pow(2, 2*n -1) * 9-1;
		    if(new BigInteger(x + "").isProbablePrime(0)){
			System.out.println("x: " + x + " y: " + y + " z: " + z);
			
			int a = (int) Math.pow(2, n)*x*y;
			int b = (int) Math.pow(2, n)*z;
			System.out.println("a: "+a +" b: "+ b);
			
			
			if(a > b) unter = a;
			else unter = b;
			if(unter >= range) break;
			
			sum += a+b;
			System.out.println("Summe: "+sum);
		    }
		}
	    }
	    
	}
	 */

    }

}
