package final11;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;

public class DataAnalysis {
	public static ArrayList<DataObject> ab = new ArrayList<>();
	public static ArrayList<DataObject> xyz = new ArrayList<>();
	public static HashMap<String, Integer> pop = new HashMap<>();
	public static HashMap<String, String> reg = new HashMap<>();
	
	public static void main(String[] args) {
		try {
			loadAB(new URL("http://www.hep.ucl.ac.uk/undergrad/3459/exam-data/2011-12/final/occurrencesAB.txt"));
			loadXYZ(new URL("http://www.hep.ucl.ac.uk/undergrad/3459/exam-data/2011-12/final/occurrencesXYZ.txt"));
			loadPop(new URL("http://www.hep.ucl.ac.uk/undergrad/3459/exam-data/2011-12/final/populations.txt"));
			loadReg(new URL("http://www.hep.ucl.ac.uk/undergrad/3459/exam-data/2011-12/final/regions.txt"));
			dataFind();

			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void loadAB(URL url) throws IOException{
		InputStream is = url.openStream();
		Scanner sc = new Scanner(is);
		sc.nextLine();
		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			DataObject hey = new DataObject(line);
			ab.add(hey);
		}
	}
	public static void loadXYZ(URL url) throws IOException{
		InputStream is = url.openStream();
		Scanner sc = new Scanner(is);
		sc.nextLine();
		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			DataObject hey = new DataObject(line);
			xyz.add(hey);
		}
	}
	public static void loadPop(URL url) throws IOException{
		InputStream is = url.openStream();
		Scanner sc = new Scanner(is);
		sc.nextLine();
		while(sc.hasNextLine()){
			String[] parts = sc.nextLine().split("\\s+");
			pop.put(parts[0],Integer.parseInt(parts[1]));
		}
	}
	public static void loadReg(URL url) throws IOException{
		InputStream is = url.openStream();
		Scanner sc = new Scanner(is);
		sc.nextLine();
		while(sc.hasNextLine()){
			String[] parts = sc.nextLine().split(",");
			reg.put(parts[0],parts[1]);
		}
	}
	public static void dataFind(){
		int sumA=0, sumB=0, sumX=0, sumY=0,sumZ=0, countsame=0;
		int dA=0, dB=0, dX=0, dY=0, dZ=0;
		double high = Double.NEGATIVE_INFINITY,highSA=Double.NEGATIVE_INFINITY,highSB=Double.NEGATIVE_INFINITY,highSX=Double.NEGATIVE_INFINITY,highSY=Double.NEGATIVE_INFINITY,highSZ=Double.NEGATIVE_INFINITY;
		double low = Double.POSITIVE_INFINITY;
		double sumT=0;
		double sumD=0;
		String highN = null, lowN=null,highA=null,highB=null,highX=null,highY=null,highZ=null;
		double cory=0,corz=0, cora=0,corb=0;
		for(Entry<String, String> e: reg.entrySet()){
			double ssA=0, ssB=0,ssX=0,ssY=0,ssZ=0;
			for(DataObject f: ab){
				if(f.ID.equals(e.getKey())){
					for(Entry<String, Integer> g: pop.entrySet()){
						if(g.getKey().equals(e.getKey())){
							sumA+=pop.get(g.getKey());
							sumB+=pop.get(g.getKey());
							}
					}
					dA+=f.aCount;
					dB+=f.bCount;
					//System.out.println(dA);
					//System.out.println(pop.get(e.getKey()));
					
				}
				ssA = (f.aCount-(pop.get(e.getKey())*(float)dA/sumA))/(Math.pow(pop.get(e.getKey())*(float)dA/sumA, 0.5));
				if(ssA>highSA){
					highSA=ssA;
					highA = reg.get(e.getKey());
				}
				ssB = (f.bCount-(pop.get(e.getKey())*(float)dB/sumB))/Math.pow(pop.get(e.getKey())*(float)dB/sumB, 0.5);
				if(ssB>highSB){
					highSB=ssB;
					highB = reg.get(e.getKey());
				}
				
			}
			for(DataObject f: xyz){
				if(f.ID.equals(e.getKey())){
					for(Entry<String, Integer> g: pop.entrySet()){
						if(g.getKey().equals(e.getKey())){
							sumX+=pop.get(g.getKey());
							sumY+=pop.get(g.getKey());
							sumZ+=pop.get(g.getKey());
							}
					}
					dX+=f.xCount;
					dY+=f.yCount;
					dZ+=f.zCount;			
				}
				ssX = (f.xCount-(pop.get(e.getKey())*(float)dX/sumX))/Math.pow(pop.get(e.getKey())*(float)dX/sumX, 0.5);
				if(ssX>highSX){
					highSX=ssX;
					highX = reg.get(e.getKey());
				}
				ssY = (f.yCount-(pop.get(e.getKey())*(float)dY/sumY))/Math.pow(pop.get(e.getKey())*(float)dY/sumY, 0.5);
				if(ssY>highSY){
					highSY=ssY;
					highY = reg.get(e.getKey());
				}
				ssZ = (f.zCount-(pop.get(e.getKey())*(float)dZ/sumZ))/Math.pow(pop.get(e.getKey())*(float)dZ/sumZ, 0.5);
				if(ssZ>highSZ){
					highSZ=ssZ;
					highZ = reg.get(e.getKey());
				}
				
			}
			sumT=sumA+sumB+sumX+sumY+sumZ;
			//System.out.println(sumT);
			sumD=dA+dB+dX+dY+dZ;
			if((sumD/sumT)>high){
				high=sumD/sumT;
				highN=reg.get(e.getKey());
			}
			if((sumD/sumT)<low){
				low=sumD/sumT;
				lowN=reg.get(e.getKey());
			}
			//Interface inter = new Statistics();
			
			//System.out.println(inter.mean(dX, xyz.size()));
			
		}
		for(DataObject f:xyz){
			Interface inter = new Statistics();
			cory += (f.xCount-inter.mean(dX,xyz.size())*(f.yCount-inter.mean(dY, xyz.size())))/(xyz.size()*inter.stDeviationX(xyz)*inter.stDeviationY(xyz));
			corz += (f.xCount-inter.mean(dX,xyz.size())*(f.zCount-inter.mean(dZ, xyz.size())))/(xyz.size()*inter.stDeviationX(xyz)*inter.stDeviationZ(xyz));
			for(DataObject g:ab){
				if(f.ID.equals(g.ID)){
					countsame++;
				}
			}
			for(DataObject k:ab){
				/*if(f.ID.equals(k.ID)){
					countsame++;
				}*/
				if(f.ID.equals(k.ID)){
					cora = (f.xCount-inter.mean(dX,xyz.size())*(k.aCount-inter.mean(dA, ab.size())))/(countsame*inter.stDeviationX(xyz)*inter.stDeviationA(ab));
					corb = (f.xCount-inter.mean(dX,xyz.size())*(k.bCount-inter.mean(dB, ab.size())))/(countsame*inter.stDeviationX(xyz)*inter.stDeviationB(ab));
				}
			}
			}
		
		System.out.println("In the UK:");
		System.out.println("\n"+"The total population taken in consideration for disease A: "+sumA);
		System.out.println("Per capita occurence: "+(float)dA/sumA);
		System.out.println("\n"+"The total population taken in consideration for disease B: "+sumB);
		System.out.println("Per capita occurence: "+(float)dB/sumB);
		System.out.println("\n"+"The total population taken in consideration for disease X: "+sumX);
		System.out.println("Per capita occurence: "+(float)dX/sumX);
		System.out.println("\n"+"The total population taken in consideration for disease Y: "+sumY);
		System.out.println("Per capita occurence: "+(float)dY/sumY);
		System.out.println("\n"+"The total population taken in consideration for disease Z: "+sumZ);
		System.out.println("Per capita occurence: "+(float)dZ/sumZ);
		System.out.println("\n"+"The region with the highest per capita occurence of all diseases is: "+highN);
		System.out.println("\n"+"The region with the lowest per capita occurence of all diseases is: "+lowN);
		System.out.println("\n"+"Highest statistical significance for disease A is in: "+highA);
		System.out.println("\n"+"Highest statistical significance for disease B is in: "+highB);
		System.out.println("\n"+"Highest statistical significance for disease X is in: "+highX);
		System.out.println("\n"+"Highest statistical significance for disease Y is in: "+highY);
		System.out.println("\n"+"Highest statistical significance for disease Z is in: "+highZ);
		System.out.println("\n"+"The correlation between the diseases X and Y is: "+cory);
		System.out.println("The correlation between the diseases X and Z is: "+corz);
		System.out.println("The correlation between the diseases X and A is: "+cora);
		System.out.println("The correlation between the diseases X and B is: "+corb);
		System.out.println(countsame);
	}
	
}
