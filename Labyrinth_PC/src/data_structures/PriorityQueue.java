package data_structures;
import java.util.ArrayList;

import data.Node;
import data.Point;

import search.Solve;
import util.FrontierStruct;
import util.Successable;

public class PriorityQueue<E extends Successable<?>> implements FrontierStruct<E>
{
	protected ArrayList<E> list;
	//note - used to be a linked list, but leJOS java does not have addfirst/addlast
	//which is the reason (for efficiency) LinkedList was used.
	
	private String startString;
	private E startState;
	private static String goal;
	
	public PriorityQueue(E start)
	{
		list = new ArrayList<E>();
		startState = start;
		startString = start.toSimpleString();
		goal = start.getGoal();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void heuristicSort(Solve.Task t)
	{	
			ArrayList<PairedElem<E, Integer>> pairedList = new ArrayList<PairedElem<E, Integer>>();
			for (int i = 0; i < size(); i++)
			{
				if( t == Solve.Task.GRID )
				{
					Node<Point> p = (Node<Point>)get(i);
					pairedList.add(new PairedElem<E, Integer>(get(i), 
							calcGridHeuristic(p)));
				}
				else
				{
					pairedList.add(new PairedElem<E, Integer>(get(i), 
							calcHeuristic(get(i).toSimpleString())));
				}
			}
			
			Collections.sort(pairedList);
		
			list = new ArrayList<E>();	

			for (PairedElem<E, Integer> pairedElem : pairedList) 
			{
				push(pairedElem.getFirst());
			}
	}
	
	@SuppressWarnings("unchecked")
	private int calcGridHeuristic(Node<Point> n)
	{
		/*
		Point p = n.getVal();
		Node<Point> startn = (Node<Point>)startState;
		Point start = startn.getVal();
		Point goal = Solve.pointGoal;
		int h = Math.abs(goal.getDist() - p.getDist());
		int g = p.getDist();
		System.out.println("At X = " + p.getX() + " Y = " + p.getY());
		System.out.println("h = " + h + " g = " + g);

		return g + h;
		
		 old, guesstimated distance
		Point p = n.getVal();
		Node<Point> startn = (Node<Point>)startState;
		Point start = (Point)startn.getVal();
		Point goal = Solve.pointGoal;
		int h = Math.abs(goal.getX() - p.getX()) + Math.abs(goal.getY() - p.getY());
		int g = Math.abs(p.getX() - start.getX()) + Math.abs(p.getY() - start.getY());
		
		return g + h;
		*/
		
		Point p = n.getVal();
		Node<Point> startn = (Node<Point>)startState;
		Point start = (Point)startn.getVal();
		Point goal = Solve.pointGoal;
		int h = Math.abs(goal.getX() - p.getX()) + Math.abs(goal.getY() - p.getY()) +
				Math.abs(p.getX() - start.getX()) + Math.abs(p.getY() - start.getY());
		
		return h + p.getDist();
	}
	
	private int calcHeuristic(String current)
	{
		int g = diffChars(current, startString);
		int h = diffChars(current, goal);
		return g + h;
	}

	private int sameChars(String s, String y)
	{
		int num = 0;
		
		//we know the strings are equal in length!
		for(int i = 0; i < s.length(); i++)
		{
			if(s.charAt(i) == y.charAt(i)) num++;
		}
		
		return num;
	}
	
	private int diffChars(String s, String y)
	{
		return s.length() - sameChars(s, y);
	}
	

	@Override
	public E pop()
	{
		E thingToPop = list.get(list.size() - 1);
		list.remove(list.size() - 1);
		return thingToPop;
	}
	
	public E get(int i)
	{
		return list.get(i);
	}

	@Override
	public void push(E it) 
	{
		//list.addFirst(it); grrr
		list.add(0, it);
	}

	@Override
	public int size() { return list.size(); }

	@Override
	public boolean isEmpty() { return list.isEmpty(); }
	
	private class PairedElem<F, I extends Comparable<I>> implements Comparable<PairedElem<F, I>>
	{
		private F first;
		private I last;
		
		PairedElem(F first, I last)
		{
			this.first = first;
			this.last = last;
		}
		
		public F getFirst()
		{
			return first;
		}
		
		public I getLast()
		{
			return last;
		}
		
		@Override
		public int compareTo(PairedElem<F, I> o) 
		{
			return getLast().compareTo(o.getLast());
		}
	}
	
}
