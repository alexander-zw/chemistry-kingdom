package data;

/**
 * When a player's move is illegal.
 * 
 * @author AlexanderWu
 */
public class IllegalOperationException extends Exception {

	public IllegalOperationException(String string) {
		super(string);
	}

	private static final long serialVersionUID = 8457279106056729413L;

}
