package util;

import lejos.nxt.Button;
import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;
import main.XK9Class;


public class LineF extends XK9Class
{
	private final LightSensor lsl;
	private final LightSensor lsr;
		
	
	protected static int RIGHT_FLOOR_VAL = 40;
	protected static int LEFT_FLOOR_VAL = 50;
	
	private final static int VARIANCE = 6;
		
	protected final static double STEER_RATE = 70.0; 
	//70 is Pretty Good(tm)
	//handles everything that isn't the Corner Of Death perfectly
	
	LineF(boolean b)
	{
		super();

		lsl = new LightSensor(SensorPort.S1);
		lsr = new LightSensor(SensorPort.S2);
		
		calibrateLight();
		m_pilot.forward();
		run();
	}
	
	protected LineF()
	{
		super();

		lsl = new LightSensor(SensorPort.S1);
		lsr = new LightSensor(SensorPort.S2);
	}
	
	private void run()
	{
		while(!(Button.ESCAPE.isDown()))
		{
			if(onBackgroundL() && !(onBackgroundR()))
			{
				m_pilot.steer(-STEER_RATE);
			}
			else if(onBackgroundR() && !(onBackgroundL()))
			{
				m_pilot.steer(STEER_RATE);
			}
			else if(!onBackgroundR() && !onBackgroundL())
			{
				m_pilot.forward();
			}
		}
	}
	
	protected void calibrateLight()
	{
		System.out.println("Place both sensors over the floor and press Enter.");
		
		Button.waitForAnyPress();
		
		RIGHT_FLOOR_VAL = lsr.getLightValue();
		LEFT_FLOOR_VAL = lsl.getLightValue();
		
		System.out.println("Now place it ready to go and press Enter!");
			
		Button.waitForAnyPress();
	}
	
	protected boolean onBackgroundL()
	{
		return (lsl.getLightValue() > LEFT_FLOOR_VAL - VARIANCE) && (lsl.getLightValue() < LEFT_FLOOR_VAL + VARIANCE);
	}
	
	protected boolean onBackgroundR()
	{
		return (lsr.getLightValue() > RIGHT_FLOOR_VAL - VARIANCE) && (lsr.getLightValue() < RIGHT_FLOOR_VAL + VARIANCE);
	}
		
	protected int getLVal()
	{
		return lsl.getLightValue();
	}
	
	protected int getRVal()
	{
		return lsr.getLightValue();
	}

	
	public static void main(String[] args)
	{
		new LineF(true);
	}

}