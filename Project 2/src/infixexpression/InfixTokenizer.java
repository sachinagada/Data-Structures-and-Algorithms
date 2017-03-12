package infixexpression;


public class InfixTokenizer {
	
	int items = 0;
	int arraySize = 100;
	Token a[] = new Token[arraySize];
	String line;

	// precondition: line not an illegal infix expression and numbers less than 0 start with 0.0909 and not the decimal point right away
	public InfixTokenizer(String line) {
		this.line = line;
		for (int i = 0; i<arraySize; i++){
			a[i] = new Token();
		}

	}

	//postcondition: size of the array of tokens is returned
	public int getSize(){
		return items;
	}
	
	// postcondition: an array of Tokens is returned
	public Token[] getTokens(){
		return this.parseLine(); // parseLine converts string into tokens[] so this will return a token
	}
	
	//postcondition: returns true only if ch is in the set { +,-,/,*, ^, =}
	public boolean operator(char ch){
		char a[] = { '+','-','/','*', '^', '='};
		// loops through the a array to see if ch is in the array
		for (int i = 0; i<a.length;i++){
			if (a[i] == ch){
				return true;
			}
		} 
		// character not found in the array so return false
		return false;
	}

	//postcondition: returns true only if ch is a letter
	public boolean isLetter(char ch){
		return Character.isLetter(ch);
	}
	
	//postcondition: returns true only if ch is a digit	
	public boolean isDigit(char ch){
		return Character.isDigit(ch);
	}
	
	//postcondition: returns true only if ch is a decimal point
	public boolean isDecimalPoint(char ch){
		return (ch == '.');
	}
	
	//postcondition: returns true only if ch is a left parenthesis
	public boolean isLeftParen(char ch){
		return (ch == '(');
	}
	
	//postcondition: returns true only if ch is a right parenthesis
	public boolean isRightParen(char ch){
		return (ch == ')');
	}
	
	// postcondition: returns an array of tokens after parsing the line
	public Token[] parseLine(){
		
		String str = line;
		String variable = ""; // will be used when a variable of mulitple characters is used in the line
		String number = ""; // will be used when a number with multiple digits is used in the line
		
		for (int x=0; x<str.length();){
			if (operator(str.charAt(x))){
				a[items].setType("operator"); // sets the token at the items' index type to be operator
				// converts character to string and sets the string as the type of value in token[items]
				a[items].setVal(Character.toString(str.charAt(x))); 
				items++;
				x++; 
			
			} else if (Character.isWhitespace(str.charAt(x))){
				x++;
			
			} else if (isLetter(str.charAt(x))){
				// the characters will be part of the same variable until they stop being letters
				while (x<str.length() && isLetter(str.charAt(x))){ 
					variable += Character.toString(str.charAt(x));
					x++; // add 1 to x to keep traversing through the line 
				}
				
				a[items].setType("variable");
				a[items].setVal(variable);
				items++;
				variable = ""; // reset the variable string to be empty for the next string
			
			} else if (isDigit(str.charAt(x))){
				while (x<str.length() && (isDigit(str.charAt(x)) || isDecimalPoint(str.charAt(x)))){
					number += Character.toString(str.charAt(x));
					x++;
				}
				
				a[items].setType("number");
				a[items].setVal(number);
				items++;
				number = "";
				
			} else if (isLeftParen(str.charAt(x))){
				a[items].setType("leftParen"); // sets the token at the items' index type to be operator
				// converts character to string and sets the string as the type of value in token[items]
				a[items].setVal(Character.toString(str.charAt(x))); 
				items++;
				x++;
			} else if (isRightParen(str.charAt(x))){
				a[items].setType("rightParen"); // sets the token at the items' index type to be operator
				// converts character to string and sets the string as the type of value in token[items]
				a[items].setVal(Character.toString(str.charAt(x))); 
				items++;
				x++;
			} 
		}
		
		return a;
	}

	
	// to test drive the InfixTokenizer
//	public static void main(String args[]){
//        String line = "xik = (23 + 2) * (3 - 49)^66";
//        InfixTokenizer tok = new InfixTokenizer(line);
//        Token[] tokens = tok.parseLine();
//        for (int i = 0 ; i < tok.getSize(); i++) {
//            System.out.println(tokens[i].getType() + " " + tokens[i].getVal());
//        }   
//    }

}


