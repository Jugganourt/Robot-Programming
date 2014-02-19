package robotics.inatthedeepend;

import lejos.nxt.Button;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.SensorPortListener;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.navigation.DifferentialPilot;

public class Bumpandturn2 {

	private final static RegulatedMotor left = Motor.C;
	private final static RegulatedMotor right = Motor.B;
	private final static DifferentialPilot pilot = new DifferentialPilot(56,
			122, left, right);

	public static void main(String[] args) {
		System.out.println("To start press The Orange Button");
		System.out.println("");
		System.out.println("To STOP hold The Orange Button");

		SensorPortListener aListener = new EventListener(pilot);
		SensorPort.S1.addSensorPortListener(aListener);

		Button.waitForAnyPress();
		pilot.forward();
		Button.waitForAnyPress();

	}

}
