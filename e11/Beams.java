package e11;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.Map.Entry;

import e13.DataParticle;

public class Beams {
	private static int zeroc=0, hunc=0, twoc=0, thrc=0, fourc=0, fivec=0, sixc=0, sevc=0, eigc=0, ninec=0, thousc=0;
	private static double sumzx =0, sumhx=0, sumtwx=0, sumthx=0, sumfox=0, sumfix=0, sumsixx=0, sumsex=0, sumeix=0, sumnix=0, sumthox=0;
	private static double sumzy =0, sumhy=0, sumtwy=0, sumthy=0, sumfoy=0, sumfiy=0, sumsixy=0, sumsey=0, sumeiy=0, sumniy=0, sumthoy=0;
	private static int sumx;
	public static String[] coorList = new String[] { "X", "Y", "Z" };
	public static List<DataBeam> dataList = new ArrayList<DataBeam>();
	public static HashMap<Integer, ArrayList<DataBeam>> list = new HashMap<>();

	public static void main(String[] args) {
		try {
			loadData(new URL(
					"http://www.hep.ucl.ac.uk/undergrad/3459/exam-data/2011-12/midterm/bpm.txt"));
			nSep();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void loadData(URL url) throws IOException {
		int count = 0;
		InputStream is = url.openStream();
		Scanner scanner = new Scanner(is);
		scanner.nextLine();
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			dataList.add(new DataBeam(line));
			DataBeam heya = new DataBeam(line);
			//seen the Z value before, retrieve all old values and adds the object to the list
			if (list.containsKey((int)heya.getZ())) {
				list.get((int)heya.getZ()).add(heya);
			} else { //unseen z value, new list of objects associated with that z value, and adds it to to the HashMap
				ArrayList<DataBeam> tempList = new ArrayList<DataBeam>();
				tempList.add(heya);
				list.put((int) heya.getZ(), tempList);
			}
			


		}
		for (Entry<Integer, ArrayList<DataBeam>> e : list.entrySet()) {//Creates entry interface that iterates over the keys (BPM) that includes a list of DataObject that store (x,y)
			double sumx=0;
			double sumy=0;
			double ri=0;
			double sumR=0;
			double ris=0;
			double risF=Double.NEGATIVE_INFINITY;
			System.out.println("\n"+"The distance of the BPM: "+e.getKey());//Extracts the key set(BPM)
			System.out.println("The number of particles detected at this BPM: "+e.getValue().size());//prints how many objects there are in the list for that key

			for(DataBeam its: e.getValue()){// To go over the individual x and y values of for that key (BPM)
				sumx+=its.getX();
				sumy+=its.getY();
				ri=Math.pow((its.getX()+(double)(sumx/e.getValue().size())),2)+Math.pow((its.getY()+(double)(sumy/e.getValue().size())), 2);
				sumR+=ri;
				ris=Math.sqrt(ri);
				if(risF<ris){risF=ris;}
			}
			
			System.out.println("The rms is: " +Math.sqrt(sumR/e.getValue().size()));
			System.out.println("The average of x is: " +sumx/e.getValue().size());
			System.out.println("The average of y is: " +sumy/e.getValue().size());
			System.out.println("The sum of X is: "+sumx);
			System.out.println("The sum of Y is: "+sumy);
			System.out.println("The biggest radius is: "+ris);
		}
	}

	public static void nSep() {
		HashSet<Integer> noDup = new HashSet<>();
		for (DataBeam data : dataList) {
			noDup.add((int) data.getZ());
		}
		System.out.println("The number of different BPMs is: "+noDup.size());
		System.out.println("The list of the different BPM distances is: "+list.keySet());
		//System.out.println(noDup);

	}

	/*public static void nPar(){

		for(DataBeam no : dataList){
			if(no.getZ()==(int)-0){ zeroc++; sumzx+=no.getX(); sumzy+=no.getY();}
			if(no.getZ()==(int)100){ hunc++; sumhx=no.getX();sumhy=no.getY();}
			if(no.getZ()==(int)200){ twoc++; sumtwx=no.getX();sumtwy=no.getY();}
			if(no.getZ()==(int)300){ thrc++; sumthx = no.getX();sumthy = no.getY();}
			if(no.getZ()==(int)400){ fourc++; sumfox = no.getX();sumfoy = no.getY();}
			if(no.getZ()==(int)500){ fivec++; sumfix = no.getX();sumfiy = no.getY();}
			if(no.getZ()==(int)600){ sixc++; sumsixx = no.getX();sumsixy = no.getY();}
			if(no.getZ()==(int)700){ sevc++; sumsex = no.getX();sumsey = no.getY();}
			if(no.getZ()==(int)800){ eigc++; sumeix = no.getX();sumeiy = no.getY();}
			if(no.getZ()==(int)900){ ninec++; sumnix = no.getX();sumniy = no.getY();}
			if(no.getZ()==(int)1000){ thousc++; sumthox=no.getX();sumthoy=no.getY();}
			}
		System.out.println("\n"+"Number of particle at BMP 0: "+zeroc);
		System.out.println("Average x value is: "+sumzx/zeroc+" and average y value is: "+sumzy/zeroc);
		System.out.println("\n"+"Number of particle at BMP 100: "+hunc);
		System.out.println("Average x value is: "+sumhx/hunc+" and average y value is: "+sumhy/hunc);
		System.out.println("\n"+"Number of particle at BMP 200: "+twoc);
		System.out.println("Average x value is: "+sumtwx/twoc+" and average y value is: "+sumtwy/twoc);
		System.out.println("\n"+"Number of particle at BMP 300: "+thrc);
		System.out.println("Average x value is: "+sumthx/thrc+" and average y value is: "+sumthy/thrc);
		System.out.println("\n"+"Number of particle at BMP 400: "+fourc);
		System.out.println("Average x value is: "+sumfox/fourc+" and average y value is: "+sumfoy/fourc);
		System.out.println("\n"+"Number of particle at BMP 500: "+fivec);
		System.out.println("Average x value is: "+sumfix/fivec+" and average y value is: "+sumfiy/fivec);
		System.out.println("\n"+"Number of particle at BMP 600: "+sixc);
		System.out.println("Average x value is: "+sumsixx/sixc+" and average y value is: "+sumsixy/sixc);
		System.out.println("\n"+"Number of particle at BMP 700: "+sevc);
		System.out.println("Average x value is: "+sumsex/sevc+" and average y value is: "+sumsey/sevc);
		System.out.println("\n"+"Number of particle at BMP 800: "+eigc);
		System.out.println("Average x value is: "+sumeix/eigc+" and average y value is: "+sumeiy/eigc);
		System.out.println("\n"+"Number of particle at BMP 900: "+ninec);
		System.out.println("Average x value is: "+sumnix/ninec+" and average y value is: "+sumniy/ninec);
		System.out.println("\n"+"Number of particle at BMP 1000: "+thousc);
		System.out.println("Average x value is: "+sumthox/thousc+" and average y value is: "+sumthoy/thousc);

	}*/

}

	


