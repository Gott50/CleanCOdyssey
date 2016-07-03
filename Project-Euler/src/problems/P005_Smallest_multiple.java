package problems;

public class P005_Smallest_multiple {

    /**
     * @param args
     */
    public static void main(String[] args) {

	for(int i = 2; i > 1 ; i+=2){
	   int counter = 0;
	   for(int q = 1; q <= 20; q++){
	       if(i % q != 0)break;
	       else{
//		   System.out.println(i);
		   counter++;
	       }
	   }
	   if(counter == 20){
	       System.out.println("ergebniss: " +i);
	       break;
	   }
	}

    }

}
