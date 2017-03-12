package pkg15.pkg121project1;

import java.util.TreeMap;
import stackimplementations.StackFactory;
import stackimplementations.Stack;


// Post: Stack and TreeMap are defined.
public class RPNCalc {
	
	StackFactory stack = new StackFactory();
	Stack s = StackFactory.getStack();
	TreeMap<String, Float> treemap = new TreeMap<String,Float>();


//	public static void main(String[] a)
//	{
//		// to test rpncalc
//	}

	// post: tells us if the calculator has an empty stack.
	public boolean calcNotEmpty()
	{
		return (s.isEmpty());
	}
	
	
	// pre: two objects exist on the top of the stack. Any names on the stack are reflected in the TreeMap. 
	// post: The operation was performed and the stack top has the result.
	public void add()
	{
		Object val1 = s.pop();
		Object val2 = s.pop();
		float val3, val4;
		
		if (val1 instanceof String){
			val3 = treemap.get(val1);
		} else {
			val3 = (Float) val1;
		}
		
		if (val2 instanceof String){
			val4 = treemap.get(val2);
		} else {
			val4 = (Float) val2;
		}
		
		float val5 = val3 + val4;
		
		s.push(val5);
	}
	
	// pre: two objects exist on the top of the stack. Any names on the stack are reflected in the TreeMap. 
	// post: The operation was performed and the stack top has the result.

	public void raise()
	{
		Object val1 = s.pop();
		Object val2 = s.pop();
		float val3, val4;
		
		if (val1 instanceof String){
			val3 = treemap.get(val1);
		} else {
			val3 = (Float) val1;
		}
		
		if (val2 instanceof String){
			val4 = treemap.get(val2);
		} else {
			val4 = (Float) val2;
		}
		
		float val5 = (float) Math.pow(val4, val3);
		
		s.push(val5);
	}
	
	// pre: two objects exist on the top of the stack. Any names on the stack are reflected in the TreeMap. 
	// post: The operation was performed and the stack top has the result.
	public void subtract()
	{
		Object val1 = s.pop();
		Object val2 = s.pop();
		float val3, val4;
		
		if (val1 instanceof String){
			val3 = treemap.get(val1);
		} else {
			val3 = (Float) val1;
		}
		
		if (val2 instanceof String){
			val4 = treemap.get(val2);
		} else {
			val4 = (Float) val2;
		}
		
		float val5 = val4 - val3;
		
		s.push(val5);	
	}
	
	
	// pre: two objects exist on the top of the stack. Any names on the stack are reflected in the TreeMap. 
	// post: The operation was performed and the stack top has the result.
	public void divide()
	{
		Object val1 = s.pop();
		Object val2 = s.pop();
		float val3, val4;
		
		if (val1 instanceof String){
			val3 = treemap.get(val1);
		} else {
			val3 = (Float) val1;
		}
		
		if (val2 instanceof String){
			val4 = treemap.get(val2);
		} else {
			val4 = (Float) val2;
		}
		
		float val5 = val4 / val3;
		
		s.push(val5);	
	}
	
	// pre: two objects exist on the top of the stack. Any names on the stack are reflected in the TreeMap. 
	// post: The operation was performed and the stack top has the result.
	public void multiply()
	{
		Object val1 = s.pop();
		Object val2 = s.pop();
		float val3, val4;
		
		if (val1 instanceof String){
			val3 = treemap.get(val1);
		} else {
			val3 = (Float) val1;
		}
		
		if (val2 instanceof String){
			val4 = treemap.get(val2);
		} else {
			val4 = (Float) val2;
		}
		
		float val5 = val3 * val4;
		
		s.push(val5);
	}
	
	//Pre: there is room in the calculator's stack. 
	//post: a new item has been added to the top of the stack.
	public void number(Object item)
	{
		s.push(item);
	}
	
	// pre: two objects must be available on the stack. The second is a name. 
	// post: the variable is assigned a new value in the TreeMap.
	public void assign()
	{
		Object val1 = s.pop();
		Object val2 = s.pop();
		float val3;

		if (val1 instanceof String){
			val3 = treemap.get(val1);
		} else {
			val3 = (Float) val1;
		}
		
		String val4 = (String) val2;

		treemap.put(val4, val3);
		
		s.push(val4);
	}
	
	// pre: stack is not empty 
	// post: the value at the top is returned. If top is a variable, its value from the TreeMap is returned.
	
	public Object top()
	{
		Object val1 = s.peek(); 
		
		if (val1 instanceof String){
			val1 = treemap.get(val1);
		}
		
		return val1;	
	}
	
	// Pre: stack is not empty 
	// Post: the stack is popped and the value is returned.
	public Object pop()
	{
		Object val1 = s.pop(); 
		
		if (val1 instanceof String){
			val1 = treemap.get(val1);
		}
		System.out.println(val1);
		return val1;	
	}
}
