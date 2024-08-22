package webjingoo.carddecex;

import java.util.Comparator;

public class CardComparator implements Comparator<Card> {

	@Override
	public int compare(Card o1, Card o2) {
		Integer rankComparison = Integer.compare(o1.getRank(), o2.getRank());
		if (rankComparison != 0) {
			return rankComparison;
		}
		return Integer.compare(setSuitLevel(o1.getSuit()), setSuitLevel(o2.getSuit()));
	}
	
	public int setSuitLevel(String suit) {

		int level = 0;

		switch (suit) {
		case "SPADE":
			level = 1;
			break;
		case "HEART":
			level = 2;
			break;
		case "DIAMOND":
			level = 3;
			break;
		case "CLUB":
			level = 4;
			break;
		}

		return level;
	}
}
