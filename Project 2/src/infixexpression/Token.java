package infixexpression;

public class Token {
	
	String val="";  // val holds the value of a token (e.g. '+' or '-' or 'num' or '98.76') 
	String type=""; // member is used to state the type of this token (e.g. 'operator' or 'variable' or 'number')
	
	public Token(String value, String types){
		val = value;
		type = types;
	}
	
	public Token() {
		// TODO Auto-generated constructor stub
	}

	public String getType(){
		return type;
	}
	
	public String getVal(){
		return val;
	}
	
	public void setType(String type){
		this.type = type;
	}
	
	public void setVal(String val){
		this.val = val;	
	}
}
