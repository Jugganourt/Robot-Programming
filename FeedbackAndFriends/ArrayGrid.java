

import lejos.nxt.Button;
import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;
import lejos.nxt.addon.OpticalDistanceSensor;
import lejos.util.Delay;

public class ArrayGrid extends RobotSettings {

	public static LightSensor s_left = new LightSensor(SensorPort.S3);
	public static LightSensor s_right = new LightSensor(SensorPort.S2);
	OpticalDistanceSensor sensor = new OpticalDistanceSensor(SensorPort.S4);
	private int threshold = 40;
	private int d_right = 2;
	private int d_left = 1;
	private int d_fwd = 0;
	private int[] map = new int[]{d_left,d_right, d_fwd, d_right, d_fwd}; 
	private int curr;
	private int wall = 200;
	private int noLine = 15;
	private int shortDistance = 5;
	public ArrayGrid() {
		super();
	}

	public void run() {
	curr = map[0];
		while (m_run()) {
		
	}

	public static void main(String[] args) {
		Button.waitForAnyPress();
		Delay.msDelay(500);
		ArrayGrid run = new ArrayGrid();
		run.run();

	}
}
