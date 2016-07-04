package problems;

public class P019_Counting_Sundays {

    /**
     * @param args
     */
    public static void main(String[] args) {
	int countat = 1901;
	int von = 1900;		//1. Jan
	int bis = 2000;		//31. Dec.
	
	int[] monat = new int[12];
	int day = 1;
	long sum = 0;
	
	for(int jahr = von; jahr <= bis; jahr++){
	    System.out.println(jahr);
	    int m = 1;
        	for(int i = 0; i < 12; i++){
        	    if(m == 8) m++;
        	    if(m % 2 != 0) monat[i] = 31;
        	    else monat[i] = 30;
                	    if (m == 2){
                		if((jahr % 4 == 0 && jahr % 100 != 0) || jahr % 400 == 0) monat[i] = 29;
                		else monat[i] = 28;
                	    }
        	    System.out.println("monat"+ (i+1) + " : " + monat[i]);
        	    
        	    for(int a = 1; a <= monat[i]; a++){
        		if(a == 1)
        		if(day % 7 == 0 && jahr >= countat) {
        		    sum++;
        		    System.out.println(sum);
        		}
        		day++;
        	    }
        	    m++;
        	} 
	    
	    
	    
	    
	}
	
	
	
	
	

    }

}
