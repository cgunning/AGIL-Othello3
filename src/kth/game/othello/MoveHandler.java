package kth.game.othello;

import java.util.ArrayList;
import java.util.List;

import kth.game.othello.board.Node;
import kth.game.othello.player.movestrategy.MoveStrategy;

/**
 * Class that handles moves on a Othello board
 * 
 * @author Nils Dahlbom Norgren, Christoffer Gunning
 * 
 */
public class MoveHandler {

	private BoardHandler boardHandler;
	private RulesImpl rules;

	/**
	 * Creates a movehandler that takes care of the the moves in a othello game
	 * 
	 * @param boardHandler
	 *            A BoardHandler
	 * @param ruleHelper
	 *            A RuleHelper
	 */
	MoveHandler(BoardHandler boardHandler, RulesImpl ruleHelper) {
		this.boardHandler = boardHandler;
		this.rules = ruleHelper;
	}

	/**
	 * Method that does a computer move with different strategies.
	 * 
	 * @param playerId
	 *            The id of the player who wants to make the move
	 * @return List<Node> The list of the nodes that will be effected for that
	 *         move
	 */
	List<Node> move(String playerId, MoveStrategy moveStrategy) throws IllegalStateException {
		Node node = moveStrategy.move(playerId, rules, boardHandler.getBoard());
		if (node == null) {
			throw new IllegalStateException();
		} else {
			try {
				return move(playerId, node.getId());
			} catch (IllegalArgumentException e) {
				throw new IllegalStateException();
			}
		}
	}

	/**
	 * Method that makes a move to be played for a certain node.
	 * 
	 * @param playerId
	 *            The id of the player who wants to make the move
	 * @param nodeId
	 *            The id of the node that the player wants to make a move on
	 * @return List<Node> The list of the nodes that will be effected for that
	 *         move
	 */
	List<Node> move(String playerId, String nodeId) throws IllegalArgumentException {
		// TODO Check so that the id is in the range of the game
		 if (!rules.isMoveValid(playerId, nodeId))
			 throw new IllegalArgumentException();
		 
		List<Node> nodesToSwap = rules.getNodesToSwap(playerId, nodeId);
		boardHandler.updateMovesOnBoard(nodesToSwap, playerId);
		return nodesToSwap;
	}

}
