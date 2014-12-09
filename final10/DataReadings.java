package final10;


public class DataReadings {
	public String ID;
	public int Year;
	public int Month;
	public String Type;
	public int[] measurements;
	
	
	public DataReadings(String entry){
		String[] splitLine = entry.split("\\s+");
		
		ID = splitLine[0];
		Year =Integer.parseInt(splitLine[1]);
		Month = Integer.parseInt(splitLine[2]);
		Type = splitLine[3];
		measurements = new int[31];
		for(int i=4; i<splitLine.length; i++){
			measurements[i-4] = Integer.parseInt(splitLine[i]);
		}
				
	}
}