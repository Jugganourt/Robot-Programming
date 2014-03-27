package data;

public class Pair<S, D>
{
	private S state;
	private D direction;
	
	public Pair(S state, D direction)
	{
		this.state = state;
		this.direction = direction;
	}
	
	public S getState()
	{
		return state;
	}
	
	public D getDirection()
	{
		return direction;
	}
	
}
