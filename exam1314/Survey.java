package exam1314;

public class Survey {
	private double latitude;
	private double longitude;
	private String id;
	private int height;
	
	public Survey(double latitude, double longitude, String id, int height){
		this.latitude = latitude;
		this.longitude = longitude;
		this.id = id;
		this.height = height;
	}

	/**
	 * @return the latitude
	 */
	public double getLatitude() {
		return latitude;
	}

	/**
	 * @return the longitude
	 */
	public double getLongitude() {
		return longitude;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}
	public String toString() {
		return id+" "+latitude+" "+longitude+" "+height; 
	}
	
}
