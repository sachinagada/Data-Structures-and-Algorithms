package bstproject;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.util.Scanner;

public class BSTTreeDriver {

	static String output = "";
	public BSTTreeDriver(){
		
	}
	
	// pre: user only enters between numbers 1-4 for the menu selection
	public static void main(String[] args) throws Exception{
		
		// reads the CSV file of crimes
		BSTTree b = new BSTTree("CrimeLatLonXY.csv"); // reads the CSV file with the crimes
		System.out.println("Reading input data");
		
		// gets the number of individual crimes and distinct locations the crimes have happened
		int crimes = b.getCrimeCount();
		int distinct = b.getDistinctCrimeLocations();
		
		//prints out the number of individual crimes and distinct addresses
		System.out.println("File read with " + Integer.toString(crimes) + " records");
		System.out.println("Total number of crimes reported on file: " + Integer.toString(crimes));
		System.out.println("Number of distinct address locations: " + Integer.toString(distinct));
		
		output += "1) Enter a Street address for a crime report at that street address.\n"
				+ "2) Find all crimes at the most popular address for crimes.\n" +
		 "3) Find all crimes within a user specified distance from a crime address. \n" + "4) Quit";
		System.out.println(output);
		
		
		
		while (true){ // has the scanner keep on going until the user types 4 to quit
			Scanner sc = new Scanner(System.in);
			int i = sc.nextInt();
			
			//the different menus below based on the number that the user types
			if (i == 1){
				BSTTreeDriver.handleLookUp(b, sc);
	
			} else if (i == 2){
				BSTTreeDriver.handleLookForMostCrimes(b);
	
			} else if (i==3){
				BSTTreeDriver.handleNearbyCrimes(b, sc);
	
			// if the user types 4, the system quits
			} else{
				System.out.println("Quitting - be safe out there");
				System.exit(0);
			}
			
			// Repeats the output once again so the user can explore different menu options
			System.out.println(output);
		}
	}


	// pre: user must type 1 in order to select this menu
	// post: returns the number of crimes at a given address
	public static void handleLookUp(BSTTree tree, Scanner sc) throws java.io.IOException{
		
		// gets the address from the user and converts it to an uppercase string
		System.out.println("Enter street address");
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	    String address = in.readLine();
	    address = address.toUpperCase();

		// looks up how many crimes took place at that address
		CrimeRecordList c = tree.lookUp(address);
		int crimes = c.getSize();
		
		// tell the user about the number of crimes that took place before going back to menu selection
		if (crimes == 0){
			System.out.println("Found no crimes at " + address);
		
		} else {
			System.out.println("Found " + Integer.toString(crimes) + " crimes at " + address);

			// writes a kml file
			c.toGoogleEarth("/Users/Sachi/Desktop/PGHCrimes");
			System.out.println("Report written to PGHCrimes.kml on the desktop");
		}
		
	}
	
	// pre: user must type 2 in order to select this menu
	// post: returns the address at which the most number of crimes happened and the number of crimes
	public static void handleLookForMostCrimes(BSTTree tree) throws java.io.IOException{
		
		// informs the user that the program is searching
		System.out.println("Performing search. Finding address with most crimes.");
		System.out.println("Looking for address with most crime reports.");
		
		// finds the number of crimes that have occurred at the location with the most number of crimes
		int champion = tree.inOrderFindChampion();

		// finds the street where the most number of crimes took place
		String street = tree.getChampionPtr().getData().front.getData().getStreet();
		System.out.println("Found " + Integer.toString(champion) + " crimes at the following addres " + street );
		
		
		// writes a kml file
		CrimeRecordList c = tree.getChampionPtr().getData();
		c.toGoogleEarth("/Users/Sachi/Desktop/PGHCrimes");
		System.out.println("Report written to PGHCrimes.kml on the desktop");
	}
	
	// pre: user must select 3 in order to select this menu
	// post: returns the number of near by crimes given an address and the given distance
	public static void handleNearbyCrimes(BSTTree tree, Scanner sc) throws java.io.IOException{
		
		// looks up the number of crimes at the user given address
		System.out.println("Enter street address of a known crime location to center search");
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	    String address = in.readLine();
	    address = address.toUpperCase();
	
		CrimeRecordList c = tree.lookUp(address);
		int crimes = c.getSize();
		if (crimes == 0){
			System.out.println("Found no crimes at " + address);
		} else {
			// if more than one crime found, then determine all the crimes that happened within the user provided radius
			System.out.println("Found " + Integer.toString(crimes) + " crimes at " + address);
			System.out.println("Enter distance in feet from this spot to find additional crimes");
			BufferedReader dist = new BufferedReader(new InputStreamReader(System.in));
			String d = dist.readLine();
			double distance = Double.parseDouble(d);
			
			// the crime with which the other crimes will be compared to determine if it was within the given distance
			CrimeRecord node = c.front.getData();	
			CrimeRecordList l = tree.bruteForceSearch(node, distance); 
			System.out.println("Found " + Integer.toString(l.getSize()) + " crimes in that range");
			
			//writes to a kml file
			l.toGoogleEarth("/Users/Sachi/Desktop/PGHCrimes");
			System.out.println("Report written to PGHCrimes.kml on the desktop");
		}
	}
}
