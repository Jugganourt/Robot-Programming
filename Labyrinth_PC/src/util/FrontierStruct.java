package util;

import search.Solve;


public interface FrontierStruct<T>
{
	public T pop();
	public void push(T it);
	public int size();
	public boolean isEmpty();
	public void heuristicSort(Solve.Task t);
	public T get(int i);
}


