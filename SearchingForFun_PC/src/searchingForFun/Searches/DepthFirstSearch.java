package searchingForFun.Searches;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import rp13.search.problem.puzzle.EightPuzzle;
import rp13.search.problem.puzzle.EightPuzzleSuccessorFunction;
import rp13.search.problem.puzzle.EightPuzzle.PuzzleMove;
import rp13.search.util.ActionStatePair;

public class DepthFirstSearch  {
		
	private StackClass<ActionStatePair<PuzzleMove, EightPuzzle>> agenda;
	private EightPuzzle start;
	private EightPuzzleSuccessorFunction sf = new EightPuzzleSuccessorFunction();
	private ActionStatePair<PuzzleMove, EightPuzzle> node;

	public DepthFirstSearch() {
		super();
	}

	public ActionStatePair<PuzzleMove, EightPuzzle> Search() {
		agenda = new StackClass<ActionStatePair<PuzzleMove, EightPuzzle>>();
		start = EightPuzzle.randomEightPuzzle();
		
		
		System.out.println(start);
		
		EightPuzzle goal = EightPuzzle.orderedEightPuzzle();
		
		System.out.println(goal);

		// start.getSuccessor(start,list);
		Queue<EightPuzzle> visited = new LinkedList<EightPuzzle>();
		List<ActionStatePair<PuzzleMove, EightPuzzle>> _successors = new ArrayList<ActionStatePair<PuzzleMove, EightPuzzle>>();
		sf.getSuccessors(start, _successors);
		
		visited.add(start);

		for (ActionStatePair<PuzzleMove, EightPuzzle> node : _successors) {
			//System.out.println(node);
			if (visited.contains(node.getState()) == false)
				agenda.push(node);

		}
		_successors.clear();
		while (!agenda.isEmpty()) {
			node = agenda.pop();
			//System.out.println(node);
			if (node.getState().equals(goal)) {
			
				
				return node;
			} else {
				sf.getSuccessors(node.getState(), _successors);
				visited.add(node.getState());
				for (ActionStatePair<PuzzleMove, EightPuzzle> suc : _successors) {
					if (visited.contains(suc.getState()) == false)
						agenda.push(suc);
				}
		
			}
			_successors.clear();
		}
		return node;
		
	}
}
