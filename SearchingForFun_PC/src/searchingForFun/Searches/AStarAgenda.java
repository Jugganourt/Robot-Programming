package searchingForFun.Searches;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import rp13.search.interfaces.Agenda;
import rp13.search.interfaces.SortedAgenda;

public class AStarAgenda<ItemT extends Comparable<ItemT>> implements SortedAgenda<ItemT>, Agenda<ItemT> {
	
	private Queue<ItemT> list = new LinkedList<ItemT>();

	@Override
	public Iterator iterator() {
		
		return list.iterator();
	}

	@Override
	public ItemT pop() {
		return list.poll();
	}

	@Override
	public boolean isEmpty() {
		if(list.peek() == null)
			return true;
		else 
			return false;
	}

	

	@Override
	public void sort() {
		
		
	}

	@Override
	public void push(ItemT _item) {
		list.add(_item);
	}

	@Override
	public boolean contains(ItemT _item) {
		return list.contains(_item);
	}

	
	
	
	
	

}
