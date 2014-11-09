package e12;
import java.io.*;
import java.net.*;
import java.util.*;
import java.text.*;
public class RainfallUK {
	public static List<DataObject> dataList = new ArrayList<DataObject>();
	public static String[] monthList = new String[]{"JAN","FEB","MAR","APR","MAY","JUN","JUL","AUG","SEP","OCT","NOV","DEC","ANN"};
	public static void main(String[] args) {	
		try {
            retrieveData("http://www.hep.ucl.ac.uk/undergrad/3459/exam-data/2012-13/HadEWP_monthly_qc.txt");
            System.out.println("************");
            findWettestMonth();
            System.out.println("************");
            findDriestandWettestYearforaMonth();
            System.out.println("************");
            findWettestThreeMonths();
            System.out.println("************");
            rmsaverage();
            System.out.println("************");
            percentage();
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("URL seems to be malformed");
        }

	}
	public static void retrieveData(String url) throws Exception{
		URL u = new URL(url);
		InputStream reader = u.openStream();
		Scanner sc = new Scanner(reader);
		sc.nextLine();
		sc.nextLine();
		sc.nextLine();
		sc.nextLine();
		while(sc.hasNextLine()){
			String line = sc.nextLine();
			dataList.add(new DataObject(line));
		}
	}
	public static void findWettestMonth(){
		double max = Double.NEGATIVE_INFINITY;
		int month = -1;
		int year = -1;
		for(DataObject data : dataList){//iterator that goes over all values
			for(int i = 0; i<data.dataPoints.length-1;++i){//only goes over the month value, that's why there is -1
				if(max<data.dataPoints[i]){
					max=data.dataPoints[i];
					year=data.getYear();
					month=i;
				}
			}
		}
		System.out.println("Wettest Month is " + monthList[month] + " with the amount " + max + " in year " + year + ".");
	}
	public static void findDriestandWettestYearforaMonth(){
		double[] driestMonth = new double[monthList.length-1];
		double[] wettestMonth = new double[monthList.length-1];
		for(int i = 0; i<driestMonth.length; i++){
			driestMonth[i]=Double.POSITIVE_INFINITY;
			wettestMonth[i]=Double.NEGATIVE_INFINITY;
		}
		int[] wettestMonthYear = new int[monthList.length-1];
		int[] driestMonthYear = new int[monthList.length-1];
		for(DataObject data : dataList){
			for(int i= 0; i<data.dataPoints.length-1;i++){
				if(data.getYear()<2012){
					if(driestMonth[i]>data.dataPoints[i]){
						driestMonth[i]=data.dataPoints[i];
	                       driestMonthYear[i] = data.getYear();
					}
					if(wettestMonth[i]<data.dataPoints[i]){
						wettestMonth[i]=data.dataPoints[i];
                        wettestMonthYear[i] = data.getYear();
					}
				} else if (i < 9) { // if its 2012 only allow months smaller than the future date
                    if (driestMonth[i] > data.dataPoints[i]) { // compare the stored value to the current value the iterator is looking at
                        driestMonth[i] = data.dataPoints[i]; // so if the driest current month is +INF save the current dataPoint
                        driestMonthYear[i] = data.getYear();
                    }
                    if (wettestMonth[i] < data.dataPoints[i]) { // compare the stored value to the current value
                        wettestMonth[i] = data.dataPoints[i];
                        wettestMonthYear[i] = data.getYear();
                    }
				}
			}
		}
		System.out.println("MONTH\tWETTEST\tAMOUNT\tDRIEST\tAMOUNT");
		for(int i = 0;i<monthList.length-1; i++){
			 System.out.println(monthList[i] + "\t" + wettestMonthYear[i] + "\t" + wettestMonth[i] + "\t" + driestMonthYear[i] + "\t" + driestMonth[i]);
		}
	}
	public static double sumMonths(double[] toSum) {
	        double sum = 0;
	        for (int i = 0; i < toSum.length; ++i) {
	            sum += toSum[i];
	        }
	        return sum;
	}
	public static void findWettestThreeMonths(){
		double[] lastThreeMonths = new double[]{Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY};
		int[] maxyears = new int[]{-1, -1, -1};
		int[] currentyears = new int[]{-1,-1,-1};
		int[] maxmonths = new int[]{-1,-1,-1};
		int[] currentmonths = new int[]{-1,-1,-1};
		double maxRainfall = Double.NEGATIVE_INFINITY;
		for(DataObject data : dataList){
			for(int i =0; i<data.dataPoints.length-1; i++){
				currentyears[0]=currentyears[1];// first shift the last 2 values back 
				currentyears[1]=currentyears[2];
				currentyears[2]=data.getYear(); // input newest year on last spot
				currentmonths[0]=currentmonths[1];; // same here
				currentmonths[1]=currentmonths[2];
				currentmonths[2]=i; // input current month
				lastThreeMonths[0]=lastThreeMonths[1];
				lastThreeMonths[1]=lastThreeMonths[2];
				lastThreeMonths[2]=data.dataPoints[i];// get last three months values
				double sum = sumMonths(lastThreeMonths);
				if(maxRainfall<sum){// if its bigger than the stored value then save the total, the months and the year
					maxRainfall=sum;
					maxyears=Arrays.copyOf(currentyears,3);
					maxmonths = Arrays.copyOf(currentmonths,3); // gotta copy them. Otherwise it only saves the reference and you dont get the right values
				}
			}
		}
		System.out.println("Total Rainfall: "+maxRainfall);
	    System.out.println("Occurred in " + maxyears[0] + " " + monthList[maxmonths[0]] + " " + maxyears[1] + " " + monthList[maxmonths[1]] + " " + maxyears[2] + " " + monthList[maxmonths[2]]);
	}
	public static void rmsaverage(){
		int n=0;
		double[] mean = new double[monthList.length-1];
		double[] rms = new double[monthList.length-1];// save value for each month square
		for(DataObject data:dataList){
			for(int i=0;i<data.dataPoints.length-1;i++){
				mean[i]+=data.dataPoints[i];
				rms[i]+=data.dataPoints[i]*data.dataPoints[i];
			}
			n++;
		}
		System.out.println("MONTH\tMEAN\tRMS");
		for (int i=0;i<monthList.length-1;i++){
            System.out.println(monthList[i] + "\t" + (mean[i] / (double) n) + "\t" + (Math.sqrt(rms[i] / (double) n)) + "\t");
		}
		
	}
	public static void percentage(){
		double[] smaller = new double [9];
		double[] bigger = new double[9];
		double[] monthsin2012 = new double[9];
		for(int i=0; i<dataList.get(dataList.size()-1).dataPoints.length-4;i++){
			monthsin2012[i]=dataList.get(dataList.size()-1).dataPoints[i];
		
		}
		for (int j = 0; j < dataList.size() - 1; ++j) { // go for size -1 to ignore 2012 
            for (int i = 0; i < dataList.get(j).dataPoints.length - 4; ++i) { // ignore every month except the last 3 since those are not valid in 2012
                if (monthsin2012[i] > dataList.get(j).dataPoints[i]) {
                    smaller[i]++; // if its smaller then add 1 to the specific month
                } else {
                    bigger[i]++; // if its bigger then add 1 to the specific month that is bigger than 201
                }
            }
        }
        for (int i = 0; i < bigger.length; ++i) {
            double biggerPercentage = bigger[i] / (bigger[i] + smaller[i]); // bigger divided by overall amount
            System.out.println("Month:" + monthList[i] + "\t" + "Bigger: " + biggerPercentage + " Smaller: " + (1 - biggerPercentage));
        }
	}
}
