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
		SearchMechanics<StringMove, Jumble > jumbleB = new SearchMechanics<>(); // used for the DFS because of strange duplication error 
		JumbleSuccessorFunction sfJ = new JumbleSuccessorFunction();
		Agenda<ActionStatePair<StringMove, Jumble>> agendaJ = new QueueClass<ActionStatePair<StringMove, Jumble>>();
		Agenda<ActionStatePair<StringMove, Jumble>> agendaJ2 = new StackClass<ActionStatePair<StringMove, Jumble>>();
		AStar<StringMove, Jumble> starJ = new AStar<StringMove, Jumble>();

	
		/** 
		//BFS PUZZLE QUIZ
		Stack<ActionStatePair<PuzzleMove,EightPuzzle>> a = sm.doSearch(EightPuzzle.randomEightPuzzle(),EightPuzzle.orderedEightPuzzle(), sf, agenda);
		for(int i = s.size()-1 ; i >= 0; i--){
			ActionStatePair<PuzzleMove,EightPuzzle> x = a.get(i);
			System.out.print(x.getAction()+ ", ");
		}
		System.out.println();
		
		//DFS PUZZLE QUIZ
		Stack<ActionStatePair<PuzzleMove,EightPuzzle>> b = sm.doSearch(EightPuzzle.randomEightPuzzle(),EightPuzzle.orderedEightPuzzle(), sf, agenda2);
		for(int i = t.size()-1 ; i >= 0; i--){
			ActionStatePair<PuzzleMove,EightPuzzle> x = b.get(i);
			System.out.print(x.getAction()+ ", ");
		}
		System.out.println(); 
		
		//A* PUZZEL QUIZ
		Stack<ActionStatePair<PuzzleMove, EightPuzzle>> c = star.doSearch(EightPuzzle.randomEightPuzzle(),EightPuzzle.orderedEightPuzzle(), sf, agenda2);
		for(int i = u.size()-1 ; i >= 0; i--){
			ActionStatePair<PuzzleMove,EightPuzzle> x = c.get(i);
			System.out.print(x.getAction()+ ", ");
		}
		System.out.println(); */
		
		
		///**
		Stack<ActionStatePair<StringMove, Jumble>> d = jumble.doSearch(new Jumble("ollhe") , new Jumble("hello") , sfJ, agendaJ);
		System.out.println(d.get(0).getState());
		System.out.println(); 
		System.out.println(); //*/
		
		Stack<ActionStatePair<StringMove, Jumble>> e = jumbleB.doSearch(new Jumble("gmica") , new Jumble("magic") , sfJ, agendaJ2);
		System.out.println(e.get(0).getState());
		System.out.println();
		System.out.println(); 
		
	
		Stack<ActionStatePair<StringMove, Jumble>> f = starJ.doSearch(new Jumble("idefnr") , new Jumble("friend") , sfJ, agendaJ2);
		System.out.println(f.get(0).getState());
	}
}
