package robot.moves;

import rp.robotics.localisation.PerfectActionModel;
import rp.robotics.mapping.Heading;
import lejos.nxt.Button;
import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;
import lejos.nxt.addon.OpticalDistanceSensor;
import lejos.util.Delay;

public class RobotMoves extends RobotSettings {
	
	public static LightSensor s_left = new LightSensor(SensorPort.S3);
	public static LightSensor s_right = new LightSensor(SensorPort.S2);
	private int threshold = 40;
	private static OpticalDistanceSensor sensor = new OpticalDistanceSensor(SensorPort.S4);
	private int wall = 300;
	private int noLine = 15;
	private int shortDistance = 5;
	PerfectActionModel model = new PerfectActionModel();
	

	public RobotMoves(){
		super();
	}
	
	public static void buttonPress(){
		Button.waitForAnyPress();
		Delay.msDelay(500);
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
	
	public void closeToGridWallCondition(){
		if(sensor.getDistance() < wall){
			
			pilot.stop();
			
			pilot.rotate(90);
		}
	}
	
	private boolean canMove() {
		if(sensor.getDistance() > wall){
			return false;
		}
		else{
			return true;
		}
	}
	
	public void junction(int ran){
		
		if(s_left.readValue() < threshold && s_right.readValue() < threshold)
		{
			
			pilot.travel(60);
			if(ran == 1){
				pilot.rotate(90);
			}
			else if(ran ==2){
					pilot.rotate(-90);
			}
			else{ pilot.forward();}
			while(canMove()){
				closeToGridWallCondition();
			}	
			
		}
		
	
	}
	
	protected boolean junction() {
		if(s_left.readValue() < threshold && s_right.readValue() < threshold)
		{
			return true;
		}
		else{
			return false;
		}
		
	}
	

}
