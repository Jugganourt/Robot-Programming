package robotics.FeedbackAndFriend;

import lejos.nxt.Button;
import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;
import lejos.nxt.addon.OpticalDistanceSensor;
import lejos.util.Delay;

public class ArrayGrid extends RobotMoves {

	public static LightSensor s_left = new LightSensor(SensorPort.S3);
	public static LightSensor s_right = new LightSensor(SensorPort.S2);
	OpticalDistanceSensor sensor = new OpticalDistanceSensor(SensorPort.S4);
	private int d_right = 2;
	private int d_left = 1;
	private int d_fwd = 0;
	private int[] map = new int[]{d_fwd,d_right, d_fwd, d_right, d_fwd, d_left}; 
	private int curr;
	private int i=0;
	public ArrayGrid() {
		super();
	}

	public void run() {
	curr = map[i];
		while (m_run()&& i<map.length) {
			pilot.setTravelSpeed(300);

			
			forwardCondition();
			i = junctionReturn(curr, i);
			closeToGridWallCondition();
			rightCondition();
			leftCondition();
			if( i<map.length)
				curr = map[i];
		}

	}

	public static void main(String[] args) {
		Button.waitForAnyPress();
		Delay.msDelay(500);
		ArrayGrid run = new ArrayGrid();
		run.run();

	}
}
