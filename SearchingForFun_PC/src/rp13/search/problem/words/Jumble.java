package rp13.search.problem.words;


public class Jumble {
	
	private char[] word;
	
	public Jumble(String s){
		word = s.toCharArray();
	}
	
	public Jumble(char[] c){
		word = c;
	}

	public void executeMove(StringMove move){
		
		int first = move.getFirst();
		int second = move.getSecond();
		
		char temp = word[first];
		word[first] = word[second];
		word[second] = temp;
		
		
	}
	
	public int arrayLength(){
		return word.length;
	}
	
	public char[] getCharArray(){
		return word;
	}
	
	@Override
	public boolean equals(Object game){
		
		Jumble j = (Jumble)game;
		
		char[] temp = j.getCharArray();
		
		for(int i = 0; i <word.length; i ++){
			if(word[i] != temp[i]){
				return false;
			}
		}
		return true;
		
	}
	
	public String toString(){
		return word.toString();
	}
	
}

