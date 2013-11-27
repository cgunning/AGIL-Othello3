package kth.game.othello;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import kth.game.othello.board.Board;
import kth.game.othello.board.Node;
import kth.game.othello.player.movestrategy.MoveStrategy;

import org.junit.Assert;
import org.junit.Test;

public class MoveHandlerTest {

	@Test(expected = IllegalStateException.class)
	public void testNullCheckOnMove() {
		BoardHandler boardHandler = mock(BoardHandler.class);
		RulesImpl rulesImpl = mock(RulesImpl.class);
		MoveHandler moveHandler = new MoveHandler(boardHandler, rulesImpl);
		MoveStrategy moveStrategy = mock(MoveStrategy.class);

		when(moveStrategy.move(anyString(), any(Rules.class), any(Board.class)))
				.thenReturn(null);
		Assert.assertEquals(IllegalStateException.class,
				moveHandler.move("", moveStrategy));
	}

	@Test(expected = IllegalStateException.class)
	public void testNonExistingNode() {
		Node node = mock(Node.class);
		BoardHandler boardHandler = mock(BoardHandler.class);
		RulesImpl rulesImpl = mock(RulesImpl.class);
		MoveHandler moveHandler = new MoveHandler(boardHandler, rulesImpl);
		MoveStrategy moveStrategy = mock(MoveStrategy.class);
		when(moveStrategy.move(anyString(), any(Rules.class), any(Board.class)))
				.thenReturn(node);
		when(rulesImpl.isMoveValid(anyString(), anyString())).thenThrow(
				new IllegalArgumentException());
		Assert.assertEquals(IllegalStateException.class,
				moveHandler.move("", moveStrategy));
	}
}
