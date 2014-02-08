package robotics.FeedbackAndFriend;

import lejos.nxt.Button;
import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;
import lejos.util.Delay;

public class FollowLine extends RobotSettings {

	public static LightSensor left = new LightSensor(SensorPort.S3);
	public static LightSensor right = new LightSensor(SensorPort.S2);
	private int threshold = 40;

	public FollowLine() {
		super();
	}

	public void run() {
		while (m_run()) {
			pilot.setTravelSpeed(300);
			
			if(left.readValue() < threshold && right.readValue() >threshold)
			{
				pilot.rotate(20);
			}
			
			if(left.readValue() < threshold && right.readValue() < threshold)
			{
				if(left.readValue( )<threshold){
					pilot.rotate(-90);
				}
				else{
					pilot.rotate(90);
				}
			
			}
			if(left.readValue() > threshold && right.readValue() > threshold )
			{
				pilot.forward(); 
			} 
			if(left.readValue() > threshold && right.readValue() < threshold)
			{
				pilot.rotate(-20);
				
			}
		}
	}

	public static void main(String[] args) {
		Button.waitForAnyPress();
		Delay.msDelay(500);
		FollowLine run = new FollowLine();
		run.run();

	}
}
