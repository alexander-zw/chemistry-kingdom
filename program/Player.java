package program;

import data.Card;

public class Player {

	/* ------------------------ Variables ------------------------ */
	
	private int mass;
	private Card[] cards;
	
	/* ------------------------ Gets and sets ------------------------ */

	public int getMass() {
		return mass; 
	}

	public Card[] getCards() {
		return cards; 
	}

	/* ------------------------ Main actions ------------------------ */

	/** Play a card. */
	public void play(Card card) {
		
	}
	
	/* ------------------------ Used for debugging ------------------------ */

	/**
	 * converts player to string: lists ships
	 */
	public String toString(){
		String string = "player:\n";
		return string;
	}


}
