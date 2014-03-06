package searchingForFun.Searches;

import java.util.Stack;

import rp13.search.interfaces.Agenda;
import rp13.search.problem.puzzle.EightPuzzle;
import rp13.search.problem.puzzle.EightPuzzleSuccessorFunction;
import rp13.search.problem.puzzle.EightPuzzle.PuzzleMove;
import rp13.search.problem.words.Jumble;
import rp13.search.problem.words.JumbleSuccessorFunction;
import rp13.search.problem.words.StringMove;
import rp13.search.util.ActionStatePair;

public class SearchObject {

	public static void main(String[] args){
		

		SearchMechanics<PuzzleMove, EightPuzzle > sm = new SearchMechanics<>();
		AStar<PuzzleMove, EightPuzzle> star = new AStar<PuzzleMove, EightPuzzle>();
		EightPuzzleSuccessorFunction sf = new EightPuzzleSuccessorFunction();
		Agenda<ActionStatePair<PuzzleMove, EightPuzzle>> agenda = new QueueClass<ActionStatePair<PuzzleMove, EightPuzzle>>();
		Agenda<ActionStatePair<PuzzleMove, EightPuzzle>> agenda2 = new StackClass<ActionStatePair<PuzzleMove, EightPuzzle>>();
		
		SearchMechanics<StringMove, Jumble > jumble = new SearchMechanics<>();
		JumbleSuccessorFunction sfJ = new JumbleSuccessorFunction();
		Agenda<ActionStatePair<StringMove, Jumble>> agendaJ = new QueueClass<ActionStatePair<StringMove, Jumble>>();
		Agenda<ActionStatePair<StringMove, Jumble>> agendaJ2 = new StackClass<ActionStatePair<StringMove, Jumble>>();
		AStar<StringMove, Jumble> starJ = new AStar<StringMove, Jumble>();

	
		
		//BFS PUZZLE QUIZ
		Stack<ActionStatePair<PuzzleMove,EightPuzzle>> s = sm.doSearch(EightPuzzle.randomEightPuzzle(),EightPuzzle.orderedEightPuzzle(), sf, agenda);
		for(int i = s.size()-1 ; i >= 0; i--){
			ActionStatePair<PuzzleMove,EightPuzzle> x = s.get(i);
			System.out.print(x.getAction()+ ", ");
		}
		System.out.println();
		
		/** //DFS PUZZLE QUIZ
		Stack<ActionStatePair<PuzzleMove,EightPuzzle>> t = sm.doSearch(EightPuzzle.randomEightPuzzle(),EightPuzzle.orderedEightPuzzle(), sf, agenda2);
		for(int i = t.size()-1 ; i >= 0; i--){
			ActionStatePair<PuzzleMove,EightPuzzle> x = t.get(i);
			System.out.print(x.getAction()+ ", ");
		}
		System.out.println(); */
		
		//A* PUZZEL QUIZ
		Stack<ActionStatePair<PuzzleMove, EightPuzzle>> u = star.doSearch(EightPuzzle.randomEightPuzzle(),EightPuzzle.orderedEightPuzzle(), sf, agenda2);
		for(int i = u.size()-1 ; i >= 0; i--){
			ActionStatePair<PuzzleMove,EightPuzzle> x = u.get(i);
			System.out.print(x.getAction()+ ", ");
		}
		System.out.println(); 
		
		Stack<ActionStatePair<StringMove, Jumble>> v = jumble.doSearch(new Jumble("ollhe") , new Jumble("hello") , sfJ, agendaJ);
		for(int i = 0 ; i >= 0; i--){
			ActionStatePair<StringMove, Jumble> x = v.get(i);
			System.out.print(x.getState()+ ", "); 
		}
		System.out.println(); 
		Stack<ActionStatePair<StringMove, Jumble>> y = starJ.doSearch(new Jumble("idefnr") , new Jumble("friend") , sfJ, agendaJ2);
		for(int i = 0 ; i >= 0; i--){
			ActionStatePair<StringMove, Jumble> z = y.get(i);
			System.out.print(z.getState()+ ", "); 
		}
	}
}
