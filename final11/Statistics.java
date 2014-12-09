package final11;

import java.util.ArrayList;


public class Statistics implements Interface{

	public static void main(String[] args) {

	}

	@Override
	public double mean(double total, double count) {
		double result = total/count;
		return result;
	}

	@Override
	public double stDeviationA(ArrayList<DataObject> list) {
		double sum=0;
		double sumD=0;
		for(DataObject e: list){
			sumD+=e.aCount;
		}
		for(DataObject e: list){
			sum+=Math.pow((e.aCount-mean(sumD, list.size())),2);
		}
		double stDevA= Math.pow((sum/list.size()), 0.5);
		return stDevA;
	}

	@Override
	public double stDeviationB(ArrayList<DataObject> list) {
		double sum=0;
		double sumD=0;
		for(DataObject e: list){
			sumD+=e.bCount;
		}
		for(DataObject e: list){
			sum+=Math.pow((e.bCount-mean(sumD, list.size())),2);
		}
		double stDevB= Math.pow((sum/list.size()), 0.5);
		return stDevB;
	}

	@Override
	public double stDeviationX(ArrayList<DataObject> list) {
		double sum=0;
		double sumD=0;
		for(DataObject e: list){
			sumD+=e.xCount;
		}
		for(DataObject e: list){
			sum+=Math.pow((e.xCount-mean(sumD, list.size())),2);
		}
		double stDevX= Math.pow((sum/list.size()), 0.5);
		return stDevX;
	}

	@Override
	public double stDeviationY(ArrayList<DataObject> list) {
		double sum=0;
		double sumD=0;
		for(DataObject e: list){
			sumD+=e.yCount;
		}
		for(DataObject e: list){
			sum+=Math.pow((e.yCount-mean(sumD, list.size())),2);
		}
		double stDevY= Math.pow((sum/list.size()), 0.5);
		return stDevY;
	}

	@Override
	public double stDeviationZ(ArrayList<DataObject> list) {
		double sum=0;
		double sumD=0;
		for(DataObject e: list){
			sumD+=e.zCount;
		}
		for(DataObject e: list){
			sum+=Math.pow((e.zCount-mean(sumD, list.size())),2);
		}
		double stDevZ= Math.pow((sum/list.size()), 0.5);
		return stDevZ;
	}




}
