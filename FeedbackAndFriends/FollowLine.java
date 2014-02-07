import lejos.nxt.Button;
import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;
import lejos.nxt.comm.RConsole;
import lejos.util.Delay;


public class FollowLine extends RobotSettings {

	public static LightSensor left = new LightSensor(SensorPort.S3);
	public static LightSensor right = new LightSensor(SensorPort.S2);
	private int threshold;
	public FollowLine(int threshold) {
		super();
		this.threshold = threshold;
	}


	public void run(){
			while(m_run())
			{
				findLine();
			}
		}
	

	public static void main(String[] args) {
			Button.waitForAnyPress();
			Delay.msDelay(500);
			FollowLine run = new FollowLine(30);
			run.run();
		
	}
	
	public boolean isLineLeft()
	{
		if(left.readValue()<threshold)
			return true;
		return false;
	}
	
	public boolean isLineRight()
	{
		if(right.readValue()<threshold)
			return true;
		return false;
	}
	
	
	
	public void findLine()
	{
		if(isLineRight() == false && isLineLeft() == false)
		{
			pilot.travel(10, true);
		}
		while(isLineLeft()==true)
		{	
			pilot.rotate(5);
		}
		while(isLineRight()==true)
		{	
			pilot.rotate(-5);
		}
		
		
		
		
	}

}
