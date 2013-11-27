package kth.game.othello;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import kth.game.othello.board.Board;
import kth.game.othello.board.Node;

import org.junit.Assert;
import org.junit.Test;

public class RuleHandlerTest {

	@Test
	public void testGetNodesToSwap2() {
		Board board = mock(Board.class);
		BoardHandler boardHandler = mock(BoardHandler.class);

		// Creates a mocked board with mocked nodes
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				Node node = mock(Node.class);
				when(node.getXCoordinate()).thenReturn(i);
				when(node.getYCoordinate()).thenReturn(j);
				when(node.getId()).thenReturn("" + i + ":" + j);
				if (i == 1 && j == 1) {
					when(node.getOccupantPlayerId()).thenReturn("1");
					when(node.isMarked()).thenReturn(true);
				} else if (i == 1 && j == 2) {
					when(node.getOccupantPlayerId()).thenReturn("2");
					when(node.isMarked()).thenReturn(true);
				} else if (i == 2 && j == 2) {
					when(node.getOccupantPlayerId()).thenReturn("1");
					when(node.isMarked()).thenReturn(true);
				} else if (i == 2 && j == 1) {
					when(node.getOccupantPlayerId()).thenReturn("2");
					when(node.isMarked()).thenReturn(true);
				} else {
					when(node.getOccupantPlayerId()).thenReturn(null);
					when(node.isMarked()).thenReturn(false);
				}
				when(boardHandler.getNodeFromId("" + i + ":" + j)).thenReturn(
						node);
				when(board.getNode(i, j)).thenReturn(node);
			}

		}

		// Try the logic in testGetNodesToSwap method in RuleHandler
		when(boardHandler.getBoard()).thenReturn(board);
		RuleHandler ruleHandler = new RuleHandler(boardHandler);
		Assert.assertEquals(2, ruleHandler.getNodesToSwap("2", "0:1").size());
		Assert.assertEquals(1, ruleHandler.getNodesToSwap("1", "0:1").size());
		Assert.assertEquals(1, ruleHandler.getNodesToSwap("2", "0:2").size());
	}
}
