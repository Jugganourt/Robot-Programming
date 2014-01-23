package robotics.inatthedeepend;

import lejos.nxt.Button;
import lejos.util.Delay;

/**
 * makes the robot draw shapes
 * @author txs397
 */
public class Shapes 
{
	public static void main(String[] args)
    {
		RobotInformation robot = new RobotInformation();
		robot.SetSpeed(200);
        System.out.println("Hello World!!");
        System.out.println("");
        System.out.println("To start press The Orange Button");
        System.out.println("");
        System.out.println("To STOP hold The Orange Button");
              
        robot.ButtonPress();
        while(Button.ENTER.isUp())
        {
        	robot.Square();
        }
        Delay.msDelay(500);
        while(Button.ENTER.isUp())
        {
        	robot.Hexagon();
        }
    }
}
