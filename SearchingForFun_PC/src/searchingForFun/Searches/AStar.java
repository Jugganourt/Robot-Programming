package searchingForFun.Searches;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

import rp13.search.interfaces.Agenda;
import rp13.search.interfaces.SuccessorFunction;
import rp13.search.problem.puzzle.States;
import rp13.search.util.ActionStatePair;

public class AStar<ActionT, StateT extends States<StateT>> {

	
	ActionStatePair<ActionT, StateT> node;
	ActionStatePair<ActionT, StateT> parent;
	Queue<StateT> visited = new LinkedList<StateT>();
	Stack<ActionStatePair<ActionT,StateT>> s = new Stack<ActionStatePair<ActionT,StateT>>();
	List<ActionStatePair<ActionT, StateT>> _successors = new ArrayList<ActionStatePair<ActionT, StateT>>();
	
	PriorityQueue<ActionStatePair<ActionT, StateT>> sortedSuccessors;

	
	public Stack<ActionStatePair<ActionT, StateT>> doSearch(StateT start, StateT goal, SuccessorFunction<ActionT, StateT> successorFn, Agenda<ActionStatePair<ActionT, StateT>> agenda) {

		sortedSuccessors = new PriorityQueue<ActionStatePair<ActionT, StateT>>(
				100, new Comparator<ActionStatePair<ActionT, StateT>>() {

					@Override
					public int compare(ActionStatePair<ActionT, StateT> o1,
							ActionStatePair<ActionT, StateT> o2) {
						if (o1.getHeur() < o2.getHeur())
							return -1;
						else if (o1.getHeur() == o2.getHeur())
							return 0;
						else
							return 1;

					}

				});
		successorFn.getSuccessors(start, _successors);

		visited.add(start);
		System.out.println("start");
		System.out.println(start);

		for (ActionStatePair<ActionT, StateT> node : _successors) {
			node.setHeur(node.getState().heuristic());
			sortedSuccessors.add(node);
		}
		// System.out.println("firstsuccessors");
		// System.out.println(sortedSuccessors);
		while (visited.contains(sortedSuccessors.peek().getState()))
			sortedSuccessors.poll();

		agenda.push(sortedSuccessors.peek());

		sortedSuccessors.clear();
		_successors.clear();
		/*
		 * System.out.println("agenda"); for (ActionStatePair<ActionT, StateT>
		 * element : agenda) { System.out.print(element.getState()); }
		 */
		while (!agenda.isEmpty()) {
			node = agenda.pop();
			if (node.getState().equals(goal)) {
				s.push(node);
				while(node.getParent() != null)
				{
					s.push(node.getParent());
					node = node.getParent();
				}
				return s;
			} else {
				successorFn.getSuccessors(node.getState(), _successors);
				visited.add(node.getState());
				// System.out.println(node.getState());
				for (ActionStatePair<ActionT, StateT> suc : _successors) {
					suc.setHeur(suc.getState().heuristic());
					sortedSuccessors.add(suc);
					suc.setParent(node);
				}

				while (visited.contains(sortedSuccessors.peek().getState()))
					sortedSuccessors.poll();

				agenda.push(sortedSuccessors.peek());

			}
			_successors.clear();
			sortedSuccessors.clear();
		}
		return null;
	}
}
