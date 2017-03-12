package pkg15.pkg121project1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class RPNParser {
	
	//precondition: All tokens are separated by a single space and the expression is well formed post fix. 
	public static void main(String[] argv) throws java.io.IOException
	{
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		RPNCalc the_calc = new RPNCalc();
		while (true)
        {
             StringTokenizer strtok = null;
             // get a line from the user
             
             String line = input.readLine();
             strtok = new StringTokenizer(line," ");
             
             // If an empty line is entered then end the application
             if(!strtok.hasMoreTokens()) break;

             while (strtok.hasMoreTokens())
             {
                    String token = strtok.nextToken();
                    
                   // It may be a float, a variable, or an operator
                    try 
                    {   // if it is a float then push it
                            float f = (new Float(token)).floatValue();
                            the_calc.number(new Float(f));
                    }
                    catch (NumberFormatException n)
                    { //catch it if it's an operation
                    	if (isOper(token)){
                    		doOperation(the_calc, token);

                    	} else{ // if not float or operation, it has to be a variable
                    		the_calc.number(token);
                    	}
                    }
                    
             }
             the_calc.pop();
        }
	}
	
	
	public static void doOperation(RPNCalc the_calc,String token)
    {//checks what token it is and assigns it to the correct method
		if (token.equals("+")) {
			the_calc.add();
		} else if (token.equals("-")){
			the_calc.subtract();
		} else if (token.equals("*")) {
			the_calc.multiply();
		} else if (token.equals("/")) {
			the_calc.divide();
		} else if (token.equals("=")) {
			the_calc.assign();
		} else if (token.equals("^")) { 
			the_calc.raise();
		} else { //print out an error message its not a valid operation
			throw new IllegalArgumentException (token + "is not a valid operation");
		}
    }
	
	//post: returns true if and only if tok is a legal operation.
	public static boolean isOper(String tok)
	{
		String a[] = {"+","-", "*", "/", "^","="};
		for (int i = 0; i<a.length; i++){
			if (a[i].equals(tok)){
				return true;
			}
		}
		return false; //if goes through the array and doesn't find token, then not valid operation
		
	}
	

}
