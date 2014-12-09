package exam1112;

public class Region {
	private String id;
	private String name;
	private int pop;
	
	public Region (String id, String name, int pop) {
		this.id = id;
		this.name  = name;		
		this.pop = pop;
	}
	
	public String getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
		
	public int getPop() {
		return pop;
	}
	
}
