package robotics.inatthedeepend;

import lejos.nxt.Button;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.util.Delay;

public class MazeExcape
{
        private final static RegulatedMotor left = Motor.C;
        private final static RegulatedMotor right = Motor.B;
        private final static DifferentialPilot pilot = new DifferentialPilot(56, 122, left, right);

        public static void main(String[] args)
        {
                UltrasonicSensor range = new UltrasonicSensor(SensorPort.S4);
                TouchSensor sensor = new TouchSensor(SensorPort.S1);
                
                Button.waitForAnyPress();
                Delay.msDelay(500);
                while(Button.ENTER.isUp())
                {
                        range.continuous();
                        
                        if(range.getDistance() < 50 && !sensor.isPressed())        //if no bump and out of range sensor keep driving
                        {
                                drive();         //keep driving forward
                        }
                        
                        else if(range.getDistance() < 50 && sensor.isPressed())
                        {
                                bump();
                        }
                        
                        else
                        {
                                wallLocate();
                        }
                }
        }
        
        public static void bump()
        {
                TouchSensor sensor = new TouchSensor(SensorPort.S1);
                if(sensor.isPressed()) //if bump
                {
                        backwards(); //back a bit
                        spin(180); //turn
                }
        }
        
        private void stop()
        {
                pilot.stop();
        }

        private static void spin(double angle)
        {
                pilot.rotate(angle);;
        }
        
        public static void backwards()
        {
                pilot.backward();
                Delay.msDelay(500);
        }
        
        public static void drive()
        {
                pilot.forward();
        }
        public static void wallLocate()
        {
                pilot.forward();
                Delay.msDelay(700);
                pilot.rotate(90);
                pilot.forward();
                Delay.msDelay(1500);
        }
}
