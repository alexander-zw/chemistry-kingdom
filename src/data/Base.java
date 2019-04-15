package data;

import java.util.List;

import program.Player;

public class Base extends MainCard {

	public Base(char cardSuit, int cardRank) {
		super(cardSuit, cardRank);
	}

	public Base() {
		super();
	}

	/** Whether a card can be used as a base. */
	public static boolean baselike(Card c) {
		return c instanceof Base;
	}
	
	/** Bases have no active action. */
	@Override
	public List<Card> apply(Player p) throws IllegalOperationException {
		throw new IllegalOperationException("You cannot apply a base");
	}

	public String description() {
		return "Base";
	}

}
