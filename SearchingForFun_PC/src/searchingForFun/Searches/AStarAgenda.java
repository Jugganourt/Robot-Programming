package searchingForFun.Searches;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import rp13.search.interfaces.SortedAgenda;
import stuffWeNoLongerNeed.SearchNode;

public class AStarAgenda<ItemT extends Comparable<ItemT>> implements SortedAgenda {
	
	private ArrayList<ItemT> list = new ArrayList<ItemT>();

	@Override
	public void push(Object _item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object pop() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean contains(Object _item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterator iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	@Override
	public void sort() {
		
		java.util.Collections.sort(list);
	}
	
	

}
