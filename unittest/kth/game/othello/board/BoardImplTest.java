package kth.game.othello.board;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test the OthelloBoard class
 * 
 * @author Nils Dahlbom Norgren, Christoffer Gunning
 * 
 */
public class BoardImplTest {

	@Test
	public void boardImplTest() {
		BoardImpl boardImpl = createBoardImplWithMockedNodes();

		// Test the BoardImpl
		Assert.assertEquals(5, boardImpl.getNodes().size());
		// Test getNode
		Assert.assertEquals(0, boardImpl.getNode(0, 0).getXCoordinate());
		Assert.assertEquals(0, boardImpl.getNode(0, 0).getYCoordinate());

	}

	@Test(expected = IllegalArgumentException.class)
	public void tryException() {
		BoardImpl boardImpl = createBoardImplWithMockedNodes();
		Assert.assertEquals(IllegalArgumentException.class,
				boardImpl.getNode(5, 5));
	}

	public BoardImpl createBoardImplWithMockedNodes() {
		// Create a BoardImpl with a list of mocked nodes
		List<Node> nodes = new ArrayList<Node>();
		for (int i = 0; i < 5; i++) {
			Node node = mock(Node.class);
			when(node.getXCoordinate()).thenReturn(i);
			when(node.getYCoordinate()).thenReturn(i);
			nodes.add(node);
		}
		return new BoardImpl(nodes);

	}

}
