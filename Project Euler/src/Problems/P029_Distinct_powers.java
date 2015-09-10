package Problems;

import java.math.BigInteger;
import java.util.ArrayList;

public class P029_Distinct_powers {

    /**
     * @param args
     */
    public static void main(String[] args) {
	
	int von = 2, bis = 100;
	
	ArrayList<BigInteger> List = new ArrayList<BigInteger>();
//	System.out.println(List.size());
	for(int a = von; a <= bis; a++)
	    for(int b = von; b <= bis; b++){
		BigInteger I = new BigInteger(a +"").pow(b);
		System.out.println("a: "+a +" b: " +b +" I: " +I);
		boolean temp = true;
		if(List.size() != 0)
		for(int i = 0; i < List.size(); i++){
		    if(I.equals(List.get(i))) {
			temp = false;
			break;
		    }
		}
		if(temp){
		    List.add(I);
		}
	    }
	System.out.println("Elements: " + List.size());
    }

}
