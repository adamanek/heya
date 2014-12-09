package exam1314;

import java.util.ArrayList;

public class DistanceLocation implements RegionSearch{
	private double latitude;
	private double longitude;
	private double distance;
	private final int r = 6371;
	
	public DistanceLocation(double latitude, double longitude, double distance) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.distance = distance;
		
	}
	
	@Override
	public ArrayList<Survey> locationSort(ArrayList<Survey> survey) {
		ArrayList<Survey> output = new ArrayList<>();
		
		for(Survey plant : survey){
			double h = haversin(latitude-plant.getLatitude())+Math.cos(latitude)*Math.cos(plant.getLatitude())*haversin(longitude-plant.getLongitude());
			double d = 2*r*Math.asin(Math.sqrt(h));
			if(d<distance){
				output.add(plant);
			}
		}
		return output;
	}
	public double haversin(double angle) {
		return (1-Math.cos(angle))/2;	
	}
}
