package kth.game.othello.player;

import static org.mockito.Mockito.mock;
import kth.game.othello.player.Player.Type;
import kth.game.othello.player.movestrategy.MoveStrategy;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test different player implementations
 * 
 * @author Nils Dahlbom Norgren, Christoffer Gunning
 * 
 */

public class PlayerTest {

	@Test
	public void testPlayer() {
		// Create 4 players, 2 computer-players and 2 human-players
		Player computerPlayer = new ComputerPlayer("computer");
		Player computerPlayer2 = new ComputerPlayer("computer2");
		Player humanPlayer = new HumanPlayer("human");
		Player humanPlayer2 = new HumanPlayer("human2");

		// Test if getName is correct
		Assert.assertEquals("computer", computerPlayer.getName());
		Assert.assertEquals("computer2", computerPlayer2.getName());
		Assert.assertEquals("human", humanPlayer.getName());
		Assert.assertEquals("human2", humanPlayer2.getName());

		// Test to see that no players have the same Id
		Assert.assertNotSame(computerPlayer.getId(), computerPlayer2.getId());
		Assert.assertNotSame(computerPlayer.getId(), humanPlayer.getId());
		Assert.assertNotSame(computerPlayer.getId(), humanPlayer2.getId());
		Assert.assertNotSame(humanPlayer.getId(), computerPlayer2.getId());
		Assert.assertNotSame(humanPlayer.getId(), humanPlayer2.getId());
		Assert.assertNotSame(humanPlayer2.getId(), computerPlayer2.getId());

		// Test to see that type for player is correct
		Assert.assertEquals(Type.COMPUTER, computerPlayer.getType());
		Assert.assertEquals(Type.COMPUTER, computerPlayer2.getType());
		Assert.assertEquals(Type.HUMAN, humanPlayer.getType());
		Assert.assertEquals(Type.HUMAN, humanPlayer2.getType());

	}

	@Test(expected = UnsupportedOperationException.class)
	public void testSetMoveStrategy() {
		Player humanPlayer = new HumanPlayer("human");
		MoveStrategy moveStrategy = mock(MoveStrategy.class);
		humanPlayer.setMoveStrategy(moveStrategy);

	}
}
