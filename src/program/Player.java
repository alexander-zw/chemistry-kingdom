package program;

import java.util.ArrayList;
import java.util.List;
import data.*;

public abstract class Player {

	/* ------------------------ Variables ------------------------ */
	
	protected String name;
	private int mass;
	private int massCapacity;
	protected List<Card> cards = new ArrayList<>();
	
	public Player(String playerName, int totalMass) {
		name = playerName;
		mass = massCapacity = totalMass;
	}
	
	/* ------------------------ Gets and sets ------------------------ */

	public String name() {
		return name;
	}
	
	public int getMass() {
		return mass; 
	}

	public int getMassCapacity() {
		return massCapacity; 
	}

	public List<Card> getCards() {
		return cards; 
	}

	public void addCard(Card c) {
		c.setOwner(this);
		cards.add(c);
	}
	
	public boolean isDead() {
		return mass == 0;
	}

	public void loseMass(int amount) throws PlayerDeadException {
		mass -= amount;
		if (isDead()) {
			throw new PlayerDeadException(this);
		}
	}
	
	public void gainMass(int amount) {
		mass = Math.min(massCapacity, mass + amount);
	}
	
	/* ------------------------ Main actions ------------------------ */

	/** Play a card.
	 * @return the any cards used in the process.
	 * @throws PlayerDeadException if anyone dies */
 	public List<Card> play(int cardInd, Player target) throws PlayerDeadException {
		Card card = cards.get(cardInd);
		List<Card> usedCards = new ArrayList<>();
		try {
			usedCards.addAll(card.apply(target));
			usedCards.add(cards.remove(cardInd));
		} catch (IllegalOperationException e) {
			outputIllegalMessage();
		}
		return usedCards;
	}

	public abstract int getAttackingOption();

	public abstract Player getTarget(int cInd, List<Player> players);
	
	/** Returns null if injured, otherwise returns the card used to block the acid.
	 * @throws PlayerDeadException */
	public abstract List<Card> respondToAcid(Player attacker) throws PlayerDeadException;
	
	private void outputIllegalMessage() {
		System.out.println("You can't do that! Try something else.");
	}

	/* ------------------------ Used for debugging ------------------------ */

	/**
	 * converts player to string: lists ships
	 */
	@Override
	public String toString(){
		String string = "Player " + name + "\nmass: " + mass + "/" + massCapacity + ", cards: " + cards;
		return string;
	}

}
