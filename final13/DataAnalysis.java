package final13;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.Map.Entry;


public class DataAnalysis {

	
	public static HashMap<String, ArrayList<DataSurvey>> sortedSurvey = new HashMap<>();
	public static HashMap<String, ArrayList<DataSurvey>> sortedAni = new HashMap<>();
	public static HashMap<String, String> sortedSpecies = new HashMap<>();
	public static HashMap<String, String> nameAni = new HashMap<>();
	public static ArrayList<DataSurvey> dataSurvey = new ArrayList<>();
	public static ArrayList<DataSurvey> dataAni = new ArrayList<>();	
	
	public static void main(String[] args) {
		
		try {
			
			System.out.println();
			retrieveSpecies(new URL("http://www.hep.ucl.ac.uk/undergrad/3459/exam-data/2013-14/species-plants.txt"));
			retrieveSurvey(new URL("http://www.hep.ucl.ac.uk/undergrad/3459/exam-data/2013-14/survey-plants.txt"));
			retrieveSurvey(new URL("http://www.hep.ucl.ac.uk/undergrad/3459/exam-data/2013-14/survey-animals.txt"));
			retrieveAni(new URL("http://www.hep.ucl.ac.uk/undergrad/3459/exam-data/2013-14/species-animals.txt"));
			latitudeMean(90,-30);
			latitudeMean(-30,-90);
			pointMean(-30.967,75.430);
			animalFind(-30.967,75.43);

			
		} catch (MalformedURLException e) {
			
			e.printStackTrace();
		} catch (Exception e) {
			
			e.printStackTrace();
		}	
	}
	
	public static void retrieveSurvey(URL url) throws Exception {
		
		double highMean = Double.NEGATIVE_INFINITY;
		double lowMean = Double.POSITIVE_INFINITY;
		String plantNamehigh = null;
		String plantNamelow = null;
		
		
		InputStream is = url.openStream();
		Scanner sc = new Scanner(is);
		
		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			String[] splitLine = line.split("\\s+");
			if(splitLine.length==4)
			{
				dataSurvey.add(new DataSurvey(line));
			
				DataSurvey kozo = new DataSurvey(line);
			
			
				if (sortedSurvey.containsKey(kozo.ID)) {
					sortedSurvey.get(kozo.ID).add(kozo);
				} 
				else {
					ArrayList<DataSurvey> newlist = new ArrayList<>();
					newlist.add(kozo);
				
					sortedSurvey.put(kozo.ID, newlist);
					}
			}
			else{
				dataAni.add(new DataSurvey(line));
				
				DataSurvey kozo = new DataSurvey(line);
			
			
				if (sortedAni.containsKey(kozo.ID)) {
					sortedAni.get(kozo.ID).add(kozo);
				} 
				else {
					ArrayList<DataSurvey> newlist = new ArrayList<>();
					newlist.add(kozo);
				
					sortedAni.put(kozo.ID, newlist);
					}
			}
		}
		for (Entry<String, ArrayList<DataSurvey>> e : sortedSurvey.entrySet()) {
			double sum = 0;
			double mean = 0;
			
			double highHeight = 0;
			double lowHeight = 0;
			
			
			
			for(DataSurvey its : e.getValue()){
			
				sum += its.height;
			}
			sortedSpecies.get(e.getKey()); //This works because the keys are literally the same.
			
				
			mean = sum/(e.getValue().size());	
			if(highMean<mean){highMean=mean;plantNamehigh = sortedSpecies.get(e.getKey());}
			if(lowMean>mean){lowMean=mean;plantNamelow = sortedSpecies.get(e.getKey());}
			
			
			System.out.println("****************");
			System.out.println(sortedSpecies.get(e.getKey()));
			//System.out.println(e.getKey());
			System.out.println("Number of specimens "+e.getValue().size());
			System.out.println("The mean is "+mean);
			
			
		}
		System.out.println("****************");
		System.out.println("The highest mean is "+highMean);
		System.out.println("The Plant name is "+plantNamehigh);
		System.out.println("the lowest mean is " + lowMean);
		System.out.println("the Plant name is " +plantNamelow);
		}
		
		
		
	/**
	 * 
	 * @param url
	 * @throws Exception
	 */
	public static void retrieveSpecies(URL url) throws Exception {		
		InputStream is = url.openStream();
		Scanner sc = new Scanner(is);	
		while (sc.hasNextLine()) {
				String line = sc.nextLine();
				//dataSpecies.add(new DataSpecies(line));				
				DataSpecies kozo = new DataSpecies(line);				
					sortedSpecies.put(kozo.ISpec,kozo.Name); 

		}
	
	}
	public static void retrieveAni(URL url) throws Exception {		
		InputStream is = url.openStream();
		Scanner sc = new Scanner(is);	
		while (sc.hasNextLine()) {
				String line = sc.nextLine();
				//dataSpecies.add(new DataSpecies(line));				
				DataSpecies kozo = new DataSpecies(line);				
					nameAni.put(kozo.ISpec,kozo.Name); 

		}
	
	}
	public static void latitudeMean(double maxLat, double minLat){
		LocationSorter latSort = new LatitudeSorter();
		double sum=0;
		double count=0;
		for(DataSurvey e : latSort.filterSpecimens(dataSurvey, maxLat, minLat)){

			if(e.ID.equals("NT")){
				sum+=e.height;
				count++;
			}
		}
		System.out.println("The mean is: "+sum/count+" cm.");
	}
	public static void pointMean(double lat, double lon){
		LocationSorter pointSort = new PointSorter();
		double sum1=0;
		double count1=0;
		for(DataSurvey e : pointSort.filterSpecimens(dataSurvey, lat, lon)){

			if(e.ID.equals("BN")){
				sum1+=e.height;
				count1++;
			}
		}
		System.out.println(count1);
		System.out.println("The mean within 50 km is: "+sum1/count1+" cm.");
		
	}
	public static void animalFind(double lat, double lon){
		LocationSorter aniSort= new AnimalFinder();
		System.out.println("*****************");
		System.out.println("These species are living exclusively withing 100 kms of the coordinates: ");
		for (Entry<String, ArrayList<DataSurvey>> e : sortedAni.entrySet()){
			double sumA=0;
			double count=0;
			for(DataSurvey f: e.getValue()){
				sumA++;
			}
			for(DataSurvey f: aniSort.filterSpecimens(dataAni, lat, lon)){
				if(f.ID.equals(e.getKey())){
					count++;
				}
			}
			if(sumA==count){
				System.out.println(nameAni.get(e.getKey()));
			}
		}
	}
}
