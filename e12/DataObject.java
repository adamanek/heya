package e12;

public class DataObject {
	public int year;
	public double[] dataPoints;
	public DataObject(String entry){
		String[] data = entry.split("\\s+");
		dataPoints= new double[13];
		year=Integer.parseInt(data[1]);//
		for(int i =2; i<data.length; i++){
			dataPoints[i-2]=Double.parseDouble(data[i]);
		}
		
	}
	public double getAnnualAmmount(){
		return dataPoints[12];
	}
	public int getYear(){
		return year;
	}

	public static void main(String[] args) {

	}

}
