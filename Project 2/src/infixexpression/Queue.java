package infixexpression;

public class Queue {
	Node front;
	Node rear;
	int size =0; //keeps track of the number of items in the queue
	
	public Queue(){
		front = null;
		rear = null;
	}

	//precondition: the queue is not empty
	// postcondition: removes and returns the front of the queue
	public Object getFront(){
		Object value = null;
		if (!isEmpty()){
			value = front.getData(); //store the object in a temporary variable called value
			front = front.getlink(); // make front refer to the second node
			size--;
		}
		return value;
	}
	
	//postcondition: returns true only if queue is empty
	public boolean isEmpty(){
		return (size==0);
	}
	
	// postcondition: adds an object to the end of the queue.
	public void insert(Object x){
		Node n = new Node();
		
		n.setData(x);
		n.setLink(null); // sets the link of the new node to null
		if (size < 1){ //sets the first node to be front
			front = n;
			rear = n;
		}
		rear.setLink(n);
		rear = n; //since new node is added in the back, the new node is the rear
		
		size++;
	}
}


