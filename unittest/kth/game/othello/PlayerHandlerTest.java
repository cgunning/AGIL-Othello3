package kth.game.othello;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import kth.game.othello.player.ComputerPlayer;
import kth.game.othello.player.HumanPlayer;
import kth.game.othello.player.Player;

import org.junit.Assert;
import org.junit.Test;

public class PlayerHandlerTest {

	@Test
	public void testGetPlayerFromId() {
		Player blackPlayer = mock(HumanPlayer.class);
		Player whitePlayer = mock(ComputerPlayer.class);
		Player orangePlayer = mock(ComputerPlayer.class);

		ArrayList<Player> players = new ArrayList<Player>();
		players.add(blackPlayer);
		players.add(whitePlayer);
		players.add(orangePlayer);

		when(blackPlayer.getId()).thenReturn("1");
		when(whitePlayer.getId()).thenReturn("2");
		when(orangePlayer.getId()).thenReturn("3");

		PlayerHandler playerHandler = new PlayerHandler(players);

		Assert.assertEquals("1",
				playerHandler.getPlayerFromId(blackPlayer.getId()).getId());
		Assert.assertNotEquals("2", playerHandler.getPlayerFromId(orangePlayer.getId()).getId());
	}

	@Test
	public void testGetNextPlayer() {
		Player blackPlayer = mock(HumanPlayer.class);
		Player whitePlayer = mock(ComputerPlayer.class);
		Player orangePlayer = mock(ComputerPlayer.class);

		ArrayList<Player> players = new ArrayList<Player>();
		when(blackPlayer.getId()).thenReturn("1");
		when(whitePlayer.getId()).thenReturn("2");
		when(orangePlayer.getId()).thenReturn("3");
		players.add(blackPlayer);
		players.add(whitePlayer);
		players.add(orangePlayer);

		PlayerHandler playerHandler = new PlayerHandler(players);

		Assert.assertEquals("2",
				playerHandler.getNextPlayer(blackPlayer.getId()).getId());
		Assert.assertNotEquals("3",
				playerHandler.getNextPlayer(orangePlayer.getId()).getId());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testException() {
		Player blackPlayer = mock(HumanPlayer.class);
		Player whitePlayer = mock(ComputerPlayer.class);

		ArrayList<Player> players = new ArrayList<Player>();
		when(blackPlayer.getId()).thenReturn("1");
		when(whitePlayer.getId()).thenReturn("2");
		players.add(blackPlayer);
		players.add(whitePlayer);

		PlayerHandler playerHandler = new PlayerHandler(players);
		Assert.assertEquals(IllegalArgumentException.class,
				playerHandler.getPlayerFromId("Grovsopor"));
	}
}
