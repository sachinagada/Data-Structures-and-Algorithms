package coloringHashing;

public class Queue {
	Node front;
	Node rear;
	int size =0; //keeps track of the number of items in the queue
	
	public Queue(){

	}

	//precondition: the queue is not empty
	// postcondition: let's you know if the class name is in the queue

	public boolean seen(String name){
		Node temp  = front;
		String match;
		while (temp != null){
			match = temp.getclassName();
			if (name.equals(match)){
				return true;
			} else {
				temp = temp.getlink();
			}
		}
		return false;
	}
	
	//pre: the string name is in the queue
	//post: returns the number assigned to the class name (not the hash) and -1 if not found
	
	public int getIndex(String name){
		Node temp  = front;
		String match;
		while (temp != null){
			match = temp.getclassName();
			if (name.equals(match)){
				return temp.getHash(); // actually returning the number its stored in the table and not the hash value
			} else {
				temp = temp.getlink();
			}
		}
		return -1; // should never return because of precondition
	}
	
	//postcondition: returns true only if queue is empty
	public boolean isEmpty(){
		return (size==0);
	}
	
	// postcondition: adds an object to the end of the queue.
	public void insert(String name, int number){
		Node n = new Node();
		n.setClassName(name);
		n.setHash(number);
		n.setLink(null); // sets the link of the new node to null
		if (size < 1){ //sets the first node to be front
			front = n;
			rear = n;
		} else {
			rear.setLink(n);
			rear = n; //since new node is added in the back, the new node is the rear
		}
		size++;
	}
}


