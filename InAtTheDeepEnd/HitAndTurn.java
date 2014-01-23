package robotics.inatthedeepend;

import lejos.nxt.Button;
import lejos.nxt.LightSensor;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;
import lejos.nxt.UltrasonicSensor;
import lejos.util.Delay;


/**
 * Made so that the robot can escape a maze
 * @author txs397
 *
 */
public class MazeExcape
{
	public static void main(String[] args)
    {
		System.out.println("To start press The Orange Button");
        System.out.println("");
        System.out.println("To STOP hold The Orange Button");
        
		RobotInformation robot = new RobotInformation();
        UltrasonicSensor range = new UltrasonicSensor(SensorPort.S4);
        TouchSensor sensor = new TouchSensor(SensorPort.S1);
        
        
        robot.ButtonPress();
        while(Button.ENTER.isUp())
        {
        	robot.SetSpeed(200);
        	range.continuous();
          	
        	if(range.getDistance() < 10 )
        	{
        		robot.Stop();
        		Motor.C.forward();
        		Delay.msDelay(500);
        		robot.Stop();
        		Motor.B.forward();
        		Delay.msDelay(500);
        		robot.Stop();
        	}
        	
            if(range.getDistance() <= 45 && !sensor.isPressed()) //if left wall is in range and no bump...keep moving forward
            {
            	robot.Drive(); 
            }
            
            if(range.getDistance() > 45 && !sensor.isPressed())
            {	
            	robot.Stop();
            	robot.NoWall(); //if wall is out of range.... turn left and continue
            	robot.Stop();
            }

            if(range.getDistance() <= 45 && sensor.isPressed()) //if left wall is in range but has a bump...turn -90 degrees (to the right)
            {
            	robot.Stop();
            	robot.Backwards();
            	robot.Backwards();
                robot.Spin(-90);
            }
            
            if(range.getDistance() > 45 && sensor.isPressed())
            {	
            	robot.Stop();
            	robot.Backwards();
            	robot.Backwards();
                robot.Spin(90);
                robot.Stop();
            }
            
        }
    }
}
