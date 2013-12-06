package kth.game.othello.tournament;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import kth.game.othello.Othello;
import kth.game.othello.OthelloImpl;
import kth.game.othello.board.BoardCreatorImpl;
import kth.game.othello.board.NodeCreatorImpl;
import kth.game.othello.board.factory.BoardFactory;
import kth.game.othello.player.ComputerPlayer;
import kth.game.othello.player.Player;
import kth.game.othello.player.movestrategy.FirstPickStrategy;
import kth.game.othello.player.movestrategy.MostTurnedNodesStrategy;
import kth.game.othello.player.movestrategy.MoveStrategy;
import kth.game.othello.player.movestrategy.RandomStrategy;
import kth.game.othello.score.ScoreItem;
import kth.game.othello.view.swing.OthelloView;
import kth.game.othello.view.swing.OthelloViewFactory;

/**
 * Italian tournament class
 * 
 * @author christoffergunning
 * 
 */

public class Tournamentedidado {

	List<MoveStrategy> moveStrategies;
	ArrayList<Player> players;
	ArrayList<Othello> games;
	HashMap<String, Integer> scoreTable;

	public static void main(String[] args) {
		ArrayList<MoveStrategy> moveStrategies = new ArrayList<MoveStrategy>();
		moveStrategies.add(new RandomStrategy());
		moveStrategies.add(new FirstPickStrategy());
		moveStrategies.add(new MostTurnedNodesStrategy());
		Tournamentedidado t = new Tournamentedidado(moveStrategies);
		t.startTournamentedidadoWithGUI();

	}

	public Tournamentedidado(List<MoveStrategy> moveStrategies)
			throws IllegalArgumentException {
		if (moveStrategies.size() < 2)
			throw new IllegalArgumentException(
					"Not enough move strategies to create a tournament!");
		this.moveStrategies = moveStrategies;
		this.scoreTable = new HashMap<String, Integer>();
		this.players = createPlayers(moveStrategies);
		this.games = createGames(players);
	}

	private ArrayList<Player> createPlayers(List<MoveStrategy> moveStrategies) {
		ArrayList<Player> players = new ArrayList<Player>();
		for (MoveStrategy moveStrategy : moveStrategies) {
			ComputerPlayer player = new ComputerPlayer(moveStrategy.getName());
			player.setMoveStrategy(moveStrategy);
			players.add(player);
			scoreTable.put(player.getId(), 0);
		}
		return players;
	}

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

	private Othello createGame(Player player1, Player player2) {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(player1);
		players.add(player2);
		NodeCreatorImpl nodeCreator = new NodeCreatorImpl();
		BoardCreatorImpl boardCreator = new BoardCreatorImpl();

		return createGame(nodeCreator, boardCreator, players);
	}

	private Othello createGame(NodeCreatorImpl nodeCreator,
			BoardCreatorImpl boardCreator, ArrayList<Player> players) {
		BoardFactory boardFactory = new BoardFactory(nodeCreator, boardCreator);
		return new OthelloImpl(players, boardFactory.getQuadraticBoard(8,
				players));
	}

	public void startTournamentedidadoWithGUI() {
		for (Othello game : games) {
			OthelloView othelloView = OthelloViewFactory.create(game, 5, 10);
			othelloView.start(game.getPlayers().get(0).getId());
			playGame(game);
		}
		printTournamentScore();
	}

	public void startTournamentedidadoWithoutGUI() {
		for (Othello game : games) {
			game.start(game.getPlayers().get(0).getId());
			playGame(game);
		}
	}

	private void playGame(Othello game) {
		game.start(game.getPlayers().get(0).getId());
		while (game.isActive()) {
			game.move();
		}
		updateScore(game);
	}

	private void updateScore(Othello game) {
		ArrayList<ScoreItem> scores = (ArrayList<ScoreItem>) game.getScore()
				.getPlayersScore();

		ScoreItem bestScore = null;
		if (scores.get(0).getScore() > scores.get(1).getScore())
			bestScore = scores.get(0);
		else if (scores.get(0).getScore() < scores.get(1).getScore())
			bestScore = scores.get(1);

		if (bestScore == null) {
			scoreTable.put(scores.get(0).getPlayerId(),
					scoreTable.get(scores.get(0).getPlayerId()) + 1);
			scoreTable.put(scores.get(1).getPlayerId(),
					scoreTable.get(scores.get(1).getPlayerId()) + 1);
		} else {
			scoreTable.put(bestScore.getPlayerId(),
					scoreTable.get(bestScore.getPlayerId()) + 3);
		}
	}

	private void printTournamentScore() {
		int i = 1;
		while (scoreTable.size() > 0) {
			int bestScore = 0;
			String bestPlayerId = "";
			for (String playerId : scoreTable.keySet()) {
				if (scoreTable.get(playerId) >= bestScore) {
					bestScore = scoreTable.get(playerId);
					bestPlayerId = playerId;
				}
			}
			String playerName = "";

			for (Player player : players) {
				if (player.getId().equals(bestPlayerId)) {
					playerName = player.getName();
				}
			}
			System.out.println(i + ": " + playerName + " - " + bestScore);
			scoreTable.remove(bestPlayerId);
			i++;
		}
	}
}
