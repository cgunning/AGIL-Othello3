package kth.game.othello;

import java.util.List;

import kth.game.othello.board.Node;

/**
 * The responsibility of the Rules is to define when a player can make a move
 * and in that case also determine what nodes to swap at the board.
 * 
 * @author Nils Dahlbom Norgren & Christoffer Gunning
 */
public class RulesImpl implements Rules {

	private RuleHandler ruleHandler;

	/**
	 * Creates an implementation of the Rules interface
	 * 
	 * @param boardHandler
	 *            A boardhandler that is used in the RulesImpl
	 * 
	 */
	RulesImpl(BoardHandler boardHandler) {
		this.ruleHandler = new RuleHandler(boardHandler);
	}

	@Override
	public List<Node> getNodesToSwap(String playerId, String nodeId) {
		return ruleHandler.getNodesToSwap(playerId, nodeId);
	}

	@Override
	public boolean hasValidMove(String playerId) {
		return ruleHandler.hasValidMove(playerId);
	}

	@Override
	public boolean isMoveValid(String playerId, String nodeId) {
		return ruleHandler.isMoveValid(playerId, nodeId);
	}
}
