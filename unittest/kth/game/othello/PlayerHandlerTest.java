package kth.game.othello;

import java.util.ArrayList;

import kth.game.othello.player.ComputerPlayer;
import kth.game.othello.player.HumanPlayer;
import kth.game.othello.player.Player;


import org.junit.Assert;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class PlayerHandlerTest {
	
	PlayerHandler playerHandler;
	
	@Test
	public void testGetPlayerFromId() {
		Player blackPlayer = mock(HumanPlayer.class);
		Player whitePlayer = mock(ComputerPlayer.class);
		
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(blackPlayer);
		players.add(whitePlayer);
		
		when(blackPlayer.getId()).thenReturn("1");		
		when(whitePlayer.getId()).thenReturn("2");
		
		PlayerHandler playerHandler = new PlayerHandler(players);
		
		Assert.assertEquals("1", playerHandler.getPlayerFromId(blackPlayer.getId()).getId());
		Assert.assertEquals(null, playerHandler.getPlayerFromId("Grovsopor"));
	}
	
	@Test
	public void testGetOpponent() {
		Player blackPlayer = mock(HumanPlayer.class);
		Player whitePlayer = mock(ComputerPlayer.class);
		
		when(blackPlayer.getId()).thenReturn("1");		
		when(whitePlayer.getId()).thenReturn("2");
		
		PlayerHandler playerHandler = new PlayerHandler(blackPlayer, whitePlayer);
		
		Assert.assertEquals("2", playerHandler.getOpponent(blackPlayer.getId()).getId());
		Assert.assertEquals(null, playerHandler.getOpponent("Grovsopor"));
	}
}
