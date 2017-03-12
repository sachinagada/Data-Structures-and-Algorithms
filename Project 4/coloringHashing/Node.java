package coloringHashing;

public class Node {

	// This class makes/edits the nodes and gives back the info regarding the nodes

	private String className;
	private int hash;
	Node link;
	
	public String getclassName(){
		return className;
	}
	
	public int getHash(){
		return hash;
	}
	
	public Node getlink(){
		return link;
	}
	
	public void setClassName(String newClassName){
		className = newClassName;
	}
	
	public void setHash(int newHash){
		hash = newHash;
	}
		
	public void setLink(Node newLink){
		link = newLink;
	}
}
