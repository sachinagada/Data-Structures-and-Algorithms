package bstproject;

import java.io.FileWriter;
import java.io.Writer;

public class CrimeRecordList {
	
	int size = 0;
	CrimeRecordListNode ptr;
	CrimeRecordListNode front;
	CrimeRecordListNode rear;
	
	public CrimeRecordList(){
		
	}
	
	public int getSize(){
		return size;
	}
	
	// post: a crime record is added at the end of the list
	public void add(CrimeRecord cr){
		CrimeRecordListNode newOne = new CrimeRecordListNode(cr, null);
		if (size<1){
			front = newOne; // initializes the front pointer
			rear = newOne; // when only one node is there, the front and end point to the same node
		} else { // more than 1 node in list
			rear.setNext(newOne); 
			rear = newOne; // sets the rear pointer to the newly added node
		}
		size +=1; 
	}

	// post: the pointer is set to the head of the list
	public void start(){
		ptr = front;
	}
	
	// post: returns false only if pointer is null
	public boolean hasNext(){
		// looks at what the node's next data is. If it's null then returns false for hasNext
		return (ptr != null && ptr.getData() != null); 
	}
	
	// post: returns the current value of the ptr and sets the ptr to the next node
	public CrimeRecord next(){
		CrimeRecord currentNode = ptr.getData(); // CrimeRecord pointed to by ptr
		if (currentNode == null){ // no more elements in the list
			return null;
		} else { // there are elements in the list
			ptr = ptr.getNext();
			return currentNode;
		}
	}
	
	public String toKml(){ // insert a loop for several crimes. Only after the KML
		start();
		String s= ""; // the following string is taken from the project description
		s+= "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n"; // \n lets it go to a new line
		s += "<kml xmlns=\"http://earth.google.com/kml/2.2\">\n" + "<Document>\n" + "<Style id=\"style1\">\n";
		s += "<IconStyle>\n" + "<Icon> \n" + "<href>http://maps.gstatic.com/intl/en_ALL/mapfiles/ms/micons/blue-dot.png</href> \n";
		s += "</Icon>\n" + "</IconStyle>\n" + "</Style>\n";
		while (hasNext()){ // if a query has multiple crimes
			
			s += "<Placemark> \n" + "<name>" + ptr.getData().getOffense() + "</name>\n";
			s += "<description>" + ptr.getData().getStreet() + "</description>\n" + "<styleUrl>#style1</styleUrl>\n" + "<Point>\n";
			s += "<coordinates>" + Double.toString(ptr.getData().getLon()) + "," + Double.toString(ptr.getData().getLat()) + ",0.000000" +"</coordinates>\n";
			s += "</Point>\n" + "</Placemark>\n";
			ptr = ptr.getNext();
		}
		
		s += "</Document>\n" + "</kml>";
		return s;
	}
	
	// pre: pathToFile is the entire path to the file
	// post: generates a KML document to the user selected file name and exception is thrown when file not found
	public void toGoogleEarth(String pathToFile) throws java.io.IOException{

		pathToFile += ".kml";
		Writer writer = new FileWriter(pathToFile);
		writer.write(toKml());
		writer.close();
	}

	// post: All of the data in b is added to the end of a. The new list is returned. The lists a and b are left unchanged. 
	public static CrimeRecordList concat(CrimeRecordList a, CrimeRecordList b){
		CrimeRecordList c = new CrimeRecordList();
		if (a != null){
			a.start();
			while(a.hasNext()){
				// a.next will add the data from current node to c and have the pointer go to the next node so a is switching
				c.add(a.next()); // adds each element from a into c. Run time analysis of O(N)
			}
		}
		if (b != null){
			b.start();
		
			while(b.hasNext()){		// similar to before but adding elements of b into c. Run time analysis of O(N)
				c.add(b.next()); // rest is similar as before
			}
		}
		return c;
	}

}
