package data;
import java.util.ArrayList;

import util.Successable;

public class Node<E extends Successable<E>> implements Successable<E>
{
	private E val;
	private Node<E> parent;
	private String trans;
	
	public Node(E it)
	{
		val = it;
		this.parent = null;
	}
	
	public Node(E it, Node<E> parent)
	{
		val = it;
		this.parent = parent;
	}

	public E getVal()
	{
		return val;
	}

	public void setVal(E it)
	{
		val = it;
	}

	public ArrayList<Pair<E, String>> getSuccessors()
	{
		return val.getSuccessors();
	}

	public String toSimpleString() 
	{
		return val.toSimpleString();
	}
	
	public String getGoal()
	{
		return val.getGoal();
	}
	public Node<E> getParent() 
	{
		return parent;
	}
	
	public String toString()
	{
		return val.toString();
	}
	
	public void setTransition(String trans)
	{
		this.trans = trans;
	}
	
	public String getTransition()
	{
		return trans;
	}


}
