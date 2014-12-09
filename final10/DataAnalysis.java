package final10;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.Map.Entry;
/**
 * 
 * @author Adam Suhaj
 *
 */
public class DataAnalysis {

	public static ArrayList<DataReadings> dataReadings = new ArrayList<>();
	public static HashMap<Integer, ArrayList<DataReadings>> sortedReadings = new HashMap<>();
	public static HashMap<String, ArrayList<DataReadings>> sortedReadings2 = new HashMap<>();
	public static HashMap<String, String> sortedStation = new HashMap<>();
	public static HashMap<String, String> sortedCountry = new HashMap<>();

	public static void main(String[] args) {
		try {
			retreiveReadings(new URL(
					"http://www.hep.ucl.ac.uk/undergrad/3459/exam-data/2010-11/final/readings.txt"));
			retreiveCountry(new URL(
					"http://www.hep.ucl.ac.uk/undergrad/3459/exam-data/2010-11/final/countries.txt"));
			retreiveStation(new URL(
					"http://www.hep.ucl.ac.uk/undergrad/3459/exam-data/2010-11/final/stations.txt"));
			System.out.println(dataReadings.size());
			System.out.println(sortedReadings.keySet());
			
			dataWork();

			/*
			 * for(Entry<Integer, ArrayList<DataReadings>> z :
			 * sortedReadings.entrySet()){ for(DataReadings g : dataReadings){
			 * System.out.println("The month "+g.Month); } }
			 */

		

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	/**
	 * 
	 * @param url
	 * @throws IOException
	 * @return 
	 */
	public static void retreiveReadings(URL url) throws IOException {
		InputStream is = url.openStream();
		Scanner sc = new Scanner(is);
		while (sc.hasNextLine()) {
			String line = sc.nextLine();

			DataReadings kozo = new DataReadings(line);
			if (kozo.Type.equals("TMAX")) {
				dataReadings.add(new DataReadings(line));

				if (sortedReadings.containsKey(kozo.Year)) {
					sortedReadings.get(kozo.Year).add(kozo);
				} else {
					ArrayList<DataReadings> newlist = new ArrayList<>();
					newlist.add(kozo);
					sortedReadings.put(kozo.Year, newlist);
				}
				if (sortedReadings2.containsKey(kozo.ID)) {
					sortedReadings2.get(kozo.ID).add(kozo);
				} else {
					ArrayList<DataReadings> newlist = new ArrayList<>();
					newlist.add(kozo);
					sortedReadings2.put(kozo.ID, newlist);
				}
			}

		}
	}

	public static void retreiveCountry(URL url) throws IOException {

		InputStream is = url.openStream();
		Scanner sc = new Scanner(is);
		while (sc.hasNextLine()) {
			String rest = "";
			String[] splitLine = sc.nextLine().split("\\s+");
			for (int i = 1; i < splitLine.length; i++) {
				rest += splitLine[i] + " ";
				/*
				 * Can also use String Line = sc.NextLine();
				 * sortedCountry.put(Line.substring(0,2), Line.substring(2));
				 */

			}
			sortedCountry.put(splitLine[0], rest);

		}
	}

	public static void retreiveStation(URL url) throws IOException {

		InputStream is = url.openStream();
		Scanner sc = new Scanner(is);
		while (sc.hasNextLine()) {
			String rest = "";
			String[] splitLine = sc.nextLine().split("\\s+");
			for (int i = 1; i < splitLine.length; i++) {
				rest += splitLine[i] + " ";
			}
			sortedStation.put(splitLine[0], rest);

		}
	}

	public static void dataWork() {
		int high = Integer.MIN_VALUE;
		int year = -1, sum = 0, mean = 0, numvar = 0, lowmean = Integer.MAX_VALUE, yearmean = 0;

		String station = "", country = "", lowcountry = "";

		for (DataReadings e : dataReadings) {
			for (int i = 0; i < e.measurements.length; i++) {
				if (e.measurements[i] > high) {
					if (e.measurements[i] <= -9999) {
					} else {
						high = e.measurements[i];
						year = e.Year;
						station = sortedStation.get(e.ID);
						country = sortedCountry.get(e.ID.substring(0, 2));
					}
				}
				if (!(e.measurements[i] <= -9999)) {
					sum += e.measurements[i];
					numvar++;
				}
			}
			mean = sum / numvar;
			if (mean < lowmean) {
				lowmean = mean;
				lowcountry = sortedCountry.get(e.ID.substring(0, 2));

			}

		}

		for (Entry<Integer, ArrayList<DataReadings>> d : sortedReadings.entrySet()) {
			String yearcountry = "";
			int yearyear = 0;
			int yearsum = 0, yearnumvar = 0, yearlowmean = Integer.MAX_VALUE;
			int uksum = 0, francesum = 0, gersum = 0, swedsum = 0, spainsum = 0;
			HashMap<String, DataMean> plez = new HashMap<>();
			DataMean ukobject = new DataMean(null, 0,0);
			int uknumval = 0, francenumval = 0, gernumval = 0, swednumval = 0, spainnumval = 0;
			for (int j = 1; j < 13; j++) {
				for (DataReadings g : d.getValue()) {
					if (g.Month == j) {
						if (g.ID.contains("UK")) {
							//if(
							for (int i = 0; i < g.measurements.length; i++) {
								if (!(g.measurements[i] <= -9999)) {
									uksum += g.measurements[i];
									uknumval++;
								}
							}

						}
						if (g.ID.contains("GM")) {
							for (int i = 0; i < g.measurements.length; i++) {
								if (!(g.measurements[i] <= -9999)) {
									gersum += g.measurements[i];
									gernumval++;
								}
							}

						}
						if (g.ID.contains("SW")) {
							for (int i = 0; i < g.measurements.length; i++) {
								if (!(g.measurements[i] <= -9999)) {
									swedsum += g.measurements[i];
									swednumval++;
								}
							}

						}
						if (g.ID.contains("SP")) {
							for (int i = 0; i < g.measurements.length; i++) {
								if (!(g.measurements[i] <= -9999)) {
									spainsum += g.measurements[i];
									spainnumval++;
								}
							}

						}
						if (g.ID.contains("FR")) {
							for (int i = 0; i < g.measurements.length; i++) {
								if (!(g.measurements[i] <= -9999)) {
									francesum += g.measurements[i];
									francenumval++;
								}
							}

						}
						/*System.out.println("uk sum "+uksum);
						System.out.println("ger sum "+gersum);
						System.out.println("swed sum "+swedsum);
						System.out.println("spain sum "+spainsum);
						System.out.println("france sum "+francesum);*/
					}

					// System.out.println("the year is " + yearyear
					// + " the mean is " + yearmean);
				}
				 System.out.println("The measurement is "+high);
				 System.out.println("The year of measurement "+year);
				 System.out.println("The station "+station);
				 System.out.println("The country "+country);
				// System.out.println("the lowest mean is " +
				// mean+" in the "+lowcountry);

			}
			/*double [] sorted = new double[5];
			for(int i=0; i<5; i++){
				sorted[0] = uksum/uknumval;
				sorted[1] = gersum/gernumval;
				sorted[2] = swedsum/swednumval;
				sorted[3] = francesum/francenumval;
				sorted[4] = spainsum/spainnumval;

			}
			Arrays.sort(sorted);
			System.out.println(sorted[0]);*/
			ukobject = new DataMean("UK", uksum, uknumval);
			DataMean gerobject = new DataMean("GR", gersum, gernumval);
			DataMean swedobject = new DataMean("SW", swedsum, swednumval);
			DataMean franceobject = new DataMean("FR", francesum, francenumval);
			DataMean spainobject = new DataMean("SP", spainsum, spainnumval);
			plez.put("UK", ukobject);
			plez.put("GR", gerobject);
			plez.put("SW", swedobject);
			plez.put("FR", franceobject);
			plez.put("SP", spainobject);

			double mapmin = 0;
			double mapinf = Double.POSITIVE_INFINITY;
			String mapcountry = "";

			for (DataMean m8 : plez.values()) {
				mapmin = m8.mean / m8.number;
				//System.out.println(mapmin);

				if (mapmin < mapinf) {
					mapinf = mapmin;
					mapcountry = sortedCountry.get(m8.ID.substring(0, 2));
				}

			}
			/*System.out.println(plez.keySet());
			System.out.println(plez.get("UK"));
			System.out.println(plez.get("GR"));
			System.out.println(plez.get("SW"));
			System.out.println(plez.get("FR"));
			System.out.println(plez.get("SP"));
			*/System.out.println("for year " + d.getKey()
					+ " the lowest mean is from country " + mapcountry+"with mean "+mapinf);
		}
	}
}