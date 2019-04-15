package program;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import data.*;

/**
 * Contains the main method from which the terminal version of the game is run.
 * 
 * TODO
 * In progress. Next steps: cannot apply acid to self, don't ask for target if target not needed.
 * 
 * @author AlexanderWu
 */
public class TerminalGame {
	
	private static Scanner scan = new Scanner(System.in);
	private static Random rand = new Random();
	
	private static List<Human> humans;
	private static List<Player> computers; // TODO
	private static List<Player> players;

	private static List<Card> cardPile;

	private static boolean gameOver() {
		return false; // TODO
	}
	
	public static void initializePlayers() {
		// get number of players
		System.out.println("How many human players do you want?");
		int numHumans = scan.nextInt();
		humans = new ArrayList<>(numHumans);
		System.out.println("How many computer players do you want?");
		int numComputers = scan.nextInt();
		computers = new ArrayList<>(numComputers);
		
		// initialize each player
		System.out.println("How much mass does each player have?");
		for (int i = 0; i < numHumans; i++) {
			String playerName = "Human " + (i + 1);
			System.out.println(playerName + ":");
			humans.add(new Human(playerName, scan.nextInt()));
		}

		// TODO add computers
//		for (int i = 0; i < numComputers; i++) {
//			String playerName = "Computer " + (i + 1);
//			computers.add(new Computer(playerName, 4));
//		}

		players = new ArrayList<Player>(numHumans + numComputers);
		players.addAll(humans);
		players.addAll(computers);
	}

	private static Card randomCardFromPile() {
		return cardPile.remove(rand.nextInt(cardPile.size()));
	}

	@SuppressWarnings("unused")
	private static Card cardFromPile() {
		return cardPile.remove(cardPile.size() - 1);
	}

	private static void addToCardPile(Card c) {
		int randInd = rand.nextInt(cardPile.size() + 1);
		cardPile.add(randInd, c);
	}

	private static void addToCardPile(List<Card> cards) {
		for (Card c : cards)
			addToCardPile(c);
	}
	
	public static void initializeCards() {
		// initialize card pile
		cardPile = new ArrayList<Card>();
		
		for (int i = 0; i < 20; i++)
			cardPile.add(new Acid());
		for (int i = 0; i < 8; i++)
			cardPile.add(new Base());
		
		// initialize players' hand cards
		for (Player p : players) {
			for (int i = 0; i < 4; i++) {
				p.addCard(randomCardFromPile());
			}
		}
	}
	
	public static void startGame() {

		int currentPlayer = 0; // begins with humans, then computers
		Player current;
		while (true) {
			current = players.get(currentPlayer);
			
			System.out.println(cardPile);
			
			if (current instanceof Human) {
				int cardInd = 0;
				while (cardInd != -1) {
					cardInd = current.getAttackingOption();
					if (cardInd != -1) {
						Player target = current.getTarget(cardInd, players);
						try {
							List<Card> used = current.play(cardInd, target);
							addToCardPile(used);
						} catch (PlayerDeadException e) {
							handleDeath(e.getPlayer());
						}
					} 
				}
			}
			
			if (gameOver())
				return;
			
			currentPlayer = (currentPlayer + 1) % (humans.size() + computers.size());
		}
	}

	public static void handleDeath(Player p) {
		humans.remove(p);
		computers.remove(p);
		players.remove(p);
		System.out.println(p.name + " has died!"); // TODO implement salt
	}
	
	public static void main(String[] args) {
		
		initializePlayers();
		initializeCards();
		
		startGame();
		
		scan.close();
	}

}
