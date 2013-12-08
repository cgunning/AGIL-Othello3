package kth.game.othello.player;

/**
 * Class for human player
 * 
 * @author Nils Dahlbom Norgren, Christoffer Gunning
 * 
 */
public class HumanPlayer extends AbstractPlayer {

	/**
	 * Creates a new human player
	 * 
	 * @param name
	 *            The name for the player
	 */
	public HumanPlayer(String name) {
		super(name, Type.HUMAN);

	}
}
