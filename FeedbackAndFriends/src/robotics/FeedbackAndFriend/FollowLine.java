package robotics.FeedbackAndFriend;

public class FollowLine extends RobotMoves {

	public FollowLine() {
		super();
	}

	public void run() {
		while (m_run()) {
			pilot.setTravelSpeed(300);

			leftCondition();
			stuckCondition();
			forwardCondition();
			rightCondition();

		}
	}

	public static void main(String[] args) {
		buttonPress();
		FollowLine run = new FollowLine();
		run.run();

	}
}