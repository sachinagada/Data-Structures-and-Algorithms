package bstproject;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class BSTTree {

	int crimes;
	int distinctAddresses;
	int championVal = 0; 
	BSTTreeNode championPtr; // the node where the most number of crimes have occurred
	BSTTreeNode root;
	CrimeRecordList result;
	
	public BSTTree(){
		crimes = 0;
		distinctAddresses = 0;
		root = null;
	}
	
	// pre: filePathname points to the crime file and it's a CSV file
	// post: the tree is constructed with the crime data
	public BSTTree(String filePathAndName){
		try {
			readTree(filePathAndName); // eclipse forces me to surround the readTree because of the FileNotFoundException
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	// handles the actual reading of the file. It is called by the constructor
	public void readTree(String crimeDataLocation) throws FileNotFoundException{
		 BufferedReader in = new BufferedReader(new FileReader(crimeDataLocation));
		 String singleLine = null;
		 
		 try {
			singleLine = in.readLine();
			while((singleLine = in.readLine()) != null){
				CrimeRecord c = CrimeRecord.parseLine(singleLine); // creates a CrimeRecord out of each line in the CSV file
				addCrime(c);
			 }
		 } catch (IOException e) {
			e.printStackTrace();
		 }
	}

	// pre: the crimeRecord has all of its members filled
	// post:adds a CrimeRecord to the BSTtree. Takes into account if there is a list in the node already
	public void addCrime(CrimeRecord d){
		// tree is empty
		if (root == null){
			CrimeRecordList l = new CrimeRecordList();
			l.add(d); // adds the crimeRecord to the list
			distinctAddresses++;
			root  = new BSTTreeNode(l, null, null);
			
		// Tree isn't empty
		} else {
			BSTTreeNode node = root;   //used to keep track of your current node
            BSTTreeNode parent = null; //used to keep track of the parent node
            while(node!=null){
            	parent = node;
            	String currentStreet = node.getData().front.getData().getStreet();
            	int result = d.getStreet().compareTo(currentStreet);
            	
            	if (result == 0){ // same street address so we want to add at this node
            		break;
            	} else if(result < 0){ // street address is smaller so go to the left child
            		node = node.getLc();
            	} else {
            		node = node.getRc(); // street address is bigger so go to the right child
            	}
            }
            
            String parentStreet = parent.getData().front.getData().getStreet();
            int result = d.getStreet().compareTo(parentStreet);
            if (result < 0){ // street of added crime is alphabetically smaller than the parent street
            	CrimeRecordList l = new CrimeRecordList();
            	l.add(d);
            	parent.setLc(new BSTTreeNode(l, null, null));
            	distinctAddresses++; // one more node was added so one more address was added
            }else if (result > 0){ // street of added crime is alphabetically bigger than the parent street
            	CrimeRecordList l = new CrimeRecordList();
            	l.add(d);
            	distinctAddresses++;
            	parent.setRc(new BSTTreeNode(l, null, null));
            } else { // result = 0 same street address 
            	parent.getData().add(d);
            }
		}
		crimes++; // the number of crimes is now one more
		
	}

	public int getChampionVal(){
		return championVal;
	}
	
	public BSTTreeNode getChampionPtr(){
		inOrderFindChampion(root);
		return championPtr;
	}
	
	// post: returns the distinct number of crimes
	public int getCrimeCount(){
		return crimes;
	}
	
	// returns the distinct addresses where crimes have occurred
	public int getDistinctCrimeLocations(){
		return distinctAddresses;
	}
	
	// recursive method that visits each node in pre-order
	// On each visit, the entire list of crimes is displayed.
	// this method would have a runtime analysis of O(N) since it goes through every node in the list
	public void preOrderPrint(BSTTreeNode t){
		
		if (t!=null && t.getData() != null){
			t.getData().start(); // make the pointer point to the head of the list
			while(t.getData().hasNext()){
				System.out.println(t.getData().next().toString());
			}
		}

		//gets the left children
		if (t.getLc() != null){
			preOrderPrint(t.getLc());
		}
		
		//gets the right children
		if (t.getRc() != null){
			preOrderPrint(t.getRc());
		}
	}
	
	// calls the recursive preOrderPrint method
	public void preOrderPrint(){
		preOrderPrint(root);
	}
	
	// recursive method that visits each node in in-order. 
	// On each visit, the entire list of crimes is displayed.
	// this method would have a runtime analysis of O(N) since it goes through every node in the list
	public void inOrderPrint(BSTTreeNode t){
		
		if (t.getLc() != null){ // gets the left children first because its in-order traversal
			inOrderPrint(t.getLc());
		}
		
		// the following few lines prints the info from the crimelist in the node
		if (t.getData() != null){
			t.getData().start(); // make the pointer point to the head of the list
			while(t.getData().hasNext()){
				System.out.println(t.getData().next().toString());
			}
		}
		
		// gets the right children through recursions
		if (t.getRc() != null){
			inOrderPrint(t.getRc());
		}
	}
	
	// Calls the recursive inOrdePrint method
	public void inOrderPrint(){
		inOrderPrint(root);
	}
	
	// This is a recursive method that visits each node  in-order. 
	// If this list is larger than the old champion, the new champion becomes this list.
	// this method would have a runtime analysis of O(N) since it goes through every node in the list
	public void inOrderFindChampion(BSTTreeNode t){
		if (t.getLc()!= null){
			inOrderFindChampion(t.getLc());
		}
		
		if (t.getData().getSize() > championVal){
			championVal = t.getData().getSize();
			championPtr = t;
		}
		
		if (t.getRc() != null){
			inOrderFindChampion(t.getRc());
		}
	}
	
	// calls the recursive inOrderFindChampion method
	public int inOrderFindChampion(){
		inOrderFindChampion(root);
		return championVal;
	}
	
	// post: a list of crimes at that address, or a null if there are no crimes found.
	// runtime analysis would be O(log(N)) since it's going down the depth of the tree and not through all the nodes
	public CrimeRecordList lookUp(String address){
		CrimeRecordList a = new CrimeRecordList();
		BSTTreeNode node = root;
		while (node != null){
			String nodeAddress = node.getData().front.getData().getStreet();
			// compares the addresses alphabetically
			int result = address.compareTo(nodeAddress);
			
			if (result > 0){// address is bigger, go to the right child
				node = node.getRc();
			} else if (result < 0){ // address is smaller, go to left child
				node = node.getLc();
			} else { // result = 0 and this is the node we want
				a = node.getData();
				break;
				//node = null; // breaks the while loop
			}
		}
		// if a is empty then a would be null, otherwise a would be a list
		return a;
	}
	
	// n - contains a crime record of a crime in the tree
	// distance - is a distance in feet
	// post: a crime list with those crimes within this distance from n
	// this would have a runtime analysis of O(N) since it has to go through every node
	public CrimeRecordList bruteForceSearch(CrimeRecord n, double distance){
		this.result = new CrimeRecordList();
		inOrderSearch(root,n, this.result,distance); // the result list will keep on changing in the inOrderSearch
//		System.out.println(result.getSize()); // THE RESULT LENGHT KEEPS ON PRINTING 0;
		return this.result;
	}
	
	// post: goes through all the crimes to determine which ones occurred within a certain distance
	// method would have a runtime analysis of O(N) since it has to go through every node
	public void inOrderSearch(BSTTreeNode t, CrimeRecord n,
            CrimeRecordList resultList,double distance){
		
		// goes through the left children first
		if (t.getLc() != null){
			inOrderSearch(t.getLc(), n, this.result, distance); // DONT THINK RESULTLIST IS GETTING PASSED IN CORRECTLY
		}
		
		// looks at the current node
		if (t.getData() != null){
			CrimeRecord currentCrimeRecord = t.getData().front.getData();
			if (CrimeRecord.distanceBetween(currentCrimeRecord, n) <= distance){
				this.result = CrimeRecordList.concat(this.result, t.getData()); // **** MIGHT BE A MISTAKE HERE
			}
		}
		
		// goes through the right children
		if (t.getRc() != null){
			inOrderSearch(t.getRc(),n,this.result,distance); //// DONT THINK RESULTLIST IS GETTING PASSED IN CORRECTLY
		}
	}
	
	public static void go(String x){
		// debugging method. Ignore
	}
	
	public static void sto(java.lang.String x){
		// debugging method. Ignore
	}
}
