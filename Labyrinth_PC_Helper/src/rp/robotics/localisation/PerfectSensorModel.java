package rp.robotics.localisation;

import rp.robotics.mapping.Heading;


/**
 * An example of how you could start writing an action model given the available
 * classes.
 * 
 * @author nah
 * 
 */


public class PerfectSensorModel {
	private float currR, currL, currF, currB;
	
	public float getCurrR() {
		return currR;
	}
	public float getCurrL() {
		return currL;
	}
	public float getCurrF() {
		return currF;
	}
	public float getCurrB() {
		return currB;
	}
	
	public GridPositionDistribution updateDistributionAfterSensing(float valueL,float valueR, float valueF, float valueB,GridPositionDistribution _dist) {
		GridPositionDistribution to = new GridPositionDistribution(_dist);
		for (int y = 0; y < to.getGridHeight(); y++) {
			for (int x = 0; x < to.getGridWidth(); x++)
			{	
				if (!_dist.isObstructed(x, y)) {
				 currR = _dist.getGridMap().rangeToObstacleFromGridPoint(x, y,Heading.PLUS_Y);//difference between what his right is and what the right in the virtual model is 
				 currL = _dist.getGridMap().rangeToObstacleFromGridPoint(x, y,Heading.MINUS_Y);
				 currF = _dist.getGridMap().rangeToObstacleFromGridPoint(x, y,Heading.PLUS_X);
				 currB = _dist.getGridMap().rangeToObstacleFromGridPoint(x, y,Heading.MINUS_X);
				 if(aprox(valueL, currL) && aprox(valueR, currR) && aprox(valueF, currF) && aprox(valueB, currB))
				 {
					 to.setProbability(x, y, _dist.getProbability(x, y));
					 
				 }
				 else{
					 to.setProbability(x, y,0.f);
				 }
				 
					 
			}
			}
			
		}return to;
		/*4 measurements 
		get 4 measuremnt 
		compare the measurment to all positions on the map 
		set prob to 0 
		error +5 cm
		if one of them is diff to 0 i am there
		if not take a move and relocalise */
		// Commented out the random code t o stop people using it without looking

		// Random rand = new Random();
		//
		// float prob;
		// // iterate through points updating as appropriate
		// for (int x = 0; x < _dist.getGridWidth(); x++) {
		// for (int y = 0; y < _dist.getGridHeight(); y++) {
		// // make sure to respect obstructed grid points
		// if (!_dist.isObstructed(x, y)) {
		// prob = _dist.getProbability(x, y);
		// // randomly set some decent values to 0
		// if (prob > 0.1) {
		// if (rand.nextBoolean()) {
		// _dist.setProbability(x, y, 0);
		// }
		// }
		// }
		// }
		// }
		//
		// _dist.normalise();
	}
	private boolean aprox(float value, float curr) {
		if(value>800 || (curr<value+70 && curr>value-70)) // accepts an error of 7 cm
		{
			
			return true;
			
		}
		else{
		return false;
		}
	}

}