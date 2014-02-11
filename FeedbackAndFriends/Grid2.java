
import java.util.Random;

import lejos.nxt.Button;
import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;
import lejos.nxt.addon.OpticalDistanceSensor;
import lejos.util.Delay;

public class Grid2 extends RobotSettings {

	public static LightSensor s_left = new LightSensor(SensorPort.S3);
	public static LightSensor s_right = new LightSensor(SensorPort.S2);
	OpticalDistanceSensor sensor = new OpticalDistanceSensor(SensorPort.S4);
	private int threshold = 40;
	private int wall = 200;
	private Random rand = new Random();
	private int noLine = 15;
	private int shortDistance = 5;
	public Grid2() {
		super();
	}

	public void run() {
		while (m_run()) {
			pilot.setTravelSpeed(300);

		
			pilot.forward();
			if(s_left.readValue() < threshold && s_right.readValue() < threshold)
			{
				int ran = rand.nextInt(3);
				pilot.travel(60);
				if(ran == 1)
					pilot.rotate(90);
				else
					if(ran ==2)
						pilot.rotate(-90);
					else pilot.forward();
			}
			if(sensor.getDistance() < wall){
				pilot.rotate(180);
			}
			if(s_left.readValue() > threshold && s_right.readValue() < threshold)
			{
				pilot.rotate(-1*noLine);
				pilot.travel(shortDistance);

			}
			if(s_left.readValue() < threshold && s_right.readValue() >threshold)
			{
				pilot.rotate(noLine);
				pilot.travel(shortDistance);
			}
		
	
		
		}
	}

	public static void main(String[] args) {
		Button.waitForAnyPress();
		Delay.msDelay(500);
		Grid2 run = new Grid2();
		run.run();

	}
}
