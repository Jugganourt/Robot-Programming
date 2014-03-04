package rp13.search.problem.words;

public class StringMove {
	
	private int first;
	private int second;

	public StringMove(int first, int second){
		this.first = first;
		this.second = second;
	}

	public int getFirst() {
		return first;
	}

	public int getSecond() {
		return second;
	}
	
	public String toString(){
		
		return "Swapping Postion " + first +" with Postion " + second;
	}
}
