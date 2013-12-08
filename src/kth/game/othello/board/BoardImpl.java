package kth.game.othello.board;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents an OthelloBoard
 * 
 * @author Nils Dahlbom Norgren, Christoffer Gunning
 * 
 */
public class BoardImpl implements Board {

	private List<Node> nodes;

	/**
	 * Creates a board of specific structure
	 * 
	 * @param nodes
	 *            - The nodes of the structure
	 */
	public BoardImpl(List<Node> nodes) {
		this.nodes = nodes;
	}

	@Override
	public List<Node> getNodes() {
		return new ArrayList<Node>(nodes);
	}

	@Override
	public Node getNode(int x, int y) throws IllegalArgumentException {
		for (Node node : nodes)
			if (node.getXCoordinate() == x && node.getYCoordinate() == y)
				return node;
		throw new IllegalArgumentException("No such node");
	}

	@Override
	public int getMaxX() {
		int maxX = 0;
		for (Node node : nodes)
			if (node.getXCoordinate() > maxX)
				maxX = node.getXCoordinate();
		return maxX;
	}

	@Override
	public int getMaxY() {
		int maxY = 0;
		for (Node node : nodes)
			if (node.getYCoordinate() > maxY)
				maxY = node.getYCoordinate();
		return maxY;
	}

	@Override
	public boolean hasNode(int x, int y) {
		try {
			getNode(x, y);
		} catch (IllegalArgumentException e) {
			return false;
		}

		return true;
	}
}
