package main;

import data.Node;
import data.Point;
import data_structures.Stack;
import robot.moves.Grid2;
import search.Solve;
import lejos.nxt.Button;
import lejos.nxt.Sound;

public class SolveGrid extends Grid2 {
	public enum Direction {
		NORTH, SOUTH, EAST, WEST
	}

	public SolveGrid(int x, int y, Direction currDir2) {
		super();
		genRoute(x, y, currDir2);
	}

	private void genRoute(int x, int y, Direction currDir2) {
		Stack<Node<Point>> moves = Solve.solveGrid(new Point(x, y, 0),
				new Point(10, 6, 16), Solve.Search.BREADTH_FIRST);

		Direction currDir = currDir2;

		int movesLen = moves.size();

		for (int i = 0; i < movesLen; i++) {
			Node<Point> currentNode = moves.pop();
			if (i != 0) {
				switch (currentNode.getTransition()) {
				case "NORTH":
					if (currDir == Direction.NORTH) {
						//addMove(2);
					} else if (currDir == Direction.EAST) {
						//addMove(1);
					} else if (currDir == Direction.WEST) {
						//addMove(3);
					}
					currDir = Direction.NORTH;
					break;
				case "SOUTH":
					if (currDir == Direction.SOUTH) {
						//addMove(2);
					} else if (currDir == Direction.EAST) {
						//addMove(3);
					} else if (currDir == Direction.WEST) {
						//addMove(1);
					}
					currDir = Direction.SOUTH;
					break;
				case "EAST":
					if (currDir == Direction.EAST) {
						//addMove(2);
					} else if (currDir == Direction.NORTH) {
						//addMove(3);
					} else if (currDir == Direction.SOUTH) {
						//addMove(1);
					}
					currDir = Direction.EAST;
					break;
				case "WEST":
					if (currDir == Direction.WEST) {
						//addMove(2);
					} else if (currDir == Direction.NORTH) {
						//addMove(1);
					} else if (currDir == Direction.SOUTH) {
						//addMove(3);
					}
					currDir = Direction.WEST;
					break;
				}
			}
		}
		run();
	}

	private void addMove(int i) {
		// TODO Auto-generated method stub
		
	}
}
