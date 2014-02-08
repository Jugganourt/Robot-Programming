import lejos.robotics.subsumption.Behavior;


public class DriveForward extends RobotSettings implements Behavior  {

	private boolean supressed;

	public DriveForward() {
		super();
		supressed = false;
		
	}

	@Override
	public boolean takeControl() {
		
		return true;
	}

	@Override
	public void action() {
		pilot.forward();
		while(!supressed)
		{
			Thread.yield();
		}
		pilot.stop();
		supressed = false;

	}

	@Override
	public void suppress() {
		pilot.stop();
		supressed = true;

	}

}
