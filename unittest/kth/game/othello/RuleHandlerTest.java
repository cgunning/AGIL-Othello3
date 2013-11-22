package kth.game.othello;

import java.util.ArrayList;
import java.util.List;

import kth.game.othello.board.Node;

import org.junit.Assert;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class RuleHandlerTest {

	@Test
	public void testIsValidNodeId() {
		
		List<Node> nodes = new ArrayList<Node>();
		BoardHandler boardHandler = mock(BoardHandler.class);
		for(int i = 0; i < 10; i++) {
			Node node = mock(Node.class);
			when(node.getId()).thenReturn(i + "");
			when(boardHandler.getNodeFromId(i + "")).thenReturn(node);
			nodes.add(node);
		}

		RuleHandler ruleHandler = new RuleHandler(boardHandler);
		Assert.assertEquals(true, ruleHandler.isValidNodeId("4"));
		Assert.assertEquals(false, ruleHandler.isValidNodeId("19"));
	}
}
