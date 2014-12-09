package final13;

public class DataSpecies {
	
	public String ISpec;
	public String Name;
	
	public DataSpecies(String entry) {
		String[] splitLine = entry.split("\\s+");
		
		ISpec = splitLine[0];
		Name = splitLine[1]+" "+splitLine[2];
	}
}
