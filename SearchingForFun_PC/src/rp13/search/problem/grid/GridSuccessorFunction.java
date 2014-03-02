package rp13.search.problem.grid;

import java.util.ArrayList;
import java.util.List;

import rp13.search.interfaces.SuccessorFunction;
import rp13.search.problem.grid.Grid.GridMove;
import rp13.search.problem.puzzle.EightPuzzle;
import rp13.search.problem.puzzle.EightPuzzle.PuzzleMove;
import rp13.search.util.ActionStatePair;

public class GridSuccessorFunction implements SuccessorFunction<GridMove,Grid>
{

	@Override
	public void getSuccessors(Grid _state,
			List<ActionStatePair<GridMove, Grid>> _successors) 
	{
		
		assert (_successors != null);

		// for each of the moves that are available
		for (GridMove move : GridMove.values()) 
		{

			// check if it is possible
			if (_state.isPossibleMove(move)) 
			{

				// create a copy of the input state as we don't want to change
				// it
				Grid successor = new Grid(_state);
				// apply the move
				successor.makeMove(move);
				// store the move and action together in a pair and add to
				// successor list
				_successors
						.add(new ActionStatePair<Grid.GridMove, Grid>(
								move, successor));
			}
		
		}
	}
}


