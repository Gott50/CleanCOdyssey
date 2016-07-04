package problems;

public class P003_Largest_prime_factor {

    /**
     * @param args
     */
    public static void main(String[] args) {
	
	long prime = 600851475143l;
	int[] num1 = new int [100];
	int a = 0;
	long largest = 10000000000000l;
	
	for(int i = 2; i < prime; i++){
	    if(prime % i == 0) {
		num1[a] = i;
		System.out.println("factor:" + num1[a]);
		if (num1[a] < 0) break;
		a++;
	    }
	    
	}
	
	
	for(int i = 0; i < a; i++) {
	    
	    for(int p = 0; p < a; p++){
		if((num1[i] % num1[p] == 0) && (num1[i] != num1[p])) {
		    
		    if(largest > num1[i-1] && num1[i-1] != 0)largest=num1[i-1];
		    
		    break;
		}
	    }
	    
	}
	System.out.println("largest Prim: " + largest);
	
	
    }

}
