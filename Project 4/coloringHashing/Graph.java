package coloringHashing;

public class Graph {
	private static boolean[][] edges;
	int[] colors;

	// the following constructor and 2 methods are from the slides in class
	public Graph(int n){
		edges = new boolean[n][n];
	}
	
	// pre: given integers, s &  t, are less then n, where n is the length of the rows and columns of the graph
	// post: adds an edge between vertex s and vertex t and adjusts the matrix accordingly
	public void addEdge(int s, int t){
		edges[s][t] = true;
		edges[t][s] = true;
	}
	
	// post: gets neighbors of vertex v;
	public int[] neighbors(int v){
		int count = 0;
		int[] answer;
		
		// determines how long answer has to be and how many neighbors
		for (int i = 0; i<edges.length; i++){
			if (edges[v][i]) count++;
		}
		
		// puts the neighbors in the answer array
		answer = new int[count];
		count = 0;
		for (int I = 0; I<edges.length; I++){
			if (edges[v][I]){
				answer[count] = I;
				count++;
			}
		}
		return answer;
	}
	
	// pre: k is a positive integer
	// post: will return true only if the graph can be colored by k colors or less
	public boolean kColorable(int k){
		int[] best = minColors();
		int max = 0;//max is the optimal number of colors
		for (int i = 0; i<best.length;i++){
			if(best[i]>max){
				max = best[i];
			}
		}
		printColors(best, max+1); // uses the method to print what vertices will be colored what color
		return k>=(max+1); //max + 1 to account for color 0
	}
	
	public void printColors(int[] a, int numColors){
		System.out.println("\n");
		System.out.println("Found a coloring with " + numColors + " colors:"); // the optimal number of colors
		for (int i=0; i<a.length; i++){
			System.out.println("Color vertex " + i + " the color " + a[i]);
		}
		System.out.println("\n");
	}
	
	// post: returns the optimal number of colors necessary to color a graph
	private int[] minColors(){
		int size = edges.length; // number of vertices
		int j = 0;
		int base = 0; // start with base 1 so 1 color
		colors = new int[size]; // will keep track of different color combinations
		for (int i = 0; i<size; i++){
			colors[i] = 0;
		}
		while (!isLegalColorable(colors) && base<size){
			base++; // try increasing the number of the colors
			while(!isLegalColorable(colors) && j<size){
				for (j = 0; j<size; j++){
					colors[j] = colors[j] + 1;
					if (colors[j] == base){ //make the element that has reached the base go back to 0
						colors[j] = 0;
					} else {
						break;
					}
				}
			}
			j = 0; // restart the counting with j = 0 so the second while loop can pass
		}
		return colors;
	}
	
	public boolean isLegalColorable(int[] colored){
		for(int i = 0; i<colored.length; i++){
			int[] attached = neighbors(i); // returns a list of vertices that are neighbors of vertex i
			for (int j = 0; j<attached.length; j++){
				if (colored[i] == colored[attached[j]]){// checks if vertex is the same color as a neighbor
					return false;
				}
			}
		}
		return true;
	}
	
	public void printEdges(){
		int size = edges.length;
		for (int i =0; i<size; i++){
			System.out.print("\t" + i + "  "); // prints the heading of the matrix
		}
		System.out.println();
		System.out.println("-----------------------------------");
		
		for (int i =0; i<size; i++){
			System.out.print(i + "|\t"); // prints the left column headings of the matrix
			for (int j = 0; j<size; j++){
				System.out.print(edges[i][j] + "\t");
			}
			System.out.println();
		}
	}
	
	/* The worst case lower bound is O(n) = n^n since this is a brute force method 
	 * and will go through n vertices with combinations for each run before it finds 
	 * that all vertices will have to be different colors.
	 * 
	 * In the graphs created below for experimentation, you can see that if all the vertices are connected,
	 * The number colors needed = number of vertices. If not all the vertices are connected, but edges do exist,
	 * then the minimal number of colors will have to be <= number of edges
	 * If no edges exist, then only 1 color is needed
	 */
	public static void main(String args[]){ // used to test out the code above
		
		// should need 4 colors because all edges are connected
		System.out.println("Building a graph, g");
	    Graph g = new Graph(4);
	    g.addEdge(1, 2);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(3, 1);
        g.addEdge(3, 2);
        g.addEdge(3, 0);
        g.printEdges();
        
        int kColors = 2;
        if(g.kColorable(kColors)) {
           System.out.println(kColors + " colors is enough");
        } else {
               System.out.println(kColors + " colors is not enough");
        }
        System.out.println();

        // should be able to color in 1 color since no edges
        System.out.println("Building a graph, h");
        Graph h = new Graph(1);
        h.printEdges();
        if(h.kColorable(kColors)) {
            System.out.println(kColors + " colors is enough");
         } else {
                System.out.println(kColors + " colors is not enough");
         }
        System.out.println();
        
        // should be able to color in 2 colors because only 2 edges are connected
        System.out.println("Building a graph, l");
        Graph l = new Graph(4);
        l.addEdge(1, 2);
        l.addEdge(2, 3);
        l.printEdges();
        if(l.kColorable(kColors)) {
            System.out.println(kColors + " colors is enough");
         } else {
                System.out.println(kColors + " colors is not enough");
         }
        System.out.println();
        
        // should require 3 colors because all the vertices are connected
        System.out.println("Building a graph, m");
        Graph m = new Graph(3);
        m.addEdge(1, 2);
        m.addEdge(0, 2);
        m.addEdge(0, 1);
        m.printEdges();
        if(m.kColorable(kColors)) {
            System.out.println(kColors + " colors is enough");
         } else {
                System.out.println(kColors + " colors is not enough");
         }
        System.out.println();

   }

}