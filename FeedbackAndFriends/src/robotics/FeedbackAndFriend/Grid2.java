package robotics.FeedbackAndFriend;

import java.util.Random;

public class Grid2 extends RobotMoves {

	private Random rand = new Random();

	public Grid2() {
		super();
	}

	public void run() {
		while (m_run()) {
			pilot.setTravelSpeed(300);

			int ran = rand.nextInt(3);
			forwardCondition();
			junction(ran);
			closeToGridWallCondition();
			rightCondition();
			leftCondition();
		}
	}

	public static void main(String[] args) {
		buttonPress();
		Grid2 run = new Grid2();
		run.run();

	}
}