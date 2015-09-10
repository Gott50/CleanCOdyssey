package Problems;

import java.math.BigInteger;

public class P26_Reciprocal_cycles {

    /**
     * @param args
     */
    public static void main(String[] args) {
	int range = 1000;
	
	int longest = 0;
	int count = 0;
	
	for(int d = 500; d < range; d+=2){
	System.out.println(d);
	if(10000000 % d == 0) continue;
    	BigInteger asd = new BigInteger("10").pow(2000).divide(new BigInteger(d +""));
//    	System.out.println(asd);
    	
    	int length = asd.toString().length() - 1;
    	int counter = 0;
    	String test = "";
    	boolean end = true;
        	for(int i = 0; end; i++){
        	    test = test + asd.toString().charAt(length-i) + "";
//        	    System.out.println("test: " +test);
        	    String ask = "";
        	    counter++;
        	    for(int t = 1; t <= i+1; t++){
        		ask =  ask + asd.toString().charAt(length-(i+t)) + "";
//        		System.out.println("ask: " +ask);
        		if(test.equals(ask)) {
        		    end = false;
        		    System.out.println("counter: " + counter);
        		    if(count < counter){
        			count = counter;
        			longest = d;
        		    }
        		    break;
        		}
        		
        	    }
        	    
        	}
	}
	System.out.println("Ergebniss: " +longest);
	
	
    }

}
