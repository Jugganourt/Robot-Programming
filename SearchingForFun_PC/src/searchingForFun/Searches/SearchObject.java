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
		
		AStar<PuzzleMove, EightPuzzle> star = new AStar<PuzzleMove, EightPuzzle>();
		EightPuzzleSuccessorFunction sf = new EightPuzzleSuccessorFunction();
		Agenda<ActionStatePair<PuzzleMove, EightPuzzle>> agenda = new QueueClass<ActionStatePair<PuzzleMove, EightPuzzle>>();
		Agenda<ActionStatePair<PuzzleMove, EightPuzzle>> agenda2 = new StackClass<ActionStatePair<PuzzleMove, EightPuzzle>>();
		System.out.println("goal:");
		System.out.println(star.doSearch(EightPuzzle.randomEightPuzzle(),EightPuzzle.orderedEightPuzzle(), sf, agenda2));
		Stack<ActionStatePair<PuzzleMove,EightPuzzle>> s = sm.doSearch(EightPuzzle.randomEightPuzzle(),EightPuzzle.orderedEightPuzzle(), sf, agenda);
		for(int i = s.size()-1 ; i >= 0; i--){
			ActionStatePair<PuzzleMove,EightPuzzle> x = s.get(i);
			System.out.print(x.getAction()+ ", ");
		}
		System.out.println();
		
		Stack<ActionStatePair<PuzzleMove,EightPuzzle>> t = sm.doSearch(EightPuzzle.randomEightPuzzle(),EightPuzzle.orderedEightPuzzle(), sf, agenda2);
		for(int i = s.size()-1 ; i >= 0; i--){
			ActionStatePair<PuzzleMove,EightPuzzle> x = t.get(i);
			System.out.print(x.getAction()+ ", ");
		}
		System.out.println();
		
		
		//System.out.println(star.doSearch(EightPuzzle.randomEightPuzzle(),EightPuzzle.orderedEightPuzzle(), sf, agenda2));

		//sm.doSearch(EightPuzzle.orderedEightPuzzle(), EightPuzzle.randomEightPuzzle(), sf, agenda);		
		
	}

}
