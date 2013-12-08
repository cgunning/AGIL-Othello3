package kth.game.othello.tournament;

import java.util.ArrayList;
import java.util.List;

import kth.game.othello.Othello;
import kth.game.othello.OthelloImpl;
import kth.game.othello.board.BoardCreatorImpl;
import kth.game.othello.board.NodeCreatorImpl;
import kth.game.othello.board.factory.BoardFactory;
import kth.game.othello.player.ComputerPlayer;
import kth.game.othello.player.Player;
import kth.game.othello.player.movestrategy.MoveStrategy;

/**
 * The responsibility of this class is to create a Tournament of computer
 * players with the specified move strategies
 * 
 * @author Nils Dahlbom Norgren, Christoffer Gunning
 * 
 */
public class TournamentFactory {

	public Tournament createTournament(List<MoveStrategy> moveStrategies) {
		if (moveStrategies.size() < 2)
			throw new IllegalArgumentException(
					"Not enough move strategies to create a tournament!");
		ArrayList<Player> players = createPlayers(moveStrategies);
		TournamentScore scores = new TournamentScore(players);
		ArrayList<Othello> games = createGames(players);
		return new Tournament(players, games, scores);
	}

	/**
	 * Creates computer players, one for each move strategy
	 * 
	 * @param moveStrategies
	 *            the move strategies to create computer players for
	 * @return an ArrayList of computer players, one with each move strategy
	 */
	private ArrayList<Player> createPlayers(List<MoveStrategy> moveStrategies) {
		ArrayList<Player> players = new ArrayList<Player>();
		for (MoveStrategy moveStrategy : moveStrategies) {
			ComputerPlayer player = new ComputerPlayer(moveStrategy.getName());
			player.setMoveStrategy(moveStrategy);
			players.add(player);
		}
		return players;
	}

	/**
	 * Creates all games that are supposed to be played
	 * 
	 * @param players
	 *            the players in the tournament
	 * @return an ArrayList of all the games created
	 */
	private ArrayList<Othello> createGames(ArrayList<Player> players) {
		ArrayList<Othello> games = new ArrayList<Othello>();
		for (Player player1 : players) {
			for (Player player2 : players) {
				if (player1.getId().equals(player2.getId()))
					continue;

				games.add(createGame(player1, player2));
			}
		}
		return games;
	}

	/**
	 * Creates a game
	 * 
	 * @param player1
	 *            the first player
	 * @param player2
	 *            the second player
	 * @return a Game with the two players
	 */
	private Othello createGame(Player player1, Player player2) {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(player1);
		players.add(player2);
		NodeCreatorImpl nodeCreator = new NodeCreatorImpl();
		BoardCreatorImpl boardCreator = new BoardCreatorImpl();

		return createGame(nodeCreator, boardCreator, players);
	}

	/**
	 * Creates a game
	 * 
	 * @param nodeCreator
	 *            a NodeCreator
	 * @param boardCreator
	 *            a BoardCreator
	 * @param players
	 *            an ArrayList of players
	 * @return a game on a classical board with the players specified
	 */
	private Othello createGame(NodeCreatorImpl nodeCreator,
			BoardCreatorImpl boardCreator, ArrayList<Player> players) {
		BoardFactory boardFactory = new BoardFactory(nodeCreator, boardCreator);
		return new OthelloImpl(players, boardFactory.getQuadraticBoard(8,
				players));
	}
}
