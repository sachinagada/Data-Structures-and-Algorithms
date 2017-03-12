package bstproject;

// represents a single node on a list of nodes
public class CrimeRecordListNode {
	CrimeRecord data;
	CrimeRecordListNode next;

	public CrimeRecordListNode(CrimeRecord data, CrimeRecordListNode next){
		this.data = data;
		this.next = next;
	}

	public CrimeRecordListNode getNext(){
		return next;
	}
	
	public void setNext(CrimeRecordListNode next){
		this.next = next; // sets the next of node to the given parameter
	}
	
	public CrimeRecord getData(){
		return data;
	}
	
	public void setData(CrimeRecord data){
		this.data = data; // sets the data of node to the given parameter
	}
}
