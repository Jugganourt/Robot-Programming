package data;

import java.util.ArrayList;

import search.Solve;
import util.Successable;

public class Point implements Successable<Point> 
{
	public static final int MAX_SIZEX = 11;
	public static final int MAX_SIZEY = 7;
	private static final boolean[][][][] BLOCKED = new boolean[MAX_SIZEX][MAX_SIZEY][MAX_SIZEX][MAX_SIZEY];
	
	int x;
	int y;
	int dist;
	
	public Point()
	{
		x = 0;
		y = 0;
		dist = 0;
	}
	
	public Point(int x, int y, int dist)
	{
		this.x = x;
		this.y = y;
		this.dist = dist;
	}
	
	public void setDist(int dist)
	{
		this.dist = dist;
	}
	
	public int getDist()
	{
		return dist;
	}
	
	public int getX()
	{
		return x;
	}
	
	private static boolean isValid(Point p, Point p2)
	{
		//roughly 1/10th of available memory - but comes with O(1) time complexity

		return !(BLOCKED[p.getX()][p.getY()][p2.getX()][p2.getY()]) && 
				!(BLOCKED[p2.getX()][p2.getY()][p.getX()][p.getY()]);
	}
	
	public boolean equals(Object o)
	{
		if(o instanceof Point)
		{
			Point that = (Point) o;
			return that.getX() == x && that.getY() == y;
		}
		else return false;
	}
	
	public int getY()
	{
		return y;
	}
	
	public static void createBlocks()
	{
		/*
		 * Syntax =
		 * Blocked[X1][X2][Y1][Y2]
		 */
		
		BLOCKED[2][0][2][1] = true;
        BLOCKED[4][0][4][1] = true;
        BLOCKED[5][0][5][1] = true;
        BLOCKED[6][0][6][1] = true;
        BLOCKED[8][0][8][1] = true;
        BLOCKED[2][1][3][1] = true;
        BLOCKED[7][1][8][1] = true;
        BLOCKED[2][1][2][2] = true;
        BLOCKED[4][1][4][2] = true;
        BLOCKED[5][1][5][2] = true;
        BLOCKED[6][1][6][2] = true;
        BLOCKED[8][1][8][2] = true;
        BLOCKED[0][2][1][2] = true;
        BLOCKED[1][2][2][2] = true;
        BLOCKED[8][2][9][2] = true;
        BLOCKED[9][2][10][2] = true;
        BLOCKED[1][2][1][3] = true;
        BLOCKED[5][2][5][3] = true;
        BLOCKED[9][2][9][3] = true;
        BLOCKED[4][3][5][3] = true;
        BLOCKED[5][3][6][3] = true;
        BLOCKED[1][3][1][4] = true;
        BLOCKED[9][3][9][4] = true;
        BLOCKED[0][4][1][4] = true;
        BLOCKED[1][4][2][4] = true;
        BLOCKED[8][4][9][4] = true;
        BLOCKED[9][4][10][4] = true;
        BLOCKED[2][4][2][5] = true;
        BLOCKED[8][4][8][5] = true;
        BLOCKED[4][4][4][5] = true;
        BLOCKED[5][4][5][5] = true;
        BLOCKED[6][4][6][5] = true;
        BLOCKED[2][5][3][5] = true;
        BLOCKED[7][5][8][5] = true;
        BLOCKED[2][5][2][6] = true;
        BLOCKED[4][5][4][6] = true;
        BLOCKED[5][5][5][6] = true;
        BLOCKED[6][5][6][6] = true;
        BLOCKED[8][5][8][6] = true;
	}

	@Override
	public ArrayList<Pair<Point, String>> getSuccessors() 
	{
		
		ArrayList<Pair<Point, String>> successors = new ArrayList<Pair<Point, String>>();
		
		if(y != 0)
		{
			Pair<Point, String> p = new Pair<Point, String>(new Point(x, y - 1, dist + 1), "SOUTH");
			if (isValid(this, p.getState())) 
			{
				successors.add(p);
			}
		}
		
		if(x != 0)
		{
			Pair<Point, String> p = new Pair<Point, String>(new Point(x - 1, y, dist + 1), "WEST");
			if (isValid(this, p.getState())) 
			{
				successors.add(p);
			}
		}
		
		if(x + 1 < MAX_SIZEX)
		{
			Pair<Point, String> p = new Pair<Point, String>(new Point(x + 1, y, dist + 1), "EAST");
			if (isValid(this, p.getState())) 
			{
				successors.add(p);
			}
		}
		
		if(y + 1 < MAX_SIZEY)
		{
			Pair<Point, String> p = new Pair<Point, String>(new Point(x, y + 1, dist + 1), "NORTH");
			if (isValid(this, p.getState())) 
			{
				successors.add(p);
			}
		}
		
		return successors;
	}

	@Override
	public String toSimpleString() 
	{
		return toString();
	}
	
	public String toString()
	{
		return "x = " + x + ", y = " + y;
	}

	@Override
	public String getGoal() 
	{
		return "";
	}
	
}


//GRAVEYARD -
//there's the option of a 4D array but it'd take roughly 6KB of memory
//return array[x1][y1][x2][y2] && array[x2][y2][x1][y1]

//this is dirty way to do it, but it's memory efficient with O(n) time complexity


/*
 * 
 * OLD D:
if((p.equals(new Point(1, 0)) && p2.equals(new Point(0, 0))) ||
		p.equals(new Point(0, 0)) && p2.equals(new Point(1, 0)))
{
	return false;
}
else if((p.equals(new Point(2, 1)) && p2.equals(new Point(2, 2))) ||
		p.equals(new Point(2, 2)) && p2.equals(new Point(1, 2)))
{
	return false;
}
else if((p.equals(new Point(2, 4)) && p2.equals(new Point(2, 5))) ||
		p.equals(new Point(2, 5)) && p2.equals(new Point(2, 4)))
{
	return false;
}
else if((p.equals(new Point(3, 4)) && p2.equals(new Point(4, 4))) ||
		p.equals(new Point(4, 4)) && p2.equals(new Point(3, 4)))
{
	return false;
}
else if((p.equals(new Point(6, 2)) && p2.equals(new Point(6, 3))) ||
		p.equals(new Point(6, 3)) && p2.equals(new Point(6, 2)))
{
	return false;
}
else if((p.equals(new Point(6, 6)) && p2.equals(new Point(6, 7))) ||
		p.equals(new Point(6, 7)) && p2.equals(new Point(6, 6)))
{
	return false;
}

return true;



EXTENSIVE LIST OF BLOCKED NODES -
0, 0 to 1, 0
2, 1 to 2, 2
2, 4 to 2, 5
3, 4 to 4, 4
6, 2 to 6, 3
6, 6 to 6, 7
*/

