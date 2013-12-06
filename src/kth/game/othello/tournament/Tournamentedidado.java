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
import kth.game.othello.player.movestrategy.FirstPickStrategy;
import kth.game.othello.player.movestrategy.MostTurnedNodesStrategy;
import kth.game.othello.player.movestrategy.MoveStrategy;
import kth.game.othello.player.movestrategy.RandomStrategy;

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

	public static void main(String[] args) {
		ArrayList<MoveStrategy> moveStrategies = new ArrayList<MoveStrategy>();
		moveStrategies.add(new RandomStrategy());
		moveStrategies.add(new FirstPickStrategy());
		moveStrategies.add(new MostTurnedNodesStrategy());
		Tournamentedidado t = new Tournamentedidado(moveStrategies);
		t.startTournamentedidado();
	}

	public Tournamentedidado(List<MoveStrategy> moveStrategies)
			throws IllegalArgumentException {
		if (moveStrategies.size() < 2)
			throw new IllegalArgumentException(
					"Not enough move strategies to create a tournament!");
		this.moveStrategies = moveStrategies;
		this.players = createPlayers(moveStrategies);
		this.games = createGames(players);
	}

	private ArrayList<Player> createPlayers(List<MoveStrategy> moveStrategies) {
		ArrayList<Player> players = new ArrayList<Player>();
		for (MoveStrategy moveStrategy : moveStrategies) {
			ComputerPlayer player = new ComputerPlayer(moveStrategy.getName());
			player.setMoveStrategy(moveStrategy);
			players.add(player);
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

	public void startTournamentedidado() {
		for (Othello game : games) {
			game.start(game.getPlayers().get(0).getId());
			while (game.isActive()) {
				game.move();
			}
			System.out.println("Finished - isActive() = " + game.isActive());
		}
	}
}
