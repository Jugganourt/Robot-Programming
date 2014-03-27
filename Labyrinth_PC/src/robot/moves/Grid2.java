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
	
	
	private GridMap gridMap = LocalisationUtils.create2014Map1();
	private GridPositionDistribution distribution = new GridPositionDistribution(
			gridMap);
	private ActionModel actionModel = new PerfectActionModel();
	private Heading action = Heading.PLUS_X;
	private float max = 0;
	private int xMax = 0;
	private int yMax = 0;
	private double thres = 0.1;

	public Grid2() {
		super();
	}

	
	
	public void run() {
			while (m_run()) {
			pilot.setTravelSpeed(100);
			
			int ran = rand.nextInt(3);			
			forwardCondition();
			rightCondition();
			leftCondition();
			junction(ran);	
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
		
		//itterate through the map
		//get the maximum probability in the map
		//in the while true 
		// while max< thresh
		
		if(maxprob(gridMap)< thres){
			
		if(s_left.readValue() < threshold && s_right.readValue() < threshold)
		{
			
			pilot.travel(60);
			if(ran == 1){
				System.out.println(action);
				distribution = actionModel.updateAfterMove(distribution, action);
				distribution.normalise();
				action = cycleThroughtLeft(action);
				pilot.rotate(90);
				
				
			}
			else if(ran ==2){
				System.out.println(action);
				distribution = actionModel.updateAfterMove(distribution, action);
				distribution.normalise();
				action = cycleThroughtRight(action);
					pilot.rotate(-90);
			}
			else{ 
				System.out.println(action);
				distribution = actionModel.updateAfterMove(distribution, action);
				distribution.normalise();
				action = cycleThroughtForward(action);
				pilot.forward();
				}
			
			while(canMove()){
				closeToGridWallCondition();
				action = cycleThroughtLeft(action);
			}
			


			System.out.println("map sum: " + distribution.sumProbabilities());
			
		}
		}
		if(maxprob(gridMap)>thres){
		System.out.println("Xposs:" + xMax);
		System.out.println("Xposs:" + yMax);
		}
		
	
	}
	
	private double maxprob(GridMap gridMap) {
		
	
		for (int y = 0; y < gridMap.getGridHeight(); y++) {
			for (int x = 0; x < gridMap.getGridWidth(); x++) 
				if(distribution.getProbability(x, y)>max)
				{
					max = distribution.getProbability(x, y);
					xMax = x;
					yMax = y;
				}
		}
		System.out.println("maximum "+max);
		return max;
		
	}



	public Heading cycleThroughtLeft(Heading action){
		if(action == Heading.PLUS_X ){
			 action = Heading.MINUS_Y;
		}
		else if( action == Heading.MINUS_Y){
			action = Heading.MINUS_X;
		}
		else if(action == Heading.MINUS_X){
			action = Heading.PLUS_Y;
		}
		else if(action == Heading.PLUS_Y){
			action = Heading.PLUS_X;
		}
		return action;
		
	}	
	
	public Heading cycleThroughtRight(Heading action){
		if(action == Heading.PLUS_X ){
			 action = Heading.PLUS_Y;
		}
		else if( action == Heading.PLUS_Y){
			action = Heading.MINUS_X;
		}
		else if(action == Heading.MINUS_X){
			action = Heading.MINUS_Y;
		}
		else if(action == Heading.MINUS_Y){
			action = Heading.PLUS_X;
		}
		return action;
		
	}	
	
	public Heading cycleThroughtForward(Heading action){
		if(action == Heading.PLUS_X ){
			 action = Heading.PLUS_X;
		}
		else if( action == Heading.PLUS_Y){
			action = Heading.PLUS_Y;
		}
		else if(action == Heading.MINUS_X){
			action = Heading.MINUS_X;
		}
		else if(action == Heading.MINUS_Y){
			action = Heading.MINUS_Y;
		}
		return action;
		
	}	
	
}
