package searchingForFun.Searches;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import rp13.search.interfaces.Agenda;
import rp13.search.interfaces.SuccessorFunction;
import rp13.search.problem.puzzle.States;
import rp13.search.util.ActionStatePair;

public class SearchMechanics<ActionT,StateT extends States<StateT>> 
{
	ActionStatePair<ActionT, StateT> node;
	ActionStatePair<ActionT, StateT> parent;
	Queue<StateT> visited = new LinkedList<StateT>();
	Stack<ActionStatePair<ActionT,StateT>> s = new Stack<ActionStatePair<ActionT,StateT>>();
	List<ActionStatePair<ActionT, StateT>> successors = new ArrayList<ActionStatePair<ActionT, StateT>>();
		
	public Stack<ActionStatePair<ActionT, StateT>> doSearch(StateT start, StateT goal, SuccessorFunction<ActionT, StateT> successorFn, Agenda<ActionStatePair<ActionT, StateT>> agenda)
	{
		System.out.println(start);
		
		successorFn.getSuccessors(start, successors);
		visited.add(start);

		for (ActionStatePair<ActionT, StateT> suc : successors) {
			if (!visited.contains(suc.getState())){
				agenda.push(suc);
			}
		}		
		successors.clear();
		
		while (!agenda.isEmpty()) 
		{
			node = agenda.pop();
			
			if (node.getState().equals(goal))
			{
				s.push(node);
				while(node.getParent() != null)
				{
					s.push(node.getParent());
					node = node.getParent();
				}
				return s;
			} 
			else
			{
				successorFn.getSuccessors(node.getState(), successors);				
				visited.add(node.getState());
				for (ActionStatePair<ActionT, StateT> suc : successors)
				{
					
					suc.setParent(node);
					if (visited.contains(suc.getState()) == false)
						agenda.push(suc);
				}
			}
			successors.clear();
			
			
		}
		return null;
	}		
}