package searchingForFun.Searches;

import rp13.search.problem.puzzle.EightPuzzle;
import rp13.search.problem.puzzle.EightPuzzle.PuzzleMove;
import rp13.search.util.ActionStatePair;

public class SearchObject {
	
	
	
	public static void main(String[] args){
		
		BreadthFirstSearch bfs = new BreadthFirstSearch();
		
		DepthFirstSearch dfs = new DepthFirstSearch();
		System.out.println("Breadth");
		
		ActionStatePair<PuzzleMove, EightPuzzle> x = bfs.Search();
		System.out.println(x);
		
		 
		System.out.println("");
		
		System.out.println("Depth");
		
		ActionStatePair<PuzzleMove, EightPuzzle> y = dfs.Search();
		System.out.println(y);
		
		
		
		
		
		
	}

}
