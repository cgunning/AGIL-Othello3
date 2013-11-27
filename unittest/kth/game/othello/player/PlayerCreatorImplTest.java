package kth.game.othello.player;

import static org.mockito.Mockito.mock;
import kth.game.othello.player.movestrategy.MoveStrategy;

import org.junit.Assert;
import org.junit.Test;

public class PlayerCreatorImplTest {

	@Test
	public void testCreators() {
		// Test computer creator
		MoveStrategy moveStrategy = mock(MoveStrategy.class);

		PlayerCreatorImpl playerCreator = new PlayerCreatorImpl();
		Player computerPlayerWithName = playerCreator
				.createComputerPlayer("computer");
		Player computerPlayerWithNameAndStrategy = playerCreator
				.createComputerPlayer("computer2", moveStrategy);

		Assert.assertEquals(Player.Type.COMPUTER,
				computerPlayerWithName.getType());
		Assert.assertEquals(Player.Type.COMPUTER,
				computerPlayerWithNameAndStrategy.getType());
		Assert.assertEquals("FirstPickStrategy", computerPlayerWithName
				.getMoveStrategy().getName());

		// Test human creator
		Player humanPlayerWithName = playerCreator.createHumanPlayer("human");
		Assert.assertEquals(Player.Type.HUMAN, humanPlayerWithName.getType());

	}
}
