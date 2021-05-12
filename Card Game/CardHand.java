package interfaces;

import java.util.ArrayList;
import java.util.List;

public class CardHand implements CardContainer {
	
	private List<Card> hand = new ArrayList<Card>();
	
	public CardHand() {	
	}
	
	public int getCardCount() {
		return hand.size();	
	}
	
	public Card getCard(int n) {
		if (n<0 || n>=getCardCount()) {
			throw new IllegalArgumentException("Invalid value.");
		}
		return hand.get(n);
	}
	
	public void addCard(Card card) {
		hand.add(card);
	}
	
	public Card play(int n) {
		Card pick = hand.get(n);
		hand.remove(n);
		return pick;
	}
	
	public static void main(String[] args) {
		CardHand hand = new CardHand();
		Card card1 = new Card('S', 13);
		hand.addCard(card1);
		System.out.println(hand.getCard(0));
	}

}
