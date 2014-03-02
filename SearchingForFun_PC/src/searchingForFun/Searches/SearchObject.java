package searchingForFun.Searches;

import rp13.search.interfaces.Agenda;
import rp13.search.problem.puzzle.EightPuzzle;
import rp13.search.problem.puzzle.EightPuzzleSuccessorFunction;
import rp13.search.problem.puzzle.EightPuzzle.PuzzleMove;
import rp13.search.util.ActionStatePair;


public class SearchObject {
	
	
	
	public static void main(String[] args){
		
	
		SearchMechanics<PuzzleMove, EightPuzzle > sm = new SearchMechanics<>();
		AStar<PuzzleMove, EightPuzzle> star = new AStar<PuzzleMove, EightPuzzle>();
		EightPuzzleSuccessorFunction sf = new EightPuzzleSuccessorFunction();
		Agenda<ActionStatePair<PuzzleMove, EightPuzzle>> agenda = new QueueClass<ActionStatePair<PuzzleMove, EightPuzzle>>();
		
<<<<<<< HEAD
		System.out.println(sm.doSearch(EightPuzzle.randomEightPuzzle(),EightPuzzle.orderedEightPuzzle(), sf, agenda));
		System.out.println(sm.doSearch(EightPuzzle.randomEightPuzzle(),EightPuzzle.orderedEightPuzzle(), sf, agenda2));
		//System.out.println(star.doSearch(EightPuzzle.randomEightPuzzle(),EightPuzzle.orderedEightPuzzle(), sf, agenda2));
=======
		sm.doSearch(EightPuzzle.orderedEightPuzzle(), EightPuzzle.randomEightPuzzle(), sf, agenda);
		
		
		System.out.println(sm.doSearch(EightPuzzle.orderedEightPuzzle(), EightPuzzle.randomEightPuzzle(), sf, agenda));
		
		
>>>>>>> parent of ddcd883... GENERICS SORED
	
		
		
		
		
		
	}

}
