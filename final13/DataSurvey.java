package final13;

public class DataSurvey {
	public double longitude;
	public double latitude;	
	public String ID;	
	public int height;
	
	public DataSurvey(String entry){
		
		String[] splitLine = entry.split("\\s+");
		if(splitLine.length==3){
			longitude =  Double.parseDouble(splitLine[1]);
			latitude = Double.parseDouble(splitLine[0]);
			ID = splitLine[2];
		}
		else{
			longitude =  Double.parseDouble(splitLine[1]);
			latitude = Double.parseDouble(splitLine[0]);
			ID = splitLine[2];
			height = Integer.parseInt(splitLine[3]);
		}
	}
	public Object getLat(){
		return latitude;
	}
	public Object getLon(){
		return longitude;
	}

}
