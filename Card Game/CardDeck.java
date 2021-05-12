package interfaces;

import java.util.ArrayList;
import java.util.List;

public class CardDeck implements CardContainer {
	
	private List<Card> cards = new ArrayList<Card>();
	
	public CardDeck(int n) {
		cards = new ArrayList<Card>();
		for (int i = 0; i<Card.suits.length(); i++) {
			for (int face=1; face <= n; face++) {
				Card card = new Card(Card.suits.charAt(i),face);
				cards.add(card);
			}
		}
	}
	
	@Override
	public String toString() {
		return "[Deck: " + cards.toString().substring(1) +"]";
	}
	
	public int getCardCount() {
		return cards.size();	
	}
	
	public Card getCard(int n) {
		if (n<0 || n>=getCardCount()) {
			throw new IllegalArgumentException("Invalid value.");
		}
		return cards.get(n);
	}
	
	public void shufflePerfectly() {
		int halfSize = cards.size()/2;
		for (int i = 0; i<halfSize; i++) {
			Card card = cards.remove(halfSize + i);
			cards.add(i*2+1, card);
		}
	}
	
	//CARD DEL 2
	
	public void deal (CardHand hand, int n) {
		for (int i=0; i<n; i++) {
			hand.addCard(cards.get(getCardCount()-1));
			cards.remove(getCardCount()-1);
		}
	}
	
	public static void main(String[] args) {
		CardDeck deck = new CardDeck(10);
		System.out.println(deck.toString());
	}
	
	
}
