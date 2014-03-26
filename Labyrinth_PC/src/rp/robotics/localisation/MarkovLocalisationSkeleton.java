package rp.robotics.localisation;

import lejos.util.Delay;
import rp.robotics.mapping.GridMap;
import rp.robotics.mapping.Heading;
import rp.robotics.mapping.LocalisationUtils;

public class MarkovLocalisationSkeleton {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		
		GridMap gridMap = LocalisationUtils.create2014Map1();

		// The probability distribution over the robot's location
		GridPositionDistribution distribution = new GridPositionDistribution(
				gridMap);


		// ActionModel actionModel = new DummyActionModel();
		ActionModel actionModel = new PerfectActionModel();

		DummySensorModel sensorModel = new DummySensorModel();
		
		while (true) {
			// Do some action
			// E.g. attempting to move one node in the PLUS_X direction
			Heading action = Heading.PLUS_Y;
			// I'm faking movement by waiting for some time
			Delay.msDelay(1000);

			// Once action is completed, apply action model based on the move
			// the robot took. This creates a new instance of
			// GridPoseDistribution and assigns it to distribution
			distribution = actionModel.updateAfterMove(distribution, action);
			distribution.normalise();
						
			// Update visualisation. Only necessary because it needs to know
			// about the new distribution instance
		

			System.out.println("map sum: " + distribution.sumProbabilities());

			// Do some sensing
			// ...
			// I'm faking sensing by waiting for some time
			//Delay.msDelay(1000);

			// Once completed apply sensor model as appropriate. This changes
			// the distribution directly (i.e. by reference)
			sensorModel.updateDistributionAfterSensing(distribution/**
			 * , include
			 * sensor readings
			 **/
			);

			// Note, as the sensor model changes the distribution directly, the
			// visualisation will update automatically so
			// mapVis.setDistribution is not necessary after the sensor model

		}

	}
}
