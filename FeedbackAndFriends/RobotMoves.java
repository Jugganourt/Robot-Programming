package robotics.FeedbackAndFriend;

import lejos.nxt.Button;
import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;
import lejos.nxt.addon.OpticalDistanceSensor;
import lejos.util.Delay;

public class RobotMoves extends RobotSettings {
	
	public static LightSensor s_left = new LightSensor(SensorPort.S3);
	public static LightSensor s_right = new LightSensor(SensorPort.S2);
	private int threshold = 44;
	private static OpticalDistanceSensor sensor = new OpticalDistanceSensor(SensorPort.S4);
	private int wall = 200;
	private int noLine = 15;
	private int shortDistance = 5;

	public RobotMoves(){
		super();
	}
	
	public static void buttonPress(){
		Button.waitForAnyPress();
		Delay.msDelay(500);
	}
	public void speedChange(){
		int reading = sensor.getDistance();
		int desiredDistance = 350;
		
		reading = sensor.getDistance();
		int error = desiredDistance - reading;
		double speed = 0.6 * Math.abs(error);

		pilot.setTravelSpeed(speed);
		if(error<0)
			pilot.forward();
		else
			pilot.backward();
	}
	public void leftCondition(){
		if(s_left.readValue() < threshold && s_right.readValue() >threshold)
		{
			pilot.rotate(noLine);
			pilot.travel(shortDistance);
		}
	}
	public void rightCondition()
	{
		if(s_left.readValue() > threshold && s_right.readValue() < threshold)
		{
			pilot.rotate(-1*noLine);
			pilot.travel(shortDistance);
		}
	}
	public void forwardCondition(){
		if(s_left.readValue() > threshold && s_right.readValue() > threshold)
		{
			pilot.forward(); 
		} 
	}
	public void stuckCondition(){
		if(s_left.readValue() < threshold && s_right.readValue() < threshold)
		{
			pilot.travel(5);
			if(s_left.readValue( ) < threshold){
				pilot.travel(5);
				pilot.rotate(5);
				
			}
			if(s_left.readValue( ) > threshold){
				pilot.travel(5);
				pilot.rotate(5);
			}
		
		}
	}
	public void closeToGridWallCondition(){
		if(sensor.getDistance() < wall){
			pilot.rotate(180);
		}
	}
	public void junction(int ran){
		if(s_left.readValue() < threshold && s_right.readValue() < threshold)
		{
			
			pilot.travel(60);
			if(ran == 1)
				pilot.rotate(90);
			else
				if(ran ==2)
					pilot.rotate(-90);
				else pilot.forward();
		}
	}





}
