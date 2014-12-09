package exam1112;

import java.util.ArrayList;
import java.util.HashMap;

public interface Statistic {
	double findValue(ArrayList<IllnessInt> illness, HashMap<String, Region> regions, String type, String id);
}
