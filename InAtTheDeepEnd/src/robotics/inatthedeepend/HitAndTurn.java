package robotics.inatthedeepend;

import lejos.nxt.Button;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;

/**
 * The Robot will hit something and do a 180 spin in the opposite direction
 * 
 * @author txs397
 */
public class HitAndTurn {
	public static void main(String[] args) {
		RobotInformation robot = new RobotInformation();
		TouchSensor sensor = new TouchSensor(SensorPort.S1);
		robot.setSpeed(200);

		System.out.println("To start press The Orange Button");
		System.out.println("");
		System.out.println("To STOP hold The Orange Button");

		robot.buttonPress();
		while (Button.ENTER.isUp()) // loop
		{
			robot.drive();// go forward

			if (sensor.isPressed()) // if bump
			{
				robot.backwardsMimimum(); // back a bit
				robot.spin(180); // turn
			}
		}
	}
}