package final13;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.Map.Entry;

public interface LocationSorter {
	
	public ArrayList<DataSurvey> filterSpecimens(ArrayList<DataSurvey> specimens, double maxLat, double minLat);
	
	

}
