package program;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import data.*;

public class Human extends Player {
	
	public static Scanner scan = new Scanner(System.in);

	public Human(String name, int totalMass) {
		super(name, totalMass);
	}

	@Override
	public List<Card> respondToAcid(Player attacker) throws PlayerDeadException {
		List<Card> baselike = Card.baselikeCards(cards);
		List<Card> usedCards = new ArrayList<>();
		Card baseBlockingAcid = null;
		
		if (!baselike.isEmpty()) {
			baseBlockingAcid = askForBase(baselike);
		}
		if (baseBlockingAcid == null) {
			loseMass(1);
			outputInjuredMessage(1, attacker);
			return usedCards;
		}
		
		outputAvoidInjureMessage(attacker);
		usedCards.add(baseBlockingAcid);
		return usedCards;
	}

	private void outputInjuredMessage(int amount, Player attacker) {
		System.out.println("You lost " + amount + " gram" + (amount == 1? "" : "s") +
				" from " + attacker.name() + "!");
	}

	private void outputAvoidInjureMessage(Player attacker) {
		System.out.println("You were not harmed by " + attacker.name() + "!");
	}
	
	public Card askForBase(List<Card> options) {
		System.out.println("Would you like to block the acid attack?");
		System.out.println("Your options are: " + options);
		System.out.println("Type the index, or -1 to ignore.");
		int choice;
		while (true) {
			try {
				choice = scan.nextInt();
				if (choice >= -1 && choice < options.size())
					break;
			} catch (InputMismatchException e) {
				scan.next();
			}
			System.out.println("No, no, no, that is not a valid input. Try again.");
		}
		if (choice != -1) {
			return cards.remove(choice);
		}
		return null;
	}

	@Override
	public int getAttackingOption() {
		System.out.println("\n\nIt's " + name + "'s turn. Your options are: " + cards);
		System.out.println("Type the index, or -1 to pass.");
		int choice;
		while (true) {
			try {
				choice = scan.nextInt();
				if (choice >= -1 && choice < cards.size())
					return choice;
			} catch (InputMismatchException e) {
				scan.next();
			}
			System.out.println("No, no, no, that is not a valid input. Try again.");
		}
	}
	
	private String playerList(List<Player> players) {
		String str = "[";
		for (Player p : players) {
			str += p.name() + ", ";
		}
		return str.substring(0, str.length() - 2) + "]";
	}
	
	@Override
	public Player getTarget(int cInd, List<Player> players) {
		System.out.println("Who do you want to apply " + cards.get(cInd) + " to? Options: " +
							playerList(players));
		return players.get(scan.nextInt());
	}
	
	public static void main(String[] args) {
		Player p1 = new Human("p1", 4);
		Player p2 = new Human("p2", 3);
		
		p1.addCard(new Acid(Card.CLUB, 1));
		p1.addCard(new Base(Card.DIAMOND, 2));
		p2.addCard(new Base(Card.HEART, 3));
		p2.addCard(new Acid(Card.SPADE, 4));
		p2.addCard(new Base(Card.CLUB, 5));
		System.out.println(p1);
		System.out.println(p2 + "\n");
		try {
			p1.play(0, p2);
		} catch (PlayerDeadException e) {
			System.out.println(e.getPlayer() + " died!");
		}
		System.out.println(p1);
		System.out.println(p2);
	}


}
