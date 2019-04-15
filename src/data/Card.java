package data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import program.Player;

/**
 * Only includes hand cards.
 * 
 * Types of hand cards: main card, reaction card, apparatus card. These are cards that can be
 * played by a player, and may or may not have a target player.
 * 
 * @author AlexanderWu
 */
public abstract class Card {

	private static Random rand = new Random();
	
	protected Player owner;
	protected char suit;
	protected int rank;
	
	public static final char SPADE = '\u2660';
	public static final char CLUB = '\u2663';
	public static final char HEART = '\u2665';
	public static final char DIAMOND = '\u2666';
	
	public Card(char cardSuit, int cardRank) {
		suit = cardSuit;
		rank = cardRank;
	}

	/** random suit and rank */
	public Card() {
		this(randSuit(), randRank());
	}
	
	public void setOwner(Player p) {
		owner = p;
	}
	
	public static char randSuit() {
		switch (rand.nextInt(4)) {
		case 0:
			return SPADE;
		case 1:
			return CLUB;
		case 2:
			return HEART;
		case 3:
			return DIAMOND;
		default:
			throw new RuntimeException("Invalid random int");
		}
	}

	public static int randRank() {
		return rand.nextInt(13) + 1;
	}
	
	public static List<Card> baselikeCards(List<Card> cards) {
		List<Card> baselike = new ArrayList<>();
		for (Card c : cards) {
			if (Base.baselike(c))
				baselike.add(c);
		}
		return baselike;
	}
	
	/** The active action of the card (passive action is implemented elsewhere). 
	 * @return any additional cards that were used in the process
	 * @throws PlayerDeadException */
	public abstract List<Card> apply(Player p) throws IllegalOperationException, PlayerDeadException;
	
	public String description() {
		return "Hand Card";
	}
	
	@Override
	public String toString() {
		return description() + " " + rank + suit;
	}

	@Override
	public int hashCode() {
		return toString().hashCode();
	}
	
	public static void main(String[] args) {
		System.out.println(new Acid(CLUB, 1));
	}

}
