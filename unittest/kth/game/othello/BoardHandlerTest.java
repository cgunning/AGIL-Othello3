package kth.game.othello;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import kth.game.othello.board.Board;
import kth.game.othello.board.Node;
import kth.game.othello.board.NodeImpl;

import org.junit.Assert;
import org.junit.Test;

public class BoardHandlerTest {

	@Test
	public void testGetSwappedBoardNodes() {
		Board board = mock(Board.class);
		List<Node> nodes = new ArrayList<Node>();
		List<Node> nodesToSwap = new ArrayList<Node>();
		String playerId = "1";

		for (int i = 0; i < 10; i++) {
			NodeImpl node = mock(NodeImpl.class);
			when(node.getId()).thenReturn(i + "");
			when(node.getOccupantPlayerId()).thenReturn(null);
			nodes.add(node);
		}

		for (int i = 4; i < 7; i++) {
			NodeImpl node = mock(NodeImpl.class);
			when(node.getId()).thenReturn(i + "");
			when(node.getOccupantPlayerId()).thenReturn("1");
			nodesToSwap.add(node);
		}

		when(board.getNodes()).thenReturn(nodes);

		BoardHandler boardHandler = new BoardHandler(board);
		List<Node> swappedNodes = null;
		try {
			System.out.println("1");
			Method getSwappedBoardNodes = boardHandler.getClass()
					.getDeclaredMethod("getSwappedBoardNodes", List.class,
							String.class);

			System.out.println("2");
			getSwappedBoardNodes.setAccessible(true);
			System.out.println("3");
			swappedNodes = (List<Node>) getSwappedBoardNodes.invoke(
					boardHandler, nodesToSwap, playerId);
		} catch (Exception useless) {
			useless.printStackTrace();
		}

		for (int i = 0; i < 10; i++) {
			if (i >= 4 && i < 7)
				Assert.assertEquals("1", swappedNodes.get(i)
						.getOccupantPlayerId());
			else
				Assert.assertEquals(null, swappedNodes.get(i)
						.getOccupantPlayerId());
		}

	}
}
