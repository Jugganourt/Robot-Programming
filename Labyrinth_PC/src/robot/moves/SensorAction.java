package robot.moves;

import java.util.Random;

import data.Node;
import data.Point;
import data_structures.Stack;
import lejos.nxt.Button;
import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;
import lejos.nxt.Sound;
import lejos.nxt.addon.OpticalDistanceSensor;
import lejos.util.Delay;
import main.SolveGrid;
import rp.robotics.localisation.ActionModel;
import rp.robotics.localisation.GridPositionDistribution;
import rp.robotics.localisation.PerfectActionModel;
import rp.robotics.localisation.PerfectSensorModel;
import rp.robotics.mapping.GridMap;
import rp.robotics.mapping.Heading;
import rp.robotics.mapping.LocalisationUtils;
import search.Solve;
import search.Solve.Search;

public class SensorAction extends RobotSettings {
	
	public static LightSensor s_left = new LightSensor(SensorPort.S3);
	public static LightSensor s_right = new LightSensor(SensorPort.S2);
	private int threshold = 40;
	private static OpticalDistanceSensor sensor = new OpticalDistanceSensor(SensorPort.S4);
	private int wall = 315;
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
	private double thres = 0.4;

	public SensorAction() {
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
		SensorAction run = new SensorAction();
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
				/*if(ran == 1){
					System.out.println(action);
					distribution = actionModel.updateAfterMove(distribution, action);
					distribution.normalise();
					action = cycleThroughtLeft(action);
					//distribution = sensing4D(distribution);
					//sensing
					// senseleft, right, ahead, update distribution and carry on 
				
					pilot.rotate(90);
				
				
				}
				else if(ran ==2){
					System.out.println(action);
					distribution = actionModel.updateAfterMove(distribution, action);
					distribution.normalise();
					action = cycleThroughtRight(action);
					//distribution = sensing4D(distribution);
					pilot.rotate(-90);
				}
				else{ 
					System.out.println(action);
					distribution = actionModel.updateAfterMove(distribution, action);
					distribution.normalise();
					action = cycleThroughtForward(action);
					//distribution = sensing4D(distribution);
					pilot.forward();
				}
			
				while(canMove()){
					closeToGridWallCondition();
					action = cycleThroughtLeft(action);			
				}*/
				System.out.println("sensing");
				
				if(sensor.getDistance() > wall){
					System.out.println(action);
					distribution = actionModel.updateAfterMove(distribution, action);
					distribution.normalise();
					action = cycleThroughtForward(action);
					System.out.println("map sum: " + distribution.sumProbabilities());
					distribution = sensing4D(distribution);
					distribution.normalise();
					
				}
				else{
					System.out.println(action);
					distribution = actionModel.updateAfterMove(distribution, action);
					distribution.normalise();
					distribution = sensing4D(distribution);
					distribution.normalise();
					while(canMove()){
						closeToGridWallCondition();
						action = cycleThroughtLeft(action);	
						System.out.println("map sum: " + distribution.sumProbabilities());

					}
					
				}
			}
		}
		if(maxprob(gridMap)>thres){
			
			Sound.beep();
			System.out.println("Xposs:" + xMax);
			System.out.println("Yposs:" + yMax);
			
		
		}
		
	
	}
	
	private void FindAPlace(int xPoss, int yPoss) {
			
			Sound.beepSequence();
		
			Stack<Node<Point>> nodes = Solve.solveGrid(new Point(xPoss,yPoss,(xPoss+yPoss)), new Point(5,3,(5+3)), Search.BREADTH_FIRST);
			SolveGrid grid = new SolveGrid(xPoss, yPoss, null);
			
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



	public static Heading cycleThroughtLeft(Heading action){
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
	
	public static Heading cycleThroughtRight(Heading action){
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
	
	public static Heading cycleThroughtForward(Heading action){
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
	
	public static Heading cycleThroughtBackwards(Heading action){
		if(action == Heading.PLUS_X ){
			 action = Heading.MINUS_X;
		}
		else if( action == Heading.PLUS_Y){
			action = Heading.MINUS_Y;
		}
		else if(action == Heading.MINUS_X){
			action = Heading.PLUS_X;
		}
		else if(action == Heading.MINUS_Y){
			action = Heading.PLUS_Y;
		}
		return action;
		
	}	
	public GridPositionDistribution sensing4D(GridPositionDistribution g1){
		
		PerfectSensorModel sensorModel = new PerfectSensorModel(action);//sensing the one ahead not the one where i am  
		float valueF = sensor.getRange();
		sensormotor.rotate(95);
		System.out.println(valueF);
		float valueR = sensor.getRange();
		sensormotor.rotate(95);
		System.out.println(valueR);
		float valueB = sensor.getRange();
		sensormotor.rotate(95);
		System.out.println(valueB);
		float valueL = sensor.getRange();
		System.out.println(valueL);
		sensormotor.rotate(-285);
		g1 = sensorModel.updateDistributionAfterSensing(valueL,valueR, valueF, valueB,g1);
		g1.normalise();
		return g1;
	}
}
