package kth.game.othello;

import java.util.ArrayList;
import java.util.List;

import kth.game.othello.board.Node;

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
	 * Method that chooses a random valid move to be played
	 * 
	 * @param playerId
	 *            The id of the player who wants to make the move
	 * @return List<Node> The list of the nodes that will be effected for that
	 *         move
	 */
	List<Node> move(String playerId) {
		List<Node> nodes = boardHandler.getBoard().getNodes();
		for (Node node : nodes) {
			if (rules.isMoveValid(playerId, node.getId())) {
				return move(playerId, node.getId());
			}
		}
		return new ArrayList<Node>();
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
	List<Node> move(String playerId, String nodeId) {
		// // TODO Check so that the id is in the range of the game
		// if (!rules.isMoveValid(playerId, nodeId))
		// throw new IllegalArgumentException();
		List<Node> nodesToSwap = rules.getNodesToSwap(playerId, nodeId);
		boardHandler.updateMovesOnBoard(nodesToSwap, playerId);
		return nodesToSwap;
	}

}
