package kth.game.othello;

import java.util.ArrayList;
import java.util.List;

import kth.game.othello.board.Board;
import kth.game.othello.board.Node;
import kth.game.othello.player.Player;

/**
 * A class to
 * 
 * @author Nils Dahlbom Norgren, Christoffer Gunning
 * 
 */
public class RuleHelper {

	private static int UP = -8, LEFT = -1, RIGHT = 1, DOWN = 8, UP_LEFT = -9,
			UP_RIGHT = -7, DOWN_LEFT = 7, DOWN_RIGHT = 9;

	/**
	 * Method to see if the node in the given direction is a valid step.
	 * 
	 * @param board
	 *            - The board that is played on
	 * @param index
	 *            - Index of the node to be checked if is valid step
	 * @param direction
	 *            - The direction of movement
	 * @return boolean - True if this step is valid in this direction, otherwise
	 *         false.
	 */
	boolean isValidStep(Board board, int index, int direction) {
		return (index < board.getNodes().size() && index >= 0 && (Math
				.abs(direction) > 1
				|| ((index + 1) % 8 != 0 && direction == LEFT) || ((index) % 8 != 0 && direction == RIGHT)));
	}

	/**
	 * Checks whether a step in a diagonal direction is an illegal step.
	 * 
	 * @param board
	 *            - The board that is played on
	 * @param lastNode
	 *            - The last x coordinate
	 * @param currentNode
	 *            - The index for the node in the current step
	 * @param change
	 *            - The number of nodes to skip in the list to make the step
	 * @return boolean - True if the step is an illegal step in that diagonal
	 *         direction, otherwise false.
	 */
	boolean isIllegalDiagonalStep(Board board, Node lastNode, Node currentNode,
			int change) {
		if (currentNode.getXCoordinate() == lastNode.getXCoordinate()
				&& (change == UP_RIGHT || change == DOWN_LEFT))
			return true;
		else if (Math.abs(currentNode.getXCoordinate()
				- lastNode.getXCoordinate()) == 2
				&& (change == UP_LEFT || change == DOWN_RIGHT))
			return true;
		return false;
	}

	/**
	 * Finds a valid move in the specified direction
	 * 
	 * @param board
	 *            - The board that is played on
	 * @param xCoordinate
	 *            - The x coordinate of the node that is played on
	 * @param yCoordinate
	 *            - The y coordinate of the node that is played on
	 * @param player
	 *            - The player making the move
	 * @param direction
	 *            - The direction to find a valid move in
	 * @return List<Node> nodes - The nodes to be swapped in the direction
	 *         specified
	 */
	List<Node> findValidMoveInDirection(Board board, int xCoordinate,
			int yCoordinate, String playerId, int direction) {
		List<Node> nodes = board.getNodes();
		int i = 8*xCoordinate + yCoordinate;
		Node lastNode = nodes.get(i);
		boolean foundOpponent = false;
		List<Node> returnedNodes = new ArrayList<Node>();
		returnedNodes.add(nodes.get(i));
		i += direction;
		while (isValidStep(board, i, direction)) {
			Node currentNode = nodes.get(i);

			if (isIllegalDiagonalStep(board, lastNode, currentNode, direction))
				return null;

			if (currentNode.isMarked()) {
				if (!foundOpponent) { // The first node in a direction needs to
										// be an opponents
					if (!currentNode.getOccupantPlayerId().equals(
							playerId)) {
						foundOpponent = true;
						returnedNodes.add(currentNode);
					} else {
						return null;
					}
				} else if (currentNode.getOccupantPlayerId().equals(
						playerId)) {
					return returnedNodes;
				} else {
					returnedNodes.add(currentNode);
				}
			} else
				return null;
			i += direction; // Continue in the given direction
			lastNode = currentNode;
		}
		return null;
	}

	/**
	 * Gets the index of a node from its coordinates
	 * 
	 * @param board
	 *            - The board that is played on
	 * @param xCoordinate
	 *            - The x coordinate of the node
	 * @param yCoordinate
	 *            - The y coordinate of the node
	 * @return The index of the node
	 */
	int getIndexFromCoordinates(Board board, int xCoordinate, int yCoordinate) {
		int dimension = (int) Math.sqrt(board.getNodes().size());
		return dimension * xCoordinate + yCoordinate;
	}

	/**
	 * Returns a node corresponding to the ID
	 * 
	 * @param nodeId
	 *            - The ID of the node
	 * @return The node
	 */
	Node getNodeFromId(Board board, String nodeId) {
		for(Node node : board.getNodes()) {
			if(node.getId() == nodeId) {
				return node;
			}
		}
		return null;
	}

	/**
	 * Checks if an ID of a node is a valid ID
	 * 
	 * @param nodeId
	 *            - The ID of the node
	 * @return True if the ID is valid, false otherwise
	 */
	boolean isValidNodeId(Board board, String nodeId) {
		Node node = getNodeFromId(board, nodeId);
		if (node == null)
			return false;
		return true;
	}
}
