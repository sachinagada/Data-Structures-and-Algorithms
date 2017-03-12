package infixexpression;

import java.io.BufferedReader;
import java.io.InputStreamReader;
//import java.util.Stack;

//import stackimplementations.StackFactory;
//import stackimplementations.Stack;
//import stackimplementations.StackFactory;
import stackimplementations.StackShifter;

public class ShuntingYard {
	Queue q = new Queue();
	StackShifter s = new StackShifter();
	String str = "";

	
	// postcondition: returns the queue contents as a String in FIFO order.
	public String getQueueAsString(){
		while (!q.isEmpty()){ // keeps popping from the queue and adding to string until queue is empty
			Token valuePopped = (Token) q.getFront();
			str += valuePopped.getVal();
			str += " ";
		}
		return str;
	}
	
	// postcondition: returns true if and only if o is +, -, /, *
	public boolean leftAssociative(Token o){
		if ((o.getVal().equals("+")) || (o.getVal().equals("-")) || (o.getVal().equals("*")) || (o.getVal().equals("/"))){
			return true;
		} else {
			return false;
		}
	}
	
	// postcondition: returns true if and only if o is ^ or =
	public boolean rightAssociative(Token o){
		if (o.getVal().equals("^") || o.getVal().equals("=")){
			
			return true;
		}
		return false;
	}
	
	// postcondition: returns true if and only if o1 has less than or equal to the precedence of o2.
	public boolean isPrecedenceLessThanOrEqualTo(Token o1,Token o2){
		// = has lower precedence than everything else
		if (o1.getVal().equals("=")){ 
			return true;
		
		// ( has lower precedence than everything besides =
		} else if (o1.getVal().equals("(") && (!o2.getVal().equals("="))){ 
			return true;
			
		// + will be lower or equal precedence than everything besides = and (
		} else if (o1.getVal().equals("+") && ((!o2.getVal().equals("(")) || !o2.getVal().equals("="))){
			return true;
			
		// - will be lower or equal precedence than everything besides = and (	
		} else if (o1.getVal().equals("-") && ((!o2.getVal().equals("(")) || !o2.getVal().equals("="))){
			return true;
		
		// * will only be lower or equal precedence if o2 was ^ or / or itself
		} else if (o1.getVal().equals("*") && ((o2.getVal() .equals("^")) || (o2.getVal().equals("/")) || o2.getVal().equals("*"))){
			return true;
		
		// / will only be lower or equal precedence if o2 was ^ or * or itself
		} else if (o1.getVal().equals("/") && ((o2.getVal().equals("^")) || (o2.getVal().equals("*")) || o2.getVal().equals("/"))){
			return true;
		
		// all the other cases will return false
		} else {
			return false;
		}	
	}
	
	// postcondition: returns true if and only if o1 has less than the precedence of o2.
	public boolean isPrecedenceLessThan(Token o1,Token o2){
		
		// = has lower precedence than everything else except itself
		if (o1.getVal().equals("=") && (!o2.getVal().equals("="))){ 
			return true;
		
		// ( has lower precedence than everything besides = and itself
		} else if (o1.getVal().equals("(") && (!o2.getVal().equals("=")) && (!o2.getVal().equals("("))){ 
			return true;
			
		// + will be lower precedence to *, /, and ^
		} else if (o1.getVal().equals("+") && ((o2.getVal().equals("^")) || o2.getVal().equals("*") || o2.getVal().equals("/"))){
			return true;
			
		// - will be lower precedence to *, /, and ^
		} else if (o1.getVal().equals("-") && ((o2.getVal().equals("^")) || o2.getVal().equals("*") || o2.getVal().equals("/"))){
			return true;
		
		// * and / will only be lower if o2 was ^
		} else if (((o1.getVal().equals("*")) || (o2.getVal().equals("/"))) && (o2.getVal().equals("^"))){
			return true;
			
		// all the other cases will return false -> they won't be lower precedence
		} else {
			return false;
		}	
}
	
	// postcondition: adds the postfix expression to the queue after performing the Shunting yard algorithm
	public void shuntingYard(Token[] toks, int size){
		
		// iterates over the length of the token array
		for (int i =0; i < size; i++){
			
			// if the token is a number or a variable, it goes in the queue
			if ((toks[i].getType().equals("number")) || toks[i].getType().equals("variable")){
				q.insert(toks[i]);
			
			// follows the rules in shunting yard algorithm for operators
			} else if (toks[i].getType().equals("operator")){
				Token t = (Token) s.peek();

				if (s.peek() != null){
					while ((s.peek() != null) && t.getType().equals("operator") && 
							(leftAssociative(toks[i]) && isPrecedenceLessThanOrEqualTo(toks[i],t)) 
							|| (rightAssociative(toks[i]) && isPrecedenceLessThan(toks[i], t))){
						q.insert(s.pop()); // pop from the stack and insert onto the queue
						//t = (Token) s.peek();
					}
				}
				// last step is to push the operator onto the top of the stack
				s.push(toks[i]);
			
			// push the left parenthesis onto the stack
			} else if (toks[i].getType().equals("leftParen")){
				s.push(toks[i]);
			
			// if right parenthesis then keeps popping off stack onto queue until left parenthesis found
			} else if (toks[i].getType().equals("rightParen")){
				Token t = (Token) s.peek();
				while(!t.getType().equals("leftParen")){
					if (t.getType().equals("leftParen")){
						break;
					} else {
					q.insert(s.pop());
					t = (Token) s.peek(); // reset t to be the next value in the stack
					}
				}
				// removes the left parenthesis but doesn't add to the queue
				s.pop();
			}
		}
		
		// empty the objects in the stack onto the queue at the end of the iteration
		while (!s.isEmpty()){
			q.insert(s.pop());
		}
		
		
	}
	
	// precondition: Pre-condition: The user is well
	// behaved and always enters legal infix expressions containing number, 
	// variable names or operators chosen from the set {+,-,*,/,^,=}
	// postcondition: The generated postfix contains a single space between tokens. 
	// This routine creates a ShuntingYard object. It then runs the shuntingYard method 
	// on the tokenized input. The tokenized input is the infix string broken down into tokens. 
	// It writes the data left in the queue to the output screen.
	public static void main(java.lang.String[] args) throws java.io.IOException{
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));    
        while (true) {     
            String line = input.readLine();
            if(line.isEmpty()) break;
            InfixTokenizer tok = new InfixTokenizer(line);
            Token[] tokens = tok.getTokens();
            ShuntingYard sy = new ShuntingYard();
            sy.shuntingYard(tokens, tok.getSize()); 
            String postFix = sy.getQueueAsString();
            System.out.println(postFix);
        }
	}
}





