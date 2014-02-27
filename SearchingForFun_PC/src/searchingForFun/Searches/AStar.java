package searchingForFun.Searches;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import rp13.search.interfaces.Agenda;
import rp13.search.interfaces.SuccessorFunction;
import rp13.search.problem.puzzle.EightPuzzle;
import rp13.search.util.ActionStatePair;



public class AStar<ActionT,StateT> 
{
	
	ActionStatePair<ActionT, StateT> node;
	Queue<StateT> visited = new LinkedList<StateT>();
	List<ActionStatePair<ActionT, StateT>> _successors = new ArrayList<ActionStatePair<ActionT, StateT>>();
	
	

	
	public ActionStatePair<ActionT, StateT> doSearch(StateT start, StateT goal, SuccessorFunction<ActionT, StateT> successorFn, Agenda<ActionStatePair<ActionT, StateT>> agenda)
	{
		
		successorFn.getSuccessors(start, _successors);
	
		visited.add(start);

		for (ActionStatePair<ActionT, StateT> node : _successors) {
			if (visited.contains(node.getState()) == false)
				agenda.push(node);

		}	
		_successors.clear();
		while (!agenda.isEmpty()) 
		{
			node = agenda.pop();
			if (node.getState().equals(goal))
			{
								
				return node;
			} 
			else
			{
				successorFn.getSuccessors(node.getState(), _successors);
				visited.add(node.getState());
				for (ActionStatePair<ActionT, StateT> suc : _successors)
				{
					if (visited.contains(suc.getState()) == false)
						agenda.push(suc);
				}
			}
			_successors.clear();
		}
		return node;
	}


}