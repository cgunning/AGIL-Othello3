package kth.game.othello;

import java.util.List;

import kth.game.othello.board.Board;
import kth.game.othello.board.BoardImpl;
import kth.game.othello.board.Node;
import kth.game.othello.board.NodeImpl;

/**
 * This class initializes and updates a board.
 * 
 * @author Nils Dahlbom Norgren, Christoffer Gunning
 * 
 */
class BoardHandler {

	private Board board;

	/**
	 * Creates a boardHandler, which purpose is to change the nodes on the
	 * board.
	 * 
	 * @param board
	 *            Board that is played on.
	 */
	BoardHandler(Board board) {
		this.board = board;
	}

	/**
	 * Method for updating nodes on a existing board
	 * 
	 * @param nodesToSwap
	 *            The nodes to be swapped on the board
	 * @param playerId
	 *            The Id of the player who is swapping the nodes
	 */
	/* TODO */
	void updateMovesOnBoard(List<Node> nodesToSwap, String playerId) {
		this.board = new BoardImpl(getSwappedBoardNodes(nodesToSwap, playerId));
	}

	/**
	 * Updates the board nodes with the nodes that are to be swapped
	 * 
	 * @param nodesToSwap
	 *            the nodes to swap
	 * @param playerId
	 *            the player that are swapping the nodes
	 * @return
	 */
	private List<Node> getSwappedBoardNodes(List<Node> nodesToSwap,
			String playerId) {
		List<Node> boardNodes = board.getNodes();
		for (Node node : nodesToSwap) {
			NodeImpl nodeToSwap = (NodeImpl) node;
			nodeToSwap.setOccupantPlayerId(playerId);
			for (int i = 0; i < boardNodes.size(); i++)
				if (boardNodes.get(i).getId().equals(nodeToSwap.getId()))
					boardNodes.set(i, nodeToSwap);
		}
		return boardNodes;
	}

	/**
	 * Returns the Othello board
	 * 
	 * @return - Returns the board to be played at
	 */
	Board getBoard() {
		return board;
	}

	/**
	 * Get a node from its Id
	 * 
	 * @param nodeId
	 *            The Id for the node to be returned
	 * @return The node corresponding to the Id, if there is no node who has the
	 *         Id it returns null
	 */
	Node getNodeFromId(String nodeId) {
		for (Node node : board.getNodes()) {
			if (node.getId().equals(nodeId)) {
				return node;
			}
		}
		return null;
	}
}
