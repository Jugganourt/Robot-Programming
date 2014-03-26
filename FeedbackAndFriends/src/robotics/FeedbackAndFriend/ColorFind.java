package robotics.FeedbackAndFriend;

import lejos.nxt.Button;
import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;
import lejos.nxt.addon.OpticalDistanceSensor;
import lejos.util.Delay;

public class ColorFind extends RobotSettings {

	public static LightSensor s_left = new LightSensor(SensorPort.S3);
	public static LightSensor s_right = new LightSensor(SensorPort.S2);
	public static LightSensor s_middle = new LightSensor(SensorPort.S1);
	public static OpticalDistanceSensor sensor = new OpticalDistanceSensor(SensorPort.S4);
	public void run() {
		while (m_run()) {
			/*System.out.println("Left");
			System.out.println(s_left.readValue());
			Delay.msDelay(1000);
			System.out.println("right");
			System.out.println(s_right.readValue());
			Delay.msDelay(1000); 
			System.out.println("middle");
			System.out.println(s_middle.readValue());
			Delay.msDelay(1000); */
			System.out.println(sensor.getDistance());
			Delay.msDelay(1000);
		}
	}
	
	public static void main(String[] arg){
		Button.waitForAnyPress();
		Delay.msDelay(500);
		ColorFind run = new ColorFind();
		run.run();		
	}
	
}
