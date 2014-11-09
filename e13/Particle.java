package e13;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;


public class Particle {
	
	public static HashMap<String, ArrayList<DataParticle>> dataList = new HashMap<String, ArrayList<DataParticle>>();
	private static int signalRegion = 0;
	private static int backgroundRegion = 0;
	public static void main(String[] args) {
		
		
		
		try{
			retrieveData(new URL("http://www.hep.ucl.ac.uk/undergrad/3459/exam-data/particles.txt"));

			System.out.println(dataList.get("B13").get(0).matrix[0][0]);
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
			
			if(line.contains("E")) {
				String[] headerLine = line.split("\\s+");
				
				int numberOfLines = Integer.parseInt(headerLine[1]);
				String[] dataLines = new String[numberOfLines];
				
				for (int i=0; i< numberOfLines; i++) {
					dataLines[i] =scanner.nextLine();
				}
				
				if (dataList.containsKey(headerLine[2])) {
					dataList.get(headerLine[2]).add(new DataParticle(headerLine[2], dataLines));
				} else {
					
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
		for (Entry<String, ArrayList<DataParticle>> e : dataList.entrySet()) {
			ArrayList<DataParticle> tempList = e.getValue();
			
			for(DataParticle data: tempList) {
				for (int i=0 ; i<data.matrix[0].length; ++i) {
					for (int j =0; j< data.matrix[0].length; ++j) {
						if (j!=i) { 
							if(data.matrix[i][0] != data.matrix[j][0]) {
								double mass=Math.sqrt(calculateInvariantMass(data.matrix[i], data.matrix[j]));
								System.out.println(mass);
								if(mass>=8 && mass<10){									
									signalRegion++;
								}
								if(mass>=11 && mass<15){									
									backgroundRegion++;
								}
	
						}
					}
				}
			}
		}
			System.out.println(signalRegion);
			System.out.println(backgroundRegion);
	}

}
}