package robotics.inatthedeepend;

import lejos.nxt.Button;
import lejos.util.Delay;

/**
 * makes the robot draw shapes
 * 
 * @author txs397
 */
public class Shapes {
	public static void main(String[] args) {
		RobotInformation robot = new RobotInformation();
		robot.setSpeed(200);
		System.out.println("Hello World!!");
		System.out.println("");
		System.out.println("To start press The Orange Button");
		System.out.println("");
		System.out.println("To STOP hold The Orange Button");

		robot.buttonPress();
		while (Button.ENTER.isUp()) {
			robot.square();
		}
		Delay.msDelay(500);
		while (Button.ENTER.isUp()) {
			robot.hexagon();
		}
	}
}
