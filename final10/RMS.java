package final10;

import java.util.ArrayList;
import java.util.HashMap;

public class RMS implements Interface {

	private int year;

	public RMS(int year) {
		this.year = year;
	}

	public void lowtemp(ArrayList<DataReadings> list,
			HashMap<String, String> stationlist,
			HashMap<String, String> countrylist) {
		int sum = 0, numvar = 0;
		double RMS = 0, highrms = Double.NEGATIVE_INFINITY;
		String Highcountry = "";
		for (DataReadings e : list) {
			if (e.Year == year) {
				for (int i = 0; i < e.measurements.length; i++) {
					if (e.measurements[i] != -9999) {
						sum += e.measurements[i] * e.measurements[i];
						numvar++;

					}

				}
				RMS = Math.pow(sum / numvar, 0.5);
				//System.out.println(RMS);
				if (RMS > highrms) {
					highrms = RMS;
					//System.out.println(highrms);
					Highcountry = countrylist.get(e.ID.substring(0, 2));

				}
			}
		}
		System.out.println("The country with the highest RMS "+highrms+"temperature is "
				+ Highcountry);

	}

}