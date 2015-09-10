package Problems;

public class P017_Number_letter_counts {

    /**
     * @param args
     */
    public static void main(String[] args) {
	int digits = 4;
	
	int[] digit = new int [digits];
	long sum = 0;
	
	for(int i = 1; i <= 1000; i++){
	    System.out.println(i);
	    String Zahl = "";
	    int n = i;
	    for(int d = 0; i >= Math.pow(10, d); d++){
		digit[d] = (int) (n % Math.pow(10, d+1) / Math.pow(10, d));
		n -= digit[d];
//		System.out.println("Zahl at " +d+ " = " + digit[d]);
	    }
	    
	    if (i % 100 >= 10 && i % 100 < 20)
		switch(digit[0]){
        	    case 0: Zahl = Zahl + new String("ten");
        	    break;
        	    case 1: Zahl = Zahl + new String("eleven");
        	    break;
        	    case 2: Zahl = Zahl + new String("twelve");
        	    break;
        	    case 3: Zahl = Zahl + new String("thirteen");
        	    break;
        	    case 4: Zahl = Zahl + new String("fourteen");
        	    break;
        	    case 5: Zahl = Zahl + new String("fifteen");
        	    break;
        	    case 6: Zahl = Zahl + new String("sixteen");
        	    break;
        	    case 7: Zahl = Zahl + new String("seventeen");
        	    break;
        	    case 8: Zahl = Zahl + new String("eighteen");
        	    break;
        	    case 9: Zahl = Zahl + new String("nineteen");
        	    break;
        	}else
        	if (i % 10 > 0)
		switch(digit[0]){
        	    case 1: Zahl = Zahl + new String("one");
        	    break;
        	    case 2: Zahl = Zahl + new String("two");
        	    break;
        	    case 3: Zahl = Zahl + new String("three");
        	    break;
        	    case 4: Zahl = Zahl + new String("four");
        	    break;
        	    case 5: Zahl = Zahl + new String("five");
        	    break;
        	    case 6: Zahl = Zahl + new String("six");
        	    break;
        	    case 7: Zahl = Zahl + new String("seven");
        	    break;
        	    case 8: Zahl = Zahl + new String("eight");
        	    break;
        	    case 9: Zahl = Zahl + new String("nine");
        	    break;
        	}
	    if (i % 100 > 19)
		switch(digit[1]){
        	    case 1: Zahl =  new String("ten") + Zahl;
        	    break;
        	    case 2: Zahl =  new String("twenty") + Zahl;
        	    break;
        	    case 3: Zahl =  new String("thirty") + Zahl;
        	    break;
        	    case 4: Zahl =  new String("forty") + Zahl;
        	    break;
        	    case 5: Zahl =  new String("fifty") + Zahl;
        	    break;
        	    case 6: Zahl =  new String("sixty") + Zahl;
        	    break;
        	    case 7: Zahl =  new String("seventy") + Zahl;
        	    break;
        	    case 8: Zahl =  new String("eighty") + Zahl;
        	    break;
        	    case 9: Zahl =  new String("ninety") + Zahl;
        	    break;
		}
	    if (i % 1000 >= 100 ){
		if(i % 100 != 0) Zahl = new String("and") + Zahl;
		Zahl = new String("hundred") + Zahl;
		
		switch(digit[2]){
	       	    case 1: Zahl = new String("one") + Zahl;
	       	    break;
	       	    case 2: Zahl = new String("two") + Zahl;
	       	    break;
	       	    case 3: Zahl = new String("three") + Zahl;
	       	    break;
	       	    case 4: Zahl = new String("four") + Zahl;
	       	    break;
	       	    case 5: Zahl = new String("five") + Zahl;
	       	    break;
	       	    case 6: Zahl = new String("six") + Zahl;
	       	    break;
	       	    case 7: Zahl = new String("seven") + Zahl;
	       	    break;
	       	    case 8: Zahl = new String("eight") + Zahl;
	       	    break;
        	    case 9: Zahl = new String("nine") + Zahl;	        	    
        	    break;
	        }
	    }
	    if (i % 10000 >= 1000 ){
		if(i % 1000 != 0) Zahl = new String("and") + Zahl;
		Zahl = new String("thousand") + Zahl;
		
		switch(digit[3]){
	       	    case 1: Zahl = new String("one") + Zahl;
	       	    break;
	       	    case 2: Zahl = new String("two") + Zahl;
	       	    break;
	       	    case 3: Zahl = new String("three") + Zahl;
	       	    break;
	       	    case 4: Zahl = new String("four") + Zahl;
	       	    break;
	       	    case 5: Zahl = new String("five") + Zahl;
	       	    break;
	       	    case 6: Zahl = new String("six") + Zahl;
	       	    break;
	       	    case 7: Zahl = new String("seven") + Zahl;
	       	    break;
	       	    case 8: Zahl = new String("eight") + Zahl;
	       	    break;
        	    case 9: Zahl = new String("nine") + Zahl;	        	    
        	    break;
	        }
	    }
	    System.out.println(Zahl);
	    sum += Zahl.length();
	    System.out.println("Summe: " +sum);
	}

    }

}
