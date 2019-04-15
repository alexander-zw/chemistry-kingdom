package data;

/**
 * Types of reaction cards:
 * 
 * Chemical Change - acid or base, opponent target
 * Physical Change - discard opponent's card, opponent target
 * Decomposition Reaction - draw two cards
 * Synthesis Reaction - discard two opponent's cards and allow to draw, opponent target
 * Double-Replacement Reaction - everyone gets a card
 * Single-Replacement Reaction - obtain opponent card, distance 1 target
 * Neutralization Reaction - cancel reaction card, any target
 * Explosion - lose 3g, judging
 * HCl Volatilization - opponents play base or lose 1g
 * Ammonia Volatilization - opponents play acid or lose 1g
 * Combustion - opponent and player alternate playing acid, opponent target
 * Conservation of Mass - draw card when lose card up to 3 times
 * Dissolution - opponent skip attacking, judging, opponent target
 * 
 * @author AlexanderWu
 */
public abstract class ReactionCard extends Card {
	
	public ReactionCard(char cardSuit, int cardRank) {
		super(cardSuit, cardRank);
	}

	public ReactionCard() {
		super();
	}

	public String description() {
		return "Reaction Card";
	}
	
}
