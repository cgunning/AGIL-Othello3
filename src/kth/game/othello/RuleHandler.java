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
public class RuleHandler {
	
	public enum Direction {
		UP(-1, 0), DOWN(1, 0), LEFT(0, -1), RIGHT(0, 1), UP_LEFT(-1, -1), UP_RIGHT(-1, 1), DOWN_LEFT(1, -1), DOWN_RIGHT(1, 1);
		
		private int x, y;

        private Direction(int x, int y) {
                this.x = x;
                this.y = y;
        }
	}

	private BoardHandler boardHandler;
	
	public RuleHandler(BoardHandler boardHandler) {
		this.boardHandler = boardHandler;
	}
	
	/**
	 * Checks if an ID of a node is a valid ID
	 * 
	 * @param nodeId
	 *            - The ID of the node
	 * @return True if the ID is valid, false otherwise
	 */
	boolean isValidNodeId(String nodeId) {
		Node node = boardHandler.getNodeFromId(nodeId);
		if (node == null)
			return false;
		return true;
	}
	
	List<Node> getNodesToSwap(String playerId, String nodeId) {
		Node node = boardHandler.getNodeFromId(nodeId);
		Board board = boardHandler.getBoard();
		if(node.isMarked())
			return null;
		
		List<Node> nodesToSwap = new ArrayList<Node>();
		for(Direction d : Direction.values()) {
			Node newNode = null;
			boolean foundOpponent = false;
			List<Node> nodesToSwapInDirection = new ArrayList<Node>();
			
			try {
				newNode = board.getNode(node.getXCoordinate() + d.x, node.getYCoordinate() + d.y);
			} catch (IllegalArgumentException e) {
				return null;
			}
			while(true) {
				if(newNode.isMarked()) {
					if(!foundOpponent) {
						if(!newNode.getOccupantPlayerId().equals(playerId)) {
							foundOpponent = true;
							nodesToSwapInDirection.add(newNode);
						} 
					} else {
						if(!newNode.getOccupantPlayerId().equals(playerId)) {
							nodesToSwapInDirection.add(newNode);
						} else if(newNode.getOccupantPlayerId().equals(playerId)) {
							nodesToSwap.addAll(nodesToSwapInDirection);
							break;
						} else {
							break;
						}
					}
				}
		
				try {
					newNode = board.getNode(newNode.getXCoordinate() + d.x, newNode.getYCoordinate() + d.y);
				} catch (IllegalArgumentException e) {
					break;
				}
			}
		}
		
		return nodesToSwap;
	}
	
	boolean hasValidMove(String playerId) {
		List<Node> nodes = boardHandler.getBoard().getNodes();
		for (Node node : nodes) {
			if (!node.isMarked() && isMoveValid(playerId, node.getId())) {
				return true;
			}
		}
		return false;
	}
	
	boolean isMoveValid(String playerId, String nodeId) {
		if (getNodesToSwap(playerId, nodeId).size() == 0)
			return false;
		
		return true;
	}
}