package problems;

public class P028_Number_spiral_diagonals {

    /**
     * @param args
     */
    public static void main(String[] args) {
	int large = 1001;
	
	long sum = 1;
	int n = 0;
	int d = 2;
	int c = 0;
	for(int i = 3; i <= large*large; i++){
	    if(n % d == 0){
		System.out.println("i: "+i + " d: " +d);
		sum += i;
		c++;
		if(c % 4 == 0) {
		    d +=2;
		    n = 0;
		}
	    }
	    n++;
	}
	System.out.println("Summe: " + sum);
    }

}
