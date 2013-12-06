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

	List<Node> nodes;

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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMaxY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean hasNode(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}
}
