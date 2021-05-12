package interfaces;

public class Card implements Comparable<Card>{
	
	private char color;
	public int face;
	public final static String suits = "SHDC";
	
	public Card(char color, int face){
		if ((color == 'S' || color == 'H' || color == 'D' || color == 'C') && (face>0 && face<14)) {
			this.color = color;
			this.face = face;
		}
		else {
			throw new IllegalArgumentException("Color or face is invalid");
		}
	}
	
	public char getSuit() {
		return color;
	}
	
	public int getFace() {
		return face;
	}
	
	public String toString() {
		return "" + color + face + "";
	}
	
	@Override
	public int compareTo(Card o) {
		int val = suits.indexOf(this.getSuit()) - suits.indexOf(o.getSuit());
		if (val == 0) {
			return this.getFace() - o.getFace();
		}
		return val;
		/*if (suits.indexOf(this.getSuit()) != (suits.indexOf(o.getSuit()))) {
			return (suits.indexOf(this.getSuit()) - (suits.indexOf(o.getSuit())));
		}
		else if (this.getFace() != o.getFace()) {
			return (this.getFace()-o.getFace());
		}*/
	}
	
	public static void main(String[] args) {
		Card kort1 = new Card('S',1);
		System.out.println(kort1);
		Card kort2 = new Card('S',6);
		Card kort3 = new Card('C',1);
		System.out.println(kort2.compareTo(kort1));
		System.out.println(kort1.compareTo(kort3));
	}

}
