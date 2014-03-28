package robot.moves;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Grid.ForbiddenCollection;
import Grid.ForbiddenRoute;
import Grid.Grid;
import Grid.GridSuccessorFunction;
import Grid.Grid.PuzzleMove;
import action.AStarAgenda;
import action.AstarSearch;
import action.Node;
import lejos.nxt.Button;
import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;
import lejos.nxt.Sound;
import lejos.nxt.addon.OpticalDistanceSensor;
import lejos.util.Delay;
import rp.robotics.localisation.ActionModel;
import rp.robotics.localisation.GridPositionDistribution;
import rp.robotics.localisation.PerfectActionModel;
import rp.robotics.localisation.PerfectSensorModel;
import rp.robotics.mapping.GridMap;
import rp.robotics.mapping.Heading;
import rp.robotics.mapping.LocalisationUtils;

public class Grid2 extends RobotSettings {

	public static LightSensor s_left = new LightSensor(SensorPort.S3);
	public static LightSensor s_right = new LightSensor(SensorPort.S2);
	private int threshold = 40;
	private static OpticalDistanceSensor sensor = new OpticalDistanceSensor(
			SensorPort.S4);
	private int wall = 315;
	private int noLine = 15;
	private int shortDistance = 5;
	PerfectActionModel model = new PerfectActionModel();
	private Random rand = new Random();

	private GridMap gridMap = LocalisationUtils.create2014Map1();
	private GridPositionDistribution distribution = new GridPositionDistribution(
			gridMap);
	private ActionModel actionModel = new PerfectActionModel();
	private Heading action = Heading.PLUS_X;
	private float max = 0;
	private int xMax = 0;
	private int yMax = 0;
	private double thres = 0.4;

	public Grid2() {
		super();
	}

	public void run() {
		while (m_run()) {
			pilot.setTravelSpeed(100);
			int ran = rand.nextInt(3);
			forwardCondition();
			rightCondition();
			leftCondition();
			junction(ran);
		}
	}

	public static void main(String[] args) {
		buttonPress();
		Grid2 run = new Grid2();
		run.run();

	}

	public static void buttonPress() {
		Button.waitForAnyPress();
		Delay.msDelay(500);
	}

	public void leftCondition() {
		if (s_left.readValue() < threshold && s_right.readValue() > threshold) {
			pilot.rotate(noLine);
			pilot.travel(shortDistance);
		}
	}

	public void rightCondition() {
		if (s_left.readValue() > threshold && s_right.readValue() < threshold) {
			pilot.rotate(-1 * noLine);
			pilot.travel(shortDistance);
		}
	}

	public void forwardCondition() {
		if (s_left.readValue() > threshold && s_right.readValue() > threshold) {
			pilot.forward();
		}
	}

	public void closeToGridWallCondition() {
		if (sensor.getDistance() < wall) {

			pilot.stop();

			pilot.rotate(90);
		}
	}

	private boolean canMove() {
		if (sensor.getDistance() > wall) {
			return false;
		} else {
			return true;
		}
	}

	public void junction(int ran) {

		// itterate through the map
		// get the maximum probability in the map
		// in the while true
		// while max< thresh

		if (maxprob(gridMap) < thres) {

			if (s_left.readValue() < threshold
					&& s_right.readValue() < threshold) {

				pilot.travel(60);
				/*
				 * if(ran == 1){ System.out.println(action); distribution =
				 * actionModel.updateAfterMove(distribution, action);
				 * distribution.normalise(); action = cycleThroughtLeft(action);
				 * //distribution = sensing4D(distribution); //sensing //
				 * senseleft, right, ahead, update distribution and carry on
				 * 
				 * pilot.rotate(90);
				 * 
				 * 
				 * } else if(ran ==2){ System.out.println(action); distribution
				 * = actionModel.updateAfterMove(distribution, action);
				 * distribution.normalise(); action =
				 * cycleThroughtRight(action); //distribution =
				 * sensing4D(distribution); pilot.rotate(-90); } else{
				 * System.out.println(action); distribution =
				 * actionModel.updateAfterMove(distribution, action);
				 * distribution.normalise(); action =
				 * cycleThroughtForward(action); //distribution =
				 * sensing4D(distribution); pilot.forward(); }
				 * 
				 * while(canMove()){ closeToGridWallCondition(); action =
				 * cycleThroughtLeft(action); }
				 */

				if (sensor.getDistance() > wall) {
					System.out.println(action);
					distribution = actionModel.updateAfterMove(distribution,
							action);
					distribution.normalise();
					action = cycleThroughtForward(action);
				} else {
					System.out.println(action);
					distribution = actionModel.updateAfterMove(distribution,
							action);
					distribution.normalise();
					while (canMove()) {
						closeToGridWallCondition();
						action = cycleThroughtLeft(action);
					}
				}
				System.out.println("map sum: "
						+ distribution.sumProbabilities());
			}
		}
		if (maxprob(gridMap) > thres) {

			Sound.beep();
			System.out.println("Xposs:" + xMax);
			System.out.println("Yposs:" + yMax);
			FindAPlace(xMax, yMax);

		}

	}

	private void FindAPlace(int xPoss, int yPoss) {

		Sound.beepSequence();

		List<ForbiddenRoute> foblist = new ArrayList<ForbiddenRoute>();

		foblist.add(new ForbiddenRoute(2,13));
		foblist.add(new ForbiddenRoute(13,14));
		foblist.add(new ForbiddenRoute(13,24));
		foblist.add(new ForbiddenRoute(23,24));
		foblist.add(new ForbiddenRoute(23,34));
		foblist.add(new ForbiddenRoute(22,23));
		foblist.add(new ForbiddenRoute(4,15));
		foblist.add(new ForbiddenRoute(5,16));
		foblist.add(new ForbiddenRoute(6,17));
		foblist.add(new ForbiddenRoute(15,26));
		foblist.add(new ForbiddenRoute(16,27));
		foblist.add(new ForbiddenRoute(17,28));
		foblist.add(new ForbiddenRoute(8,19));
		foblist.add(new ForbiddenRoute(18,19));
		foblist.add(new ForbiddenRoute(19,30));
		foblist.add(new ForbiddenRoute(30,31));
		foblist.add(new ForbiddenRoute(31,42));
		foblist.add(new ForbiddenRoute(31,32));
		foblist.add(new ForbiddenRoute(37,38));
		foblist.add(new ForbiddenRoute(38,49));
		foblist.add(new ForbiddenRoute(38,39));
		foblist.add(new ForbiddenRoute(44,45));
		foblist.add(new ForbiddenRoute(34,45));
		foblist.add(new ForbiddenRoute(45,46));
		foblist.add(new ForbiddenRoute(46,57));
		foblist.add(new ForbiddenRoute(57,58));
		foblist.add(new ForbiddenRoute(57,68));
		foblist.add(new ForbiddenRoute(48,59));
		foblist.add(new ForbiddenRoute(49,60));
		foblist.add(new ForbiddenRoute(50,61));
		foblist.add(new ForbiddenRoute(59,70));
		foblist.add(new ForbiddenRoute(60,71));
		foblist.add(new ForbiddenRoute(61,72));
		foblist.add(new ForbiddenRoute(52,53));
		foblist.add(new ForbiddenRoute(42,53));
		foblist.add(new ForbiddenRoute(52,63));
		foblist.add(new ForbiddenRoute(62,63));
		foblist.add(new ForbiddenRoute(63,74));
		foblist.add(new ForbiddenRoute(53,54));


		ForbiddenCollection fob = new ForbiddenCollection(foblist);

		Grid state = new Grid(xPoss, yPoss, fob);

		Grid goal = new Grid(5, 3, fob);

		GridSuccessorFunction successor = new GridSuccessorFunction();

		AStarAgenda<Node<PuzzleMove, Grid>> agenda = new AStarAgenda<Node<PuzzleMove, Grid>>();

		AstarSearch<PuzzleMove, Grid> ass = new AstarSearch<PuzzleMove, Grid>(
				state, goal, successor, agenda);

		Node node = ass.doSearch();

		List<PuzzleMove> path = new ArrayList<PuzzleMove>();

		path = node.getPath();
		ArrayList<Integer> list = new ArrayList<Integer>();
		System.out.println(path);
		int count = 1;
		// Stack<Node<Point>> nodes = Solve.solveGrid(new
		// Point(xPoss,yPoss,(xPoss+yPoss)), new Point(5,3,(5+3)),
		// Search.BREADTH_FIRST);
		// SolveGrid grid = new SolveGrid(xPoss, yPoss, null);
		while (true) {
			
		
			forwardCondition();
			rightCondition();
			leftCondition();
			if (s_left.readValue() < threshold && s_right.readValue() < threshold) {
				pilot.travel(60);
				PuzzleMove rount = path.get(count);
//				System.out.println(count);
//				System.out.println(action);
//				System.out.println(rount.toString());
//				System.out.println(rount);
				if (rount.toString().equals("UP")) {
					if(action == Heading.PLUS_Y){
						action = cycleThroughtForward(action);
						if (count != path.size() - 1) {
							System.out.println(path.get(count));
							count++;
						}
					}
					else if(action == Heading.PLUS_X){
						pilot.rotate(90);
						action = cycleThroughtLeft(action);
						if (count != path.size() - 1) {
							System.out.println(path.get(count));
							count++;
						}
					}
					else if(action == Heading.MINUS_X){
						pilot.rotate(-90);
						action = cycleThroughtRight(action);
						if (count != path.size() - 1) {
							System.out.println(path.get(count));
							count++;
						}
					}
					else if(action == Heading.MINUS_Y){
						pilot.rotate(180);
						
						action = cycleThroughtBackwards(action);
						count--;
						
					}
					
				} else if (rount.toString().equals("DOWN")) {
					if(action == Heading.PLUS_Y){
						action = cycleThroughtForward(action);
						if (count != path.size() - 1) {
							System.out.println(path.get(count));
							count++;
						}
					}
					else if(action == Heading.PLUS_X){
						pilot.rotate(-90);
						action = cycleThroughtRight(action);
						if (count != path.size() - 1) {
							System.out.println(path.get(count));
							count++;
						}
					}
					else if(action == Heading.MINUS_X){
						pilot.rotate(90);
						action = cycleThroughtLeft(action);
						if (count != path.size() - 1) {
							System.out.println(path.get(count));
							count++;
						}
					}
					else if(action == Heading.MINUS_Y){
						pilot.rotate(180);
						action = cycleThroughtBackwards(action);
						count--;
						
					}
					
				} else if (rount.toString().equals("LEFT")) {
					if(action == Heading.PLUS_Y){
						pilot.rotate(-90);						
						action = cycleThroughtRight(action);
						if (count != path.size() - 1) {
							System.out.println(path.get(count));
							count++;
						}
					}
					else if(action == Heading.PLUS_X){
						pilot.rotate(180);
						action = cycleThroughtBackwards(action);
						count--;
					}
					else if(action == Heading.MINUS_X){
						action = cycleThroughtForward(action);
						if (count != path.size() - 1) {
							System.out.println(path.get(count));
							count++;
						}
					}
					else if(action == Heading.MINUS_Y){
						pilot.rotate(90);
						action = cycleThroughtLeft(action);
						if (count != path.size() - 1) {
							System.out.println(path.get(count));
							count++;
						}
					}
				} else if (rount.toString().equals("RIGHT")) {
					if(action == Heading.PLUS_Y){
						pilot.rotate(90);
						action = cycleThroughtLeft(action);
						if (count != path.size() - 1) {
							System.out.println(path.get(count));
							count++;
						}
					}
					else if(action == Heading.PLUS_X){
						action = cycleThroughtForward(action);
						if (count != path.size() - 1) {
							System.out.println(path.get(count));
							count++;
						}
					}
					else if(action == Heading.MINUS_X){
						pilot.rotate(180);
						action = cycleThroughtBackwards(action);
						count--;
					}
					else if(action == Heading.MINUS_Y){
						pilot.rotate(-90);
						action = cycleThroughtRight(action);
						if (count != path.size() - 1) {
							System.out.println(path.get(count));
							count++;
						}
					}
					else{
						System.out.println("WELL SHIT!");
					}
				}

				
			}
		}
				/**
		for (int i = 0; i < path.size(); i++) {
			if (path.get(i) == Grid.PuzzleMove.UP) {
				if (action == Heading.PLUS_X) {
					list.add(1);
					action = Heading.MINUS_Y;
				} else if (action == Heading.PLUS_Y) {
					list.add(4);
					action=Heading.MINUS_Y;
				} else if (action == Heading.MINUS_X) {
					list.add(2);
					action = Heading.MINUS_Y;
				} else if (action == Heading.MINUS_Y) {
					list.add(0);
				}
			} else if (path.get(i) == Grid.PuzzleMove.DOWN) {
				if (action == Heading.PLUS_X) {
					list.add(2);
					action = Heading.PLUS_Y;
				} else if (action == Heading.PLUS_Y) {
					list.add(0);
				} else if (action == Heading.MINUS_X) {
					list.add(1);
					action = Heading.PLUS_Y;
				} else if (action == Heading.MINUS_Y) {
					list.add(4);
					action=Heading.PLUS_Y;
				}
			} else if (path.get(i) == Grid.PuzzleMove.RIGHT) {
				if (action == Heading.PLUS_X) {
					list.add(0);
				} else if (action == Heading.PLUS_Y) {
					list.add(1);
					action=Heading.PLUS_X;
				} else if (action == Heading.PLUS_X) {
					list.add(4);
					action=Heading.MINUS_X;
				} else if (action == Heading.MINUS_Y) {
					list.add(2);
					action = Heading.PLUS_X;
				}
				}
			else if (path.get(i) == Grid.PuzzleMove.LEFT) {
				if (action == Heading.PLUS_X) {
					list.add(4);
					action=Heading.MINUS_X;
				} else if (action == Heading.PLUS_Y) {
					list.add(2);
					action=Heading.MINUS_X;
				} else if (action == Heading.MINUS_X) {
					list.add(0);
				} else if (action == Heading.MINUS_Y) {
					list.add(1);
					action = Heading.MINUS_X;
				}
				}
			}**/
		
	}

	private double maxprob(GridMap gridMap) {

		for (int y = 0; y < gridMap.getGridHeight(); y++) {
			for (int x = 0; x < gridMap.getGridWidth(); x++)
				if (distribution.getProbability(x, y) > max) {
					max = distribution.getProbability(x, y);
					xMax = x;
					yMax = y;
				}
		}
		System.out.println("maximum " + max);
		return max;

	}

	public static Heading cycleThroughtLeft(Heading action) {
		if (action == Heading.PLUS_X) {
			action = Heading.MINUS_Y;
		} else if (action == Heading.MINUS_Y) {
			action = Heading.MINUS_X;
		} else if (action == Heading.MINUS_X) {
			action = Heading.PLUS_Y;
		} else if (action == Heading.PLUS_Y) {
			action = Heading.PLUS_X;
		}
		return action;

	}

	public static Heading cycleThroughtRight(Heading action) {
		if (action == Heading.PLUS_X) {
			action = Heading.PLUS_Y;
		} else if (action == Heading.PLUS_Y) {
			action = Heading.MINUS_X;
		} else if (action == Heading.MINUS_X) {
			action = Heading.MINUS_Y;
		} else if (action == Heading.MINUS_Y) {
			action = Heading.PLUS_X;
		}
		return action;

	}

	public static Heading cycleThroughtForward(Heading action) {
		if (action == Heading.PLUS_X) {
			action = Heading.PLUS_X;
		} else if (action == Heading.PLUS_Y) {
			action = Heading.PLUS_Y;
		} else if (action == Heading.MINUS_X) {
			action = Heading.MINUS_X;
		} else if (action == Heading.MINUS_Y) {
			action = Heading.MINUS_Y;
		}
		return action;

	}

	public static Heading cycleThroughtBackwards(Heading action) {
		if (action == Heading.PLUS_X) {
			action = Heading.MINUS_X;
		} else if (action == Heading.PLUS_Y) {
			action = Heading.MINUS_Y;
		} else if (action == Heading.MINUS_X) {
			action = Heading.PLUS_X;
		} else if (action == Heading.MINUS_Y) {
			action = Heading.PLUS_Y;
		}
		return action;

	}
	/**
	 * public GridPositionDistribution sensing4D(GridPositionDistribution g1){
	 * 
	 * PerfectSensorModel sensorModel = new PerfectSensorModel(action); float
	 * valueF = sensor.getRange(); sensormotor.rotate(95);
	 * System.out.println(valueF); float valueR = sensor.getRange();
	 * sensormotor.rotate(95); System.out.println(valueR); float valueB =
	 * sensor.getRange(); sensormotor.rotate(95); System.out.println(valueB);
	 * float valueL = sensor.getRange(); System.out.println(valueL);
	 * sensormotor.rotate(-285); g1 =
	 * sensorModel.updateDistributionAfterSensing(valueL,valueR, valueF,
	 * valueB,g1); g1.normalise(); return g1; }
	 **/
}
