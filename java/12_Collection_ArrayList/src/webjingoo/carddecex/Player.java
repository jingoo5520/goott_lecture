package webjingoo.carddecex;

import java.util.ArrayList;

public class Player {
	private ArrayList<Card> hands;
	private int totalScore;

	public ArrayList<Card> getHands() {
		return hands;
	}

	public void setHands(ArrayList<Card> cards) {
		this.hands = cards;
		
		for(Card card : cards) {
			totalScore += card.getRank();
		}
	}
	
	public void sortHands() {
		hands.sort(new CardComparator());
	}
	
	public int getTotalScore () {
		return totalScore;
	}
}
