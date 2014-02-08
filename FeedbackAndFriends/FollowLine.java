package robotics.FeedbackAndFriend;

import lejos.nxt.Button;
import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;
import lejos.nxt.Sound;
import lejos.util.Delay;

public class FollowLine extends RobotSettings {

	public static LightSensor s_left = new LightSensor(SensorPort.S3);
	public static LightSensor s_right = new LightSensor(SensorPort.S2);
	private int thresholdBlackNBlue = 43;
	public FollowLine() {
		super();
	}

	public void run() {
		while (m_run()) {
			pilot.setTravelSpeed(300);
			
			if(s_left.readValue() < thresholdBlackNBlue && s_right.readValue() >thresholdBlackNBlue)
			{
				pilot.rotate(20);
			}
			
			if(s_left.readValue() < thresholdBlackNBlue && s_right.readValue() < thresholdBlackNBlue)
			{
				if(s_left.readValue( ) < thresholdBlackNBlue){
					pilot.rotate(20);
				}
				if(s_left.readValue( ) > thresholdBlackNBlue){
					pilot.rotate(-20);
				}
			
			}
			if(s_left.readValue() > thresholdBlackNBlue && s_right.readValue() > thresholdBlackNBlue )
			{
				pilot.forward(); 
			} 
			if(s_left.readValue() > thresholdBlackNBlue && s_right.readValue() < thresholdBlackNBlue)
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
