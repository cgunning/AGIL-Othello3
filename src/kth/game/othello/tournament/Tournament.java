package kth.game.othello.tournament;

import java.util.ArrayList;
import java.util.List;

import kth.game.othello.Othello;
import kth.game.othello.player.Player;
import kth.game.othello.player.movestrategy.FirstPickStrategy;
import kth.game.othello.player.movestrategy.MostTurnedNodesStrategy;
import kth.game.othello.player.movestrategy.MoveStrategy;
import kth.game.othello.player.movestrategy.RandomStrategy;
import kth.game.othello.view.swing.OthelloView;
import kth.game.othello.view.swing.OthelloViewFactory;

/**
 * This class is responsible for creating and executing
 * 
 * @author Nils Dahlbom Norgren, Christoffer Gunning
 * 
 */

public class Tournament {

	List<MoveStrategy> moveStrategies;
	ArrayList<Player> players;
	ArrayList<Othello> games;
	TournamentScore scores;

	public static void main(String[] args) {
		ArrayList<MoveStrategy> moveStrategies = new ArrayList<MoveStrategy>();
		moveStrategies.add(new RandomStrategy());
		moveStrategies.add(new FirstPickStrategy());
		moveStrategies.add(new MostTurnedNodesStrategy());
		TournamentFactory tournamentFactory = new TournamentFactory();
		Tournament t = tournamentFactory.createTournament(moveStrategies);
		t.startTournament(true);
	}

	Tournament(ArrayList<Player> players, ArrayList<Othello> games,
			TournamentScore scores) {
		this.scores = scores;
		this.players = players;
		this.games = games;
	}

	/**
	 * Starts and plays the tournament. Prints the final score when the
	 * tournament has ended
	 * 
	 * @param withGUI
	 *            Enables GUI if true. If it is false, the game is played hidden
	 *            and only results are printed
	 */
	public void startTournament(boolean withGUI) {
		for (Othello game : games) {
			if (withGUI) {
				OthelloView othelloView = OthelloViewFactory
						.create(game, 5, 10);
				othelloView.start(game.getPlayers().get(0).getId());
			} else {
				game.start(game.getPlayers().get(0).getId());
			}
			playGame(game);
		}
		scores.printTournamentScore();
	}

	/**
	 * Plays the game to the end
	 * 
	 * @param game
	 *            The game to be played
	 */
	private void playGame(Othello game) {
		game.start(game.getPlayers().get(0).getId());
		while (game.isActive()) {
			game.move();
		}
		scores.updateScore(game);
	}

}
