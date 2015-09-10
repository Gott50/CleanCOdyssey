package Problems;

public class P024_Lexicographic_permutations {

    private static int time = 0;
    private static int range = 1000000;
    private static String Out;
    /**
     * @param args
     */
    public static void main(String[] args) {
	int range = 6;
	
	char[] num = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
	
	int b1 = 1;
	int b2 = 2;
	int b3;
	    
	int counter = 0;
	for(int a = 0; counter < range; a++){
	    
	    for(int b = 0; b < 2; b++){
		System.out.print(num[a]);
		System.out.print(num[b1]);
		System.out.print(num[b2]);
		System.out.println();
		
		b3 = b1;
		b1 = b2;
		b2 = b3;
		
		counter++;
	    }
	    b1 = a;
	    b3 = b1;
	    b1 = b2;
	    b2 = b3;
	}
	
	
	String P = "0123456789";
	perm1(P);
	
	System.out.println("Out: "+Out);
	
	
	
	
    }
    // print N! permutation of the characters of the string s (in order)
        public  static void perm1(String s) { perm1("", s); }
        private static void perm1(String prefix, String s) {
            int N = s.length();
            if (N == 0) {
        	System.out.println(prefix);
        	if(time == range-1)
        	Out = prefix;
        	time++;
            }
            else {
                for (int i = 0; i < N; i++)
                   perm1(prefix + s.charAt(i), s.substring(0, i) + s.substring(i+1, N));
            }

        }
    
}
	