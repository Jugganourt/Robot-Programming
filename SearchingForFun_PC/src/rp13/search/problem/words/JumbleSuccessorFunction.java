package rp13.search.problem.words;

import java.util.List;
import rp13.search.interfaces.SuccessorFunction;
import rp13.search.util.ActionStatePair;

public class JumbleSuccessorFunction implements SuccessorFunction<StringMove, Jumble> {

	@Override
	public void getSuccessors(Jumble state, List<ActionStatePair<StringMove, Jumble>> successors) {
			
		for(int i = 0; i < state.arrayLength(); i++ ){
			for (int j = i+1; j < state.arrayLength(); j++) {
			
					
					StringMove temp = new StringMove(i,j);
					Jumble copy = state;
					copy.executeMove(temp);
					System.out.println(copy);
					successors.add(i, new ActionStatePair<StringMove, Jumble>(temp, copy));
					
			}
			
		}
		System.out.println(successors);
	}
}
