package data_structures;
import java.util.ArrayList;

import search.Solve;
import util.FrontierStruct;

public class Queue<E> implements FrontierStruct<E>
{
	protected ArrayList<E> list;
	//note - used to be a linked list, but leJOS java does not have addfirst/addlast
	//which is the reason (for efficiency) LinkedList was used.

	public Queue()
	{
		list = new ArrayList<E>();
	}

	public void push(E it) 
	{
		//list.addFirst(it); grrrrr
		list.add(0, it);
	}

	public E pop()
	{
		E thingToPop = list.get(list.size() - 1);
		list.remove(list.size() - 1);
		return thingToPop;
	}

	public int size() { return list.size(); }
	
	public boolean isEmpty() { return list.isEmpty(); }
	
	public E get(int i)
	{
		return list.get(i);
	}
	
	public void heuristicSort(Solve.Task t){  };
	
	protected E getFirst()
	{
		return list.get(list.size() - 1);
	}
}
