package problems;

import java.math.BigInteger;

public class P010_Summation_of_primes {

    /**
     * @param args
     */
    public static void main(String[] args) {
	int range = 2000000;
	
	BigInteger Prim1 = new BigInteger("0");
	BigInteger Prim2 = new BigInteger("0");
	BigInteger Sum = new BigInteger("0");
	
	for(int i = 1; i <= range; i++){
	    Sum = Sum.add(Prim1);
	    
	    Prim1 = Prim2.nextProbablePrime();
	    System.out.println(Prim1);
	    Prim2 = Prim1;
	    
	    if(Prim1.compareTo(new BigInteger(range + "")) != -1) {
		System.out.println("Summe:" +Sum);
		break;
	    }
	}

    }

}
