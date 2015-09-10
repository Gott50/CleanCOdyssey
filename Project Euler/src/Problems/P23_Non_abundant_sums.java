package Problems;

import java.math.BigInteger;
import java.util.ArrayList;

public class P23_Non_abundant_sums {

    /**
     * @param args
     */
    public static void main(String[] args) {
	int range = 28123;   //28123
	
	
	ArrayList<Integer> abundant = new ArrayList<Integer>();
	
	int n = 0;
	for(int tri = 2; tri <= range; tri++){
	    int a = 0;
	    for (int q = 1; q <= tri/2; q++) {
//		System.out.println("q " +q);
//		else if(new BigInteger(tri + "").isProbablePrime(0)) break;
		if (tri % q == 0) {
		    a += q;
		}
	    }
	    if(a > tri){
		abundant.add(tri);
		System.out.println(tri);
		n++;
	    }
	}
	System.out.println("Last: " +abundant.get(n-1));
	
	int num1 = 0;
	long sum = 0;
	for(int num = 1; num <= range; num++){    //range
	    num1 = num;
	    Bre:
	    for(int a = 0; num > abundant.get(a); a++){
		for(int b = a; num > abundant.get(b); b++){
		    int test = abundant.get(a)+abundant.get(b);
//		    System.out.println("Test: " + num +" mit "+ test);
		    if(num == test) {
			System.out.print("Warning! ");
			sum -= num;
			break Bre;
		    }
		}
	    }
	    sum += num;
	    System.out.println(num1);
	    System.out.println("Summe: "+sum);
	}
	
	
	
	

    }

}
