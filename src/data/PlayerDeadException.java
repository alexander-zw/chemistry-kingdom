package data;

import program.Player;

public class PlayerDeadException extends Exception {

	private static final long serialVersionUID = 1355450262711847259L;
	
	private Player player;
	
	public Player getPlayer() {
		return player;
	}

	public PlayerDeadException(Player p) {
		player = p;
	}

	public PlayerDeadException(Player p, String message) {
		super(message);
		player = p;
	}

}
