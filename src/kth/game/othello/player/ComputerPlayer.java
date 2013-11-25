package kth.game.othello.player;

import kth.game.othello.player.movestrategy.MoveStrategy;

/**
 * Class for computer-player
 * 
 * @author Nils Dahlbom Norgren, Christoffer Gunning
 *
 */
public class ComputerPlayer extends AbstractPlayer {
	
	MoveStrategy moveStrategy;
	
	/**
	 * Creates a new computer-player
	 * @param name Name for the computer-player
	 */
	public ComputerPlayer(String name) {
		super(name, Type.COMPUTER);	
	}

}
