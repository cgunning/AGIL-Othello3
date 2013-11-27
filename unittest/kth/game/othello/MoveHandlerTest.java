package kth.game.othello;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import kth.game.othello.board.Board;
import kth.game.othello.player.movestrategy.MoveStrategy;

import org.junit.Assert;
import org.junit.Test;

public class MoveHandlerTest {

	@Test
	public void testNullCheckOnMove() {
		BoardHandler boardHandler = mock(BoardHandler.class);
		RulesImpl rulesImpl = mock(RulesImpl.class);
		MoveHandler moveHandler = new MoveHandler(boardHandler, rulesImpl);
		MoveStrategy moveStrategy = mock(MoveStrategy.class);
		ArrayList empty = new ArrayList();

		when(moveStrategy.move(anyString(), any(Rules.class), any(Board.class)))
				.thenReturn(null);
		Assert.assertEquals(empty, moveHandler.move("", moveStrategy));

	}
}
