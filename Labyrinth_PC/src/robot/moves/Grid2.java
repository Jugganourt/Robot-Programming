package robot.moves;

import java.util.Random;

import lejos.util.Delay;
import rp.robotics.localisation.ActionModel;
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
		GridMap gridMap = LocalisationUtils.create2014Map1();

		// The probability distribution over the robot's location
		GridPositionDistribution distribution = new GridPositionDistribution(
				gridMap);


		// ActionModel actionModel = new DummyActionModel();
		ActionModel actionModel = new PerfectActionModel();
		while (m_run()) {
			pilot.setTravelSpeed(100);
			
			

			
			forwardCondition();
			rightCondition();
			leftCondition();
			
			//while (true) {
				if(junction()){
					pilot.stop();
				int ran = rand.nextInt(3);

				junction(ran);				
				
				Heading action = Heading.PLUS_X;
				System.out.println(action);
				distribution = actionModel.updateAfterMove(distribution, action);
				distribution.normalise();

				System.out.println("map sum: " + distribution.sumProbabilities());

			}
		}

	}

	public static void main(String[] args) {
		buttonPress();
		Grid2 run = new Grid2();
		run.run();		
		
	}
}