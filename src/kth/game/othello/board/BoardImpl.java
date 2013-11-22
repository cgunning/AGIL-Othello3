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
	private int dimension;
	
	//TODO
	/**
	 * Creates a board of specific structure
	 * @param nodes - 	The nodes of the structure
	 */
	public BoardImpl(List<Node> nodes) {
		this.nodes = nodes;
	}
	
	/**
	 * Creates a square board of specified dimension. Results in a board of size dimension*dimension
	 * @param dimension		-	the dimension of the board
	 */
	public BoardImpl(int dimension) {
		this.dimension = dimension;
		nodes = new ArrayList<Node>();
		for(int i = 0; i < dimension; i++) {
			for(int j = 0; j < dimension; j++) {
				nodes.add(new NodeImpl(i, j));
			}
		}
	}
	
	@Override
	public List<Node> getNodes() {
		return new ArrayList<Node>(nodes);
	}
	
	/**
	 * Gets the dimension of the board
	 * @return dimension	-	the dimension of the board
	 */
	int getDimension() {
		return dimension;
	}

	@Override
	public Node getNode(int x, int y) {
		return nodes.get(8*x + y);
	}
}
