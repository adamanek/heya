package final11;

import java.util.ArrayList;

public class Filtering implements Interface2{

	public static void main(String[] args) {
	}

	@Override
	public ArrayList<DataObject> filtering(ArrayList<DataObject> list) {
		ArrayList<Filtering> result = new ArrayList<>();
		int sum=0;
		for(DataObject e:list){
			sum+=e.xCount;
		}
		for(DataObject e:list){
			float cap = e.xCount/sum;
			result.add(new Filtering(e.ID,cap));
		}
		System.out.println(result);
		return null;
	}


}
