package coloringHashing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class graphColoring {
	static int size = 0; // the number of elements in the document when the string is split with space
	static int totalCourses;
    static String [] a = new String[280];
    static String [] courses = new String[20]; // the distinct courses located at index which related to their given number
	
	// 280 because there can be a max of 40 students and each can take 5 classes max. 
	// So with a slot for name, number of classes, and the classes, you need 7*40 = 280 slots 


    // pre: change the file name to the place where the file exists and is in the specified format
    // post: prints out a table of classes and their assigned number, graph represented as a matrix, and final exam schedule such
    // that no student has overlapping exams. 
	public static void main(String args[]) {
		// the try and catch was given in the assignment document
		try{
			FileReader in = new FileReader("/Users/Sachi/Desktop/StudentSchedules2.txt");
			BufferedReader br = new BufferedReader(in);
            String line;
            line = br.readLine();
            while(line != null){
            	processLine(line);
            	line = br.readLine();
            }               
        }
        catch(IOException e) {
                System.out.println("IO Exception");
        }
		
		HashTable h = makeTable(a); // returns an array of linked lists with the hashed course names
		int[][] graph = h.buildGraph(a, totalCourses, size); // makes the matrix representation of a graph
		h.printGraph(graph);
		h.greedyAlgorithm(graph, totalCourses, courses);// determines the final exam schedule and prints it

    }

	// post: takes each entry from the document separated by space and makes it into a list of elements
	public static void processLine(String line) {
		StringTokenizer st;
	     
	    // using space as delimiter to separate all the values
	    st = new StringTokenizer(line, " "); 

	    while (st.hasMoreTokens()) {
	    	String next = st.nextToken();
	    	if (!next.equals(null)){
	    		a[size] = next;
	    		size++;
	    	}
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
	
	// pre: give a string array that contains names, number of classes the students are taking, and the class names
	// post: returns a table of the numbers that correspond to class names (0:MAT101, 1: CHE202 etc). 
	public static HashTable makeTable(String [] l){
		HashTable h = new HashTable();
		int distinctCourse = 0;
		for (int i =0; i<size; i++){
			// Only string which is the course name will go through if statement
			if (!a[i].contains(",") && a[i].length()>1){
				String name = a[i]; //course name
				int number = hash(name); // gives back the hash value of the string
				// checks to see if the queue of courses already has this course
				Queue s = h.a[number];
				if (!s.seen(name)){
					s.insert(name, distinctCourse);// adds the course to queue if not seen
					courses[distinctCourse] = name;
					distinctCourse ++;
				}
			} 
		}
		// prints the table of courses and the associated number with it
		for (int i = 0; i<distinctCourse; i++){
			System.out.print(i + "   ");
			System.out.println(courses[i]);
		}
		totalCourses = distinctCourse;
		return h;

	}
}
	

