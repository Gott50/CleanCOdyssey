package Problems;

public class P18_Maximum_path_sum_I {

    /**
     * @param args
     */
    public static void main(String[] args) {
	String In = "75 95 64 17 47 82 18 35 87 10 20 04 82 47 65 19 01 23 75 03 34 88 02 77 73 07 63 67 99 65 04 28 06 16 70 92 41 41 26 56 83 40 80 70 33 41 48 72 33 47 32 37 16 94 29 53 71 44 65 25 43 91 52 97 51 14 70 11 33 28 77 73 17 78 39 68 17 57 91 71 52 38 17 14 91 43 58 50 27 29 48 63 66 04 68 89 53 67 30 73 16 69 87 40 31 04 62 98 27 23 09 70 98 73 93 38 53 60 04 23";
	System.out.println(In);
	int range = 15;
	int[][] num = new int[range][range];
	
	for(int y = 0; y < range; y++){
	    for(int x = 0; x < range; x++){
		try{
		    if(y <= x){
			int n = x+1;
			int at = 3*(n*(n+1)/2-1-y);
//			System.out.println(at);
			num[y][x-y] = Integer.parseInt(Character.toString(In.charAt(at)) + Character.toString(In.charAt(at+1)));
//			System.out.println("y: "+y +" x: " + (x-y) + " = " + num[y][x-y]);
		    }
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
			
		}
	    }
	}
	
	int x = 0; int y = 0;
	long sum = 0;
	for(int step = 1; step <= range; step++){
	    System.out.println("y: "+y +" x: " + (x) + " = " + num[y][x]);
	    sum += num[y][x];
	    System.out.println("Summe: " + step + " = " +sum);
	    
	    
	    
	    
	    
	    int a = num[y+1][x] + num[y+2][x];
	    int b;
	    int c = num[y][x+1] +num[y][x+2];
	    
	    if(num[y+1][x] > num[y][x+1]) b = num[y+1][x] + num[y+1][x+1];
	    else b = num[y][x+1] + num[y+1][x+1];
	    
	    System.out.println("a: " + a + " b: " + b + " c: "+ c);
	    
	    if(a > b && a > c) y++;
	    else if(c > b) x++;
	    else if(num[y+1][x] > num[y][x+1]) y++;
	    else x++;
	    
	    /*
	    
	    if(num[y][x+2] < num[y+1][x+1] && num[y+1][x+1] > num[y+2][x]){
		if(num[y+1][x] > num[y][x+1]) y++;
	    else x++;
	    }else if(num[y+1][x] + num[y+2][x] > num[y][x+1] + num[y][x+2]) y++;
	    else x++;
	    
	    
	    
	    if(num[y+1][x] > num[y][x+1]) y++;
	    else x++;
	    */
	}
	
	
	

    }

}
