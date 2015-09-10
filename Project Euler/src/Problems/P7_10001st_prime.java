package Problems;

import java.math.BigInteger;

public class P7_10001st_prime {

    /**
     * @param args
     */
    public static void main(String[] args) {
	BigInteger Prim1 = new BigInteger("0");
	BigInteger Prim2 = new BigInteger("0");
	
	for(int i = 1; i <= 10001; i++){
	    Prim1 = Prim2.nextProbablePrime();
	    System.out.println(Prim1);
	    Prim2 = Prim1;
	}
	    
	
	

    }

}
