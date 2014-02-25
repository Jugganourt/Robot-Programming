package searchingForFun.Searches;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import rp13.search.problem.puzzle.EightPuzzle;
import rp13.search.problem.puzzle.EightPuzzle.PuzzleMove;
import rp13.search.problem.puzzle.EightPuzzleSuccessorFunction;
import rp13.search.util.ActionStatePair;

//ActionStatePair<PuzzleMove, EightPuzzle>

public class BreadthFirstSearch extends QueueClass 
{
	private QueueClass<ActionStatePair<PuzzleMove, EightPuzzle>> agenda;
	private EightPuzzle start;
	private EightPuzzleSuccessorFunction sf = new EightPuzzleSuccessorFunction();
	private ActionStatePair<PuzzleMove, EightPuzzle> node;

	public BreadthFirstSearch() {
		super();
	}

	// take the first node
	// see if its the goal state
	// if not, finds successors
	// add successors to the back of the list
	// start again...
	public ActionStatePair<PuzzleMove, EightPuzzle> Search() {
		QueueClass<ActionStatePair<PuzzleMove, EightPuzzle>> agenda = new QueueClass<ActionStatePair<PuzzleMove, EightPuzzle>>();
		EightPuzzle start = EightPuzzle.randomEightPuzzle();
		
		
		//System.out.println(start);
		
		EightPuzzle goal = EightPuzzle.orderedEightPuzzle();
		
		//System.out.println(goal);

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
			if (node.getState() == goal) {
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

// generate successors
// add them to a temporary list
// check if i have visited the node before
// if not -> add it to the queue
/**
 * else{ //generate successors...call to successor function that
 * was showin in the last lecutre //... one search node per
 * successor to push on t0 the adgernda //the agenda is the list
 * of next moves ( e.g. successor) // missing inform search to
 * cheack if a node already excicst in the adgenda.. and a
 * closed list to see the ones you have already searched
 * for(SearchNode node: successors){ agenda.push(node); } } }
 */
