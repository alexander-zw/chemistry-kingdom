package data;

public abstract class MainCard extends Card {
	
	public MainCard(char cardSuit, int cardRank) {
		super(cardSuit, cardRank);
	}

	public MainCard() {
		super();
	}

	public String description() {
		return "Main Card";
	}
	
}
