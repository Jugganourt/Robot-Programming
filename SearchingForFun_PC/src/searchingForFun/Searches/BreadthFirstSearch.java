package searchingForFun.Searches;

import java.util.LinkedList;
import java.util.Queue;

import rp13.search.problem.puzzle.EightPuzzle;



public class BreadthFirstSearch extends QueueClass{
	private QueueClass<EightPuzzle> agenda;
	private EightPuzzle start;
	private EightPuzzle node;
	public BreadthFirstSearch(){
		super();
	}
	//take the first node
	//see if its the goal state
	//if not, finds successors
	//add successors to the back of the list
	//start again...
	public EightPuzzle Search(){
	QueueClass<EightPuzzle> agenda = new QueueClass<EightPuzzle>();
	EightPuzzle start = EightPuzzle.randomEightPuzzle();
	
	
	agenda.push(start);
	
	
	while(!agenda.isEmpty()){
		node = agenda.pop();
		if(isGoal(node.getState())){
			return node;
		}
		else
		{
			getSuccessor(node);
			
		}
	//generate successors 
		// add them to a temporary list
		// check if i have visited the node before
		//if not -> add it to the queue 
		/**
		else{
			//generate successors...call to successor function that was showin in the last lecutre
			//... one search node per successor to push on t0 the adgernda
			//the agenda is the list of next moves ( e.g. successor)
			// missing inform search to cheack if a node already excicst in the adgenda.. and a closed list to see the ones you have already searched
			for(SearchNode node: successors){
				agenda.push(node);
			}
		}
	} */
	}
	}
}
