package bstproject;

public class CrimeRecord {
	double x; // x-coordinate in State Plane coordinate system
    double y; // y-coordinate in State Plane coordinate system
    int time; // time of day the crime occurred
    String street; // street address of the crime
    String offense; // crime type (Robbery, assault etc)
    String date; // date of crime
    String tract; 
    double lat; // latitude
    double lon; // longitude

    
    // constructor to initialize the above parameters
    public CrimeRecord(double x, double y, int time, String street, String offense,
            String date, String tract, double lat, double lon){
    	this.x = x;
    	this.y = y;
    	this.time = time;
    	this.street = street;
    	this.offense = offense;
    	this.date = date;
    	this.tract = tract;
    	this.lat = lat;
    	this.lon = lon;
    }
    
    public CrimeRecord(){
    	
    }
    
    public String getDate(){
    	return date;
    }
    
    public double getLat(){
    	return lat;
    }
    
    public double getLon(){
    	return lon;
    }
    
    public String getOffense(){
    	return offense;
    }
    
    public java.lang.String getStreet(){
    	return street;
    }
    
    public int getTime(){
    	return time;
    }
    
    public String getTract(){
    	return tract;
    }
    
    public double getX(){
    	return x;
    }
    
    public double getY(){
    	return y;
    }
    
    public void setDate(String date){
    	this.date = date;
    }
    
    public void setLat(double lat){
    	this.lat = lat;
    }
    
    public void setLon(double lon){
    	this.lon = lon;
    }
    
    public void setOffense(String offense){
    	this.offense = offense;
    }
    
    public void setStreet(String street){
    	this.street = street;
    }
    
    public void setTime(int time){
    	this.time = time;
    }
    
    public void setTract(String tract){
    	this.tract = tract;
    }
    
    public void setX(double x){
    	this.x = x;
    }
    
    public void setY(double y){
    	this.y = y;
    }
    
    // override toString in class java.lang.Object
    // returns a string containing all member data
    public String toString(){
    	String a = "";
    	a += Double.toString(this.x) + " ";
    	a += Double.toString(this.y) + " ";
    	a += Integer.toString(this.time) + " ";
    	a += this.street + " ";
    	a += this.offense + " ";
    	a += this.date + " ";
    	a += this.tract + " ";
    	a += Double.toString(this.lat) + " ";
    	a += Double.toString(this.lon) + " ";
    	return a; 	
    }

    // pre: line contains comma separated values of crime data - as shown by each line in the file.
    // post: a new CrimeRecord object holding the data from the String line
    // @line - is a single line of input from the crime file
    public static CrimeRecord parseLine(String line){
    	String s[] = line.split(",");
    	CrimeRecord c = new CrimeRecord();
    	c.setX(Double.parseDouble(s[0]));
    	c.setY(Double.parseDouble(s[1]));
    	c.setTime(Integer.parseInt(s[2]));
    	c.setStreet(s[3]);
    	c.setOffense(s[4]);
    	c.setDate(s[5]);
    	c.setTract(s[6]);
    	c.setLat(Double.parseDouble(s[7]));
    	c.setLon(Double.parseDouble(s[8]));
    	return c;
    }
    
    // pre: two crime records contain valid State Plane coordinate data
    public static double distanceBetween(CrimeRecord a, CrimeRecord b){
    	
    	// the decimal latitude/longitude are converted to kilometers using the conversion that there are 10000 km / 90 degrees
    	// The km is then converted to feet with the conversion 3280.4 feet per km
    	double ax = a.getX();
    	double ay = a.getY();
    	double bx = b.getX();
    	double by = b.getY();

    	// using the pythagorean theorem below
    	double distance = Math.pow((Math.pow((ax-bx),2) + Math.pow((ay-by),2)),0.5);
    	return distance;
    }
    
    
}
