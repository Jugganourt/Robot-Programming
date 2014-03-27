package search;
import java.util.ArrayList;

import data.Node;
import data.Pair;
import data.Point;
import data_structures.PriorityQueue;
import data_structures.Queue;
import data_structures.Stack;
import util.FrontierStruct;

public class Solve 
{

	public enum Search
	{
		A_STAR, BREADTH_FIRST, DEPTH_FIRST
	}
	
	public enum Task
	{
		GRID, PUZZLE, STRING
	}

	public static final Point pointGoal = new Point(5, 3, (5 + 3));

	public static Stack<Node<Point>> solveGrid(Point toSolve, Point pointGoal, Search search) 
	{
		Point.createBlocks();
		boolean astar = false;
		int count = 1;
		ArrayList<String> explored = new ArrayList<String>();
		/*           <#-#-#-NOTE about explored-#-#-#>
		 * explored is NOT the standard definition of explored in terms
		 * of searches. Our explored ArrayList contains node states that
		 * have been seen before, whether they've been fully explored or not,
		 * this prevents two instances of the same state from ever being
		 * on the frontier set at once. Makes the code far faster and far
		 * more efficient, and is good practice.
		 */

		FrontierStruct<Node<Point>> frontier;
		if (search == Search.BREADTH_FIRST) 
		{
			frontier = new Queue<Node<Point>>();
		} 
		else if (search == Search.DEPTH_FIRST)
		{
			frontier = new Stack<Node<Point>>();
		} 
		else // use that sweet sweet A*
		{
			astar = true;
			frontier = new PriorityQueue<Node<Point>>(new Node<Point>(toSolve));
		}
		Node<Point> solving = new Node<Point>(toSolve);
		frontier.push(solving);

		Node<Point> currentNode;

		while(!frontier.isEmpty())
		{			
			currentNode = frontier.pop();
			
			// Checks if Goal
			if (currentNode.getVal().equals(pointGoal)) 
			{
				System.out.println("The number of steps was " + count);
				System.out.println("The goal was - \n" + currentNode.getVal().toString());
				
				Stack<Node<Point>> route = new Stack<Node<Point>>();
				
				while (currentNode != null) 
				{					
					route.push(currentNode);
					currentNode = currentNode.getParent();
				}
				return route;
			} 
			else 
			{
				ArrayList<Pair<Point, String>> successors = currentNode.getSuccessors();
				for (Pair<Point, String> successor : successors) 
				{
					if(!(explored.contains(successor.getState().toString())))
					{
						Node<Point> newNode = new Node<Point>(successor.getState(), currentNode);
						newNode.setTransition(successor.getDirection());
						frontier.push(newNode);
						explored.add(successor.getState().toString());
						//makes sure nodes in frontier are ALWAYS unique
					}
				}
				
				if (astar) frontier.heuristicSort(Task.GRID);
			}
			
			count++;
		}

		
		// else no solution available D:
		System.err.println("No solution available. Incorrect start state given, or goal blocked.");
		
		return new Stack<Node<Point>>(); //i.e. not null because null pointers are messy
	}
	
	/*
	public static void main(String[] args)
	{
			Point p = new Point(0, 0, 0);
		
			Stack<Node<Point>> movesDF = Solve.solveGrid(p, Solve.Search.DEPTH_FIRST);
			Stack<Node<Point>> movesBF = Solve.solveGrid(p, Solve.Search.BREADTH_FIRST);
			Stack<Node<Point>> movesAS = Solve.solveGrid(p, Solve.Search.A_STAR);
			
	  		System.out.println("------THE MOVE SEQUENCE (OF A*) -----\n");
	  		
	  		int movesLen = movesAS.size();
	  		
	  		for(int i = 0; i < movesLen; i++)
	  		{
	  			Node<Point> currentNode = movesAS.pop();
	  			if (i == 0)
	  			{
	  				System.out.println("Start Node...");
	  			}
	  			else
	  			{
	  				System.out.println(currentNode.getTransition());
	  			}
	  			System.out.println(currentNode.toString());	
	  		}
	}
	FO TESTING FOO
	*/
	
}


