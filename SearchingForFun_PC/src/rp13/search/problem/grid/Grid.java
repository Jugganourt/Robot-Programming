package rp13.search.problem.grid;

import rp13.search.problem.grid.Grid.GridMove;

public class Grid {
	private int width = 77;
	
	private boolean[][] map = new boolean[77][77];
	private int starti;//initial start position i
	private int startj;//initial start position j
	private int endi;//goal position i
	private int endj;//goal position j
	
	
	public Grid(){
		for(int i=0;i<width;i++)
			for(int j=0;j<width;j++)
				map[i][j]=false;
		
		for(int i=0;i<width;i++)
			{
				
				if(i>0)
				{
					map[i][i-1]=true;
					map[i-1][i]=true;
				}
				if(i<width-1)
				{
				map[i+1][i]=true;
				map[i][i+1]=true;
				}
				if(i>6)
				{
					map[i-7][i]=true;
				}
				if(i<width-8)
				{
					map[i+7][i]=true;
					
				}
				//connect to neighbours 
			}
		
		
	}
	public Grid(Grid _state) {
		// TODO Auto-generated constructor stub
	}
	public void addLink(int i,int j){
		map[i][j]=true;
	}
	public void blockLink(int i,int j)
	{
		map[i][j]=false;
		
	}
	
	public void makeMove(GridMove move)
	{
		
	}
	
	public enum GridMove {

	}

	
	
	public boolean isPossibleMove(GridMove move) {
		// TODO Auto-generated method stub
		return false;
	}
}
