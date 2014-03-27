package main;

import lejos.nxt.MotorPort;
import lejos.nxt.NXTRegulatedMotor;
import lejos.robotics.navigation.DifferentialPilot;

/**
 * Has all of the default constants for K9, to be extended by other classes.
 * 
 * 
 * @author axh387
 *
 */
public class XK9Class
{
	
	protected final DifferentialPilot m_pilot;
	
	protected XK9Class()
	{
		NXTRegulatedMotor right = new NXTRegulatedMotor(MotorPort.B);
		NXTRegulatedMotor left = new NXTRegulatedMotor(MotorPort.A);
																		
																		
		m_pilot = new DifferentialPilot(56.0, 110.0, left, right);
		m_pilot.setTravelSpeed(150.0);
		m_pilot.setRotateSpeed(150.0);
	}
	
    /*
            public DifferentialPilot(double wheelDiameter,
                     double trackWidth,
             RegulatedMotor leftMotor,
             RegulatedMotor rightMotor,
             boolean reverse)
             Allocates a DifferentialPilot object, and sets the physical parameters of the NXT robot.
    Parameters:
    wheelDiameter - Diameter of the tire, in any convenient units (diameter in mm is usually printed on the tire).
    trackWidth - Distance between center of right tire and center of left tire, in same units as wheelDiameter.
    leftMotor - The left Motor (e.g., Motor.C).
    rightMotor - The right Motor (e.g., Motor.A).
    reverse - If true, the NXT robot moves forward when the motors are running backward.
    ^^^^ LOOK THIS THING IS WHAT WE NEED
     */
}
