package Problems;

public class P030_Digit_fifth_powers {

    /**
     * @param args
     */
    public static void main(String[] args) {
	
	long answer = 0;
	
	for(long num = 2; num < 200000;num++){
	    
	    String NUM = num + "";
	    long sum = 0;
	    for(int i = 0; i < NUM.length(); i++){
		int digit = Integer.parseInt(NUM.charAt(i)+"");
//		System.out.println(digit);
		sum += (int) Math.pow(digit, 5);
	    }
	    System.out.println("Answer: "+answer + " num: " +num + " sum: "+sum );
	    if(sum == num){
		answer += num;
		
	    }
	    
	}
	System.out.println("Answer: "+answer);
    }

}
