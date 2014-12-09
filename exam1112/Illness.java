package exam1112;

public class Illness implements IllnessInt{
	private String id;
	private int XXX;
	private int YYY;
	private int ZZZ;
	
	public Illness(String id,int XXX, int YYY, int ZZZ) {
		this.id = id;
		this.XXX = XXX;
		this.YYY = YYY;
		this.ZZZ = ZZZ;
	}

	public String getId() {
		return id;
	}

	public int getXXX() {
		return XXX;
	}

	public int getYYY() {
		return YYY;
	}

	public int getZZZ() {
		return ZZZ;
	}

	@Override
	public int getAAA() {
		return -1;
	}

	@Override
	public int getBBB() {
		return -1;
	}
	
	
	

}
