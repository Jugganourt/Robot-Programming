package test;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import static org.testng.AssertJUnit.assertEquals;
import rp13.search.interfaces.Agenda;
import rp13.search.problem.puzzle.EightPuzzle;
import rp13.search.problem.puzzle.EightPuzzleSuccessorFunction;
import rp13.search.problem.puzzle.EightPuzzle.PuzzleMove;
import rp13.search.problem.words.Jumble;
import rp13.search.problem.words.JumbleSuccessorFunction;
import rp13.search.problem.words.StringMove;
import rp13.search.util.ActionStatePair;
import searchingForFun.Searches.AStar;
import searchingForFun.Searches.QueueClass;
import searchingForFun.Searches.SearchMechanics;
import searchingForFun.Searches.StackClass;

@Test
public class MyTest {

	SearchMechanics<PuzzleMove, EightPuzzle > sm;
	AStar<PuzzleMove, EightPuzzle> starPuzzle;
	EightPuzzleSuccessorFunction sf;
	Agenda<ActionStatePair<PuzzleMove, EightPuzzle>> agendaBFS;
	Agenda<ActionStatePair<PuzzleMove, EightPuzzle>> agendaDFS; 
	
	SearchMechanics<StringMove, Jumble > jumble; 
	JumbleSuccessorFunction sfJ;
	Agenda<ActionStatePair<StringMove, Jumble>> agendaJBFS;
	Agenda<ActionStatePair<StringMove, Jumble>> agendaJDFS;
	AStar<StringMove, Jumble> starJ;
	
	@BeforeMethod
	public void setUp() {
		sm = new SearchMechanics<>();
		starPuzzle = new AStar<PuzzleMove, EightPuzzle>();
		sf = new EightPuzzleSuccessorFunction();
		agendaBFS = new QueueClass<ActionStatePair<PuzzleMove, EightPuzzle>>();
		agendaDFS = new StackClass<ActionStatePair<PuzzleMove, EightPuzzle>>();
		
		jumble = new SearchMechanics<>();
		sfJ = new JumbleSuccessorFunction();
		agendaJBFS  = new QueueClass<ActionStatePair<StringMove, Jumble>>();
		agendaJDFS  = new StackClass<ActionStatePair<StringMove, Jumble>>();
		starJ = new AStar<StringMove, Jumble>();
	}

	public void testBFSPuzzle(){
		assertEquals(EightPuzzle.orderedEightPuzzle().toString() , sm.doSearch(EightPuzzle.randomEightPuzzle(),EightPuzzle.orderedEightPuzzle(), sf, agendaBFS).get(0).getState().toString());
	}
	
	public void testDFSPuzzle(){
		//assertEquals(EightPuzzle.orderedEightPuzzle().toString() , sm.doSearch(EightPuzzle.randomEightPuzzle(),EightPuzzle.orderedEightPuzzle(), sf, agendaDFS).get(0).getState().toString());
	}
	
	public void testAStarPuzzle(){
		assertEquals(EightPuzzle.orderedEightPuzzle().toString() , starPuzzle.doSearch(EightPuzzle.randomEightPuzzle(),EightPuzzle.orderedEightPuzzle(), sf, agendaBFS).get(0).getState().toString());
	}
	
	
	public void testBFSJumble(){
		assertEquals("hello" , jumble.doSearch(new Jumble("ollhe") , new Jumble("hello") , sfJ, agendaJBFS).get(0).getState().toString());
	}
	
	public void testDFSJumble(){
		assertEquals("magic" , jumble.doSearch(new Jumble("gmica") , new Jumble("magic") , sfJ, agendaJDFS).get(0).getState().toString());
	}
	
	public void testAStarJumble(){
		assertEquals("friend" , starJ.doSearch(new Jumble("idefnr") , new Jumble("friend") , sfJ, agendaJDFS).get(0).getState().toString());
	}

}
