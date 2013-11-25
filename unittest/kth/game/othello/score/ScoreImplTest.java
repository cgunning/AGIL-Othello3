package kth.game.othello.score;

import java.util.ArrayList;
import java.util.List;

import kth.game.othello.board.Node;
import kth.game.othello.board.NodeImpl;
import kth.game.othello.player.Player;

import org.junit.Assert;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class ScoreImplTest {

	
	@Test
	public void testUpdate() {
		NodeImpl node = mock(NodeImpl.class);
		List<Player> players = new ArrayList<Player>();
		
		Player player1 = mock(Player.class);
		when(player1.getId()).thenReturn("1");
		Player player2 = mock(Player.class);
		when(player2.getId()).thenReturn("2");
		players.add(player1);
		players.add(player2);
		
		ScoreImpl scores = new ScoreImpl(players);	
		when(node.getOccupantPlayerId()).thenReturn("2");	
		scores.update(node, null);
		Assert.assertEquals(1, scores.getPoints("2"));

		when(node.getOccupantPlayerId()).thenReturn("1");	
		scores.update(node, "2");
		Assert.assertEquals(1, scores.getPoints("1"));
		Assert.assertEquals(0, scores.getPoints("2"));
	}
}
