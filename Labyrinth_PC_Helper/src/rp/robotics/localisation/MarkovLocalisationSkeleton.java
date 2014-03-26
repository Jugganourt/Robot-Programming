package rp.robotics.localisation;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

import lejos.util.Delay;
import rp.robotics.mapping.GridMap;
import rp.robotics.mapping.Heading;
import rp.robotics.mapping.LocalisationUtils;
import rp.robotics.visualisation.GridPoseDistributionVisualisation;

public class MarkovLocalisationSkeleton {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		JFrame frame = new JFrame("Map Viewer");
		frame.addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent _arg0) {

			}

			@Override
			public void windowIconified(WindowEvent _arg0) {

			}

			@Override
			public void windowDeiconified(WindowEvent _arg0) {

			}

			@Override
			public void windowDeactivated(WindowEvent _arg0) {

			}

			@Override
			public void windowClosing(WindowEvent _arg0) {

			}

			@Override
			public void windowClosed(WindowEvent _arg0) {

				System.exit(0);

			}

			@Override
			public void windowActivated(WindowEvent _arg0) {

			}
		});

		GridMap gridMap = LocalisationUtils.create2014Map1();

		// The probability distribution over the robot's location
		GridPositionDistribution distribution = new GridPositionDistribution(
				gridMap);

		// view the map with 2 pixels as 1 cm
		GridPoseDistributionVisualisation mapVis = new GridPoseDistributionVisualisation(
				distribution, 2);

		frame.add(mapVis);
		frame.pack();
		frame.setSize(1050, 600);
		frame.setVisible(true);

		// ActionModel actionModel = new DummyActionModel();
		ActionModel actionModel = new PerfectActionModel();

		PerfectSensorModel sensorModel = new PerfectSensorModel();
		
		while (true) {
			// Do some action
			// E.g. attempting to move one node in the PLUS_X direction
			//Heading action = Heading.PLUS_Y;
			// I'm faking movement by waiting for some time
			//Delay.msDelay(1000);

			// Once action is completed, apply action model based on the move
			// the robot took. This creates a new instance of
			// GridPoseDistribution and assigns it to distribution
			//distribution = actionModel.updateAfterMove(distribution, action);
			
			
			
			// Update visualisation. Only necessary because it needs to know
			// about the new distribution instance
			mapVis.setDistribution(distribution);
			
		

			

			// Do some sensing
			// ...
			// I'm faking sensing by waiting for some time
			//Delay.msDelay(1000);

			// Once completed apply sensor model as appropriate. This changes
			// the distribution directly (i.e. by reference)
			int x =2;
			int y=1;
			float valueB = gridMap.rangeToObstacleFromGridPoint(x, y, Heading.MINUS_X);
			float valueL = gridMap.rangeToObstacleFromGridPoint(x, y, Heading.MINUS_Y);
			float valueF = gridMap.rangeToObstacleFromGridPoint(x, y, Heading.PLUS_X);
			float valueR = gridMap.rangeToObstacleFromGridPoint(x, y, Heading.PLUS_Y);
			sensorModel.updateDistributionAfterSensing(valueL,valueR, valueF, valueB,distribution);
			distribution.normalise();
			// Note, as the sensor model changes the distribution directly, the
			// visualisation will update automatically so
			// mapVis.setDistribution is not necessary after the sensor model
			System.out.println("map sum: " + distribution.sumProbabilities());

			// Note, as the sensor model changes the distribution directly, the
			// visualisation will update automatically so
			// mapVis.setDistribution is not necessary after the sensor model

		}

	}
}
