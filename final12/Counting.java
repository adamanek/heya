package final12;

public class Counting {
	public static int top;
	public static int bot;
	public double c;
	public Counting(int d, int e, double count){
		c=count;
		top=e;
		bot=d;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public static int getBot(){
		return bot;
	}
	public static int getTop(){
		return top;
	}
	public String toString(){
		return bot+","+top+","+c;
	}

}
