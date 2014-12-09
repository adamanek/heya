package exam1112;

import java.util.ArrayList;
import java.util.HashMap;

public class Expectation implements Statistic {
	public double findValue(ArrayList<IllnessInt> illness, HashMap<String, Region> regions, String type, String id){
		return ProcessPop.perCapita(type)*regions.get(id).getPop();
	}
}
