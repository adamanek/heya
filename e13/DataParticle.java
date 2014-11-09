package e13;

public class DataParticle {
	
	String triggerId;
	
	double[][] matrix;
	
	/*public DataParticle(String triggerID, String[] lines) {
		this.triggerId = triggerID;
		
		matrix = new double[4][lines.length];
		
		for (int i=0; i<lines.length; ++i) {
			String[] dataPoints = lines[i].trim().split("\\s+");
			
			for (int j=0; j<dataPoints.length; ++j) {
				
				matrix[j][i] =Double.parseDouble(dataPoints[j]);
			}
		}
	}*/


	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}		
		public DataParticle(String triggerID, String[] lines) {
	        this.triggerId = triggerID;
	        
	        matrix = new double[lines.length][4];
	        
	        for (int i = 0; i < lines.length; ++i) {
	            String[] dataPoints = lines[i].trim().split("\\s+");
	            		
	            
	            for (int j = 0; j < dataPoints.length; ++j) {
	                matrix[i][j] = Double.parseDouble(dataPoints[j]);
	            }
	        } 
	    } 
	    
	    public String getTriggerId() {
	        return triggerId;
	    }
	}
