

import lejos.nxt.Button;
import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;
import lejos.util.Delay;

public class Grid extends RobotSettings {

	public static LightSensor s_left = new LightSensor(SensorPort.S3);
	public static LightSensor s_right = new LightSensor(SensorPort.S2);
	private int threshold = 40;

	public Grid() {
		super();
	}

	public void run() {
		Behavior[] behaviours = {new DriveForward(), new Junction(s_left,s_right,threshold)};
		Arbitrator arb = new Arbitrator (behaviours);
		arb.start();
	}

	public static void main(String[] args) {
		Button.waitForAnyPress();
		Delay.msDelay(500);
		Grid run = new Grid();
		run.run();

	}
}
