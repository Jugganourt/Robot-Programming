package rp.robotics.localisation;

import lejos.util.Delay;
import rp.robotics.mapping.Heading;

/**
 * Example structure for an action model that should move the probabilities 1
 * cell in the requested direction. In the case where the move would take the
 * robot into an obstacle or off the map, this model assumes the robot stayed in
 * one place. This is the same as the model presented in Robot Programming
 * Lecture 14.
 * 
 * Note that this class doesn't actually do this, instead it shows you a
 * <b>possible</b> structure for your action model.
 * 
 * @author nah
 * 
 */
public class PerfectActionModel implements ActionModel {

	@Override
	public GridPositionDistribution updateAfterMove(
			GridPositionDistribution _from, Heading _heading) {

		// Create the new distribution that will result from applying the action
		// model
		GridPositionDistribution to = new GridPositionDistribution(_from);

		// Move the probability in the correct direction for the action
		if (_heading == Heading.PLUS_X) {
			movePlusX(_from, to);
		} else if (_heading == Heading.PLUS_Y) {
			// you could implement a movePlusY etc. or you could find a way do
			// do all moves in a single method. Hint: all changes are just + or
			// - 1 to an x or y value.
		} else if (_heading == Heading.MINUS_X) {

		} else if (_heading == Heading.MINUS_Y) {

		}

		return to;
	}

	/**
	 * Move probabilities from _from one cell in the plus x direction into _to
	 * 
	 * @param _from
	 * @param _to
	 */
	private void movePlusX(GridPositionDistribution _from,
			GridPositionDistribution _to) {
		float p = 0;
		// iterate through points updating as appropriate
		for (int y = 0; y < _to.getGridHeight(); y++) {
			for (int x = 0; x < _to.getGridWidth(); x++) {
								
				
	
				
				// make sure to respect obstructed grid points
				if (!_to.isObstructed(x, y)) {
					
					float q = _from.getProbability(x, y);
					
					if(_from.getGridMap().isValidTransition(x, y, x+1, y)){
						
						
						_to.setProbability(x, y, p);
						p = q;
				
					}
					else{
						_to.setProbability(x, y, p + q);
						p = 0;
					}
					
				
									
				}
			}
		}
	}
}