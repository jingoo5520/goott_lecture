package webjingoo.carddecex;

import java.util.ArrayList;
import java.util.Collection;

public class Dealer {
	private ArrayList<Card> dec;
	private int[] RANKS = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13 };
	private String[] SUITS = { "SPADE", "HEART", "DIAMOND", "CLUB" };

	public Dealer() {
		setCardDec();
	}

	public ArrayList<Card> getDec() {
		return dec;
	}

	public void setCardDec() {
		dec = new ArrayList<Card>();
		for (String shape : SUITS) {
			for (int number : RANKS) {
				dec.add(new Card(number, shape));
			}
		}
	}

	
	public void suffle() {
		Card temp;

		int randNum;

		for (int i = 0; i < dec.size(); i++) {
			randNum = (int) (Math.random() * 52);
			temp = dec.get(i);
			dec.set(i, dec.get(randNum));
			dec.set(randNum, temp);
		}
	}

	public ArrayList<Card> handOutCards() {
		ArrayList<Card> cards = new ArrayList<Card>();

		int count = 0;
		while (count < 5) {
			cards.add(dec.get(0));
			dec.remove(0);
			count++;
		}

		return cards;
	}

	public void openHands(Player p1, Player p2) {
		int p1Score = p1.getTotalScore();
		int p2Score = p2.getTotalScore();
		System.out.println("Player1 score: " + p1Score);
		System.out.println("Player2 score: " + p2Score);
		if (p1Score > p2Score) {
			System.out.println("Player1 win!");
		} else if (p1Score == p2Score) {
			System.out.println("Draw!");
		} else {
			System.out.println("Player2 win!");
		}
	}
}
