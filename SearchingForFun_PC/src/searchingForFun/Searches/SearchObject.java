package searchingForFun.Searches;

import rp13.search.interfaces.Agenda;
import rp13.search.problem.puzzle.EightPuzzle;
import rp13.search.problem.puzzle.EightPuzzleSuccessorFunction;
import rp13.search.problem.puzzle.EightPuzzle.PuzzleMove;
import rp13.search.problem.grid.Grid;
import rp13.search.problem.grid.Grid.GridMove;
import rp13.search.util.ActionStatePair;


public class SearchObject {
	
	
	
	public static void main(String[] args){
		
	
		SearchMechanics<PuzzleMove, EightPuzzle > sm = new SearchMechanics<>();
		AStar<GridMove, Grid> star = new AStar<GridMove, Grid>();
		EightPuzzleSuccessorFunction sf = new EightPuzzleSuccessorFunction();
		Agenda<ActionStatePair<PuzzleMove, EightPuzzle>> agenda = new QueueClass<ActionStatePair<PuzzleMove, EightPuzzle>>();
		Agenda<ActionStatePair<PuzzleMove, EightPuzzle>> agenda2 = new StackClass<ActionStatePair<PuzzleMove, EightPuzzle>>();
		Agenda<ActionStatePair<GridMove, Grid>> agenda3 = new StackClass<ActionStatePair<GridMove, Grid>>();
		System.out.println(sm.doSearch(EightPuzzle.randomEightPuzzle(),EightPuzzle.orderedEightPuzzle(), sf, agenda));
		System.out.println(sm.doSearch(EightPuzzle.randomEightPuzzle(),EightPuzzle.orderedEightPuzzle(), sf, agenda2));
		System.out.println(star.doSearch(Grid.setStart(2, 3),Grid.setGoal(7,9), star, agenda2));
	
		
		
		
		
		
	}

	

}
