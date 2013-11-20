package kth.game.othello;

import java.util.ArrayList;
import java.util.List;

import kth.game.othello.board.Board;
import kth.game.othello.board.Node;
import kth.game.othello.player.Player;

/**
 * Class that handles moves on a Othello board
 * 
 * @author Nils Dahlbom Norgren, Christoffer Gunning
 * 
 */
public class MoveHandler {

	private NodeHelper nodeHelper;
	private BoardHandler boardHandler;
	private RulesImpl rules;

	MoveHandler(NodeHelper nodeHelper,
			BoardHandler boardHandler, RulesImpl ruleHelper) {
		this.nodeHelper = nodeHelper;
		this.boardHandler = boardHandler;
		this.rules = ruleHelper;
	}

	/**
	 * Method that chooses a random valid move to be played
	 * 
	 * @param board
	 *            The board that is played on
	 * @param playerInTurnId
	 *            The Id of the player in turn
	 * @return List of nodes that will be effected for that move or an empty
	 *         ArrayList if there are no moves.
	 */
	List<Node> move(Player player) {
		List<Node> nodes = boardHandler.getBoard().getNodes();
		for (Node node : nodes) {
			if (rules.isMoveValid(player.getId(), node.getId())) {
				return move(player, node.getId());
			}
		}
		return new ArrayList<Node>();
	}

	/**
	 * Method that makes a move to be played for a certain node.
	 * 
	 * @param board
	 *            The board that is played on
	 * @param playerId
	 *            The Id for the player who makes the move
	 * @param nodeId
	 *            The Id for the node where the move is played
	 * @return List of nodes that will be effected for that move.
	 */
	List<Node> move(Player player, String nodeId) {
		// TODO Check so that the id is in the range of the game
		if (!nodeHelper.isValidNodeId(nodeId) || player == null)
			throw new IllegalArgumentException();
		List<Node> nodesToSwap = rules.getNodesToSwap(player.getId(), nodeId);
		boardHandler.updateMovesOnBoard(nodesToSwap, player);
		return nodesToSwap;
	}

}