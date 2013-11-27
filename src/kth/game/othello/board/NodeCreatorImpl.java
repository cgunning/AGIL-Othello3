package kth.game.othello.board;

public class NodeCreatorImpl implements NodeCreator {

	@Override
	public Node createNodeWithCoordinate(int x, int y) {
		return new NodeImpl(x, y);
	}

	@Override
	public Node createNodeWithCoordinateAndOccupant(int x, int y,
			String occupantPlayerId) {
		NodeImpl node = new NodeImpl(x, y);
		node.setOccupantPlayerId(occupantPlayerId);
		return node;
	}

}
