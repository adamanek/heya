package final13;

import java.util.ArrayList;



public class PointSorter implements LocationSorter {
	
	private final int r = 6371;

	public ArrayList<DataSurvey> filterSpecimens(ArrayList<DataSurvey> specimens, double maxLat, double minLat) {
		ArrayList<DataSurvey> output = new ArrayList<>();
		
		
		for(DataSurvey specimen : specimens){
			
			double d = 0;
		//maxLat is latitude and minLat is longitude.
			d = 2*r*Math.asin(Math.pow((Math.pow(Math.sin(Math.toRadians(((double)specimen.getLat()-maxLat)/2)), 2)+Math.cos(Math.toRadians(maxLat))*Math.cos(Math.toRadians((double)specimen.getLat()))*
				Math.pow(Math.sin(Math.toRadians(((double)specimen.getLon()-minLat)/2)), 2)),0.5));
			if(d<50.0){
				output.add(specimen);


			}	

		}
		return output;

	}
}
