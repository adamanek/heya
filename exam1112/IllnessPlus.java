package exam1112;

public class IllnessPlus extends Illness implements IllnessInt{
	private int AAA;
	private int BBB;
	
	public IllnessPlus(String id, int XXX, int YYY, int ZZZ, int AAA, int BBB) {
		super(id, XXX, YYY, ZZZ);
		this.AAA = AAA;
		this.BBB = BBB;
		// TODO Auto-generated constructor stub
	}
	
	public int getAAA() {
		return AAA;
	}
	public int getBBB() {
		return BBB;
	}

}
