package kth.game.othello.board;

import org.junit.Assert;
import org.junit.Test;

public class NodeCreatorTest {

	@Test
	public void testNodeCreatorWithCoordinates() {
		NodeCreatorImpl nodeCreatorImpl = new NodeCreatorImpl();

		Node nodeWithCoordinates = nodeCreatorImpl.createNodeWithCoordinate(0, 0);
		Assert.assertEquals(0, nodeWithCoordinates.getXCoordinate());
		Assert.assertEquals(0, nodeWithCoordinates.getYCoordinate());
		Assert.assertEquals(null, nodeWithCoordinates.getOccupantPlayerId());
		Assert.assertEquals(false, nodeWithCoordinates.isMarked());
	}

	@Test
	public void testNodeCreatorWithCoordinatesAndOccupant() {
		NodeCreatorImpl nodeCreatorImpl = new NodeCreatorImpl();

		Node nodeWithCoordinatesAndOccupant = nodeCreatorImpl
				.createNodeWithCoordinateAndOccupant(0, 0, "one");
		Assert.assertEquals(0, nodeWithCoordinatesAndOccupant.getXCoordinate());
		Assert.assertEquals(0, nodeWithCoordinatesAndOccupant.getYCoordinate());
		Assert.assertEquals("one",
				nodeWithCoordinatesAndOccupant.getOccupantPlayerId());
		Assert.assertEquals(true, nodeWithCoordinatesAndOccupant.isMarked());
	}
}
