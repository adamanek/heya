package final10;

import java.util.ArrayList;
import java.util.HashMap;

public class LowestTemp implements Interface {

	public void lowtemp(ArrayList<DataReadings> list,
			HashMap<String, String> stationlist,
			HashMap<String, String> countrylist) {
		int high = Integer.MIN_VALUE;
		int yeear = 0, sum = 0, mean = 0, numvar = 0, lowmean = Integer.MAX_VALUE, yearmean = 0;
		String station = "", countryy = "", lowcountry = "";

		for (DataReadings e : list) {
			for (int i = 0; i < e.measurements.length; i++) {
				/*if (e.measurements[i] != -9999) {
					high = e.measurements[i];
					yeear = e.Year;
					station = stationlist.get(e.ID);
					countryy = countrylist.get(e.ID.substring(0, 2));

				}*/
				if (e.measurements[i] != -9999) {
					sum += e.measurements[i];
					numvar++;

				}
			}
			mean = sum / numvar;
			if (mean < lowmean) {
				lowmean = mean;
				lowcountry = countrylist.get(e.ID.substring(0, 2));
			}
		}
		System.out.println("The country with the lowest temperature is "
				+ lowcountry);
	}

}
