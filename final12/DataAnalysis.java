package final12;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;

public class DataAnalysis {
	public static ArrayList<DataObject> dataGG = new ArrayList<>();
	public static ArrayList<DataObject> dataZZ = new ArrayList<>();
	public static HashMap<String, ArrayList<HiggsObject>> higgs = new HashMap<>();
	public  static ArrayList<HiggsObject> higList = new ArrayList<>();
	public  static ArrayList<DataObject> countGG = new ArrayList<>();


	public static void main(String[] args) {

		try {
			loadGG(new URL("http://www.hep.ucl.ac.uk/undergrad/3459/exam-data/2011-12/backgroundGG.txt"));
			loadZZ(new URL("http://www.hep.ucl.ac.uk/undergrad/3459/exam-data/2011-12/backgroundZZ.txt"));
			loadHiggs(new URL("http://www.hep.ucl.ac.uk/undergrad/3459/exam-data/2011-12/higgsData.txt"));
			System.out.println("The number of events between 120 and 140 GeV for channel GG: ");
			range(dataGG);
			System.out.println("\n"+"The number of events between 120 and 140 GeV for channel ZZ: ");
			range(dataZZ);
			binning(dataGG,dataZZ);
			rangeCh();
			Interface inter = new Predictions();
			System.out.println("************************");
			System.out.println("For the channel h -> ZZ: ");
			inter.prediction(179.5, 110.5, 1, 6);
			System.out.println("************************");
			System.out.println("For the channel h -> GG: ");
			inter.prediction(179.5, 110.5, 2, 100);

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		

	}
	public static void loadGG(URL url) throws IOException{
		InputStream is = url.openStream();
		Scanner sc = new Scanner(is);
		sc.nextLine();
		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			DataObject hey = new DataObject(line);
			dataGG.add(hey);
		}
	}
	public static void loadZZ(URL url) throws IOException{
		InputStream is = url.openStream();
		Scanner sc = new Scanner(is);
		sc.nextLine();
		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			DataObject hey = new DataObject(line);
			dataZZ.add(hey);
		}
	}
	public static void loadHiggs(URL url) throws IOException{
		InputStream is = url.openStream();
		Scanner sc = new Scanner(is);
		sc.nextLine();
		while(sc.hasNextLine()){
			String line = sc.nextLine();
			higList.add(new HiggsObject(line));
			
			HiggsObject kozo = new HiggsObject(line);
			
			
			if (higgs.containsKey(kozo.getChan())) {
				higgs.get(kozo.getChan()).add(kozo);
			} else {
				ArrayList<HiggsObject> newlist = new ArrayList<>();
				newlist.add(kozo);				
				higgs.put(kozo.getChan(), newlist);}
		}
	}
	public static void binning(ArrayList<DataObject> g, ArrayList<DataObject> z){
		double countupg=0;
		double countupz=0;
		for(Entry<String, ArrayList<HiggsObject>> h : higgs.entrySet()){	
			for(int i=100; i<200; i++){
			int sum = 0;
				for(HiggsObject its : h.getValue()){
					if(its.counts>i && its.counts<(i+1)){
						sum += 1;	
					}

				}
				if(h.getKey().equals("ZZ")){
					if(sum!=0){
						countupz+= (z.get(i-100).getEvents()-sum)+(sum*Math.log(sum/z.get(i-100).getEvents()));
						
					}
				}
				if(h.getKey().equals("GG"))	{
					//for(int j=1; j<100;j++){
						countupg+= (g.get(i-100).getEvents()-sum)+(sum*Math.log(sum/g.get(i-100).getEvents()));

					
				}
				System.out.println("Channel: "+h.getKey());
				System.out.println("For bin between "+i+" and "+(i+1));
				System.out.println("The number of candidates in the bin is "+sum);
				System.out.println("*****************");

				
			}

		}
		System.out.println("Log-likelyhood of GG: "+countupg);
		System.out.println("Log-likelyhood of ZZ: "+countupz);
		System.out.println("*********************");
		for(DataObject its :g){
				countGG.add(its);			
		}
		for(DataObject its:z){
				countGG.add(its);
		}
		ArrayList<Counting> sumtot = new ArrayList<>();
		for(int i=100; i<200; i++){
			double sumtotal=0;
			for(DataObject e:countGG){
				if(e.getMin()==(i)){
					sumtotal+=e.getEvents();
					sumtot.add(new Counting(i,(i+1), e.getEvents()));
				}
			}
			System.out.println("In the range "+i+" to "+(i+1)+" there are in total: "+sumtotal+" events. ");
		}
		System.out.println("******************");
		System.out.println("Total predicted number of events: ");
		Interface in = new Predictions();
		for(double i=110.5; i<179.5; i+=1){
			double countg = 0.5*(in.calculation(200,2,100,i)+in.calculation(100,2,100,i))*((200)-100);
			double countz = 0.5*(in.calculation(200,1,6,126)+in.calculation(100,1,6,i))*((200)-100);
			//System.out.println("The number of predicted events between "+i+" and "+(i+1)+"GeV is "+ (countg+countz));
			for(Counting h:sumtot){
				if(i>h.getBot() && i<h.getTop()){
					//countupg+= (g.get(i-100).getEvents()-sum)+(sum*Math.log(sum/g.get(i-100).getEvents()));
				}
			}
		}
	}
	public static void rangeCh(){
		int sum=0;
		for(Entry<String, ArrayList<HiggsObject>> h : higgs.entrySet()){
			for(HiggsObject its : h.getValue()){
				if(its.counts>120 && its.counts<240){
					sum++;
				}
			}
			System.out.println("***************");
			System.out.println("In the channel: "+h.getKey()+" the number of events between 120 and 240 GeV is: "+sum);
		}
	}
	public static void range(ArrayList<DataObject> object){
		double count = 0;
		for(DataObject e : object){
			if(e.getMin()>=120 && e.getMin()<140){
				count +=e.getEvents();
			}
		}

		System.out.println(count);
	}
	 
}
