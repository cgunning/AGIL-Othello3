package kth.game.othello.score;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import kth.game.othello.board.NodeImpl;
import kth.game.othello.player.Player;

import org.junit.Assert;
import org.junit.Test;

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
		Assert.assertEquals(3, scores.getPoints("2"));

		when(node.getOccupantPlayerId()).thenReturn("1");
		scores.update(node, "2");
		Assert.assertEquals(3, scores.getPoints("1"));
		Assert.assertEquals(2, scores.getPoints("2"));
	}

	@Test
	public void testConstructorWithScore() {
		List<Player> players = createAmountOfPlayers(2);
		ScoreImpl scores = new ScoreImpl(players);
		Assert.assertEquals(2, scores.getPoints("0"));
		Assert.assertEquals(2, scores.getPoints("1"));
		Assert.assertEquals(-1, scores.getPoints("2"));
		Assert.assertEquals(2, scores.getPlayersScore().size());

	}

	@Test
	public void testWithAmountOfPlayers() {
		List<Player> players = createAmountOfPlayers(6);
		ScoreImpl scores = new ScoreImpl(players);
		Assert.assertEquals(6, scores.getPlayersScore().size());

		players = createAmountOfPlayers(40);
		scores = new ScoreImpl(players);
		Assert.assertEquals(40, scores.getPlayersScore().size());

		Assert.assertEquals(2, scores.getPoints("30"));
	}

	public List<Player> createAmountOfPlayers(int amount) {
		List<Player> players = new ArrayList<Player>();
		for (int i = 0; i < amount; i++) {
			Player player = mock(Player.class);
			when(player.getId()).thenReturn(i + "");
			players.add(player);
		}

		return players;
	}
}
