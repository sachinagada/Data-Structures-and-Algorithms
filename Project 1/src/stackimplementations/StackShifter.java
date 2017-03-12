package stackimplementations;

public class StackShifter extends stackimplementations.Stack{
	
	int arrayLength = 0; //keep track of the number of elements in the stack
	Object [] objArray = new Object [100];
	
//	public static void main(String[] args)
//	{
//		was used to test the stackshifter stack
//	}
//	
	
	// precondition: stack is not full
	// post: Value added to top of stack

	public void push(Object o)
	{
		if (isFull() == false)
		{
			for (int i = arrayLength; i > 0; i--) 
			{
				objArray[i] = objArray[i-1];
			}
			objArray[0] = o;
			arrayLength += 1;
		}
	}
	
	// precondition: stack is not empty
	// post: Top element removed from stack and returned.
	public Object pop()
	{
		Object value = null;
		if (isEmpty() == false)
		{
			value = objArray[0];
			for (int i = 0; i < arrayLength; i++)
			{
				objArray[i] = objArray[i+1];
			}
			arrayLength -= 1;
		}
		return value;
	}
	
	// post: True if and only if stack is empty
	public boolean isEmpty()
	{
		if (arrayLength == 0)
		{
			return true;
		} else {
			return false;
		}
	}
	
	// post: True if and only if stack is full.
	public boolean isFull()
	{
		if (arrayLength == 100)
		{
			return true;
		} else {
			return false;
		}
	}
	
	// pre: stack not empty
	// post: The top element is returned but not removed from the stack.
	public Object peek()
	{
		return objArray[0];
	}
}
