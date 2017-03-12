package bstproject;

// key for each node will be the street address to determine if the crime is greater or less than another node
public class BSTTreeNode {
	CrimeRecordList data; // list of crimes in each node
	BSTTreeNode lc;
	BSTTreeNode rc;
	
	// constructor initializes the data, lc, and rc
	public BSTTreeNode(CrimeRecordList data,BSTTreeNode lc,BSTTreeNode rc){
		this.data = data;
		this.lc = lc;
		this.rc = rc;
	}
	
	// post: gets the left child of the tree
	public BSTTreeNode getLc(){
		return lc;
	}
	
	// post: gets the right child of the tree
	public BSTTreeNode getRc(){
		return rc;
	}
	
	// post: sets the left child to the specified tree node
	public void setLc(BSTTreeNode lc){
		this.lc = lc;
	}
	
	// post: sets the right child to the specified tree node
	public void setRc(BSTTreeNode rc){
		this.rc = rc;
	}

	// post: returns the crime record data
	public CrimeRecordList getData(){
		return data;
	}
	
	// post: sets the data to the specified crime record list
	public void setData(CrimeRecordList data){
		this.data = data;
	}
}
