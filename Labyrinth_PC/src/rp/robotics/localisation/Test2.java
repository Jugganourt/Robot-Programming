package rp.robotics.localisation;

import lejos.nxt.SensorPort;
import lejos.nxt.addon.OpticalDistanceSensor;
import lejos.util.Delay;
import robot.moves.Grid2;
import robot.moves.RobotSettings;
import rp.robotics.mapping.GridMap;
import rp.robotics.mapping.Heading;
import rp.robotics.mapping.LocalisationUtils;

public class Test2 extends RobotSettings{

	public static void main(String[] args) {
		
		Grid2.buttonPress();
		
		
		/*
		 * 2 ways to do the sensor model
		 * Sep's way: you only look at the was in you immediate proximity 
		 * one ahead, one left, one right, do you need the one back?
		 * 
		 * my suggestion - you only look at the measurements that are below your senson's 
		 * range - 80cm
		 * 
		 */
		
		OpticalDistanceSensor sensor = new OpticalDistanceSensor(SensorPort.S4);
		PerfectSensorModel sensorModel = new PerfectSensorModel();
		GridMap gridMap = LocalisationUtils.create2014Map1();
		int x =3;
		int y= 0;
		
		float accB = gridMap.rangeToObstacleFromGridPoint(x, y, Heading.MINUS_X);
		float accL =  gridMap.rangeToObstacleFromGridPoint(x, y, Heading.MINUS_Y);
		float accF = gridMap.rangeToObstacleFromGridPoint(x, y, Heading.PLUS_X);
		float accR = gridMap.rangeToObstacleFromGridPoint(x, y, Heading.PLUS_Y);
		float valueF = sensor.getRange();
		System.out.println(valueF);
		System.out.println(accF);
		Delay.msDelay(5000);
		sensormotor.rotate(95);
		float valueR = sensor.getRange();
		System.out.println(valueR);
		System.out.println(accR);
		Delay.msDelay(10000);
		sensormotor.rotate(95);
		float valueB = sensor.getRange();
		System.out.println(valueB);
		System.out.println(accB);
		Delay.msDelay(5000);
		sensormotor.rotate(95);
		float valueL = sensor.getRange();
		System.out.println(valueL);
		System.out.println(accL);
		
		sensormotor.rotate(-285);
		Delay.msDelay(90000);
	}

}
