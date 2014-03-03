package searchingForFun.Searches;

import java.util.Stack;

import rp13.search.interfaces.Agenda;
import rp13.search.problem.puzzle.EightPuzzle;
import rp13.search.problem.puzzle.EightPuzzleSuccessorFunction;
import rp13.search.problem.puzzle.EightPuzzle.PuzzleMove;
import rp13.search.util.ActionStatePair;


public class SearchObject {
	
	
	
	public static void main(String[] args){
		
	
		SearchMechanics<PuzzleMove, EightPuzzle > sm = new SearchMechanics<>();
		//AStar<PuzzleMove, EightPuzzle> star = new AStar<PuzzleMove, EightPuzzle>();
		EightPuzzleSuccessorFunction sf = new EightPuzzleSuccessorFunction();
		Agenda<ActionStatePair<PuzzleMove, EightPuzzle>> agenda = new QueueClass<ActionStatePair<PuzzleMove, EightPuzzle>>();
		Agenda<ActionStatePair<PuzzleMove, EightPuzzle>> agenda2 = new StackClass<ActionStatePair<PuzzleMove, EightPuzzle>>();
		

		Stack<ActionStatePair<PuzzleMove,EightPuzzle>> s = sm.doSearch(EightPuzzle.randomEightPuzzle(),EightPuzzle.orderedEightPuzzle(), sf, agenda);
			for(ActionStatePair<PuzzleMove,EightPuzzle> x : s)
				System.out.print(x.getAction() + ", ");
			System.out.println();
			System.out.println(EightPuzzle.orderedEightPuzzle());
		System.out.println(sm.doSearch(EightPuzzle.randomEightPuzzle(),EightPuzzle.orderedEightPuzzle(), sf, agenda2));
			for(ActionStatePair<PuzzleMove,EightPuzzle> x : s)
				System.out.print(x.getAction() + ", ");
			System.out.println();
			System.out.println(EightPuzzle.orderedEightPuzzle());
		
		//System.out.println(star.doSearch(EightPuzzle.randomEightPuzzle(),EightPuzzle.orderedEightPuzzle(), sf, agenda2));

		//sm.doSearch(EightPuzzle.orderedEightPuzzle(), EightPuzzle.randomEightPuzzle(), sf, agenda);		
		
	}

}
