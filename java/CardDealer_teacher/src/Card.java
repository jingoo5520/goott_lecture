
public class Card {
	private int kind;
	private int number;

	private static final int HEART = 1;
	private static final int SPADE = 2;
	private static final int CLUB = 3;
	private static final int DIAMOND = 4;

	static final int KIND_MAX = 4;
	static final int NUMBER_MAX = 13;

	public Card(int kind, int number) {
		this.kind = kind;
		this.number = number;
	}

	public String toString() {
		String kind = "";
		String number = "";

		switch (this.number) {
		case 1:
			number = "A";
			break;
		case 11:
			number = "J";
			break;
		case 12:
			number = "Q";
			break;
		case 13:
			number = "K";
			break;
		default:
			number = this.number + "";
			break;
		}

		switch(this.kind) {
		case HEART:
			kind = "♥";
			break;
		case SPADE:
			kind = "♠";
			break;
		case CLUB:
			kind = "♣";
			break;
		case DIAMOND:
			kind = "◈";
			break;
		}
		
		return "";
	}
}
