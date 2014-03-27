package data_structures;
import java.util.LinkedList;

import search.Solve;
import search.Solve.Task;
import util.FrontierStruct;

public class Stack<E> implements FrontierStruct<E>
{

	protected LinkedList<E> list;

	public Stack()
	{
		list = new LinkedList<E>();
	}
	
	public void push(E it)
	{
		list.add(it);
	}

	public E pop()
	{
		E thingToPop = list.get(list.size() - 1);
		list.remove(list.size() - 1);
		return thingToPop;
	}

	public int size() { return list.size(); }

	@SuppressWarnings("unchecked")
	public E[] asArray()
	{
		return (E[])list.toArray();
	}
	
	public E get(int i)
	{
		return list.get(i);
	}

	public boolean isEmpty() { return list.isEmpty(); }
	
	public void heuristicSort(Solve.Task t){  };
}
