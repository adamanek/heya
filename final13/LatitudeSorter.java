package final13;

import java.util.ArrayList;

public class LatitudeSorter implements LocationSorter {

	public ArrayList<DataSurvey> filterSpecimens(ArrayList<DataSurvey> specimens, double maxLat, double minLat) {
		ArrayList<DataSurvey> output = new ArrayList<>();
		double sum=0;
		double count =0;
		for(DataSurvey specimen : specimens){
			if((double)specimen.getLat()>minLat && (double)specimen.getLat()<maxLat){
				output.add(specimen);			
			}
			
		
		}
		for(DataSurvey e : output){
			if(e.ID.equals("NT")){
				sum+=e.height;
				count++;
			}
		}
		
		return output;
	}
	

}
