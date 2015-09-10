package Problems;

public class P9_Special_Pythagorean_triplet {

    /**
     * @param args
     */
    public static void main(String[] args) {
	
	int range = 1000; 
	
	stop:
	for(int a = 0; a <= range ; a++){
	     for(int b = 0; b <= range; b++){
		 int c = (int) Math.sqrt((Math.pow(a, 2) + Math.pow(b, 2)));
		 
		 if(a < b && b < c){
		    
		    if(Math.pow(a, 2) + Math.pow(b, 2) == Math.pow(c, 2)) {
			System.out.println("a: " + a +" b: " + b + " c: " + c);
			
			if(a + b + c == 1000) {
			    System.out.println("a: " + a +" b: " + b + " c: " + c);
			    System.out.println("Prduct: " + (a*b*c));
			    break stop;
			}
		    }
		}
	     }
		
	}

    }

}
