package robotics.FeedbackAndFriend;

/* if the object is further than 10 cm go  speed 300 
 * calculation to slowly decrease as he gets closer 
 * if the object is as <5cm stop  */

public class SpeedControl extends RobotMoves {

	public SpeedControl() {
		super();
	}

	public void run() {

		while (m_run()) {
			speedChange();
		}
	}

	public static void main(String[] args) {
		buttonPress();
		SpeedControl run = new SpeedControl();
		run.run();
	}
}
