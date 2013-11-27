package kth.game.othello.board;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test the OthelloNode class
 * 
 * @author Nils Dahlbom Norgren, Christoffer Gunning
 * 
 */
public class NodeTest {

	/**
	 * 
	 */
	@Test
	public void testNodes() {
		NodeImpl unMarkednode = new NodeImpl(0, 0);
		Assert.assertEquals(0, unMarkednode.getXCoordinate());
		Assert.assertEquals(0, unMarkednode.getYCoordinate());
		Assert.assertEquals("0:0", unMarkednode.getId());
		Assert.assertEquals(null, unMarkednode.getOccupantPlayerId());
		Assert.assertEquals(false, unMarkednode.isMarked());

		NodeImpl markedNode = new NodeImpl(1, 1);
		markedNode.setOccupantPlayerId("1");
		Assert.assertEquals(1, markedNode.getXCoordinate());
		Assert.assertEquals(1, markedNode.getYCoordinate());
		Assert.assertEquals("1:1", markedNode.getId());
		Assert.assertEquals("1", markedNode.getOccupantPlayerId());
		Assert.assertEquals(true, markedNode.isMarked());
	}
}
