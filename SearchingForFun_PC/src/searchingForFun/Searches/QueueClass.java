package searchingForFun.Searches;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import rp13.search.interfaces.Agenda;

public class QueueClass<ItemT> implements Agenda<ItemT> {

	Queue<ItemT> queue = new LinkedList<ItemT>();
	Queue<ItemT> copy = new LinkedList<ItemT>();
	@Override
	public Iterator<ItemT> iterator() {
		// TODO Auto-generated method stub
		return queue.iterator();
	}

	@Override
	public void push(ItemT _item) {
		queue.add(_item);

	}

	@Override
	public ItemT pop() {
		// TODO Auto-generated method stub
		return queue.poll();
	}

	@Override
	public boolean isEmpty() {
		if (queue.peek() == null)
			return true;
		else
			return false;
	}

	@Override
	public boolean contains(ItemT _item) {
		
			return queue.contains(_item);
			
	}

}
