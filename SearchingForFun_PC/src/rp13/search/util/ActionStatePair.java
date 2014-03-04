/**
 * 
 */
package rp13.search.util;

import rp13.search.problem.puzzle.States;



/**
 * A class to store an action and the state it has produced together.
 * 
 * @author nah
 * 
 */
public class ActionStatePair<ActionT, StateT extends States<StateT>>  implements Comparable<ActionStatePair<ActionT, StateT >> {

	private final ActionT m_action;
	private final StateT m_state;
	private ActionStatePair<ActionT,StateT> parent;
	private int heur;
	

	/**
	 * Construct the pair from input values.
	 * 
	 * @param _action
	 * @param _state
	 */
	public ActionStatePair(ActionT _action, StateT _state) {
		m_action = _action;
		m_state = _state;
		this.parent = null;
	}
	
	public ActionStatePair<ActionT, StateT> getParent() {
		return parent;
	}
	


	public void setParent(ActionStatePair<ActionT, StateT> parent) {
		this.parent = parent;
	}
	
	

	/**
	 * Get action.
	 * 
	 * @return
	 */
	public ActionT getAction() {
		return m_action;
	}

	/**
	 * Get state.
	 * 
	 * @return
	 */
	public StateT getState() {
		return m_state;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(m_state);
		if (m_action != null) {
			sb.append("\n -> \n");
			sb.append(m_action);
		}

		return sb.toString();
	}

<<<<<<< HEAD
=======
	public ActionStatePair<ActionT,StateT> goBack() {
		return this.getParent();
		
	}

	@Override
	public int compareTo(ActionStatePair<ActionT, StateT> arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getHeur() {
		
		return heur;
	}

	public void setHeur(int heur) {
		this.heur = heur;
	}

	
>>>>>>> 0c99286fad7bd1565d7a4edd3933a5227eae4321
}
