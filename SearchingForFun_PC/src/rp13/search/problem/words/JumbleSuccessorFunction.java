package rp13.search.problem.words;

import java.util.List;
import rp13.search.interfaces.SuccessorFunction;
import rp13.search.util.ActionStatePair;

public class JumbleSuccessorFunction implements SuccessorFunction<StringMove, Jumble> {

	@Override
	public void getSuccessors(Jumble _state, List<ActionStatePair<StringMove, Jumble>> _successors) {
		for(int i = 0; i < _state.arrayLength(); i++ ){
			for (int j = i; j < _state.arrayLength(); j++) {
				if(i != j){
					StringMove temp = new StringMove(i,j);
					
					Jumble stateSuc = new Jumble(_state.getCharArray().toString());
					stateSuc.executeMove(temp);
					_successors.add(new ActionStatePair<StringMove, Jumble>(temp, stateSuc));
				}
			}
		}
	}
}
