import lejos.nxt.LightSensor;
import lejos.robotics.subsumption.Behavior;


public class Junction extends RobotSettings implements Behavior {

	private LightSensor s_left;
	private LightSensor s_right;
	private boolean supressed;
	private int threshold;

	public Junction(LightSensor s_left, LightSensor s_right,int threshold) {
		super();
		this.s_left = s_left;
		this.s_right = s_right;
		supressed = false;
		this.threshold = threshold;
		
	}


	@Override
	public boolean takeControl() {
		if(s_left.readValue()<threshold && s_right.readValue()<threshold)
			return true;
		return false;
	}

	@Override
	public void action() {
		System.out.println("IM AT A JUNTION HELP ME ");
		pilot.stop();
		pilot.rotate(90);
		while(!supressed)
		{
			Thread.yield();
			
			
		}
		supressed = false; 
	}

	@Override
	public void suppress() {
		supressed = true; 

	}

}
