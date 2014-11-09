package e11;

public class DataBeam {
	private double[] dataPoints;
	public DataBeam(String entry){
		String[] data = entry.split("\\s+");
		dataPoints= new double[3];
		for(int i =0; i<data.length; i++){
			dataPoints[i]=Double.parseDouble(data[i]);
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public double getZ(){
		return dataPoints[2];
	}
	public double getX(){
		return dataPoints[0];
	}
	public double getY(){
		return dataPoints[1];
	}

}
