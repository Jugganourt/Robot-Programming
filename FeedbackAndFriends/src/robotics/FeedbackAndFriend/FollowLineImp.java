package robotics.FeedbackAndFriend;

import lejos.nxt.Button;
import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;
import lejos.util.Delay;

public class FollowLineImp extends RobotSettings {

	public static LightSensor s_left = new LightSensor(SensorPort.S3);
	public static LightSensor s_right = new LightSensor(SensorPort.S2);
	public static LightSensor s_middle = new LightSensor(SensorPort.S1);
	private int threshold = 43;
	private int thresholdMid = 45;

	public FollowLineImp() {
		super();
	}

	public void run() {
		while (m_run()) {
			
			if(s_left.readValue() > threshold && s_right.readValue() > threshold && s_middle.readValue() > thresholdMid){//OOO
				System.out.println("end of line");
				pilot.rotate(180);
				pilot.travel(30);
			}
			if(s_left.readValue() > threshold && s_right.readValue() > threshold && s_middle.readValue() < thresholdMid){//OXO
				System.out.println("drive");
				pilot.forward();
			}
			if(s_left.readValue() > threshold && s_right.readValue() < threshold && s_middle.readValue() > thresholdMid){//OOX
				
				System.out.println("turn right");
				pilot.rotate(-20);
			}
			if(s_left.readValue() > threshold && s_right.readValue() < threshold && s_middle.readValue() < thresholdMid){//OXX
				System.out.println("turn right");
				pilot.rotate(-160);
			}
			if(s_left.readValue() < threshold && s_right.readValue() > threshold && s_middle.readValue() > thresholdMid){//XOO
				System.out.println("turn left");
				pilot.rotate(20);
			}
			if(s_left.readValue() < threshold && s_right.readValue() > threshold && s_middle.readValue() < thresholdMid){//XXO
				pilot.rotate(160);
				System.out.println("turn left");
			}
			if(s_left.readValue() < threshold && s_right.readValue() < threshold && s_middle.readValue() > thresholdMid){//XOX
				System.out.println("WEll FUCK!!");
				pilot.rotate(90);
			}
			if(s_left.readValue() < threshold && s_right.readValue() < threshold && s_middle.readValue() < thresholdMid){//XXX
				System.out.println("Junstion BABY!");
				pilot.rotate(90);
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