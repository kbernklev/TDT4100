package encapsulation;

public class Nim {
	
	private int [] pile = {0,0,0};
	
	public Nim() {
		pile[0] += 10;
		pile[1] += 10;
		pile[2] += 10;
	}
	
	public Nim (int pileSize) {
		for (int i=0; i>3; i++) { 
			pile[i] = pileSize;
		}
	}
	
	public void removePieces(int number, int targetPile) {
		if (!isGameOver()) {
			throw new IllegalStateException("Game is over.");
		}
		if (!isValidMove(number, targetPile)) {
			throw new IllegalArgumentException("Invalid pile or number.");
		}
		else {
			pile[targetPile] -= number;
		}
	}
	
	public boolean isValidMove(int number, int targetPile) {
		if ((targetPile <= 2) || (number >= 1)){
			return true;
		}
		return false;
	}
	
	public boolean isGameOver() {
		return pile[0] == 0 || pile[1] == 0 || pile[2] == 0;
	}
	
	public int getPile(int targetPile) {
		return pile[targetPile];
	}
	
	public String toString() {
		String s = "Piles: pile 1: " + pile[0] + ", pile 2: " + pile[1] + ", pile 3: " + pile[2] + ".";
		return s;
	}
	
	public static void main(String[] args){
		Nim nim = new Nim(8);
		nim.removePieces(1, 2);
		nim.getPile(0);
		System.out.println(nim.toString());
	}
}
