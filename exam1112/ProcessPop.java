package exam1112;

import java.io.*;
import java.net.URL;
import java.util.*;


public class ProcessPop {

	static HashMap<String, Region> regions = new HashMap<>();
	static HashMap<String, Integer> pops = new HashMap<>();
	static ArrayList<IllnessInt> illness = new ArrayList<>();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		loadRegions();
		loadIllness();
		int consider = findPopConsideration("all");
		System.out.println("Total UK population under consideration is: "+consider);
		consider = findPopConsideration("XXX");
		System.out.println("Total XXX, YYY, ZZZ survey population under consideration is: "+consider);
		consider = findPopConsideration("AAA");
		System.out.println("Total AAA, BBB survey population under consideration is: "+consider);
		
		System.out.println(" - ");
		
		System.out.println("XXX per capita is: "+perCapita("XXX"));
		System.out.println("YYY per capita is: "+perCapita("YYY"));
		System.out.println("ZZZ per capita is: "+perCapita("ZZZ"));
		System.out.println("AAA per capita is: "+perCapita("AAA"));
		System.out.println("BBB per capita is: "+perCapita("BBB"));
		
		System.out.println(" - ");
		
		System.out.println("Highest XXX is: "+findHighestRegion("XXX").getName());
		System.out.println("Highest YYY is: "+findHighestRegion("YYY").getName());
		System.out.println("Highest ZZZ is: "+findHighestRegion("ZZZ").getName());
		System.out.println("Highest AAA is: "+findHighestRegion("AAA").getName());
		System.out.println("Highest BBB is: "+findHighestRegion("BBB").getName());
		
		System.out.println(" - ");
		
		System.out.println("Lowest XXX is: "+findLowestRegion("XXX").getName());
		System.out.println("Lowest YYY is: "+findLowestRegion("YYY").getName());
		System.out.println("Lowest ZZZ is: "+findLowestRegion("ZZZ").getName());
		System.out.println("Lowest AAA is: "+findLowestRegion("AAA").getName());
		System.out.println("Lowest BBB is: "+findLowestRegion("BBB").getName());
		
		System.out.println(" - ");
		
		System.out.println(ExcessOfOccurrences.findHighestSERegion(illness, regions, "XXX").getName());
		System.out.println(ExcessOfOccurrences.findHighestSERegion(illness, regions, "YYY").getName());
		System.out.println(ExcessOfOccurrences.findHighestSERegion(illness, regions, "ZZZ").getName());
		System.out.println(ExcessOfOccurrences.findHighestSERegion(illness, regions, "AAA").getName());
		System.out.println(ExcessOfOccurrences.findHighestSERegion(illness, regions, "BBB").getName());

		System.out.println(" - ");
		
		
	}
	
	
	private static Region findHighestRegion(String type){
		int top = 0;
		String topID = null;
	
		for(IllnessInt ill : illness) {
			//System.out.println(ill.getClass().getSimpleName());
			if (type == "XXX"){if(ill.getXXX()>top){top = ill.getXXX();topID = ill.getId();}}
			else if(type == "YYY"){if(ill.getYYY()>top){top = ill.getXXX();topID = ill.getId();}}
			else if(type == "ZZZ"){if(ill.getZZZ()>top){top = ill.getXXX();topID = ill.getId();}}
			else if (type == "AAA"){if(ill.getAAA()>top && ill.getAAA()!=-1){top = ill.getAAA();topID = ill.getId();}}
			else if(type == "BBB"){if(ill.getBBB()>top && ill.getBBB()!=-1){top = ill.getBBB();topID = ill.getId();}}
		}
		
	
		return regions.get(topID);
	}
	
	private static Region findLowestRegion(String type){
		int top = 1000000000;
		String topID = null;
		for(IllnessInt ill : illness) {
			//System.out.println(ill.getClass().getSimpleName());
			if (type == "XXX"){if(ill.getXXX()<top){top = ill.getXXX();topID = ill.getId();}}
			else if(type == "YYY"){if(ill.getYYY()<top){top = ill.getXXX();topID = ill.getId();}}
			else if(type == "ZZZ"){if(ill.getZZZ()<top){top = ill.getXXX();topID = ill.getId();}}
			else if (type == "AAA"){if(ill.getAAA()<top && ill.getAAA()!=-1){top = ill.getAAA();topID = ill.getId();}}
			else if(type == "BBB"){if(ill.getBBB()<top && ill.getBBB()!=-1){top = ill.getBBB();topID = ill.getId();}}
		}

		return regions.get(topID);
	}
	
	private static int findPopConsideration(String option){
		int sum = 0;
		if (option == "all") {
			// Total UK pop
			for (HashMap.Entry<String, Region> cursor : regions.entrySet()){
				sum += cursor.getValue().getPop();
			}
		}
		else if (option == "AAA"||option == "BBB") {
			// Not Total UK pop
			for (HashMap.Entry<String, Region> cursor : regions.entrySet()){
				String id = cursor.getValue().getId();
				for(IllnessInt ill : illness) {
					//System.out.println(ill.getClass().getSimpleName());
					if (id.equals(ill.getId()) && ill.getClass().getSimpleName().equals("IllnessPlus")){
						sum += cursor.getValue().getPop();
					}
				}
				
			}
		}
		else if (option == "XXX" || option == "YYY"|| option == "ZZZ") {
			// Total UK pop
			sum = findPopConsideration("all");
		}
		return sum;
	}
	
	public static float perCapita(String type) {
		int sum = 0;
		for (HashMap.Entry<String, Region> cursor : regions.entrySet()){
			String id = cursor.getValue().getId();
			for(IllnessInt ill : illness) {
				//System.out.println(ill.getClass().getSimpleName());
				if (id.equals(ill.getId()) && ill.getClass().getSimpleName().equals("IllnessPlus")){
					if (type == "XXX"){sum += ill.getXXX();}
					else if(type == "YYY"){sum += ill.getYYY();}
					else if(type == "ZZZ"){sum += ill.getZZZ();}
					else if (type == "AAA"){sum += ill.getAAA();}
					else if(type == "BBB"){sum += ill.getBBB();}
				}
				else if (id.equals(ill.getId())) {
					if (type == "XXX"){sum += ill.getXXX();}
					else if(type == "YYY"){sum += ill.getYYY();}
					else if(type == "ZZZ"){sum += ill.getZZZ();}
				}
			}
			
		}
		return ((float)sum/(float)findPopConsideration(type));
	}
	
	private static BufferedReader loadStreamFromURL(URL url) throws IOException {
		InputStream is = url.openStream();			
		InputStreamReader isr = new InputStreamReader(is);
		return new BufferedReader(isr);	
	}
	
	static void loadRegions() {
		BufferedReader stream = null;
		
		try {
			stream = loadStreamFromURL(new URL("http://www.hep.ucl.ac.uk/undergrad/3459/exam-data/2011-12/final/populations.txt"));
		} catch (IOException e) {
			System.out.println("ERROR: Populations file could not be found, or connection issues.");
		}
		
		Scanner lines = new Scanner(stream);
		lines.nextLine(); // Skip header
		
		while (lines.hasNextLine()) {
			String[] parts = lines.nextLine().split("\\s+");
			pops.put(parts[0], Integer.parseInt(parts[1]));
		}
		lines.close();
		stream = null;
		
		try {
			stream = loadStreamFromURL(new URL("http://www.hep.ucl.ac.uk/undergrad/3459/exam-data/2011-12/final/regions.txt"));
		} catch (IOException e) {
			System.out.println("ERROR: Regions file could not be found, or connection issues.");	
		}
		
		lines = new Scanner(stream);
		lines.nextLine(); // Skip header
		
		while (lines.hasNextLine()) {
			String[] parts = lines.nextLine().split(",");
			regions.put(parts[0], new Region(parts[0], parts[1], pops.get(parts[0])));
		}
		lines.close();
		
	}
	
	static void loadIllness() {
		BufferedReader xyzStream = null;
		BufferedReader abStream = null;
		try {
			xyzStream = loadStreamFromURL(new URL("http://www.hep.ucl.ac.uk/undergrad/3459/exam-data/2011-12/final/occurrencesXYZ.txt"));
		} catch (IOException e) {
			System.out.println("ERROR: OccurrencesXYZ file could not be found, or connection issues.");
		}
		try {
			abStream = loadStreamFromURL(new URL("http://www.hep.ucl.ac.uk/undergrad/3459/exam-data/2011-12/final/occurrencesAB.txt"));
		} catch (IOException e) {
			System.out.println("ERROR: OccurrencesAB file could not be found, or connection issues.");
		}
		
		Scanner xyzLines = new Scanner(xyzStream);
		Scanner abLines = new Scanner(abStream);
		String[] store = null;
		
		while (xyzLines.hasNextLine()) {
			String[] xyzParts = xyzLines.nextLine().split("\\s+");
			String[] abParts;
			if(store == null) {
				abParts = abLines.nextLine().split("\\s+");
			}
			else {
				abParts = store;
			}
			if(xyzParts[0].equals(abParts[0])){
				illness.add(new IllnessPlus(xyzParts[0],
						Integer.parseInt(xyzParts[1]), 
						Integer.parseInt(xyzParts[2]),
						Integer.parseInt(xyzParts[3]),
						Integer.parseInt(abParts[1]),
						Integer.parseInt(abParts[2])));
				store = null;
			}
			else{
				illness.add(new Illness(xyzParts[0],
						Integer.parseInt(xyzParts[1]), 
						Integer.parseInt(xyzParts[2]), 
						Integer.parseInt(xyzParts[3])));
				store = abParts;
			}
		}
		abLines.close();
		xyzLines.close();
		
	}

}
