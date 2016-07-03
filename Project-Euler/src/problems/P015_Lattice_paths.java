package problems;

public class P015_Lattice_paths {

    /**
     * @param args
     */
    public static void main(String[] args) {
	int range = 21;
	long [][] routes = new long[range][range];
	
	for(int x = 0; x < range; x++)
	    for(int y = 0; y < range; y++){
		if(x == 0 || y == 0) {
		    routes[y][x] = 1;
		    System.out.println("y: "+y +" x: " +x +" = " + routes[y][x]);
		}else{
		    routes[y][x] = routes[y-1][x] + routes[y][x-1];
		    System.out.println("y: "+y +" x: " +x +" = " + routes[y][x]);
		}
		
		
		
	    }

    }

}
