package data;

import java.util.List;

import program.Player;

public class Acid extends MainCard {

	public Acid(char cardSuit, int cardRank) {
		super(cardSuit, cardRank);
	}

	public Acid() {
		super();
	}

	@Override
	public List<Card> apply(Player p) throws IllegalOperationException, PlayerDeadException {
		if (p == owner) {
			throw new IllegalOperationException("Cannot apply acid to yourself");
		}
		return p.respondToAcid(owner);
	}

	public String description() {
		return "Acid";
	}
	
}
