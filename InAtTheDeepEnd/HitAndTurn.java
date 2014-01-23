package robotics.inatthedeepend;

import lejos.nxt.Button;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.util.Delay;

public class HitAndTurn {
        
        private final static RegulatedMotor left = Motor.C;
        private final static RegulatedMotor right = Motor.B;
        private final static DifferentialPilot pilot = new DifferentialPilot(56, 122, left, right);
        
        
        public static void main(String[] args)
        {
                TouchSensor sensor = new TouchSensor(SensorPort.S1);
                
                Button.waitForAnyPress();
                Delay.msDelay(500);
                while(Button.ENTER.isUp()) //loop
                {
                        drive();//go forward
                        if(sensor.isPressed()) //if bump
                        {
                                backwards(); //back a bit
                                spin(180); //turn
                        }
                }
        }
        
        private void stop() {
                pilot.stop();
        }

        private static void spin(double angle) {
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

        
        
}
