package kth.game.othello;

import java.util.ArrayList;
import java.util.List;

import kth.game.othello.board.Board;
import kth.game.othello.board.Node;

/**
 * A class to
 * 
 * @author Nils Dahlbom Norgren, Christoffer Gunning
 * 
 */
public class RuleHandler {
	private BoardHandler boardHandler;

	/**
	 * Enum for all Directions on an othello board
	 */
	public enum Direction {
		UP(-1, 0), DOWN(1, 0), LEFT(0, -1), RIGHT(0, 1), UP_LEFT(-1, -1), UP_RIGHT(
				-1, 1), DOWN_LEFT(1, -1), DOWN_RIGHT(1, 1);

		private int x, y;

		private Direction(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	/**
	 * Creates a rulehandlar that takes care of the rules in a othello game
	 * 
	 * @param boardHandler
	 */
	public RuleHandler(BoardHandler boardHandler) {
		this.boardHandler = boardHandler;
	}

	/**
	 * TODO
	 * 
	 * @param playerId
	 * @param nodeId
	 * @return
	 */
	List<Node> getNodesToSwap(String playerId, String nodeId) {
		Node node = boardHandler.getNodeFromId(nodeId);
		Board board = boardHandler.getBoard();
		if (node.isMarked())
			return new ArrayList<Node>();

		List<Node> nodesToSwap = new ArrayList<Node>();
		nodesToSwap.add(node);
		for (Direction d : Direction.values()) {
			Node newNode = null;
			boolean foundOpponent = false;
			List<Node> nodesToSwapInDirection = new ArrayList<Node>();

			try {
				newNode = board.getNode(node.getXCoordinate() + d.x,
						node.getYCoordinate() + d.y);
			} catch (IllegalArgumentException e) {
				return new ArrayList<Node>();
			}
			while (true) {
				if (newNode.isMarked()) {
					if (!foundOpponent) {
						if (!newNode.getOccupantPlayerId().equals(playerId)) {
							foundOpponent = true;
							nodesToSwapInDirection.add(newNode);
						}
					} else {
						if (!newNode.getOccupantPlayerId().equals(playerId)) {
							nodesToSwapInDirection.add(newNode);
						} else if (newNode.getOccupantPlayerId().equals(
								playerId)) {
							nodesToSwap.addAll(nodesToSwapInDirection);
							break;
						} else {
							break;
						}
					}
				}

				try {
					newNode = board.getNode(newNode.getXCoordinate() + d.x,
							newNode.getYCoordinate() + d.y);
				} catch (IllegalArgumentException e) {
					break;
				}
			}
		}
		return nodesToSwap;
	}

	/**
	 * Checks if a player has a valid move
	 * 
	 * @param playerId
	 *            The Id of the player
	 * @return True if the players has a valid move, otherwise false
	 */
	boolean hasValidMove(String playerId) {
		List<Node> nodes = boardHandler.getBoard().getNodes();
		for (Node node : nodes) {
			if (!node.isMarked() && isMoveValid(playerId, node.getId())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Checks if a move is valid
	 * 
	 * @param playerId
	 *            The Id of the player
	 * @param nodeId
	 *            The Id of the node we want to play at
	 * @return True if the player has a valid move on that node, otherwise false
	 */
	boolean isMoveValid(String playerId, String nodeId) {
		if (getNodesToSwap(playerId, nodeId).size() == 0)
			return false;
		return true;
	}
}
