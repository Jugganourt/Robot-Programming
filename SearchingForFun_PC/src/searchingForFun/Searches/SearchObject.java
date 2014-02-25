package searchingForFun.Searches;

import rp13.search.problem.puzzle.EightPuzzle;
import rp13.search.problem.puzzle.EightPuzzle.PuzzleMove;
import rp13.search.util.ActionStatePair;

public class SearchObject {
	
	
	
	public static void main(String[] args){
		
		BreadthFirstSearch bfs = new BreadthFirstSearch();
		
		ActionStatePair<PuzzleMove, EightPuzzle> x = bfs.Search();
		
		System.out.println(x);
		
		
		
		
		
		
		
	}

}
