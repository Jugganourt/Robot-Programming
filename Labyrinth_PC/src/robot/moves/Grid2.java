package robot.moves;

import java.util.Random;
import lejos.nxt.Button;
import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;
import lejos.nxt.addon.OpticalDistanceSensor;
import lejos.util.Delay;
import rp.robotics.localisation.ActionModel;
import rp.robotics.localisation.GridPositionDistribution;
import rp.robotics.localisation.PerfectActionModel;
import rp.robotics.mapping.GridMap;
import rp.robotics.mapping.Heading;
import rp.robotics.mapping.LocalisationUtils;

public class Grid2 extends RobotSettings {
	
	public static LightSensor s_left = new LightSensor(SensorPort.S3);
	public static LightSensor s_right = new LightSensor(SensorPort.S2);
	private int threshold = 40;
	private static OpticalDistanceSensor sensor = new OpticalDistanceSensor(SensorPort.S4);
	private int wall = 300;
	private int noLine = 15;
	private int shortDistance = 5;
	PerfectActionModel model = new PerfectActionModel();
	private Random rand = new Random();

	public Grid2() {
		super();
	}

	
	
	public void run() {
		GridMap gridMap = LocalisationUtils.create2014Map1();

		// The probability distribution over the robot's location
		GridPositionDistribution distribution = new GridPositionDistribution(
				gridMap);


		// ActionModel actionModel = new DummyActionModel();
		ActionModel actionModel = new PerfectActionModel();
		while (m_run()) {
			pilot.setTravelSpeed(100);
			
			

			
			forwardCondition();
			rightCondition();
			leftCondition();
			
			//while (true) {
				if(junction()){
					pilot.stop();
				int ran = rand.nextInt(3);

				junction(ran);				
				
				Heading action = Heading.PLUS_X;
				System.out.println(action);
				distribution = actionModel.updateAfterMove(distribution, action);
				distribution.normalise();

				System.out.println("map sum: " + distribution.sumProbabilities());

			}
		}

	}

	public static void main(String[] args) {
		buttonPress();
		Grid2 run = new Grid2();
		run.run();		
		
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