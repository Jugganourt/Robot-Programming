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
		int i=15;
		while (i>0) {
			
			Heading action = Heading.PLUS_X;
			Delay.msDelay(1000);
			int x =5;
			int y=2;
			/*
			 * you've done the move and updated the distribution, do the sensing there and update your model
			 * 
			 */
			
			distribution = actionModel.updateAfterMove(distribution, action);
			distribution.normalise();
			float valueB = gridMap.rangeToObstacleFromGridPoint(x+1, y, Heading.MINUS_X);
			System.out.println("minus x: "+valueB);
			float valueL =  gridMap.rangeToObstacleFromGridPoint(x+1, y, Heading.MINUS_Y);
			System.out.println("minus y: "+valueL);
			float valueF = gridMap.rangeToObstacleFromGridPoint(x+1, y, Heading.PLUS_X);
			System.out.println("plus x: "+valueF);
			float valueR = gridMap.rangeToObstacleFromGridPoint(x+1, y, Heading.PLUS_Y);
			System.out.println("plus y: "+valueR);
			distribution = sensorModel.updateDistributionAfterSensing(valueL, valueR, valueF, valueB, distribution);
			distribution.normalise();
			mapVis.setDistribution(distribution);
			i--;
		

			

			// Do some sensing
			// ...
			// I'm faking sensing by waiting for some time
			//Delay.msDelay(1000);

			// Once completed apply sensor model as appropriate. This changes
			// the distribution directly (i.e. by reference)
		/*	Delay.msDelay(5000);
			
			distribution = sensorModel.updateDistributionAfterSensing(valueL,valueR, valueF, valueB,distribution);
			distribution.normalise();
			// Note, as the sensor model changes the distribution directly, the
			// visualisation will update automatically so
			// mapVis.setDistribution is not necessary after the sensor model
			System.out.println("map sum: " + distribution.sumProbabilities());
*/
			// Note, as the sensor model changes the distribution directly, the
			// visualisation will update automatically so
			// mapVis.setDistribution is not necessary after the sensor model

		}

	}
}
