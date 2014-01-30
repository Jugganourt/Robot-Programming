package robotics.inatthedeepend;

import lejos.nxt.Button;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;
import lejos.nxt.UltrasonicSensor;

/**
 * Made so that the robot can escape a maze
 * 
 * @author txs397
 * 
 */
public class MazeExcape {

	private final static int distanceFromWall = 45;
	private final static int tooCloseToTheWall = 10;

	public static void main(String[] args) {
		System.out.println("To start press The Orange Button");
		System.out.println("");
		System.out.println("To STOP hold The Orange Button");

		RobotInformation robot = new RobotInformation();
		UltrasonicSensor range = new UltrasonicSensor(SensorPort.S4);
		TouchSensor sensor = new TouchSensor(SensorPort.S1);

		robot.buttonPress();
		while (Button.ENTER.isUp()) {
			robot.setSpeed(200);
			range.continuous();

			if (range.getDistance() < tooCloseToTheWall) {
				robot.moveAwayFromWall();
			}

			if (range.getDistance() <= distanceFromWall && !sensor.isPressed()) {
				robot.drive();
			}

			if (range.getDistance() > distanceFromWall && !sensor.isPressed()) {
				robot.stop();
				robot.noWall();
				robot.stop();
			}

			if (range.getDistance() <= distanceFromWall && sensor.isPressed())

			{
				robot.stop();
				robot.backwardsFar();
				robot.spin(-90);
			}

			if (range.getDistance() > distanceFromWall && sensor.isPressed()) {
				robot.stop();
				robot.backwardsFar();
				robot.spin(90);
				robot.stop();
			}

		}
	}
}
