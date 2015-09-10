package Problems;

public class P2_Even_Fibonacci_numbers {

    /**
     * @param args
     */
    public static void main(String[] args) {
	
	int fib = 1, i = 2, sum = 0;
	int[] num = new int [10000];
	num [0]=1;
	num [1]=2;
	
	while(fib < 4000000) {
	    System.out.println(fib);
	    
	    
	    
	    num[i] = num [i-1] + num [i-2];
	    
	    fib=num[i];
	    
	    i++;
	    
	}
	
	for (int a = 0; a < 10000; a++){
	    if(num[a] % 2 == 0) sum += num [a];
	}
	
	System.out.println("Summe:" + sum);
	
    }

}
