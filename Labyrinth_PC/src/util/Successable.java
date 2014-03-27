package util;
import java.util.ArrayList;

import data.Pair;


public interface Successable<S>
{
	public ArrayList<Pair<S, String>> getSuccessors();
	public String toSimpleString();
	public String getGoal();
}

