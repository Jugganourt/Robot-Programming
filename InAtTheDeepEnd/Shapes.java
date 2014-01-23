package robotics.inatthedeepend;

import lejos.nxt.Button;
import lejos.nxt.Motor;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.util.Delay;

public class Shapes {

        private final static RegulatedMotor left = Motor.C;
        private final static RegulatedMotor right = Motor.B;
        private final static DifferentialPilot pilot = new DifferentialPilot(56, 122, left, right);
        
        
        @SuppressWarnings("deprecation")
        public static void main(String[] args)
        {
                System.out.println("Hello World!!");
                System.out.println("");
                System.out.println("To start press The Orange Button");
                System.out.println("");
                System.out.println("To STOP hold The Orange Button");
                
                Button.waitForAnyPress();
                Delay.msDelay(500);
                while(Button.ENTER.isUp())
                {
                        move1();
                
                }
                
                Button.waitForAnyPress();
                Delay.msDelay(500);
                while(Button.ENTER.isUp())
                {
                        move2();
                
                }
                
        }
        
        public static void move1()
        {
                pilot.forward();
                Delay.msDelay(2000);
                pilot.rotate(105);
        }
        
        public static void move2()
        {
                pilot.forward();
                Delay.msDelay(2000);
                pilot.rotate(50);
        }
        
}
