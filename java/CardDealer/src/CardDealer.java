public class CardDealer {
	private Card[] cards;

	public CardDealer() {
		cards = new Card[52];

		for (int i = 0; i < cards.length; i++) {
			String type;
			String number = null;

			if (i < 13) {
				type = "spade";
			} else if (i < 26) {
				type = "diamond";
			} else if (i < 39) {
				type = "club";
			} else {
				type = "heart";
			}

			switch (i % 13) {
			case 0:
				number = "A";
				break;
			case 1:
				number = "2";
				break;
			case 2:
				number = "3";
				break;
			case 3:
				number = "4";
				break;
			case 4:
				number = "5";
				break;
			case 5:
				number = "6";
				break;
			case 6:
				number = "7";
				break;
			case 7:
				number = "8";
				break;
			case 8:
				number = "9";
				break;
			case 9:
				number = "10";
				break;
			case 10:
				number = "J";
				break;
			case 11:
				number = "Q";
				break;
			case 12:
				number = "K";
				break;
			}

			cards[i] = new Card(type, number);
		}
	}

	public void showCard() {
		int spade = 0;
		int diamond = 0;
		int club = 0;
		int heart = 0;
		System.out.println("남은 카드수: " + cards.length);
		for (int i = 0; i < cards.length; i++) {
			if (cards[i] != null) {
				System.out.print(cards[i].toString() + " ");
				switch (cards[i].getType()) {
				case "spade":
					spade++;
					break;
				case "diamond":
					diamond++;
					break;
				case "club":
					club++;
					break;
				case "heart":
					heart++;
					break;
				}
			}

			if ((i + 1) % (cards.length / 4) == 0 && i != cards.length - 1) {
				System.out.println();
			}
		}
		
		System.out.println();
		System.out.println("spade: " + spade + "장, " + "diamond " + diamond + "장, " + "club " + club + "장, " + "heart " + heart + "장");
		System.out.println();
	}

	public void shuffle() {
		int randNum;
		Card tempCard;

		for (int i = 0; i < cards.length; i++) {
			randNum = (int) (Math.random() * 45 + 1);

			tempCard = cards[i];
			cards[i] = cards[randNum];
			cards[randNum] = tempCard;
		}

		System.out.println("셔플 완료");
		System.out.println();
	}

	public void pickCard() {
		int deckLength = cards.length;
		Card[] restCards;
		int randNum = (int) (Math.random() * deckLength);

		System.out.println("picked Card = " + cards[randNum].toString());
		System.out.println();
		cards[randNum] = null;

		restCards = new Card[deckLength - 1];

		int newIdx = 0; 
		int idx = 0;
		while (true) {
			if (cards[idx] != null) {
				restCards[newIdx] = cards[idx];
				newIdx++;
			}
			idx++;

			if (newIdx == restCards.length) {
				break;
			}
		}

		cards = restCards;
	}
}
