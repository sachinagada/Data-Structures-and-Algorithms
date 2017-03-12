package stackimplementations;

public abstract class Stack{
	String implementationType = null;
	
	// name returned or null if unnamed.
	public String getImplementationType()
	{
		return this.implementationType;
		
	}
	
	//post: Implementation type name set
	public void setImplementationType(String implementationType)
	{	
		this.implementationType = implementationType;
	}
	// pre: Stack is not full. 
	// post: Value added to top of stack
	public abstract void push(Object o);
	
	// pre: Stack is not empty. 
	// post: Value returned was on the top of stack. The top was removed.
	public abstract Object pop();

	// post: True if and only if stack is empty.
	public abstract boolean isEmpty();

	// post: True if and only if stack is 
	public abstract boolean isFull();
	
	// pre: Stack not empty 
	// post: The top element is returned but not removed from the stack.
	public abstract Object peek();

}
