package Problems;

public class P1_Multipes_of_3_and_5 {
    /**
     * @param args
     */
    public static void main(String[] args) {
	       int sum = 0;
	       
	       System.out.println(sum);
	       
	for (int i = 0; i < 1000; i++) {
	   if (i % 3 == 0 || i % 5 == 0){
	
	       sum += i;
	       
	       System.out.println(sum);
	       
	   }
	    
	}

    }
    
}
