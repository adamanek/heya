package exam1112;

import java.util.ArrayList;
import java.util.HashMap;

public class ExcessOfOccurrences{
	public static Region findHighestSERegion(ArrayList<IllnessInt> illness,HashMap<String, Region> regions, String type) {
		Statistic expectation = new Expectation();
		double best = 0;
		String bestId = null;
		for (HashMap.Entry<String, Region> cursor : regions.entrySet()){
			String id = cursor.getValue().getId();
			int occurrences = 0;
			for (IllnessInt line : illness) {
				if(line.getId().equals(id)){
					if (type == "XXX"){occurrences = line.getXXX();}
					else if(type == "YYY"){occurrences = line.getYYY();}
					else if(type == "ZZZ"){occurrences = line.getZZZ();}
					else if (type == "AAA"){occurrences = line.getAAA();}
					else if(type == "BBB"){occurrences = line.getBBB();}
				}
			}
			double expected = expectation.findValue(illness, regions, type, id);
			double excessOfOccurrences = (occurrences-expected)/Math.sqrt(expected);
			if(excessOfOccurrences > best){
				best = excessOfOccurrences;
				bestId = id;
			}
		}
		return regions.get(bestId);
	}
	

}
