package e13;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;


public class wow {
	
	public static HashMap<String, ArrayList<DataParticle>> dataList = new HashMap<String, ArrayList<DataParticle>>();
	
	
	public static void main(String[] args) {

		
		try{
			retrieveData(new URL("http://www.hep.ucl.ac.uk/undergrad/3459/exam-data/particles.txt"));
			
			
			
			//System.out.println(dataList.get("B13").get(0).matrix[0][0]); to get specific value (first trigger B13 and 0,0 position in the matrix)
			
			calculateMassPairs();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("STUFF");
		}
	}
	
	
	
	public static void retrieveData(URL url) throws Exception {
		InputStream is = url.openStream();
		Scanner scanner = new Scanner(is);
		
		String line;
		
		while(scanner.hasNextLine()) {
			line = scanner.nextLine();
			
			if(line.contains("E")) {//checks for lines that begin with E
				String[] headerLine = line.split("\\s+");//Splits it
				
				int numberOfLines = Integer.parseInt(headerLine[1]);
				String[] dataLines = new String[numberOfLines];
				
				for (int i=0; i< numberOfLines; i++) {
					dataLines[i] =scanner.nextLine();
				}
				
				if (dataList.containsKey(headerLine[2])) {// when it finds an line that is associated with the trigger ID, creates a new object with the value in the lines and the key and add it to the HashMap
					dataList.get(headerLine[2]).add(new DataParticle(headerLine[2], dataLines));
				} else {//for all other unknown trigger ID cases, it crates a new list, that's gonna contain object with all the particle values associated with the trigger and adds the list to the HashMap so that the list is associated with the trigger ID(key)
					ArrayList<DataParticle> tempList = new ArrayList<DataParticle>();
					tempList.add(new DataParticle(headerLine[2], dataLines));
					dataList.put(headerLine[2], tempList);
				}
			}
			
			
			}
		
		}
	public static double calculateInvariantMass(double[] data1, double[] data2) {
		double mass = 2 * data1[1] * data2[1] * (Math.cosh(data1[2] - data2[2]) - Math.cos(data1[3] - data2[3]));
		return mass;
		
		
	}
	public static void calculateMassPairs() {
		
		double signalRegion =0, backgroundRegion =0;
		
		HashMap<String, double[]> triggerRegion = new HashMap<>();//hashmap that has they trigger id as the key and associates with it the background and signal ratio count(stored as an array)
		
		
		for (Entry<String, ArrayList<DataParticle>> e : dataList.entrySet()) {//creates an entry interface that iterates over each trigger and goes over each object (matrix containing values for the event)
			ArrayList<DataParticle> tempList = e.getValue();//temp array that has the size of the number of object
			
			for(DataParticle data: tempList) {//a for loop that goes over the objects stored inside the list(to access the individual values)
				double[] signalAndBackgroundforTrigger= new double[2];//signal and background count is saved here
				for (int i=0 ; i<data.matrix.length; ++i) {//for loop that goes over the matrix length of that object. it goes down the rows, first pick the first row, goes over the rest, then second goes oer the rest etc. until the end of matrix is hit
					for (int j = i; j < data.matrix.length; ++j) {//this means that when it chooses the first row in the above loop, it goes over all the rest of the rows
						if (data.matrix[i][0] != data.matrix[j][0]) {//if the charges are not equal, it pairs them up and calculates the mass
							double mass = Math.sqrt(calculateInvariantMass(data.matrix[i], data.matrix[j]));
							if (mass >=8 && mass <=10) {//checks signal region
								signalAndBackgroundforTrigger[0]++;//add +1 for signal region for the ratio for a specific trigger
								signalRegion++;//counts up the total signal region particles
							} else if (mass >=11 && mass <= 15) {//background region
								
								signalAndBackgroundforTrigger[1]++;//counts the number for that trigger
								backgroundRegion++;;//counts the number of total
							}
						}
					}
				}
				triggerRegion.put(data.getTriggerId(), signalAndBackgroundforTrigger);//adds an object to the hashmap, that is keyed by the trigger ID in that object and the associated signal and background ratios
			}
		}
		System.out.println("*****************");
		System.out.println("Total Signal Region Particles: " +signalRegion);
		System.out.println("Total Background Region Particles: "+backgroundRegion);
		System.out.println("Total Signal Ratio: "+ signalRegion/backgroundRegion);
		System.out.println("*****************");
		double maxDouble = Double.NEGATIVE_INFINITY;
		String trigger = "";
		for(Entry<String, double[]> e : triggerRegion.entrySet()){//gets the first key and then iterates over the list of that specific key, HashMap that has trigger as the key and is associated with double array that contains counts for background and signal region for the trigger
			System.out.println("Trigger is "+e.getKey());//gets the key for that list
			System.out.println("Signal Region: "+e.getValue()[0]);//gets the first value of the double array for that region
			System.out.println("Background Region: "+e.getValue()[1]);//gets the second value of the double array for that region
			if(e.getValue()[1]==0){
				System.out.println("Signal Ratio INFINTE");
			} else{
				double signalRatio= e.getValue()[0]/e.getValue()[1];//calculates the ratio for that trigger
				if(maxDouble<signalRatio){
					maxDouble=signalRatio;
					trigger = e.getKey();//updates the trigger variable for when highest one is found
				}
			}
			System.out.println("*******************");
		}
        System.out.println("Highest Signal Ratio in: " + trigger);
        System.out.println("Highest Signal Ratio: " + maxDouble);
	}

}