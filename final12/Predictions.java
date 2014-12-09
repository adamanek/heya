package final12;

import java.util.ArrayList;
import java.util.Arrays;

public class Predictions implements Interface {

	@Override
	public void prediction(double max, double min, double w, double n) {
		for(double i=min; i<max; i+=1){
			double count =(float) 0.5*(calculation(200,w,n,i)+calculation(100,w,n,i))*((200)-100);
			System.out.println("The number of predicted events between "+i+" and "+(i+1)+"GeV is "+ count);

		}

		
	}



	@Override
	public double calculation(double energy, double width, double N, double mass) {
		double result = (N/(width*Math.pow(Math.PI,0.5)))*(Math.exp(-Math.pow((energy-mass),2)/(2*Math.pow(width,2))));
		return result;
	}

}
