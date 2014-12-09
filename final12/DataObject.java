package final12;

public class DataObject {
	private double[] dataPoints;
	public DataObject(String entry){
		String[] data = entry.split("\\s+");
		dataPoints=new double[3];
		dataPoints[0]= Double.parseDouble(data[0]);
		dataPoints[1]= Double.parseDouble(data[1]);
		dataPoints[2]= Double.parseDouble(data[2]);
	}
	public static void main(String[] args) {

	}
	public double getMin(){
		return dataPoints[0];
	}
	public double getMax(){
		return dataPoints[1];
	}
	public double getEvents(){
		return dataPoints[2];
	}
	public String toString(){
		return dataPoints[0]+","+dataPoints[1]+","+dataPoints[2];
	}
}


