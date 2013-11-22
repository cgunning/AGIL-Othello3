package kth.game.othello;
import java.util.List;

import kth.game.othello.board.Board;
import kth.game.othello.board.BoardCreator;
import kth.game.othello.board.Node;
import kth.game.othello.board.NodeImpl;
import kth.game.othello.player.Player;

/**
 * This class initializes and updates a board.
 * 
 * @author Nils Dahlbom Norgren, Christoffer Gunning
 * 
 */
class BoardHandler {

	private Board board;
	private BoardCreator boardCreator;
	
	BoardHandler(Board board, BoardCreator boardCreator) {
		this.board = board;
		this.boardCreator = boardCreator;
	}

	/**
	 * Method for updating nodes on a existing board
	 * 
	 * @param board
	 *            Board to be updated
	 * @param nodesToSwap
	 *            a list of all the nodes that should be swapped
	 * @param player
	 *            the player whom turn it is
	 */
	void updateMovesOnBoard(List<Node> nodesToSwap, String playerId) {
		List<Node> boardNodes = board.getNodes();
		for (Node node : nodesToSwap) {
			int xCoordinate = node.getXCoordinate();
			int yCoordinate = node.getYCoordinate();
			int index = 8 * xCoordinate + yCoordinate;
			boardNodes.set(index, new NodeImpl(xCoordinate, yCoordinate,
					playerId));
		}
		this.board = boardCreator.createBoard(boardNodes);
	}

	/**
	 * Returns the Othello board
	 * 
	 * @return - Returns the board to be played at
	 */
	Board getBoard() {
		return board;
	}

}
