package rp13.search.problem.grid;

import rp13.search.problem.grid.Grid.GridMove;

public class Grid {
	private int width = 77;
	
	private static boolean[][] map = new boolean[77][77];
	private static int starti;//initial start position i
	private static int startj;//initial start position j
	private static int endi;//goal position i
	private static int endj;//goal position j
	private int currenti;//current position i
	private int currentj;//current position j
	
	
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
	public static void setStart(int i,int j)
	{
		starti = i;
		startj = j;
	}
	public static void setGoal(int i,int j)
	{
		endi= i;
		endj=j;
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
		return map[currenti][currentj];
	}
	
	public static Grid createGrid(int i, int j) {
		// TODO Auto-generated method stub
		return null;
	}
}
