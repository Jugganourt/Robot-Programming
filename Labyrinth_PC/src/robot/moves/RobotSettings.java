package robot.moves;

import lejos.nxt.Button;
import lejos.nxt.Motor;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.navigation.DifferentialPilot;

public class RobotSettings {


	private final static RegulatedMotor left = Motor.C;
	private final static RegulatedMotor right = Motor.B;
	public final static RegulatedMotor sensormotor = Motor.A;
	public final static DifferentialPilot pilot = new DifferentialPilot(56,
			122, left, right);
	
	
	public static Boolean m_run()
	{
		while(Button.ENTER.isUp())
		{
			return true;
		}
		return false;
	}
}
