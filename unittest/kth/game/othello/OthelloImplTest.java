package kth.game.othello;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import kth.game.othello.player.Player;
import kth.game.othello.player.Player.Type;
import kth.game.othello.score.Score;

import org.junit.Assert;
import org.junit.Test;

public class OthelloImplTest {

	@Test(expected = IllegalStateException.class)
	public void testMove() {
		MoveHandler moveHandler = mock(MoveHandler.class);
		PlayerHandler playerHandler = mock(PlayerHandler.class);
		BoardHandler boardHandler = mock(BoardHandler.class);
		RulesImpl rules = mock(RulesImpl.class);
		Score score = mock(Score.class);
		Player player = mock(Player.class);
		when(playerHandler.getPlayerInTurn()).thenReturn(player);
		when(player.getType()).thenReturn(Type.HUMAN);
		Constructor<OthelloImpl> constr = (Constructor<OthelloImpl>) OthelloImpl.class
				.getDeclaredConstructors()[0];
		constr.setAccessible(true);
		OthelloImpl othelloImpl;
		try {
			othelloImpl = constr.newInstance(moveHandler, playerHandler,
					boardHandler, rules, score);
			Assert.assertEquals(IllegalStateException.class, othelloImpl.move());
		} catch (IllegalArgumentException e) {
		} catch (InstantiationException e) {
		} catch (IllegalAccessException e) {
		} catch (InvocationTargetException e) {

		}

	}
}
