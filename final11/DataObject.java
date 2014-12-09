package final11;

public class DataObject {
	public String ID;
	public int aCount;
	public int bCount;
	public int xCount;
	public int yCount;
	public int zCount;
	public DataObject(String entry){
		String[] splitLine = entry.split("\\s+");		

		if(splitLine.length==3){
			aCount =  Integer.parseInt(splitLine[1]);
			ID = splitLine[0];
			bCount = Integer.parseInt(splitLine[2]);
		}
		else{
			ID=splitLine[0];
			xCount =  Integer.parseInt(splitLine[1]);
			yCount =  Integer.parseInt(splitLine[2]);
			zCount =  Integer.parseInt(splitLine[3]);
		}
	}
	public static void main(String[] args) {

	}
	public String getID() {
		return ID;
	}
	public int getaCount() {
		return aCount;
	}
	public int getbCount() {
		return bCount;
	}
	public int getxCount() {
		return xCount;
	}
	public int getyCount() {
		return yCount;
	}
	public int getzCount() {
		return zCount;
	}

}
