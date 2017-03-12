package stackimplementations;

import java.util.Random;

// returns a LinkedStack, StackWithTopPointer, or StackShifter depending on the random selection
public class StackFactory {
	
	public static Stack getStack()
	{
		Random rnd = new Random();
		int i = rnd.nextInt(3); //chooses a random number between [0,3)
		
		if (i == 0) 
		{
			Stack shift = new StackShifter();
			return shift;
			
		} else if (i == 1) {
			Stack pointer = new StackWithTopPointer();
			return pointer;
			
		} else {
			Stack linked = new LinkedStack();
			return linked;
		}
		
		
	}

}
