package exam1314;

import java.io.*;
import java.net.URL;
import java.util.*;

public class ProcessData {

	public static HashMap<String, String> species = new HashMap<>();
	public static ArrayList<Survey> survey = new ArrayList<>();
	public static HashMap<String, String> ASpecies = new HashMap<>();
	public static ArrayList<Survey> ASurvey = new ArrayList<>();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		loadSpecies("P");
		//System.out.println(species);
		loadSurvey("P");
		//System.out.println(survey);
		
		double heighest = 0;
		double lowest = 10000000;
		String heighestName = null;
		String lowestName = null;
		
		for(Map.Entry<String, String> cursor : species.entrySet()) {
			double mean = meanHeight(survey, cursor.getKey());
			if(mean > heighest){heighest = mean; heighestName = cursor.getValue();}
			if(mean < lowest){lowest = mean; lowestName = cursor.getValue();}		
		}
		System.out.println("The highest mean height is: "+heighest+", for the species "+heighestName);
		System.out.println("The lowest mean height is: "+lowest+", for the species "+lowestName+"\n");

		RegionSearch minMaxLatNorth = new MinMaxLatitude(-30, 90);
		RegionSearch minMaxLatSouth = new MinMaxLatitude(-90, -30);
		System.out.println("North of -30 latitude ");
		meanHeight(minMaxLatNorth.locationSort(survey),"NT");
		System.out.println("South of -30 latitude ");
		meanHeight(minMaxLatSouth.locationSort(survey),"NT");
		
		RegionSearch distanceLocation = new DistanceLocation(-30.967,75.430,50);
		System.out.println("In a circle with radius 50km of the mountain at -30.967,75.430");
		meanHeight(distanceLocation.locationSort(survey),"BN");
		
		loadSpecies("A");
		loadSurvey("A");
		RegionSearch animalDistanceLocation = new DistanceLocation(-30.967,75.430,100);
		ArrayList<String> animals = findExclusive(animalDistanceLocation.locationSort(ASurvey));
		System.out.println("The animals only found within 100km of -30.967,75.430 are: ");
		for(String animal:animals){
			System.out.println(ASpecies.get(animal));
		}
		
		
		
		
	}
	
	public static ArrayList<String> findExclusive(ArrayList<Survey> surveyData) {
		ArrayList<String> output = new ArrayList<>();
				
		for(Survey animal:surveyData) {
			if(!output.contains(animal.getId())) {
				output.add(animal.getId());
			}
		}
			
		for(Survey animal:ASurvey) {
			boolean isInArea = false;
			for(Survey areaAnimal:surveyData){
				if(ASurvey.contains(areaAnimal)){
					isInArea = true;
				}
			}
			if (!isInArea){
				output.remove(animal.getId());
			}
		}
		
		return output;
	}
	
	public static double meanHeight(ArrayList<Survey> survey, String id){
		int sum = 0;
		int count = 0;			
		System.out.println("For the speices "+species.get(id));
		for(Survey plant : survey) {
			if(plant.getId().equals(id)) {
				count++;
				sum += plant.getHeight();
			}
		}
		System.out.println("The number of plants is: "+count);
		System.out.println("With a mean height of: "+sum/count+"cm \n");
		return sum/count;
	}
	
	public static BufferedReader loadStreamUrl(URL url) throws IOException{
			InputStream is = url.openStream();			
			InputStreamReader isr = new InputStreamReader(is);
			return new BufferedReader(isr);
	}
	
	public static void loadSpecies(String type){
		if(type == "P") {
			BufferedReader stream = null;
			try {
				 stream = loadStreamUrl(new URL("http://www.hep.ucl.ac.uk/undergrad/3459/exam-data/2013-14/species-plants.txt"));
	
			} catch (IOException e) {
				System.out.println("ERROR: Species file could not be found or connection issues.");
			}
			
			Scanner lines = new Scanner(stream);
			while(lines.hasNextLine()){
				String[] parts = lines.nextLine().split("\\s+");
				species.put(parts[0], parts[1]+" "+parts[2]);
				
			}
			lines.close();
		}
		else if(type == "A") {
			BufferedReader stream = null;
			try {
				 stream = loadStreamUrl(new URL("http://www.hep.ucl.ac.uk/undergrad/3459/exam-data/2013-14/species-animals.txt"));
	
			} catch (IOException e) {
				System.out.println("ERROR: Animal species file could not be found or connection issues.");
			}
			
			Scanner lines = new Scanner(stream);
			while(lines.hasNextLine()){
				String[] parts = lines.nextLine().split("\\s+");
				ASpecies.put(parts[0], parts[1]+" "+parts[2]);
				
			}
			lines.close();
		}

	}
	
	public static void loadSurvey(String type){
		if(type == "P") {
			BufferedReader stream = null;
			try {
				 stream = loadStreamUrl(new URL("http://www.hep.ucl.ac.uk/undergrad/3459/exam-data/2013-14/survey-plants.txt"));
	
			} catch (IOException e) {
				System.out.println("ERROR: Survey file could not be found or connection issues.");
			}
			
			Scanner lines = new Scanner(stream);
			while(lines.hasNextLine()){
				String[] parts = lines.nextLine().split("\\s+");
				survey.add(new Survey(Double.parseDouble(parts[0]), Double.parseDouble(parts[1]), parts[2],Integer.parseInt(parts[3])));
				
			}
			lines.close();
		}
		else if (type == "A") {
			BufferedReader stream = null;
			try {
				 stream = loadStreamUrl(new URL("http://www.hep.ucl.ac.uk/undergrad/3459/exam-data/2013-14/survey-animals.txt"));
	
			} catch (IOException e) {
				System.out.println("ERROR: Animal survey file could not be found or connection issues.");
			}
			
			Scanner lines = new Scanner(stream);
			while(lines.hasNextLine()){
				String[] parts = lines.nextLine().split("\\s+");
				ASurvey.add(new Survey(Double.parseDouble(parts[0]), Double.parseDouble(parts[1]), parts[2],-1));
				
			}
			lines.close();
			
		}

	}
}
