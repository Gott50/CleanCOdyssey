/**
 * 
 */
package Problems;

import java.util.ArrayList;

/*
 * P33.java 
 *
 * @author Gott50
 * 17.06.2014 
 * 
 * Ergebnis: 1/100
 */
public class P033_Digit_cancelling_fractions {

    /**
     * @param args
     */
    public static void main(String[] args) {
//	System.out.println(isKuerzen(49,98));
	

	ArrayList<int[]> bruch = examples(100);
	int[] product = {1,1};
	for(int i = 0; i < bruch.size(); i++){
	    product[0] *= bruch.get(i)[0];
	    product[1] *= bruch.get(i)[1];
	}
	
	
	int[] out = kuerzen(product[0],product[1]); 
	System.out.println("Ergebnis: " + out[0] + "/" + out[1]);

    }
    
    public static int[] find(int[] z, int[] n){
//	Gibt die Koordinaten der non-trivial zur K�rzende Elemente wieder, wenn nicht vorhanden {-1,-1}
	
	int[] back = {-1,-1};
	for(int i = 0; i < z.length; i++)
	    for(int j = 0; j < n.length; j++){
		if(z[i] == n[j]){
		    back[0] = i;
		    back[1] = j;
		}
	    }

	    return back;
    }
    public static int[] toArray(int i){
//	macht aus zahlen int[], jedes Element ist eine Ziffer
	String si = i + "";
	int[] digit = new int[si.length()];
	for(int a = 0; a < si.length(); a++){
	    digit[a] = Integer.parseInt(new String(si.charAt(a) + "")); 
	}
	return digit;
    }
    public static boolean isKuerzen(int z, int n){
//	pr�ft, ob non-trivial gek�rzt werden kann
	
	int[] pos = find(toArray(z), toArray(n));
	
	if(pos[0] == -1) return false;
	
	int z2 = zahlKuerzen(toArray(z), pos[0]);
	int n2 = zahlKuerzen(toArray(n), pos[1]);
	
	if((kuerzen(z,n)[0] == kuerzen(z2,n2)[0]) && (kuerzen(z,n)[1] == kuerzen(z2,n2)[1]))return true;
	else return false;
    }
    public static int zahlKuerzen(int[] z, int pos){
//	wandelt ein int[] wieder in eine Zahl um, aber l�sst die Ziffer auf pos aus
	String str = "";
	for(int i = 0; i < z.length; i++){
	    if(i != pos)
	    str = str + z[i];
	}
	if(!str.equals(""))
	return Integer.parseInt(str);
	else return 1;
    }
    public static ArrayList<int[]> examples(int range){
	ArrayList<int[]> bruch = new ArrayList<int[]>();
	for(int n = 1; n < range; n++)
	    for(int z = 1; z < n; z++){
		if(n % 10 != 0)
		if(isKuerzen(z,n)){
		    System.out.println(z + "/" + n);
		    int[] br = {z,n};
		    bruch.add(br);
		}
	    }
	return bruch;
    }
    public static int[] kuerzen(int zaehler, int nenner) {
	    int m, n, r;	//lokale Variablen
	    m = zaehler;
	    n = nenner;
	    r = m % n;
	    
	    while (r > 0) {
		m = n;
		n = r;
		r = m % n;
	    }
	    
	    zaehler /= n;	//in n steht jetzt der ggT
	    nenner /= n;
	    
	    int[] bruch = {zaehler, nenner};
	    return bruch;
	}
}
