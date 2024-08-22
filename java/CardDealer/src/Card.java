public class Card {
	private String type; // heart, spade, diamond, club
	private String number; // A(1), 2, 3, 4, ... , 10, J, Q, K
	
	public Card(String type, String number) {
		this.type = type;
		this.number = number;
	}
	
	public String getType() {
		return type;
	}
	
	public String getNumber() {
		return number;
	}
	
	public String toString() {
		return "[" + Character.toUpperCase(type.charAt(0)) + "-" + number + "]";
	}
}
