package searchingForFun.Searches;



import java.util.Iterator;

import java.util.Stack;

import rp13.search.interfaces.Agenda;

public class StackClass<ItemT> implements Agenda<ItemT> {

	Stack<ItemT> stack = new Stack<ItemT>();
	@Override
	public Iterator<ItemT> iterator() {
		return stack.iterator();
	}

	@Override
	public void push(ItemT _item) {
		stack.push(_item);
		
	}

	@Override
	public ItemT pop() {
	
		return stack.pop();
	}

	@Override
	public boolean isEmpty() {
		if (stack.peek() == null)
			return true;
		else
			return false;
	}

	@Override
	public boolean contains(ItemT _item) {
		return stack.contains(_item);
	}

}
