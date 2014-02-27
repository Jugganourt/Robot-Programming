package searchingForFun.Searches;

public class SearchNode<ActionT,StateT> {
	
	private SearchNode<ActionT, StateT> parent;
	private final ActionT m_action;
	private final StateT m_state;
	
	public SearchNode(ActionT _action, StateT _state){
		
		this.m_action = _action;
		this.m_state = _state;
		
	}
	
	
	public SearchNode(ActionT _action, StateT _state, SearchNode<ActionT, StateT> parent){
		
		this.m_action = _action;
		this.m_state = _state;
		this.parent = parent;
	}
	
	public ActionT getAction() {
		return m_action;
	}

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
	
	

	
}
