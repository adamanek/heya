package final10;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.Map.Entry;
public class DataAnalysisK {

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
           //System.out.println(dataReadings.size());
            //System.out.println(sortedReadings.keySet());

            dataWork();

            /*
             * for(Entry<Integer, ArrayList<DataReadings>> z :
             * sortedReadings.entrySet()){ for(DataReadings g : dataReadings){
             * System.out.println("The month "+g.Month); } }
             */
//			dataWork();
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static void retreiveReadings(URL url) throws IOException {
        InputStream is = url.openStream();
        Scanner sc = new Scanner(is);
        while (sc.hasNextLine()) {
            String line = sc.nextLine();

            DataReadings kozo = new DataReadings(line);
            if (kozo.Type.equals("TMAX")) {
                dataReadings.add(kozo);

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
                if (e.measurements[i] >= high) {
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
  		System.out.println("The highest measurement is "+high);
  		System.out.println("The year of measurement "+year);
  		System.out.println("The station "+station);
  		System.out.println("The country "+country);
        for (Entry<Integer, ArrayList<DataReadings>> d : sortedReadings.entrySet()) {

            HashMap<String, DataMean> plez = new HashMap<>();
            //System.out.println("BLA");
            for (DataReadings g : d.getValue()) {
                //System.out.println(g.ID+", "+g.Month+ ", " + g.Year);
                double tempSum = 0;
                int tempAmount = 0;
                for (int i = 0; i < g.measurements.length; i++) {
                    if (!(g.measurements[i] <= -9999)) {
                        tempSum += g.measurements[i];
                        tempAmount++;
                    }
                }
                
                String shortenedId = g.ID.substring(0, 2);
                if (plez.containsKey(shortenedId)) {
                    plez.get(shortenedId).mean += tempSum;
                    plez.get(shortenedId).number += tempAmount;
                } else {
                    plez.put(shortenedId, new DataMean(shortenedId, tempSum, tempAmount));
                }

            }
            //System.out.println("NEW");
            double mapmin = 0;
            double mapinf = Double.POSITIVE_INFINITY;
            String mapcountry = "";

            for (DataMean m8 : plez.values()) {
                mapmin = m8.mean / m8.number;
                if (mapmin < mapinf) {
                    mapinf = mapmin;
                    mapcountry = sortedCountry.get(m8.ID.substring(0, 2));
                }

            }

            System.out.println("for year " + d.getKey()
                    + " the lowest mean is from country " + mapcountry + "with mean " + mapinf);

        }

    }

}
