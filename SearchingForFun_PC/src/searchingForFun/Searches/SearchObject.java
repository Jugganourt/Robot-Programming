package searchingForFun.Searches;

import rp13.search.interfaces.Agenda;
import rp13.search.problem.puzzle.EightPuzzle;
import rp13.search.problem.puzzle.EightPuzzleSuccessorFunction;
import rp13.search.problem.puzzle.EightPuzzle.PuzzleMove;
import rp13.search.util.ActionStatePair;


public class SearchObject {
	
	
	
	public static void main(String[] args){
		
		BreadthFirstSearch bfs = new BreadthFirstSearch();
		DepthFirstSearch dfs = new DepthFirstSearch();
		SearchMechanics<PuzzleMove, EightPuzzle > sm = new SearchMechanics<>();
		EightPuzzleSuccessorFunction sf = new EightPuzzleSuccessorFunction();
		
		Agenda<SearchMechanics<EightPuzzle, PuzzleMove>> agenda = new QueueClass<SearchMechanics<EightPuzzle, PuzzleMove>>();
		
		sm.doSearch(EightPuzzle.orderedEightPuzzle(), EightPuzzle.randomEightPuzzle(), sf, agenda);
		
		
		System.out.println(sm.doSearch(EightPuzzle.orderedEightPuzzle(), EightPuzzle.randomEightPuzzle(), sf, agenda));
		
		
		
		System.out.println("Breadth");
		ActionStatePair<PuzzleMove, EightPuzzle> x = bfs.Search();
		System.out.println(x);
		
		System.out.println("");
		System.out.println("Depth");
		ActionStatePair<PuzzleMove, EightPuzzle> y = dfs.Search();
		System.out.println(y);
		
		
		
		
		
		
	}

}
