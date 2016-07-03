package problems;

public class P031_Coin_sums {

    /**
     * @param args
     */
    public static void main(String[] args) {
	
	int Zsum = 200; 
	int sum;
	int count = 0;
	for(int pound2 = 0; pound2 <= Zsum; pound2+=200){
	    for(int pound1 = 0; pound1 <= Zsum; pound1+=100)
		for(int p50 = 0; p50 <= Zsum; p50+=50)
		    for(int p20 = 0; p20 <= Zsum; p20+=20)
			for(int p10 = 0; p10 <= Zsum; p10+=10)
			    for(int p5 = 0; p5 <= Zsum; p5+=5)
				for(int p2 = 0; p2 <= Zsum; p2+=2)
				    for(int p1 = 0; p1 <= Zsum; p1+=1){
					sum = pound2+pound1+p50+p20+p10+p5+p2+p1;
					if(sum > Zsum) break;
					if(sum == Zsum) {
					    count++;
					    System.out.println("Count: " + count);
					}
				    }
	}
	System.out.println("Ergebniss: " + count);
    }

}
