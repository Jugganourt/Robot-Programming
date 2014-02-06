package robotics.FeedbackAndFriend;

import lejos.nxt.Button;
import lejos.nxt.SensorPort;
import lejos.nxt.addon.OpticalDistanceSensor;
import lejos.util.Delay;

/* if the object is further than 10 cm go  speed 300 
 * calculation to slowly decrease as he gets closer 
 * if the object is as <5cm stop  */

public class SpeedControl extends RobotSettings {

	private static OpticalDistanceSensor infrared = new OpticalDistanceSensor(
			SensorPort.S4);

	public SpeedControl() {
		super();
	}

	
	public void run(){
		int reading = infrared.getDistance();
		int desiredDistance = 350;
		while(m_run())
		{
			reading = infrared.getDistance();
			int error = desiredDistance - reading;
			double speed = 0.6 * Math.abs(error);

			pilot.setTravelSpeed(speed);
			if(error<0)
				pilot.forward();
			else
				pilot.backward();
		}
	}
	
	
	
	public static void main(String[] args) {
		Button.waitForAnyPress();
		Delay.msDelay(500);
		SpeedControl run = new SpeedControl();
		run.run();
	}
}
