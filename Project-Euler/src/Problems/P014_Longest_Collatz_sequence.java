package Problems;

public class P014_Longest_Collatz_sequence {

    /**
     * @param args
     */
    public static void main(String[] args) {
	int bereich = 1000000;
	int count = 0;
	int verg = 0, ergebniss = 0;
	
	
	for(int i = 1; i <= bereich; i++){
	    long n = i;
	    count = 0;
	    while(n > 1){
//		System.out.println(n);
		if(n % 2 == 0) n = n / 2;
		else n = n * 3 +1;
		count++;
	    }
//	    System.out.println(n);
	    System.out.println("Das war: " + i + " mit " + (count + 1));
	    
	    if(verg <= count) {
		verg = count;
		ergebniss = i;
	    }
	    
	}
	System.out.println("Das Ergebniss ist: " + ergebniss + " mit " + (verg + 1));
	

    }

}
