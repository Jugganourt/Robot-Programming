package robot.moves;

import java.util.Random;

import rp.robotics.localisation.ActionModel;
import rp.robotics.localisation.DummySensorModel;
import rp.robotics.localisation.GridPositionDistribution;
import rp.robotics.localisation.PerfectActionModel;
import rp.robotics.mapping.GridMap;
import rp.robotics.mapping.Heading;
import rp.robotics.mapping.LocalisationUtils;

public class Grid2 extends RobotMoves {

	private Random rand = new Random();

	public Grid2() {
		super();
	}

	
	
	public void run() {
		while (m_run()) {
			pilot.setTravelSpeed(300);
			
			GridMap gridMap = LocalisationUtils.create2014Map1();

			// The probability distribution over the robot's location
			GridPositionDistribution distribution = new GridPositionDistribution(
					gridMap);


			// ActionModel actionModel = new DummyActionModel();
			ActionModel actionModel = new PerfectActionModel();

			DummySensorModel sensorModel = new DummySensorModel();
			
			while (true) {
				

				int ran = rand.nextInt(3);
				forwardCondition();
				junction(ran);
				rightCondition();
				leftCondition();
				
				Heading action = Heading.PLUS_Y;
	
				distribution = actionModel.updateAfterMove(distribution, action);
				distribution.normalise();

			

				System.out.println("map sum: " + distribution.sumProbabilities());

				sensorModel.updateDistributionAfterSensing(distribution);
			}
			
		}
	}

	public static void main(String[] args) {
		buttonPress();
		Grid2 run = new Grid2();
		run.run();		
		
	}
}