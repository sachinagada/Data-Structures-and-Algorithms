package infixexpression;

public class Node {

	// This class makes/edits the nodes and gives back the info regarding the nodes

	private Object data;
	Node link;
	
	public Object getData(){
		return data;
	}
	
	public Node getlink(){
		return link;
	}
	
	public void setData(Object newData){
		data = newData;
	}
	
	public void setLink(Node newLink){
		link = newLink;
	}
}
