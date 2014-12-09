package final12;

public class HiggsObject {
	public double counts;
	public String channel;
	public HiggsObject(String entry){
		String[] data = entry.split("\\s+");
		channel = data[0];
		counts = Double.parseDouble(data[1]);
	}
	public static void main(String[] args) {

	}
	public String getChan(){
		return channel;
	}
	public double getEnergy(){
		return counts;
	}
}
