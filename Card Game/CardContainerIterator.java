package interfaces;

import java.util.Iterator;

public class CardContainerIterator implements Iterator<Card>{
	
	private CardContainer cardContainer;
	private int position;
	
	public CardContainerIterator (CardContainer n) {
		this.cardContainer = n;
		this.position = 0;
	}
	
	public boolean hasNext() {
		return position<cardContainer.getCardCount();
	}
	
	public Card next() {
		return cardContainer.getCard(position++);
	}
	
	@Override
	public void remove() {
		
	}
}
