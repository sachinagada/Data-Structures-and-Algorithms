package stackimplementations;

public class StackWithTopPointer extends stackimplementations.Stack {
	
	// initially start with pointer at -1 so first value goes in the 0th position
	// initialize an empty array of length 100 so values can go here later
	// have an integer variable to keep track of all the numbers that have been put in the stack
	
	int pointer = -1; // controls the top of the stack
	Object [] objArray = new Object [100];
	int stackLength = 0;
	
	
	// pre: Stack is not full
	// post: Value added to top of stack
	public void push(Object o)
	{
		pointer += 1;
		objArray[pointer] = o;
		stackLength += 1; 
	}
		
	// precondition:Stack is not empty
	// post: value returned was on top of stack and the top was removed
	
	public Object pop()
	{
		Object value = null;
		if (isEmpty() == false)
		{
			value = objArray[pointer];	// stores the top variable
			stackLength -= 1;
			pointer -= 1;
		}
		return value;
	}
	
	// post: true only when the stack is completely empty
	public boolean isEmpty()
	{
		if (stackLength == 0){
			return true;
		} else {
			return false;
		}
	}
	
	// post: true when the stack is completely full
	public boolean isFull()
	{
		if (stackLength == 100){
			return true;
		} else {
			return false;
		}
	}
	
	// precondition: stack not empty
	// postcondition: top element returned but not removed from element
	
	public Object peek()
	{
		return objArray[pointer];	
	}
	
}
