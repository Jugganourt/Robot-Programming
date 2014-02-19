package robotics.inatthedeepend;

import lejos.nxt.SensorPort;
import lejos.nxt.SensorPortListener;
import lejos.robotics.navigation.DifferentialPilot;

public class EventListener implements SensorPortListener {

	private DifferentialPilot pilot;

	public EventListener(DifferentialPilot pilot) {
		this.pilot = pilot;
	}

	@Override
	public void stateChanged(SensorPort aSource, int aOldValue, int aNewValue) {

		if (aNewValue < 400) {
			pilot.travel(-22);
			pilot.rotate(180);
			pilot.forward();
		}

	}

}
