package webjingoo.carddecex;

public class Card {
	private int rank;
	private String suit;

	public Card(int rank, String suit) {
		super();
		this.rank = rank;
		this.suit = suit;
	}

	@Override
	public String toString() {
		return "[" + rank + ", " + suit.charAt(0) + "]";
	}

	public int getRank() {
		return rank;
	}

	public String getSuit() {
		return suit;
	}
}
