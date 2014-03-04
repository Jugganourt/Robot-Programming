package rp13.search.problem.words;

import rp13.search.problem.puzzle.States;


public class Jumble implements States<Jumble> {
	
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
		String w = "";
		for (int i = 0; i < word.length; i++) {
			w = w + word[i];
			
		}
		
		return w;
	}

	public static void main(String[] args){
		
		Jumble jj = new Jumble("java");
		
		System.out.println(jj);
		
		
		
	}
	
	
	
	
	
	
	
	@Override
	public int heuristic() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}

