package exam1314;

import java.util.ArrayList;

public class MinMaxLatitude implements RegionSearch{
	private double min;
	private double max;
	
	public MinMaxLatitude(double min, double max){
		this.min = min;
		this.max = max;
	}
	
	@Override
	public ArrayList<Survey> locationSort(ArrayList<Survey> survey) {
		ArrayList<Survey> output = new ArrayList<>();
		for(Survey plant : survey){
			if(plant.getLatitude()<max&&plant.getLatitude()>min){
				output.add(plant);
			}
		}
		return output;
	}
}
