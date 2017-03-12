package stackimplementations;

public class LinkedStack extends stackimplementations.Stack{
	
	int cursor = 0;
	intNode head = null;
	
//	public static void main(String[] args)
//	{
//		 LinkedStack n = new LinkedStack();
//		 n.push(4);
//		 n.push(3);
//		 n.pop();
//
//		 n.isEmpty();
//	}
	
	//  post: True if and only if stack is empty.
	public boolean isEmpty()
	{
		if (cursor == 0){
			return true;
		} else {
			return false;
		}
	}

	// post: True if and only if stack is full.
	public boolean isFull()
	{
		// a linked list is never full
		return false;
	}
	
	// precondition: Stack is not full
	// post: Value added to top of stack
	
	public void push(Object o)
	{
		if (isFull() == false)
		{
			intNode n = new intNode();
			n.setData(o);
			n.setLink(head);
			head = n;
			cursor += 1; // to keep track of the length of the list
		}
	}
	
	// precondition: stack is not empty
	// post: Value returned was on the top of stack. The top was removed
	public Object pop()
	{
		Object value = null;
		if (isEmpty() == false)
		{
			value = head.getData();
			head = head.getlink();
			cursor -= 1;
		}
		return value;
	}
	
	// precondition: Stack not empty
	// post: The top element is returned but not removed from the stack.

	public Object peek()
	{
		return head;
	}
}

// This class makes the nodes and gives back the info regarding the nodes
class intNode{
	private Object data;
	intNode link;
	
	public Object getData(){
		return data;
	}
	
	public intNode getlink(){
		return link;
	}
	
	public void setData(Object newData){
		data = newData;
	}
	
	public void setLink(intNode newLink){
		link = newLink;
	}
}
