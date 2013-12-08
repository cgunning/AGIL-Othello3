package kth.game.othello.board;

import java.util.Observable;
import java.util.Observer;
import java.util.UUID;

/**
 * The responsibility of a node is to keep information of which player is
 * occupying it.
 * 
 * @author Nils Dahlbom Norgren, Christoffer Gunning
 * 
 */
public class NodeImpl extends Observable implements Node {

	private UUID id;
	private String occupantPlayerId;
	private int xCoordinate;
	private int yCoordinate;
	private boolean marked;

	/**
	 * Creates an unmarked node
	 * 
	 * @param xCoordinate
	 *            - the x coordinate of the node
	 * @param yCoordinate
	 *            - the y coordinate of the node
	 */
	public NodeImpl(int xCoordinate, int yCoordinate) {
		this.id = UUID.randomUUID();
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
		this.marked = false;
	}

	public void setOccupantPlayerId(String playerId) {
		String previousOccupantPlayerId = this.occupantPlayerId;
		this.occupantPlayerId = playerId;
		this.marked = true;
		setChanged();
		notifyObservers(previousOccupantPlayerId);
	}

	@Override
	public String getId() {
		return id.toString();
	}

	@Override
	public String getOccupantPlayerId() {
		return occupantPlayerId;
	}

	@Override
	public int getXCoordinate() {
		return xCoordinate;
	}

	@Override
	public int getYCoordinate() {
		return yCoordinate;
	}

	@Override
	public boolean isMarked() {
		return marked;
	}

	@Override
	public void addObserver(Observer observer) {
		super.addObserver(observer);
	}
}
