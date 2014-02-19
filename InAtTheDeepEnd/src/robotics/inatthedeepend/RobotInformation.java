package robotics.inatthedeepend;

import lejos.nxt.Button;
import lejos.util.Delay;
import lejos.nxt.Motor;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.navigation.DifferentialPilot;

/**
 * All the important stuff that the robot can do
 * 
 * @author txs397
 */
public class RobotInformation {

	private final static RegulatedMotor left = Motor.C;
	private final static RegulatedMotor right = Motor.B;
	public final static DifferentialPilot pilot = new DifferentialPilot(56,
			122, left, right);

	/**
	 * waits for any button to be pressed
	 */
	public void buttonPress() {
		Button.waitForAnyPress();
		Delay.msDelay(500);
	}

	/**
	 * Makes the robot move in a Triangle formation
	 */
	public void triangle() {
		pilot.travel(400);
		pilot.rotate(120);
	}

	/**
	 * Makes the robot move in a Square formation
	 */
	public void square() {
		pilot.travel(400);
		pilot.rotate(90);
	}

	/**
	 * Makes the robot move in a Pentagon formation
	 */
	public void pentagon() {
		pilot.travel(400);
		pilot.rotate(72);
	}

	/**
	 * Makes the robot move in a Hexagon formation
	 */
	public void hexagon() {
		pilot.travel(400);
		pilot.rotate(60);
	}

	/**
	 * Makes the robot rotate
	 * 
	 * @param angle
	 *            The angle the robot will rotate in degrees
	 */
	public void spin(double angle) {
		pilot.rotate(angle);
		;
	}

	/**
	 * Makes the Robot reverse for half a second
	 */
	public void backwardsMimimum() {
		pilot.travel(-22);
	}

	/**
	 * Makes the Robot reverse for half a second
	 */
	public void backwardsFar() {
		pilot.travel(-100);
	}

	/**
	 * Makes the robot drive forward
	 */
	public void drive() {
		pilot.forward();
	}

	public void stop() {
		pilot.stop();
	}

	public void noWall() {
		pilot.travel(50);
		pilot.rotate(90);
		pilot.travel(230);
	}

	public void setSpeed(double speed) {
		pilot.setTravelSpeed(speed);
	}

	public void moveAwayFromWall() {
		pilot.stop();
		pilot.rotate(-45);
		pilot.travel(50);
		pilot.rotate(45);
		pilot.stop();
	}
}
