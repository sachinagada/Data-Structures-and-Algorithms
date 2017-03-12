package coloringHashing;

public class HashTable {
	Queue[] a;
	final int tableSize = 31;
	boolean[] colored = new boolean[20];
	int counter2 = 1;
	
	public HashTable(){
		a = new Queue[tableSize];
		for (int i = 0; i<tableSize; i++){
			a[i] = new Queue();
		}
	}

	// pre: a string of 6 characters is given
	// post: a hash function is used to determine where the string will go in the hash table
	public static int hash(String x){
		final int tableSize = 31;
		int sum = 0;
		int h = 0;
		for (int i = 0; i <6; i++){
			char c = x.charAt(i);
			int ascii = (int) c;
			sum += ascii ;
			h = sum % tableSize;
		}
		return h;
	}

	// pre: the size and distinctClasses have to be positive integers
	// post: builds the graph as an adjacent matrix given the the number of distinct classes and a list a that 
	public int[][] buildGraph(String [] a, int distinctClasses, int size){
		int [][] adjMatrix = new int[distinctClasses][distinctClasses];
		String[] connections = new String[5]; // will hold the name of the classes
		int counter = 0;
		for (int i = 0; i< size; i++){
			if (a[i].contains(",") || a[i].length()<2){ // if student name or number, then erase the connections string array
				for (int l = 0; l<5; l++){
					connections[l] = null;
					counter = 0;
				}
			} else {
				connections[counter] = a[i];
				counter++;
			}
			
			for (int j = 0; j<5; j++){ // this will keep track of the vertex
				// uses the name of the course to get its number/vertex 
				if (connections[j] != null){
					int number = hash(connections[j]);
					Queue s = this.a[number];
					int vertex = s.getIndex(connections[j]);
					// determines the neighbors(classes the same student is taking) and add as edge between the vertices
					for (int k = 0; k<5; k++){ // will keep track of the neighbors
						if (j != k && connections[k]!= null){
							int number2 = hash(connections[k]);
							Queue t = this.a[number2];
							int neighbor = t.getIndex(connections[k]);
							adjMatrix[vertex][neighbor] = 1;
						}
					}
				}
			}
		}
		return adjMatrix;
	}
	
	public void printGraph (int[][] Graph){
		System.out.println("\n");
		for (int i = 0; i< Graph.length; i++){ // prints the heading that represents the vertex
			System.out.print("\t" + i + "  ");
		}
		
		System.out.println("\n"); // prints a blank line to differentiate the heading and matrix
		System.out.println("----------------------------------");
		for (int i = 0; i<Graph.length; i++){
			System.out.print(i + "|\t");
			for (int j = 0; j<Graph.length; j++){

				System.out.print(Graph[i][j] + "\t");
			}
			System.out.println();
		}
	}
	
	// post: returns true only if all the vertices in the graph are colored
	public boolean isColored(int size){
		for (int i = 0; i<size; i++){
			if (! colored[i]){
				return false;
			}
		}
		return true;
		
	}
	
	// pre:distinctCourses has to be a postive integer
	// post: returns a final exam schedule such that no student has overlapping exams. 
	public void greedyAlgorithm(int[][] Graph, int distinctCourses, String[] courses){
		System.out.println();
		while(!isColored(distinctCourses)){
			greedyAlgorithmHelper(Graph, distinctCourses, courses);
			counter2++;
		}
	}
	
	// post: does the actual printing of the final exam schedule and runs the greedy algorithm
	public void greedyAlgorithmHelper(int[][] Graph, int distinctCourses, String[] courses){
		boolean found;
		int v,w;
		int[] newclr = new int[distinctCourses]; // set elements to -1 initially in the for loop
		for (int k = 0; k<distinctCourses; k++){
			newclr[k] = -1;
		}
		for (int i =0; i<distinctCourses; i++){// determines first uncolored vertex in G
			if (colored[i] == false){
				v= i;
				found = false;
				for (int j = 0; j<distinctCourses; j++){ //determines first vertex in newclr 
					if (newclr[j] != -1){
						w = j;
						if (Graph[v][w] ==1){
							found = true;
						}
					}
				}
				if (found == false){
					colored[v] = true; 
					newclr[v] = 1; // the vertex that can be colored the same color
				}
			}
		}
		System.out.println("Final Exam Period " + counter2);
		for (int i =0; i<distinctCourses; i++){
			if (newclr[i] != -1){
				System.out.println("\t" + courses[i]);
			}
		}
		System.out.println();
	}
}
